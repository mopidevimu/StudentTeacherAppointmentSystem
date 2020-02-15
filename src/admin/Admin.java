package admin;
import javafx.application.Platform;
import javafx.scene.Node;
import menuBar.MenuBarControl;
import profileView.Info;
import profileView.ProfileView;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * @author Project red
 * This Class involves with all the functionalities of the admin in the admin GUI window 
 *
 */
public class Admin implements Initializable {

    @FXML
    TableView<AdminTable> adminTableView;
   
    @FXML
    TableColumn<AdminTable,String> adminTableStudentName;
    @FXML
    TableColumn<AdminTable,String> adminTableStudentID;
    @FXML
    TableColumn<AdminTable,String> adminTableStudentDepartment;
    @FXML
    TableColumn<AdminTable,String> adminTableStudentPhone;
    @FXML
    TableColumn<AdminTable,String> adminTableStudentDOB;
    @FXML
    TableColumn<AdminTable,String> adminTableStudentAddress;


    @FXML
    private Button adminClearButtonClick;
    @FXML
    private Button adminSaveButtonClick;


    @FXML
    private TextField adminTFStudentFname;
    @FXML
    private TextField adminTFStudentLname;
    @FXML
    private TextField adminTFStudentID;
    @FXML
    private ChoiceBox adminCBStudentDepartment;
    @FXML
    private TextField adminTFStudentEmail;
    @FXML
    private TextField adminTFStudentPhone;
    @FXML
    private TextField adminTFStudentAddress;
    @FXML
    private DatePicker adminDPStudentDOB;
    @FXML
    private TextField adminTFSearch;

   
    private DBConnection database = new DBConnection();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private boolean isSetAdminAddNewButtonClick;
    private boolean isSetAdminEditButtonClick;
    private MenuBarControl menuBarControl = new MenuBarControl();

    private String temp;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    	{
	        adminTableStudentName.setCellValueFactory(new PropertyValueFactory<AdminTable, String>("adminTableDataStudentName"));
	        adminTableStudentID.setCellValueFactory(new PropertyValueFactory<AdminTable, String>("adminTableDataStudentID"));
	        adminTableStudentDepartment.setCellValueFactory(new PropertyValueFactory<AdminTable, String>("adminTableDataStudentDepartment"));
	        adminTableStudentPhone.setCellValueFactory(new PropertyValueFactory<AdminTable, String>("adminTableDataStudentPhone"));
	        adminTableStudentDOB.setCellValueFactory(new PropertyValueFactory<AdminTable, String>("adminTableDataStudentDOB"));
	        adminTableStudentAddress.setCellValueFactory(new PropertyValueFactory<AdminTable, String>("adminTableDataStudentAddress"));
	      
	        adminTableView.setItems(getDataFromSqlAndAddToObservableList("SELECT * FROM student;"));
    	}

    /**
     * @param query sql querry is passed
     * @return
     * This method executes sql query and populates the table view with results got
     */
    
    private ObservableList getDataFromSqlAndAddToObservableList(String query)
    	{
        ObservableList<AdminTable> adminTableData = FXCollections.observableArrayList();
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                adminTableData.add(new AdminTable(      
                        resultSet.getString("dbStudentFname")+" "+resultSet.getString("dbStudentLname"),
                        resultSet.getString("dbStudentID"),
                        resultSet.getString("dbStudentDepartment"),                    
                        resultSet.getString("dbStudentPhone"),
                        resultSet.getString("dbStudentDOB"),                     
                        resultSet.getString("dbStudentAddress")
                        ));
            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminTableData;
    }



    /**
     * @param event Button Event got from Add New Button
     * This method sets attribute isSetAdminAddNewButtonClick to TRUE 
     */
    @FXML
    private void setAdminAddNewButtonClick(Event event)
	    {
	        adminSetAllEnable();
	        isSetAdminAddNewButtonClick = true;
	    }

    
    /**
     * This method enables fields disabled by default
     */
    private void adminSetAllEnable(){
        adminTFStudentFname.setDisable(false);
        adminTFStudentLname.setDisable(false);
        adminTFStudentID.setDisable(false);
        adminCBStudentDepartment.setDisable(false);
        adminTFStudentEmail.setDisable(false);
        adminTFStudentPhone.setDisable(false);
        adminTFStudentAddress.setDisable(false);
        adminDPStudentDOB.setDisable(false);
        adminSaveButtonClick.setDisable(false);
        adminClearButtonClick.setDisable(false);
    	}

