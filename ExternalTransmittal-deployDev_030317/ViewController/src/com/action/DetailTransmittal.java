package com.action;


import com.utils.ADFUtils;
import com.utils.JSFUtils;

import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.binding.AttributeBinding;
import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.NameValuePairs;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;


public class DetailTransmittal {
    //-- CONSTRUCTOR
    private RichTable tblSubmittal;
    private RichSelectOneChoice socReceiver;
    private String currentAddress;
    private String getFileURL;
    private JSFUtils util = new JSFUtils();
    private RichPopup bindPopUpForward;
    private RichSelectBooleanCheckbox bindCloseTransmittal;
    private RichButton bindBtnFinish;
    private RichButton bindBtnAbort;
    private RichButton bindBtnReject;
    private RichInputText bindRejectReason;
    private RichOutputText countCheckBox;
    private String CompanyApName;
    private String Userlogin;
    
    public DetailTransmittal() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest request =
            (HttpServletRequest)ctx.getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        Userlogin =request.getParameter("dUser");//"iwansyah""weblogic";//;
        CompanyApName=request.getParameter("companyap");//"PHE ONWJ";//
    }
    
    {
        bindRejectReason = new RichInputText();
        bindRejectReason.setValue("");
    }

    public void setTblSubmittal(RichTable tblSubmittal) {
        this.tblSubmittal = tblSubmittal;
    }

    public RichTable getTblSubmittal() {
        return tblSubmittal;
    }

    public void setGetFileURL(String getFileURL) {
        this.getFileURL = getFileURL;
    }

    public String getGetFileURL() {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding obGetConfig =
            bindings.getOperationBinding("ExecuteGetConfigWithParams");
        AttributeBinding abConfigValue =
            (AttributeBinding)bindings.get("KeyValue");
        obGetConfig.getParamsMap().put("keyConfig", "GETFILE_URL");
        obGetConfig.execute();
        this.getFileURL = abConfigValue.getInputValue().toString();
        return getFileURL;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getCurrentAddress() {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding obGetConfig =
            bindings.getOperationBinding("ExecuteGetConfigWithParams");
        AttributeBinding abConfigValue =
            (AttributeBinding)bindings.get("KeyValue");
        obGetConfig.getParamsMap().put("keyConfig", "AUTOVUE_URL");
        obGetConfig.execute();
        this.currentAddress = abConfigValue.getInputValue().toString();
        return currentAddress;
    }

    public void setSocReceiver(RichSelectOneChoice socReceiver) {
        this.socReceiver = socReceiver;
    }

    public RichSelectOneChoice getSocReceiver() {
        return socReceiver;
    }


    public HttpSession GetSession() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest request =
            (HttpServletRequest)ctx.getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        return session;
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

    public void setBindBtnReject(RichButton bindBtnReject) {
        this.bindBtnReject = bindBtnReject;
    }

    public RichButton getBindBtnReject() {
        return bindBtnReject;
    }

    public void setBindRejectReason(RichInputText bindRejectReason) {
        this.bindRejectReason = bindRejectReason;
    }

    public RichInputText getBindRejectReason() {
        return bindRejectReason;
    }

    public void setCountCheckBox(RichOutputText countCheckBox) {
        this.countCheckBox = countCheckBox;
    }

    public RichOutputText getCountCheckBox() {
        return countCheckBox;
    }

    public void countCheckBox1(ValueChangeEvent valueChangeEvent) {
        int countVar=1;
        if (valueChangeEvent.getNewValue().toString().toUpperCase().equals("TRUE")){
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
             DCBindingContainer dcb = (DCBindingContainer) evaluateEL("#{bindings}");
             DCIteratorBinding dciter =dcb.findIteratorBinding("DetailExternalSubmittalDoc1Iterator");

             ViewObject vo = dciter.getViewObject();
             int i = 0;
             Row row = null;
             vo.reset();
             while (vo.hasNext()) {
             if (i == 0)
             row = vo.first();
             else
             row = vo.next();
          

                 if(row.getAttribute("Mark1").equals(true)){
                     countVar++;}
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
             DCBindingContainer dcb = (DCBindingContainer) evaluateEL("#{bindings}");
             DCIteratorBinding dciter =dcb.findIteratorBinding("DetailExternalSubmittalDoc1Iterator");

             ViewObject vo = dciter.getViewObject();
             int i = 0;
             Row row = null;
             vo.reset();
             while (vo.hasNext()) {
             if (i == 0)
             row = vo.first();
             else
             row = vo.next();
          

                 if(row.getAttribute("Mark1").equals(true)){
                     countVar++;}
             i++;
             }
        System.out.println("deselect");
        System.out.println("countVar: "+countVar);
//        if(countVar>rows.length){
//        System.out.println("countVar-rows.length>0");
        countVar=countVar-2;
//        }
        }
            System.out.println("count detail:" +countVar);
            countCheckBox.setValue(countVar);
            AdfFacesContext.getCurrentInstance().addPartialTarget(countCheckBox);
    }

    public class TransmittalStatus {
        private String Finish = "Completed";
        private String Abort = "Aborted";
        private String Reject = "Rejected";
    }
    private TransmittalStatus StatusList = new TransmittalStatus();
    //-- END OF CONSTRUCTOR
    
    public void CheckAllSupportingDoc(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue().toString().toUpperCase().equals("TRUE")) {
            System.out.println("select all");
            DCBindingContainer dcBindings =
                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            JUCtrlHierBinding table =
                (JUCtrlHierBinding)dcBindings.getControlBinding("DetailExternalSupportingDoc1");
            Row[] rows = table.getAllRowsInRange();
            for (int i = 0; i < rows.length; i++) {
                Row row = rows[i];
                row.setAttribute("Mark", true);
            }
        } else {
            System.out.println("deselect all");
            DCBindingContainer dcBindings =
                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            JUCtrlHierBinding table =
                (JUCtrlHierBinding)dcBindings.getControlBinding("DetailExternalSupportingDoc1");
            Row[] rows = table.getAllRowsInRange();
            for (int i = 0; i < rows.length; i++) {
                Row row = rows[i];
                row.setAttribute("Mark", false);
            }
        }
    }

    private static Object evaluateEL(String el) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ELContext elContext = facesContext.getELContext();
    ExpressionFactory expressionFactory = (ExpressionFactory)facesContext.getApplication().getExpressionFactory();
    ValueExpression exp =
    expressionFactory.createValueExpression(elContext, el,
              Object.class);
    return exp.getValue(elContext);
    }
    
    public void CheckAllSubmittalDoc(ValueChangeEvent valueChangeEvent) {
        int count=0;
        if (valueChangeEvent.getNewValue().toString().toUpperCase().equals("TRUE")){ 
            System.out.println("select all");
          
            DCBindingContainer dcBindings =
                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            JUCtrlHierBinding table =
                (JUCtrlHierBinding)dcBindings.getControlBinding("DetailExternalSubmittalDoc1");
                
             //nanda 3012   
             DCBindingContainer dcb = (DCBindingContainer) evaluateEL("#{bindings}");
             DCIteratorBinding dciter =dcb.findIteratorBinding("DetailExternalSubmittalDoc1Iterator");

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
            DCBindingContainer dcb = (DCBindingContainer) evaluateEL("#{bindings}");
            DCIteratorBinding dciter =dcb.findIteratorBinding("DetailExternalSubmittalDoc1Iterator");

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
        System.out.println("count all:" +count);
        countCheckBox.setValue(count);
        AdfFacesContext.getCurrentInstance().addPartialTarget(countCheckBox);
       
    }

    public void Receiver_Changed(ValueChangeEvent valueChangeEvent) {
        FacesContext contxt = FacesContext.getCurrentInstance();
        valueChangeEvent.getComponent().processUpdates(contxt);
        DCBindingContainer dcBindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterBindChange =
            (DCIteratorBinding)dcBindings.get("TransmittalReceiverListVO1Iterator");
        ApplicationModule appModule =
            ADFUtils.getApplicationModuleForDataControl("AppModuleExternalTransmittalDataControl");
        ViewObject vo =
            appModule.findViewObject("DetailExternalSubmittalDoc1");
        vo.setWhereClause("Uploader = '" +iterBindChange.getCurrentRow().getAttribute("Receiver").toString()+"'");
        vo.executeQuery();
        AdfFacesContext.getCurrentInstance().addPartialTarget(tblSubmittal);
    }

    public void BindHistoryDocNumber(PopupFetchEvent popupFetchEvent) {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding obHistoryNumber =
            bindings.getOperationBinding("ExecuteHistoryDocNumber");
        DCIteratorBinding supportingDocIterator =
            (DCIteratorBinding)bindings.get("DetailExternalSupportingDoc1Iterator");
        Row supportingDocCurrentRow = supportingDocIterator.getCurrentRow();
        obHistoryNumber.getParamsMap().put("docNumber",
                                           supportingDocCurrentRow.getAttribute("DocNumber"));
        obHistoryNumber.execute();
    }

    public boolean DoUpdateDSWFStatus(String status) {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding obUpdateDSWFStatus =
            bindings.getOperationBinding("UpdateStatusDSWFMaster");
        obUpdateDSWFStatus.getParamsMap().put("transmittalStatus", status);
        return ((Boolean)obUpdateDSWFStatus.execute());
    }

    public ApplicationModule GetApplicationModule(String appName) {
        return ADFUtils.getApplicationModuleForDataControl(appName);
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
    
    public void DisableBtn(){
        bindBtnFinish.setDisabled(true);
        bindBtnAbort.setDisabled(true);
        bindBtnReject.setDisabled(true);
        
        bindBtnFinish.setVisible(false);
        bindBtnAbort.setVisible(false);
        bindBtnReject.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindBtnFinish);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindBtnAbort);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindBtnReject);
    }

    public void dFinishClicked(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            DCBindingContainer bindings =
                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            AttributeBinding abTransmittalNo =
                (AttributeBinding)bindings.get("TransmittalId");
            if (this.DoUpdateDSWFStatus(StatusList.Finish))
                util.addFacesInfoMessage("Transmittal no: " +
                                         abTransmittalNo.getInputValue() +
                                         " status successfully change to " +
                                         StatusList.Finish);
            else
                util.addFacesErrorMessage("Transmittal no: " +
                                          abTransmittalNo.getInputValue() +
                                          " status failed change to " +
                                          StatusList.Finish);

            String isPublish = "No";
            ApplicationModule app =
                GetApplicationModule("AppModuleExternalTransmittalDataControl");
            try {
                ViewObject myVoPar = app.findViewObject("PheDswfMasterVO1");
                String query1 =
                    "TRANSMITTAL_ID = '" + AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString() +
                    "'";
                myVoPar.setWhereClause(query1);
                myVoPar.executeQuery();
                while (myVoPar.hasNext()) {
                    Row row = myVoPar.next();
                    isPublish =
                            row.getAttribute("ProjectorganizationName").toString();
                }
                if (isPublish.equals("Publish")) {
                    ViewObject voPublish = app.findViewObject("PhdDswfDocVo1");
                    String query =
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
                nvpLog.setAttribute("TransmittalId", abTransmittalNo.getInputValue().toString());
                nvpLog.setAttribute("Username",Userlogin);
                                    //ADFContext.getCurrent().getSecurityContext().getUserName());
                nvpLog.setAttribute("Action", "3");
                nvpLog.setAttribute("Description",
                                    "Finish Transmittal No "+AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString());
                voLog.createAndInitRow(nvpLog);
                app.getTransaction().commit();
                DisableBtn();
            } catch (Exception e) {
                app.getTransaction().rollback();
            }
        }
    }

    public void dAbortedClicked(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            DCBindingContainer bindings =
                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            AttributeBinding abTransmittalNo =
                (AttributeBinding)bindings.get("TransmittalId");
            if (this.DoUpdateDSWFStatus(StatusList.Abort))
                util.addFacesInfoMessage("Transmittal no: " +
                                         abTransmittalNo.getInputValue() +
                                         " status successfully change to " +
                                         StatusList.Abort);
            else
                util.addFacesErrorMessage("Transmittal no: " +
                                          abTransmittalNo.getInputValue() +
                                          " status failed change to Finished " +
                                          StatusList.Abort);
            ApplicationModule app =
                GetApplicationModule("AppModuleExternalTransmittalDataControl");
            ViewObject voLog = app.findViewObject("PheDswfLog1");
            NameValuePairs nvpLog = new NameValuePairs();
            nvpLog.setAttribute("TransmittalId", abTransmittalNo.getInputValue().toString());
            nvpLog.setAttribute("Username",Userlogin);
                                //ADFContext.getCurrent().getSecurityContext().getUserName());
            nvpLog.setAttribute("Action", "6");
            nvpLog.setAttribute("Description",
                                "Abort Transmittal");
            voLog.createAndInitRow(nvpLog);
            try{
                app.getTransaction().commit();
            }catch(Exception e){
                e.printStackTrace();
                app.getTransaction().rollback();
            }
            DisableBtn();
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
    
    public void dRejectedClicked(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            DCBindingContainer bindings =
                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            AttributeBinding abTransmittalNo =
                (AttributeBinding)bindings.get("TransmittalId");
            AttributeBinding abContractNo =
                (AttributeBinding)bindings.get("ContractNo");
            AttributeBinding abWoNo =
                (AttributeBinding)bindings.get("WoNo");
            AttributeBinding abSubject =
                (AttributeBinding)bindings.get("TransmittalSubject");
            AttributeBinding ProjectorganizationName =
                (AttributeBinding)bindings.get("ProjectorganizationName");
            AttributeBinding TransmittalRnrCode =
                (AttributeBinding)bindings.get("TransmittalRnrCode");
            String linkExternal = "";
            if(TransmittalRnrCode.getInputValue().toString()=="Non Routine"){
                linkExternal = "Projects";
            }else{
                linkExternal= "Organization";
            }
            if (this.DoUpdateDSWFStatus(StatusList.Reject))
                util.addFacesInfoMessage("Transmittal no: " +
                                         abTransmittalNo.getInputValue() +
                                         " status successfully change to " +
                                         StatusList.Reject);
            else
                util.addFacesErrorMessage("Transmittal no: " +
                                          abTransmittalNo.getInputValue() +
                                          " status failed change to " +
                                          StatusList.Reject);
            ApplicationModule app =
                GetApplicationModule("AppModuleExternalTransmittalDataControl");
            ViewObject voLog = app.findViewObject("PheDswfLog1");
            NameValuePairs nvpLog = new NameValuePairs();
            nvpLog.setAttribute("TransmittalId", abTransmittalNo.getInputValue().toString());
            nvpLog.setAttribute("Username",Userlogin);
//                                ADFContext.getCurrent().getSecurityContext().getUserName());
            nvpLog.setAttribute("Action", "7");
            nvpLog.setAttribute("Description",
                                "Reject Transmittal");
            voLog.createAndInitRow(nvpLog);
            String isi = "";
            if(ProjectorganizationName.getInputValue().toString()=="Publish"){
                isi="<html>\n" + 
                "<head></head>\n" + 
                "<body style=\"font-family:arial\">Dear Sir/Madam<br/><br/>You have received a new transmittal. The details are:<br/><br/>\n" + 
                "<table ><tr><td>Transmittal No</td><td>:</td><td>"+abTransmittalNo.getInputValue().toString()+"</td></tr>\n" + 
                "<tr><td>Contract No</td><td>:</td><td>"+abContractNo.getInputValue().toString()+"</td></tr>" +
                "<tr><td>WO No.</td><td>:</td><td>"+abWoNo.getInputValue().toString()+"</td></tr>" +
                "<tr><td>Subject</td><td>:</td><td>"+abSubject.getInputValue().toString()+"</td></tr></table>\n" + 
                "<br/><br/>Has been rejected due to following reason : "+bindRejectReason.getValue().toString() +
                "<br/>Please click " + 
                "<a href=\""+GetConfig("EXTERNAL_DSWF")+"?tPath=Publish"+"/"+abTransmittalNo.getInputValue().toString()+"\">here</a> to revise the submittal.<br/>Thank you for your attention.</body></html>";
            }else{
                isi="<html>\n" + 
                "<head></head>\n" + 
                "<body style=\"font-family:arial\">Dear Sir/Madam<br/><br/>You have received a new transmittal. The details are:<br/><br/>\n" + 
                "<table ><tr><td>Transmittal No</td><td>:</td><td>"+abTransmittalNo.getInputValue().toString()+"</td></tr>\n" + 
                "<tr><td>Contract No</td><td>:</td><td>"+abContractNo.getInputValue().toString()+"</td></tr>" +
                "<tr><td>WO No.</td><td>:</td><td>"+abWoNo.getInputValue().toString()+"</td></tr>" +
                "<tr><td>Subject</td><td>:</td><td>"+abSubject.getInputValue().toString()+"</td></tr></table>\n" + 
                "<br/><br/>Has been rejected due to following reason : "+bindRejectReason.getValue().toString() +
                "<br/>Please click " + 
                "<a href=\""+GetConfig("EXTERNAL_DSWF")+"?tPath="+linkExternal+"/"+ProjectorganizationName.getInputValue().toString()+"/"+abTransmittalNo.getInputValue().toString()+"\">here</a> to revise the submittal.<br/>Thank you for your attention.</body></html>";   
            }
            
            OperationBinding sendEmail = bindings.getOperationBinding("SendEmailTo");
            String cc = GetCreatorEmail();
            Map paramEmail = sendEmail.getParamsMap();
            paramEmail.put("Subject","[PHEONWJ] Rejected Transmittal No. "+abTransmittalNo.getInputValue().toString());
            paramEmail.put("EmailCc",cc);
            paramEmail.put("HtmlText",isi);
            String penerima = GetReceiverEmail(abTransmittalNo.getInputValue().toString());
            if(penerima != ""){
                paramEmail.put("EmailTo",penerima);
            }else{
                paramEmail.put("EmailTo",cc);
            }
            try{
                app.getTransaction().commit();
                sendEmail.execute();/*matikan email dulu 27082021*/
            }catch(Exception e){
                e.printStackTrace();
                app.getTransaction().rollback();
            }
            DisableBtn();
        }
    }
    
    public String GetCreatorEmail(){
        ApplicationModule am = ADFUtils.getApplicationModuleForDataControl("AppModuleExternalTransmittalDataControl");
        ViewObject voGetPar = am.findViewObject("UsersInternalVO1");
        voGetPar.setWhereClause("USERNAME = '"+Userlogin+"'");//ADFContext.getCurrent().getSecurityContext().getUserName()
        voGetPar.executeQuery();
        String email = "";
        while(voGetPar.hasNext()){
            Row row = voGetPar.next();
            email = row.getAttribute("Email").toString();
        }
        return email;
    }
    
    public String GetReceiverEmail(String transmittalId){
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding obGetConfig = bindings.getOperationBinding("GetAllRecepientEmail");
        obGetConfig.getParamsMap().put("transmittalId", transmittalId);
        obGetConfig.execute();
        DCBindingContainer dcBindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterBind =
            (DCIteratorBinding)dcBindings.get("GetAllRecepientEmailByTransmittalIDVO1Iterator");
        Row[] rows = iterBind.getAllRowsInRange();
        String penerima = "";
        for(int i=0;i<rows.length;i++){
            if(i!=0 && i<rows.length){
                penerima = penerima+",";
            }
            penerima=penerima+rows[i].getAttribute("ContactEmail");
        }
        return penerima;
    }
}
