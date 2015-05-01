/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.jets.tourism.poi.bean;

import citysdk.tourism.client.exceptions.InvalidParameterException;
import citysdk.tourism.client.exceptions.InvalidParameterTermException;
import citysdk.tourism.client.exceptions.InvalidValueException;
import citysdk.tourism.client.exceptions.ResourceNotAllowedException;
import citysdk.tourism.client.exceptions.ServerErrorException;
import citysdk.tourism.client.exceptions.UnknownErrorException;
import citysdk.tourism.client.exceptions.VersionNotAvailableException;
import citysdk.tourism.client.poi.base.*;
import citysdk.tourism.client.poi.single.Category;
import citysdk.tourism.client.poi.single.PointOfInterest;
import citysdk.tourism.client.requests.Parameter;
import citysdk.tourism.client.requests.ParameterList;
import citysdk.tourism.client.requests.TourismClient;
import citysdk.tourism.client.requests.TourismClientFactory;
import citysdk.tourism.client.terms.ParameterTerms;
import com.iti.jets.tourism.admin.controller.category.AllCategories;
import com.iti.jets.tourism.admin.insertData;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.ws.Response;

import com.iti.jets.tourism.common.PolygonSplitter;
import com.iti.jets.tourism.common.encodeString;
import org.glassfish.jersey.client.ClientConfig;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.*;

import java.nio.file.Files;

import com.iti.jets.tourism.poi.controller.GetImage;
import org.primefaces.model.map.Polygon;

/**
 *
 * @author Marwa
 */
@ManagedBean(name = "poi")
@SessionScoped
public class POIDataBean {

    static Map<String, List<String>> categoryValue;
    String catSelected;
    private MapModel emptyModel;
    private String title;
    private double lat;
    private double lng;
    private String Language;
    private String latlngArr;
    private String latlngLine;
    POITermType termType;
    POITermType termTypeCat=new POITermType();
    POIBaseType baseType=new POIBaseType();
    POIBaseType baseTypeAdd=new POITermType();
    PointOfInterest pointofinterest;
    private List<POITermType> linkURL=new ArrayList<>();
    POIType poiType;
    POITermType termAuthor=new POITermType();
    private Geometry geometry;
    private List<String> termtypeList;
    private List<String> langList=new ArrayList<>();
    private static final String base = "http://citysdk.url.pt/pois/";
    private List<POITermType> label = new ArrayList<POITermType>();
    private List<POIBaseType> description = new ArrayList<POIBaseType>();
    private List<POITermType> category = new ArrayList<POITermType>();
    private List<POITermType> time = new ArrayList<POITermType>();
    private List<POITermType> link = new ArrayList<>();
    private List<Relationship> relationship=new ArrayList<>();
    private static List<String> labelVal = new ArrayList<>();
    private List<String> termcatList=new ArrayList<>();
    private List<String> mapList=new ArrayList<>();
    private static List<String> catId;
    private static List<String> linkTerm=new ArrayList<>();
    Map<String, POITermType> poiData = new HashMap<String, POITermType>();
    private String selectedMapType;
    private List<Double> latArr=new ArrayList<>();
    private List<Double> lngArr=new ArrayList<>();
    private List<Point> pointArr=new ArrayList<>();
    private List<citysdk.tourism.client.poi.base.Polygon> polygonArr = new ArrayList<citysdk.tourism.client.poi.base.Polygon>();
    private List<String> values;


