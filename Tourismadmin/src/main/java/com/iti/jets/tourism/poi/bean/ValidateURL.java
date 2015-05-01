package com.iti.jets.tourism.poi.bean;

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
@FacesValidator("url")
public class ValidateURL implements Validator{

    private static final String URL_PATTERN = "(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?";

    private Pattern pattern;
    private Matcher matcher;

    public ValidateURL(){
        pattern = Pattern.compile(URL_PATTERN);
    }
//    public boolean validateMail(String mail){
//        Pattern p = Pattern.compile();
//        Matcher m;
//        m=p.matcher(mail);
//    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        matcher = pattern.matcher(o.toString());
        if (!matcher.matches()&& !o.equals("") && !o.equals(null)) {
            FacesMessage msg
                    = new FacesMessage("the URL must not formatted well",
                    "Invalid URL format.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }
}
