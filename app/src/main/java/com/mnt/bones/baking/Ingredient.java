package com.mnt.bones.baking;

/**
 * Created by Usuario on 05/02/2018.
 */

public class Ingredient {

    private String quantity;
    private String measure;
    private String ingredientName;

    public Ingredient(String quantity, String measure, String ingredientName) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredientName = ingredientName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
