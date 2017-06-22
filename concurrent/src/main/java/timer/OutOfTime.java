package timer;

import java.util.Timer;
import java.util.TimerTask;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by benwq on 2017/6/22.
 */
public class OutOfTime {

    public static void main(String[] args) throws Exception{
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(),1);
        SECONDS.sleep(1);
        timer.schedule(new ThrowTask(),5);
        SECONDS.sleep(5);
    }


    public static void throwE(){
        throw new RuntimeException();
    }

    static class ThrowTask extends TimerTask{
        @Override
        public void run() {
            throw new RuntimeException();
        }
    }
}
