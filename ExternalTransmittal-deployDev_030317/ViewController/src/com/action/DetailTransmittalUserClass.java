package com.action;


import com.utils.ADFUtils;

import com.utils.JSFUtils;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.sql.DataSource;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelLabelAndMessage;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Key;
import oracle.jbo.NameValuePairs;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;

import oracle.stellent.ridc.model.DataObject;

import org.apache.myfaces.trinidad.model.UploadedFile;

import org.apache.myfaces.trinidad.util.ComponentReference;

import transmittalExternal.vo.PheDelegateViewImpl;


public class DetailTransmittalUserClass {
    private String currentAddress;
    private RichSelectOneChoice bindSOCAction;
    private List<tempAction> tampActin = new ArrayList<tempAction>();
    private RichPanelLabelAndMessage bindComment;
    private RichInputFile bindUploadedSubmittalFile;
    //    private RichInputText bindSubmittalName;
    //    private RichInputText bindSubmittalDocNumber;
    //    private RichInputText bindSubmittalDocTitle;
//    private RichInputText bindSubmittalPages;
    private RichInputText bindUserSearch;
    private RichInputText bindSubmittalRemarks;
//    private RichInputText bindSubmittalRev;
    private RichInputText bindSubmittalDocType;
    private ComponentReference bindSubmittalName;
    private ComponentReference bindSubmittalDocNumber;
    private ComponentReference bindSubmittalDocTitle;
    private ComponentReference bindSubmittalPages;
    private ComponentReference bindSubmittalRev;
    

    private RichSelectOneChoice bindSubmittalDocStatus;
   
    private UploadedFile file;
    private long fileLength;
    private InputStream fileInputStream;
    private String fileName;
    private String fileContentType;
    private Statement stmt;
    private Connection conn;
    private String transmittalGuid;
    private String documentsGuid;
    private String submittalGuid;
    private RichTable bindtableSubmittal;
    private RichTable bindTableSupporting;
    private RichPopup bindPopUpSuccess;
    private String urmurl;
    private RichTable bindTableInternalUser;
    
    private boolean IsDelegate;
    private boolean IsDisplayDelegateHistory;
    private RichTable bindTableDelegateHistory;
    private RichButton btnDelegate;
    private RichButton btnAbortDelegate;
    private RichButton btnFinish;
    private RichShowDetailItem panelTabDelegate;
    private RichButton btnSaveAsDraft;
    private RichButton btnAddSubmittal;
    {
        bindUserSearch = new RichInputText();
        bindUserSearch.setValue("");
    }
    private String isPublish;
    private RichTable bindTableSupportingDelegate;
    private String SedangDelegate;
//    private RichOutputText bindSubject;
    private ComponentReference bindSubject;
    private Boolean isSentItem;
  private RichPopup bindPopupValidateFinish;
    private String CompanyApName;
    private String Userlogin;
    private String Password;
    private String Transmittalid;
    private RichPopup bindPopupConfirmasi;

