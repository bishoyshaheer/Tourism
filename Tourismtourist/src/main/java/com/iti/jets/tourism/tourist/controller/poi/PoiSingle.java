/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.tourist.controller.poi;

import citysdk.tourism.client.exceptions.ServerErrorException;
import citysdk.tourism.client.exceptions.UnknownErrorException;
import citysdk.tourism.client.poi.single.PointOfInterest;
import citysdk.tourism.client.requests.TourismClient;
import com.iti.jets.tourism.util.ClientUtil;
import com.iti.jets.tourism.util.JsfUtil;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped
public class PoiSingle {

    private String poiId = "xxx";
//    private PointOfInterest pointOfInterest;
//    private TourismClient client;

//    public PoiSingle() {
//        pointOfInterest = new PointOfInterest();
//        try {
//            client =new  ClientUtil().getClient();
//        } catch (IOException ex) {
//            Logger.getLogger(PoiSingle.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnknownErrorException ex) {
//            Logger.getLogger(PoiSingle.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ServerErrorException ex) {
//            Logger.getLogger(PoiSingle.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public PointOfInterest getPointOfInterest() {
//        return pointOfInterest;
//    }
//
//    public void setPointOfInterest(PointOfInterest pointOfInterest) {
//        this.pointOfInterest = pointOfInterest;
//    }
    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public String fromJSNavigate() {
//        try {
        System.out.println("the POI ID = " + this.poiId);
//            pointOfInterest = client.getPoi(ClientUtil.poiBase, poiId);
//            System.out.println("the POI: " + pointOfInterest);
//            System.out.println("the POI getHref: " + pointOfInterest.getHref());
//            System.out.println("the POI getId: " + pointOfInterest.getId());
//            System.out.println("the POI getImageData: " + pointOfInterest.getImageData());
//            System.out.println("the POI getLang: " + pointOfInterest.getLang());
//            System.out.println("the POI getType: " + pointOfInterest.getType());
//            System.out.println("the POI getCreated: " + pointOfInterest.getCreated());
//        } catch (IOException ex) {
//            Logger.getLogger(PoiSingle.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnknownErrorException ex) {
//            Logger.getLogger(PoiSingle.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ServerErrorException ex) {
//            Logger.getLogger(PoiSingle.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        FacesContext.getCurrentInstance()
//            .getExternalContext()
//            .getRequestMap()
//            .put("poiId", poiId);
//        this.poiId = poiId;
        JsfUtil.sendRedirct("/singlePoi.xhtml");
        return null;
    }

    public String loadMe(){
    return null;
    }
}
