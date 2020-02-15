package teacher;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.StringUtils;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Duration;
import menuBar.MenuBarControl;
import student.ViewAppointmentmodule;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;

import static jdk.nashorn.internal.objects.NativeMath.round;

/**
 * @author Project_Red
 * This Class involves with functionalities of teacher appointments 
 */
public class TeacherController {
	@FXML
	private ComboBox<String> teacherTableSecChoose;
		
	@FXML
    private TableView<TeacherTableData> teacherTableView;
	@FXML
    private TableColumn<TeacherTableData, String> teacherColumnID;
    @FXML
    private TableColumn<TeacherTableData, String> teacherColumnName;
    @FXML
    private TableColumn<TeacherTableData, String> teacherColumnPhone;
  
      
    @FXML
    private TableView<appTeacherview> teacherappView;
    @FXML
    private TableColumn<appTeacherview, String> appColumnID1;
    @FXML
    private TableColumn<appTeacherview, String> appColumnName1;
    @FXML
    private TableColumn<appTeacherview, String> appColumnPhone1;
    @FXML
    private TableColumn<appTeacherview, String> appteacherdescription;
    @FXML
    private TableColumn<appTeacherview, String> appteacherColumnStatus;
    @FXML
    private TableColumn<appTeacherview, String> appRequeststatus;

   
    @FXML
    private Button teacherviewStudents;
    @FXML
    private Button appviewappointment;
    @FXML
    private Button Delete;
    @FXML
    private Button Accept;
    @FXML
    TextArea TextAreass;

    
    static String ID;
    static String TNAME, freeDate, freeTime; 
    static String dateval;
    static LocalTime Stardat;
    static LocalTime Enddat;
    
    private DBConnection database = new DBConnection();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet,resultSet1;
    private MenuBarControl menuBarControl = new MenuBarControl();
    private static String email;
       
    private String selectedID;
    private String courseID;
    
    @FXML
    private JFXDatePicker TeachFreeDate;
    
    @FXML
    private JFXTimePicker StartTime;
    
