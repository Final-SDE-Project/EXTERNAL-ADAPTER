package external;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("external")
public class MyApplicationConfig extends ResourceConfig {
    public MyApplicationConfig () {
        packages("external");
    }
}
