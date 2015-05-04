/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.poi.bean;

import com.iti.jets.tourism.admin.*;
import com.iti.jets.tourism.admin.controller.category.AllCategories;
import com.iti.jets.tourism.admin.controller.request.*;
import citysdk.tourism.client.poi.base.POIBaseType;
import citysdk.tourism.client.poi.base.POITermType;
import citysdk.tourism.client.poi.single.Category;
import com.iti.jets.tourism.common.encodeString;
import org.primefaces.event.FileUploadEvent;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
    private List<POITermType> label = new ArrayList<POITermType>();
    private List<String> termtypeList;
    POITermType termAuthor=new POITermType();
    private List<String> langList=new ArrayList<>();
    private List<Category> subCategories=new ArrayList<>();
    private List<String> subCatTerm=new ArrayList<>();

    public DataBean() {
        cat = new Category();
        baseType = new POIBaseType();
        termType = new POITermType();
        termtypeList = new ArrayList<>();
        termtypeList.add("primary");
        termtypeList.add("secondry");
        label.add(new POITermType());
        langList.add("Ar_ar");
        langList.add("En-en");
        subCategories.add(new Category());
        subCatTerm.add("category");
        subCatTerm.add("tag");
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

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public List<String> getSubCatTerm() {
        return subCatTerm;
    }

    public void setSubCatTerm(List<String> subCatTerm) {
        this.subCatTerm = subCatTerm;
    }

    public void handleFileUpload(FileUploadEvent event) {
        System.out.println("True");
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        try {
            //  String tomcatDir = System.getProperty("catalina.home");
            File pathFile=new File(System.getProperty("user.home")+"/Cat");
            if(!pathFile.exists())
                pathFile.mkdir();
            File f=new File(System.getProperty("user.home")+"/Tourism/Cat/");
            if(!f.exists())
                f.mkdir();
            File file2=new File(f.getPath()+"/"+event.getFile().getFileName());
            OutputStream outputStream=new FileOutputStream(file2);
            outputStream.write(event.getFile().getContents());

//            ClientConfig config = new ClientConfig();
//
//            Client client = ClientBuilder.newClient(config);
//
//            WebTarget target = client.target(getBaseURI());
//
//            System.out.println(target.path("rest").path("data").request());

        } catch (Exception e) {
            e.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void insertData() throws MalformedURLException {

        FacesContext context = FacesContext.getCurrentInstance();

        BASE64Encoder encoder = new BASE64Encoder();

//        encodeString e=new encodeString();
//       String y= e.getStringEncoded(baseType.getValue());
//        baseType.setValue(y);
  //      e.getStringDecoded(y);
        AllCategories categ=new AllCategories();
        boolean test=categ.checkAvailability(termType.getValue());
        if(test) {
            encodeString e=new encodeString();
            String x;
            x= e.getStringEncoded(termType.getValue());
            termType.setValue(x);
            cat.setValue(termType.getValue());
            cat.setTerm(termType.getTerm());
            cat.setLang(termType.getLang());
            cat.addLabel(label.get(0));
//            x= e.getStringEncoded(baseType.getValue());
//            baseType.setValue(x);
//            cat.addDescription(baseType);
           // System.out.println("Test "+cat.getDescription());
            subCategories.get(0).setCreated(new Date());
            subCategories.get(0).setDeleted(null);
            cat.addCategory(subCategories.get(0));
            termAuthor.setValue("testAuthor");
            termAuthor.setTerm("primary");
            cat.setAuthor(termAuthor);
            cat.setCreated(new Date());
            cat.setDeleted(null);

            insertData insert = new insertData();
            String response=insert.insertCategoryJson(cat);
            if(response.equals("true")){
                context.addMessage(null, new FacesMessage("Successful Data Inserted"));
            }
            else{
                context.addMessage(null, new FacesMessage("Error !!"+response));
            }

        }
        else {
            context.addMessage(null, new FacesMessage("Error !!", "Category Name is already exist"));
        }

    }

}
