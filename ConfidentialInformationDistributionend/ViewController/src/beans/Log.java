package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Log {
    public Log() {
        super();
    }
    
    public void log(String idrequest, String action, String logdesc) {
        Connection dbCon = null;
        Statement stat = null;
        String Query = "";
        //ResultSet result = null;
        try {
            //DEV
            dbCon =
                    DriverManager.getConnection("jdbc:oracle:thin:@10.252.61.167:15678:OWCDEV19C",
                                                "DEVX_OCS", "Pertaminahe21");
                        //DEV
 //           dbCon =
//                    DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
 //                                               "DEVX_OCS", "Pertaminahe19");

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

        try {Query ="INSERT INTO phe_cid_log (idrequest,action,actiondate,logdescription)VALUES ("+idrequest+",'"+action+"',SYSDATE,'"+logdesc+"')";
            
            System.out.println("\nQuery = " + Query);
            stat = dbCon.createStatement();
            stat.executeUpdate(Query);
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
}
