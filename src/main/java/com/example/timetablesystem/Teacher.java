package com.example.timetablesystem;

import javafx.scene.control.Alert;
import java.util.Vector;

public class Teacher extends Person {

    Teacher(String Name,int Id, String Email){
        super.Name=Name;
        super.Id=Id;
        super.Email=Email;
    };

    //Attributes Declaration
    private String Name;
    private int Id;
    private String Email;
    private Vector<Course> Courses=new Vector<Course>(10);

    //Getters and Setters
    public String GetName(){return super.GetName();}
    public int GetId(){return super.GetId();}
    public String getEmail(){return super.GetEmail();}

    //Main Functionality
    void AddCourse(Course course){
        try{Courses.add(course);}
        catch(Exception e)
        {
            showAlert("Failed Add Course to Teacher", e.getMessage());
        }
    }

    void DeleteCourse(Course course){
        try{Courses.remove(course);}
        catch(Exception e)
        {
            showAlert("Failed Remove Course from Teacher",e.getMessage());
        }
    }


    //Alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
