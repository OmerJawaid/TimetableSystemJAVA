package com.example.timetablesystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDashboardController {
    String Username;
    void setUsername(String username){Username=username;}


    @FXML
    Button YoueTimetable;
    @FXML
    public void ProfileButtonOnClick(ActionEvent actionEvent) {
    }

    @FXML
    public void TimetableButtonOnClick(ActionEvent actionEvent) throws IOException {
        try {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/com/example/timetablesystem/TeacherTimetable.fxml"));
        Parent TeachertimetableParent =loader.load();
        TeachertimetableController TeacherTimetableController =loader.getController();
        TeacherTimetableController.setUsername(Username);
        TeacherTimetableController.TImetbaleGeneration();

        Scene TeachertimetableScene =new Scene(TeachertimetableParent);
        Stage window= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(TeachertimetableScene);
        window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void CLassButtonOnClick(ActionEvent actionEvent) {
    }

    public void TeacherPanelOnClick(MouseEvent mouseEvent) throws IOException {
        try {
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/com/example/timetablesystem/TeacherTimetable.fxml"));
            Parent TeachertimetableParent =loader.load();
            TeachertimetableController TeacherTimetableController =loader.getController();
            TeacherTimetableController.setUsername(Username);
            TeacherTimetableController.TImetbaleGeneration();

            Scene TeachertimetableScene =new Scene(TeachertimetableParent);
            Stage window= (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(TeachertimetableScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void ClassButtonOnClick(ActionEvent actionEvent) {
    }

    public void LogoutButtonOnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/com/example/timetablesystem/Login.fxml"));
        Parent LoginParent =loader.load();
        LoginController LoginController =loader.getController();

        Scene LoginScene =new Scene(LoginParent);
        Stage window= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(LoginScene);
        window.show();
    }

    DatabaseConnection con=new DatabaseConnection();
}
