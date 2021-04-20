package model.Cutting_ManPower;

import java.sql.SQLException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.dss.crosstab.Total;

import oracle.jbo.domain.Number;

public class Main_Bean {
    private RichInputDate documentDate_S;
    private RichInputText production_Qty;
    private RichInputText produced_Min;
    private RichTable productionTable;
    private RichTable codeTable;

    public Main_Bean() {
    }

    public OperationBinding executeOperation(String operation) {
        OperationBinding createParam =
            getBindingsCont().getOperationBinding(operation);
        return createParam;

    }

    public void Populate(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("Going In method");
        OperationBinding operationBinding = executeOperation("Populate");

        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            System.out.println("if errors -->");
        }
    }

    public BindingContainer getBindingsCont() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void setReceiveDate_S(RichInputDate id) {

        FacesContext fctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = fctx.getExternalContext();
        HttpSession userSession = (HttpSession)ectx.getSession(false);
        userSession.setAttribute("DocDate", id.getValue());
        System.out.println("Document Date is..." + id.getValue());
    }

    public void setDocumentDate_S(RichInputDate documentDate_S) {
        this.documentDate_S = documentDate_S;
        setReceiveDate_S(documentDate_S);
    }

    public RichInputDate getDocumentDate_S() {
        return documentDate_S;
    }

    public void SAM_VC(ValueChangeEvent valueChangeEvent) {
        // Add event code here...

        Double SAM = 0.0;
        Double Production_Qty = 0.0;
        Double ProducedMin = 0.0;

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());

        try {
            SAM =
Double.parseDouble(valueChangeEvent.getNewValue().toString());
        } catch (Exception e) {
            // TODO: Add catch code
            SAM = 0.0;
            e.printStackTrace();
        }

        try {
            Production_Qty =
                    Double.parseDouble(getProduction_Qty().getValue().toString());
        } catch (Exception e) {
            // TODO: Add catch code
            Production_Qty = 0.0;
            e.printStackTrace();
        }

        ProducedMin = Production_Qty * SAM;

        try {
            produced_Min.setValue(new oracle.jbo.domain.Number(ProducedMin));
        } catch (Exception e) {
            ;
        }

        AdfFacesContext.getCurrentInstance().addPartialTarget(productionTable);

        //        System.out.println("Going In method");
        //        OperationBinding operationBinding = executeOperation("TotalProducedMins_Calc");
        //
        //        operationBinding.execute();
        //        if (!operationBinding.getErrors().isEmpty()) {
        //            System.out.println("if errors -->");
        //        }
    }

    public void setProduction_Qty(RichInputText production_Qty) {
        this.production_Qty = production_Qty;
    }

    public RichInputText getProduction_Qty() {
        return production_Qty;
    }

    public void setProduced_Min(RichInputText produced_Min) {
        this.produced_Min = produced_Min;
    }

    public RichInputText getProduced_Min() {
        return produced_Min;
    }

    public void setProductionTable(RichTable productionTable) {
        this.productionTable = productionTable;
    }

    public RichTable getProductionTable() {
        return productionTable;
    }

    public void save(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("Going In method");
        OperationBinding operationBinding = executeOperation("save");

        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            System.out.println("if errors -->");
        }

    }

    public void FetchListener(PopupFetchEvent popupFetchEvent) {
        // Add event code here...
    }

    public void CancelListener(PopupCanceledEvent popupCanceledEvent) {
        // Add event code here...
    }

    public void DialogListener(DialogEvent dialogEvent) {

        if (dialogEvent.getOutcome().name().equals("ok")) {
            callPopulate();
            AdfFacesContext.getCurrentInstance().addPartialTarget(codeTable);

        } else if (dialogEvent.getOutcome().name().equals("cancel")) {
            ;
        }
    }

    public void callPopulate() {

        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding operationBinding =
            bindings.getOperationBinding("CodeDescPopulate");
        operationBinding.execute();

        if (!operationBinding.getErrors().isEmpty()) {
            System.out.println("call populate code/description-->");
            //List errors = operationBinding.getErrors();

        }

    }

    public void setCodeTable(RichTable codeTable) {
        this.codeTable = codeTable;
    }

    public RichTable getCodeTable() {
        return codeTable;
    }
}
