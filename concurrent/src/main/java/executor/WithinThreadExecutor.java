package executor;

import java.util.concurrent.Executor;

/**
 * Created by benwq on 2017/6/19.
 */
public class WithinThreadExecutor implements Executor{
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
