package com.example.timetablesystem;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;

public class AdminDashboardController {

    @FXML
    private Label UsernameLabel;

    void SetUsername(String UsernameAssign){
        if (UsernameLabel != null) {
            UsernameLabel.setText(UsernameAssign);

        } else {
            System.err.println("UsernameLabel is null. Check the FXML file.");
        }
    }

    @FXML
    void LogoutButtonOnClick(ActionEvent event) throws IOException {

        FXMLLoader loader =new FXMLLoader(getClass().getResource("/com/example/timetablesystem/Login.fxml"));
        Parent LoginParent =loader.load();
        LoginController LoginController =loader.getController();

        Scene LoginScene =new Scene(LoginParent);
        Stage window= (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(LoginScene);
        window.show();
    }

    @FXML
    void ProfileButtonOnClick(ActionEvent event) {

    }

    @FXML
    void SignupButtonOnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/timetablesystem/Signup.fxml"));
            Parent signupParent = loader.load();
                SignupController SignupController = loader.getController();

            Scene signupScene = new Scene(signupParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(signupScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void StudentButtonOnClick(ActionEvent event) {

    }

    @FXML
    void TeacherButtonOnCLick(ActionEvent event) {

    }

    @FXML
    void TimetableButtonOnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/timetablesystem/Timetable.fxml"));
            Parent signupParent = loader.load();
            TimetableGenerator timetableGenerator = loader.getController();


            Scene signupScene = new Scene(signupParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(signupScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void SectionPaneOnClick(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/timetablesystem/AdminSection.fxml"));
            Parent signupParent = loader.load();
            AdminSectionController AdminSectionController = loader.getController();
            AdminSectionController.Starter();

            Scene signupScene = new Scene(signupParent);
            Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(signupScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void NewSectionButtonOnClick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/timetablesystem/AdminSection.fxml"));
            Parent signupParent = loader.load();
            AdminSectionController AdminSectionController = loader.getController();
            AdminSectionController.Starter();

            Scene signupScene = new Scene(signupParent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(signupScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AddStudentButtonOnClick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/timetablesystem/AdminSection.fxml"));
            Parent signupParent = loader.load();
            AdminSectionController AdminSectionController = loader.getController();
            AdminSectionController.NewSectionPane.setVisible(false);
            AdminSectionController.AddStudentPane.setVisible(true);
            AdminSectionController.Starter();

            Scene signupScene = new Scene(signupParent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(signupScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void NewCourseButtonOnClick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/timetablesystem/AdminCourse.fxml"));
            Parent newcourseParent = loader.load();
            AdminCourseController AdminCourseController = loader.getController();
            //AdminSectionController.Starter();

            Scene newcourseScene = new Scene(newcourseParent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(newcourseScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AddSectionButtonOnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/timetablesystem/AdminCourse.fxml"));
        Parent newcourseParent = loader.load();
        AdminCourseController AdminCourseController = loader.getController();
        AdminCourseController.Starter();
        AdminCourseController.NewCoursePane.setVisible(false);
        AdminCourseController.AddSectionPane.setVisible(true);


        Scene newcourseScene = new Scene(newcourseParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(newcourseScene);
        window.show();
    }


    public void AddTeacherButtonOnClick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/timetablesystem/AdminCourse.fxml"));
            Parent newcourseParent = loader.load();
            AdminCourseController AdminCourseController = loader.getController();
            AdminCourseController.NewCoursePane.setVisible(false);
            AdminCourseController.AddSectionPane.setVisible(true);
            AdminCourseController.Starter();


            Scene newcourseScene = new Scene(newcourseParent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(newcourseScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CoursePaneOnClick(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/timetablesystem/AdminCourse.fxml"));
            Parent newcourseParent = loader.load();
            AdminCourseController AdminCourseController = loader.getController();
            //AdminSectionController.Starter();

            Scene newcourseScene = new Scene(newcourseParent);
            Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(newcourseScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
