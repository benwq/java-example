package volatiletest;

/**
 * Created by be on 2017-4-18.
 */
public class TestWithoutVolatile {
    private static boolean vChanged;

    public static void main(String args[]) throws InterruptedException{
        new Thread(
            ()->{
                for(;;){
                    if(vChanged!=vChanged){
                        System.out.println("!=");
                        System.exit(0);
                    }
                }
            }
        ).start();
        Thread.sleep(1);
        new Thread(
            ()->{
                for(;;){
                    vChanged = !vChanged;
                }
            }
        ).start();
    }
}
