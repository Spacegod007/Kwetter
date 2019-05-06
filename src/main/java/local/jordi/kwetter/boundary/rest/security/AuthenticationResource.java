package local.jordi.kwetter.boundary.rest.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import local.jordi.kwetter.dao.collection.SimpleStore;
import local.jordi.kwetter.service.IAuthenticateService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("authentication")
@Stateless
public class AuthenticationResource
{

    @Inject
    private IAuthenticateService authenticateService;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("login")
    public Response authenticateUser(JsonObject jsonObject)
    {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        try
        {
            long userId = authenticateService.authenticate(username, password);
            String tokenWithBearer = "Bearer " + issueToken(username);

            JsonObject json = Json.createObjectBuilder()
                    .add("access_token", tokenWithBearer)
                    .add("userId", userId).build();

            return Response.ok(json.toString()).header(HttpHeaders.AUTHORIZATION, tokenWithBearer).build();
        }
        catch (Exception e)
        {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private String issueToken(String username)
    {
        String key = SimpleStore.getSecret();
        JwtBuilder builder = Jwts.builder()
                .setSubject(username)
                .setIssuer("Kwetter")
                .signWith(SignatureAlgorithm.HS512, key);
        return builder.compact();
    }
}
