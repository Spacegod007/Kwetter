package local.jordi.kwetter.boundary.rest.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import local.jordi.kwetter.service.IAuthenticateService;
import org.primefaces.json.JSONObject;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.Date;

@Path("authentication")
@Stateless
public class AuthenticationResource
{
    @Inject
    private IAuthenticateService authenticateService;

    @POST
    @Path("login")
    public Response authenticateUser(JsonObject jsonObject)
    {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        try
        {
            long userId = authenticateService.authenticate(username, password);
            String token = issueToken(username);

            JSONObject returnableJson = new JSONObject();
            returnableJson.put("access_token", "Bearer " + token);
            returnableJson.put("userId", userId);

            return Response.ok(returnableJson.toString()).header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
        }
        catch (Exception e)
        {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private String issueToken(String username)
    {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        JwtBuilder builder = Jwts.builder();
        builder.setSubject(username);
        builder.setIssuedAt(new Date());
        builder.setExpiration(new Date(new Date().toInstant().plusSeconds(900L).toEpochMilli()));
        builder.signWith(key);

        return builder.compact();
    }
}

