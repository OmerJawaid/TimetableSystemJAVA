package com.example.timetablesystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TeacherClassController {

    DatabaseConnection con =new DatabaseConnection();

    @FXML
    TextField SectionTextField;
    TextField DateTextField;
    TextField RoomTextField;

    void StudentDataGetter()
    {
        Vector<String>Section=new Vector<>(100);
       // Vector<int> StudentId=new Vector<int>(100);
        String SectionName = SectionTextField.getText();
        try (Connection connection = con.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("Select Section From studentsectionassociation");
             ResultSet resultSet = preparedStatement.executeQuery();) {
            while (resultSet.next()) {
                String SectionNametemp = resultSet.getString("SectionName");
         //       Courses.add(course1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void DashboardButtonOnClick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/timetablesystem/TeacherDashboard.fxml"));
            Parent dashboardParent = loader.load();
            TeacherDashboardController teacherController = loader.getController();

            Scene dashboardScene = new Scene(dashboardParent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(dashboardScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}