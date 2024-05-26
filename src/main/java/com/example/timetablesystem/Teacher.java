package com.example.timetablesystem;

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
}
