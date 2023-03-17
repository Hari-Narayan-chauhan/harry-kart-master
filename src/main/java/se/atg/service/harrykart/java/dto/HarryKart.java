package se.atg.service.harrykart.java.dto;

import java.io.Serializable;
import java.util.List;

public class HarryKart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numberOfLoops;

	private List<Participant> startList;
	private List<Loop> powerUps;

	public int getNumberOfLoops() {
		return numberOfLoops;
	}

	public void setNumberOfLoops(int numberOfLoops) {
		this.numberOfLoops = numberOfLoops;
	}

	public List<Participant> getStartList() {
		return startList;
	}

	public void setStartList(List<Participant> startList) {
		this.startList = startList;
	}

	public List<Loop> getPowerUps() {
		return powerUps;
	}

	public void setPowerUps(List<Loop> powerUps) {
		this.powerUps = powerUps;
	}

}
