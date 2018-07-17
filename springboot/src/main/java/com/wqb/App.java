package com.wqb;

import java.io.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String result = test("java -version");
        System.out.println(result);
    }

    public static String test(String commandStr) {
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(commandStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();
        } catch (
                Exception e)

        {
            e.printStackTrace();
        } finally

        {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
