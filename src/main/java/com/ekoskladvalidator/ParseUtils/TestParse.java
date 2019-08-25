package com.ekoskladvalidator.ParseUtils;


import org.apache.catalina.connector.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Component
public class TestParse {

    private static final Logger log = LoggerFactory.getLogger(TestParse.class);

    public TestParse() {
    }

    private HashMap<String, String> urlTitleMap = new HashMap<>();

    public String getOneEllem(String xpathStr) throws IOException {

        Document doc = Jsoup.connect("https://aquapolis.ua/slinoj-klapan-dlja-parogeneratora-5-var.html").get();
        Elements ells = doc.select(xpathStr);
        Element ell = ells.first();
        String ellTxt = ell.text();
        Double inter = Double.parseDouble(ellTxt.replaceAll(" " , "").replaceAll("грн", "").replaceAll(",","."));


        return ellTxt;
    }


    public void parseTestEx(String urlToBuildSiteMapIn) {

        try {
            urlTitleMap.clear();

            String urlToBuildSiteMap = urlToBuildSiteMapIn;
            Document doc = Jsoup.connect(urlToBuildSiteMap).get();

            //  Page Title
            String title = doc.title();
            //System.out.println("title: " + title);

            //  Links in page
            Elements links = doc.select("a[href*=aquapolis.ua]");
            List<String> url_array = new ArrayList<String>();
            int i = 0;
            url_array.add(urlToBuildSiteMap);
            String root = urlToBuildSiteMap;
            urlTitleMap.put(urlToBuildSiteMap, title);
            Iterator<String> keySetIterator = urlTitleMap.keySet().iterator();
            while ((i <= urlTitleMap.size())) {
                try {
                    urlToBuildSiteMap = url_array.get(i).toString();
                    doc = Jsoup.connect(urlToBuildSiteMap).get();
                    title = doc.title();
                    links = doc.select("a[href*=aquapolis.ua]");

                    for (Element link : links) {

                        String res = urlTitleMap.putIfAbsent(link.attr("href"), link.text());
                        if (res == null) {
                            if (link.attr("href").contains("https:")) {
                                url_array.add(link.attr("href"));
                            } else {
                                url_array.add(("https:" + link.attr("href")));
                            }
                            System.out.println("\nURL: " + link.attr("href"));
                            System.out.println("CONTENT: " + link.text());
                        }
                    }
                } catch (Exception e) {
                    System.out.println("\n" + e);
                }

                log.info(url_array.get(i).toString());
                i++;

            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    public void testProdId() {
        String stUrl = "https://www.usilok.com.ua/index.php?route=product/product&product_id=%s";
        for (int c = 100000; c <= 200000; c += 1000) {
            for (int d = 1; d < 10; d++) {
                for (int i = 1; i <= 15; i++) {
                    try {
                        int genId = c + (100 * d) + i;
                        String stUrlFS = String.format(stUrl, genId);
                        URL url = new URL(stUrlFS);

                        HttpURLConnection con = null;
                        con = (HttpURLConnection) url.openConnection();
                        con.setRequestMethod("GET");
                        con.setConnectTimeout(15000);
                        con.setReadTimeout(15000);

                        if (con.getResponseCode() != Response.SC_NOT_FOUND)
                            log.info(stUrlFS);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }
//    public void parseTestExT(){
//
//        String urlToBuildSiteMapT = "https://www.usilok.com.ua/index.php?route=product/product&product_id=0";
//        try{
//            urlTitleMap.clear();
//
//            String urlToBuildSiteMap = urlToBuildSiteMapT;
//            Document doc = Jsoup.connect(urlToBuildSiteMap).get();
//
//            //  Page Title
//            String title = doc.title();
//            //System.out.println("title: " + title);
//
//            //  Links in page
//            Elements links = doc.select("a[href*=www.usilok.com.ua]");
//            List url_array = new ArrayList();
//            int i = 0;
//            url_array.add(urlToBuildSiteMap);
//            String root = urlToBuildSiteMap;
//            urlTitleMap.put(urlToBuildSiteMap, title);
//            Iterator<String> keySetIterator = urlTitleMap.keySet().iterator();
//            while ((i <= urlTitleMap.size())) {
//                try {
//                    urlToBuildSiteMap = url_array.get(i).toString();
//                    doc = Jsoup.connect(urlToBuildSiteMap).get();
//                    title = doc.title();
//                    links = doc.select("a[href*=www.usilok.com.ua]");
//
//                    for (Element link : links) {
//
//                        String res = urlTitleMap.putIfAbsent(link.attr("href"), link.text());
//                        if (res == null) {
//                            url_array.add(link.attr("href"));
//                            System.out.println("\nURL: " + link.attr("href"));
//                            System.out.println("CONTENT: " + link.text());
//                        }
//                    }
//                } catch (Exception e) {
//                    System.out.println("\n" + e);
//                }
//
//                log.info(url_array.get(i).toString());
//                i++;
//
//            }
//        } catch(
//                Exception e)
//
//        {
//            e.printStackTrace();
//        }
//    }

    public HashMap<String, String> getUrlTitleMap() {
        return urlTitleMap;
    }
}

