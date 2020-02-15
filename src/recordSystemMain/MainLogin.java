package recordSystemMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Date: 10-Dec-2018
 * This is Main class for the Student Teacher Appointment System for appointment makings
 * @Author Project Red Team
 * @Version 1.0
 * 
 */

public class MainLogin extends Application {

    /**
     * @param args array of string arguments
     * This basically initiates and sets up the launch environment
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainLogin.fxml"));
        primaryStage.setTitle("STUDENT TEACHER APPOINTMENT SYSTEM");
        primaryStage.setScene(new Scene(root, 1200, 600));
        primaryStage.show();
    }



}
