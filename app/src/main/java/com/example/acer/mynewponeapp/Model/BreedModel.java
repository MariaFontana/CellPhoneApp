package com.example.acer.mynewponeapp.Model;

public class BreedModel {


   public int breedId;
   public String name;

    public BreedModel(int breedId, String name) {
        this.breedId = breedId;
        this.name = name;
    }

    public BreedModel() {

    }

    public int getBreedId() {
        return breedId;
    }

    public String getName() {
        return name;
    }

    public void setBreedId(int breedId) {
        this.breedId = breedId;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString()
    {
        return name;
    }

}
