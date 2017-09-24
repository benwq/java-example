package freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.lucene.document.DateTools;
import org.junit.Test;
import utils.DateTool;

import java.io.*;
import java.util.*;

public class FreeMarkerTest {

    private static String exportPath = "G:\\公司文档\\R-人流量西湖\\";

    public String  createDoc(Map<String,Object> dataMap) {
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        //设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
        //这里我们的模板是放在com.havenliu.document.template包下面
        configuration.setClassForTemplateLoading(this.getClass(), "/");
        //configuration.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "/com/supconit/peoplecounting/report/controller/");

        Template t=null;
        try {
            //test.ftl为要装载的模板
            t = configuration.getTemplate("report3.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //输出文档路径及名称
        File outFileDir = new File(exportPath);
        if(!outFileDir.exists()){
            outFileDir.mkdirs();
        }

        String fileName = dataMap.get("year")+(String)dataMap.get("timeRangeName")+"游客量监测月报2.doc";
        File outFile = new File(exportPath +fileName);

        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            fileName = null;
        }

        try {
            t.process(dataMap, out);
        } catch (TemplateException e) {
            e.printStackTrace();
            fileName = null;
        } catch (IOException e) {
            e.printStackTrace();
            fileName = null;
        }finally{
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return fileName;

    }

    @Test
    public void test() {
        FreeMarkerTest freeMarkerTest = new FreeMarkerTest();
        Map<String,Object> dataMap = new HashMap<>();

        /**excel数据内容填充*/
        dataMap.put("year","2017");
        dataMap.put("timeRangeName","端午节");
        dataMap.put("startDate", DateTool.toDate("2017-05-28","yyyy-MM-dd"));
        dataMap.put("endDate",DateTool.toDate("2017-05-30","yyyy-MM-dd"));
        dataMap.put("prevYearStartDate",DateTool.toDate("2016-06-09","yyyy-MM-dd"));
        dataMap.put("prevYearEndDate",DateTool.toDate("2016-06-11","yyyy-MM-dd"));
        dataMap.put("prevYear","2016");
        dataMap.put("part1_up_str","柳浪闻莺北段、六和塔、花港观鱼、平湖秋月、抱朴道院、清行宫遗址");                          //目录1内容上升区域
        dataMap.put("part1_down_str","钱王祠码头、白堤、断桥");                                                         //目录1内容下降区域
        dataMap.put("part1_flat_str","钱王祠码头、白堤、断桥等稳中有降；岳飞墓（庙）、涌金桥、小瀛洲、文澜阁、净慈寺");           //目录1内容持平区域

        List<Map<String,Object>> part2_detail_list = new ArrayList<>();                 //各区域数据list

        Map<String,Object> part2_detail_map = new HashMap<>();
        part2_detail_map.put("areaName","岳飞墓（庙）");
        part2_detail_map.put("timeRangeRll","17290");                                   //时间段内的总人流量
        part2_detail_map.put("upBate","2.02%");                                          //同比增幅百分比

        List<Map<String,Object>> part2_detail_logicarea_list = new ArrayList<>();       //逻辑区域数据list

        Map<String,Object> logicarea_map = new HashMap<>();                             //各逻辑区域详细数据
        logicarea_map.put("logicAreaName","岳飞墓（庙）园区");
        /**逻辑区域表一开始*/
        List<Map<String,Object>> tb1_list = new ArrayList<>();                          //表1 datalist
        Map<String,Object> tb1_map1 = new HashMap<>();
        tb1_map1.put("dateStr","2017-05-28");
        tb1_map1.put("rllData","6131");
        tb1_map1.put("prevDateStr","2016-06-09");
        tb1_map1.put("prevRllData","6490");
        tb1_map1.put("upBate","-5.53%");
        tb1_map1.put("upOrDown","-1");
        tb1_list.add(tb1_map1);

        Map<String,Object> tb1_map2 = new HashMap<>();
        tb1_map2.put("dateStr","2017-05-29");
        tb1_map2.put("rllData","6716");
        tb1_map2.put("prevDateStr","2016-06-10");
        tb1_map2.put("prevRllData","6564");
        tb1_map2.put("upBate","2.32%");
        tb1_map2.put("upOrDown","1");
        tb1_list.add(tb1_map2);

        Map<String,Object> tb1_map3 = new HashMap<>();
        tb1_map3.put("dateStr","2017-05-30");
        tb1_map3.put("rllData","4443");
        tb1_map3.put("prevDateStr","2016-06-11");
        tb1_map3.put("prevRllData","3894");
        tb1_map3.put("upBate","14.10%");
        tb1_map3.put("upOrDown","1");
        tb1_list.add(tb1_map3);

        logicarea_map.put("tb1_list",tb1_list);
        logicarea_map.put("tb1_totalRllData","17290");
        logicarea_map.put("tb1_totalPrevRllData","16948");
        logicarea_map.put("tb1_updateBate","2.02%");
        logicarea_map.put("tb1_upOrDown","1");
        /**逻辑区域表一结束*/

        /**逻辑区域表二开始*/
        List<String> tb2_dateStrList = new ArrayList<>();
        tb2_dateStrList.add("2017-05-28");
        tb2_dateStrList.add("2017-05-29");
        tb2_dateStrList.add("2017_05-30");
        logicarea_map.put("tb2_dateStrList",tb2_dateStrList);
        List<String> tb2_timeStrList = new ArrayList<>();
        for(int i=7;i<=18;i++){
            tb2_timeStrList.add(i+"");
        }
        logicarea_map.put("tb2_timeStrList",tb2_timeStrList);
        Map<String,Object> tb2_data_map = new HashMap<>();
        Random random = new Random();
        for(int i=0;i<tb2_timeStrList.size();i++){
            String timeStr = tb2_timeStrList.get(i);
            for(int k=0;k<tb2_dateStrList.size();k++){
                String dateStr = tb2_dateStrList.get(k);
                tb2_data_map.put(timeStr+"&"+dateStr+"&IN",random.nextInt(200)+"");
                tb2_data_map.put(timeStr+"&"+dateStr+"&OUT",random.nextInt(200)+"");
                tb2_data_map.put(timeStr+"&"+dateStr+"&STAYCOUNT",random.nextInt(200)+"");
            }
        }
        for(int i=0;i<tb2_dateStrList.size();i++){
            String dateStr = tb2_dateStrList.get(i);
            tb2_data_map.put(dateStr+"&ALLIN",random.nextInt(7000)+"");
            tb2_data_map.put(dateStr+"&ALLOUT",random.nextInt(7000)+"");
        }
        tb2_data_map.put("test",6085+"");
        logicarea_map.put("tb2_data_map",tb2_data_map);
        /**逻辑区域表二结束*/
//        Map<String,Object> logicarea_map2 = new HashMap<>();
//        logicarea_map2.put("logicAreaName","岳飞庙（庙）墓区");

        /**逻辑区域表三开始*/
        List<PhysicalEntry> entryList = new ArrayList<>();          //卡口
        PhysicalEntry physicalEntry1 = new PhysicalEntry();
        physicalEntry1.setName("后门");
        physicalEntry1.setEntryCode("ENTRY_1");
        PhysicalEntry physicalEntry2 = new PhysicalEntry();
        physicalEntry2.setName("入口");
        physicalEntry2.setEntryCode("ENTRY_2");
        entryList.add(physicalEntry1);
        entryList.add(physicalEntry2);
        logicarea_map.put("tb3_entryList",entryList);
        Map<String,Object> tb3_data_map = new HashMap<>();
        for(int i=0;i<tb2_timeStrList.size();i++){
            String timeStr = tb2_timeStrList.get(i);
            for(int k=0;k<tb2_dateStrList.size();k++){
                String dateStr = tb2_dateStrList.get(k);
                for(int j=0;j<entryList.size();j++){
                    PhysicalEntry physicalEntry = entryList.get(j);
                    String entryCode = physicalEntry.getEntryCode();
                    tb3_data_map.put(timeStr+"&"+dateStr+"&"+entryCode+"&IN",random.nextInt(700));
                    tb3_data_map.put(timeStr+"&"+dateStr+"&"+entryCode+"&OUT",random.nextInt(700));
                }
            }
        }
        logicarea_map.put("tb3_data_map",tb3_data_map);
        /**逻辑区域表三结束*/

        /**逻辑区域表四开始*/

        /**逻辑区域表四结束*/

        part2_detail_logicarea_list.add(logicarea_map);
//        part2_detail_logicarea_list.add(logicarea_map2);
        part2_detail_map.put("logicAreaDetails",part2_detail_logicarea_list);

        part2_detail_list.add(part2_detail_map);

        dataMap.put("part2_detail_list",part2_detail_list);                             //区域详情部分数据
        /**excel数据内容填充*/

        freeMarkerTest.createDoc(dataMap);
    }






    public class PhysicalEntry{
        private String name;
        private String entryCode;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEntryCode() {
            return entryCode;
        }

        public void setEntryCode(String entryCode) {
            this.entryCode = entryCode;
        }
    }
}
