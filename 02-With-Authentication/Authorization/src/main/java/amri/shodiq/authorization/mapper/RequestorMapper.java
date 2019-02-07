package amri.shodiq.authorization.mapper;

import amri.shodiq.authorization.model.Requestor;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 *
 * @author amri.s
 */
public class RequestorMapper implements ResultSetMapper<Requestor> {

    @Override
    public Requestor map(int i, ResultSet rs, StatementContext sc) throws SQLException {
        return new Requestor(
                rs.getInt("requestor_id"), 
                rs.getString("username"), 
                rs.getString("password")
        );
    }
    
}
