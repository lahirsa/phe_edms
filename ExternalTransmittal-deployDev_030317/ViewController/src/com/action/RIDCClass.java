package com.action;

import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

import oracle.stellent.ridc.IdcClientException;
import oracle.stellent.ridc.IdcClientManager;
import oracle.stellent.ridc.IdcContext;
import oracle.stellent.ridc.model.DataBinder;
import oracle.stellent.ridc.model.DataObject;
import oracle.stellent.ridc.model.DataResultSet;
import oracle.stellent.ridc.model.TransferFile;
import oracle.stellent.ridc.protocol.ServiceResponse;
import oracle.stellent.ridc.protocol.http.IdcHttpClient;


public class RIDCClass {
//    public String IDC_SERVER = "http://kponwjap005:16300/urm/idcplg";
//   public String IDC_SERVER = "http://phekpdmdv01.pertamina.com:16200/cs/idcplg";
    public String IDC_SERVER = "http://phekpdm005.pertamina.com:16200/cs/idcplg";
    public static final int CONNECTION_SIZE = 100;
    private IdcClientManager manager;
    private IdcHttpClient idcClient;
    private IdcContext userContext;
    private String statusMessage;
    
    public RIDCClass(String username, String password) {
        Properties prop = new Properties();
        
        this.manager = new IdcClientManager();
        this.userContext = new IdcContext(username, password);

        try {
            this.idcClient = (IdcHttpClient)manager.createClient(IDC_SERVER);
            this.idcClient.getConfig().setConnectionSize(CONNECTION_SIZE);
        } catch (IdcClientException e) {
            e.printStackTrace();
        }
    }
    
    public DataObject GetFileInfo(String did) {
            DataObject resultSet = null;
            try {
                DataBinder binder = this.idcClient.createBinder();
                binder.putLocal("IdcService", "DOC_INFO");
                binder.putLocal("dID", did);
                ServiceResponse response = this.idcClient.sendRequest(this.userContext, binder);
                DataBinder responseBinder = response.getResponseAsBinder();
                DataResultSet myDataResultSet =
                    responseBinder.getResultSet("FileInfo");
                resultSet = myDataResultSet.getRows().get(myDataResultSet.getRows().size()-1);
                response.close();
                
            } catch (Exception e) {
                System.out.println("document with dID "+did+" doesn't exist in the system");
                e.printStackTrace();
                resultSet = null;
            }
            return resultSet;
        }
    
    public DataObject GetDocInfo(String did) {
            DataObject resultSet = null;
            try {
                DataBinder binder = this.idcClient.createBinder();
                binder.putLocal("IdcService", "DOC_INFO");
                binder.putLocal("dID", did);
                ServiceResponse response = this.idcClient.sendRequest(this.userContext, binder);
                DataBinder responseBinder = response.getResponseAsBinder();
                DataResultSet myDataResultSet =
                    responseBinder.getResultSet("DOC_INFO");
                resultSet = myDataResultSet.getRows().get(myDataResultSet.getRows().size()-1);
                response.close();
                
            } catch (Exception e) {
                System.out.println("document with dID "+did+" doesn't exist in the system");
                e.printStackTrace();
                resultSet = null;
            }
            return resultSet;
        }
    
    public DataObject GetDocInfobyName(String ddocname) {
            DataObject resultSet = null;
            try {
                DataBinder binder = this.idcClient.createBinder();
                binder.putLocal("IdcService", "DOC_INFO_BY_NAME");
                binder.putLocal("dDocName", ddocname);
                ServiceResponse response = this.idcClient.sendRequest(this.userContext, binder);
                DataBinder responseBinder = response.getResponseAsBinder();
                DataResultSet myDataResultSet =
                    responseBinder.getResultSet("DOC_INFO");
                resultSet = myDataResultSet.getRows().get(myDataResultSet.getRows().size()-1);
                response.close();
                
            } catch (Exception e) {
                e.printStackTrace();
                resultSet = null;
            }
            return resultSet;
        }
    
