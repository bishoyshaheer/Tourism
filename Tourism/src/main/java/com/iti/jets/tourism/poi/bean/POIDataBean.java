/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.poi.bean;

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
import com.iti.jets.tourism.admin.controller.category.AllCategories;
import com.iti.jets.tourism.admin.insertData;
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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Marwa
 */
@ManagedBean(name = "poi")
@SessionScoped
public class POIDataBean {

    static Map<String, List<String>> categoryValue;
    String catSelected;
    int lat;
    int lng;
    POITermType termType;
    POIBaseType baseType;
    PointOfInterest pointofinterest;
    POIType poiType;
    private List<String> termtypeList;
    private static final String base = "http://citysdk.url.pt/pois/";
    private List<POITermType> label = new ArrayList<POITermType>();
    private List<POIBaseType> description = new ArrayList<POIBaseType>();
    private List<POITermType> category = new ArrayList<POITermType>();
    private List<POITermType> time = new ArrayList<POITermType>();
    private List<POITermType> link = new ArrayList<POITermType>();
    private static List<String> labelVal = new ArrayList<>();

    private static List<String> catId;
    Map<String, POITermType> poiData = new HashMap<String, POITermType>();

    static {
        try {
            Class.forName("com.iti.jets.tourism.admin.controller.category.AllCategories");
            AllCategories cat = new AllCategories();
            catId = cat.getCategoryID();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public POIDataBean() {
        termtypeList = new ArrayList<>();
        pointofinterest = new PointOfInterest();
        poiType = new POIType();
        label.add(new POITermType());
        description.add(new POIBaseType());
        time.add(new POITermType());
        termtypeList.add("primary");
        termtypeList.add("secondry");
    }

    public Map<String, List<String>> getCategoryValue() {
        return categoryValue;
    }

    public String getCatSelected() {
        return catSelected;
    }

    public static List<String> getLabelVal() {
        return labelVal;
    }

    public static void setLabelVal(List<String> labelVal) {
        POIDataBean.labelVal = labelVal;
    }

    public void setCategoryValue(Map<String, List<String>> categoryValue) {
        POIDataBean.categoryValue = categoryValue;
    }

    public void setCatSelected(String catSelected) {
        this.catSelected = catSelected;
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

    public List<String> getCatId() {
        return catId;
    }

    public void setCatId(List<String> catId) {
        POIDataBean.catId = catId;
    }

    public List<String> getTermtypeList() {
        return termtypeList;
    }

    public void setTermtypeList(List<String> termtypeList) {
        this.termtypeList = termtypeList;
    }

    public int getLat() {
        return lat;
    }

    public int getLng() {
        return lng;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }

    public void handleFileUpload(FileUploadEvent event) {
     System.out.println("True");
     FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
     FacesContext.getCurrentInstance().addMessage(null, message);
     }
    public void insertPoi() {
        POITermType p = new POITermType();
        // System.out.println(label.get(0).term);
        p.setId(catSelected);
        pointofinterest.addCategory(p);

        // after adding map
//        Point point = new Point();
//        Location l = new Location();
//        Geometry g = new Geometry();
//        g.setPosList(lat + " " + lng);
//        point.setPoint(g);
//        l.addPoint(point);
//        pointofinterest.setLocation(l);
        pointofinterest.addLabel(label.get(0));
        pointofinterest.addDescription(description.get(0));
        pointofinterest.setBase(base);
        pointofinterest.setLang("pt-PT");
        pointofinterest.setCreated(new Date());
        insertData insert = new insertData();
        insert.insertPointOfInterestJson(pointofinterest);
    }

}
