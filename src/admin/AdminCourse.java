package admin;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import menuBar.MenuBarControl;

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
 * This Class involves with functionalities of admin adding/editing/deleting courses 
 *
 */
public class AdminCourse implements Initializable {

    private DBConnection database = new DBConnection();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private boolean isSetCourseAddNewButtonClick;
    private boolean isSetCourseEditButtonClick;
    private MenuBarControl menuBarControl = new MenuBarControl();
    private String temp ;

    @FXML
    TableView<CourseTable> adminCourseTableView;
    @FXML
    TableColumn<CourseTable,Integer> adminCourseTableNo;
    @FXML
    TableColumn<CourseTable,String> adminCourseTableCode;
    @FXML
    TableColumn<CourseTable,String> adminCourseTableTitle;
    @FXML
    TableColumn<CourseTable,Integer> adminCourseTableCredit;
    @FXML
    TableColumn<CourseTable,String> adminCourseTableSec;


    @FXML
    private TextField courseTFCode;
    @FXML
    private TextField courseTFTitle;
    @FXML
    private TextField courseTFCredit;
    @FXML
    private TextField courseTFSec;
    @FXML
    private TextField courseTFSearch;


    @FXML
    private Button courseClearButtonClick;
    @FXML
    private Button courseSaveButtonClick;


