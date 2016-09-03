package external.controller;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONObject;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;

@Stateless // will work only inside a Java EE application
@LocalBean // will work only inside a Java EE application
@Path("/quote")
public class Quote {

    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    public String getQuote() {
        String output = "";
        try
        {
            ClientConfig clientConfig = new ClientConfig();
            Client client = ClientBuilder.newClient(clientConfig);
            WebTarget service = client.target("http://quotesondesign.com/api/3.0/api-3.0.json");
            Response response = service.request().accept(MediaType.APPLICATION_JSON_TYPE).get();
            output= response.readEntity(String.class);

            JSONObject obj = new JSONObject(output);
            String n = obj.getString("quote");
            System.out.println(n);
            String m = obj.getString("author");
            System.out.println(""+m);

        }catch (Exception e)
        {
            System.out.println(e.getCause());
        }
        return output;
    }
}