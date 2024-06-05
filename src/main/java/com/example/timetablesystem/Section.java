package com.example.timetablesystem;

import java.util.Vector;
public class Section {

    Section(){};
    //Section(){};

    //Attributes Declaration
    private String Name;
    private int NoOfStudents;
    private Vector<Student> StudentsObj = new Vector<Student>(100);
    private Vector<Course> CoursesObj=new Vector<>(6);

    //Getters and Setters
    public String GetName(){return Name;}
    public int GetNoOgStudents(){return NoOfStudents;}
    public void SetNoofStudents(int Number){NoOfStudents=Number;}
    public void SetSectionName(String SectionName){Name=SectionName;}
    //Add Students to Section Vector by Reference
    public void AddStudent(Student studenttovec){
        StudentsObj.add(studenttovec);
    }
    public void AddCourse(Course coursetovec){CoursesObj.add(coursetovec);}
}
