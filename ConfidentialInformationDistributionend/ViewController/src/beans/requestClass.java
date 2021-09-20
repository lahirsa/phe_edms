package beans;

import com.utils.ADFUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelTabbed;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.AttributeChangeEvent;
import org.apache.myfaces.trinidad.event.DisclosureEvent;
import org.apache.myfaces.trinidad.event.ReturnEvent;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;

import java.io.FileOutputStream;
import java.io.InputStream;

import java.sql.Date;

import java.sql.ResultSet;
import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.Calendar;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.FacesMessage;

import javax.faces.component.UIComponent;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.share.ADFContext;

import oracle.binding.AttributeBinding;

import oracle.jbo.RowSetIterator;

import oracle.jbo.ValidationException;
import oracle.jbo.server.ViewObjectImpl;

import oracle.stellent.ridc.IdcClientException;
import oracle.stellent.ridc.model.DataBinder;
import oracle.stellent.ridc.model.DataObject;

import org.apache.myfaces.trinidad.component.UIXTree;
import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.ComponentReference;
import org.apache.myfaces.trinidad.util.Service;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class requestClass {
    private RichSelectOneChoice dropDownViaBind;
    private String currentAddress;
    private String weblogicusername;
    private String weblogicpassword;
    private RichTable bindTable;
    private String Userlogin;
    private String UserloginFullName;
    private String idrequest;
    private String tableStatus;
    private String RemarksRewrite;
    private String contractorRewrite;
    private String contractorUser;
    private String countList;
    private RichButton bindSaveDocRequest;
    private RichButton bindCreateInserDocList;
    private RichPanelTabbed bindInputList;
    private RichPanelSplitter bindPanelSplitter;
    private RichPanelGroupLayout bindTabPage;
    private RichPanelFormLayout bindForm;
    private RichButton bindNewInsert;
    private RichInputText bindSV;
    private RichInputText bindDocSource;
    private RichInputText bindDocStatus;
    private RichButton bindCancelInput;
    private RichTable bindTableCreateRequest;
    private RichButton bindCreateRequest;
    private RichPanelGroupLayout bindFromRequest;
    private RichOutputText bindOutput;
    private RichInputText bindTdcLead;
    private RichInputText bindStatusRequest;
    private RichInputText bindRequestor;
    private RichInputText bindIdRequestCreate;
    private RichInputText bindIdRequest;
    private RichTree bindTreeEdms;
    private RichTable bindTableFilesEDMS;
    private List<ChildFiles> childFiles = new ArrayList<ChildFiles>();
    private RichInputText bindSearchNameInternalUser;
    private RichButton btnSearchInternalUser;
    private RichTable bindTableInternalUser;
    private RichColumn bindSelectedInternalUsername;
    private RichPopup bindPopupInternalUser;
    private RichDialog dialogLsnrSelectInternalUser;
    private RichColumn bindSelectedInternalFullname;
    private RichInputText bindSelectSVFullname;
    private RichInputText bindSelectUserFullName;
    private RichInputText bindSelectUserEmail;
    private RichInputText bindSelectUserName;
    private RichInputText bindSVName;
    private RichInputText bindSVLabel;
    private RichInputText bindPurposeLabel;
    private RichButton bindSend;
    private RichOutputText bindTextStatusDocRequest;
    private RichPopup bindPopupCIDSend;
    private RichButton bindNextFlow;
    private RichPopup bindPopupNoItem;
    private RichPopup bindPopupSelectedItem;
    private RichInputText bindRequestCIDRequestorPosition;
    private RichPopup bindPopupFillForm;
    private RichPopup popupRequestCreated;
    private RichPopup bindPopupRequestCreated;
    private RichInputText bindRequestCIDPurpose;
    private RichInputText bindRequestCIDSVPosition;
    private RichInputDate bindRequestCIDDateRequest;
    private RichButton bindSubmitForm;
    String reqStatus = "";
    private RichButton bindUpdate;
    private RichButton bindSaveButton;
    private RichButton bindUpdateButton;
    private RichButton bindCreateButton;
    Log log = new Log();
    private RichInputText bindRemarksRequestor;
    private RichPanelCollection bindTableHistory;
    private Date date;
    private UploadedFile file;
    private long fileLength;
    private InputStream fileInputStream;
    private String fileName;
    private String fileContentType;
    private RichInputFile bindUploadFile;
    private String selectedEDMSFile;
    private String userSelected;
    private RichSelectBooleanRadio bindRadioEDMS;
    private ComponentReference bindEdmsDocNumber;
    private ComponentReference bindEdmsDocTitle;
    private RichPanelGroupLayout bindFormRequestEDMS;
    private RichInputText bindDidDocEDMS;
    private RichInputText bindDocNumberEDMS;
    private RichInputText bindDocTitleEDMS;
    private RichInputText bindDocSourceEDMS;
    private RichInputText bindStatusDocRequestEDMS;
    private RichInputFile bindAttachFile;
    private RichInputText bindAttachFormIdrequest;
    private RichInputDate bindAttachFormDate;
    private RichInputText bindAttachFormDID;
    private RichInputText bindAttachFormDocName;
    private RichInputText bindAttachFormDocFormat;
    private RichInputText bindAttachFormDocSize;
    private RichInputText bindAttachFormStatus;
    private RichInputText bindAttachFormFolderGuid;
    private RichPopup bindPopupInputAttach;
    private RichPopup bindPopupAttachInput;
    private RichInputText bindIdRequestEDMS;
    private RichTable bindTableAttach;
    private RichButton bindAttachAdd;
    private RichButton bindAttachUpload;
    private RichButton bindCancelInputRequest;
    private RichButton bindCancelEditRequest;
    private RichInputText bindAttachcoloum;
    private RichButton bindCancelInputRequestCID;
    private RichButton bindCancelEditRequestCID;
    private RichOutputText bindLabelUSer;
    private RichOutputText bindLabelUSer2;
    private RichInputText bindContractorRewrite;
    private RichInputText bindRemarksRewrite;
    private RichInputText bindUserContractor;
    private RichInputText bindInternalUser;
    private RichButton bindDeleteDocList;
    private RichButton bindEditDocList;
    private RichButton bindSendRequest;
    private RichButton bindFinishDocList;
    private RichButton bindAddtoList;
    private RichButton bindCreateRequestButton;
    private RichButton bindEditRequestButton;
    private RichButton bindDeleteRequestButton;
    private RichButton bindDocumentListButton;
    private RichPanelFormLayout bindFormCheckbox;
    private RichSelectBooleanCheckbox bindCheckbox;
    private RichPopup bindPopupAlreadyRequested;
    private RichTable bindTableDocRequest;
    private RichPanelFormLayout bindInputForm;
    private RichPopup bindPopupValidationDocList;
    private RichPopup bindPopupValidationDelList;
    private RichPopup bindPopupValidationDel;
    private RichPopup bindPopupValidationSend;
    private RichPopup bindPopupValidationDelCreate;
    private RichPopup bindPopupSend;
    private RichPopup bindpopupDeleteRequestList;
    ArrayList<String> validationDocList = new ArrayList<String>();
    private RichPopup bindPopupValidationEdit;
    private RichButton bindBackToReqList;
    private RichButton bindHome;
    private RichPanelTabbed bindTabInputType;
    private RichInputText bindDocNumNonEDMS;
    private RichInputText bindDocTitleNonEDMS;
    private RichTable bindTableRequest;
    private RichPanelFormLayout bindFormCreateRequest;
    private RichInputText bindAttachUploadForm;
    private RichButton bindSelectDocument;
    private RichPanelGroupLayout bindAddTolist;
    private RichPopup bindPopupNullSelect;
    private RichOutputText bindCountList;
    private RichOutputText doccount;
    private RichOutputText tyestboiuy;
    private RichOutputText sampah;
    private RichOutputText bindIteratorRowCount;
    private RichInputText bindApNamaCompany;
    private RichSelectOneChoice bindSocApName;
    private RichInputText bindApNama;
    private String IDC_SERVER;
    private String URL_CID;
    private String Url_Database;
    private String User_Database;
    private String Pass_Database;
    private String roleTdcReview;
    private RichButton tesBtn;
    private RichPopup pupdate;

    public requestClass() {
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();

        setCurrentAddress(getValueInDB("AppModuleDataControl", "PheConfigVO1",
                                       "KEY_CONFIG = 'RIDC_URL'", "KeyValue"));
        setWeblogicusername(getValueInDB("AppModuleDataControl",
                                         "PheConfigVO1",
                                         "KEY_CONFIG = 'WEBLOGIC_USER'",
                                         "KeyValue"));
        setWeblogicpassword(getValueInDB("AppModuleDataControl",
                                         "PheConfigVO1",
                                         "KEY_CONFIG = 'WEBLOGIC_PASS'",
                                         "KeyValue"));
        setIDC_SERVER(getValueInDB("AppModuleDataControl",
                                         "PheConfigVO1",
                                         "KEY_CONFIG = 'IDC_SERVER'",
                                         "KeyValue"));
        setURL_CID(getValueInDB("AppModuleDataControl",
                                         "PheConfigVO1",
                                         "KEY_CONFIG = 'URL_CID'",
                                         "KeyValue"));
        setUrl_Database(getValueInDB("AppModuleDataControl",
                                         "PheConfigVO1",
                                         "KEY_CONFIG = 'URL_DATABASE'",
                                         "KeyValue"));
        setUser_Database(getValueInDB("AppModuleDataControl",
                                         "PheConfigVO1",
                                         "KEY_CONFIG = 'USER_DATABASE'",
                                         "KeyValue"));
        setPass_Database(getValueInDB("AppModuleDataControl",
                                         "PheConfigVO1",
                                         "KEY_CONFIG = 'PASS_DATABASE'",
                                         "KeyValue"));
        
        try {
            Userlogin =request.getParameter("username");//"owc.admin";//"yudhi.widhiyana";//"owc.support";//request.getParameter("username");//"PHEH_User";//"PHEH_User";//request.getParameter("username");//"tdc_drawing";"NSO_Admin";"yudhi.widhiyana";//
            ApplicationModule am =
                ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
            ViewObject voRequestInput = am.findViewObject("PheApListVO1");
            voRequestInput.setNamedWhereClauseParam("dUser", Userlogin);
            voRequestInput.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Error get Username");
        }
        try {
            UserloginFullName =
                    getValueInDB("AppModuleDataControl", "UsersVO1",
                                 "DNAME = '" + Userlogin + "'", "Dfullname");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Error get fullname");
        }
        try {
            bindLabelUSer = new RichOutputText();
            bindLabelUSer2 = new RichOutputText();
            bindLabelUSer.setValue(UserloginFullName);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindLabelUSer);
            bindLabelUSer2.setValue(UserloginFullName);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindLabelUSer2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //main method
        //UserloginFullName = "Yudhi Widhiyana";
        //Userlogin = "yudhi.widhiyana";
        //UserloginFullName = "weblogic";
        //Userlogin = "weblogic";
        //UserloginFullName = "indra.fajar";
        //Userlogin = "indra.fajar";
        log.log("0", "Login User", UserloginFullName);
        ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        ViewObject voRequestInput = am.findViewObject("PheCidListrequestVO1");
        voRequestInput.setWhereClause("CIDREQUESTOR = '" + UserloginFullName +
                                      "' AND Cidstatusrequest <> 'Publish' AND Cidstatusrequest <> 'Approve TDC Lead'");
        voRequestInput.executeQuery();
        tableStatus = voRequestInput.getEstimatedRowCount() + "";
        refresh();
    }

    public Void createInput() {
        // tombol add to list di form input doc list, nambahin list di doc request
        bindFinishDocList.setVisible(true);
        bindFormRequestEDMS.setVisible(false);
        //log.log(idrequest, "remarks requestor",bindRemarksRequestor.getValue().toString());
        bindTabPage.setVisible(false);
        bindCancelEditRequestCID.setVisible(false); //Cancel Edit
        bindSaveDocRequest.setVisible(false); // Add To List

        AdfFacesContext.getCurrentInstance().addPartialTarget(bindFinishDocList);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindFormRequestEDMS);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTabPage);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCancelEditRequestCID);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSaveDocRequest);
        
        buttonDocListOn();
        refreshTable();
        return null;
    }

    public String createRequest() {
        bindCreateRequest.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCreateRequest);
        bindSaveButton.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSaveButton);
        bindUpdateButton.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindUpdateButton);
        bindCancelEditRequest.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCancelEditRequest);
        bindCancelInputRequest.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCancelInputRequest);
        
        // membuat request CID
        bindFromRequest.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindFromRequest);
        // rama , pakai yg di comment jg bs, cmn biar irit codingan pake yg bawahnya aj
        //        Row keyValue =
        //            ADFUtils.findIterator("tdcLead1Iterator").getCurrentRow();
        //        String tdcLead = keyValue.getAttribute("KeyValue").toString();
        /* */
        
        ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        ViewObject voRequestInput = am.findViewObject("PheApListVO1");
        voRequestInput.setNamedWhereClauseParam("dUser", Userlogin);
        voRequestInput.executeQuery();
        Row r=voRequestInput.first();
        
        if(voRequestInput.getEstimatedRowCount() >=1 ){
            String tdcCompany=r.getAttribute("Dattributename").toString();
            String tdclabel=getValueInDB("AppModuleDataControl", "PheConfigVO1", "UPPER(KEY_CONFIG) = UPPER('TDCLEAD_"+tdcCompany.substring(4).replaceAll("\\s+","")+"')",
                                     "KeyDisplay");
            String tdcLead =
                        getValueInDB("AppModuleDataControl", "PheConfigVO1", "UPPER(KEY_CONFIG) = UPPER('TDCLEAD_"+tdcCompany.substring(4).replaceAll("\\s+","")+"')",
                                     "KeyValue");
                   System.out.print("\ntdcLead = " + tdcLead + ' '+"nama AP = "+tdcCompany.substring(4).replaceAll("\\s+",""));
                    bindTdcLead.setValue(tdcLead); 
                    bindTdcLead.setLabel(tdclabel);
                    bindApNama.setValue(tdcCompany);
                    if(tdcCompany.substring(4).replaceAll("\\s+","").equalsIgnoreCase("ONWJ")){
                        setRoleTdcReview("TDC");
                    }else{
                        setRoleTdcReview(tdcCompany.substring(4).replaceAll("\\s+",""));
                    }
                    System.out.println("roleTdcReviewAp = "+getRoleTdcReview());
                 AdfFacesContext.getCurrentInstance().addPartialTarget(bindApNama);
        }
        
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSocApName);
        
        bindStatusRequest.setValue("Draft");
        bindRequestor.setValue(UserloginFullName);
        oracle.jbo.domain.Timestamp datetime =
            new oracle.jbo.domain.Timestamp(System.currentTimeMillis());
        bindRequestCIDDateRequest.setValue(datetime);
        //System.out.print(datetime);
        buttonReqListOFF();
        refreshTableRequest();

        return null;
    }

    public void refreshTable() {

        ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        ViewObject voDocInput = am.findViewObject("PheCidListdocrequestVO1");
        voDocInput.setWhereClause("IDREQUEST = '" + idrequest + "'");
        voDocInput.executeQuery();
        countList = voDocInput.getEstimatedRowCount() + "";

        try {
            bindCountList.setValue(countList);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindCountList);
        } catch (Exception e) {
        }

        System.out.print("\nIDREQUEST = '" + idrequest + "'");

        ViewObject voRequestInput = am.findViewObject("PheCidAttachmentVO1");
        voRequestInput.setWhereClause("IDREQUEST = '" + idrequest + "' ");
        voRequestInput.executeQuery();

        ViewObject voRequestLog = am.findViewObject("PheCidLogVO1");
        voRequestLog.setWhereClause("IDREQUEST = '" + idrequest +
                                    "' AND ACTION <> 'getValueInDB' AND ACTION <> 'Send Email'");
        voRequestLog.executeQuery();
        //AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableHistory);
        refresh();
    }

    public void refreshTableRequest() {
        idrequest = bindIdRequest.getValue().toString();
        ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        ViewObject voDocInput = am.findViewObject("PheCidListdocrequestVO1");
        voDocInput.setWhereClause("IDREQUEST = '" + idrequest + "'");
        voDocInput.executeQuery();

        countList = voDocInput.getEstimatedRowCount() + "";

        try {
            bindCountList.setValue(countList);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindCountList);
        } catch (Exception e) {
        }

        System.out.print("\nIDREQUEST = '" + idrequest + "'");

        ViewObject voRequestInput = am.findViewObject("PheCidAttachmentVO1");
        voRequestInput.setWhereClause("IDREQUEST = '" + idrequest + "' ");
        voRequestInput.executeQuery();

        ViewObject voRequestLog = am.findViewObject("PheCidLogVO1");
        voRequestLog.setWhereClause("IDREQUEST = '" + idrequest +
                                    "' AND ACTION <> 'getValueInDB' AND ACTION <> 'Send Email'");
        //voRequestInput.setWhereClause("upper(action) like upper('%remarks%') or upper(action) like upper('%approval%')");
        voRequestLog.executeQuery();
        //AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableHistory);
        refresh();

    }

    public String inputList() {
        //validasiInputDoc
        System.out.print("===Testing====");
        DCIteratorBinding dci =
            ADFUtils.findIterator("PheCidListdocrequestVO1Iterator");
        System.out.println("DCI COUNT: " + dci.getEstimatedRowCount());

        //proses simpen temporary di PageFlowScope
        ADFContext actx = ADFContext.getCurrent();
        Map pfscount = actx.getPageFlowScope();
        Object var = pfscount.put("doccount", dci.getEstimatedRowCount());
        
        long a = dci.getEstimatedRowCount();
        String isBlocked = "true";
        if (a >= 109) {
            isBlocked = "false";
        } else {
            isBlocked = "true";
        }

        if (isBlocked.equalsIgnoreCase("true")) {
            
            Row r=dci.getNavigatableRowIterator().createRow();
            r.setNewRowState(r.STATUS_INITIALIZED);
           
            /*  BindingContainer bindings1 =
                BindingContext.getCurrent().getCurrentBindingsEntry();
            OperationBinding operationbinding =
                bindings1.getOperationBinding("CreateInsert");
            operationbinding.execute(); */
            String Cidstatusrequest =
                getValueInDB("AppModuleDataControl", "PheCidListrequestVO1",
                             "Idrequest= '" + idrequest + "'",
                             "Cidstatusrequest");
            if ((Cidstatusrequest.equals("Draft"))) {
                buttonDocListOFF();
                bindIteratorRowCount.setVisible(false);
                bindUpdate.setVisible(false);
                bindSaveDocRequest.setVisible(false);
                bindAddtoList.setVisible(true);
                bindCancelInputRequestCID.setVisible(true);
                bindCancelEditRequestCID.setVisible(false);
                bindTabPage.setVisible(true);
                bindDocSource.setValue("Non EDMS");
                bindDocStatus.setValue("Draft");
                bindIdRequestCreate.setValue(idrequest);
                bindAddtoList.setDisabled(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindIteratorRowCount);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindUpdate);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindSaveDocRequest);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindAddtoList);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindCancelInputRequestCID);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindCancelEditRequestCID);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindTabPage);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocSource);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocStatus);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindIdRequestCreate);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindAddtoList);
                dci.getRowSetIterator().insertRow(r);
                try{ 
                    BindingContainer bindings = getBindings();
                    OperationBinding operationBinding =
                        bindings.getOperationBinding("Commit");
                    operationBinding.execute();
                }catch (Exception e){
                    e.printStackTrace();
                }
             
            } else {
                RichPopup.PopupHints ph = new RichPopup.PopupHints();
                bindPopupValidationDocList.show(ph);
                refreshTable();
            }
        } else {
            BindingContainer bindings1 =
                BindingContext.getCurrent().getCurrentBindingsEntry();
            OperationBinding operationbinding =
                bindings1.getOperationBinding("Rollback");
            operationbinding.execute();

            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage fm =
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Document sudah terinput sebanyak 109, Document berikutnya harap membuat CID baru.",
                                 "");
            ctx.addMessage(null, fm);
        }
        // bindNewInsert.setVisible(false);

        //debug hasil print count list
        //String test = pfscount.get("doccount") == null ? "" : pfscount.get("doccount").toString();
        //System.out.println("****### "+test);
        return null;
    }
    
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    public static ViewObjectImpl getViewObjectFromIterator(String nomIterator) {
        ViewObjectImpl returnVO = null;
        DCBindingContainer dcb = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        if (dcb != null) {
            DCIteratorBinding iter = dcb.findIteratorBinding(nomIterator);
            if (iter != null) {
                returnVO = (ViewObjectImpl)iter.getViewObject();
            }
        }
        return returnVO;
    }


    private void commit(String IteratorName) {
        ViewObject vo = this.getViewObjectFromIterator(IteratorName);
        try {
            vo.getApplicationModule().getTransaction().validate();
            vo.getApplicationModule().getTransaction().commit();
        } catch (ValidationException e) {
            String validationErrorMessage = e.getDetailMessage();
            //Occur when some committed data is rejected due to validation error.
            //log it : log(Level.WARNING, " " + validationErrorMessage);
        }
        catch (Exception e) {
            //Log it and warn something unexpected occured
        }
    }


    public void buttonDocListOFF() {
        bindBackToReqList.setVisible(false);
        bindCreateInserDocList.setVisible(false);
        bindEditDocList.setVisible(false);
        bindDeleteDocList.setVisible(false);
        bindSendRequest.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindBackToReqList);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCreateInserDocList);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEditDocList);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDeleteDocList);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSendRequest);
    }

    public void buttonDocListOn() {
        bindSelectDocument.setVisible(true);
        bindBackToReqList.setVisible(true);
        bindCreateInserDocList.setVisible(true);
        bindEditDocList.setVisible(true);
        bindDeleteDocList.setVisible(true);
        bindSendRequest.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSelectDocument);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindBackToReqList);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCreateInserDocList);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEditDocList);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDeleteDocList);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSendRequest);
    }

    public void buttonReqListOn() {
        bindHome.setVisible(true);
        bindCreateRequestButton.setVisible(true);
        bindEditRequestButton.setVisible(true);
        bindDeleteRequestButton.setVisible(true);
        bindDocumentListButton.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindHome);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCreateRequestButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEditRequestButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDeleteRequestButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocumentListButton);
    }

    public void buttonReqListOFF() {
        bindHome.setVisible(false);
        bindCreateRequestButton.setVisible(false);
        bindEditRequestButton.setVisible(false);
        bindDeleteRequestButton.setVisible(false);
        bindDocumentListButton.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindHome);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCreateRequestButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEditRequestButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDeleteRequestButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocumentListButton);
    }

    public void getValue(ActionEvent actionEvent) {
        //Add event code here...
        bindOutput.setValue(bindIdRequest.getValue().toString());
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindOutput);
        System.out.print(actionEvent);
    }

    public String cancelInput() {
        bindTabPage.setVisible(false);
        bindCreateInserDocList.setVisible(true);
        bindCancelEditRequestCID.setVisible(false); //Cancel Edit
        bindSaveDocRequest.setVisible(false); // Add To List
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTabPage);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCreateInserDocList);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCancelEditRequestCID);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSaveDocRequest);
        buttonDocListOn();
        refreshTable();
        // Add event code here...
        return null;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 30);
        date = (Date)cal.getTime();
        return date;
    }

    public void setDropDownViaBind(RichSelectOneChoice dropDownViaBind) {
        this.dropDownViaBind = dropDownViaBind;
    }

    public RichSelectOneChoice getDropDownViaBind() {
        return dropDownViaBind;
    }

    public void setBindTable(RichTable bindTable) {
        this.bindTable = bindTable;
    }

    public RichTable getBindTable() {
        return bindTable;
    }

    public void setBindSaveDocRequest(RichButton bindSaveDocRequest) {
        this.bindSaveDocRequest = bindSaveDocRequest;
    }

    public RichButton getBindSaveDocRequest() {
        return bindSaveDocRequest;
    }

    public void setBindCreateInserDocList(RichButton bindCreateInserDocList) {
        this.bindCreateInserDocList = bindCreateInserDocList;
    }

    public RichButton getBindCreateInserDocList() {
        return bindCreateInserDocList;
    }

    public void setBindInputList(RichPanelTabbed bindInputList) {
        this.bindInputList = bindInputList;
    }

    public RichPanelTabbed getBindInputList() {
        return bindInputList;
    }

    public void setBindPanelSplitter(RichPanelSplitter bindPanelSplitter) {
        this.bindPanelSplitter = bindPanelSplitter;
    }

    public RichPanelSplitter getBindPanelSplitter() {
        return bindPanelSplitter;
    }

    public void setBindTabPage(RichPanelGroupLayout bindTabPage) {
        this.bindTabPage = bindTabPage;
    }

    public RichPanelGroupLayout getBindTabPage() {
        return bindTabPage;
    }

    public void setBindForm(RichPanelFormLayout bindForm) {
        this.bindForm = bindForm;
    }

    public RichPanelFormLayout getBindForm() {
        return bindForm;
    }

    public void setBindNewInsert(RichButton bindNewInsert) {
        this.bindNewInsert = bindNewInsert;
    }

    public RichButton getBindNewInsert() {
        return bindNewInsert;
    }

    public void setBindSV(RichInputText bindSV) {
        this.bindSV = bindSV;
    }

    public RichInputText getBindSV() {
        return bindSV;
    }

    public void setBindDocSource(RichInputText bindDocSource) {
        this.bindDocSource = bindDocSource;
    }

    public RichInputText getBindDocSource() {
        return bindDocSource;
    }

    public void setBindDocStatus(RichInputText bindDocStatus) {
        this.bindDocStatus = bindDocStatus;
    }

    public RichInputText getBindDocStatus() {
        return bindDocStatus;
    }

    public void setBindCancelInput(RichButton bindCancelInput) {
        this.bindCancelInput = bindCancelInput;
    }

    public RichButton getBindCancelInput() {
        return bindCancelInput;
    }

    public void setBindTableCreateRequest(RichTable bindTableCreateRequest) {
        this.bindTableCreateRequest = bindTableCreateRequest;
    }

    public RichTable getBindTableCreateRequest() {
        return bindTableCreateRequest;
    }

    public void setBindCreateRequest(RichButton bindCreateRequest) {
        this.bindCreateRequest = bindCreateRequest;
    }

    public RichButton getBindCreateRequest() {
        return bindCreateRequest;
    }

    public void setBindFromRequest(RichPanelGroupLayout bindFromRequest) {
        this.bindFromRequest = bindFromRequest;
    }

    public RichPanelGroupLayout getBindFromRequest() {
        return bindFromRequest;
    }

    public String cancelRequest() {
        // Add event code here...
        bindFromRequest.setVisible(false);
        buttonReqListOn();
        return null;
    }

    public void setBindOutput(RichOutputText bindOutput) {
        this.bindOutput = bindOutput;
    }

    public RichOutputText getBindOutput() {
        return bindOutput;
    }

    public void setBindTdcLead(RichInputText bindTdcLead) {
        this.bindTdcLead = bindTdcLead;
    }

    public RichInputText getBindTdcLead() {
        return bindTdcLead;
    }

    public void setBindStatusRequest(RichInputText bindStatusRequest) {
        this.bindStatusRequest = bindStatusRequest;
    }

    public RichInputText getBindStatusRequest() {
        return bindStatusRequest;
    }

    public void setBindRequestor(RichInputText bindRequestor) {
        this.bindRequestor = bindRequestor;
    }

    public RichInputText getBindRequestor() {
        return bindRequestor;
    }

    public void setBindIdRequestCreate(RichInputText bindIdRequestCreate) {
        this.bindIdRequestCreate = bindIdRequestCreate;
    }

    public RichInputText getBindIdRequestCreate() {
        return bindIdRequestCreate;
    }

    public void setBindIdRequest(RichInputText bindIdRequest) {
        this.bindIdRequest = bindIdRequest;
    }

    public RichInputText getBindIdRequest() {
        return bindIdRequest;
    }

    public void nextFlow(ActionEvent actionEvent) {
        
        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        if (tableStatus.equals("0")) {
            bindPopupNoItem.show(ph);
        } else {
            bindPopupSelectedItem.show(ph);
            refreshTableRequest();
        }
        System.out.print(actionEvent);
    }

    public void setBindTreeEdms(RichTree bindTreeEdms) {
        this.bindTreeEdms = bindTreeEdms;
    }

    public RichTree getBindTreeEdms() {
        return bindTreeEdms;
    }

    public void setSelectedEDMSFile(String selectedEDMSFile) {
        this.selectedEDMSFile = selectedEDMSFile;
    }

    public String getSelectedEDMSFile() {
        return selectedEDMSFile;
    }

    public void OnChangeRadio(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue().toString().equalsIgnoreCase("true")) {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            Map p =
                ((UIComponent)valueChangeEvent.getSource()).getAttributes();
            System.out.println("rowvalIndex = " +
                               p.get("rowvalIndex").toString());
            setSelectedEDMSFile(p.get("rowvalIndex").toString());
        }
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

    public RichInputText getBindEdmsDocNumber() {
        if (bindEdmsDocNumber != null) {
            return (RichInputText)bindEdmsDocNumber.getComponent();
        }
        return null;
    }

    private static Object evaluateEL(String el) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory =
            (ExpressionFactory)facesContext.getApplication().getExpressionFactory();
        ValueExpression exp =
            expressionFactory.createValueExpression(elContext, el,
                                                    Object.class);
        return exp.getValue(elContext);
    }

    public void ActionSelectEdms(ActionEvent actionEvent) {
        String dID = "";
        bindAddtoList.setDisabled(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindAddtoList);
        try {
            dID =
                childFiles.get(Integer.parseInt(getSelectedEDMSFile())).getDID() + "";
        } catch (Exception e) {
        }
        if (dID.equals("")) {
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            bindPopupNullSelect.show(ph);
        } else {
            String result = select(dID, idrequest);
            System.out.println("sudah ada atau belum = " + result);
            if (result.equals("")) {
                BindingContainer bindings =
                    BindingContext.getCurrent().getCurrentBindingsEntry();
                OperationBinding operationBinding =
                    bindings.getOperationBinding("CreateInsert");
                operationBinding.execute();
         
                bindFormRequestEDMS.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindFormRequestEDMS);
                bindStatusDocRequestEDMS.setValue("Draft");
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindStatusDocRequestEDMS);
                bindIdRequestEDMS.setValue(idrequest);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindIdRequestEDMS);
                bindDidDocEDMS.setValue(childFiles.get(Integer.parseInt(getSelectedEDMSFile())).getDID());
                bindDocNumberEDMS.setValue(childFiles.get(Integer.parseInt(getSelectedEDMSFile())).getXDocNumber());
                bindDocTitleEDMS.setValue(childFiles.get(Integer.parseInt(getSelectedEDMSFile())).getDDocTitle());
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindDidDocEDMS);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocNumberEDMS);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocTitleEDMS);
                bindDocSourceEDMS.setValue("EDMS : " +
                                           childFiles.get(Integer.parseInt(getSelectedEDMSFile())).getXDocPurpose());
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocSourceEDMS);
                try {
                    /* bindContractorRewrite.setSubmittedValue(contractorRewrite);
                    bindRemarksRewrite.setSubmittedValue(RemarksRewrite);
                    bindUserContractor.setSubmittedValue(contractorUser); */
                    bindContractorRewrite.setSubmittedValue("");
                    bindRemarksRewrite.setSubmittedValue("");
                    bindUserContractor.setSubmittedValue("");
                } catch (Exception e) {
                }
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindUserContractor);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindContractorRewrite);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindRemarksRewrite);

                System.out.print("\n selected EDMS XDocNumber file = " +
                                 childFiles.get(Integer.parseInt(getSelectedEDMSFile())).getXDocNumber());
                System.out.print("\n selected EDMS DDocName file = " +
                                 childFiles.get(Integer.parseInt(getSelectedEDMSFile())).getDDocName());
                System.out.print("\n selected EDMS XDocName file = " +
                                 childFiles.get(Integer.parseInt(getSelectedEDMSFile())).getXDocName());
                System.out.print("\n selected EDMS DID file = " +
                                 childFiles.get(Integer.parseInt(getSelectedEDMSFile())).getDID());
                
            } else {
                RichPopup.PopupHints ph = new RichPopup.PopupHints();
                bindPopupAlreadyRequested.show(ph);
            }
        }
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
            System.out.println("id ffolderguid = "+ID);
            RIDCClass ridc =
                new RIDCClass(getWeblogicusername(), getWeblogicpassword());
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
                                                      object.get("xDocPurpose").toString(),
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

    public void setChildFiles(List<ChildFiles> childFiles) {
        this.childFiles = childFiles;
    }

    public List<ChildFiles> getChildFiles() {
        return childFiles;
    }

    public HttpSession GetSession() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest request =
            (HttpServletRequest)ctx.getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        return session;
    }

    public void setBindSearchNameInternalUser(RichInputText bindSearchNameInternalUser) {
        this.bindSearchNameInternalUser = bindSearchNameInternalUser;
    }

    public RichInputText getBindSearchNameInternalUser() {
        return bindSearchNameInternalUser;
    }

    public void setBtnSearchInternalUser(RichButton btnSearchInternalUser) {
        this.btnSearchInternalUser = btnSearchInternalUser;
    }

    public RichButton getBtnSearchInternalUser() {
        return btnSearchInternalUser;
    }

    public void searchInternalUser(ActionEvent actionEvent) {
        ApplicationModule app =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        ViewObject voParIn = app.findViewObject("UserInternalVO1");
        voParIn.setWhereClause("upper(FULLNAME) like upper('%" +
                               bindSearchNameInternalUser.getValue().toString() +
                               "%')");
        voParIn.executeQuery();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableInternalUser);
    }

    public void setBindTableInternalUser(RichTable bindTableInternalUser) {
        this.bindTableInternalUser = bindTableInternalUser;
    }

    public RichTable getBindTableInternalUser() {
        return bindTableInternalUser;
    }

    public void setBindSelectedInternalUsername(RichColumn bindSelectedInternalUsername) {
        this.bindSelectedInternalUsername = bindSelectedInternalUsername;
    }

    public RichColumn getBindSelectedInternalUsername() {
        return bindSelectedInternalUsername;
    }

    public void setBindPopupInternalUser(RichPopup bindPopupInternalUser) {
        this.bindPopupInternalUser = bindPopupInternalUser;
    }

    public RichPopup getBindPopupInternalUser() {
        return bindPopupInternalUser;
    }

    public void setDialogLsnrSelectInternalUser(RichDialog dialogLsnrSelectInternalUser) {
        this.dialogLsnrSelectInternalUser = dialogLsnrSelectInternalUser;
    }

    public RichDialog getDialogLsnrSelectInternalUser() {
        return dialogLsnrSelectInternalUser;
    }

    public void DialogLsnrSelectInternalUser(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            bindSVName.setValue(bindSelectUserFullName.getValue().toString());
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindSVName);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableInternalUser);
            
        }
    }

    public void email(String EmailTo, String Subject, String HtmlText) {
        BindingContext bindingctx = BindingContext.getCurrent();
        System.out.print("\nbindingctx = " +
                         bindingctx.getCurrentDataControlFrame().toString());
        BindingContainer bindings = bindingctx.getCurrentBindingsEntry();
        OperationBinding sendEmail = bindings.getOperationBinding("SendEmail");
        System.out.print("\nsendEmail = " + sendEmail);
        Map paramEmail = sendEmail.getParamsMap();
        paramEmail.put("EmailTo", EmailTo);
        paramEmail.put("Subject", Subject);
        paramEmail.put("HtmlText", HtmlText);
        try {
            sendEmail.execute();//dimatikan dulu
            log.log(idrequest, "Send Email",
                    "Sender : " + UserloginFullName + ", Reciever : " +
                    EmailTo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void emailWithCC(String EmailTo, String EmailCc, String Subject,
                            String HtmlText) {
        BindingContext bindingctx = BindingContext.getCurrent();
        System.out.print("\nbindingctx = " +
                         bindingctx.getCurrentDataControlFrame().toString());
        BindingContainer bindings = bindingctx.getCurrentBindingsEntry();
        OperationBinding sendEmail =
            bindings.getOperationBinding("SendEmailTo");
        System.out.print("\nsendEmail = " + sendEmail);
        Map paramEmail = sendEmail.getParamsMap();
        paramEmail.put("EmailTo", EmailTo);
        paramEmail.put("EmailCc", EmailCc);
        paramEmail.put("Subject", Subject);
        paramEmail.put("HtmlText", HtmlText);
        try {
            sendEmail.execute();//dimatikan dulu
            log.log(idrequest, "Send Email",
                    "Sender : " + UserloginFullName + ", Reciever : " +
                    EmailTo + ", CC : " + EmailCc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void btnSearchInternalUser(ActionEvent actionEvent) {
        // Add event code here...
        System.out.print(actionEvent);
    }

    public void setBindSelectedInternalFullname(RichColumn bindSelectedInternalFullname) {
        this.bindSelectedInternalFullname = bindSelectedInternalFullname;
    }

    public RichColumn getBindSelectedInternalFullname() {
        return bindSelectedInternalFullname;
    }

    public void setBindSelectSVFullname(RichInputText bindSelectSVFullname) {
        this.bindSelectSVFullname = bindSelectSVFullname;
    }

    public RichInputText getBindSelectSVFullname() {
        return bindSelectSVFullname;
    }

    public void setBindSelectUserFullName(RichInputText bindSelectUserFullName) {
        this.bindSelectUserFullName = bindSelectUserFullName;
    }

    public RichInputText getBindSelectUserFullName() {
        return bindSelectUserFullName;
    }

    public void setBindSelectUserEmail(RichInputText bindSelectUserEmail) {
        this.bindSelectUserEmail = bindSelectUserEmail;
    }

    public RichInputText getBindSelectUserEmail() {
        return bindSelectUserEmail;
    }

    public void setBindSelectUserName(RichInputText bindSelectUserName) {
        this.bindSelectUserName = bindSelectUserName;
    }

    public RichInputText getBindSelectUserName() {
        return bindSelectUserName;
    }

    public void setBindSVName(RichInputText bindSVName) {
        this.bindSVName = bindSVName;
    }

    public RichInputText getBindSVName() {
        return bindSVName;
    }

    public void setBindSVLabel(RichInputText bindSVLabel) {
        this.bindSVLabel = bindSVLabel;
    }

    public RichInputText getBindSVLabel() {
        return bindSVLabel;
    }

    public void setBindPurposeLabel(RichInputText bindPurposeLabel) {
        this.bindPurposeLabel = bindPurposeLabel;
    }

    public RichInputText getBindPurposeLabel() {
        return bindPurposeLabel;
    }

    public void GoToHome(ActionEvent actionEvent) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            ctx.getExternalContext().redirect(getIDC_SERVER()+"?IdcService=GET_DOC_PAGE&Action=GetTemplatePage&Page=HOME_PAGE");
            //    ctx.getExternalContext().redirect("http://phekpowcdev.pertamina.com:16200/urm/idcplg?IdcService=GET_DOC_PAGE&Action=GetTemplatePage&Page=HOME_PAGE");

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(actionEvent);
    }

    public void gotoInbox() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            // ctx.getExternalContext().redirect("http://kponwjap005.pertamina.com:7070/CID/faces/pages/request.jspx?username=" +Userlogin);
            ctx.getExternalContext().redirect(getURL_CID()+"/CID/faces/pages/request.jspx?username=" +
                                              Userlogin);
            // ctx.getExternalContext().redirect("http://127.0.0.1:7101/CID/faces/pages/request.jspx");
        } catch (IOException e) {
            e.printStackTrace();
        }
        refreshTable();
    }

    public void GoToCID(ActionEvent actionEvent) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            ctx.getExternalContext().redirect(getURL_CID()+"/CID/faces/pages/request.jspx?username=" +
                                              Userlogin);
            //ctx.getExternalContext().redirect("http://kponwjis013.pertamina.com:7070/CID/faces/pages/request.jspx?username=" +Userlogin);
            // ctx.getExternalContext().redirect("http://127.0.0.1:7101/CID/faces/pages/request.jspx");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(actionEvent);
    }

    public void setBindSend(RichButton bindSend) {
        this.bindSend = bindSend;
    }

    public RichButton getBindSend() {
        return bindSend;
    }

    public void sendRequest(ActionEvent actionEvent) {
        System.out.print(actionEvent);
        String docStatus = "";
        String status =
            getValueInDB("AppModuleDataControl", "PheCidListrequestVO1",
                         "IDREQUEST = '" + idrequest + "'",
                         "Cidstatusrequest");
        if ((status.equals("TDC Review")) ||
            (status.equals("Approve TDC Lead")) ||
            (status.equals("Approve SPV")) ||
            (status.equals("Requested")) ||
            (status.equals("Reject TDC Lead With Remarks"))) {
            if(status.equals("Reject TDC Lead With Remarks")){
                update("req", "Requested",idrequest); 
                update("doc", "Requested", idrequest);
                emailSubmit("single", "Cidrequestorsupervisor");
//                RichPopup.PopupHints ph = new RichPopup.PopupHints();
//                bindPopupSend.show(ph);
            }
            bindSendRequest.setVisible(false);
            refreshTable();
        } else if ((status.equals("Draft")) ||
            (status.equals("Reject SPV With Remarks"))) {
            if (status.equals("Draft")) {
                update("req", "Requested",
                       idrequest); //saat pertama kali buat request CID
                       RichPopup.PopupHints ph = new RichPopup.PopupHints();
                       bindPopupSend.show(ph);
            }
            try {
                docStatus = bindDocStatus.getValue().toString();
                System.out.print("\ndocStatus = " + docStatus);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if ((docStatus.equals("Draft")) ||
                (docStatus.equals("Reject SPV With Remarks"))) {
                update("doc", "Requested", idrequest);
                log.log(idrequest, "Update doc",
                        "User : " + UserloginFullName + " - " +
                        "(Reject SPV With Remarks) or (Draft) change to (Requested)");
                RichPopup.PopupHints ph = new RichPopup.PopupHints();
                bindPopupSend.show(ph);
            }
            if ((reqStatus.equals("Draft")) ||
                (reqStatus.equals("Reject SPV With Remarks"))) {
                update("req", "Requested", idrequest);
                log.log(idrequest, "Update Request",
                        "User : " + UserloginFullName + " - " +
                        "(Reject SPV With Remarks) or (Draft) change to (Requested)");
                RichPopup.PopupHints ph = new RichPopup.PopupHints();
                bindPopupSend.show(ph);
            }
            log.log(idrequest, "Request Send",
                    "User : " + UserloginFullName + " - " +
                    "Request CID has been requested");
            commit();
            refresh();
            String statusRequest =
                getValueInDB("AppModuleDataControl", "PheCidListrequestVO1",
                             "IDREQUEST = '" + idrequest + "'",
                             "Cidstatusrequest");
            if ((statusRequest.equals("Requested")) ||
                (statusRequest.equals("Reject SPV With Remarks"))) {
                emailSubmit("single", "Cidrequestorsupervisor");
                RichPopup.PopupHints ph = new RichPopup.PopupHints();
                bindPopupSend.show(ph);
            } else if ((statusRequest.equals("Approve SPV")) ||
                       (statusRequest.equals("Reject TDC Review With Remarks"))) {
                ApplicationModule am =
                    ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
                ViewObject voUserTDCReview =
                    am.findViewObject("UsersecurityattributesVO1");
                voUserTDCReview.setWhereClause("upper (dattributetype)=upper('role') and upper(dattributename)=upper('CID_"+getRoleTdcReview().trim()+"_REVIEW')");
                voUserTDCReview.executeQuery();
                // String countUserTDCReview = voUserTDCReview.getEstimatedRowCount() + "";
                String first = "";
                String tdcDusername = "";
                String tdcFullname = "";
                while (voUserTDCReview.hasNext()) {
                    Row row = null;
                    if (first.equals("")) {
                        row = voUserTDCReview.first();
                        first = "1";
                    } else {
                        row = voUserTDCReview.next();
                    }
                    try {
                        tdcDusername =
                                row.getAttribute("Dusername").toString();
                        tdcFullname =
                                getValueInDB("AppModuleDataControl", "UsersVO1",
                                             "DNAME = '" + tdcDusername + "'",
                                             "Dfullname");
                        emailSubmit("multiple", tdcFullname);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                // untuk TDC Review harus define dengan banyak user
            } else if ((statusRequest.equals("Approve SPV")) ||
                       (statusRequest.equals("TDC Review")) ||
                       (statusRequest.equals("Reject TDC Lead With Remarks"))) {
                        if(statusRequest.equals("Reject TDC Lead With Remarks")){
                            update("req", "Requested",idrequest); 
                            update("doc", "Requested", idrequest);
                            emailSubmit("single", "Cidrequestorsupervisor");
                        }
                        
                    emailSubmit("single", "Cidtdclead");

                RichPopup.PopupHints ph = new RichPopup.PopupHints();
                bindPopupSend.show(ph);
            }
        }
    }

    public void emailSubmit(String people, String to) {
        String toUser = ""; // user full name
        if (people.equals("single")) {
            toUser =
                    getValueInDB("AppModuleDataControl", "PheCidListrequestVO1",
                                 "IDREQUEST = '" + idrequest + "'", to);
        } else {
            toUser = to;
        }
        String EmailAddress =
            getValueInDB("AppModuleDataControl", "UsersVO1", "DFULLNAME = '" +
                         toUser + "'", "Demail");
        String username =
            getValueInDB("AppModuleDataControl", "UsersVO1", "DFULLNAME = '" +
                         toUser + "'", "Dname");
        System.out.print("\nCidrequestorsupervisor = " + toUser);
        System.out.print("\nEmailAddress = " + EmailAddress);

        ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        ViewObject voGetPar = am.findViewObject("PheCidListdocrequestVO1");
        voGetPar.setWhereClause("IDREQUEST = '" + idrequest + "'");
        voGetPar.executeQuery();

        ViewObject voGetParReq = am.findViewObject("PheCidListrequestVO1");
        voGetParReq.setWhereClause("IDREQUEST = '" + idrequest + "'");
        voGetParReq.executeQuery();
        //Row rowReq = voGetParReq.first();
        String countDocList = voGetPar.getEstimatedRowCount() + "";

        String first = "";
        // String Ciddocnumber = "";
        String Ciddocname = "";
        String Ciddoctitle = "";
        String documents = "";
        String purpose = "";
        try {
            Row row = voGetParReq.first();
            purpose = row.getAttribute("Cidpurpose").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (countDocList.equals("1")) {
            Row row = voGetPar.first();
            try {
                //Ciddocnumber = row.getAttribute("Ciddocnumber").toString();
                String DID = row.getAttribute("Diddoc").toString();
                Ciddocname =
                        getValueInDB("AppModuleDataControl", "DocmetaVO1", "DID = '" +
                                     DID + "'", "Xdocname");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Ciddoctitle = row.getAttribute("Ciddoctitle").toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            documents =
                    "<tr><th style=\"background-color:gainsboro;\">" + Ciddocname +
                    "</th><th></th><th style=\"background-color:gainsboro;\">" +
                    Ciddoctitle + "</th></tr>";
        } else {
            while (voGetPar.hasNext()) {
                Row row = null;
                if (first.equals("")) {
                    row = voGetPar.first();
                    first = "1";
                } else {
                    row = voGetPar.next();
                }
                try {
                    //Ciddocnumber = row.getAttribute("Ciddocnumber").toString();
                    String DID = row.getAttribute("Diddoc").toString();
                    Ciddocname =
                            getValueInDB("AppModuleDataControl", "DocmetaVO1",
                                         "DID = '" + DID + "'", "Xdocname");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Ciddoctitle = row.getAttribute("Ciddoctitle").toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                documents =
                        documents + "<tr><th style=\"background-color:gainsboro;\">" +
                        Ciddocname +
                        "</th><th></th><th style=\"background-color:gainsboro;\">" +
                        Ciddoctitle + "</th></tr>";
            }
        }
        String body = "<html>\n" +
            "<head></head>\n" +
            "<body style=\"font-family:arial\">Dear Sir/Madam<br/><br/>You have received a CID request from <b>" +
            UserloginFullName +
            "</b>  for following approved document(s):<br/><br/>\n<table>" +
            "<tr><th style=\"text-align:center;background-color:khaki;padding:8px 10px;\" align=\"center\">Document Name</th><th></th><th style=\"text-align:center;background-color:khaki;padding:8px 10px;\" align=\"center\">Document Title</th></tr>" +
            documents + "</table><br/><br/>" +
            "Please see click this <a href=" +getIDC_SERVER()+
            "?IdcService=PHE_LIST_CID&startRow=1&endRow=100&keyword=request&username=" +
            username + "&numcid=" +
            // "http://kponwjis013.pertamina.com:7070/CID/faces/pages/inboxApproval.jspx?username=" +
            //"http://kponwjap005.pertamina.com:7070/CID/faces/pages/inboxApproval.jspx?username="
            //username +
            ">link</a> to open CID request file(s) into the system." +
            "<br/>Thank you for your attention.</body></html>";


        email(EmailAddress, "Request CID : " + purpose, body);
        //bikin email body
    }
    
    public String getValueInDB2(String module, String datacontrolVO,
                               String whereClause) {
        ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl(module);
        ViewObject svname = am.findViewObject(datacontrolVO);
        svname.setNamedWhereClauseParam("NAMA",whereClause);
        svname.executeQuery();
        String value = "";
            if(svname.getEstimatedRowCount()>0){
                value = "ada";
            }else{
                value = "tidakada";
            }
        return value;
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

    public void setBindTextStatusDocRequest(RichOutputText bindTextStatusDocRequest) {
        this.bindTextStatusDocRequest = bindTextStatusDocRequest;
    }

    public RichOutputText getBindTextStatusDocRequest() {
        return bindTextStatusDocRequest;
    }

    public void update(String method, String status, String idrequest) {
        Connection dbCon = null;
        Statement stat = null;
        String UpdateQuery = "";
        //ResultSet result = null;
        try {
            //DEV
            dbCon =
            DriverManager.getConnection(getUrl_Database(),getUser_Database(), getPass_Database());
//                    DriverManager.getConnection("jdbc:oracle:thin:@PHEKPDBDV09.pertamina.com:1521:owc12cdv",
//                                                "DEVX_OCS", "Pertaminahe19");

            //dbCon = DriverManager.getConnection(
            //      "jdbc:oracle:thin:@PHEKPDBDV09.pertamina.com:1521:owc12cdv",
            //    "owc_staging", "staging2014owc");
        } catch (SQLException e) {
            e.printStackTrace();
            if (dbCon != null) {
                System.out.println("Connected to Database!");
            } else {
                System.out.println("Failed to make connection!");
            }
            return;
        }

        try {
            if (method.equals("doc")) {
                UpdateQuery =
                        "update phe_cid_listdocrequest set cidstatusdocrequest='" +
                        status + "' where idrequest=" + idrequest;
            } else if (method.equals("req")) {
                UpdateQuery =
                        "update phe_cid_listrequest set cidstatusrequest='" +
                        status + "' where idrequest=" + idrequest;
            } else if (method.equals("attach")) {
                UpdateQuery =
                        "update phe_cid_create set attachphysical='" + status +
                        "' where idrequest=" + idrequest;
            } else if (method.equals("publish")) {
                UpdateQuery =
                        "update phe_cid_create set cidstatuscid='" + status +
                        "' where idrequest=" + idrequest;
            } else if (method.equals("publishRequest")) {
                UpdateQuery =
                        "update phe_cid_listrequest set cidstatusrequest='" +
                        status + "' where idrequest=" + idrequest;
            }
            System.out.println("\nQuery = " + UpdateQuery);
            stat = dbCon.createStatement();
            stat.executeUpdate(UpdateQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stat.close();
            dbCon.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String select(String Diddoc, String idrequest) {
        Connection dbCon = null;
        Statement stat = null;
        String SelectQuery = "";
        String value = "";
        ResultSet result = null;
        try {
            //DEV
            dbCon = DriverManager.getConnection(getUrl_Database(),getUser_Database(), getPass_Database());
//                    DriverManager.getConnection("jdbc:oracle:thin:@PHEKPDBDV09.pertamina.com:1521:owc12cdv",
//                                                "DEVX_OCS", "Pertaminahe19");

            //dbCon = DriverManager.getConnection(
            //      "jdbc:oracle:thin:@PHEKPDBDV09.pertamina.com:1521:owc12cdv",
            //    "owc_staging", "staging2014owc");
        } catch (SQLException e) {
            e.printStackTrace();
            if (dbCon != null) {
                System.out.println("Connected to Database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        }

        try {
            SelectQuery =
                    "select idlist as IDLIST from phe_cid_listdocrequest where idRequest='" +
                    idrequest + "' and diddoc='" + Diddoc + "'";
            //System.out.println("\nQuery = " + SelectQuery);
            stat = dbCon.createStatement();
            result = stat.executeQuery(SelectQuery);
            while (result.next()) {
                value = result.getString("IDLIST") + "";
            }
            //System.out.println("value = "+value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            dbCon.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    public void setBindPopupCIDSend(RichPopup bindPopupCIDSend) {
        this.bindPopupCIDSend = bindPopupCIDSend;
    }

    public RichPopup getBindPopupCIDSend() {
        return bindPopupCIDSend;
    }

    public void refresh() {
        bindTable = new RichTable();
        bindTableCreateRequest = new RichTable();
        bindTableAttach = new RichTable();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableAttach);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTable);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableCreateRequest);
    }

    public void commit() {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding method = bindings.getOperationBinding("Commit");
        method.execute();
    }

    public String bindDeleteRequest() {
        System.out.print("===Testing====");
        DCIteratorBinding dci =
            ADFUtils.findIterator("PheCidListrequestVO1Iterator");
        System.out.println("DCI COUNT: " + dci.getEstimatedRowCount());
        
        ADFContext actx = ADFContext.getCurrent();
        Map pfscount = actx.getPageFlowScope();
        Object var = pfscount.put("doccount", dci.getEstimatedRowCount());
        
        commit();
        refresh();
        // Add event code here...
        return null;
    }

    public void setBindNextFlow(RichButton bindNextFlow) {
        this.bindNextFlow = bindNextFlow;
    }

    public RichButton getBindNextFlow() {
        return bindNextFlow;
    }

    public void setBindPopupNoItem(RichPopup bindPopupNoItem) {
        this.bindPopupNoItem = bindPopupNoItem;
    }

    public RichPopup getBindPopupNoItem() {
        return bindPopupNoItem;
    }

    public void setBindPopupSelectedItem(RichPopup bindPopupSelectedItem) {
        this.bindPopupSelectedItem = bindPopupSelectedItem;
    }

    public RichPopup getBindPopupSelectedItem() {
        return bindPopupSelectedItem;
    }

    public void setBindRequestCIDRequestorPosition(RichInputText bindRequestCIDRequestorPosition) {
        this.bindRequestCIDRequestorPosition = bindRequestCIDRequestorPosition;
    }

    public RichInputText getBindRequestCIDRequestorPosition() {
        return bindRequestCIDRequestorPosition;
    }

    public void setBindPopupFillForm(RichPopup bindPopupFillForm) {
        this.bindPopupFillForm = bindPopupFillForm;
    }

    public RichPopup getBindPopupFillForm() {
        return bindPopupFillForm;
    }

    public void setPopupRequestCreated(RichPopup popupRequestCreated) {
        this.popupRequestCreated = popupRequestCreated;
    }

    public RichPopup getPopupRequestCreated() {
        return popupRequestCreated;
    }

    public void setBindPopupRequestCreated(RichPopup bindPopupRequestCreated) {
        this.bindPopupRequestCreated = bindPopupRequestCreated;
    }

    public RichPopup getBindPopupRequestCreated() {
        return bindPopupRequestCreated;
    }

    public void setBindRequestCIDPurpose(RichInputText bindRequestCIDPurpose) {
        this.bindRequestCIDPurpose = bindRequestCIDPurpose;
    }

    public RichInputText getBindRequestCIDPurpose() {
        return bindRequestCIDPurpose;
    }

    public void setBindRequestCIDSVPosition(RichInputText bindRequestCIDSVPosition) {
        this.bindRequestCIDSVPosition = bindRequestCIDSVPosition;
    }

    public RichInputText getBindRequestCIDSVPosition() {
        return bindRequestCIDSVPosition;
    }

    public void setBindRequestCIDDateRequest(RichInputDate bindRequestCIDDateRequest) {
        this.bindRequestCIDDateRequest = bindRequestCIDDateRequest;
    }

    public RichInputDate getBindRequestCIDDateRequest() {
        return bindRequestCIDDateRequest;
    }

    public void setBindSubmitForm(RichButton bindSubmitForm) {
        this.bindSubmitForm = bindSubmitForm;
    }

    public RichButton getBindSubmitForm() {
        return bindSubmitForm;
    }

    public void bindEditButton(ActionEvent actionEvent) {
        String Cidstatusrequest =
            getValueInDB("AppModuleDataControl", "PheCidListrequestVO1",
                         "Idrequest= '" + idrequest + "'", "Cidstatusrequest");
        System.out.println("Cidstatusrequest btn edit "+Cidstatusrequest);
        if ((Cidstatusrequest.equals("TDC Review")) ||
            (Cidstatusrequest.equals("Approve TDC Lead")) ||
            (Cidstatusrequest.equals("Approve SPV")) ||
            (Cidstatusrequest.equals("Requested"))) {
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            bindPopupValidationDocList.show(ph);
            refreshTable();
        } else {
            SelectOne_action();
            bindIteratorRowCount.setVisible(false);
            bindFormRequestEDMS.setVisible(true);
            bindCancelInputRequestCID.setVisible(false);
            bindCancelEditRequestCID.setVisible(true); //Cancel Edit
            bindSaveDocRequest.setVisible(true); // Add To List
            bindAddtoList.setVisible(false);
            System.out.print(actionEvent);
            bindFinishDocList.setVisible(false);
            bindUpdate.setVisible(true);
            bindTabPage.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTabPage);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindIteratorRowCount);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindFormRequestEDMS);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindCancelInputRequestCID);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindCancelEditRequestCID);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindSaveDocRequest);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindAddtoList);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindFinishDocList);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindUpdate);
            buttonDocListOFF();

        }
    }
    
    public  void SelectOne_action() {
         
           DCBindingContainer bindings =
               (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
           DCIteratorBinding dcItteratorBindings =
               bindings.findIteratorBinding("PheCidListdocrequestVO1Iterator");
           ViewObject voTableData = dcItteratorBindings.getViewObject();
           // Get selected row
           Row rowSelected = voTableData.getCurrentRow();
           String ciddocnum=rowSelected.getAttribute("Ciddocnumber")==null ? ""
               :rowSelected.getAttribute("Ciddocnumber").toString();
           
           System.out.println(rowSelected.getAttribute("Ciddocnumber"));
           ApplicationModule am =
               ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
           ViewObject voRequestInput = am.findViewObject("PheCidListdocrequestVO1");
           voRequestInput.setWhereClause("CIDDOCNUMBER = '" + ciddocnum + "'");
           voRequestInput.executeQuery();
          Row r= voRequestInput.getCurrentRow();
           String bindContractorRewrite1=r.getAttribute("Cidcontractor")==null ? ""
               :r.getAttribute("Cidcontractor").toString();
           String bindUserContractor1=r.getAttribute("Cidusercontractor")==null ? ""
               :r.getAttribute("Cidusercontractor").toString();
           String bindRemarksRewrite1=r.getAttribute("Cidremarkrequestor")==null ? ""
               :r.getAttribute("Cidremarkrequestor").toString();
           bindUserContractor.setSubmittedValue(bindUserContractor1);
           bindContractorRewrite.setSubmittedValue(bindContractorRewrite1);
           bindRemarksRewrite.setSubmittedValue(bindRemarksRewrite1);
           AdfFacesContext.getCurrentInstance().addPartialTarget(bindUserContractor);
           AdfFacesContext.getCurrentInstance().addPartialTarget(bindContractorRewrite);
           AdfFacesContext.getCurrentInstance().addPartialTarget(bindRemarksRewrite);
           AdfFacesContext.getCurrentInstance().addPartialTarget(bindFormRequestEDMS);
           
           
       }



    public void bindDeleteButton(ActionEvent actionEvent) {

        String Cidstatusrequest =
            getValueInDB("AppModuleDataControl", "PheCidListrequestVO1",
                         "Idrequest= '" + idrequest + "'", "Cidstatusrequest");
        System.out.println("Cidstatusrequest del "+Cidstatusrequest);
        if ((Cidstatusrequest.equals("TDC Review")) ||
            (Cidstatusrequest.equals("Approve TDC Lead")) ||
            (Cidstatusrequest.equals("Approve SPV")) ||
            (Cidstatusrequest.equals("Requested"))) {
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            bindPopupValidationDel.show(ph);
            refreshTable();
        } else {
            RichPopup.PopupHints dh = new RichPopup.PopupHints();
            bindPopupValidationDelList.show(dh);
            bindIteratorRowCount.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindIteratorRowCount);
        }
    }

    public void bindDeleteButtonRequest(ActionEvent actionEvent) {
        //validationEdit
        String Cidstatusrequest = bindStatusRequest.getValue().toString();
        System.out.println("Cidstatusrequest del request "+Cidstatusrequest);
        if ((Cidstatusrequest.equals("TDC Review")) ||
            (Cidstatusrequest.equals("Approve TDC Lead")) ||
            (Cidstatusrequest.equals("Approve SPV")) ||
            (Cidstatusrequest.equals("Requested"))) {
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            bindPopupValidationDelCreate.show(ph);
        } else {
            RichPopup.PopupHints dh = new RichPopup.PopupHints();
            bindpopupDeleteRequestList.show(dh);
        }
    }

    public void setBindUpdate(RichButton bindUpdate) {
        this.bindUpdate = bindUpdate;
    }

    public RichButton getBindUpdate() {
        return bindUpdate;
    }

    public void bindUpdateButtonRequest(ActionEvent actionEvent) {
        //validationEdit
        String Cidstatusrequest = bindStatusRequest.getValue().toString();
         System.out.println("Cidstatusrequest update "+Cidstatusrequest);
        if ((Cidstatusrequest.equals("Draft")) ||
            (Cidstatusrequest.equals("Reject SPV With Remarks")) ||
            (Cidstatusrequest.equals("Reject TDC Lead With Remarks"))) {
            System.out.print(actionEvent);
            bindCancelEditRequest.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindCancelEditRequest);
            bindCancelInputRequest.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindCancelInputRequest);
            bindCreateRequest.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindCreateRequest);
            //bindSaveButton.setVisible(true);
            //AdfFacesContext.getCurrentInstance().addPartialTarget(bindSaveButton);
            bindUpdateButton.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindUpdateButton);
            bindFromRequest.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindFromRequest);
            buttonReqListOFF();
            refreshTableRequest();
        } else {
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            bindPopupValidationEdit.show(ph);
        }
    }

    public String bindSaveButtonRequest() {
        // Add event code here...
        bindTableRequest = new RichTable();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableRequest);
        bindSaveButton.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSaveButton);
        bindUpdateButton.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindUpdateButton);
        bindFromRequest.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindFromRequest);
        buttonReqListOn();
        return null;
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

    public void setBindSaveButton(RichButton bindSaveButton) {
        this.bindSaveButton = bindSaveButton;
    }

    public RichButton getBindSaveButton() {
        return bindSaveButton;
    }

    public void setBindUpdateButton(RichButton bindUpdateButton) {
        this.bindUpdateButton = bindUpdateButton;
    }

    public RichButton getBindUpdateButton() {
        return bindUpdateButton;
    }

    public void setBindCreateButton(RichButton bindCreateButton) {
        this.bindCreateButton = bindCreateButton;
    }

    public RichButton getBindCreateButton() {
        return bindCreateButton;
    }


    public void setBindRemarksRequestor(RichInputText bindRemarksRequestor) {
        this.bindRemarksRequestor = bindRemarksRequestor;
    }

    public RichInputText getBindRemarksRequestor() {
        return bindRemarksRequestor;
    }

    public void setBindTableHistory(RichPanelCollection bindTableHistory) {
        this.bindTableHistory = bindTableHistory;
    }

    public RichPanelCollection getBindTableHistory() {
        return bindTableHistory;
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

    public void bindHistoryButton(ActionEvent actionEvent) {
    }

    public void setBindUploadFile(RichInputFile bindUploadFile) {
        this.bindUploadFile = bindUploadFile;
    }

    public RichInputFile getBindUploadFile() {
        return bindUploadFile;
    }

    public void setBindRadioEDMS(RichSelectBooleanRadio bindRadioEDMS) {
        this.bindRadioEDMS = bindRadioEDMS;
    }

    public RichSelectBooleanRadio getBindRadioEDMS() {
        return bindRadioEDMS;
    }

    public void setBindFormRequestEDMS(RichPanelGroupLayout bindFormRequestEDMS) {
        this.bindFormRequestEDMS = bindFormRequestEDMS;
    }

    public RichPanelGroupLayout getBindFormRequestEDMS() {
        return bindFormRequestEDMS;
    }

    public void setBindDidDocEDMS(RichInputText bindDidDocEDMS) {
        this.bindDidDocEDMS = bindDidDocEDMS;
    }

    public RichInputText getBindDidDocEDMS() {
        return bindDidDocEDMS;
    }

    public void setBindDocNumberEDMS(RichInputText bindDocNumberEDMS) {
        this.bindDocNumberEDMS = bindDocNumberEDMS;
    }

    public RichInputText getBindDocNumberEDMS() {
        return bindDocNumberEDMS;
    }

    public void setBindDocTitleEDMS(RichInputText bindDocTitleEDMS) {
        this.bindDocTitleEDMS = bindDocTitleEDMS;
    }

    public RichInputText getBindDocTitleEDMS() {
        return bindDocTitleEDMS;
    }

    public void setBindDocSourceEDMS(RichInputText bindDocSourceEDMS) {
        this.bindDocSourceEDMS = bindDocSourceEDMS;
    }

    public RichInputText getBindDocSourceEDMS() {
        return bindDocSourceEDMS;
    }

    public void setBindStatusDocRequestEDMS(RichInputText bindStatusDocRequestEDMS) {
        this.bindStatusDocRequestEDMS = bindStatusDocRequestEDMS;
    }

    public RichInputText getBindStatusDocRequestEDMS() {
        return bindStatusDocRequestEDMS;
    }

    public void setBindAttachFile(RichInputFile bindAttachFile) {
        this.bindAttachFile = bindAttachFile;
    }

    public RichInputFile getBindAttachFile() {
        return bindAttachFile;
    }

    public void onChangeAttachFile(ValueChangeEvent valueChangeEvent) throws IOException {
        // Add event code here...
        file = (UploadedFile)valueChangeEvent.getNewValue();

        fileLength = file.getLength();
        fileInputStream = file.getInputStream();
        fileName = file.getFilename();
        fileContentType = file.getContentType();

        System.out.print("\nfileLength = " + fileLength);
        System.out.print("\nfileInputStream =" + fileInputStream);
        System.out.print("\nfileName = " + fileName);
        System.out.print("\nfileContentType =" + fileContentType);
    }

    public void setBindAttachFormIdrequest(RichInputText bindAttachFormIdrequest) {
        this.bindAttachFormIdrequest = bindAttachFormIdrequest;
    }

    public RichInputText getBindAttachFormIdrequest() {
        return bindAttachFormIdrequest;
    }

    public void setBindAttachFormDate(RichInputDate bindAttachFormDate) {
        this.bindAttachFormDate = bindAttachFormDate;
    }

    public RichInputDate getBindAttachFormDate() {
        return bindAttachFormDate;
    }

    public void setBindAttachFormDID(RichInputText bindAttachFormDID) {
        this.bindAttachFormDID = bindAttachFormDID;
    }

    public RichInputText getBindAttachFormDID() {
        return bindAttachFormDID;
    }

    public void setBindAttachFormDocName(RichInputText bindAttachFormDocName) {
        this.bindAttachFormDocName = bindAttachFormDocName;
    }

    public RichInputText getBindAttachFormDocName() {
        return bindAttachFormDocName;
    }

    public void setBindAttachFormDocFormat(RichInputText bindAttachFormDocFormat) {
        this.bindAttachFormDocFormat = bindAttachFormDocFormat;
    }

    public RichInputText getBindAttachFormDocFormat() {
        return bindAttachFormDocFormat;
    }

    public void setBindAttachFormDocSize(RichInputText bindAttachFormDocSize) {
        this.bindAttachFormDocSize = bindAttachFormDocSize;
    }

    public RichInputText getBindAttachFormDocSize() {
        return bindAttachFormDocSize;
    }

    public void setBindAttachFormStatus(RichInputText bindAttachFormStatus) {
        this.bindAttachFormStatus = bindAttachFormStatus;
    }

    public RichInputText getBindAttachFormStatus() {
        return bindAttachFormStatus;
    }

    public void setBindAttachFormFolderGuid(RichInputText bindAttachFormFolderGuid) {
        this.bindAttachFormFolderGuid = bindAttachFormFolderGuid;
    }

    public RichInputText getBindAttachFormFolderGuid() {
        return bindAttachFormFolderGuid;
    }

    public void setBindPopupInputAttach(RichPopup bindPopupInputAttach) {
        this.bindPopupInputAttach = bindPopupInputAttach;
    }

    public RichPopup getBindPopupInputAttach() {
        return bindPopupInputAttach;
    }

    public String bindUploadAttach() throws IOException {

        RIDCClass ridc =
            new RIDCClass(getWeblogicusername(), getWeblogicpassword());
        InputStream is = null;
        String dDID = "";
        String folderGuid = "";
        Timestamp date = null;
        try {
            is = fileInputStream;
            dDID =
ridc.CheckinCIDForm(is, fileName, fileLength, "Confidential Information Distribution/Attachment Documents");
            log.log(idrequest, "Check in",
                    "User : " + UserloginFullName + " - " +
                    "CheckIN Attch file CID to EDMS in folder Confidential Information Distribution/Attachment Documents and get dID : " +
                    dDID);
            System.out.print("\ndDID = " + dDID);
            date = new Timestamp(System.currentTimeMillis());
            DataObject ob =
                ridc.FolderInfo("Confidential Information Distribution/Attachment Documents");
            folderGuid = ob.get("fFolderGUID").toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IdcClientException e) {
            StackTraceElement[] element = e.getStackTrace();
            System.out.print(element);
        }
        bindAttachFormIdrequest.setValue(idrequest);
        bindAttachFormDate.setValue(date);
        bindAttachFormDID.setValue(dDID);
        bindAttachFormDocName.setValue(fileName);
        bindAttachFormDocFormat.setValue(fileContentType);
        bindAttachFormDocSize.setValue(fileLength);
        bindAttachFormStatus.setValue("Draft");
        bindAttachFormFolderGuid.setValue(folderGuid);
        bindAttachcoloum.setValue("Yes");
        bindAttachUploadForm.setValue("Yes");
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindAttachUploadForm);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindFormCreateRequest);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindAttachcoloum);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindAttachFile);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableAttach);

        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        bindPopupInputAttach.show(ph);
        return null;

    }

    public void bindInputAttachOK(ActionEvent actionEvent) {
        // Add event code here...
        bindPopupAttachInput.cancel();
    }

    public void setBindPopupAttachInput(RichPopup bindPopupAttachInput) {
        this.bindPopupAttachInput = bindPopupAttachInput;
    }

    public RichPopup getBindPopupAttachInput() {
        return bindPopupAttachInput;
    }

    public String bindAttachSubmitForm() {
        // Add event code here...
        bindAttachFile.resetValue();
        bindAttachFile = new RichInputFile();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindAttachFile);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableAttach);
        return null;
    }

    public void setBindIdRequestEDMS(RichInputText bindIdRequestEDMS) {
        this.bindIdRequestEDMS = bindIdRequestEDMS;
    }

    public RichInputText getBindIdRequestEDMS() {
        return bindIdRequestEDMS;
    }

    public void setBindTableAttach(RichTable bindTableAttach) {
        this.bindTableAttach = bindTableAttach;
    }

    public RichTable getBindTableAttach() {
        return bindTableAttach;
    }

    public void setBindAttachAdd(RichButton bindAttachAdd) {
        this.bindAttachAdd = bindAttachAdd;
    }

    public RichButton getBindAttachAdd() {
        return bindAttachAdd;
    }

    public void setBindAttachUpload(RichButton bindAttachUpload) {
        this.bindAttachUpload = bindAttachUpload;
    }

    public RichButton getBindAttachUpload() {
        return bindAttachUpload;
    }

    public String bindCheckForm() {
        String requestorPosition = "";
        String SVname = "";
        String dateRequest = "";
        String SVPoristion = "";
        String purpose = "";
      
        try {
            reqStatus = bindStatusRequest.getValue()==null?"":bindStatusRequest.getValue().toString();
            purpose = bindRequestCIDPurpose.getValue()==null?"":bindRequestCIDPurpose.getValue().toString();
            SVPoristion = bindRequestCIDSVPosition.getValue()==null?"":bindRequestCIDSVPosition.getValue().toString();
            dateRequest = bindRequestCIDDateRequest.getValue()==null?"": bindRequestCIDDateRequest.getValue().toString();
            SVname = bindSVName.getValue()==null?"":bindSVName.getValue().toString();
            requestorPosition =
                    bindRequestCIDRequestorPosition.getValue()==null?"":bindRequestCIDRequestorPosition.getValue().toString();
            ApplicationModule am =
                ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
           String apName=bindApNama.getValue()==null?"":bindApNama.getValue().toString();
            ViewObject svname = am.findViewObject("ParentMenuVo1");
            //svname.setNamedWhereClauseParam("groupName","PHEONWJ");//test 21072020
            svname.setNamedWhereClauseParam("groupName",apName.replaceAll("\\s+",""));
            svname.executeQuery();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        RichPopup.PopupHints pop = new RichPopup.PopupHints();
        if ((requestorPosition.equals("")) || (purpose.equals("")) ||
            (SVPoristion.equals("")) || (dateRequest.equals("")) ||
            (SVname.equals(""))) {
            bindPopupFillForm.show(pop);
        } else {
            bindPopupRequestCreated.show(pop);
        }
        buttonReqListOn();
        return null;
    }

    public void bindCancelEdit(ActionEvent actionEvent) {
        // Add event code here...
        buttonReqListOn();
        bindFromRequest.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindFromRequest);
    }

    public void setBindCancelInputRequest(RichButton bindCancelInputRequest) {
        this.bindCancelInputRequest = bindCancelInputRequest;
    }

    public RichButton getBindCancelInputRequest() {
        return bindCancelInputRequest;
    }

    public void setBindCancelEditRequest(RichButton bindCancelEditRequest) {
        this.bindCancelEditRequest = bindCancelEditRequest;
    }

    public RichButton getBindCancelEditRequest() {
        return bindCancelEditRequest;
    }

    public void setBindAttachcoloum(RichInputText bindAttachcoloum) {
        this.bindAttachcoloum = bindAttachcoloum;
    }

    public RichInputText getBindAttachcoloum() {
        return bindAttachcoloum;
    }

    public void printDraftCID(String username, String password) {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        System.out.print("\ndate = " + sdf.format(new java.util.Date()));
        String numberCid = "Draft - " + sdf.format(new java.util.Date());
        try {
            System.out.print("\nprint idrequest  = " + idrequest);
            RIDCClass ridc = new RIDCClass(username, password);

            String numberFile = numberCid + " - " + UserloginFullName;
            System.out.print("\nnumberFile  = " + numberFile);


            File fileasli = null;
            log.log(idrequest, "Create File CID",
                    "User : " + UserloginFullName + " - " +
                    "find base template" + "for ID Request " + idrequest);
            fileasli = new File("C:\\CID_temp\\PHEONWJ-CID-yyyymmdd-0000 - Requestor Name.xls");
//            fileasli =
//                    new File("E:\\Oracle\\Config\\user_projects\\domains\\base_domain\\CID_temp\\PHEONWJ-CID-yyyymmdd-0000 - Requestor Name.xls");
            FileInputStream inp = null;
            //bikin atau edit template
            inp = new FileInputStream(fileasli);

            log.log(idrequest, "Create File CID",
                    "User : " + UserloginFullName + " - " +
                    "remake file CID from base template" + "for ID Request " +
                    idrequest);
            //System.out.print("inp = "+inp.available());
            HSSFWorkbook my_xls_workbook = new HSSFWorkbook(inp);

            HSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0);
            Cell cell = null;
            ApplicationModule am =
                ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
            ViewObject voGetPar = am.findViewObject("PheCidListdocrequestVO1");
            voGetPar.setWhereClause("IDREQUEST = '" + idrequest + "'");
            voGetPar.executeQuery();

            ViewObject voGetParReq = am.findViewObject("PheCidListrequestVO1");
            voGetPar.setWhereClause("IDREQUEST = '" + idrequest + "'");
            voGetPar.executeQuery();
            Row rowReq = voGetParReq.first();

            int i = 17;
            int j = 2;
            int r = 1;
            String first = "";
            String Ciddocnumber = "";
            String Ciddoctitle = "";
            String Cidremarkrequestor = "";
            String Cidcontractor = "";
            String Cidrequestor = "";
            String Cidrequestorsupervisor = "";
            String Cidrequestorsupervisorposition = "";
            String Cidrequestorposition = "";
            String Cidpurpose = "";
            String Cidtdclead = "";
            while (voGetPar.hasNext()) {
                Row row = null;
                if (first.equals("")) {
                    row = voGetPar.first();
                    first = "1";
                } else {
                    row = voGetPar.next();
                }

                // System.out.print("\nCiddocnumber = "+Ciddocnumber);
                System.out.print("\n list create = " + r);
                try {
                    Ciddocnumber = row.getAttribute("Ciddocnumber").toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Ciddoctitle = row.getAttribute("Ciddoctitle").toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Cidremarkrequestor =
                            row.getAttribute("Cidremarkrequestor").toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (first.equals("1")) {
                        Cidcontractor =
                                row.getAttribute("Cidcontractor").toString();
                        first = "2";
                    } else {
                        Cidcontractor =
                                Cidcontractor + ", " + row.getAttribute("Cidcontractor").toString();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                cell = my_worksheet.getRow(i).getCell(j);
                cell.setCellValue(r + "");
                cell = my_worksheet.getRow(i).getCell(j + 1);
                cell.setCellValue(Ciddocnumber);
                cell = my_worksheet.getRow(i).getCell(j + 3);
                cell.setCellValue(Ciddoctitle);
                cell = my_worksheet.getRow(i).getCell(j + 5);
                cell.setCellValue(Cidremarkrequestor);
                i++;
                r++;
            }
            try {
                Cidpurpose = rowReq.getAttribute("Cidpurpose").toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Cidtdclead = rowReq.getAttribute("Cidtdclead").toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Cidrequestor = rowReq.getAttribute("Cidrequestor").toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Cidrequestorposition =
                        rowReq.getAttribute("Cidrequestorposition").toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Cidrequestorsupervisor =
                        rowReq.getAttribute("Cidrequestorsupervisor").toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Cidrequestorsupervisorposition =
                        rowReq.getAttribute("Cidrequestorsupervisorposition").toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            cell = my_worksheet.getRow(6).getCell(5);
            cell.setCellValue(numberCid);
            cell = my_worksheet.getRow(7).getCell(5);
            cell.setCellValue(sdf.format(new java.util.Date()));
            cell = my_worksheet.getRow(8).getCell(5);
            cell.setCellValue(Cidrequestor);
            cell = my_worksheet.getRow(9).getCell(5);
            cell.setCellValue(Cidtdclead);
            cell = my_worksheet.getRow(29).getCell(5);
            cell.setCellValue(Cidpurpose);
            cell = my_worksheet.getRow(31).getCell(5);
            cell.setCellValue(Cidcontractor);

            cell = my_worksheet.getRow(42).getCell(2);
            cell.setCellValue(Cidtdclead);
            cell = my_worksheet.getRow(48).getCell(2);
            cell.setCellValue(Cidrequestorsupervisor);
            cell = my_worksheet.getRow(48).getCell(5);
            cell.setCellValue(Cidrequestorsupervisorposition);
            cell = my_worksheet.getRow(53).getCell(2);
            cell.setCellValue(Cidrequestor);
            cell = my_worksheet.getRow(53).getCell(5);
            cell.setCellValue(Cidrequestorposition);
            cell = my_worksheet.getRow(53).getCell(7);
            cell.setCellValue(Cidcontractor);


            //java.net.URL imgURL = getClass().getResource("Image/approve.png");
            // aboutFrame.setIconImage(new ImageIcon(imgURL).getImage());

            log.log(idrequest, "Create File CID",
                    "User : " + UserloginFullName + " - " +
                    "input data to template" + "for ID Request " + idrequest);
            inp.close();

            FileOutputStream output_file = new FileOutputStream(new File("C:\\CID_temp\\" + numberFile +".xls"));
//            FileOutputStream output_file =
//                new FileOutputStream(new File("E:\\Oracle\\Config\\user_projects\\domains\\base_domain\\CID_temp\\temp\\" +
//                                              numberFile + ".xls"));
            //write changes
            my_xls_workbook.write(output_file);
            //close the stream
            output_file.close();
            log.log(idrequest, "Create File CID", "new file CID created");

            //uploadfile ke EDMS
            File file = new File("C:\\CID_temp\\" + numberFile + ".xls");
//            File file =
//                new File("E:\\Oracle\\Config\\user_projects\\domains\\base_domain\\CID_temp\\temp\\" +
//                         numberFile + ".xls");
            InputStream is = null;
            try {
                is = new FileInputStream(file);

                String dDID =
                    ridc.CheckinCIDForm(is, file.getName(), file.length(),
                                        "Confidential Information Distribution/Draft Documents");
                log.log(idrequest, "Check in",
                        "CheckIN file CID to EDMS in folder Confidential Information Distribution/Supporting Documents and get dID : " +
                        dDID);
                System.out.print("\ndDID = " + dDID);
                inboxClass inboxClass = new inboxClass();
                inboxClass.insert(idrequest, numberCid, "SYSDATE", "Draft",
                                  getIDC_SERVER()+"?IdcService=VIEW_IN_AUTOVUE&dID=" +
                        //"http://phekpowcdev.pertamina.com:16200/urm/idcplg?IdcService=VIEW_IN_AUTOVUE&dID="
                        dDID, "");
                // openfile(dDID);
                download(dDID);
                gotoInbox();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IdcClientException e) {
                StackTraceElement[] element = e.getStackTrace();
                System.out.print(element);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openfile(String dDID) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            ctx.getExternalContext().redirect(getIDC_SERVER()+"?IdcService=VIEW_IN_AUTOVUE&dID=" +
                                              dDID);
            //  ctx.getExternalContext().redirect("http://phekpowcdev.pertamina.com:16200/urm/idcplg?IdcService=VIEW_IN_AUTOVUE&dID=" +dDID);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void download(String dDID) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try { //ctx.getExternalContext().redirect("http://phekpowcdev.pertamina.com:16200/urm/idcplg?IdcService=GET_FILE&dID=" +dDID);
            ctx.getExternalContext().redirect(getIDC_SERVER()+"?IdcService=GET_FILE&dID=" +
                                              dDID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public oracle.jbo.domain.Date getNowDate(java.util.Date pJavaDate) {
        return new oracle.jbo.domain.Date(new Timestamp(pJavaDate.getTime()));
    }

    public void printDraft(ActionEvent actionEvent) {
        // Add event code here...
        printDraftCID(getWeblogicusername(), getWeblogicpassword());
    }

    public void cancelEdit(ActionEvent actionEvent) {
        // Add event code here...
        bindTabPage.setVisible(false);
        bindCreateInserDocList.setVisible(true);
        bindFinishDocList.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTabPage);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCreateInserDocList);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindFinishDocList);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindFormRequestEDMS);
        
        buttonDocListOn();
        refreshTableRequest();
    }

    public void bindFinishInputList(ActionEvent actionEvent) {
        // Add event code here...
        bindFormRequestEDMS.setVisible(false);
    }

    public void contractorInput(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        try {
            RemarksRewrite = bindRemarksRewrite.getValue().toString();
        } catch (Exception e) {
        }
    }

    public void remarksInput(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        try {
            contractorRewrite = bindContractorRewrite.getValue().toString();
        } catch (Exception e) {
        }
    }

    public void userContractorInput(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        try {
            contractorUser = bindUserContractor.getValue().toString();
        } catch (Exception e) {
        }
    }

    public void selectSV(ActionEvent actionEvent) {
        // Add event code here...
        userSelected = "SPV";
        ApplicationModule app =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        ViewObject voParIn = app.findViewObject("UserInternalVO1");
//        voParIn.setWhereClause("upper(FULLNAME) like upper('%" +
//                               bindSearchNameInternalUser.getValue().toString() +
//                               "%')");
        voParIn.executeQuery();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableInternalUser);
    }

    public void selectInternalUser(ActionEvent actionEvent) {
        // Add event code here...
        userSelected = "internalUser";
    }


    public void backRequest(ActionEvent actionEvent) {
        // Add event code here...
        refresh();
    }


    public void selectUserInternal(ActionEvent actionEvent) {
        // Add event code here...
        bindSVName.setValue(bindSelectUserFullName.getValue().toString());
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSVName);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableInternalUser);
        bindPopupInternalUser.hide();
    }

    public void tabEDMS(DisclosureEvent disclosureEvent) {
        // Add event code here...
        bindDocSource.setValue("Non EDMS");
        bindDocSourceEDMS.setValue("EDMS");
        AdfFacesContext.getCurrentInstance().getPageFlowScope().put("btnAddStat", "");
        bindAddtoList.setDisabled(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindAddtoList);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocStatus);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindStatusDocRequestEDMS);
    }

    public void tabNonEDMS(DisclosureEvent disclosureEvent) {
        // Add event code here...
        bindDocNumNonEDMS.setValue("");
        bindDocTitleNonEDMS.setValue("");
        bindDocSource.setValue("Non EDMS");
        bindDocSourceEDMS.setValue("EDMS");
        bindAddtoList.setDisabled(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindAddtoList);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocNumNonEDMS);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocTitleNonEDMS);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocStatus);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindStatusDocRequestEDMS);
    }

    public String bindUpdateButton() {
        // Add event code here...
        bindSaveButton.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSaveButton);
        return null;
    }

    public void closePage(ReturnEvent returnEvent) {
        // Add event code here...
    }

    public void closePage(ActionEvent actionEvent) {
        // Add event code here...

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExtendedRenderKitService service =
            Service.getRenderKitService(facesContext,
                                        ExtendedRenderKitService.class);
        service.addScript(facesContext,
                          "window.open('', '_self', ''); window.close();self.close();");

    }
    
    public void selectApEvent(ValueChangeEvent vce) {
        String namaCompany="";
        String varnama=vce.getNewValue()==null?"":vce.getNewValue().toString();
//        System.out.println(varnama.replaceAll("\\s+",""));
        if(!varnama.equalsIgnoreCase("")){
            namaCompany= vce.getNewValue().toString();
            bindApNama.setValue(namaCompany);
            String tdclabel=getValueInDB("AppModuleDataControl", "PheConfigVO1", "UPPER(KEY_CONFIG) = UPPER('TDCLEAD_"+namaCompany.substring(4)+"')",
                                     "KeyDisplay");
            String tdcLead =
                        getValueInDB("AppModuleDataControl", "PheConfigVO1", "UPPER(KEY_CONFIG) = UPPER('TDCLEAD_"+namaCompany.substring(4)+"')",
                                     "KeyValue");
            //                System.out.print("\ntdcLead = " + tdcLead + '_'+namaCompany.substring(4));
                    bindTdcLead.setValue(tdcLead); 
                    bindTdcLead.setLabel(tdclabel); 
                    
            if(namaCompany.substring(4).replaceAll("\\s+","").equalsIgnoreCase("ONWJ")){
                setRoleTdcReview("TDC");
            }else{
                setRoleTdcReview(namaCompany.substring(4).replaceAll("\\s+",""));
            }
        }
//       bindApNamaCompany.setValue(namaCompany);
//       AdfFacesContext.getCurrentInstance().addPartialTarget(bindApNamaCompany);    
        
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindTdcLead);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindApNama);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableRequest);
    }

    public void setBindCancelInputRequestCID(RichButton bindCancelInputRequestCID) {
        this.bindCancelInputRequestCID = bindCancelInputRequestCID;
    }

    public RichButton getBindCancelInputRequestCID() {
        return bindCancelInputRequestCID;
    }

    public void setBindCancelEditRequestCID(RichButton bindCancelEditRequestCID) {
        this.bindCancelEditRequestCID = bindCancelEditRequestCID;
    }

    public RichButton getBindCancelEditRequestCID() {
        return bindCancelEditRequestCID;
    }

    public void setBindLabelUSer(RichOutputText bindLabelUSer) {
        this.bindLabelUSer = bindLabelUSer;
    }

    public RichOutputText getBindLabelUSer() {
        return bindLabelUSer;
    }

    public void setBindLabelUSer2(RichOutputText bindLabelUSer2) {
        this.bindLabelUSer2 = bindLabelUSer2;
    }

    public RichOutputText getBindLabelUSer2() {
        return bindLabelUSer2;
    }

    public void setBindContractorRewrite(RichInputText bindContractorRewrite) {
        this.bindContractorRewrite = bindContractorRewrite;
    }

    public RichInputText getBindContractorRewrite() {
        return bindContractorRewrite;
    }

    public void setBindRemarksRewrite(RichInputText bindRemarksRewrite) {
        this.bindRemarksRewrite = bindRemarksRewrite;
    }

    public RichInputText getBindRemarksRewrite() {
        return bindRemarksRewrite;
    }
    public void setBindUserContractor(RichInputText bindUserContractor) {
        this.bindUserContractor = bindUserContractor;
    }

    public RichInputText getBindUserContractor() {
        return bindUserContractor;
    }

    public void setBindInternalUser(RichInputText bindInternalUser) {
        this.bindInternalUser = bindInternalUser;
    }

    public RichInputText getBindInternalUser() {
        return bindInternalUser;
    }
    public void setBindDeleteDocList(RichButton bindDeleteDocList) {
        this.bindDeleteDocList = bindDeleteDocList;
    }

    public RichButton getBindDeleteDocList() {
        return bindDeleteDocList;
    }

    public void setBindEditDocList(RichButton bindEditDocList) {
        this.bindEditDocList = bindEditDocList;
    }

    public RichButton getBindEditDocList() {
        return bindEditDocList;
    }

    public void setBindSendRequest(RichButton bindSendRequest) {
        this.bindSendRequest = bindSendRequest;
    }

    public RichButton getBindSendRequest() {
        return bindSendRequest;
    }

    public void setBindFinishDocList(RichButton bindFinishDocList) {
        this.bindFinishDocList = bindFinishDocList;
    }

    public RichButton getBindFinishDocList() {
        return bindFinishDocList;
    }

    public void setBindAddtoList(RichButton bindAddtoList) {
        this.bindAddtoList = bindAddtoList;
    }

    public RichButton getBindAddtoList() {
        return bindAddtoList;
    }

    public void setBindCreateRequestButton(RichButton bindCreateRequestButton) {
        this.bindCreateRequestButton = bindCreateRequestButton;
    }

    public RichButton getBindCreateRequestButton() {
        return bindCreateRequestButton;
    }

    public void setBindEditRequestButton(RichButton bindEditRequestButton) {
        this.bindEditRequestButton = bindEditRequestButton;
    }

    public RichButton getBindEditRequestButton() {
        return bindEditRequestButton;
    }

    public void setBindDeleteRequestButton(RichButton bindDeleteRequestButton) {
        this.bindDeleteRequestButton = bindDeleteRequestButton;
    }

    public RichButton getBindDeleteRequestButton() {
        return bindDeleteRequestButton;
    }

    public void setBindDocumentListButton(RichButton bindDocumentListButton) {
        this.bindDocumentListButton = bindDocumentListButton;
    }

    public RichButton getBindDocumentListButton() {
        return bindDocumentListButton;
    }

    public void setBindPopupAlreadyRequested(RichPopup bindPopupAlreadyRequested) {
        this.bindPopupAlreadyRequested = bindPopupAlreadyRequested;
    }

    public RichPopup getBindPopupAlreadyRequested() {
        return bindPopupAlreadyRequested;
    }

    public void setBindTableDocRequest(RichTable bindTableDocRequest) {
        this.bindTableDocRequest = bindTableDocRequest;
    }

    public RichTable getBindTableDocRequest() {
        return bindTableDocRequest;
    }

    public void setBindInputForm(RichPanelFormLayout bindInputForm) {
        this.bindInputForm = bindInputForm;
    }

    public RichPanelFormLayout getBindInputForm() {
        return bindInputForm;
    }

    public void setBindPopupValidationDocList(RichPopup bindPopupValidationDocList) {
        this.bindPopupValidationDocList = bindPopupValidationDocList;
    }

    public RichPopup getBindPopupValidationDocList() {
        return bindPopupValidationDocList;
    }
    public void setBindTabInputType(RichPanelTabbed bindTabInputType) {
        this.bindTabInputType = bindTabInputType;
    }

    public RichPanelTabbed getBindTabInputType() {
        return bindTabInputType;
    }

    public void setBindPopupValidationEdit(RichPopup bindPopupValidationEdit) {
        this.bindPopupValidationEdit = bindPopupValidationEdit;
    }

    public RichPopup getBindPopupValidationEdit() {
        return bindPopupValidationEdit;
    }

    public void setBindBackToReqList(RichButton bindBackToReqList) {
        this.bindBackToReqList = bindBackToReqList;
    }

    public RichButton getBindBackToReqList() {
        return bindBackToReqList;
    }

    public void setBindHome(RichButton bindHome) {
        this.bindHome = bindHome;
    }

    public RichButton getBindHome() {
        return bindHome;
    }
    public void setBindDocNumNonEDMS(RichInputText bindDocNumNonEDMS) {
        this.bindDocNumNonEDMS = bindDocNumNonEDMS;
    }

    public RichInputText getBindDocNumNonEDMS() {
        return bindDocNumNonEDMS;
    }

    public void setBindDocTitleNonEDMS(RichInputText bindDocTitleNonEDMS) {
        this.bindDocTitleNonEDMS = bindDocTitleNonEDMS;
    }

    public RichInputText getBindDocTitleNonEDMS() {
        return bindDocTitleNonEDMS;
    }
    public void setBindTableRequest(RichTable bindTableRequest) {
        this.bindTableRequest = bindTableRequest;
    }

    public RichTable getBindTableRequest() {
        return bindTableRequest;
    }

    public void setBindFormCreateRequest(RichPanelFormLayout bindFormCreateRequest) {
        this.bindFormCreateRequest = bindFormCreateRequest;
    }

    public RichPanelFormLayout getBindFormCreateRequest() {
        return bindFormCreateRequest;
    }

    public void setBindAttachUploadForm(RichInputText bindAttachUploadForm) {
        this.bindAttachUploadForm = bindAttachUploadForm;
    }

    public RichInputText getBindAttachUploadForm() {
        return bindAttachUploadForm;
    }

    public void setBindSelectDocument(RichButton bindSelectDocument) {
        this.bindSelectDocument = bindSelectDocument;
    }

    public RichButton getBindSelectDocument() {
        return bindSelectDocument;
    }

    public void setBindAddTolist(RichPanelGroupLayout bindAddTolist) {
        this.bindAddTolist = bindAddTolist;
    }

    public RichPanelGroupLayout getBindAddTolist() {
        return bindAddTolist;
    }

    public void setBindPopupNullSelect(RichPopup bindPopupNullSelect) {
        this.bindPopupNullSelect = bindPopupNullSelect;
    }

    public RichPopup getBindPopupNullSelect() {
        return bindPopupNullSelect;
    }
    public void setBindCountList(RichOutputText bindCountList) {
        this.bindCountList = bindCountList;
    }

    public RichOutputText getBindCountList() {
        return bindCountList;
    }

    public void setBindPopupValidationDelList(RichPopup bindPopupValidationDelList) {
        this.bindPopupValidationDelList = bindPopupValidationDelList;
    }

    public RichPopup getBindPopupValidationDelList() {
        return bindPopupValidationDelList;
    }

    public void setBindPopupValidationDel(RichPopup bindPopupValidationDel) {
        this.bindPopupValidationDel = bindPopupValidationDel;
    }

    public RichPopup getBindPopupValidationDel() {
        return bindPopupValidationDel;
    }

    public void setBindPopupValidationSend(RichPopup bindPopupValidationSend) {
        this.bindPopupValidationSend = bindPopupValidationSend;
    }

    public RichPopup getBindPopupValidationSend() {
        return bindPopupValidationSend;
    }

    public void setBindPopupValidationDelCreate(RichPopup bindPopupValidationDelCreate) {
        this.bindPopupValidationDelCreate = bindPopupValidationDelCreate;
    }

    public RichPopup getBindPopupValidationDelCreate() {
        return bindPopupValidationDelCreate;
    }

    public void setBindPopupSend(RichPopup bindPopupSend) {
        this.bindPopupSend = bindPopupSend;
    }

    public RichPopup getBindPopupSend() {
        return bindPopupSend;
    }

    public void setBindpopupDeleteRequestList(RichPopup bindpopupDeleteRequestList) {
        this.bindpopupDeleteRequestList = bindpopupDeleteRequestList;
    }

    public RichPopup getBindpopupDeleteRequestList() {
        return bindpopupDeleteRequestList;
    }

    public void setSampah(RichOutputText sampah) {
        this.sampah = sampah;
    }

    public RichOutputText getSampah() {
        return sampah;
    }

    public void setBindIteratorRowCount(RichOutputText bindIteratorRowCount) {
        this.bindIteratorRowCount = bindIteratorRowCount;
    }

    public RichOutputText getBindIteratorRowCount() {
        return bindIteratorRowCount;
    }

    public void setUserlogin(String Userlogin) {
        this.Userlogin = Userlogin;
    }

    public String getUserlogin() {
        return Userlogin;
    }

    public void setBindApNamaCompany(RichInputText bindApNamaCompany) {
        this.bindApNamaCompany = bindApNamaCompany;
    }

    public RichInputText getBindApNamaCompany() {
        return bindApNamaCompany;
    }



    public void setBindSocApName(RichSelectOneChoice bindSocApName) {
        this.bindSocApName = bindSocApName;
    }

    public RichSelectOneChoice getBindSocApName() {
        return bindSocApName;
    }

    public void setBindApNama(RichInputText bindApNama) {
        this.bindApNama = bindApNama;
    }

    public RichInputText getBindApNama() {
        return bindApNama;
    }

    public void setIDC_SERVER(String IDC_SERVER) {
        this.IDC_SERVER = IDC_SERVER;
    }

    public String getIDC_SERVER() {
        return IDC_SERVER;
    }

    public void setURL_CID(String URL_CID) {
        this.URL_CID = URL_CID;
    }

    public String getURL_CID() {
        return URL_CID;
    }

    public void setUrl_Database(String Url_Database) {
        this.Url_Database = Url_Database;
    }

    public String getUrl_Database() {
        return Url_Database;
    }

    public void setUser_Database(String User_Database) {
        this.User_Database = User_Database;
    }

    public String getUser_Database() {
        return User_Database;
    }

    public void setPass_Database(String Pass_Database) {
        this.Pass_Database = Pass_Database;
    }

    public String getPass_Database() {
        return Pass_Database;
    }

    public void setRoleTdcReview(String roleTdcReview) {
        this.roleTdcReview = roleTdcReview;
    }

    public String getRoleTdcReview() {
        return roleTdcReview;
    }

    public void setTesBtn(RichButton tesBtn) {
        this.tesBtn = tesBtn;
    }

    public RichButton getTesBtn() {
        return tesBtn;
    }

    public void addtolistAction(ActionEvent actionEvent) {
        System.out.print("===Testing====");
        DCIteratorBinding dci =
            ADFUtils.findIterator("PheCidListdocrequestVO1Iterator");
        System.out.println("DCI COUNT: " + dci.getEstimatedRowCount());

        //proses simpen temporary di PageFlowScope
        ADFContext actx = ADFContext.getCurrent();
        Map pfscount = actx.getPageFlowScope();
        Object var = pfscount.put("doccount", dci.getEstimatedRowCount());
        
        long a = dci.getEstimatedRowCount();
        String isBlocked = "true";
        if (a >= 109) {
            isBlocked = "false";
        } else {
            isBlocked = "true";
        }

        if (isBlocked.equalsIgnoreCase("true")) {
            
//            Row r=dci.getNavigatableRowIterator().createRow();
//            r.setNewRowState(r.STATUS_INITIALIZED);
           System.out.println("masuk kondisi true");
             
            String Cidstatusrequest =
                getValueInDB("AppModuleDataControl", "PheCidListrequestVO1",
                             "Idrequest= '" + idrequest + "'",
                             "Cidstatusrequest");
            System.out.println("Cidstatusrequest "+Cidstatusrequest);
            System.out.println("idrequest "+idrequest);
            if ((Cidstatusrequest.equalsIgnoreCase("Draft"))) {
                System.out.println("masuk ke draft");
                buttonDocListOFF();
                bindIteratorRowCount.setVisible(false);
                bindUpdate.setVisible(false);
                bindSaveDocRequest.setVisible(false);
                bindAddtoList.setVisible(true);
                bindCancelInputRequestCID.setVisible(true);
                bindCancelEditRequestCID.setVisible(false);
                bindTabPage.setVisible(true);
                bindDocSource.setValue("Non EDMS");
                bindDocStatus.setValue("Draft");
                bindIdRequestCreate.setValue(idrequest);
                bindAddtoList.setDisabled(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindIteratorRowCount);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindUpdate);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindSaveDocRequest);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindAddtoList);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindCancelInputRequestCID);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindCancelEditRequestCID);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindTabPage);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocSource);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocStatus);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindIdRequestCreate);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindAddtoList);
