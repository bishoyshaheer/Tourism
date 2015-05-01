package com.iti.jets.tourism.test;
import java.util.ArrayList;

import java.util.Collection;


import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

import static com.sun.faces.el.ELUtils.createValueExpression;

/**
 * Created by AYA on 01/05/2015.
 */


@javax.faces.bean.ManagedBean(name = "backing_testdynamic")
@SessionScoped
public class Testdynamic {private HtmlForm form1;
    private Collection cl;
    private HtmlPanelGrid gridComponent;
 private String data;

    public  Testdynamic() {
        cl=new ArrayList();
  int  j=0;

            HtmlInputText inputPutText = new HtmlInputText();
            gridComponent=new HtmlPanelGrid();
            inputPutText.setId("iptextCol1-"+j);
            cl.add(inputPutText);

        gridComponent.getChildren().addAll(cl);


    }

    public void addFunction(){
        int i=gridComponent.getChildCount();
        HtmlInputText inputPutText1 = new HtmlInputText();
        inputPutText1.setId("iptextcol1-" + i);
        FacesContext fctx = FacesContext.getCurrentInstance();
        ELContext elctx = fctx.getELContext();
        Application jsfApp = fctx.getApplication();
        ExpressionFactory exprFactory = jsfApp.getExpressionFactory();
        inputPutText1.setValueExpression("value", createValueExpression("#{backing_testdynamic.data}", String.class));
        gridComponent.getChildren().add(inputPutText1);


    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void view(){
        System.out.println(data);
    }

    public void setGridComponent(HtmlPanelGrid gridComponent) {
        this.gridComponent = gridComponent;
    }

    public HtmlPanelGrid getGridComponent() {
        return gridComponent;
    }


}