    public DetailTransmittalUserClass() {
        getParamPath();
        System.out.println("isSentItem "+isSentItem);
        setSedangDelegate(ExeIsDelegate());
        setUrmurl(GetConfig("RIDC_URL"));
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest request =
            (HttpServletRequest)ctx.getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        //        GetSession().setAttribute("username", "mk.acep.karmana");
        //        GetSession().setAttribute("password", "welcome1");
        Userlogin =request.getParameter("dUser");//"owc.admin";//"iwansyah""weblogic";//;"user_hsse";//
        CompanyApName=request.getParameter("companyap");//"PHE ONWJ";//"PHE NSB";//
        Transmittalid=request.getParameter("transmittalId");//"PHEONWJ-ID-2021824-0002";//
        
        ADFContext adfCtx = ADFContext.getCurrent();
        Map pageFlowScope = adfCtx.getPageFlowScope();
        Object val = pageFlowScope.put("transmittalId2", Transmittalid);
        Object val2 = pageFlowScope.put("transmittalId", Transmittalid);
        
        System.out.println("transmittal xxxx "+AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString());
        setPassword(getValueInDB("AppModuleExternalTransmittalDataControl",
                                         "usersDcrmsVO1",
                                         "U_NAME = '"+Userlogin+"'",
                                         "UPassword"));
        System.out.println(getPassword());
        bindSubmittalRemarks = new RichInputText();
        bindSubmittalRemarks.setValue("");
        ApplicationModule app =
            GetApplicationModule("AppModuleExternalTransmittalDataControl");
        ViewObject VoDocProc =
            app.findViewObject("DetailInteralSupportingDocForUser1");
        //        VoDocProc.setWhereClause("receiver = '"+AdfFacesContext.getCurrentInstance().getPageFlowScope().get("dUser").toString()+"'");
        VoDocProc.setWhereClause("receiver = '" +
                                 Userlogin +
                                 //session.getAttribute("username").toString() +
                                 "'");
        VoDocProc.executeQuery();
        tampActin.clear();
        while (VoDocProc.hasNext()) {
            Row row = VoDocProc.next();
            if (row.getAttribute("Action") != null) {
                if (row.getAttribute("Notes") == null) {
                    tampActin.add(new tempAction(row.getAttribute("UniqueId").toString(),
                                                 row.getAttribute("Action").toString()));
                } else {
                    tampActin.add(new tempAction(row.getAttribute("UniqueId").toString(),
                                                 row.getAttribute("Action").toString(),
                                                 row.getAttribute("Notes").toString()));
                }
            }
        }
        if (!AdfFacesContext.getCurrentInstance().isPostback()) {
            isIsDelegate();
            isIsDisplayDelegateHistory();
        }
        setIsPublish("No");
        System.out.println("TRANSMITTAL_ID "+AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId2").toString().trim());
        ViewObject voPublish = app.findViewObject("PheDswfMasterVO1");
        voPublish.setWhereClause("TRANSMITTAL_ID = '"//+Transmittalid+// +"PHEONWJ-ID-2021824-0002"+
                              +AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId2").toString().trim()+
                                 "'");
        System.out.println("query "+voPublish.getQuery());
        voPublish.executeQuery();
        while (voPublish.hasNext()) {
            Row row = voPublish.next();
            setIsPublish(row.getAttribute("ProjectorganizationName").toString());
        }
           if (isSentItem == true) {
            System.out.println("Sent Item");
        } else {
            System.out.println("Inbox Item");
        } 
    }
    
    //add by nanda 17022016
  public void GoToInbox(ActionEvent actionEvent) {
      FacesContext ctx = FacesContext.getCurrentInstance();
      try {
              ctx.getExternalContext().redirect(GetConfig("RIDC_URL") +
                                                "?IdcService=PHE_REDIRECT_INBOX_DSWF&fromCreate=1");
         
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  
    public String getValueInDB(String module, String datacontrolVO,
                               String whereClause, String getAttributeName) {
        ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl(module);
        ViewObject svname = am.findViewObject(datacontrolVO);
        svname.setWhereClause(whereClause);
        svname.executeQuery();

        String value = "";
        Row row = null;
        String first = "";
        try {
            row = svname.first();
            value = row.getAttribute(getAttributeName).toString();
        } catch (Exception e) {
        }
        while (svname.hasNext()) {
            if (first.equals("")) {
                row = svname.first();
                first = "1";
            } else {
                row = svname.next();
            }
            value = row.getAttribute(getAttributeName).toString();

        }
        //System.out.print("\nvalue = " + value);
        // log.log(idrequest, "getValueInDB", value);
        return value;
    }
    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String GetConfig(String param) {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding obGetConfig =
            bindings.getOperationBinding("ExecuteGetConfigWithParams");
        obGetConfig.getParamsMap().put("keyConfig", param);
        AttributeBinding abConfigValue =
            (AttributeBinding)bindings.get("KeyValue");
        obGetConfig.execute();
        return abConfigValue.getInputValue().toString();
    }

    public String ExeIsDelegate() {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding obGetConfig =
            bindings.getOperationBinding("ExecuteIsDelegate");
        obGetConfig.getParamsMap().put("transmittalId",//Transmittalid);//"PHEONWJ-ID-20161020-0001");
                                     AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId2").toString().trim());
        obGetConfig.getParamsMap().put("delegateTo", Userlogin);//"owc.admin");//
                                      // ADFContext.getCurrent().getSecurityContext().getUserName());
        AttributeBinding abConfigValue =
            (AttributeBinding)bindings.get("isDelegate");
        obGetConfig.execute();
        if (abConfigValue.getInputValue() != null)
            return abConfigValue.getInputValue().toString();
        else
            return "";
    }

    public String getCurrentAddress() {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding obGetConfig =
            bindings.getOperationBinding("ExecuteGetConfigWithParams");
        obGetConfig.getParamsMap().put("keyConfig", "AUTOVUE_URL");
        obGetConfig.execute();
        AttributeBinding abConfigValue =
            (AttributeBinding)bindings.get("KeyValue");
        this.currentAddress = abConfigValue.getInputValue().toString();
        return currentAddress;
    }

    public String IsRoleExist(String username, String role) {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding obGetConfig =
            bindings.getOperationBinding("ExecuteIsRoleExist");
        obGetConfig.getParamsMap().put("role", role);
        obGetConfig.getParamsMap().put("name", username);
        AttributeBinding Isroleexist =
            (AttributeBinding)bindings.get("Isroleexist");
        obGetConfig.execute();
        if (Isroleexist.getInputValue() != null)
            return Isroleexist.getInputValue().toString();
        else
            return "";
    }

    public void PopUpFetchDistributionHistory(PopupFetchEvent popupFetchEvent) {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding obHistoryNumber =
            bindings.getOperationBinding("ExecuteHistoryDocNumber");
        DCIteratorBinding supportingDocIterator =
            (DCIteratorBinding)bindings.get("DetailInteralSupportingDocForUser1Iterator");
        Row supportingDocCurrentRow = supportingDocIterator.getCurrentRow();
        obHistoryNumber.getParamsMap().put("docNumber",
                                           supportingDocCurrentRow.getAttribute("DocNumber"));
        obHistoryNumber.execute();
    }

    public void getParamPath() {
        InitialContext inc;
        //        HttpServletRequest request =
//            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            inc = new InitialContext();
            DataSource ds = (DataSource)inc.lookup("jdbc/EDMSConn");
            conn = ds.getConnection();
            this.stmt = this.conn.createStatement();
          
//          edit by nanda 170216 - ganti cara getParam
          String transmittalid="PHEONWJ-ID-20161020-0001";// (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("transmittalId");
          // System.out.println("--transmittalId2:"+AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId2").toString());
          // System.out.println("--transmittalId:"+request.getParameter("transmittalId").toString());
          String transmittalId2 =AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId2").toString().trim();
            System.out.println("transmittalId2 "+transmittalId2);
            String query =
                "select ffolderguid,fparentguid from folderfolders where ffoldername = '" +transmittalId2
                + "'";
//          end edit
        
            ResultSet rs = this.stmt.executeQuery(query);
            String temp = "";
            while (rs.next()) {
                transmittalGuid = rs.getString("ffolderguid").toString();
                temp = rs.getString("fparentguid").toString();
            }
            System.out.println("temp "+temp);
            query =
                    "select ffolderguid from folderfolders where fparentguid = '" +
                    temp + "' and ffoldername = 'Documents'";
            rs = this.stmt.executeQuery(query);
            while (rs.next()) {
                documentsGuid = rs.getString("ffolderguid").toString();
            }
            query =
                    "select ffolderguid from folderfolders where fparentguid = '" +
                    transmittalGuid + "' and ffoldername = 'Submittal'";
            rs = this.stmt.executeQuery(query);
            while (rs.next()) {
                submittalGuid = rs.getString("ffolderguid").toString();
            }
            //set isSentItem
//            HttpSession session = request.getSession(true);
//            query =
//          "select enddate from phe_dswf_participant where transmittal_id = 'PHEONWJ-ID-2016211-0001' and receiver = '" +
//                              session.getAttribute("username").toString() +
//                              "' and enddate is not null";
              query=
                    "select enddate from phe_dswf_participant where transmittal_id = '" +
                    AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId2").toString().trim()+
                   // transmittalId2 +
                    "' and receiver = '" +
                    Userlogin +
                    //session.getAttribute("username").toString() +
                    "' and enddate is not null";
            rs = this.stmt.executeQuery(query);
            setIsSentItem(false);
            while (rs.next()) {
                setIsSentItem(true);
            }
            if (!this.stmt.isClosed())
                this.stmt.close();
            if (!this.conn.isClosed())
                this.conn.close();

        } catch (NamingException e) {
//            setIsSentItem(false);
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void setBindSOCAction(RichSelectOneChoice bindSOCAction) {
        this.bindSOCAction = bindSOCAction;
    }

    public RichSelectOneChoice getBindSOCAction() {
        return bindSOCAction;
    }

    public void ValueLsnrAction(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null 
//            || valueChangeEvent.getNewValue() != "0"
            ) {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            Map p =
                ((UIComponent)valueChangeEvent.getSource()).getAttributes();
            int tampEdit = -1;
            for (int i = 0; i < tampActin.size(); i++) {
                if (tampActin.get(i).getRowId().equalsIgnoreCase(p.get("rowvalIndex").toString())) {
                    tampEdit = i;
                }
            }
            if (tampEdit != -1) {
                if (valueChangeEvent.getNewValue() != null) {
                    tampActin.get(tampEdit).setAction(valueChangeEvent.getNewValue().toString());
                    tampActin.get(tampEdit).setComment(null);
                }
            } else {
                tampActin.add(new tempAction(p.get("rowvalIndex").toString(),
                                             valueChangeEvent.getNewValue().toString()));
            }
            if (valueChangeEvent.getNewValue() != null) {
                if (valueChangeEvent.getNewValue().toString().equals("2")) {
                    bindComment.setVisible(true);
                } else {
                    bindComment.setVisible(false);
                }
            }

            AdfFacesContext.getCurrentInstance().addPartialTarget(bindComment);
        }
    }

    public ApplicationModule GetApplicationModule(String appName) {
        return ADFUtils.getApplicationModuleForDataControl(appName);
    }

    public oracle.jbo.domain.Date getNowDate(java.util.Date pJavaDate) {
        return new oracle.jbo.domain.Date(new Timestamp(pJavaDate.getTime()));
    }

    public void Finish(ActionEvent actionEvent) {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        bindPopupConfirmasi.show(hints);
        /* System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:begin");
        oracle.jbo.domain.Date nowDate = getNowDate(new Date());
        ApplicationModule app =
            GetApplicationModule("AppModuleExternalTransmittalDataControl");
        BindingContext bindingctx = BindingContext.getCurrent();
        System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:bindingctx:"+bindingctx);
        BindingContainer bindings = bindingctx.getCurrentBindingsEntry();
        System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:bindings:"+bindings);
        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        Boolean isValidFinish=true;
        Boolean isInformationOnly=false;
        Boolean isEmptyAction=false;
        String roleRecipient="";
        String transmittalID =//"PHEONWJ-ID-20161020-0001";
            AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString();
        System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:transmittalID"+transmittalID);
        System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:TryCatchBefore");
        
        
        try {
            System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:TryCatchBegin");
          //nanda 050416
          ViewObject myVoPar =
              app.findViewObject("PheDswfParticipantVO1_1");
            System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:myVoPar:"+myVoPar);
          //            String query = "TRANSMITTAL_ID = '"+AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString()+"' and receiver = '"+AdfFacesContext.getCurrentInstance().getPageFlowScope().get("dUser").toString()+"'";
          String query =
              "TRANSMITTAL_ID = '" + AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString() +
              "' and receiver = '" +
                Userlogin+ "'";
             // GetSession().getAttribute("username").toString() + "'";
            System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:query:"+query);
          myVoPar.setWhereClause(query);
          myVoPar.executeQuery();
            System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:query:executeQuery");
          while (myVoPar.hasNext()) {
            Row rows = myVoPar.next();
            roleRecipient= rows.getAttribute("Role").toString();
            System.out.println("roleRecipient:"+roleRecipient);
          }

            String sender = "";
            if (SedangDelegate.length() > 0) {
                ViewObject delegate =
                    app.findViewObject("PheDelegateDavidVO1");
                delegate.setWhereClause("transmittal_id = '" + transmittalID +
                                        "' and id = '" + SedangDelegate + "'");
                delegate.executeQuery();
                System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:SedangDelegate:SedangDelegate");
                while (delegate.hasNext()) {
                    Row row = delegate.next();
                    row.setAttribute("EndDate", nowDate);
                    row.setAttribute("StatusDesc", "Completed");
                    row.setAttribute("StatusCode", "1");
                    System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:SedangDelegate:row:"+row);
                }
            } else {
                System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:VoDocProcDel");
                ViewObject VoDocProcDel =
                    app.findViewObject("PheDocProcessVO1");
                VoDocProcDel.setWhereClause("transmittal_id = '" +
                                            transmittalID +
                                            "' and recipient = '" +
                                            Userlogin +
                                            //ADFContext.getCurrent().getSecurityContext().getUserName() +
                                            "'");
                VoDocProcDel.executeQuery();
                System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:VoDocProcDel:executeQuery");
                while (VoDocProcDel.hasNext()) {
                    Row row = VoDocProcDel.next();
                    System.out.println(row.getAttribute("Did") + " " +
                                       row.getAttribute("Recipient")+" "+ row.getAttribute("DocStatus"));
                  if(row.getAttribute("DocStatus").toString().equalsIgnoreCase("IFI")){
                  isInformationOnly = true;
                  }
                    row.remove();
                }

                ViewObject myVo =
                    app.findViewObject("DetailInteralSupportingDocForUser1");
                myVo.executeQuery();
                System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:myVo:executeQuery");
                ViewObject VoDocProc = app.findViewObject("PheDocProcessVO1");
                for (int i = 0; i < tampActin.size(); i++) {
                    System.out.println(tampActin.get(i).getRowId() + " -action-" +
                                       tampActin.get(i).getAction() + " " +
                                       tampActin.get(i).getComment());
                  String action = tampActin.get(i).getAction().toString();
                  //String docstatus = tampActin.get(i).getDocStatus().toString();
                   
                   //add by nanda 04032016 - validasi finish
                  System.out.println("nilai action string : "+action);
                  
                  if (action.equalsIgnoreCase("0") || action.contains("0")){
                    isEmptyAction=true;
                  }
                  System.out.println("nilai action isEmptyAction:"+isEmptyAction);
          System.out.println("nilai action isInformationOnly:"+isInformationOnly);
         // nanda 050416 - ngecek role recipient untuk validasi finish
    //          if(!roleRecipient.equalsIgnoreCase("Reviewer/Approver")){
    //            isValidFinish = true;
    //            System.out.println("--isValidFinish:BukanReviewer:true:"+isValidFinish);
    //          }
          if (roleRecipient.equalsIgnoreCase("Reviewer/Approver")&&isEmptyAction==true&&isInformationOnly==false) {
                  isValidFinish = false;
                  System.out.println("--isValidFinish:harusnyafalse:"+isValidFinish);
                } 
         
          else {
                    isValidFinish = true;
                    System.out.println("--isValidFinish:harusnya true:"+isValidFinish);
                      Key keys =
                          new Key(new Object[] { tampActin.get(i).getRowId() });
                      Row[] rows = myVo.findByKey(keys, 1);
                      NameValuePairs nvp = new NameValuePairs();
                      sender = rows[0].getAttribute("Uploader").toString();
                      nvp.setAttribute("TransmittalId",
                                       rows[0].getAttribute("TransmittalId").toString());
                      nvp.setAttribute("Did",
                                       rows[0].getAttribute("Id").toString());
                      nvp.setAttribute("Sender",
                                       rows[0].getAttribute("Uploader").toString());
                      nvp.setAttribute("Recipient",Userlogin);
                                      // GetSession().getAttribute("username").toString());
                      nvp.setAttribute("DocType",
                                       rows[0].getAttribute("DocType").toString());
                      nvp.setAttribute("DocTitle",
                                       rows[0].getAttribute("DocTitle").toString());
                      nvp.setAttribute("Action", tampActin.get(i).getAction());
                      nvp.setAttribute("DocStatus",
                                       rows[0].getAttribute("DocStatus").toString());
                      nvp.setAttribute("Notes", tampActin.get(i).getComment());
                      VoDocProc.createAndInitRow(nvp);
                  }
        }
                System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:isValidFinishBefore");
              if(isValidFinish==true){
                ViewObject myVoPar1 =
                    app.findViewObject("PheDswfParticipantVO1_1");
                System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:myVoPar1");
    //                            String query1 = "TRANSMITTAL_ID = '"+AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString()+"' and receiver = '"+AdfFacesContext.getCurrentInstance().getPageFlowScope().get("dUser").toString()+"'";
                String query1 =
                    "TRANSMITTAL_ID = '" + AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString() +
                    "' and receiver = '" +
                            Userlogin + "'";
                    //GetSession().getAttribute("username").toString() + "'";
                System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:query1:"+query1);
                myVoPar1.setWhereClause(query1);
                myVoPar1.executeQuery();
                System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:query1:executeQuery");
                while (myVoPar1.hasNext()) {
                    Row row = myVoPar.next();
                    row.setAttribute("Enddate", nowDate);
                    System.out.println("update end date participant in dswf_participant in id = " +
                                       row.getAttribute("Id") + " to " +
                                       nowDate);
                    System.out.println(row.getAttribute("Enddate"));
                }
            }
            }
            System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:isValidFinishBefore2");
          if(isValidFinish==true){
            ViewObject voLog = app.findViewObject("PheDswfLog1");
            NameValuePairs nvpLog = new NameValuePairs();
            nvpLog.setAttribute("TransmittalId",
                                AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString());
            //            nvpLog.setAttribute("Username",AdfFacesContext.getCurrentInstance().getPageFlowScope().get("dUser").toString() );
            nvpLog.setAttribute("Username",Userlogin);
                                //GetSession().getAttribute("username").toString());
            nvpLog.setAttribute("Action", "2");
              System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:SedangDelegateBefore");
            if (SedangDelegate.length() > 0) {
                nvpLog.setAttribute("Description", "Finish Delegate");
                System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:Finish Delegate");
            } else {
                nvpLog.setAttribute("Description", "Finish Submittal");
                System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:Finish Submittal");
            }
            voLog.createAndInitRow(nvpLog);

            AttributeBinding subject =
                (AttributeBinding)bindings.get("TransmittalSubject");
            OperationBinding ExecuteGetEmail =
                bindings.getOperationBinding("ExecuteGetEmail");
            try {
                ExecuteGetEmail.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            AttributeBinding emailCreator;
            String emailDelegateFrom = "";
            String isi;
              System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:Email");
            if (SedangDelegate.length() > 0) {
                emailCreator = (AttributeBinding)bindings.get("DelegateFrom");
                emailDelegateFrom =
                        getEmail(emailCreator.getInputValue().toString());
                isi =
    "<html><head></head><body>" + "Dear Sir/Madam,<br/><br/>" +
    "You have received a new response from the following transmittal:<br/><br/>" +
    "<table><tr><td>Transmittal No</td><td>:</td><td style=\"text-align:left\">" +
    transmittalID + "</td></tr>" + "<tr><td>Subject</td><td>:</td><td>" +
    subject.getInputValue().toString() + "</td></tr>" +
    "<tr><td>Recipient</td><td>:</td><td>" +
        Userlogin + "</td></tr>" +
    "</table><br/>Please click <a href=\"" + getUrmurl() +
    "?IdcService=PHE_GET_TRANSMITTAL_DETAIL&transmittalID=" + transmittalID +
    "\">here</a> to review the transmittal." +
    "<br/>Thank you for your attention.</body></html>";
            } else {
                emailCreator = (AttributeBinding)bindings.get("Demail");
                isi =
    "<html><head></head><body>" + "Dear Sir/Madam,<br/><br/>" +
    "You have received a new response from the following transmittal:<br/><br/>" +
    "<table><tr><td>Transmittal No</td><td>:</td><td style=\"text-align:left\">" +
    transmittalID + "</td></tr>" + "<tr><td>Subject</td><td>:</td><td>" +
    subject.getInputValue().toString() + "</td></tr>" +
    "<tr><td>Recipient</td><td>:</td><td>" +
        Userlogin + "</td></tr>" +
    "</table><br/>Please click <a href=\"" + getUrmurl() +
    "?IdcService=PHE_GET_TRANSMITTAL_SUMMARY&transmittalId=" + transmittalID +
    "&dUser=" + sender + "\">here</a> to review the transmittal." +
    "<br/>Thank you for your attention.</body></html>";
            }

            OperationBinding sendEmail =
                bindings.getOperationBinding("SendEmail");
            Map paramEmail = sendEmail.getParamsMap();
              System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:sendEmail");
            if (emailCreator.getInputValue() != null) {
                paramEmail.put("EmailTo",
                               emailCreator.getInputValue().toString());
            } else {
                paramEmail.put("EmailTo", "owc.support@pheonwj.pertamina.com");
            }
            if (SedangDelegate.length() > 0) {
                paramEmail.put("EmailTo", emailDelegateFrom);
                paramEmail.put("Subject",
                               "["+CompanyApName+"] Delegate Response for Transmittal  - " +
                               transmittalID);
            } else {
                paramEmail.put("Subject",
                               "["+CompanyApName+"] New Submittal for Transmittal  - " +
                               transmittalID);
            }

            paramEmail.put("HtmlText", isi);
            try {
                sendEmail.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }

            app.getTransaction().commit();

            bindPopUpSuccess.show(ph);
          }
          else{
            bindPopupValidateFinish.show(ph);
          }
            System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:FinishProses");
        } catch (Exception e) {
            System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:ErrorCacth");
            e.printStackTrace();
            app.getTransaction().rollback();
        } */
    }

    public void setTampActin(List<tempAction> tampActin) {
        this.tampActin = tampActin;
    }

    public List<tempAction> getTampActin() {
        return tampActin;
    }

    public void VlcsrComment(ValueChangeEvent valueChangeEvent) {

        Map p = ((UIComponent)valueChangeEvent.getSource()).getAttributes();
        Integer.valueOf(p.get("rowCommentIndex").toString());
        int tampEdit = -1;
        for (int i = 0; i < tampActin.size(); i++) {
            if (tampActin.get(i).getRowId().equalsIgnoreCase(p.get("rowCommentIndex").toString())) {
                tampEdit = i;
            }
        }
        if (tampEdit != -1) {
            tampActin.get(tampEdit).setComment(valueChangeEvent.getNewValue().toString());
        }

        //        try{
        //            tampActin.get(Integer.valueOf(p.get("rowCommentIndex").toString())).setComment(valueChangeEvent.getNewValue().toString());
        ////            System.out.println("try");
        //        }catch(Exception e){
        //            System.out.println(e.getMessage());
        //        }
    }

    public void setBindComment(RichPanelLabelAndMessage bindComment) {
        this.bindComment = bindComment;
    }

    public RichPanelLabelAndMessage getBindComment() {
        return bindComment;
    }

    public void setBindUploadedSubmittalFile(RichInputFile bindUploadedSubmittalFile) {
        this.bindUploadedSubmittalFile = bindUploadedSubmittalFile;
    }

    public RichInputFile getBindUploadedSubmittalFile() {
        return bindUploadedSubmittalFile;
    }

    public RichInputText getBindSubmittalName() {
        if (bindSubmittalName != null) {
            return (RichInputText)bindSubmittalName.getComponent();
        }
        return null;
    }

    public void setBindSubmittalName(RichInputText bindSubmittalName) {
        this.bindSubmittalName =
                ComponentReference.newUIComponentReference(bindSubmittalName);
    }

    public RichInputText getBindSubmittalDocNumber() {
        if (bindSubmittalDocNumber != null) {
            return (RichInputText)bindSubmittalDocNumber.getComponent();
        }
        return null;
    }

    public void setBindSubmittalDocNumber(RichInputText bindSubmittalDocNumber) {
        this.bindSubmittalDocNumber =
                ComponentReference.newUIComponentReference(bindSubmittalDocNumber);
    }

    public RichInputText getBindSubmittalDocTitle() {
            if (bindSubmittalDocTitle != null) {
                return (RichInputText)bindSubmittalDocTitle.getComponent();
            }
            return null;
        }

        public void setBindSubmittalDocTitle(RichInputText bindSubmittalDocTitle) {
            this.bindSubmittalDocTitle =
                    ComponentReference.newUIComponentReference(bindSubmittalDocTitle);
        }

    public void setBindSubmittalDocStatus(RichSelectOneChoice bindSubmittalDocStatus) {
        this.bindSubmittalDocStatus = bindSubmittalDocStatus;
    }

    public RichSelectOneChoice getBindSubmittalDocStatus() {
        return bindSubmittalDocStatus;
    }

    public RichInputText getBindSubmittalPages() {
            if (bindSubmittalPages != null) {
                return (RichInputText)bindSubmittalPages.getComponent();
            }
            return null;
        }

        public void setBindSubmittalPages(RichInputText bindSubmittalPages) {
            this.bindSubmittalPages =
                    ComponentReference.newUIComponentReference(bindSubmittalPages);
        }

    public void setBindSubmittalRemarks(RichInputText bindSubmittalRemarks) {
        this.bindSubmittalRemarks = bindSubmittalRemarks;
    }

    public RichInputText getBindSubmittalRemarks() {
        return bindSubmittalRemarks;
    }

    public RichInputText getBindSubmittalRev() {
            if (bindSubmittalRev != null) {
                return (RichInputText)bindSubmittalRev.getComponent();
            }
            return null;
        }

        public void setBindSubmittalRev(RichInputText bindSubmittalRev) {
            this.bindSubmittalRev =
                    ComponentReference.newUIComponentReference(bindSubmittalRev);
        }

    public HttpSession GetSession() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest request =
            (HttpServletRequest)ctx.getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        return session;
    }

    public BindingContainer GetBindings() {
        BindingContext bindingctx = BindingContext.getCurrent();
        BindingContainer bindings = bindingctx.getCurrentBindingsEntry();
        return bindings;
    }

    public BindingContainer getBinding() {
        BindingContainer bindings =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        return bindings;
    }

    public String getDocumentName(String fileName) {
        fileName = fileName.replace("'", "''");
        return (fileName.substring(0, fileName.lastIndexOf(".")));
    }

    public String getDocumentNumber(String fileName) {
        return fileName.split(" ")[0];
    }

    public void onChangeSubmittalFile(ValueChangeEvent valueChangeEvent) throws IOException {
        file = (UploadedFile)valueChangeEvent.getNewValue();
        fileLength = file.getLength();
        fileInputStream = file.getInputStream();
        bindUploadedSubmittalFile.setValue(file.getFilename());
        fileName = bindUploadedSubmittalFile.getValue().toString().trim();
        fileContentType = file.getContentType();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindUploadedSubmittalFile);
        RIDCClass ridc =new RIDCClass(Userlogin,getPassword());
            
//            new RIDCClass(GetSession().getAttribute("username").toString(),
//                          GetSession().getAttribute("password").toString());
        OperationBinding method =
            GetBindings().getOperationBinding("EWIsDocExist");
        Map paramsMap = method.getParamsMap();
        paramsMap.put("folderGUID", documentsGuid);
        paramsMap.put("fileName", fileName);
        method.execute();
        BindingContainer bindings2 = getBinding();
        DCIteratorBinding iterBind =
            (DCIteratorBinding)bindings2.get("IsDocExistVO1Iterator");
        RowSetIterator rsi = iterBind.getRowSetIterator();
        int i = 0;
        for (Row row : rsi.getAllRowsInRange()) {
            try {
                i++;
            } catch (NullPointerException e) {
                System.out.println("No Latest Id");
            }
        }
        if (i > 0) {
            FacesMessage msg =
                new FacesMessage(FacesMessage.SEVERITY_INFO, "File is already exist in this Project",
                                 "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            //bindUploadedFile.resetValue();
            bindUploadedSubmittalFile = new RichInputFile();
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindUploadedSubmittalFile);
            bindUploadedSubmittalFile.setValue("");
            getBindSubmittalDocNumber().setValue("");
            AdfFacesContext.getCurrentInstance().addPartialTarget(getBindSubmittalDocNumber());
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindUploadedSubmittalFile);
            fileName = "";
        } else {
            getBindSubmittalName().setValue(getDocumentName(fileName));
            getBindSubmittalDocNumber().setValue(getDocumentNumber(fileName));
            AdfFacesContext.getCurrentInstance().addPartialTarget(getBindSubmittalName());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getBindSubmittalDocNumber());
        }
    }

