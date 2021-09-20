package beans;

import com.utils.ConnectHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.stellent.ridc.IdcClient;
import oracle.stellent.ridc.IdcClientException;
import oracle.stellent.ridc.IdcContext;
import oracle.stellent.ridc.model.DataBinder;
import oracle.stellent.ridc.protocol.ServiceResponse;

public class updateDocMeta {
    public updateDocMeta() {
        super();
    }

    public void updateDocMetadata(String dID, String CIDStatus) throws SQLException {
        Connection dbCon = null;
        Statement stat = null;
        ResultSet result = null;

        String Query =
            "SELECT R.DID, F.FPARENTGUID,R.DDOCNAME FROM REVISIONS R,  FOLDERFILES F WHERE R.DDOCNAME = F.DDOCNAME AND R.DID ='" +
            dID + "'";
        System.out.println("\nQuery = " + Query);
        try {

            // Prod DOCUMENTUM
            //             dbCon = DriverManager.getConnection(
            //                           "jdbc:oracle:thin:@PHEKPDBDV09.pertamina.com:1521:owc12cdv",
            //                         "owc_staging", "staging2014owc");

            //prod owc
            //                      dbCon = DriverManager.getConnection(
            //                                      "jdbc:oracle:thin:@PHEKPDBDV09.pertamina.com:1521:owc12cdv",
            //                                      "DEVX_OCS", "Pertaminahe19");

            //DEV
            dbCon =
                    DriverManager.getConnection("jdbc:oracle:thin:@10.252.61.167:15678:OWCDEV19C",
                                                "DEVX_OCS", "Pertaminahe21");


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed! Check output console");
            return;
        }

        if (dbCon != null) {
            System.out.println("Connected to Database!");

        } else {
            System.out.println("Failed to make connection!");
        }

        try {
            stat = dbCon.createStatement();
            result = stat.executeQuery(Query);
        } catch (SQLException e) {
        }


        //      System.out.println("\ndocname = "+result.getString("dDocName"));
        //        System.out.println("\ndID = "+result.getString("dID"));
        //        System.out.println("\nfParentGUID = "+result.getString("fParentGUID"));
        //        System.out.println("\nxCIDStatus = "+CIDStatus);
        ConnectHandler Conn = new ConnectHandler();
        // Prod
        //Conn.createConnection("idc://10.252.4.111:4444", "owc.admin", "Pertaminahe19");
  
        //dev
        try {
            Conn.createConnection("idc://phekpdmdv01.pertamina.com:4444", "weblogic",
                                  "Pertaminahe21");
        } catch (IdcClientException e) {
        }
        IdcClient client = Conn.getClient();
        IdcContext connectionContext = Conn.getConnectionContext();
        while (result.next()) {
        try {
            DataBinder requestData = client.createBinder();
            requestData.putLocal("IdcService", "UPDATE_DOCINFO");
            requestData.putLocal("dDocName", result.getString("dDocName"));
            requestData.putLocal("dID", result.getString("dID"));
            requestData.putLocal("fParentGUID", result.getString("fParentGUID"));
            requestData.putLocal("xCIDStatus", CIDStatus);
            ServiceResponse response = client.sendRequest(connectionContext, requestData);
            DataBinder binder = response.getResponseAsBinder();
        } catch (SQLException e) {
        } catch (IdcClientException e) {
        }
            }
        try {
            result.close();
            stat.close();
            dbCon.close();
        } catch (SQLException e) {
        }

    }
}
