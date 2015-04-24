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
import citysdk.tourism.client.poi.single.Category;
import citysdk.tourism.client.requests.Parameter;
import citysdk.tourism.client.requests.ParameterList;
import citysdk.tourism.client.requests.TourismClient;
import citysdk.tourism.client.requests.TourismClientFactory;
import citysdk.tourism.client.terms.ParameterTerms;
import static com.iti.jets.tourism.test.POIDataBean.categoryValue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marwa
 */
public class AllCategories {

    static Map<String, List<String>> categoryValue;
    static Map<String, List<POITermType>> categoryValueTwo;
    private static List<String> labelVal = new ArrayList<>();
    private static List<String> catId;

    public AllCategories() {
        getCategory();
    }

    public void getCategory() {
        try {
            Class.forName("citysdk.tourism.client.requests.TourismClient");
            Class.forName("citysdk.tourism.client.requests.TourismClientFactory");
            Class.forName("citysdk.tourism.client.requests.ParameterList");
            String url = "http://jes.iti.gov.eg/";
            TourismClient tourismClient = TourismClientFactory.getInstance().getClient(url);
            tourismClient.useVersion("1.0");
            ParameterList list = new ParameterList();
            // the parameter list according to the type of categories to be returned
            list.add(new Parameter(ParameterTerms.LIST, ParameterTerms.POIS.getTerm()));
            Category cat = tourismClient.getCategories(list);
            List<Category> categories = cat.getSubCategories();
            categoryValueTwo = new HashMap<>();
            categoryValue = new HashMap<>();
            catId = new ArrayList<>();
            for (Category categorie : categories) {
                List<POITermType> categoryLabel = categorie.getLabel();
                labelVal.clear();
                for (POITermType categoryLabel1 : categoryLabel) {
                    labelVal.add(categoryLabel1.getValue());
                }
                // System.out.println(labelVal.get(0) + "");
                catId.add(categorie.getId());
                categoryValue.put(categorie.getId(), labelVal);
                categoryValueTwo.put(categorie.getId(), categoryLabel);
            }
        } catch (InvalidParameterException ex) {
            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidValueException ex) {
            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownErrorException ex) {
            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServerErrorException ex) {
            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidParameterTermException ex) {
            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ResourceNotAllowedException ex) {
            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (VersionNotAvailableException ex) {
            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> getCategoryID() {
        return catId;
    }

    public Map<String, List<String>> getCategoryValID() {
        return categoryValue;
    }

    public Map<String, List<POITermType>> getCategoryMap() {
        return categoryValueTwo;
    }
}
