package teacher;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Project_Red
 *This class is used to get/set Teachers data's from/to observable list
 */
public class appTeacherview {
	private final SimpleStringProperty appTableDataID;
    private final SimpleStringProperty appTableDataName;
    private final SimpleStringProperty appTableDataPhone;
    private final SimpleStringProperty appTableDataDescription;
    private final SimpleStringProperty appTableDatawhetherrequest;
    private final SimpleStringProperty appTableDataStatus;
   
    public appTeacherview(String appTableDataID, String appTableDataName, String appTableDataPhone, String appTableDataDescription, String appTableDatawhetherrequest, String appTableDataStatus) {
        this.appTableDataID = new SimpleStringProperty(appTableDataID);
        this.appTableDataName = new SimpleStringProperty(appTableDataName);
        this.appTableDataPhone = new SimpleStringProperty(appTableDataPhone);
        this.appTableDataDescription = new SimpleStringProperty(appTableDataDescription);
        this.appTableDatawhetherrequest = new SimpleStringProperty(appTableDatawhetherrequest);
        this.appTableDataStatus = new SimpleStringProperty(appTableDataStatus);
    }

    public String getAppTableDataID() {
        return appTableDataID.get();
    }

    public SimpleStringProperty appTableDataIDProperty() {
        return appTableDataID;
    }

    public void setAppTableDataID(String appTableDataID) {
        this.appTableDataID.set(appTableDataID);
    }

    public String getAppTableDataName() {
        return appTableDataName.get();
    }

    public SimpleStringProperty appTableDataNameProperty() {
        return appTableDataName;
    }

    public void setAppTableDataName(String appTableDataName) {
        this.appTableDataName.set(appTableDataName);
    }


    public String getAppTableDataPhone() {
        return appTableDataPhone.get();
    }

    public SimpleStringProperty appTableDataPhoneProperty() {
        return appTableDataPhone;
    }

    public void setAppTableDataPhone(String appTableDataPhone) {
        this.appTableDataPhone.set(appTableDataPhone);
    }

    public String getAppTableDataDescription() {
        return appTableDataDescription.get();
    }

    public SimpleStringProperty appTableDataDescriptionProperty() {
        return appTableDataDescription;
    }

    public void setAppTableDataDescription(String appTableDataDescription) {
        this.appTableDataDescription.set(appTableDataDescription);
    }
    public String getAppTableDatawhetherrequest() {
        return appTableDatawhetherrequest.get();
    }

    public SimpleStringProperty appTableDatawhetherrequestProperty() {
        return appTableDatawhetherrequest;
    }

    public void setAppTableDatawhetherrequest(String appTableDatawhetherrequest) {
        this.appTableDatawhetherrequest.set(appTableDatawhetherrequest);
    }
    
    public String getAppTableDataStatus() {
        return appTableDataStatus.get();
    }

    public SimpleStringProperty appTableDataStatusProperty() {
        return appTableDataStatus;
    }

    public void setAppTableDataStatus(String appTableDataStatus) {
        this.appTableDataStatus.set(appTableDataStatus);
    }
}


