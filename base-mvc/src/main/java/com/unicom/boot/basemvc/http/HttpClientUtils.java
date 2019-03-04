//package com.unicom.boot.basemvc.http;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.config.Registry;
//import org.apache.http.config.RegistryBuilder;
//import org.apache.http.conn.socket.ConnectionSocketFactory;
//import org.apache.http.conn.socket.PlainConnectionSocketFactory;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.apache.http.util.EntityUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//import java.io.IOException;
//import java.security.cert.CertificateException;
//
//
///**
// * https://github.com/Arronlong/httpclientUtil
// *
// * httpClient：需要引入httpclient，httpmime的jar，提供了连接池，更友好的API;
// * 使用流程：
// *      0. 创建HttpClient，通过HttpClients，custom()可以设置一些参数，例如绕过https证书，自定义证书，连接池等
// *      1. 根据url和method创建HttpGet,HttpPost，通过RequestConfig设置参数，如：连接和读取超时时间，从连接池获取超时
// *      2. 通过httpGet，httpPost设置请求头，请求体，get请求参数拼接在url后面，请求头通过setHeader()，请求体通过setEntity()
// *              application/json 使用StringEntity，application/x-www-form-urlencoded使用UrlEncodedFormEntity,
// *              multipart/form-data通常使用MultipartEntityBuilder
// *      4. 通过httpclient.execute(httpGet/httpPost)得到httpResponse，获取响应头，响应体
// *              并用EntityUtils解析响应体， EntityUtils.consume(entity) ？？？
// *      5. 关闭连接httpClient,httpResponse
// *
// * https请求，异步，线程池，携带cookie ??
// *
// * @author dengh
// */
//public class HttpClientUtils {
//
//    private final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
//
//    public static void main(String[] args) throws IOException {
//        httpDemo();
//    }
//
//
//    public static void httpDemo(){
//
//        HttpClient httpClient = HttpClients.createDefault();
//
//        HttpPost httpPost = new HttpPost("host:port");
//        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000)
//                .setConnectionRequestTimeout(10000).setSocketTimeout(10000).build();
//        httpPost.setConfig(requestConfig);
//
//        httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
//        StringEntity stringEntity = new StringEntity("httpClient请求体的内容", "utf-8");
//        httpPost.setEntity(stringEntity);
//
//        /*
//        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//        List<NameValuePair> nameValuePairList = new ArrayList<>();
//        nameValuePairList.add(new BasicNameValuePair("name", "value"));
//        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList);
//        httpPost.setEntity(urlEncodedFormEntity);
//
//        httpPost.setHeader("Conent-Type", "multipart/form-data");
//        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
//        multipartEntityBuilder.addTextBody("key1", "val1");
//        multipartEntityBuilder.addBinaryBody("fileKey", new File("path"));
//        httpPost.setEntity(multipartEntityBuilder.build());
//        */
//        try {
//            HttpResponse httpResponse = httpClient.execute(httpPost);
//            //    httpResponse.getAllHeaders();
//            //    httpResponse.getStatusLine();
//            HttpEntity entity = httpResponse.getEntity();
//            String result = EntityUtils.toString(entity, "utf-8");
//            EntityUtils.consume(entity);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * https 权威认证的证书可以直接使用以上的demo，否则需要：1.绕过证书验证；2.自签名的证书（自己知道秘钥库）
//     * 1. 采用绕过验证的方式处理https请求
//     * 2. 自签名的证书：知道秘钥库
//     * @return
//     */
//    public void httpsDemo() {
//        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
//        X509TrustManager trustManager = new X509TrustManager() {
//            @Override
//            public void checkClientTrusted(
//                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
//                    String paramString) throws CertificateException {
//            }
//
//            @Override
//            public void checkServerTrusted(
//                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
//                    String paramString) throws CertificateException {
//            }
//
//            @Override
//            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                return null;
//            }
//        };
//
//        SSLContext sslcontext = null;
//        try {
//            sslcontext = SSLContext.getInstance("SSLv3");
//            sslcontext.init(null, new TrustManager[]{trustManager}, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // 设置协议http和https对应的处理socket链接工厂的对象
//        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
//                .register("http", PlainConnectionSocketFactory.INSTANCE)
//                .register("https", new SSLConnectionSocketFactory(sslcontext))
//                .build();
//        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//
//        //创建自定义的httpclient对象
//        HttpClient client = HttpClients.custom().setConnectionManager(connManager).build();
//        /* 之后参考 httpDemo */
//    }
//}
//
