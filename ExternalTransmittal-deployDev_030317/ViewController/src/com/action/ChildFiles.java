package com.action;

public class ChildFiles {
    public ChildFiles(String fFileGUID,String did, String dDocName, String dFormat, String xDocNumber,
                      String dDocTitle, String xDocName, String xDocOwner,String originalName) {
        super();
        this.fFileGUID = fFileGUID;
        this.dID = did;
        this.dDocName = dDocName;
        this.dFormat = dFormat;
        this.xDocNumber = xDocNumber;
        this.dDocTitle = dDocTitle;
        this.xDocName = xDocName;
        this.xDocOwner = xDocOwner;
        this.originalName = originalName;
    }
    
    private String fFileGUID;
    private String dID;
    private String dDocName;
    private String dFormat;
    private String xDocNumber;
    private String dDocTitle;
    private String xDocName;
    private String xDocOwner;
    private String originalName;

    public void setDID(String dID) {
        this.dID = dID;
    }

    public String getDID() {
        return dID;
    }

    public void setDDocName(String dDocName) {
        this.dDocName = dDocName;
    }

    public String getDDocName() {
        return dDocName;
    }

    public void setDFormat(String dFormat) {
        this.dFormat = dFormat;
    }

    public String getDFormat() {
        return dFormat;
    }

    public void setXDocNumber(String xDocNumber) {
        this.xDocNumber = xDocNumber;
    }

    public String getXDocNumber() {
        return xDocNumber;
    }

    public void setDDocTitle(String dDocTitle) {
        this.dDocTitle = dDocTitle;
    }

    public String getDDocTitle() {
        return dDocTitle;
    }

    public void setXDocName(String xDocName) {
        this.xDocName = xDocName;
    }

    public String getXDocName() {
        return xDocName;
    }

    public void setXDocOwner(String xDocOwner) {
        this.xDocOwner = xDocOwner;
    }

    public String getXDocOwner() {
        return xDocOwner;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setFFileGUID(String fFileGUID) {
        this.fFileGUID = fFileGUID;
    }

    public String getFFileGUID() {
        return fFileGUID;
    }
}
