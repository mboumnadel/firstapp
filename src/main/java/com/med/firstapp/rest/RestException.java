package com.med.firstapp.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RestException implements
                ExceptionMapper<Throwable> 
{
    private static final long serialVersionUID = 1L;
 
    @Override
    public Response toResponse(Throwable error) 
    {
    	 return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                 .entity("Internal error xxx:" + error.getMessage()).type("text/plain").build();
    }
}
