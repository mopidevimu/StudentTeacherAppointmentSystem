package teacher;

import javafx.beans.property.SimpleStringProperty;


/**
 * @author Project_Red
 *	This class is used to get/set student data's from/to observable list
 */
public class TeacherTableData {
    private final SimpleStringProperty teacherTableDataID;
    private final SimpleStringProperty teacherTableDataName;
    private final SimpleStringProperty teacherTableDataPhone;
  
   
    public TeacherTableData(String teacherTableDataID, String teacherTableDataName, String teacherTableDataPhone ) {
        this.teacherTableDataID = new SimpleStringProperty(teacherTableDataID);
        this.teacherTableDataName = new SimpleStringProperty(teacherTableDataName);
        this.teacherTableDataPhone = new SimpleStringProperty(teacherTableDataPhone);
     
    }

    public String getTeacherTableDataID() {
        return teacherTableDataID.get();
    }

    public SimpleStringProperty teacherTableDataIDProperty() {
        return teacherTableDataID;
    }

    public void setTeacherTableDataID(String teacherTableDataID) {
        this.teacherTableDataID.set(teacherTableDataID);
    }

    public String getTeacherTableDataName() {
        return teacherTableDataName.get();
    }

    public SimpleStringProperty teacherTableDataNameProperty() {
        return teacherTableDataName;
    }

    public void setTeacherTableDataName(String teacherTableDataName) {
        this.teacherTableDataName.set(teacherTableDataName);
    }


    public String getTeacherTableDataPhone() {
        return teacherTableDataPhone.get();
    }

    public SimpleStringProperty teacherTableDataPhoneProperty() {
        return teacherTableDataPhone;
    }

    public void setTeacherTableDataPhone(String teacherTableDataPhone) {
        this.teacherTableDataPhone.set(teacherTableDataPhone);
    }
 

  
}

