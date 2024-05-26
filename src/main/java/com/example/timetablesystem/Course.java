package com.example.timetablesystem;
import java.util.Vector;

public class Course {

    Course(){};

    //Attributes Declaration
    private int Code;
    private String Name;

    //For Association with refrences.
    private Vector<Teacher> TeachersObj = new Vector<Teacher>(10);
    private Vector<Section> SectionsObj = new Vector<Section>(10);

    //Getters and Setters
    public int GetCode(){return Code;}
    public String GetName(){return Name;}

    //Data Assignment to Vectors
    public void AddTeacher(Teacher teachertovec)
    {
        TeachersObj.add(teachertovec);
    }
    public void AddSection(Section sectiontovec)
    {
        SectionsObj.add(sectiontovec);
    }
}
