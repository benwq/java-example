package ineratortest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by benwq on 2017/4/21.
 */
public class IntratorTest {
    static List<String> list = new ArrayList<String>();

    public static void main(String []args){
        for(int i=0;i<1000;i++){
            list.add(""+i);
        }

        new Thread(()->{
            Iterator iterator = list.iterator();
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
        }).start();

        new Thread(()->{
            for(int i=0;i<1000;i++){
                list.add(""+(i+1000));
            }
        }).start();
    }
}
