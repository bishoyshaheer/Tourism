/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.dto;

import java.util.List;

/**
 *
 * @author root
 */
public class categoryDTO {
    List<String> catVal;
    String catKey;

    public List<String> getCatVal() {
        return catVal;
    }

    public String getCatKey() {
        return catKey;
    }

    public void setCatVal(List<String> catVal) {
        this.catVal = catVal;
    }

    public void setCatKey(String catKey) {
        this.catKey = catKey;
    }
    
    
}
