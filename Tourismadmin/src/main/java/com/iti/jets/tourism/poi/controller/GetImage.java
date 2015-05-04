package com.iti.jets.tourism.poi.controller;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.awt.*;
import java.net.URL;

/**
 * Created by Marwa on 4/26/2015.
 */
@Path("/data")
@Produces("image/png")
public class GetImage {

    @GET
    @Path("/{imagename}")
    public Image readImage(@PathParam("imagename") String imageName){
        //     URL url2 = new URL(imageUrl);
//        Image image = ImageIO.read(urlhttp://localhost:8088);
//        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(bufferedImage, "jpg", baos);
//        baos.flush();
//        byte[] imageInByte = baos.toByteArray();
//        baos.close();
//
//        BASE64Encoder encoder = new BASE64Encoder();
//        String imageString = encoder.encode(imageInByte);

        URL url = this.getClass().getResource(System.getProperty("user.home")+"/Tourism/"+imageName);
        Image img = java.awt.Toolkit.getDefaultToolkit().createImage(url);

        return img;




    }
}
