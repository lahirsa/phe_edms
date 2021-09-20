package transmittalExternal.vo;

import java.sql.ResultSet;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.server.ViewRowImpl;
import oracle.jbo.server.ViewRowSetImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Apr 16 13:43:24 ICT 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DetailExternalHistoryImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public DetailExternalHistoryImpl() {
    }

    /**
     * Returns the bind variable value for transmittalID.
     * @return bind variable value for transmittalID
     */
    public String gettransmittalID() {
        return AdfFacesContext.getCurrentInstance().getPageFlowScope().get("transmittalId").toString();
    }

    /**
     * Sets <code>value</code> for bind variable transmittalID.
     * @param value value to bind as transmittalID
     */
    public void settransmittalID(String value) {
        setNamedWhereClauseParam("transmittalID", value);
    }

    /**
     * executeQueryForCollection - overridden for custom java data source support.
     */
    protected void executeQueryForCollection(Object qc, Object[] params,
                                             int noUserParams) {
        super.executeQueryForCollection(qc, params, noUserParams);
    }

    /**
     * hasNextForCollection - overridden for custom java data source support.
     */
    protected boolean hasNextForCollection(Object qc) {
        boolean bRet = super.hasNextForCollection(qc);
        return bRet;
    }

    /**
     * createRowFromResultSet - overridden for custom java data source support.
     */
    protected ViewRowImpl createRowFromResultSet(Object qc,
                                                 ResultSet resultSet) {
        ViewRowImpl value = super.createRowFromResultSet(qc, resultSet);
        return value;
    }

    /**
     * getQueryHitCount - overridden for custom java data source support.
     */
    public long getQueryHitCount(ViewRowSetImpl viewRowSet) {
        long value = super.getQueryHitCount(viewRowSet);
        return value;
    }
}
