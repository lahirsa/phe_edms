package com.action;


import com.utils.ADFUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelLabelAndMessage;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.NameValuePairs;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import oracle.stellent.ridc.model.DataObject;

import org.apache.myfaces.trinidad.component.UIXTree;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;


public class ExternalTransmittalClass {
    private RichSelectOneChoice bindSource;
    private RichPopup bindPopUpEdms;
    private RichSelectOneChoice bindUser;
    private List<DataUser> dataUser = new ArrayList<DataUser>();
    private RichTable tableUser;
    private RichPopup binderPopUpElectronic;
    private RichInputText bindElectronicName;
    private UploadedFile file;
    private long fileLength;
    private InputStream fileInputStream;
    private String fileName;
    private RichPopup bindPopUpHardcopy;
    private RichInputText bindDocType;
    private RichInputText bindDocNumber;
    private RichInputText binDocTitle;
    private RichSelectOneChoice bindDocSource;
    private RichInputText bindPages;
    private RichInputText bindRemarks;
    private RichInputText bindRevisions;
    private List<TempDocuments> tempDoc = new ArrayList<TempDocuments>();
    private List<ChildFiles> childFiles = new ArrayList<ChildFiles>();
    private String fileContentType;
    private RichSelectOneChoice bindDocStatus;
    private RichTable bindTableDoc;
    private RichTable bindTableFilesEDMS;
    private RichSelectBooleanRadio bindRadioEDMS;
    private RichInputText bindHardcopyDocNumber;
    private RichInputText bindHardcopyDocTitle;
    private RichInputText bindHardcopyRevision;
    private RichSelectOneChoice bindHardcopyDocStatus;
    private RichInputText bindHardcopyPages;
    private RichInputText bindHardcopyRemarks;
    private RichSelectOneChoice bindHardcopyDistributionMethod;
    private String paramPath;
    private String selectedEDMSFile;
    private RichInputText bindEdmsDocNumber;
    private RichInputText bindEdmsDocTitle;
    private RichSelectOneChoice bindEdmsDocStatus;
    private RichInputText bindEdmsPages;
    private RichInputText bindEdmsRemarks;
    private RichInputText bindEdmsRevisions;
    private RichTree bindTreeEdms;
    private RichPopup bindPopUpEdmsForm;
    private RichInputFile bindUploadedFile;
    private RichOutputLabel bindOutputProjectTitle;
    private RichInputText bindTransmittalSubject;
    private RichInputDate bindTransmittalDueDate;
    private RichSelectOneChoice bindTransmittalContractor;
    private RichInputText bindTransmittalWoNo;
    private String routineNonRoutine;
    private String projectorOrganization;
    private RichSelectOneChoice bindTransmittalContractorOgranization;
    private RichSelectOneChoice bindUserOrganization;
    private RichPopup bindPopUpEditEdmsForm;
    private RichInputText bindEdmsEditDocNumber;
    private RichInputText bindEdmsEditDocTitle;
    private RichSelectOneChoice bindEdmsEditDocStatus;
    private RichInputText bindEdmsEditPages;
    private RichInputText bindEdmsEditRemarsk;
    private RichInputText bindEdmsEditRevisions;
    private RichOutputLabel bindEditDocName;
    private RichSelectOneChoice bindHardcopyEditDistributionMethod;
    private RichPanelLabelAndMessage bindLabelEditDocName;
    private RichOutputLabel bindEditIndex;
    private RichPopup bindPopUpSuccess;
    private String projectOrgId;
    private String currentAddress;
    private String weblogicusername;
    private String weblogicpassword;
    private String externalAddress;
    private RichSelectOneChoice bindUserPublish;
    private RichSelectOneChoice bindTransmittalContractorPublish;
    private String isForward;
    private RichOutputLabel bindCompanyName;
    private String CompanyApName;
    private String Userlogin;
    private String Password;

    public ExternalTransmittalClass() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest requestSession = (HttpServletRequest)ctx.getExternalContext().getRequest();
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
        HttpSession session =  requestSession.getSession(true);
        
        Userlogin =request.getParameter("dUser");//"owc.admin";//
        CompanyApName=request.getParameter("companyap");//"PHE ONWJ";//
        
        setPassword(getValueInDB("AppModuleExternalTransmittalDataControl",
                                         "usersDcrmsVO1",
                                         "U_NAME = '"+Userlogin+"'",
                                         "UPassword"));
        
