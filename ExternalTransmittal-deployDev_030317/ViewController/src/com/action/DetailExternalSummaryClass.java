package com.action;

import com.utils.ADFUtils;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;

import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.NameValuePairs;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;

public class DetailExternalSummaryClass {
    private List<TempDocuments> fowardedDoc = new ArrayList<TempDocuments>();
    private RichPopup bindPopUpForward;
    private RichSelectBooleanCheckbox bindCloseTransmittal;
    private String currentAddress;
    private String weblogicusername;
    private String weblogicpassword;
    private String dswfAddress;
    private String isPublish;
    private String CompanyApName;
    private String Userlogin;
    
    public DetailExternalSummaryClass() {
        setIsPublish("No");
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest request =
            (HttpServletRequest)ctx.getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        Userlogin =request.getParameter("dUser");//"iwansyah""weblogic";//;
        CompanyApName= request.getParameter("companyap");//"PHE ONWJ";//
        ApplicationModule app =
            GetApplicationModule("AppModuleExternalTransmittalDataControl");
        ViewObject voPublish = app.findViewObject("PheDswfMasterVO1");
        voPublish.setWhereClause("TRANSMITTAL_ID = '"+AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString()+"'");
        voPublish.executeQuery();
        while(voPublish.hasNext()){
            Row row = voPublish.next();
            setIsPublish(row.getAttribute("ProjectorganizationName").toString());
        }
    }

