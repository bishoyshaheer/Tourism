/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.tourist.controller.home;

import citysdk.tourism.client.exceptions.InvalidParameterException;
import citysdk.tourism.client.exceptions.InvalidParameterTermException;
import citysdk.tourism.client.exceptions.InvalidValueException;
import citysdk.tourism.client.exceptions.ResourceNotAllowedException;
import citysdk.tourism.client.exceptions.ServerErrorException;
import citysdk.tourism.client.exceptions.UnknownErrorException;
import citysdk.tourism.client.exceptions.VersionNotAvailableException;
import citysdk.tourism.client.parser.DataReader;
import citysdk.tourism.client.poi.single.Category;
import citysdk.tourism.client.requests.Parameter;
import citysdk.tourism.client.requests.ParameterList;
import citysdk.tourism.client.requests.TourismClient;
import citysdk.tourism.client.terms.ParameterTerms;
import citysdk.tourism.client.terms.Term;
import com.iti.jets.tourism.util.CategoryUtil;
import com.iti.jets.tourism.util.ClientUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author root
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class HomeApplicationBean {

    ArrayList<Map.Entry<String, List<String>>> categoriesMap;
    Locale locale;

    public HomeApplicationBean() {
        try {
            TourismClient client = ClientUtil.getClient();

            ParameterList list = new ParameterList();
            // the parameter list according to the type of categories to be returned
            list.add(new Parameter(ParameterTerms.LIST, ParameterTerms.POIS.getTerm()));
            list.add(new Parameter(ParameterTerms.LIMIT, new Integer(-1)));

            Category cat = client.getCategories(list);
            List<Category> categories = cat.getSubCategories();

            localInit();

            categoriesMap = CategoryUtil.categorySplitter(categories, Term.LABEL_TERM_PRIMARY, locale);
            System.out.println("categories map: " + categoriesMap);
        } catch (IOException ex) {
            Logger.getLogger(HomeApplicationBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownErrorException ex) {
            Logger.getLogger(HomeApplicationBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServerErrorException ex) {
            Logger.getLogger(HomeApplicationBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidParameterException ex) {
            Logger.getLogger(HomeApplicationBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidValueException ex) {
            Logger.getLogger(HomeApplicationBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidParameterTermException ex) {
            Logger.getLogger(HomeApplicationBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ResourceNotAllowedException ex) {
            Logger.getLogger(HomeApplicationBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (VersionNotAvailableException ex) {
            Logger.getLogger(HomeApplicationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Map.Entry<String, List<String>>> getCategoriesMap() {
        return categoriesMap;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    private void localInit() {
        // define the default language to be used, in case the wanted language
        // does not exist. The default language - if this method is not called - is en_GB.
        DataReader.setDefaultLocale(new Locale("en", "GB"));

        // get the default locale
        locale = new Locale("pt", "PT");
    }

}
