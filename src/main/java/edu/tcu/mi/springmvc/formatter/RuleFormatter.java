package edu.tcu.mi.springmvc.formatter;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import edu.tcu.mi.springmvc.service.IService;

public class RuleFormatter implements Formatter<Object> {

	private final IService<Object> service;


    @Autowired
    public RuleFormatter(IService<Object> service) {
        this.service = service;
    }
	
	@Override
	public String print(Object object, Locale locale) {
		return object.toString();
	}

	@Override
	public Object parse(String text, Locale locale) throws ParseException {

        throw new ParseException("type not found: " + text, 0);
	}

}
