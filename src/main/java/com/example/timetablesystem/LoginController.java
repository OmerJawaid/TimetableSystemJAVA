package com.example.timetablesystem;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private Label WarningPassword;

    @FXML
    private Label WarningUsername;

    @FXML
    private Label WarningRole;

    @FXML
    private ComboBox<String> RoleCombobox;

    @FXML
    private TextField UsernameTextbox;

    @FXML
    private TextField PasswordTextbox;

    @FXML
    private Button SigninButton;

    @FXML
    private Button GoSignupButton;

    @FXML
    public void initialize() {
        // Add items to the RoleComboBox
        RoleCombobox.getItems().addAll("Student", "Teacher", "Admin");
    }

    DatabaseConnection con =new DatabaseConnection();

    @FXML
    void SigninButtonOnClick(ActionEvent event) {

        String Username = UsernameTextbox.getText();
        String Password = PasswordTextbox.getText();
        String Role = RoleCombobox.getValue();
        if(Username.isEmpty() || Password.isEmpty() || Role == null) {
            if(Username.isEmpty()) {
                WarningUsername.setVisible(true);
                WarningUsername.setText("Username is Empty");
            }
            if(Password.isEmpty()){
                WarningPassword.setVisible(true);
                WarningUsername.setText("Password is Empty");
            }
            if(Role==null) {
                WarningRole.setVisible(true);
                WarningRole.setText("Please Select Your Role");
            }
        }
        else{
            WarningUsername.setVisible(false);
            WarningPassword.setVisible(false);
            WarningRole.setVisible(false);
            if(Role.equals("Student"))
            {
                if(ValidateData(Username, Password,Role))
                {
                    showAlert("Login Sucessfull","Welcome");
                }
                else {
                    showAlert("Login Failed", "Invalid username or password");
                }
            }
            else if(Role.equals("Teacher"))
            {
                if(ValidateData(Username, Password,Role))
                {
                    showAlert("Login Sucessfull","Welcome");
                }
                else {
                    showAlert("Login Failed", "Invalid username or password");
                }
            }
            else
            {
                if(ValidateData(Username, Password,Role))
                {
                    showAlert("Login Sucessfull","Welcome");
                }
                else {
                    showAlert("Login Failed", "Invalid username or password");
                }
            }
        }
    }
    boolean ValidateData(String Username, String Password,String Role ) {
        if (Role.equals("Student")) {
            try (
                    Connection connection = con.getConnection();
                    PreparedStatement preparedStatement =
                            connection.prepareStatement("SELECT * FROM studentdata WHERE username = ? AND password = ?")
            ) {
                preparedStatement.setString(1, Username);
                preparedStatement.setString(2, Password);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.next();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (Role.equals("Teacher")) {
            try (
                    Connection connection = con.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM teacherdata WHERE username = ? AND password = ?")
            ) {
                preparedStatement.setString(1, Username);
                preparedStatement.setString(2, Password);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.next();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (
                    Connection connection = con.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM admindata WHERE username = ? AND password = ?")
            ) {
                preparedStatement.setString(1, Username);
                preparedStatement.setString(2, Password);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}