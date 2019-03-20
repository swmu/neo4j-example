package com.swmu.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @author seven.mu
 * @version 1.0
 * @description 文件上传工具类服务方法
 * @date Create in 2018/12/17 12:22
 */
public class FileUtils {
    /**
     * 文件上传工具类服务方法
     *
     * @param  file, filePath, fileName]
     * @return java.lang.String
     * @author seven.mu
     * @date Create in 2018/12/18 13:07
     */
    public static String uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        String filePathName = filePath + fileName;
        FileOutputStream out = new FileOutputStream(filePathName);
        out.write(file);
        out.flush();
        out.close();
        return filePathName;
    }


    /**
     * 通过url获取图片数据
     * @param strUrl
     * @return
     */
    public static byte[] getImageFromNetByUrl(String strUrl){
        URL url = null;
        try {
            url = new URL(strUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5*1000);
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] bytes = readInputStream(inputStream);
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字节数据转换位base664编码
     * @param data
     * @return
     */
    public static String base64Image(byte[] data){
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    /**
     * 将读取的图片数据写入输出流
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    /**
     * 按行读取文件数据为字符串且通过逗号分隔
     * @return
     */
    public static String readFileLineToString(String filePath){
        Set<String> set = new HashSet<>();
        try {
            File file = ResourceUtils.getFile("classpath:"+filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String s = null;
            while ( (s = br.readLine()) != null){
                set.add(s);
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        String join = StringUtils.join(set, ",");
        join = ","+join+",";
        return join;
    }

    public static void main(String[] args) throws  Exception{
//        byte[] imageFromNetByUrl = getImageFromNetByUrl("http://172.16.205.69:8090/letter/51d0b526d22b42778321c994a622b975-0.jpg");
//        String s = base64Image(imageFromNetByUrl);
//        System.out.println("base64");
//        System.out.println(s);
//        String jpg = "http://172.16.205.69:8090/letter/51d0b526d22b42778321c994a622b975-0.jpg";
//        String[] split = jpg.split("/");
//        System.out.println(split[split.length-1]);
//
//        String filePath = "jiebaWord/newsStop.txt";
//        String strings = FileUtils.readFileLineToString(filePath);

//        String absolutePath = new File(getClass().getClassLoader().getResource("dicts/jieba.dict").getPath()).getAbsolutePath();
        // 词典路径为Resource/dicts/jieba.dict
//        File file = ResourceUtils.getFile("classpath:jiebaWord/newsStop.txt");
//        String path1 = file.getPath();
//        Path path = Paths.get(path1) ;
//        //加载自定义的词典进词库
//        WordDictionary.getInstance().loadUserDict(path) ;

    }
}
