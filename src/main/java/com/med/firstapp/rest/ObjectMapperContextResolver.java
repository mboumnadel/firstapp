package com.med.firstapp.rest;

import javax.ws.rs.core.Link;
import javax.ws.rs.ext.ContextResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

//@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    public ObjectMapperContextResolver() {
        mapper = new ObjectMapper();

        mapper.registerModule(new JaxbAnnotationModule());

        /*
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Link.class, new LinkSerializer());
        mapper.registerModule(simpleModule);
        */

        //mapper.addMixIn(User.class, UserMixin.class);
        //mapper.addMixIn(UserResource.class, UserResourceMixin.class);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {

    	System.out.println("ObjectMapper getContext : " + type);

    	 if (type.equals(User.class) || type.equals(UserResource.class) || type.equals(Link.class)){
    		 return mapper;
          }else {
        	  return mapper;
          }
    }
}
