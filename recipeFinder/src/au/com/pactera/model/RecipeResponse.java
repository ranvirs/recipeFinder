package au.com.pactera.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RecipeResponse {

    private String message;
    private Recipe recipe;

    public RecipeResponse(String message) {
        this(message, null);
    }

    public RecipeResponse(String message, Recipe recipe) {
        setMessage(message);
        setRecipe(recipe);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
