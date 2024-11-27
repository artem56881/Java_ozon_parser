package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.Map;

public class ProductInfoCollector {

    public static Map<String, String> collectProductInfo(String htmlContent) {
        Map<String, String> productInfo = new HashMap<>();

        try {
            // Parse the HTML content with Jsoup
            Document document = Jsoup.parse(htmlContent);

            Element oCardPriceElement = document.selectFirst("span.m7s_27.sm5_27");
            Element scriptElement = document.selectFirst("script[type=\"application/ld+json\"]");
//            System.out.println(scriptElement.html());
//            System.out.println(oCardPriceElement.html());

            assert scriptElement != null;
            String ldJson = scriptElement.html();

            String oCardPrice = null;

            if (oCardPriceElement != null) {
                oCardPrice = oCardPriceElement.html();
            }
            String basePrice = ldJson.substring(ldJson.indexOf("\"price\":\"")+9, ldJson.indexOf("\",\"priceC"));
            String itemId = ldJson.substring(ldJson.indexOf("\"sku\":\"")+7, ldJson.lastIndexOf("\"}"));
            String itemName = ldJson.substring(ldJson.indexOf("\"name\":\"")+8, ldJson.lastIndexOf("\",\"offe"));
            String itemRating = ldJson.substring(ldJson.indexOf("\"ratingValue\":\"")+15, ldJson.lastIndexOf("\",\"reviewCou"));

            productInfo.put("base_price", basePrice);
            productInfo.put("item_id", itemId);
            productInfo.put("item_name", itemName);
            productInfo.put("item_rating", itemRating);
            productInfo.put("ozon_card_price", oCardPrice);

        } catch (Exception e) {
            System.err.println("Error parsing HTML content: " + e.getMessage());
            e.printStackTrace();
        }

        return productInfo;
    }
}
