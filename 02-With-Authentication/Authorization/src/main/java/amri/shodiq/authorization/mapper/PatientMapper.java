package amri.shodiq.authorization.mapper;

import amri.shodiq.authorization.model.Patient;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 *
 * @author amri.s
 */
public class PatientMapper implements ResultSetMapper<Patient>{

    @Override
    public Patient map(int i, ResultSet rs, StatementContext sc) throws SQLException {
        return new Patient(
                rs.getLong("patient_id"), 
                rs.getString("national_id_number"), 
                rs.getString("real_name"), 
                rs.getDate("date_of_birth"), 
                rs.getInt("gender"), 
                rs.getString("mother_name"), 
                rs.getString("father_name"), 
                rs.getString("responsible_person")
        );
    }
    
}
