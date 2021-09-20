package transmittalExternal.model;

import java.math.BigDecimal;

import java.sql.Date;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.SequenceImpl;
import oracle.jbo.server.TransactionEvent;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Apr 15 12:00:24 ICT 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PheDocProcessImpl extends EntityImpl {
    private static EntityDefImpl mDefinitionObject;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        Id {
            public Object get(PheDocProcessImpl obj) {
                return obj.getId();
            }

            public void put(PheDocProcessImpl obj, Object value) {
                obj.setId((Number)value);
            }
        }
        ,
        TransmittalId {
            public Object get(PheDocProcessImpl obj) {
                return obj.getTransmittalId();
            }

            public void put(PheDocProcessImpl obj, Object value) {
                obj.setTransmittalId((String)value);
            }
        }
        ,
        Did {
            public Object get(PheDocProcessImpl obj) {
                return obj.getDid();
            }

            public void put(PheDocProcessImpl obj, Object value) {
                obj.setDid((String)value);
            }
        }
        ,
        Sender {
            public Object get(PheDocProcessImpl obj) {
                return obj.getSender();
            }

            public void put(PheDocProcessImpl obj, Object value) {
                obj.setSender((String)value);
            }
        }
        ,
        Recipient {
            public Object get(PheDocProcessImpl obj) {
                return obj.getRecipient();
            }

            public void put(PheDocProcessImpl obj, Object value) {
                obj.setRecipient((String)value);
            }
        }
        ,
        DocType {
            public Object get(PheDocProcessImpl obj) {
                return obj.getDocType();
            }

            public void put(PheDocProcessImpl obj, Object value) {
                obj.setDocType((String)value);
            }
        }
        ,
        DocTitle {
            public Object get(PheDocProcessImpl obj) {
                return obj.getDocTitle();
            }

            public void put(PheDocProcessImpl obj, Object value) {
                obj.setDocTitle((String)value);
            }
        }
        ,
        Action {
            public Object get(PheDocProcessImpl obj) {
                return obj.getAction();
            }

            public void put(PheDocProcessImpl obj, Object value) {
                obj.setAction((BigDecimal)value);
            }
        }
        ,
        DocStatus {
            public Object get(PheDocProcessImpl obj) {
                return obj.getDocStatus();
            }

            public void put(PheDocProcessImpl obj, Object value) {
                obj.setDocStatus((String)value);
            }
        }
        ,
        Notes {
            public Object get(PheDocProcessImpl obj) {
                return obj.getNotes();
            }

            public void put(PheDocProcessImpl obj, Object value) {
                obj.setNotes((String)value);
            }
        }
        ,
        SubmitDate {
            public Object get(PheDocProcessImpl obj) {
                return obj.getSubmitDate();
            }

            public void put(PheDocProcessImpl obj, Object value) {
                obj.setSubmitDate((Date)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(PheDocProcessImpl object);

        public abstract void put(PheDocProcessImpl object, Object value);

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() +
                AttributesEnum.staticValues().length;
        }

        public static AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int ID = AttributesEnum.Id.index();
    public static final int TRANSMITTALID = AttributesEnum.TransmittalId.index();
    public static final int DID = AttributesEnum.Did.index();
    public static final int SENDER = AttributesEnum.Sender.index();
    public static final int RECIPIENT = AttributesEnum.Recipient.index();
    public static final int DOCTYPE = AttributesEnum.DocType.index();
    public static final int DOCTITLE = AttributesEnum.DocTitle.index();
    public static final int ACTION = AttributesEnum.Action.index();
    public static final int DOCSTATUS = AttributesEnum.DocStatus.index();
    public static final int NOTES = AttributesEnum.Notes.index();
    public static final int SUBMITDATE = AttributesEnum.SubmitDate.index();

    /**
     * This is the default constructor (do not remove).
     */
    public PheDocProcessImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = EntityDefImpl.findDefObject("transmittalExternal.model.PheDocProcess");
        }
        return mDefinitionObject;
    }

    /**
     * Gets the attribute value for Id, using the alias name Id.
     * @return the Id
     */
    public Number getId() {
        return (Number)getAttributeInternal(ID);
    }

    /**
     * Sets <code>value</code> as the attribute value for Id.
     * @param value value to set the Id
     */
    public void setId(Number value) {
        setAttributeInternal(ID, value);
    }

    /**
     * Gets the attribute value for TransmittalId, using the alias name TransmittalId.
     * @return the TransmittalId
     */
    public String getTransmittalId() {
        return (String)getAttributeInternal(TRANSMITTALID);
    }

    /**
     * Sets <code>value</code> as the attribute value for TransmittalId.
     * @param value value to set the TransmittalId
     */
    public void setTransmittalId(String value) {
        setAttributeInternal(TRANSMITTALID, value);
    }

    /**
     * Gets the attribute value for Did, using the alias name Did.
     * @return the Did
     */
    public String getDid() {
        return (String)getAttributeInternal(DID);
    }

    /**
     * Sets <code>value</code> as the attribute value for Did.
     * @param value value to set the Did
     */
    public void setDid(String value) {
        setAttributeInternal(DID, value);
    }

    /**
     * Gets the attribute value for Sender, using the alias name Sender.
     * @return the Sender
     */
    public String getSender() {
        return (String)getAttributeInternal(SENDER);
    }

    /**
     * Sets <code>value</code> as the attribute value for Sender.
     * @param value value to set the Sender
     */
    public void setSender(String value) {
        setAttributeInternal(SENDER, value);
    }

    /**
     * Gets the attribute value for Recipient, using the alias name Recipient.
     * @return the Recipient
     */
    public String getRecipient() {
        return (String)getAttributeInternal(RECIPIENT);
    }

    /**
     * Sets <code>value</code> as the attribute value for Recipient.
     * @param value value to set the Recipient
     */
    public void setRecipient(String value) {
        setAttributeInternal(RECIPIENT, value);
    }

    /**
     * Gets the attribute value for DocType, using the alias name DocType.
     * @return the DocType
     */
    public String getDocType() {
        return (String)getAttributeInternal(DOCTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for DocType.
     * @param value value to set the DocType
     */
    public void setDocType(String value) {
        setAttributeInternal(DOCTYPE, value);
    }

    /**
     * Gets the attribute value for DocTitle, using the alias name DocTitle.
     * @return the DocTitle
     */
    public String getDocTitle() {
        return (String)getAttributeInternal(DOCTITLE);
    }

    /**
     * Sets <code>value</code> as the attribute value for DocTitle.
     * @param value value to set the DocTitle
     */
    public void setDocTitle(String value) {
        setAttributeInternal(DOCTITLE, value);
    }

    /**
     * Gets the attribute value for Action, using the alias name Action.
     * @return the Action
     */
    public BigDecimal getAction() {
        return (BigDecimal)getAttributeInternal(ACTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for Action.
     * @param value value to set the Action
     */
    public void setAction(BigDecimal value) {
        setAttributeInternal(ACTION, value);
    }

    /**
     * Gets the attribute value for DocStatus, using the alias name DocStatus.
     * @return the DocStatus
     */
    public String getDocStatus() {
        return (String)getAttributeInternal(DOCSTATUS);
    }

    /**
     * Sets <code>value</code> as the attribute value for DocStatus.
     * @param value value to set the DocStatus
     */
    public void setDocStatus(String value) {
        setAttributeInternal(DOCSTATUS, value);
    }

    /**
     * Gets the attribute value for Notes, using the alias name Notes.
     * @return the Notes
     */
    public String getNotes() {
        return (String)getAttributeInternal(NOTES);
    }

    /**
     * Sets <code>value</code> as the attribute value for Notes.
     * @param value value to set the Notes
     */
    public void setNotes(String value) {
        setAttributeInternal(NOTES, value);
    }

    /**
     * Gets the attribute value for SubmitDate, using the alias name SubmitDate.
     * @return the SubmitDate
     */
    public Date getSubmitDate() {
        return (Date)getAttributeInternal(SUBMITDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for SubmitDate.
     * @param value value to set the SubmitDate
     */
    public void setSubmitDate(Date value) {
        setAttributeInternal(SUBMITDATE, value);
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
     * @param id key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number id) {
        return new Key(new Object[]{id});
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
        super.doDML(operation, e);
    }

    public Number getSeqValue(String sequenceName) {
        Number seqNumber = new Number(0);
        if (sequenceName != null && !sequenceName.equals("")) {
            SequenceImpl seqImpl =
                new SequenceImpl(sequenceName, getDBTransaction());
            seqNumber = seqImpl.getSequenceNumber();
        }
        return seqNumber;
    }

    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        setId(getSeqValue("PHE_DOC_PROCESS_SEQ"));
    }
}