        setIsForward("No");
        setCurrentAddress(GetConfig("RIDC_URL"));
        setWeblogicusername(GetConfig("WEBLOGIC_USER"));
        setWeblogicpassword(GetConfig("WEBLOGIC_PASS"));
        setExternalAddress(GetConfig("EXTERNAL_DSWF"));
        // comment dulu
        tempDoc = new ArrayList<TempDocuments>();
        tempDoc = (List<TempDocuments>)session.getAttribute("forwardedItem");
        if(tempDoc!=null){
            for(int i = 0;i<tempDoc.size();i++){
                System.out.println("forwared doc ke"+i+": "+tempDoc.get(i).getDid()+" "+tempDoc.get(i).getDocumentNumber());
                //commented by nanda - 0202 - flag isForward move here
                setIsForward("Yes");
                }
                //commented by nanda - 0202
                //            setIsForward("Yes");
        }else{
            tempDoc = new ArrayList<TempDocuments>();
            System.out.println("bukan forward eksternal");
            setIsForward("No");
        }
        try{
            /* String cuk = request.getParameter("seqId");
            System.out.println("CUK "+cuk);
            //nanda 020215
            System.out.println("isForward="+getIsForward());
            String[] docs = cuk.split(",");
            RIDCClass ridc = new RIDCClass(getWeblogicusername(),getWeblogicpassword());
            String publishfolderGUID = ridc.FolderInfo("Document Secure Workflow/Publish/Documents").get("fFolderGUID").toString();
            ApplicationModule app =
                GetApplicationModule("AppModuleExternalTransmittalDataControl");
            BindingContext bindingctx = BindingContext.getCurrent();
            BindingContainer bindings = null;
            bindings = bindingctx.getCurrentBindingsEntry();
            for(int doc=0;doc<docs.length;doc++){
                ViewObject voPublish = app.findViewObject("PhdDswfDocVo1");
                voPublish.setWhereClause("SEQ_ID = '"+docs[doc]+"'");
                voPublish.executeQuery();
                Row row1 = null;
                while (voPublish.hasNext()) {
                    row1 = voPublish.next();
                }
                String did ="";
                String dDocname ="";
                String docFormat ="";
                String documentName ="";
                String documentNumber ="";
                String documentTitle ="";
                String revision ="";
                String pages ="";
                String remarks = "";
                String distribution ="";
                if(row1.getAttribute("DocSource").toString().equals("Electronic")){
                    DataObject obj = ridc.CopyFile(publishfolderGUID, ridc.GetFileInfo(row1.getAttribute("Id").toString()).get("fFileGUID").toString());
                    did = obj.get("dID").toString();
                    dDocname = obj.get("dDocName").toString();
                }else{
                    OperationBinding method = bindings.getOperationBinding("getSeqValue");
                    Map paramsMap = method.getParamsMap();
                    paramsMap.put("sequenceName","PHE_DSWF_DOC_SEQ")  ;
                    Object ob = method.execute();
                    did=ob.toString();
                    dDocname =row1.getAttribute("ContentId") !=null ?row1.getAttribute("ContentId").toString() :"";
                }
                docFormat =row1.getAttribute("Format") !=null ?row1.getAttribute("Format").toString() :"";
                documentName =row1.getAttribute("DocName") !=null ?row1.getAttribute("DocName").toString() :"";
                documentNumber = row1.getAttribute("DocNumber") !=null ?row1.getAttribute("DocNumber").toString() :"";
                documentTitle = row1.getAttribute("DocTitle") !=null ?row1.getAttribute("DocTitle").toString() :"";
                revision = row1.getAttribute("Revision") !=null ?row1.getAttribute("Revision").toString() :"";
                pages = row1.getAttribute("Pages") !=null ?row1.getAttribute("Pages").toString() :"1";
                remarks = row1.getAttribute("Remarks") !=null ?row1.getAttribute("Remarks").toString() :"";
                distribution =row1.getAttribute("DocSource").toString();
                tempDoc.add(new TempDocuments(did,dDocname, "Transmittal",docFormat,documentName,
                                              documentNumber,documentTitle,revision,"IFP",
                                              pages,remarks,distribution));
                System.out.println("DID Document Publish ke"+doc+"="+did);
            } */
        }catch(Exception e){
            System.out.println("Create Transmittal Internal bukan Publish");
        }
        bindSource = new RichSelectOneChoice();
        bindSource.setValue("");
        bindEdmsEditRemarsk = new RichInputText();
        bindEdmsEditRemarsk.setValue("");
        bindRemarks = new RichInputText();
        bindRemarks.setValue("");
        bindEdmsRemarks = new RichInputText();
        bindEdmsRemarks.setValue("");
        bindHardcopyRemarks = new RichInputText();
        bindHardcopyRemarks.setValue("");
      //  setParamPath("Document Secure Workflow/Non Routine/EDMS");//
      setParamPath(request.getParameter("path")); 
//        GetSession().setAttribute("username", "weblogic");
//        GetSession().setAttribute("password", "34welcome12");
        System.out.println(getParamPath());
        if(getParamPath().contains("publish")){
           /*  bindOutputProjectTitle = new RichOutputLabel();
            bindOutputProjectTitle.setValue("Publish"); */
        }else{
            String[] parts = getParamPath().split("/");
            String part1 = parts[(parts.length-1)];
            String part2 = parts[(parts.length-2)];
            bindOutputProjectTitle = new RichOutputLabel();
            bindOutputProjectTitle.setValue(part1);
            setRoutineNonRoutine(part2);   
        }
        bindCompanyName=new RichOutputLabel();
        bindCompanyName.setValue(CompanyApName);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindOutputProjectTitle);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCompanyName);
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        if(getParamPath().contains("publish")){
           /*  setProjectorOrganization("Publish");
            RIDCClass ridc = new RIDCClass(GetSession().getAttribute("username").toString(),GetSession().getAttribute("password").toString());
            DataObject object = ridc.FolderInfo("Document Secure Workflow/Publish/Documents");
            if(object == null){
                ridc.CreateFolder("Documents", "Document Secure Workflow/Publish");
            } */
        }else{
            if(getParamPath().contains("Non Routine")){
                setProjectorOrganization("Project");
                //RIDCClass ridc = new RIDCClass(GetSession().getAttribute("username").toString(),GetSession().getAttribute("password").toString());
                RIDCClass ridc = new RIDCClass(Userlogin,getPassword());
                DataObject object = ridc.FolderInfo(getParamPath()+"/Documents");
                if(object == null){
                    ridc.CreateFolder("Documents", getParamPath());
                }
                OperationBinding method = bindings.getOperationBinding("ExecuteGetProjectId");
                Map paramsMap = method.getParamsMap();
                paramsMap.put("projectTitle",bindOutputProjectTitle.getValue().toString());
                method.execute();
                AttributeBinding projectId = (AttributeBinding) bindings.get("ProjectId");
                setProjectOrgId(projectId.getInputValue()==null?"":projectId.getInputValue().toString());
            }else{
                setProjectorOrganization("Organization");
                int year = Calendar.getInstance().get(Calendar.YEAR);
                RIDCClass ridc = new RIDCClass(Userlogin,getPassword());
                //RIDCClass ridc = new RIDCClass(GetSession().getAttribute("username").toString(),GetSession().getAttribute("password").toString());
                DataObject object = ridc.FolderInfo(getParamPath()+"/"+String.valueOf(year));
                if(object == null){
                    ridc.CreateFolder(String.valueOf(year), getParamPath());
                    ridc.CreateFolder("Documents", getParamPath()+"/"+String.valueOf(year));
                }else{
                    DataObject object1 = ridc.FolderInfo(getParamPath()+"/"+String.valueOf(year)+"/Documents");
                    if(object1 == null){
                        ridc.CreateFolder("Documents", getParamPath()+"/"+String.valueOf(year));
                    }
                }
                OperationBinding method = bindings.getOperationBinding("ExecuteGetOrgId");
                Map paramsMap = method.getParamsMap();
                paramsMap.put("organizationName",bindOutputProjectTitle.getValue().toString());
                method.execute();
                AttributeBinding projectId = (AttributeBinding) bindings.get("OrgId");
                setProjectOrgId(projectId.getInputValue()==null?"":projectId.getInputValue().toString());
            }
        }
                
        try{
            if(getParamPath().contains("publish")){
                /* DCBindingContainer bindingsImpl = (DCBindingContainer) bindings;
                DCIteratorBinding dciter = bindingsImpl.findIteratorBinding("contractNumberPublish1Iterator");
                Row temp = dciter.getRowAtRangeIndex(0);
                OperationBinding method1 = bindings.getOperationBinding("EWParamsExternalUserPublish");
                Map paramsMap1 = method1.getParamsMap();
                paramsMap1.put("contract",temp.getAttribute("ContractNumber"));
                method1.execute(); */
            }else{
                if(getProjectorOrganization().contains("Project")){
                    OperationBinding method = bindings.getOperationBinding("EWParamsContract");
                    Map paramsMap = method.getParamsMap();
                    paramsMap.put("project",bindOutputProjectTitle.getValue().toString());
                    method.execute();            
                    DCBindingContainer bindingsImpl = (DCBindingContainer) bindings;
                    DCIteratorBinding dciter = bindingsImpl.findIteratorBinding("contractNumberVO1Iterator");
                    Row temp = dciter.getRowAtRangeIndex(0);
                    OperationBinding method1 = bindings.getOperationBinding("EWParamExternalUser");
                    Map paramsMap1 = method1.getParamsMap();
                    paramsMap1.put("project",temp.getAttribute("ProjectId"));
                    paramsMap1.put("contract",temp.getAttribute("ContractNumber"));
                    method1.execute();
                }else{
                    OperationBinding method = bindings.getOperationBinding("EWParamsContractOrganization");
                    Map paramsMap = method.getParamsMap();
                    paramsMap.put("organization",bindOutputProjectTitle.getValue().toString());
                    method.execute();
                    DCBindingContainer bindingsImpl = (DCBindingContainer) bindings;
                    DCIteratorBinding dciter = bindingsImpl.findIteratorBinding("contractNumberOrganizationVO1Iterator");
                    Row temp = dciter.getRowAtRangeIndex(0);
                    OperationBinding method1 = bindings.getOperationBinding("EWParamsExternalUserOrganization");
                    Map paramsMap1 = method1.getParamsMap();
                    paramsMap1.put("organization",temp.getAttribute("OrganizationId"));
                    paramsMap1.put("contract",temp.getAttribute("ContractNumber"));
                    method1.execute();
                }   
            }
        }catch(Exception e){
            e.getMessage();
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
    public String GetCreatorEmail(){
        ApplicationModule am = ADFUtils.getApplicationModuleForDataControl("AppModuleExternalTransmittalDataControl");
        ViewObject voGetPar = am.findViewObject("UsersInternalVO1");
        voGetPar.setWhereClause("USERNAME = '"+Userlogin+"'");//ADFContext.getCurrent().getSecurityContext().getUserName()
        voGetPar.executeQuery();
        String email = "";
        while(voGetPar.hasNext()){
            Row row = voGetPar.next();
            email = row.getAttribute("Email")==null ? "":row.getAttribute("Email").toString();
        }
        return email;
    }

    public void setBindSource(RichSelectOneChoice bindSource) {
        this.bindSource = bindSource;
    }

    public RichSelectOneChoice getBindSource() {
        return bindSource;
    }

    public String GetConfig(String param) {
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding obGetConfig = bindings.getOperationBinding("ExecuteGetConfigWithParams");
        obGetConfig.getParamsMap().put("keyConfig", param);
        AttributeBinding abConfigValue = (AttributeBinding)bindings.get("KeyValue");
        obGetConfig.execute();
        return abConfigValue.getInputValue().toString();
    }

    public void sentViaChange(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue().toString().equalsIgnoreCase("EDMS")){
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            bindPopUpEdms.show(ph);
        }else if(valueChangeEvent.getNewValue().toString().equalsIgnoreCase("Electronic")){
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            binderPopUpElectronic.show(ph);
        }else if(valueChangeEvent.getNewValue().toString().equalsIgnoreCase("Physical")){
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            bindPopUpHardcopy.show(ph);
        }
        else{
            
        }
        bindSource.resetValue();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSource);
    }

    public void setBindPopUpEdms(RichPopup bindPopUpEdms) {
        this.bindPopUpEdms = bindPopUpEdms;
    }

    public RichPopup getBindPopUpEdms() {
        return bindPopUpEdms;
    }

    public void setBindUser(RichSelectOneChoice bindUser) {
        this.bindUser = bindUser;
    }

    public RichSelectOneChoice getBindUser() {
        return bindUser;
    }

    public void AddToList(ActionEvent actionEvent) {
        BindingContext bindingctx = BindingContext.getCurrent();
        BindingContainer bindings = null;
        bindings = bindingctx.getCurrentBindingsEntry();
        DCBindingContainer bindingsImpl = (DCBindingContainer) bindings;
        DCIteratorBinding dciter = null;
        Row temp = null;
        if(getParamPath().contains("publish")){
            dciter = bindingsImpl.findIteratorBinding("ExternalUserPublishVO1Iterator");// your lookup iterator
            temp = dciter.getRowAtRangeIndex((Integer.parseInt(bindUserPublish.getValue().toString()))); 
        }else{
            if(projectorOrganization.equalsIgnoreCase("Project")){
                dciter = bindingsImpl.findIteratorBinding("ExternalUserVO1Iterator");// your lookup iterator
                temp = dciter.getRowAtRangeIndex((Integer.parseInt(bindUser.getValue().toString())));    
            }else{
                dciter = bindingsImpl.findIteratorBinding("ExternalUserOrganizationVo1Iterator");// your lookup iterator
                temp = dciter.getRowAtRangeIndex((Integer.parseInt(bindUserOrganization.getValue().toString())));
            }
        }
        
        
//        System.out.println( temp.getAttribute("Username").toString()+"-"+temp.getAttribute("ContactRole").toString());
        int count=0;
        for(int i=0;i<dataUser.size();i++){
            if(dataUser.get(i).getUsername().toString().equalsIgnoreCase(temp.getAttribute("Username").toString())&&dataUser.get(i).getContactRole().toString().equalsIgnoreCase(temp.getAttribute("ContactRole").toString())){
                count++;
            }
        }
        if(count==0)
        dataUser.add(new DataUser(temp.getAttribute("Id").toString(),temp.getAttribute("Username").toString(),temp.getAttribute("ContactRole").toString(),temp.getAttribute("ContactEmail").toString()));
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableUser);
    }

    public void setDataUser(List<DataUser> dataUser) {
        this.dataUser = dataUser;
    }

    public List<DataUser> getDataUser() {
        return dataUser;
    }

    public void setTableUser(RichTable tableUser) {
        this.tableUser = tableUser;
    }

    public RichTable getTableUser() {
        return tableUser;
    }

    public void DeleteUser(ActionEvent actionEvent) {
        int tempHapus = Integer.parseInt(actionEvent.getComponent().getAttributes().get("delIndex").toString());
        dataUser.remove(tempHapus);
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableUser);
    }

    public void setBinderPopUpElectronic(RichPopup binderPopUpElectronic) {
        this.binderPopUpElectronic = binderPopUpElectronic;
    }

    public RichPopup getBinderPopUpElectronic() {
        return binderPopUpElectronic;
    }
    
    public BindingContainer GetBindings(){
        BindingContext bindingctx = BindingContext.getCurrent();
        BindingContainer bindings = bindingctx.getCurrentBindingsEntry();
        return bindings;
    }

    public void onChangeElectronicFile(ValueChangeEvent valueChangeEvent) throws IOException {
//        file = (UploadedFile)valueChangeEvent.getNewValue();
//        fileLength = file.getLength();
//        fileInputStream = file.getInputStream();
//        bindElectronicName.setValue(file.getFilename());
//        fileName = bindElectronicName.getValue().toString().trim();
//        fileContentType = file.getContentType();
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindElectronicName);
        file = (UploadedFile)valueChangeEvent.getNewValue();
        fileLength = file.getLength();
        fileInputStream = file.getInputStream();
        bindElectronicName.setValue(file.getFilename());
        fileName = bindElectronicName.getValue().toString().trim();
        fileContentType = file.getContentType();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindElectronicName);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String docPath = "";
        if(projectorOrganization.equalsIgnoreCase("Project")){
            docPath = paramPath+"/Documents";
        }else{
            docPath = paramPath+"/"+year+"/Documents";
        }
