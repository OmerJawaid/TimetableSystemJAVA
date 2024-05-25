module com.example.timetablesystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.timetablesystem to javafx.fxml;
    exports com.example.timetablesystem;
}