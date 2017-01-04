package com.panhainan.http;

import com.alibaba.fastjson.JSONObject;
import sun.org.mozilla.javascript.internal.json.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by FirePan on 2017/1/4.
 */
public class HttpTest {
    public static void main(String[] args) throws Exception {
        String url = "http://132.126.2.241:8099/jolokia/read/*:type=Flow,name=\"httpTest\"";
        //"http://132.126.2.241:8099/jolokia/read/*:type=Flow,name=\"httpTest\"";
        //"http://132.126.2.241:8083/jolokia/read/*:type=Flow,name=\"httpTest\"";
        // "http://132.126.2.241:8272/jolokia/read/*:type=Flow,name=\"aop\"";
        URL u = new URL(url);
        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        System.out.println(con);
        con.setRequestMethod("GET");
        int responseCode = 0;
        try {
            responseCode = con.getResponseCode();
        } catch (IOException e) {
            System.out.println("拒绝连接:"+e.getMessage());
        }
        if(responseCode==0){
            System.out.println("接口无效");
        }else{
            System.out.println(responseCode);
            String responseBody = readResponseBody(con.getInputStream());
            System.out.println(responseBody);
        }
    }

    // 读取输入流中的数据
    private static String readResponseBody(InputStream inputStream) throws IOException {

        BufferedReader in = new BufferedReader(
                new InputStreamReader(inputStream));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}
