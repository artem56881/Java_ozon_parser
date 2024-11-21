package org.example;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import static org.example.ProductInfoCollector.collectProductInfo;


public class FetchHtml {
    public static void main(String[] args) {
        String url = "https://www.ozon.ru/product/umnaya-kolonka-sberboom-home-fistashkovyy-s-iskusstvennym-intellektom-gigachat-umnyy-1637384035/?from_sku=1637384220&oos_search=false";

        // Create an HttpClient instance
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Create a GET request
            HttpGet httpGet = new HttpGet(url);
//            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36");

            // Execute the request
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                // Extract response content
                String htmlContent = EntityUtils.toString(response.getEntity());
//                System.out.println(htmlContent);
                String ozonCardPrice = collectProductInfo(htmlContent).get("product_ozon_card_price");
                System.out.print("Цена с озон картой: ");

                System.out.println(ozonCardPrice);
            }
        } catch (Exception e) {
            System.err.println("Error fetching the HTML content: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
