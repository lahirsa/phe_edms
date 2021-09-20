package beans;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;

import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class pocbean {
    public pocbean() {
    }

    public void buttoncustome(ActionEvent actionEvent) {
        BindingContainer bindings =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding =
            bindings.getOperationBinding("CreateInsert");
        operationBinding.execute();
    }

    public void fecth(PopupFetchEvent popupFetchEvent) {
        BindingContainer bindings =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding =
            bindings.getOperationBinding("CreateInsert");
        operationBinding.execute();
    }
}
