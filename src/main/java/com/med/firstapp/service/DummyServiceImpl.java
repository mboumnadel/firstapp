package com.med.firstapp.service;

import org.springframework.stereotype.Service;

@Service("DummyService")
public class DummyServiceImpl implements DummyService {

	@Override
	public String getData(String str) {
		return "from service " + str;
	}

}
