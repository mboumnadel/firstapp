package com.med.firstapp.provider;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.DateTimeProvider;

public class AuditingDateTimeProvider implements DateTimeProvider {

	@Autowired
    private DateTimeService dateTimeService;

    public AuditingDateTimeProvider() {
    }

    public AuditingDateTimeProvider(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    @Override
    public Calendar getNow() {

    	LocalDateTime dateTime = dateTimeService.getCurrentDateAndTime();
    	ZonedDateTime zdt = dateTime.atZone(ZoneId.systemDefault());

    	return GregorianCalendar.from(zdt);
    }
}

// older java.util.Date and java.util.Calendar

/*
java.time.
LocalDate LocalTime LocalDateTime
ZonedDateTime
Period Duration

LocalDate localDate = LocalDate.now();
LocalDate.parse("2015-02-20");

LocalTime sixThirty = LocalTime.parse("06:30");

LocalDateTime.now();
LocalDateTime.parse("2015-02-20T06:30:00");

ZoneId zoneId = ZoneId.of("Europe/Paris");
ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
*/