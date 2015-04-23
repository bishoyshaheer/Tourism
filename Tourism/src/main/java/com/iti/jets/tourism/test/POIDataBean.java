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
import citysdk.tourism.client.poi.base.Geometry;
import citysdk.tourism.client.poi.base.Location;
import citysdk.tourism.client.poi.base.POIBaseType;
import citysdk.tourism.client.poi.base.POITermType;
import citysdk.tourism.client.poi.base.POIType;
import citysdk.tourism.client.poi.base.Point;
import citysdk.tourism.client.poi.single.Category;
import citysdk.tourism.client.poi.single.PointOfInterest;
import citysdk.tourism.client.requests.Parameter;
import citysdk.tourism.client.requests.ParameterList;
import citysdk.tourism.client.requests.TourismClient;
import citysdk.tourism.client.requests.TourismClientFactory;
import citysdk.tourism.client.terms.ParameterTerms;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Marwa
 */
@ManagedBean(name = "poi")
@ApplicationScoped
public class POIDataBean {

    static Map<String, List<POITermType>> categoryValue;
    String catSelected;
    Geometry geo;
    Location loc;
    Point point;
    POITermType termType;
    POIBaseType baseType;
    PointOfInterest pointofinterest;
    POIType poiType;
    private List<POITermType> label = new ArrayList<POITermType>();
    private List<POIBaseType> description = new ArrayList<POIBaseType>();
    private List<POITermType> category = new ArrayList<POITermType>();
    private List<POITermType> time = new ArrayList<POITermType>();
    private List<POITermType> link = new ArrayList<POITermType>();

    Map<String, POITermType> poiData = new HashMap<String, POITermType>();

    static {
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
            categoryValue = new HashMap<>();
            for (Category categorie : categories) {
                List<POITermType> categoryLabel = categorie.getLabel();
                categoryValue.put(categorie.getId(), categoryLabel);
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

//    @PostConstruct
//    private void init() {
//        try {
//           // Class.forName("citysdk.tourism.client.requests.TourismClient");
//           // Class.forName("citysdk.tourism.client.requests.TourismClientFactory");
//            String url = "http://jes.iti.gov.eg/";
//            TourismClient tourismClient = TourismClientFactory.getInstance().getClient(url);
//            tourismClient.useVersion("1.0");
//            ParameterList list = new ParameterList();
//            //the parameter list according to the type of categories to be returned
//            list.add(new Parameter(ParameterTerms.LIST, ParameterTerms.POIS.getTerm()));
//            Category cat = tourismClient.getCategories(list);
//            List<Category> categories = cat.getSubCategories();
//            categoryValue = new HashMap<>();
//            for (Category categorie : categories) {
//                List<POITermType> categoryLabel = categorie.getLabel();
//                categoryValue.put(categorie.getId(), categoryLabel);
//            }
//          //  System.out.println("Done"+ca);
//        } catch (InvalidParameterException ex) {
//            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InvalidValueException ex) {
//            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnknownErrorException ex) {
//            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ServerErrorException ex) {
//            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InvalidParameterTermException ex) {
//            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ResourceNotAllowedException ex) {
//            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (VersionNotAvailableException ex) {
//            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//    }
    public POIDataBean() {
        geo = new Geometry();
        point = new Point();
        pointofinterest = new PointOfInterest();
        poiType = new POIType();
        label.add(new POITermType());
        description.add(new POIBaseType());
        time.add(new POITermType());
        
    }

    public Map<String, List<POITermType>> getCategoryValue() {
        return categoryValue;
    }

    public String getCatSelected() {
        return catSelected;
    }

    public void setCategoryValue(Map<String, List<POITermType>> categoryValue) {
        POIDataBean.categoryValue = categoryValue;
    }

    public void setCatSelected(String catSelected) {
        this.catSelected = catSelected;
    }

    public Geometry getGeo() {
        return geo;
    }

    public Location getLoc() {
        return loc;
    }

    public Point getPoint() {
        return point;
    }

    public POITermType getTermType() {
        return termType;
    }

    public POIBaseType getBaseType() {
        return baseType;
    }

    public PointOfInterest getPointofinterest() {
        return pointofinterest;
    }

    public void setGeo(Geometry geo) {
        this.geo = geo;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setTermType(POITermType termType) {
        this.termType = termType;
    }

    public void setBaseType(POIBaseType baseType) {
        this.baseType = baseType;
    }

    public void setPointofinterest(PointOfInterest pointofinterest) {
        this.pointofinterest = pointofinterest;
    }

    public Map<String, POITermType> getPoiData() {
        return poiData;
    }

    public void setPoiData(Map<String, POITermType> poiData) {
        this.poiData = poiData;
    }

    public POIType getPoiType() {
        return poiType;
    }

    public void setPoiType(POIType poiType) {
        this.poiType = poiType;
    }

    public List<POITermType> getLabel() {
        return label;
    }

    public List<POIBaseType> getDescription() {
        return description;
    }

    public List<POITermType> getCategory() {
        return category;
    }

    public List<POITermType> getTime() {
        return time;
    }

    public List<POITermType> getLink() {
        return link;
    }

    public void setLabel(List<POITermType> label) {
        this.label = label;
    }

    public void setDescription(List<POIBaseType> description) {
        this.description = description;
    }

    public void setCategory(List<POITermType> category) {
        this.category = category;
    }

    public void setTime(List<POITermType> time) {
        this.time = time;
    }

    public void setLink(List<POITermType> link) {
        this.link = link;
    }

    public void insertPoi() {
        POITermType p = new POITermType();
        p.setId("552f880eb9b3f20e9461720f");
        pointofinterest.addCategory(p);
        pointofinterest.addLabel(label.get(0));
        pointofinterest.addDescription(description.get(0));
        pointofinterest.setBase("http://citysdk.url.pt/pois/");
        pointofinterest.setLang("pt-PT");
        pointofinterest.setCreated(new Date());
        insertData insert = new insertData();
        insert.insertPointOfInterestJson(pointofinterest);
    }

}
