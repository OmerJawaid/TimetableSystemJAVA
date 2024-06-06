package com.example.timetablesystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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


public class StudentTimetableController {

    public static List<TimetableEntries> timetableEntries = new ArrayList<>();

    @FXML
    AnchorPane SectionTimetablePane;
    @FXML
    AnchorPane SectionNamePane;
    @FXML
    AnchorPane SectionTimetableShowPane;
    @FXML
    HBox SectiontimetableHBox;
    @FXML
    TextField SectionNameTextField;
    DatabaseConnection con = new DatabaseConnection();

void studentimetablegeneration() throws SQLException {
    String sql = "SELECT TeacherName, SectionName, CourseName, RoomId, TimeSlot FROM Timetable WHERE SectionName = ?";

    Connection connection = con.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, "BSE 2-A");
    ResultSet resultSet = preparedStatement.executeQuery();

    while (resultSet.next()) {
        String teacherName = resultSet.getString("TeacherName");
        String courseName = resultSet.getString("CourseName");
        String roomId = resultSet.getString("RoomId");
        String timeSlot = resultSet.getString("TimeSlot");
        TimetableEntries entry = new TimetableEntries("BSE 2-A", teacherName, courseName, roomId, timeSlot);
        timetableEntries.add(entry);
    }

    if (!timetableEntries.isEmpty()) {
        // Create columns for Teacher, Course, Room, and Time
        VBox teacherColumn = new VBox(5);
        VBox courseColumn = new VBox(5);
        VBox roomColumn = new VBox(5);
        VBox timeColumn = new VBox(5);

        // Add headers
        teacherColumn.getChildren().add(new Label("Teacher"));
        courseColumn.getChildren().add(new Label("Course"));
        roomColumn.getChildren().add(new Label("Room"));
        timeColumn.getChildren().add(new Label("Time"));

        // Add timetable entries
        for (TimetableEntries entry : timetableEntries) {
            teacherColumn.getChildren().add(new Label(entry.getTeacherName()));
            courseColumn.getChildren().add(new Label(entry.getCourseName()));
            roomColumn.getChildren().add(new Label(entry.getRoomId()));
            timeColumn.getChildren().add(new Label(entry.getTime()));
        }

        // Add columns to the HBox
        SectiontimetableHBox.getChildren().addAll(teacherColumn, courseColumn, roomColumn, timeColumn);
    } else {
        System.out.println("No timetable found for " + "BSE 2-A");
    }
}

    public void LogoutButtonOnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/com/example/timetablesystem/StudentDashboard.fxml"));
        Parent LoginParent =loader.load();
        StudentDashboardController LoginController =loader.getController();

        Scene LoginScene =new Scene(LoginParent);
        Stage window= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(LoginScene);
        window.show();
    }
}
