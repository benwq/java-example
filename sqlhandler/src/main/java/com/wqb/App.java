package com.wqb;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{

    /**gps库配置*/
    private static String gpsDriverClassName;
    private static String gpsUrl;
    private static String gpsUsername;
    private static String gpsPassword;
    static{
        gpsDriverClassName = "com.mysql.jdbc.Driver";
        gpsUrl = "jdbc:mysql://10.10.99.32:3306/rll?useUnicode=true&characterEncoding=utf-8";
        gpsUsername = "root";
        gpsPassword = "supconit";
    }
    /**gps库配置*/

    public static void main( String[] args )
    {
        List<String> filename_prefix_list = new ArrayList<>();
        filename_prefix_list.add("G:\\公司文档\\R-人流量西湖\\cfSql\\pc_wifi_data_\\rll_0406_pc_wifi_data_");
        filename_prefix_list.add("G:\\公司文档\\R-人流量西湖\\cfSql\\pc_wifi_track_\\rll_0406_pc_wifi_track_");
        for(String filename_prefix:filename_prefix_list) {
            for (int i = 1; i <= 10; i++) {
                String fileName = filename_prefix + i + ".sql";
                String fileName2 = filename_prefix + i + "_c.sql";
                try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
                     BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName2)))) {
                    String txt = null;
                    List<String> sqlList = new ArrayList<>();
                    int count = 0;
                    bw.write("START TRANSACTION;\n");
                    while ((txt = br.readLine()) != null) {
                        count++;
                        bw.write(txt + "\n");
                        if (count == 5000) {
                            count = 0;
                            bw.write("COMMIT;\n");
                            bw.write("START TRANSACTION;\n");
                        }

                    }
                    bw.write("COMMIT;\n");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(fileName2 + "---完成");
            }
        }
    }
}
