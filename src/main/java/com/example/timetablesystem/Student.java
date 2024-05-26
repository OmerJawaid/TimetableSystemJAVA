package com.example.timetablesystem;

public class Student extends Person {

    Student(String Name, int Id,String Email){
        super.Name=Name;
        super.Id=Id;
        super.Email=Email;
    };

    //Attributes Declaration
    private Section Section=new Section();

    //Getters and Setters
    public String GetName(){return super.GetName();}
    public int GetId(){return super.GetId();}
    public String GetEmail(){return super.GetEmail();}
    public Section GetSection(){return Section;}

    //Assign Section
    public void AssignSection(Section SectionAssign){
        Section=SectionAssign;
    }
}
