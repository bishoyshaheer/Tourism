package com.iti.jets.tourism.test;
//import java.util.ArrayList;
//
//import java.util.Collection;
//import java.util.List;
//
//

// import javax.el.ExpressionFactory;
//import javax.el.ValueExpression;
//import javax.faces.application.Application;
//import javax.faces.bean.SessionScoped;
//import javax.faces.component.html.HtmlForm;
//import javax.faces.component.html.HtmlInputText;
//import javax.faces.component.html.HtmlOutputText;
//import javax.faces.component.html.HtmlPanelGrid;
//import javax.faces.context.FacesContext;
//
//import static com.sun.faces.el.ELUtils.createValueExpression;
//
///**
// * Created by AYA on 01/05/2015.
// */
//
//
//@javax.faces.bean.ManagedBean(name = "backing_testdynamic")
//@SessionScoped
//public class Testdynamic {private HtmlForm form1;
//    private Collection cl;
//    private HtmlPanelGrid gridComponent;
// private String data;
//String [] test=new String[10];
//    public  Testdynamic() {
//        cl=new ArrayList();
//  int  j=0;
//
//            HtmlInputText inputPutText = new HtmlInputText();
//            gridComponent=new HtmlPanelGrid();
//            inputPutText.setId("iptextCol1-"+j);
//            cl.add(inputPutText);
//        inputPutText.setValueExpression("value",
//                resolveExpression("#{backing_testdynamic.test['" + 0 + "']}"));
//        gridComponent.getChildren().addAll(cl);
//
//
//    }
//
//    public void addFunction(){
//        int i=gridComponent.getChildCount();
//     //   System.out.println(i);
//        HtmlInputText inputPutText1 = new HtmlInputText();
//        inputPutText1.setId("iptextcol1-" + i++);
//        FacesContext fctx = FacesContext.getCurrentInstance();
//        ELContext elctx = fctx.getELContext();
//        Application jsfApp = fctx.getApplication();
//        ExpressionFactory exprFactory = jsfApp.getExpressionFactory();
//      //  inputPutText1.setValueExpression("value", createValueExpression("#{backing_testdynamic.data}"));
//
//        inputPutText1.setValueExpression("value",
//                resolveExpression("#{backing_testdynamic.test['" + i+ "']}"));
//        gridComponent.getChildren().add(inputPutText1);
//
//
//    }
//
//    public static ValueExpression resolveExpression(String expression) {
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        Application app = facesContext.getApplication();
//        ExpressionFactory elFactory = app.getExpressionFactory();
//        ELContext elContext = facesContext.getELContext();
//        ValueExpression valueExp = elFactory.createValueExpression(elContext,
//                expression, String.class);
//        return valueExp;
//    }
//
//    public String getData() {
//        return data;
//    }
//
//    public void setData(String data) {
//        this.data = data;
//    }
//
//    public String[] getTest() {
//        return test;
//    }
//
//    public void setTest(String[] test) {
//        this.test = test;
//    }
//
//    public void view(){
////        System.out.println(data);
//        for (int i = 0; i <test.length ; i++) {
//            System.out.println(test[i]);
//        }
//    }
//
//    public void setGridComponent(HtmlPanelGrid gridComponent) {
//        this.gridComponent = gridComponent;
//    }
//
//    public HtmlPanelGrid getGridComponent() {
//        return gridComponent;
//    }
//
//
//}package com.iti.jets.tourism.test;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

import java.util.ArrayList;
import java.util.Collection;

/**
 + * Created by AYA on 01/05/2015.
 + */
@javax.faces.bean.ManagedBean(name = "backing_testdynamic")

@SessionScoped
public class Testdynamic {private HtmlForm form1;

        private Collection cl;
       private HtmlPanelGrid gridComponent;
     private String data;
    String [] test=new String[10];
        public  Testdynamic() {
               cl=new ArrayList();
          int  j=0;

               HtmlInputText inputPutText = new HtmlInputText();
                   gridComponent=new HtmlPanelGrid();
                  inputPutText.setId("iptextCol1-"+j);
                   cl.add(inputPutText);
             inputPutText.setValueExpression("value",
                       resolveExpression("#{backing_testdynamic.test['" + 0 + "']}"));
                gridComponent.getChildren().addAll(cl);
           }

    public void addFunction(){
               int i=gridComponent.getChildCount();
            //   System.out.println(i);
                HtmlInputText inputPutText1 = new HtmlInputText();
              inputPutText1.setId("iptextcol1-" + i++);
               FacesContext fctx = FacesContext.getCurrentInstance();
               ELContext elctx = fctx.getELContext();
              Application jsfApp = fctx.getApplication();
               ExpressionFactory exprFactory = jsfApp.getExpressionFactory();
             //  inputPutText1.setValueExpression("value", createValueExpression("#{backing_testdynamic.data}"));

                               inputPutText1.setValueExpression("value",
                                               resolveExpression("#{backing_testdynamic.test['" + i+ "']}"));
             gridComponent.getChildren().add(inputPutText1);

                          }
    public static ValueExpression resolveExpression(String expression) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
              ELContext elContext = facesContext.getELContext();
             ValueExpression valueExp = elFactory.createValueExpression(elContext,
                              expression, String.class);
                return valueExp;
            }

    public String getData() {
               return data;
          }

               public void setData(String data) {
               this.data = data;
            }

               public String[] getTest() {
               return test;
            }

                public void setTest(String[] test) {
               this.test = test;
           }

    public void view(){
           System.out.println(data);
        for (int i = 0; i <test.length ; i++) {
            System.out.println(test[i]);
        }
         }
     public void setGridComponent(HtmlPanelGrid gridComponent) {
               this.gridComponent = gridComponent;
           }

    public HtmlPanelGrid getGridComponent() {
              return gridComponent;
          }


            }