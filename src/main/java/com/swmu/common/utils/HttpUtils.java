package com.swmu.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author YuanJingshan
 * @version 1.0
 * @description httpClient公共类，用于通过http请求调用外部接口
 * @date Create in 2018/12/17 12:22
 */
@Slf4j
public class HttpUtils {

    public static final String URL_ENCODE = "UTF-8";
    public static final String CONTENT_TYPE = "application/json";
    public static final String FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static final String CHAR_SET = "UTF-8";
    public static final int RETURN_CODE_SUCCESS = 0;
    public static final int RETURN_CODE_FAIL = 1;
    /**
     * 每个路由允许活跃连接数
     */
    private static final int MAX_PRE_ROUTE = 200;
    /**
     * 所有路由总连接数上限
     */
    private static final int MAX_TOTAL = 2000;
    /**
     * 向连接池申请连接超时
     */
    private static final int CONNECT_REQUEST_TIMEOUT = 10 * 1000;
    /**
     * 建立连接超时
     */
    private static final int CONNECT_TIMEOUT = 5 * 1000;
    /**
     * 等待数据超时
     */
    private static final int SOCKET_TIMEOUT = 5 * 1000;
    /**
     * 重试次数
     */
    private static final int RETRY_NUMBER = 3;
    public static HttpClient httpClient;
    private static ScheduledExecutorService executor;
    private static RequestConfig requestConfig;

