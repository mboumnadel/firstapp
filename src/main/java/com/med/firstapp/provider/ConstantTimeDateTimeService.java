package com.med.firstapp.provider;

import java.time.LocalDateTime;

public class ConstantTimeDateTimeService implements DateTimeService {

	@Override
	public LocalDateTime getCurrentDateAndTime() {
		return LocalDateTime.parse("2018-04-19T10:30:00");
	}
}
