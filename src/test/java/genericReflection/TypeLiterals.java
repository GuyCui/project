package genericReflection;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class TypeLiterals {
  public static class Sample{
    ArrayList<Integer> nums;
    ArrayList<Character> chars;
    ArrayList<String> strings;

    public Sample(){
      nums = new ArrayList<>();
      nums.add(42);
      nums.add(1729);
      chars = new ArrayList<>();
      chars.add('H');
      chars.add('i');
      strings = new ArrayList<>();
      strings.add("Hello");
      strings.add("World");
    }
  }

  private static <T> String join(String separator,ArrayList<T> elements){
    StringBuffer result = new StringBuffer();
    for (T e : elements){
      if (result.length() > 0) {
        result.append(separator);
      }
      result.append(e.toString());
    }
    return result.toString();
  }

  public static void main(String[] args) throws IllegalAccessException {
    Formatter formatter = new Formatter();
    formatter.forType(new TypeLiteral<ArrayList<Integer>>(){},
            lst -> join(" ",lst));
    formatter.forType(new TypeLiteral<ArrayList<Character>>(){},
            lst -> "\"" + join("",lst) + "\"");
    System.out.println(formatter.formatFields(new Sample()));
  }
}

/**
 * A type literal describes a type that can be generic, such as ArrayList<String>.
 * 类型文字描述了可以通用的类型，例如ArrayList <String>。
 *
 * @param <T>
 */
class TypeLiteral<T> {
  private Type type;

  /**
   * TypeLiteral 构造器会捕获泛型超类型：
   */
  public TypeLiteral() {
    Type parentType = getClass().getGenericSuperclass();
    if (parentType instanceof ParameterizedType) {
      type = ((ParameterizedType) parentType).getActualTypeArguments()[0];
    } else throw new UnsupportedOperationException("Counstruct as new TypeLiteral<...>(){}");
  }

  private TypeLiteral(Type type) {
    this.type = type;
  }

  public static TypeLiteral<?> of(Type type) {
    return new TypeLiteral<Object>(type);
  }

  public String toString() {
    if (type instanceof Class) {
      return ((Class<?>) type).getName();
    } else {
      return type.toString();
    }
  }

  public boolean equals(Object otherObject) {
    return otherObject instanceof TypeLiteral && type.equals(((TypeLiteral<?>) otherObject).type);
  }

  public int hashCode() {
    return type.hashCode();
  }
}

class Formatter {
  private Map<TypeLiteral<?>, Function<?, String>> rules = new HashMap<>();

  public <T> void forType(TypeLiteral<T> type, Function<T, String> formatterForType) {
    rules.put(type, formatterForType);
  }

  public String formatFields(Object obj) throws IllegalAccessException, IllegalArgumentException {
    StringBuffer result = new StringBuffer();
     for (Field f : obj.getClass().getDeclaredFields()){
      result.append(f.getName());
      result.append("=");
      f.setAccessible(true);
      Function<?,String> formatterForType = rules.get(TypeLiteral.of(f.getGenericType()));
       if (formatterForType != null) {
         @SuppressWarnings("unchecked")
         Function<Object,String> objFormatter = (Function<Object, String>) formatterForType;
         result.append(objFormatter.apply(f.get(obj)));
       }else {
         result.append(f.get(obj).toString());
       }
      result.append("\n");
    }
    return result.toString();
  }
}