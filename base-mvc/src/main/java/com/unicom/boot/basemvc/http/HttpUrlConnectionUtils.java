package com.unicom.boot.basemvc.http;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

/**
 * Http客户端：HttpUrlConnection的使用；
 * Http服务端：网络服务器apache，tomcat，servlet技术
 *
 * @author dengh
 */
public class HttpUrlConnectionUtils {

    public static final Logger logger = LoggerFactory.getLogger(HttpUrlConnectionUtils.class);

    public static void doPost(String url, String contentType){

        try {
            URL httpUrl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
            httpURLConnection.setRequestMethod("POST");
            /* 设置连接超时和请求超时时间，单位毫秒*/
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(10000);
            /* 允许设置请求体，获取响应体 */
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            httpURLConnection.setRequestProperty("Content-Type", contentType);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            String formStr = "sex=" + URLEncoder.encode("男", "utf-8") + "&age=29";
            outputStream.write(formStr.getBytes("utf-8"));
            outputStream.flush();
            outputStream.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            logger.info("完成一次http通信，收到响应头：{}，收到响应体为：{}",
                    JSONObject.toJSONString(httpURLConnection.getHeaderFields()), reader.readLine());
            reader.close();

            httpURLConnection.disconnect();

        } catch (ProtocolException e) {
            logger.info("协议错误：{}", e.getMessage());
            e.printStackTrace();
        } catch (MalformedURLException e) {
            logger.info("url错误：{}", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.info("io错误：{}", e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String url = null;
        try {
            url = "http://localhost:8090/baseMvc/testParam2?name="+ URLEncoder.encode("邓浩", "utf-8");
            url = "http://localhost:8090/baseMvc/testPost4";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //doPost(url, "application/x-www-form-urlencoded");
        doPost(url, "application/json");
        /*try {
            String hello = URLEncoder.encode("中文", "utf-8");
            System.out.println(hello);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
    }

}
/**
 *
 *  IP 地址的操作类 InetAddress：ip地址和域名之间的转换（域名是因为ip地址不友好，借由DNS转换）
 *
 *  URL 操作类 URL：http://www.runoob.com/index.html?language=cn#j2se
 *                协议://主机:端口/文件路径?请求参数#定位位置
 *
 *  连接超时时间，读取超时时间是socket（tcp）通信参数，因为http是建立在tcp之上，所以需要设置
 *      conn.setReadTimeout(10000);conn.setConnectTimeout(10000)
 *
 *  如果不显示调用httpURLConnection.connect()那么http连接会在
 *      conn.getInputStream(),conn.getOutputStream()才建立，通常也不需要显示调用conn.connect()
 *
 *  java.lang.IllegalStateException: Already connected：是因为conn.setDoOutput(true);conn.setDoInput(true)等设置
 *      放在了conn.connect()之前，或者get请求开启了conn.setDoOutput(true)
 *
 *  conn.getOutputStream()：设置请求体，请求头通过conn.setRequestProperty()设置
 *
 *  conn.getInputStream()：读取响应体，响应头通过conn.getHeaderFields()读取
 *
 *  每次http请求，只有在conn.getInputStream()或者conn.getHeaderFields()等才会真正的向服务端发送请求
 *
 *  Keep-alive：
 *      默认http1.1就是keep-alive连接，nginx，apache都会有一个keep-alive的timeout参数，
 *      建立的tcp连接在响应后不会立即关闭，而是等待keep-alive时间，并且期间内每有一个请求，都会重新等待keep-alive时间
 *      https://www.cnblogs.com/freefish12/p/5394876.html
 *
 *  content-type为
 *      application/x-www-form-urlencoded，可以使用工具类URLEncoder.encode()对value进行编码
 *      application/json，可以使用JSONObject类似的工具类
 *      multipart/form-data，？？待完善
 *
 */