    static {
        LayeredConnectionSocketFactory socketFactory = null;
        try {
            socketFactory = new SSLConnectionSocketFactory(SSLContext.getDefault());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("create SSLConnectionSocketFactory fail");
        }
        //访问协议
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", socketFactory)
                .register("http", new PlainConnectionSocketFactory())
                .build();
        //多线程连接管理器
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connectionManager.setMaxTotal(MAX_TOTAL);
        connectionManager.setDefaultMaxPerRoute(MAX_PRE_ROUTE);
        //请求设置
        requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();
        //创建httpclient
        httpClient = HttpClients.custom().setConnectionManager(connectionManager)
                .setRetryHandler((exception, executionCount, context) -> {
                    if (executionCount > RETRY_NUMBER) {
                        log.warn("Maximum tries reached for client http pool ");
                        return false;
                    }
                    //NoHttpResponseException 重试 || 连接超时重试
                    if (exception instanceof NoHttpResponseException || exception instanceof ConnectTimeoutException) {
                        log.warn("NoHttpResponseException on " + executionCount + " call");
                        return true;
                    }
                    return false;
                }).build();
        //每隔1分钟执行连接池清理
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            // 关闭连接失效的链接
            connectionManager.closeExpiredConnections();
            // 关闭30s空闲的链接
            connectionManager.closeIdleConnections(5, TimeUnit.SECONDS);
        }, 5, 5, TimeUnit.SECONDS);
    }

    /**
     * get方式调用接口
     *
     * @param url 接口地址
     * @return String
     */
    public static String get(String url) throws Exception {
        String result = null;
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                if (response.getEntity() != null) {
                    result = EntityUtils.toString(response.getEntity());
                }
            } else {
                httpGet.abort();
                log.info("HttpUtils get statusCode: {} url: {}", response.getStatusLine().getStatusCode(), url);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("HttpUtils request fail! url: {}", url);
            throw e;
        } finally {
            if (response != null) {
                EntityUtils.consume(response.getEntity());
            }
        }
        return result;
    }

    /**
     * post方式调用接口
     *
     * @param url     接口地址
     * @param jsonStr json串
     * @return String
     */
    public static String post(String url, String jsonStr) throws Exception {
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        HttpResponse response = null;
        try {
            ArrayList<NameValuePair> params = new ArrayList<>();
            httpPost.setEntity(new UrlEncodedFormEntity(params, URL_ENCODE));
            httpPost.addHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE);

            // 将JSON进行UTF-8编码,以便传输中文
            StringEntity se = new StringEntity(jsonStr, CHAR_SET);
            se.setContentType(CONTENT_TYPE);
            se.setContentEncoding(CHAR_SET);
            httpPost.setEntity(se);

            response = httpClient.execute(httpPost);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                if (response.getEntity() != null) {
                    result = EntityUtils.toString(response.getEntity());
                }
            } else {
                httpPost.abort();
                log.info("HttpUtils post statusCode: {} url: {}", response.getStatusLine().getStatusCode(), url);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("HttpUtils request fail! url: {}, params: {}", url, jsonStr, e);
            throw e;
        } finally {
            if (response != null) {
                EntityUtils.consume(response.getEntity());
            }
        }
        return result;
    }

    /**
     * post方式调用接口
     * 参数为表单格式
     *
     * @param url        接口地址
     * @param jsonObject map对象
     * @return String
     */
    public static String postForm(String url, JSONObject jsonObject) throws Exception {
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        try {
            ArrayList<NameValuePair> params = new ArrayList<>();
            for (Map.Entry map : jsonObject.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(map.getKey().toString(), map.getValue().toString());
                params.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(params, URL_ENCODE));
            httpPost.addHeader(HTTP.CONTENT_TYPE, FORM_CONTENT_TYPE);

            HttpResponse response = httpClient.execute(httpPost);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                if (response.getEntity() != null) {
                    result = EntityUtils.toString(response.getEntity());
                }
            } else {
                httpPost.abort();
                log.info("HttpUtils post statusCode: {} url: {}", response.getStatusLine().getStatusCode(), url);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("HttpUtils request fail! url: {}, params: {}", url, null, e);
            throw e;
        }
        return result;
    }

    public static void main(String[] args) {
        JSONObject jsonMap = new JSONObject();

        //电子邮件
        jsonMap.put("dzyj", "");
        // 固定电话
        jsonMap.put("gddh", "");
        // 公开标志 1： 公开，  0 ：不公开
        jsonMap.put("gkbz", "1");
        // 获取地址：http://218.2.26.138:18080/gzszfjk_webservice/Service.asmx/sjld_nrfl?parentid=0
        // 内容分类代码 该代码表示其它
        jsonMap.put("nrfldm", "990000");
        // 内容分类名称
        jsonMap.put("nrflmc", "其它");
        // 信访目的代码
        jsonMap.put("xfmddm", "99");
        // 信访目的名称
        jsonMap.put("xfmdmc", "其他");
        // 手机号
        jsonMap.put("sjh", "13511069426");

        // 投诉内容
        jsonMap.put("tsnr", "测试测试");
        //投诉主题
        jsonMap.put("tszt", "测试");
        // 问题属地代码
        jsonMap.put("wtsddm", "无");
        // 问题属地名称
        jsonMap.put("wtsdmc", "贵州贵阳");

        // 姓名
        jsonMap.put("xm", "穆仕伟");
        // 详细住址
        jsonMap.put("xxzz", "贵州贵阳");
        // 证件号码
        jsonMap.put("zjhm", "522132199110204911");
        //证件类型
        jsonMap.put("zjlx", "111");
        // 证件类型名称
        jsonMap.put("zjlxmc", "");
        // 住址
        jsonMap.put("zz", "");
        // 住址代码
        jsonMap.put("zzdm", "无");

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>(16);
        map.put("fileName", "");
        map.put("fileContent", "");
        list.add(map);
        jsonMap.put("file", list);

        JSONObject params = new JSONObject();

        params.put("Jsonstring", jsonMap.toJSONString());


        String url = "http://218.2.26.138:18080/gzszfjk_webservice/Service.asmx/apptijiaoldxx";
        String param = params.toString();

        try {
            String res = HttpUtils.postForm(url, params);
            Map<String, Object> responseMap = JSONObject.parseObject(res);
            System.out.print(responseMap.toString());
        } catch (Exception e) {
            log.error("异常", e);
        }
    }
}
