package external.controller;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONObject;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;

@Stateless // will work only inside a Java EE application
@LocalBean // will work only inside a Java EE application
@Path("/bmi")
public class BMI {

    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    public String getBmi(@QueryParam("weight") double weight, @QueryParam("height") double height) {
        String output = "";
        try
        {
            ClientConfig clientConfig = new ClientConfig();
            Client client = ClientBuilder.newClient(clientConfig);
            WebTarget service = client.target("https://bmi.p.mashape.com/");

            String message = "{\"weight\":{\"value\":\"85.00\",\"unit\":\"kg\"},\"height\":{\"value\":\"170.00\",\"unit\":\"cm\"},\"sex\":\"m\",\"age\":\"24\"}";
            JSONObject objedit = new JSONObject(message);
            objedit.getJSONObject("weight").put("value", weight);
            objedit.getJSONObject("height").put("value", height);


            Entity<String> entityPostJson = Entity.json(objedit.toString());
            Response response = service.request()
                    .header("X-Mashape-Key", "GuCjdAkWBjmshMRXs16T2VGWetJjp1O8iU6jsnG51qatFngVJo")
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .accept(MediaType.APPLICATION_JSON)
                    .post(entityPostJson);

            output = response.readEntity(String.class);

            JSONObject obj = new JSONObject(output);
            String n = obj.getString("ideal_weight");
            System.out.println("Your ideal Weight should be between: "+n);
            String m = obj.getJSONObject("bmi").getString("value");
            System.out.println("BMI value: "+m);
        }catch (Exception e)
        {
            System.out.println(e.getCause());
        }
        return output;
    }
}