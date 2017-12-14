package semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            semaphore.release();
            e.printStackTrace();
        }
        System.out.println(1);
    }
}
