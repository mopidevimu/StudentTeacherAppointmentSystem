package admin;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 * @author Project_Red
 * This class is used to get/set teachers data's from/to observable list
 *
 */
public class AdminTeacherTable {

    private final SimpleStringProperty AdminTeacherTableDataName;
    private final SimpleStringProperty AdminTeacherTableDataID;
    private final SimpleStringProperty AdminTeacherTableDataEmail;
    private final SimpleStringProperty AdminTeacherTableDataDepartment;
    private final SimpleStringProperty AdminTeacherTableDataSec;

    public AdminTeacherTable( String adminTeacherTableDataName, String adminTeacherTableDataID, String adminTeacherTableDataEmail, String adminTeacherTableDataDepartment, String adminTeacherTableDataSec) {
        AdminTeacherTableDataName = new SimpleStringProperty(adminTeacherTableDataName);
        AdminTeacherTableDataID = new SimpleStringProperty(adminTeacherTableDataID);
        AdminTeacherTableDataEmail = new SimpleStringProperty(adminTeacherTableDataEmail);
        AdminTeacherTableDataDepartment = new SimpleStringProperty(adminTeacherTableDataDepartment);
        AdminTeacherTableDataSec = new SimpleStringProperty(adminTeacherTableDataSec);
    }

    
    public String getAdminTeacherTableDataName() {
        return AdminTeacherTableDataName.get();
    }

    public SimpleStringProperty adminTeacherTableDataNameProperty() {
        return AdminTeacherTableDataName;
    }

    public void setAdminTeacherTableDataName(String adminTeacherTableDataName) {
        this.AdminTeacherTableDataName.set(adminTeacherTableDataName);
    }

    public String getAdminTeacherTableDataID() {
        return AdminTeacherTableDataID.get();
    }

    public SimpleStringProperty adminTeacherTableDataIDProperty() {
        return AdminTeacherTableDataID;
    }

    public void setAdminTeacherTableDataID(String adminTeacherTableDataID) {
        this.AdminTeacherTableDataID.set(adminTeacherTableDataID);
    }

    public String getAdminTeacherTableDataEmail() {
        return AdminTeacherTableDataEmail.get();
    }

    public SimpleStringProperty adminTeacherTableDataEmailProperty() {
        return AdminTeacherTableDataEmail;
    }

    public void setAdminTeacherTableDataEmail(String adminTeacherTableDataEmail) {
        this.AdminTeacherTableDataEmail.set(adminTeacherTableDataEmail);
    }

    public String getAdminTeacherTableDataDepartment() {
        return AdminTeacherTableDataDepartment.get();
    }

    public SimpleStringProperty adminTeacherTableDataDepartmentProperty() {
        return AdminTeacherTableDataDepartment;
    }

    public void setAdminTeacherTableDataDepartment(String adminTeacherTableDataDepartment) {
        this.AdminTeacherTableDataDepartment.set(adminTeacherTableDataDepartment);
    }

    public String getAdminTeacherTableDataSec() {
        return AdminTeacherTableDataSec.get();
    }

    public SimpleStringProperty adminTeacherTableDataSecProperty() {
        return AdminTeacherTableDataSec;
    }

    public void setAdminTeacherTableDataSec(String adminTeacherTableDataSec) {
        this.AdminTeacherTableDataSec.set(adminTeacherTableDataSec);
    }
}
