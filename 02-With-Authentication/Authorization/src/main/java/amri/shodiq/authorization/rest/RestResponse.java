package amri.shodiq.authorization.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author amri.s
 */
public class RestResponse<T> {
    @JsonProperty
    public boolean ok;
    
    @JsonProperty
    public String message;
    
    @JsonProperty
    public T data;
    
    public RestResponse() {
    }

    public RestResponse(boolean ok, T data) {
        this(ok, data, null);
    }
    
    public RestResponse(boolean ok, T data, String message) {
        this.ok = ok;
        this.data = data;
        this.message = message;
    }
}
