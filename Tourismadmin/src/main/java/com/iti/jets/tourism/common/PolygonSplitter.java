package com.iti.jets.tourism.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AYA on 01/05/2015.
 */
public class PolygonSplitter {

    public List<String> latP=new ArrayList<>();
    String polyPosList="";
    boolean b=false;
    public String setPolgon(String poly){
     //   System.out.println(poly);
        if(poly.contains(",")){
            String[] parts =poly.split(",");
            for (int i = 0; i <parts.length ; i++) {
                if(parts[i].contains("(") ){
                    parts[i]=parts[i].replace("(","");
                    polyPosList+=parts[i]+" ";
                }
                if(parts[i].contains(")")){
                    parts[i]=parts[i].replace(")","");
                    b=true;
                }
                if(b) {
                    polyPosList += parts[i] + ",";
                    b=false;
                }
            }
          //  System.out.println(polyPosList);
        }

        return  polyPosList;

    }
String posLine="";
 List<String>  lines=new ArrayList<>();
    List<String>  linesF=new ArrayList<>();
    public List<String> setPolyLine(String line){
    boolean x=false;
        if(line.contains(",")){
            String[] parts =line.split(",");
            for (int i = 0; i <parts.length ; i++) {
                if(parts[i].contains("(") ){
                    parts[i]=parts[i].replace("(","");
                    posLine+=parts[i]+" ";
                }
                if(parts[i].contains(")")){
                    parts[i]=parts[i].replace(")","");
                    posLine+=parts[i];
                    x=true;
                }
                if(!posLine.equals("")&&x) {
                    lines.add(posLine);
                    posLine="";
                    x=false;
                }
            }

            for (int i =1; i <lines.size() ; i++) {
                linesF.add(lines.get(i-1)+","+lines.get(i));

            }
//            for (int i = 0; i <linesF.size() ; i++) {
//                System.out.println(linesF.get(i));
//            }
        }
        return linesF;
    }
}
