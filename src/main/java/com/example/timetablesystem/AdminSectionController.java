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
import java.sql.*;

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

    //For all Warnings Reset
    void Starter(){
        StudentCapacityWarning.setVisible(false);
        StudentIdWarning.setVisible(false);
        SectionNameWarning.setVisible(false);
        StudentSectionNameWarning.setVisible(false);
        AddtoComboBoxSection();
    }

    DatabaseConnection con =new DatabaseConnection();

    //Return to Dashboard
    @FXML
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


    //Pane Changes on button
    @FXML
    public void NewSectionButtonOnClick(ActionEvent actionEvent) {
        NewSectionPane.setVisible(true);
        AddStudentPane.setVisible(false);
    }

    @FXML
    public void AddStudentButtonOnClick(ActionEvent actionEvent) {
        NewSectionPane.setVisible(false);
        AddStudentPane.setVisible(true);
        AddtoComboBoxSection();
    }


    //Main Button Operations
    @FXML
    public void CreateSectionButtonOnClick(ActionEvent actionEvent) {

        if(!SectionNameTextbox.getText().isEmpty()&& !StudentCapacityTextbox.getText().isEmpty()) {

            //Adding a new Section
            try {
                String text = StudentCapacityTextbox.getText();
                int intValue = Integer.parseInt(text);
                try(Connection connection= con.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO section  (Name, NumofStudent ) VALUES (?,?)" );){
                    preparedStatement.setString(1, SectionNameTextbox.getText());
                    preparedStatement.setInt(2,intValue);
                    preparedStatement.executeUpdate();
                    showAlert("Success", "Successfully Created Section");
                }
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }catch(Exception e)
            {StudentCapacityWarning.setVisible(true); StudentCapacityWarning.setText("Enter Integer Value");}

        }
        else {
            //If any Textbox is Empty
            if(SectionNameTextbox.getText().isEmpty())
            {SectionNameWarning.setVisible(true); SectionNameWarning.setText("Section Name is Empty");}
            else if(StudentCapacityTextbox.getText().isEmpty())
            {StudentCapacityWarning.setVisible(true);StudentCapacityWarning.setText("Student Capacity is Empty");}
        }
    }

    public void AddButtonOnClick(ActionEvent actionEvent) {
        //None is Empty
        if (!StudentIdTextbox.getText().isEmpty() && !SectionNameCombobox.getSelectionModel().isEmpty()) {
            try {

                //Student ID Textbox text to integer
                int intValue = Integer.parseInt(StudentIdTextbox.getText());
                String Sectionnamestring=SectionNameCombobox.getSelectionModel().getSelectedItem().toString();
                boolean flag;

                //Check Student entered is in Student Table or not
                {
                    try(Connection connection= con.getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM studentdata WHERE Id=?");)
                    {
                        preparedStatement.setInt(1, intValue);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        flag=resultSet.next();
                    }
                    catch(Exception e)
                    { showAlert("Not good",e.getMessage()); flag=false;}
                }

                //Insert to sql association of student section
                if(flag) {
                    try (Connection connection = con.getConnection();
                         PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO studentsectionassociation (SectionName, StudentId) VALUES (?,?)");) {
                        preparedStatement.setString(1, Sectionnamestring);
                        preparedStatement.setInt(2, intValue);
                        preparedStatement.executeUpdate();
                        showAlert("Success", "Successfully Added Student to Section");
                    } catch (Exception e) {
                        showAlert("Database Issue", e.getMessage());
                    }
                }
                else
                {showAlert("Student Not there","The Student Id is invalid");}
            } catch (Exception e) {
                StudentIdWarning.setVisible(true);
                StudentIdWarning.setText("Student Id is not a Number");
            }
        }
        else {
            //If any textbox is empty
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

    //Add Data to Combo Box
    void AddtoComboBoxSection() {
        try (
                Connection connection = con.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT Name FROM section");
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            //CLear all Items in Section Combo box
            SectionNameCombobox.getItems().clear();

            while (resultSet.next()) {
                String nameFromDB = resultSet.getString("Name");
                SectionNameCombobox.getItems().add(nameFromDB);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
