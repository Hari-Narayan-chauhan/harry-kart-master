package se.atg.service.harrykart.java.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "loop")
public class Loop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JacksonXmlProperty(isAttribute = true)
	private int number;
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "lane")
	private List<Lane> lanes;
	
	public Loop() {
		
	}

	public Loop(int number, List<Lane> lanes) {
		super();
		this.number = number;
		this.lanes = lanes;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Lane> getLanes() {
		return lanes;
	}

	public void setLanes(List<Lane> lanes) {
		this.lanes = lanes;
	}

	@Override
	public String toString() {
		return "Loop [number=" + number + ", lanes=" + lanes + "]";
	}
	
	

}
