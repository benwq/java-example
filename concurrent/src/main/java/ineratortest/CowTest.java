package ineratortest;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by benwq on 2017/4/28.
 */
public class CowTest {
    private static CopyOnWriteArraySet<Integer> safeSet = new CopyOnWriteArraySet<>();
//    private static Set<Integer> safeSet = new HashSet<>();
    private static Integer count = 0;
    private static Integer sum = 0;
    public static void main(String[]args){
        for(int i=0;i<10000;i++){
            safeSet.add(i);
        }
        new Thread(()->{
            for(int i=0;i<1000;i++){
                if(safeSet.contains(i))
                    safeSet.remove(i);
            }
            System.out.println("size2:"+safeSet.size());
        }).start();

        new Thread(()->{
            for(Integer i:safeSet){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count ++;
                sum += i;
//                System.out.println("i="+i+","+"count="+count);
            }
            System.out.println("count:"+count);
            System.out.println("sum:"+sum);
            System.out.println("size1:"+safeSet.size());
        }).start();

        Integer[] iArr = new Integer[1000];
        for(int i=0;i<1000;i++){
            iArr[i]=i;
        }
        ClassA a = new ClassA(iArr);
        iArr[999]=null;
        System.out.println(iArr[999]);
        System.out.println(a.get(999));
    }

}

class ClassA{
    final Integer[] i2;

    public ClassA(Integer[] i2) {
        this.i2 = i2;
    }

    public Integer get(int i){
        return i2[i];
    }
}
