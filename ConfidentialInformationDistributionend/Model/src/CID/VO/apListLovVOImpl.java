package CID.VO;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri May 17 13:37:12 GMT+07:00 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class apListLovVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public apListLovVOImpl() {
    }

    /**
     * Returns the bind variable value for userLog.
     * @return bind variable value for userLog
     */
    public String getuserLog() {
        return (String)getNamedWhereClauseParam("userLog");
    }

    /**
     * Sets <code>value</code> for bind variable userLog.
     * @param value value to bind as userLog
     */
    public void setuserLog(String value) {
        setNamedWhereClauseParam("userLog", value);
    }
}
