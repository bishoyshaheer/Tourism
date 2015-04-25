/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.poi.bean;

import com.iti.jets.tourism.admin.*;
import com.iti.jets.tourism.admin.controller.request.*;
import citysdk.tourism.client.poi.base.POIBaseType;
import citysdk.tourism.client.poi.base.POITermType;
import citysdk.tourism.client.poi.single.Category;
import java.io.Serializable;
import java.net.MalformedURLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Marwa
 */
@ManagedBean(name = "data")
@SessionScoped
public class DataBean implements Serializable {

    Category cat;
    POIBaseType baseType;
    POITermType termType;
    private List<String> termtypeList;

    public DataBean() {
        cat = new Category();
        baseType = new POIBaseType();
        termType = new POITermType();
        termtypeList = new ArrayList<>();
        termtypeList.add("primary");
        termtypeList.add("secondry");
    }

    public void setCat(Category cat) {
        this.cat = cat;
    }

    public void setBaseType(POIBaseType baseType) {
        this.baseType = baseType;
    }

    public void setTermType(POITermType termType) {
        this.termType = termType;
    }

    public Category getCat() {
        return cat;
    }

    public POIBaseType getBaseType() {
        return baseType;
    }

    public POITermType getTermType() {
        return termType;
    }

    public List<String> getTermtypeList() {
        return termtypeList;
    }

    public void setTermtypeList(List<String> termtypeList) {
        this.termtypeList = termtypeList;
    }

    public void insertData() throws MalformedURLException {
        cat.addLabel(termType);
        cat.addDescription(baseType);
        cat.setCreated(new Date());
        cat.setDeleted(null);
        insertData insert = new insertData();
        insert.insertCategoryJson(cat);
    }

}