    /**
     * @param query sql querry is passed
     * @return
     * This method executes sql query and populates the table view with results got
     */
    private ObservableList getDataFromCourseAndAddToObservableList(String query){
        ObservableList<CourseTable> courseTableData = FXCollections.observableArrayList();
        try {

            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                courseTableData.add(new CourseTable(
                        resultSet.getInt("dbCourseSerial"),
                        resultSet.getString("dbCourseCode"),
                        resultSet.getString("dbCourseTitle"),
                        resultSet.getInt("dbCourseCredit"),
                        resultSet.getString("dbCourseSec")
                ));
            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseTableData;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        adminCourseTableNo.setCellValueFactory(new PropertyValueFactory<CourseTable,Integer>("courseTableDataNo"));
        adminCourseTableCode.setCellValueFactory(new PropertyValueFactory<CourseTable,String>("courseTableDataCode"));
        adminCourseTableTitle.setCellValueFactory(new PropertyValueFactory<CourseTable,String>("courseTableDataTitle"));
        adminCourseTableCredit.setCellValueFactory(new PropertyValueFactory<CourseTable,Integer>("courseTableDataCredit"));
        adminCourseTableSec.setCellValueFactory(new PropertyValueFactory<CourseTable,String>("courseTableDataSec"));

        adminCourseTableView.setItems(getDataFromCourseAndAddToObservableList("SELECT * FROM course;"));

    }

    /**
     * @param event Button Event got from Add New Button
     * This method sets attribute isSetAdminAddNewButtonClick to TRUE 
     */
    @FXML
    private void setCourseAddNewButtonClick(Event event){
        courseSetAllEnable();
        isSetCourseAddNewButtonClick = true;
    }

    /**
     * This method enables fields disabled by default
     */
    private void courseSetAllEnable(){

        courseTFCode.setDisable(false);
        courseTFTitle.setDisable(false);
        courseTFCredit.setDisable(false);
        courseTFSec.setDisable(false);
        courseClearButtonClick.setDisable(false);
        courseSaveButtonClick.setDisable(false);
    }

    /**
     * This method disables fields 
     */
    private void courseSetAllDisable(){
        courseTFCode.setDisable(true);
        courseTFTitle.setDisable(true);
        courseTFCredit.setDisable(true);
        courseTFSec.setDisable(true);
        courseClearButtonClick.setDisable(true);
        courseSaveButtonClick.setDisable(true);
    }

    /**
     * @param event Button Event got from edit button
     * This method fetches the columns to be edited from database 
     */
    @FXML
    private void setCourseEditButtonClick(Event event){
        CourseTable getSelectedRow = adminCourseTableView.getSelectionModel().getSelectedItem();

        String sqlQuery = "select * FROM course where dbCourseCode = '"+getSelectedRow.getCourseTableDataCode()+"';";

        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            courseSetAllEnable();
            while(resultSet.next()) {
                courseTFCode.setText(resultSet.getString("dbCourseCode"));
                courseTFTitle.setText(resultSet.getString("dbCourseTitle"));
                courseTFCredit.setText(resultSet.getString("dbCourseCredit"));
                courseTFSec.setText(resultSet.getString("dbCourseSec"));

            }

            temp =courseTFCode.getText();
            isSetCourseEditButtonClick = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param event Button Event got from delete button
     * This method deletes selected row of course from the database
     */
    @FXML
    private void setCourseDeleteButtonClick(Event event){
        CourseTable getSelectedRow = adminCourseTableView.getSelectionModel().getSelectedItem();
        String sqlQuery = "delete from course where dbCourseCode = '"+getSelectedRow.getCourseTableDataCode()+"';";
        try {
            connection = database.getConnection();
            statement = connection.createStatement();

            int rowsAffected = statement.executeUpdate(sqlQuery);
            adminCourseTableView.setItems(getDataFromCourseAndAddToObservableList("SELECT * FROM course;"));

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param event Button event got from save button
     * This method saves the newly added or edited details of courses 
     */
    @FXML
    private void setCourseSaveButtonClick(Event event){

        try{
            connection = database.getConnection();
            statement = connection.createStatement();

            if(isSetCourseAddNewButtonClick){
                int rowsAffected = statement.executeUpdate("insert into `course` (`dbCourseCode`,`dbCourseTitle`,`dbCourseCredit`,`dbCourseSec`) values ('"+
                        courseTFCode.getText()+"','"+courseTFTitle.getText()+"','"+courseTFCredit.getText()+"','"+courseTFSec.getText()+"') ;");
            }
            else if (isSetCourseEditButtonClick){
                int rowsAffected = statement.executeUpdate("update course set "+
                        "dbCourseCode = '"+courseTFCode.getText()+"',"+
                        "dbCourseTitle = '"+courseTFTitle.getText()+"',"+
                        "dbCourseCredit = '"+courseTFCredit.getText()+"',"+
                        "dbCourseSec = '"+courseTFSec.getText()+

                        "' where dbCourseCode = '"+
                        temp+"';");
            }


            connection.close();
            statement.close();
            resultSet.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        courseSetAllClear();
        courseSetAllDisable();
        adminCourseTableView.setItems(getDataFromCourseAndAddToObservableList("SELECT * FROM course;"));
        isSetCourseAddNewButtonClick=false;
        isSetCourseEditButtonClick = false;

    }

    private void courseSetAllClear(){
        courseTFCode.clear();
        courseTFTitle.clear();
        courseTFCredit.clear();
        courseTFSec.clear();
    }

    @FXML
    private void setCourseClearButtonClick(Event event){
        courseSetAllClear();
        courseSetAllDisable();
        isSetCourseEditButtonClick=false;
        isSetCourseAddNewButtonClick = false;
    }

    /**
     * @param event Button Event got from Refresh Button
     * This method refreshes the contents in the table view
     */
    @FXML
    private void setCourseRefreshButtonClick(Event event){
        adminCourseTableView.setItems(getDataFromCourseAndAddToObservableList("SELECT * FROM course;"));
        courseTFSearch.clear();
    }

    /**
     * @param event Button Event got from search button
     * This method searches in the database for the record searched
     */
    @FXML
    private void setCourseSearchButtonClick(Event event){
        String sqlQuery = "select * FROM course where dbCourseCode = '"+courseTFSearch.getText()+"';";
        adminCourseTableView.setItems(getDataFromCourseAndAddToObservableList(sqlQuery));
    }

    /**
     * @param event Button Event got from about button
     * @throws IOException
     * This method will show the about window
     */
    @FXML
    private void setCourseAboutButtonClick(Event event) throws IOException {
        menuBarControl.about();
    }

    /**
     * @param event Button Event got from close button
     * This method will close the entire window
     */
    @FXML
    private void setCourseCloseButtonClick(Event event){
        menuBarControl.close();
    }





}
