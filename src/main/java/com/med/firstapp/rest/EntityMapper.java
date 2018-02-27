package com.med.firstapp.rest;

import org.mapstruct.Mapper;

import com.med.firstapp.model.Email;
import com.med.firstapp.model.User;

@Mapper
public interface EntityMapper {

	UserDto convertToDto(User user);
	User convertToEntity(UserDto userDto);

	EmailDto convertToDto(Email email);
    Email convertToEntity(EmailDto emailDto);

}
