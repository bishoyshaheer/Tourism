/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.test;

import citysdk.tourism.client.exceptions.InvalidParameterException;
import citysdk.tourism.client.exceptions.InvalidValueException;
import citysdk.tourism.client.exceptions.ResourceNotAllowedException;
import citysdk.tourism.client.exceptions.ServerErrorException;
import citysdk.tourism.client.exceptions.UnknownErrorException;
import citysdk.tourism.client.exceptions.VersionNotAvailableException;
import citysdk.tourism.client.poi.base.POITermType;
import citysdk.tourism.client.poi.lists.ListPOIS;
import citysdk.tourism.client.poi.lists.ListPointOfInterest;
import citysdk.tourism.client.poi.single.Category;
import citysdk.tourism.client.poi.single.PointOfInterest;
import citysdk.tourism.client.requests.Parameter;
import citysdk.tourism.client.requests.ParameterList;
import citysdk.tourism.client.requests.TourismClient;
import citysdk.tourism.client.requests.TourismClientFactory;
import citysdk.tourism.client.terms.ParameterTerms;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marwa
 */
public class TestGetId {

    public static void main(String[] args) throws IOException, UnknownErrorException, ServerErrorException, InvalidParameterException, ResourceNotAllowedException, VersionNotAvailableException {
        String url = "http://jes.iti.gov.eg/";
        TourismClient tourismClient = TourismClientFactory.getInstance().getClient(url);
        tourismClient.useVersion("1.0");
        ParameterList parameterList = new ParameterList();
// parameterList.add(new Parameter(ParameterTerms.CATEGORY,"reaurant"));
        ListPointOfInterest listPointOfInterest = tourismClient.getPois(parameterList);
        List<PointOfInterest> pois = listPointOfInterest.getPois();

        for (PointOfInterest trace : pois) {
            System.out.println(trace.getId());
        }
    }

}
