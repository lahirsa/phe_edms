package CID.VO;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed May 15 16:17:16 GMT+07:00 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TdcLeadValVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public TdcLeadValVOImpl() {
    }

    /**
     * Returns the bind variable value for NAMA.
     * @return bind variable value for NAMA
     */
    public String getNAMA() {
        return (String)getNamedWhereClauseParam("NAMA");
    }

    /**
     * Sets <code>value</code> for bind variable NAMA.
     * @param value value to bind as NAMA
     */
    public void setNAMA(String value) {
        setNamedWhereClauseParam("NAMA", value);
    }
}
