package CID.VO;

import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed May 15 16:17:16 GMT+07:00 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TdcLeadValVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        KeyConfig {
            public Object get(TdcLeadValVORowImpl obj) {
                return obj.getKeyConfig();
            }

            public void put(TdcLeadValVORowImpl obj, Object value) {
                obj.setKeyConfig((String)value);
            }
        }
        ,
        KeyValue {
            public Object get(TdcLeadValVORowImpl obj) {
                return obj.getKeyValue();
            }

            public void put(TdcLeadValVORowImpl obj, Object value) {
                obj.setKeyValue((String)value);
            }
        }
        ,
        KeyDisplay {
            public Object get(TdcLeadValVORowImpl obj) {
                return obj.getKeyDisplay();
            }

            public void put(TdcLeadValVORowImpl obj, Object value) {
                obj.setKeyDisplay((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(TdcLeadValVORowImpl object);

        public abstract void put(TdcLeadValVORowImpl object, Object value);

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
    public static final int KEYCONFIG = AttributesEnum.KeyConfig.index();
    public static final int KEYVALUE = AttributesEnum.KeyValue.index();
    public static final int KEYDISPLAY = AttributesEnum.KeyDisplay.index();

    /**
     * This is the default constructor (do not remove).
     */
    public TdcLeadValVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute KeyConfig.
     * @return the KeyConfig
     */
    public String getKeyConfig() {
        return (String) getAttributeInternal(KEYCONFIG);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute KeyConfig.
     * @param value value to set the  KeyConfig
     */
    public void setKeyConfig(String value) {
        setAttributeInternal(KEYCONFIG, value);
    }

    /**
     * Gets the attribute value for the calculated attribute KeyValue.
     * @return the KeyValue
     */
    public String getKeyValue() {
        return (String) getAttributeInternal(KEYVALUE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute KeyValue.
     * @param value value to set the  KeyValue
     */
    public void setKeyValue(String value) {
        setAttributeInternal(KEYVALUE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute KeyDisplay.
     * @return the KeyDisplay
     */
    public String getKeyDisplay() {
        return (String) getAttributeInternal(KEYDISPLAY);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute KeyDisplay.
     * @param value value to set the  KeyDisplay
     */
    public void setKeyDisplay(String value) {
        setAttributeInternal(KEYDISPLAY, value);
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