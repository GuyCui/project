package pair1;

import generic_class.Pair;

public class PairTest1 {
    /**
     * 使用静态的 minmax 方法遍历数组并同时计算出最小值和最大值
     */
    public static void main(String[] args) {
        String[] words = {"Mary", "had", "a", "little", "lamb"};
        Pair<String> mm = ArrayAlg.minmax(words);
        System.out.println("min = " + mm.getFairst());
        System.out.println("max = " + mm.getSecond());
    }

    static class ArrayAlg{
        public static Pair<String> minmax(String[] a){
            if (a == null || a.length == 0) {
                return null;
            }
            String min = a[0];
            String max = a[0];
            /*
             * 用 compareTo 方法比较两个字符串
             * 如果字符串相同则返回 0
             * 按照字典顺序，如果第一个字符串比第二个字符串靠前就返回一个负整数
             * 否则返回一个正整数
             */
            for (int i = 1; i < a.length; i++){
                if (min.compareTo(a[i]) > 0){
                    min = a[i];
                }
                if (max.compareTo(a[i]) < 0) {
                    max = a[i];
                }
            }
            return new Pair<String>(min,max);
        }
    }
}
