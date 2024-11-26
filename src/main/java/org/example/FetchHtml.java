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
        String html = FetchHtml.ExtarctHtml("https://www.ozon.ru/product/stilus-dlya-telefona-planshetov-smartfona-ipad-dlya-ios-android-windows-universalnyy-chernyy-167276895/?advert=APwAd22HHm5pfsVkqe9Z0pQck33alpCPWE6sV216x0oIvlD5TNHCY4iI6BVB00tY8HKiLGpVHo00XK1P4qDWbcAfr0yn72MjLxmCDb9QByVjXoNCgRODV_u69cgbF9Ch4yy0RyP_hC2LDplpGdX-iA6-0KGtIMJjzeGxIeOShn94pJyGBMWVQ-VOKUhU5m-q5DRYvZYVGM8jnqmHrNN1BCYN6KOs1lMMoBqE7e_qqtiFQe2IiFQ&avtc=1&avte=4&avts=1732659833");
        System.out.println(ProductInfoCollector.collectProductInfo(html));
    }

}