    public void setBindSubmittalDocType(RichInputText bindSubmittalDocType) {
        this.bindSubmittalDocType = bindSubmittalDocType;
    }

    public RichInputText getBindSubmittalDocType() {
        return bindSubmittalDocType;
    }

    public void AddSubmittal(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            if (bindSubmittalDocStatus.getValue().toString().equals("0")) {
                FacesMessage msg =
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Please select Document Status",
                                     "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                oracle.jbo.domain.Date nowDate = getNowDate(new Date());
                ApplicationModule app =
                    GetApplicationModule("AppModuleExternalTransmittalDataControl");
                RIDCClass ridc =new RIDCClass(Userlogin,getPassword());
//                    new RIDCClass(GetSession().getAttribute("username").toString(),
//                                  GetSession().getAttribute("password").toString());
                String checkin = "";
                String publishSubmittalGUID = "";
                if (isPublish.equals("Publish")) {
                    String publishfolderGUID =
                        ridc.FolderInfo("Document Secure Workflow/Publish/Documents").get("fFolderGUID").toString();
                    publishSubmittalGUID =
                            ridc.FolderInfo("Document Secure Workflow/Publish/" +
                                            AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString().trim() +
                                            "/Submittal").get("fFolderGUID").toString();
                    checkin =
                            ridc.CheckinElectronic(publishfolderGUID, getBindSubmittalName().getValue().toString(),
                                                   getBindSubmittalDocNumber().getValue().toString(),
                                                   getBindSubmittalDocTitle().getValue().toString(),
                                                   getBindSubmittalRev().getValue().toString(),
                                                   fileInputStream, fileLength,
                                                   fileName);
                } else {
                    checkin =
                            ridc.CheckinElectronic(documentsGuid, getBindSubmittalName().getValue().toString(),
                                                   getBindSubmittalDocNumber().getValue().toString(),
                                                   getBindSubmittalDocTitle().getValue().toString(),
                                                   getBindSubmittalRev().getValue().toString(),
                                                   fileInputStream, fileLength,
                                                   fileName);
                }
                DataObject object = ridc.GetDocInfo(checkin);
                ridc.UpdateDocxRobjId(object.get("dID").toString(),
                                      object.get("dDocName").toString());
                if (isPublish.equals("Publish")) {
                    ridc.createLinkWithFolderGUID(publishSubmittalGUID,
                                                  object.get("dID").toString());
                } else {
                    ridc.createLinkWithFolderGUID(submittalGuid,
                                                  object.get("dID").toString());
                }
                try {
                    ViewObject voDoc =
                        app.findViewObject("PheDswfDocSubmittalVO1");
                    NameValuePairs nvpDoc = new NameValuePairs();
                    nvpDoc.setAttribute("Id", object.get("dID").toString());
                    nvpDoc.setAttribute("TransmittalId",
                                        AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString().trim());
                    nvpDoc.setAttribute("ContentId",
                                        object.get("dDocName").toString());
                    nvpDoc.setAttribute("Type", "Transmittal");
                    nvpDoc.setAttribute("Format",
                                        object.get("dFormat").toString());
                    nvpDoc.setAttribute("UploadDate", nowDate);
                    //                nvpDoc.setAttribute("Uploader", AdfFacesContext.getCurrentInstance().getPageFlowScope().get("dUser").toString());
                    nvpDoc.setAttribute("Uploader",Userlogin);
                                       // GetSession().getAttribute("username").toString());
                    nvpDoc.setAttribute("DocName",
                                        getBindSubmittalName().getValue().toString());
                    nvpDoc.setAttribute("DocTitle",
                                        getBindSubmittalDocTitle().getValue().toString());
                    nvpDoc.setAttribute("DocNumber",
                                        getBindSubmittalDocNumber().getValue().toString());
                    nvpDoc.setAttribute("DocStatus",
                                        bindSubmittalDocStatus.getValue().toString());
                    nvpDoc.setAttribute("DocSource", "Electronic");
                    nvpDoc.setAttribute("Pages",
                                        getBindSubmittalPages().getValue().toString());
                    nvpDoc.setAttribute("Remarks",
                                        bindSubmittalRemarks.getValue().toString());
                    nvpDoc.setAttribute("Revision",
                                        getBindSubmittalRev().getValue().toString());
                    nvpDoc.setAttribute("DocType", "Submittal");
                    voDoc.insertRow(voDoc.createAndInitRow(nvpDoc));
                    app.getTransaction().commit();
                    AdfFacesContext.getCurrentInstance().addPartialTarget(bindtableSubmittal);
                    ResetValue(bindUploadedSubmittalFile);
                    ResetValue(getBindSubmittalName());
                    ResetValue(getBindSubmittalDocTitle());
                    ResetValue(getBindSubmittalDocNumber());
                    ResetValue(bindSubmittalRemarks);
                    ResetValue(getBindSubmittalPages());
                    ResetValue(getBindSubmittalRev());
                } catch (Exception e) {
                    ridc.DeleteDoc(object.get("dID").toString(),
                                   object.get("dDocName").toString());
                    app.getTransaction().rollback();
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void ResetValue(RichInputText param) {
        param.setValue("");
        AdfFacesContext.getCurrentInstance().addPartialTarget(param);
    }

    public void ResetValue(RichInputFile param) {
        param.resetValue();
        param = new RichInputFile();
        AdfFacesContext.getCurrentInstance().addPartialTarget(param);
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
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

    public void setTransmittalGuid(String transmittalGuid) {
        this.transmittalGuid = transmittalGuid;
    }

    public String getTransmittalGuid() {
        return transmittalGuid;
    }

    public void setDocumentsGuid(String documentsGuid) {
        this.documentsGuid = documentsGuid;
    }

    public String getDocumentsGuid() {
        return documentsGuid;
    }

    public void setSubmittalGuid(String submittalGuid) {
        this.submittalGuid = submittalGuid;
    }

    public String getSubmittalGuid() {
        return submittalGuid;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void SaveAsDraft(ActionEvent actionEvent) {
        oracle.jbo.domain.Date nowDate = getNowDate(new Date());
        ApplicationModule app =
            GetApplicationModule("AppModuleExternalTransmittalDataControl");
        try {
            ViewObject myVo =
                app.findViewObject("DetailInteralSupportingDocForUser1");
            ViewObject VoDocProc = app.findViewObject("PheDocProcessVO1");
            //            VoDocProc.setWhereClause("transmittal_id = '"+AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString()+"' and recipient = '"+AdfFacesContext.getCurrentInstance().getPageFlowScope().get("dUser").toString()+"'");
            VoDocProc.setWhereClause("transmittal_id = '" +
                                    AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString().trim() +
                                     "' and recipient = '" + Userlogin +
                                     //GetSession().getAttribute("username").toString() +
                                     "'");
            VoDocProc.executeQuery();
            while (VoDocProc.hasNext()) {
                Row row = VoDocProc.next();
                row.remove();
            }

            for (int i = 0; i < tampActin.size(); i++) {
                System.out.println(tampActin.get(i).getRowId() + " " +
                                   tampActin.get(i).getAction() + " " +
                                   tampActin.get(i).getComment());
                Key keys =
                    new Key(new Object[] { tampActin.get(i).getRowId() });
                Row[] rows = myVo.findByKey(keys, 1);
                NameValuePairs nvp = new NameValuePairs();
                nvp.setAttribute("TransmittalId",
                                 rows[0].getAttribute("TransmittalId").toString());
                nvp.setAttribute("Did", rows[0].getAttribute("Id").toString());
                nvp.setAttribute("Sender",
                                 rows[0].getAttribute("Uploader").toString());
                //                nvp.setAttribute("Recipient", AdfFacesContext.getCurrentInstance().getPageFlowScope().get("dUser").toString());
                nvp.setAttribute("Recipient",Userlogin);
                                 //GetSession().getAttribute("username").toString());
                nvp.setAttribute("DocType",
                                 rows[0].getAttribute("DocType").toString());
                nvp.setAttribute("DocTitle",
                                 rows[0].getAttribute("DocTitle").toString());
                nvp.setAttribute("Action", tampActin.get(i).getAction());
                nvp.setAttribute("DocStatus",
                                 rows[0].getAttribute("DocStatus").toString());
                nvp.setAttribute("Notes", tampActin.get(i).getComment());
                VoDocProc.createAndInitRow(nvp);
            }
            app.getTransaction().commit();

            FacesMessage msg =
                new FacesMessage(FacesMessage.SEVERITY_INFO, "This Submittal has been saved as a draft and hasn't been sent to TDC. Press button Close Screen to go to Inbox.",
                                 "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            e.printStackTrace();
            app.getTransaction().rollback();
        }
    }

    public void removeSubmittal(ActionEvent actionEvent) {
        Map p = ((UIComponent)actionEvent.getSource()).getAttributes();
        System.out.println(p.get("delvalIndex").toString());
        ApplicationModule app =
            GetApplicationModule("AppModuleExternalTransmittalDataControl");
        try {
            ViewObject myVo = app.findViewObject("PheDswfDocSubmittalVO1");
            Key keys =
                new Key(new Object[] { p.get("delvalIndex").toString() });
            Row[] rows = myVo.findByKey(keys, 1);
            System.out.println(rows[0].getAttribute("DocTitle"));
            RIDCClass ridc = new RIDCClass(Userlogin,getPassword());
//                new RIDCClass(GetSession().getAttribute("username").toString(),
//                              GetSession().getAttribute("password").toString());
            ridc.DeleteDoc(rows[0].getAttribute("Id").toString(),
                           rows[0].getAttribute("ContentId").toString());
            rows[0].remove();
            app.getTransaction().commit();
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindtableSubmittal);
        } catch (Exception e) {
            app.getTransaction().rollback();
            e.printStackTrace();
        }


    }

    public void setBindtableSubmittal(RichTable bindtableSubmittal) {
        this.bindtableSubmittal = bindtableSubmittal;
    }

    public RichTable getBindtableSubmittal() {
        return bindtableSubmittal;
    }

    public void setBindTableSupporting(RichTable bindTableSupporting) {
        this.bindTableSupporting = bindTableSupporting;
    }

    public RichTable getBindTableSupporting() {
        return bindTableSupporting;
    }

    public void setBindPopUpSuccess(RichPopup bindPopUpSuccess) {
        this.bindPopUpSuccess = bindPopUpSuccess;
    }

    public RichPopup getBindPopUpSuccess() {
        return bindPopUpSuccess;
    }

    public void setUrmurl(String urmurl) {
        this.urmurl = urmurl;
    }

    public String getUrmurl() {
        return urmurl;
    }

    public void BtnSearchInternalUser(ActionEvent actionEvent) {
        ApplicationModule app =
            GetApplicationModule("AppModuleExternalTransmittalDataControl");
        ViewObject voParIn = app.findViewObject("UsersInternalVO1");
        voParIn.setWhereClause("upper(FULLNAME) like upper('%" +
                               bindUserSearch.getValue().toString() + "%')");
        voParIn.executeQuery();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableInternalUser);
    }

    public void setBindTableInternalUser(RichTable bindTableInternalUser) {
        this.bindTableInternalUser = bindTableInternalUser;
    }

    public RichTable getBindTableInternalUser() {
        return bindTableInternalUser;
    }

    public void setBindUserSearch(RichInputText bindUserSearch) {
        this.bindUserSearch = bindUserSearch;
    }

    public RichInputText getBindUserSearch() {
        return bindUserSearch;
    }

    public void DelegateDialog(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            if (InsertPheDelegate()) {
                DCBindingContainer bindings =
                    (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding userIterator =
                    (DCIteratorBinding)bindings.get("UsersInternalVO1Iterator");
                Row selectedUser = userIterator.getCurrentRow();
                JSFUtils.addFacesInfoMessage("Delegate Success to " +
                                             selectedUser.getAttribute("Username").toString()+"IsSentItem "+getIsSentItem().toString());
                
            }
        }
    }

    public void BtnDeleteDelegate(ActionEvent actionEvent) {
        if (DeletePheDelegate()) {
            JSFUtils.addFacesInfoMessage("Delegate Deleted");
        }
    }

    public boolean InsertPheDelegate() {
        try {
            ApplicationModule app =
                GetApplicationModule("AppModuleExternalTransmittalDataControl");
            ViewObject myVo = app.findViewObject("PheDelegateView1");
            DCBindingContainer bindings =
                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding userIterator =
                (DCIteratorBinding)bindings.get("UsersInternalVO1Iterator");
            Row selectedUser = userIterator.getCurrentRow();
            String iD;
            String transmittalID;
            String delegateFrom;
            String delegateTo;
            String statusCode;
            String statusDesc;
            String delegatePID;
            transmittalID = this.getBinding().get("TransmittalId").toString();
            delegateFrom =Userlogin;
                    //ADFContext.getCurrent().getSecurityContext().getUserName();
            delegateTo = selectedUser.getAttribute("Username").toString();
            String emailDelegate =
                selectedUser.getAttribute("Email").toString();
            statusCode = "0";
            statusDesc = "In Progress";
            delegatePID = this.GetDelegatePID(transmittalID, delegateFrom);
            //            PheDelegateViewImpl voi = new PheDelegateViewImpl();
            NameValuePairs row = new NameValuePairs();
            row.setAttribute("TransmittalId", transmittalID);
            row.setAttribute("DelegateFrom", delegateFrom);
            row.setAttribute("DelegateTo", delegateTo);
            //        row.setAttribute("START_DATE", startDate);
            //        row.setAttribute("END_DATE", endDate);
            row.setAttribute("StatusCode", statusCode);
            row.setAttribute("StatusDesc", statusDesc);
            row.setAttribute("DelegatePid", delegatePID);
            myVo.createAndInitRow(row);

            ViewObject myLog = app.findViewObject("PheDswfLog1");
            NameValuePairs nvpLog = new NameValuePairs();
            nvpLog.setAttribute("TransmittalId", transmittalID);
            nvpLog.setAttribute("Username",Userlogin);
                                //GetSession().getAttribute("username").toString());
            nvpLog.setAttribute("Action", "12");
            nvpLog.setAttribute("Description",
                                getFullName(delegateFrom) + " delegate to " +
                                getFullName(delegateTo));
            myLog.createAndInitRow(nvpLog);

            AttributeBinding userrole = (AttributeBinding)bindings.get("Role");
            if (IsRoleExist(delegateTo,
                            userrole.getInputValue().toString()).equals("0")) {
                ViewObject voUserRole =
                    app.findViewObject("UserSecurityAttributesVO1");
                NameValuePairs nvpUserRole = new NameValuePairs();
                nvpUserRole.setAttribute("Dusername", delegateTo);
                nvpUserRole.setAttribute("Dattributetype", "role");
                nvpUserRole.setAttribute("Dattributename",
                                         userrole.getInputValue().toString());
                nvpUserRole.setAttribute("Dattributeprivilege", "15");
                voUserRole.createAndInitRow(nvpUserRole);
            }

            DCBindingContainer dcBindings =
                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            String DocString =
                "<table style=\"font-family:arial\" cellpadding=\"3\" cellspacing=\"3\">\n" +
                "<tr style=\"background-color:#f0e68c\">\n" +
                "<td>Document No</td>\n" +
                "<td>Document Title</td>\n" +
                "<td>Revision No</td>\n" +
                "</tr>";
            DCIteratorBinding allDoc = null;
            if (SedangDelegate.length() > 0) {
                allDoc =
                        (DCIteratorBinding)dcBindings.get("DetailInternalSupportingDocForDelegate1Iterator");
            } else {
                allDoc =
                        (DCIteratorBinding)dcBindings.get("DetailInteralSupportingDocForUser1Iterator");
            }

            Row[] docs = allDoc.getAllRowsInRange();
            for (int doc = 0; doc < docs.length; doc++) {
                DocString =
                        DocString + "<tr style=\"background-color:#dcdcdc\">" +
                        "<td>" +
                        docs[doc].getAttribute("DocNumber").toString() +
                        "</td>" + "<td>" +
                        docs[doc].getAttribute("DocTitle").toString() +
                        "</td>" + "<td>" +
                        docs[doc].getAttribute("Revision").toString() +
                        "</td></tr>";
            }
            //updated nanda 14-12-15
            DocString = DocString + "</table>";
            String isi = "<html>\n" +
                "<head></head>\n" +
                "<body style=\"font-family:arial\">Dear Sir/Madam<br/><br/>You have received a new transmittal from delegate. The details are:<br/><br/>\n" +
                "<table><tr><td>Transmittal No</td><td>: " + transmittalID +
                "</td></tr>\n" +
                "<tr><td>Project/Organization</td><td>: " + getIsPublish() +
                "</td></tr>" + "<tr><td>Subject</td><td>: " +
                getBindSubject().getValue().toString() + "</td></tr>" +
                "</table><br/><br/>" + DocString + "<br/><br/>" +
                "Please see the transmittal in Inbox DSWF menu or click this <a href=\"" +
                GetConfig("RIDC_URL") +
                //updated by nanda 14-12-2015
            "?IdcService=PHE_GET_TRANSMITTAL_INBOX&fromEmail=1&dUser=" +
                                            delegateTo +
                                            "&sender=&dateType=&month=&year=&status=&category=&startRow=1&endRow=10&transmittalNo="+
                transmittalID +  "\">link</a>" +       
                         
//                "?IdcService=PHE_GET_TRANSMITTAL_DETAIL&transmittalID=" +
//                transmittalID + "\">link</a>" +
                "<br/>Thank you for your attention.</body></html>";
            OperationBinding sendEmail =
                bindings.getOperationBinding("SendEmail");
            Map paramEmail = sendEmail.getParamsMap();
            paramEmail.put("EmailTo", emailDelegate);
            paramEmail.put("Subject",
                           "[PHEONWJ] New Transmittal for Transmittal  - " +
                           transmittalID);
            paramEmail.put("HtmlText", isi);
            try {
                sendEmail.execute();/*matikan email dulu 27082021*/
            } catch (Exception e) {
                e.printStackTrace();
            }

            OperationBinding obCommit =
                this.getBinding().getOperationBinding("Commit");
            obCommit.execute();

            DCIteratorBinding delegateIterator =
                (DCIteratorBinding)bindings.get("PheDelegateView1Iterator");
            delegateIterator.executeQuery();
            isIsDelegate();
            isIsDisplayDelegateHistory();
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableDelegateHistory);
            AdfFacesContext.getCurrentInstance().addPartialTarget(btnFinish);
            AdfFacesContext.getCurrentInstance().addPartialTarget(btnDelegate);
            AdfFacesContext.getCurrentInstance().addPartialTarget(btnAbortDelegate);
            AdfFacesContext.getCurrentInstance().addPartialTarget(panelTabDelegate);
            AdfFacesContext.getCurrentInstance().addPartialTarget(btnSaveAsDraft);
            AdfFacesContext.getCurrentInstance().addPartialTarget(btnAddSubmittal);
            return true;

        } catch (NamingException e) {
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public String getFullName(String username) throws NamingException,
                                                      SQLException {
        String query;
        String fullname = "";
        query = "select dfullname from users where dname = '" + username + "'";
        InitialContext inc;
        inc = new InitialContext();
        DataSource ds = (DataSource)inc.lookup("jdbc/EDMSConn");
        conn = ds.getConnection();
        this.stmt = this.conn.createStatement();
        ResultSet rs = this.stmt.executeQuery(query);
        if (rs.next()) {
            fullname = rs.getString("dfullname");
        }

        if (!this.stmt.isClosed())
            this.stmt.close();
        if (!this.conn.isClosed())
            this.conn.close();

        return fullname;
    }

    public String getEmail(String username) throws NamingException,
                                                   SQLException {
        String query;
        String fullname = "";
        query = "select demail from users where dname = '" + username + "'";
        InitialContext inc;
        inc = new InitialContext();
        DataSource ds = (DataSource)inc.lookup("jdbc/EDMSConn");
        conn = ds.getConnection();
        this.stmt = this.conn.createStatement();
        ResultSet rs = this.stmt.executeQuery(query);
        if (rs.next()) {
            fullname = rs.getString("demail");
        }

        if (!this.stmt.isClosed())
            this.stmt.close();
        if (!this.conn.isClosed())
            this.conn.close();

        return fullname;
    }

    public boolean DeletePheDelegate() {
        ApplicationModule app =
            GetApplicationModule("AppModuleExternalTransmittalDataControl");
        try {
            String query;
            String transmittalID;
            String userName;
            transmittalID = this.getBinding().get("TransmittalId").toString();
            userName =Userlogin;
                    //ADFContext.getCurrent().getSecurityContext().getUserName();

            DCBindingContainer bindings =
                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            OperationBinding obGetConfig =
                bindings.getOperationBinding("GetFullNameDelegateTo");
            obGetConfig.getParamsMap().put("transmittalId", transmittalID);
            obGetConfig.execute();
            AttributeBinding Dfullname =
                (AttributeBinding)bindings.get("Dfullname");

            ViewObject myLog = app.findViewObject("PheDswfLog1");
            NameValuePairs nvpLog = new NameValuePairs();
            nvpLog.setAttribute("TransmittalId", transmittalID);
            nvpLog.setAttribute("Username",Userlogin);
                                //GetSession().getAttribute("username").toString());
            nvpLog.setAttribute("Action", "13");
            nvpLog.setAttribute("Description",
                                "Abort Delegate from " + getFullName(userName) +
                                " to " + Dfullname.getInputValue().toString());
            myLog.createAndInitRow(nvpLog);

            query =
                    "DELETE PHE_DELEGATE WHERE TRANSMITTAL_ID ='" + transmittalID +
                    "' " + "AND DELEGATE_FROM ='" + userName +
                    "' AND END_DATE IS NULL";

            InitialContext inc;
            inc = new InitialContext();
            DataSource ds = (DataSource)inc.lookup("jdbc/EDMSConn");
            conn = ds.getConnection();
            this.stmt = this.conn.createStatement();
            this.stmt.execute(query);

            //            ViewObject myVo = app.findViewObject("PheDelegateView1");
            //            myVo.setWhereClause("END_DATE is null");
            //            myVo.executeQuery();
            //            while(myVo.hasNext())
            //                myVo.removeCurrentRow();
            //            OperationBinding obCommit = this.getBinding().getOperationBinding("Commit");
            //            obCommit.execute();

            DCIteratorBinding delegateIterator =
                (DCIteratorBinding)bindings.get("PheDelegateView1Iterator");
            delegateIterator.executeQuery();
            isIsDelegate();
            isIsDisplayDelegateHistory();
            app.getTransaction().commit();
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableDelegateHistory);
            AdfFacesContext.getCurrentInstance().addPartialTarget(btnFinish);
            AdfFacesContext.getCurrentInstance().addPartialTarget(btnDelegate);
            AdfFacesContext.getCurrentInstance().addPartialTarget(btnAbortDelegate);
            AdfFacesContext.getCurrentInstance().addPartialTarget(panelTabDelegate);
            AdfFacesContext.getCurrentInstance().addPartialTarget(btnSaveAsDraft);
            AdfFacesContext.getCurrentInstance().addPartialTarget(btnAddSubmittal);

            if (!this.stmt.isClosed())
                this.stmt.close();
            if (!this.conn.isClosed())
                this.conn.close();
            return true;
        } catch (Exception ex) {
            app.getTransaction().rollback();
            return false;
        }
    }

    public String GetDelegatePID(String transmittalID,
                                 String username) throws NamingException,
                                                         SQLException {
        String query;
        String parentID = "";
        query = "SELECT ID FROM PHE_DELEGATE\n" +
                "WHERE TRANSMITTAL_ID = '" + transmittalID +
                "' AND DELEGATE_TO = '" + username + "'";
        InitialContext inc;
        inc = new InitialContext();
        DataSource ds = (DataSource)inc.lookup("jdbc/EDMSConn");
        conn = ds.getConnection();
        this.stmt = this.conn.createStatement();
        ResultSet rs = this.stmt.executeQuery(query);
        if (rs.next()) {
            parentID = rs.getString("ID");
        }

        if (!this.stmt.isClosed())
            this.stmt.close();
        if (!this.conn.isClosed())
            this.conn.close();

        return parentID;
    }

    public void setBindTableDelegateHistory(RichTable bindTableDelegateHistory) {
        this.bindTableDelegateHistory = bindTableDelegateHistory;
    }

    public RichTable getBindTableDelegateHistory() {
        return bindTableDelegateHistory;
    }

    public void setIsDelegate(boolean IsDelegate) {
        this.IsDelegate = IsDelegate;
    }

    public boolean isIsDelegate() {
        String query;
        String transmittalID;
        String userName;
        boolean result;
        transmittalID = this.getBinding().get("TransmittalId").toString();
        userName = ADFContext.getCurrent().getSecurityContext().getUserName();
        try {

            query = "select count(*) as isDelegate from (\n" +
                    "SELECT count(*) as total from PHE_DELEGATE\n" +
                    "WHERE TRANSMITTAL_ID = '" + transmittalID +
                    "' AND DELEGATE_FROm = '" + userName + "'\n" +
                    "minus\n" +
                    "SELECT count(*) as total from PHE_DELEGATE\n" +
                    "WHERE TRANSMITTAL_ID = '" + transmittalID +
                    "' AND DELEGATE_FROm = '" + userName +
                    "' and end_date is not null)";
            InitialContext inc;
            inc = new InitialContext();

            DataSource ds = (DataSource)inc.lookup("jdbc/EDMSConn");
            conn = ds.getConnection();

            this.stmt = this.conn.createStatement();
            ResultSet rs = this.stmt.executeQuery(query);
            if (rs.next()) {
                if (Integer.valueOf(rs.getString("isDelegate")) == 0)
                    result = true;
                else
                    result = false;
            } else
                result = false;
            if (!this.stmt.isClosed())
                this.stmt.close();
            if (!this.conn.isClosed())
                this.conn.close();
            return result;
        } catch (NamingException e) {
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public void setBtnDelegate(RichButton btnDelegate) {
        this.btnDelegate = btnDelegate;
    }

    public RichButton getBtnDelegate() {
        return btnDelegate;
    }

    public void setBtnAbortDelegate(RichButton btnAbortDelegate) {
        this.btnAbortDelegate = btnAbortDelegate;
    }

    public RichButton getBtnAbortDelegate() {
        return btnAbortDelegate;
    }

    public void setBtnFinish(RichButton btnFinish) {
        this.btnFinish = btnFinish;
    }

    public RichButton getBtnFinish() {
        return btnFinish;
    }

    public void setPanelTabDelegate(RichShowDetailItem panelTabDelegate) {
        this.panelTabDelegate = panelTabDelegate;
    }

    public RichShowDetailItem getPanelTabDelegate() {
        return panelTabDelegate;
    }

    public void setBtnSaveAsDraft(RichButton btnSaveAsDraft) {
        this.btnSaveAsDraft = btnSaveAsDraft;
    }

    public RichButton getBtnSaveAsDraft() {
        return btnSaveAsDraft;
    }

    public void setBtnAddSubmittal(RichButton btnAddSubmittal) {
        this.btnAddSubmittal = btnAddSubmittal;
    }

    public RichButton getBtnAddSubmittal() {
        return btnAddSubmittal;
    }

    public void setIsDisplayDelegateHistory(boolean IsDisplayDelegateHistory) {
        this.IsDisplayDelegateHistory = IsDisplayDelegateHistory;
    }

    public boolean isIsDisplayDelegateHistory() {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding delegateIterator =
            (DCIteratorBinding)bindings.get("PheDelegateView1Iterator");
        if (delegateIterator.getRowSetIterator().getRowCount() > 0)
            return true;
        else
            return false;
    }


    public void setIsPublish(String isPublish) {
        this.isPublish = isPublish;
    }

    public String getIsPublish() {
        return isPublish;
    }

    public void setBindTableSupportingDelegate(RichTable bindTableSupportingDelegate) {
        this.bindTableSupportingDelegate = bindTableSupportingDelegate;
    }

    public RichTable getBindTableSupportingDelegate() {
        return bindTableSupportingDelegate;
    }

    public void setSedangDelegate(String SedangDelegate) {
        this.SedangDelegate = SedangDelegate;
    }

    public String getSedangDelegate() {
        return SedangDelegate;
    }

//    public void setBindSubject(RichOutputText bindSubject) {
//        this.bindSubject = bindSubject;
//    }
//
//    public RichOutputText getBindSubject() {
//        return bindSubject;
//    }
        public RichOutputText getBindSubject() {
            if (bindSubject != null) {
                return (RichOutputText)bindSubject.getComponent();
            }
            return null;
        }

        public void setBindSubject(RichOutputText bindSubject) {
            this.bindSubject =
                    ComponentReference.newUIComponentReference(bindSubject);
        }

    public void setIsSentItem(Boolean isSentItem) {
        this.isSentItem = isSentItem;
    }

    public Boolean getIsSentItem() {
        return isSentItem;
    }

  public void setBindPopupValidateFinish(RichPopup bindPopupValidateFinish) {
    this.bindPopupValidateFinish = bindPopupValidateFinish;
  }

  public RichPopup getBindPopupValidateFinish() {
    return bindPopupValidateFinish;
  }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPassword() {
        return Password;
    }

    public void setBindPopupConfirmasi(RichPopup bindPopupConfirmasi) {
        this.bindPopupConfirmasi = bindPopupConfirmasi;
    }

    public RichPopup getBindPopupConfirmasi() {
        return bindPopupConfirmasi;
    }

    public void submittalActionConfirm(ActionEvent actionEvent) {
        System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:begin");
                oracle.jbo.domain.Date nowDate = getNowDate(new Date());
                ApplicationModule app =
                    GetApplicationModule("AppModuleExternalTransmittalDataControl");
                BindingContext bindingctx = BindingContext.getCurrent();
                System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:bindingctx:"+bindingctx);
                BindingContainer bindings = bindingctx.getCurrentBindingsEntry();
                System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:bindings:"+bindings);
                RichPopup.PopupHints ph = new RichPopup.PopupHints();
                Boolean isValidFinish=true;
                Boolean isInformationOnly=false;
                Boolean isEmptyAction=false;
                String roleRecipient="";
                String transmittalID =//"PHEONWJ-ID-2021824-0002";
                    AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString().trim();
                System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:transmittalID"+transmittalID);
                System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:TryCatchBefore");
                
                
                try {
                    System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:TryCatchBegin");
                  //nanda 050416
                  ViewObject myVoPar =
                      app.findViewObject("PheDswfParticipantVO1_1");
                    System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:myVoPar:"+myVoPar);
                  //            String query = "TRANSMITTAL_ID = '"+AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString()+"' and receiver = '"+AdfFacesContext.getCurrentInstance().getPageFlowScope().get("dUser").toString()+"'";
                  String query =
                      "TRANSMITTAL_ID = '" +AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString().trim() +
                      "' and receiver = '" +
                        Userlogin+ "'";
                     // GetSession().getAttribute("username").toString() + "'";
                    System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:query:"+query);
                  myVoPar.setWhereClause(query);
                  myVoPar.executeQuery();
                    System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:query:executeQuery");
                  while (myVoPar.hasNext()) {
                    Row rows = myVoPar.next();
                    roleRecipient= rows.getAttribute("Role").toString();
                    System.out.println("roleRecipient:"+roleRecipient);
                  }

                    String sender = "";
                    if (SedangDelegate.length() > 0) {
                        ViewObject delegate =
                            app.findViewObject("PheDelegateDavidVO1");
                        delegate.setWhereClause("transmittal_id = '" + transmittalID +
                                                "' and id = '" + SedangDelegate + "'");
                        delegate.executeQuery();
                        System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:SedangDelegate:SedangDelegate");
                        while (delegate.hasNext()) {
                            Row row = delegate.next();
                            row.setAttribute("EndDate", nowDate);
                            row.setAttribute("StatusDesc", "Completed");
                            row.setAttribute("StatusCode", "1");
                            System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:SedangDelegate:row:"+row);
                        }
                    } else {
                        System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:VoDocProcDel");
                        ViewObject VoDocProcDel =
                            app.findViewObject("PheDocProcessVO1");
                        VoDocProcDel.setWhereClause("transmittal_id = '" +
                                                    transmittalID +
                                                    "' and recipient = '" +
                                                    Userlogin +
                                                    //ADFContext.getCurrent().getSecurityContext().getUserName() +
                                                    "'");
                        VoDocProcDel.executeQuery();
                        System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:VoDocProcDel:executeQuery");
                        while (VoDocProcDel.hasNext()) {
                            Row row = VoDocProcDel.next();
                            System.out.println(row.getAttribute("Did") + " " +
                                               row.getAttribute("Recipient")+" "+ row.getAttribute("DocStatus"));
                          if(row.getAttribute("DocStatus").toString().equalsIgnoreCase("IFI")){
                          isInformationOnly = true;
                          }
                            row.remove();
                        }

                        ViewObject myVo =
                            app.findViewObject("DetailInteralSupportingDocForUser1");
                        myVo.executeQuery();
                        System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:myVo:executeQuery");
                        ViewObject VoDocProc = app.findViewObject("PheDocProcessVO1");
                        for (int i = 0; i < tampActin.size(); i++) {
                            System.out.println(tampActin.get(i).getRowId() + " -action-" +
                                               tampActin.get(i).getAction() + " " +
                                               tampActin.get(i).getComment());
                          String action = tampActin.get(i).getAction().toString();
                          //String docstatus = tampActin.get(i).getDocStatus().toString();
                           
                           //add by nanda 04032016 - validasi finish
                          System.out.println("nilai action string : "+action);
                          
                          if (action.equalsIgnoreCase("0") || action.contains("0")){
                            isEmptyAction=true;
                          }
                          System.out.println("nilai action isEmptyAction:"+isEmptyAction);
                  System.out.println("nilai action isInformationOnly:"+isInformationOnly);
                 // nanda 050416 - ngecek role recipient untuk validasi finish
            //          if(!roleRecipient.equalsIgnoreCase("Reviewer/Approver")){
            //            isValidFinish = true;
            //            System.out.println("--isValidFinish:BukanReviewer:true:"+isValidFinish);
            //          }
                  if (roleRecipient.equalsIgnoreCase("Reviewer/Approver")&&isEmptyAction==true&&isInformationOnly==false) {
                          isValidFinish = false;
                          System.out.println("--isValidFinish:harusnyafalse:"+isValidFinish);
                        } 
                 
                  else {
                            isValidFinish = true;
                            System.out.println("--isValidFinish:harusnya true:"+isValidFinish);
                              Key keys =
                                  new Key(new Object[] { tampActin.get(i).getRowId() });
                              Row[] rows = myVo.findByKey(keys, 1);
                              NameValuePairs nvp = new NameValuePairs();
                              sender = rows[0].getAttribute("Uploader").toString();
                              nvp.setAttribute("TransmittalId",
                                               rows[0].getAttribute("TransmittalId").toString());
                              nvp.setAttribute("Did",
                                               rows[0].getAttribute("Id").toString());
                              nvp.setAttribute("Sender",
                                               rows[0].getAttribute("Uploader").toString());
                              nvp.setAttribute("Recipient",Userlogin);
                                              // GetSession().getAttribute("username").toString());
                              nvp.setAttribute("DocType",
                                               rows[0].getAttribute("DocType").toString());
                              nvp.setAttribute("DocTitle",
                                               rows[0].getAttribute("DocTitle").toString());
                              nvp.setAttribute("Action", tampActin.get(i).getAction());
                              nvp.setAttribute("DocStatus",
                                               rows[0].getAttribute("DocStatus").toString());
                              nvp.setAttribute("Notes", tampActin.get(i).getComment());
                              VoDocProc.createAndInitRow(nvp);
                          }
                }
                        System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:isValidFinishBefore");
                      if(isValidFinish==true){
                        ViewObject myVoPar1 =
                            app.findViewObject("PheDswfParticipantVO1_1");
                        System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:myVoPar1");
            //                            String query1 = "TRANSMITTAL_ID = '"+AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString()+"' and receiver = '"+AdfFacesContext.getCurrentInstance().getPageFlowScope().get("dUser").toString()+"'";
                        String query1 =
                            "TRANSMITTAL_ID = '" + AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString().trim() +
                            "' and receiver = '" +
                                    Userlogin + "'";
                            //GetSession().getAttribute("username").toString() + "'";
                        System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:query1:"+query1);
                        myVoPar1.setWhereClause(query1);
                        myVoPar1.executeQuery();
                        System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:query1:executeQuery");
                        while (myVoPar1.hasNext()) {
                            Row row = myVoPar.next();
                            row.setAttribute("Enddate", nowDate);
                            System.out.println("update end date participant in dswf_participant in id = " +
                                               row.getAttribute("Id") + " to " +
                                               nowDate);
                            System.out.println(row.getAttribute("Enddate"));
                        }
                    }
                    }
                    System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:isValidFinishBefore2");
                  if(isValidFinish==true){
                    ViewObject voLog = app.findViewObject("PheDswfLog1");
                    NameValuePairs nvpLog = new NameValuePairs();
                    nvpLog.setAttribute("TransmittalId",
                                        AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString().trim());
                    //            nvpLog.setAttribute("Username",AdfFacesContext.getCurrentInstance().getPageFlowScope().get("dUser").toString() );
                    nvpLog.setAttribute("Username",Userlogin);
                                        //GetSession().getAttribute("username").toString());
                    nvpLog.setAttribute("Action", "2");
                      System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:SedangDelegateBefore");
                    if (SedangDelegate.length() > 0) {
                        nvpLog.setAttribute("Description", "Finish Delegate");
                        System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:Finish Delegate");
                    } else {
                        nvpLog.setAttribute("Description", "Finish Submittal");
                        System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:Finish Submittal");
                    }
                    voLog.createAndInitRow(nvpLog);

                    AttributeBinding subject =
                        (AttributeBinding)bindings.get("TransmittalSubject");
                    OperationBinding ExecuteGetEmail =
                        bindings.getOperationBinding("ExecuteGetEmail");
                    try {
                        ExecuteGetEmail.execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    AttributeBinding emailCreator;
                    String emailDelegateFrom = "";
                    String isi;
                      System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:Email");
                    if (SedangDelegate.length() > 0) {
                        emailCreator = (AttributeBinding)bindings.get("DelegateFrom");
                        emailDelegateFrom =
                                getEmail(emailCreator.getInputValue().toString());
                        isi =
            "<html><head></head><body>" + "Dear Sir/Madam,<br/><br/>" +
            "You have received a new response from the following transmittal:<br/><br/>" +
            "<table><tr><td>Transmittal No</td><td>:</td><td style=\"text-align:left\">" +
            transmittalID + "</td></tr>" + "<tr><td>Subject</td><td>:</td><td>" +
            subject.getInputValue().toString() + "</td></tr>" +
            "<tr><td>Recipient</td><td>:</td><td>" +
                Userlogin + "</td></tr>" +
            "</table><br/>Please click <a href=\"" + getUrmurl() +
            "?IdcService=PHE_GET_TRANSMITTAL_DETAIL&transmittalID=" + transmittalID +
            "\">here</a> to review the transmittal." +
            "<br/>Thank you for your attention.</body></html>";
                    } else {
                        emailCreator = (AttributeBinding)bindings.get("Demail");
                        isi =
            "<html><head></head><body>" + "Dear Sir/Madam,<br/><br/>" +
            "You have received a new response from the following transmittal:<br/><br/>" +
            "<table><tr><td>Transmittal No</td><td>:</td><td style=\"text-align:left\">" +
            transmittalID + "</td></tr>" + "<tr><td>Subject</td><td>:</td><td>" +
            subject.getInputValue().toString() + "</td></tr>" +
            "<tr><td>Recipient</td><td>:</td><td>" +
                Userlogin + "</td></tr>" +
            "</table><br/>Please click <a href=\"" + getUrmurl() +
            "?IdcService=PHE_GET_TRANSMITTAL_SUMMARY&transmittalId=" + transmittalID +
            "&dUser=" + sender + "\">here</a> to review the transmittal." +
            "<br/>Thank you for your attention.</body></html>";
                    }

                    OperationBinding sendEmail =
                        bindings.getOperationBinding("SendEmail");
                    Map paramEmail = sendEmail.getParamsMap();
                      System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:sendEmail");
                    if (emailCreator.getInputValue() != null) {
                        paramEmail.put("EmailTo",
                                       emailCreator.getInputValue().toString());
                    } else {
                        paramEmail.put("EmailTo", "owc.support@pheonwj.pertamina.com");
                    }
                    if (SedangDelegate.length() > 0) {
                        paramEmail.put("EmailTo", emailDelegateFrom);
                        paramEmail.put("Subject",
                                       "["+CompanyApName+"] Delegate Response for Transmittal  - " +
                                       transmittalID);
                    } else {
                        paramEmail.put("Subject",
                                       "["+CompanyApName+"] New Submittal for Transmittal  - " +
                                       transmittalID);
                    }

                    paramEmail.put("HtmlText", isi);
                    try {
                        sendEmail.execute(); /*matikan email dulu 27082021*/
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    app.getTransaction().commit();
                    bindPopUpSuccess.show(ph);
                  }
                  else{
                    bindPopupValidateFinish.show(ph);
                  }
                    System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:FinishProses");
                } catch (Exception e) {
                    System.out.println("ExternalTransmittal:DetailTransmittalUserClass:Finish:ErrorCacth");
                    e.printStackTrace();
                    app.getTransaction().rollback();
                }
    }

    public void submittalActionUnConfirm(ActionEvent actionEvent) {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        bindPopupConfirmasi.hide();
    }
}
