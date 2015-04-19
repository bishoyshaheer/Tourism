/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.poi.controller;

import citysdk.tourism.client.poi.single.Category;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author JEDiver
 */
public class PutTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        try {
            CookieManager cookieManager = new CookieManager();
            CookieHandler.setDefault(cookieManager);
            URL url = new URL("http://jes.iti.gov.eg/CitySDK/auth?username=admin&password=defaultCitySDKPassword");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            Map<String, List<String>> headerFields = httpCon.getHeaderFields();

            Set<String> headerFieldsSet = headerFields.keySet();
            Iterator<String> hearerFieldsIter = headerFieldsSet.iterator();
            while (hearerFieldsIter.hasNext()) {
                String headerFieldKey = hearerFieldsIter.next();

                if ("Set-Cookie".equalsIgnoreCase(headerFieldKey)) {
                    List<String> headerFieldValue = headerFields.get(headerFieldKey);

                    for (String headerValue : headerFieldValue) {

                        System.out.println("Cookie Found...");

                        String[] fields = headerValue.split(";");

                        String cookieValue = fields[0];
                        String expires = null;
                        String path = null;
                        String domain = null;
                        boolean secure = false;

                        // Parse each field
                        for (int j = 1; j < fields.length; j++) {
                            if ("secure".equalsIgnoreCase(fields[j])) {
                                secure = true;
                            } else if (fields[j].indexOf('=') > 0) {
                                String[] f = fields[j].split("=");
                                if ("expires".equalsIgnoreCase(f[0])) {
                                    expires = f[1];
                                } else if ("domain".equalsIgnoreCase(f[0])) {
                                    domain = f[1];
                                } else if ("path".equalsIgnoreCase(f[0])) {
                                    path = f[1];
                                }
                            }

                        }
                        
                        

                        System.out.println("cookieValue:" + cookieValue);
                        System.out.println("expires:" + expires);
                        System.out.println("path:" + path);
                        System.out.println("domain:" + domain);
                        System.out.println("secure:" + secure);

                        System.out.println("*****************************************");

                    }

                }
            }
//            String sessionId = "156513243";
//            httpCon.setRequestProperty("Cookie", "JSESSIONID=" + sessionId);
//            httpCon.connect();
//            InputStream is2 = httpCon.getInputStream();
//            byte[] br2 = new byte[is2.available()];
//            is2.read(br2);
//            System.out.println(new String(br2));
////            httpCon.se
//            URL url2 = new URL("http://jes.iti.gov.eg/CitySDK/categories?List=poi");
//            final Charset UTF_8 = Charset.forName("UTF-8");
//            String json = " {\"list\" : \"poi\", \"category\": { \"label\": [ { \"lang\": \"pt-PT\", \"term\": \"primary\",\"value\": \"Bishoy\" }, { \"lang\": \"en-GB\", \"term\": \"primary\", \"value\": \"test\" } ], \"lang\": \"pt-PT\", \"term\": \"category\", \"value\": \"teste\" } }";
//            System.out.println(json);
//            json = new String(json.getBytes(), UTF_8);
//            System.out.println(json);
//            HttpURLConnection httpCon2 = (HttpURLConnection) url2.openConnection();
//            httpCon2.setRequestProperty("Content-Type", "text/json");
//            httpCon2.setRequestProperty("Cookie", "SessionId=1");
//            httpCon2.setDoOutput(true);
//            httpCon2.setRequestMethod("PUT");
//            OutputStreamWriter out = new OutputStreamWriter(httpCon2.getOutputStream());
//            out.write(json);
//            out.close();
//
//            httpCon2.connect();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(httpCon2.getInputStream()));
//            StringBuilder results = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                results.append(line);
//            }
//            httpCon2.disconnect();
//            System.out.println(results.toString());
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
