package com.med.firstapp.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;

public class CustomLinkAdapter extends XmlAdapter<CustomJaxbLink, Link> {

public CustomLinkAdapter() {
}

@Override
public Link unmarshal(CustomJaxbLink customLink) {

    Link.Builder builder = Link.fromUri(customLink.getUri());
    for (Map.Entry<QName, Object> entry : customLink.getParams().entrySet()) {
        builder.param(entry.getKey().getLocalPart(), entry.getValue().toString());
    }
    return builder.build();
}

@Override
public CustomJaxbLink marshal(Link link) {

    Map<QName, Object> params = new HashMap<>();
    for (Map.Entry<String, String> entry : link.getParams().entrySet()) {
        params.put(new QName("", entry.getKey()), entry.getValue());
    }
    return new CustomJaxbLink(link.getUri(), params);
}
}
