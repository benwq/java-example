package entities;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by benwq on 2017/7/5.
 */
public class TravelCompony {

    public TravelQuote solicitQuote(TravelInfo travelInfo){
        try {
            SECONDS.sleep(1);
            return new TravelQuote();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
