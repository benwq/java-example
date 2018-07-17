package com.wqb.monitortool;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author benwq
 * @Description:java执行外部脚本非常占用系统资源，测试所占用的CPU及内存
 *
 * @Date: 14:57 2018/4/24
 */
public class ExecTest {
    public static void main(String[] args) {
        for(int i=0;i<1000;i++){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(()->{
                try {
                    Process process = Runtime.getRuntime().exec("dir");
                    InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
                    System.out.println(inputStreamReader.read());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
