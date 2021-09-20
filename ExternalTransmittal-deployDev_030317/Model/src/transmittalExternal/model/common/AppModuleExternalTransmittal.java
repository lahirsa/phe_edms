package transmittalExternal.model.common;

import oracle.jbo.ApplicationModule;
import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Apr 04 09:19:53 ICT 2014
// ---------------------------------------------------------------------
public interface AppModuleExternalTransmittal extends ApplicationModule {
    Number getSeqValue(String sequenceName);

    String getTransmittalType();

    void SetLoadTransmittalId();

    void ExecDetailSubmittalByUploader();

    void ExecDetailSupportingInternalByUploader();
}