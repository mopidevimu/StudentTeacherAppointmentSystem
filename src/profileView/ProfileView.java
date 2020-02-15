package profileView;

import database.DBConnection;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sun.tracing.NullProviderFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Project_Red
 *This class deals about displaying profile view of students on request
 */
public class ProfileView {

    @FXML
    private ImageView profilePic;

    @FXML
    Text tID;
    @FXML
    Text tName;
    @FXML
    Text tDepartment;
    @FXML
    Text tDOB;
    @FXML
    Text tVEmail;
    @FXML
    Text tPhone;
    @FXML
    Text tAddress;
  
    DBConnection database = new DBConnection();
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    
    private Info currentInfo;

    /**
     * @param currentInfo
     * This method retrieve's data's from the database to populate in profile view window
     */
    public void setCurrentInfo(Info currentInfo) {
        tID.setText(currentInfo.getId());
        tName.setText(currentInfo.getName());
        tDepartment.setLayoutX(tName.getLayoutX()+10+tName.getBoundsInParent().getWidth());
        tDepartment.setText(currentInfo.getDepartment());
        tVEmail.setText(currentInfo.getVemail());
        tDOB.setText(currentInfo.getDob());
        
        tPhone.setText(currentInfo.getPhone());

        try {
            Image image = new Image("/image/"+currentInfo.getfullId()+".jpg");
            profilePic.setImage(image);
        }
        catch (Exception e){
            profilePic.setImage(new Image("/image/default-user-icon.png"));
        }

        this.currentInfo = currentInfo;

    }


}
