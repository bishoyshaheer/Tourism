package com.iti.jets.tourism.common;

import java.io.UnsupportedEncodingException;
//import java.util.Base64;
import org.apache.commons.codec.binary.Base64;



/**
 * Created by Marwa on 4/28/2015.
 */
public class encodeString {

    public String getStringEncoded(String str){
        byte[]   bytesEncoded = new byte[0];
        try {
            //bytesEncoded = Base64.getEncoder().encode(str.getBytes("UTF-8"));
            bytesEncoded = Base64.encodeBase64(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
      //  System.out.println("ecncoded value is " + new String(bytesEncoded ));
        return new String(bytesEncoded );
    }

    public String getStringDecoded(String str){
      //  byte[] valueDecoded= Base64.getDecoder().decode(str);

        byte[] valueDecoded= Base64.decodeBase64(str.getBytes());
        String decode = null;
        try {
            decode=new String(valueDecoded,"UTF-8");
           // System.out.println("Decoded value is " + new String(valueDecoded,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decode;
    }
}
