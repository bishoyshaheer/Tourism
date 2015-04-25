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
import java.util.LinkedHashMap;
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

        // the parameter list according to the type of categories to be returned
        list.add(new Parameter(ParameterTerms.LIST, ParameterTerms.POIS.getTerm()));

        Category cat = tourismClient.getCategories(list);
        List<Category> categories;
        categories = cat.getSubCategories();
        LinkedHashMap<String, String> categoryValue = new LinkedHashMap<String, String>();

        for (Category categorie : categories) {
            List<POITermType> categoryLabel = categorie.getLabel();
            // iterate inside one of categories to return its value
           // System.out.println("c"+categorie.getId());
            catName.clear();
            for (POITermType cat1 : categoryLabel) {
               // if (cat1.getLang().equals("en-GB")) {
                     catName.add(cat1.getValue());
                     System.out.println(cat1.getValue());
                   // categoryValue.put(cat1.getValue(), categorie.getId());
               // }
            }
            System.out.println(catName.size()+categorie.getId());
        }

        for (int i = 0; i < categoryValue.size(); i++) {

        }
//        new GetCategory().getCategoryNames(categories);
    }

    public ArrayList<String> getCategoryNames(List<Category> categories) {
        Category c = new Category();
        //iterate through the list to return IDs
        for (Category categorie : categories) {
            List<POITermType> cat = categorie.getLabel();

            // iterate inside one of categories to return its value
            for (POITermType cat1 : cat) {
                if (cat1.getLang().equals("en-GB")) {
                    catName.add(cat1.getValue());
                    System.out.println(cat1.getValue() + "  " + categorie.getId());
                }
            }
        }
        return catName;
    }
}
