/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.admin.controller.category;

import citysdk.tourism.client.exceptions.*;
import citysdk.tourism.client.poi.base.POITermType;
import citysdk.tourism.client.poi.single.Category;
import citysdk.tourism.client.requests.Parameter;
import citysdk.tourism.client.requests.ParameterList;
import citysdk.tourism.client.requests.TourismClient;
import citysdk.tourism.client.requests.TourismClientFactory;
import citysdk.tourism.client.terms.ParameterTerms;
import com.iti.jets.tourism.poi.bean.POIDataBean;

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
public class AllEventCategories {

    static Map<String, List<String>> categoryValue;
    static Map<String, List<POITermType>> categoryValueTwo;
    private static List<String> labelVal;
    private static List<String> labelValues;
    private static List<String> catId;

    public AllEventCategories() {
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
            list.add(new Parameter(ParameterTerms.LIST, ParameterTerms.EVENTS.getTerm()));
            Category cat = tourismClient.getCategories(list);
            List<Category> categories = cat.getSubCategories();
            categoryValueTwo = new HashMap<>();
            categoryValue = new HashMap<>();
            catId = new ArrayList<>();
            labelValues=new ArrayList<>();
            for (Category categorie : categories) {
                List<POITermType> categoryLabel = categorie.getLabel();
                labelVal = new ArrayList<>();
                for (POITermType categoryLabel1 : categoryLabel) {
                    labelVal.add(categoryLabel1.getValue());
                }
//                 System.out.println(labelVal.get(0) + "");
                catId.add(categorie.getId());
                categoryValue.put(categorie.getId(), labelVal);
                categoryValueTwo.put(categorie.getId(), categoryLabel);
            }

            for (Map.Entry<String, List<String>> entry : categoryValue.entrySet()) {

                String key = entry.getKey();
                List<String> values = entry.getValue();
                for (int i = 0; i < values.size(); i++) {
                    labelValues.add(values.get(0));
                    //  System.out.println("Test");
                }
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

    public List<String> getLabelValues(){
        return labelValues;
    }
}
