package com.cohen.hackathonworld.Model;

public class Classroom {

    /**
     *  Classroom Class - every classroom in campus have location
     */
    public static int NUMBER_OF_CLASSROOMS = 5000000;

    public int classroomId = 0;
    public String classroomName = "";
    public Location location = null;

    public Classroom(){}

    public Classroom withClassroomId(){
        this.classroomId = NUMBER_OF_CLASSROOMS++;
        return this;
    }
    public Classroom withClassroomName(String classroomName){
        this.classroomName = classroomName;
        return this;
    }
    public Classroom withClassroomLocation(double lat, double log){
        location = new Location(lat, log);
        return  this;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public Location getLocation() {
        return location;
    }
}
