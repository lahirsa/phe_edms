package beans;

import com.bea.xbean.store.Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class utils {
    public utils() {
        super();
    }

    public void update(String query) throws SQLException {
        Connection dbCon = null;
        Statement stat = null;
        //ResultSet result = null;
        try {
            //DEV
            dbCon =
                    DriverManager.getConnection("jdbc:oracle:thin:@10.252.61.167:15678:OWCDEV19C",
                                                "DEVX_OCS", "Pertaminahe21");

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
            System.out.println("\nQuery = " + query);
            stat = dbCon.createStatement();
            stat.executeUpdate(query);
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
