package amri.shodiq.authorization.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 *
 * @author amri.s
 */
@JsonInclude(Include.NON_NULL)
public class Requestor {
    public int requestorId;
    public String username;
    public String password;
    public String token;
    
    public Requestor() {
    }

    public Requestor(int requestorId, String username, String password) {
        this(requestorId, username, password, null);
    }
    
    public Requestor(int requestorId, String username, String password, String token) {
        this.requestorId = requestorId;
        this.username = username;
        this.password = password;
        this.token = token;
    }

    @Override
    public String toString() {
        return "id: "+requestorId+", username: "+username+", password: "+password;
    }
    
    
}
