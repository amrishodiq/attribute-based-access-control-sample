package amri.shodiq.authorization.dao;

import amri.shodiq.authorization.mapper.PatientMapper;
import amri.shodiq.authorization.model.Patient;
import java.util.Date;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 *
 * @author amri.s
 */
@RegisterMapper(PatientMapper.class)
public interface PatientDao {
    @SqlQuery("SELECT * FROM rest.patients")
    List<Patient> allPatients();
    
    @SqlUpdate("INSERT INTO rest.patients "
            + " (patient_id, national_id_number, real_name, date_of_birth, gender, mother_name, father_name, responsible_person) "
            + "VALUES "
            + " (:patientId, :nationalIdNumber, :realName, :dateOfBirth, :gender, :motherName, :fatherName, :responsiblePerson)")
    void save(
            @Bind("patientId") long patientId, 
            @Bind("nationalIdNumber") String nationalIdNumber,
            @Bind("realName") String realName,
            @Bind("dateOfBirth") Date dateOfBirth,
            @Bind("gender") int gender,
            @Bind("motherName") String motherName,
            @Bind("fatherName") String fatherName,
            @Bind("responsiblePerson") String responsiblePerson
    );
}