//                dci.getRowSetIterator().insertRow(r);
                dci.getDataControl().commitTransaction();
                dci.executeQuery();
                ViewObject voclear = dci.getViewObject();
                voclear.clearCache();
                BindingContainer bindings1 =
                  BindingContext.getCurrent().getCurrentBindingsEntry();
                OperationBinding operationbinding =
                  bindings1.getOperationBinding("CreateInsert");
                operationbinding.execute();
                /*  try{ 
                    BindingContainer bindings = getBindings();
                    OperationBinding operationBinding =
                        bindings.getOperationBinding("Commit");
                    operationBinding.execute();
                }catch (Exception e){
                    e.printStackTrace();
                } */
             
            } else {
                RichPopup.PopupHints ph = new RichPopup.PopupHints();
                bindPopupValidationDocList.show(ph);
                refreshTable();
            }
        } else {
            BindingContainer bindings1 =
                BindingContext.getCurrent().getCurrentBindingsEntry();
            OperationBinding operationbinding =
                bindings1.getOperationBinding("Rollback");
            operationbinding.execute();

            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage fm =
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Document sudah terinput sebanyak 109, Document berikutnya harap membuat CID baru.",
                                 "");
            ctx.addMessage(null, fm);
        }
    }

    public void Updatevent(ActionEvent actionEvent) {
        DCIteratorBinding dci =
            ADFUtils.findIterator("PheCidListdocrequestVO1Iterator");
        dci.getDataControl().commitTransaction();
        dci.executeQuery();
        refresh();
    }

    public void fecthListenerAllshow(PopupFetchEvent popupFetchEvent) {
        ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        ViewObject voRequestInput = am.findViewObject("PheCidListdocrequestVO1");
        voRequestInput.executeQuery();
    }

    public void clearAlldataDoclist(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok)
            {
            System.out.println("masuk ok");
//                BindingContext bctx = BindingContext.getCurrent();
//                BindingContainer bindings = bctx.getCurrentBindingsEntry();
//                DCIteratorBinding iter = (DCIteratorBinding)
//                bindings.get("PheCidListdocrequestVO1Iterator");
//                iter.clearForRecreate();
                /*    ApplicationModule am =
                    ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
                ViewObject voRequestInput = am.findViewObject("PheCidListdocrequestVO1");
                voRequestInput.executeQuery();
                System.out.println("jumlah "+voRequestInput.getEstimatedRowCount());*/
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableDocRequest); 
                
            } else
            {
           
           }
    }


    public void updateEvent(ActionEvent actionEvent) {
        BindingContainer bindings = getBindings();
        OperationBinding operationRefreshDoc =
            bindings.getOperationBinding("Execute1");
        operationRefreshDoc.execute();
//        ApplicationModule am =
//            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
//        ViewObject voRequestInput = am.findViewObject("PheCidListdocrequestVO1");
//        voRequestInput.executeQuery();
        refreshTable();
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.getPupdate().show(hints);
    }
    
   
    public void setPupdate(RichPopup pupdate) {
        this.pupdate = pupdate;
    }

    public RichPopup getPupdate() {
        return pupdate;
    }
}
