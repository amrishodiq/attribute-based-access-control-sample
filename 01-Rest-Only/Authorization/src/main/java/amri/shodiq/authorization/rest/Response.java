package amri.shodiq.authorization.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author amri.s
 */
public class Response<T> {
    @JsonProperty
    public boolean ok;
    
    @JsonProperty
    public T data;

    public Response() {
    }

    public Response(boolean ok, T data) {
        this.ok = ok;
        this.data = data;
    }
    
    
}
