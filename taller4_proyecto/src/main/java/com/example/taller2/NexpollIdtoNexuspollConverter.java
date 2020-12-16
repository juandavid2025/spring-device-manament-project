package com.example.taller2;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.taller2.delegate.interfaces.NexuspollDelegate;
import com.example.taller2.model.Nexuspoll;

@Component
public class NexpollIdtoNexuspollConverter implements Converter<String,Nexuspoll>{
	
	private NexuspollDelegate del;
	
	public NexpollIdtoNexuspollConverter(NexuspollDelegate del) {
		this.del=del;
	}

	@Override
	public Nexuspoll convert(String source) {
		// TODO Auto-generated method stub
		return del.findById(Long.parseLong(source));
	}

}
