package transmittalExternal.vo;

import java.math.BigDecimal;

import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Jan 27 13:28:53 ICT 2020
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ValidateDetailSupportingDocVoRowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Action {
            public Object get(ValidateDetailSupportingDocVoRowImpl obj) {
                return obj.getAction();
            }

            public void put(ValidateDetailSupportingDocVoRowImpl obj,
                            Object value) {
                obj.setAction((BigDecimal)value);
            }
        }
        ,
        TransmittalId {
            public Object get(ValidateDetailSupportingDocVoRowImpl obj) {
                return obj.getTransmittalId();
            }

            public void put(ValidateDetailSupportingDocVoRowImpl obj,
                            Object value) {
                obj.setTransmittalId((String)value);
            }
        }
        ,
        Recipient {
            public Object get(ValidateDetailSupportingDocVoRowImpl obj) {
                return obj.getRecipient();
            }

            public void put(ValidateDetailSupportingDocVoRowImpl obj,
                            Object value) {
                obj.setRecipient((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(ValidateDetailSupportingDocVoRowImpl object);

        public abstract void put(ValidateDetailSupportingDocVoRowImpl object,
                                 Object value);

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
    public static final int ACTION = AttributesEnum.Action.index();
    public static final int TRANSMITTALID = AttributesEnum.TransmittalId.index();
    public static final int RECIPIENT = AttributesEnum.Recipient.index();

    /**
     * This is the default constructor (do not remove).
     */
    public ValidateDetailSupportingDocVoRowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute Action.
     * @return the Action
     */
    public BigDecimal getAction() {
        return (BigDecimal) getAttributeInternal(ACTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Action.
     * @param value value to set the  Action
     */
    public void setAction(BigDecimal value) {
        setAttributeInternal(ACTION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TransmittalId.
     * @return the TransmittalId
     */
    public String getTransmittalId() {
        return (String) getAttributeInternal(TRANSMITTALID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TransmittalId.
     * @param value value to set the  TransmittalId
     */
    public void setTransmittalId(String value) {
        setAttributeInternal(TRANSMITTALID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Recipient.
     * @return the Recipient
     */
    public String getRecipient() {
        return (String) getAttributeInternal(RECIPIENT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Recipient.
     * @param value value to set the  Recipient
     */
    public void setRecipient(String value) {
        setAttributeInternal(RECIPIENT, value);
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
}