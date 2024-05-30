package com.example.timetablesystem;

import java.util.Vector;
public class Section {

    Section(){};

    //Attributes Declaration
    private String Name;
    private int NoOfStudents;
    private Vector<Student> StudentsObj = new Vector<Student>(100);

    //Getters and Setters
    public String GetName(){return Name;}
    public int GetNoOgStudents(){return NoOfStudents;}

    //Add Students to Section Vector by Reference
    public void AddStudent(Student studenttovec){
        StudentsObj.add(studenttovec);
    }
}
