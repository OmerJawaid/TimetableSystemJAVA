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


public class AdminCourseController {

    @FXML
    AnchorPane AddSectionPane;
    @FXML
    AnchorPane NewCoursePane;
    @FXML
    TextField TeacherIdTextbox;
    @FXML
    TextField SectionNameTextbox;
    @FXML
    TextField CourseCodeTextbox;
    @FXML
    TextField CourseNameTextbox;
    @FXML
    ComboBox CourseNameCombobox;
    @FXML
    Label CourseCodeWarning;
    @FXML
    Label CourseNameWarning;
    @FXML
    Label TeacherIdWarning;
    @FXML
    Label SectionNameWarning;
    @FXML
    Label CourseNameCreateWarning;

    DatabaseConnection con =new DatabaseConnection();

    public void Starter()
    {
        CourseCodeWarning.setVisible(false);
        CourseNameWarning.setVisible(false);
        TeacherIdWarning.setVisible(false);
        SectionNameWarning.setVisible(false);
        CourseNameCreateWarning.setVisible(false);
        setCourseCodeCombobox();
    }

    //Back to Dashboard
    @FXML
    public void DashboardButtonOnclick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/timetablesystem/AdminDashboard.fxml"));
            Parent dashboardParent = loader.load();

            Scene dashboardScene = new Scene(dashboardParent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(dashboardScene);
            window.show();
        } catch (IOException e) {
            //Have to Implemented
        }
    }


    @FXML
    public void AddSectionButtonOnClick(ActionEvent actionEvent) {
        NewCoursePane.setVisible(false);
        AddSectionPane.setVisible(true);
        Starter();
    }

    //For Course Creation
    @FXML
    public void CreateCourseButtonOnClick(ActionEvent actionEvent) {
       NewCoursePane.setVisible(true);
       AddSectionPane.setVisible(false);
    }

    @FXML
    public void AddButtonOnClick(ActionEvent actionEvent) {
        if(!CourseNameCombobox.getSelectionModel().getSelectedItem().toString().isEmpty() || !TeacherIdTextbox.getText().isEmpty() || !SectionNameTextbox.getText().isEmpty()) {
            int CourseCodeInt = Integer.parseInt(CourseNameCombobox.getSelectionModel().getSelectedItem().toString());

            boolean SectionNameflag, TeacherIdflag;

            try{int TeacherIdInt = Integer.parseInt(TeacherIdTextbox.getText());

                //Check CourseCode entered is in Course Table or not
                try(Connection connection= con.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM section WHERE Name=?");)
                {
                    preparedStatement.setString(1, SectionNameTextbox.getText());
                    ResultSet resultSet = preparedStatement.executeQuery();
                    SectionNameflag=resultSet.next();
                }
                catch(Exception e)
                { showAlert("Not good",e.getMessage()); SectionNameflag=false;}

                //Check Teacher ID entered is in teacherdata Table or not
                try(Connection connection= con.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM teacherdata WHERE Id=?");)
                {
                    preparedStatement.setInt(1, TeacherIdInt);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    TeacherIdflag=resultSet.next();
                }
                catch(Exception e)
                { showAlert("Not good",e.getMessage()); TeacherIdflag=false;}

                if(TeacherIdflag && SectionNameflag) {
                    try (Connection connection = con.getConnection();
                         PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO courseteachersection(CourseCode, TeacherId, SectionName) VALUES (?,?,?)")) {
                        preparedStatement.setInt(1, CourseCodeInt);
                        preparedStatement.setInt(2, TeacherIdInt);
                        preparedStatement.setString(3, SectionNameTextbox.getText());
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    if(!TeacherIdflag){
                        TeacherIdWarning.setVisible(true);
                        TeacherIdWarning.setText("Teacher Not Registered");
                    } else if (!SectionNameflag) {
                        SectionNameWarning.setVisible(true);
                        SectionNameWarning.setText("Section Not Created");

                    }
                }
            }
            catch (Exception e){TeacherIdWarning.setVisible(true);TeacherIdWarning.setText("Teacher Id is not a number");}

        }
        else{
            if(CourseNameCombobox.getSelectionModel().getSelectedItem().toString().isEmpty()){
                CourseNameCreateWarning.setVisible(true);
                CourseNameCreateWarning.setText("Course Not Selected");
            }
            else if(TeacherIdTextbox.getText().isEmpty()){
                TeacherIdWarning.setVisible(true);
                TeacherIdWarning.setText("Teacher Id is Empty");
            }
            else if(SectionNameTextbox.getText().isEmpty()) {
                SectionNameWarning.setVisible(true);
                SectionNameWarning.setText("Section Name is Empty");
            }
        }
    }

    //Set Course Data to Combo Box
    private void setCourseCodeCombobox() {
        try (Connection connection = con.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("Select CourseCode From course");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String CourseCodeFromDB = resultSet.getString("CourseCode");
                CourseNameCombobox.getItems().add(CourseCodeFromDB);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    public void CreateCourseButtonPaneOnClick(ActionEvent actionEvent) {
        if(!CourseCodeTextbox.getText().isEmpty() || !CourseNameTextbox.getText().isEmpty()) {
            int CodeInteger = Integer.parseInt(CourseCodeTextbox.getText());
            String CourseName = CourseNameTextbox.getText();
            try (Connection connection = con.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO course (CourseCode, CourseName) VALUES (?,?)")) {
                preparedStatement.setInt(1, CodeInteger);
                preparedStatement.setString(2, CourseName);
                preparedStatement.executeUpdate();
                showAlert("Success", "Successfully Created new Course");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            if(CourseCodeTextbox.getText().isEmpty()){
                CourseCodeWarning.setVisible(true);
                CourseCodeWarning.setText("Course Code is Empty");
            }
            else if(CourseNameTextbox.getText().isEmpty()){
                CourseNameWarning.setVisible(true);
                CourseNameWarning.setText("Course Name is Empty");
            }
            else
            {
                showAlert("Went Wrong","Somthing went wrong in Create Course. None of these are empty but stil...");
            }
        }
    }
}
