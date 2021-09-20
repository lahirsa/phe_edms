package com.action;

public class DataUser {
    public DataUser(String p0, String p1, String p2, String p3) {
        super();
        this.id = p0;
        this.username = p1;
        this.contactRole = p2;
        this.contactEmail = p3;
    }
    
    private String username;
    private String contactRole;
    private String contactEmail;
    private String id;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setContactRole(String contactRole) {
        this.contactRole = contactRole;
    }

    public String getContactRole() {
        return contactRole;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
