package pair2;

import generic_class.Pair;

import java.time.LocalDate;

/** 类型变量的限定 */
public class PairTest2 {

  public static void main(String[] args) {
    LocalDate[] birthdays = {
            LocalDate.of(1906,12,9),
            LocalDate.of(1815,12,10),
            LocalDate.of(1999,10,19),
            LocalDate.of(1976,01,11),
    };
    Pair<LocalDate> mm = ArrayAlg.minmax(birthdays);
    System.out.println("min =" + mm.getFirst());
    System.out.println("max =" + mm.getSecond());
  }

  static class ArrayAlg {

    /*
     * 为什么使用关键字 extends 而不是 implements? 毕竟 Comparable 是一个接口。
     * <T extends BoundingType> 表示 T 应该是限定类型(bounding type)的子类型(subtype)。T 和限定类型可以是类，也可以是接口。
     * 选择关键字 extends 的原因是它更接近子类型的概念，并且 Java 的设计者也不打算在语言中再添加一个新的关键字(如 sub)。
     * 一个类型变量或通配符可以有多个限定，例如：
     * <T extends Comparable & Serializable>
     * 限定类型用“&”分隔，而逗号用来分隔类型变量。
     * 在 Java 的继承中，可以根据需要拥有多个接口超类型，但最多有一个限定可以是类。如果有一个类作为限定，它必须是限定列表中的第一个限定。
     */
    public static <T extends Comparable> Pair<T> minmax(T[] a) {
      if (a == null || a.length == 0) {
        return null;
      }
      T min = a[0];
      T max = a[0];

      for (int i = 1; i < a.length; i++) {
        if (min.compareTo(a[i]) > 0) {
          min = a[i];
        }
        if (max.compareTo(a[i]) < 0) {
          max = a[i];
        }
      }
      return new Pair<T>(min, max);
    }
  }
}
