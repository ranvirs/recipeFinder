package au.com.pactera.service;

import au.com.pactera.model.*;
import au.com.pactera.repo.RecipesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status;

@Path("/recipes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecipesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipesService.class);

    @GET
    public List<Recipe> get() {
        return RecipesRepository.instance().get();
    }

    @POST @Path("/add")
    public RecipeResponse add(List<Recipe> recipes) {
        LOGGER.info("Adding recipes: [{}]", recipes);

        try {
            RecipesRepository.instance().update(recipes);
        } catch(IllegalArgumentException e) {
            throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build());
        }

        return ResponseHelper.suggestRecipe();
    }

    @GET @Path("/suggestion")
    public RecipeResponse suggestion() {
        return ResponseHelper.suggestRecipe();
    }
}
