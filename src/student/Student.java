package student;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import menuBar.MenuBarControl;
import profileView.Info;
import profileView.ProfileView;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Project_Red
 * This Class involves with functionalities of Student appointments 
 *
 */
public class Student {

    @FXML
    TableView<RegistrationTableData> studentRunningCourseTableView;
    @FXML
    TableColumn<RegistrationTableData,String> studentRCourseColumnCode;
    @FXML
    TableColumn<RegistrationTableData,String> studentRCourseColumnTitle;
    @FXML
    TableColumn<RegistrationTableData,Integer> studentRCourseColumnCredit;
    @FXML
    TableColumn<RegistrationTableData,String> studentRCourseColumnSec;
    
    @FXML
    TableView<appointmentTable> AppointmentView;
    @FXML
    TableColumn<appointmentTable,String> TeacherID;
    
    @FXML
    TableColumn<appointmentTable,String> TeacherName;
    @FXML
    TableColumn<appointmentTable,String> Course;
    @FXML
    TableColumn<appointmentTable,String> FreeDate;
    @FXML
    TableColumn<appointmentTable,String> FreeTime;
    
    @FXML
    TableView<appointmentTable> viewyourappointment;
    @FXML
    TableColumn<appointmentTable,String> TTeachername;
    @FXML
    TableColumn<appointmentTable,String> TTRequest;
    @FXML
    TableColumn<appointmentTable,String> TTappStatus;
    
    @FXML
    TextField studentTFFname;
    @FXML
    TextField studentTFLname;
    @FXML
    TextField studentTFID;
    @FXML
    TextField studentTFEmail;
    @FXML
    TextField studentTFPhone;
    @FXML
    TextField studentTFAddress;
    @FXML
    TextField studentTFPassword;
   
    
    @FXML
    TextArea TextAreass;

    @FXML
    Button studentSaveClick;
    @FXML
    Button studentCancelClick;
    @FXML
    Button showAppointmentClick;
    
    @FXML
    private Button SendClick;
    @FXML
    private Button viwyourapp;
    
    public boolean isViwAppClicked = false;
    
    public String Studentname, StudentID,StudentPhone;
    
    private DBConnection database = new DBConnection();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    RegistrationController registrationController;

    private MenuBarControl menuBarControl = new MenuBarControl();

    static String ID;
    public void setStudentId(String ID){
        this.ID = ID;
    }
    
    @FXML
    private void Enablethetableview(Event event){
    	isViwAppClicked = true;
    	SetapptableAllEnable();
    	
    }

    private void SetapptableAllEnable(){
    	AppointmentView.setDisable(false);
    	//viewyourappointment.setDisable(false);
    	SendClick.setDisable(false);
    	AppointmentView.setItems(getDataFromSqlAndAddToObservableList("SELECT  dbTeacherName,dbCourse,dbAppDate,dbAppTime FROM appointmet;"));
    	//viewyourappointment.setItems(getappDataFromSqlAndAddToObservableList("SELECT dbTeacherName,dbTeacherrequest,dbAppstatus FROM appointmet WHERE dbStudentID='"+ID+"';"));
    }
    
