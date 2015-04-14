/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.poi.controller;

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

/**
 *
 * @author Marwa
 */
public class PutPointOFInterest {

    public static void main(String[] args) {

        try {
            CookieManager cookieManager = new CookieManager();
            CookieHandler.setDefault(cookieManager);
            URL url = new URL("http://jes.iti.gov.eg/CitySDK/auth?username=admin&password=defaultCitySDKPassword");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            String sessionId = "1234567890";
            httpCon.setRequestProperty("Cookie", "JSESSIONID=" + sessionId);
            httpCon.connect();
            InputStream is2 = httpCon.getInputStream();
            byte[] br2 = new byte[is2.available()];
            is2.read(br2);
            System.out.println(new String(br2));
            //    httpCon.se
            URL url2 = new URL("http://jes.iti.gov.eg/CitySDK/pois");
            final Charset UTF_8 = Charset.forName("UTF-8");
            String json = " {\"poi\":[{\"location\":{\"point\":[{\"Point\":{\"posList\":\"38.7623018491608 -9.09537155864194\",\"srsName\":\"http://www.opengis.net/def/crs/EPSG/0/4326\"},\"term\":\"entrance\"}]},\"label\":[{\"term\":\"primary\",\"value\":\"Primary Label\"}],\"description\":[{\"value\":\"Description 1\",\"lang\":\"pt-GB\"},{\"value\":\"Descricao 1\",\"lang\":\"pt-PT\"}],\"category\":[{\"term\":\"category\",\"value\":\"First Category\",\"lang\":\"pt-PT\"},{\"term\":\"category\",\"value\":\"test\",\"lang\":\"ar-EG\"}],\"time\":[{\"term\":\"open\",\"value\":\"Opening time\"}],\"link\":[],\"id\":\"55166577b9b3f20290d02ab9\",\"base\":\"http://citysdk.url.pt/pois/\",\"lang\":\"pt-PT\",\"created\":\"2015-03-28T08:25:27.1540000Z\",\"author\":{\"term\":\"primary\",\"value\":\"admin\"},\"license\":{\"term\":\"primary\",\"value\":\"open-data\"}}]}";
            // System.out.println(json);
            String output = "{ \"poi\": { \"location\":{ \"point\":[ { \"Point\":{ \"posList\":\"38.7623018491608 -9.09537155864194\", \"srsName\":\"http://www.opengis.net/def/crs/EPSG/0/4326\" }, \"term\":\"entrance\" } ] }, \"label\":[ { \"term\":\"primary\", \"value\":\"Primary Label\"}], \"description\":[ { \"value\":\"Description 1\", \"lang\":\"pt-GB\" }, { \"value\":\"Descricao 1\", \"lang\":\"pt-PT\" } ], \"category\":[ { \"id\":\"5527b90db9b3f210dc46d4c8\" } ], \"time\":[ { \"term\":\"open\", \"value\":\"Opening time\" } ], \"link\":[ ], \"base\":\"http://citysdk.url.pt/pois/\", \"lang\":\"pt-PT\", \"created\":\"2013-02-05T12:31:11.0000000Z\", \"author\":{ \"term\":\"primary\", \"value\":\"CitySDK\" }, \"license\":{ \"term\":\"primary\", \"value\":\"open-data\" } } }";

            output = new String(output.getBytes(), UTF_8);
            System.out.println(output);
            HttpURLConnection httpCon2 = (HttpURLConnection) url2.openConnection();
            httpCon2.setRequestProperty("Content-Type", "text/json");
            //   httpCon2.setRequestProperty("charset", "utf-8");
            httpCon2.setRequestProperty("Cookie", "SessionId=1");
            httpCon2.setDoOutput(true);
            httpCon2.setRequestMethod("PUT");
            OutputStreamWriter out = new OutputStreamWriter(httpCon2.getOutputStream());

            out.write(output);
            out.close();
            //  HttpURLConnection httpConn = (HttpURLConnection) connection;
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
//            httpCon2.connect();
//            InputStream is3 = httpCon2.getInputStream();
//            byte[] br3 = new byte[is3.available()];
//            is3.read(br3);
//            System.out.println(new String(br3));
//            BufferedReader reader = new BufferedReader(new InputStreamReader(httpCon2.getInputStream()));
//            StringBuilder results = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                results.append(line);
//            }
            Gson jsn = new Gson();

            httpCon2.disconnect();
            //  System.out.println(results.toString());
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
