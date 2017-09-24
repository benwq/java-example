import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.stream.FileImageOutputStream;
import java.io.*;
import java.net.*;
import java.util.*;

public class MrywSpider {
    private transient static final Logger logger = LoggerFactory.getLogger(MrywSpider.class);

    private static HttpURLConnection con;
    private static InputStream inputStream;
    private static List<String> imageurls = new ArrayList<String>();

    private static String tempFilePath = "F:\\dataFile\\elasticSearch\\";

    final static String proxyip="127.0.0.1";
    final static int proxyport = 53099;

    final static String urladdress = "https://www.meiriyiwen.com/random";

    public static void main(String[] args) throws IOException {

    }

    public Map<String,List<String>> getEssay(){
        try {
            createtemporaryfile();
            File input = new File(tempFilePath + "linwen.html");
            Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
            deleteTempFile();
            Map<String,List<String>> map = new HashMap<String,List<String>>();

            Elements title = findFromDocument(doc,"#article_show h1");
            Elements author = findFromDocument(doc,"#article_show .article_author span");
            Elements contents = findFromDocument(doc,"#article_show .article_text p");
            List<String> titleStr = elements2list(title);
            List<String> authorStr = elements2list(author);
            List<String> psStr = elements2list(contents);

            map.put("title",titleStr);
            map.put("author",authorStr);
            map.put("content",psStr);
            return map;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    //返回的是刷新后不同的url,非刷新后的url,该方法自行修改(按更新的需求)
    public static void refreshimageurl(List<String> oldimgurls,String path) throws IOException{
        File input = new File(path + "linwen.html");
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
    }

    /**
     * 创建临时文件
     * @throws IOException
     */
    public static void createtemporaryfile() throws IOException{
//        con = getConn(urladdress);
        con = getConnWithProxy(proxyip,proxyport,urladdress);
        inputStream = con.getInputStream();
        inputtohtml(inputStream, tempFilePath+"linwen.html","utf-8");
    }

    /**
     * 删除临时文件
     * @throws IOException
     */
    public static void deleteTempFile() throws IOException{
        File input = new File(tempFilePath + "linwen.html");
        if(input.exists()) {
            input.delete();
        }
    }

    /**
     * 使用jquery表达式从文档中查找元素
     * @param doc
     * @param jQ_select
     */
    private Elements findFromDocument(Document doc,String jQ_select){
        Elements links = doc.select(jQ_select);
        return links;
    }

    private static List<String> elements2list(Elements es){
        List<String> list = new ArrayList<String>();
        for(int i=0;i<es.size();i++){
            list.add(es.get(i).html());
        }
        return list;
    }

    /**
     * 将流内容写入文件中
     * @param inputStream
     * @param path
     * @throws IOException
     */
    private static void inputtohtml(InputStream inputStream,String path,String charset) throws IOException {
        if(StringUtil.isBlank(charset)){
            charset = "utf-8";
        }
        byte[] buffer = new byte[1024];
        int len;
        PrintStream out = new PrintStream(path,charset);
        while((len = inputStream.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.close();
    }

    /**
     * 获得URL连接
     * @param urladdress
     * @return
     * @throws IOException
     */
    public static HttpURLConnection getConn(String urladdress) throws IOException{
        URL url = new URL(urladdress);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        return conn;
    }

    /**
     * 通过外网代理获得连接
     * @param proxyip
     * @param port
     * @param urladdress
     * @return
     * @throws IOException
     */
    public static HttpURLConnection getConnWithProxy(String proxyip,int port,String urladdress) throws IOException{
        InetAddress host = InetAddress.getByName(proxyip);
        Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(host,port));
        URL url = new URL(urladdress);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection(proxy);
        return conn;
    }

    /**
     * 将byte转为image
     * @param data
     * @param path
     */
    public static void byte2image(byte[] data,String path){
        if(data.length<3||path.equals("")) return;
        try{
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch(Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }

    public static HttpURLConnection getConn() {
        return con;
    }


    @Test
    public void test_createtemporaryfile(){
        try {
            createtemporaryfile();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }

    @Test
    public void test_deleteTempFile(){
        try {
            deleteTempFile();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }

    @Test
    public void test_groupThem(){
        try {
            File meiriyiwen = new File(tempFilePath+"meiriyiwen.json");
            Date date = new Date();
            OutputStream fos = new FileOutputStream(meiriyiwen);
            Set<String> existEssayTitles = new HashSet<String>();
            for(int i=1;i<=10000;i++) {
                Map<String, List<String>> map = getEssay();
                String title = map.get("title").get(0);
                if(existEssayTitles.contains(title)){
                    i--;
                    continue;
                }
                existEssayTitles.add(title);
                String author = map.get("author").get(0);
                List<String> contents = map.get("content");
                StringBuffer contentsb = new StringBuffer();
                for (String content : contents) {
                    contentsb.append(content);
                }
                StringBuffer sb = new StringBuffer();
                sb.append("{\"index\":{\"_index\":\"meltedmedia\",\"_type\":\"article\",\"_id\":\""+i+"\"}}\n");
                sb.append("{\"title\":\"" + title + "\"," + "\"keywords\":\"" +title + "\"," +"\"updateTime\":" +date.getTime() + ","+ "\"author\":\"" + author + "\",\"contents\":\""+ contentsb.toString() + "\"}\n");
//                System.out.println(sb.toString());
                byte b2[] = sb.toString().getBytes("utf-8");
                fos.write(b2);
                fos.flush();
                System.out.println(i);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }
}
