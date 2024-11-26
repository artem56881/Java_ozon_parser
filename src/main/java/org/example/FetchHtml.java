package org.example;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;


public class FetchHtml {
    public static String ExtarctHtml(String url) {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
//            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36");

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                return EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            System.err.println("Error fetching the HTML content: " + e.getMessage());
            e.printStackTrace();
            return "";
        }
    }
    public static void main(String[] args){
        String html = FetchHtml.ExtarctHtml("https://www.ozon.ru/product/mysh-oklik-385m-chernyy-krasnyy-opticheskaya-1000dpi-usb-dlya-noutbuka-3but-210972580/?avtc=1&avte=4&avts=1732557196");
        System.out.println(ProductInfoCollector.collectProductInfo(html));
    }

}
