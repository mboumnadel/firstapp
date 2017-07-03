package com.med.firstapp.rest;

import java.util.List;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class UserResourceMixin {

	@JsonProperty("loooinks") public abstract List<Link> getLinks();

	@JsonProperty public abstract int getId();
	//@JsonProperty public abstract String getName();
	@JsonProperty("prooooofession") public abstract String getProfession();

}
