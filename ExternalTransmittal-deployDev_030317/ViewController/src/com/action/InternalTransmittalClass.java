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

import javax.faces.model.SelectItem;

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
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.component.rich.layout.RichPanelLabelAndMessage;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.DialogEvent.Outcome;
import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Key;
import oracle.jbo.NameValuePairs;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import oracle.jbo.uicli.binding.JUCtrlValueBindingRef;

import oracle.stellent.ridc.model.DataObject;
import oracle.stellent.ridc.model.DataResultSet;

import org.apache.myfaces.trinidad.component.UIXTree;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//add by nanda 241115
import org.apache.myfaces.trinidad.util.ComponentReference;


public class InternalTransmittalClass {
    private RichOutputLabel bindOutputProjectTitle;
    private String projectorOrganization;
    private String paramPath;
    private String routineNonRoutine;

    //    private RichInputText bindTransmittalSubject;
    //    private RichInputText bindEdmsDocNumber;
    //    private RichInputText bindEdmsDocTitle;
    private RichSelectOneChoice bindEdmsDocStatus;
    //    private RichInputText bindEdmsPages;
    private RichInputText bindEdmsRemarks;
    //    private RichInputText bindEdmsRevisions;
    private RichInputFile bindUploadedFile;
    //    private RichInputText bindElectronicName;
    //    private RichInputText bindDocType;
    //    private RichInputText bindDocNumber;
    //    private RichInputText binDocTitle;
    //    private RichInputText bindPages;
    private RichInputText bindRemarks;
    //    private RichInputText bindRevisions;
    //    private RichInputText bindHardcopyDocNumber;
    //    private RichInputText bindHardcopyDocTitle;
    //    private RichInputText bindHardcopyRevision;
    //    private RichInputText bindHardcopyPages;
    private RichInputText bindHardcopyRemarks;
    //    private RichInputText bindSearchNameInternalUser;

    private ComponentReference bindTransmittalSubject;
    private ComponentReference bindEdmsDocNumber;
    private ComponentReference bindEdmsDocTitle;
    private ComponentReference bindEdmsPages;
    //    private ComponentReference bindEdmsRemarks;
    private ComponentReference bindEdmsRevisions;
    //    private ComponentReference<RichInputFile> bindUploadedFile;
    private ComponentReference bindElectronicName;
    private ComponentReference binDocTitle;
    private ComponentReference bindDocType;
    private ComponentReference bindDocNumber;
    private ComponentReference bindPages;
    private ComponentReference bindRevisions;
    private ComponentReference bindHardcopyDocNumber;
    private ComponentReference bindHardcopyDocTitle;
    private ComponentReference bindHardcopyRevision;
    private ComponentReference bindHardcopyPages;
    private ComponentReference bindSearchNameInternalUser;

    private RichInputDate bindTransmittalDueDate;
    private RichSelectOneChoice bindSource;
    private RichTable bindTableDoc;
    private RichTable tableUser;
    private List<TempDocuments> tempDoc = new ArrayList<TempDocuments>();
    private List<ChildFiles> childFiles = new ArrayList<ChildFiles>();
    private List<DataUser> dataUser = new ArrayList<DataUser>();
    private RichTree bindTreeEdms;
    private RichTable bindTableFilesEDMS;
    private RichSelectBooleanRadio bindRadioEDMS;
    private String selectedEDMSFile;
    private RichPopup bindPopUpEdms;
    private RichPopup bindPopUpEdmsForm;

    private RichSelectOneChoice bindDocStatus;

    private RichPopup binderPopUpElectronic;
    private UploadedFile file;
    private long fileLength;
    private InputStream fileInputStream;
    private String fileName;

    private RichSelectOneChoice bindHardcopyDocStatus;

    private RichSelectOneChoice bindHardcopyDistributionMethod;
    private RichPopup bindPopUpHardcopy;
    private RichTable bindTableInternalUser;

    private RichButton btnSearchInternalUser;
    private RichInputText bindSelectedInternalUser;
    private String bindSelectedInternalUsername;
    private String bindSelectedInternalUserEmail;
    private RichPopup bindPopupInternalUser;
    private RichSelectOneRadio bindSelectInternalRole;
    private String fileContentType;
    private RichPanelLabelAndMessage bindLabelEditDocName;
    private RichOutputLabel bindEditDocName;
    private RichInputText bindEditDocNumber;
    private RichInputText bindEditDocTitle;
    private RichSelectOneChoice bindEditDocStatus;
    private RichInputText bindEditPages;
    private RichInputText bindEditRemarks;
    private RichInputText bindEditRevisions;
    private RichSelectOneChoice bindEditDistributionMethod;
    private RichPopup bindPopUpEditForm;
    private RichOutputLabel bindEditIndex;
    private RichPopup bindPopUpSuccess;
    private String projectOrgId;
    private String currentAddress;
    private String weblogicusername;
    private String weblogicpassword;
    private String isForward;
    private String Userlogin;
    private String UserloginFullName;
    private String CompanyApName;
    private String Password;
    private RichPopup bindPopUpRecipient;
    private RichPopup bindPopUpEnter;
    private RichOutputLabel bindCompanyName;
    private RichInputText ibindCompanyName;

