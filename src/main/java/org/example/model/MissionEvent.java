package org.example.model;

public class MissionEvent {
    private int id;
    private int astronautId;
    private int day;
    private MissionEventType type;
    private int basePoints;

    public MissionEvent(){

    }

    public int getId(){return id;}
    public void setId(int id){this.id=id;}

    public int getAstronautId(){return astronautId;}
    public void setAstronautId(int id){this.astronautId=id;}

    public int getDay(){return day;}
    public void setDay(int day){this.day=day;}

    public MissionEventType getType(){return type;}
    public void setType(MissionEventType type){this.type=type;}

    public int getBasePoints(){return basePoints;}
    public void setBasePoints(int basePoints){this.basePoints=basePoints;}

}
