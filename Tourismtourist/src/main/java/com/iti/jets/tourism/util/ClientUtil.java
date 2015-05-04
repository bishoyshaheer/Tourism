/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.util;

import citysdk.tourism.client.exceptions.ServerErrorException;
import citysdk.tourism.client.exceptions.UnknownErrorException;
import citysdk.tourism.client.requests.TourismClient;
import citysdk.tourism.client.requests.TourismClientFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class ClientUtil {
    public static final String poiBase = "http://tourism.citysdk.cm-lisboa.pt:80/pois/";
    public static final String url = "http://tourism.citysdk.cm-lisboa.pt/";
//    public static final String url = "http://jes.iti.gov.eg/";
    TourismClient client;

    public ClientUtil() {
        
        
    }
    
    public static TourismClient getClient()  throws IOException, UnknownErrorException, ServerErrorException{
        // get a factory instance
        TourismClientFactory factory = TourismClientFactory.getInstance();
        TourismClient client;
        client = factory.getClient(url);
        // set it to use the version 1.0
        client.useVersion("1.0");
        return client;
    }

    public void setClient(TourismClient client) {
        this.client = client;
    }
    
}
