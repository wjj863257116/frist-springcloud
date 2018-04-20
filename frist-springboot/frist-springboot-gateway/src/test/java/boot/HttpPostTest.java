package boot;



import boot.uitl.AsyncHttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Request;
import com.ning.http.client.Response;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by xuchun on 2017/7/11.
 */
public class HttpPostTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        String url="http://localhost:38080/springcloud/apitest/test?agencyId=2022900005";
        String s="{\n" +
                "  \"wbMerchantId\": \"202100000020002\",\n" +
                "  \"partnerMchId\": \"20170512112607mer124\",\n" +
                "  \"agencyId\": \"2022900005\",\n" +
                "\"queryType\": \"01\",\n" +
                "\"desc\":\"中文\"\n" +
                "}";

       /*
       AsyncHttpClientUtil.post(url,s)的内部逻辑

       AsyncHttpClient  http=new AsyncHttpClient();

        AsyncHttpClient.BoundRequestBuilder builder=http.preparePost(url);

        builder.setBodyEncoding("UTF-8");
        builder.addHeader("Content-type", "application/json;charset=UTF-8");
        builder.setBody(s);

       Future<Response> f= builder.execute();
       String res=f.get().getResponseBody("UTF-8");
*/

       String res= AsyncHttpClientUtil.post(url,s);



        System.out.println("调用结束返回数据："+res);
    }
}
