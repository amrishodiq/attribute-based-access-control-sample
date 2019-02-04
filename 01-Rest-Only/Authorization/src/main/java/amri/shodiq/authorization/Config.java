package amri.shodiq.authorization;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author amri.s
 */
public class Config extends Configuration {
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDatabaseConfiguration() {
        return database;
    }

    @JsonProperty("database")
    public void setDatabaseConfiguration(DataSourceFactory database) {
        this.database = database;
    }
}
