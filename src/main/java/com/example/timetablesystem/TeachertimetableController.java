package com.example.timetablesystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

public class TeachertimetableController {

    @FXML
    private HBox GeneratorPane;

    private String Username;

    public void setUsername(String username) {
        Username = username;
    }

    private DatabaseConnection con = new DatabaseConnection();

    public void LogoutButtonOnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/timetablesystem/TeacherDashboard.fxml"));
        Parent loginParent = loader.load();
        TeacherDashboardController loginController = loader.getController();
        loginController.setUsername(Username); // Correctly setting the username
        Scene loginScene = new Scene(loginParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    public void TImetbaleGeneration() {
        List<TimetableEntries> timetableEntries = new ArrayList<>();
        String sql = "SELECT SectionName, TeacherName, CourseName, RoomId, TimeSlot FROM Timetable WHERE TeacherName = ?";

        try (Connection connection = con.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, Username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String sectionName = resultSet.getString("SectionName");
                String courseName = resultSet.getString("CourseName");
                String roomId = resultSet.getString("RoomId");
                String timeSlot = resultSet.getString("TimeSlot");
                TimetableEntries entry = new TimetableEntries(sectionName, Username, courseName, roomId, timeSlot);
                timetableEntries.add(entry);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!timetableEntries.isEmpty()) {
            // Create columns for Section, Course, Room, and Time
            VBox sectionColumn = new VBox(7);
            VBox courseColumn = new VBox(7);
            VBox roomColumn = new VBox(7);
            VBox timeColumn = new VBox(7);

            // Add headers
            sectionColumn.getChildren().add(new Label("Section"));
            courseColumn.getChildren().add(new Label("Course"));
            roomColumn.getChildren().add(new Label("Room"));
            timeColumn.getChildren().add(new Label("Time"));

            // Add timetable entries
            for (TimetableEntries entry : timetableEntries) {
                sectionColumn.getChildren().add(new Label(entry.getSectionName()));
                courseColumn.getChildren().add(new Label(entry.getCourseName()));
                roomColumn.getChildren().add(new Label(entry.getRoomId()));
                timeColumn.getChildren().add(new Label(entry.getTime()));
            }

            // Clear previous content and add new columns to the GeneratorPane
            GeneratorPane.getChildren().clear();
            GeneratorPane.getChildren().addAll(sectionColumn, courseColumn, roomColumn, timeColumn);
        } else {
            System.out.println("No timetable found for " + Username);
        }
    }
}
