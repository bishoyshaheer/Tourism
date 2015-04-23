/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.test;

import citysdk.tourism.client.poi.base.POIBaseType;
import citysdk.tourism.client.poi.single.Category;
import citysdk.tourism.client.poi.single.PointOfInterest;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marwa
 */
public class insertData {

    public void insertCategoryJson(Category Poi) {

        try {
            AuthanticateRequestBeforeInsertion auth = new AuthanticateRequestBeforeInsertion();
            auth.getAuthanticate();

            URL url = new URL("http://jes.iti.gov.eg/CitySDK/categories?List=poi");
            Gson jsn = new Gson();
            String json = "{\"list\" : \"poi\", \"category\": " + jsn.toJson(Poi) + "}";

            InsertDataRequest dataRequest = new InsertDataRequest();
            dataRequest.insertIntoServer(json, url);
            System.out.println(json);
        } catch (MalformedURLException ex) {
            Logger.getLogger(insertData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertPointOfInterestJson(PointOfInterest poi) {
        try {
            AuthanticateRequestBeforeInsertion auth = new AuthanticateRequestBeforeInsertion();
            auth.getAuthanticate();

            URL url = new URL("http://jes.iti.gov.eg/CitySDK/pois");
            Gson jsn = new Gson();
            String output = "{\"poi\": " + jsn.toJson(poi) + "}";

            InsertDataRequest dataRequest = new InsertDataRequest();
            dataRequest.insertIntoServer(output, url);
            System.out.println(output);
        } catch (MalformedURLException ex) {
            Logger.getLogger(insertData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
