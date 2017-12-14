package python;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PythonTest {
    @Test
    public void test() {
        Long start = System.currentTimeMillis();
        Process process;
        try {
            process = Runtime.getRuntime().exec("python G:\\workspace\\test\\java-example\\pluginApi\\src\\main\\java\\python\\proving_listdata.py 35");
            process.waitFor();
            BufferedReader stdOut=new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while((s=stdOut.readLine())!=null){
                System.out.println(s.toString());
            }

            int result=process.waitFor();
//            System.out.println(result);
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long end=System.currentTimeMillis();
        System.out.println((end-start)+"ms");
    }

}
