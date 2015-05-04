/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.util;

import citysdk.tourism.client.parser.DataReader;
import citysdk.tourism.client.poi.single.Category;
import citysdk.tourism.client.terms.Term;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author root
 */
public class CategoryUtil {

    //this method split the categories to return List Of Lists
    public static ArrayList<Map.Entry<String, List<String>>> categorySplitter(List<Category> categories, Term term, Locale locale) {
        Map<String, List<String>> categoryMap = new HashMap<>();
        for (Category cat : categories) {
            String label = DataReader.getLabel(cat, term, locale);

            
            String catKey = "";
            String catVal = "";
            String[] parts = label.split(" - ");
            if (parts.length > 0) catKey = parts[0];
            if (parts.length > 1) catVal = parts[1];

            if (!categoryMap.containsKey(catKey)) {
                List<String> catValList = new ArrayList<>();
                catValList.add(catVal);

                categoryMap.put(catKey, catValList);
            } else {
                List<String> catValList = categoryMap.get(catKey);
                catValList.add(catVal);
                categoryMap.replace(catKey, catValList);
            }
        }
        Set<Map.Entry<String, List<String>>> categorySet = categoryMap.entrySet();
        return new ArrayList<Map.Entry<String, List<String>>>(categorySet);
//        return categoryMap;
    }
}
