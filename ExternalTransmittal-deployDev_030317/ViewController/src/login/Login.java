/*package login;

 
import com.utils.SessionClass;

import java.io.Serializable;

import javax.faces.event.PhaseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Login implements Serializable {
    public SessionClass ses = new SessionClass();

    public Login() {
    }
    public String convertToString(String s){
            String value="";
            int interval = 3;
            int arrayLength = (int) Math.ceil(((s.length() / (double)interval)));
            String[] result = new String[arrayLength];

            int j = 0;
            int lastIndex = result.length - 1;
            for (int i = 0; i < lastIndex; i++) {
                result[i] = s.substring(j, j + interval);
                j += interval;
            } //Add the last bit
            result[lastIndex] = s.substring(j);
            for (int i=0;i<arrayLength;i++){
                        //System.out.println(result[i]);
                        //System.out.println((char) Integer.parseInt(result[i]));
                    value=value+(char) Integer.parseInt(result[i]);
                }
            //System.out.println("hasil decode = "+value);
            return value;
        }
    public void SetSession(PhaseEvent phaseEvent) {
        // Add event code here...
        System.out.println("Get IN !");   
//        ses.setSessionParameter("username", convertToString(ses.getParameter("j_username")));
//        ses.setSessionParameter("password", convertToString(ses.getParameter("j_password")));
      // System.out.println("ExternalTransmittal:Login:Login:Username : " + ses.getParameter("j_username"));
      // System.out.println("ExternalTransmittal:Login:Login:Password : " + ses.getParameter("j_password"));
      // System.out.println("ExternalTransmittal:Login:Login:Username_convertToString : " + convertToString(ses.getParameter("j_username")));
      // System.out.println("ExternalTransmittal:Login:Login:Password_convertToString : " + convertToString(ses.getParameter("j_password")));
//       System.out.println("ExternalTransmittal:Login:Login:Username_Session: " + ses.getSessionParameter("username"));
      // System.out.println("ExternalTransmittal:Login:Login:Password_Session : " + ses.getSessionParameter("password"));
       
//      Calendar cal = Calendar.getInstance();
//      SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//      System.out.println("ExternalTransmittal:Login:Login:time: " + sdf.format(cal.getTime())); 
    }
} *///Menggunakan lempar parameter tanpa cookie 



//Menggunakan cookie dev version tanpa decript
package login;

import com.utils.SessionClass;
import java.io.Serializable;
import javax.faces.event.PhaseEvent;
import oracle.adf.view.rich.component.rich.input.RichInputText;

public class Login implements Serializable {
    private RichInputText username;
    private RichInputText password;
    public SessionClass ses = new SessionClass();
    public String[] parts;

    public Login() {
    }
    public void setUsername(RichInputText username) {
        this.username = username;
    }

    public RichInputText getUsername() {
        return username;
    }

    public void setPassword(RichInputText password) {
        this.password = password;
    }

    public RichInputText getPassword() {
        return password;
    }
    public void SetSession(PhaseEvent phaseEvent) {
        // Add event code here...
        
     //   System.out.println("yuhuuu !"); 
//        System.out.println("getHeader:" + ses.getHeader("User-Agent"));
//        System.out.println("getHeader Cookie:" + ses.getHeader("Cookie"));
        // modified by nanda 300315 - add trycatch jika cookie ternyata di disabled
        try{
            try{
                String mystring = ses.getHeader("Cookie");
                parts = mystring.split("; ");
              
            }catch(Exception e){
                e.printStackTrace();
                this.password.setValue("CookieDisabled");
            }
            String[] temp;
        
        for(int i=0; i < parts.length; i++)
           {
               temp= parts[i].split("=");
               System.out.println("parts:" + i + "; value:" +temp[0]+"passPage");
               System.out.println("parts:" + i + "; value:" +temp[1]);
               if(temp[0].equalsIgnoreCase("passPage")) {
                   System.out.println("password setSession:" +temp[1]);
                   ses.setSessionParameter("password", temp[1]);
                   this.password.setValue(temp[1]);
                   }
           }
       
        ses.setSessionParameter("username", ses.getParameter("j_username"));
        this.username.setValue(ses.getParameter("j_username"));
        System.out.println("Get IN !");   
        System.out.println("usernamePara setSession : " + ses.getParameter("j_username"));
//        System.out.println("passParam ses.getParam: " + ses.getParameter("j_password"));
        }catch(Exception e){
            e.printStackTrace();
            this.password.setValue("CookieDisabled");
        }
    }
}
