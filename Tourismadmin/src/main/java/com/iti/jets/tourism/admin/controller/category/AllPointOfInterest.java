package com.iti.jets.tourism.admin.controller.category;

import citysdk.tourism.client.exceptions.*;
import citysdk.tourism.client.poi.base.POITermType;
import citysdk.tourism.client.poi.lists.ListPointOfInterest;
import citysdk.tourism.client.poi.single.Category;
import citysdk.tourism.client.poi.single.PointOfInterest;
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
 * Created by Marwa on 4/27/2015.
 */
public class AllPointOfInterest {
    static Map<String, List<String>> poiValue;
    static Map<String, List<POITermType>> poiValueTwo;
    private static List<String> poiLabelVal;
    private static List<String> labelValues;
    private static List<String> poiId;

    public AllPointOfInterest() {
         getAllPointOfInterest();
    }

    public void getAllPointOfInterest(){
        try {
//            Class.forName("citysdk.tourism.client.requests.TourismClient");
//            Class.forName("citysdk.tourism.client.requests.TourismClientFactory");
//            Class.forName("citysdk.tourism.client.requests.ParameterList");
            String url = "http://jes.iti.gov.eg/";
            TourismClient tourismClient = TourismClientFactory.getInstance().getClient(url);
            tourismClient.useVersion("1.0");
            ParameterList params = new ParameterList();
            // the parameter list according to the type of categories to be returned
            params.add(new Parameter(ParameterTerms.LIMIT, -1));
//            params.add(new Parameter(ParameterTerms.OFFSET, 0));
            ListPointOfInterest poiList = tourismClient.getPois(params);
            List<PointOfInterest> pois = poiList.getPois();
            poiId=new ArrayList<>();
            for (int i = 0; i < pois.size(); i++) {
              //  System.out.println(pois.get(i).getId());
                poiId.add(pois.get(i).getId());
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
        } catch (ResourceNotAllowedException ex) {
            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (VersionNotAvailableException ex) {
            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<String> getPoiID() {
        return poiId;
    }

}
