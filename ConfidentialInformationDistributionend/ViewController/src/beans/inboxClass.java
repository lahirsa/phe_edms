package beans;

import CID.Module.AppModuleImpl;

import CID.VO.PheApListVOImpl;

import CID.VO.TdcLeadValVOImpl;


import com.utils.ADFUtils;

import com.utils.ConnectHandler;
import javax.faces.context.FacesContext;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelLabelAndMessage;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.nav.RichGoLink;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.ViewObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.context.FacesContext;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import oracle.binding.BindingContainer;

import oracle.jbo.Row; 

import oracle.stellent.ridc.IdcClientException;

import org.apache.myfaces.trinidad.event.RowDisclosureEvent;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.sql.DataSource;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.share.ADFContext;

import oracle.jbo.JboException;

import oracle.stellent.ridc.IdcClient;
import oracle.stellent.ridc.IdcContext;
import oracle.stellent.ridc.model.DataObject;
import oracle.stellent.ridc.protocol.ServiceResponse;

import org.apache.myfaces.trinidad.model.UploadedFile;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;

import utils.system;

public class inboxClass {
    private RichTable bindTableInboxRequest;
    private String currentAddress;
    private String weblogicusername;
    private String weblogicpassword;
    private String Userlogin;
    private String UserloginFullName;
    private String Usersecure;
    private String tableStatus;
    private String cekStatus;
    private String tableDocCount;
    private String idrequest;
    private String numberCid;
    private String numberRunCid;
    private String reqStatus;
    private String idList;
    private String tdclead;
    private String Cidapname;
    private String requestor;
    private String purpose;
    private String checkTable;
    private UploadedFile file;
    private String statusDocListBefore;
    private long fileLength;
    private InputStream fileInputStream;
    private String fileName;
    private String fileContentType;
    private String multiStatusApproval;
    private String multiRemarksTDCLead;
    private String multiRemarksTDCReview;
    private String multiRemarksSPV;
    private String LastApproveStatusRequest;
    private String LastApproveRemarks;
    private String selectAll;
    private String statusReject;
    private RichPanelCollection bindTableRequestApproval;
    private RichPopup bindPopupSelectedItemApproval;
    private RichPopup bindPopupNoItemApproval;
    private RichInputText bindIdRequest;
    private RichTable bindTableInboxListRequest;
    private RichSelectOneChoice bindAction;
    private RichInputText bindIdList;
    private RichInputText bindStatusDocApproval;
    private RichInputText bindStatusRequestApproval;
    private RichInputText bindRemarksv;
    private RichPopup bindPopupInputRemarksSV;
    private RichPopup bindPopupRespondAllDocumentList;
    private RichPopup bindPopupSubmit;
    private RichInputText bindInputRemarksSV;
    private RichPanelFormLayout bindFormInputRemarksSV;
    private RichInputText bindStatusDocRequest;
    private RichInputText bindInputRemarksTDCReview;
    private RichInputText bindInputRemarksTDCLead;
    private RichPopup bindTDCApproval;
    private RichInputText bindCreateCIDNumcid;
    private RichInputText bindCreateCIDIdrequest;
    private RichInputDate bindCreateCIDCreatedate;
    private RichInputText bindCreateCIDTdclead;
    private RichInputText bindCreateCIDRequestor;
    private RichInputText bindCreateCIDStatuscid;
    private RichInputText bindCreateCIDId;
    private RichColumn bindIdListInbox;
    Log log = new Log();
    private RichPanelCollection bindTableHistory;
    private RichButton bindTDCRemarksInput;
    private RichPopup bindPopupInputAction;
    private RichOutputText bindLabelCIDNumber;
    private RichPopup bindPopupCheckCID;
    private RichOutputText bindInputRemarksTDCreview;
    private RichOutputText bindModifiedRemarksTDCReview;
    private RichInputText bindTempInputRemarksSV;
    private RichInputText bindIDRequest;
    private RichInputText bindIdListTemp;
    ConnectHandler ConnectHandler = new ConnectHandler();
    private RichOutputText bindLabelUsername;
    private RichOutputText bindLabelUsername2;
    private RichInputText bindSearchCIDNumber;
    private RichTable bindTableCIDNumber;
    private RichButton searchCIDNumber;
    private RichColumn bindIdCidUpload;
    private RichOutputText bindAttachPhysical;
    private RichPanelFormLayout bindCIDNumberForm;
    private RichInputFile bindAttachFilePhysical;
    private RichButton bindButtonAttachFilePhysical;
    private RichColumn bindIdRequestAttachPhysical;
    private RichOutputText bindAttachIdRequest;
    private RichOutputText bindStatusUpload;
    private RichButton uploadPhysicalRequestCID;
    private RichOutputText bindLabelCIDSelected;
    private RichPanelLabelAndMessage bindNumCIDPhysical;
    private RichInputText bindNumCIDAttachPhysical;
    private RichPanelGroupLayout bindStatusUploadForm;
    private RichInputText bindNumCIDPhysicalAttach;
    private RichInputText bindAttachPhysicalUpload;
    private RichInputText bindIDrequestAttachPhysical;
    private RichColumn selectedCIDNumber;
    private RichPanelFormLayout bindCIDNumberFormHidden;
    private RichOutputText ciDSelected;
    private RichOutputText bindRowNumCID;
    private RichInputText bindNumcid;
    private RichOutputText bindNumcid2;
    private RichOutputText numcid;
    private RichInputText bindIdCID;
    private RichInputText bindNumcidGetParam;
    private RichPanelFormLayout bindCIDForm;
    private RichInputText biindIdRequestAttach;
    private RichOutputText bindLabelCIDNumberPopup;
    private RichPopup bindPopupPublishUpload;
    private RichPopup bindPopupNullCIDNumber;
    private RichPanelGroupLayout bindPublishForm;
    private RichInputText bindTDCLead;
    private RichInputText bindRequestorPublish;
    requestClass requestClass = new requestClass();
    private RichOutputText bindCIDSelected;
    private RichInputText bindDocStatus;
    private RichOutputText bindTextRepond;
    private RichPopup bindPopupRespond;
    private RichSelectBooleanCheckbox bindApproveCheckbox;
    private RichPanelFormLayout bindMultiApprovalForm;
    private RichInputText bindIdListMultiForm;
    private RichInputText bingStatusMultiform;
    ArrayList<String> MultiApproveIdRequest = new ArrayList<String>();
    ArrayList<String> MultiRemarkTdcReviewRequest = new ArrayList<String>();
    private RichColumn bindColumnApprove;
    private RichSelectBooleanCheckbox bindCheckAll;
    private RichPanelCollection bindTableRequestListApproval;
    private RichSelectBooleanCheckbox bindCheckBox;
    private RichButton bindSelectAllButton;
    private RichButton bindUnselectAllButton;
    private RichTable bindTableCIDNumberPublish;
    private RichSelectOneRadio radioCIDType;
    private RichGoLink golinkAttach;
    private RichInputText bindSupervisor;
    private RichInputText bindNumCidPublish;
    private RichInputText bindIdRequestAttachPublish;
    private RichInputText bindRequestorAttachPurpose;
    private RichPanelFormLayout bindCidFormPurpose;
    private RichPopup bindPopupSelectRespond;
    private RichPopup bindPopupRespondSubmitReminder;
    private RichButton bindCreateFormCidTDCReview;
    private RichButton bindOpenAllList;
    private RichTable bindTableListRequest;
    private RichInputText bindApNameinsert;
    private String IDC_SERVER;
    private String URL_CID;
    private String Url_Database;
    private String User_Database;
    private String Pass_Database;
    private String jabatan;
    private String roleTdcReview;
    public inboxClass() {
        numberCid = "";
        fileName = "";
        LastApproveRemarks = "";
        multiRemarksTDCLead = "";
        multiRemarksTDCReview = "";
        multiRemarksSPV = "";
        requestClass = new requestClass();
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();

        setCurrentAddress(requestClass.getValueInDB("AppModuleDataControl",
                                                    "PheConfigVO1",
                                                    "KEY_CONFIG = 'RIDC_URL'",
                                                    "KeyValue"));
        setWeblogicusername(requestClass.getValueInDB("AppModuleDataControl",
                                                      "PheConfigVO1",
                                                      "KEY_CONFIG = 'WEBLOGIC_USER'",
                                                      "KeyValue"));
        setWeblogicpassword(requestClass.getValueInDB("AppModuleDataControl",
                                                      "PheConfigVO1",
                                                      "KEY_CONFIG = 'WEBLOGIC_PASS'",
                                                      "KeyValue"));
        setIDC_SERVER(requestClass.getValueInDB("AppModuleDataControl",
                                         "PheConfigVO1",
                                         "KEY_CONFIG = 'IDC_SERVER'",
                                         "KeyValue"));
        setURL_CID(requestClass.getValueInDB("AppModuleDataControl",
                                         "PheConfigVO1",
                                         "KEY_CONFIG = 'URL_CID'",
                                         "KeyValue"));
        setUrl_Database(requestClass.getValueInDB("AppModuleDataControl",
                                         "PheConfigVO1",
                                         "KEY_CONFIG = 'URL_DATABASE'",
                                         "KeyValue"));
        setUser_Database(requestClass.getValueInDB("AppModuleDataControl",
                                         "PheConfigVO1",
                                         "KEY_CONFIG = 'USER_DATABASE'",
                                         "KeyValue"));
        setPass_Database(requestClass.getValueInDB("AppModuleDataControl",
                                         "PheConfigVO1",
                                         "KEY_CONFIG = 'PASS_DATABASE'",
                                         "KeyValue"));

        try {
            Userlogin =request.getParameter("username");//"user.hsse";//request.getParameter("username");//"iwansyah";//"yudhi.widhiyana";//request.getParameter("username");//"user_reviewer";//"mk.olivia.paramitha";//"iwansyah";//"PHEH_Admin";//"iwansyah";//request.getParameter("username");//"iwansyah";"yudhi.widhiyana";"NSO_User";
            ApplicationModule am =
                ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
            ViewObject voRequestInput = am.findViewObject("PheCidCreatePurposeVO1");
            voRequestInput.setNamedWhereClauseParam("dUser", Userlogin);
            voRequestInput.executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error get Username");
            }
        try{
            UserloginFullName =
                    requestClass.getValueInDB("AppModuleDataControl",
                                              "UsersVO1",
                                              "DNAME = '" + Userlogin + "'",
                                              "Dfullname");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error get Fullname");
        }
        try {
            bindLabelUsername = new RichOutputText();
            bindLabelUsername2 = new RichOutputText();
            bindLabelUsername.setValue(UserloginFullName);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindLabelUsername);
            bindLabelUsername2.setValue(UserloginFullName);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindLabelUsername2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //main method
        //UserloginFullName = "Yudhi Widhiyana";
        //Userlogin = "yudhi.widhiyana";
        //UserloginFullName = "Rudi Mulia";
        //Userlogin = "rudi.mulia";
        //UserloginFullName = "Olivia ";
        //Userlogin = "mk.olivia";
        //UserloginFullName = "user_reviewer";
        //Userlogin = "user_reviewer";
        //UserloginFullName = "Indra Fajar";
        //Userlogin = "indra.fajar";
        //Userlogin = "iwansyah";
        //UserloginFullName = "Iwansyah";
        //System.out.print("\nUserloginFullName =" + UserloginFullName);
        //System.out.print("\nUserlogin =" + Userlogin);

        log.log("0", "Login User", UserloginFullName);

        tdclead =
                requestClass.getValueInDB2("AppModuleDataControl", "TdcLeadValVO1",
                                          UserloginFullName);
        System.out.print("\ntdcLead = " + tdclead);
//                System.out.println("Id request "+bindIdRequest.getValue().toString());
                String tdcrole=requestClass.getValueInDB("AppModuleDataControl", "CidTDCRoleVO1",
                                          "DUSERNAME='" + Userlogin + "'",
                                          "Dattributename");
//               System.out.println(requestClass.getValueInDB("AppModuleDataControl", "CidTDCRoleVO1",
//                                          "DUSERNAME='" + Userlogin + "'",
//                                          "Dattributename")); 
//               "+getRoleTdcReview().replaceAll("\\s+","")+"
        if (tdclead.equalsIgnoreCase("ada")) {
            Usersecure = "tdclead";
        } else {                                                                                                                                                                                            
            String Dattributeprivilege =
                requestClass.getValueInDB("AppModuleDataControl",
                                          "UsersecurityattributesVO1",
                                          "upper (dattributetype)=upper('role') and upper(dattributename)=upper('"+tdcrole+"') and upper(dusername)=upper('" +
                                          Userlogin + "')",
                                          "Dattributeprivilege");
            System.out.print("\nvalue user login Dattributeprivilege = " +
                             Dattributeprivilege);
            ApplicationModule am1 =
                ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
            ViewObject chekStatusrequest = am1.findViewObject("PheCidListrequestVO1");
            chekStatusrequest.setWhereClause("PheCidListrequestEO.CIDREQUESTORSUPERVISOR='"+UserloginFullName+"'"+
                                            " AND PheCidListrequestEO.CIDSTATUSREQUEST='Requested'");
            chekStatusrequest.executeQuery();
            
            //Andika 20072020
            cekStatus = chekStatusrequest.getEstimatedRowCount()+ "";
            System.out.print("\ncekStatus = " + cekStatus);
            //----------------------
            if (Dattributeprivilege.equals("15")) {
                if(chekStatusrequest.getEstimatedRowCount()>0){
                    Usersecure = "spvreviewandtdcreview";
                }else{
                    Usersecure = "tdcreview";
                }
            } else {    
                Usersecure = "spvreview";
            }
        }
        System.out.print("\nvalue user secure = " + Usersecure);
        ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        ViewObject voListApproval = am.findViewObject("PheCidListrequestVO1");
        
        if (Usersecure.equals("tdclead")) {
            voListApproval.setWhereClause("(PheCidListrequestEO.Cidstatusrequest = 'Approve SPV' OR PheCidListrequestEO.Cidstatusrequest = 'Reject TDC Lead With Remarks' OR PheCidListrequestEO.Cidstatusrequest = 'Requested') " +
                                        "   AND PheCidListrequestEO.Cidtdclead= '"+UserloginFullName+"'" +
                                        "   AND (select Count(1) from PHE_CID_LISTDOCREQUEST PheCisListdocrequest where PheCisListdocrequest.IDREQUEST=PheCidListrequestEO.Idrequest and PheCisListdocrequest.CIDSTATUSDOCREQUEST='Approve SPV' OR PheCisListdocrequest.CIDSTATUSDOCREQUEST = 'Requested') > 0");
            //"' AND Cidstatusrequest <> 'Approve TDC Lead' OR (Cidstatusrequest <> 'Requested' AND Cidstatusrequest <> 'Reject SPV With Remarks' AND Cidstatusrequest <> 'Draft' AND Cidstatusrequest <> 'Publish' AND Cidstatusrequest <> 'Approve TDC Lead')");
        //System.out.println("<br>tdc lead : "+voListApproval.getQuery());
        } else if (Usersecure.equals("tdcreview")) {
        voListApproval.setWhereClause("Cidstatusrequest = 'Approve SPV' " + 
        " and Cidapname in (SELECT DISTINCT a.key_display as DATTRIBUTENAME" + 
        " from phe_config A, usersecurityattributes B where" + 
        "    substr(A.KEY_CONFIG, 0, 11) = 'ROLE_PREFIX' and" + 
        "    (substr(B.dattributename, 0, instr(B.dattributename,'_')-1) in A.KEY_VALUE or" + 
        "    B.dattributename in A.KEY_VALUE)" + 
        "    and upper(B.dusername)=upper('"+Userlogin+"'))");
            
//"(Cidrequestorsupervisor = '" +
//                                          UserloginFullName +
//                                          "' AND (Cidstatusrequest = 'Requested' OR Cidstatusrequest = 'Reject SPV With Remarks')) OR (Cidstatusrequest = 'Approve SPV' OR Cidstatusrequest = 'Reject TDC Lead With Remarks') ");
            //"' AND Cidstatusrequest <> 'Publish' OR (Cidstatusrequest <> 'Requested' AND Cidstatusrequest <> 'Reject SPV With Remarks' AND Cidstatusrequest <> 'Draft' AND Cidstatusrequest <> 'Publish' AND Cidstatusrequest <> 'Approve TDC Lead')");
          
        } else if(Usersecure.equals("spvreviewandtdcreview")){
                voListApproval.setWhereClause("Cidstatusrequest = 'Approve SPV' " + 
                " and Cidapname in (SELECT DISTINCT a.key_display as DATTRIBUTENAME" + 
                " from phe_config A, usersecurityattributes B where" + 
                "    substr(A.KEY_CONFIG, 0, 11) = 'ROLE_PREFIX' and" + 
                "    (substr(B.dattributename, 0, instr(B.dattributename,'_')-1) in A.KEY_VALUE or" + 
                "    B.dattributename in A.KEY_VALUE)" + 
                "    and upper(B.dusername)=upper('"+Userlogin+"'))OR " +
                    "PheCidListrequestEO.Cidrequestorsupervisor ='"+UserloginFullName+"'" +
                    "and PheCidListrequestEO.CIDSTATUSREQUEST='Requested'");
            }else {
            voListApproval.setWhereClause("(PheCidListrequestEO.Cidrequestorsupervisor = '" +
                                          UserloginFullName +"' OR PheCidListrequestEO.CIDTDCLEAD ='" +
                                          UserloginFullName + 
                                          "') AND (PheCidListrequestEO.Cidstatusrequest = 'Requested' OR PheCidListrequestEO.Cidstatusrequest = 'Reject SPV With Remarks') " +
                                          " AND (select Count(1) from PHE_CID_LISTDOCREQUEST PheCisListdocrequest where PheCisListdocrequest.IDREQUEST=PheCidListrequestEO.Idrequest and PheCisListdocrequest.CIDSTATUSDOCREQUEST='Requested') > 0");
            
        }
        System.out.println("query "+voListApproval.getQuery().toString());
        voListApproval.executeQuery();
        tableStatus = voListApproval.getEstimatedRowCount() + "";
        ViewObject voListApprovalDone =
            am.findViewObject("PheCidListrequestRO1");
        if (Usersecure.equals("tdclead")) {
            voListApprovalDone.setWhereClause("(PheCidListrequestEO.Cidstatusrequest = 'Approve TDC Lead' OR PheCidListrequestEO.Cidstatusrequest ='Reject TDC Lead With Remarks')" +
            "   AND PheCidListrequestEO.Cidtdclead= '"+UserloginFullName+"'" +
                "AND (select Count(1) from PHE_CID_LISTDOCREQUEST PheCisListdocrequest where PheCisListdocrequest.IDREQUEST=PheCidListrequestEO.Idrequest and PheCisListdocrequest.CIDSTATUSDOCREQUEST='Approve SPV') < 1");
        } else if (Usersecure.equals("tdcreview")) {
            voListApprovalDone.setWhereClause("Cidstatusrequest = 'Approve TDC Lead'");
        } else {
            voListApprovalDone.setWhereClause("PheCidListrequestEO.Cidrequestorsupervisor = '" + UserloginFullName + "' " + 
                                              "AND (PheCidListrequestEO.Cidstatusrequest = 'Approve SPV' OR PheCidListrequestEO.Cidstatusrequest= 'Reject SPV With Remarks') " + 
                                              "AND (select Count(1) from PHE_CID_LISTDOCREQUEST PheCisListdocrequest where PheCisListdocrequest.IDREQUEST=PheCidListrequestEO.Idrequest and PheCisListdocrequest.CIDSTATUSDOCREQUEST='Requested') < 1");
            /*
            voListApprovalDone.setWhereClause("Cidrequestorsupervisor = '" +
                                              UserloginFullName +
                                              "' AND (Cidstatusrequest = 'Approve SPV' OR Cidstatusrequest= 'Reject SPV With Remarks')" + 
                                              " AND (select Count(1) from PheCidListdocrequest where PheCidListdocrequest.Idrequest=PheCidListrequest.Idrequest and PheCidListdocrequest.Cidstatusrequest='Requested') < 1 ");
            */
        }
        voListApprovalDone.executeQuery();
        ViewObject voListCreate = am.findViewObject("PheCidCreatePurposeVO1");
        if (Usersecure.equals("tdcreview")) {
            voListCreate.setWhereClause("Cidstatuscid <> 'Publish'");
        }
        voListCreate.executeQuery();
        radioCIDType = new RichSelectOneRadio();
        radioCIDType.setValue("task");
        AdfFacesContext.getCurrentInstance().addPartialTarget(radioCIDType);

        System.out.print("\ntableStatus jumlah = " + tableStatus);
        bindTableInboxRequest = new RichTable();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableInboxRequest);
  