    public InternalTransmittalClass() {
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();

        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest requestSession =
            (HttpServletRequest)ctx.getExternalContext().getRequest();
        
        Userlogin ="owc.support";//request.getParameter("dUser");//"owc.admin";//request.getParameter("dUser");//"tdc_drawing";//"iwansyah""weblogic";//;"user_hsse";//"NSB_Admin";//
        CompanyApName="PHE ONWJ";//request.getParameter("companyap");//"PHE ONWJ";//"PHE NSB";//"PHE NSB";//
        System.out.println("user Login "+Userlogin);
//        System.out.println("company "+CompanyApName);
        setPassword(getValueInDB("AppModuleExternalTransmittalDataControl",
                                         "usersDcrmsVO1",
                                         "U_NAME = '"+Userlogin+"'",
                                         "UPassword"));
//        setPassword("pertamina18");
        setIsForward("No");
        setBindSelectedInternalUserEmail("");
        setBindSelectedInternalUsername("");
        System.out.println("ridc url "+GetConfig("RIDC_URL"));
        setCurrentAddress(GetConfig("RIDC_URL"));
        setWeblogicusername(GetConfig("WEBLOGIC_USER"));
        setWeblogicpassword(GetConfig("WEBLOGIC_PASS"));
        //bindCompanyName.setValue(getCompanyApName());
        //ibindCompanyName.setSubmittedValue(request.getParameter("companyap"));
        //System.out.println("company ap= "+request.getParameter("companyap").toString()+" Userlogin "+Userlogin);
      /*   try{
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error get Username");
        } */
        System.out.println(getCurrentAddress()+" "+getWeblogicusername()+" "+getWeblogicpassword());
        HttpSession session = requestSession.getSession(true);
        tempDoc = new ArrayList<TempDocuments>();
        tempDoc = (List<TempDocuments>)session.getAttribute("forwardedItem");
        if (tempDoc != null) {
            for (int i = 0; i < tempDoc.size(); i++) {
                System.out.println("forwared doc ke" + i + ": " +
                                   tempDoc.get(i).getDid() + " " +
                                   tempDoc.get(i).getDocumentNumber());
                //commented by nanda - 0202 - flag isForward move here
                setIsForward("Yes");
            }
            //commented by nanda - 0202
            //            setIsForward("Yes");
        } else {
            tempDoc = new ArrayList<TempDocuments>();
            System.out.println("bukan forward internal");
            setIsForward("No");
        }
        try {
            /* String cuk = request.getParameter("seqId");
            System.out.println("CUK " + cuk);
            //nanda 020215
            System.out.println("isForward=" + getIsForward());
            String[] docs = cuk.split(",");
            RIDCClass ridc =
                new RIDCClass(getWeblogicusername(), getWeblogicpassword());
            String publishfolderGUID =
                ridc.FolderInfo("Document Secure Workflow/Publish/Documents").get("fFolderGUID").toString();
            ApplicationModule app =
                GetApplicationModule("AppModuleExternalTransmittalDataControl");
            BindingContext bindingctx = BindingContext.getCurrent();
            BindingContainer bindings = null;
            bindings = bindingctx.getCurrentBindingsEntry();
            for (int doc = 0; doc < docs.length; doc++) {
                ViewObject voPublish = app.findViewObject("PhdDswfDocVo1");
                voPublish.setWhereClause("SEQ_ID = '" + docs[doc] + "'");
                voPublish.executeQuery();
                Row row1 = null;
                while (voPublish.hasNext()) {
                    row1 = voPublish.next();
                }
                String did = "";
                String dDocname = "";
                String docFormat = "";
                String documentName = "";
                String documentNumber = "";
                String documentTitle = "";
                String revision = "";
                String pages = "";
                String remarks = "";
                String distribution = "";
                if (row1.getAttribute("DocSource").toString().equals("Electronic")) {
                    DataObject obj =
                        ridc.CopyFile(publishfolderGUID, ridc.GetFileInfo(row1.getAttribute("Id").toString()).get("fFileGUID").toString());
                    did = obj.get("dID").toString();
                    dDocname = obj.get("dDocName").toString();
                } else {
                    OperationBinding method =
                        bindings.getOperationBinding("getSeqValue");
                    Map paramsMap = method.getParamsMap();
                    paramsMap.put("sequenceName", "PHE_DSWF_DOC_SEQ");
                    Object ob = method.execute();
                    did = ob.toString();
                    dDocname =
                            row1.getAttribute("ContentId") != null ? row1.getAttribute("ContentId").toString() :
                            "";
                }
                docFormat =
                        row1.getAttribute("Format") != null ? row1.getAttribute("Format").toString() :
                        "";
                documentName =
                        row1.getAttribute("DocName") != null ? row1.getAttribute("DocName").toString() :
                        "";
                documentNumber =
                        row1.getAttribute("DocNumber") != null ? row1.getAttribute("DocNumber").toString() :
                        "";
                documentTitle =
                        row1.getAttribute("DocTitle") != null ? row1.getAttribute("DocTitle").toString() :
                        "";
                revision =
                        row1.getAttribute("Revision") != null ? row1.getAttribute("Revision").toString() :
                        "";
                pages =
                        row1.getAttribute("Pages") != null ? row1.getAttribute("Pages").toString() :
                        "1";
                remarks =
                        row1.getAttribute("Remarks") != null ? row1.getAttribute("Remarks").toString() :
                        "";
                distribution = row1.getAttribute("DocSource").toString();
                tempDoc.add(new TempDocuments(did, dDocname, "Transmittal",
                                              docFormat, documentName,
                                              documentNumber, documentTitle,
                                              revision, "IFP", pages, remarks,
                                              distribution));
                System.out.println("DID Document Publish ke" + doc + "=" +
                                   did);
            } */
        } catch (Exception e) {
            System.out.println("Create Transmittal Internal bukan Publish");
        }
        bindHardcopyRemarks = new RichInputText();
        bindHardcopyRemarks.setValue("");
        bindRemarks = new RichInputText();
        bindRemarks.setValue("");
        //commented by nanda 251115
        bindEdmsRemarks = new RichInputText();
        bindEdmsRemarks.setValue("");
        bindEditRemarks = new RichInputText();
        bindEditRemarks.setValue("");
       setParamPath("Document Secure Workflow/Non Routine/Copy of owc enhancement");
       // setParamPath ("Document Secure Workflow/Non Routine/Structure Redrawn 2014");
      //setParamPath(request.getParameter("path"));//code asli dari param
        //        GetSession().setAttribute("username", "weblogic");
        //        GetSession().setAttribute("password", "34welcome12");
//        System.out.println("getParamPath()="+getParamPath());
        if (getParamPath().contains("publish")) {
          /*   bindOutputProjectTitle = new RichOutputLabel();
            bindOutputProjectTitle.setValue("Publish"); */
        } else {
            String[] parts = getParamPath().split("/");
            String part1 = parts[(parts.length - 1)];
            String part2 = parts[(parts.length - 2)];
            bindOutputProjectTitle = new RichOutputLabel();
            bindOutputProjectTitle.setValue(part1);
            setRoutineNonRoutine(part2);
        }
        bindCompanyName=new RichOutputLabel();
        bindCompanyName.setValue(CompanyApName);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindOutputProjectTitle);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCompanyName);
        BindingContainer bindings =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        if (getParamPath().contains("publish")) {
          /*   RIDCClass ridc =
                new RIDCClass(GetSession().getAttribute("username").toString(),
                              GetSession().getAttribute("password").toString());
            DataObject object =
                ridc.FolderInfo("Document Secure Workflow/Publish/Documents");
            if (object == null) {
                ridc.CreateFolder("Documents",
                                  "Document Secure Workflow/Publish");
            } */
        } else {
            if (getParamPath().contains("Non Routine")) {
                setProjectorOrganization("Project");
                RIDCClass ridc =
                    new RIDCClass(Userlogin,
                                 getPassword());
//                new RIDCClass(GetSession().getAttribute("username").toString(),
//                              GetSession().getAttribute("password").toString());
                System.out.println("fullpath : "+getParamPath() + "/Documents");
                DataObject object =
                    ridc.FolderInfo(getParamPath() + "/Documents");
                System.out.println("fullpath : "+getParamPath() + "/Documents");
                if (object == null) {
                    ridc.CreateFolder("Documents", getParamPath());
                }
                OperationBinding method =
                    bindings.getOperationBinding("ExecuteGetProjectId");
                Map paramsMap = method.getParamsMap();
                paramsMap.put("projectTitle",
                              bindOutputProjectTitle.getValue().toString());
                method.execute();
                AttributeBinding projectId =
                    (AttributeBinding)bindings.get("ProjectId");
                setProjectOrgId(projectId.getInputValue()==null?"":projectId.getInputValue().toString());
            } else {
                setProjectorOrganization("Organization");
                int year = Calendar.getInstance().get(Calendar.YEAR);
                RIDCClass ridc =
                new RIDCClass(Userlogin,
                             getPassword());
//                    (GetSession().getAttribute("username").toString(),
//                                  GetSession().getAttribute("password").toString());
                DataObject object =
                    ridc.FolderInfo(getParamPath() + "/" + String.valueOf(year));
                if (object == null) {
                    ridc.CreateFolder(String.valueOf(year), getParamPath());
                    ridc.CreateFolder("Documents",
                                      getParamPath() + "/" + String.valueOf(year));
                } else {
                    DataObject object1 =
                        ridc.FolderInfo(getParamPath() + "/" + String.valueOf(year) +
                                        "/Documents");
                    if (object1 == null) {
                        ridc.CreateFolder("Documents",
                                          getParamPath() + "/" + String.valueOf(year));
                    }
                }
                OperationBinding method =
                    bindings.getOperationBinding("ExecuteGetOrgId");
                Map paramsMap = method.getParamsMap();
                paramsMap.put("organizationName",
                              bindOutputProjectTitle.getValue().toString());
                method.execute();
                AttributeBinding projectId =
                    (AttributeBinding)bindings.get("OrgId");
                setProjectOrgId("1");//projectId.getInputValue().toString());
            }
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

     public String GetCreatorEmail() {
        ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl("AppModuleExternalTransmittalDataControl");
        ViewObject voGetPar = am.findViewObject("UsersInternalVO1");
        voGetPar.setWhereClause("USERNAME = '" +
                                Userlogin +
                                //ADFContext.getCurrent().getSecurityContext().getUserName() +
                                "'");
        voGetPar.executeQuery();
        String email = "";
        while (voGetPar.hasNext()) {
            Row row = voGetPar.next();
            email = row.getAttribute("Email")==null ? "":row.getAttribute("Email").toString();
        }
        return email;
    }

    public HttpSession GetSession() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest request =
            (HttpServletRequest)ctx.getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        return session;
    }

    public void setBindOutputProjectTitle(RichOutputLabel bindOutputProjectTitle) {
        this.bindOutputProjectTitle = bindOutputProjectTitle;
    }

    public RichOutputLabel getBindOutputProjectTitle() {
        return bindOutputProjectTitle;
    }

    public void setProjectorOrganization(String projectorOrganization) {
        this.projectorOrganization = projectorOrganization;
    }

    public String getProjectorOrganization() {
        return projectorOrganization;
    }

    public void setParamPath(String paramPath) {
        this.paramPath = paramPath;
    }

    public String getParamPath() {
        return paramPath;
    }

    public void setRoutineNonRoutine(String routineNonRoutine) {
        this.routineNonRoutine = routineNonRoutine;
    }

    public String getRoutineNonRoutine() {
        return routineNonRoutine;
    }

    //    public void setBindTransmittalSubject(RichInputText bindTransmittalSubject) {
    //        this.bindTransmittalSubject = bindTransmittalSubject;
    //    }
    //
    //    public RichInputText getBindTransmittalSubject() {
    //        return bindTransmittalSubject;
    //    }

    public RichInputText getBindTransmittalSubject() {
        if (bindTransmittalSubject != null) {
            return (RichInputText)bindTransmittalSubject.getComponent();
        }
        return null;
    }

    public void setBindTransmittalSubject(RichInputText bindTransmittalSubject) {
        this.bindTransmittalSubject =
                ComponentReference.newUIComponentReference(bindTransmittalSubject);
    }

    public RichInputText getBindEdmsDocNumber() {
        if (bindEdmsDocNumber != null) {
            return (RichInputText)bindEdmsDocNumber.getComponent();
        }
        return null;
    }

    public void setBindEdmsDocNumber(RichInputText bindEdmsDocNumber) {
        this.bindEdmsDocNumber =
                ComponentReference.newUIComponentReference(bindEdmsDocNumber);
    }


    public void setBindTransmittalDueDate(RichInputDate bindTransmittalDueDate) {
        this.bindTransmittalDueDate = bindTransmittalDueDate;
    }

    public RichInputDate getBindTransmittalDueDate() {
        return bindTransmittalDueDate;
    }

    public void setBindSource(RichSelectOneChoice bindSource) {
        this.bindSource = bindSource;
    }

    public RichSelectOneChoice getBindSource() {
        return bindSource;
    }

    public void setBindTableDoc(RichTable bindTableDoc) {
        this.bindTableDoc = bindTableDoc;
    }

    public RichTable getBindTableDoc() {
        return bindTableDoc;
    }

    public void AddToList(ActionEvent actionEvent) {
        if (bindSelectedInternalUser.getValue() == null) {

        } else {
            BindingContext bindingctx = BindingContext.getCurrent();
            BindingContainer bindings = null;
            bindings = bindingctx.getCurrentBindingsEntry();
            DCBindingContainer bindingsImpl = (DCBindingContainer)bindings;
            DCIteratorBinding dciter = null;
            Row temp = null;
            dciter =
                    bindingsImpl.findIteratorBinding("ViewInternalRoleVO1Iterator"); // your lookup iterator
            temp =
                dciter.getRowAtRangeIndex((Integer.parseInt(bindSelectInternalRole.getValue().toString())));
            if (!getBindSelectedInternalUserEmail().equals("")) {
                int count = 0;
                for (int i = 0; i < dataUser.size(); i++) {
                    if (dataUser.get(i).getId().toString().equalsIgnoreCase(bindSelectedInternalUsername)) {
                        count++;
                    }
                }
                if (count == 0) {
                    if (bindOutputProjectTitle.getValue().equals("Publish")) {
                        dataUser.add(new DataUser(bindSelectedInternalUsername,
                                                  bindSelectedInternalUser.getValue().toString(),
                                                  "Originator",
                                                  bindSelectedInternalUserEmail));
                    } else {
                        dataUser.add(new DataUser(bindSelectedInternalUsername,
                                                  bindSelectedInternalUser.getValue().toString(),
                                                  temp.getAttribute("Code").toString(),
                                                  bindSelectedInternalUserEmail));
                    }
                }
            } else {
                if (bindSelectedInternalUser.getValue() != null) {
                    ApplicationModule am =
                        ADFUtils.getApplicationModuleForDataControl("AppModuleExternalTransmittalDataControl");
                    ViewObject voGetPar =
                        am.findViewObject("UsersInternalVO1");
                    voGetPar.setWhereClause("FULLNAME like '%" +
                                            bindSelectedInternalUser.getValue().toString() +
                                            "%'");
                    voGetPar.executeQuery();
                    while (voGetPar.hasNext()) {
                        Row row = voGetPar.next();
                        bindSelectedInternalUserEmail =
                            row.getAttribute("Email")==null ?"":row.getAttribute("Email").toString();
                        bindSelectedInternalUsername =
                                row.getAttribute("Username")==null ?"": row.getAttribute("Username").toString();
                    }
                }
                int count = 0;
                for (int i = 0; i < dataUser.size(); i++) {
                    if (dataUser.get(i).getId().toString().equalsIgnoreCase(bindSelectedInternalUsername)) {
                        count++;
                    }
                }
                if (count == 0) {
                    if (bindOutputProjectTitle.getValue().equals("Publish")) {
                        dataUser.add(new DataUser(bindSelectedInternalUsername,
                                                  bindSelectedInternalUser.getValue().toString(),
                                                  "Originator",
                                                  bindSelectedInternalUserEmail));
                    } else {
                        dataUser.add(new DataUser(bindSelectedInternalUsername,
                                                  bindSelectedInternalUser.getValue().toString(),
                                                  temp.getAttribute("Code").toString(),
                                                  bindSelectedInternalUserEmail));
                    }
                }
            }
            setBindSelectedInternalUserEmail("");
            setBindSelectedInternalUsername("");
            bindSelectedInternalUser.setValue("");
            AdfFacesContext.getCurrentInstance().addPartialTarget(tableUser);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindSelectedInternalUser);
        }
    }

    public void setTableUser(RichTable tableUser) {
        this.tableUser = tableUser;
    }

    public RichTable getTableUser() {
        return tableUser;
    }

    public void setTempDoc(List<TempDocuments> tempDoc) {
        this.tempDoc = tempDoc;
    }

    public List<TempDocuments> getTempDoc() {
        return tempDoc;
    }

    public void setChildFiles(List<ChildFiles> childFiles) {
        this.childFiles = childFiles;
    }

    public List<ChildFiles> getChildFiles() {
        return childFiles;
    }

    public void setDataUser(List<DataUser> dataUser) {
        this.dataUser = dataUser;
    }

    public List<DataUser> getDataUser() {
        return dataUser;
    }

    public void setBindTreeEdms(RichTree bindTreeEdms) {
        this.bindTreeEdms = bindTreeEdms;
    }

    public RichTree getBindTreeEdms() {
        return bindTreeEdms;
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
            //System.out.println(ID);
            RIDCClass ridc =
            new RIDCClass(Userlogin,
                         getPassword());
//                new RIDCClass(GetSession().getAttribute("username").toString(),
//                              GetSession().getAttribute("password").toString());
            childFiles.clear();
            try {
                for (DataObject object : ridc.FolderBrowseFile(ID).getRows()) {
                    if (object.get("xStatus") != "Registered") {
                        System.out.println(object.get("xDocName").toString() +
                                           "--" +
                                           object.get("xOwnerName").toString());
                        childFiles.add(new ChildFiles(object.get("fFileGUID").toString(),
                                                      object.get("dID").toString(),
                                                      object.get("dDocName").toString(),
                                                      object.get("dFormat").toString(),
                                                      object.get("xDocNumber").toString(),
                                                      object.get("dDocTitle").toString(),
                                                      object.get("xDocName").toString(),
                                                      object.get("xOwnerName").toString(),
                                                      object.get("fFileName").toString()));
                    }
                }
            } catch (Exception e) {
                System.out.println("Error select tree listener select file EDMS " +
                                   e.getMessage());
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

    public void setBindRadioEDMS(RichSelectBooleanRadio bindRadioEDMS) {
        this.bindRadioEDMS = bindRadioEDMS;
    }

    public RichSelectBooleanRadio getBindRadioEDMS() {
        return bindRadioEDMS;
    }

    public void OnChangeRadio(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue().toString().equalsIgnoreCase("true")) {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            Map p =
                ((UIComponent)valueChangeEvent.getSource()).getAttributes();
            //System.out.println(p.get("rowvalIndex").toString());
            setSelectedEDMSFile(p.get("rowvalIndex").toString());
        }
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

    public BindingContainer getBinding() {
        BindingContainer bindings =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        return bindings;
    }

    public BindingContainer GetBindings() {
        BindingContext bindingctx = BindingContext.getCurrent();
        BindingContainer bindings = bindingctx.getCurrentBindingsEntry();
        return bindings;
    }

    public void ActionSelectEdms(ActionEvent actionEvent) {
        //        String docPath = paramPath+"/Documents";
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String docPath = "";
        if (projectorOrganization.equalsIgnoreCase("Project")) {
            docPath = paramPath + "/Documents";
        } else {
            docPath = paramPath + "/" + year + "/Documents";
        }
        RIDCClass ridc =
        new RIDCClass(Userlogin,
                    getPassword());
//            new RIDCClass(GetSession().getAttribute("username").toString(),
//                          GetSession().getAttribute("password").toString());
        String folderGUID =
            ridc.FolderInfo(docPath).get("fFolderGUID").toString();
        //DataResultSet rs = ridc.FolderSearchFile(folderGUID, childFiles.get(Integer.parseInt(getSelectedEDMSFile())).getOriginalName());
        OperationBinding method =
            GetBindings().getOperationBinding("EWIsDocExist");
        Map paramsMap = method.getParamsMap();
        paramsMap.put("folderGUID", folderGUID);
        paramsMap.put("fileName",
                      childFiles.get(Integer.parseInt(getSelectedEDMSFile())).getOriginalName());
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
        } else {
            getBindEdmsDocNumber().setValue(childFiles.get(Integer.parseInt(getSelectedEDMSFile())).getXDocNumber());
            getBindEdmsDocTitle().setValue(childFiles.get(Integer.parseInt(getSelectedEDMSFile())).getDDocTitle());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getBindEdmsDocNumber());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getBindEdmsDocTitle());
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            bindPopUpEdmsForm.show(ph);
        }
    }

    public void setSelectedEDMSFile(String selectedEDMSFile) {
        this.selectedEDMSFile = selectedEDMSFile;
    }

    public String getSelectedEDMSFile() {
        return selectedEDMSFile;
    }

    public void setBindPopUpEdms(RichPopup bindPopUpEdms) {
        this.bindPopUpEdms = bindPopUpEdms;
    }

    public RichPopup getBindPopUpEdms() {
        return bindPopUpEdms;
    }

    public void setBindPopUpEdmsForm(RichPopup bindPopUpEdmsForm) {
        this.bindPopUpEdmsForm = bindPopUpEdmsForm;
    }

    public RichPopup getBindPopUpEdmsForm() {
        return bindPopUpEdmsForm;
    }


    public RichInputText getBindEdmsDocTitle() {
        if (bindEdmsDocTitle != null) {
            return (RichInputText)bindEdmsDocTitle.getComponent();
        }
        return null;
    }

    public void setBindEdmsDocTitle(RichInputText bindEdmsDocTitle) {
        this.bindEdmsDocTitle =
                ComponentReference.newUIComponentReference(bindEdmsDocTitle);
    }

    public void setBindEdmsDocStatus(RichSelectOneChoice bindEdmsDocStatus) {
        this.bindEdmsDocStatus = bindEdmsDocStatus;
    }

    public RichSelectOneChoice getBindEdmsDocStatus() {
        return bindEdmsDocStatus;
    }

    public RichInputText getBindEdmsPages() {
        if (bindEdmsPages != null) {
            return (RichInputText)bindEdmsPages.getComponent();
        }
        return null;
    }

    public void setBindEdmsPages(RichInputText bindEdmsPages) {
        this.bindEdmsPages =
                ComponentReference.newUIComponentReference(bindEdmsPages);
    }

    //    public RichInputText getBindEdmsRemarks() {
    //        if (bindEdmsRemarks != null) {
    //            return (RichInputText)bindEdmsRemarks.getComponent();
    //        }
    //        return null;
    //    }
    //
    //    public void setBindEdmsRemarks(RichInputText bindEdmsRemarks) {
    //        this.bindEdmsRemarks =
    //                ComponentReference.newUIComponentReference(bindEdmsRemarks);
    //    }

    public void setBindEdmsRemarks(RichInputText bindEdmsRemarks) {
        this.bindEdmsRemarks = bindEdmsRemarks;
    }

    public RichInputText getBindEdmsRemarks() {
        return bindEdmsRemarks;
    }

    public RichInputText getBindEdmsRevisions() {
        if (bindEdmsRevisions != null) {
            return (RichInputText)bindEdmsRevisions.getComponent();
        }
        return null;
    }

    public void setBindEdmsRevisions(RichInputText bindEdmsRevisions) {
        this.bindEdmsRevisions =
                ComponentReference.newUIComponentReference(bindEdmsRevisions);
    }

    public void dialogBoxEDMSForm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            if (getBindEdmsPages().getValue() == null) {
                getBindEdmsPages().setValue("1");
            }
            RIDCClass ridc =
                new RIDCClass(getWeblogicusername(), getWeblogicpassword());
            int year = Calendar.getInstance().get(Calendar.YEAR);
            String docPath = "";
            if (projectorOrganization.equalsIgnoreCase("Project")) {
                docPath = paramPath + "/Documents";
            } else {
                docPath = paramPath + "/" + year + "/Documents";
            }
        //nanda 220116
          System.out.println("projectorOrganization="+projectorOrganization);
            System.out.println("docPath="+docPath);
            String folderGUID =
                ridc.FolderInfo(docPath).get("fFolderGUID").toString();
            DataObject obj =
                ridc.CopyFile(folderGUID, childFiles.get(Integer.parseInt(getSelectedEDMSFile())).getFFileGUID());
            ridc.UpdateAfterCopyFile(obj.get("dID").toString(),
                                     obj.get("dDocName").toString(),
                                     getBindEdmsDocTitle().getValue().toString(),
                                     obj.get("xDocName").toString(),
                                     getBindEdmsDocNumber().getValue().toString(),
                                     getBindEdmsRevisions().getValue().toString(),
                                     GetSession().getAttribute("username").toString());
            BindingContext bindingctx = BindingContext.getCurrent();
            BindingContainer bindings = null;
            bindings = bindingctx.getCurrentBindingsEntry();
            DCBindingContainer bindingsImpl = (DCBindingContainer)bindings;
            DCIteratorBinding dciter = null;
            dciter =
                    bindingsImpl.findIteratorBinding("ListDocumentStatusVO1Iterator"); // your lookup iterator
            Row temp =
                dciter.getRowAtRangeIndex((Integer.parseInt(bindEdmsDocStatus.getValue().toString())));
            //            System.out.println(obj.get("dID").toString());
            //            System.out.println(obj.get("dDocName").toString());
            //            System.out.println(obj.get("dFormat").toString());
            //            System.out.println(obj.get("xDocName").toString());
            //            System.out.println(bindEdmsDocNumber.getValue().toString());
            //            System.out.println(bindEdmsDocTitle.getValue().toString());
            //            System.out.println(bindEdmsRevisions.getValue().toString());
            //            System.out.println(temp.getAttribute("Description").toString());
            //            System.out.println(bindEdmsPages.getValue().toString());
            //            System.out.println(bindEdmsRemarks.getValue().toString());
            tempDoc.add(new TempDocuments(obj.get("dID").toString(),
                                          obj.get("dDocName").toString(),
                                          "Transmittal",
                                          obj.get("dFormat").toString(),
                                          obj.get("xDocName").toString(),
                                          getBindEdmsDocNumber().getValue().toString(),
                                          getBindEdmsDocTitle().getValue().toString(),
                                          getBindEdmsRevisions().getValue().toString(),
                                          temp.getAttribute("Description").toString(),
                                          getBindEdmsPages().getValue().toString(),
                                          getBindEdmsRemarks().getValue().toString(),
                                          "Electronic"));
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableDoc);
        }
        ResetValue(getBindEdmsDocNumber());
        ResetValue(getBindEdmsDocTitle());
        ResetValue(getBindEdmsPages());
        getBindEdmsPages().setValue("1");
        AdfFacesContext.getCurrentInstance().addPartialTarget(getBindEdmsPages());
        ResetValue(getBindEdmsRemarks());
        ResetValue(getBindEdmsRevisions());
        bindPopUpEdms.hide();
    }

    public void ResetValue(RichInputText param) {
        param.setValue("");
        AdfFacesContext.getCurrentInstance().addPartialTarget(param);
    }

    public RichInputFile getBindUploadedFile() {
        return bindUploadedFile;
    }

    public void setBindUploadedFile(RichInputFile bindUploadedFile) {
        this.bindUploadedFile = bindUploadedFile;
    }

    public RichInputText getBindElectronicName() {
        if (bindElectronicName != null) {
            return (RichInputText)bindElectronicName.getComponent();
        }
        return null;
    }

    public void setBindElectronicName(RichInputText bindElectronicName) {
        this.bindElectronicName =
                ComponentReference.newUIComponentReference(bindElectronicName);
    }

    public RichInputText getBindDocType() {
        if (bindDocType != null) {
            return (RichInputText)bindDocType.getComponent();
        }
        return null;
    }

    public void setBindDocType(RichInputText bindDocType) {
        this.bindDocType =
                ComponentReference.newUIComponentReference(bindDocType);
    }

    public RichInputText getBindDocNumber() {
        if (bindDocNumber != null) {
            return (RichInputText)bindDocNumber.getComponent();
        }
        return null;
    }

    public void setBindDocNumber(RichInputText bindDocNumber) {
        this.bindDocNumber =
                ComponentReference.newUIComponentReference(bindDocNumber);
    }

    public RichInputText getBinDocTitle() {
        if (binDocTitle != null) {
            return (RichInputText)binDocTitle.getComponent();
        }
        return null;
    }

    public void setBinDocTitle(RichInputText binDocTitle) {
        this.binDocTitle =
                ComponentReference.newUIComponentReference(binDocTitle);
    }

    public void setBindDocStatus(RichSelectOneChoice bindDocStatus) {
        this.bindDocStatus = bindDocStatus;
    }

    public RichSelectOneChoice getBindDocStatus() {
        return bindDocStatus;
    }

    public RichInputText getBindPages() {
        if (bindPages != null) {
            return (RichInputText)bindPages.getComponent();
        }
        return null;
    }

    public void setBindPages(RichInputText bindPages) {
        this.bindPages = ComponentReference.newUIComponentReference(bindPages);
    }

    public RichInputText getBindRevisions() {
        if (bindRevisions != null) {
            return (RichInputText)bindRevisions.getComponent();
        }
        return null;
    }

    public void setBindRevisions(RichInputText bindRevisions) {
        this.bindRevisions =
                ComponentReference.newUIComponentReference(bindRevisions);
    }

    public void setBindRemarks(RichInputText bindRemarks) {
        this.bindRemarks = bindRemarks;
    }

    public RichInputText getBindRemarks() {
        return bindRemarks;
    }

    public void setBinderPopUpElectronic(RichPopup binderPopUpElectronic) {
        this.binderPopUpElectronic = binderPopUpElectronic;
    }

    public RichPopup getBinderPopUpElectronic() {
        return binderPopUpElectronic;
    }

    public void ElectronicDialogLsnr(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            RIDCClass ridc =
            new RIDCClass(Userlogin,
                         getPassword());
//                new RIDCClass(GetSession().getAttribute("username").toString(),
//                              GetSession().getAttribute("password").toString());
            int year = Calendar.getInstance().get(Calendar.YEAR);
            String docPath = "";
            if (projectorOrganization.equalsIgnoreCase("Project")) {
                docPath = paramPath + "/Documents";
            } else {
                docPath = paramPath + "/" + year + "/Documents";
            }
            if (getBindPages().getValue() == null) {
                getBindPages().setValue("1");
            }
            String folderGUIDEx =
                ridc.FolderInfo(docPath).get("fFolderGUID").toString();
            DataResultSet rs = ridc.FolderSearchFile(folderGUIDEx, fileName);
            if (rs.getRows().size() > 0) {
                FacesMessage msg =
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "File is already exist in this Project",
                                     "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                String folderGUID =
                    ridc.FolderInfo(docPath).get("fFolderGUID").toString();
                String checkin =
                    ridc.CheckinElectronic(folderGUID, getBindElectronicName().getValue().toString(),
                                           getBindDocNumber().getValue().toString(),
                                           getBinDocTitle().getValue().toString(),
                                           getBindRevisions().getValue().toString(),
                                           fileInputStream, fileLength,
                                           fileName);
                System.out.println("checkin "+checkin);
                DataObject object = ridc.GetDocInfo(checkin);
                ridc.UpdateDocxRobjId(object.get("dID").toString(),
                                      object.get("dDocName").toString());
                BindingContext bindingctx = BindingContext.getCurrent();
                BindingContainer bindings = null;
                bindings = bindingctx.getCurrentBindingsEntry();
                DCBindingContainer bindingsImpl = (DCBindingContainer)bindings;
                DCIteratorBinding dciter = null;
                dciter =
                        bindingsImpl.findIteratorBinding("ListDocumentStatusVO1Iterator"); // your lookup iterator
                Row temp =
                    dciter.getRowAtRangeIndex((Integer.parseInt(bindEdmsDocStatus.getValue().toString())));
                System.out.println(temp.getAttribute("Value").toString());
                tempDoc.add(new TempDocuments(object.get("dID").toString(),
                                              object.get("dDocName").toString(),
                                              "Transmittal",
                                              object.get("dFormat").toString(),
                                              getBindElectronicName().getValue().toString(),
                                              getBindDocNumber().getValue().toString(),
                                              getBinDocTitle().getValue().toString(),
                                              getBindRevisions().getValue().toString(),
                                              temp.getAttribute("Description").toString(),
                                              getBindPages().getValue().toString(),
                                              getBindRemarks().getValue().toString(),
                                              "Electronic"));
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableDoc);
            }
        } else {

        }
        ResetValue(getBindElectronicName());
        ResetValue(bindUploadedFile);
        ResetValue(getBindDocNumber());
        ResetValue(getBinDocTitle());
        ResetValue(getBindPages());
        getBindPages().setValue("1");
        AdfFacesContext.getCurrentInstance().addPartialTarget(getBindPages());
        ResetValue(getBindRemarks());
        ResetValue(getBindRevisions());
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

    public RichInputText getBindHardcopyDocNumber() {
        if (bindHardcopyDocNumber != null) {
            return (RichInputText)bindHardcopyDocNumber.getComponent();
        }
        return null;
    }

    public void setBindHardcopyDocNumber(RichInputText bindHardcopyDocNumber) {
        this.bindHardcopyDocNumber =
                ComponentReference.newUIComponentReference(bindHardcopyDocNumber);
    }

    public RichInputText getBindHardcopyDocTitle() {
        if (bindHardcopyDocTitle != null) {
            return (RichInputText)bindHardcopyDocTitle.getComponent();
        }
        return null;
    }

    public void setBindHardcopyDocTitle(RichInputText bindHardcopyDocTitle) {
        this.bindHardcopyDocTitle =
                ComponentReference.newUIComponentReference(bindHardcopyDocTitle);
    }

    public RichInputText getBindHardcopyRevision() {
        if (bindHardcopyRevision != null) {
            return (RichInputText)bindHardcopyRevision.getComponent();
        }
        return null;
    }

    public void setBindHardcopyRevision(RichInputText bindHardcopyRevision) {
        this.bindHardcopyRevision =
                ComponentReference.newUIComponentReference(bindHardcopyRevision);
    }

    public void setBindHardcopyDocStatus(RichSelectOneChoice bindHardcopyDocStatus) {
        this.bindHardcopyDocStatus = bindHardcopyDocStatus;
    }

    public RichSelectOneChoice getBindHardcopyDocStatus() {
        return bindHardcopyDocStatus;
    }

    public RichInputText getBindHardcopyPages() {
        if (bindHardcopyPages != null) {
            return (RichInputText)bindHardcopyPages.getComponent();
        }
        return null;
    }

    public void setBindHardcopyPages(RichInputText bindHardcopyPages) {
        this.bindHardcopyPages =
                ComponentReference.newUIComponentReference(bindHardcopyPages);
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

    public void setBindPopUpHardcopy(RichPopup bindPopUpHardcopy) {
        this.bindPopUpHardcopy = bindPopUpHardcopy;
    }

    public RichPopup getBindPopUpHardcopy() {
        return bindPopUpHardcopy;
    }

    public void PhysicalDialogLsnr(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            if (getBindHardcopyPages().getValue() == null) {
                getBindHardcopyPages().setValue("1");
            }
            BindingContext bindingctx = BindingContext.getCurrent();
            BindingContainer bindings = null;
            bindings = bindingctx.getCurrentBindingsEntry();
            DCBindingContainer bindingsImpl = (DCBindingContainer)bindings;
            DCIteratorBinding dciter = null;
            dciter =
                    bindingsImpl.findIteratorBinding("ListDocumentStatusVO1Iterator"); // your lookup iterator
            Row temp =
                dciter.getRowAtRangeIndex((Integer.parseInt(bindHardcopyDocStatus.getValue().toString())));
            //            System.out.println(temp.getAttribute("Value").toString());
            DCIteratorBinding dciter2 = null;
            dciter2 =
                    bindingsImpl.findIteratorBinding("ListSentHardcopyVO1Iterator"); // your lookup iterator
            Row temp2 =
                dciter2.getRowAtRangeIndex((Integer.parseInt(bindHardcopyDistributionMethod.getValue().toString())));
            System.out.println(temp2.getAttribute("Value").toString());
            //get seq number for id
            OperationBinding method =
                bindings.getOperationBinding("getSeqValue");
            Map paramsMap = method.getParamsMap();
            paramsMap.put("sequenceName", "PHE_DSWF_DOC_SEQ");
            Object ob = method.execute();
            tempDoc.add(new TempDocuments(ob.toString(), "", "Transmittal", "",
                                          "",
                                          getBindHardcopyDocNumber().getValue().toString(),
                                          getBindHardcopyDocTitle().getValue().toString(),
                                          getBindHardcopyRevision().getValue().toString(),
                                          temp.getAttribute("Description").toString(),
                                          getBindHardcopyPages().getValue().toString(),
                                          bindHardcopyRemarks.getValue().toString(),
                                          temp2.getAttribute("Value").toString()));
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableDoc);
        }
        ResetValue(getBindHardcopyDocNumber());
        //        ResetValue(bindHardcopyDocNumber);
        ResetValue(bindHardcopyRemarks);
        ResetValue(getBindHardcopyDocTitle());
        ResetValue(getBindHardcopyPages());
        getBindHardcopyPages().setValue("1");
        AdfFacesContext.getCurrentInstance().addPartialTarget(getBindHardcopyPages());
        ResetValue(getBindHardcopyRevision());
    }

    public void setBindTableInternalUser(RichTable bindTableInternalUser) {
        this.bindTableInternalUser = bindTableInternalUser;
    }

    public RichTable getBindTableInternalUser() {
        return bindTableInternalUser;
    }

    public RichInputText getBindSearchNameInternalUser() {
        if (bindSearchNameInternalUser != null) {
            return (RichInputText)bindSearchNameInternalUser.getComponent();
        }
        return null;
    }

    public void setBindSearchNameInternalUser(RichInputText bindSearchNameInternalUser) {
        this.bindSearchNameInternalUser =
                ComponentReference.newUIComponentReference(bindSearchNameInternalUser);
    }

    public void setBtnSearchInternalUser(RichButton btnSearchInternalUser) {
        this.btnSearchInternalUser = btnSearchInternalUser;
    }

    public RichButton getBtnSearchInternalUser() {
        return btnSearchInternalUser;
    }
    
    public Object getSelectedRow(RichTable table) {
       Object _selectedRowData = table.getSelectedRowData();
       JUCtrlHierNodeBinding _nodeBinding = (JUCtrlHierNodeBinding)_selectedRowData;
       return _nodeBinding.getRow();
    }
    public void DialogLsnrSelectInternalUser(DialogEvent dialogEvent) {
//        DCBindingContainer dcBindings =
//            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
//        DCIteratorBinding iterBind =
//            (DCIteratorBinding)dcBindings.get("UsersInternalVO1Iterator");
       //nanda 280316     
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {

            /* RowKeySet selectedEmps = getBindTableInternalUser().getSelectedRowKeys();
            Iterator selectedEmpIter = selectedEmps.iterator();
            DCBindingContainer bindings =
                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding empIter =
                bindings.findIteratorBinding("UsersInternalVO1Iterator");
            RowSetIterator empRSIter = empIter.getRowSetIterator(); */
            
            DCBindingContainer bindings =
                        (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                    DCIteratorBinding dcItteratorBindings =
                        bindings.findIteratorBinding("UsersInternalVO1Iterator");
                ViewObject voTableData = dcItteratorBindings.getViewObject();
                 Row currentRow = voTableData.getCurrentRow();
                 // Display attribute of row in console output - would generally be bound to a UI component like a Label and or used to call another proces
                 System.out.println(currentRow.getAttribute("Username"));
            
            String fullname="";
//            while (selectedEmpIter.hasNext()) {
//                Key key = (Key)((List)selectedEmpIter.next()).get(0);
//                Row currentRow = empRSIter.getRow(key);
                ADFUtil.setEL("#{pageFlowScope.userRecipient}", currentRow.getAttribute("Username")==null ? "": currentRow.getAttribute("Username").toString());
                ADFUtil.setEL("#{pageFlowScope.userFullname}",currentRow.getAttribute("Fullname")==null ? "":currentRow.getAttribute("Fullname").toString());
                ADFUtil.setEL("#{pageFlowScope.userEmail}", currentRow.getAttribute("Email")==null ? "":currentRow.getAttribute("Email").toString());
              fullname= currentRow.getAttribute("Fullname")==null ? "":currentRow.getAttribute("Fullname").toString();
                setBindSelectedInternalUsername(currentRow.getAttribute("Username")==null ?"":currentRow.getAttribute("Username").toString());
                setBindSelectedInternalUserEmail(currentRow.getAttribute("Email")==null ?"":currentRow.getAttribute("Email").toString());
//            } 
            
            bindSelectedInternalUser.setSubmittedValue(fullname);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindSelectedInternalUser);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableInternalUser);
            bindPopupInternalUser.hide();
//            RichPopup.PopupHints ph1 = new RichPopup.PopupHints();
//            bindPopUpRecipient.show(ph1);
             /*  try{
                bindSelectedInternalUser.setValue(AdfFacesContext.getCurrentInstance().getPageFlowScope().get("userFullname").toString());
                setBindSelectedInternalUsername(AdfFacesContext.getCurrentInstance().getPageFlowScope().get("userRecipient").toString());
                setBindSelectedInternalUserEmail(AdfFacesContext.getCurrentInstance().getPageFlowScope().get("userEmail").toString());
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindSelectedInternalUser);
                bindPopupInternalUser.hide();
                
              } catch(Exception e){
                RichPopup.PopupHints ph = new RichPopup.PopupHints();
                bindPopUpEnter.show(ph);
              } */
        }else{
            bindPopupInternalUser.hide();
        }
    }



    public void showInternalUserList(ActionEvent actionEvent) {
        String namesearch="%";
        ApplicationModule app =
            GetApplicationModule("AppModuleExternalTransmittalDataControl");
        ViewObject voParIn = app.findViewObject("UsersInternalVO1");
        voParIn.setWhereClause("upper(Fullname) like upper('%" +
                               namesearch +
                               "%')");
        voParIn.executeQuery();
        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        bindPopupInternalUser.show(ph);
        
    }

    public void setBindSelectedInternalUser(RichInputText bindSelectedInternalUser) {
        this.bindSelectedInternalUser = bindSelectedInternalUser;
    }

    public RichInputText getBindSelectedInternalUser() {
        return bindSelectedInternalUser;
    }

    public ApplicationModule GetApplicationModule(String appName) {
        return ADFUtils.getApplicationModuleForDataControl(appName);
    }

    public void BtnSearchInternalUser(ActionEvent actionEvent) {
        String namesearch=getBindSearchNameInternalUser().getValue()==null?"%":getBindSearchNameInternalUser().getValue().toString();
        ApplicationModule app =
            GetApplicationModule("AppModuleExternalTransmittalDataControl");
        ViewObject voParIn = app.findViewObject("UsersInternalVO1");
        voParIn.setWhereClause("upper(Fullname) like upper('%" +
                               namesearch +
                               "%')");
        voParIn.executeQuery();
        System.out.println(voParIn.getEstimatedRowCount());
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableInternalUser);
    }

    public String GetFullNameFromUsername(String username) {
        ApplicationModule app =
            GetApplicationModule("AppModuleExternalTransmittalDataControl");
        ViewObject voParIn = app.findViewObject("UsersInternalVO1");
        voParIn.setWhereClause("upper(USERNAME) like upper('%" + username +
                               "%')");
        voParIn.executeQuery();
        String fullName = "";
        while (voParIn.hasNext()) {
            Row row = voParIn.next();
            fullName = row.getAttribute("Fullname").toString();
        }
        //        DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        //        DCIteratorBinding iterBind= (DCIteratorBinding)dcBindings.get("UsersInternalVO1Iterator");
        //        return (String)iterBind.getCurrentRow().getAttribute("Fullname");
        return fullName;
    }

    public void setBindPopupInternalUser(RichPopup bindPopupInternalUser) {
        this.bindPopupInternalUser = bindPopupInternalUser;
    }

    public RichPopup getBindPopupInternalUser() {
        return bindPopupInternalUser;
    }

    public void setBindSelectedInternalUsername(String bindSelectedInternalUsername) {
        this.bindSelectedInternalUsername = bindSelectedInternalUsername;
    }

    public String getBindSelectedInternalUsername() {
        return bindSelectedInternalUsername;
    }

    public void setBindSelectedInternalUserEmail(String bindSelectedInternalUserEmail) {
        this.bindSelectedInternalUserEmail = bindSelectedInternalUserEmail;
    }

    public String getBindSelectedInternalUserEmail() {
        return bindSelectedInternalUserEmail;
    }

    public void DeleteUser(ActionEvent actionEvent) {
        int tempHapus =
            Integer.parseInt(actionEvent.getComponent().getAttributes().get("delIndex").toString());
        dataUser.remove(tempHapus);
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableUser);
    }

    public void setBindSelectInternalRole(RichSelectOneRadio bindSelectInternalRole) {
        this.bindSelectInternalRole = bindSelectInternalRole;
    }

    public RichSelectOneRadio getBindSelectInternalRole() {
        return bindSelectInternalRole;
    }

    public oracle.jbo.domain.Date getNowDate(java.util.Date pJavaDate) {
        return new oracle.jbo.domain.Date(new Timestamp(pJavaDate.getTime()));
    }

    public void sentViaChange(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue().toString().equalsIgnoreCase("EDMS")) {
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            bindPopUpEdms.show(ph);
        } else if (valueChangeEvent.getNewValue().toString().equalsIgnoreCase("Electronic")) {
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            binderPopUpElectronic.show(ph);
        } else if (valueChangeEvent.getNewValue().toString().equalsIgnoreCase("Physical")) {
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            bindPopUpHardcopy.show(ph);
        } else {

        }
        bindSource.resetValue();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSource);
    }

    public String getDocumentTitle(String fileName) {
        fileName = fileName.replace("'", "''");
        String returnan = fileName;
        try {
            returnan =
                    fileName.substring(fileName.indexOf(" ") + 1, fileName.length());
        } catch (Exception e) {
        }
        return returnan;
    }

    public String getDocumentNumber(String fileName) {
        return fileName.split(" ")[0];
    }

    public void onChangeElectronicFile(ValueChangeEvent valueChangeEvent) throws IOException {
        file = (UploadedFile)valueChangeEvent.getNewValue();
        fileLength = file.getLength();
        fileInputStream = file.getInputStream();
        getBindElectronicName().setValue(file.getFilename());
        fileName = getBindElectronicName().getValue().toString().trim();
        fileContentType = file.getContentType();
        AdfFacesContext.getCurrentInstance().addPartialTarget(getBindElectronicName());
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String docPath = "";
        if (projectorOrganization.equalsIgnoreCase("Project")) {
            docPath = paramPath + "/Documents";
        } else {
            docPath = paramPath + "/" + year + "/Documents";
        }
        System.out.println("docpath "+docPath);
        RIDCClass ridc =
        new RIDCClass(Userlogin,
                     getPassword());
//        RIDCClass ridc =
//        new RIDCClass("weblogic",
//                     "Pertaminahe21");
        
//            new RIDCClass(GetSession().getAttribute("username").toString(),
//                          GetSession().getAttribute("password").toString());
        
        String folderGUID =
            ridc.FolderInfo(docPath).get("fFolderGUID")== null ? "":ridc.FolderInfo(docPath).get("fFolderGUID").toString();
//        DataObject ob = ridc.FolderInfo(docPath);
//        String folderGUID = ob.get("fFolderGUID").toString();
        System.out.println("folderGUID "+folderGUID);
        //DataResultSet rs = ridc.FolderSearchFile(folderGUID, fileName);
         OperationBinding method =
            GetBindings().getOperationBinding("EWIsDocExist");
        Map paramsMap = method.getParamsMap();
        paramsMap.put("folderGUID", folderGUID);
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
            bindUploadedFile.resetValue();
            bindUploadedFile = new RichInputFile();
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindUploadedFile);
            getBindElectronicName().setValue("");
            AdfFacesContext.getCurrentInstance().addPartialTarget(getBindElectronicName());
            fileName = "";
        } else {
            getBindElectronicName().setValue(fileName);
            getBindDocNumber().setValue(getDocumentNumber(fileName));
            getBinDocTitle().setValue(getDocumentTitle(fileName));
            AdfFacesContext.getCurrentInstance().addPartialTarget(getBindElectronicName());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getBindDocNumber());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getBinDocTitle());
        } 
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public oracle.jbo.domain.Date GetNewDate(java.util.Date pJavaDate) throws ParseException {
        SimpleDateFormat formater =
            new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String tempDate =
            formater.format(pJavaDate).replace("00:00:00", "23:59:59");
        return new oracle.jbo.domain.Date(new Timestamp(formater.parse(tempDate).getTime()));
    }

    public void ActionCreateTransmittal(ActionEvent actionEvent) {
        int statusTidakLengkap = 0;
        for (int cek = 0; cek < tempDoc.size(); cek++) {
            if (tempDoc.get(cek).getDocumentStatus().equals("0")) {
                statusTidakLengkap++;
            }
        }
        if (tempDoc.size() > 0 && dataUser.size() > 0 &&
            statusTidakLengkap == 0) {
            String penerima = "";
            BindingContext bindingctx = BindingContext.getCurrent();
            BindingContainer bindings = null;
            bindings = bindingctx.getCurrentBindingsEntry();
            java.util.Date utilDate =
                (java.util.Date)bindTransmittalDueDate.getValue();
            SimpleDateFormat formater =
                new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String tempDate =
                formater.format(utilDate).replace("00:00:00", "23:59:59");
            String tranmisttalNo = "";
            String Company= bindCompanyName.getValue().toString();
            
            tranmisttalNo =
                    CompanyApName.replaceAll("\\s+","")+"-ID-" + Calendar.getInstance().get(Calendar.YEAR) +
                    "" + (Calendar.getInstance().get(Calendar.MONTH) + 1) +
                    "" + Calendar.getInstance().get(Calendar.DATE);
            String latestNo = "0001";
            OperationBinding method =
                bindings.getOperationBinding("ExecuteWithParams");
            Map paramsMap = method.getParamsMap();
            paramsMap.put("temp", tranmisttalNo);
            method.execute();
            BindingContainer bindings2 = getBinding();
            DCIteratorBinding iterBind =
                (DCIteratorBinding)bindings2.get("LatestTransmittalNoVo1Iterator");
            RowSetIterator rsi = iterBind.getRowSetIterator();
            String descriptionLog =
                "Create Transmittal Internal " + Userlogin/*GetSession().getAttribute("username").toString()*/ +
                " to ";
            for (Row row : rsi.getAllRowsInRange()) {
                try {
                    String[] dir =
                        row.getAttribute("Latestid").toString().split("-");
                    latestNo = dir[dir.length - 1];
                    int b = 0;
                    b = Integer.parseInt(latestNo);
                    b = b + 1;
                    latestNo = String.valueOf(b);
                } catch (NullPointerException e) {
                    System.out.println("No Latest Id");
                }
            }
            if (latestNo.length() == 1) {
                latestNo = "000" + latestNo;
            } else if (latestNo.length() == 2) {
                latestNo = "00" + latestNo;
            } else if (latestNo.length() == 3) {
                latestNo = "0" + latestNo;
            } else {
            }

            ApplicationModule app =
                GetApplicationModule("AppModuleExternalTransmittalDataControl");
            oracle.jbo.domain.Date nowDate = getNowDate(new Date());
            System.out.println("now date = " + nowDate);

            try {
                File fileasli = null;
                fileasli =
                        new File("C:\\covernote_temp\\Cover Note Internal.xls");
                FileInputStream inp = null;
                try {
                    inp = new FileInputStream(fileasli);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                HSSFWorkbook my_xls_workbook = new HSSFWorkbook(inp);
                HSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0);
                Cell cell = null;
                cell = my_worksheet.getRow(7).getCell(4);
                cell.setCellValue(tranmisttalNo + "-" + latestNo);
                cell = my_worksheet.getRow(8).getCell(4);
                cell.setCellValue(bindOutputProjectTitle.getValue().toString());
                cell = my_worksheet.getRow(9).getCell(4);
                cell.setCellValue(nowDate.toString());
                cell = my_worksheet.getRow(10).getCell(4);
                //modified by nanda 241115
                cell.setCellValue(getBindTransmittalSubject().getValue().toString());

                cell = my_worksheet.getRow(28).getCell(2);
                cell.setCellValue(GetFullNameFromUsername(Userlogin));//ADFContext.getCurrent().getSecurityContext().getUserName()));
                //                cell = my_worksheet.getRow(29).getCell(2);
                //                cell.setCellValue(new Date().toString());
                //                cell = my_worksheet.getRow(30).getCell(2);
                //                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                //                cell.setCellValue(sdf.format(new java.util.Date()));

                //edit by nanda 220814 - untuk mengganti format tanggal covernote
                //                cell = my_worksheet.getRow(36).getCell(2);
                //                cell.setCellValue(new Date().toString());
                cell = my_worksheet.getRow(29).getCell(2);
                cell.setCellType(cell.CELL_TYPE_STRING);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                cell.setCellValue(sdf.format(new java.util.Date()));
                cell = my_worksheet.getRow(30).getCell(2);
                SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
                cell.setCellType(cell.CELL_TYPE_STRING);
                cell.setCellValue(sdf2.format(new java.util.Date()));

                //insert into phe_dswf_master
                ViewObject vo = app.findViewObject("PheDswfMasterVO1");
                NameValuePairs nvp = new NameValuePairs();
                nvp.setAttribute("TransmittalId",
                                 tranmisttalNo + "-" + latestNo);
                nvp.setAttribute("TransmittalType", "Internal");
                nvp.setAttribute("TransmittalRnrCode", getRoutineNonRoutine());
                //modified by nanda 241115
                nvp.setAttribute("TransmittalSubject",
                                 getBindTransmittalSubject().getValue().toString());
                nvp.setAttribute("ProjectorganizationId", getProjectOrgId());
                nvp.setAttribute("Companyap",CompanyApName);//company name
                nvp.setAttribute("ProjectorganizationName",
                                 bindOutputProjectTitle.getValue().toString());
                nvp.setAttribute("TransmittalCreator",Userlogin);
                                 //ADFContext.getCurrent().getSecurityContext().getUserName());
                nvp.setAttribute("TransmittalDuedate",
                                 GetNewDate(formater.parse(tempDate)));
                nvp.setAttribute("TransmittalStatus", "In Progress");
                vo.createAndInitRow(nvp);
                my_worksheet.shiftRows(18, my_worksheet.getLastRowNum(),
                                       tempDoc.size());
                String DocString =
                    "<table style=\"font-family:arial\" cellpadding=\"3\" cellspacing=\"3\">\n" +
                    "<tr style=\"background-color:#f0e68c\">\n" +
                    "<td>Document No</td>\n" +
                    "<td>Document Title</td>\n" +
                    "<td>Revision No</td>\n" +
                    "</tr>";
                for (int i = 0; i < tempDoc.size(); i++) {
                    //edit by nanda 140814 - add try catch
                    try {
                        my_worksheet.getRow(18 + i).createCell(0);
                        cell = my_worksheet.getRow(18 + i).getCell(0);
                        cell.setCellValue(i + 1);
                        my_worksheet.addMergedRegion(new CellRangeAddress(18 +
                                                                          i,
                                                                          18 +
                                                                          i, 1,
                                                                          3));
                        my_worksheet.getRow(18 + i).createCell(1);
                        cell = my_worksheet.getRow(18 + i).getCell(1);
                        cell.setCellValue(tempDoc.get(i).getDocumentNumber());
                        my_worksheet.getRow(18 + i).createCell(4);
                        cell = my_worksheet.getRow(18 + i).getCell(4);
                        cell.setCellValue(tempDoc.get(i).getDocumentTitle());
                        my_worksheet.getRow(18 + i).createCell(5);
                        cell = my_worksheet.getRow(18 + i).getCell(5);
                        cell.setCellValue(tempDoc.get(i).getRevision());
                        my_worksheet.getRow(18 + i).createCell(6);
                        cell = my_worksheet.getRow(18 + i).getCell(6);
                        cell.setCellValue(tempDoc.get(i).getDocumentStatus());
                        my_worksheet.getRow(18 + i).createCell(7);
                        cell = my_worksheet.getRow(18 + i).getCell(7);
                        cell.setCellValue(tempDoc.get(i).getPages());
                        my_worksheet.getRow(18 + i).createCell(8);
                        cell = my_worksheet.getRow(18 + i).getCell(8);
                        cell.setCellValue(tempDoc.get(i).getDistribution());
                        my_worksheet.getRow(18 + i).createCell(9);
                        cell = my_worksheet.getRow(18 + i).getCell(9);
                        cell.setCellValue(tempDoc.get(i).getRemarks());
                        //isnert to phe_dswf_doc
                        ViewObject voDoc = app.findViewObject("PhdDswfDocVo1");
                        NameValuePairs nvpDoc = new NameValuePairs();
                        nvpDoc.setAttribute("Id", tempDoc.get(i).getDid());
                        nvpDoc.setAttribute("TransmittalId",
                                            tranmisttalNo + "-" + latestNo);
                        nvpDoc.setAttribute("ContentId",
                                            tempDoc.get(i).getDDocname());
                        nvpDoc.setAttribute("Type", "Transmittal");
                        nvpDoc.setAttribute("Format",
                                            tempDoc.get(i).getDocFormat());
                        nvpDoc.setAttribute("Uploader",Userlogin);
                                           // ADFContext.getCurrent().getSecurityContext().getUserName());
                        nvpDoc.setAttribute("DocName",
                                            tempDoc.get(i).getDocumentName());
                        nvpDoc.setAttribute("DocTitle",
                                            tempDoc.get(i).getDocumentTitle());
                        nvpDoc.setAttribute("DocNumber",
                                            tempDoc.get(i).getDocumentNumber());
                        nvpDoc.setAttribute("DocStatus",
                                            tempDoc.get(i).getDocumentStatus());
                        nvpDoc.setAttribute("DocSource",
                                            tempDoc.get(i).getDistribution());
                        nvpDoc.setAttribute("Pages",
                                            tempDoc.get(i).getPages());
                        nvpDoc.setAttribute("Remarks",
                                            tempDoc.get(i).getRemarks());
                        nvpDoc.setAttribute("Revision",
                                            tempDoc.get(i).getRevision());
                        nvpDoc.setAttribute("DocType", "Supporting");
                        if (getParamPath().contains("publish")) {
                            nvpDoc.setAttribute("PublishStatus", "2");
                        }
                        voDoc.createAndInitRow(nvpDoc);
                        DocString =
                                DocString + "<tr style=\"background-color:#dcdcdc\">" +
                                "<td>" + tempDoc.get(i).getDocumentNumber() +
                                "</td>" + "<td>" +
                                tempDoc.get(i).getDocumentTitle() + "</td>" +
                                "<td>" + tempDoc.get(i).getRevision() +
                                "</td></tr>";
                        for (int j = 0; j < dataUser.size(); j++) {
                            ViewObject voDocProcess =
                                app.findViewObject("PheDocProcessVO1");
                            NameValuePairs nvpDocProcess =
                                new NameValuePairs();
                            nvpDocProcess.setAttribute("Did",
                                                       tempDoc.get(i).getDid());
                            nvpDocProcess.setAttribute("TransmittalId",
                                                       tranmisttalNo + "-" +
                                                       latestNo);
                            nvpDocProcess.setAttribute("Sender",Userlogin);
                                                       //GetSession().getAttribute("username").toString());
                            nvpDocProcess.setAttribute("Recipient",
                                                       dataUser.get(j).getId());
                            nvpDocProcess.setAttribute("DocTitle",
                                                       tempDoc.get(i).getDocumentTitle());
                            nvpDocProcess.setAttribute("DocStatus",
                                                       tempDoc.get(i).getDocumentStatus());
                            nvpDocProcess.setAttribute("DocType",
                                                       "Supporting");
                            voDocProcess.createAndInitRow(nvpDocProcess);
                        }
                    } catch (Exception e) {
                        //                    e.printStackTrace();
                        //nanda - 2310 - commented due of error forward/create internal transmittal
                        //                    my_worksheet.getRow(18+i).createCell(0);
                        //add by nanda 1408 - to create row when getrow is null pointer
                        my_worksheet.createRow(18 + i);
                        my_worksheet.getRow(18 + i).createCell(0);
                        cell = my_worksheet.getRow(18 + i).getCell(0);
                        cell.setCellValue(i + 1);


                        //                    my_worksheet.createRow(18+i);
                        //                    cell = my_worksheet.getRow(18+i).getCell(0);
                        //                    cell.setCellValue(i+1);
                        my_worksheet.addMergedRegion(new CellRangeAddress(18 +
                                                                          i,
                                                                          18 +
                                                                          i, 1,
                                                                          3));
                        my_worksheet.getRow(18 + i).createCell(1);
                        cell = my_worksheet.getRow(18 + i).getCell(1);
                        cell.setCellValue(tempDoc.get(i).getDocumentNumber());
                        my_worksheet.getRow(18 + i).createCell(4);
                        cell = my_worksheet.getRow(18 + i).getCell(4);
                        cell.setCellValue(tempDoc.get(i).getDocumentTitle());
                        my_worksheet.getRow(18 + i).createCell(5);
                        cell = my_worksheet.getRow(18 + i).getCell(5);
                        cell.setCellValue(tempDoc.get(i).getRevision());
                        my_worksheet.getRow(18 + i).createCell(6);
                        cell = my_worksheet.getRow(18 + i).getCell(6);
                        cell.setCellValue(tempDoc.get(i).getDocumentStatus());
                        my_worksheet.getRow(18 + i).createCell(7);
                        cell = my_worksheet.getRow(18 + i).getCell(7);
                        cell.setCellValue(tempDoc.get(i).getPages());
                        my_worksheet.getRow(18 + i).createCell(8);
                        cell = my_worksheet.getRow(18 + i).getCell(8);
                        cell.setCellValue(tempDoc.get(i).getDistribution());
                        my_worksheet.getRow(18 + i).createCell(9);
                        cell = my_worksheet.getRow(18 + i).getCell(9);
                        cell.setCellValue(tempDoc.get(i).getRemarks());
                        //isnert to phe_dswf_doc
                        ViewObject voDoc = app.findViewObject("PhdDswfDocVo1");
                        NameValuePairs nvpDoc = new NameValuePairs();
                        nvpDoc.setAttribute("Id", tempDoc.get(i).getDid());
                        nvpDoc.setAttribute("TransmittalId",
                                            tranmisttalNo + "-" + latestNo);
                        nvpDoc.setAttribute("ContentId",
                                            tempDoc.get(i).getDDocname());
                        nvpDoc.setAttribute("Type", "Transmittal");
                        nvpDoc.setAttribute("Format",
                                            tempDoc.get(i).getDocFormat());
                        nvpDoc.setAttribute("Uploader",Userlogin);
                                            //ADFContext.getCurrent().getSecurityContext().getUserName());
                        nvpDoc.setAttribute("DocName",
                                            tempDoc.get(i).getDocumentName());
                        nvpDoc.setAttribute("DocTitle",
                                            tempDoc.get(i).getDocumentTitle());
                        nvpDoc.setAttribute("DocNumber",
                                            tempDoc.get(i).getDocumentNumber());
                        nvpDoc.setAttribute("DocStatus",
                                            tempDoc.get(i).getDocumentStatus());
                        nvpDoc.setAttribute("DocSource",
                                            tempDoc.get(i).getDistribution());
                        nvpDoc.setAttribute("Pages",
                                            tempDoc.get(i).getPages());
                        nvpDoc.setAttribute("Remarks",
                                            tempDoc.get(i).getRemarks());
                        nvpDoc.setAttribute("Revision",
                                            tempDoc.get(i).getRevision());
                        nvpDoc.setAttribute("DocType", "Supporting");
                        if (getParamPath().contains("publish")) {
                            nvpDoc.setAttribute("PublishStatus", "2");
                        }
                        voDoc.createAndInitRow(nvpDoc);
                        DocString =
                                DocString + "<tr style=\"background-color:#dcdcdc\">" +
                                "<td>" + tempDoc.get(i).getDocumentNumber() +
                                "</td>" + "<td>" +
                                tempDoc.get(i).getDocumentTitle() + "</td>" +
                                "<td>" + tempDoc.get(i).getRevision() +
                                "</td></tr>";
                        for (int j = 0; j < dataUser.size(); j++) {
                            ViewObject voDocProcess =
                                app.findViewObject("PheDocProcessVO1");
                            NameValuePairs nvpDocProcess =
                                new NameValuePairs();
                            nvpDocProcess.setAttribute("Did",
                                                       tempDoc.get(i).getDid());
                            nvpDocProcess.setAttribute("TransmittalId",
                                                       tranmisttalNo + "-" +
                                                       latestNo);
                            nvpDocProcess.setAttribute("Sender",Userlogin);
                                                       //GetSession().getAttribute("username").toString());
                            nvpDocProcess.setAttribute("Recipient",
                                                       dataUser.get(j).getId());
                            nvpDocProcess.setAttribute("DocTitle",
                                                       tempDoc.get(i).getDocumentTitle());
                            nvpDocProcess.setAttribute("DocStatus",
                                                       tempDoc.get(i).getDocumentStatus());
                            nvpDocProcess.setAttribute("DocType",
                                                       "Supporting");
                            voDocProcess.createAndInitRow(nvpDocProcess);
                        }
                    }
                }
                DocString = DocString + "</table>";
                my_worksheet.shiftRows(14, my_worksheet.getLastRowNum(),
                                       dataUser.size());
                ViewObject voPar = app.findViewObject("PheDswfPaticipantVO1");
                ViewObject voUserRole =
                    app.findViewObject("UserSecurityAttributesVO1");
                System.out.println("dataUser.size() internal : " +
                                   dataUser.size());
                for (int i = 0; i < dataUser.size(); i++) {
                    if (i != 0 && i < dataUser.size()) {
                        descriptionLog = descriptionLog + ", ";
                        //modified by nanda 0110
                        //                        penerima = penerima+",";
                    }
                    penerima =
                            dataUser.get(i).getContactEmail() + "," + penerima;
                    NameValuePairs nvpPar = new NameValuePairs();
                    nvpPar.setAttribute("TransmittalId",
                                        tranmisttalNo + "-" + latestNo);
                    nvpPar.setAttribute("Sender",Userlogin);
//                                        GetSession().getAttribute("username").toString());
                    nvpPar.setAttribute("Receiver", dataUser.get(i).getId());
                    nvpPar.setAttribute("Role",
                                        dataUser.get(i).getContactRole());
                    voPar.createAndInitRow(nvpPar);
                    descriptionLog =
                            descriptionLog + dataUser.get(i).getUsername();
                    //insert user security attributes
                    if (IsRoleExist(dataUser.get(i).getId(),
                                    dataUser.get(i).getContactRole()).equals("0")) {
                        NameValuePairs nvpUserRole = new NameValuePairs();
                        nvpUserRole.setAttribute("Dusername",
                                                 dataUser.get(i).getId());
                        nvpUserRole.setAttribute("Dattributetype", "role");
                        nvpUserRole.setAttribute("Dattributename",
                                                 dataUser.get(i).getContactRole());
                        nvpUserRole.setAttribute("Dattributeprivilege", "15");
                        voUserRole.createAndInitRow(nvpUserRole);
                    }
                    //end insert user security attributes
                  
                  //modified by nanda 040216
                  // add try catch and createRow
                  try{
                    my_worksheet.getRow(14 + i).createCell(0);
                    cell = my_worksheet.getRow(14 + i).getCell(0);
                    cell.setCellValue(i + 1);
                    my_worksheet.addMergedRegion(new CellRangeAddress(14 + i,
                                                                      14 + i,
                                                                      1, 3));
                    my_worksheet.getRow(14 + i).createCell(1);
                    cell = my_worksheet.getRow(14 + i).getCell(1);
                    cell.setCellValue("");
                    my_worksheet.addMergedRegion(new CellRangeAddress(14 + i,
                                                                      14 + i,
                                                                      4, 7));
                    my_worksheet.getRow(14 + i).createCell(4);
                    cell = my_worksheet.getRow(14 + i).getCell(4);
                    cell.setCellValue(dataUser.get(i).getUsername());
                  }catch(Exception e){
                    my_worksheet.createRow(14 + i);
                    my_worksheet.getRow(14 + i).createCell(0);
                    cell = my_worksheet.getRow(14 + i).getCell(0);
                    cell.setCellValue(i + 1);
                    my_worksheet.addMergedRegion(new CellRangeAddress(14 + i,
                                                                      14 + i,
                                                                      1, 3));
                    my_worksheet.getRow(14 + i).createCell(1);
                    cell = my_worksheet.getRow(14 + i).getCell(1);
                    cell.setCellValue("");
                    my_worksheet.addMergedRegion(new CellRangeAddress(14 + i,
                                                                      14 + i,
                                                                      4, 7));
                    my_worksheet.getRow(14 + i).createCell(4);
                    cell = my_worksheet.getRow(14 + i).getCell(4);
                    cell.setCellValue(dataUser.get(i).getUsername());
                    e.printStackTrace();
                  }
                }

                //insert to log
                ViewObject voLog = app.findViewObject("PheDswfLog1");
                NameValuePairs nvpLog = new NameValuePairs();
                nvpLog.setAttribute("TransmittalId",
                                    tranmisttalNo + "-" + latestNo);
                nvpLog.setAttribute("Username",Userlogin);
//                                    GetSession().getAttribute("username").toString());
                if (getParamPath().contains("publish")) {
                    nvpLog.setAttribute("Action", "5");
                } else {
                    nvpLog.setAttribute("Action", "1");
                }
                nvpLog.setAttribute("ActionDate", nowDate);
                nvpLog.setAttribute("Description", descriptionLog);
                voLog.createAndInitRow(nvpLog);

                int year = Calendar.getInstance().get(Calendar.YEAR);
                RIDCClass ridc =
                new RIDCClass(Userlogin,
                             getPassword());
//                    new RIDCClass(GetSession().getAttribute("username").toString(),
//                                  GetSession().getAttribute("password").toString());
                if (getParamPath().contains("publish")) {
                    ridc.CreateFolderTransmittal(tranmisttalNo + "-" +
                                                 latestNo,
                                                 "/Document Secure Workflow/Publish");
                } else {
                    if (projectorOrganization.equalsIgnoreCase("Project")) {
                        ridc.CreateFolderTransmittal(tranmisttalNo + "-" +
                                                     latestNo, getParamPath());
                    } else {
                        ridc.CreateFolderTransmittal(tranmisttalNo + "-" +
                                                     latestNo,
                                                     getParamPath() + "/" +
                                                     String.valueOf(year));
                    }
                }
//                String publishfolderGUID =
//                    ridc.FolderInfo("Document Secure Workflow/Publish/Documents").get("fFolderGUID").toString();
                for (int i = 0; i < tempDoc.size(); i++) {
                    if (!tempDoc.get(i).getDDocname().isEmpty()) {
                        if (getParamPath().contains("publish")) {
                            if (tempDoc.get(i).getDistribution().equals("Electronic")) {
                                try {
                                    //                                    DataObject obj = ridc.CopyFile(publishfolderGUID, ridc.GetFileInfo(tempDoc.get(i).getDid()).get("fFileGUID").toString());
                                    ridc.createLink("/Document Secure Workflow/Publish/" +
                                                    tranmisttalNo + "-" +
                                                    latestNo +
                                                    "/Supporting Documents",
                                                    tempDoc.get(i).getDid());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            if (projectorOrganization.equalsIgnoreCase("Project")) {
                                ridc.createLink(getParamPath() + "/" +
                                                tranmisttalNo + "-" +
                                                latestNo +
                                                "/Supporting Documents",
                                                tempDoc.get(i).getDid());
                            } else {
                                ridc.createLink(getParamPath() + "/" +
                                                String.valueOf(year) + "/" +
                                                tranmisttalNo + "-" +
                                                latestNo +
                                                "/Supporting Documents",
                                                tempDoc.get(i).getDid());
                            }
                        }
                    }
                }
                inp.close();
                FileOutputStream output_file =
                    new FileOutputStream(new File("C:\\covernote_temp\\Cover Note - " +
                                                  tranmisttalNo + "-" +
                                                  latestNo + ".xls"));
                //write changes
                my_xls_workbook.write(output_file);
                //close the stream
                output_file.close();
                File file =
                    new File("C:\\covernote_temp\\Cover Note - " + tranmisttalNo +
                             "-" + latestNo + ".xls");
                InputStream is = null;
                try {
                    is = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                String dID = "";
                if (getParamPath().contains("publish")) {
                   /*  dID =
                    ridc.CheckinCoverNote(is, file.getName(), file.length(),
                      "Document Secure Workflow/Publish/" + tranmisttalNo +
                      "-" + latestNo + "/Supporting Documents");
                    ridc.createLinkReadme("Document Secure Workflow/Publish/" +
                                          tranmisttalNo + "-" + latestNo,
                                          "READMEFILEINDONESIA");
                    ridc.createLinkReadme("Document Secure Workflow/Publish/" +
                                          tranmisttalNo + "-" + latestNo,
                                          "READMEFILEENGLISH"); */
                } else {
                    if (projectorOrganization.equalsIgnoreCase("Project")) {
                        System.out.println("is "+is);
                        System.out.println("file "+file.getName());
                        System.out.println("file length "+file.length());
                        System.out.println("Path "+getParamPath());
                        System.out.println("tranmisttalNo "+tranmisttalNo);
                        System.out.println("latestNo "+latestNo);
                        dID =ridc.CheckinCoverNote(is, file.getName(), file.length(),
                      getParamPath() + "/" + tranmisttalNo + "-" + latestNo +
                      "/Supporting Documents");
                        ridc.createLinkReadme(getParamPath() + "/" +
                                              tranmisttalNo + "-" + latestNo,
                                              "READMEFILEINDONESIA");
                        ridc.createLinkReadme(getParamPath() + "/" +
                                              tranmisttalNo + "-" + latestNo,
                                              "READMEFILEENGLISH");
                    } else {
                        dID =
                        ridc.CheckinCoverNote(is, file.getName(), file.length(),
                      getParamPath() + "/" + year + "/" + tranmisttalNo + "-" +
                      latestNo + "/Supporting Documents");
                        ridc.createLinkReadme(getParamPath() + "/" +
                                              String.valueOf(year) + "/" +
                                              tranmisttalNo + "-" + latestNo,
                                              "READMEFILEINDONESIA");
                        ridc.createLinkReadme(getParamPath() + "/" +
                                              String.valueOf(year) + "/" +
                                              tranmisttalNo + "-" + latestNo,
                                              "READMEFILEENGLISH");
                    }
                }
                DataObject ob = ridc.GetDocInfo(dID);
                ViewObject voDoc = app.findViewObject("PhdDswfDocVo1");
                NameValuePairs nvpDoc = new NameValuePairs();
                nvpDoc.setAttribute("Id", dID);
                nvpDoc.setAttribute("TransmittalId",
                                    tranmisttalNo + "-" + latestNo);
                nvpDoc.setAttribute("ContentId",
                                    ob.get("dDocName").toString());
                nvpDoc.setAttribute("Type", "Transmittal");
                nvpDoc.setAttribute("Format", ob.get("dFormat").toString());
                nvpDoc.setAttribute("Uploader",Userlogin);
                                    //GetSession().getAttribute("username").toString());
                nvpDoc.setAttribute("UploadDate", nowDate);
                nvpDoc.setAttribute("DocTitle",
                                    ob.get("dDocTitle").toString());
                nvpDoc.setAttribute("DocType", "Covernote");
                voDoc.createAndInitRow(nvpDoc);

                app.getTransaction().commit();
                file.delete();
                //nanda 190615 - email looping

                for (int i = 0; i < dataUser.size(); i++) {
                    String isi = "";
                    if (getParamPath().contains("publish")) {
                       /*  isi = "<html>\n" +
                                "<head></head>\n" +
                                "<body style=\"font-family:arial\">Dear Sir/Madam<br/><br/>You have received a request from TDC to send the native file(s) for the following approved document(s):<br/><br/>\n" +
                                DocString + "<br/><br/>" +
                                "Please see click this <a href=\"" +
                                currentAddress +
                                "?IdcService=PHE_GET_TRANSMITTAL_INBOX&dUser=" +
                                dataUser.get(i).getId()  +  //ini ambil username, sebelumnya ambil fullname - nanda 131215
                                "&sender=&dateType=&month=&year=&status=&category=&startRow=1&endRow=10&transmittalNo=" +
                                tranmisttalNo + "-" + latestNo +
                                "\">link</a> to open the transmittal sheet and upload the native file(s) into the system." +
                                "<br/>Thank you for your attention.</body></html>"; */
                    } else {
                        isi = "<html>\n" +
                                "<head></head>\n" +
                                "<body style=\"font-family:arial;text-align:left\">Dear Sir/Madam<br/><br/>You have received a new transmittal. The details are:<br/><br/>\n" +
                                "<table ><tr><td>Transmittal No</td><td>: " +
                                tranmisttalNo + "-" + latestNo +
                                "</td></tr>\n" +
                                "<tr><td>Project/Organization</td><td>: " +
                                bindOutputProjectTitle.getValue().toString() +
                                "</td></tr>" + "<tr><td>Subject</td><td>: " +
                                getBindTransmittalSubject().getValue().toString() +
                                "</td></tr>" +
                                //modified by nanda 241115 pake componentReference
                                "</table><br/><br/>" + DocString +
                                "<br/><br/>" +
                                "Please see the transmittal in Inbox DSWF menu or click this <a href=\"" +
                                currentAddress +
                                "?IdcService=PHE_GET_TRANSMITTAL_INBOX&fromEmail=1&dUser=" +
                                dataUser.get(i).getId() +
                                "&sender=&dateType=&month=&year=&status=&category=&startRow=1&endRow=10&transmittalNo=" +
                                tranmisttalNo + "-" + latestNo +
                                "\">link</a>" +
                                "<br/>Thank you for your attention.</body></html>";
                    }
// edit SendEmailTo to SendEmail nanda 131215
                    OperationBinding sendEmail =
                        bindings.getOperationBinding("SendEmail"); //edit by nanda 2708 edit SendEmailTo - 2609 dari SendEmail ke sendEmailTo
                    Map paramEmail = sendEmail.getParamsMap();
                    System.out.println("penerima internal:" +dataUser.get(i).getId()+";"+dataUser.get(i).getUsername()+";"+
                                       dataUser.get(i).getContactEmail());
                    if (!dataUser.get(i).getContactEmail().isEmpty()) {
                        //modified by nanda 0110
                       
                        paramEmail.put("EmailTo",
                                       dataUser.get(i).getContactEmail());
                    } else {
                        paramEmail.put("EmailTo",
                                       "owc.support@pertamina.com");
                    }
                    //modified by nanda 071215 - creator transmittal
//                    paramEmail.put("EmailCc", GetCreatorEmail());
//                    paramEmail.put("EmailCc", "");
                    if (getParamPath().contains("publish")) {
                        paramEmail.put("Subject",
                                       "["+CompanyApName.replaceAll("\\s+","")+"] Request Native File for transmittal No. " +
                                       tranmisttalNo + "-" + latestNo);
                    } else {
                        paramEmail.put("Subject",
                                       "["+CompanyApName.replaceAll("\\s+","")+"] New Transmittal No. " +
                                       tranmisttalNo + "-" + latestNo);
                    }

                    paramEmail.put("HtmlText", isi);
                    try {
                        sendEmail.execute();/*matikan email dulu 27082021*/
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


                //end update email looping
                
                //add by nanda - 131215 - send email to transmittal creator / doc control
                String isitdc = "<html>\n" +
                        "<head></head>\n" +
                        "<body style=\"font-family:arial;text-align:left\">Dear Sir/Madam<br/><br/>You have created a new transmittal. The details are:<br/><br/>\n" +
                        "<table ><tr><td>Transmittal No</td><td>: " +
                        tranmisttalNo + "-" + latestNo +
                        "</td></tr>\n" +
                        "<tr><td>Project/Organization</td><td>: " +
                        bindOutputProjectTitle.getValue().toString() +
                        "</td></tr>" + "<tr><td>Subject</td><td>: " +
                        getBindTransmittalSubject().getValue().toString() +
                        "</td></tr>" +
                        //modified by nanda 241115 pake componentReference
                        "</table><br/><br/>" + DocString +
                        "<br/><br/>" +
                        "Please see the transmittal in TDC Review menu or click this <a href=\"" +
                        currentAddress +
                        "?IdcService=PHE_GET_TRANSMITTAL_REVIEW&fromEmail=1" +
                        "&sender=&dateType=&month=&year=&status=&category=&startRow=1&endRow=10&transmittalNo=" +
                        tranmisttalNo + "-" + latestNo +
                        "\">link</a>" +
                        "<br/>Thank you for your attention.</body></html>";
                OperationBinding sendEmail =
                    bindings.getOperationBinding("SendEmail"); 
                Map paramEmail = sendEmail.getParamsMap();
                System.out.println("penerima doc control:" + GetCreatorEmail());
                if (!GetCreatorEmail().isEmpty()) {
                    //modified by nanda 0110
                   
                    paramEmail.put("EmailTo",
                                    GetCreatorEmail());
                } else {
                    paramEmail.put("EmailTo",
                                   "owc.support@pertamina.com");
                }
//                paramEmail.put("EmailCc", "");
                paramEmail.put("Subject", "["+CompanyApName.replaceAll("\\s+","")+"] New Transmittal No. " +
                                   tranmisttalNo + "-" + latestNo);

                paramEmail.put("HtmlText", isitdc);
                try {
                    sendEmail.execute();/*matikan email dulu 27082021*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
               
                //end update - send email to transmittal creator / doc control

                RichPopup.PopupHints ph = new RichPopup.PopupHints();
                bindPopUpSuccess.show(ph);

                //nanda - 2310 - hapus cache abis createinternal
                tempDoc.clear();
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableDoc);
            } catch (Exception e) {
                //rollback transaction
                app.getTransaction().rollback();
                e.printStackTrace();
                FacesMessage msg =
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Create new transmittal failed",
                                     "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        } else {
            if (statusTidakLengkap != 0) {
                FacesMessage msg =
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "You have to set the document status in every document",
                                     "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg =
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "You have to choose document(s) and user(s)",
                                     "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
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

    public void setBindLabelEditDocName(RichPanelLabelAndMessage bindLabelEditDocName) {
        this.bindLabelEditDocName = bindLabelEditDocName;
    }

    public RichPanelLabelAndMessage getBindLabelEditDocName() {
        return bindLabelEditDocName;
    }

    public void setBindEditDocName(RichOutputLabel bindEditDocName) {
        this.bindEditDocName = bindEditDocName;
    }

    public RichOutputLabel getBindEditDocName() {
        return bindEditDocName;
    }

    public void setBindEditDocNumber(RichInputText bindEditDocNumber) {
        this.bindEditDocNumber = bindEditDocNumber;
    }

    public RichInputText getBindEditDocNumber() {
        return bindEditDocNumber;
    }

    public void setBindEditDocTitle(RichInputText bindEditDocTitle) {
        this.bindEditDocTitle = bindEditDocTitle;
    }

    public RichInputText getBindEditDocTitle() {
        return bindEditDocTitle;
    }

    public void setBindEditDocStatus(RichSelectOneChoice bindEditDocStatus) {
        this.bindEditDocStatus = bindEditDocStatus;
    }

    public RichSelectOneChoice getBindEditDocStatus() {
        return bindEditDocStatus;
    }

    public void setBindEditPages(RichInputText bindEditPages) {
        this.bindEditPages = bindEditPages;
    }

    public RichInputText getBindEditPages() {
        return bindEditPages;
    }

    public void setBindEditRemarks(RichInputText bindEditRemarks) {
        this.bindEditRemarks = bindEditRemarks;
    }

    public RichInputText getBindEditRemarks() {
        return bindEditRemarks;
    }

    public void setBindEditRevisions(RichInputText bindEditRevisions) {
        this.bindEditRevisions = bindEditRevisions;
    }

    public RichInputText getBindEditRevisions() {
        return bindEditRevisions;
    }

    public void setBindEditDistributionMethod(RichSelectOneChoice bindEditDistributionMethod) {
        this.bindEditDistributionMethod = bindEditDistributionMethod;
    }

    public RichSelectOneChoice getBindEditDistributionMethod() {
        return bindEditDistributionMethod;
    }

    public void setBindPopUpEditForm(RichPopup bindPopUpEditForm) {
        this.bindPopUpEditForm = bindPopUpEditForm;
    }

    public RichPopup getBindPopUpEditForm() {
        return bindPopUpEditForm;
    }

    public void EditFile(ActionEvent actionEvent) {
        int tempHapus =
            Integer.parseInt(actionEvent.getComponent().getAttributes().get("EditFileIndex").toString());
        bindEditIndex.setValue(tempHapus);
        ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl("AppModuleExternalTransmittalDataControl");
        ViewObject voStatus = am.findViewObject("ListDocumentStatusVO1");
        voStatus.executeQuery();
        int i = 0;
        while (voStatus.hasNext()) {
            Row rowStatus = voStatus.next();
            if (tempDoc.get(tempHapus).getDocumentStatus().equalsIgnoreCase(rowStatus.getAttribute("Description").toString())) {
                break;
            }
            i++;
        }
        bindEditDocStatus = new RichSelectOneChoice();
        bindEditDocStatus.setValue(i);
        bindEditDocNumber.setValue(tempDoc.get(tempHapus).getDocumentNumber());
        bindEditDocTitle.setValue(tempDoc.get(tempHapus).getDocumentTitle());
        bindEditPages.setValue(tempDoc.get(tempHapus).getPages());
        bindEditRemarks.setValue(tempDoc.get(tempHapus).getRemarks());
        bindEditRevisions.setValue(tempDoc.get(tempHapus).getRevision());
        if (tempDoc.get(tempHapus).getDistribution().equalsIgnoreCase("Electronic")) {
            bindEditDocName.setValue(tempDoc.get(tempHapus).getDocumentName());
            bindEditDocName.setVisible(true);
            bindLabelEditDocName.setVisible(true);
            bindEditDistributionMethod.setVisible(false);
        } else {
            ViewObject voDis = am.findViewObject("ListSentHardcopyVO1");
            voDis.executeQuery();
            i = 0;
            while (voDis.hasNext()) {
                Row rowStatus = voDis.next();
                if (tempDoc.get(tempHapus).getDistribution().equalsIgnoreCase(rowStatus.getAttribute("Description").toString())) {
                    break;
                }
                i++;
            }
            bindEditDistributionMethod = new RichSelectOneChoice();
            bindEditDistributionMethod.setValue(i);
            bindEditDocName.setVisible(false);
            bindLabelEditDocName.setVisible(false);
            bindEditDistributionMethod.setVisible(true);
        }
        voStatus.executeQuery();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEditDistributionMethod);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEditDocName);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEditDocNumber);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEditDocTitle);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEditPages);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEditRemarks);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEditDocStatus);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEditRevisions);
        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        bindPopUpEditForm.show(ph);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindPopUpEditForm);
    }

    public void dialogBoxEditForm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            int temp = Integer.parseInt(bindEditIndex.getValue().toString());
            BindingContext bindingctx = BindingContext.getCurrent();
            BindingContainer bindings = null;
            bindings = bindingctx.getCurrentBindingsEntry();
            DCBindingContainer bindingsImpl = (DCBindingContainer)bindings;
            DCIteratorBinding dciter = null;
            dciter =
                    bindingsImpl.findIteratorBinding("ListDocumentStatusVO1Iterator"); // your lookup iterator
            Row status =
                dciter.getRowAtRangeIndex((Integer.parseInt(bindEditDocStatus.getValue().toString())));
            tempDoc.get(temp).setDocumentNumber(bindEditDocNumber.getValue().toString());
            tempDoc.get(temp).setDocumentTitle(bindEditDocTitle.getValue().toString());
            tempDoc.get(temp).setDocumentStatus(status.getAttribute("Description").toString());
            tempDoc.get(temp).setPages(bindEditPages.getValue().toString());
            tempDoc.get(temp).setRemarks(bindEditRemarks.getValue().toString());
            tempDoc.get(temp).setRevision(bindEditRevisions.getValue().toString());
            if (tempDoc.get(temp).getDistribution().equalsIgnoreCase("Electronic")) {
                //                RIDCClass ridc = new RIDCClass(GetSession().getAttribute("username").toString(),GetSession().getAttribute("password").toString());
                //                ridc.UpdateAfterCopyFile(tempDoc.get(temp).getDid(), tempDoc.get(temp).getDDocname(),
                //                                         tempDoc.get(temp).getDocumentTitle(), tempDoc.get(temp).getDocumentName(),
                //                                         tempDoc.get(temp).getDocumentNumber(), tempDoc.get(temp).getRevision());
            } else {
                DCIteratorBinding dciter2 = null;
                dciter2 =
                        bindingsImpl.findIteratorBinding("ListSentHardcopyVO1Iterator"); // your lookup iterator
                Row temp2 =
                    dciter2.getRowAtRangeIndex((Integer.parseInt(bindHardcopyDistributionMethod.getValue().toString())));
                tempDoc.get(temp).setDistribution(temp2.getAttribute("Value").toString());
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableDoc);

        }
    }

    public void DeleteFile(ActionEvent actionEvent) {
        int tempHapus =
            Integer.parseInt(actionEvent.getComponent().getAttributes().get("delFileIndex").toString());
        if (getIsForward().equals("No")) {
            RIDCClass ridc =
            new RIDCClass(Userlogin,
                         getPassword());
//                new RIDCClass(GetSession().getAttribute("username").toString(),
//                              GetSession().getAttribute("password").toString());
            ridc.DeleteDoc(tempDoc.get(tempHapus).getDid(),
                           tempDoc.get(tempHapus).getDDocname());
        }
        tempDoc.remove(tempHapus);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableDoc);
    }

    public void CancelTransmittal(ActionEvent actionEvent) {
        //modified by nanda - 020215 - do not delete doc if forward
        System.out.println("isForward=" + getIsForward());
        if (getIsForward().equals("No")) {
            System.out.println();
            RIDCClass ridc =
                new RIDCClass(getWeblogicusername(), getWeblogicpassword());
            for (int i = 0; i < tempDoc.size(); i++) {
                if (!tempDoc.get(i).getDDocname().isEmpty()) {
                    ridc.DeleteDoc(tempDoc.get(i).getDid(),
                                   tempDoc.get(i).getDDocname());
                }
            }
        }
        if (getParamPath().contains("publish")) {
            RIDCClass ridc =
                new RIDCClass(getWeblogicusername(), getWeblogicpassword());
            for (int i = 0; i < tempDoc.size(); i++) {
                if (!tempDoc.get(i).getDDocname().isEmpty()) {
                    ridc.DeleteDoc(tempDoc.get(i).getDid(),
                                   tempDoc.get(i).getDDocname());
                }
            }
        }
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            // modified by nanda - 020215 - clear cache after cancel forward
            tempDoc.clear();
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableDoc);

            if (getParamPath().contains("publish")) {
                ctx.getExternalContext().redirect(currentAddress +
                                                  "?IdcService=PHE_GET_TRANSMITTAL_DOC_SEARCH&keyword=&status=&startRow=&endRow=");
            } else {
                ctx.getExternalContext().redirect(currentAddress +
                                                  "?IdcService=FLD_BROWSE&path=" +
                                                  getParamPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            if (getParamPath().contains("publish")) {
                ctx.getExternalContext().redirect(currentAddress +
                                                  "?IdcService=PHE_GET_TRANSMITTAL_DOC_SEARCH&keyword=&status=&startRow=&endRow=&fromCreate=1");
            } else {
                ctx.getExternalContext().redirect(currentAddress +
                                                  "?IdcService=FLD_BROWSE&fromCreate=1&path=" +
                                                  getParamPath());
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

    public void setWeblogicpassword(String weblogicpassword) {
        this.weblogicpassword = weblogicpassword;
    }

    public String getWeblogicpassword() {
        return weblogicpassword;
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

    public List AutoSuggestItems(String keyword) {
        ArrayList<SelectItem> selectItems = new ArrayList<SelectItem>();
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding setVariable =
            (OperationBinding)bindings.get("ExecuteWithParamAutoSuggest");
        setVariable.getParamsMap().put("keyword", keyword);
        setVariable.execute();
        JUCtrlHierBinding hierBinding =
            (JUCtrlHierBinding)bindings.get("AutoSuggestInternalUserVO1");
        hierBinding.executeQuery();
        List<JUCtrlValueBindingRef> displayDataList =
            hierBinding.getRangeSet();
        for (JUCtrlValueBindingRef displayData : displayDataList) {
            Row rw = displayData.getRow();
            selectItems.add(new SelectItem((String)rw.getAttribute("Dfullname"),
                                           (String)rw.getAttribute("Dfullname")));
        }
        return selectItems;
    }

    public void setIsForward(String isForward) {
        this.isForward = isForward;
    }

    public String getIsForward() {
        return isForward;
    }

  public void setBindPopUpRecipient(RichPopup bindPopUpRecipient) {
    this.bindPopUpRecipient = bindPopUpRecipient;
  }

  public RichPopup getBindPopUpRecipient() {
    return bindPopUpRecipient;
  }

  public void recipientSelected(SelectionEvent selectionEvent) {
    // Add event code here...
    ADFUtil.invokeEL("#{bindings.UsersInternalVO1.collectionModel.makeCurrent}",new Class[] { SelectionEvent.class }, new Object[] { selectionEvent });
    Row selectedRow = (Row)ADFUtil.evaluateEL("#{bindings.UsersInternalVO1Iterator.currentRow}");
    ADFUtil.setEL("#{pageFlowScope.userRecipient}", selectedRow.getAttribute("Username"));
    ADFUtil.setEL("#{pageFlowScope.userFullname}", selectedRow.getAttribute("Fullname"));
    ADFUtil.setEL("#{pageFlowScope.userEmail}", selectedRow.getAttribute("Email"));
    
    RichPopup.PopupHints ph = new RichPopup.PopupHints();
    bindPopUpRecipient.show(ph);
  }

  public void setBindPopUpEnter(RichPopup bindPopUpEnter) {
    this.bindPopUpEnter = bindPopUpEnter;
  }

  public RichPopup getBindPopUpEnter() {
    return bindPopUpEnter;
  }

    public void setBindCompanyName(RichOutputLabel bindCompanyName) {
        this.bindCompanyName = bindCompanyName;
    }

    public RichOutputLabel getBindCompanyName() {
        return bindCompanyName;
    }


    public void setIbindCompanyName(RichInputText ibindCompanyName) {
        this.ibindCompanyName = ibindCompanyName;
    }

    public RichInputText getIbindCompanyName() {
        return ibindCompanyName;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPassword() {
        return Password;
    }


}
