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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TimetableGenerator {
    private final Vector<Student> Students = new Vector<>(100);
    private static final List<Teacher> Teachers = new ArrayList<>();
    private static final List<Section> Sections = new ArrayList<>();
    private static final List<Course> Courses = new ArrayList<>();
    private static final List<Room> Rooms = new ArrayList<>();
    private final DatabaseConnection con =new DatabaseConnection();
    public static Map<String, Set<String>> teacherTimetable = new HashMap<>();
    public static Map<String, Set<String>> sectionTimetable = new HashMap<>();
    public static List<TimetableEntries> timetableEntries = new ArrayList<>();

    //FXML
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

    @FXML
    AnchorPane TeacherTimetablePane;
    @FXML
    AnchorPane GeneratorPane;
    @FXML
    AnchorPane TeacherNamePane;
    @FXML
    AnchorPane TeacherTimetableShowPane;
    @FXML
    HBox timetableHBox;
    @FXML
    TextField TeacherNameTextField;


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

    public void SectionButtonPaneOnClick(ActionEvent actionEvent) {
        SectionTimetablePane.setVisible(true);
        GeneratorPane.setVisible(false);
        TeacherTimetablePane.setVisible(false);
        TeacherNamePane.setVisible(false);
        TeacherTimetableShowPane.setVisible(false);
        SectionNamePane.setVisible(true);
        SectionTimetableShowPane.setVisible(false);
    }

    public void TeacherButtonOnClick(ActionEvent actionEvent) {
        SectionTimetablePane.setVisible(false);
        GeneratorPane.setVisible(false);
        TeacherTimetablePane.setVisible(true);
        TeacherNamePane.setVisible(true);
        TeacherTimetableShowPane.setVisible(false);
        SectionNamePane.setVisible(false);
        SectionTimetableShowPane.setVisible(false);
    }

    public void GeneratorButtonPaneOnClick(ActionEvent actionEvent) {
        SectionTimetablePane.setVisible(false);
        GeneratorPane.setVisible(true);
        TeacherTimetablePane.setVisible(false);
        SectionNamePane.setVisible(false);
        SectionTimetableShowPane.setVisible(false);
        DAL();
    }

    public void GenerateButtonPaneOnClick(ActionEvent actionEvent) {
        DAL();
    }

    public void SaveButtonOnClick(ActionEvent actionEvent) {
        String sql = "INSERT INTO Timetable (SectionName, TeacherName, CourseName, RoomId, TimeSlot) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = con.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (TimetableEntries entry : timetableEntries) {
                preparedStatement.setString(1, entry.getSectionName());
                preparedStatement.setString(2, entry.getTeacherName());
                preparedStatement.setString(3, entry.getCourseName());
                preparedStatement.setString(4, entry.getRoomId());
                preparedStatement.setString(5, entry.getTime());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void TeacherTimetableShowButtonPaneOnClick(ActionEvent actionEvent) {
        TeacherNamePane.setVisible(false);
        TeacherTimetableShowPane.setVisible(true);
        String TeacherName=TeacherNameTextField.getText();

        timetableHBox.getChildren().clear();
        List<TimetableEntries> timetableEntries = new ArrayList<>();
        String sql = "SELECT SectionName, TeacherName, CourseName, RoomId, TimeSlot FROM Timetable WHERE TeacherName = ?";

        try (Connection connection = con.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, TeacherName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String sectionName = resultSet.getString("SectionName");
                String courseName = resultSet.getString("CourseName");
                String roomId = resultSet.getString("RoomId");
                String timeSlot = resultSet.getString("TimeSlot");
                TimetableEntries entry = new TimetableEntries(sectionName, TeacherName, courseName, roomId, timeSlot);
                timetableEntries.add(entry);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!timetableEntries.isEmpty()) {
            // Create columns for Section, Course, Room, and Time
            VBox sectionColumn = new VBox(5);
            VBox courseColumn = new VBox(5);
            VBox roomColumn = new VBox(5);
            VBox timeColumn = new VBox(5);

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

            // Add columns to the HBox
            timetableHBox.getChildren().addAll(sectionColumn, courseColumn, roomColumn, timeColumn);
        } else {
            System.out.println("No timetable found for " + TeacherName);
        }
    }

    public void SectionTimetableShowButtonPaneOnClick(ActionEvent actionEvent) {
        SectionNamePane.setVisible(false);
        SectionTimetableShowPane.setVisible(true);
        SectiontimetableHBox.setVisible(true);

        String sectionName = SectionNameTextField.getText();
        SectiontimetableHBox.getChildren().clear();
        List<TimetableEntries> timetableEntries = new ArrayList<>();
        String sql = "SELECT TeacherName, SectionName, CourseName, RoomId, TimeSlot FROM Timetable WHERE SectionName = ?";

        try (Connection connection = con.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, sectionName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String teacherName = resultSet.getString("TeacherName");
                String courseName = resultSet.getString("CourseName");
                String roomId = resultSet.getString("RoomId");
                String timeSlot = resultSet.getString("TimeSlot");
                TimetableEntries entry = new TimetableEntries(sectionName,teacherName, courseName, roomId, timeSlot);
                timetableEntries.add(entry);
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
            System.out.println("No timetable found for " + sectionName);
        }
    }


    public void DAL() {
        //Data from teacher table
        try (Connection connection = con.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT Id, Name, Email from teacherdata");
             ResultSet resultSet = preparedStatement.executeQuery();) {
            while (resultSet.next()) {
                int TeacherId = resultSet.getInt("Id");
                String TeacherName= resultSet.getString("Name");
                String TeacherEmail = resultSet.getString("Email");
                Teacher teacher= new Teacher(TeacherName,TeacherId, TeacherEmail);
                Teachers.add(teacher);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Data from student table
        try (Connection connection = con.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT Id, Name, Email from studentdata");
             ResultSet resultSet = preparedStatement.executeQuery();) {
            while (resultSet.next()) {
                int StudentId = resultSet.getInt("Id");
                String StudentName= resultSet.getString("Name");
                String StudentEmail = resultSet.getString("Email");
                Student student= new Student(StudentName,StudentId, StudentEmail);
                Students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = con.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("Select CourseCode,CourseName From course");
             ResultSet resultSet = preparedStatement.executeQuery();) {
            while (resultSet.next()) {
                int courseCode = resultSet.getInt("CourseCode");
                String courseName= resultSet.getString("CourseName");
                Course course1= new Course(courseName,courseCode);
                Courses.add(course1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = con.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT Name,NumofStudent From section");
             ResultSet resultSet = preparedStatement.executeQuery();) {
            while (resultSet.next()) {
                Section section= new Section();
                section.SetNoofStudents(resultSet.getInt("NumofStudent"));
                section.SetSectionName(resultSet.getString("Name"));
                Sections.add(section);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Rooms.add(new Room("4-01",70, Arrays.asList("Mon 9-30", "Tue 1-30", "Wed 8-30", "Thurs 12-30","Thurs 7-30","Fri 12-30" )));
        Rooms.add(new Room("4-02",60, Arrays.asList("Fri 8-30", "Wed 9:30")));
        Rooms.add(new Room("4-03",50, Arrays.asList("Tue 12-30", "Fri 1-30")));
        Rooms.add(new Room("4-04",50, Arrays.asList("Thur 9-30", "Tue 11-30")));
        Rooms.add(new Room("4-14",50, Arrays.asList("Thur 8-30", "Tue 11-30")));
        Rooms.add(new Room("4-15",50, Arrays.asList("Thur 9-30", "Tue 11-30")));
        Rooms.add(new Room("4-13",50, Arrays.asList("Thur 9-30", "Tue 11-30")));
        Rooms.add(new Room("4-05",50, Arrays.asList("Thur 9-30", "Tue 11-30")));
        Rooms.add(new Room("4-06",50, Arrays.asList("Thur 9-30", "Tue 11-30")));


        Random random = new Random();
        scheduleTimetable(random);
        String specificTeacherName = "Tamim"; // Replace with the name of the specific teacher
        displayTeacherTimetable(specificTeacherName);

        // Display the timetable for a specific section
        String specificSectionName = "BSE 2-A"; // Replace with the name of the specific section
        displaySectionTimetable(specificSectionName);
    }

    //Timetable Generation
    private static void scheduleTimetable(Random random) {
        Map<String, Set<String>> teacherSchedule = new HashMap<>();
        Map<String, Set<String>> roomSchedule = new HashMap<>();
        Map<String, Set<String>> courseSchedule = new HashMap<>();

        for (Teacher teacher : Teachers) {
            for (Course course : Courses) {
                if (course == null) {
                    System.out.println("Null course found in course list, skipping...");
                    continue;
                }
                for (String time : getAllAvailableTimes(Rooms)) {
                    for (Section section : Sections) {
                        Room room = Rooms.get(random.nextInt(Rooms.size()));

                        if (section.GetNoOgStudents() <= room.Capacity) {
                            if (isTimeAvailable(teacher.GetName(), time, teacherSchedule) &&
                                    isTimeAvailable(room.RoomId, time, roomSchedule) &&
                                    isTimeAvailable(course.GetName(), time, courseSchedule)) {
                                assignSchedule(teacher, section, room, course, time, teacherSchedule, roomSchedule, courseSchedule);
                                assignedTeacherTimetable(teacher, section, course, time);
                                assignedSectionTimetable(section, teacher, room, course, time);
                                timetableEntries.add(new TimetableEntries(section.GetName(), teacher.GetName(), course.GetName(), room.RoomId, time));
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private static List<String> getAllAvailableTimes(List<Room> rooms) {
        Set<String> allTimes = new HashSet<>();
        for (Room room : rooms) {
            allTimes.addAll(room.AvailableTimes);
        }
        return new ArrayList<>(allTimes);
    }

    private static boolean isTimeAvailable(String id, String time, Map<String, Set<String>> schedule) {
        return !schedule.containsKey(id) || !schedule.get(id).contains(time);
    }

    private static void assignSchedule(Teacher teacher, Section section, Room room, Course course, String time,
                                       Map<String, Set<String>> teacherSchedule, Map<String, Set<String>> roomSchedule, Map<String, Set<String>> courseSchedule) {
        teacherSchedule.computeIfAbsent(teacher.GetName(), k -> new HashSet<>()).add(time);
        roomSchedule.computeIfAbsent(room.RoomId, k -> new HashSet<>()).add(time);
        courseSchedule.computeIfAbsent(course.GetName(), k -> new HashSet<>()).add(time);

        System.out.println("Assigned Teacher " + teacher.GetName() + " for Course " + course.GetName() +
                " to Section " + section.GetName() + " in Room " + room.RoomId + " at " + time);
    }

    private static void assignedSectionTimetable(Section section, Teacher teacher, Room room, Course course, String time) {
        String entry = "Teacher: " + teacher.GetName() + ", Course: " + course.GetName() + ", Room: " + room.RoomId + ", Time: " + time;
        sectionTimetable.computeIfAbsent(section.GetName(), k -> new HashSet<>()).add(entry);
    }

    private static void assignedTeacherTimetable(Teacher teacher, Section section, Course course, String time) {
        String entry = "Section: " + section.GetName() + ", Course: " + course.GetName() + ", Time: " + time;
        teacherTimetable.computeIfAbsent(teacher.GetName(), k -> new HashSet<>()).add(entry);
    }

    private static void displayTeacherTimetable(String teacherName) {
        if (teacherTimetable.containsKey(teacherName)) {
            System.out.println("Timetable for " + teacherName + ":");
            for (String entry : teacherTimetable.get(teacherName)) {
                System.out.println(entry);
            }
        } else {
            System.out.println("No timetable found for " + teacherName);
        }
    }

    private static void displaySectionTimetable(String sectionName) {
        if (sectionTimetable.containsKey(sectionName)) {
            System.out.println("Timetable for " + sectionName + ":");
            for (String entry : sectionTimetable.get(sectionName)) {
                System.out.println(entry);
            }
        } else {
            System.out.println("No timetable found for " + sectionName);
        }
    }



}
