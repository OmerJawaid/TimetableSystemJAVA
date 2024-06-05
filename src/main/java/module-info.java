module com.example.timetablesystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;


    opens com.example.timetablesystem to javafx.fxml;
    exports com.example.timetablesystem;
}