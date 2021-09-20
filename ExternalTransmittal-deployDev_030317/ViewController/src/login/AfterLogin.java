package login;


import com.utils.SessionClass;

import java.text.SimpleDateFormat;

import java.util.Calendar;

import javax.faces.event.PhaseEvent;

import oracle.adf.view.rich.component.rich.input.RichInputText;


public class AfterLogin {
    private RichInputText username;
    private RichInputText password;
    public SessionClass ses = new SessionClass();
    public AfterLogin() {
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

    public void GetSession(PhaseEvent phaseEvent) {
        // Add event code here...
        this.username.setValue(ses.getSessionParameter("username"));
        this.password.setValue(ses.getSessionParameter("password"));
        System.out.println("ExternalTransmittal:Login:AfterLogin:Username: " + this.username.getValue());
      //  System.out.println("ExternalTransmittal:Login:AfterLogin:Password: " + this.password.getValue());
        
      Calendar cal = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
      System.out.println("ExternalTransmittal:Login:AfterLogin:time: " + sdf.format(cal.getTime())); 
    }
}//Menggunakan lempar parameter tanpa cookie 

/*
//Menggunakan cookie dev version tanpa decript
package login;


import com.utils.SessionClass;

import javax.faces.event.PhaseEvent;

import oracle.adf.view.rich.component.rich.input.RichInputText;


public class AfterLogin {
    private RichInputText username;
    private RichInputText password;
    public SessionClass ses = new SessionClass();
    public AfterLogin() {
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

    public void GetSession(PhaseEvent phaseEvent) {
        // Add event code here...
//        ses.setSessionParameter("username", ses.getParameter("j_username"));
//        ses.setSessionParameter("password", ses.getParameter("j_password"));
//        System.out.println("usernamePara set session : " + ses.getParameter("j_username"));
//        System.out.println("passParam set session: " + ses.getParameter("j_password"));
        
        this.username.setValue(ses.getSessionParameter("username"));
        this.password.setValue(ses.getSessionParameter("password"));
        System.out.println("usernamePara setValue: " + this.username.getValue());      
        System.out.println("passParam setValue: " + this.password.getValue());
    }
}
*/