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
    minmaxBonus(managers,result);
    System.out.println("first:" + result.getFirst().getName()+",second:"+result.getSecond().getName());
    maxminBonus(managers,result);
    System.out.println("first:" + result.getFirst().getName()+",second:"+result.getSecond().getName());
  }

  public static void printBuddies(Pair<? extends Employee> pair){
    Employee first = pair.getFirst();
    Employee second = pair.getSecond();
    System.out.println(first.getName() + "and" +second.getName() + "are buddies");
  }

  public static void minmaxBonus(Manager[] managers, Pair<? super Manager> result){
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

  public static void maxminBonus(Manager[] managers, Pair<? super Manager> result){
    minmaxBonus(managers,result);
    PairAlg.swapHelper(result);
  }

  static class PairAlg{
    public static boolean hasNulls(Pair<?> p){
      return p.getFirst() == null || p.getSecond() == null;
    }

    public static void swap(Pair<?> p){swapHelper(p);}

    public static <T> void swapHelper(Pair<T> p){
      T t = p.getFirst();
      p.setFirst(p.getSecond());
      p.setSecond(t);
    }
  }
}