    public void UpdateDocxRobjId(String did,String dDocName){
        try {
            DataBinder binder = this.idcClient.createBinder();
            binder.putLocal("IdcService", "UPDATE_DOCINFO");
            binder.putLocal("dID", did);
            binder.putLocal("dDocName", dDocName);
            binder.putLocal("xRObjectId", did);
            ServiceResponse response = this.idcClient.sendRequest(this.userContext, binder);
            DataBinder responseBinder = response.getResponseAsBinder();
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String AssignACL(String did, String ddocname, String access){
        try {
            DataBinder binder = this.idcClient.createBinder();
            binder.putLocal("IdcService", "UPDATE_DOCINFO");
            binder.putLocal("dID", did);
            binder.putLocal("dDocName", ddocname);
            binder.putLocal("xClbraUserList", access);
            ServiceResponse response = this.idcClient.sendRequest(this.userContext, binder);
            DataBinder responseBinder = response.getResponseAsBinder();
            response.close();
            return "Success update access list";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed update access list";
        }
    }
    
    public String DeleteDoc(String did, String ddocname){
        try {
            DataBinder binder = this.idcClient.createBinder();
            binder.putLocal("IdcService", "DELETE_DOC");
            binder.putLocal("dID", did);
            binder.putLocal("dDocName", ddocname);
            ServiceResponse response = this.idcClient.sendRequest(this.userContext, binder);
            response.close();
            return "Success delete";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed delete";
        }
    }
    
    public void UpdateAfterCopyFile(String did,String dDocName,String dDocTitle,
                                    String xDocName, String xDocNumber, String dRevLabel){
        try {
            DataBinder binder = this.idcClient.createBinder();
            binder.putLocal("IdcService", "UPDATE_DOCINFO");
            binder.putLocal("dID", did);
            binder.putLocal("dDocName", dDocName);
            binder.putLocal("xRObjectId", did);
            binder.putLocal("dDocTitle", dDocTitle);
            binder.putLocal("dSecurityGroup", "Workflow");
            binder.putLocal("dDocType", "Transmittal");
            binder.putLocal("xDocName", xDocName);
            binder.putLocal("xDocNumber", xDocNumber);
            binder.putLocal("xRevision", dRevLabel);
            binder.putLocal("dDocAccount", "");
            binder.putLocal("xStatus", "");
            ServiceResponse response = this.idcClient.sendRequest(this.userContext, binder);
            DataBinder responseBinder = response.getResponseAsBinder();
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void UpdateAfterCopyFile(String did,String dDocName,String dDocTitle,
                                    String xDocName, String xDocNumber, String dRevLabel,String dDocAuthor){
        try {
            DataBinder binder = this.idcClient.createBinder();
            binder.putLocal("IdcService", "UPDATE_DOCINFO");
            binder.putLocal("dID", did);
            binder.putLocal("dDocName", dDocName);
            binder.putLocal("xRObjectId", did);
            binder.putLocal("dDocTitle", dDocTitle);
            binder.putLocal("dSecurityGroup", "Workflow");
            binder.putLocal("dDocType", "Transmittal");
            binder.putLocal("xDocName", xDocName);
            binder.putLocal("xDocNumber", xDocNumber);
            binder.putLocal("xRevision", dRevLabel);
            binder.putLocal("dDocAccount", "");
            binder.putLocal("xStatus", "");
            binder.putLocal("dDocAuthor", dDocAuthor);
            ServiceResponse response = this.idcClient.sendRequest(this.userContext, binder);
            DataBinder responseBinder = response.getResponseAsBinder();
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String GetFolderPathFromGuid(String fguid){
        try {
            DataBinder binder = this.idcClient.createBinder();
            binder.putLocal("IdcService", "FLD_INFO");
            binder.putLocal("fFolderGUID", fguid);
            ServiceResponse response =
                this.idcClient.sendRequest(this.userContext, binder);
            DataBinder responseBinder = response.getResponseAsBinder();
            String rs = responseBinder.getLocal("folderPath");
            response.close();
            return rs;
        } catch (IdcClientException e) {
            System.out.println(e.getMessage());
            return "";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
    
    public DataObject FolderInfo(String path){
            DataObject dObj =null;
            try {
                DataBinder binder = this.idcClient.createBinder();
                binder.putLocal("IdcService", "FLD_INFO");
                binder.putLocal("path", path);
                ServiceResponse response =
                    this.idcClient.sendRequest(this.userContext, binder);
                DataBinder responseBinder = response.getResponseAsBinder();
                DataResultSet rs = responseBinder.getResultSet("FolderInfo");
                dObj = rs.getRows().get(rs.getRows().size()-1);
                response.close();
                return dObj;
            } catch (IdcClientException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return dObj;
        }
    
    public DataObject FolderFileInfo(String fileguid){
            DataObject dObj =null;
            try {
                DataBinder binder = this.idcClient.createBinder();
                binder.putLocal("IdcService", "FLD_INFO");
                binder.putLocal("fFileGUID", fileguid);
                ServiceResponse response =
                    this.idcClient.sendRequest(this.userContext, binder);
                DataBinder responseBinder = response.getResponseAsBinder();
                DataResultSet rs = responseBinder.getResultSet("FileInfo");
                dObj = rs.getRows().get(rs.getRows().size()-1);
                response.close();
                return dObj;
            } catch (IdcClientException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return dObj;
        }
    
    public DataResultSet FolderBrowseFile(String id){
        DataResultSet rs =null;
        try {
            DataBinder binder = this.idcClient.createBinder();
            binder.putLocal("IdcService", "FLD_BROWSE");
            binder.putLocal("fFolderGUID", id);
            ServiceResponse response =
                this.idcClient.sendRequest(this.userContext, binder);
            DataBinder responseBinder = response.getResponseAsBinder();
            rs = responseBinder.getResultSet("ChildFiles");
            response.close();
            return rs;
        } catch (IdcClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public DataResultSet FolderSearchFile(String folderGUID,String filename){
        DataResultSet rs =null;
        try {
            DataBinder binder = this.idcClient.createBinder();
            binder.putLocal("IdcService", "FLD_FOLDER_SEARCH");
            binder.putLocal("itemType", "File");
            binder.putLocal("fParentGUID", folderGUID);
            String query = "(fFileName <starts> `"+filename+"`)  <AND>  (fParentGUID <matches> `"+folderGUID+"`)";
            binder.putLocal("QueryText", query);
            ServiceResponse response =
                this.idcClient.sendRequest(this.userContext, binder);
            DataBinder responseBinder = response.getResponseAsBinder();
            rs = responseBinder.getResultSet("SEARCH_RESULTS");
            response.close();
            return rs;
        } catch (IdcClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public DataObject CopyFile(String destination, String fileGUID){
        DataObject dObj =null;
        try {
            DataBinder binder = this.idcClient.createBinder();
            binder.putLocal("IdcService", "FLD_COPY");
            binder.putLocal("destination", "fFolderGUID:"+destination);
            binder.putLocal("item1", "fFileGUID:"+fileGUID);
            ServiceResponse response =
                this.idcClient.sendRequest(this.userContext, binder);
            DataBinder responseBinder = response.getResponseAsBinder();
            DataResultSet rs = responseBinder.getResultSet("NewDocInfo");
            dObj = rs.getRows().get(rs.getRows().size()-1);
            response.close();
            return dObj;
        } catch (IdcClientException e) {
            e.printStackTrace();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dObj;
    }
    
    
    public String CheckinEDMS(String fFolderGUID,String xDocName, String xDocNumber, String dDocTitle,
                                    InputStream fileinputStream, long fileLength, String fileName){
        try {
            DataBinder binder = this.idcClient.createBinder();
            binder.putLocal("IdcService", "CHECKIN_UNIVERSAL");
            binder.putLocal("dDocTitle", dDocTitle);
            binder.putLocal("fParentGUID", fFolderGUID);
            binder.putLocal("dSecurityGroup", "Workflow");
            binder.putLocal("dDocType", "Transmittal");
            binder.putLocal("xDocName", xDocName);
            binder.putLocal("xDocNumber", xDocNumber);
//            binder.putLocal("dRevLabel", dRevLabel);
            binder.putLocal("dDocAccount", "");
            binder.addFile("primaryFile",
                           new TransferFile(fileinputStream, fileName,
                                            fileLength));
            ServiceResponse response =
                this.idcClient.sendRequest(this.userContext, binder);
            DataBinder responseBinder = response.getResponseAsBinder();
            response.close();
            return(responseBinder.getLocal("dID"));
        } catch (IdcClientException e) {
            e.printStackTrace();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public String CheckinElectronic(String fFolderGUID,String xDocName, String xDocNumber, String dDocTitle,
                                    String dRevLabel,InputStream fileinputStream, long fileLength, String fileName){
        try {
            DataBinder binder = this.idcClient.createBinder();
            binder.putLocal("IdcService", "CHECKIN_UNIVERSAL");
            binder.putLocal("dDocTitle", dDocTitle);
            binder.putLocal("fParentGUID", fFolderGUID);
            binder.putLocal("dSecurityGroup", "Workflow");
            binder.putLocal("dDocType", "Transmittal");
            binder.putLocal("xDocName", xDocName);
            binder.putLocal("xDocNumber", xDocNumber);
            binder.putLocal("xRevision", dRevLabel);
            binder.putLocal("dDocAccount", "");
            binder.addFile("primaryFile",
                           new TransferFile(fileinputStream, fileName,
                                            fileLength));
            ServiceResponse response =
                this.idcClient.sendRequest(this.userContext, binder);
            DataBinder responseBinder = response.getResponseAsBinder();
            response.close();
            return(responseBinder.getLocal("dID"));
        } catch (IdcClientException e) {
            e.printStackTrace();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public InputStream getFileInputStreamWithDID(String dID) throws IdcClientException {

        System.out.println("Preparing to GET_FILE from dDocName : "+dID);
        DataBinder binder = this.idcClient.createBinder();
        binder.putLocal("IdcService", "GET_FILE");
        binder.putLocal("dID", dID);
        //binder.putLocal("allowInterrupt", "true");
        //binder.putLocal("noSaveAs", "true");
        //binder.putLocal("RevisionSelectionMethod", "LatestReleased");
        ServiceResponse response =
            this.idcClient.sendRequest(this.userContext, binder);
        return response.getResponseStream();
    }
    
    
    public String CreateFolderTransmittal(String folderName, String path){
        String exception = "";
        try {
            exception = CreateFolder(folderName, path);
            exception = CreateFolder("Submittal", path+"/"+folderName);
            exception = CreateFolder("Supporting Documents", path+"/"+folderName);
            return "Success";
        } catch (Exception e) {
            return exception;
        }
    }
        
    public String CreateFolder(String folderName, String path){
        try {
            DataObject ob = FolderInfo(path);
            DataBinder binder = this.idcClient.createBinder();
            binder.putLocal("IdcService", "FLD_CREATE_FOLDER");
            binder.putLocal("fFolderName", folderName);
            binder.putLocal("fParentGUID", ob.get("fFolderGUID").toString());
            ServiceResponse response =
                this.idcClient.sendRequest(this.userContext, binder);
            DataBinder responseBinder = response.getResponseAsBinder();
            response.close();
            return "Success Create Folder : "+folderName;
        } catch (IdcClientException e) {
            return "Failed Creata Folder : "+folderName+" "+e.getMessage();
        } catch (Exception e) {
            return "Failed Creata Folder : "+folderName+" "+e.getMessage();
        }
    }
    
    public static void main(String[] args){
        RIDCClass ridc = new RIDCClass("weblogic","Pertaminahe21");
        //ridc.createLink("/Document Secure Workflow/Non Routine/AGIT(2014)/PHEONWJ-AG001-PO10803-10003-0001/Supporting Documents", "3620");
//        DataObject ob = ridc.FolderInfo("Cabinets PHE NSB/DSWF NSB/Non Routine/AbarDSWF06");
        DataObject ob = ridc.FolderInfo("/Document Secure Workflow/Non Routine/Test Project/Documents");
        System.out.println(ob.get("fFolderGUID").toString());
//        ridc.CreateFolder("Documents", "/Document Secure Workflow/Non Routine/Test Project");
    }
    
    public String createLinkWithFolderGUID(String fguid, String did){
        try {
            DataObject ob1 = GetDocInfo(did);
            DataBinder binder = this.idcClient.createBinder();
            binder.putLocal("IdcService", "FLD_CREATE_FILE");
            binder.putLocal("fFileType", "soft");
            System.out.println(fguid);
            binder.putLocal("fParentGUID", fguid);
            binder.putLocal("contructDidCreateLinkDialog", "1");
            binder.putLocal("dID", did);
            System.out.println(did+" "+ob1.get("dDocName").toString()+" "+ob1.get("dOriginalName").toString());
            binder.putLocal("dDocName", ob1.get("dDocName").toString());
            binder.putLocal("fFileName", ob1.get("dOriginalName").toString());
            ServiceResponse response =
                this.idcClient.sendRequest(this.userContext, binder);
            DataBinder responseBinder = response.getResponseAsBinder();
            response.close();
            return "Success";
        } catch (IdcClientException e) {
            return "Failed "+e.getMessage();
        } catch (Exception e) {
            return "Failed "+e.getMessage();
        }
    }
    
    public String createLink(String path, String did){
        try {
            DataObject ob = FolderInfo(path);
            DataObject ob1 = GetDocInfo(did);
            DataBinder binder = this.idcClient.createBinder();
            binder.putLocal("IdcService", "FLD_CREATE_FILE");
            binder.putLocal("fFileType", "soft");
            System.out.println(ob.get("fFolderGUID").toString());
            binder.putLocal("fParentGUID", ob.get("fFolderGUID").toString());
            binder.putLocal("contructDidCreateLinkDialog", "1");
            binder.putLocal("dID", did);
            System.out.println(did+" "+ob1.get("dDocName").toString()+" "+ob1.get("dOriginalName").toString());
            binder.putLocal("dDocName", ob1.get("dDocName").toString());
            binder.putLocal("fFileName", ob1.get("dOriginalName").toString());
            ServiceResponse response =
                this.idcClient.sendRequest(this.userContext, binder);
            DataBinder responseBinder = response.getResponseAsBinder();
            response.close();
            return "Success";
        } catch (IdcClientException e) {
            return "Failed "+e.getMessage();
        } catch (Exception e) {
            return "Failed "+e.getMessage();
        }
    }
    
    public String createLinkReadme(String path, String dDocName){
        try {
            DataObject ob = FolderInfo(path);
            DataObject ob1 = GetDocInfobyName(dDocName);
            DataBinder binder = this.idcClient.createBinder();
            binder.putLocal("IdcService", "FLD_CREATE_FILE");
            binder.putLocal("fFileType", "soft");
            System.out.println(ob.get("fFolderGUID").toString());
            binder.putLocal("fParentGUID", ob.get("fFolderGUID").toString());
            binder.putLocal("contructDidCreateLinkDialog", "1");
            binder.putLocal("dID", ob1.get("dID").toString());
            System.out.println(ob1.get("dID").toString()+" "+ob1.get("dDocName").toString()+" "+ob1.get("dOriginalName").toString());
            binder.putLocal("dDocName", ob1.get("dDocName").toString());
            binder.putLocal("fFileName", ob1.get("dOriginalName").toString());
            ServiceResponse response =
                this.idcClient.sendRequest(this.userContext, binder);
            DataBinder responseBinder = response.getResponseAsBinder();
            response.close();
            return "Success";
        } catch (IdcClientException e) {
            return "Failed "+e.getMessage();
        } catch (Exception e) {
            return "Failed "+e.getMessage();
        }
    }
    
    public String CheckinCoverNote(InputStream is, String fileName, long filelength,String folder) throws IdcClientException, IOException {
            try {
                DataObject ob = FolderInfo(folder);
                DataBinder binder = this.idcClient.createBinder();
                binder.putLocal("IdcService", "CHECKIN_UNIVERSAL");
                binder.putLocal("dSecurityGroup", "Workflow");
                binder.putLocal("fParentGUID", ob.get("fFolderGUID").toString());
                binder.putLocal("dDocAccount", "");
                binder.putLocal("dDocTitle", fileName);
                binder.putLocal("xDocName", fileName);
                binder.addFile("primaryFile", new TransferFile(is,fileName,filelength));
                ServiceResponse response = idcClient.sendRequest(userContext, binder);
                DataBinder responseBinder = response.getResponseAsBinder();
                String as = responseBinder.getLocal("dID");
    //            DataObject a = responseBinder.getLocalData();
    //            DataResultSet resultSet = responseBinder.getResultSet("COLLECTIONBRANCH");
                response.close();
                return as;

            } catch (IdcClientException e) {
                e.printStackTrace();
                return "";
            }
        }

    public void setIDC_SERVER(String IDC_SERVER) {
        this.IDC_SERVER = IDC_SERVER;
    }

    public String getIDC_SERVER() {
        return IDC_SERVER;
    }
    
    public InputStream getNewFileInputStreamWithDID(String dID) throws IdcClientException {

            DataBinder binder = this.idcClient.createBinder();
            binder.putLocal("IdcService", "GET_FILE");
            binder.putLocal("dID", dID);

            ServiceResponse response =
                    this.idcClient.sendRequest(this.userContext, binder);
            return response.getResponseStream();
    }
}
