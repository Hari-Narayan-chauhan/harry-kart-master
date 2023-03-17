package se.atg.service.harrykart.java.dto;

public class HorseRank {

	int position;
	String horseName;
	double time;

	public HorseRank(int position, String horseName, double time) {
		this.position = position;
		this.horseName = horseName;
		this.time = time;
	}
	
	public HorseRank(int position, String horseName) {
		this.position = position;
		this.horseName = horseName;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "HorseRank [position=" + position + ", horseName=" + horseName + "]";
	}

}
