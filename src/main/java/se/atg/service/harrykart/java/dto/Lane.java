package se.atg.service.harrykart.java.dto;

import java.io.Serializable;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class Lane implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JacksonXmlProperty(isAttribute = true)
	int number;
	@JacksonXmlText
	int powerValue;
	
	public Lane() {
		
	}
	
	public Lane(int number, int powerValue) {
		super();
		this.number = number;
		this.powerValue = powerValue;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getPowerValue() {
		return powerValue;
	}
	public void setPowerValue(int powerValue) {
		this.powerValue = powerValue;
	}
	
	

}
