package com.example.timetablesystem;

class TimetableEntries {
    private String sectionName;
    private String teacherName;
    private String courseName;
    private String roomId;
    private String time;

    public TimetableEntries(String sectionName, String teacherName, String courseName, String roomId, String time) {
        this.sectionName = sectionName;
        this.teacherName = teacherName;
        this.courseName = courseName;
        this.roomId = roomId;
        this.time = time;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getTime() {
        return time;
    }
}