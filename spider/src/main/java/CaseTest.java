
public class CaseTest {
    public static void main(String[] args) {
        int a =2;
        switch (a){
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
            case 3:
                System.out.println(3);
            case 4:
                System.out.println(4);
            case 5:
                System.out.println(5);
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println(1)));
        System.out.println("cpus:"+Runtime.getRuntime().availableProcessors());
    }
}
