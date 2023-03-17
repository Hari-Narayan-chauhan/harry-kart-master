package se.atg.service.harrykart.java.dto;

import java.io.Serializable;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "participant")
public class Participant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JacksonXmlProperty
	int lane;
	@JacksonXmlProperty
	String name;
	@JacksonXmlProperty
	int baseSpeed;

	public Participant() {
	}

	public Participant(int lane, String name, int baseSpeed) {
		super();
		this.lane = lane;
		this.name = name;
		this.baseSpeed = baseSpeed;
	}

	public int getLane() {
		return lane;
	}

	public void setLane(int lane) {
		this.lane = lane;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBaseSpeed() {
		return baseSpeed;
	}

	public void setBaseSpeed(int baseSpeed) {
		this.baseSpeed = baseSpeed;
	}

	@Override
	public String toString() {
		return "Participant [lane=" + lane + ", name=" + name + ", baseSpeed=" + baseSpeed + "]";
	}

}
