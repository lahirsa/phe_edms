package beans;

import com.bea.staxb.runtime.BindingContext;

import com.utils.ADFUtils;

//import com.utils.DBConnection;

import com.utils.DBConnection;

import com.utils.JSFUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import java.text.SimpleDateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
//import oracle.adf.model.BindingContext;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContainer;
import oracle.adf.model.OperationBinding;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import oracle.ods.virtualization.engine.backend.db.DBConnectionPool;

import org.apache.myfaces.trinidad.model.CollectionModel;

public class MonitoringCID {
    private RichOutputText bindCidNumber;
    private RichOutputText bindIDRequest;
    private RichPopup bindUpdateCID;
    
    private RichTable bindTableCID;
    private RichOutputText bindCIDStatus;
    private RichOutputText CID_SUPERADMIN;
    private RichSelectOneChoice _bindNewStatus;
    private RichTable _bindTableMonitoring;
    private String _superadmin;
    private String _username;
    private String _newstatus;
    private String _remarks;
    Log log = new Log();
    private RichPanelFormLayout _bindTableCID;
    private RichOutputText bindSupervisor;
    private RichOutputText bindTdcLead;
    private boolean isVisible = false;
    private boolean isTDCAdmin = false;
    private RichInputText inptIdRequest;

    public MonitoringCID() {
        super();
    }

    public String detailCID() {
        // Add event code here...
        
        String IdReg=(String)JSFUtils.resolveExpression("#{bindings.Idrequest.inputValue}").toString();
//        System.out.println("IdReg "+IdReg);
        ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        ViewObject voMonitoring = am.findViewObject("DetailCID1");
        voMonitoring.setWhereClause("IDREQUEST ='"+IdReg+"'");
        voMonitoring.executeQuery();

        ViewObject voMonitoring2 = am.findViewObject("HistoryCIDVO1");
        voMonitoring2.setWhereClause("IDREQUEST ='"+IdReg+"'");
        voMonitoring2.executeQuery();
        
        ViewObject voLastReject = am.findViewObject("LastRejectVO1");
        voLastReject.setWhereClause("IDREQUEST ='"+IdReg +"'");
        voLastReject.executeQuery();
        
        return "detail";
    }
    
    
    public void handleTableDoubleClick(ClientEvent ce){
        System.out.println("Super Admin: "+_superadmin);
        if(_superadmin.equalsIgnoreCase(_username)){
    RichPopup popup = this.getBindUpdateCID();
    //no hints means that popup is launched in the 
    //center of the page
    RichPopup.PopupHints ph = new RichPopup.PopupHints();
    popup.show(ph);
        }
    }
    
    public void onSubmitPopup(ActionEvent actionEvent) {
    RichPopup popup = this.getBindUpdateCID();
    popup.hide();
    //refresh the table
    AdfFacesContext adfctx = AdfFacesContext.getCurrentInstance();
    adfctx.addPartialTarget(bindTableCID);
    }

    public void onCancel(ActionEvent actionEvent) {
    //undo changes
    RichTable  table = this.bindTableCID;
    CollectionModel model = (CollectionModel) table.getValue();
    JUCtrlHierBinding treeBinding = 
    (JUCtrlHierBinding) model.getWrappedData();
    DCIteratorBinding iterator = 
    treeBinding.getDCIteratorBinding();
    Row rw = iterator.getCurrentRow();
    rw.refresh(Row.REFRESH_UNDO_CHANGES);
    RichPopup popup = this.bindUpdateCID;
    popup.hide();
    }
    public void setBindCidNumber(RichOutputText bindCidNumber) {
        this.bindCidNumber = bindCidNumber;
    }

    public RichOutputText getBindCidNumber() {
        return bindCidNumber;
    }

    public void setBindIDRequest(RichOutputText bindIDRequest) {
        this.bindIDRequest = bindIDRequest;
    }

    public RichOutputText getBindIDRequest() {
        return bindIDRequest;
    }

    public void setBindUpdateCID(RichPopup bindUpdateCID) {
        this.bindUpdateCID = bindUpdateCID;
    }

    public RichPopup getBindUpdateCID() {
        return bindUpdateCID;
    }

    public void setBindTableCID(RichTable bindTableCID) {
        this.bindTableCID = bindTableCID;
    }

    public RichTable getBindTableCID() {
        return bindTableCID;
    }

    public void setBindCIDStatus(RichOutputText bindCIDStatus) {
        this.bindCIDStatus = bindCIDStatus;
    }

    public RichOutputText getBindCIDStatus() {
        return bindCIDStatus;
    }

