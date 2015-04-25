/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.test;

import citysdk.tourism.client.poi.single.Category;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Marwa
 */
public class getRequestWithoutLibrary {

    public static void main(String[] args) {

        String output = "";
        try {
            URL url = new URL("http://jes.iti.gov.eg/CitySDK/categories?List=poi");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                System.out.println("ERROR");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((output = br.readLine()) != null) {
                output += output;
                System.out.println(output);
                System.out.println(output);
                Gson json = new Gson();
                //String y = json.toJson(output);
                //  Category x = json.fromJson(output, Category.class);

            }

            for (int i = 0; i < output.length(); i++) {
                //  System.out.println(output.get);
            }
        } catch (MalformedURLException ex) {
            System.out.println("Error");
        } catch (IOException ex) {
            System.out.println("IOExcep.");
        }

    }

}
