package elasticsearch;

import com.sun.scenario.Settings;
import com.sun.xml.internal.bind.v2.TODO;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

/**
 * Created by benwq on 2017/5/10.
 */
public class ApiTest {

    public static void main(String[] args) throws Exception{
        sys();
    }

    private static void sys() throws IOException {
//         TODO Auto-generated method stub
//        String idxName = "test";
//        String idxType = "attachments";
//        Settings settings =ImmutableSettings.settingsBuilder().put("cluster.name","az_bsms_elasticsearch").build();
//        Client client=new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300));
//        String data64=org.elasticsearch.common.Base64.encodeFromFile(filepath);
//        XContentBuilder source = jsonBuilder().startObject()
//                .field("file", data64)
//                .field("text", data64)
//                .endObject();
//
//        String id = "file"+11;
//        IndexResponse idxResp = client.prepareIndex().setIndex(idxName).setType(idxType).setId(id)
//                .setSource(source).setRefresh(true).execute().actionGet();
//        System.out.println(idxResp);
//        client.close();
    }
}
