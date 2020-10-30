package generic_class;

/**
 * 常见的做法是类型变量使用大写字母，而且很简短。Java 库使用变量 E 表示集合的元素类型，
 * K 和 V 分别表示键和值的类型。T（必要时还可以用相邻的字母 U 和 S）表示“任意类型”。
 * @param <T>
 */
public class Pair<T> {
    private T fairst;
    private T second;

    public Pair() {
        fairst = null;
        second = null;
    }

    public Pair(T fairst, T second) {
        this.fairst = fairst;
        this.second = second;
    }

    public T getFairst() {
        return fairst;
    }

    public T getSecond() {
        return second;
    }

    public void setFairst(T newValue) {
        fairst = newValue;
    }

    public void setSecond(T newValue) {
        second = newValue;
    }
}
