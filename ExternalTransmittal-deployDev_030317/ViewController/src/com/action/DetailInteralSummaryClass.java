package com.action;


import com.utils.ADFUtils;
import com.utils.JSFUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Map;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.el.ELContext;

import javax.el.ExpressionFactory;

import javax.el.MethodExpression;

import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.naming.InitialContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.sql.DataSource;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.model.binding.DCIteratorBindingDef;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
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
import oracle.jbo.NameValuePairs;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import oracle.jbo.uicli.binding.JUCtrlListBinding;

import oracle.jdbc.driver.OracleDriver;

import oracle.stellent.ridc.IdcClientException;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.util.ComponentReference;
import org.apache.tools.ant.taskdefs.Get;

import org.python.parser.ast.Pass;


public class DetailInteralSummaryClass {
  private List<TempDocuments> fowardedDoc = new ArrayList<TempDocuments>();
  private RichPopup bindPopUpUser;
  private RichSelectOneRadio bindRole;
  private RichInputText bindSearchNameUser;
  private Connection conn;
  
  
  {
    bindSearchNameUser = new RichInputText();
    bindSearchNameUser.setValue("");
  }
  private RichTable bindtableInternalUser;
  private String IsAddorChange;
  private RichSelectOneChoice bindReceiver;
  private JSFUtils util = new JSFUtils();
  private RichTable tblSubmittal;
  private String All;
  {
    All = new String();
    All = "no";
  }
  private String AllSupporting;
  {
    AllSupporting = new String();
    AllSupporting = "no";
  }
  private RichInputText bindAbortReason;
  {
    bindAbortReason = new RichInputText();
  }
  private RichInputText bindFinishReason;
  private RichButton bindBtnFinish;
  private RichButton bindBtnAbort;
  private RichTable tblSupporting;
  private RichPopup bindPopUpForward;
  private RichSelectBooleanCheckbox bindCloseTransmittal;
  private RichButton bindButtonChangeUser;
  private RichButton bindButtonAddUser;
  private RichButton bindButtonDeleteUser;
  {
    bindFinishReason = new RichInputText();
  }
  private String TDCEMail;
  private String currentAddress;
  private String weblogicusername;
  private String weblogicpassword;
  private String dswfAddress;
  private String zipName;
  private String isPublish;
  //    private RichOutputText countCheckBox;
  private ComponentReference countCheckBox;
  private RichPopup bindPopupDuedate;
  private RichButton bindButtonEditDuedate;
  private RichInputDate bindTransmittalDueDate;
  private RichOutputText dueDate;
    private String CompanyApName;
    private String Userlogin;
    
  public DetailInteralSummaryClass() {
    setZipName("Export Supporting Doc " +
               AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString() +
               ".zip");
    setIsPublish("No");
      FacesContext ctx = FacesContext.getCurrentInstance();
      HttpServletRequest request =
          (HttpServletRequest)ctx.getExternalContext().getRequest();
      HttpSession session = request.getSession(true);
      Userlogin =request.getParameter("dUser");//"owc.admin";//"iwansyah""weblogic";//;
      CompanyApName=request.getParameter("companyap");//"PHE ONWJ";//
      ApplicationModule app =
        GetApplicationModule("AppModuleExternalTransmittalDataControl");
      
      ViewObject vo1lov = app.findViewObject("TransmittalReceiverListVO1");
      vo1lov.executeQuery();
      Row r=vo1lov.first();
      
      String userPertama=r.getAttribute("Receiver")==null ? "" :r.getAttribute("Receiver").toString();
    
      ViewObject vo1 = app.findViewObject("DetailInternalSupportinDocVO1");
      vo1.setWhereClause("recipient = '"+userPertama+"'");
      vo1.executeQuery();
 
    ViewObject voPublish = app.findViewObject("PheDswfMasterVO1");
    voPublish.setWhereClause("TRANSMITTAL_ID = '" +
                             AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString() +
                             "'");
    voPublish.executeQuery();
    while (voPublish.hasNext()) {
      Row row = voPublish.next();
      setIsPublish(row.getAttribute("ProjectorganizationName").toString());
    }
  }

  public void AddUser(ActionEvent actionEvent) {
      System.out.println("masuk ke popup");
    setIsAddorChange("Add");
//    bindRole.setVisible(false);
    AdfFacesContext.getCurrentInstance().addPartialTarget(bindRole);
    RichPopup.PopupHints ph = new RichPopup.PopupHints();
    bindPopUpUser.show(ph);
  }

  public void ChangeUser(ActionEvent actionEvent) {
    setIsAddorChange("Change");
    bindRole.setVisible(false);
    AdfFacesContext.getCurrentInstance().addPartialTarget(bindRole);
    RichPopup.PopupHints ph = new RichPopup.PopupHints();
    bindPopUpUser.show(ph);
  }
  
  public void setBindPopUpUser(RichPopup bindPopUpUser) {
    this.bindPopUpUser = bindPopUpUser;
  }

  public RichPopup getBindPopUpUser() {
    return bindPopUpUser;
  }

  public ApplicationModule GetApplicationModule(String appName) {
    return ADFUtils.getApplicationModuleForDataControl(appName);
  }

  public void SearchUser(ActionEvent actionEvent) {
    ApplicationModule app =
      GetApplicationModule("AppModuleExternalTransmittalDataControl");
    ViewObject voParIn = app.findViewObject("UsersInternalVO1");
    voParIn.setWhereClause("upper(FULLNAME) like upper('%" +
                           bindSearchNameUser.getValue().toString() + "%')");
    voParIn.executeQuery();
    AdfFacesContext.getCurrentInstance().addPartialTarget(bindtableInternalUser);
  }

  public void setBindRole(RichSelectOneRadio bindRole) {
    this.bindRole = bindRole;
  }

  public RichSelectOneRadio getBindRole() {
    return bindRole;
  }

  public void setBindSearchNameUser(RichInputText bindSearchNameUser) {
    this.bindSearchNameUser = bindSearchNameUser;
  }

  public RichInputText getBindSearchNameUser() {
    return bindSearchNameUser;
  }

  public void setBindtableInternalUser(RichTable bindtableInternalUser) {
    this.bindtableInternalUser = bindtableInternalUser;
  }

  public RichTable getBindtableInternalUser() {
    return bindtableInternalUser;
  }

  public void setIsAddorChange(String IsAddorChange) {
    this.IsAddorChange = IsAddorChange;
  }

  public String getIsAddorChange() {
    return IsAddorChange;
  }