    static {
        try {
            Class.forName("com.iti.jets.tourism.admin.controller.category.AllCategories");
            AllCategories cat = new AllCategories();
            catId = cat.getCategoryValues();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(POIDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PostConstruct
    public void init() {
        emptyModel = new DefaultMapModel();
        values = new ArrayList();
        values.add("");
    }

    public POIDataBean() {
        termtypeList = new ArrayList<>();
        pointofinterest = new PointOfInterest();
        poiType = new POIType();
        label.add(new POITermType());
        description.add(new POIBaseType());
        time.add(new POITermType());
        termtypeList.add("primary");
        termtypeList.add("secondry");
        termcatList.add("category");
        termcatList.add("tag");
        mapList.add("point");
        mapList.add("line");
        mapList.add("polyLine");
        langList.add("Ar_ar");
        langList.add("En-en");
        linkURL.add(new POITermType());
        linkTerm.add("alternate");
        linkTerm.add("source");

    }

    public String getLatlngArr() {
        return latlngArr;
    }

    public void setLatlngArr(String latlngArr) {
        this.latlngArr = latlngArr;
    }

    public String getLatlngLine() {
        return latlngLine;
    }

    public void setLatlngLine(String latlngLine) {
        this.latlngLine = latlngLine;
    }

    public void extend() {
        values.add("");
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public List<String> getValues() {
        return values;
    }

    public Map<String, List<String>> getCategoryValue() {
        return categoryValue;
    }

    public String getCatSelected() {
        return catSelected;
    }

    public static List<String> getLabelVal() {
        return labelVal;
    }

    public static void setLabelVal(List<String> labelVal) {
        POIDataBean.labelVal = labelVal;
    }

    public void setCategoryValue(Map<String, List<String>> categoryValue) {
        POIDataBean.categoryValue = categoryValue;
    }

    public void setCatSelected(String catSelected) {
        this.catSelected = catSelected;
    }

    public POITermType getTermType() {
        return termType;
    }

    public POIBaseType getBaseType() {
        return baseType;
    }

    public PointOfInterest getPointofinterest() {
        return pointofinterest;
    }

    public void setTermType(POITermType termType) {
        this.termType = termType;
    }

    public void setBaseType(POIBaseType baseType) {
        this.baseType = baseType;
    }

    public void setPointofinterest(PointOfInterest pointofinterest) {
        this.pointofinterest = pointofinterest;
    }

    public Map<String, POITermType> getPoiData() {
        return poiData;
    }

    public void setPoiData(Map<String, POITermType> poiData) {
        this.poiData = poiData;
    }

    public POIType getPoiType() {
        return poiType;
    }

    public void setPoiType(POIType poiType) {
        this.poiType = poiType;
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

    public String getSelectedMapType() {
        return selectedMapType;
    }

    public void setSelectedMapType(String selectedMapType) {
        this.selectedMapType = selectedMapType;
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

    public List<String> getCatId() {
        return catId;
    }

    public void setCatId(List<String> catId) {
        POIDataBean.catId = catId;
    }

    public List<String> getTermtypeList() {
        return termtypeList;
    }

    public void setTermtypeList(List<String> termtypeList) {
        this.termtypeList = termtypeList;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }

    public List<Double> getLatArr() {
        return latArr;
    }

    public List<Double> getLngArr() {
        return lngArr;
    }

    public void setLatArr(List<Double> latArr) {
        this.latArr = latArr;
    }

    public void setLngArr(List<Double> lngArr) {
        this.lngArr = lngArr;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public MapModel getEmptyModel() {
        return emptyModel;
    }

    public String getTitle() {
        return title;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public List<Relationship> getRelationship() {
        return relationship;
    }

    public List<String> getTermcatList() {
        return termcatList;
    }

    public List<String> getMapList() {
        return mapList;
    }

    public void setRelationship(List<Relationship> relationship) {
        this.relationship = relationship;
    }

    public void setTermcatList(List<String> termcatList) {
        this.termcatList = termcatList;
    }

    public void setMapList(List<String> mapList) {
        this.mapList = mapList;
    }

    public POITermType getTermTypeCat() {
        return termTypeCat;
    }

    public void setTermTypeCat(POITermType termTypeCat) {
        this.termTypeCat = termTypeCat;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public void setBaseTypeAdd(POIBaseType baseTypeAdd) {
        this.baseTypeAdd = baseTypeAdd;
    }

    public POIBaseType getBaseTypeAdd() {
        return baseTypeAdd;
    }

    public POITermType getTermAuthor() {

        return termAuthor;
    }

    public void setTermAuthor(POITermType termAuthor) {
        this.termAuthor = termAuthor;
    }

    public List<POITermType> getLinkURL() {
        return linkURL;
    }

    public  List<String> getLinkTerm() {
        return linkTerm;
    }

    public void setLinkURL(List<POITermType> linkURL) {
        this.linkURL = linkURL;
    }

    public  void setLinkTerm(List<String> linkTerm) {
        POIDataBean.linkTerm = linkTerm;
    }

    public void setLangList(List<String> langList) {
        this.langList = langList;
    }

    public List<String> getLangList() {
        return langList;
    }


    public void addMarker() {
        System.out.println("hello "+lat+"  "+lng);
       // System.out.println(latlngArr);
       addAction();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));
    }
    public void onMarkerDrag(MarkerDragEvent event) {
        Marker marker2 = event.getMarker();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + marker2.getLatlng().getLat() + ", Lng:" + marker2.getLatlng().getLng()));
    }

    public String addAction() {

        latArr.add(this.lat);
        lngArr.add(this.lng);
        return null;
    }

    public void handleFileUpload(FileUploadEvent event) {
        System.out.println("True");
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        try {
          //  String tomcatDir = System.getProperty("catalina.home");
            File pathFile=new File(System.getProperty("user.home")+"/Tourism");
            if(!pathFile.exists())
                pathFile.mkdir();

            //C:\\com\Turism\images\Egypt\Restaurants\Spectra.jpg//save
//            String absolutePath = System.getProperty("user.home");
//            absolutePath+=poiData.toString();
            File f=new File(System.getProperty("user.home")+"/Tourism/Assets/");
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

    private static URI getBaseURI() {

        return UriBuilder.fromUri("http://localhost:8088").build();

    }

    public List<String> completeText(String query) {
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < catId.size(); i++) {
            if(catId.get(i).contains(query))
                results.add(catId.get(i));
        }

        return results;
    }

    public void insertPoi() {
        FacesContext context = FacesContext.getCurrentInstance();
        POITermType p = new POITermType();

//call the splitter class to parse the input of markers and polygon
        PolygonSplitter ps=new PolygonSplitter();
        String pos=ps.setPolgon(latlngArr);
       List<String> lines= ps.setPolyLine(latlngLine);


        // for test setting the polygon
        citysdk.tourism.client.poi.base.Polygon pol=new citysdk.tourism.client.poi.base.Polygon();
        Geometry gem=new Geometry();
       gem.setPosList(pos);
        pol.setSimplePolygon(gem);
        Location lo=new Location();
        lo.addPolygon(pol);

        // for test seeting the poly line and add it to an array
        List<Line> lineCity=new ArrayList<>();
        Line line=new Line();
        Geometry gline=new Geometry();
        for (int i = 0; i < lines.size(); i++) {
            gline.setPosList(lines.get(i));
            line.setLineString(gline);
            lineCity.add(line);
        }
        lo.setLine(lineCity);


        AllCategories cat=new AllCategories();
        Map <String,String> val=cat.getCategoryValueID();
//        String id=val.get(catSelected);
        String id=cat.getIDFromValue(catSelected);
        System.out.println("id "+id);
        p.setId(id);
        pointofinterest.addCategory(p);

        // after adding map
        Point point = new Point();
        Location l = new Location();
        Geometry g = new Geometry();
        for (int i = 0; i <latArr.size() ; i++) {
            g.setPosList(lngArr.get(i) + " " + latArr.get(i));
            point.setPoint(g);
            pointArr.add(point);
        }
//        g.setPosList(lng + " " + lat);
//        point.setPoint(g);
        lo.setPoint(pointArr);
        System.out.println(baseTypeAdd.getValue());
        lo.setAddress(baseTypeAdd);

        pointofinterest.setLocation(lo);
        encodeString e=new encodeString();
        String y= e.getStringEncoded(label.get(0).getValue());
       label.get(0).setValue(y);
        pointofinterest.addLabel(label.get(0));
        String y1= e.getStringEncoded(description.get(0).getValue());
        description.get(0).setValue(y1);
        pointofinterest.addDescription(description.get(0));
        pointofinterest.setBase(base);
        pointofinterest.setLang(Language);


        termAuthor.setValue("testAuthor");
        termAuthor.setTerm("primary");
        pointofinterest.setAuthor(termAuthor);
//        if(!link.isEmpty()){
//
//            pointofinterest.addLink(linkURL.get(0));
//        }
        pointofinterest.setCreated(new Date());
        pointofinterest.setDeleted(null);
        insertData insert = new insertData();
       String response= insert.insertPointOfInterestJson(pointofinterest);
        if(response.equals("true")){
            context.addMessage(null, new FacesMessage("Successful Data Inserted"));
        }
        else{
            context.addMessage(null, new FacesMessage("Error !!" + response));
        }
    }

}
