package Models;

import java.util.ArrayList;

public class Restaurant { // класс ресторана
    private int id;
    private String name;
    private String location;
    private String kitchen;
    private String timetable;
    private String rating;
    private String phoneofrestaurant;

    public String getPhoneofrestaurant() {
        return phoneofrestaurant;
    }

    public void setPhoneofrestaurant(String phoneofrestaurant) {
        this.phoneofrestaurant = phoneofrestaurant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getKitchen() {
        return kitchen;
    }

    public void setKitchen(String kitchen) {
        this.kitchen = kitchen;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
