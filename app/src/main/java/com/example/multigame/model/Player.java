package com.example.multigame.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Player {

    @NonNull
    @PrimaryKey
    private String name;
    private String picture;
    private String firstName;
    private int age;
    private String localisation;
    private int dragNDropScore;
    private int swipeScore;
    private int fastTapScore;
    private int ipacScore;

    public Player(String picture, String name, String firstName, int age, String localisation){
        setPicture(picture);
        setName(name);
        setFirstName(firstName);
        setAge(age);
        setLocalisation(localisation);
        setDragNDropScore(0);
        setSwipeScore(0);
        setFastTapScore(0);
        setIpacScore(0);
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDragNDropScore() {
        return dragNDropScore;
    }

    public void setDragNDropScore(int dragNDropScore) { this.dragNDropScore = dragNDropScore; }

    public int getSwipeScore() {
        return swipeScore;
    }

    public void setSwipeScore(int swipeScore) {
        this.swipeScore = swipeScore;
    }

    public int getFastTapScore() {
        return fastTapScore;
    }

    public void setFastTapScore(int fastTapScore) {
        this.fastTapScore = fastTapScore;
    }

    public int getIpacScore() {
        return ipacScore;
    }

    public void setIpacScore(int ipacScore) {
        this.ipacScore = ipacScore;
    }
}
