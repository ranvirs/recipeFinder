package au.com.pactera.service;

import au.com.pactera.model.*;
import au.com.pactera.repo.FridgeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

import static javax.ws.rs.core.Response.Status;

@Path("/fridge")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FridgeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FridgeService.class);

    @GET
    public List<FridgeItem> get() {
        return FridgeRepository.instance().get();
    }

    @POST @Path("/add")
    @Consumes(MediaType.TEXT_PLAIN)
    public RecipeResponse addItems(String items) throws IOException {
        LOGGER.info("Adding items: [{}]", items);

        try {
            if (items.trim().isEmpty())
                throw new IllegalArgumentException("No items specified! ");

            List<FridgeItem> fridgeItems = ItemsParser.parseFridgeItems(items);
            FridgeRepository.instance().update(fridgeItems);
        } catch(IllegalArgumentException e) {
            throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build());
        }

        return ResponseHelper.suggestRecipe();
    }
}
