package com.example.garorasu.onceagain;

/**
 * Created by Rohit Nagpal on 15/5/16.
 * Email: rohitrfactor@gmail.com
 */
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
public class API {
    private static Reader reader=null;
    public static Reader getData(String SERVER_URL) {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpPost = new HttpGet(SERVER_URL);
            HttpResponse response = httpClient.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            System.out.println("Status : rohit "+statusLine.getStatusCode());
            if (statusLine.getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                System.out.println("Content"+content);
                reader = new InputStreamReader(content);
            } else {
//				Log.e("error:", "Server responded with status code: "+ statusLine.getStatusCode());


            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reader;
    }
}