        if (Usersecure.equals("tdcreview")) {
            uploadPhysicalRequestCID = new RichButton();
            uploadPhysicalRequestCID.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(uploadPhysicalRequestCID);
            
            bindCreateFormCidTDCReview = new RichButton();
            bindOpenAllList = new RichButton();
            bindCreateFormCidTDCReview.setVisible(true);
            bindOpenAllList.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindOpenAllList);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindCreateFormCidTDCReview);
            //buat create form cid
        } else {
            uploadPhysicalRequestCID = new RichButton();
            bindCreateFormCidTDCReview = new RichButton();
            bindOpenAllList = new RichButton();
            uploadPhysicalRequestCID.setVisible(false);
            bindCreateFormCidTDCReview.setVisible(false);
            bindOpenAllList.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(uploadPhysicalRequestCID);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindOpenAllList);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindCreateFormCidTDCReview);
            //buat create form cid
        }
    }

    public void nextFlow(ActionEvent actionEvent) {
        ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        System.out.print("\ntableStatus = " + tableStatus);
        try {
            reqStatus = bindStatusRequestApproval.getValue().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        if (tableStatus.equals("0")) {
            bindPopupNoItemApproval.show(ph);
        } else {
            idrequest = bindIdRequest.getValue().toString();
//            System.out.println("bindIdRequest = "+bindIdRequest.getValue().toString());
            /* String Cidapname =
                requestClass.getValueInDB("AppModuleDataControl", "PheCidListrequestVO1",
                                          "Idrequest='" + bindIdRequest.getValue().toString() + "'",
                                          "Cidapname");*/
                setRoleTdcReview(requestClass.getValueInDB("AppModuleDataControl", "PheCidListrequestVO1",
                                      "Idrequest='" + bindIdRequest.getValue().toString() + "'",
                                      "Cidapname")); 
//            System.out.println("role tdc = "+getRoleTdcReview());
            /* ViewObject voRequestInput = am.findViewObject("CIDRunNumberVO1");
            voRequestInput.setNamedWhereClauseParam("apNama",Cidapname);
            voRequestInput.executeQuery();
            
            Row r=voRequestInput.first();
            String Value =r.getAttribute("Value")==null ? "":r.getAttribute("Value").toString();
            System.out.println("idrequest selected = " + idrequest);
            System.out.println("Value = " + Value);
              numberRunCid=Value; */
            
            String spv = bindSupervisor.getValue().toString();
            String statusRequest =
                bindStatusRequestApproval.getValue().toString();
            if (UserloginFullName.equals(spv)) {
                if ((statusRequest.equals("Requested")) ||
                    (statusRequest.equals("Reject SPV With Remarks"))) {
                    Usersecure = "spvreview";
                }
            }
            bindPopupSelectedItemApproval.show(ph);
            //refreshTableRequest();
        }
    }

    public HttpSession GetSession() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest request =
            (HttpServletRequest)ctx.getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        return session;
    }

    public BindingContainer getBinding() {
        BindingContainer bindings =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        return bindings;
    }

    public oracle.jbo.domain.Date getNowDate(java.util.Date pJavaDate) {
        return new oracle.jbo.domain.Date(new Timestamp(pJavaDate.getTime()));
    }

    public void refreshTableRequest() {
        ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        ViewObject voDocInput = am.findViewObject("PheCidListdocrequestVO1");
        voDocInput.setWhereClause("IDREQUEST = '" + idrequest + "'");
        tableDocCount = voDocInput.getEstimatedRowCount() + "";
        System.out.print("\ntableDocCount = " + tableDocCount);
        voDocInput.executeQuery();
        //refresh();
        System.out.print("\nIDREQUEST = " +
                         bindIdRequest.getValue().toString());
        oracle.jbo.domain.Date nowDate = getNowDate(new Date());
        System.out.println("\nnow date = " + nowDate);

        ViewObject voRequestInput = am.findViewObject("PheCidAttachmentVO1");
        voRequestInput.setWhereClause("IDREQUEST = '" + idrequest + "'");
        voRequestInput.executeQuery();

        ViewObject voRequestLog = am.findViewObject("PheCidLogVO1");
        voRequestLog.setWhereClause("IDREQUEST = '" + idrequest +
                                    "' AND ACTION <> 'getValueInDB' AND ACTION <> 'Send Email'");
        //voRequestInput.setWhereClause("upper(action) like upper('%remarks%') or upper(action) like upper('%approval%')");
        voRequestLog.executeQuery();
    }

    public void action(ValueChangeEvent valueChangeEvent) {
        idList = bindIdList.getValue().toString();
        String Docnumber =
            requestClass.getValueInDB("AppModuleDataControl", "PheCidListdocrequestVO1",
                                      "upper(idlist)=upper('" + idList + "')",
                                      "Ciddocnumber");
        if (valueChangeEvent.getNewValue().toString().equalsIgnoreCase("APPROVE")) {
            if (Usersecure.equals("tdclead")) {
                bindStatusDocRequest.setValue("Approve TDC Lead");
                bindInputRemarksSV.setVisible(false);
                bindInputRemarksTDCReview.setVisible(false);
                log.log(idrequest, "Approval",
                        "User : " + UserloginFullName + " - " +
                        "Approve TDC Lead - Approve Document " + Docnumber);
                //bindDateApproveTDCLead.setValue(datetime);
            } else {
                bindStatusDocRequest.setValue("Approve SPV");
                bindInputRemarksTDCReview.setVisible(false);
                bindInputRemarksTDCLead.setVisible(false);
                //bindDateApproveSV.setValue(datetime);
                log.log(idrequest, "Approval",
                        "User : " + UserloginFullName + " - " +
                        "Approve SPV - Approve Document " + Docnumber);
            }
        } else if (valueChangeEvent.getNewValue().toString().equalsIgnoreCase("REJECTWITHREMARKS")) {

            if (Usersecure.equals("tdclead")) {
                bindStatusDocRequest.setValue("Reject TDC Lead With Remarks");
                bindInputRemarksSV.setVisible(false);
                bindInputRemarksTDCReview.setVisible(false);
                log.log(idrequest, "Approval",
                        "User : " + UserloginFullName + " - " +
                        "Reject TDC Lead With Remarks - Reject Document " +
                        Docnumber);
            } else {
                bindStatusDocRequest.setValue("Reject SPV With Remarks");
                bindInputRemarksTDCReview.setVisible(false);
                bindInputRemarksTDCLead.setVisible(false);
                log.log(idrequest, "Approval",
                        "User : " + UserloginFullName + " - " +
                        "Reject SPV With Remarks- Reject Document " +
                        Docnumber);
            }

            //            System.out.print("idList Rej= " + idList);
            //            bindFormInputRemarksSV.setVisible(true);
            //            AdfFacesContext.getCurrentInstance().addPartialTarget(bindFormInputRemarksSV);
            //            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            //            bindPopupInputRemarksSV.show(ph);

        }
        multiStatusApproval = bindStatusDocRequest.getValue().toString();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindStatusDocRequest);
        refresh();
    }

    public void execute() {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding method = bindings.getOperationBinding("Execute");
        method.execute();
    }

    public void commit() {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding method = bindings.getOperationBinding("Commit");
        method.execute();
    }

    public void GoToHome(ActionEvent actionEvent) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try { //ctx.getExternalContext().redirect("http://kponwj005.pertamina.com:16300/urm/idcplg?IdcService=GET_DOC_PAGE&Action=GetTemplatePage&Page=HOME_PAGE");
            //
            ctx.getExternalContext().redirect(getIDC_SERVER()+"?IdcService=GET_DOC_PAGE&Action=GetTemplatePage&Page=HOME_PAGE");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(actionEvent);
    }

    public void GoToCID() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            //ctx.getExternalContext().redirect("http://kponwj005.pertamina.com:7070/CID/faces/pages/inboxApproval.jspx?username=" +Userlogin);
            ctx.getExternalContext().redirect(getURL_CID()+"/CID/faces/pages/inboxApproval.jspx?username=" +
                                              Userlogin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AppModuleImpl getAm() {
          AppModuleImpl am =
              (AppModuleImpl) ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
          return am;
      }
    
    public void countTable(String idrequest, String docStatus) {
        Connection dbCon = null;
        Statement stat = null;
        String Query = "";
        ResultSet result = null;
       /*  try {
            //DEV
            dbCon =
                    DriverManager.getConnection("jdbc:oracle:thin:@PHEKPDBDV09.pertamina.com:1521:owc12cdv",
                                                "DEVX_OCS", "Pertaminahe19");

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
        } */
        
        try {
            Query =
                    "select idlist from PHE_CID_LISTDOCREQUEST where Idrequest='" +
                    idrequest + "' and CIDSTATUSDOCREQUEST='" + docStatus +
                    "'";
            System.out.println("Query = " + Query);
            stat = getAm().getDBTransaction().createStatement(1);
//            stat = dbCon.createStatement();
            result = stat.executeQuery(Query);
            checkTable = "0";
            while (result.next()) {
                checkTable = "1";
                System.out.println("ADA HASIL");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stat.close();
//            dbCon.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        try {
            ApplicationModule am =
                ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
            ViewObject voListApproval =
                am.findViewObject("PheCidListrequestVO1");
            if (Usersecure.equals("tdclead")) {
                voListApproval.setWhereClause("Cidrequestorsupervisor = '" +
                                              UserloginFullName +
                                              "' OR (Cidstatusrequest <> 'Requested' AND Cidstatusrequest <> 'Reject SPV With Remarks' AND Cidstatusrequest <> 'Draft' AND Cidstatusrequest <> 'Publish' AND Cidstatusrequest <> 'Approve TDC Lead')");
            } else if (Usersecure.equals("tdcreview")) {
                voListApproval.setWhereClause("Cidstatusrequest <> 'Requested' AND Cidstatusrequest <> 'Reject SPV With Remarks' AND Cidstatusrequest <> 'Draft' AND Cidstatusrequest <> 'Publish' AND Cidstatusrequest <> 'Approve TDC Lead'");
            } else {
                voListApproval.setWhereClause("Cidrequestorsupervisor = '" +
                                              UserloginFullName +
                                              "' AND (Cidstatusrequest = 'Requested' OR Cidstatusrequest = 'Reject SPV With Remarks') ");
            }
            voListApproval.executeQuery();
        } catch (Exception e) {
        }

        bindTableInboxRequest = new RichTable();
        bindTableInboxListRequest = new RichTable();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableInboxRequest);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableInboxListRequest);
    }

    public void update(String column, String method, String status,
                       String id) {
        Connection dbCon = null;
        Statement stat = null;
        String UpdateQuery = "";
        //ResultSet result = null;
        try {
            //DEV
            dbCon =DriverManager.getConnection(getUrl_Database(),getUser_Database(), getPass_Database());
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
            if (column.equals("cidstatusdocrequest")) {
                if (method.equals("doc")) {
                    UpdateQuery =
                            "update phe_cid_listdocrequest set cidstatusdocrequest='" +
                            status + "' where idlist=" + id;
                } else if (method.equals("req")) {
                    UpdateQuery =
                            "update phe_cid_listrequest set cidstatusrequest='" +
                            status + "' where idrequest=" + id;
                }
            } else if (column.equals("cidremarkspv")) {
                if (method.equals("doc")) {
                    UpdateQuery =
                            "update phe_cid_listdocrequest set cidremarksv='" +
                            status + "' where idlist=" + id;
                }
            }
            System.out.println("Query = " + UpdateQuery);
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

    public void insert(String IDREQUEST, String NUMCID, String CIDCREATEDATE,
                       String CHECKINTYPE, String CHECKINLINK,
                       String CHECKINNOTE) {
        Connection dbCon = null;
        Statement stat = null;
        String InsertQuery = "";
        //ResultSet result = null;

        try {
            //DEV
            dbCon =DriverManager.getConnection(getUrl_Database(),getUser_Database(), getPass_Database());
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
            InsertQuery =
                    "INSERT INTO PHE_CID_CHECKIN ( IDCHECKIN, IDREQUEST,NUMCID,CIDCREATEDATE,CHECKINTYPE,CHECKINLINK,CHECKINNOTE) VALUES (DEVX_OCS.PHE_CID_CHECKIN_SEQ.nextval,'" +
                    IDREQUEST + "','" + NUMCID + "'," + CIDCREATEDATE + ",'" +
                    CHECKINTYPE + "','" + CHECKINLINK + "','" + CHECKINNOTE +
                    "')";
            System.out.println("Query = " + InsertQuery);
            stat = dbCon.createStatement();
            stat.executeUpdate(InsertQuery);
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

    public void setBindTableInboxRequest(RichTable bindTableInboxRequest) {
        this.bindTableInboxRequest = bindTableInboxRequest;
    }

    public RichTable getBindTableInboxRequest() {
        return bindTableInboxRequest;
    }

    public void setBindTableRequestApproval(RichPanelCollection bindTableRequestApproval) {
        this.bindTableRequestApproval = bindTableRequestApproval;
    }

    public RichPanelCollection getBindTableRequestApproval() {
        return bindTableRequestApproval;
    }

    public void setBindPopupSelectedItemApproval(RichPopup bindPopupSelectedItemApproval) {
        this.bindPopupSelectedItemApproval = bindPopupSelectedItemApproval;
    }

    public RichPopup getBindPopupSelectedItemApproval() {
        return bindPopupSelectedItemApproval;
    }

    public void setBindPopupNoItemApproval(RichPopup bindPopupNoItemApproval) {
        this.bindPopupNoItemApproval = bindPopupNoItemApproval;
    }

    public RichPopup getBindPopupNoItemApproval() {
        return bindPopupNoItemApproval;
    }

    public void setBindIdRequest(RichInputText bindIdRequest) {
        this.bindIdRequest = bindIdRequest;
    }

    public RichInputText getBindIdRequest() {
        return bindIdRequest;
    }

    public void setBindTableInboxListRequest(RichTable bindTableInboxListRequest) {
        this.bindTableInboxListRequest = bindTableInboxListRequest;
    }

    public RichTable getBindTableInboxListRequest() {
        return bindTableInboxListRequest;
    }

    public void setBindAction(RichSelectOneChoice bindAction) {
        this.bindAction = bindAction;
    }

    public RichSelectOneChoice getBindAction() {
        return bindAction;
    }

    public void setBindIdList(RichInputText bindIdList) {
        this.bindIdList = bindIdList;
    }

    public RichInputText getBindIdList() {
        return bindIdList;
    }

    public void setBindStatusDocApproval(RichInputText bindStatusDocApproval) {
        this.bindStatusDocApproval = bindStatusDocApproval;
    }

    public RichInputText getBindStatusDocApproval() {
        return bindStatusDocApproval;
    }

    public void setBindStatusRequestApproval(RichInputText bindStatusRequestApproval) {
        this.bindStatusRequestApproval = bindStatusRequestApproval;
    }

    public RichInputText getBindStatusRequestApproval() {
        return bindStatusRequestApproval;
    }

    public void setBindRemarksv(RichInputText bindRemarksv) {
        this.bindRemarksv = bindRemarksv;
    }

    public RichInputText getBindRemarksv() {
        return bindRemarksv;
    }

    public void setBindPopupInputRemarksSV(RichPopup bindPopupInputRemarksSV) {
        this.bindPopupInputRemarksSV = bindPopupInputRemarksSV;
    }

    public RichPopup getBindPopupInputRemarksSV() {
        return bindPopupInputRemarksSV;
    }

    public void setBindPopupRespondAllDocumentList(RichPopup bindPopupRespondAllDocumentList) {
        this.bindPopupRespondAllDocumentList = bindPopupRespondAllDocumentList;
    }

    public RichPopup getBindPopupRespondAllDocumentList() {
        return bindPopupRespondAllDocumentList;
    }

    public void setBindPopupSubmit(RichPopup bindPopupSubmit) {
        this.bindPopupSubmit = bindPopupSubmit;
    }

    public RichPopup getBindPopupSubmit() {
        return bindPopupSubmit;
    }

    public void submitTDClead() {
        utils update = new utils();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        System.out.print(sdf.format(new java.util.Date()));
        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        String query =
            "update phe_cid_listrequest set ciddateapprovetdclead=(to_date('" +
            sdf.format(new java.util.Date()) +
            "', 'yyyy/mm/dd hh24:mi:ss')) where idrequest=" + idrequest;
        try {
            update.update(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        checkSubmit("TDCLead", "Reject TDC Lead With Remarks",
                    "Reject TDC Lead With Remarks", "Approve TDC Lead");
        countTable(idrequest, "Reject TDC Lead With Remarks");
        if (checkTable.equals("1")) {
            bindPopupSubmit.show(ph);
        } else {
            bindTDCApproval.show(ph);
        }
    }

    public String submit() {
        commit();
        utils update = new utils();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        System.out.print(sdf.format(new java.util.Date()));
        countTable(idrequest, "Requested");
        if (Usersecure.equals("spvreview")) {
            countTable(idrequest, "Requested");
        } else if (Usersecure.equals("tdclead")) {
            countTable(idrequest, "Approve SPV");
        }

        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        if (checkTable.equals("1")) {
            bindPopupRespondAllDocumentList.show(ph);
        } else {
            if (Usersecure.equals("tdclead")) {
                requestClass = new requestClass();
                String Numcid =
                    requestClass.getValueInDB("AppModuleDataControl",
                                              "PheCidCreateVO1",
                                              "IDREQUEST = '" + idrequest +
                                              "'", "Numcid");
                if (Numcid.equals("")) {
                    submitTDClead();
                } else {
                    bindLabelCIDNumber.setValue(Numcid);
                    bindPopupCheckCID.show(ph);
                }
            } else if (Usersecure.equals("tdcreview")) {
                String query =
                    "update phe_cid_listrequest set cidapprovetdc=(to_date('" +
                    sdf.format(new java.util.Date()) +
                    "', 'yyyy/mm/dd hh24:mi:ss')) where idrequest=" +
                    idrequest;
                try {
                    update.update(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //checkSubmit("TDCReview", "Reject TDC Review With Remarks","Reject TDC Review With Remarks", "TDC Review");
                bindPopupSubmit.show(ph);
            } else {
                String query =
                    "update phe_cid_listrequest set ciddateapprovesv=(to_date('" +
                    sdf.format(new java.util.Date()) +
                    "', 'yyyy/mm/dd hh24:mi:ss')) where idrequest=" +
                    idrequest;
                try {
                    update.update(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                checkSubmit("SPV", "Reject SPV With Remarks",
                            "Reject SPV With Remarks", "Approve SPV");
                bindPopupSubmit.show(ph);
            }

            refresh();
            commit();
        }

        return null;
    }

    public void checkSubmit(String from, String reject, String docReject,
                            String docApprove) {
        requestClass = new requestClass();
        String Cidrequestor =
            requestClass.getValueInDB("AppModuleDataControl", "PheCidListrequestVO1",
                                      "IDREQUEST = '" + idrequest + "'",
                                      "Cidrequestor");
        String Demail =
            requestClass.getValueInDB("AppModuleDataControl", "UsersVO1",
                                      "DFULLNAME = '" + Cidrequestor + "'",
                                      "Demail");
        String CidrequestorUsername =
            requestClass.getValueInDB("AppModuleDataControl", "UsersVO1",
                                      "DFULLNAME = '" + Cidrequestor + "'",
                                      "Dname");

        String tdclead =
            requestClass.getValueInDB("AppModuleDataControl", "PheCidListrequestVO1",
                                      "IDREQUEST = '" + idrequest + "'",
                                      "Cidtdclead");
        String DemailTdclead =
            requestClass.getValueInDB("AppModuleDataControl", "UsersVO1",
                                      "DFULLNAME = '" + tdclead + "'",
                                      "Demail");
        String tdcleadUsername =
            requestClass.getValueInDB("AppModuleDataControl", "UsersVO1",
                                      "DFULLNAME = '" + tdclead + "'",
                                      "Dname");
        //emailnya
        countTable(idrequest, reject);
        if (checkTable.equals("1")) {
            statusReject = "reject";
            update("cidstatusdocrequest", "req", docReject, idrequest);
        } else {
            statusReject = "approve";
            update("cidstatusdocrequest", "req", docApprove, idrequest);
        }

        if (Usersecure.equals("tdclead")) {
            tdcReviewEmail();
            SendEmail(Demail, CidrequestorUsername, "requestor");
        } else if (Usersecure.equals("tdcreview")) {
            //SendEmail(Demail, CidrequestorUsername, "requestor");
            SendEmail(DemailTdclead, tdcleadUsername, "tdc");
        } else {
            SendEmail(Demail, CidrequestorUsername, "requestor");
            String statusRequest =
                requestClass.getValueInDB("AppModuleDataControl",
                                          "PheCidListrequestVO1",
                                          "IDREQUEST = '" + idrequest + "'",
                                          "Cidstatusrequest");
            if (statusRequest.equals("Approve SPV")) {
                tdcReviewEmail();
                SendEmail(DemailTdclead, tdcleadUsername, "tdc");
            }
        }
    }

    public void tdcReviewEmail() {
        System.out.print("\ntdcReviewEmail");
        requestClass = new requestClass();
        ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        ViewObject voUserTDCReview =
            am.findViewObject("UsersecurityattributesVO1");
        voUserTDCReview.setWhereClause("upper (dattributetype)=upper('role') and upper(dattributename)=upper('CID_"+getRoleTdcReview().replaceAll("\\s+","")+"_REVIEW')");
        voUserTDCReview.executeQuery();
        String first = "";
        String tdcDusername = "";
        String tdcFullname = "";
        System.out.print("\nlist TDC user");
        while (voUserTDCReview.hasNext()) {
            Row row = null;
            if (first.equals("")) {
                row = voUserTDCReview.first();
                first = "1";
            } else {
                row = voUserTDCReview.next();
            }
            try {
                System.out.print("\nget TDC Dusername");
                tdcDusername = row.getAttribute("Dusername").toString();
                System.out.print("\nget TDC tdcFullname");
                tdcFullname =
                        requestClass.getValueInDB("AppModuleDataControl", "UsersVO1",
                                                  "DNAME = '" + tdcDusername +
                                                  "'", "Dfullname");
                System.out.print("\nget TDC Demail");
                String Demail =
                    requestClass.getValueInDB("AppModuleDataControl",
                                              "UsersVO1",
                                              "DFULLNAME = '" + tdcFullname +
                                              "'", "Demail");
                    System.out.print("\nstatusReject masuk");
                    System.out.print("\nidrequest = "+idrequest);
                String statusRequest =
                        requestClass.getValueInDB("AppModuleDataControl",
                                                  "PheCidListrequestVO1",
                                                  "IDREQUEST = '" + idrequest + "'",
                                                  "Cidstatusrequest");
                System.out.print("\nstatusRequest = "+statusRequest);
                    if (statusRequest.equals("Approve SPV")) {
                        System.out.print("\nEmail Approve SPV");
                        SendEmail(Demail, tdcDusername, "requestor");
                    }
                    if (statusRequest.equals("Approve TDC Lead")) {
                        System.out.print("\nEmail Approve SPV");
                        SendEmail(Demail, tdcDusername, "create");
                    }
                    if (statusRequest.equals("Publish")) {
                        System.out.print("\nEmail Publish");
                        SendEmail(Demail, tdcDusername, "publish");
                    }
                    if (statusRequest.equals("create")) {
                            System.out.print("\nEmail create");
                        SendEmail(Demail, tdcDusername, "create");}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void SendEmail(String Demail, String toUsername, String toStatus) {
        requestClass = new requestClass();
        System.out.print("\ntoStatus = " + toStatus);
        System.out.print("\nidrequest publish= " + idrequest);
        ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        ViewObject voGetPar = am.findViewObject("PheCidListdocrequestVO1");
        voGetPar.setWhereClause("IDREQUEST = '" + idrequest + "'");
        voGetPar.executeQuery();

        ViewObject voGetParReq = am.findViewObject("PheCidListrequestVO1");
        voGetParReq.setWhereClause("IDREQUEST = '" + idrequest + "'");
        voGetParReq.executeQuery();
        //Row rowReq = voGetParReq.first();
        
        String countDocList = voGetPar.getEstimatedRowCount()+"";
        String first = "";
        String Ciddocnumber = "";
        String Ciddocname = "";
        String Ciddoctitle = "";
        String documents = "";
        String purpose ="";
        try {
            Row row = voGetParReq.first();
            purpose=row.getAttribute("Cidpurpose").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(countDocList.equals("1")){
                Row row = voGetPar.first();
                try {
                    Ciddocnumber = row.getAttribute("Ciddocnumber").toString();
                    String DID = row.getAttribute("Diddoc").toString();
                    Ciddocname = requestClass.getValueInDB("AppModuleDataControl", "DocmetaVO1",
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
        }else{
        while (voGetPar.hasNext()) {
            Row row = null;
            if (first.equals("")) {
                row = voGetPar.first();
                first = "1";
            } else {
                row = voGetPar.next();
            }
            try {
                Ciddocnumber = row.getAttribute("Ciddocnumber").toString();
                String DID = row.getAttribute("Diddoc").toString();
                Ciddocname = requestClass.getValueInDB("AppModuleDataControl", "DocmetaVO1",
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
        String pages = "";
        if (toStatus.equals("requestor")) {
            pages = "request";
        } else if (toStatus.equals("publish")) {
            pages = "inboxApproval";
        } else if (toStatus.equals("tdc")){
            if(numberCid.equals("")){}else{
            pages = "tdc";}
        }
        String link = "";
        String head = "";
        String bodyHead = "";
        String bodyFoot = "";
        if (toStatus.equals("publish")) {
            bodyFoot = ">link</a> to open publish file into the system.";
            bodyHead = "You have received notification of Publish CID ";
            head = "CID Published : "+purpose;
            link =
"CID Number is <b>" + numberCid + "</b>. Please see click this <a href=" +
 getIDC_SERVER()+"?IdcService=PHE_LIST_PUBLISH_CID&startRow=1&endRow=100&keyword=&requestor=" +
 toUsername+"&numcid="+numberCid;
        } else if (toStatus.equals("create")) {
            bodyFoot = ">link</a> to open create file into the system.";
            bodyHead = "You have received notification of Create CID ";
            head = "CID Created : "+purpose;
            link =
"CID Number is <b>" + numberCid + "</b>. Please see click this <a href=" +
 getIDC_SERVER()+"?IdcService=PHE_LIST_CID&startRow=1&endRow=100&keyword=&username=" +
 toUsername+"&numcid="+numberCid;
            if((pages.equals("tdc"))&&(statusReject.equals("approve")))
            {link="";
             bodyFoot="CID has been Created.";}
        } else {
            bodyFoot =
                    ">link</a> to open CID request file(s) into the system.";
            bodyHead = "You have received a CID respond from";
            head = "CID Approval : "+purpose;
           /*
            link =
"Please see click this <a href=" + "http://kponwjis013.pertamina.com:7070/CID/faces/pages/" +
                    //"http://kponwjap005.pertamina.com:7070/CID/faces/pages/"
                    pages + ".jspx?username=" + toUsername;*/
            link = "Please see click this <a href="
                   +getIDC_SERVER()+"?IdcService=PHE_LIST_CID&startRow=1&endRow=100&keyword="+statusReject+"&username="+toUsername+"&numcid=";
        }
        System.out.print("\nLINK = " + link);
        String body = "<html>\n" +
            "<head></head>\n" +
            "<body style=\"font-family:arial\">Dear Sir/Madam<br/><br/>" +
            bodyHead + " <b>" + UserloginFullName +
            "</b>  for following document(s):<br/><br/>\n<table>" +
            "<tr><th style=\"text-align:center;background-color:khaki;padding:8px 10px;\" align=\"center\">Document Name</th><th></th><th style=\"text-align:center;background-color:khaki;padding:8px 10px;\" align=\"center\">Document Title</th></tr>" +
            documents + "</table><br/><br/>" + link + bodyFoot +
            "<br/>Thank you for your attention.</body></html>";

        requestClass.email(Demail, head, body);
    }

    public void setBindInputRemarksSV(RichInputText bindInputRemarksSV) {
        this.bindInputRemarksSV = bindInputRemarksSV;
    }

    public RichInputText getBindInputRemarksSV() {
        return bindInputRemarksSV;
    }

    public void setBindFormInputRemarksSV(RichPanelFormLayout bindFormInputRemarksSV) {
        this.bindFormInputRemarksSV = bindFormInputRemarksSV;
    }

    public RichPanelFormLayout getBindFormInputRemarksSV() {
        return bindFormInputRemarksSV;
    }

    public void saveInputRemarksSV(ActionEvent actionEvent) {

        if (Usersecure.equals("tdclead")) {
            multiRemarksTDCLead =
                    bindInputRemarksTDCLead.getValue().toString();
            try {
                log.log(idrequest, "remarks TDC Lead",
                        "User : " + UserloginFullName + " - " +
                        bindInputRemarksTDCLead.getValue().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (Usersecure.equals("tdcreview")) {
            multiRemarksTDCReview =
                    bindInputRemarksTDCReview.getValue().toString();
            try {
                log.log(idrequest, "remarks TDC Review",
                        "User : " + UserloginFullName + " - " +
                        bindInputRemarksTDCReview.getValue().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            multiRemarksSPV = bindInputRemarksSV.getValue().toString();
            try {
                log.log(idrequest, "remarks SPV",
                        "User : " + UserloginFullName + " - " +
                        bindInputRemarksSV.getValue().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        refreshTableRequest();
        bindAction.resetValue();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindAction);

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

    public void setBindStatusDocRequest(RichInputText bindStatusDocRequest) {
        this.bindStatusDocRequest = bindStatusDocRequest;
    }

    public RichInputText getBindStatusDocRequest() {
        return bindStatusDocRequest;
    }

    public void setBindInputRemarksTDCReview(RichInputText bindInputRemarksTDCReview) {
        this.bindInputRemarksTDCReview = bindInputRemarksTDCReview;
    }

    public RichInputText getBindInputRemarksTDCReview() {
        return bindInputRemarksTDCReview;
    }

    public void setBindInputRemarksTDCLead(RichInputText bindInputRemarksTDCLead) {
        this.bindInputRemarksTDCLead = bindInputRemarksTDCLead;
    }

    public RichInputText getBindInputRemarksTDCLead() {
        return bindInputRemarksTDCLead;
    }

    public void setBindTDCApproval(RichPopup bindTDCApproval) {
        this.bindTDCApproval = bindTDCApproval;
    }

    public RichPopup getBindTDCApproval() {
        return bindTDCApproval;
    }

    public void bindSubmitApproval(ActionEvent actionEvent) {
        System.out.print(actionEvent);
        // Add event code here...
        GoToCID();
    }

    public String submitTDCLead() {
        // create CID form
        log.log(idrequest, "System Info",
                "TDC Lead button action");
        System.out.print("\nnumberCid = " + numberCid);
        printCID(getWeblogicusername(), getWeblogicpassword());
        return null;
    }

    public void setBindCreateCIDNumcid(RichInputText bindCreateCIDNumcid) {
        this.bindCreateCIDNumcid = bindCreateCIDNumcid;
    }

    public RichInputText getBindCreateCIDNumcid() {
        return bindCreateCIDNumcid;
    }

    public void setBindCreateCIDIdrequest(RichInputText bindCreateCIDIdrequest) {
        this.bindCreateCIDIdrequest = bindCreateCIDIdrequest;
    }

    public RichInputText getBindCreateCIDIdrequest() {
        return bindCreateCIDIdrequest;
    }

    public void setBindCreateCIDCreatedate(RichInputDate bindCreateCIDCreatedate) {
        this.bindCreateCIDCreatedate = bindCreateCIDCreatedate;
    }

    public RichInputDate getBindCreateCIDCreatedate() {
        return bindCreateCIDCreatedate;
    }

    public void setBindCreateCIDTdclead(RichInputText bindCreateCIDTdclead) {
        this.bindCreateCIDTdclead = bindCreateCIDTdclead;
    }

    public RichInputText getBindCreateCIDTdclead() {
        return bindCreateCIDTdclead;
    }

    public void setBindCreateCIDRequestor(RichInputText bindCreateCIDRequestor) {
        this.bindCreateCIDRequestor = bindCreateCIDRequestor;
    }

    public RichInputText getBindCreateCIDRequestor() {
        return bindCreateCIDRequestor;
    }

    public void setBindCreateCIDStatuscid(RichInputText bindCreateCIDStatuscid) {
        this.bindCreateCIDStatuscid = bindCreateCIDStatuscid;
    }

    public RichInputText getBindCreateCIDStatuscid() {
        return bindCreateCIDStatuscid;
    }
    public static Connection getConnection() throws Exception, NullPointerException {
           
           InitialContext ctx=new InitialContext();
           DataSource ds = (DataSource)ctx.lookup("jdbc/phmcode");
           Connection conn = ds.getConnection();         
           return conn;
       }
    
    
    public static Connection getConnectionDS(String dsName) throws NamingException,
                                                                   SQLException {
        Connection con = null;
        DataSource datasource = null;

        Context initialContext = new InitialContext();
        if (initialContext == null) {
        }
        datasource = (DataSource)initialContext.lookup(dsName);
        if (datasource != null) {
            con = datasource.getConnection();
        } else {
            System.out.println("Failed to Find JDBC DataSource.");
        }
        return con;
    }
    
    public String formCreateCID() {
        ApplicationModule am1 =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ApNama="";
        requestClass = new requestClass();
        Cidapname=requestClass.getValueInDB("AppModuleDataControl", "PheCidListrequestVO1",
                                  "IDREQUEST = '" + idrequest + "'",
                              "Cidapname");
        ViewObject voGetParReq = am1.findViewObject("PheCidListrequestVO1");
        voGetParReq.setWhereClause("IDREQUEST = '" + idrequest + "'");
        voGetParReq.executeQuery();
        Row rowReq = voGetParReq.first();
        String tdcLead=rowReq.getAttribute("Cidtdclead")==null ? "":rowReq.getAttribute("Cidtdclead").toString();
        String apnama=rowReq.getAttribute("Cidapname")==null ? "":rowReq.getAttribute("Cidapname").toString();
       System.out.println("apnama "+apnama.replaceAll("\\s+","").substring(3)+"  "+apnama);
        /*  setJabatan(requestClass.getValueInDB("AppModuleDataControl",
                                                 "PheConfigVO1",
                                                 "KEY_CONFIG ='TDCLEAD_"+apnama.replaceAll("\\s+","").substring(3)+"'"+"AND KEY_VALUE='"+tdcLead+"'",
                                                 "KEY_DISPLAY"));
        System.out.println("Jabatan "+getJabatan()+" and "+tdcLead); */
        Connection con1 = null;
        String num1="";
        try {
            con1 = getConnectionDS("jdbc/CIDconn");
        } catch (SQLException e) {
        } catch (NamingException e) {
        }
        try {
            PreparedStatement stmt =
                con1.prepareStatement("SELECT KEY_DISPLAY FROM PHE_CONFIG WHERE UPPER(KEY_CONFIG)=UPPER('"+"TDCLEAD_"+apnama.replaceAll("\\s+","").substring(3)+"')"+"AND UPPER(KEY_VALUE)=UPPER('"+tdcLead+"')");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("nilai display " + rs.getString(1));
                num1=rs.getString(1)==null ? "" :rs.getString(1).toString();
                setJabatan(num1);
            }
            rs.close();

        } catch (SQLException e) {
            throw new JboException(e);
        }
        
        
       String cekAbbrivation=requestClass.getValueInDB("AppModuleDataControl",
                                                 "PheConfigVO1",
                                                 "KEY_CONFIG = 'ABBREVIATION' AND KEY_VALUE ='"+Cidapname+"'",
                                                 "KeyDisplay");
               if(cekAbbrivation.length()>0){ 
                   ApNama=cekAbbrivation;
               }else{
                   ApNama=Cidapname.replaceAll("\\s+","");
               }
        ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        ViewObject voGetNumberRunCid = am.findViewObject("CIDrunNumberAPVO1");
        voGetNumberRunCid.setNamedWhereClauseParam("APNAMA",Cidapname.replaceAll("\\s+",""));
        voGetNumberRunCid.executeQuery();
//        System.out.println("ap : "+Cidapname.replaceAll("\\s+",""));
        bindApNameinsert.setSubmittedValue(Cidapname.replaceAll("\\s+",""));
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindApNameinsert);
        Connection con = null;
        String num="";
        try {
            con = getConnectionDS("jdbc/CIDconn");
        } catch (SQLException e) {
        } catch (NamingException e) {
        }
        try {
            PreparedStatement stmt =
                con.prepareStatement("SELECT MAX(ROWNUM)+1 AS RUNNUMBER FROM PHE_CID_CREATE WHERE UPPER(APNAME)=UPPER("+"'"+Cidapname.replaceAll("\\s+","")+"')");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("nilai " + rs.getString(1));
                num=rs.getString(1)==null ? "" :rs.getString(1).toString();
            }
            rs.close();

        } catch (SQLException e) {
            throw new JboException(e);
        }
        
//        if(voGetNumberRunCid.getEstimatedRowCount()>0){
        if(!num.equalsIgnoreCase("")){
            numberRunCid=num;
        }else{
            numberRunCid="1";
        }
        //String CIDid = intToString(Integer.parseInt(bindCreateCIDId.getValue().toString()), 4);
        // String number = "PHEONWJ-CID-" + (sdf.format(new java.util.Date())) + "-" + CIDid;
        String number =
            ApNama+"-CID-" + (sdf.format(new java.util.Date())) + "-" +numberRunCid;
            //bindCreateCIDId.getValue().toString();
        
        /* tdclead =
                requestClass.getValueInDB("AppModuleDataControl", "PheConfigVO1",
                                          "KEY_CONFIG = 'TDCLEAD'",
                                          "KeyValue"); */
        tdclead=requestClass.getValueInDB("AppModuleDataControl", "PheCidListrequestVO1",
                                  "IDREQUEST = '" + idrequest + "'",
                                  "Cidtdclead");
        requestor =
                requestClass.getValueInDB("AppModuleDataControl", "PheCidListrequestVO1",
                                          "IDREQUEST = '" + idrequest + "'",
                                          "Cidrequestor");
        purpose =
                requestClass.getValueInDB("AppModuleDataControl", "PheCidListrequestVO1",
                                          "IDREQUEST = '" + idrequest + "'",
                                          "Cidpurpose");
        System.out.print("\nCreate CID = tdclead :" + tdclead +
                         " requestor :" + requestor + " purpose :" + purpose);
        oracle.jbo.domain.Timestamp datetime =
            new oracle.jbo.domain.Timestamp(System.currentTimeMillis());
        numberCid = number;
        bindCreateCIDNumcid.setValue(number);
        bindCreateCIDIdrequest.setValue(idrequest);
        bindCreateCIDTdclead.setValue(tdclead);
        bindCreateCIDRequestor.setValue(requestor);
        bindCreateCIDStatuscid.setValue("Created");
        bindCreateCIDCreatedate.setValue(datetime);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCreateCIDCreatedate);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCreateCIDIdrequest);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCreateCIDNumcid);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCreateCIDTdclead);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCreateCIDRequestor);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCreateCIDStatuscid);
        log.log(idrequest, "Create CID",
                "Document Number : " + number + " - " + requestor);
        return null;
    }

    public static String intToString(int num, int digits) {
        StringBuffer s = new StringBuffer(digits);
        int zeroes = digits - (int)(Math.log(num) / Math.log(10)) - 1;
        for (int i = 0; i < zeroes; i++) {
            s.append(0);
        }
        return s.append(num).toString();
    }

    public void setBindCreateCIDId(RichInputText bindCreateCIDId) {
        this.bindCreateCIDId = bindCreateCIDId;
    }

    public RichInputText getBindCreateCIDId() {
        return bindCreateCIDId;
    }

    public oracle.jbo.domain.Date GetNewDate(java.util.Date pJavaDate) throws ParseException {
        SimpleDateFormat formater =
            new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String tempDate =
            formater.format(pJavaDate).replace("00:00:00", "23:59:59");
        return new oracle.jbo.domain.Date(new Timestamp(formater.parse(tempDate).getTime()));
    }

    public void printCID(String username, String password) {
        //create CID form
        try {
            log.log(idrequest, "System Info",
                    "RIDC connection");
            System.out.print("\nprint idrequest  = " + idrequest);
            RIDCClass ridc = new RIDCClass(username, password);

            String numberFile = numberCid + " - " + requestor;
            log.log(idrequest, "System Info",
                    "File Number = "+numberFile);
            System.out.print("\nnumberFile  = " + numberFile);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            System.out.print("\ndate = " + sdf.format(new java.util.Date()));
            log.log(idrequest, "System Info",
                    "Create CID form time = "+sdf.format(new java.util.Date()));

            File fileasli = null;
            log.log(idrequest, "System Info", "find base template");
            fileasli = new File("C:\\CID_temp\\PHEONWJ-CID-yyyymmdd-0000 - Requestor Name.xls");
//            fileasli =
//                    new File("E:\\Oracle\\Config\\user_projects\\domains\\base_domain\\CID_temp\\PHEONWJ-CID-yyyymmdd-0000 - Requestor Name.xls");
            log.log(idrequest, "System Info",
                    "Set Base Template");
            FileInputStream inp = null;
            //bikin atau edit template
            inp = new FileInputStream(fileasli);
            log.log(idrequest, "System Info",
                    "remake file CID from base template");
            //System.out.print("inp = "+inp.available());
            HSSFWorkbook my_xls_workbook = new HSSFWorkbook(inp);
            my_xls_workbook.setActiveSheet(0);
            HSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0);
            
            log.log(idrequest, "System Info",
                    "Set VO PheCidListdocrequestVO1");
            Cell cell = null;
            ApplicationModule am =
                ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
            ViewObject voGetPar = am.findViewObject("PheCidListdocrequestVO1");
            voGetPar.setWhereClause("IDREQUEST = '" + idrequest + "'");
            voGetPar.executeQuery();
            log.log(idrequest, "System Info",
                    "Set VO PheCidListrequestVO1");
            ViewObject voGetParReq = am.findViewObject("PheCidListrequestVO1");
            voGetParReq.setWhereClause("IDREQUEST = '" + idrequest + "'");
            voGetParReq.executeQuery();
            log.log(idrequest, "System Info",
                    "Set VO Row PheCidListrequestVO1 to first");
            Row rowReq = voGetParReq.first();
            
            int i = 17;
            int j = 2;
            int r = 1;
            String first = "";
            String contractorfirst = "";
            String contractoruserfirst = "";
            String Ciddocnumber = "";
            String Ciddoctitle = "";
            String Cidremarkrequestor = "";
            String Cidcontractor = "";
            String Cidinternaluser = "";
            String Cidusercontractor = "";
            String Cidrequestor = "";
            String Cidrequestorsupervisor = "";
            String Cidrequestorsupervisorposition = "";
            String Cidrequestorposition = "";
            String Cidpurpose = "";
            String Cidtdclead = "";
            String xdocname = "";
            log.log(idrequest, "System Info",
                    "Set VO Row PheCidListdocrequestVO1 to first");
            Row rowDoc = voGetPar.first();
            try {
                log.log(idrequest, "System Info",
                        "get Value In DB DocmetaVO1");
                requestClass = new requestClass();
                String Diddoc = rowDoc.getAttribute("Diddoc").toString();
                xdocname =
                        requestClass.getValueInDB("AppModuleDataControl", "DocmetaVO1",
                                                  "DID = '" + Diddoc + "'",
                                                  "Xdocname");
                ViewObject xdocnameUpdate = am.findViewObject("DocmetaVO1");
                xdocnameUpdate.setWhereClause("XDOCNAME = '" + xdocname +
                                              "' AND ((XDOCPURPOSE = 'Published' OR XDOCPURPOSE = 'Native' ) AND (XSTATUS='Implemented' OR XSTATUS='Native')) AND XSTATUS is not null");
                xdocnameUpdate.executeQuery();
                log.log(idrequest, "System Info",
                        "xdocnameUpdate");
              /*  try{
                    log.log(idrequest, "System Info",
                            "xdocnameUpdate first data");
                    Row rowUpdate = xdocnameUpdate.first();
                    Diddoc = rowUpdate.getAttribute("Did").toString();
                    if (Diddoc != null) {
                        updateMetadataDocList(Diddoc);
                    }
                }catch(Exception e){                
                    log.log(idrequest, "System Info",
                        "xdocnameUpdate first data error");}
*/
                try{
                    log.log(idrequest, "System Info",
                            "xdocnameUpdate multi data");
                    while (xdocnameUpdate.hasNext()) {
                        Row rowUpdate = xdocnameUpdate.next();
                        Diddoc = rowUpdate.getAttribute("Did").toString();
                        if (Diddoc != null) {
                            updateMetadataDocList(Diddoc);
                        }
                    }
                }catch(Exception e){                
                    log.log(idrequest, "System Info",
                        "xdocnameUpdate multi data error");}
                

            } catch (Exception e) {
                e.printStackTrace();
            }
            // System.out.print("\nCiddocnumber = "+Ciddocnumber);
            System.out.print("\n list create = " + r);
            try {
                Ciddocnumber = rowDoc.getAttribute("Ciddocnumber").toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Ciddoctitle = rowDoc.getAttribute("Ciddoctitle").toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Cidremarkrequestor =
                        rowDoc.getAttribute("Cidremarkrequestor").toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Cidcontractor =
                        rowDoc.getAttribute("Cidcontractor").toString();
                contractorfirst = "1";
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Cidusercontractor =
                        rowDoc.getAttribute("Cidusercontractor").toString();
                contractoruserfirst = "1";
            } catch (Exception e) {
                e.printStackTrace();
            }

            cell = my_worksheet.getRow(i).getCell(j);
            cell.setCellValue(r + "");
            cell = my_worksheet.getRow(i).getCell(j + 1);
            cell.setCellValue(xdocname);
            cell = my_worksheet.getRow(i).getCell(j + 3);
            cell.setCellValue(Ciddoctitle);
            cell = my_worksheet.getRow(i).getCell(j + 5);
            cell.setCellValue(Cidremarkrequestor);
            i = 17;
            j = 2;
            r = 1;
            int baris = 0;
            String sheet = "";
            log.log(idrequest, "System Info",
                "voGetPar multi data");
            while (voGetPar.hasNext()) {
                if (baris < 9) {
                    my_worksheet = my_xls_workbook.getSheetAt(0);
                } else {
                    if ((baris >= 9)&&(baris < 29)) {
                    my_worksheet = my_xls_workbook.getSheetAt(1);}
                    else if ((baris >= 29)&&(baris < 49)) {
                    my_worksheet = my_xls_workbook.getSheetAt(2);}
                    else if ((baris >= 49)&&(baris < 69)) {
                    my_worksheet = my_xls_workbook.getSheetAt(3);}
                    else if (baris >= 69) {
                    my_worksheet = my_xls_workbook.getSheetAt(4);}
                    if (sheet.equals("")) {
                        i = 17;
                        j = 2;
                        sheet = "1";
                    }
                }
                Row row = null;
                if (first.equals("")) {
                    row = voGetPar.first();
                    first = "1";
                } else {
                    row = voGetPar.next();
                }
                try {
                    requestClass = new requestClass();
                    String Diddoc = row.getAttribute("Diddoc").toString();
                    xdocname =
                            requestClass.getValueInDB("AppModuleDataControl",
                                                      "DocmetaVO1",
                                                      "DID = '" + Diddoc + "'",
                                                      "Xdocname");
                    ViewObject xdocnameUpdate =
                        am.findViewObject("DocmetaVO1");
                    xdocnameUpdate.setWhereClause("XDOCNAME = '" + xdocname +
                                                  "' AND ((XDOCPURPOSE = 'Published' OR XDOCPURPOSE = 'Native' ) AND (XSTATUS='Implemented' OR XSTATUS='Native')) AND XSTATUS is not null");
                    xdocnameUpdate.executeQuery();
                    while (xdocnameUpdate.hasNext()) {
                        Row rowUpdate = xdocnameUpdate.next();
                        Diddoc = rowUpdate.getAttribute("Did").toString();
                        if (Diddoc != null) {
                            updateMetadataDocList(Diddoc);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
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
                    } else {
                        int result =
                            Cidcontractor.toLowerCase().indexOf((row.getAttribute("Cidcontractor").toString()).toLowerCase());
                        if (result >= 0) {
                        } else {
                            Cidcontractor =
                                    Cidcontractor + ", " + row.getAttribute("Cidcontractor").toString();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (first.equals("1")) {
                        Cidusercontractor =
                                row.getAttribute("Cidusercontractor").toString();
                        first = "2";
                    } else {
                        int result =
                            Cidusercontractor.toLowerCase().indexOf((row.getAttribute("Cidusercontractor").toString()).toLowerCase());
                        if (result >= 0) {
                        } else {
                            Cidusercontractor =
                                    Cidusercontractor + ", " + row.getAttribute("Cidusercontractor").toString();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                cell = my_worksheet.getRow(i).getCell(j);
                cell.setCellValue(r + "");
                cell = my_worksheet.getRow(i).getCell(j + 1);
                cell.setCellValue(xdocname);
                cell = my_worksheet.getRow(i).getCell(j + 3);
                cell.setCellValue(Ciddoctitle);
                cell = my_worksheet.getRow(i).getCell(j + 5);
                cell.setCellValue(Cidremarkrequestor);
                i++;
                r++;
                baris++;
            }
            my_worksheet = my_xls_workbook.getSheetAt(0);
            try {
                Cidpurpose = rowReq.getAttribute("Cidpurpose").toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Cidinternaluser =
                        rowReq.getAttribute("Cidinternaluser").toString();
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
           
            HSSFSheet my_worksheet2 = my_xls_workbook.getSheetAt(1);
            cell = my_worksheet2.getRow(6).getCell(5);
            cell.setCellValue(numberCid);
            cell = my_worksheet2.getRow(7).getCell(5);
            cell.setCellValue(sdf.format(new java.util.Date()));
            cell = my_worksheet2.getRow(8).getCell(5);
            cell.setCellValue(Cidrequestor);
            cell = my_worksheet2.getRow(9).getCell(5);
            cell.setCellValue(Cidtdclead);

            cell = my_worksheet.getRow(29).getCell(5);
            cell.setCellValue(Cidpurpose);
            cell = my_worksheet.getRow(30).getCell(5);
            cell.setCellValue(Cidinternaluser);
            cell = my_worksheet.getRow(31).getCell(5);
            cell.setCellValue(Cidcontractor);
            cell = my_worksheet.getRow(37).getCell(2);
            String cellIsi="For external Quality Management's document distribution, "+getJabatan()+" is only responsible for issuing number of CID, not content of its CID.";
            cell.setCellValue(cellIsi);
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
            cell.setCellValue(Cidusercontractor);
            cell = my_worksheet.getRow(27).getCell(2);
            cell.setCellValue("On " + sdf.format(new java.util.Date()) +
                              ", to the following recipient(s) with specific purpose(s).");
            my_worksheet = my_xls_workbook.getSheetAt(0);
            //java.net.URL imgURL = getClass().getResource("Image/approve.png");
            // aboutFrame.setIconImage(new ImageIcon(imgURL).getImage());


            log.log(idrequest, "Create File CID", "input data to template");
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
                                        "Confidential Information Distribution/Supporting Documents");
                log.log(idrequest, "Check in",
                        "User : " + UserloginFullName + " - " +
                        "CheckIN file CID to EDMS in folder Confidential Information Distribution/Supporting Documents and get dID : " +
                        dDID);
                System.out.print("\ndDID = " + dDID);
                insert(idrequest, numberCid, "SYSDATE", "CID",
                        //"http://phekpowcdev.pertamina.com:16200/urm/idcplg?IdcService=VIEW_IN_AUTOVUE&dID=" +
                        //"http://phekpowcdev.pertamina.com:16200/urm/idcplg?IdcService=VIEW_IN_AUTOVUE&dID="
                        dDID, "");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IdcClientException e) {
                StackTraceElement[] element = e.getStackTrace();
                System.out.print(element);
            }
            try {
                String DemailRequestor =
                    requestClass.getValueInDB("AppModuleDataControl",
                                              "UsersVO1",
                                              "DNAME = '" + requestor + "'",
                                              "Demail");
                System.out.print("\nsend email DemailRequestor = " +
                                 DemailRequestor);
                System.out.print("\nsend email requestor = " + requestor);
                SendEmail(DemailRequestor, requestor, "create");
                //tdcReviewEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        GoToCID();
    }

    public void setBindIdListInbox(RichColumn bindIdListInbox) {
        this.bindIdListInbox = bindIdListInbox;
    }

    public RichColumn getBindIdListInbox() {
        return bindIdListInbox;
    }

    public void setBindTableHistory(RichPanelCollection bindTableHistory) {
        this.bindTableHistory = bindTableHistory;
    }

    public RichPanelCollection getBindTableHistory() {
        return bindTableHistory;
    }

    public void setBindTDCRemarksInput(RichButton bindTDCRemarksInput) {
        this.bindTDCRemarksInput = bindTDCRemarksInput;
    }

    public RichButton getBindTDCRemarksInput() {
        return bindTDCRemarksInput;
    }

    public void inputAction(ActionEvent actionEvent) {
        actionRemarks();
    }

    public void actionRemarks() {
        String status = bindDocStatus.getValue().toString();
        if (status.equals("Approve TDC Lead")) {
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            bindPopupRespond.show(ph);
        } else {
            idList = bindIdList.getValue().toString();
            if (Usersecure.equals("tdclead")) {
                bindInputRemarksTDCLead.setVisible(true);
                bindInputRemarksSV.setVisible(false);
                bindInputRemarksTDCReview.setVisible(false);
                log.log(idrequest, "Approval",
                        "User : " + UserloginFullName + " - " +
                        "TDC Lead Respond");
            } else if (Usersecure.equals("tdcreview")) {
                bindAction.setVisible(false);
                //bindStatusDocRequest.setValue("TDC Review Respond");
                bindInputRemarksTDCReview.setVisible(true);
                bindInputRemarksSV.setVisible(false);
                bindInputRemarksTDCLead.setVisible(false);
                String Value = "";

                //boolean var = false;
                String otherValue = "";


                /* for (String retval : Value.split(";")) {
                        System.out.println("retval = " + retval);
                    boolean val = retval.contains(UserloginFullName);
                        System.out.println("val = " + val);
                        System.out.println("User aktif = " + UserloginFullName);
                        //var= val;
                        //System.out.println("var = " + var);
                    if (val) {
                        bindInputRemarksTDCReview.setValue(retval.replace(UserloginFullName +
                                                                          ":",
                                                                          ""));
                        AdfFacesContext.getCurrentInstance().addPartialTarget(bindInputRemarksTDCReview);
                    } else {
                        if (Value.equals("")) {
                            otherValue = otherValue + retval;//intinya kosong
                        } else {
                            otherValue = otherValue + retval + ";";
                        }
                        System.out.println("otherValue = " + otherValue);
                        otherValue.replaceAll(";;", ";");
                        bindModifiedRemarksTDCReview.setValue(otherValue);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(bindModifiedRemarksTDCReview);
                    }

                    }*/
                //if (var) {}else{bindInputRemarksTDCReview.setValue("");}

                log.log(idrequest, "Approval",
                        "User : " + UserloginFullName + " - " + "TDC Review");
            } else {
                bindInputRemarksSV.setVisible(true);
                bindInputRemarksTDCReview.setVisible(false);
                bindInputRemarksTDCLead.setVisible(false);
                log.log(idrequest, "Approval",
                        "User : " + UserloginFullName + " - " + "SPV Respond");
            }

            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            bindPopupInputAction.show(ph);
        }
    }

    public void setBindPopupInputAction(RichPopup bindPopupInputAction) {
        this.bindPopupInputAction = bindPopupInputAction;
    }

    public RichPopup getBindPopupInputAction() {
        return bindPopupInputAction;
    }

    public void setBindLabelCIDNumber(RichOutputText bindLabelCIDNumber) {
        this.bindLabelCIDNumber = bindLabelCIDNumber;
    }

    public RichOutputText getBindLabelCIDNumber() {
        return bindLabelCIDNumber;
    }

    public void setBindPopupCheckCID(RichPopup bindPopupCheckCID) {
        this.bindPopupCheckCID = bindPopupCheckCID;
    }

    public RichPopup getBindPopupCheckCID() {
        return bindPopupCheckCID;
    }

    public void bindSubmitTDCrecreate(ActionEvent actionEvent) {
        // Add event code here...
        submitTDClead();
    }

    public void setBindInputRemarksTDCreview(RichOutputText bindInputRemarksTDCreview) {
        this.bindInputRemarksTDCreview = bindInputRemarksTDCreview;
    }

    public RichOutputText getBindInputRemarksTDCreview() {
        return bindInputRemarksTDCreview;
    }

    public void setBindModifiedRemarksTDCReview(RichOutputText bindModifiedRemarksTDCReview) {
        this.bindModifiedRemarksTDCReview = bindModifiedRemarksTDCReview;
    }

    public RichOutputText getBindModifiedRemarksTDCReview() {
        return bindModifiedRemarksTDCReview;
    }

    public void setBindTempInputRemarksSV(RichInputText bindTempInputRemarksSV) {
        this.bindTempInputRemarksSV = bindTempInputRemarksSV;
    }

    public RichInputText getBindTempInputRemarksSV() {
        return bindTempInputRemarksSV;
    }

    public void setBindIDRequest(RichInputText bindIDRequest) {
        this.bindIDRequest = bindIDRequest;
    }

    public RichInputText getBindIDRequest() {
        return bindIDRequest;
    }

    public void setBindIdListTemp(RichInputText bindIdListTemp) {
        this.bindIdListTemp = bindIdListTemp;
    }

    public RichInputText getBindIdListTemp() {
        return bindIdListTemp;
    }

    public void changelistenerInputRemarks(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        /*
        String input = bindInputRemarksTDCReview.getValue().toString();
        bindInputRemarksTDCReview.setValue(UserloginFullName + ": " + input +
                                           ";" +
                                           bindModifiedRemarksTDCReview.getValue().toString());
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindInputRemarksTDCReview);
        bindModifiedRemarksTDCReview.setValue("");
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindModifiedRemarksTDCReview);
*/
    }

    public void updateMetadataDocList(String Diddoc) {
        //label CID
        updateDocMeta updateDocMeta = new updateDocMeta();
        try {
            updateDocMeta.updateDocMetadata(Diddoc, "CID");
        } catch (SQLException e) {
        }
    }

    public void setBindLabelUsername(RichOutputText bindLabelUsername) {
        this.bindLabelUsername = bindLabelUsername;
    }

    public RichOutputText getBindLabelUsername() {
        return bindLabelUsername;
    }

    public void setBindLabelUsername2(RichOutputText bindLabelUsername2) {
        this.bindLabelUsername2 = bindLabelUsername2;
    }

    public RichOutputText getBindLabelUsername2() {
        return bindLabelUsername2;
    }

    public void setBindSearchCIDNumber(RichInputText bindSearchCIDNumber) {
        this.bindSearchCIDNumber = bindSearchCIDNumber;
    }

    public RichInputText getBindSearchCIDNumber() {
        return bindSearchCIDNumber;
    }

    public void setBindTableCIDNumber(RichTable bindTableCIDNumber) {
        this.bindTableCIDNumber = bindTableCIDNumber;
    }

    public RichTable getBindTableCIDNumber() {
        return bindTableCIDNumber;
    }

    public void setSearchCIDNumber(RichButton searchCIDNumber) {
        this.searchCIDNumber = searchCIDNumber;
    }

    public RichButton getSearchCIDNumber() {
        return searchCIDNumber;
    }

    public void searchCIDNumber(ActionEvent actionEvent) {
        // Add event code here...
        String numberCID = "";
        try {
            numberCID = bindSearchCIDNumber.getValue().toString();
        } catch (Exception e) {
        }
        ApplicationModule app =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        ViewObject voParIn = app.findViewObject("PheCidCreatePurposeVO1");
        voParIn.setWhereClause("upper(NUMCID) like upper('%" + numberCID +
                               "%')");
        voParIn.executeQuery();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCidFormPurpose);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableCIDNumber);
    }

    public void selectCIDNumber(ActionEvent actionEvent) {
//        numberCid = bindNumcidGetParam.getValue().toString();
//        idrequest = biindIdRequestAttach.getValue().toString();
//        requestor = bindRequestorPublish.getValue().toString();
        numberCid = bindNumCidPublish.getValue().toString();
        idrequest = bindIdRequestAttachPublish.getValue().toString();
        requestor = bindRequestorAttachPurpose.getValue().toString();
        
        System.out.print("\nnumberCid publish = " + numberCid);
        System.out.print("\nRequestor publish = " + requestor);
        bindCIDSelected.setValue(numberCid);
        System.out.print("\nNumber CID = " +
                         bindNumCidPublish.getValue().toString());
        bindLabelCIDNumberPopup.setValue(bindNumCidPublish.getValue().toString());
//        bindLabelCIDNumberPopup.setValue(bindNumcidGetParam.getValue().toString());
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindLabelCIDNumberPopup);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCIDSelected);
    }

    public void setBindIdCidUpload(RichColumn bindIdCidUpload) {
        this.bindIdCidUpload = bindIdCidUpload;
    }

    public RichColumn getBindIdCidUpload() {
        return bindIdCidUpload;
    }

    public void setBindAttachPhysical(RichOutputText bindAttachPhysical) {
        this.bindAttachPhysical = bindAttachPhysical;
    }

    public RichOutputText getBindAttachPhysical() {
        return bindAttachPhysical;
    }

    public void DialogListenerUploadPhysical(DialogEvent dialogEvent) {
        // Add event code here...
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            System.out.print(numcid.getValue().toString());
            //            bindSearchCIDNumber.resetValue();
            //            bindAttachPhysicalUpload.setValue(bindNumCIDPhysicalAttach.getValue().toString());
            //            AdfFacesContext.getCurrentInstance().addPartialTarget(bindAttachPhysicalUpload);
            //            AdfFacesContext.getCurrentInstance().addPartialTarget(bindSearchCIDNumber);
            //            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableCIDNumber);
            //            requestClass requestClass = new requestClass();
            //            requestClass.update("req", "Publish",
            //                                bindIDrequestAttachPhysical.getValue().toString());
            //            GoToCID();
        }
    }

    public void setBindCIDNumberForm(RichPanelFormLayout bindCIDNumberForm) {
        this.bindCIDNumberForm = bindCIDNumberForm;
    }

    public RichPanelFormLayout getBindCIDNumberForm() {
        return bindCIDNumberForm;
    }

    public void onChangeAttachFilePhysical(ValueChangeEvent valueChangeEvent) throws IOException {
        // Add event code here...
        file = (UploadedFile)valueChangeEvent.getNewValue();

        fileLength = file.getLength();
        fileInputStream = file.getInputStream();
        fileName = file.getFilename();
        fileContentType = file.getContentType();
    }

    public void setBindAttachFilePhysical(RichInputFile bindAttachFilePhysical) {
        this.bindAttachFilePhysical = bindAttachFilePhysical;
    }

    public RichInputFile getBindAttachFilePhysical() {
        return bindAttachFilePhysical;
    }

    public void setBindButtonAttachFilePhysical(RichButton bindButtonAttachFilePhysical) {
        this.bindButtonAttachFilePhysical = bindButtonAttachFilePhysical;
    }

    public RichButton getBindButtonAttachFilePhysical() {
        return bindButtonAttachFilePhysical;
    }

    public String bindUploadAttach() throws IOException {
        // Add event code here...
        RIDCClass ridc =
            new RIDCClass(getWeblogicusername(), getWeblogicpassword());
        InputStream is = null;
        String dDID = "";
        String folderGuid = "";
        Timestamp date = null;
        try {
            is = fileInputStream;
            dDID =
ridc.CheckinCIDForm(is, fileName, fileLength, "Confidential Information Distribution/Physical Document");
            log.log(idrequest, "Check in",
                    "User : " + UserloginFullName + " - " +
                    "CheckIN Attch file CID to EDMS in folder Confidential Information Distribution/Physical Documents and get dID : " +
                    dDID);
            System.out.print("\ndDID = " + dDID);
            date = new Timestamp(System.currentTimeMillis());
            requestClass = new requestClass();
            requestClass.update("attach", dDID, idrequest);
            requestClass.update("publish", "Publish", idrequest);
            requestClass.update("publishRequest", "Publish", idrequest);
            bindStatusUploadForm.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindStatusUploadForm);
            bindPublishForm.setVisible(false);
            try {
                String TDCLead =
                    requestClass.getValueInDB("AppModuleDataControl",
                                              "UsersVO1",
                                              "DFULLNAME = '" + tdclead + "'",
                                              "Dname");
                System.out.print("\nTDCLead = " + TDCLead);
                String Demail =
                    requestClass.getValueInDB("AppModuleDataControl",
                                              "UsersVO1",
                                              "DFULLNAME = '" + tdclead + "'",
                                              "Demail");
                System.out.print("\nsend email Demail = " + Demail);
                System.out.print("\nsend email TDCUserName = " + TDCLead);
                tdcReviewEmail();
                SendEmail(Demail, TDCLead, "publish");
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String DemailRequestor =
                    requestClass.getValueInDB("AppModuleDataControl",
                                              "UsersVO1",
                                              "DNAME = '" + requestor + "'",
                                              "Demail");
                System.out.print("\nsend email DemailRequestor = " +
                                 DemailRequestor);
                System.out.print("\nsend email requestor = " + requestor);
                SendEmail(DemailRequestor, requestor, "publish");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IdcClientException e) {
            StackTraceElement[] element = e.getStackTrace();
            System.out.print(element);
        }
        GoToCID();
        return null;

    }

    public void bindSaveAttachPhysical(ActionEvent actionEvent) {
        commit();
        bindNumCIDAttachPhysical.resetValue();
        bindStatusUploadForm.setVisible(false);

    }

    public void setBindIdRequestAttachPhysical(RichColumn bindIdRequestAttachPhysical) {
        this.bindIdRequestAttachPhysical = bindIdRequestAttachPhysical;
    }

    public RichColumn getBindIdRequestAttachPhysical() {
        return bindIdRequestAttachPhysical;
    }

    public void setBindAttachIdRequest(RichOutputText bindAttachIdRequest) {
        this.bindAttachIdRequest = bindAttachIdRequest;
    }

    public RichOutputText getBindAttachIdRequest() {
        return bindAttachIdRequest;
    }

    public String bindSaveAttachPhysical() {
        // Add event code here...

        return null;
    }

    public void setBindStatusUpload(RichOutputText bindStatusUpload) {
        this.bindStatusUpload = bindStatusUpload;
    }

    public RichOutputText getBindStatusUpload() {
        return bindStatusUpload;
    }

    public void setUploadPhysicalRequestCID(RichButton uploadPhysicalRequestCID) {
        this.uploadPhysicalRequestCID = uploadPhysicalRequestCID;
    }

    public RichButton getUploadPhysicalRequestCID() {
        return uploadPhysicalRequestCID;
    }

    public void setBindLabelCIDSelected(RichOutputText bindLabelCIDSelected) {
        this.bindLabelCIDSelected = bindLabelCIDSelected;
    }

    public RichOutputText getBindLabelCIDSelected() {
        return bindLabelCIDSelected;
    }

    public void setBindNumCIDPhysical(RichPanelLabelAndMessage bindNumCIDPhysical) {
        this.bindNumCIDPhysical = bindNumCIDPhysical;
    }

    public RichPanelLabelAndMessage getBindNumCIDPhysical() {
        return bindNumCIDPhysical;
    }

    public void setBindNumCIDAttachPhysical(RichInputText bindNumCIDAttachPhysical) {
        this.bindNumCIDAttachPhysical = bindNumCIDAttachPhysical;
    }

    public RichInputText getBindNumCIDAttachPhysical() {
        return bindNumCIDAttachPhysical;
    }

    public void setBindStatusUploadForm(RichPanelGroupLayout bindStatusUploadForm) {
        this.bindStatusUploadForm = bindStatusUploadForm;
    }

    public RichPanelGroupLayout getBindStatusUploadForm() {
        return bindStatusUploadForm;
    }

    public void setBindNumCIDPhysicalAttach(RichInputText bindNumCIDPhysicalAttach) {
        this.bindNumCIDPhysicalAttach = bindNumCIDPhysicalAttach;
    }

    public RichInputText getBindNumCIDPhysicalAttach() {
        return bindNumCIDPhysicalAttach;
    }

    public void setBindAttachPhysicalUpload(RichInputText bindAttachPhysicalUpload) {
        this.bindAttachPhysicalUpload = bindAttachPhysicalUpload;
    }

    public RichInputText getBindAttachPhysicalUpload() {
        return bindAttachPhysicalUpload;
    }

    public void setBindIDrequestAttachPhysical(RichInputText bindIDrequestAttachPhysical) {
        this.bindIDrequestAttachPhysical = bindIDrequestAttachPhysical;
    }

    public RichInputText getBindIDrequestAttachPhysical() {
        return bindIDrequestAttachPhysical;
    }

    public void setSelectedCIDNumber(RichColumn selectedCIDNumber) {
        this.selectedCIDNumber = selectedCIDNumber;
    }

    public RichColumn getSelectedCIDNumber() {
        return selectedCIDNumber;
    }

    public void setBindCIDNumberFormHidden(RichPanelFormLayout bindCIDNumberFormHidden) {
        this.bindCIDNumberFormHidden = bindCIDNumberFormHidden;
    }

    public RichPanelFormLayout getBindCIDNumberFormHidden() {
        return bindCIDNumberFormHidden;
    }

    public void setCiDSelected(RichOutputText ciDSelected) {
        this.ciDSelected = ciDSelected;
    }

    public RichOutputText getCiDSelected() {
        return ciDSelected;
    }

    public void setBindRowNumCID(RichOutputText bindRowNumCID) {
        this.bindRowNumCID = bindRowNumCID;
    }

    public RichOutputText getBindRowNumCID() {
        return bindRowNumCID;
    }

    public void setBindNumcid(RichInputText bindNumcid) {
        this.bindNumcid = bindNumcid;
    }

    public RichInputText getBindNumcid() {
        return bindNumcid;
    }

    public void setBindNumcid2(RichOutputText bindNumcid2) {
        this.bindNumcid2 = bindNumcid2;
    }

    public RichOutputText getBindNumcid2() {
        return bindNumcid2;
    }

    public void setNumcid(RichOutputText numcid) {
        this.numcid = numcid;
    }

    public RichOutputText getNumcid() {
        return numcid;
    }

    public void setBindIdCID(RichInputText bindIdCID) {
        this.bindIdCID = bindIdCID;
    }

    public RichInputText getBindIdCID() {
        return bindIdCID;
    }

    public void setBindNumcidGetParam(RichInputText bindNumcidGetParam) {
        this.bindNumcidGetParam = bindNumcidGetParam;
    }

    public RichInputText getBindNumcidGetParam() {
        return bindNumcidGetParam;
    }

    public void setBindCIDForm(RichPanelFormLayout bindCIDForm) {
        this.bindCIDForm = bindCIDForm;
    }

    public RichPanelFormLayout getBindCIDForm() {
        return bindCIDForm;
    }

    public void setBiindIdRequestAttach(RichInputText biindIdRequestAttach) {
        this.biindIdRequestAttach = biindIdRequestAttach;
    }

    public RichInputText getBiindIdRequestAttach() {
        return biindIdRequestAttach;
    }

    public void setBindLabelCIDNumberPopup(RichOutputText bindLabelCIDNumberPopup) {
        this.bindLabelCIDNumberPopup = bindLabelCIDNumberPopup;
    }

    public RichOutputText getBindLabelCIDNumberPopup() {
        return bindLabelCIDNumberPopup;
    }

    public void bindButtonUploadPublish(ActionEvent actionEvent) {
        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        if ((numberCid.equals("")) || (fileName.equals(""))) {
            bindPopupNullCIDNumber.show(ph);
        } else {
            bindPopupPublishUpload.show(ph);
        }
    }

    public void setBindPopupPublishUpload(RichPopup bindPopupPublishUpload) {
        this.bindPopupPublishUpload = bindPopupPublishUpload;
    }

    public RichPopup getBindPopupPublishUpload() {
        return bindPopupPublishUpload;
    }

    public void setBindPopupNullCIDNumber(RichPopup bindPopupNullCIDNumber) {
        this.bindPopupNullCIDNumber = bindPopupNullCIDNumber;
    }

    public RichPopup getBindPopupNullCIDNumber() {
        return bindPopupNullCIDNumber;
    }

    public void setBindPublishForm(RichPanelGroupLayout bindPublishForm) {
        this.bindPublishForm = bindPublishForm;
    }

    public RichPanelGroupLayout getBindPublishForm() {
        return bindPublishForm;
    }

    public void openform(ActionEvent actionEvent) {
        // Add event code here...

        bindPublishForm.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindPublishForm);
    }

    public void cancelUploadPublish(ActionEvent actionEvent) {
        // Add event code here...
        bindPublishForm.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindPublishForm);
    }

    public void setBindTDCLead(RichInputText bindTDCLead) {
        this.bindTDCLead = bindTDCLead;
    }

    public RichInputText getBindTDCLead() {
        return bindTDCLead;
    }

    public void setBindRequestorPublish(RichInputText bindRequestorPublish) {
        this.bindRequestorPublish = bindRequestorPublish;
    }

    public RichInputText getBindRequestorPublish() {
        return bindRequestorPublish;
    }

    public void setBindCIDSelected(RichOutputText bindCIDSelected) {
        this.bindCIDSelected = bindCIDSelected;
    }

    public RichOutputText getBindCIDSelected() {
        return bindCIDSelected;
    }

    public void setBindDocStatus(RichInputText bindDocStatus) {
        this.bindDocStatus = bindDocStatus;
    }

    public RichInputText getBindDocStatus() {
        return bindDocStatus;
    }

    public void setBindTextRepond(RichOutputText bindTextRepond) {
        this.bindTextRepond = bindTextRepond;
    }

    public RichOutputText getBindTextRepond() {
        return bindTextRepond;
    }

    public void setBindPopupRespond(RichPopup bindPopupRespond) {
        this.bindPopupRespond = bindPopupRespond;
    }

    public RichPopup getBindPopupRespond() {
        return bindPopupRespond;
    }

    public void setBindApproveCheckbox(RichSelectBooleanCheckbox bindApproveCheckbox) {
        this.bindApproveCheckbox = bindApproveCheckbox;
    }

    public RichSelectBooleanCheckbox getBindApproveCheckbox() {
        return bindApproveCheckbox;
    }

    public void setBindMultiApprovalForm(RichPanelFormLayout bindMultiApprovalForm) {
        this.bindMultiApprovalForm = bindMultiApprovalForm;
    }

    public RichPanelFormLayout getBindMultiApprovalForm() {
        return bindMultiApprovalForm;
    }

    public void setBindIdListMultiForm(RichInputText bindIdListMultiForm) {
        this.bindIdListMultiForm = bindIdListMultiForm;
    }

    public RichInputText getBindIdListMultiForm() {
        return bindIdListMultiForm;
    }

    public void setBingStatusMultiform(RichInputText bingStatusMultiform) {
        this.bingStatusMultiform = bingStatusMultiform;
    }

    public RichInputText getBingStatusMultiform() {
        return bingStatusMultiform;
    }

    public void MultiApprovalMethode(ActionEvent actionEvent) {
        // Add event code here...
        //actionRemarks();
    }

    public void multiSubmit(ActionEvent actionEvent) {
        multiRemarksTDCLead = "";
        multiRemarksTDCReview = "";
        multiRemarksSPV = "";
        System.out.println("multi submit");
        if (Usersecure.equals("tdclead")) {
            try {
                multiRemarksTDCLead =
                        bindInputRemarksTDCLead.getValue().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                log.log(idrequest, "remarks TDC Lead",
                        "User : " + UserloginFullName + " - " +
                        bindInputRemarksTDCLead.getValue().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (Usersecure.equals("tdcreview")) {
            try {
                multiRemarksTDCReview =
                        bindInputRemarksTDCReview.getValue().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                log.log(idrequest, "remarks TDC Review",
                        "User : " + UserloginFullName + " - " +
                        bindInputRemarksTDCReview.getValue().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                multiRemarksSPV = bindInputRemarksSV.getValue().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                log.log(idrequest, "remarks SPV",
                        "User : " + UserloginFullName + " - " +
                        bindInputRemarksSV.getValue().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("multi submit remarks");
        //refreshTableRequest();

        ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        ViewObject voGetPars = am.findViewObject("PheCidListdocrequestVO1");
        voGetPars.setWhereClause("IDREQUEST = '" + idrequest + "'");
        voGetPars.executeQuery();
            int i = 0;
            voGetPars.reset();
            String list = voGetPars.getEstimatedRowCount() + "";
            
            if (list.equals("1")) {
                    System.out.println("sebelum while");
                    while (list.equals("1")) {
                    System.out.println("masuk while");
                    Row row = null;
                        row = voGetPars.first();
                        String rowIdlist = row.getAttribute("Idlist") + "";
                        System.out.println("rowIdlist = " + rowIdlist);
                        for (String idlist : MultiApproveIdRequest) {
                        if (rowIdlist.equals(idlist)) {
                            String rowTDCReview = row.getAttribute("Cidremarktdc") + "";
                            System.out.println("rowTDCReview = " + rowTDCReview);
                            if (Usersecure.equals("tdclead")) {
                                row.setAttribute("Cidstatusdocrequest",
                                                 multiStatusApproval);
                                row.setAttribute("Cidremarktdclead",
                                                 multiRemarksTDCLead);
                            } else if (Usersecure.equals("tdcreview")) {
                                for (String remarkslist :
                                     MultiRemarkTdcReviewRequest) {
                                    System.out.println("remarkslist = " + remarkslist);
                                    if (rowTDCReview.equals(remarkslist)) {
                                        for (String retval : remarkslist.split(";")) {
                                            System.out.println("retval = " + retval);
                                            boolean val =
                                                retval.contains(UserloginFullName);
                                            System.out.println("val = " + val);
                                            if (val) {
                                            String comment = retval.replaceAll(UserloginFullName+":", "");
                                                System.out.println("comment = " + comment);
                                            String UserRemarks = retval.replaceAll(comment, bindInputRemarksTDCReview.getValue().toString());
                                                System.out.println("UserRemarks = " + UserRemarks);
                                                if(UserRemarks.equals("")){}else{UserRemarks=UserRemarks+";";}
                                                String RemarkTdc = remarkslist.replaceAll(retval,UserRemarks); 
                                                multiRemarksTDCReview =remarkslist.replaceAll(remarkslist,RemarkTdc);
                                                System.out.println("multiRemarksTDCReview 1= " + multiRemarksTDCReview);
                                                multiRemarksTDCReview=multiRemarksTDCReview.replaceAll("null;","");
                                                multiRemarksTDCReview=multiRemarksTDCReview.replaceAll(";;",";");
                                            row.setAttribute("Cidremarktdc", multiRemarksTDCReview);
                                                //replace dr inputan remarks sama retval
                                            }else if(remarkslist.equals("")){
                                            multiRemarksTDCReview= UserloginFullName+": "+bindInputRemarksTDCReview.getValue().toString()+";";
                                            System.out.println("multiRemarksTDCReview 2= " + multiRemarksTDCReview);
                                            multiRemarksTDCReview=multiRemarksTDCReview.replaceAll("null","");
                                            multiRemarksTDCReview=multiRemarksTDCReview.replaceAll(";;",";");
                                            row.setAttribute("Cidremarktdc", multiRemarksTDCReview);
                                            }
                                            else { 
                                            multiRemarksTDCReview =remarkslist +UserloginFullName+": "+bindInputRemarksTDCReview.getValue().toString()+";";
                                            System.out.println("multiRemarksTDCReview 3= " + multiRemarksTDCReview);
                                            multiRemarksTDCReview=multiRemarksTDCReview.replaceAll("null","");
                                            multiRemarksTDCReview=multiRemarksTDCReview.replaceAll(";;",";");
                                            row.setAttribute("Cidremarktdc", multiRemarksTDCReview);
                                            }

                                        }
                                    }
                                    //replace procedure , ganti yg double
                                }
                                AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableInboxListRequest);
                            } else if (Usersecure.equals("spvreview")) {
                                row.setAttribute("Cidstatusdocrequest",
                                                 multiStatusApproval);
                                row.setAttribute("Cidremarksv", multiRemarksSPV);
                            }
                        }
                        i++;list="";
                    }
                    }
                }
            /////
            System.out.println("sebelum while ganda");
            while (voGetPars.hasNext()) {
            System.out.println("masuk while ganda");
            Row row = null;
                if (i == 0) {
                    row = voGetPars.first();
                } else {
                    row = voGetPars.next();
                }
                String rowIdlist = row.getAttribute("Idlist") + "";
                System.out.println("rowIdlist = " + rowIdlist);
                for (String idlist : MultiApproveIdRequest) {
                if (rowIdlist.equals(idlist)) {
                    String rowTDCReview = row.getAttribute("Cidremarktdc") + "";
                    System.out.println("rowTDCReview = " + rowTDCReview);
                    if (Usersecure.equals("tdclead")) {
                        row.setAttribute("Cidstatusdocrequest",
                                         multiStatusApproval);
                        row.setAttribute("Cidremarktdclead",
                                         multiRemarksTDCLead);
                    } else if (Usersecure.equals("tdcreview")) {
                        for (String remarkslist :
                             MultiRemarkTdcReviewRequest) {
                            System.out.println("remarkslist = " + remarkslist);
                            if (rowTDCReview.equals(remarkslist)) {
                                for (String retval : remarkslist.split(";")) {
                                    System.out.println("retval = " + retval);
                                    boolean val =
                                        retval.contains(UserloginFullName);
                                    System.out.println("val = " + val);
                                    if (val) {
                                    String comment = retval.replaceAll(UserloginFullName+":", "");
                                        System.out.println("comment = " + comment);
                                    String UserRemarks = retval.replaceAll(comment, bindInputRemarksTDCReview.getValue().toString());
                                        System.out.println("UserRemarks = " + UserRemarks);
                                        if(UserRemarks.equals("")){}else{UserRemarks=UserRemarks+";";}
                                        String RemarkTdc = remarkslist.replaceAll(retval,UserRemarks); 
                                        multiRemarksTDCReview =remarkslist.replaceAll(remarkslist,RemarkTdc);
                                        System.out.println("multiRemarksTDCReview 1= " + multiRemarksTDCReview);
                                        multiRemarksTDCReview=multiRemarksTDCReview.replaceAll("null;","");
                                        multiRemarksTDCReview=multiRemarksTDCReview.replaceAll(";;",";");
                                    row.setAttribute("Cidremarktdc", multiRemarksTDCReview);
                                        //replace dr inputan remarks sama retval
                                    }else if(remarkslist.equals("")){
                                    multiRemarksTDCReview= UserloginFullName+": "+bindInputRemarksTDCReview.getValue().toString()+";";
                                    System.out.println("multiRemarksTDCReview 2= " + multiRemarksTDCReview);
                                    multiRemarksTDCReview=multiRemarksTDCReview.replaceAll("null","");
                                    multiRemarksTDCReview=multiRemarksTDCReview.replaceAll(";;",";");
                                    row.setAttribute("Cidremarktdc", multiRemarksTDCReview);
                                    }
                                    else { 
                                    multiRemarksTDCReview =remarkslist +UserloginFullName+": "+bindInputRemarksTDCReview.getValue().toString()+";";
                                    System.out.println("multiRemarksTDCReview 3= " + multiRemarksTDCReview);
                                    multiRemarksTDCReview=multiRemarksTDCReview.replaceAll("null","");
                                    multiRemarksTDCReview=multiRemarksTDCReview.replaceAll(";;",";");
                                    row.setAttribute("Cidremarktdc", multiRemarksTDCReview);
                                    }

                                }
                            }
                            //replace procedure , ganti yg double
                        }
                        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableInboxListRequest);
                    } else if (Usersecure.equals("spvreview")) {
                        row.setAttribute("Cidstatusdocrequest",
                                         multiStatusApproval);
                        row.setAttribute("Cidremarksv", multiRemarksSPV);
                    }
                }
                i++;
            }
        }
        Integer temp = 0;
        Row row = voGetPars.last();
        String lastID = row.getAttribute("Idlist") + "";
        for (String no : MultiApproveIdRequest)
            if (no.equals(lastID))
                temp = MultiApproveIdRequest.contains(no) ? 1 : 2;
        if (temp == 1) {
        } else {
            if (Usersecure.equals("tdclead")) {
                row.setAttribute("Cidstatusdocrequest",
                                 LastApproveStatusRequest);
                row.setAttribute("Cidremarktdclead", LastApproveRemarks);
            } else if (Usersecure.equals("tdcreview")) {
                //row.setAttribute("Cidstatusdocrequest", "TDC Review");
                row.setAttribute("Cidremarktdc", LastApproveRemarks);
            } else if (Usersecure.equals("spvreview")) {
                row.setAttribute("Cidstatusdocrequest",
                                 LastApproveStatusRequest);
                row.setAttribute("Cidremarksv", LastApproveRemarks);
            }
        }
        commit();
        bindSelectAllButton.setVisible(true);
        bindUnselectAllButton.setVisible(false);
        //bindCheckAll.resetValue();
        bindAction.resetValue();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindAction);
        MultiApproveIdRequest.clear();
        MultiRemarkTdcReviewRequest.clear();


        ViewObject voGetPar = am.findViewObject("PheCidListdocrequestVO1");
        voGetPar.setWhereClause("IDREQUEST = '" + idrequest + "'");
        voGetPar.executeQuery();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableInboxListRequest);
        
        // popup ambigu
        countTable(idrequest, "Requested");
        if (Usersecure.equals("spvreview")) {
            countTable(idrequest, "Requested");
        } else if (Usersecure.equals("tdclead")) {
            countTable(idrequest, "Approve SPV");
        }

        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        if (checkTable.equals("0")) {
            bindPopupRespondSubmitReminder.show(ph);
        }
        
        ViewObject voDocInput = am.findViewObject("PheCidListrequestVO1");
        voDocInput.setWhereClause("IDREQUEST = '" + idrequest + "'");
        voDocInput.executeQuery();
    }

    public void setBindColumnApprove(RichColumn bindColumnApprove) {
        this.bindColumnApprove = bindColumnApprove;
    }

    public RichColumn getBindColumnApprove() {
        return bindColumnApprove;
    }


    public void selectAllCheckBoxVCL(ValueChangeEvent valueChangeEvent) {
        System.out.println("xdebug c1 : In selectAllChoiceBoxLN with value = " +
                           valueChangeEvent.getNewValue());

        selectAll = "Y";
        boolean isSelected =
            ((Boolean)valueChangeEvent.getNewValue()).booleanValue();
        DCBindingContainer dcb = (DCBindingContainer)evaluateEL("#{bindings}");
        DCIteratorBinding dciter =
            dcb.findIteratorBinding("PheCidListdocrequestVO1Iterator");
        ViewObject vo = dciter.getViewObject();

        int i = 0;
        Row row = null;
        vo.reset();
        while (vo.hasNext()) {
            if (i == 0)
                row = vo.first();
            else
                row = vo.next();
            if (isSelected) {
                row.setAttribute("booleanApprove", "Y");
            } else {
                row.setAttribute("booleanApprove", "N");
            }

            System.out.println("xdebug c2: Changing row  : " +
                               row.getAttribute("booleanApprove"));
            i++;
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableInboxListRequest);
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

    public String readIdlist() {
        System.out.println("read id list");
        // Add event code here...
        DCBindingContainer dcb = (DCBindingContainer)evaluateEL("#{bindings}");
        DCIteratorBinding dciter =
            dcb.findIteratorBinding("PheCidListdocrequestVO1Iterator");
        ViewObject vo = dciter.getViewObject();

        int i = 0;
        Row row = null;
        String checked = "N";
        String booleanApprove = "";
        LastApproveStatusRequest = "";
        LastApproveRemarks = " ";
        vo.reset();
        String list = vo.getEstimatedRowCount() + "";
        if (list.equals("1")) {
            try {
                row = vo.first();
                booleanApprove = row.getAttribute("booleanApprove") + "";
                if (booleanApprove.equals("Y")) {
                    checked="Y";
                    System.out.println("xdebug c2: idlist  : " +
                                       row.getAttribute("Idlist"));
                    MultiApproveIdRequest.add(row.getAttribute("Idlist") + "");
                    MultiRemarkTdcReviewRequest.add(row.getAttribute("Cidremarktdc") +
                                                    "");
                }
            } catch (Exception e) {
            }
        }
        while (vo.hasNext()) {
            if (i == 0)
                row = vo.first();
            else
                row = vo.next();
            try {
                booleanApprove = row.getAttribute("booleanApprove") + "";
            } catch (Exception e) {
            }
            if (booleanApprove.equals("Y")) {
                checked="Y";
                System.out.println("xdebug c2: idlist  : " +
                                   row.getAttribute("Idlist"));
                MultiApproveIdRequest.add(row.getAttribute("Idlist") + "");
                MultiRemarkTdcReviewRequest.add(row.getAttribute("Cidremarktdc") +
                                                "");
            }
            i++;
        }
        row = vo.last();
        String value = "";
        
        if (list.equals("1")) {            
            try {
                row = vo.first();
            } catch (Exception e) {
            }}
        if (Usersecure.equals("tdclead")) {
            //responAction
            LastApproveStatusRequest =
                    row.getAttribute("Cidstatusdocrequest") + "";
            try {
                value = row.getAttribute("Cidremarktdclead") + "";
            } catch (Exception e) {
            }
        } else if (Usersecure.equals("tdcreview")) {
            LastApproveStatusRequest =
                    row.getAttribute("Cidstatusdocrequest") + "";
            try {
                value = row.getAttribute("Cidremarktdc") + "";
                System.out.println("set value  = "+value);
            } catch (Exception e) {
            }
        } else if (Usersecure.equals("spvreview")) {
            LastApproveStatusRequest =
                    row.getAttribute("Cidstatusdocrequest") + "";
            try {
                value = row.getAttribute("Cidremarksv") + "";
            } catch (Exception e) {
            }
        }
        System.out.println("value remarks = "+value);
        LastApproveRemarks = value.replace("null", " ");
        System.out.println("\nMultiApproveIdRequest = " +
                           MultiApproveIdRequest.toString());
        System.out.println("\nMultiApproveIdRequest = " +
                           MultiRemarkTdcReviewRequest.toString());
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableInboxListRequest);
        if(checked.equals("N"))
        {   RichPopup.PopupHints ph = new RichPopup.PopupHints();
            bindPopupSelectRespond.show(ph);
        }else{actionRemarks();checked="N";}
        return null;
    }

    public void setBindCheckAll(RichSelectBooleanCheckbox bindCheckAll) {
        this.bindCheckAll = bindCheckAll;
    }

    public RichSelectBooleanCheckbox getBindCheckAll() {
        return bindCheckAll;
    }

    public void checkboxChangeListener(ValueChangeEvent valueChangeEvent) {
        // AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableInboxListRequest);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableRequestListApproval);
        // AdfFacesContext.getCurrentInstance().addPartialTarget(bindCheckBox);
        //refreshMultiple();
    }

    public void setBindTableRequestListApproval(RichPanelCollection bindTableRequestListApproval) {
        this.bindTableRequestListApproval = bindTableRequestListApproval;
    }

    public RichPanelCollection getBindTableRequestListApproval() {
        return bindTableRequestListApproval;
    }

    public void setBindCheckBox(RichSelectBooleanCheckbox bindCheckBox) {
        this.bindCheckBox = bindCheckBox;
    }

    public RichSelectBooleanCheckbox getBindCheckBox() {
        return bindCheckBox;
    }

    public void refreshMultiple() {
        DCBindingContainer dcb = (DCBindingContainer)evaluateEL("#{bindings}");
        DCIteratorBinding dciter =
            dcb.findIteratorBinding("PheCidListdocrequestVO1Iterator");
        ViewObject vo = dciter.getViewObject();

        int i = 0;
        Row row = null;
        vo.reset();
        while (vo.hasNext()) {
            if (i == 0)
                row = vo.first();
            else
                row = vo.next();
            row.setAttribute("booleanApprove", "N");
            i++;
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableRequestListApproval);
    }

    public void SelectAll(ActionEvent actionEvent) {
        // Add event code here...
        bindSelectAllButton.setVisible(false);
        bindUnselectAllButton.setVisible(true);
        selectAll = "Y";
        DCBindingContainer dcb = (DCBindingContainer)evaluateEL("#{bindings}");
        DCIteratorBinding dciter =
            dcb.findIteratorBinding("PheCidListdocrequestVO1Iterator");
        ViewObject vo = dciter.getViewObject();

        int i = 0;
        Row row = null;
        vo.reset();
        while (vo.hasNext()) {
            if (i == 0)
                row = vo.first();
            else
                row = vo.next();

            row.setAttribute("booleanApprove", "Y");

            System.out.println("xdebug c2: Changing row  : " +
                               row.getAttribute("booleanApprove"));
            i++;
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableInboxListRequest);
    }

    public void unselectAll(ActionEvent actionEvent) {
        // Add event code here...
        bindSelectAllButton.setVisible(true);
        bindUnselectAllButton.setVisible(false);
        selectAll = "";
        DCBindingContainer dcb = (DCBindingContainer)evaluateEL("#{bindings}");
        DCIteratorBinding dciter =
            dcb.findIteratorBinding("PheCidListdocrequestVO1Iterator");
        ViewObject vo = dciter.getViewObject();

        int i = 0;
        Row row = null;
        vo.reset();
        while (vo.hasNext()) {
            if (i == 0)
                row = vo.first();
            else
                row = vo.next();

            row.setAttribute("booleanApprove", "N");


            System.out.println("xdebug c2: Changing row  : " +
                               row.getAttribute("booleanApprove"));
            i++;
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableInboxListRequest);
        MultiRemarkTdcReviewRequest.clear();
        MultiApproveIdRequest.clear();
    }

    public void setBindSelectAllButton(RichButton bindSelectAllButton) {
        this.bindSelectAllButton = bindSelectAllButton;
    }

    public RichButton getBindSelectAllButton() {
        return bindSelectAllButton;
    }

    public void setBindUnselectAllButton(RichButton bindUnselectAllButton) {
        this.bindUnselectAllButton = bindUnselectAllButton;
    }

    public RichButton getBindUnselectAllButton() {
        return bindUnselectAllButton;
    }

    public void setBindTableCIDNumberPublish(RichTable bindTableCIDNumberPublish) {
        this.bindTableCIDNumberPublish = bindTableCIDNumberPublish;
    }

    public RichTable getBindTableCIDNumberPublish() {
        return bindTableCIDNumberPublish;
    }

    public void changeViewPublish(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        Object object = radioCIDType.getValue();
        String change = object + "";
        ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        ViewObject voListCreate = am.findViewObject("PheCidCreatePurposeVO1");
        if (change.equals("task")) {
            bindRowNumCID.setVisible(true);
            golinkAttach.setVisible(false);
            voListCreate.setWhereClause("Cidstatuscid <> 'Publish'");
        } else if (change.equals("taskDone")) {
            bindRowNumCID.setVisible(false);
            golinkAttach.setVisible(true);
            voListCreate.setWhereClause("Cidstatuscid = 'Publish'");
        }
        voListCreate.executeQuery();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCidFormPurpose);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableCIDNumber);
    }

    public void setRadioCIDType(RichSelectOneRadio radioCIDType) {
        this.radioCIDType = radioCIDType;
    }

    public RichSelectOneRadio getRadioCIDType() {
        return radioCIDType;
    }

    public void setGolinkAttach(RichGoLink golinkAttach) {
        this.golinkAttach = golinkAttach;
    }

    public RichGoLink getGolinkAttach() {
        return golinkAttach;
    }

    public void setBindSupervisor(RichInputText bindSupervisor) {
        this.bindSupervisor = bindSupervisor;
    }

    public RichInputText getBindSupervisor() {
        return bindSupervisor;
    }


    public void refreshPage(ActionEvent actionEvent) {
        // Add event code here...
        refreshTableRequest();
    }

    public void setBindNumCidPublish(RichInputText bindNumCidPublish) {
        this.bindNumCidPublish = bindNumCidPublish;
    }

    public RichInputText getBindNumCidPublish() {
        return bindNumCidPublish;
    }

    public void setBindIdRequestAttachPublish(RichInputText bindIdRequestAttachPublish) {
        this.bindIdRequestAttachPublish = bindIdRequestAttachPublish;
    }

    public RichInputText getBindIdRequestAttachPublish() {
        return bindIdRequestAttachPublish;
    }

    public void setBindRequestorAttachPurpose(RichInputText bindRequestorAttachPurpose) {
        this.bindRequestorAttachPurpose = bindRequestorAttachPurpose;
    }

    public RichInputText getBindRequestorAttachPurpose() {
        return bindRequestorAttachPurpose;
    }

    public void setBindCidFormPurpose(RichPanelFormLayout bindCidFormPurpose) {
        this.bindCidFormPurpose = bindCidFormPurpose;
    }

    public RichPanelFormLayout getBindCidFormPurpose() {
        return bindCidFormPurpose;
    }

    public void closePage(ActionEvent actionEvent) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
                    ExtendedRenderKitService service =
                        Service.getRenderKitService(facesContext,
                                                    ExtendedRenderKitService.class);
                    service.addScript(facesContext, "window.open('', '_self', ''); window.close();self.close();");
        }

    public void setBindPopupRespondSubmitReminder(RichPopup bindPopupRespondSubmitReminder) {
        this.bindPopupRespondSubmitReminder = bindPopupRespondSubmitReminder;
    }

    public RichPopup getBindPopupRespondSubmitReminder() {
        return bindPopupRespondSubmitReminder;
    }
    public void setBindPopupSelectRespond(RichPopup bindPopupSelectRespond) {
        this.bindPopupSelectRespond = bindPopupSelectRespond;
    }

    public RichPopup getBindPopupSelectRespond() {
        return bindPopupSelectRespond;
    }

    public void setBindCreateFormCidTDCReview(RichButton bindCreateFormCidTDCReview) {
        this.bindCreateFormCidTDCReview = bindCreateFormCidTDCReview;
    }

    public RichButton getBindCreateFormCidTDCReview() {
        return bindCreateFormCidTDCReview;
    }

    public void setBindOpenAllList(RichButton bindOpenAllList) {
        this.bindOpenAllList = bindOpenAllList;
    }

    public RichButton getBindOpenAllList() {
        return bindOpenAllList;
    }

    public void openAllList(ActionEvent actionEvent) {
        // Add event code here...
        ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        ViewObject voListApproval = am.findViewObject("PheCidListrequestVO1");
        voListApproval.setWhereClause("(Cidstatusrequest = 'Approve TDC Lead' )");
        voListApproval.executeQuery();
        tableStatus = voListApproval.getEstimatedRowCount() + "";
        bindTableListRequest = new RichTable();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableListRequest);
    }

    public void setBindTableListRequest(RichTable bindTableListRequest) {
        this.bindTableListRequest = bindTableListRequest;
    }

    public RichTable getBindTableListRequest() {
        return bindTableListRequest;
    }


    public void DialogListenerSubmitApproval(DialogEvent dialogEvent) {
        // Add event code here...
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.yes)) {
            //System.out.print(numcid.getValue().toString());
            commit();
            utils update = new utils();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            System.out.print(sdf.format(new java.util.Date()));
            countTable(idrequest, "Requested");
            if (Usersecure.equals("spvreview")) {
                countTable(idrequest, "Requested");
            } else if (Usersecure.equals("tdclead")) {
                countTable(idrequest, "Approve SPV");
            }

            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            if (checkTable.equals("1")) {
                bindPopupRespondAllDocumentList.show(ph);
            } else {
                if (Usersecure.equals("tdclead")) {
                    requestClass = new requestClass();
                    String Numcid =
                        requestClass.getValueInDB("AppModuleDataControl",
                                                  "PheCidCreateVO1",
                                                  "IDREQUEST = '" + idrequest +
                                                  "'", "Numcid");
                    if (Numcid.equals("")) {
                        submitTDClead();
                    } else {
                        bindLabelCIDNumber.setValue(Numcid);
                        bindPopupCheckCID.show(ph);
                    }
                } else if (Usersecure.equals("tdcreview")) {
                    String query =
                        "update phe_cid_listrequest set cidapprovetdc=(to_date('" +
                        sdf.format(new java.util.Date()) +
                        "', 'yyyy/mm/dd hh24:mi:ss')) where idrequest=" +
                        idrequest;
                    try {
                        update.update(query);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //checkSubmit("TDCReview", "Reject TDC Review With Remarks","Reject TDC Review With Remarks", "TDC Review");
                    bindPopupSubmit.show(ph);
                } else {
                    String query =
                        "update phe_cid_listrequest set ciddateapprovesv=(to_date('" +
                        sdf.format(new java.util.Date()) +
                        "', 'yyyy/mm/dd hh24:mi:ss')) where idrequest=" +
                        idrequest;
                    try {
                        update.update(query);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    checkSubmit("SPV", "Reject SPV With Remarks",
                                "Reject SPV With Remarks", "Approve SPV");
                    bindPopupSubmit.show(ph);
                }

                refresh();
                commit();
            }
            
        }
    }

    public void setBindApNameinsert(RichInputText bindApNameinsert) {
        this.bindApNameinsert = bindApNameinsert;
    }

    public RichInputText getBindApNameinsert() {
        return bindApNameinsert;
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

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setRoleTdcReview(String roleTdcReview) {
        this.roleTdcReview = roleTdcReview;
    }

    public String getRoleTdcReview() {
        return roleTdcReview;
    }
}
