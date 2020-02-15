package student;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Project_Red
 * This class is used to get/set student's appointment data's from/to observable list 
 *
 */
public class ViewAppointmentmodule {
	 	private final SimpleStringProperty vTeacherName;   
	    private final SimpleStringProperty vTeacherRequest;
	    private final SimpleStringProperty vTeacherappointmentstatus;
	    
	    public ViewAppointmentmodule(String vTeacherName, String vTeacherRequest, String vTeacherappointmentstatus) {
	    	this.vTeacherName = new SimpleStringProperty(vTeacherName);
	    	this.vTeacherRequest = new SimpleStringProperty(vTeacherRequest);
	    	this.vTeacherappointmentstatus = new SimpleStringProperty(vTeacherappointmentstatus);	
	    	}
	    public String getVTeacherName() {
	        return vTeacherName.get();
	    }

	    public SimpleStringProperty vTeacherNameProperty() {
	        return vTeacherName;
	    }

	    public void setVTeacherName(String vTeacherName) {
	        this.vTeacherName.set(vTeacherName);
	    }

	    public String getVTeacherRequest() {
	        return vTeacherRequest.get();
	    }

	    public SimpleStringProperty vTeacherRequestProperty() {
	        return vTeacherRequest;
	    }

	    public void setVTeacherRequest(String vTeacherRequest) {
	        this.vTeacherRequest.set(vTeacherRequest);
	    }

	    public String getVTeacherappointmentstatus() {
	        return vTeacherappointmentstatus.get();
	    }

	    public SimpleStringProperty vTeacherappointmentstatusProperty() {			
	        return vTeacherappointmentstatus;
	    }

	    public void setVTeacherappointmentstatus(String vTeacherappointmentstatus) {
	        this.vTeacherappointmentstatus.set(vTeacherappointmentstatus);
	    }
	   
	}

