package CID.VO;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon May 20 14:33:52 GMT+07:00 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CIDrunNumberAPVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CIDrunNumberAPVOImpl() {
    }

    /**
     * Returns the bind variable value for APNAMA.
     * @return bind variable value for APNAMA
     */
    public String getAPNAMA() {
        return (String)getNamedWhereClauseParam("APNAMA");
    }

    /**
     * Sets <code>value</code> for bind variable APNAMA.
     * @param value value to bind as APNAMA
     */
    public void setAPNAMA(String value) {
        setNamedWhereClauseParam("APNAMA", value);
    }
}
