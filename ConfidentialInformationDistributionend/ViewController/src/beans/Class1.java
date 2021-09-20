package beans;

import CID.Module.AppModuleImpl;

//import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import com.utils.ADFUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class Class1 {
    
    public static final String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
    public static final String MBEAN_SERVER = "weblogic.management.mbeanservers.domainruntime";
    public static final String JNDI_ROOT = "/jndi/";
    public static final String DEFAULT_PROTOCOL = "t3";
    public static final String PROTOCOL_PROVIDER_PACKAGES = "weblogic.management.remote";
    public static final String DOMAIN_MBEAN_NAME = "com.bea:Name=DomainRuntimeService,Type=weblogic.management.mbeanservers.domainruntime.DomainRuntimeServiceMBean";
    private static MBeanServerConnection connection;
    private static ObjectName OID_Provider;
    private static ObjectName defaultAuthenticator;
    private static ObjectName[] authenticationProviders;
    private static String authenticatorName="DefaultAuthenticator";
//    private static final long serialVersionUID = 1L;
    private JMXConnector connector;
    
    public Class1() {
        
        super();
    }
    
    public AppModuleImpl getAm() {
    AppModuleImpl am =
    (AppModuleImpl) ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
    return am;
    }
    
    public static void main(String[] args) {
        Statement stat = null;
        String Query = "";
        ResultSet result = null;
        String str = "user_reviewer: mantap;Rudi Mulia: kitu;Rudi Mulia: kitu;Rudi Mulia: kitu;";
        String findStr = "Rudi Mulia: kitu";
//        int lastIndex = 0;
//        int count = 0;
//
//        while (lastIndex != -1) {
//            lastIndex = str.indexOf(findStr, lastIndex);
//
//            if (lastIndex != -1)
//                count++;
//
//            lastIndex += findStr.length();
//        }
       
                try {
            Query =
                    "select idlist from PHE_CID_LISTDOCREQUEST where Idrequest= '298'";//'" +
//                    idrequest + "' and CIDSTATUSDOCREQUEST='" + docStatus +
//                    "'";
            System.out.println("Query = " + Query);
//            stat = getAm
//            stat = dbCon.createStatement();
            result = stat.executeQuery(Query);
           String checkTable = "0";
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

//        System.out.println(findStr);
    }
    
    public void connect(String host,String port, String username, String password){
        try { 
                   Map h = new HashMap();
//                               Hashtable h = new Hashtable();
                               JMXServiceURL serviceURL;
        serviceURL = new JMXServiceURL(DEFAULT_PROTOCOL, host, Integer.valueOf(port).intValue(),
                                             "/jndi/weblogic.management.mbeanservers.domainruntime");

                   h.put("java.naming.security.principal", username);
                   h.put("java.naming.security.credentials", password);
                   h.put("jmx.remote.protocol.provider.pkgs",
                         "weblogic.management.remote");

                   //Creating a JMXConnector to connect to JMX
                   this.connector =
                       JMXConnectorFactory.connect(serviceURL, h);

                   connection = this.connector.getMBeanServerConnection();
                    System.out.println("connection : "+connection);
                   /****
                     We Get Objects by creating ObjectName with it's Qualified name.
                     The constructor take a String of the full Qualified name of the MBean
                     We then use connection to get Attribute out of this ObjectName but specifying a String of
                     this Attribute
                     *****/

                   ObjectName configurationMBeans=
                       new ObjectName(DOMAIN_MBEAN_NAME);
                   ObjectName domain =
                       (ObjectName)connection.getAttribute(configurationMBeans, "DomainConfiguration");
                    System.out.println("Domain : "+domain);
                   ObjectName security =
                       (ObjectName)connection.getAttribute(domain, "SecurityConfiguration");
                System.out.println("Security : "+security);
                   ObjectName realm =
                       (ObjectName)connection.getAttribute(security, "DefaultRealm");
    System.out.println("Realm : "+realm);
                   authenticationProviders =
                           (ObjectName[])connection.getAttribute(realm,
                                                                 "AuthenticationProviders");
    System.out.println("authentic : "+authenticationProviders);
                   for (int i = 0; i < authenticationProviders.length; i++) {
                       String name =
                           (String)connection.getAttribute(authenticationProviders[i],
                                                           "Name");
                        System.out.println("Name : "+name);
                       if (name.equals(authenticatorName))
                           defaultAuthenticator = authenticationProviders[i];
                   }
            System.out.println("Provider : "+defaultAuthenticator);
               } catch (Exception e) {
                   throw new RuntimeException(e);
               }
    }
    
    
}
