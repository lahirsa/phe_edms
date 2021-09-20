package com.utils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class TransmittalValidator implements Validator {
    public TransmittalValidator() {
        super();
    }

    public void validate(FacesContext facesContext, UIComponent uIComponent,
                         Object object) throws ValidatorException {
        String wonumber = object.toString();
        if (wonumber.contains("#")||wonumber.contains("&")||wonumber.contains("?")) {
        FacesMessage fm =
        new FacesMessage("WO Number should not contain '#','&' and/or '?'");
        throw new ValidatorException(fm);
        }
    }
}
