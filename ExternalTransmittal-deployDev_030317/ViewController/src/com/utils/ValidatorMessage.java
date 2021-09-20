package com.utils;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class ValidatorMessage implements Serializable, Validator {
    public ValidatorMessage() {
        super();
    }

    public void validate(FacesContext facesContext, UIComponent uiComponent,
                         Object object) {
        System.out.println("");
        if(object == null){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN,
                                                                      "Please fill this field",
                                                                      null));
        }
        
    }
}
