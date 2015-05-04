package com.iti.jets.tourism.poi.bean;

import citysdk.tourism.client.poi.base.*;
import citysdk.tourism.client.poi.single.Event;
import com.iti.jets.tourism.admin.controller.category.AllCategories;
import com.iti.jets.tourism.admin.controller.category.AllEventCategories;
import com.iti.jets.tourism.admin.controller.category.AllPointOfInterest;
import com.iti.jets.tourism.admin.insertData;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Marwa on 4/27/2015.
 */

@ManagedBean(name ="eve")
@SessionScoped
public class EventDataBean {

    private Event event;
    private List<String> termtypeList;
    private List<String> termcatList=new ArrayList<>();
    private String catSelected;
    private POITermType poiTermType;
    private List<POITermType> label = new ArrayList<POITermType>();
    private List<POIBaseType> description = new ArrayList<POIBaseType>();
    private List<POITermType> category = new ArrayList<POITermType>();
    private List<POITermType> time = new ArrayList<POITermType>();
    private List<POITermType> link = new ArrayList<POITermType>();
    private List<Relationship> relationship=new ArrayList<>();
    private static List<String> catId=new ArrayList<>();
    private static List<String> poiId=new ArrayList<>();
    private List<String> poiTargetTerm=new ArrayList<>();
    private static final String base = "http://citysdk.url.pt/pois/";
    public EventDataBean() {
        event=new Event();
        label.add(new POITermType());
        description.add(new POIBaseType());
        termtypeList = new ArrayList<>();
        termtypeList.add("primary");
        termtypeList.add("secondry");
        category.add(new POITermType());
        category.add(new POITermType());
        termcatList.add("category");
        termcatList.add("tag");
        relationship.add(new Relationship());
        poiTargetTerm.add("disjoint");
        poiTargetTerm.add("intersects");
        poiTargetTerm.add("crosses");
        poiTargetTerm.add("overlaps");
        poiTargetTerm.add("within");
        poiTargetTerm.add("contains");
    }

    static {
        try {
            Class.forName("com.iti.jets.tourism.admin.controller.category.AllEventCategories");
            Class.forName("com.iti.jets.tourism.admin.controller.category.AllPointOfInterest");
            AllEventCategories cat = new AllEventCategories();
            catId = cat.getCategoryID();
            AllPointOfInterest poi=new AllPointOfInterest();
            poiId =poi.getPoiID();
            System.out.println(poi);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Event getEvent() {
        return event;
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

    public List<Relationship> getRelationship() {
        return relationship;
    }

    public void setEvent(Event event) {
        this.event = event;
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

    public void setRelationship(List<Relationship> relationship) {
        this.relationship = relationship;
    }

    public List<String> getTermtypeList() {
        return termtypeList;
    }

    public String getCatSelected() {
        return catSelected;
    }

    public void setCatSelected(String catSelected) {
        this.catSelected = catSelected;
    }

    public void setTermtypeList(List<String> termtypeList) {
        this.termtypeList = termtypeList;
    }

    public void setPoiTermType(POITermType poiTermType) {
        this.poiTermType = poiTermType;
    }

    public POITermType getPoiTermType() {
        return poiTermType;
    }

    public List<String> getTermcatList() {
        return termcatList;
    }

    public void setTermcatList(List<String> termcatList) {
        this.termcatList = termcatList;
    }

    public  List<String> getCatId() {
        return catId;
    }

    public  void setCatId(List<String> catId) {
        EventDataBean.catId = catId;
    }

    public  List<String> getPoiId() {
        return poiId;
    }

    public  void setPoiId(List<String> poiId) {
        EventDataBean.poiId = poiId;
    }

    public List<String> getPoiTargetTerm() {
        return poiTargetTerm;
    }

    public void setPoiTargetTerm(List<String> poiTargetTerm) {
        this.poiTargetTerm = poiTargetTerm;
    }

    public void insertPoi() {
        Location l=new Location();
        l.addRelationship(relationship.get(0));
        System.out.println(relationship.get(0).getTargetPOI());
        event.setLocation(l);

        event.addDescription(description.get(0));
        event.addLabel(label.get(0));

        category.get(0).setId(category.get(0).getId());
        event.addCategory(category.get(0));
        System.out.println(category.get(0).getId());
       // event.addCategory(category.get(1));

        event.setCreated(new Date());
        event.setDeleted(null);
        event.setBase(base);
        insertData insert = new insertData();
        insert.insertEventJson(event);
    }
}