    @FXML
    private void initialize(){
        studentRCourseColumnCode.setCellValueFactory(new PropertyValueFactory<RegistrationTableData,String>("courseTableDataCode"));
        studentRCourseColumnTitle.setCellValueFactory(new PropertyValueFactory<RegistrationTableData,String>("courseTableDataTitle"));
        studentRCourseColumnCredit.setCellValueFactory(new PropertyValueFactory<RegistrationTableData,Integer>("courseTableDataCredit"));
        studentRCourseColumnSec.setCellValueFactory(new PropertyValueFactory<RegistrationTableData,String>("courseTableDataSec"));

        registrationController = new RegistrationController();
        studentRunningCourseTableView.setItems(registrationController.getDataFromCurrentCourseAndAddToObservableList("SELECT * FROM enrollment WHERE dbstudentgpaID = '"+ID+"';"));
        TeacherID.setCellValueFactory(new PropertyValueFactory<appointmentTable,String>("appTeacherID")); 
        TeacherName.setCellValueFactory(new PropertyValueFactory<appointmentTable,String>("appTeacherName")); 
		Course.setCellValueFactory(new PropertyValueFactory<appointmentTable, String>("appTeacherCourse"));
		FreeDate.setCellValueFactory(new PropertyValueFactory<appointmentTable, String>("appTeacherFreeDate"));
		FreeTime.setCellValueFactory(new PropertyValueFactory<appointmentTable, String>("appTeacherFreeTime"));
		
		
		TTeachername.setCellValueFactory(new PropertyValueFactory<appointmentTable,String>("vTeacherName")); 
		TTRequest.setCellValueFactory(new PropertyValueFactory<appointmentTable, String>("vTeacherRequest"));
		TTappStatus.setCellValueFactory(new PropertyValueFactory<appointmentTable, String>("vTeacherappointmentstatus"));
				//adding after program works
		
		try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM student WHERE dbStudentID = '"+ID+"';");
            while (resultSet.next()){
            	StudentID= resultSet.getString("dbStudentID");
            	Studentname=resultSet.getString("dbStudentFname")+" "+resultSet.getString("dbStudentLname");
                StudentPhone=resultSet.getString("dbStudentPhone");
               
            }
            connection.close();
            statement.close();
            resultSet.close();
         //   System.out.println(StudentID + Studentname+ StudentPhone);
            setAllDisable(); 
			}  
			catch (SQLException e) {
            e.printStackTrace();
			}
    }
    
    @FXML
    void OnViewSchedule(ActionEvent event) {
    	setAllEnable();
    	String sqlQuery = "Select * from teacher;";
    	
    	try {
            connection = database.getConnection();
            statement = connection.createStatement();
            statement.executeQuery(sqlQuery);
            AppointmentView.setItems(getDataFromSqlAndAddToObservableList("SELECT * FROM teacher ;"));
    	 }
        catch (SQLException e) {
            e.printStackTrace();
        }	
    }
    
    private ObservableList getDataFromSqlAndAddToObservableList(String query) {
    	 ObservableList<appointmentTable> appTableData = FXCollections.observableArrayList();
         try {
             connection = database.getConnection();
             statement = connection.createStatement();
             resultSet = statement.executeQuery(query);
             while(resultSet.next()){
                 appTableData.add(new appointmentTable(
                		 resultSet.getString("dbTeacherID"),
                         resultSet.getString("dbTeacherFname")+" "+ resultSet.getString("dbTeacherLname"),
                         resultSet.getString("dbTeacherCourse"),
                         resultSet.getString("dbAppFreedate"),
                         resultSet.getString("dbAppFreeTime")  
                         ));
             }
             connection.close();
             statement.close();
             resultSet.close(); 
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return appTableData;
     }
    
    @FXML
    void OnSendRequest(ActionEvent event) {
    	appointmentTable getSelectedRow = AppointmentView.getSelectionModel().getSelectedItem();
    	String text= TextAreass.getText();
    	
    	String Tid= getSelectedRow.getAppTeacherID();
    	String Tname =getSelectedRow.getAppTeacherName();
    	String Tfreedate = getSelectedRow.getAppTeacherFreeDate();
    	String Tfreetime= getSelectedRow.getAppTeacherFreeTime();
    	
    	if((Tfreedate!=null) && (Tfreetime!=null))
    	{
    	String sqlQuery = "Insert into appointmet (dbTeacherID, dbTeacherName,dbStudentID,dbStudentName,dBPhone,dbTeacherrequest,dbstudentrequest,dbAppDescription,dbAppStatus) values('"+Tid+"','"+Tname+"','"+StudentID+"','"+Studentname+"','"+StudentPhone+"','Student',1,'"+text+"', 'Processing') ";
    	
    	try {
            connection = database.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            //TableColumn
            TextAreass.clear();
        	NotificationType notificationType = NotificationType.SUCCESS;
            TrayNotification tray = new TrayNotification();
        	 tray.setTitle(" Success");
             tray.setMessage("You have successfully submitted the Request for appointment");
             tray.setNotificationType(notificationType);
             tray.showAndDismiss(Duration.millis(4000));
    	 }
        catch (SQLException e) {
            e.printStackTrace();
            NotificationType notificationType = NotificationType.ERROR;
            TrayNotification tray = new TrayNotification();
        	 tray.setTitle("Error");
             tray.setMessage("You have already Requested for appointment");
             tray.setNotificationType(notificationType);
             tray.showAndDismiss(Duration.millis(4000));
        }
    	}
    	else
    	{
    		NotificationType notificationType = NotificationType.WARNING;
            TrayNotification tray = new TrayNotification();
        	 tray.setTitle("Warning");
             tray.setMessage("Teacher Has no Free Schedule");
             tray.setNotificationType(notificationType);
             tray.showAndDismiss(Duration.millis(4000));
    	}
    }
    
    @FXML
    void OnViewAppointments(ActionEvent event) {
    	setAllEnable();
    	String text= TextAreass.getText();
    	String sqlQuery = "Select * from appointmet where dbStudentID='"+ID+"';";
    	
    	try {
            connection = database.getConnection();
            statement = connection.createStatement();
            statement.executeQuery(sqlQuery);
            viewyourappointment.setItems(getappDataFromSqlAndAddToObservableList("SELECT DISTINCT dbTeacherName,dbTeacherrequest,dbAppstatus FROM appointmet WHERE dbStudentID='"+ID+"';"));
    	 }
        catch (SQLException e) {
            e.printStackTrace();
        }	
    }
    
    private ObservableList getappDataFromSqlAndAddToObservableList(String query) {
   	 ObservableList<ViewAppointmentmodule> appvvvTableData = FXCollections.observableArrayList();
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
            	appvvvTableData.add(new ViewAppointmentmodule(
                        resultSet.getString("dbTeacherName"),
                        resultSet.getString("dbTeacherRequest"),
                        resultSet.getString("dbAppstatus")
                        ));
            }
            connection.close();
            statement.close();
            resultSet.close(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appvvvTableData;
    }
    
 
    private void setAllEnable(){
        studentTFFname.setDisable(false);
        studentTFLname.setDisable(false);
        studentTFEmail.setDisable(false);
        studentTFPhone.setDisable(false);
        studentTFAddress.setDisable(false);
        studentTFPassword.setDisable(false);
        studentSaveClick.setDisable(false);
        studentCancelClick.setDisable(false);
        AppointmentView.setDisable(false);
    }

    private void setAllDisable(){
        studentTFFname.setDisable(true);
        studentTFLname.setDisable(true);
        studentTFEmail.setDisable(true);
        studentTFPhone.setDisable(true);
        studentTFAddress.setDisable(true);
        studentTFPassword.setDisable(true);

        studentSaveClick.setDisable(true);
        studentCancelClick.setDisable(true);
        
        AppointmentView.setDisable(true);
       
    }

    private void setAllClear(){
        studentTFFname.clear();
        studentTFLname.clear();
        studentTFID.clear();
        studentTFEmail.clear();
        studentTFPhone.clear();
        studentTFAddress.clear();
        studentTFPassword.clear();
        studentTFPassword.clear();
        
    }
 
    @FXML
    private void setCourseAboutButtonClick(Event event) throws IOException {
        menuBarControl.about();
    }

    @FXML
    private void setCourseCloseButtonClick(Event event){
        menuBarControl.close();
    }

    @FXML
    private void setStudentEditProfileClick(Event event){
        setAllEnable();
        String sqlQuery = "select * FROM student where dbStudentID = '"+ID+"';";
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()) {
                studentTFFname.setText(resultSet.getString("dbStudentFname"));
                studentTFLname.setText(resultSet.getString("dbStudentLname"));
                studentTFID.setText(resultSet.getString("dbStudentID"));
                studentTFEmail.setText(resultSet.getString("dbStudentEmail"));
                studentTFPassword.setText(resultSet.getString("dbStudentPassword"));
                studentTFPhone.setText(resultSet.getString("dbStudentPhone"));
                studentTFAddress.setText(resultSet.getString("dbStudentAddress"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void setStudentViewProfileClick(Event event) throws IOException {

        Info info = new Info(ID);
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

    @FXML
    private void setStudentCancelClick(Event event){
        setAllDisable();
        setAllClear();
    }

    @FXML
    private void setStudentSaveClick(Event event){
        try {
            connection = database.getConnection();
            statement = connection.createStatement();

            int rowsAffected = statement.executeUpdate("update student set "+
                    "dbStudentFname = '"+studentTFFname.getText()+"',"+
                    "dbStudentLname = '"+studentTFLname.getText()+"',"+
                    "dbStudentID = '"+studentTFID.getText()+"',"+
                    "dbStudentEmail = '"+studentTFEmail.getText()+"',"+
                    "dbStudentPassword = '"+studentTFPassword.getText()+"',"+
                    "dbStudentPhone = '"+studentTFPhone.getText()+"',"+
                    "dbStudentAddress = '"+studentTFAddress.getText()+
                    "' where dbStudentID = '"+
                    ID+"';");

            connection.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        setAllClear();
        setAllDisable();

    }

    @FXML
    private void setStudentRegistrationClick(Event event)throws IOException{
        RegistrationController re = new RegistrationController();
        re.setID(ID);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/student/Registration.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        RegistrationController registrationController = new RegistrationController();
        registrationController.setStage(stage);
        registrationController.setTanother(studentRunningCourseTableView);
        stage.show();

    }


}