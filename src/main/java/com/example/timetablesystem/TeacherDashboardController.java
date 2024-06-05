package com.example.timetablesystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class TeacherDashboardController {
    @FXML
    public void ProfileButtonOnClick(ActionEvent actionEvent) {
    }

    @FXML
    public void TimetableButtonOnClick(ActionEvent actionEvent) {
    }

    public void CLassButtonOnClick(ActionEvent actionEvent) {
    }

    public void TeacherPanelOnClick(MouseEvent mouseEvent) {
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
}
