package au.com.pactera.service;

import au.com.pactera.model.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/time")
@Produces(MediaType.APPLICATION_JSON)
public class TimeService {

    @GET
    public Time get() {
        return new Time();
    }

}

