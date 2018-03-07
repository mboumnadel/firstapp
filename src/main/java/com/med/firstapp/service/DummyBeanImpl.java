package com.med.firstapp.service;

//@Component("dummyBean")
// A mock of this object will be provided in test environment
public class DummyBeanImpl implements DummyBean {

	@Override
	public String getData(String str) {
		return "from DummyBeanImpl " + str;
	}

}
