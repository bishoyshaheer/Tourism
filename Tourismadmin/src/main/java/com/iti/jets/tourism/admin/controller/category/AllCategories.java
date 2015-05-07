/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.admin.controller.category;

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
import com.iti.jets.tourism.common.encodeString;
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
public class AllCategories {

    static Map<String, List<String>> categoryValue;
    static Map<String, String> categoryValueID;
    static Map<String, List<POITermType>> categoryValueTwo;
    private static List<String> labelVal;
    private static List<String> labelValues;
    private static List<String> catId;
    private static List<String> catVal;
    private static Category category;
    static  List<Category> categories=new ArrayList<>();

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
           list.add(new Parameter(ParameterTerms.LIMIT, -1));
            Category cat = tourismClient.getCategories(list);
           categories = cat.getSubCategories();
            categories.get(0).getValue();
            System.out.println(categories.get(0).getValue());
            categoryValueTwo = new HashMap<>();
            categoryValueID=new HashMap<>();
            categoryValue = new HashMap<>();
            catId = new ArrayList<>();
            labelValues=new ArrayList<>();
            catVal=new ArrayList<>();
            for (Category categorie : categories) {

//                 get the Value Of the Category
               encodeString e=new encodeString();
                String encode=e.getStringEncoded(categorie.getValue());
                String decode=e.getStringDecoded(categorie.getValue());
//                System.out.println(categorie.getValue());
//              /  System.out.println(decode);
                catVal.add(categorie.getValue());
                categoryValueID.put(categorie.getId(),categorie.getValue());
                //store the CatID and Cat Value that is UNIQUE in HashMap CatValueID


                // get the Label Of the Category
                List<POITermType> categoryLabel = categorie.getLabel();
                labelVal = new ArrayList<>();

                // iterate Through Label and Get its Values
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


//    public Map<String, List<POITermType>> getCategoryMap() {
//        return categoryValueTwo;
//    }
//
//    public List<String> getLabelValues(){
//        return labelValues;
//    }

    public Category getCategoryFromValue(String val){
        if(val.equals("") || val.equals(null)){
            return new Category();
        }
        for (int i = 0; i <categories.size() ; i++) {
            if(categories.get(i).getValue().equals(val))
                category=categories.get(i);
        }
        return category;
    }

    public List<String> getCategoryValues(){return catVal;}

    public Map<String, String> getCategoryValueID(){return categoryValueID;}

    public  String getIDFromValue(String value){
        for (Map.Entry<String,String> entry : categoryValueID.entrySet()) {

            String key = entry.getKey();
            String values = entry.getValue();
           if(value.equals(values)){
               System.out.println(key);
               return key;
           }
        }
        return "error";
    }

    public boolean checkAvailability(String str){

        for (int i = 0; i < catVal.size(); i++) {
            if(str.equals(catVal.get(i))){
                return false;
            }
        }
        return true;

    }
}