  public HttpSession GetSession() {
    FacesContext ctx = FacesContext.getCurrentInstance();
    HttpServletRequest request =
      (HttpServletRequest)ctx.getExternalContext().getRequest();
    HttpSession session = request.getSession(true);
    return session;
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

  public String GetPublishId() {
    DCBindingContainer bindings =
      (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    OperationBinding obGetPublishid =
      bindings.getOperationBinding("GetPublishId");
    obGetPublishid.getParamsMap().put("transmittalId",
                                      AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString());
    obGetPublishid.execute();
    DCBindingContainer dcBindings =
      (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    DCIteratorBinding iterBind =
      (DCIteratorBinding)dcBindings.get("UpdatePublishStatusDoc1Iterator");
    Row[] rows = iterBind.getAllRowsInRange();
    String returnan = "";
    for (int i = 0; i < rows.length; i++) {
      if (i != 0 && i < rows.length) {
        returnan = returnan + ",";
      }
      returnan = returnan.concat("'");
      returnan = returnan + rows[i].getAttribute("Id");
      returnan = returnan.concat("'");
    }
    if (returnan == "") {
      returnan = returnan.concat("'");
      returnan = returnan.concat("'");
    }
    return returnan;
  }

  public String GetDocPublishing() {
    DCBindingContainer bindings =
      (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    OperationBinding obGetPublishid =
      bindings.getOperationBinding("GetDocPublishing");
    obGetPublishid.getParamsMap().put("transmittalId",
                                      AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString());
    obGetPublishid.execute();
    DCBindingContainer dcBindings =
      (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    DCIteratorBinding iterBind =
      (DCIteratorBinding)dcBindings.get("DocPublishing1Iterator");
    Row[] rows = iterBind.getAllRowsInRange();
    String returnan = "";
    for (int i = 0; i < rows.length; i++) {
      if (i != 0 && i < rows.length) {
        returnan = returnan + ",";
      }
      returnan = returnan.concat("'");
      returnan = returnan + rows[i].getAttribute("Id");
      returnan = returnan.concat("'");
    }
    if (returnan == "") {
      returnan = returnan.concat("'");
      returnan = returnan.concat("'");
    }
    return returnan;
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

  public void DialogLsnrSelectUser(DialogEvent dialogEvent) {
    if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
      String transmittalId =
        AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString();
      DCBindingContainer dcBindings =
        (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
      DCIteratorBinding iterBind =
        (DCIteratorBinding)dcBindings.get("UsersInternalVO1Iterator");
      BindingContext bindingctx = BindingContext.getCurrent();
      BindingContainer bindings = bindingctx.getCurrentBindingsEntry();
      DCBindingContainer bindingsImpl = (DCBindingContainer)bindings;
      DCIteratorBinding dciter = null;
      dciter =
          bindingsImpl.findIteratorBinding("TransmittalReceiverListVO1Iterator");
      int ada = 0;
      Row[] rows = dciter.getAllRowsInRange();
      for (int i = 0; i < rows.length; i++) {
        if (rows[i].getAttribute("Receiver").toString().equals(iterBind.getCurrentRow().getAttribute("Username"))) {
          ada++;
        }
      }
      if (ada == 0) {
        Row temp = null;
        dciter =
            bindingsImpl.findIteratorBinding("ViewInternalRoleVO1Iterator");
        temp =
            dciter.getRowAtRangeIndex((Integer.parseInt(bindRole.getValue().toString())));
        System.out.println(getIsAddorChange());
        System.out.println("ROLE= " + temp);
        if (getIsAddorChange().equalsIgnoreCase("Add")) {
          ApplicationModule app =
            GetApplicationModule("AppModuleExternalTransmittalDataControl");
          try {
            //insert to participant
            ViewObject voPar = app.findViewObject("PheDswfPaticipantVO1");
            NameValuePairs nvpPar = new NameValuePairs();
            nvpPar.setAttribute("TransmittalId", transmittalId);
            nvpPar.setAttribute("Sender",Userlogin);
                               // GetSession().getAttribute("username").toString());
            nvpPar.setAttribute("Receiver",
                                iterBind.getCurrentRow().getAttribute("Username"));
            nvpPar.setAttribute("Role", temp.getAttribute("Code").toString());
            voPar.createAndInitRow(nvpPar);
            System.out.println("ROLE USER= " + temp.getAttribute("Code").toString());
            //inser to user security attributes
            if (IsRoleExist(iterBind.getCurrentRow().getAttribute("Username").toString(),
                            temp.getAttribute("Code").toString()) == "0") {
              ViewObject voUserRole =
                app.findViewObject("UserSecurityAttributesVO1");
              NameValuePairs nvpUserRole = new NameValuePairs();
              nvpUserRole.setAttribute("Dusername",
                                       iterBind.getCurrentRow().getAttribute("Username"));
              nvpUserRole.setAttribute("Dattributename", "role");
              nvpUserRole.setAttribute("Dattributetype",
                                       temp.getAttribute("Code").toString());
              nvpUserRole.setAttribute("Dattributeprivilege", "15");
              voUserRole.createAndInitRow(nvpUserRole);
            }
            //insert to log
            ViewObject voLog = app.findViewObject("PheDswfLog1");
            NameValuePairs nvpLog = new NameValuePairs();
            nvpLog.setAttribute("TransmittalId", transmittalId);
            nvpLog.setAttribute("Username",Userlogin);
                               // GetSession().getAttribute("username").toString());
            nvpLog.setAttribute("Action", "10");
            nvpLog.setAttribute("Description",
                                "Add User " + iterBind.getCurrentRow().getAttribute("Fullname"));
            voLog.createAndInitRow(nvpLog);
            AttributeBinding subject =
              (AttributeBinding)bindings.get("TransmittalSubject");
            AttributeBinding ProjectorganizationName =
              (AttributeBinding)bindings.get("ProjectorganizationName");
            String DocString =
              "<table style=\"font-family:arial\" cellpadding=\"3\" cellspacing=\"3\">\n" +
              "<tr style=\"background-color:#f0e68c\">\n" +
              "<td>Document No</td>\n" +
              "<td>Document Title</td>\n" +
              "<td>Revision No</td>\n" +
              "</tr>";
            DCIteratorBinding allDoc =
              (DCIteratorBinding)dcBindings.get("DetailInternalSupportinDocVO1Iterator");
            Row[] docs = allDoc.getAllRowsInRange();
            for (int doc = 0; doc < docs.length; doc++) {
              ViewObject voDocProcess = app.findViewObject("PheDocProcessVO1");
              NameValuePairs nvpDocProcess = new NameValuePairs();
              nvpDocProcess.setAttribute("Did", docs[doc].getAttribute("Did"));
              nvpDocProcess.setAttribute("TransmittalId", transmittalId);
              nvpDocProcess.setAttribute("Sender",Userlogin);
                                        // GetSession().getAttribute("username").toString());
              nvpDocProcess.setAttribute("Recipient",
                                         iterBind.getCurrentRow().getAttribute("Username"));
              nvpDocProcess.setAttribute("DocTitle",
                                         docs[doc].getAttribute("DocTitle").toString());
              nvpDocProcess.setAttribute("DocStatus",
                                         docs[doc].getAttribute("DocStatus").toString());
              nvpDocProcess.setAttribute("DocType", "Supporting");
              voDocProcess.createAndInitRow(nvpDocProcess);
              DocString =
                  DocString + "<tr style=\"background-color:#dcdcdc\">" +
                  "<td>" + docs[doc].getAttribute("DocNumber").toString() +
                  "</td>" + "<td>" +
                  docs[doc].getAttribute("DocTitle").toString() + "</td>" +
                  "<td>" + docs[doc].getAttribute("Revision").toString() +
                  "</td></tr>";
            }
            DocString = DocString + "</table>";
            String isi = "<html>\n" +
              "<head></head>\n" +
              "<body style=\"font-family:arial\">Dear Sir/Madam<br/><br/>You have received a new transmittal. The details are:<br/><br/>\n" +
              "<table><tr><td>Transmittal No</td><td>: " + transmittalId +
              "</td></tr>\n" +
              "<tr><td>Project/Organization</td><td>: " +
              ProjectorganizationName.getInputValue().toString() +
              "</td></tr>" + "<tr><td>Subject</td><td>: " +
              subject.getInputValue().toString() + "</td></tr>" +
              "</table><br/><br/>" + DocString + "<br/><br/>" +
              "Please see the transmittal in Inbox DSWF menu or click this <a href=\"" +
              getCurrentAddress() +
              "?IdcService=PHE_GET_TRANSMITTAL_INBOX&fromEmail=1&dUser=" +
              iterBind.

              getCurrentRow().getAttribute("Username") +
              "&sender=&dateType=&month=&year=&status=&category=&startRow=1&endRow=10&transmittalNo=" +
              transmittalId + "\">link</a>" +

              //                        "?IdcService=PHE_GET_TRANSMITTAL_DETAIL&transmittalID="+transmittalId+"\">link</a>"+
              "<br/>Thank you for your attention.</body></html>";
            //updated by VIN 300516 add cc DocControl
            OperationBinding sendEmail = bindings.getOperationBinding("SendEmailTo");
            Map paramEmail = sendEmail.getParamsMap();
            if (iterBind.getCurrentRow().getAttribute("Email") != null) {
              paramEmail.put("EmailTo", iterBind.getCurrentRow().getAttribute("Email").toString());
            } else {
              paramEmail.put("EmailTo", "owc.support@pertamina.com");
            }        
                          
            paramEmail.put("EmailCc", GetCreatorEmail()); //update by VIN 300516 add cc DocControl 
            paramEmail.put("Subject",
                           "["+CompanyApName.replaceAll("\\s+","")+"] New Transmittal for Transmittal  - " +
                           transmittalId);
            paramEmail.put("HtmlText", isi);
            try {
              sendEmail.execute();/*matikan email dulu 27082021*/
            } catch (Exception e) {
              e.printStackTrace();
            }
            app.getTransaction().commit();
          } catch (Exception e) {
            app.getTransaction().rollback();
            e.printStackTrace();
          }
        } else {
          ApplicationModule app =
            GetApplicationModule("AppModuleExternalTransmittalDataControl");
          try {
            DCIteratorBinding iterBindChange =
              (DCIteratorBinding)dcBindings.get("TransmittalReceiverListVO1Iterator");
            ViewObject voPar = app.findViewObject("PheDswfPaticipantVO1");
            voPar.setWhereClause("receiver = '" +
                                 iterBindChange.getCurrentRow().getAttribute("Receiver").toString() +
                                 "' and transmittal_id = '" + transmittalId +
                                 "'");
            voPar.executeQuery();
            while (voPar.hasNext()) {
              Row row = voPar.next();
              row.setAttribute("Receiver",
                               iterBind.getCurrentRow().getAttribute("Username"));
            }
            ViewObject voLog = app.findViewObject("PheDswfLog1");
            NameValuePairs nvpLog = new NameValuePairs();
            nvpLog.setAttribute("TransmittalId", transmittalId);
            nvpLog.setAttribute("Username",Userlogin);
                                //GetSession().getAttribute("username").toString());
            nvpLog.setAttribute("Action", "11");
            nvpLog.setAttribute("Description",
                                "Change User " + iterBindChange.getCurrentRow().getAttribute("Fullname") +
                                " to " +
                                iterBind.getCurrentRow().getAttribute("Fullname"));
            voLog.createAndInitRow(nvpLog);
            AttributeBinding subject =
              (AttributeBinding)bindings.get("TransmittalSubject");
            AttributeBinding ProjectorganizationName =
              (AttributeBinding)bindings.get("ProjectorganizationName");
            String DocString =
              "<table style=\"font-family:arial\" cellpadding=\"3\" cellspacing=\"3\">\n" +
              "<tr style=\"background-color:#f0e68c\">\n" +
              "<td>Document No</td>\n" +
              "<td>Document Title</td>\n" +
              "<td>Revision No</td>\n" +
              "</tr>";
            ViewObject voDocProcess = app.findViewObject("PheDocProcessVO1");
            voDocProcess.setWhereClause("recipient = '" +
                                        iterBindChange.getCurrentRow().getAttribute("Receiver").toString() +
                                        "' and transmittal_id = '" +
                                        transmittalId + "'");
            voDocProcess.executeQuery();
            while (voDocProcess.hasNext()) {
              Row row = voDocProcess.next();
              System.out.println(row.getAttribute("Recipient"));
              row.setAttribute("Recipient",
                               iterBind.getCurrentRow().getAttribute("Username"));
            }
            DCIteratorBinding allDoc =
              (DCIteratorBinding)dcBindings.get("DetailInternalSupportinDocVO1Iterator");
            Row[] docs = allDoc.getAllRowsInRange();
            for (int doc = 0; doc < docs.length; doc++) {
              DocString =
                  DocString + "<tr style=\"background-color:#dcdcdc\">" +
                  "<td>" + docs[doc].getAttribute("DocNumber").toString() +
                  "</td>" + "<td>" +
                  docs[doc].getAttribute("DocTitle").toString() + "</td>" +
                  "<td>" + docs[doc].getAttribute("Revision").toString() +
                  "</td></tr>";
            }
            DocString = DocString + "</table>";
            String isi = "<html>\n" +
              "<head></head>\n" +
              "<body style=\"font-family:arial\">Dear Sir/Madam<br/><br/>You have received a new transmittal. The details are:<br/><br/>\n" +
              "<table><tr><td>Transmittal No</td><td>:</td><td>" +
              transmittalId + "</td></tr>\n" +
              "<tr><td>Project/Organization</td><td>: " +
              ProjectorganizationName.getInputValue().toString() +
              "</td></tr>" + "<tr><td>Subject</td><td>: " +
              subject.getInputValue().toString() + "</td></tr>" +
              "</table><br/><br/>" + DocString + "<br/><br/>" +
              "Please see the transmittal in Inbox DSWF menu or click this <a href=\"" +
              getCurrentAddress() +
              //updated by nanda 14-12-2015
              "?IdcService=PHE_GET_TRANSMITTAL_INBOX&fromEmail=1&dUser=" +
              iterBind.getCurrentRow().getAttribute("Username") +
              "&sender=&dateType=&month=&year=&status=&category=&startRow=1&endRow=10&transmittalNo=" +
              transmittalId + "\">link</a>" +

              //                        "?IdcService=PHE_GET_TRANSMITTAL_DETAIL&transmittalID="+transmittalId+"\">link</a>"+
              // end of update
              "<br/>Thank you for your attention.</body></html>";
            OperationBinding sendEmail =
              bindings.getOperationBinding("SendEmail");
            Map paramEmail = sendEmail.getParamsMap();
            if (iterBind.getCurrentRow().getAttribute("Email") != null) {
              paramEmail.put("EmailTo",
                             iterBind.getCurrentRow().getAttribute("Email").toString());
            } else {
              paramEmail.put("EmailTo", "owc.support@pheonwj.pertamina.com");
            }
            paramEmail.put("Subject",
                           "[PHEONWJ] New Transmittal for Transmittal  - " +
                           transmittalId);
            paramEmail.put("HtmlText", isi);
            try {
              sendEmail.execute();/*matikan email dulu 27082021*/
            } catch (Exception e) {
              e.printStackTrace();
            }
            app.getTransaction().commit();
          } catch (Exception e) {
            app.getTransaction().rollback();
            e.printStackTrace();
          }
        }
        ApplicationModule app =
          GetApplicationModule("AppModuleExternalTransmittalDataControl");
        ViewObject voPar = app.findViewObject("TransmittalReceiverListVO1");
        voPar.executeQuery();
        bindRole.resetValue();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindRole);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindReceiver);
        ViewObject vo = app.findViewObject("DetailExternalSubmittalDoc1");
        DCBindingContainer dcBindings2 =
          (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterBindChange =
          (DCIteratorBinding)dcBindings2.get("TransmittalReceiverListVO1Iterator");
        vo.setWhereClause("Uploader = '" +
                          iterBindChange.getCurrentRow().getAttribute("Receiver").toString() +
                          "'");
        vo.executeQuery();
        AdfFacesContext.getCurrentInstance().addPartialTarget(tblSubmittal);
      } else {
        FacesMessage msg =
          new FacesMessage(FacesMessage.SEVERITY_WARN, "Selected user already in recipient list",
                           "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } else {

    }
    bindSearchNameUser.setValue("");
    ApplicationModule app =
      GetApplicationModule("AppModuleExternalTransmittalDataControl");
    ViewObject voParIn = app.findViewObject("UsersInternalVO1");
    voParIn.setWhereClause("upper(FULLNAME) like upper('%" +
                           bindSearchNameUser.getValue().toString() + "%')");
    voParIn.executeQuery();
    AdfFacesContext.getCurrentInstance().addPartialTarget(bindSearchNameUser);
    AdfFacesContext.getCurrentInstance().addPartialTarget(bindtableInternalUser);
  }

  public void setBindReceiver(RichSelectOneChoice bindReceiver) {
    this.bindReceiver = bindReceiver;
  }

  public RichSelectOneChoice getBindReceiver() {
    return bindReceiver;
  }

  public void ReceiverChange(ValueChangeEvent valueChangeEvent) {    
      ApplicationModule appModule =
      ADFUtils.getApplicationModuleForDataControl("AppModuleExternalTransmittalDataControl");
      DCBindingContainer dcBindings =
        (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    FacesContext contxt = FacesContext.getCurrentInstance();
        valueChangeEvent.getComponent().processUpdates(contxt);
        ViewObject vo =
            appModule.findViewObject("DetailExternalSubmittalDoc1");
        RichSelectOneChoice soc =
            (RichSelectOneChoice)valueChangeEvent.getComponent();
        Object value =
            ADFUtils.findIterator("TransmittalReceiverListVO1Iterator").getRowAtRangeIndex((Integer)soc.getValue()).getAttribute("Receiver");
        System.out.println("Value " + value.toString());
        String userReceiver=value == null ? "":value.toString();
        DCIteratorBinding iterBindChange =
      (DCIteratorBinding)dcBindings.get("TransmittalReceiverListVO1Iterator");
    vo.setWhereClause("Uploader = '" +
                      iterBindChange.getCurrentRow().getAttribute("Receiver").toString() +
                      "'");
    vo.executeQuery();
   ViewObject vo2 = appModule.findViewObject("ValidateDetailSupportingDocVo1");
    vo2.setWhereClause("TRANSMITTAL_ID = '"+AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString()+"'"+
    " AND RECIPIENT = '" + userReceiver+"'");
    vo2.executeQuery();
    
        if(vo2.getEstimatedRowCount()<2){
            ViewObject vo1 = appModule.findViewObject("DetailInternalSupportinDocVO1");
            vo1.setWhereClause("recipient = '" +
                                    userReceiver +
//                               iterBindChange.getCurrentRow().getAttribute("Receiver").toString() +
                               "' AND ACTION = '0'");
            vo1.executeQuery();
        }else{
            ViewObject vo1 = appModule.findViewObject("DetailInternalSupportinDocVO1");
            vo1.setWhereClause("recipient = '" +
                                userReceiver +
//                               iterBindChange.getCurrentRow().getAttribute("Receiver").toString() +
                               "' AND ACTION <> '0'");
            vo1.executeQuery();
        }
//    }
//    ViewObject vo1 = appModule.findViewObject("DetailInternalSupportinDocVO1");
//    vo1.setWhereClause("RECEIVER = '" +
//                       iterBindChange.getCurrentRow().getAttribute("Receiver").toString() +
//                       "'");
//    vo1.executeQuery();
    AdfFacesContext.getCurrentInstance().addPartialTarget(tblSupporting);
    AdfFacesContext.getCurrentInstance().addPartialTarget(tblSubmittal);

  }

  public void setTblSubmittal(RichTable tblSubmittal) {
    this.tblSubmittal = tblSubmittal;
  }

  public RichTable getTblSubmittal() {
    return tblSubmittal;
  }

  private static Object evaluateEL(String el) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ELContext elContext = facesContext.getELContext();
    ExpressionFactory expressionFactory =
      (ExpressionFactory)facesContext.getApplication().getExpressionFactory();
    ValueExpression exp =
      expressionFactory.createValueExpression(elContext, el, Object.class);
    return exp.getValue(elContext);
  }

  public void CheckBoxSubmittal(ValueChangeEvent valueChangeEvent) {
    //nanda
    int count = 0;
    if (valueChangeEvent.getNewValue().toString().toUpperCase().equals("TRUE")) {
      System.out.println("select all");

      DCBindingContainer dcBindings =
        (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
      JUCtrlHierBinding table =
        (JUCtrlHierBinding)dcBindings.getControlBinding("DetailExternalSubmittalDoc1");

      //nanda 3012
      DCBindingContainer dcb = (DCBindingContainer)evaluateEL("#{bindings}");
      DCIteratorBinding dciter =
        dcb.findIteratorBinding("DetailExternalSubmittalDoc1Iterator");

      ViewObject vo = dciter.getViewObject();
      int i = 0;
      Row row = null;
      vo.reset();
      while (vo.hasNext()) {
        if (i == 0)
          row = vo.first();
        else
          row = vo.next();


        row.setAttribute("Mark1", true);
        count++;
        i++;
      }


    } else {
      System.out.println("deselect all");

      //nanda 3012
      DCBindingContainer dcb = (DCBindingContainer)evaluateEL("#{bindings}");
      DCIteratorBinding dciter =
        dcb.findIteratorBinding("DetailExternalSubmittalDoc1Iterator");

      ViewObject vo = dciter.getViewObject();
      int i = 0;
      Row row = null;
      vo.reset();
      while (vo.hasNext()) {
        if (i == 0)
          row = vo.first();
        else
          row = vo.next();


        row.setAttribute("Mark1", false);

        i++;
      }

    }

    AdfFacesContext.getCurrentInstance().addPartialTarget(tblSubmittal);
    //nanda 2912
    System.out.println("count all:" + count);

    getCountCheckBox().setValue(count);

    AdfFacesContext.getCurrentInstance().addPartialTarget(getCountCheckBox());
  }

  public void setAll(String All) {
    this.All = All;
  }

  public String getAll() {
    return All;
  }

  public oracle.jbo.domain.Date getNowDate(java.util.Date pJavaDate) {
    return new oracle.jbo.domain.Date(new Timestamp(pJavaDate.getTime()));
  }

  public void Finish(DialogEvent dialogEvent) {
    if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
      //            oracle.jbo.domain.Date nowDate = getNowDate(new Date());
      String isPublish = "No";
      ApplicationModule app =
        GetApplicationModule("AppModuleExternalTransmittalDataControl");
      try {
        ViewObject myVoPar = app.findViewObject("PheDswfMasterVO1");
        String query =
          "TRANSMITTAL_ID = '" + AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString() +
          "'";
        myVoPar.setWhereClause(query);
        myVoPar.executeQuery();
        while (myVoPar.hasNext()) {
          Row row = myVoPar.next();
          row.setAttribute("TransmittalStatus", "Completed");
          row.setAttribute("FinishReason",
                           bindFinishReason.getValue().toString());
          isPublish = row.getAttribute("ProjectorganizationName").toString();
        }

        if (!isPublish.equals("Publish")) {
          ViewObject voPublish = app.findViewObject("PhdDswfDocVo1");
          query =
              "TRANSMITTAL_ID = '" + AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString() +
              "' and ID in (" + GetPublishId() + ")";
          voPublish.setWhereClause(query);
          voPublish.executeQuery();
          while (voPublish.hasNext()) {
            Row row1 = voPublish.next();
            row1.setAttribute("PublishStatus", "1");
          }
        } else {
          ViewObject voPublish = app.findViewObject("PhdDswfDocVo1");
          query =
              "TRANSMITTAL_ID = '" + AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString() +
              "' and ID in (" + GetDocPublishing() + ")";
          voPublish.setWhereClause(query);
          voPublish.executeQuery();
          while (voPublish.hasNext()) {
            Row row1 = voPublish.next();
            row1.setAttribute("PublishStatus", "3");
          }
        }


        ViewObject voLog = app.findViewObject("PheDswfLog1");
        NameValuePairs nvpLog = new NameValuePairs();
        nvpLog.setAttribute("TransmittalId",
                            AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString());
        nvpLog.setAttribute("Username",
                            AdfFacesContext.getCurrentInstance().getPageFlowScope().get("dUser").toString());
        nvpLog.setAttribute("Action", "3");
        nvpLog.setAttribute("Description",
                            "Finish Transmittal " + AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString());
        voLog.createAndInitRow(nvpLog);

        System.out.println("Transmittal " +
                           AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString() +
                           " has been finished");
        app.getTransaction().commit();
        bindBtnAbort.setDisabled(true);
        bindBtnFinish.setDisabled(true);
        bindButtonAddUser.setDisabled(true);
        bindButtonChangeUser.setDisabled(true);
        bindButtonEditDuedate.setDisabled(true);
        bindButtonDeleteUser.setDisabled(true);
          
          bindBtnFinish.setVisible(false);  
          bindBtnAbort.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindBtnAbort);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindBtnFinish);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindButtonAddUser);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindButtonChangeUser);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindButtonEditDuedate);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindButtonDeleteUser);
      } catch (Exception e) {
        e.printStackTrace();
        app.getTransaction().rollback();
      }

    }
  }

  public void Abort(DialogEvent dialogEvent) {
    if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
      oracle.jbo.domain.Date nowDate = getNowDate(new Date());
      ApplicationModule app =
        GetApplicationModule("AppModuleExternalTransmittalDataControl");
      try {
        ViewObject myVoPar = app.findViewObject("PheDswfMasterVO1");
        String query =
          "TRANSMITTAL_ID = '" + AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString() +
          "'";
        myVoPar.setWhereClause(query);
        myVoPar.executeQuery();
        while (myVoPar.hasNext()) {
          Row row = myVoPar.next();
          row.setAttribute("TransmittalStatus", "Aborted");
          row.setAttribute("AbortedReason",
                           bindAbortReason.getValue().toString());
        }

        ViewObject voLog = app.findViewObject("PheDswfLog1");
        NameValuePairs nvpLog = new NameValuePairs();
        nvpLog.setAttribute("TransmittalId",
                            AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString());
        nvpLog.setAttribute("Username",
                            AdfFacesContext.getCurrentInstance().getPageFlowScope().get("dUser").toString());
        nvpLog.setAttribute("Action", "6");
        nvpLog.setAttribute("Description",
                            "Abort Transmittal " + AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString());
        voLog.createAndInitRow(nvpLog);

        System.out.println("Transmittal " +
                           AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString() +
                           " has been aborted");
        app.getTransaction().commit();
        bindBtnAbort.setDisabled(true);
        bindBtnFinish.setDisabled(true);
        bindButtonAddUser.setDisabled(true);
        bindButtonChangeUser.setDisabled(true);
        bindButtonEditDuedate.setDisabled(true);
          
          bindBtnFinish.setVisible(false);
          bindBtnAbort.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindBtnAbort);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindBtnFinish);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindButtonAddUser);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindButtonChangeUser);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindButtonEditDuedate);
      } catch (Exception e) {
        e.printStackTrace();
        app.getTransaction().rollback();
      }

    }
  }

  public void setBindAbortReason(RichInputText bindAbortReason) {
    this.bindAbortReason = bindAbortReason;
  }

  public RichInputText getBindAbortReason() {
    return bindAbortReason;
  }

  public void setBindFinishReason(RichInputText bindFinishReason) {
    this.bindFinishReason = bindFinishReason;
  }

  public RichInputText getBindFinishReason() {
    return bindFinishReason;
  }

  public void setBindBtnFinish(RichButton bindBtnFinish) {
    this.bindBtnFinish = bindBtnFinish;
  }

  public RichButton getBindBtnFinish() {
    return bindBtnFinish;
  }

  public void setBindBtnAbort(RichButton bindBtnAbort) {
    this.bindBtnAbort = bindBtnAbort;
  }

  public RichButton getBindBtnAbort() {
    return bindBtnAbort;
  }

  public void setTblSupporting(RichTable tblSupporting) {
    this.tblSupporting = tblSupporting;
  }

  public RichTable getTblSupporting() {
    return tblSupporting;
  }

  public void PopUpFetchDistributionHistory(PopupFetchEvent popupFetchEvent) {
    DCBindingContainer bindings =
      (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    OperationBinding obHistoryNumber =
      bindings.getOperationBinding("ExecuteHistoryDocNumber");
    DCIteratorBinding supportingDocIterator =
      (DCIteratorBinding)bindings.get("DetailInternalSupportinDocVO1Iterator");
    Row supportingDocCurrentRow = supportingDocIterator.getCurrentRow();
    obHistoryNumber.getParamsMap().put("docNumber",
                                       supportingDocCurrentRow.getAttribute("DocNumber"));
    obHistoryNumber.execute();
  }

  public void CheckBoxSupporting(ValueChangeEvent valueChangeEvent) {
    if (valueChangeEvent.getNewValue().equals(true)) {
      System.out.println("select all");
      setAllSupporting("yes");
      DCBindingContainer dcBindings =
        (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
      JUCtrlHierBinding table =
        (JUCtrlHierBinding)dcBindings.getControlBinding("DetailInternalSupportinDocVO1");
      Row[] rows = table.getAllRowsInRange();
      for (int i = 0; i < rows.length; i++) {
        Row row = rows[i];
        row.setAttribute("Mark", true);
      }
    } else {
      setAllSupporting("no");
      System.out.println("deselect all");
      DCBindingContainer dcBindings =
        (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
      JUCtrlHierBinding table =
        (JUCtrlHierBinding)dcBindings.getControlBinding("DetailInternalSupportinDocVO1");
      Row[] rows = table.getAllRowsInRange();
      for (int i = 0; i < rows.length; i++) {
        Row row = rows[i];
        row.setAttribute("Mark", false);
      }
    }
    AdfFacesContext.getCurrentInstance().addPartialTarget(tblSupporting);
  }

  public void setAllSupporting(String AllSupporting) {
    this.AllSupporting = AllSupporting;
  }

  public String getAllSupporting() {
    return AllSupporting;
  }

  public void Forward(ActionEvent actionEvent) {
    fowardedDoc.clear();
    fowardedDoc = new ArrayList<TempDocuments>();
    DCBindingContainer dcBindings =
      (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    DCIteratorBinding iterBind =
      (DCIteratorBinding)dcBindings.get("DetailInternalSupportinDocVO1Iterator");
    Row[] rows = iterBind.getAllRowsInRange();
    String did = "";
    String dDocname = "";
    String docType = "";
    String docFormat = "";
    String documentName = "";
    String documentNumber = "";
    String documentTitle = "";
    String revision = "";
    String documentStatus = "";
    String pages = "";
    String remarks = "";
    String distribution = "";
    for (int i = 0; i < rows.length; i++) {
      if (rows[i].getAttribute("Mark") != null) {
        if (rows[i].getAttribute("Mark").equals(true)) {
          did = rows[i].getAttribute("Id").toString();
          dDocname =
              rows[i].getAttribute("ContentId") != null ? rows[i].getAttribute("ContentId").toString() :
              "";
          docType = rows[i].getAttribute("DocType").toString();
          docFormat =
              rows[i].getAttribute("Format") != null ? rows[i].getAttribute("Format").toString() :
              "";
          documentName =
              rows[i].getAttribute("DocName") != null ? rows[i].getAttribute("DocName").toString() :
              "";
          documentNumber =
              rows[i].getAttribute("DocNumber") != null ? rows[i].getAttribute("DocNumber").toString() :
              "";
          documentTitle =
              rows[i].getAttribute("DocTitle") != null ? rows[i].getAttribute("DocTitle").toString() :
              "";
          revision =
              rows[i].getAttribute("Revision") != null ? rows[i].getAttribute("Revision").toString() :
              "";
          documentStatus = "IFR";
          pages =
              rows[i].getAttribute("Pages") != null ? rows[i].getAttribute("Pages").toString() :
              "1";
          remarks = "";
          distribution = rows[i].getAttribute("DocSource").toString();
          fowardedDoc.add(new TempDocuments(did, dDocname, docType, docFormat,
                                            documentName, documentNumber,
                                            documentTitle, revision,
                                            documentStatus, pages, remarks,
                                            distribution));
        }
      }
    }
    iterBind =
        (DCIteratorBinding)dcBindings.get("DetailExternalSubmittalDoc1Iterator");
    rows = iterBind.getAllRowsInRange();
    for (int i = 0; i < rows.length; i++) {
      if (rows[i].getAttribute("Mark1") != null) {
        if (rows[i].getAttribute("Mark1").equals(true)) {
          did = rows[i].getAttribute("Id").toString();
          dDocname =
              rows[i].getAttribute("ContentId") != null ? rows[i].getAttribute("ContentId").toString() :
              "";
          docType = rows[i].getAttribute("DocType").toString();
          docFormat =
              rows[i].getAttribute("Format") != null ? rows[i].getAttribute("Format").toString() :
              "";
          documentName =
              rows[i].getAttribute("DocName") != null ? rows[i].getAttribute("DocName").toString() :
              "";
          documentNumber =
              rows[i].getAttribute("DocNumber") != null ? rows[i].getAttribute("DocNumber").toString() :
              "";
          documentTitle =
              rows[i].getAttribute("DocTitle") != null ? rows[i].getAttribute("DocTitle").toString() :
              "";
          revision =
              rows[i].getAttribute("Revision") != null ? rows[i].getAttribute("Revision").toString() :
              "";
          documentStatus =
              rows[i].getAttribute("DocStatus") != null ? rows[i].getAttribute("DocStatus").toString() :
              "IFR";
          pages =
              rows[i].getAttribute("Pages") != null ? rows[i].getAttribute("Pages").toString() :
              "1";
          remarks = "";
          distribution = rows[i].getAttribute("DocSource").toString();
          fowardedDoc.add(new TempDocuments(did, dDocname, "Supporting",
                                            docFormat, documentName,
                                            documentNumber, documentTitle,
                                            revision, documentStatus, pages,
                                            remarks, distribution));
        }
      }
    }
    if (fowardedDoc.size() > 0) {
      RichPopup.PopupHints ph = new RichPopup.PopupHints();
      bindPopUpForward.show(ph);
    } else {
      FacesMessage msg =
        new FacesMessage(FacesMessage.SEVERITY_WARN, "You should choose file(s) to initiate forward transmittal",
                         "");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    }
  }

  public void setFowardedDoc(List<TempDocuments> fowardedDoc) {
    this.fowardedDoc = fowardedDoc;
  }

  public List<TempDocuments> getFowardedDoc() {
    return fowardedDoc;
  }

  public void setBindPopUpForward(RichPopup bindPopUpForward) {
    this.bindPopUpForward = bindPopUpForward;
  }

  public RichPopup getBindPopUpForward() {
    return bindPopUpForward;
  }

  public void ForwardTransmittal(DialogEvent dialogEvent) {
    if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
      String transmittalId =
        AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString();
      System.out.println("Close transmittal when forward : ");
//                         bindCloseTransmittal.getValue()==null ? "":bindCloseTransmittal.getValue().toString());
      System.out.println("--transmittalID:" + transmittalId);
      BindingContainer bindings =
        BindingContext.getCurrent().getCurrentBindingsEntry();
      OperationBinding method =
        bindings.getOperationBinding("ExecuteGetFguid");
      Map paramsMap = method.getParamsMap();
      paramsMap.put("folderName", transmittalId);
      method.execute();
      AttributeBinding Ffolderguid =
        (AttributeBinding)bindings.get("Ffolderguid");
      RIDCClass ridc =
        new RIDCClass(getWeblogicusername(), getWeblogicpassword());
      try {
        System.out.println("--Ffolderguid:" +
                           Ffolderguid.getInputValue().toString());
        String path =
          ridc.GetFolderPathFromGuid(Ffolderguid.getInputValue().toString());
        System.out.println("path dswf forward:" + path);
        if (path.contains("Non Routine")) {
          path = path.substring(0, path.lastIndexOf("/"));
        } else {
          for (int i = 0; i < 2; i++) {
            path = path.substring(0, path.lastIndexOf("/"));
          }
        }
        //update by nanda - 101114
          String CloseTransmittal=bindCloseTransmittal.getValue()==null ? "":bindCloseTransmittal.getValue().toString();
        if (CloseTransmittal.equalsIgnoreCase("true")) {
          ApplicationModule app =
            GetApplicationModule("AppModuleExternalTransmittalDataControl");
          try {
            ViewObject myVoPar = app.findViewObject("PheDswfMasterVO1");
            String query =
              "TRANSMITTAL_ID = '" + AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString() +
              "'";

            System.out.println("ClosetTransmittal:query:" + query);
            myVoPar.setWhereClause(query);
            myVoPar.executeQuery();
            while (myVoPar.hasNext()) {
              Row row = myVoPar.next();
              row.setAttribute("TransmittalStatus", "Completed");
            }

            ViewObject voLog = app.findViewObject("PheDswfLog1");
            NameValuePairs nvpLog = new NameValuePairs();
            nvpLog.setAttribute("TransmittalId",
                                AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString());
            nvpLog.setAttribute("Username",
                                AdfFacesContext.getCurrentInstance().getPageFlowScope().get("dUser").toString());
            nvpLog.setAttribute("Action", "3");
            nvpLog.setAttribute("Description",
                                "Finish Transmittal " + AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString());
            voLog.createAndInitRow(nvpLog);

            System.out.println("Transmittal " +
                               AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString() +
                               " has been finished");
            app.getTransaction().commit();
          } catch (Exception e) {
            app.getTransaction().rollback();
          }
        }
        DCBindingContainer dcBindings =
          (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterBind =
          (DCIteratorBinding)dcBindings.get("ForwardSelectionVo1Iterator");
        System.out.println(iterBind.getCurrentRow().getAttribute("Code"));
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (iterBind.getCurrentRow().getAttribute("Code").equals("Internal")) {
          try {
            HttpServletRequest request =
              (HttpServletRequest)ctx.getExternalContext().getRequest();
            //update code variabel company lahir 
            String company= request.getParameter("companyap");
            HttpSession session = request.getSession(true);
            session.setAttribute("forwardedItem", fowardedDoc);
            ctx.getExternalContext().redirect(getDswfAddress() +
                                              "CreateTransmittalInternalForm.jspx?path=" +
                                              path+"&companyap="+CompanyApName+"&dUser="+Userlogin);
            //                        ctx.getExternalContext().redirect("http://localhost:7101/ExternalTransmittal-ViewController-context-root/faces/Pages/CreateTransmittalInternalForm.jspx?path="+path);
          } catch (IOException e) {
            e.printStackTrace();
          }
        } else {
          try {
            HttpServletRequest request =
              (HttpServletRequest)ctx.getExternalContext().getRequest();
              //update code variabel company lahir 
            String company= request.getParameter("companyap");
            HttpSession session = request.getSession(true);
            session.setAttribute("forwardedItem", fowardedDoc);
            ctx.getExternalContext().redirect(getDswfAddress() +
                                              "CreateTransmittalExternalForm.jspx?path=" +
                                              path+"&companyap="+CompanyApName+"&dUser="+Userlogin);
            //                        ctx.getExternalContext().redirect("http://localhost:7101/ExternalTransmittal-ViewController-context-root/faces/Pages/CreateTransmittalExternalForm.jspx?path="+path);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {

    }
  }

  public void setBindCloseTransmittal(RichSelectBooleanCheckbox bindCloseTransmittal) {
    this.bindCloseTransmittal = bindCloseTransmittal;
  }

  public RichSelectBooleanCheckbox getBindCloseTransmittal() {
    return bindCloseTransmittal;
  }

  public void setBindButtonChangeUser(RichButton bindButtonChangeUser) {
    this.bindButtonChangeUser = bindButtonChangeUser;
  }

  public RichButton getBindButtonChangeUser() {
    return bindButtonChangeUser;
  }

  public void setBindButtonAddUser(RichButton bindButtonAddUser) {
    this.bindButtonAddUser = bindButtonAddUser;
  }

  public RichButton getBindButtonAddUser() {
    return bindButtonAddUser;
  }
  
  public void setBindButtonDeleteUser(RichButton bindButtonDeleteUser){
    this.bindButtonDeleteUser = bindButtonDeleteUser;
  }
  
  public RichButton getBindButtonDeleteUser(){
    return bindButtonDeleteUser;
  }

  public static Object invokeEL(String el, Class[] paramTypes,
                                Object[] params) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ELContext elContext = facesContext.getELContext();
    ExpressionFactory expressionFactory =
      facesContext.getApplication().getExpressionFactory();
    MethodExpression exp =
      expressionFactory.createMethodExpression(elContext, el, Object.class,
                                               paramTypes);

    return exp.invoke(elContext, params);
  }

  public void SupportingSelection(SelectionEvent selectionEvent) {
    this.invokeEL("#{bindings.DetailInternalSupportinDocVO1.collectionModel.makeCurrent}",
                  new Class[] { SelectionEvent.class },
                  new Object[] { selectionEvent });
    System.out.println("selection");
  }

  public void setCurrentAddress(String currentAddress) {
    this.currentAddress = currentAddress;
  }

  public String getCurrentAddress() {
    return GetConfig("RIDC_URL");
  }
  
  public String getTDCEmail(){
    return GetConfig("TDC_EMAIL_CC");    
  }
  
  public void setTDCEmail(String TDCEMail){
    this.TDCEMail = TDCEMail;
  }
  
  public String GetCreatorName() { //updated by VINO
    ApplicationModule am = ADFUtils.getApplicationModuleForDataControl("AppModuleExternalTransmittalDataControl");
    ViewObject voPar = am.findViewObject("PheDswfMasterVO1");
    voPar.setWhereClause("transmittal_id = '" + AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString() + "'");
    voPar.executeQuery();
    
    String username = "";
    while (voPar.hasNext()){
        Row row = voPar.next();
        username = row.getAttribute("TransmittalCreator").toString();
    } 
        return username;
  }
  
  public String  GetCreatorEmail(){ //updated by VINO
    ApplicationModule am = ADFUtils.getApplicationModuleForDataControl("AppModuleExternalTransmittalDataControl");
    ViewObject voGetPar = am.findViewObject("UsersInternalVO1");
    voGetPar.setWhereClause("Username = '" + GetCreatorName().toString() + "'");
    System.out.println("USERNAME = " + GetCreatorName().toString());
    voGetPar.executeQuery();
//    System.out.println("-=-=-=-=-=-=-=-= full query:"+voGetPar.getQuery());
//    System.out.println("-=-=-=-=-=-=-=-= count:"+voGetPar.getAllRowsInRange().length);
    
    String email= "";
    if(voGetPar.getAllRowsInRange().length > 0){
//        Row row = voGetPar.next();
//        email = row.getAttribute("Email").toString();
        email = voGetPar.getCurrentRow().getAttribute("Email")==null ? "":voGetPar.getCurrentRow().getAttribute("Email").toString();
        System.out.println("EMAIL = " + email);
    }else{
        System.out.println("EMAIL CREATOR IS NULL");
    }
//    while (voGetPar.hasNext()) {
//        Row row = voGetPar.next();
//        email = row.getAttribute("Email").toString();
//        System.out.println("EMAIL = " + email);
//    }    
     return email;
  }
  
  public void setWeblogicusername(String weblogicusername) {
    this.weblogicusername = weblogicusername;
  }

  public String getWeblogicusername() {
    return GetConfig("WEBLOGIC_USER");
  }

  public void setWeblogicpassword(String weblogicpassword) {
    this.weblogicpassword = weblogicpassword;
  }

  public String getWeblogicpassword() {
    return GetConfig("WEBLOGIC_PASS");
  }

  public void setDswfAddress(String dswfAddress) {
    this.dswfAddress = dswfAddress;
  }

  public String getDswfAddress() {
    return GetConfig("PATH_DSWF");
  }

  public void DownloadSupportingDoc(FacesContext facesContext,
                                    OutputStream outputStream) throws IOException {

    DCBindingContainer dcBindings =
      (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    DCIteratorBinding iterBind =
      (DCIteratorBinding)dcBindings.get("DetailInternalSupportinDocVO1Iterator");
    Row[] rows = iterBind.getAllRowsInRange();
    String did = "";
    RIDCClass ridc =
      new RIDCClass(getWeblogicusername(), getWeblogicpassword());
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    ZipOutputStream zos = new ZipOutputStream(os);
    for (int i = 0; i < rows.length; i++) {
      if (rows[i].getAttribute("Mark") != null) {
        if (rows[i].getAttribute("Mark").equals(true) &&
            rows[i].getAttribute("DocSource").toString().equals("Electronic")) {
          did = rows[i].getAttribute("Id").toString();
          String fileName =
            ridc.GetDocInfo(did).get("dOriginalName").toString();
          try {
            this.addToZipFileWithInputStream(fileName,
                                             ridc.getNewFileInputStreamWithDID(did),
                                             zos);
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          } catch (IdcClientException e) {
            e.printStackTrace();
          }
        }
      }
    }
    zos.closeEntry();
    zos.close();
    try {
      byte[] buf = os.toByteArray();

      System.out.println("Downloading file");
      if (buf.length > 0) {
        try {
          BufferedOutputStream bos = new BufferedOutputStream(outputStream);
          bos.write(buf);
          bos.flush();
          bos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    } catch (Exception e) {
      System.out.println("Error getinputstream file " + e.getMessage());
    }
  }

  public void addToZipFileWithInputStream(String fileName, InputStream is,
                                          ZipOutputStream zos) throws FileNotFoundException,
                                                                      IOException {

    ZipEntry zipEntry = new ZipEntry(fileName);
    zos.putNextEntry(zipEntry);

    byte[] bytes = new byte[1024];
    int length;
    while ((length = is.read(bytes)) >= 0) {
      zos.write(bytes, 0, length);
    }

    zos.closeEntry();
    is.close();
  }


  public void setZipName(String zipName) {
    this.zipName = zipName;
  }

  public String getZipName() {
    return zipName;
  }

  public void setIsPublish(String isPublish) {
    this.isPublish = isPublish;
  }

  public String getIsPublish() {
    return isPublish;
  }

  //    public void setCountCheckBox(RichOutputText countCheckBox) {
  //        this.countCheckBox = countCheckBox;
  //    }
  //
  //    public RichOutputText getCountCheckBox() {
  //        return countCheckBox;
  //    }

  public RichOutputText getCountCheckBox() {
    if (countCheckBox != null) {
      return (RichOutputText)countCheckBox.getComponent();
    }
    return null;
  }

  public void setCountCheckBox(RichOutputText countCheckBox) {
    this.countCheckBox =
        ComponentReference.newUIComponentReference(countCheckBox);
  }

  public void countCheckBox1(ValueChangeEvent valueChangeEvent) {
    // Add event code here...
    int countVar = 1;
    if (valueChangeEvent.getNewValue().toString().toUpperCase().equals("TRUE")) {
      DCBindingContainer dcBindings =
        (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
      JUCtrlHierBinding table =
        (JUCtrlHierBinding)dcBindings.getControlBinding("DetailExternalSubmittalDoc1");
      //                Row[] rows = table.getAllRowsInRange();
      //                System.out.println("rows.length:"+rows.length);
      //                for (int i = 0; i < rows.length; i++) {
      //                    Row row = rows[i];
      //                    if(row.getAttribute("Mark1").equals(true)){
      //                        countVar++;
      //                    }
      //                }

      //nanda 3012
      DCBindingContainer dcb = (DCBindingContainer)evaluateEL("#{bindings}");
      DCIteratorBinding dciter =
        dcb.findIteratorBinding("DetailExternalSubmittalDoc1Iterator");

      ViewObject vo = dciter.getViewObject();
      int i = 0;
      Row row = null;
      vo.reset();
      while (vo.hasNext()) {
        if (i == 0)
          row = vo.first();
        else
          row = vo.next();


        if (row.getAttribute("Mark1").equals(true)) {
          countVar++;
        }
        i++;
      }

    } else {
      DCBindingContainer dcBindings =
        (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
      JUCtrlHierBinding table =
        (JUCtrlHierBinding)dcBindings.getControlBinding("DetailExternalSubmittalDoc1");
      //            Row[] rows = table.getAllRowsInRange();
      //            System.out.println("rows.length:"+rows.length);
      //            for (int i = 0; i < rows.length; i++) {
      //                Row row = rows[i];
      //                if(row.getAttribute("Mark1").equals(true)){
      //                    countVar++;
      //                }
      //            }
      //nanda 3012
      DCBindingContainer dcb = (DCBindingContainer)evaluateEL("#{bindings}");
      DCIteratorBinding dciter =
        dcb.findIteratorBinding("DetailExternalSubmittalDoc1Iterator");

      ViewObject vo = dciter.getViewObject();
      int i = 0;
      Row row = null;
      vo.reset();
      while (vo.hasNext()) {
        if (i == 0)
          row = vo.first();
        else
          row = vo.next();


        if (row.getAttribute("Mark1").equals(true)) {
          countVar++;
        }
        i++;
      }
      System.out.println("deselect");
      System.out.println("countVar: " + countVar);
      //        if(countVar>rows.length){
      //        System.out.println("countVar-rows.length>0");
      countVar = countVar - 2;
      //        }
    }
    System.out.println("count detail summary:" + countVar);
    getCountCheckBox().setValue(countVar);
    AdfFacesContext.getCurrentInstance().addPartialTarget(getCountCheckBox());
  }

  public void setBindPopupDuedate(RichPopup bindPopupDuedate) {
    this.bindPopupDuedate = bindPopupDuedate;
  }

  public RichPopup getBindPopupDuedate() {
    return bindPopupDuedate;
  }

  public void EditDueDate(ActionEvent actionEvent) {
    RichPopup.PopupHints ph = new RichPopup.PopupHints();
    bindPopupDuedate.show(ph);
  }

  public void setBindButtonEditDuedate(RichButton bindButtonEditDuedate) {
    this.bindButtonEditDuedate = bindButtonEditDuedate;
  }

  public RichButton getBindButtonEditDuedate() {
    return bindButtonEditDuedate;
  }

  public void setBindTransmittalDueDate(RichInputDate bindTransmittalDueDate) {
    this.bindTransmittalDueDate = bindTransmittalDueDate;
  }

  public RichInputDate getBindTransmittalDueDate() {
    return bindTransmittalDueDate;
  }

  public oracle.jbo.domain.Date GetNewDate(java.util.Date pJavaDate) throws ParseException {
    SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String tempDate =
      formater.format(pJavaDate).replace("00:00:00", "23:59:59");
    return new oracle.jbo.domain.Date(new Timestamp(formater.parse(tempDate).getTime()));
  }

  public void DialogLsnrUpdateDuedate(DialogEvent dialogEvent) {
    // Add event code here...
    //nanda 29022016
    if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
      String transmittalId =
        AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString();
      java.util.Date utilDate =
        (java.util.Date)bindTransmittalDueDate.getValue();
      SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
      String tempDate =
        formater.format(utilDate).replace("00:00:00", "23:59:59");
      DCBindingContainer dcBindings =
        (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();

//      DCIteratorBinding iterBind =
//        (DCIteratorBinding)dcBindings.get("UsersInternalVO1Iterator");
      BindingContext bindingctx = BindingContext.getCurrent();
      BindingContainer bindings = bindingctx.getCurrentBindingsEntry();
      ApplicationModule app =
        GetApplicationModule("AppModuleExternalTransmittalDataControl");
      try {

//        DCIteratorBinding iterBindChange =
//          (DCIteratorBinding)dcBindings.get("TransmittalReceiverListVO1Iterator");
        ViewObject voPar = app.findViewObject("PheDswfMasterVO1");
        voPar.setWhereClause("transmittal_id = '" + transmittalId + "'");

        //update duedate
        voPar.executeQuery();
        while (voPar.hasNext()) {
          Row row = voPar.next();
          row.setAttribute("TransmittalDuedate",
                           GetNewDate(formater.parse(tempDate)));
        }

        //insert phe_dswf_log
        ViewObject voLog = app.findViewObject("PheDswfLog1");
        NameValuePairs nvpLog = new NameValuePairs();
        nvpLog.setAttribute("TransmittalId", transmittalId);
        nvpLog.setAttribute("Username",Userlogin);
                            //GetSession().getAttribute("username").toString());
        nvpLog.setAttribute("Action", "11");
        nvpLog.setAttribute("Description", "Update due date to " + tempDate);
        voLog.createAndInitRow(nvpLog);

        //send email notification
        AttributeBinding subject =
          (AttributeBinding)bindings.get("TransmittalSubject");
        AttributeBinding ProjectorganizationName =
          (AttributeBinding)bindings.get("ProjectorganizationName");


        String DocString =
          "<table style=\"font-family:arial\" cellpadding=\"3\" cellspacing=\"3\">\n" +
          "<tr style=\"background-color:#f0e68c\">\n" +
          "<td>Document No</td>\n" +
          "<td>Document Title</td>\n" +
          "<td>Revision No</td>\n" +
          "</tr>";
        ViewObject voDocProcess = app.findViewObject("PheDswfPaticipantVO1");
        voDocProcess.setWhereClause("transmittal_id = '" +
                                    transmittalId + "'");
        voDocProcess.executeQuery();
        while (voDocProcess.hasNext()) {
          Row row = voDocProcess.next();
          System.out.println("--username recieiver"+row.getAttribute("Receiver"));
         // //                                    row.setAttribute("Recipient",iterBind.getCurrentRow().getAttribute("Username"));


          DCIteratorBinding allDoc =
            (DCIteratorBinding)dcBindings.get("DetailInternalSupportinDocVO1Iterator");
          Row[] docs = allDoc.getAllRowsInRange();
          for (int doc = 0; doc < docs.length; doc++) {
            DocString =
                DocString + "<tr style=\"background-color:#dcdcdc\">" + "<td>" +
                docs[doc].getAttribute("DocNumber").toString() + "</td>" +
                "<td>" + docs[doc].getAttribute("DocTitle").toString() +
                "</td>" + "<td>" +
                docs[doc].getAttribute("Revision").toString() + "</td></tr>";
          }
          DocString = DocString + "</table>";
          String isi = "<html>\n" +
            "<head></head>\n" +
            "<body style=\"font-family:arial\">Dear Sir/Madam<br/><br/>Your transmittal have been updated. The details are:<br/><br/>\n" +
            "<table><tr><td>Transmittal No</td><td>:</td><td>" +
            transmittalId + "</td></tr>\n" +
            "<tr><td>Project/Organization</td><td>: " +
            ProjectorganizationName.getInputValue().toString() + "</td></tr>" +
            "<tr><td>Subject</td><td>: " + subject.getInputValue().toString() +
            "</td></tr>" +
             "<tr><td>Due Date</td><td>:"+ tempDate+ "</td></tr>" +
            "</table><br/><br/>" + DocString + "<br/><br/>" +
            "Please see the transmittal in Inbox DSWF menu or click this <a href=\"" +
            getCurrentAddress() +

            //updated by nanda 14-12-2015
            "?IdcService=PHE_GET_TRANSMITTAL_INBOX&fromEmail=1&dUser=" +
            row.getAttribute("Receiver") +
            "&sender=&dateType=&month=&year=&status=&category=&startRow=1&endRow=10&transmittalNo=" +
            transmittalId + "\">link</a>" +

            //                        "?IdcService=PHE_GET_TRANSMITTAL_DETAIL&transmittalID="+transmittalId+"\">link</a>"+
            // end of update
            "<br/>Thank you for your attention.</body></html>";
          OperationBinding sendEmail =
            bindings.getOperationBinding("SendEmail");
          Map paramEmail = sendEmail.getParamsMap();

          ViewObject voUser = app.findViewObject("UsersInternalVO1");
          voUser.setWhereClause("Username = '" +
                                row.getAttribute("Receiver").toString() + "'");
          voUser.executeQuery();
          while (voUser.hasNext()) {
            Row rows = voUser.next();
            paramEmail.put("EmailTo", rows.getAttribute("Email"));
          }

          //          if (iterBind.getCurrentRow().getAttribute("Email") != null) {
          //            paramEmail.put("EmailTo",
          //                           iterBind.getCurrentRow().getAttribute("Email").toString());
          //          } else {
          //            paramEmail.put("EmailTo", "owc.support@pheonwj.pertamina.com");
          //          }
          paramEmail.put("Subject",
                         "[PHEONWJ] Updated Transmittal for Transmittal  - " +
                         transmittalId);
          paramEmail.put("HtmlText", isi);
          try {
            sendEmail.execute(); /*matikan email dulu 27082021*/
          } catch (Exception e) {
            e.printStackTrace();
          }

        }


        app.getTransaction().commit();
      } catch (Exception e) {
        app.getTransaction().rollback();
        e.printStackTrace();
      }
      dueDate.setValue(tempDate.toString());
      AdfFacesContext.getCurrentInstance().addPartialTarget(dueDate);


    } else {

    }

  }

  public void setDueDate(RichOutputText dueDate) {
    this.dueDate = dueDate;
  }

  public RichOutputText getDueDate() {
    return dueDate;
  }
/* 
  public static Connection getConnection() throws SQLException {
    String username = "DEV_URMSERVER";
    String password = "welcome1";
    String thinConn = "jdbc:oracle:thin:@10.252.4.193:1521:OWCDEV";
                DriverManager.registerDriver(new OracleDriver());
                Connection conn =
                    DriverManager.getConnection(thinConn, username, password);
                conn.setAutoCommit(false);
                return conn;
            }
     */
    public void DeleteUser(ActionEvent actionEvent) {
//        System.out.println("tes");
            DCBindingContainer dcBindings =(DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding iterBindChange =(DCIteratorBinding)dcBindings.get("TransmittalReceiverListVO1Iterator");
                String currentReceiver = iterBindChange.getCurrentRow().getAttribute("Receiver").toString();
                String transmittalId =AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString();
                ApplicationModule app =GetApplicationModule("AppModuleExternalTransmittalDataControl");
                System.out.println("======== currentReceiver: "+currentReceiver+ " transmittalId:"+transmittalId);
                    
                    InitialContext inc;
                    Connection connUPD;
                    try{
//                        connUPD = getConnection();
                        inc = new InitialContext();
                        DataSource ds = (DataSource)inc.lookup("jdbc/EDMSConn");
                        conn = ds.getConnection();
                        conn.setAutoCommit(true);
                        
                        PreparedStatement stmtDEL1 = conn.prepareStatement("DELETE PHE_DSWF_PARTICIPANT WHERE receiver = '"+currentReceiver+"' AND transmittal_id = '"+transmittalId+"'");
                        stmtDEL1.executeUpdate();
                        PreparedStatement stmtDEL2 = conn.prepareStatement("DELETE PHE_DELEGATE WHERE delegate_to = '"+currentReceiver+"' AND transmittal_id = '"+transmittalId+"'");
                        stmtDEL2.executeUpdate();
                        PreparedStatement stmtDEL3 = conn.prepareStatement("DELETE PHE_DOC_PROCESS WHERE recipient = '"+currentReceiver+"' AND transmittal_id = '"+transmittalId+"'");
                        stmtDEL3.executeUpdate();
                       //Connection  closed for minimize pooling
                        stmtDEL1.close();
                        stmtDEL2.close();
                        stmtDEL3.close();
                        conn.close();
                    }catch (Exception e){
                        System.out.println("Error:"+e);
                    }
                    
                try{
                    //insert to dswf log
                    ViewObject voLog = app.findViewObject("PheDswfLog1");
                    NameValuePairs nvpLog = new NameValuePairs();
                    nvpLog.setAttribute("TransmittalId", transmittalId);
                    nvpLog.setAttribute("Username", Userlogin);//GetSession().getAttribute("username").toString());
                    System.out.println("Username Log: "+ Userlogin);//GetSession().getAttribute("username").toString());
                    nvpLog.setAttribute("Action", "17");
                    nvpLog.setAttribute("Description", "Delete User " + currentReceiver);
                    voLog.createAndInitRow(nvpLog);  
                    
                    
                    app.getTransaction().commit();                 
                }catch(Exception e){
                    app.getTransaction().rollback();
                    System.out.println("Error Insert Log:"+e); 
                }
                    
                ViewObject voPar = app.findViewObject("TransmittalReceiverListVO1");
                voPar.setWhereClause("1 = 1");
                voPar.executeQuery();
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindReceiver);
                
                ViewObject voHis = app.findViewObject("DetailExternalHistory1");
                voHis.setWhereClause("1 = 1");
                voHis.executeQuery(); 
            }
    
        private String getLableName(ViewObject voRec, Integer nmIdx) {
            String s = "";
            voRec.executeQuery();
            Integer i=0;
            while (voRec.hasNext()) {
               Row row = voRec.next();
                if (i == nmIdx) {
                    s=(String)row.getAttribute("Fullname");
                }
                i=i+1;
             }
            voRec.closeRowSetIterator();  
            return s;
        }
    }