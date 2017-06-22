package executor;

import java.util.concurrent.Executor;

/**
 * Created by benwq on 2017/6/19.
 */
public class ThreadPerTaskWebServer implements Executor{
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
