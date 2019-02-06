package amri.shodiq.authorization;

import amri.shodiq.authorization.resource.MedicalRecordResource;
import amri.shodiq.authorization.service.MedicalRecordService;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import javax.sql.DataSource;
import org.skife.jdbi.v2.DBI;

/**
 *
 * @author amri.s
 */
public class ServerApp extends Application<Config>{

    @Override
    public void initialize(Bootstrap<Config> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/cms/", "index.html"));
        
//        super.initialize(bootstrap); 
    }

    
    
    @Override
    public void run(Config configuration, Environment environment) throws Exception {
        final DataSource datasource = configuration.getDatabaseConfiguration().build(environment.metrics(), "sql");
        DBI dbi = new DBI(datasource);
        
        environment.jersey().register(new MedicalRecordResource(dbi.onDemand(MedicalRecordService.class)));
    }
    
    public static void main(String[] args) throws Exception {
        new ServerApp().run(args);
    }
    
}
