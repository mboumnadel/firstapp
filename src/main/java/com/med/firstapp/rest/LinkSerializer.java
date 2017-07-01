package com.med.firstapp.rest;

import java.io.IOException;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

// HOW TO USE : annotate List<Link> links with @JsonSerialize(	contentUsing = LinkSerializer.class)
// HOW TO USE : define ContextResolver<ObjectMapper> and register with @Provider
// objectMapper.registerModule( new SimpleModule().addSerializer(Link.class, new LinkSerializer()) );

public class LinkSerializer extends JsonSerializer<Link>{

    @Override
    public void serialize(Link link, JsonGenerator jg, SerializerProvider sp) 
            throws IOException, JsonProcessingException {
        jg.writeStartObject();
        jg.writeStringField("rel", link.getRel());
        jg.writeStringField("href", link.getUri().toString());
        jg.writeEndObject();
    }
}
