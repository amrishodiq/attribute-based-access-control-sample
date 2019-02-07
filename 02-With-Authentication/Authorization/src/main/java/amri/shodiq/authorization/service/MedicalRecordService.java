package amri.shodiq.authorization.service;

import amri.shodiq.authorization.dao.PatientDao;
import amri.shodiq.authorization.dao.RequestorDao;
import amri.shodiq.authorization.model.Patient;
import amri.shodiq.authorization.model.Requestor;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

/**
 *
 * @author amri.s
 */
public abstract class MedicalRecordService {
    @CreateSqlObject
    abstract PatientDao getPatientDao();
    
    @CreateSqlObject
    abstract RequestorDao getRequestorDao();
    
    public List<Patient> allPatients() throws Exception {
        return getPatientDao().allPatients();
    }
    
    public void savePatient(Patient patient) throws Exception {
        getPatientDao().save(
                patient.patientId, 
                patient.nationalIdNumber, 
                patient.realName, 
                patient.dateOfBirth, 
                patient.gender, 
                patient.motherName, 
                patient.fatherName, 
                patient.responsiblePerson
        );
    }
    
    public Requestor findRequestorByUsernameAndPassword(Requestor requestor) throws Exception {
        if (requestor == null) throw new Exception("Please specify username and password.");
        
        List<Requestor> requestors = getRequestorDao()
                .findByUsernameAndPassword(
                        requestor.username, 
                        requestor.password
                );
        if (requestors == null || requestors.size() == 0) throw new Exception("Username or password missmatch.");
        
        return requestors.get(0);
    }
}
