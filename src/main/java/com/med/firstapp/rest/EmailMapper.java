package com.med.firstapp.rest;

import com.med.firstapp.model.Email;


//@Mapper
public interface EmailMapper {

	EmailDto convertToDto(Email email);
    Email convertToEntity(EmailDto emailDto);

}
