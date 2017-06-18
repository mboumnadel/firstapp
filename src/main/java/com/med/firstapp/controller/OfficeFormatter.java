package com.med.firstapp.controller;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.med.firstapp.model.Office;
import com.med.firstapp.service.OfficeService;

/*
 * It's possible to register formatters in container.
 * @Valid will be able to use formatters without the need to register them in @InitBinder.
<bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
<property name="formatters">
     <set>
           <ref bean="officeFormatter"/>
     </set>
</property>
</bean>
*/
//@Component
public class OfficeFormatter implements Formatter<Office> {

	//@Autowired
	private OfficeService officeService;

	public OfficeFormatter() {
    }

    public OfficeFormatter(OfficeService officeService) {
        this.officeService = officeService;
    }

	public OfficeService getOfficeService() {
		return officeService;
	}

	public void setOfficeService(OfficeService officeService) {
		this.officeService = officeService;
	}
    
	 @Override
	 public String print(Office office, Locale arg1) {
	       return office.getCity();
	 }

	 @Override
	  public Office parse(String officeId, Locale arg1) throws ParseException {
		 int id = Integer.parseInt(officeId);
	     Office office = officeService.findById(id);
	     return office;
	  }
}
