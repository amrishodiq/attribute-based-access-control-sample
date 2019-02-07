package amri.shodiq.authorization.resource;

import amri.shodiq.authorization.model.Patient;
import amri.shodiq.authorization.model.Requestor;
import amri.shodiq.authorization.rest.RestResponse;
import amri.shodiq.authorization.service.MedicalRecordService;
import amri.shodiq.authorization.util.Crypto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author amri.s
 */
@Path("v1")
@Produces(MediaType.APPLICATION_JSON)
public class MedicalRecordResource {
    
    private MedicalRecordService service;
    
    public MedicalRecordResource(MedicalRecordService service) {
        this.service = service;
    }
   
    @GET
    @Path("/patients")
    public Response getAllPatients(@Context HttpHeaders headers) {
        String token = headers.getHeaderString("token");
        try {
            authenticate(token);
        } catch (Exception ex) {
            RestResponse response = new RestResponse<>(false, null, ex.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(response).build();
        }
        
        try {
            RestResponse response = new RestResponse<>(true, service.allPatients());
            return Response.status(Response.Status.OK).entity(response).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            
            RestResponse response = new RestResponse<>(false, null, ex.getMessage());
            return Response.status(Response.Status.OK).entity(response).build();
        }
    }
    
    @POST
    @Path("/auth")
    public Response login(Requestor user) {
        try {
            Requestor requestor = service.findRequestorByUsernameAndPassword(user);
            requestor.password = null;
            requestor.token = Crypto.getInstance().createToken(requestor.requestorId, requestor.username);
            
            RestResponse response = new RestResponse(true, requestor);
            
            return Response.status(Response.Status.OK).entity(response).build();
        } catch (Exception ex) {
            RestResponse response = new RestResponse(false, null, ex.getMessage());
            
            return Response.status(Response.Status.UNAUTHORIZED).entity(response).build();
        }
    }
    
    private Requestor authenticate(String token) throws Exception {
        if (token == null) throw new Exception("This request need a valid token");
        if (!Crypto.getInstance().isTokenValid(token)) throw new Exception("Invalid token");
        
        Requestor requestor = Crypto.getInstance().tokenToRequestor(token);
        if (requestor == null) throw new Exception("Invalid requestor");
        
        return requestor;
    }
    
}
