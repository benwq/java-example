package file;

import java.io.*;

/**
 * Created by benwq on 2017/5/18.
 */
public class FileTest {
    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            int code = i+1;
            int size = 10000000;
            int start = i*size;
            new Thread(new ReadRunnable(start,size,code)).start();
        }
    }

}

class ReadRunnable implements Runnable{
    private int code;
    private int start;
    private int size;

    public ReadRunnable(int start,int size,int code){
        this.start = start;
        this.size = size;
        this.code = code;
    }


    //pc_equ_data_已拆分完成
    @Override
    public void run() {
        String in_url = "C://rll_0406/rll_0406.sql";
//        String out_url = "C://rll_0406/rll_0406"+code+".sql";
//        String out_url = "C://rll_0406/rll_0406_pc_equ_data_"+code+".sql";
//        String out_url = "C://rll_0406/rll_0406_pc_logic_area_data_"+code+".sql";
//        String out_url = "C://rll_0406/rll_0406_pc_logic_entry_data_"+code+".sql";
//        String out_url = "C://rll_0406/rll_0406_pc_report_data_"+code+".sql";
//        String out_url = "C://rll_0406/rll_0406_pc_wifi_data_"+code+".sql";
//        String out_url = "C://rll_0406/rll_0406_pc_wifi_track_"+code+".sql";
        String out_url = "C://rll_0406/rll_0406_equdata"+code+".sql";
        File in_file = new File(in_url);
        File out_file = new File(out_url);
        try {
            FileInputStream fileInputStream = new FileInputStream(in_file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            FileOutputStream fileOutputStream = new FileOutputStream(out_file);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            String str = null;
            int count =1;
            while((str = bufferedReader.readLine()) != null&&(count<=start+size))
            {
                count ++;
                if(count%10000==0){
                    System.out.println(count);
                    bufferedWriter.flush();
                }
                if(count<=start+1) continue;
                String lowerStr = str.toLowerCase();
//                if(lowerStr.contains("insert into `pc_equ_data_")
//                        ||lowerStr.contains("insert into `pc_logic_area_data_")
//                        ||lowerStr.contains("insert into `pc_logic_entry_data_")
//                        ||lowerStr.contains("insert into `pc_staytime_data_")
//                        ||lowerStr.contains("insert into `pc_errorlog`")
//                        ||lowerStr.contains("insert into `pc_logic_entry_data_")
//                        ||lowerStr.contains("insert into `pc_report_data_")
//                        ||lowerStr.contains("insert into `pc_wifi_data_")
//                        ||lowerStr.contains("insert into `pc_wifi_track_")
//                        ||lowerStr.contains("insert into `equdata`")
//                        ||lowerStr.contains("insert into `equdata_copy`")){
//                    continue;
//                }else{
//                    bufferedWriter.write(str+"\n");
//                }
                if(lowerStr.contains("insert into `equdata")||lowerStr.contains("insert into `equdata_copy`")){
                    bufferedWriter.write(str+"\n");
                }
            }
            bufferedWriter.flush();
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}