package amri.shodiq.authorization.resource;

import amri.shodiq.authorization.model.Patient;
import amri.shodiq.authorization.rest.Response;
import amri.shodiq.authorization.service.MedicalRecordService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    public Response<List<Patient>> getAllPatients() {
        try {
            return new Response<>(true, service.allPatients());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, null);
        }
    }
    
}