    @FXML
    private JFXTimePicker EndTime;
    
    
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }


    private void setSelectedID(String selectedID){
        this.selectedID = selectedID;
    }
    
    public void setID(String ID) {
        this.ID = ID;
    }
    
    @FXML
    private void initialize(){
    	
        teacherColumnID.setCellValueFactory(new PropertyValueFactory<TeacherTableData,String>("teacherTableDataID"));
        teacherColumnName.setCellValueFactory(new PropertyValueFactory<TeacherTableData,String>("teacherTableDataName"));
        teacherColumnPhone.setCellValueFactory(new PropertyValueFactory<TeacherTableData,String>("teacherTableDataPhone"));
    
        
        appColumnName1.setCellValueFactory(new PropertyValueFactory<appTeacherview,String>("appTableDataID"));
        appColumnID1.setCellValueFactory(new PropertyValueFactory<appTeacherview,String>("appTableDataName"));
        appColumnPhone1.setCellValueFactory(new PropertyValueFactory<appTeacherview,String>("appTableDataPhone"));
        appteacherdescription.setCellValueFactory(new PropertyValueFactory<appTeacherview,String>("appTableDataDescription"));
        appteacherColumnStatus.setCellValueFactory(new PropertyValueFactory<appTeacherview,String>("appTableDatawhetherrequest"));
        appRequeststatus.setCellValueFactory(new PropertyValueFactory<appTeacherview,String>("appTableDataStatus")); 
        
        String name;
        String teacherSecs[]=null,teacherSec=null;

        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM teacher WHERE dbTeacherID = '"+ID+"';");
            while (resultSet.next()){
            	TNAME=resultSet.getString("dbTeacherFname")+" "+resultSet.getString("dbTeacherLname");
                teacherSec=resultSet.getString("dbTeacherCourse");
                freeDate=resultSet.getString("dbAppFreeDate");
                freeTime=resultSet.getString("dbAppFreeTime");
            }
            if (teacherSec.contains(",")){
                teacherSecs = teacherSec.split(",",0);
            }
            else if (!teacherSec.isEmpty()){
                teacherSecs[0] = teacherSec;
            }
            else {
                System.out.println("There is no teacher sec");
            }

            ObservableList<String> sec = FXCollections.observableArrayList(teacherSecs);
            teacherTableSecChoose.setItems(sec);
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        teacherTableSecChoose.getSelectionModel().select(teacherSecs[0]);
        setCourseID(teacherSecs[0]);

        teacherTableView.setItems(getDataFromTeacherTableDataAndAddToObservableList(teacherSecs[0]));


    }

    private ObservableList getDataFromTeacherTableDataAndAddToObservableList(String query) {
        ObservableList<TeacherTableData> teacherTableDatas = FXCollections.observableArrayList();
        try {
            String id = null,name =null,phone=null,requeststatus=null;
            String idList[][] = new String[50][2];
            int i = 0;
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet1 = statement.executeQuery("SELECT * FROM enrollment where  dbstudentgpaCurrentCourse LIKE '%"+query+"%'");

            while (resultSet1.next()){
                idList[i][0] = resultSet1.getString("dbstudentgpaID");            
                i++;
            }


            for (int j=0;idList[j][0]!=null&&!idList[j][0].isEmpty();j++) {

                resultSet = statement.executeQuery("SELECT * FROM student where  dbStudentID = '"+idList[j][0]+"'");
                
                while (resultSet.next()){
                    name=resultSet.getString("dbStudentFname")+" "+resultSet.getString("dbStudentLname");
                    phone = resultSet.getString("dbStudentPhone");
                    
                }

                teacherTableDatas.add(new TeacherTableData(idList[j][0],name,phone));
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacherTableDatas;
    }
    
    
    @FXML
    void OnAddFreeSchedule(ActionEvent event) {
    	
    	
    	LocalDate dateval1 = TeachFreeDate.getValue();
    	Stardat= StartTime.getValue();
    	Enddat= EndTime.getValue();
    	String Totaltime = Stardat+"-"+ Enddat;
    	LocalDate todaydate=LocalDate.now();
    	LocalTime timenow = LocalTime.now();
    	LocalTime checkstart = LocalTime.of(9,00 );
    	LocalTime checkend = LocalTime.of(17,00 );
    
    	if(dateval1!=null && Stardat!=null && Enddat!=null)
    	{
    		if(dateval1.isAfter(todaydate)&& Stardat.isAfter(checkstart)&& Enddat.isBefore(checkend)&& Enddat.isAfter(Stardat))
    		{
    		try {
    			String sqlQuery= "update teacher SET dbAppFreeDate='"+dateval1+"', dbAppFreeTime='"+Totaltime+"' Where dbTeacherID= '"+ID+"'";
    			connection = database.getConnection();
                statement = connection.createStatement();
                statement.executeUpdate(sqlQuery);
    			}
    		catch (SQLException e) {
                e.printStackTrace();
            }	}
    		else
    		{
    			NotificationType notificationType = NotificationType.CUSTOM;
                TrayNotification tray = new TrayNotification();
            	tray.setTitle("Error -- Date must be bigger than today\n");
                tray.setMessage("StartTime must be greater than 9.00Hr and less than EndTime\nwhile EndTime must be less than 17.00Hr");
                tray.setNotificationType(notificationType);
                tray.showAndDismiss(Duration.millis(25000));
    		}
    	}
    	else
    	{
    		NotificationType notificationType = NotificationType.CUSTOM;
            TrayNotification tray = new TrayNotification();
        	tray.setTitle("Error -- Date must be bigger than today\n");
            tray.setMessage("StartTime must be greater than 9.00 and less than EndTime\nwhile EndTime must be less than 17.00");
            tray.setNotificationType(notificationType);
            tray.showAndDismiss(Duration.millis(10000));
    	}
    }
    
    @FXML
    void onViewAppointment(ActionEvent event) {
    	setAllEnable();
    	String sqlQuery = "Select * from appointmet where dbTeacherID='"+ID+"';";
    	
    	try {
            connection = database.getConnection();
            statement = connection.createStatement();
            statement.executeQuery(sqlQuery);
            teacherappView.setItems(getappviewDataFromSqlAndAddToObservableList("SELECT DISTINCT dbStudentID,dbStudentName,dBPhone,dbAppDescription,dbTeacherrequest,dbAppstatus FROM appointmet WHERE dbTeacherID='"+ID+"';"));
    	 }
        catch (SQLException e) {
            e.printStackTrace();
        }	
    }
    
    @FXML
    void onDeleteAppointment(ActionEvent event) {
    	appTeacherview getSelectedRow = teacherappView.getSelectionModel().getSelectedItem();
    	String StuID =getSelectedRow.getAppTableDataID();
    	String sqlQuery = "Delete FROM appointmet Where dbStudentID ='"+StuID+"' AND dbTeacherID='"+ID+"'";
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
    
    @FXML
    void OnAcceptAppointment(ActionEvent event) {
    	appTeacherview getSelectedRow = teacherappView.getSelectionModel().getSelectedItem();
    	String StuID =getSelectedRow.getAppTableDataID();
    	String sqlQuery = "UPDATE appointmet SET dbAppstatus='Accepted' Where dbStudentID ='"+StuID+"' AND dbTeacherID='"+ID+"'";
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
    
  
    
    private ObservableList getappviewDataFromSqlAndAddToObservableList(String query) {
      	 ObservableList<appTeacherview> appvvvTableData = FXCollections.observableArrayList();
           try {
               connection = database.getConnection();
               statement = connection.createStatement();
               resultSet = statement.executeQuery(query);
               while(resultSet.next()){
               	appvvvTableData.add(new appTeacherview(
	               		   resultSet.getString("dbStudentId"),
	                       resultSet.getString("dbStudentName"),
	                       resultSet.getString("dBPhone"),
                           resultSet.getString("dbAppDescription"),
                           resultSet.getString("dbTeacherrequest"),
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
    
    
    @FXML
    private void setTeacherLoadClick(Event event) throws SQLException {

        setCourseID(teacherTableSecChoose.getValue());
        teacherTableView.setItems(getDataFromTeacherTableDataAndAddToObservableList(teacherTableSecChoose.getValue()));
    }
    
    
    @FXML
    void OnSendRequest(ActionEvent event) {
    	
    	TeacherTableData getSelectedRow = teacherTableView.getSelectionModel().getSelectedItem();
    	String Sname =getSelectedRow.getTeacherTableDataName();
    	String Sid = getSelectedRow.getTeacherTableDataID();
    	String Sphone= getSelectedRow.getTeacherTableDataPhone(); 	
    	if((freeDate!=null)&&(freeTime!=null))
            	{
            	String text= TextAreass.getText();
            	
            	String sqlQuery = "Insert into appointmet (dbTeacherID, dbTeacherName,dbStudentID,dbStudentName,dbPhone,dbTeacherrequest,dbstudentrequest,dbAppDescription,dbAppStatus) values('"+ID+"','"+TNAME+"','"+Sid+"','"+Sname+"','"+Sphone+"','Teacher',0,'"+text+"', 'Meet me')";

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
                 tray.setMessage("Enter Free Schedule First");
                 tray.setNotificationType(notificationType);
                 tray.showAndDismiss(Duration.millis(4000));
        	}      
        }

    	
    
    
   /* @FXML
    private void setTeacherSelectClick(Event event){

        name.setText(teacherTableView.getSelectionModel().getSelectedItem().getTeacherTableDataName());
        id.setLayoutX(name.getLayoutX()+10+name.getBoundsInParent().getWidth());
        id.setText("("+teacherTableView.getSelectionModel().getSelectedItem().getTeacherTableDataID()+")");

        setSelectedID(teacherTableView.getSelectionModel().getSelectedItem().getTeacherTableDataID());
        setAllEnable();

    }*/

    private void setAllDisable(){
      
        teacherTableSecChoose.setDisable(true);
        
    }

    private void setAllEnable(){
      

        teacherTableSecChoose.setDisable(false);
        
    }

    private void setAllClear(){

    }


 /*   @FXML
    private void setTeacherCancelClick(Event event){
        setAllDisable();
        setAllClear();
        name.setText("Name");
        id.setLayoutX(name.getLayoutX()+10+name.getBoundsInParent().getWidth());
        id.setText("(ID)");
    }*/


    @FXML
    private void setCourseAboutButtonClick(Event event) throws IOException {
        menuBarControl.about();
    }

    @FXML
    private void setCourseCloseButtonClick(Event event){
        menuBarControl.close();
    }

   
    
}


  


