package transmittalExternal.vo;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.server.ViewObjectImpl;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Apr 21 09:38:42 ICT 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DetailInternalSupportinDocVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public DetailInternalSupportinDocVOImpl() {
    }

    /**
     * Returns the bind variable value for transmittal_id.
     * @return bind variable value for transmittal_id
     */
    public String gettransmittal_id() {
        return AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString();
    }

    /**
     * Sets <code>value</code> for bind variable transmittal_id.
     * @param value value to bind as transmittal_id
     */
    public void settransmittal_id(String value) {
        setNamedWhereClauseParam("transmittal_id", value);
    }
}
