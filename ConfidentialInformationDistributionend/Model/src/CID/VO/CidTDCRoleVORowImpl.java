package CID.VO;

import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jun 26 10:39:32 ICT 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CidTDCRoleVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Dusername {
            public Object get(CidTDCRoleVORowImpl obj) {
                return obj.getDusername();
            }

            public void put(CidTDCRoleVORowImpl obj, Object value) {
                obj.setDusername((String)value);
            }
        }
        ,
        Dattributename {
            public Object get(CidTDCRoleVORowImpl obj) {
                return obj.getDattributename();
            }

            public void put(CidTDCRoleVORowImpl obj, Object value) {
                obj.setDattributename((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(CidTDCRoleVORowImpl object);

        public abstract void put(CidTDCRoleVORowImpl object, Object value);

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
    public static final int DUSERNAME = AttributesEnum.Dusername.index();
    public static final int DATTRIBUTENAME = AttributesEnum.Dattributename.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CidTDCRoleVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute Dusername.
     * @return the Dusername
     */
    public String getDusername() {
        return (String) getAttributeInternal(DUSERNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Dusername.
     * @param value value to set the  Dusername
     */
    public void setDusername(String value) {
        setAttributeInternal(DUSERNAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Dattributename.
     * @return the Dattributename
     */
    public String getDattributename() {
        return (String) getAttributeInternal(DATTRIBUTENAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Dattributename.
     * @param value value to set the  Dattributename
     */
    public void setDattributename(String value) {
        setAttributeInternal(DATTRIBUTENAME, value);
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