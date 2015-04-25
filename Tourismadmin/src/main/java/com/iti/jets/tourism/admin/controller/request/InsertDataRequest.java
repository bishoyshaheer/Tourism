/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.admin.controller.request;

import com.iti.jets.tourism.test.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marwa
 */
public class InsertDataRequest {

    private HttpURLConnection httpCon2;

    public boolean insertIntoServer(String json, URL url) {
        try {
            httpCon2 = (HttpURLConnection) url.openConnection();
            httpCon2.setRequestProperty("Content-Type", "text/json");
            httpCon2.setRequestProperty("Cookie", "SessionId=1");
            httpCon2.setDoOutput(true);
            httpCon2.setRequestMethod("PUT");
            OutputStreamWriter out = new OutputStreamWriter(httpCon2.getOutputStream());
            out.write(json);
            out.close();

            boolean response=getInsertionResponse();
            if(response){
                return true;
            }
            else{
                return false;
            }
        } catch (IOException ex) {
            Logger.getLogger(InsertDataRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean getInsertionResponse() {
        try {
            httpCon2.connect();
            InputStream is;
            if (httpCon2.getResponseCode() >= 400) {
                is = httpCon2.getErrorStream();
                byte[] br3 = new byte[is.available()];
                is.read(br3);
                System.out.println(new String(br3));
                return false;

            } else {
                is = httpCon2.getInputStream();
                byte[] br3 = new byte[is.available()];
                is.read(br3);
                System.out.println(new String(br3));
                return true;
            }

        } catch (IOException ex) {
            Logger.getLogger(InsertDataRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            httpCon2.disconnect();
        }
        return false;
    }
}
