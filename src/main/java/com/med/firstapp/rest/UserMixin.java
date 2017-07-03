package com.med.firstapp.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class UserMixin {

	@JsonCreator
	UserMixin(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("profession") String profession){}

	@JsonProperty public abstract int getId();
	@JsonProperty public abstract String getName();
	@JsonProperty public abstract String getProfession();

}