//        RIDCClass ridc = new RIDCClass(GetSession().getAttribute("username").toString(),
//                                           GetSession().getAttribute("password").toString());
                RIDCClass ridc = new RIDCClass(Userlogin,
                                           getPassword());
        String folderGUID = ridc.FolderInfo(docPath).get("fFolderGUID").toString();
        //DataResultSet rs = ridc.FolderSearchFile(folderGUID, fileName);
        
        OperationBinding method = GetBindings().getOperationBinding("EWIsDocExist");
        Map paramsMap = method.getParamsMap();
        paramsMap.put("folderGUID",folderGUID);
        paramsMap.put("fileName",fileName);
        method.execute();
        BindingContainer bindings2 = getBinding();
        DCIteratorBinding iterBind= (DCIteratorBinding)bindings2.get("IsDocExistVO1Iterator");
        RowSetIterator rsi = iterBind.getRowSetIterator();
        int i = 0;
        for(Row row : rsi.getAllRowsInRange()){
            try{
                i++;
            }catch(NullPointerException e){
                System.out.println("No Latest Id");
            }
        }
        if(i>0){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "File is already exist in this Project", "");
            FacesContext.getCurrentInstance().addMessage(null, msg); 
            //bindUploadedFile.resetValue();
            bindUploadedFile = new RichInputFile();
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindUploadedFile);
            bindElectronicName.setValue("");
            bindDocNumber.setValue("");
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocNumber);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindElectronicName);
            fileName = "";
        }else{
            bindElectronicName.setValue(fileName);
            bindDocNumber.setValue(getDocumentNumber(fileName));
            binDocTitle.setValue(getDocumentTitle(fileName));
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindElectronicName);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocNumber);
            AdfFacesContext.getCurrentInstance().addPartialTarget(binDocTitle);
        }
    }

    public void setBindElectronicName(RichInputText bindElectronicName) {
        this.bindElectronicName = bindElectronicName;
    }

    public RichInputText getBindElectronicName() {
        return bindElectronicName;
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

    public void setBindPopUpHardcopy(RichPopup bindPopUpHardcopy) {
        this.bindPopUpHardcopy = bindPopUpHardcopy;
    }

    public RichPopup getBindPopUpHardcopy() {
        return bindPopUpHardcopy;
    }

    public void ElectronicDialogLsnr(DialogEvent dialogEvent) {
        
        if(dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)){
            FacesContext ctx = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest)ctx.getExternalContext().getRequest();
//            HttpSession session =  request.getSession(true);
//            //System.out.println(session.getAttribute("username").toString());
//            RIDCClass ridc = new RIDCClass(session.getAttribute("username").toString(),session.getAttribute("password").toString());
                        RIDCClass ridc =
            new RIDCClass(Userlogin,
                         getPassword());
            int year = Calendar.getInstance().get(Calendar.YEAR);
            String docPath = "";
            if(projectorOrganization.equalsIgnoreCase("Project")){
                docPath = paramPath+"/Documents";
            }else{
                docPath = paramPath+"/"+year+"/Documents";    
            }
            String folderGUID = ridc.FolderInfo(docPath).get("fFolderGUID").toString();
            String checkin = ridc.CheckinElectronic(folderGUID,bindElectronicName.getValue().toString(), bindDocNumber.getValue().toString(), binDocTitle.getValue().toString(), bindRevisions.getValue().toString(), fileInputStream, fileLength, fileName);
            DataObject object = ridc.GetDocInfo(checkin);
            ridc.UpdateDocxRobjId(object.get("dID").toString(),object.get("dDocName").toString());
            BindingContext bindingctx = BindingContext.getCurrent();
            BindingContainer bindings = null;
            bindings = bindingctx.getCurrentBindingsEntry();
            DCBindingContainer bindingsImpl = (DCBindingContainer) bindings;
            DCIteratorBinding dciter = null;
            dciter = bindingsImpl.findIteratorBinding("ListDocumentStatusVO1Iterator");// your lookup iterator
            Row temp = dciter.getRowAtRangeIndex((Integer.parseInt(bindDocStatus.getValue().toString())));
            System.out.println(temp.getAttribute("Value").toString());
            tempDoc.add(new TempDocuments(object.get("dID").toString(),object.get("dDocName").toString(),"Transmittal",object.get("dFormat").toString(),bindElectronicName.getValue().toString(),
                                          bindDocNumber.getValue().toString(),binDocTitle.getValue().toString(),bindRevisions.getValue().toString(),temp.getAttribute("Description").toString(),bindPages.getValue().toString(),bindRemarks.getValue().toString(),
                                          "Electronic"));
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableDoc);
        }else{
            
        }
        ResetValue(bindElectronicName);
        ResetValue(bindUploadedFile);
        ResetValue(bindDocNumber);
        ResetValue(binDocTitle);
        ResetValue(bindPages);
        bindPages.setValue("1");
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindPages);
        ResetValue(bindRemarks);
        ResetValue(bindRevisions);
    }
    
    public void ResetValue(RichInputText param){
        param.setValue("");
        AdfFacesContext.getCurrentInstance().addPartialTarget(param);
    }
    
    public void ResetValue(RichInputFile param){
        param.resetValue();
        param = new RichInputFile();
        AdfFacesContext.getCurrentInstance().addPartialTarget(param);
    }

    public void setBindDocType(RichInputText bindDocType) {
        this.bindDocType = bindDocType;
    }

    public RichInputText getBindDocType() {
        return bindDocType;
    }

    public void setBindDocNumber(RichInputText bindDocNumber) {
        this.bindDocNumber = bindDocNumber;
    }

    public RichInputText getBindDocNumber() {
        return bindDocNumber;
    }

    public void setBinDocTitle(RichInputText binDocTitle) {
        this.binDocTitle = binDocTitle;
    }

    public RichInputText getBinDocTitle() {
        return binDocTitle;
    }

    public void setBindDocSource(RichSelectOneChoice bindDocSource) {
        this.bindDocSource = bindDocSource;
    }

    public RichSelectOneChoice getBindDocSource() {
        return bindDocSource;
    }

    public void setBindPages(RichInputText bindPages) {
        this.bindPages = bindPages;
    }

    public RichInputText getBindPages() {
        return bindPages;
    }

    public void setBindRemarks(RichInputText bindRemarks) {
        this.bindRemarks = bindRemarks;
    }

    public RichInputText getBindRemarks() {
        return bindRemarks;
    }

    public void setBindRevisions(RichInputText bindRevisions) {
        this.bindRevisions = bindRevisions;
    }

    public RichInputText getBindRevisions() {
        return bindRevisions;
    }

    public void setTempDoc(List<TempDocuments> tempDoc) {
        this.tempDoc = tempDoc;
    }

    public List<TempDocuments> getTempDoc() {
        return tempDoc;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setBindDocStatus(RichSelectOneChoice bindDocStatus) {
        this.bindDocStatus = bindDocStatus;
    }

    public RichSelectOneChoice getBindDocStatus() {
        return bindDocStatus;
    }

    public void setBindTableDoc(RichTable bindTableDoc) {
        this.bindTableDoc = bindTableDoc;
    }

    public RichTable getBindTableDoc() {
        return bindTableDoc;
    }

    public void SelectTreeLsnr(SelectionEvent selectionEvent) {
        RichTree _tree = (RichTree)selectionEvent.getSource();
        RowKeySet _rkset = _tree.getSelectedRowKeys();

        Iterator _rkIterate = _rkset.iterator();

        if (_rkIterate.hasNext()) {
            List key = (List)_rkIterate.next();
            JUCtrlHierBinding treeBinds = null;
            CollectionModel collectionModel =
                (CollectionModel)_tree.getValue();
            treeBinds = (JUCtrlHierBinding)collectionModel.getWrappedData();
            JUCtrlHierNodeBinding nodeBinding = null;
            nodeBinding = treeBinds.findNodeByKeyPath(key);
            Row rw = nodeBinding.getRow();
            // Get Document ID
            String ID = rw.getAttribute("Ffolderguid").toString();
            System.out.println(ID);
            //System.out.println(GetSession().getAttribute("username").toString());
            //RIDCClass ridc = new RIDCClass(GetSession().getAttribute("username").toString(),GetSession().getAttribute("password").toString());
            RIDCClass ridc = new RIDCClass(Userlogin,
                                       getPassword());
            childFiles.clear();
            try{
                for(DataObject object:ridc.FolderBrowseFile(ID).getRows()){
                    if(object.get("xStatus")!="Registered"){
                        System.out.println(object.get("xDocName").toString()+"--"+object.get("xOwnerName").toString());
                        childFiles.add(new ChildFiles(object.get("fFileGUID").toString(),object.get("dID").toString(),object.get("dDocName").toString(),
                                                      object.get("dFormat").toString(),object.get("xDocNumber").toString(),
                                                      object.get("dDocTitle").toString(),object.get("xDocName").toString(),
                                                      object.get("xOwnerName").toString(),object.get("fFileName").toString()));   
                    }
                }
            }catch(Exception e){
                System.out.println("Error select tree listener select file EDMS "+e.getMessage());
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableFilesEDMS);
        }
    }

    public void setBindTableFilesEDMS(RichTable bindTableFilesEDMS) {
        this.bindTableFilesEDMS = bindTableFilesEDMS;
    }

    public RichTable getBindTableFilesEDMS() {
        return bindTableFilesEDMS;
    }

    public void setChildFiles(List<ChildFiles> childFiles) {
        this.childFiles = childFiles;
    }

    public List<ChildFiles> getChildFiles() {
        return childFiles;
    }

    public void setBindRadioEDMS(RichSelectBooleanRadio bindRadioEDMS) {
        this.bindRadioEDMS = bindRadioEDMS;
    }

    public RichSelectBooleanRadio getBindRadioEDMS() {
        return bindRadioEDMS;
    }

    public void SelectRadioButton(ValueChangeEvent valueChangeEvent) {
        System.out.println(valueChangeEvent.getNewValue().toString());
    }

    public void PhysicalDialogLsnr(DialogEvent dialogEvent) {
        if(dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)){
            BindingContext bindingctx = BindingContext.getCurrent();
            BindingContainer bindings = null;
            bindings = bindingctx.getCurrentBindingsEntry();
            DCBindingContainer bindingsImpl = (DCBindingContainer) bindings;
            DCIteratorBinding dciter = null;
            dciter = bindingsImpl.findIteratorBinding("ListDocumentStatusVO1Iterator");// your lookup iterator
            Row temp = dciter.getRowAtRangeIndex((Integer.parseInt(bindHardcopyDocStatus.getValue().toString())));
//            System.out.println(temp.getAttribute("Value").toString());
            DCIteratorBinding dciter2 = null;
            dciter2 = bindingsImpl.findIteratorBinding("ListSentHardcopyVO1Iterator");// your lookup iterator
            Row temp2 = dciter2.getRowAtRangeIndex((Integer.parseInt(bindHardcopyDistributionMethod.getValue().toString())));
            System.out.println(temp2.getAttribute("Value").toString());            
            //get seq number for id
            OperationBinding method = bindings.getOperationBinding("getSeqValue");
            Map paramsMap = method.getParamsMap();
            paramsMap.put("sequenceName","PHE_DSWF_DOC_SEQ")  ;
            Object ob = method.execute();
            tempDoc.add(new TempDocuments(ob.toString(),"","Transmittal","","",bindHardcopyDocNumber.getValue().toString(),bindHardcopyDocTitle.getValue().toString(),bindHardcopyRevision.getValue().toString(),
                                          temp.getAttribute("Description").toString(),bindHardcopyPages.getValue().toString(),bindHardcopyRemarks.getValue().toString(),
                                          temp2.getAttribute("Value").toString()));
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableDoc);
        }
        ResetValue(bindHardcopyDocNumber);
        ResetValue(bindHardcopyDocNumber);
        ResetValue(bindHardcopyRemarks);
        ResetValue(bindHardcopyDocTitle);
        ResetValue(bindHardcopyPages);
        bindHardcopyPages.setValue("1");
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindHardcopyPages);
        ResetValue(bindHardcopyRevision);
    }

    public void setBindHardcopyDocNumber(RichInputText bindHardcopyDocNumber) {
        this.bindHardcopyDocNumber = bindHardcopyDocNumber;
    }

    public RichInputText getBindHardcopyDocNumber() {
        return bindHardcopyDocNumber;
    }

    public void setBindHardcopyDocTitle(RichInputText bindHardcopyDocTitle) {
        this.bindHardcopyDocTitle = bindHardcopyDocTitle;
    }

    public RichInputText getBindHardcopyDocTitle() {
        return bindHardcopyDocTitle;
    }

    public void setBindHardcopyRevision(RichInputText bindHardcopyRevision) {
        this.bindHardcopyRevision = bindHardcopyRevision;
    }

    public RichInputText getBindHardcopyRevision() {
        return bindHardcopyRevision;
    }

    public void setBindHardcopyDocStatus(RichSelectOneChoice bindHardcopyDocStatus) {
        this.bindHardcopyDocStatus = bindHardcopyDocStatus;
    }

    public RichSelectOneChoice getBindHardcopyDocStatus() {
        return bindHardcopyDocStatus;
    }

    public void setBindHardcopyPages(RichInputText bindHardcopyPages) {
        this.bindHardcopyPages = bindHardcopyPages;
    }

    public RichInputText getBindHardcopyPages() {
        return bindHardcopyPages;
    }

    public void setBindHardcopyRemarks(RichInputText bindHardcopyRemarks) {
        this.bindHardcopyRemarks = bindHardcopyRemarks;
    }

    public RichInputText getBindHardcopyRemarks() {
        return bindHardcopyRemarks;
    }

    public void setBindHardcopyDistributionMethod(RichSelectOneChoice bindHardcopyDistributionMethod) {
        this.bindHardcopyDistributionMethod = bindHardcopyDistributionMethod;
    }

    public RichSelectOneChoice getBindHardcopyDistributionMethod() {
        return bindHardcopyDistributionMethod;
    }
    
    public String getParameter(String name) {  
         HttpServletRequest request =  
           (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
         String rsParam= request.getParameter(name);  
         return rsParam;  
       } 

    public void setParamPath(String paramPath) {
        this.paramPath = paramPath;
    }

    public String getParamPath() {
        return paramPath;
    }

    public void OnChangeRadio(ValueChangeEvent valueChangeEvent) {
        //System.out.println(valueChangeEvent.getNewValue().toString());
        if(valueChangeEvent.getNewValue().toString().equalsIgnoreCase("true")){
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            Map p = ((UIComponent)valueChangeEvent.getSource()).getAttributes();
            //System.out.println(p.get("rowvalIndex").toString());
            setSelectedEDMSFile(p.get("rowvalIndex").toString());
        }        
    }

    public void setSelectedEDMSFile(String selectedEDMSFile) {
        this.selectedEDMSFile = selectedEDMSFile;
    }

    public String getSelectedEDMSFile() {
        return selectedEDMSFile;
    }

    public void setBindEdmsDocNumber(RichInputText bindEdmsDocNumber) {
        this.bindEdmsDocNumber = bindEdmsDocNumber;
    }

    public RichInputText getBindEdmsDocNumber() {
        return bindEdmsDocNumber;
    }

    public void setBindEdmsDocTitle(RichInputText bindEdmsDocTitle) {
        this.bindEdmsDocTitle = bindEdmsDocTitle;
    }

    public RichInputText getBindEdmsDocTitle() {
        return bindEdmsDocTitle;
    }

    public void setBindEdmsDocStatus(RichSelectOneChoice bindEdmsDocStatus) {
        this.bindEdmsDocStatus = bindEdmsDocStatus;
    }

    public RichSelectOneChoice getBindEdmsDocStatus() {
        return bindEdmsDocStatus;
    }

    public void setBindEdmsPages(RichInputText bindEdmsPages) {
        this.bindEdmsPages = bindEdmsPages;
    }

    public RichInputText getBindEdmsPages() {
        return bindEdmsPages;
    }

    public void setBindEdmsRemarks(RichInputText bindEdmsRemarks) {
        this.bindEdmsRemarks = bindEdmsRemarks;
    }

    public RichInputText getBindEdmsRemarks() {
        return bindEdmsRemarks;
    }

    public void setBindEdmsRevisions(RichInputText bindEdmsRevisions) {
        this.bindEdmsRevisions = bindEdmsRevisions;
    }

    public RichInputText getBindEdmsRevisions() {
        return bindEdmsRevisions;
    }

    public void dialogBoxEDMSForm(DialogEvent dialogEvent) {
        if(dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)){
            RIDCClass ridc = new RIDCClass(getWeblogicusername(),getWeblogicpassword());
            int year = Calendar.getInstance().get(Calendar.YEAR);
            String docPath = "";
            if(projectorOrganization.equalsIgnoreCase("Project")){
                docPath = paramPath+"/Documents";
            }else{
                docPath = paramPath+"/"+year+"/Documents";    
            }
            String folderGUID = ridc.FolderInfo(docPath).get("fFolderGUID").toString();
            DataObject obj = ridc.CopyFile(folderGUID, childFiles.get(Integer.parseInt(getSelectedEDMSFile())).getFFileGUID());
            ridc.UpdateAfterCopyFile(obj.get("dID").toString(), obj.get("dDocName").toString(),
                                     bindEdmsDocTitle.getValue().toString(),obj.get("xDocName").toString(),
                                    // bindEdmsDocNumber.getValue().toString(),bindEdmsRevisions.getValue().toString(),GetSession().getAttribute("username").toString());
                                    bindEdmsDocNumber.getValue().toString(),bindEdmsRevisions.getValue().toString(),Userlogin);
            BindingContext bindingctx = BindingContext.getCurrent();
            BindingContainer bindings = null;
            bindings = bindingctx.getCurrentBindingsEntry();
            DCBindingContainer bindingsImpl = (DCBindingContainer) bindings;
            DCIteratorBinding dciter = null;
            dciter = bindingsImpl.findIteratorBinding("ListDocumentStatusVO1Iterator");// your lookup iterator
            Row temp = dciter.getRowAtRangeIndex((Integer.parseInt(bindEdmsEditDocStatus.getValue().toString())));
            tempDoc.add(new TempDocuments(obj.get("dID").toString(),obj.get("dDocName").toString(), "Transmittal", obj.get("dFormat").toString(),obj.get("xDocName").toString(),
                                          bindEdmsDocNumber.getValue().toString(),bindEdmsDocTitle.getValue().toString(),bindEdmsRevisions.getValue().toString(),temp.getAttribute("Description").toString(),bindEdmsPages.getValue().toString(),bindEdmsRemarks.getValue().toString(),"Electronic"));
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableDoc);
        }else{
            
        }
        ResetValue(bindEdmsDocNumber);
        ResetValue(bindEdmsDocTitle);
        ResetValue(bindEdmsPages);
        bindEdmsPages.setValue("1");
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEdmsPages);
        ResetValue(bindEdmsRemarks);
        ResetValue(bindEdmsRevisions);
        bindPopUpEdms.hide();
    }

    public void setBindTreeEdms(RichTree bindTreeEdms) {
        this.bindTreeEdms = bindTreeEdms;
    }

    public RichTree getBindTreeEdms() {
        return bindTreeEdms;
    }
    
    public void ResetTree() {
        System.out.println("reset tree");
        childFiles.clear();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableFilesEDMS);
        CollapseAllNodes();
        try {
            bindTreeEdms.getSelectedRowKeys().removeAll();
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTreeEdms);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void CollapseAllNodes() {
        UIXTree tree = getBindTreeEdms();
        RowKeySet _disclosedRowKeys = tree.getDisclosedRowKeys();
        if (_disclosedRowKeys != null && _disclosedRowKeys.size() > 0) {
            _disclosedRowKeys.clear();
        }
        tree.setDisclosedRowKeys(_disclosedRowKeys);
    }

    public void CancelPopUpEdms(ActionEvent actionEvent) {
        ResetTree();
        bindPopUpEdms.hide();
    }

    public void ActionSelectEdms(ActionEvent actionEvent) {
//        String docPath = paramPath+"/Documents";
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String docPath = "";
        if(projectorOrganization.equalsIgnoreCase("Project")){
            docPath = paramPath+"/Documents";
        }else{
            docPath = paramPath+"/"+year+"/Documents";
        }
//        RIDCClass ridc = new RIDCClass(GetSession().getAttribute("username").toString(),
//                                           GetSession().getAttribute("password").toString());
        RIDCClass ridc = new RIDCClass(Userlogin,  getPassword());
        String folderGUID = ridc.FolderInfo(docPath).get("fFolderGUID").toString();
        //DataResultSet rs = ridc.FolderSearchFile(folderGUID, childFiles.get(Integer.parseInt(getSelectedEDMSFile())).getOriginalName());
        OperationBinding method = GetBindings().getOperationBinding("EWIsDocExist");
        Map paramsMap = method.getParamsMap();
        paramsMap.put("folderGUID",folderGUID);
        paramsMap.put("fileName",childFiles.get(Integer.parseInt(getSelectedEDMSFile())).getOriginalName());
        method.execute();
        BindingContainer bindings2 = getBinding();
        DCIteratorBinding iterBind= (DCIteratorBinding)bindings2.get("IsDocExistVO1Iterator");
        RowSetIterator rsi = iterBind.getRowSetIterator();
        int i = 0;
        for(Row row : rsi.getAllRowsInRange()){
            try{
                i++;
            }catch(NullPointerException e){
                System.out.println("No Latest Id");
            }
        }
        if(i>0){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "File is already exist in this Project", "");
            FacesContext.getCurrentInstance().addMessage(null, msg); 
        }else{
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            bindEdmsDocNumber.setValue(childFiles.get(Integer.parseInt(getSelectedEDMSFile())).getXDocNumber());
            bindEdmsDocTitle.setValue(childFiles.get(Integer.parseInt(getSelectedEDMSFile())).getDDocTitle());
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindEdmsDocNumber);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindEdmsDocTitle);
            bindPopUpEdmsForm.show(ph);
        }
    }
    
    public String getDocumentTitle(String fileName){
        fileName = fileName.replace("'", "''");
        String returnan = fileName;
        try{
            returnan = fileName.substring(fileName.indexOf(" ")+1, fileName.length());
        }catch(Exception e){
        }
        return returnan;
    }
    
    public String getDocumentNumber(String fileName){
        return fileName.split(" ")[0];
    }

    public void setBindPopUpEdmsForm(RichPopup bindPopUpEdmsForm) {
        this.bindPopUpEdmsForm = bindPopUpEdmsForm;
    }

    public RichPopup getBindPopUpEdmsForm() {
        return bindPopUpEdmsForm;
    }
    
    public BindingContainer getBinding(){
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        return bindings;
    }
    
    public ApplicationModule GetApplicationModule(String appName){
        return ADFUtils.getApplicationModuleForDataControl(appName);
    }
    
    public oracle.jbo.domain.Date ChangeDate(java.util.Date pJavaDate){
        return new oracle.jbo.domain.Date(new Timestamp(pJavaDate.getTime()));                
    }
    
    public oracle.jbo.domain.Date getNowDate(java.util.Date pJavaDate){
        return new oracle.jbo.domain.Date(new Timestamp(pJavaDate.getTime()));    
    }
    public oracle.jbo.domain.Date GetNewDate(java.util.Date pJavaDate) throws ParseException {
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String tempDate = formater.format(pJavaDate).replace("00:00:00", "23:59:59");
        return new oracle.jbo.domain.Date(new Timestamp(formater.parse(tempDate).getTime()));                
    }
    
    public HttpSession GetSession(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)ctx.getExternalContext().getRequest();
        HttpSession session =  request.getSession(true);
        return session;
    }
    
    public String GetFullNameFromUsername(String username){
        ApplicationModule app = GetApplicationModule("AppModuleExternalTransmittalDataControl");
        ViewObject voParIn = app.findViewObject("UsersInternalVO1");
        voParIn.setWhereClause("upper(USERNAME) like upper('%"+username+"%')");
        voParIn.executeQuery();        
        
        String fullName="";
        while(voParIn.hasNext()){
            Row row = voParIn.next();
            fullName = row.getAttribute("Fullname").toString();
        }
//        DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
//        DCIteratorBinding iterBind= (DCIteratorBinding)dcBindings.get("UsersInternalVO1Iterator");
//        return (String)iterBind.getCurrentRow().getAttribute("Fullname");
        return fullName;
    }

    public void ActionCreateTransmittal(ActionEvent actionEvent) throws ParseException {
        int statusTidakLengkap=0;
        for(int cek=0;cek<tempDoc.size();cek++){
            if(tempDoc.get(cek).getDocumentStatus().equals("0")){
                statusTidakLengkap++;
            }
        }
        if(tempDoc.size()>0 && dataUser.size()>0 && statusTidakLengkap==0){
            String penerima= "";
            BindingContext bindingctx = BindingContext.getCurrent();
            BindingContainer bindings = null;
            bindings = bindingctx.getCurrentBindingsEntry();
            DCBindingContainer bindingsImpl = (DCBindingContainer) bindings;
            DCIteratorBinding dciter = null;
            Row temp = null;
            if(getParamPath().contains("publish")){
                dciter = bindingsImpl.findIteratorBinding("contractNumberPublish1Iterator");
                temp = dciter.getRowAtRangeIndex((Integer.parseInt(bindTransmittalContractorPublish.getValue().toString())));
            }else{
                if(projectorOrganization.equalsIgnoreCase("Project")){
                    dciter = bindingsImpl.findIteratorBinding("contractNumberVO1Iterator");
                    temp = dciter.getRowAtRangeIndex((Integer.parseInt(bindTransmittalContractor.getValue().toString())));
                }else{
                    dciter = bindingsImpl.findIteratorBinding("contractNumberOrganizationVO1Iterator");
                    temp = dciter.getRowAtRangeIndex((Integer.parseInt(bindTransmittalContractorOgranization.getValue().toString())));
                }
            }
            
            java.util.Date utilDate =(java.util.Date)bindTransmittalDueDate.getValue();
            SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String tempDate = formater.format(utilDate).replace("00:00:00", "23:59:59");
            String tranmisttalNo = "";
            String Company= bindCompanyName.getValue().toString();
            tranmisttalNo = Company+"-"+temp.getAttribute("ContractorCode").toString()+"-"+temp.getAttribute("ContractNumber")+"-"+bindTransmittalWoNo.getValue().toString().toUpperCase();
            String latestNo = "0001";
            OperationBinding method = bindings.getOperationBinding("ExecuteWithParams");
            Map paramsMap = method.getParamsMap();
            paramsMap.put("temp",tranmisttalNo);
            method.execute();
            BindingContainer bindings2 = getBinding();
            DCIteratorBinding iterBind= (DCIteratorBinding)bindings2.get("LatestTransmittalNoVo1Iterator");
            RowSetIterator rsi = iterBind.getRowSetIterator();
//            String descriptionLog = "Create Transmittal External "+GetSession().getAttribute("username").toString()+" to ";
            String descriptionLog = "Create Transmittal External "+Userlogin+" to ";
            for(Row row : rsi.getAllRowsInRange()){
                try{
                    String[] dir = row.getAttribute("Latestid").toString().split("-");
                    latestNo = dir[dir.length-1];
                    int b = 0;
                    b = Integer.parseInt(latestNo);
                    b = b+1;
                    latestNo = String.valueOf(b);
                }catch(NullPointerException e){
                    System.out.println("No Latest Id");
                }
            }
            if(latestNo.length()==1){
                latestNo = "000"+latestNo;
            }else if(latestNo.length()==2){
                latestNo = "00"+latestNo;
            }else if(latestNo.length()==3){
                latestNo = "0"+latestNo;
            }else{
            }
            
            ApplicationModule app = GetApplicationModule("AppModuleExternalTransmittalDataControl");
            oracle.jbo.domain.Date nowDate = getNowDate(new Date());
            System.out.println("now date = "+nowDate);
            
            try{
                File fileasli = null;
                fileasli =  new File("C:\\covernote_temp\\Cover Note External.xls");
                FileInputStream inp = null;
                try {
                    inp = new FileInputStream(fileasli);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Calendar cal = Calendar.getInstance();
                cal.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
                HSSFWorkbook my_xls_workbook = new HSSFWorkbook(inp); 
                HSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0); 
                Cell cell = null; 
                cell = my_worksheet.getRow(7).getCell(4);
                cell.setCellValue(temp.getAttribute("ContractorName").toString());
                cell = my_worksheet.getRow(8).getCell(4);
                cell.setCellValue(temp.getAttribute("ContractorAddress").toString());
                cell = my_worksheet.getRow(9).getCell(4);
                cell.setCellValue(temp.getAttribute("ContractorPhonenumber").toString());
                cell = my_worksheet.getRow(13).getCell(4);
                cell.setCellValue(tranmisttalNo+"-"+latestNo);
                cell = my_worksheet.getRow(14).getCell(4);
                cell.setCellValue(nowDate.toString());
                cell = my_worksheet.getRow(15).getCell(4);
                cell.setCellValue(GetNewDate(formater.parse(tempDate)).toString());
                cell = my_worksheet.getRow(16).getCell(4);
                cell.setCellValue(temp.getAttribute("ContractNumber").toString());  
                cell = my_worksheet.getRow(17).getCell(4);
                cell.setCellValue(bindTransmittalWoNo.getValue().toString()); 
                cell = my_worksheet.getRow(18).getCell(4);
                cell.setCellValue(bindTransmittalSubject.getValue().toString()); 
                cell = my_worksheet.getRow(35).getCell(2);
                cell.setCellValue(GetFullNameFromUsername(Userlogin));//ADFContext.getCurrent().getSecurityContext().getUserName()));
                //edit by nanda 220814 - untuk mengganti format tanggal covernote
//                cell = my_worksheet.getRow(36).getCell(2);
//                cell.setCellValue(new Date().toString());
                cell = my_worksheet.getRow(36).getCell(2);
                cell.setCellType(cell.CELL_TYPE_STRING);
                cell.setCellValue(sdf.format(new java.util.Date()));
                cell = my_worksheet.getRow(37).getCell(2);
                cell.setCellType(cell.CELL_TYPE_STRING);
                cell.setCellValue(sdf2.format(new java.util.Date()));
                
                //insert into phe_dswf_master
                ViewObject vo = app.findViewObject("PheDswfMasterVO1");
                NameValuePairs nvp = new NameValuePairs();
                nvp.setAttribute("TransmittalId", tranmisttalNo+"-"+latestNo);
                nvp.setAttribute("TransmittalType", "External");
                nvp.setAttribute("TransmittalRnrCode", getRoutineNonRoutine());
                nvp.setAttribute("TransmittalSubject", bindTransmittalSubject.getValue().toString());
                nvp.setAttribute("ProjectorganizationId", getProjectOrgId());
                nvp.setAttribute("Companyap", "");
                nvp.setAttribute("Contractor", temp.getAttribute("ContractNumber").toString()+"-"+temp.getAttribute("ContractorName").toString());
                nvp.setAttribute("ContractorCode", temp.getAttribute("ContractorCode").toString());
                nvp.setAttribute("ContractNo", temp.getAttribute("ContractNumber").toString());
                nvp.setAttribute("ProjectorganizationName", bindOutputProjectTitle.getValue().toString());
//                nvp.setAttribute("TransmittalCreator", GetSession().getAttribute("username").toString());
                nvp.setAttribute("TransmittalCreator", Userlogin.toString());
                nvp.setAttribute("TransmittalDuedate",GetNewDate(formater.parse(tempDate)));
                nvp.setAttribute("TransmittalStatus", "In Progress");
                nvp.setAttribute("WoNo", bindTransmittalWoNo.getValue().toString().toUpperCase());
                vo.createAndInitRow(nvp);
                ViewObject voPar = app.findViewObject("PheDswfPaticipantVO1");
                ViewObject voUserRole = app.findViewObject("UserSecurityAttributesVO1");
                System.out.println("dataUser.size() external : " + dataUser.size());  
                for(int i=0;i<dataUser.size();i++){
                    if(i!=0 && i<dataUser.size()){
                        descriptionLog = descriptionLog+", ";
                        penerima = penerima+",";
                    }
                    penerima=penerima+dataUser.get(i).getContactEmail();
                    NameValuePairs nvpPar = new NameValuePairs();
                    nvpPar.setAttribute("TransmittalId", tranmisttalNo+"-"+latestNo);
//                    nvpPar.setAttribute("Sender", GetSession().getAttribute("username").toString());
                    nvpPar.setAttribute("Sender", Userlogin);
                    nvpPar.setAttribute("Receiver", dataUser.get(i).getUsername());
                    nvpPar.setAttribute("Role", dataUser.get(i).getContactRole());
                    voPar.createAndInitRow(nvpPar);
                    descriptionLog=descriptionLog+dataUser.get(i).getUsername();
                    //insert user security attributes
                    if(IsRoleExist(dataUser.get(i).getUsername(), "BusinessContact").equals("0")){
                        NameValuePairs nvpUserRole = new NameValuePairs();
                        nvpUserRole.setAttribute("Dusername",dataUser.get(i).getUsername());
                        nvpUserRole.setAttribute("Dattributetype", "role");
                        nvpUserRole.setAttribute("Dattributename", "BusinessContact");
                        nvpUserRole.setAttribute("Dattributeprivilege", "15");
                        voUserRole.createAndInitRow(nvpUserRole);
                    }  
                }
                my_worksheet.shiftRows(23, my_worksheet.getLastRowNum(), tempDoc.size()); 
                String DocString ="<table style=\"font-family:arial\" cellpadding=\"3\" cellspacing=\"3\">\n" + 
                "<tr style=\"background-color:#f0e68c\">\n" + 
                "<td>Document No</td>\n" + 
                "<td>Document Title</td>\n" + 
                "<td>Revision No</td>\n" + 
                "</tr>";
                System.out.println(DocString);
                System.out.print(tempDoc.size());
                for(int i=0;i<tempDoc.size();i++){
                //edit by bijak 110814 - add try catch
                try{
                    
                    my_worksheet.addMergedRegion(new CellRangeAddress(23+i,23+i,0,1));
                    my_worksheet.getRow(23+i).createCell(0);
                    cell = my_worksheet.getRow(23+i).getCell(0);
                    cell.setCellValue(i+1);
                    my_worksheet.addMergedRegion(new CellRangeAddress(23+i,23+i,2,3));
                    my_worksheet.getRow(23+i).createCell(2);
                    cell = my_worksheet.getRow(23+i).getCell(2);
                    cell.setCellValue(tempDoc.get(i).getDocumentNumber());
                    my_worksheet.getRow(23+i).createCell(4);
                    cell = my_worksheet.getRow(23+i).getCell(4);
                    cell.setCellValue(tempDoc.get(i).getDocumentTitle());
                    my_worksheet.getRow(23+i).createCell(5);
                    cell = my_worksheet.getRow(23+i).getCell(5);
                    cell.setCellValue(tempDoc.get(i).getRevision());
                    my_worksheet.getRow(23+i).createCell(6);
                    cell = my_worksheet.getRow(23+i).getCell(6);
                    cell.setCellValue(tempDoc.get(i).getDocumentStatus());
                    my_worksheet.getRow(23+i).createCell(7);
                    cell = my_worksheet.getRow(23+i).getCell(7);
                    cell.setCellValue(tempDoc.get(i).getPages());
                    my_worksheet.addMergedRegion(new CellRangeAddress(23+i,23+i,8,9));
                    my_worksheet.getRow(23+i).createCell(8);
                    cell = my_worksheet.getRow(23+i).getCell(8);
                    cell.setCellValue(tempDoc.get(i).getDistribution());
                    my_worksheet.getRow(23+i).createCell(10);
                    cell = my_worksheet.getRow(23+i).getCell(10);
                    cell.setCellValue(tempDoc.get(i).getRemarks());
                    //insert to phe_dswf_doc
                    ViewObject voDoc = app.findViewObject("PhdDswfDocVo1");
                    NameValuePairs nvpDoc = new NameValuePairs();
                    nvpDoc.setAttribute("Id", tempDoc.get(i).getDid());
                    nvpDoc.setAttribute("TransmittalId", tranmisttalNo+"-"+latestNo);
                    nvpDoc.setAttribute("ContentId", tempDoc.get(i).getDDocname());
                    nvpDoc.setAttribute("Type", "Transmittal");
                    nvpDoc.setAttribute("Format", tempDoc.get(i).getDocFormat());
//                    nvpDoc.setAttribute("Uploader", GetSession().getAttribute("username").toString());
                        nvpDoc.setAttribute("Uploader", Userlogin);
                    nvpDoc.setAttribute("DocName", tempDoc.get(i).getDocumentName());
                    nvpDoc.setAttribute("DocTitle", tempDoc.get(i).getDocumentTitle());
                    nvpDoc.setAttribute("DocNumber", tempDoc.get(i).getDocumentNumber());
                    nvpDoc.setAttribute("DocStatus", tempDoc.get(i).getDocumentStatus());
                    nvpDoc.setAttribute("DocSource", tempDoc.get(i).getDistribution());
                    nvpDoc.setAttribute("Pages", tempDoc.get(i).getPages());
                    nvpDoc.setAttribute("Remarks", tempDoc.get(i).getRemarks());
                    nvpDoc.setAttribute("Revision", tempDoc.get(i).getRevision());
                    nvpDoc.setAttribute("DocType", "Supporting");
                    if(getParamPath().contains("publish")){
                        nvpDoc.setAttribute("PublishStatus", "2");
                    }
                    voDoc.createAndInitRow(nvpDoc);
                    DocString = DocString + "<tr style=\"background-color:#dcdcdc\">" +
                                "<td>"+tempDoc.get(i).getDocumentNumber()+"</td>" +
                                "<td>"+tempDoc.get(i).getDocumentTitle()+"</td>" +
                                "<td>"+tempDoc.get(i).getRevision()+"</td></tr>";
                    //insert into doc_process
                    for(int j=0;j<dataUser.size();j++){
                        ViewObject voDocProcess = app.findViewObject("PheDocProcessVO1");
                        NameValuePairs nvpDocProcess = new NameValuePairs();
                        nvpDocProcess.setAttribute("Did", tempDoc.get(i).getDid());
                        nvpDocProcess.setAttribute("TransmittalId", tranmisttalNo+"-"+latestNo);
//                        nvpDocProcess.setAttribute("Sender", GetSession().getAttribute("username").toString());
                        nvpDocProcess.setAttribute("Sender", Userlogin);
                        nvpDocProcess.setAttribute("Recipient", dataUser.get(j).getUsername());
                        nvpDocProcess.setAttribute("DocTitle", tempDoc.get(i).getDocumentTitle());
                        nvpDocProcess.setAttribute("DocStatus", tempDoc.get(i).getDocumentStatus());
                        nvpDocProcess.setAttribute("DocType", "Supporting");
                        voDocProcess.createAndInitRow(nvpDocProcess);
                    }
                    }catch(Exception e){ 
                    // e.printStackTrace(); uncode to check
                        my_worksheet.addMergedRegion(new CellRangeAddress(23+i,23+i,0,1));
                        //add by bijak 1208 - to create row when getrow is null pointer
                        my_worksheet.createRow(23+i);
                        my_worksheet.getRow(23+i).createCell(0);
                        cell = my_worksheet.getRow(23+i).getCell(0);
                        cell.setCellValue(i+1);
                        my_worksheet.addMergedRegion(new CellRangeAddress(23+i,23+i,2,3));
                        my_worksheet.getRow(23+i).createCell(2);
                        cell = my_worksheet.getRow(23+i).getCell(2);
                        cell.setCellValue(tempDoc.get(i).getDocumentNumber());
                        my_worksheet.getRow(23+i).createCell(4);
                        cell = my_worksheet.getRow(23+i).getCell(4);
                        cell.setCellValue(tempDoc.get(i).getDocumentTitle());
                        my_worksheet.getRow(23+i).createCell(5);
                        cell = my_worksheet.getRow(23+i).getCell(5);
                        cell.setCellValue(tempDoc.get(i).getRevision());
                        my_worksheet.getRow(23+i).createCell(6);
                        cell = my_worksheet.getRow(23+i).getCell(6);
                        cell.setCellValue(tempDoc.get(i).getDocumentStatus());
                        my_worksheet.getRow(23+i).createCell(7);
                        cell = my_worksheet.getRow(23+i).getCell(7);
                        cell.setCellValue(tempDoc.get(i).getPages());
                        my_worksheet.addMergedRegion(new CellRangeAddress(23+i,23+i,8,9));
                        my_worksheet.getRow(23+i).createCell(8);
                        cell = my_worksheet.getRow(23+i).getCell(8);
                        cell.setCellValue(tempDoc.get(i).getDistribution());
                        my_worksheet.getRow(23+i).createCell(10);
                        cell = my_worksheet.getRow(23+i).getCell(10);
                        cell.setCellValue(tempDoc.get(i).getRemarks());
                        //insert to phe_dswf_doc
                        ViewObject voDoc = app.findViewObject("PhdDswfDocVo1");
                        NameValuePairs nvpDoc = new NameValuePairs();
                        nvpDoc.setAttribute("Id", tempDoc.get(i).getDid());
                        nvpDoc.setAttribute("TransmittalId", tranmisttalNo+"-"+latestNo);
                        nvpDoc.setAttribute("ContentId", tempDoc.get(i).getDDocname());
                        nvpDoc.setAttribute("Type", "Transmittal");
                        nvpDoc.setAttribute("Format", tempDoc.get(i).getDocFormat());
//                        nvpDoc.setAttribute("Uploader", GetSession().getAttribute("username").toString());
                        nvpDoc.setAttribute("Uploader", Userlogin);
                        nvpDoc.setAttribute("DocName", tempDoc.get(i).getDocumentName());
                        nvpDoc.setAttribute("DocTitle", tempDoc.get(i).getDocumentTitle());
                        nvpDoc.setAttribute("DocNumber", tempDoc.get(i).getDocumentNumber());
                        nvpDoc.setAttribute("DocStatus", tempDoc.get(i).getDocumentStatus());
                        nvpDoc.setAttribute("DocSource", tempDoc.get(i).getDistribution());
                        nvpDoc.setAttribute("Pages", tempDoc.get(i).getPages());
                        nvpDoc.setAttribute("Remarks", tempDoc.get(i).getRemarks());
                        nvpDoc.setAttribute("Revision", tempDoc.get(i).getRevision());
                        nvpDoc.setAttribute("DocType", "Supporting");
                        if(getParamPath().contains("publish")){
                            nvpDoc.setAttribute("PublishStatus", "2");
                        }
                        voDoc.createAndInitRow(nvpDoc);
                        DocString = DocString + "<tr style=\"background-color:#dcdcdc\">" +
                                    "<td>"+tempDoc.get(i).getDocumentNumber()+"</td>" +
                                    "<td>"+tempDoc.get(i).getDocumentTitle()+"</td>" +
                                    "<td>"+tempDoc.get(i).getRevision()+"</td></tr>";
                        //insert into doc_process
                        for(int j=0;j<dataUser.size();j++){
                            ViewObject voDocProcess = app.findViewObject("PheDocProcessVO1");
                            NameValuePairs nvpDocProcess = new NameValuePairs();
                            nvpDocProcess.setAttribute("Did", tempDoc.get(i).getDid());
                            nvpDocProcess.setAttribute("TransmittalId", tranmisttalNo+"-"+latestNo);
//                            nvpDocProcess.setAttribute("Sender", GetSession().getAttribute("username").toString());
                            nvpDocProcess.setAttribute("Sender", Userlogin);
                            nvpDocProcess.setAttribute("Recipient", dataUser.get(j).getUsername());
                            nvpDocProcess.setAttribute("DocTitle", tempDoc.get(i).getDocumentTitle());
                            nvpDocProcess.setAttribute("DocStatus", tempDoc.get(i).getDocumentStatus());
                            nvpDocProcess.setAttribute("DocType", "Supporting");
                            voDocProcess.createAndInitRow(nvpDocProcess);
                        }               
                    
                    
                    
                    }
                }
                DocString =DocString+ "</table>";
                //insert to log
                ViewObject voLog = app.findViewObject("PheDswfLog1");
                NameValuePairs nvpLog = new NameValuePairs();
                nvpLog.setAttribute("TransmittalId", tranmisttalNo+"-"+latestNo);
//                nvpLog.setAttribute("Username", GetSession().getAttribute("username").toString());
               nvpLog.setAttribute("Username",Userlogin);
                if(getParamPath().contains("publish")){
                    nvpLog.setAttribute("Action", "5");
                }else{
                    nvpLog.setAttribute("Action", "1");        
                }
                nvpLog.setAttribute("ActionDate", nowDate);
                nvpLog.setAttribute("Description", descriptionLog);
                voLog.createAndInitRow(nvpLog);
                //commit transaction
                int year = Calendar.getInstance().get(Calendar.YEAR);
//                RIDCClass ridc = new RIDCClass(GetSession().getAttribute("username").toString(),
//                                           GetSession().getAttribute("password").toString());
                RIDCClass ridc = new RIDCClass(Userlogin,  getPassword());
                if(getParamPath().contains("publish")){
                    ridc.CreateFolderTransmittal(tranmisttalNo+"-"+latestNo, "/Document Secure Workflow/Publish");
                }else{
                    if(projectorOrganization.equalsIgnoreCase("Project")){
                        ridc.CreateFolderTransmittal(tranmisttalNo+"-"+latestNo, getParamPath());
                    }else{
                        ridc.CreateFolderTransmittal(tranmisttalNo+"-"+latestNo, getParamPath()+"/"+String.valueOf(year));    
                    }
                }
                String publishfolderGUID = ridc.FolderInfo("Document Secure Workflow/Publish/Documents").get("fFolderGUID").toString();
                my_worksheet.getRow(19).createCell(1);
                cell = my_worksheet.getRow(19).getCell(4);
                System.out.println("jumlah dokumen : "+tempDoc.size());
                cell.setCellValue(tempDoc.size());
                for(int i=0;i<tempDoc.size();i++){
                    if(!tempDoc.get(i).getDDocname().isEmpty()){
                        if(getParamPath().contains("publish")){
                            if(tempDoc.get(i).getDistribution().equals("Electronic")){
                                try{
//                                    DataObject obj = ridc.CopyFile(publishfolderGUID, ridc.GetFileInfo(tempDoc.get(i).getDid()).get("fFileGUID").toString());
                                    ridc.createLink("/Document Secure Workflow/Publish/"+tranmisttalNo+"-"+latestNo+"/Supporting Documents",tempDoc.get(i).getDid());
                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }else{
                            if(projectorOrganization.equalsIgnoreCase("Project")){
                                ridc.createLink(getParamPath()+"/"+tranmisttalNo+"-"+latestNo+"/Supporting Documents",tempDoc.get(i).getDid());
                            }else{
                                ridc.createLink(getParamPath()+"/"+String.valueOf(year)+"/"+tranmisttalNo+"-"+latestNo+"/Supporting Documents",tempDoc.get(i).getDid());
                            }   
                        }
                    }
                }
                inp.close();
                FileOutputStream output_file =new FileOutputStream(new File("C:\\covernote_temp\\Cover Note - "+tranmisttalNo+"-"+latestNo+".xls"));
                //write changes
                my_xls_workbook.write(output_file);
                //close the stream
                output_file.close();
                File file = new File("C:\\covernote_temp\\Cover Note - "+tranmisttalNo+"-"+latestNo+".xls");
                InputStream is = null;
                try {
                    is = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                
                String dID = "";
                if(getParamPath().contains("publish")){
                    dID = ridc.CheckinCoverNote(is,file.getName(),file.length(),"Document Secure Workflow/Publish/"+tranmisttalNo+"-"+latestNo+"/Supporting Documents");
                    ridc.createLinkReadme("Document Secure Workflow/Publish/"+tranmisttalNo+"-"+latestNo,"READMEFILEINDONESIA");
                    ridc.createLinkReadme("Document Secure Workflow/Publish/"+tranmisttalNo+"-"+latestNo,"READMEFILEENGLISH");
                }else{
                    if(projectorOrganization.equalsIgnoreCase("Project")){
                        dID = ridc.CheckinCoverNote(is,file.getName(),file.length(),getParamPath()+"/"+tranmisttalNo+"-"+latestNo+"/Supporting Documents");
                        ridc.createLinkReadme(getParamPath()+"/"+tranmisttalNo+"-"+latestNo,"READMEFILEINDONESIA");
                        ridc.createLinkReadme(getParamPath()+"/"+tranmisttalNo+"-"+latestNo,"READMEFILEENGLISH");
                    }else{
                        dID= ridc.CheckinCoverNote(is,file.getName(),file.length(),getParamPath()+"/"+year+"/"+tranmisttalNo+"-"+latestNo+"/Supporting Documents");
                        ridc.createLinkReadme(getParamPath()+"/"+String.valueOf(year)+"/"+tranmisttalNo+"-"+latestNo,"READMEFILEINDONESIA");
                        ridc.createLinkReadme(getParamPath()+"/"+String.valueOf(year)+"/"+tranmisttalNo+"-"+latestNo,"READMEFILEENGLISH");
                    }
                }                
                DataObject ob = ridc.GetDocInfo(dID);
                ViewObject voDoc = app.findViewObject("PhdDswfDocVo1");
                NameValuePairs nvpDoc = new NameValuePairs();
                nvpDoc.setAttribute("Id", dID);
                nvpDoc.setAttribute("TransmittalId", tranmisttalNo+"-"+latestNo);
                nvpDoc.setAttribute("ContentId", ob.get("dDocName").toString());
                nvpDoc.setAttribute("Type", "Transmittal");
                nvpDoc.setAttribute("Format", ob.get("dFormat").toString());
//                nvpDoc.setAttribute("Uploader", GetSession().getAttribute("username").toString());
                nvpDoc.setAttribute("Uploader",Userlogin);
                nvpDoc.setAttribute("UploadDate", nowDate);
                nvpDoc.setAttribute("DocTitle", ob.get("dDocTitle").toString());
                nvpDoc.setAttribute("DocType", "Covernote");
                voDoc.createAndInitRow(nvpDoc);
                //commit
                app.getTransaction().commit();
                file.delete();
                String projOrgName = getParamPath().substring(getParamPath().lastIndexOf("/")+1,getParamPath().length());
                String linkExternal = "";
                if(getProjectorOrganization().contains("Project")){
                    linkExternal = getProjectorOrganization().concat("s");
                }else{
                    linkExternal = getProjectorOrganization();
                }
                //send email
                String isi = "";
                if(getParamPath().contains("publish")){
                    isi="<html>\n" +
                    "<head></head>\n" + 
                    "<body style=\"font-family:arial\">Dear Sir/Madam<br/><br/>You have received a request from TDC to send the native file(s) for the following document(s):<br/><br/>\n" + 
                    DocString+"<br/><br/>" +
                    "Please see click this <a href=\""+getExternalAddress()+"?tPath=Publish/"+tranmisttalNo+"-"+latestNo+"\">link</a> to open the transmittal."+
                    "<br/>Thank you for your attention.</body></html>";
                }else{
                    isi="<html>\n" + 
                    "<head></head>\n" + 
                    "<body style=\"font-family:arial\">Dear Sir/Madam<br/><br/>You have received a new transmittal. The details are:<br/><br/>\n" + 
                    "<table ><tr><td>Transmittal No</td><td>:</td><td>"+tranmisttalNo+"-"+latestNo+"</td></tr>\n" + 
                    "<tr><td>Contract No</td><td>:</td><td>"+temp.getAttribute("ContractNumber").toString()+"</td></tr>" +
                    "<tr><td>WO No.</td><td>:</td><td>"+bindTransmittalWoNo.getValue().toString()+"</td></tr>" +
                    "<tr><td>Subject</td><td>:</td><td>"+bindTransmittalSubject.getValue().toString()+"</td></tr></table>\n" + 
                    "<br/><br/>"+DocString+"<br/><br/>Please click " + 
                    "<a href=\""+getExternalAddress()+"?tPath="+linkExternal+"/"+projOrgName+"/"+tranmisttalNo+"-"+latestNo+"\">here</a> to open the transmittal.<br/>Thank you for your attention.</body></html>";   
                }
                OperationBinding sendEmail = bindings.getOperationBinding("SendEmailTo");//edit by nanda 3009 edit SendEmailTo ke SendEmail
                Map paramEmail = sendEmail.getParamsMap();
                System.out.println("penerima external:" + penerima); 
                if(penerima != ""){
                    paramEmail.put("EmailTo",penerima);
                }else{
                    paramEmail.put("EmailTo","owc.support@pheonwj.pertamina.com");
                }                  
                if(getParamPath().contains("publish")){
                    paramEmail.put("Subject","["+CompanyApName.replaceAll("\\s+","")+"] Request Native File for transmittal No. "+tranmisttalNo+"-"+latestNo);
                }else{
                    paramEmail.put("Subject","["+CompanyApName.replaceAll("\\s+","")+"] New Transmittal No. "+tranmisttalNo+"-"+latestNo);   
                }
                paramEmail.put("EmailCc",GetCreatorEmail());
                paramEmail.put("HtmlText",isi);
                try{
                    sendEmail.execute();/*matikan email dulu 27082021*/
                }catch(Exception e){
                    //nanda 030315 - jika tidak terkirim akan kirim ke email owcsupport
                    paramEmail.put("EmailTo","owc.support@pheonwj.pertamina.com");
                    sendEmail.execute();/*matikan email dulu 27082021*/
                    e.printStackTrace();
                }
                RichPopup.PopupHints ph = new RichPopup.PopupHints();
                bindPopUpSuccess.show(ph);  
                
                //nanda - 2310 - hapus cache abis createtransmittal
                tempDoc.clear();
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableDoc);
            }catch(Exception e){
                //rollback transaction
                app.getTransaction().rollback();
                e.printStackTrace();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Create new transmittal failed. Please call the administrator", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }else{
            if(statusTidakLengkap!=0){
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "You have to set the document status in every document", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }else{
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "You have to choose document(s) and user(s)", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);   
            }
        }
    }
    
    public String IsRoleExist(String username, String role){
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding obGetConfig = bindings.getOperationBinding("ExecuteIsRoleExist");
        obGetConfig.getParamsMap().put("role", role);
        obGetConfig.getParamsMap().put("name", username);
        AttributeBinding Isroleexist = (AttributeBinding)bindings.get("Isroleexist");
        obGetConfig.execute();
        if(Isroleexist.getInputValue()!=null)
            return Isroleexist.getInputValue().toString();
        else
            return "";
    }
    
    public void setBindUploadedFile(RichInputFile bindUploadedFile) {
        this.bindUploadedFile = bindUploadedFile;
    }

    public RichInputFile getBindUploadedFile() {
        return bindUploadedFile;
    }

    public void setBindOutputProjectTitle(RichOutputLabel bindOutputProjectTitle) {
        this.bindOutputProjectTitle = bindOutputProjectTitle;
    }

    public RichOutputLabel getBindOutputProjectTitle() {
        return bindOutputProjectTitle;
    }

    public void setBindTransmittalSubject(RichInputText bindTransmittalSubject) {
        this.bindTransmittalSubject = bindTransmittalSubject;
    }

    public RichInputText getBindTransmittalSubject() {
        return bindTransmittalSubject;
    }

    public void setBindTransmittalDueDate(RichInputDate bindTransmittalDueDate) {
        this.bindTransmittalDueDate = bindTransmittalDueDate;
    }

    public RichInputDate getBindTransmittalDueDate() {
        return bindTransmittalDueDate;
    }

    public void setBindTransmittalContractor(RichSelectOneChoice bindTransmittalContractor) {
        this.bindTransmittalContractor = bindTransmittalContractor;
    }

    public RichSelectOneChoice getBindTransmittalContractor() {
        return bindTransmittalContractor;
    }

    public void setBindTransmittalWoNo(RichInputText bindTransmittalWoNo) {
        this.bindTransmittalWoNo = bindTransmittalWoNo;
    }

    public RichInputText getBindTransmittalWoNo() {
        return bindTransmittalWoNo;
    }

    public void setRoutineNonRoutine(String routineNonRoutine) {
        this.routineNonRoutine = routineNonRoutine;
    }

    public String getRoutineNonRoutine() {
        return routineNonRoutine;
    }

    public void ValueChangeLsnrContractor(ValueChangeEvent valueChangeEvent) {
        BindingContext bindingctx = BindingContext.getCurrent();
        BindingContainer bindings = bindingctx.getCurrentBindingsEntry();
        DCBindingContainer bindingsImpl = (DCBindingContainer) bindings;
        DCIteratorBinding dciter = bindingsImpl.findIteratorBinding("contractNumberVO1Iterator");
        Row temp = dciter.getRowAtRangeIndex((Integer.parseInt(valueChangeEvent.getNewValue().toString())));
        OperationBinding method = bindings.getOperationBinding("EWParamExternalUser");
        Map paramsMap = method.getParamsMap();
        paramsMap.put("project",temp.getAttribute("ProjectId"));
        paramsMap.put("contract",temp.getAttribute("ContractNumber"));
        method.execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindUser);
        dataUser.clear();
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableUser);
    }

    public void setProjectorOrganization(String projectorOrganization) {
        this.projectorOrganization = projectorOrganization;
    }

    public String getProjectorOrganization() {
        return projectorOrganization;
    }

    public void ValueChangeLsnrContractorOrganization(ValueChangeEvent valueChangeEvent) {
        BindingContext bindingctx = BindingContext.getCurrent();
        BindingContainer bindings = bindingctx.getCurrentBindingsEntry();
        DCBindingContainer bindingsImpl = (DCBindingContainer) bindings;
        DCIteratorBinding dciter = bindingsImpl.findIteratorBinding("contractNumberOrganizationVO1Iterator");
        Row temp = dciter.getRowAtRangeIndex((Integer.parseInt(valueChangeEvent.getNewValue().toString())));
        OperationBinding method = bindings.getOperationBinding("EWParamsExternalUserOrganization");
        Map paramsMap = method.getParamsMap();
        paramsMap.put("organization",temp.getAttribute("OrganizationId"));
        paramsMap.put("contract",temp.getAttribute("ContractNumber"));
        method.execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindUser);
        dataUser.clear();
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableUser);
    }

    public void setBindTransmittalContractorOgranization(RichSelectOneChoice bindTransmittalContractorOgranization) {
        this.bindTransmittalContractorOgranization = bindTransmittalContractorOgranization;
    }

    public RichSelectOneChoice getBindTransmittalContractorOgranization() {
        return bindTransmittalContractorOgranization;
    }

    public void setBindUserOrganization(RichSelectOneChoice bindUserOrganization) {
        this.bindUserOrganization = bindUserOrganization;
    }

    public RichSelectOneChoice getBindUserOrganization() {
        return bindUserOrganization;
    }

    public void CancelTransmittal(ActionEvent actionEvent) {
        //modified by nanda - 020215 - do not delete doc if forward
        System.out.println("isForward="+getIsForward());
        if(getIsForward().equals("No")){
            RIDCClass ridc = new RIDCClass(getWeblogicusername(),getWeblogicpassword());
            for(int i=0;i<tempDoc.size();i++){
                if(!tempDoc.get(i).getDDocname().isEmpty()){
                    ridc.DeleteDoc(tempDoc.get(i).getDid(),tempDoc.get(i).getDDocname());
                }
            }
        }
        if(getParamPath().contains("publish")){
            RIDCClass ridc = new RIDCClass(getWeblogicusername(),getWeblogicpassword());
            for(int i=0;i<tempDoc.size();i++){
                if(!tempDoc.get(i).getDDocname().isEmpty()){
                    ridc.DeleteDoc(tempDoc.get(i).getDid(),tempDoc.get(i).getDDocname());
                }
            }  
        }
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            // modified by nanda - 020215 - clear cache after cancel forward
            tempDoc.clear();
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableDoc);
            
            if(getParamPath().contains("publish")){
                ctx.getExternalContext().redirect(currentAddress+"?IdcService=PHE_GET_TRANSMITTAL_DOC_SEARCH&keyword=&status=&startRow=&endRow=");
            }else{
                ctx.getExternalContext().redirect(getCurrentAddress()+"?IdcService=FLD_BROWSE&path="+getParamPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void EditFile(ActionEvent actionEvent) {
        int tempHapus = Integer.parseInt(actionEvent.getComponent().getAttributes().get("editFileIndex").toString());
        bindEditIndex.setValue(tempHapus);
        ApplicationModule am = ADFUtils.getApplicationModuleForDataControl("AppModuleExternalTransmittalDataControl");
        ViewObject voStatus = am.findViewObject("ListDocumentStatusVO1");
        voStatus.executeQuery();
        int i = 0;
        while(voStatus.hasNext()){
            Row rowStatus = voStatus.next();
            if(tempDoc.get(tempHapus).getDocumentStatus().equalsIgnoreCase(rowStatus.getAttribute("Description").toString())){
                break;
            }
            i++;
        }
        bindEdmsEditDocStatus = new RichSelectOneChoice();
        bindEdmsEditDocStatus.setValue(i);
        bindEdmsEditDocNumber.setValue(tempDoc.get(tempHapus).getDocumentNumber());
        bindEdmsEditDocTitle.setValue(tempDoc.get(tempHapus).getDocumentTitle());
        bindEdmsEditPages.setValue(tempDoc.get(tempHapus).getPages());
        bindEdmsEditRemarsk.setValue(tempDoc.get(tempHapus).getRemarks());
        bindEdmsEditRevisions.setValue(tempDoc.get(tempHapus).getRevision());
        if(tempDoc.get(tempHapus).getDistribution().equalsIgnoreCase("Electronic")){
            bindEditDocName.setValue(tempDoc.get(tempHapus).getDocumentName());
            bindEditDocName.setVisible(true);
            bindLabelEditDocName.setVisible(true);
            bindHardcopyEditDistributionMethod.setVisible(false);
        }else{
            ViewObject voDis = am.findViewObject("ListSentHardcopyVO1");
            voDis.executeQuery();
            i = 0;
            while(voDis.hasNext()){
                Row rowStatus = voDis.next();
                if(tempDoc.get(tempHapus).getDistribution().equalsIgnoreCase(rowStatus.getAttribute("Description").toString())){
                    break;
                }
                i++;
            }
            bindHardcopyEditDistributionMethod = new RichSelectOneChoice();
            bindHardcopyEditDistributionMethod.setValue(i);
            bindEditDocName.setVisible(false);
            bindLabelEditDocName.setVisible(false);
            bindHardcopyEditDistributionMethod.setVisible(true);
        }
        voStatus.executeQuery();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindHardcopyEditDistributionMethod);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEditDocName);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEdmsEditDocNumber);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEdmsEditDocTitle);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEdmsEditPages);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEdmsEditRemarsk);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEdmsEditRevisions);
        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        bindPopUpEditEdmsForm.show(ph);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindPopUpEditEdmsForm);
    }

    public void DeleteFile(ActionEvent actionEvent) {
        int tempHapus = Integer.parseInt(actionEvent.getComponent().getAttributes().get("delFileIndex").toString());
        if(getIsForward().equals("No")){
//            RIDCClass ridc = new RIDCClass(GetSession().getAttribute("username").toString(),GetSession().getAttribute("password").toString());
            RIDCClass ridc = new RIDCClass(Userlogin,  getPassword());
            ridc.DeleteDoc(tempDoc.get(tempHapus).getDid(),tempDoc.get(tempHapus).getDDocname());
        }
        tempDoc.remove(tempHapus);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableDoc);
    }

    public void setBindPopUpEditEdmsForm(RichPopup bindPopUpEditEdmsForm) {
        this.bindPopUpEditEdmsForm = bindPopUpEditEdmsForm;
    }

    public RichPopup getBindPopUpEditEdmsForm() {
        return bindPopUpEditEdmsForm;
    }

    public void setBindEdmsEditDocNumber(RichInputText bindEdmsEditDocNumber) {
        this.bindEdmsEditDocNumber = bindEdmsEditDocNumber;
    }

    public RichInputText getBindEdmsEditDocNumber() {
        return bindEdmsEditDocNumber;
    }

    public void setBindEdmsEditDocTitle(RichInputText bindEdmsEditDocTitle) {
        this.bindEdmsEditDocTitle = bindEdmsEditDocTitle;
    }

    public RichInputText getBindEdmsEditDocTitle() {
        return bindEdmsEditDocTitle;
    }

    public void setBindEdmsEditDocStatus(RichSelectOneChoice bindEdmsEditDocStatus) {
        this.bindEdmsEditDocStatus = bindEdmsEditDocStatus;
    }

    public RichSelectOneChoice getBindEdmsEditDocStatus() {
        return bindEdmsEditDocStatus;
    }

    public void setBindEdmsEditPages(RichInputText bindEdmsEditPages) {
        this.bindEdmsEditPages = bindEdmsEditPages;
    }

    public RichInputText getBindEdmsEditPages() {
        return bindEdmsEditPages;
    }

    public void setBindEdmsEditRemarsk(RichInputText bindEdmsEditRemarsk) {
        this.bindEdmsEditRemarsk = bindEdmsEditRemarsk;
    }

    public RichInputText getBindEdmsEditRemarsk() {
        return bindEdmsEditRemarsk;
    }

    public void setBindEdmsEditRevisions(RichInputText bindEdmsEditRevisions) {
        this.bindEdmsEditRevisions = bindEdmsEditRevisions;
    }

    public RichInputText getBindEdmsEditRevisions() {
        return bindEdmsEditRevisions;
    }

    public void dialogBoxEdmsEditForm(DialogEvent dialogEvent) {
        if(dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)){
            if(bindEdmsEditPages.getValue()==null){
                bindEdmsEditPages.setValue("1");
            }
            int temp = Integer.parseInt(bindEditIndex.getValue().toString());
            BindingContext bindingctx = BindingContext.getCurrent();
            BindingContainer bindings = null;
            bindings = bindingctx.getCurrentBindingsEntry();
            DCBindingContainer bindingsImpl = (DCBindingContainer) bindings;
            DCIteratorBinding dciter = null;
            dciter = bindingsImpl.findIteratorBinding("ListDocumentStatusVO1Iterator");// your lookup iterator
            Row status = dciter.getRowAtRangeIndex((Integer.parseInt(bindEdmsEditDocStatus.getValue().toString())));
            tempDoc.get(temp).setDocumentNumber(bindEdmsEditDocNumber.getValue().toString());
            tempDoc.get(temp).setDocumentTitle(bindEdmsEditDocTitle.getValue().toString());
            tempDoc.get(temp).setDocumentStatus(status.getAttribute("Description").toString());
            tempDoc.get(temp).setPages(bindEdmsEditPages.getValue().toString());
            tempDoc.get(temp).setRemarks(bindEdmsEditRemarsk.getValue().toString());
            tempDoc.get(temp).setRevision(bindEdmsEditRevisions.getValue().toString());
            if(tempDoc.get(temp).getDistribution().equalsIgnoreCase("Electronic")){
            }else{
                DCIteratorBinding dciter2 = null;
                dciter2 = bindingsImpl.findIteratorBinding("ListSentHardcopyVO1Iterator");// your lookup iterator
                Row temp2 = dciter2.getRowAtRangeIndex((Integer.parseInt(bindHardcopyDistributionMethod.getValue().toString())));
                tempDoc.get(temp).setDistribution(temp2.getAttribute("Value").toString());
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableDoc);
        }
    }

    public void setBindEditDocName(RichOutputLabel bindEditDocName) {
        this.bindEditDocName = bindEditDocName;
    }

    public RichOutputLabel getBindEditDocName() {
        return bindEditDocName;
    }

    public void setBindHardcopyEditDistributionMethod(RichSelectOneChoice bindHardcopyEditDistributionMethod) {
        this.bindHardcopyEditDistributionMethod = bindHardcopyEditDistributionMethod;
    }

    public RichSelectOneChoice getBindHardcopyEditDistributionMethod() {
        return bindHardcopyEditDistributionMethod;
    }

    public void setBindLabelEditDocName(RichPanelLabelAndMessage bindLabelEditDocName) {
        this.bindLabelEditDocName = bindLabelEditDocName;
    }

    public RichPanelLabelAndMessage getBindLabelEditDocName() {
        return bindLabelEditDocName;
    }

    public void setBindEditIndex(RichOutputLabel bindEditIndex) {
        this.bindEditIndex = bindEditIndex;
    }

    public RichOutputLabel getBindEditIndex() {
        return bindEditIndex;
    }

    public void setBindPopUpSuccess(RichPopup bindPopUpSuccess) {
        this.bindPopUpSuccess = bindPopUpSuccess;
    }

    public RichPopup getBindPopUpSuccess() {
        return bindPopUpSuccess;
    }

    public void GoToHome(ActionEvent actionEvent) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            if(getParamPath().contains("publish")){
                ctx.getExternalContext().redirect(currentAddress+"?IdcService=PHE_GET_TRANSMITTAL_DOC_SEARCH&keyword=&status=&startRow=&endRow=");
            }else{
                ctx.getExternalContext().redirect(getCurrentAddress()+"?IdcService=FLD_BROWSE&fromCreate=1&path="+getParamPath());    
            }            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setProjectOrgId(String projectOrgId) {
        this.projectOrgId = projectOrgId;
    }

    public String getProjectOrgId() {
        return projectOrgId;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setWeblogicusername(String weblogicusername) {
        this.weblogicusername = weblogicusername;
    }

    public String getWeblogicusername() {
        return weblogicusername;
    }

    public void setWeblogicpassword(String weblogicpassword) {
        this.weblogicpassword = weblogicpassword;
    }

    public String getWeblogicpassword() {
        return weblogicpassword;
    }

    public void setExternalAddress(String externalAddress) {
        this.externalAddress = externalAddress;
    }

    public String getExternalAddress() {
        return externalAddress;
    }

    public void ValueChangeLsnrContractorPublish(ValueChangeEvent valueChangeEvent) {
        BindingContext bindingctx = BindingContext.getCurrent();
        BindingContainer bindings = bindingctx.getCurrentBindingsEntry();
        DCBindingContainer bindingsImpl = (DCBindingContainer) bindings;
        DCIteratorBinding dciter = bindingsImpl.findIteratorBinding("contractNumberPublish1Iterator");
        Row temp = dciter.getRowAtRangeIndex((Integer.parseInt(valueChangeEvent.getNewValue().toString())));
        OperationBinding method = bindings.getOperationBinding("EWParamsExternalUserPublish");
        Map paramsMap = method.getParamsMap();
        paramsMap.put("contract",temp.getAttribute("ContractNumber"));
        method.execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindUser);
        dataUser.clear();
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableUser);
    }

    public void setBindUserPublish(RichSelectOneChoice bindUserPublish) {
        this.bindUserPublish = bindUserPublish;
    }

    public RichSelectOneChoice getBindUserPublish() {
        return bindUserPublish;
    }

    public void setBindTransmittalContractorPublish(RichSelectOneChoice bindTransmittalContractorPublish) {
        this.bindTransmittalContractorPublish = bindTransmittalContractorPublish;
    }

    public RichSelectOneChoice getBindTransmittalContractorPublish() {
        return bindTransmittalContractorPublish;
    }

    public void setIsForward(String isForward) {
        this.isForward = isForward;
    }

    public String getIsForward() {
        return isForward;
    }

    public void setBindCompanyName(RichOutputLabel bindCompanyName) {
        this.bindCompanyName = bindCompanyName;
    }

    public RichOutputLabel getBindCompanyName() {
        return bindCompanyName;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPassword() {
        return Password;
    }
}
