package admin;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 * @author Project_Red
 * This class is used to get/set student data's from/to observable list
 *
 */
public class AdminTable {
    private final SimpleStringProperty adminTableDataStudentName;
    private final SimpleStringProperty adminTableDataStudentID;
    private final SimpleStringProperty adminTableDataStudentDepartment;
    private final SimpleStringProperty adminTableDataStudentPhone;
    private final SimpleStringProperty adminTableDataStudentDOB;
    private final SimpleStringProperty adminTableDataStudentAddress;

    public AdminTable( String adminTableDataStudentName, String adminTableDataStudentID, String adminTableDataStudentDepartment, String adminTableDataStudentPhone, String adminTableDataStudentDOB,  String adminTableDataStudentAddress) {
        this.adminTableDataStudentName = new SimpleStringProperty(adminTableDataStudentName);
        this.adminTableDataStudentID = new SimpleStringProperty(adminTableDataStudentID);
        this.adminTableDataStudentDepartment = new SimpleStringProperty(adminTableDataStudentDepartment);
        this.adminTableDataStudentPhone = new SimpleStringProperty(adminTableDataStudentPhone);
        this.adminTableDataStudentDOB = new SimpleStringProperty(adminTableDataStudentDOB);
        this.adminTableDataStudentAddress = new SimpleStringProperty(adminTableDataStudentAddress);
    }


  
    public String getAdminTableDataStudentName() {
        return adminTableDataStudentName.get();
    }

    public SimpleStringProperty adminTableDataStudentNameProperty() {
        return adminTableDataStudentName;
    }

    public void setAdminTableDataStudentName(String adminTableDataStudentName) {
        this.adminTableDataStudentName.set(adminTableDataStudentName);
    }

    public String getAdminTableDataStudentID() {
        return adminTableDataStudentID.get();
    }

    public SimpleStringProperty adminTableDataStudentIDProperty() {
        return adminTableDataStudentID;
    }

    public void setAdminTableDataStudentID(String adminTableDataStudentID) {
        this.adminTableDataStudentID.set(adminTableDataStudentID);
    }

    public String getAdminTableDataStudentDepartment() {
        return adminTableDataStudentDepartment.get();
    }

    public SimpleStringProperty adminTableDataStudentDepartmentProperty() {
        return adminTableDataStudentDepartment;
    }

    public void setAdminTableDataStudentDepartment(String adminTableDataStudentDepartment) {
        this.adminTableDataStudentDepartment.set(adminTableDataStudentDepartment);
    }

    public String getAdminTableDataStudentPhone() {
        return adminTableDataStudentPhone.get();
    }

    public SimpleStringProperty adminTableDataStudentPhoneProperty() {
        return adminTableDataStudentPhone;
    }

    public void setAdminTableDataStudentPhone(String adminTableDataStudentPhone) {
        this.adminTableDataStudentPhone.set(adminTableDataStudentPhone);
    }

    public String getAdminTableDataStudentDOB() {
        return adminTableDataStudentDOB.get();
    }

    public SimpleStringProperty adminTableDataStudentDOBProperty() {
        return adminTableDataStudentDOB;
    }

    public void setAdminTableDataStudentDOB(String adminTableDataStudentDOB) {
        this.adminTableDataStudentDOB.set(adminTableDataStudentDOB);
    }


    public String getAdminTableDataStudentAddress() {
        return adminTableDataStudentAddress.get();
    }

    public SimpleStringProperty adminTableDataStudentAddressProperty() {
        return adminTableDataStudentAddress;
    }

    public void setAdminTableDataStudentAddress(String adminTableDataStudentAddress) {
        this.adminTableDataStudentAddress.set(adminTableDataStudentAddress);
    }
}
