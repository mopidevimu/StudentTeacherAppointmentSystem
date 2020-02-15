package profileView;

import database.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author Project_Red
 * This is the class is used to get/set data's into profile view
 */
public class Info {
    private String id,name,department,vemail,dob,email,phone;
    private String address;
    DBConnection database = new DBConnection();
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public Info() {
    }

    public Info(String id) {
        this.id = id;
        setOtherField();

    }

    /**
     * This method retrieve's data's from the database to populate in profile view window
     */
    private void setOtherField(){
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM student where dbStudentID = '"+id+"';");
            while (resultSet.next()){
                name=resultSet.getString("dbStudentFname")+" "+resultSet.getString("dbStudentLname");
                department = resultSet.getString("dbStudentDepartment");
                vemail=name.substring(0,1)+resultSet.getString("dbStudentLname")+id.substring(3)+"@eisti.eu";
                dob = resultSet.getString("dbStudentDOB");
                email = resultSet.getString("dbStudentEmail");
                phone = resultSet.getString("dbStudentPhone");
                address = resultSet.getString("dbStudentAddress");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public String getId() {
        return id.substring(0,3)+" "+id.substring(3,6)+" "+id.substring(6);
    }

    public String getfullId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }


    public String getVemail() {
        return vemail;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

}
