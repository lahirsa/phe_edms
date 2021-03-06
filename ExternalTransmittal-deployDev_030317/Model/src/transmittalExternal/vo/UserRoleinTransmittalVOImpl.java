package transmittalExternal.vo;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.server.ViewObjectImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Apr 18 15:43:08 ICT 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class UserRoleinTransmittalVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public UserRoleinTransmittalVOImpl() {
    }

    /**
     * Returns the bind variable value for transmittalId.
     * @return bind variable value for transmittalId
     */
    public String gettransmittalId() {
        return AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString().trim();
//        return (String)getNamedWhereClauseParam("transmittalId");
    }

    /**
     * Sets <code>value</code> for bind variable transmittalId.
     * @param value value to bind as transmittalId
     */
    public void settransmittalId(String value) {
        setNamedWhereClauseParam("transmittalId", value);
    }

    /**
     * Returns the bind variable value for receiver.
     * @return bind variable value for receiver
     */
    public String getreceiver() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest requestSession = (HttpServletRequest)ctx.getExternalContext().getRequest();
        HttpSession session =  requestSession.getSession(true);
        //return session.getAttribute("username").toString();
        return  AdfFacesContext.getCurrentInstance().getPageFlowScope().get("dUser").toString().trim();
    }

    /**
     * Sets <code>value</code> for bind variable receiver.
     * @param value value to bind as receiver
     */
    public void setreceiver(String value) {
        setNamedWhereClauseParam("receiver", value);
    }
}
