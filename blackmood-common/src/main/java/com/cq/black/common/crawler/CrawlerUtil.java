package com.cq.black.common.crawler;

import java.io.*;
import java.net.*;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 爬虫工具类
 */
@Service("CrawlerUtil")
public class CrawlerUtil {
    Logger log = LoggerFactory.getLogger(CrawlerUtil.class);

    /**
     * 获取网页数据
     *
     * @param urlStr  访问地址
     * @param params  参数
     * @param charset 字符编码
     * @return
     * @throws Exception
     */
    public static String httpGet(String urlStr, Map<String, String> params, String charset) throws Exception {
        StringBuilder sb = new StringBuilder();
        if (null != params && params.size() > 0) {
            sb.append("?");
            Map.Entry<String, String> en;
            for (Iterator<Map.Entry<String, String>> ir = params.entrySet().iterator(); ir.hasNext(); ) {
                en = ir.next();
                sb.append(en.getKey() + "=" + URLEncoder.encode(en.getValue(), "utf-8") + (ir.hasNext() ? "&amp;" : ""));
            }
        }
        URL url = new URL(urlStr + sb);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() != 200) {
            throw new Exception("请求异常状态值:" + conn.getResponseCode());
        }
        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
        Reader reader = new InputStreamReader(bis, charset);
        char[] buffer = new char[2048];
        int len = 0;
        CharArrayWriter caw = new CharArrayWriter();
        while ((len = reader.read(buffer)) > -1)
            caw.write(buffer, 0, len);
        reader.close();
        bis.close();
        conn.disconnect();
        return caw.toString();
    }

    /**
     * 获取网页数据
     *
     * @param urlStr 访问地址
     * @param params 参数
     * @return
     * @throws Exception
     */
    public static String httpGet(String urlStr, Map<String, String> params) throws Exception {
        StringBuilder sb = new StringBuilder();
        if (null != params && params.size() > 0) {
            sb.append("?");
            Map.Entry<String, String> en;
            for (Iterator<Map.Entry<String, String>> ir = params.entrySet().iterator(); ir.hasNext(); ) {
                en = ir.next();
                sb.append(en.getKey() + "=" + URLEncoder.encode(en.getValue(), "utf-8") + (ir.hasNext() ? "&amp;" : ""));
            }
        }
        URL url = new URL(urlStr + sb);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() != 200)
            throw new Exception("请求异常状态值:" + conn.getResponseCode());
        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
        Reader reader = new InputStreamReader(bis, "gbk");
        char[] buffer = new char[2048];
        int len = 0;
        CharArrayWriter caw = new CharArrayWriter();
        while ((len = reader.read(buffer)) > -1)
            caw.write(buffer, 0, len);
        reader.close();
        bis.close();
        conn.disconnect();
        //System.out.println(caw);
        return caw.toString();
    }


    /**
     * 从获得的网页的document中获取指定条件的内容
     *
     * @param document
     * @param condition 条件
     * @return
     */
    public static String catchInfomationFromDocument(Document document, String condition, int position) {

        if (document != null) {
            Iterator<Element> iterator = document.select(condition).iterator();
            if (iterator.hasNext()) {
                String str = iterator.next().text();
                return str.substring(position).trim();
            }
        }
        return null;
    }

    /**
     * 判断从获得的网页的document中<br/>
     * 获取指定条件的内容是否存在
     *
     * @param document
     * @param condition 条件
     * @return
     */
    public static boolean isExistInfomation(Document document, String condition) {

        if (document != null) {
            Iterator<Element> iterator = document.select(condition).iterator();
            if (iterator.hasNext()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 使用 Jsoup 爬取网页数据
     * @return
     */
    public List<?> jsoupHtml() {
        List<?> list = new LinkedList<>();
        try {
            Document doc = Jsoup.connect("http://www.weather.com.cn/weather/101210101.shtml").get();
            Elements ul = doc.select(".t");
            Elements li = ul.select("li");
//            Weather weather = null;
//            for (Element lii : li) {
//                weather = new Weather();
//                weather.setDay(lii.selectFirst("h1").text());
//                weather.setInfo(lii.selectFirst("p").text());
//                weather.setMaxTemperature(lii.selectFirst("span").text());
//                weather.setMinTemperature(lii.selectFirst("i").text());
//                list.add(weather);
//            }
        } catch (Exception e) {
            log.error("获取天气预报异常");
        }
        if (list.size() > 0) {
            return list;
        }
        return null;
    }
}
