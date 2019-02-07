package amri.shodiq.authorization.dao;

import amri.shodiq.authorization.mapper.RequestorMapper;
import amri.shodiq.authorization.model.Requestor;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 *
 * @author amri.s
 */
@RegisterMapper(RequestorMapper.class)
public interface RequestorDao {
    @SqlQuery("SELECT * FROM rest.requestors "
            + "WHERE username = :username AND password = SHA1(:password)")
    List<Requestor> findByUsernameAndPassword(
            @Bind("username") String username, 
            @Bind("password") String password
    );
}
