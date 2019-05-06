package local.jordi.kwetter.boundary.rest.security;

import io.jsonwebtoken.Jwts;
import local.jordi.kwetter.dao.collection.SimpleStore;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@RequiresJWT
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter
{
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException
    {
        try {
            String authorizationHeader = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
            if (authorizationHeader == null || authorizationHeader.isEmpty())
            {
                setUnauthorized(containerRequestContext);
                return;
            }

            String token = authorizationHeader.substring("Bearer".length()).trim();
            String key = SimpleStore.getSecret();
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);

        }
        catch (Exception e)
        {
            setUnauthorized(containerRequestContext);
        }
    }

    private void setUnauthorized(ContainerRequestContext containerRequestContext) {
        containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
    }
}
