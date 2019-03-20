package local.jordi.kwetter.boundary.rest;

import javax.ws.rs.core.Response;

class ResourceHelper
{
    private ResourceHelper()
    {
    }


    static Response GenerateResponse(Object object)
    {
        if (object != null)
        {
            return Response.ok(object).build();
        }
        return Response.noContent().build();
    }

    static Response GenerateResponse(boolean operationSucceeded)
    {
        if (operationSucceeded)
        {
            return Response.ok().build();
        }
        return Response.notModified().build();
    }

    static Response GenerateResponse()
    {
        return Response.ok().build();
    }

    static Response GenerateErrorResponse()
    {
        return Response.serverError().build();
    }
}
