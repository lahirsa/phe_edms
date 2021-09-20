package com.utils;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class SessionClass {
    public SessionClass() {
        super();
    }
    
    public void setSessionParameter(String name, Object value) {  
         HttpServletRequest request =  
           (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
         HttpSession session = request.getSession(false);
         session.setAttribute(name, value);  
       }  
    public Object getSessionParameter(String name) {  
         HttpServletRequest request =  
           (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
         HttpSession session = request.getSession(false);  
         return session.getAttribute(name);  
       }  
    
    public String getParameter(String name) {  
         HttpServletRequest request =  
           (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
         String rsParam= request.getParameter(name);  
         return rsParam;  
       }  
    
    //add by nanda 170315
    public String getHeader(String name) {  
           ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
           String userAgent = externalContext.getRequestHeaderMap().get(name);
         //String rsParam= request.getParameter(name);  
         return userAgent;  
       }
    
    
}
