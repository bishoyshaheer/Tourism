package com.iti.jets.tourism.poi.bean;

import citysdk.tourism.client.poi.base.POIBaseType;
import citysdk.tourism.client.poi.base.POITermType;
import citysdk.tourism.client.poi.single.Category;
import com.iti.jets.tourism.admin.insertData;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Marwa on 4/26/2015.
 */

@ManagedBean(name = "event")
@SessionScoped
public class CategoryDataBean {
    Category cat;
    POIBaseType baseType;
    POITermType termType;
    private List<String> termtypeList;
    private List<POITermType> label = new ArrayList<POITermType>();
    POITermType termAuthor=new POITermType();
    private List<String> langList=new ArrayList<>();

    public CategoryDataBean() {
        cat = new Category();
        baseType = new POIBaseType();
        termType = new POITermType();
        termtypeList = new ArrayList<>();
        termtypeList.add("primary");
        termtypeList.add("secondry");
        label.add(new POITermType());
        langList.add("Ar_ar");
        langList.add("En-en");
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

    public List<POITermType> getLabel() {
        return label;
    }

    public void setLabel(List<POITermType> label) {
        this.label = label;
    }

    public POITermType getTermAuthor() {
        return termAuthor;
    }

    public List<String> getLangList() {
        return langList;
    }

    public void setTermAuthor(POITermType termAuthor) {
        this.termAuthor = termAuthor;
    }

    public void setLangList(List<String> langList) {
        this.langList = langList;
    }

    public void insertData() throws MalformedURLException {
        cat.setValue(termType.getValue());
        cat.setTerm(termType.getTerm());
        cat.setLang(termType.getLang());
        cat.addLabel(label.get(0));
        cat.addDescription(baseType);
        termAuthor.setValue("testAuthor");
        termAuthor.setTerm("primary");
        cat.setAuthor(termAuthor);
        cat.setCreated(new Date());
        cat.setDeleted(null);
        insertData insert = new insertData();
        insert.insertEventCategoryJson(cat);
    }
}
