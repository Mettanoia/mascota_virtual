// Creature.java
package com.solafides.mascota_virtual.domain;

public class Creature {
    private String id;
    private String name;
    private String imageUrl;
    private String mood;
    private String energy;

    // Constructors
    public Creature() {
    }

    public Creature(String id, String name, String imageUrl, String mood, String energy) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.mood = mood;
        this.energy = energy;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }
}
