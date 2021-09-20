package CID.EO;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.domain.Number;
import oracle.jbo.domain.Timestamp;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.TransactionEvent;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu May 16 13:24:12 GMT+07:00 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PheCidListrequestEOImpl extends EntityImpl {
    private static EntityDefImpl mDefinitionObject;
    private Row runNumCid;
    
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Idrequest {
            public Object get(PheCidListrequestEOImpl obj) {
                return obj.getIdrequest();
            }

            public void put(PheCidListrequestEOImpl obj, Object value) {
                obj.setIdrequest((Number)value);
            }
        }
        ,
        Cidrequestor {
            public Object get(PheCidListrequestEOImpl obj) {
                return obj.getCidrequestor();
            }

            public void put(PheCidListrequestEOImpl obj, Object value) {
                obj.setCidrequestor((String)value);
            }
        }
        ,
        Cidrequestorposition {
            public Object get(PheCidListrequestEOImpl obj) {
                return obj.getCidrequestorposition();
            }

            public void put(PheCidListrequestEOImpl obj, Object value) {
                obj.setCidrequestorposition((String)value);
            }
        }
        ,
        Cidpurpose {
            public Object get(PheCidListrequestEOImpl obj) {
                return obj.getCidpurpose();
            }

            public void put(PheCidListrequestEOImpl obj, Object value) {
                obj.setCidpurpose((String)value);
            }
        }
        ,
        Cidrequestorsupervisor {
            public Object get(PheCidListrequestEOImpl obj) {
                return obj.getCidrequestorsupervisor();
            }

            public void put(PheCidListrequestEOImpl obj, Object value) {
                obj.setCidrequestorsupervisor((String)value);
            }
        }
        ,
        Cidrequestorsupervisorposition {
            public Object get(PheCidListrequestEOImpl obj) {
                return obj.getCidrequestorsupervisorposition();
            }

            public void put(PheCidListrequestEOImpl obj, Object value) {
                obj.setCidrequestorsupervisorposition((String)value);
            }
        }
        ,
        Ciddaterequest {
            public Object get(PheCidListrequestEOImpl obj) {
                return obj.getCiddaterequest();
            }

            public void put(PheCidListrequestEOImpl obj, Object value) {
                obj.setCiddaterequest((Timestamp)value);
            }
        }
        ,
        Cidtdclead {
            public Object get(PheCidListrequestEOImpl obj) {
                return obj.getCidtdclead();
            }

            public void put(PheCidListrequestEOImpl obj, Object value) {
                obj.setCidtdclead((String)value);
            }
        }
        ,
        Cidstatusrequest {
            public Object get(PheCidListrequestEOImpl obj) {
                return obj.getCidstatusrequest();
            }

            public void put(PheCidListrequestEOImpl obj, Object value) {
                obj.setCidstatusrequest((String)value);
            }
        }
        ,
        Ciddateapprovesv {
            public Object get(PheCidListrequestEOImpl obj) {
                return obj.getCiddateapprovesv();
            }

            public void put(PheCidListrequestEOImpl obj, Object value) {
                obj.setCiddateapprovesv((Timestamp)value);
            }
        }
        ,
        Cidapprovetdc {
            public Object get(PheCidListrequestEOImpl obj) {
                return obj.getCidapprovetdc();
            }

            public void put(PheCidListrequestEOImpl obj, Object value) {
                obj.setCidapprovetdc((Timestamp)value);
            }
        }
        ,
        Ciddateapprovetdclead {
            public Object get(PheCidListrequestEOImpl obj) {
                return obj.getCiddateapprovetdclead();
            }

            public void put(PheCidListrequestEOImpl obj, Object value) {
                obj.setCiddateapprovetdclead((Timestamp)value);
            }
        }
        ,
        Cidattach {
            public Object get(PheCidListrequestEOImpl obj) {
                return obj.getCidattach();
            }

            public void put(PheCidListrequestEOImpl obj, Object value) {
                obj.setCidattach((String)value);
            }
        }
        ,
        Cidinternaluser {
            public Object get(PheCidListrequestEOImpl obj) {
                return obj.getCidinternaluser();
            }

            public void put(PheCidListrequestEOImpl obj, Object value) {
                obj.setCidinternaluser((String)value);
            }
        }
        ,
        Cidapname {
            public Object get(PheCidListrequestEOImpl obj) {
                return obj.getCidapname();
            }

            public void put(PheCidListrequestEOImpl obj, Object value) {
                obj.setCidapname((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(PheCidListrequestEOImpl object);

        public abstract void put(PheCidListrequestEOImpl object, Object value);

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static final int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }
    public static final int IDREQUEST = AttributesEnum.Idrequest.index();
    public static final int CIDREQUESTOR = AttributesEnum.Cidrequestor.index();
    public static final int CIDREQUESTORPOSITION = AttributesEnum.Cidrequestorposition.index();
    public static final int CIDPURPOSE = AttributesEnum.Cidpurpose.index();
    public static final int CIDREQUESTORSUPERVISOR = AttributesEnum.Cidrequestorsupervisor.index();
    public static final int CIDREQUESTORSUPERVISORPOSITION = AttributesEnum.Cidrequestorsupervisorposition.index();
    public static final int CIDDATEREQUEST = AttributesEnum.Ciddaterequest.index();
    public static final int CIDTDCLEAD = AttributesEnum.Cidtdclead.index();
    public static final int CIDSTATUSREQUEST = AttributesEnum.Cidstatusrequest.index();
    public static final int CIDDATEAPPROVESV = AttributesEnum.Ciddateapprovesv.index();
    public static final int CIDAPPROVETDC = AttributesEnum.Cidapprovetdc.index();
    public static final int CIDDATEAPPROVETDCLEAD = AttributesEnum.Ciddateapprovetdclead.index();
    public static final int CIDATTACH = AttributesEnum.Cidattach.index();
    public static final int CIDINTERNALUSER = AttributesEnum.Cidinternaluser.index();
    public static final int CIDAPNAME = AttributesEnum.Cidapname.index();

    /**
     * This is the default constructor (do not remove).
     */
    public PheCidListrequestEOImpl() {
    }

    /**
     * Gets the attribute value for Idrequest, using the alias name Idrequest.
     * @return the Idrequest
     */
    public Number getIdrequest() {
        return (Number)getAttributeInternal(IDREQUEST);
    }

    /**
     * Sets <code>value</code> as the attribute value for Idrequest.
     * @param value value to set the Idrequest
     */
    public void setIdrequest(Number value) {
        setAttributeInternal(IDREQUEST, value);
    }

    /**
     * Gets the attribute value for Cidrequestor, using the alias name Cidrequestor.
     * @return the Cidrequestor
     */
    public String getCidrequestor() {
        return (String)getAttributeInternal(CIDREQUESTOR);
    }

    /**
     * Sets <code>value</code> as the attribute value for Cidrequestor.
     * @param value value to set the Cidrequestor
     */
    public void setCidrequestor(String value) {
        setAttributeInternal(CIDREQUESTOR, value);
    }

    /**
     * Gets the attribute value for Cidrequestorposition, using the alias name Cidrequestorposition.
     * @return the Cidrequestorposition
     */
    public String getCidrequestorposition() {
        return (String)getAttributeInternal(CIDREQUESTORPOSITION);
    }

    /**
     * Sets <code>value</code> as the attribute value for Cidrequestorposition.
     * @param value value to set the Cidrequestorposition
     */
    public void setCidrequestorposition(String value) {
        setAttributeInternal(CIDREQUESTORPOSITION, value);
    }

    /**
     * Gets the attribute value for Cidpurpose, using the alias name Cidpurpose.
     * @return the Cidpurpose
     */
    public String getCidpurpose() {
        return (String)getAttributeInternal(CIDPURPOSE);
    }

    /**
     * Sets <code>value</code> as the attribute value for Cidpurpose.
     * @param value value to set the Cidpurpose
     */
    public void setCidpurpose(String value) {
        setAttributeInternal(CIDPURPOSE, value);
    }

    /**
     * Gets the attribute value for Cidrequestorsupervisor, using the alias name Cidrequestorsupervisor.
     * @return the Cidrequestorsupervisor
     */
    public String getCidrequestorsupervisor() {
        return (String)getAttributeInternal(CIDREQUESTORSUPERVISOR);
    }

    /**
     * Sets <code>value</code> as the attribute value for Cidrequestorsupervisor.
     * @param value value to set the Cidrequestorsupervisor
     */
    public void setCidrequestorsupervisor(String value) {
        setAttributeInternal(CIDREQUESTORSUPERVISOR, value);
    }

    /**
     * Gets the attribute value for Cidrequestorsupervisorposition, using the alias name Cidrequestorsupervisorposition.
     * @return the Cidrequestorsupervisorposition
     */
    public String getCidrequestorsupervisorposition() {
        return (String)getAttributeInternal(CIDREQUESTORSUPERVISORPOSITION);
    }

    /**
     * Sets <code>value</code> as the attribute value for Cidrequestorsupervisorposition.
     * @param value value to set the Cidrequestorsupervisorposition
     */
    public void setCidrequestorsupervisorposition(String value) {
        setAttributeInternal(CIDREQUESTORSUPERVISORPOSITION, value);
    }

    /**
     * Gets the attribute value for Ciddaterequest, using the alias name Ciddaterequest.
     * @return the Ciddaterequest
     */
    public Timestamp getCiddaterequest() {
        return (Timestamp)getAttributeInternal(CIDDATEREQUEST);
    }

    /**
     * Sets <code>value</code> as the attribute value for Ciddaterequest.
     * @param value value to set the Ciddaterequest
     */
    public void setCiddaterequest(Timestamp value) {
        setAttributeInternal(CIDDATEREQUEST, value);
    }

    /**
     * Gets the attribute value for Cidtdclead, using the alias name Cidtdclead.
     * @return the Cidtdclead
     */
    public String getCidtdclead() {
        return (String)getAttributeInternal(CIDTDCLEAD);
    }

    /**
     * Sets <code>value</code> as the attribute value for Cidtdclead.
     * @param value value to set the Cidtdclead
     */
    public void setCidtdclead(String value) {
        setAttributeInternal(CIDTDCLEAD, value);
    }

    /**
     * Gets the attribute value for Cidstatusrequest, using the alias name Cidstatusrequest.
     * @return the Cidstatusrequest
     */
    public String getCidstatusrequest() {
        return (String)getAttributeInternal(CIDSTATUSREQUEST);
    }

    /**
     * Sets <code>value</code> as the attribute value for Cidstatusrequest.
     * @param value value to set the Cidstatusrequest
     */
    public void setCidstatusrequest(String value) {
        setAttributeInternal(CIDSTATUSREQUEST, value);
    }

    /**
     * Gets the attribute value for Ciddateapprovesv, using the alias name Ciddateapprovesv.
     * @return the Ciddateapprovesv
     */
    public Timestamp getCiddateapprovesv() {
        return (Timestamp)getAttributeInternal(CIDDATEAPPROVESV);
    }

    /**
     * Sets <code>value</code> as the attribute value for Ciddateapprovesv.
     * @param value value to set the Ciddateapprovesv
     */
    public void setCiddateapprovesv(Timestamp value) {
        setAttributeInternal(CIDDATEAPPROVESV, value);
    }

    /**
     * Gets the attribute value for Cidapprovetdc, using the alias name Cidapprovetdc.
     * @return the Cidapprovetdc
     */
    public Timestamp getCidapprovetdc() {
        return (Timestamp)getAttributeInternal(CIDAPPROVETDC);
    }

    /**
     * Sets <code>value</code> as the attribute value for Cidapprovetdc.
     * @param value value to set the Cidapprovetdc
     */
    public void setCidapprovetdc(Timestamp value) {
        setAttributeInternal(CIDAPPROVETDC, value);
    }

    /**
     * Gets the attribute value for Ciddateapprovetdclead, using the alias name Ciddateapprovetdclead.
     * @return the Ciddateapprovetdclead
     */
    public Timestamp getCiddateapprovetdclead() {
        return (Timestamp)getAttributeInternal(CIDDATEAPPROVETDCLEAD);
    }

    /**
     * Sets <code>value</code> as the attribute value for Ciddateapprovetdclead.
     * @param value value to set the Ciddateapprovetdclead
     */
    public void setCiddateapprovetdclead(Timestamp value) {
        setAttributeInternal(CIDDATEAPPROVETDCLEAD, value);
    }

    /**
     * Gets the attribute value for Cidattach, using the alias name Cidattach.
     * @return the Cidattach
     */
    public String getCidattach() {
        return (String)getAttributeInternal(CIDATTACH);
    }

    /**
     * Sets <code>value</code> as the attribute value for Cidattach.
     * @param value value to set the Cidattach
     */
    public void setCidattach(String value) {
        setAttributeInternal(CIDATTACH, value);
    }

    /**
     * Gets the attribute value for Cidinternaluser, using the alias name Cidinternaluser.
     * @return the Cidinternaluser
     */
    public String getCidinternaluser() {
        return (String)getAttributeInternal(CIDINTERNALUSER);
    }

    /**
     * Sets <code>value</code> as the attribute value for Cidinternaluser.
     * @param value value to set the Cidinternaluser
     */
    public void setCidinternaluser(String value) {
        setAttributeInternal(CIDINTERNALUSER, value);
    }

    /**
     * Gets the attribute value for Cidapname, using the alias name Cidapname.
     * @return the Cidapname
     */
    public String getCidapname() {
        return (String)getAttributeInternal(CIDAPNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for Cidapname.
     * @param value value to set the Cidapname
     */
    public void setCidapname(String value) {
        setAttributeInternal(CIDAPNAME, value);
    }

    /**
     * getAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param attrDef the attribute

     * @return the attribute value
     * @throws Exception
     */
    protected Object getAttrInvokeAccessor(int index,
                                           AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            return AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].get(this);
        }
        return super.getAttrInvokeAccessor(index, attrDef);
    }

    /**
     * setAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param value the value to assign to the attribute
     * @param attrDef the attribute

     * @throws Exception
     */
    protected void setAttrInvokeAccessor(int index, Object value,
                                         AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].put(this, value);
            return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
    }

    /**
     * @param idrequest key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number idrequest) {
        return new Key(new Object[]{idrequest});
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("CID.EO.PheCidListrequestEO");
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
    }

    /**
     * Add locking logic here.
     */
    public void lock() {
        super.lock();
    }

    /**
     * Custom DML update/insert/delete logic here.
     * @param operation the operation type
     * @param e the transaction event
     */
    protected void doDML(int operation, TransactionEvent e) {
        
     /*     if (operation == DML_INSERT) {
            Number runNumber;
            String userInitial = null;
            String apNama = getCidapname();
            ViewObjectImpl runNumVo =
                (ViewObjectImpl)this.getDBTransaction().getRootApplicationModule().findViewObject("CIDRunNumberVO1");
            runNumVo.setNamedWhereClauseParam("apNama", apNama);
            runNumVo.executeQuery();
            if(runNumVo.getAllRowsInRange().length > 0){
                runNumCid=runNumVo.first();
                runNumber=(Number)runNumCid.getAttribute("Value");
                runNumCid.setAttribute("Value", runNumber.add(1));
            }else{
                Row newRow = runNumVo.createRow();
                newRow.setAttribute("RunNumType", "AP");
                newRow.setAttribute("ApName", apNama);
                newRow.setAttribute("Value", 2);
                runNumVo.insertRow(newRow);
                runNumber = new Number(1);
            }
        }  */
        super.doDML(operation, e);
    }
}