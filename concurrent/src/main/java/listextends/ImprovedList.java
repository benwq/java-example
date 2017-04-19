package listextends;

import anno.ThreadSafe;

import java.util.List;

/**
 * Created by be on 2017-4-19.
 */
@ThreadSafe
public class ImprovedList<E> {
    private final List<E> list;

    public ImprovedList(List<E> list) {
        this.list = list;
    }

    public synchronized boolean putIfAbsent(E x){
        boolean exist = list.contains(x);
        if(!exist)
            list.add(x);
        return !exist;
    }

    public synchronized void clear(){
        list.clear();
    }
}
