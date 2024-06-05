package com.example.timetablesystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentDashboardController {
    public void AttendanceButtonOnClick(ActionEvent actionEvent) {
    }

    public void TimetableButtonOnClick(ActionEvent actionEvent) {
    }

    public void YourTimetableButtonOnClick(ActionEvent actionEvent) {
    }

    public void TeacherTimetableButtonOnClick(ActionEvent actionEvent) {
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

    public void TimetablePaneOnClick(MouseEvent mouseEvent) {
    }
}
