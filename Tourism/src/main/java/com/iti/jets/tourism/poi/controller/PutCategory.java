/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.poi.controller;

import citysdk.tourism.client.poi.base.POIBaseType;
import citysdk.tourism.client.poi.base.POITermType;
import citysdk.tourism.client.poi.base.POIType;
import citysdk.tourism.client.poi.single.Category;
import citysdk.tourism.client.poi.single.POI;
import citysdk.tourism.client.poi.single.Tag;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Marwa
 */
@Path("/category")
public class PutCategory {

//    public static void main(String[] args) {
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public void insertPointOfInterest() {
        // TODO code application logic here
        try {
            CookieManager cookieManager = new CookieManager();
            CookieHandler.setDefault(cookieManager);
            URL url = new URL("http://jes.iti.gov.eg/CitySDK/auth?username=admin&password=defaultCitySDKPassword");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.connect();
            InputStream is2 = httpCon.getInputStream();
            byte[] br2 = new byte[is2.available()];
            is2.read(br2);
            System.out.println(new String(br2));
            //    httpCon.se
            URL url2 = new URL("http://jes.iti.gov.eg/CitySDK/categories?List=poi");
            final Charset UTF_8 = Charset.forName("UTF-8");

            // String json = "{ \"list\" : \"poi\", \"category\": { \"label\": [ { \"lang\": \"pt-PT\", \"term\": \"primary\",\"value\": \"Categoriateste\" }, { \"lang\": \"en-GB\", \"term\": \"primary\", \"value\": \"test\" } ], \"lang\": \"pt-PT\", \"term\": \"category\", \"value\": \"teste\" } }";
            Category c = new Category();

            POITermType ptt = new POITermType();
            ptt.setLang("pt-PT");
            ptt.setTerm("primary");
            ptt.setValue("Category");
            c.addLabel(ptt);
            POITermType ptt2 = new POITermType();
            ptt2.setLang("pt-PT");
            ptt2.setTerm("category2");
            ptt2.setValue("Category Two");
            c.addCategory(ptt2);

            POIBaseType pbt = new POIBaseType();
            pbt.setValue("category testing ");
            c.setCreated(new Date());
            c.setDeleted(null);
            c.setTerm("primary");
            // c.addLink(new POITermType());
            c.addDescription(pbt);
//            c.setTerm("Test");
            Gson jsn = new Gson();
            //   String json = jsn.toJson(c);
            String json = "{\"list\" : \"poi\", \"category\": " + jsn.toJson(c) + "}";
            System.out.println(json);
            json = new String(json.getBytes(), UTF_8);
            System.out.println(json);
            HttpURLConnection httpCon2 = (HttpURLConnection) url2.openConnection();
            httpCon2.setRequestProperty("Content-Type", "text/json");
            httpCon2.setRequestProperty("Cookie", "SessionId=1");
            httpCon2.setDoOutput(true);
            httpCon2.setRequestMethod("PUT");
            OutputStreamWriter out = new OutputStreamWriter(httpCon2.getOutputStream());

            out.write(json);
            out.close();

            httpCon2.connect();
            InputStream is;
            if (httpCon2.getResponseCode() >= 400) {
                is = httpCon2.getErrorStream();
            } else {
                is = httpCon2.getInputStream();
            }
            byte[] br3 = new byte[is.available()];
            is.read(br3);
            System.out.println(new String(br3));

            httpCon2.disconnect();

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

//            Category c1 = new Category();
//            POITermType ptt2 = new POITermType();
//            ptt2.setLang("pt-PT");
//            ptt2.setTerm("primary");
//            ptt2.setValue("Category");
//            c1.addLabel(ptt2);
//            c.addCategory(c1);
////            Category c1=new Category();
////            c1.addCategory();
         //   String jj = "{\"location\":{\"point\":[],\"line\":[],\"polygon\":[],\"address\":{\"id\":\"\",\"value\":\"\",\"href\":\"\",\"type\":\"\",\"lang\":\"\",\"base\":\"\",\"created\":\"Apr 16, 2015 10:56:22 AM\",\"updated\":\"Apr 16, 2015 10:56:22 AM\",\"deleted\":\"Apr 16, 2015 10:56:22 AM\"},\"relationship\":[],\"id\":\"\",\"value\":\"\",\"href\":\"\",\"type\":\"\",\"lang\":\"\",\"base\":\"\",\"created\":\"Apr 16, 2015 10:56:22 AM\",\"updated\":\"Apr 16, 2015 10:56:22 AM\",\"deleted\":\"Apr 16, 2015 10:56:22 AM\"},\"label\":[{\"term\":\"primary\",\"scheme\":\"\",\"id\":\"\",\"value\":\"Category\",\"href\":\"\",\"type\":\"\",\"lang\":\"pt-PT\",\"base\":\"\",\"created\":\"Apr 16, 2015 10:56:22 AM\",\"updated\":\"Apr 16, 2015 10:56:22 AM\",\"deleted\":\"Apr 16, 2015 10:56:22 AM\"}],\"description\":[],\"category\":[],\"time\":[],\"link\":[],\"id\":\"\",\"value\":\"\",\"href\":\"\",\"type\":\"\",\"lang\":\"\",\"base\":\"\",\"created\":\"Apr 16, 2015 10:56:22 AM\",\"updated\":\"Apr 16, 2015 10:56:22 AM\",\"deleted\":\"Apr 16, 2015 10:56:22 AM\"}\n";
