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

    private String Username;

    @FXML
    private Label UsernameLabel;

    void SetUsername(String UsernameAssign){
        Username = UsernameAssign;
        if (UsernameLabel != null) {
            UsernameLabel.setText(Username);

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

    }

    public void SectionPaneOnClick(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/timetablesystem/AdminSection.fxml"));
            Parent signupParent = loader.load();
            AdminSectionController AdminSectionController = loader.getController();

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

            Scene signupScene = new Scene(signupParent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(signupScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
