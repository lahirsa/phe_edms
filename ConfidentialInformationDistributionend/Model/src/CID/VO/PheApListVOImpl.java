package CID.VO;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue May 14 15:06:26 GMT+07:00 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PheApListVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public PheApListVOImpl() {
    }

    /**
     * Returns the bind variable value for dUser.
     * @return bind variable value for dUser
     */
    public String getdUser() {
        return (String)getNamedWhereClauseParam("dUser");
    }

    /**
     * Sets <code>value</code> for bind variable dUser.
     * @param value value to bind as dUser
     */
    public void setdUser(String value) {
        setNamedWhereClauseParam("dUser", value);
    }
}
