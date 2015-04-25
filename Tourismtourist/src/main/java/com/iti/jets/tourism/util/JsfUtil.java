/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.util;

import java.io.IOException;
import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author root
 */
public class JsfUtil {
    public static void sendRedirct(String uri){
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, uri));
        try {

//            extContext.getRequestMap()
//            .put("poiId", "hihihi");
            extContext.redirect(url);
        } catch (IOException ioe) {
            throw new FacesException(ioe);

        }
    }
}
