package student;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Project_Red
 * This class is used to get/set student appointment data's from/to observable list
 *
 */
public class appointmentTable {
	private final SimpleStringProperty appTeacherID;
	private final SimpleStringProperty appTeacherName;
    private final SimpleStringProperty appTeacherCourse;
    private final SimpleStringProperty appTeacherFreeDate;
    private final SimpleStringProperty appTeacherFreeTime;


    public appointmentTable( String appTeacherID, String appTeacherName, String appTeacherCourse, String appTeacherFreeDate, String appTeacherFreeTime) {
    	this.appTeacherID = new SimpleStringProperty(appTeacherID);
    	this.appTeacherName = new SimpleStringProperty(appTeacherName);
    	this.appTeacherCourse = new SimpleStringProperty(appTeacherCourse);
    	this.appTeacherFreeDate = new SimpleStringProperty(appTeacherFreeDate);
    	this.appTeacherFreeTime = new SimpleStringProperty(appTeacherFreeTime);
    																												//	this.appStatus = new SimpleStringProperty(appStatus);
    	}
   
    
    public String getAppTeacherID() {
        return appTeacherID.get();
    }

    public SimpleStringProperty AppTeacherIDProperty() {
        return appTeacherID;
    }

    public void setappTeacherID(String AppTeacherID) {
        this.appTeacherID.set(AppTeacherID);
    }

    
    public String getAppTeacherName() {
        return appTeacherName.get();
    }

    public SimpleStringProperty AppTeacherNameProperty() {
        return appTeacherName;
    }

    public void setAppTeacherName(String AppTeacherName) {
        this.appTeacherName.set(AppTeacherName);
    }

    public String getAppTeacherCourse() {
        return appTeacherCourse.get();
    }

    public SimpleStringProperty AppTeacherCourseProperty() {
        return appTeacherCourse;
    }

    public void setAppTeacherCourse(String AppTeacherCourse) {
        this.appTeacherCourse.set(AppTeacherCourse);
    }

    public String getAppTeacherFreeDate() {
        return appTeacherFreeDate.get();
    }

    public SimpleStringProperty AppTeacherFreeDateProperty() {			
        return appTeacherFreeDate;
    }

    public void setAppTeacherFreeDate(String AppTeacherFreeDate) {
        this.appTeacherFreeDate.set(AppTeacherFreeDate);
    }
    
    public String getAppTeacherFreeTime() {
        return appTeacherFreeTime.get();
    }

    public SimpleStringProperty appTeacherFreeTimeProperty() {
        return appTeacherFreeTime;
    }

    public void setappTeacherFreeTime(String AppTeacherTime) {
        this.appTeacherFreeTime.set(AppTeacherTime);
    }

}


