package com.action;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.context.AdfFacesContext;


public class detailTransmittalClass {
    private RichTable bindExternalSupportingDoc;

    public detailTransmittalClass() {
        System.out.println(AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString());
    }

    public void CloseScreen(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void setBindExternalSupportingDoc(RichTable bindExternalSupportingDoc) {
        this.bindExternalSupportingDoc = bindExternalSupportingDoc;
    }

    public RichTable getBindExternalSupportingDoc() {
        return bindExternalSupportingDoc;
    }
}
