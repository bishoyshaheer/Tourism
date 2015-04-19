/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.test;

import citysdk.tourism.client.exceptions.InvalidParameterException;
import citysdk.tourism.client.exceptions.InvalidParameterTermException;
import citysdk.tourism.client.exceptions.InvalidValueException;
import citysdk.tourism.client.exceptions.ResourceNotAllowedException;
import citysdk.tourism.client.exceptions.ServerErrorException;
import citysdk.tourism.client.exceptions.UnknownErrorException;
import citysdk.tourism.client.exceptions.VersionNotAvailableException;
import citysdk.tourism.client.poi.base.POITermType;
import citysdk.tourism.client.poi.lists.ListPointOfInterest;
import citysdk.tourism.client.poi.single.Category;
import citysdk.tourism.client.poi.single.PointOfInterest;
import citysdk.tourism.client.requests.Parameter;
import citysdk.tourism.client.requests.ParameterList;
import citysdk.tourism.client.requests.TourismClient;
import citysdk.tourism.client.requests.TourismClientFactory;
import citysdk.tourism.client.terms.ParameterTerms;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marwa
 */
public class GetCategory {

    private static ArrayList<String> catName = new ArrayList();

    public static void main(String[] args) throws IOException, UnknownErrorException, ServerErrorException, InvalidParameterException, ResourceNotAllowedException, VersionNotAvailableException, InvalidParameterTermException, InvalidValueException {
        String url = "http://jes.iti.gov.eg/";
        TourismClient tourismClient = TourismClientFactory.getInstance().getClient(url);
        tourismClient.useVersion("1.0");
        ParameterList list = new ParameterList();
        list.add(new Parameter(ParameterTerms.LIST, ParameterTerms.POIS.getTerm()));
        Category cat = tourismClient.getCategories(list);
        List<Category> categories;

        categories = cat.getSubCategories();

        for (Category categorie : categories) {
            List<POITermType> cat2 = categorie.getLabel();
            for (POITermType cat1 : cat2) {
                catName.add(cat1.getValue());
                System.out.println(cat1.getValue());
            }
        }

    }

    public ArrayList<String> getCategoryNames() {
        Category c = new Category();
        List<POITermType> cat = c.getLabel();
        for (POITermType cat1 : cat) {
            catName.add(cat1.getValue());
        }
        return catName;
    }
}