    public void Forward(ActionEvent actionEvent) {
        fowardedDoc.clear();
        fowardedDoc = new ArrayList<TempDocuments>();
        DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterBind= (DCIteratorBinding)dcBindings.get("DetailExternalSupportingDoc1Iterator");
        Row[] rows = iterBind.getAllRowsInRange();
        String did ="";
        String dDocname ="";
        String docType ="";
        String docFormat ="";
        String documentName ="";
        String documentNumber ="";
        String documentTitle ="";
        String revision ="";
        String documentStatus ="";
        String pages ="";
        String remarks ="";
        String distribution ="";
        for(int i =0;i<rows.length;i++){
            if(rows[i].getAttribute("Mark")!=null){
                if(rows[i].getAttribute("Mark").equals(true)){
                    did=rows[i].getAttribute("Id").toString();
                    dDocname =rows[i].getAttribute("ContentId") !=null ?rows[i].getAttribute("ContentId").toString() :"";
                    docType =rows[i].getAttribute("DocType").toString();
                    docFormat =rows[i].getAttribute("Format") !=null ?rows[i].getAttribute("Format").toString() :"";
                    documentName =rows[i].getAttribute("DocName") !=null ?rows[i].getAttribute("DocName").toString() :"";
                    documentNumber = rows[i].getAttribute("DocNumber") !=null ?rows[i].getAttribute("DocNumber").toString() :"";
                    documentTitle = rows[i].getAttribute("DocTitle") !=null ?rows[i].getAttribute("DocTitle").toString() :"";
                    revision = rows[i].getAttribute("Revision") !=null ?rows[i].getAttribute("Revision").toString() :"";
                    documentStatus =rows[i].getAttribute("DocStatus") !=null ?rows[i].getAttribute("DocStatus").toString() :"IFR";
                    pages = rows[i].getAttribute("Pages") !=null ?rows[i].getAttribute("Pages").toString() :"1";
                    remarks ="";
                    distribution =rows[i].getAttribute("DocSource").toString();
                    fowardedDoc.add(new TempDocuments(did,
                                                      dDocname,
                                                      docType,
                                                      docFormat,
                                                      documentName,
                                                      documentNumber,
                                                      documentTitle,
                                                      revision,
                                                      documentStatus,
                                                      pages, remarks,
                                                      distribution));
                }   
            }
        }
        iterBind= (DCIteratorBinding)dcBindings.get("DetailExternalSubmittalDoc1Iterator");
        rows = iterBind.getAllRowsInRange();
        for(int i =0;i<rows.length;i++){
            if(rows[i].getAttribute("Mark1")!=null){
                if(rows[i].getAttribute("Mark1").equals(true)){
                    did=rows[i].getAttribute("Id").toString();
                    dDocname =rows[i].getAttribute("ContentId") !=null ?rows[i].getAttribute("ContentId").toString() :"";
                    docType =rows[i].getAttribute("DocType").toString();
                    docFormat =rows[i].getAttribute("Format") !=null ?rows[i].getAttribute("Format").toString() :"";
                    documentName =rows[i].getAttribute("DocName") !=null ?rows[i].getAttribute("DocName").toString() :"";
                    documentNumber = rows[i].getAttribute("DocNumber") !=null ?rows[i].getAttribute("DocNumber").toString() :"";
                    documentTitle = rows[i].getAttribute("DocTitle") !=null ?rows[i].getAttribute("DocTitle").toString() :"";
                    revision = rows[i].getAttribute("Revision") !=null ?rows[i].getAttribute("Revision").toString() :"";
                    documentStatus =rows[i].getAttribute("DocStatus") !=null ?rows[i].getAttribute("DocStatus").toString() :"IFR";
                    pages = rows[i].getAttribute("Pages") !=null ?rows[i].getAttribute("Pages").toString() :"1";
                    remarks ="";
                    distribution =rows[i].getAttribute("DocSource").toString();
                    fowardedDoc.add(new TempDocuments(did,
                                                      dDocname, "Supporting",
                                                      docFormat,
                                                      documentName,
                                                      documentNumber,
                                                      documentTitle,
                                                      revision,
                                                      documentStatus,
                                                      pages, remarks,
                                                      distribution));
                }   
            }
        }
        if(fowardedDoc.size()>0){
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            bindPopUpForward.show(ph);
            System.out.println("CompanyApName "+CompanyApName);
        }else{
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "You should choose file(s) to initiate forward transmittal", "");
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

    public void setBindCloseTransmittal(RichSelectBooleanCheckbox bindCloseTransmittal) {
        this.bindCloseTransmittal = bindCloseTransmittal;
    }

    public RichSelectBooleanCheckbox getBindCloseTransmittal() {
        return bindCloseTransmittal;
    }
    
    public ApplicationModule GetApplicationModule(String appName){
        return ADFUtils.getApplicationModuleForDataControl(appName);
    }

    public void DialogForwardTransmittal(DialogEvent dialogEvent) {
        if(dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)){
            String transmittalId = AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString();
            System.out.println("Close transmittal when forward : "+bindCloseTransmittal.getValue()== null ? "":bindCloseTransmittal.getValue().toString());
            BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
            OperationBinding method = bindings.getOperationBinding("ExecuteGetFguid");
            Map paramsMap = method.getParamsMap();
            paramsMap.put("folderName",transmittalId);
            method.execute();
            AttributeBinding Ffolderguid = (AttributeBinding) bindings.get("Ffolderguid");
            RIDCClass ridc = new RIDCClass(getWeblogicusername(),getWeblogicpassword());
            try{
                String path = ridc.GetFolderPathFromGuid(Ffolderguid.getInputValue().toString());
                System.out.println("path"+path);
                if(path.contains("Non Routine")){
                    path = path.substring(0, path.lastIndexOf("/"));
                }else{
                    for(int i=0;i<2;i++){
                        path = path.substring(0, path.lastIndexOf("/"));
                    }
                }
                //edit nanda - 0511 - hasil pindah dr bawah
                String redirectpath="";
                DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding iterBind= (DCIteratorBinding)dcBindings.get("ForwardSelectionVo1Iterator");
                System.out.println(iterBind.getCurrentRow().getAttribute("Code"));
                FacesContext ctx = FacesContext.getCurrentInstance();
                if(iterBind.getCurrentRow().getAttribute("Code").equals("Internal")){    
                     redirectpath=getDswfAddress()+"CreateTransmittalInternalForm.jspx?path="+path+"&companyap="+CompanyApName+"&dUser="+Userlogin;
                     System.out.println("companyap internal "+CompanyApName);
                }else{
                     redirectpath=getDswfAddress()+"CreateTransmittalExternalForm.jspx?path="+path+"&companyap="+CompanyApName+"&dUser="+Userlogin;
                    System.out.println("companyap external "+CompanyApName);
                }
                String CloseTransmittal=bindCloseTransmittal.getValue()==null ? "":bindCloseTransmittal.getValue().toString(); 
                if(CloseTransmittal.equalsIgnoreCase("true")){
                    ApplicationModule app = GetApplicationModule("AppModuleExternalTransmittalDataControl");
                    try{
                        ViewObject myVoPar = app.findViewObject("PheDswfMasterVO1");
                        String query = "TRANSMITTAL_ID = '"+AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString()+"'";
                        System.out.println("ClosetTransmittal:query:"+query);
                        myVoPar.setWhereClause(query);
                        myVoPar.executeQuery();
                        System.out.println("myVoPar.hasNext()"+myVoPar.hasNext());
                        while(myVoPar.hasNext()){
                            Row row = myVoPar.next();
                            row.setAttribute("TransmittalStatus","Completed");
//                            app.getTransaction().commit();
//                            System.out.println("complete transmittal success");
                        }
                        
                        ViewObject voLog = app.findViewObject("PheDswfLog1");
                        NameValuePairs nvpLog = new NameValuePairs();
                        nvpLog.setAttribute("TransmittalId", AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString());
                        nvpLog.setAttribute("Username",AdfFacesContext.getCurrentInstance().getPageFlowScope().get("dUser").toString() );
                        nvpLog.setAttribute("Action","3" );
                        nvpLog.setAttribute("Description", "Finish Transmittal "+AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString());
                        voLog.createAndInitRow(nvpLog);
                        
                        System.out.println("Transmittal "+AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString()+" has been finished");
                        app.getTransaction().commit();
                    }catch(Exception e){
                        app.getTransaction().rollback();
                    }
                }
                //edit nanda - 0511 - pindah keatas
//                DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
//                DCIteratorBinding iterBind= (DCIteratorBinding)dcBindings.get("ForwardSelectionVo1Iterator");
//                System.out.println(iterBind.getCurrentRow().getAttribute("Code"));
//                FacesContext ctx = FacesContext.getCurrentInstance();
//                if(iterBind.getCurrentRow().getAttribute("Code").equals("Internal")){
                    try {
                        HttpServletRequest request =
                            (HttpServletRequest)ctx.getExternalContext().getRequest();
                        HttpSession session = request.getSession(true);
                        session.setAttribute("forwardedItem",fowardedDoc);
                        ctx.getExternalContext().redirect(redirectpath);
//                        ctx.getExternalContext().redirect("http://localhost:7101/ExternalTransmittal-ViewController-context-root/faces/Pages/CreateTransmittalInternalForm.jspx?path="+path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                }else{
//                    try {
//                        HttpServletRequest request =
//                            (HttpServletRequest)ctx.getExternalContext().getRequest();
//                        HttpSession session = request.getSession(true);
//                        session.setAttribute("forwardedItem",fowardedDoc);
//                        ctx.getExternalContext().redirect(getDswfAddress()+"CreateTransmittalExternalForm.jspx?path="+path);
//                        ctx.getExternalContext().redirect("http://localhost:7101/ExternalTransmittal-ViewController-context-root/faces/Pages/CreateTransmittalExternalForm.jspx?path="+path);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            
        }
    }
    
    public String GetConfig(String param) {
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding obGetConfig = bindings.getOperationBinding("ExecuteGetConfigWithParams");
        obGetConfig.getParamsMap().put("keyConfig", param);
        AttributeBinding abConfigValue = (AttributeBinding)bindings.get("KeyValue");
        obGetConfig.execute();
        return abConfigValue.getInputValue().toString();
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getCurrentAddress() {
        return GetConfig("RIDC_URL");
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

    public void setIsPublish(String isPublish) {
        this.isPublish = isPublish;
    }

    public String getIsPublish() {
        return isPublish;
    }
}
