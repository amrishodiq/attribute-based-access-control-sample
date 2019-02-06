package amri.shodiq.authorization.service;

import amri.shodiq.authorization.dao.PatientDao;
import amri.shodiq.authorization.model.Patient;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

/**
 *
 * @author amri.s
 */
public abstract class MedicalRecordService {
    @CreateSqlObject
    abstract PatientDao getPatientDao();
    
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
}