    public void queryUser() {
        
        //get username from url parameter
       _username ="yudhi.widhiyana";
//        _username = FacesContext.
//        getCurrentInstance().getExternalContext().getRequestParameterMap().get("username");
//        _username=(_username==null?"%":_username);
        System.out.println(_username);
        //check user role
        DBConnection gc = new DBConnection();
                           Connection con = null;
                           //PreparedStatement pr = null;
                           Statement stmt  = null;   
                String select = "";
               // String superAdmin;
                int count = 0;
            
                System.out.println("----- begin checking parameter -----");
                
                try {
                    con = gc.getConnection();
                    stmt = con.createStatement();
                    select = "SELECT COUNT(DUSERNAME) FROM USERSECURITYATTRIBUTES WHERE DUSERNAME='"+_username+"' AND DATTRIBUTENAME='CID_TDC_review'";
                    ResultSet rs = stmt.executeQuery(select);
                    System.out.println(select);
                    
                        while (rs.next()) {                   
                        count = Integer.parseInt(rs.getString(1));   
                        }rs.close();
                    
                    select = "Select KEY_VALUE from PHE_CONFIG where Key_CONFIG='CID_SUPERADMIN'";
                    ResultSet rs2 = stmt.executeQuery(select);
                    System.out.println(select);
                    
                        while (rs2.next()) {
                            _superadmin = rs2.getString(1);
                            System.out.println(_superadmin);
//                        this.CID_SUPERADMIN.setValue(rs2.getString(1));
            }rs2.close();
                   
                } catch (Exception e) {
                    System.out.println("-----error-----");
                    e.printStackTrace();
                }finally{
                    try {
                        stmt.close();
                        con.close();
                        System.out.println("fin sukses");
                    } catch (SQLException f) {
                        f.printStackTrace();
                        System.out.println("fin fail");
                    }
                
                }
        
                if(_username.toString().equalsIgnoreCase(_superadmin))
                    isVisible=true;
                else
                    isVisible=false;
                
        //refresh CID Monitoring table with new query
            ApplicationModule am =
            ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
                if(count==0){       
                    isTDCAdmin = false;
                    ViewObject voMonitoring = am.findViewObject("monitoringVO1");
                    voMonitoring.setWhereClause("DNAME like'" +_username + "%'");
                    voMonitoring.executeQuery();
                    
//                    BindingContext bc = BindingContext.
//                    BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
//                OperationBinding ob = bindings.getOperationBinding("ExecuteWithParams4");
//                ob.getParamsMap().put("p_sups_search_by_name", "parameter1");
//                Object result1 = ob.execute();
                }else{
                    isTDCAdmin = true;
                    ViewObject voMonitoring = am.findViewObject("monitoringVO1");
                    voMonitoring.setWhereClause("1=1");
                    voMonitoring.executeQuery();
                }
       // AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableCID);
    }



    public void setUsername(String _username) {
        this._username = _username;
    }

    public String getUsername() {
        return _username;
    }

    public void setCID_SUPERADMIN(RichOutputText CID_SUPERADMIN) {
        this.CID_SUPERADMIN = CID_SUPERADMIN;
    }

    public RichOutputText getCID_SUPERADMIN() {
        return CID_SUPERADMIN;
    }
    
