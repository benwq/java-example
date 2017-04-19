package listextends;

import anno.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by be on 2017-4-19.
 *
 */
@ThreadSafe
public class ListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public boolean putIfAbsent(E x){
        synchronized (list){
            boolean exist = list.contains(x);
            if(!exist) list.add(x);
            return !exist;
        }
    }
}
