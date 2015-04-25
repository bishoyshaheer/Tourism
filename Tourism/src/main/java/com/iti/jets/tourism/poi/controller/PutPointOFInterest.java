///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.iti.jets.tourism.poi.controller;
//
//import citysdk.tourism.client.poi.base.Geometry;
//import citysdk.tourism.client.poi.base.Location;
//import citysdk.tourism.client.poi.base.POIBaseType;
//import citysdk.tourism.client.poi.base.POITermType;
//import citysdk.tourism.client.poi.base.Point;
//import citysdk.tourism.client.poi.lists.ListPOIS;
//import citysdk.tourism.client.poi.lists.ListPointOfInterest;
//import citysdk.tourism.client.poi.lists.POIS;
//import citysdk.tourism.client.poi.single.PointOfInterest;
//import com.google.gson.Gson;
//import java.awt.image.BufferedImage;
//import java.io.BufferedReader;
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.CookieHandler;
//import java.net.CookieManager;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.nio.charset.Charset;
//import java.util.Base64;
//import java.util.Date;
//import javax.imageio.ImageIO;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import sun.misc.BASE64Decoder;
//
///**
// *
// * @author Marwa
// */
//@Path("/data")
//public class PutPointOFInterest {
//
//    // public static void main(String[] args) {
//    @POST
//    @Consumes("application/json")
//    @Produces("application/json")
//    public String insertPointOfInterest(String req) {
//
//        try {
//            System.out.println("Start");
//            Gson gson = new Gson();
//            POIBaseType req2 = gson.fromJson(req, POIBaseType.class);
//            // System.out.println("Req : " + req2.getImageData());
//            if (req2.getImageData() != "" && req2.getImageData() != null) {
//                String x = req2.getImageData();
//                String y = x.substring(x.indexOf(",") + 1);
//                readImage(y);
//            }
//            // InputStream stream = new ByteArrayInputStream(Base64.decode(req2.getImageData().getBytes(), Base64.DEFAULT));
//            CookieManager cookieManager = new CookieManager();
//            CookieHandler.setDefault(cookieManager);
//            URL url = new URL("http://jes.iti.gov.eg/CitySDK/auth?username=admin&password=defaultCitySDKPassword");
//            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
//            String sessionId = "1234567890";
//            httpCon.setRequestProperty("Cookie", "JSESSIONID=" + sessionId);
//            httpCon.connect();
//            InputStream is2 = httpCon.getInputStream();
//            byte[] br2 = new byte[is2.available()];
//            is2.read(br2);
//            System.out.println(new String(br2));
//            //    httpCon.se
//            URL url2 = new URL("http://jes.iti.gov.eg/CitySDK/pois");
//            final Charset UTF_8 = Charset.forName("UTF-8");
////
//            PointOfInterest poi = new PointOfInterest();
//            //  poi.setId("2345");
//            Location loc = new Location();
//            Geometry g = new Geometry();
//            g.setPosList(req2.getType());
//            String srsname = g.getSrsname();
//
//            Point p = new Point();
//            p.setPoint(g);
//            p.setTerm("entrance");
//            loc.addPoint(p);
//
//            poi.setLocation(loc);
//
//            POITermType ptt = new POITermType();
//            ptt.setTerm("primary");
//            ptt.setValue(req2.getValue());
//            poi.addLabel(ptt);
//
//            POIBaseType pbt = new POIBaseType();
//            pbt.setValue(req2.getLang());
//            pbt.setLang("pt-GB");
//            poi.addDescription(pbt);
//
//            POITermType ptt2 = new POITermType();
//            ptt2.setId("552f880eb9b3f20e9461720f");
//            poi.addCategory(ptt2);
//
//            POITermType ptt3 = new POITermType();
//            ptt3.setTerm("open");
//            ptt3.setValue(req2.getBase());
//            poi.addTime(ptt3);
//
//            POITermType pttl = new POITermType();
//            pttl.setTerm("related");
//            pttl.setHref("");
//            pttl.setValue("image/jpeg");
//
//            poi.addLink(pttl);
//            poi.setBase("http://citysdk.url.pt/pois/");
//            poi.setLang("pt-PT");
//            poi.setCreated(new Date());
//
//            POITermType ptt4 = new POITermType();
//            ptt4.setTerm("primary");
//            ptt4.setValue("city sdk");
//            poi.setAuthor(ptt4);
//
//            POITermType ptt5 = new POITermType();
//            ptt5.setTerm("primary");
//            ptt5.setValue("open-data");
//            ptt5.setLicense(ptt5);
//            ListPointOfInterest lpoi = new ListPointOfInterest();
//            lpoi.add(poi);
//
//            ListPOIS lp = new ListPOIS();
//            lp.setListPoi(lpoi);
//            POIS pos = new POIS();
//            pos.add(poi);
//            Gson jsn = new Gson();
//
//            String output
//                    = output = "{\"poi\": " + jsn.toJson(poi) + "}";
//            output = new String(output.getBytes(), UTF_8);
//            System.out.println(output);
//
//            HttpURLConnection httpCon2 = (HttpURLConnection) url2.openConnection();
//            httpCon2.setRequestProperty("Content-Type", "text/json");
//            //   httpCon2.setRequestProperty("charset", "utf-8");
//            httpCon2.setRequestProperty("Cookie", "SessionId=1");
//            httpCon2.setDoOutput(true);
//            httpCon2.setRequestMethod("PUT");
//            OutputStreamWriter out = new OutputStreamWriter(httpCon2.getOutputStream());
//
//            out.write(output);
//            out.close();
//            //  HttpURLConnection httpConn = (HttpURLConnection) connection;
//            httpCon2.connect();
//            InputStream is;
//            if (httpCon2.getResponseCode() >= 400) {
//                is = httpCon2.getErrorStream();
//                return "{\"Fail\": error}";
//            } else {
//                is = httpCon2.getInputStream();
//            }
//            byte[] br3 = new byte[is.available()];
//            is.read(br3);
//            System.out.println(new String(br3));
//
//            httpCon2.disconnect();
//            return "{\"success\": true}";
//            // System.out.println(results.toString());
//        } catch (MalformedURLException ex) {
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return "{\"Fail\": error}";
//    }
//
//    public static void readImage(String imageString) {
//
//        byte[] imageByte;
//        try {
//
//            BASE64Decoder decoder = new BASE64Decoder();
//            imageByte = decoder.decodeBuffer(imageString);
//            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
//            BufferedImage image = ImageIO.read(bis);
//            ImageIO.write(image, "jpg", new File("D:\\out.jpg"));
//            bis.close();
//            System.out.println(image);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//}
//
////
//// String tomcatDir = System.getProperty("catalina.home");
////            File f = new File(tomcatDir + "/webapps/PP/Images/");
////            String strFileName = "img" + i;
////            File file = new File(tomcatDir + "/webapps/Images/" + strFileName + ".bmp");
////            vcFileName.add(tomcatDir
////                    + "/webapps/Images/" + strFileName + ".bmp");
