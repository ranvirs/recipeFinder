package au.com.pactera.service;

import au.com.pactera.model.*;

import java.util.TreeSet;

class RecipeFound {
    final Recipe recipe;
    final TreeSet<Integer> closestDays = new TreeSet<>();

    RecipeFound(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "RecipeFound{" +
                "recipe=" + recipe +
                ", closestDays=" + closestDays +
                '}';
    }
}