    public void DialogListenerUpdateStatus(DialogEvent dialogEvent) {
        // Add event code here...
        oracle.jbo.domain.Timestamp datetime =
            new oracle.jbo.domain.Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            DBConnection gc = new DBConnection();
                               Connection con = null;
                               //PreparedStatement pr = null;
                               Statement stmt  = null;   
                    String select = "";
                    String _idrequest = this.bindIDRequest.getValue().toString();
                    String _spv ="";
                    String _tdclead="";
                    _spv = this.bindSupervisor.getValue().toString();
                    _tdclead= this.bindTdcLead.getValue().toString();
                    
                    _newstatus = this._bindNewStatus.getValue().toString();
                    
                    System.out.println("New Status: " + _newstatus + "idrequest: " + _idrequest);
            PreparedStatement preparedStatement = null; 
            try {
                con = gc.getConnection();
                stmt = con.createStatement();
                if(_newstatus.matches(".*SPV.*")){
                    select = "UPDATE PHE_CID_LISTREQUEST SET CIDSTATUSREQUEST = '" + _newstatus + "', CIDDATEAPPROVESV=(to_date('"+ sdf.format(new java.util.Date()) + "', 'yyyy/mm/dd hh24:mi:ss')) where IDREQUEST='" + _idrequest +"'";
                }else if(_newstatus.matches(".*TDC Lead.*")){
                    select = "UPDATE PHE_CID_LISTREQUEST SET CIDSTATUSREQUEST = '" + _newstatus + "', CIDDATEAPPROVETDCLEAD=(to_date('"+ sdf.format(new java.util.Date()) + "', 'yyyy/mm/dd hh24:mi:ss')) where IDREQUEST='" + _idrequest +"'";
                }
                preparedStatement = con.prepareStatement(select);
                preparedStatement.executeUpdate();
               
                //ResultSet rs = stmt.executeQuery(select);
                System.out.println(select);
                
                //    rs.close();
                select = "UPDATE PHE_CID_LISTDOCREQUEST SET CIDSTATUSDOCREQUEST = '" + _newstatus + "' where IDREQUEST='" + _idrequest +"'";
                preparedStatement = con.prepareStatement(select);
                preparedStatement.executeUpdate();
               // ResultSet rs2 = stmt.executeQuery(select);
                System.out.println(select);
                //rs2.close();
            } catch (Exception e) {
                System.out.println("-----error-----");
                e.printStackTrace();
            }finally{
                try {
                    stmt.close();
                    con.close();
                    preparedStatement.close();
                    System.out.println("fin sukses");
                } catch (SQLException f) {
                    f.printStackTrace();
                    System.out.println("fin fail");
                }
            
            }
            
            if(_newstatus.matches(".*Approve TDC Lead.*")){
            log.log(this.bindIDRequest.getValue().toString(), "Approval",
                    "User : " + _tdclead +
                    " Approve TDC Lead - Approve Document ");
            }else if(_newstatus.matches(".*Reject TDC Lead.*")){
                log.log(this.bindIDRequest.getValue().toString(), "Approval",
                        "User : " + _tdclead +
                        " Reject TDC Lead With Remarks- Reject Document " + _remarks);
            }else if(_newstatus.matches(".*Approve SPV.*")){
                log.log(this.bindIDRequest.getValue().toString(), "Approval",
                        "User : " + _spv +
                        " Approve Supervisor - Approve Document ");
            }else if(_newstatus.matches(".*Reject SPV.*")){
                log.log(this.bindIDRequest.getValue().toString(), "Approval",
                        "User : " + _spv +
                        " Reject Supervisor With Remarks- Reject Document " + _remarks);
            }
             
            String messageText="CID Status has been updated successfully";
                  FacesMessage fm = new FacesMessage(messageText);
                  /**
                   * set the type of the message.
                   * Valid types: error, fatal,info,warning
                   */
                  fm.setSeverity(FacesMessage.SEVERITY_INFO);
                  FacesContext context = FacesContext.getCurrentInstance();
                  context.addMessage(null, fm);
           
           
            //refresh CID Monitoring table with new query
                ApplicationModule am =
                ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
                    if(!isTDCAdmin){       
                        ViewObject voMonitoring = am.findViewObject("monitoringVO1");
                        voMonitoring.setWhereClause("DNAME like'" +_username + "%'");
                        voMonitoring.executeQuery();
                        
            //                    BindingContext bc = BindingContext.
            //                    BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
            //                OperationBinding ob = bindings.getOperationBinding("ExecuteWithParams4");
            //                ob.getParamsMap().put("p_sups_search_by_name", "parameter1");
            //                Object result1 = ob.execute();
                    }else{
                        ViewObject voMonitoring = am.findViewObject("monitoringVO1");
                        voMonitoring.setWhereClause("1=1");
                        voMonitoring.executeQuery();
                    }
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTableCID);
           //queryUser();
        }
    }

    public void setBindNewStatus(RichSelectOneChoice _bindNewStatus) {
        this._bindNewStatus = _bindNewStatus;
    }

    public RichSelectOneChoice getBindNewStatus() {
        return _bindNewStatus;
    }

    public void setBindTableMonitoring(RichTable _bindTableMonitoring) {
        this._bindTableMonitoring = _bindTableMonitoring;
    }

    public RichTable getBindTableMonitoring() {
        return _bindTableMonitoring;
    }

    public void set_bindTableCID(RichPanelFormLayout _bindTableCID) {
        this._bindTableCID = _bindTableCID;
    }

    public RichPanelFormLayout get_bindTableCID() {
        return _bindTableCID;
    }
    
    public void getNewStatus(ValueChangeEvent vce){
        this._newstatus = vce.getNewValue().toString();
    }
    public void getRemarks(ValueChangeEvent vce){
        this._remarks = vce.getNewValue().toString();
    }


    public void setBindSupervisor(RichOutputText bindSupervisor) {
        this.bindSupervisor = bindSupervisor;
    }

    public RichOutputText getBindSupervisor() {
        return bindSupervisor;
    }

    public void setBindTdcLead(RichOutputText bindTdcLead) {
        this.bindTdcLead = bindTdcLead;
    }

    public RichOutputText getBindTdcLead() {
        return bindTdcLead;
    }

    public String changeStatusCID() {
        // Add event code here...
        System.out.println("Super Admin: "+_superadmin);
        if(_superadmin.equalsIgnoreCase(_username)){
        RichPopup popup = this.getBindUpdateCID();
        //no hints means that popup is launched in the
        //center of the page
        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        popup.show(ph);
        }
        isVisible = true;
        return null;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setSuperadmin(String _superadmin) {
        this._superadmin = _superadmin;
    }

    public String getSuperadmin() {
        return _superadmin;
    }

    public void setInptIdRequest(RichInputText inptIdRequest) {
        this.inptIdRequest = inptIdRequest;
    }

    public RichInputText getInptIdRequest() {
        return inptIdRequest;
    }
}
