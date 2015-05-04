package com.iti.jets.tourism.poi.bean;

import com.iti.jets.tourism.common.encodeString;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Marwa on 4/28/2015.
 */
@FacesValidator("str")
public class ValidateString implements Validator {
    private static final String str_PATTERN ="^[A-Za-z, ]++$";
//    "[\\pL&&\\p{L1}]+"
// "[\u0600-\u06ff]|[\u0750-\u077f]|[\ufb50-\ufc3f]|[\ufe70-\ufefc]");
  //|\p{InArabic}+"
   // "^[A-Za-z, ]++$"
    private Pattern pattern;
    private Matcher matcher;
    private  Pattern p;
    private Matcher m;
    public ValidateString(){
        pattern = Pattern.compile(str_PATTERN);
        p = Pattern.compile("[\\u0600-\\u06ff]");
//        p = Pattern.compile("[^\\p{InArabic}a-zA-Z0-9]+");
    }
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {

//        matcher = pattern.matcher(o.toString());
//        String s=new encodeString().getStringEncoded(o.toString());
//         m = p.matcher(s.toString());
//        if (!matcher.matches()) {
//            FacesMessage msg
//                    = new FacesMessage("Name validation failed.",
//                    "Invalid name ");
//            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//            throw new ValidatorException(msg);
//        }
    }
}
