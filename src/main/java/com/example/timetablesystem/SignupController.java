package com.example.timetablesystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.EventObject;
import javafx.scene.control.Alert;
import java.io.IOException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignupController {


    //Declaration of Elements in Forms
    @FXML
    private AnchorPane AddStudentForm;
    @FXML
    private AnchorPane AddTeacherForm;
    @FXML
    private AnchorPane AddAdminForm;
    @FXML
    private Label AddStudentLabel;
    @FXML
    private Label AddTeacherLabel;
    @FXML
    private Label AddAdminLabel1;
    @FXML
    private TextField TeacherNameTextbox;
    @FXML
    private TextField TeacherEmailTextbox;
    @FXML
    private TextField TeacherUsernameTextbox;
    @FXML
    private PasswordField TeacherPasswordPasswordbox;
    @FXML
    private PasswordField TeacherRePasswordPasswordbox;
    @FXML
    private TextField StudentNameTextbox;
    @FXML
    private TextField StudentEmailTextbox;
    @FXML
    private TextField StudentUsernameTextbox;
    @FXML
    private PasswordField StudentPasswordPasswordbox;
    @FXML
    private PasswordField StudentRePasswordPasswordbox;
    @FXML
    private Label StudentPasswordWarning;
    @FXML
    private Label StudentRePasswordWarning;
    @FXML
    private Label StudentNameWarning;
    @FXML
    private Label StudentEmailWarning;
    @FXML
    private Label StudentUsernameWarning;
    @FXML
    private Label TeacherPasswordWarning;
    @FXML
    private Label TeacherRePasswordWarning;
    @FXML
    private Label TeacherNameWarning;
    @FXML
    private Label TeacherEmailWarning;
    @FXML
    private Label TeacherUsernameWarning;
    @FXML
    private Label AdminPasswordWarning1;
    @FXML
    private Label AdminRePasswordWarning1;
    @FXML
    private Label AdminNameWarning1;
    @FXML
    private Label AdminEmailWarning1;
    @FXML
    private Label AdminUsernameWarning1;
    @FXML
    private TextField AdminNameTextbox1;
    @FXML
    private TextField AdminEmailTextbox1;
    @FXML
    private TextField AdminUsernameTextbox1;
    @FXML
    private PasswordField AdminPasswordPasswordbox1;
    @FXML
    private PasswordField AdminRePasswordPasswordbox1;


    //On Signup Click
    public void handleSignupButtonClick(ActionEvent actionEvent) {
        //Evaluating Labels for adding teacher or student
        DatabaseConnection con =new DatabaseConnection();
        if(AddStudentLabel.isVisible()) {
            //Hiding Warning labels
            StudentNameWarning.setVisible(false);
            StudentEmailWarning.setVisible(false);
            StudentUsernameWarning.setVisible(false);
            StudentPasswordWarning.setVisible(false);
            StudentRePasswordWarning.setVisible(false);
            //Check for any empty textbox
            if(StudentNameTextbox.getText().isEmpty() || StudentEmailTextbox.getText().isEmpty() || StudentUsernameTextbox.getText().isEmpty() || StudentPasswordPasswordbox.getText().isEmpty() || StudentRePasswordPasswordbox.getText().isEmpty()) {
                //If any is Empty
                if(StudentNameTextbox.getText().isEmpty()){StudentNameWarning.setText("Name is Empty");StudentNameWarning.setVisible(true);}
                else if(StudentEmailTextbox.getText().isEmpty()){StudentEmailTextbox.setText("Email is Empty");StudentEmailWarning.setVisible(true);}
                else if(StudentUsernameTextbox.getText().isEmpty()){StudentUsernameTextbox.setText("Username is Empty");StudentUsernameWarning.setVisible(true);}
                else if(StudentPasswordPasswordbox.getText().isEmpty()){StudentPasswordPasswordbox.setText("Password is Empty");StudentPasswordWarning.setVisible(true);}
                else if(StudentRePasswordPasswordbox.getText().isEmpty()){StudentRePasswordPasswordbox.setText("Repassword is Empty");StudentRePasswordWarning.setVisible(true);}
            }
            else {
                //Reenter password checking is same \ not
                if(StudentRePasswordPasswordbox.getText().equals(StudentPasswordPasswordbox.getText())) {

                    try(
                        Connection connection= con.getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO studentdata (Name, Email, Username, Password) VALUES (?, ?, ?, ?)" );
                    )
                    {
                        preparedStatement.setString(1, StudentNameTextbox.getText());
                        preparedStatement.setString(2, StudentEmailTextbox.getText());
                        preparedStatement.setString(3, StudentUsernameTextbox.getText());
                        preparedStatement.setString(4, StudentPasswordPasswordbox.getText());
                        preparedStatement.executeUpdate();
                        showAlert("Sucess", "Sucessfully Added Student");
                    } catch (SQLException e) {
                        showAlert("Failed To Add", e.getMessage());
                        throw new RuntimeException(e);
                    }
                }
                else {
                    //Is not matches
                    StudentRePasswordWarning.setVisible(true);
                    StudentRePasswordWarning.setText("Not Matches to Password");
                }
            }
        }
        else if(AddTeacherLabel.isVisible()) {
            //Hiding Warning labels
            TeacherNameWarning.setVisible(false);
            TeacherEmailWarning.setVisible(false);
            TeacherUsernameWarning.setVisible(false);
            TeacherPasswordWarning.setVisible(false);
            TeacherRePasswordWarning.setVisible(false);
            //Check for any empty textbox
            if(TeacherNameTextbox.getText().isEmpty() || TeacherEmailTextbox.getText().isEmpty() || TeacherUsernameTextbox.getText().isEmpty() || TeacherPasswordPasswordbox.getText().isEmpty() || TeacherRePasswordPasswordbox.getText().isEmpty()) {
                //If any is Empty
                if(TeacherNameTextbox.getText().isEmpty()){TeacherNameWarning.setText("Name is Empty");TeacherNameWarning.setVisible(true);}
                else if(TeacherEmailTextbox.getText().isEmpty()){TeacherEmailTextbox.setText("Email is Empty");TeacherEmailWarning.setVisible(true);}
                else if(TeacherUsernameTextbox.getText().isEmpty()){TeacherUsernameTextbox.setText("Username is Empty");TeacherUsernameWarning.setVisible(true);}
                else if(TeacherPasswordPasswordbox.getText().isEmpty()){TeacherPasswordPasswordbox.setText("Password is Empty");TeacherPasswordWarning.setVisible(true);}
                else if(TeacherRePasswordPasswordbox.getText().isEmpty()){TeacherRePasswordPasswordbox.setText("Repassword is Empty");TeacherRePasswordWarning.setVisible(true);}
            }
            else {
                //Reenter password checking is same \ not
                if(TeacherRePasswordPasswordbox.getText().equals(TeacherPasswordPasswordbox.getText())) {

                    try(
                            Connection connection= con.getConnection();
                            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO teacherdata (Name, Email, Username, Password) VALUES (?, ?, ?, ?)" );
                    )
                    {
                        preparedStatement.setString(1, TeacherNameTextbox.getText());
                        preparedStatement.setString(2, TeacherEmailTextbox.getText());
                        preparedStatement.setString(3, TeacherUsernameTextbox.getText());
                        preparedStatement.setString(4, TeacherPasswordPasswordbox.getText());
                        preparedStatement.executeUpdate();
                        showAlert("Sucess", "Sucessfully Added Teacher");
                    } catch (SQLException e) {
                        showAlert("Failed To Add", e.getMessage());
                        throw new RuntimeException(e);
                    }
                }
                else {
                    //Is not matches
                    TeacherRePasswordWarning.setVisible(true);
                    TeacherRePasswordWarning.setText("Not Matches to Password");
                }
            }
        }
        else if(AddAdminLabel1.isVisible()) {
            //Hiding Warning labels
            AdminNameWarning1.setVisible(false);
            AdminEmailWarning1.setVisible(false);
            AdminUsernameWarning1.setVisible(false);
            AdminPasswordWarning1.setVisible(false);
            AdminRePasswordWarning1.setVisible(false);
            //Check for any empty textbox
            if(AdminNameTextbox1.getText().isEmpty() || AdminEmailTextbox1.getText().isEmpty() || AdminUsernameTextbox1.getText().isEmpty() || AdminPasswordPasswordbox1.getText().isEmpty() || AdminRePasswordPasswordbox1.getText().isEmpty()) {
                //If any is Empty
                if(AdminNameTextbox1.getText().isEmpty()){AdminNameWarning1.setText("Name is Empty");AdminNameWarning1.setVisible(true);}
                else if(AdminEmailTextbox1.getText().isEmpty()){AdminEmailWarning1.setText("Email is Empty");AdminEmailWarning1.setVisible(true);}
                else if(AdminUsernameTextbox1.getText().isEmpty()){AdminUsernameWarning1.setText("Username is Empty");AdminUsernameWarning1.setVisible(true);}
                else if(AdminPasswordPasswordbox1.getText().isEmpty()){AdminPasswordWarning1.setText("Password is Empty");AdminPasswordWarning1.setVisible(true);}
                else if(AdminRePasswordPasswordbox1.getText().isEmpty()){AdminRePasswordWarning1.setText("Repassword is Empty");AdminRePasswordWarning1.setVisible(true);}
            }
            else {
                //Reenter password checking is same \ not
                if(AdminRePasswordPasswordbox1.getText().equals(AdminPasswordPasswordbox1.getText())) {

                    try(
                            Connection connection= con.getConnection();
                            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO admindata (Name, Email, Username, Password) VALUES (?, ?, ?, ?)" );
                    )
                    {
                        preparedStatement.setString(1, AdminNameTextbox1.getText());
                        preparedStatement.setString(2, AdminEmailTextbox1.getText());
                        preparedStatement.setString(3, AdminUsernameTextbox1.getText());
                        preparedStatement.setString(4, AdminPasswordPasswordbox1.getText());
                        preparedStatement.executeUpdate();
                        showAlert("Sucess", "Sucessfully Added Teacher");
                    } catch (SQLException e) {
                        showAlert("Failed To Add", e.getMessage());
                        throw new RuntimeException(e);
                    }
                }
                else {
                    //Is not matches
                    AdminRePasswordWarning1.setVisible(true);
                    AdminRePasswordWarning1.setText("Not Matches to Password");
                }
            }
        }
        else {
           showAlert("No Label Shown","Check the addteacherlabel or addstudentlabel");
        }
    }

    //On Student Click
    public void handleStudentButtonClick(ActionEvent actionEvent) {
        AddStudentForm.setVisible(true);
        AddTeacherForm.setVisible(false);
        AddStudentLabel.setVisible(true);
        AddTeacherLabel.setVisible(false);
        AddAdminLabel1.setVisible(false);
        AddAdminForm.setVisible(false);
    }

    //On Teacher Click
    public void handleTeacherButtonClick(ActionEvent actionEvent) {
        AddStudentForm.setVisible(false);
        AddTeacherForm.setVisible(true);
        AddStudentLabel.setVisible(false);
        AddTeacherLabel.setVisible(true);
        AddAdminLabel1.setVisible(false);
        AddAdminForm.setVisible(false);

    }

    //On Admin CLick
    public void handleAdminButtonClick(ActionEvent actionEvent) {
        AddStudentForm.setVisible(false);
        AddTeacherForm.setVisible(false);
        AddStudentLabel.setVisible(false);
        AddTeacherLabel.setVisible(false);
        AddAdminLabel1.setVisible(true);
        AddAdminForm.setVisible(true);
    }

    //On Dashboard Click back to Dashboard
    public void DashboardButtonOnClick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/timetablesystem/AdminDashboard.fxml"));
            Parent dashboardParent = loader.load();
            AdminDashboardController adminController = loader.getController();

            Scene dashboardScene = new Scene(dashboardParent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(dashboardScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Alert Function
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
