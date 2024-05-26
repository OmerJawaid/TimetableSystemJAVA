package com.example.timetablesystem;
import java.util.Vector;

public class Admin extends Person {

    Admin(String Name, int Id, String Email ){
        super.Name=Name;
        super.Id=Id;
        super.Email=Email;
    };

    //For Association with refrences.
    private Vector<Teacher> TeachersObj = new Vector<Teacher>(10);
    private Vector<Student> StudentsObj = new Vector<Student>(100);
    private Vector<Course>  CoursesObj =new Vector<Course>(10);
    private Vector<Section>  SectionsObj =new Vector<Section>(5);

    //Getters and Setters
    public String GetName(){return super.GetName();}
    public String GetEmail(){return super.GetEmail();}
    public int GetId(){return super.GetId();}
    public String GetUsername(){return super.GetUsername();}
    public String GetPassword(){return super.GetPassword();}

    //Data Assignment to Vectors
    public void AddTeacher(Teacher teachertovec)
    {
        TeachersObj.add(teachertovec);
    }
    public void AddStudent(Student studenttovec)
    {
        StudentsObj.add(studenttovec);
    }
    public void AddCourse(Course coursetovec)
    {
        CoursesObj.add(coursetovec);
    }
    public void AddSection(Section sectiontovec)
    {
        SectionsObj.add(sectiontovec);
    }
}
