package linkedList;

import sun.plugin.javascript.navig.Link;

public class LinkedListTest {
  public interface Queue<
      E> { // a simplified form of the interface in the standard library 标准库中接口的简化形式
    void add(E element);

    E remove();

    int size();
  }

  public class CircularArrayQueue<E> implements Queue<E> { // not an actual library class
    private int head;
    private int tail;

    @Override
    public void add(E element) {}

    @Override
    public E remove() {
      return null;
    }

    @Override
    public int size() {
      return 0;
    }

    private E[] elements;
  }
  public class LinkedListQueue<E> implements Queue<E> {
    private Link head;
    private Link tail;

    LinkedListQueue(){}

    @Override
    public void add(E element) {

    }

    @Override
    public E remove() {
      return null;
    }

    @Override
    public int size() {
      return 0;
    }
  }
}
