package model.Cutting_ManPower.AM;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import model.Cutting_ManPower.AM.common.Cutting_ManPower_AM;
import model.Cutting_ManPower.VO.MnjCuttingOffStandardD_VOImpl;
import model.Cutting_ManPower.VO.MnjCuttingOffStandardL_VOImpl;
import model.Cutting_ManPower.VO.MnjCuttingWorkingHoursH_VOImpl;
import model.Cutting_ManPower.VO.MnjCuttingWorkingHoursL_VOImpl;
import model.Cutting_ManPower.VO.MnjCuttingWorkingHoursProd_VOImpl;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewLinkImpl;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Jan 12 14:49:23 PKT 2018
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class Cutting_ManPower_AMImpl extends ApplicationModuleImpl implements Cutting_ManPower_AM {
    /**
     * This is the default constructor (do not remove).
     */
    public Cutting_ManPower_AMImpl() {
    }

    /**
     * Container's getter for MnjCuttingWorkingHoursH_VO1.
     * @return MnjCuttingWorkingHoursH_VO1
     */
    public MnjCuttingWorkingHoursH_VOImpl getMnjCuttingWorkingHoursH_VO1() {
        return (MnjCuttingWorkingHoursH_VOImpl)findViewObject("MnjCuttingWorkingHoursH_VO1");
    }

    /**
     * Container's getter for MnjCuttingWorkingHoursProd_VO1.
     * @return MnjCuttingWorkingHoursProd_VO1
     */
    public MnjCuttingWorkingHoursProd_VOImpl getMnjCuttingWorkingHoursProd_VO1() {
        return (MnjCuttingWorkingHoursProd_VOImpl)findViewObject("MnjCuttingWorkingHoursProd_VO1");
    }

    /**
     * Container's getter for MnjCuttingWorkingHoursL_VO1.
     * @return MnjCuttingWorkingHoursL_VO1
     */
    public MnjCuttingWorkingHoursL_VOImpl getMnjCuttingWorkingHoursL_VO1() {
        return (MnjCuttingWorkingHoursL_VOImpl)findViewObject("MnjCuttingWorkingHoursL_VO1");
    }

    /**
     * Container's getter for MnjCuttingOffStandardL_VO1.
     * @return MnjCuttingOffStandardL_VO1
     */
    public MnjCuttingOffStandardL_VOImpl getMnjCuttingOffStandardL_VO1() {
        return (MnjCuttingOffStandardL_VOImpl)findViewObject("MnjCuttingOffStandardL_VO1");
    }

    /**
     * Container's getter for MnjCuttingOffStandardD_VO1.
     * @return MnjCuttingOffStandardD_VO1
     */
    public MnjCuttingOffStandardD_VOImpl getMnjCuttingOffStandardD_VO1() {
        return (MnjCuttingOffStandardD_VOImpl)findViewObject("MnjCuttingOffStandardD_VO1");
    }

    /**
     * Container's getter for MnjCuttingWorkinghrsProdFkLink1.
     * @return MnjCuttingWorkinghrsProdFkLink1
     */
    public ViewLinkImpl getMnjCuttingWorkinghrsProdFkLink1() {
        return (ViewLinkImpl)findViewLink("MnjCuttingWorkinghrsProdFkLink1");
    }

    /**
     * Container's getter for MnjCuttingWorkingHoursLFkLink1.
     * @return MnjCuttingWorkingHoursLFkLink1
     */
    public ViewLinkImpl getMnjCuttingWorkingHoursLFkLink1() {
        return (ViewLinkImpl)findViewLink("MnjCuttingWorkingHoursLFkLink1");
    }

    /**
     * Container's getter for MnjCuttingOffStandardLFkLink1.
     * @return MnjCuttingOffStandardLFkLink1
     */
    public ViewLinkImpl getMnjCuttingOffStandardLFkLink1() {
        return (ViewLinkImpl)findViewLink("MnjCuttingOffStandardLFkLink1");
    }

    /**
     * Container's getter for MnjCuttingOffStandardDFkLink1.
     * @return MnjCuttingOffStandardDFkLink1
     */
    public ViewLinkImpl getMnjCuttingOffStandardDFkLink1() {
        return (ViewLinkImpl)findViewLink("MnjCuttingOffStandardDFkLink1");
    }

    /**
     * Container's getter for OperatingUnits_VO1.
     * @return OperatingUnits_VO1
     */
    public ViewObjectImpl getOperatingUnits_VO1() {
        return (ViewObjectImpl)findViewObject("OperatingUnits_VO1");
    }

    public void setSessionValues(String orgId, String userId, String respId,
                                 String respAppl) {


        System.out.println("User Id -------->" + userId);
        if (userId != null) {

            FacesContext fctx = FacesContext.getCurrentInstance();
            ExternalContext ectx = fctx.getExternalContext();
            HttpSession userSession = (HttpSession)ectx.getSession(false);
            userSession.setAttribute("userId", userId);
            userSession.setAttribute("orgId", orgId);
            userSession.setAttribute("respId", respId);
            userSession.setAttribute("respAppl", respAppl);
            userSession.setAttribute("unitName", getUnitName(orgId));
        }
    }

    public String getUnitName(String orgId) {

        ViewObject vo = getOperatingUnits_VO1();
        vo.setWhereClause("ORGANIZATION_ID = " + orgId);
        vo.executeQuery();
        String name = null;
        vo.first();

        try {
            name = vo.getCurrentRow().getAttribute("Name").toString();
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        return name;
    }

    public void Populate() {
        int b;

        ViewObject populatevo = getPop_VO1(); // Populate VO
        populatevo.executeQuery();
        populatevo.first();
        System.out.println("Query @ populate..............." +
                           populatevo.getQuery());

        int cntRec = populatevo.getRowCount();
        populatevo.first();
        if (cntRec != 0) {
            for (b = 1; b <= cntRec; b++) {

                ViewObject vo_Line4 =
                    getMnjCuttingWorkingHoursProd_VO1(); // Lines VO

                Row row = vo_Line4.createRow();
                vo_Line4.last();
                vo_Line4.next();
                vo_Line4.insertRow(row);
                row.setNewRowState(Row.STATUS_INITIALIZED);

                row.setAttribute("BuyerId",
                                 populatevo.getCurrentRow().getAttribute("BuyerId"));
                row.setAttribute("BuyerName",
                                 populatevo.getCurrentRow().getAttribute("BuyerName"));
                row.setAttribute("Style",
                                 populatevo.getCurrentRow().getAttribute("Style"));
                row.setAttribute("CurrentStyle",
                                 populatevo.getCurrentRow().getAttribute("CurrentStyle"));
                row.setAttribute("Season",
                                 populatevo.getCurrentRow().getAttribute("Season"));
                row.setAttribute("CurrentSeason",
                                 populatevo.getCurrentRow().getAttribute("CurrentSeason"));
                row.setAttribute("Color",
                                 populatevo.getCurrentRow().getAttribute("Color"));
                row.setAttribute("ProductionQty",
                                 populatevo.getCurrentRow().getAttribute("ProductionQty"));
                row.setAttribute("Wash",
                                 populatevo.getCurrentRow().getAttribute("Wash"));
                row.setAttribute("Sam",
                                 populatevo.getCurrentRow().getAttribute("Sam"));
                row.setAttribute("ProducedMin",
                                 populatevo.getCurrentRow().getAttribute("ProducedMin"));

                populatevo.next();
            }
        }
        getDBTransaction().commit();
        TotalProduction_Calc();

    } //populate Lines

    public void TotalProduction_Calc() {
        ViewObject hvo = getMnjCuttingWorkingHoursH_VO1();
        Double Qty = 0.0;
        Double TotalQty = 0.0;
        ViewObject lvo = this.getMnjCuttingWorkingHoursProd_VO1();
        RowSetIterator lvoit = lvo.createRowSetIterator("lvoit");

        while (lvoit.hasNext()) {

            Row linesNext = lvoit.next();

            try{
                 Qty =Double.parseDouble(linesNext.getAttribute("ProductionQty").toString());
            }
            catch(Exception e) {
                Qty=0.0;
            }

            TotalQty = Qty + TotalQty;
        }
        lvoit.closeRowSetIterator();

        hvo.getCurrentRow().setAttribute("TotalProduction", TotalQty);
        System.out.println("Total Production " + TotalQty);
        getDBTransaction().commit();

    }

    public void save() {
        getDBTransaction().commit();

        TotalProducedMins_Calc();
    }

    public void TotalProducedMins_Calc() {
        getDBTransaction().commit();
        ViewObject hvo = getMnjCuttingWorkingHoursH_VO1();
        Double ProducedMin = 0.0;
        Double TotalProducedMin = 0.0;
        Double TotalAttendedMins = 0.0;
        Double Efficiency = 0.0;

        ViewObject lvo = this.getMnjCuttingWorkingHoursProd_VO1();
        RowSetIterator lvoit = lvo.createRowSetIterator("lvoit");

        while (lvoit.hasNext()) {

            Row linesNext = lvoit.next();

            try {
                ProducedMin = Double.parseDouble(linesNext.getAttribute("ProducedMin").toString());
            } catch (Exception e) {
                ProducedMin = 0.0;
            }

            TotalProducedMin = ProducedMin + TotalProducedMin;
        }
        lvoit.closeRowSetIterator();

        hvo.getCurrentRow().setAttribute("TotalProducedMinutes",
                                         TotalProducedMin);
        System.out.println("Total Produced Minutes " + TotalProducedMin);
        getDBTransaction().commit();
        try {
            TotalAttendedMins =
                    Double.parseDouble(hvo.getCurrentRow().getAttribute("TotalAttendedMins").toString());
        } catch (Exception e) {
            TotalAttendedMins = 0.0;
        }
        System.out.println(" Total Attended Mins " +
                           hvo.getCurrentRow().getAttribute("TotalAttendedMins".toString()));

        if (TotalAttendedMins == 0.0 || TotalAttendedMins == 0) {
            Efficiency = 0.0;
        } else {
            Efficiency = TotalProducedMin / TotalAttendedMins * 100;
        }

        System.out.println("Efficiency is..... " + Efficiency);

        hvo.getCurrentRow().setAttribute("Efficiency", Efficiency);
        getDBTransaction().commit();
    }

    public void CodeDescPopulate() {
        ViewObject populatevo = getCode_Description_PopVO1();
        Row[] r = populatevo.getAllRowsInRange();
        System.out.println("Drop ------->" + r.length);
        for (Row row : r) {

            if (row.getAttribute("Selection") != null &&
                row.getAttribute("Selection").equals("Y")) {
                System.out.println("Selection --->" +
                                   row.getAttribute("Selection"));
                populateLines(row);
            }
        }
    }

    public void populateLines(Row poprow) {
        System.out.println("populateLines-->" + poprow);
        Row linerow = createdetails();
        linerow.setAttribute("CodeStandard", getPopulateValue(poprow, "Code"));
        linerow.setAttribute("Description",
                             getPopulateValue(poprow, "Description"));

        System.out.println("populateLines2-->" + poprow);

    } //end of populateLines

    public Row createdetails() {
        System.out.println("createLinesSTN");
        ViewObject vo = getMnjCuttingOffStandardD_VO1();
        Row row = vo.createRow();
        vo.last();
        vo.next();
        vo.insertRow(row);
        row.setNewRowState(Row.STATUS_INITIALIZED);
        System.out.println("createdetails");
        return row;
    }

    public String getPopulateValue(Row r, String columnName) {

        String value = null;
        try {
            value = r.getAttribute(columnName).toString();
            System.out.println("Value " + value);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * Container's getter for Pop_VO1.
     * @return Pop_VO1
     */
    public ViewObjectImpl getPop_VO1() {
        return (ViewObjectImpl)findViewObject("Pop_VO1");
    }

    /**
     * Container's getter for Code_Description_PopVO1.
     * @return Code_Description_PopVO1
     */
    public ViewObjectImpl getCode_Description_PopVO1() {
        return (ViewObjectImpl)findViewObject("Code_Description_PopVO1");
    }
} // End of AM