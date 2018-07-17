package queuetest;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T1 {
    private ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
    private LinkedList<String> linkedList = new LinkedList<>();

    public void test(){
        concurrentLinkedQueue.poll();
        concurrentLinkedQueue.peek();
        linkedList.get(1);
    }
}
