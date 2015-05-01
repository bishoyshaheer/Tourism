/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.admin.controller.request;

import com.iti.jets.tourism.test.*;
import java.io.IOException;
import java.io.InputStream;
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
public class AuthanticateRequestBeforeInsertion {

    public void getAuthanticate() {
        try {
            CookieManager cookieManager = new CookieManager();
            CookieHandler.setDefault(cookieManager);
            URL url2 = new URL("http://jes.iti.gov.eg/CitySDK/auth?username=admin&password=defaultCitySDKPassword");
            HttpURLConnection httpCon = (HttpURLConnection) url2.openConnection();
            httpCon.connect();
            InputStream is2 = httpCon.getInputStream();
            byte[] br2 = new byte[is2.available()];
            is2.read(br2);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AuthanticateRequestBeforeInsertion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AuthanticateRequestBeforeInsertion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
