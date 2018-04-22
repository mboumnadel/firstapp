package com.med.firstapp.provider;

import java.time.LocalDateTime;

public class CurrentTimeDateTimeService implements DateTimeService {

	@Override
	public LocalDateTime getCurrentDateAndTime() {
		return LocalDateTime.now();
	}
}
