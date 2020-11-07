package pair3;

import generic_class.Employee;
import generic_class.Manager;
import generic_class.Pair;

public class PairTest3 {
  public static void main(String[] args) {
    Manager ceo = new Manager("Gus Greedy", 800000, 2003, 12, 15);
    Manager cfo = new Manager("Sid Sneaky", 600000, 2003, 12, 15);

    Pair<Manager> buddies = new Pair<>(ceo, cfo);
    printBuddies(buddies);

    ceo.setBonus(1000000);
    cfo.setBonus(500000);
    Manager[] managers = {ceo, cfo};

    Pair<Employee> result = new Pair<>();
    minmaxBonus(managers, result);
    System.out.println(
        "first:" + result.getFirst().getName() + ",second:" + result.getSecond().getName());
    maxminBonus(managers, result);
    System.out.println(
        "first:" + result.getFirst().getName() + ",second:" + result.getSecond().getName());
  }
/*
通配符概念
在通配符类型中，允许类型参数发生变化。Pair<? extends Employee>
表示任何泛型 Pair 类型，它的类型参数是 Employee 的子类，如 Pair<Manager>，但不是 Pair<String>。
 */
  public static void printBuddies(Pair<? extends Employee> pair) {
    Employee first = pair.getFirst();
    Employee second = pair.getSecond();
    System.out.println(first.getName() + "and" + second.getName() + "are buddies");
  }
  /*
    通配符限定与类型变量限定十分类似，但是，还有一个附加的能力，即可以指定一个超类型限定。
    <? super Manager>
    这个通配符限制为Manager的所有超类型。
   */
  public static void minmaxBonus(Manager[] managers, Pair<? super Manager> result) {
    if (managers.length == 0) {
      return;
    }
    Manager min = managers[0];
    Manager max = managers[0];
    for (int i = 0; i < managers.length; i++) {
      if (min.getBonus() > managers[i].getBonus()) {
        min = managers[i];
      }
      if (max.getBonus() < managers[i].getBonus()) {
        max = managers[i];
      }
      result.setFirst(min);
      result.setSecond(max);
    }
  }

  public static void maxminBonus(Manager[] managers, Pair<? super Manager> result) {
    minmaxBonus(managers, result);
    PairAlg.swapHelper(result);
  }

  static class PairAlg {
    /*
     无限定通配符
     还可以使用根本无限定的通配符，Pair<?>
     ? getFirst()的返回值只能赋值给一个 Object，setFirst 方法不能被调用，甚至不能用 Object 调用。
     Pair<?> 和 Pair 本质的不同：可以用任意 Object 对象调用原始 Pair 类的 setFirst 方法。
     可以调用 setFirst(null)。
    */
    public static boolean hasNulls(Pair<?> p) {
      return p.getFirst() == null || p.getSecond() == null;
    }

    /*
     通配符捕获
     通配符不是类型变量，不能在编写代码中使用"?"作为一种类型。
     ？t = p.getFirst(); // 错误
    */
    public static void swap(Pair<?> p) {
      swapHelper(p);
    }

    public static <T> void swapHelper(Pair<T> p) {
      T t = p.getFirst();
      p.setFirst(p.getSecond());
      p.setSecond(t);
    }
  }
}
