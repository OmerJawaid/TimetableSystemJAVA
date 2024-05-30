package com.example.timetablesystem;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminSectionController {
    @FXML
    AnchorPane AddStudentPane;
    @FXML
    AnchorPane NewSectionPane;
    @FXML
    TextField SectionNameTextbox;
    @FXML
    TextField StudentCapacityTextbox;
    @FXML
    Label SectionNameWarning;
    @FXML
    Label StudentCapacityWarning;
    @FXML
    Label StudentSectionNameWarning;
    @FXML
    Label StudentIdWarning;
    @FXML
    TextField StudentIdTextbox;
    @FXML
    ComboBox SectionNameCombobox;

    DatabaseConnection con =new DatabaseConnection();
    public void DashboardButtonOnclick(ActionEvent actionEvent) {
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

    public void NewSectionButtonOnClick(ActionEvent actionEvent) {
        NewSectionPane.setVisible(true);
        AddStudentPane.setVisible(false);
    }

    public void AddStudentButtonOnClick(ActionEvent actionEvent) {
        NewSectionPane.setVisible(false);
        AddStudentPane.setVisible(true);
    }

    public void CreateSectionButtonOnClick(ActionEvent actionEvent) {

        if(!SectionNameTextbox.getText().isEmpty()&& !StudentCapacityTextbox.getText().isEmpty()) {
            try(Connection connection= con.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO section  (Name, NumofStudent ) VALUES (?,?)" );){
                preparedStatement.setString(1, SectionNameTextbox.getText());
                preparedStatement.setInt(2,StudentCapacityTextbox.getText());
                preparedStatement.executeUpdate();
                showAlert("Success", "Successfully Created Section");
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            if(SectionNameTextbox.getText().isEmpty())
            {SectionNameWarning.setVisible(true); SectionNameWarning.setText("Section Name is Empty");}
            else if(StudentCapacityTextbox.getText().isEmpty())
            {StudentCapacityWarning.setVisible(true);StudentCapacityWarning.setText("Student Capacity is Empty");}
        }
    }

    public void AddButtonOnClick(ActionEvent actionEvent) {
        if(!StudentIdTextbox.getText().isEmpty()&& !SectionNameCombobox.getSelectionModel().isEmpty()) {

        }
        else {
            if(StudentIdTextbox.getText().isEmpty())
            {StudentIdWarning.setVisible(true); StudentIdWarning.setText("Student ID is Empty");}
            else if(SectionNameCombobox.getSelectionModel().isEmpty())
            {SectionNameWarning.setVisible(true);SectionNameWarning.setText("Section not Selected");}
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
