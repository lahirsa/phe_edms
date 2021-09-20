package com.action;

public class TempDocuments {
    public TempDocuments(String did,String dDocname, String docType, String docFormat,String documentName, String docNumber,
                         String docTitle, String revision, String docStatus, String pages, String remarks,
                         String distribution) {
        super();
        this.did = did;
        this.dDocname = dDocname;
        this.docType = docType;
        this.docFormat = docFormat;
        this.documentName = documentName;
        this.documentNumber = docNumber;
        this.documentTitle = docTitle;
        this.revision = revision;
        this.documentStatus = docStatus;
        this.pages = pages;
        this.remarks = remarks;
        this.distribution = distribution;
    }
    
    public String did;
    public String dDocname;
    public String docType;
    public String docFormat;
    public String documentName;
    public String documentNumber;
    public String documentTitle;
    public String revision;
    public String documentStatus;
    public String pages;
    public String remarks;
    public String distribution;

    public void setDid(String did) {
        this.did = did;
    }

    public String getDid() {
        return did;
    }

    public void setDDocname(String dDocname) {
        this.dDocname = dDocname;
    }

    public String getDDocname() {
        return dDocname;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocFormat(String docFormat) {
        this.docFormat = docFormat;
    }

    public String getDocFormat() {
        return docFormat;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getRevision() {
        return revision;
    }

    public void setDocumentStatus(String documentStatus) {
        this.documentStatus = documentStatus;
    }

    public String getDocumentStatus() {
        return documentStatus;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPages() {
        return pages;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentName() {
        return documentName;
    }
}
