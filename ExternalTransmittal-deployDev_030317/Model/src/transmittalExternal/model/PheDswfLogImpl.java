package transmittalExternal.model;

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
// ---    Mon Apr 07 11:17:14 ICT 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PheDswfLogImpl extends EntityImpl {
    private static EntityDefImpl mDefinitionObject;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        Id {
            public Object get(PheDswfLogImpl obj) {
                return obj.getId();
            }

            public void put(PheDswfLogImpl obj, Object value) {
                obj.setId((Number)value);
            }
        },
        TransmittalId {
            public Object get(PheDswfLogImpl obj) {
                return obj.getTransmittalId();
            }

            public void put(PheDswfLogImpl obj, Object value) {
                obj.setTransmittalId((String)value);
            }
        },
        Username {
            public Object get(PheDswfLogImpl obj) {
                return obj.getUsername();
            }

            public void put(PheDswfLogImpl obj, Object value) {
                obj.setUsername((String)value);
            }
        },
        Action {
            public Object get(PheDswfLogImpl obj) {
                return obj.getAction();
            }

            public void put(PheDswfLogImpl obj, Object value) {
                obj.setAction((String)value);
            }
        },
        ActionDate {
            public Object get(PheDswfLogImpl obj) {
                return obj.getActionDate();
            }

            public void put(PheDswfLogImpl obj, Object value) {
                obj.setActionDate((Date)value);
            }
        },
        Description {
            public Object get(PheDswfLogImpl obj) {
                return obj.getDescription();
            }

            public void put(PheDswfLogImpl obj, Object value) {
                obj.setDescription((String)value);
            }
        };
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(PheDswfLogImpl object);

        public abstract void put(PheDswfLogImpl object, Object value);

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
    public static final int TRANSMITTALID =
        AttributesEnum.TransmittalId.index();
    public static final int USERNAME = AttributesEnum.Username.index();
    public static final int ACTION = AttributesEnum.Action.index();
    public static final int ACTIONDATE = AttributesEnum.ActionDate.index();
    public static final int DESCRIPTION = AttributesEnum.Description.index();

    /**
     * This is the default constructor (do not remove).
     */
    public PheDswfLogImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject =
                    EntityDefImpl.findDefObject("transmittalExternal.model.PheDswfLog");
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
     * Gets the attribute value for Username, using the alias name Username.
     * @return the Username
     */
    public String getUsername() {
        return (String)getAttributeInternal(USERNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for Username.
     * @param value value to set the Username
     */
    public void setUsername(String value) {
        setAttributeInternal(USERNAME, value);
    }

    /**
     * Gets the attribute value for Action, using the alias name Action.
     * @return the Action
     */
    public String getAction() {
        return (String)getAttributeInternal(ACTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for Action.
     * @param value value to set the Action
     */
    public void setAction(String value) {
        setAttributeInternal(ACTION, value);
    }

    /**
     * Gets the attribute value for ActionDate, using the alias name ActionDate.
     * @return the ActionDate
     */
    public Date getActionDate() {
        return (Date)getAttributeInternal(ACTIONDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for ActionDate.
     * @param value value to set the ActionDate
     */
    public void setActionDate(Date value) {
        setAttributeInternal(ACTIONDATE, value);
    }

    /**
     * Gets the attribute value for Description, using the alias name Description.
     * @return the Description
     */
    public String getDescription() {
        return (String)getAttributeInternal(DESCRIPTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for Description.
     * @param value value to set the Description
     */
    public void setDescription(String value) {
        setAttributeInternal(DESCRIPTION, value);
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
        if ((index >= AttributesEnum.firstIndex()) &&
            (index < AttributesEnum.count())) {
            return AttributesEnum.staticValues()[index -
                AttributesEnum.firstIndex()].get(this);
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
        if ((index >= AttributesEnum.firstIndex()) &&
            (index < AttributesEnum.count())) {
            AttributesEnum.staticValues()[index -
                AttributesEnum.firstIndex()].put(this, value);
            return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
    }


    /**
     * @param id key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number id) {
        return new Key(new Object[] { id });
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
        setId(getSeqValue("PHE_DSWF_LOG_SEQ"));
    }
}
