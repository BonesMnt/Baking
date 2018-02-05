package com.mnt.bones.baking;

import java.util.ArrayList;

/**
 * Created by Usuario on 05/02/2018.
 */

public class Recipe {

    private String name;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Step> steps;
    private String serving;

    public Recipe(String name, ArrayList<Ingredient> ingredients, ArrayList<Step> steps, String serving) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.serving = serving;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public String getServing() {
        return serving;
    }

    public void setServing(String serving) {
        this.serving = serving;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                ", serving='" + serving + '\'' +
                '}';
    }
}