    /**
     * This method disables fields 
     */
    private void adminSetAllDisable(){
        adminTFStudentFname.setDisable(true);
        adminTFStudentLname.setDisable(true);
        adminTFStudentID.setDisable(true);
        adminCBStudentDepartment.setDisable(true);
        adminTFStudentEmail.setDisable(true);
        adminTFStudentPhone.setDisable(true);
        adminTFStudentAddress.setDisable(true);
        adminDPStudentDOB.setDisable(true);
        adminSaveButtonClick.setDisable(true);
        adminClearButtonClick.setDisable(true);
    	}

    /**
     * @param event Button event got from save button
     * This method saves the newly added or edited details of student 
     */
    @FXML
    private void setAdminSaveButtonClick(Event event){
        try{
            connection = database.getConnection();
            statement = connection.createStatement();

            if(isSetAdminAddNewButtonClick){
                int rowsAffected = statement.executeUpdate("insert into`student` "+
                        "(`dbStudentFname`,`dbStudentLname`,`dbStudentID`,`dbStudentDOB`,"+
                        "`dbStudentDepartment`,`dbStudentEmail`,`dbStudentPhone`,`dbStudentAddress`"+") "+
                        "values ('"+adminTFStudentFname.getText()+"','"+adminTFStudentLname.getText()+"','"+adminTFStudentID.getText()
                        +"','"+adminDPStudentDOB.getValue()
                        +"','"+adminCBStudentDepartment.getValue().toString().trim()
                        +"','"+adminTFStudentEmail.getText()
                        +"','"+adminTFStudentPhone.getText()
                        +"','"+adminTFStudentAddress.getText()

                        +"'); ");
                statement.executeUpdate("insert into `enrollment` (`dbstudentgpaID`) VALUES ('"+adminTFStudentID.getText()+"')");
            }
            else if (isSetAdminEditButtonClick){

                int rowsAffected = statement.executeUpdate("update student set "+
                        "dbStudentFname = '"+adminTFStudentFname.getText()+"',"+
                        "dbStudentLname = '"+adminTFStudentLname.getText()+"',"+
                        "dbStudentID = '"+adminTFStudentID.getText()+"',"+
                        "dbStudentDOB = '"+adminDPStudentDOB.getValue()+"',"+
                        "dbStudentDepartment = '"+adminCBStudentDepartment.getValue().toString().trim()+"',"+
                        "dbStudentEmail = '"+adminTFStudentEmail.getText()+"',"+
                        "dbStudentPhone = '"+adminTFStudentPhone.getText()+"',"+
                        "dbStudentAddress = '"+adminTFStudentAddress.getText()+

                        "' where dbStudentID = '"+temp+"';");
               
            }

            connection.close();
            statement.close();
            resultSet.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        adminSetAllClear();
        adminSetAllDisable();
        adminTableView.setItems(getDataFromSqlAndAddToObservableList("SELECT * FROM student;"));
        isSetAdminEditButtonClick=false;
        isSetAdminAddNewButtonClick = false;
    }
    
    
    private void adminSetAllClear(){
        adminTFStudentFname.clear();
        adminTFStudentLname.clear();
        adminTFStudentID.clear();
        adminTFStudentEmail.clear();
        adminTFStudentPhone.clear();
        adminTFStudentAddress.clear();
    }

    /**
     * @param event Button Event got from clear Button
     * This method clears all the fields 
     */ 
    @FXML
    private void setAdminClearButtonClick(Event event){
        adminSetAllClear();
        adminSetAllDisable();
        isSetAdminEditButtonClick=false;
        isSetAdminAddNewButtonClick = false;
    }

    /**
     * @param event Button Event got from Refresh Button
     * This method refreshes the contents in the table view
     */
    @FXML
    private void setAdminRefreshButtonClick(Event event){
        adminTableView.setItems(getDataFromSqlAndAddToObservableList("SELECT * FROM student;"));
        adminTFSearch.clear();
    }

    /**
     * @param event Button Event got from search button 
     * This method searches in the database for the record searched
     */
    @FXML
    private void setAdminSearchButtonClick(Event event){
        String sqlQuery = "select * FROM student where dbStudentID = '"+adminTFSearch.getText()+"';";
        adminTableView.setItems(getDataFromSqlAndAddToObservableList(sqlQuery));
    }

    /**
     * @param event Button Event got from edit button
     * This method fetches the columns to be edited from database 
     */
    @FXML
    private void setAdminEditButtonClick(Event event){
        AdminTable getSelectedRow = adminTableView.getSelectionModel().getSelectedItem();


        String sqlQuery = "select * FROM student where dbStudentID = '"+getSelectedRow.getAdminTableDataStudentID()+"';";

        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            adminSetAllEnable();
            while(resultSet.next()) {
                adminTFStudentFname.setText(resultSet.getString("dbStudentFname"));
                adminTFStudentLname.setText(resultSet.getString("dbStudentLname"));
                adminTFStudentID.setText(resultSet.getString("dbStudentID"));
                adminCBStudentDepartment.setValue(resultSet.getString("dbStudentDepartment"));
                adminTFStudentEmail.setText(resultSet.getString("dbStudentEmail"));
                adminTFStudentPhone.setText(resultSet.getString("dbStudentPhone"));
                adminTFStudentAddress.setText(resultSet.getString("dbStudentAddress"));
                try {
                    if (!(resultSet.getString("dbStudentDOB").isEmpty())) {
                        adminDPStudentDOB.setValue(LocalDate.parse(resultSet.getString("dbStudentDOB")));
                    }
                }
                catch (NullPointerException e){
                    adminDPStudentDOB.setValue(null);
                }
            }

            temp=adminTFStudentID.getText();
            isSetAdminEditButtonClick = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param event Button Event got from delete button
     * This method deletes selected row of data from the database
     */
    @FXML
    private void setAdminDeleteButtonClick(Event event){

        AdminTable getSelectedRow = adminTableView.getSelectionModel().getSelectedItem();
        String sqlQuery = "delete from student where dbStudentID = '"+getSelectedRow.getAdminTableDataStudentID()+"';";
        try {
            connection = database.getConnection();
            statement = connection.createStatement();

            statement.executeUpdate(sqlQuery);
            statement.executeUpdate("delete from enrollment where dbstudentgpaID ='"+getSelectedRow.getAdminTableDataStudentID()+"';");
            adminTableView.setItems(getDataFromSqlAndAddToObservableList("SELECT * FROM student;"));

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param event Button Event got from view button
     * @throws IOException
     * This method fetches data from the selected row of database and displays in profile view window
     */
    @FXML
    private void setAdminViewButtonClick(Event event) throws IOException {

        Info info = new Info(adminTableView.getSelectionModel().getSelectedItem().getAdminTableDataStudentID());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/profileView/profileView.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));

        ProfileView profileView = loader.getController();
        profileView.setCurrentInfo(info);
        stage.show();

    }

    /**
     * @param event Button Event got from close button
     * This method will close the entire window
     */
    @FXML
    private void setAdminCloseButtonClick(Event event){
        menuBarControl.close();
    }

    /**
     * @param event Button Event got from about button
     * @throws IOException
     * This method will show the about window
     */
    @FXML
    private void setAdminAboutButtonClick(Event event) throws IOException {
        menuBarControl.about();
    }

    /**
     * @param event Button Event got from course-panel button
     * @throws IOException
     * This method will show the About window
     */
    @FXML
    private void setAdminCoursePanelClick(Event event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/admin/Course.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.setTitle("Course Panel");
        stage.show();
    }

    /**
     * @param event Button Event got from course-panel button
     * @throws IOException
     * This method opens the teachers panel for the admin
     */
    @FXML
    private void setAdminTeacherPanelClick(Event event)throws IOException{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/admin/AdminTeacher.fxml"));
        loader.load();
        Parent pa = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(pa));
        stage.setTitle("Teacher Panel");
        stage.show();

    }



}
