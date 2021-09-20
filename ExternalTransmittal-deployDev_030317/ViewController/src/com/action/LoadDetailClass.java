package com.action;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.http.HttpServletRequest;

import javax.sql.DataSource;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;


public class LoadDetailClass {
    private String isInternal;
    private Statement stmt;
    private Connection conn;
    private RichTable bindExternalSupportingDoc;

    public LoadDetailClass() {
        LoadTransmittal();
        BindingContext bindingctx = BindingContext.getCurrent();
        BindingContainer bindings = bindingctx.getCurrentBindingsEntry();
//        OperationBinding method = bindings.getOperationBinding("ExecuteExternalSupportingDoc");
//        Map paramsMap = method.getParamsMap();
//        paramsMap.put("transmittal_id", AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId"));
//        method.execute();
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindExternalSupportingDoc);
    }

    public void LoadTransmittal(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
      
      //edit by nanda 170216 - ganti cara ambil param
        String transmittalid =request.getParameter("transmittalId").toString();//"PHEONWJ-ID-202192-0005";//
      String duser =request.getParameter("dUser").toString();//"user_hsse";//"owc.admin";//
      String istdc=request.getParameter("isTdc").toString();//"true";//
//      System.out.println("--transmittalId:"+transmittalid);
//      System.out.println("--duser:"+duser);
        
        AdfFacesContext.getCurrentInstance().getPageFlowScope().put("transmittalId", transmittalid);
        AdfFacesContext.getCurrentInstance().getPageFlowScope().put("dUser", duser);
        try{
//          String istdc = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("isTdc");
          AdfFacesContext.getCurrentInstance().getPageFlowScope().put("isTdc", istdc);    
        }catch(Exception e){
        }
        
        InitialContext inc;
        String type = "";
        try {
            if(duser.isEmpty())
                System.out.print("edms:dismiss");
            else{
                inc = new InitialContext();
                DataSource ds = (DataSource)inc.lookup("jdbc/EDMSConn");
                conn = ds.getConnection();
                this.stmt = this.conn.createStatement();
                String query = "select transmittal_type from phe_dswf_master where transmittal_id = '"+transmittalid+"'";
                ResultSet rs = this.stmt.executeQuery(query);
                while(rs.next()){
                    type = rs.getString("transmittal_type").toString();
                    AdfFacesContext.getCurrentInstance().getPageFlowScope().put("transmittalType", type);
                }
                if(!this.stmt.isClosed())
                    this.stmt.close();
                if(!this.conn.isClosed())
                    this.conn.close();
                
            }
        } catch (NamingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        setIsInternal(type);
    }

    public void setIsInternal(String isInternal) {
        this.isInternal = isInternal;
    }

    public String getIsInternal() {
        return isInternal;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Connection getConn() {
        return conn;
    }

    public void setBindExternalSupportingDoc(RichTable bindExternalSupportingDoc) {
        this.bindExternalSupportingDoc = bindExternalSupportingDoc;
    }

    public RichTable getBindExternalSupportingDoc() {
        return bindExternalSupportingDoc;
    }

    public void ExternalCloseScreen(ActionEvent actionEvent) {
        // Add event code here...
    }
}
