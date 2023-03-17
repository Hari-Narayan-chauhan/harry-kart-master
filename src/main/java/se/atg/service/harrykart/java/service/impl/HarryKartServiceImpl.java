package se.atg.service.harrykart.java.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import se.atg.service.harrykart.java.dto.HarryKart;
import se.atg.service.harrykart.java.dto.HorseRank;
import se.atg.service.harrykart.java.dto.Lane;
import se.atg.service.harrykart.java.dto.Loop;
import se.atg.service.harrykart.java.dto.Participant;
import se.atg.service.harrykart.java.service.HarryKartService;

@Service
public class HarryKartServiceImpl implements HarryKartService {

	public static final double TRACKLENGTH = 1000.0;
	private HarryKart harryKart;
	private List<HorseRank> positionList = new ArrayList<>();

	public HarryKart getHarryKart() {
		return harryKart;
	}

	public void setHarryKart(HarryKart harryKart) {
		this.harryKart = harryKart;
	}

	/**
	 * to get the horse rank
	 */
	@Override
	public List<HorseRank> getHorseRank(HarryKart harryKart) {
		this.harryKart = harryKart;

		harryKart.getStartList().parallelStream().forEach(this::getPowerUp);

		Collections.sort(positionList, Comparator.comparing(HorseRank::getTime));
		positionList.removeIf(rank -> rank.getTime() >= Double.MAX_VALUE);

		getFinalPosition();
		return positionList.stream().filter(rank -> rank.getPosition() <= 3).collect(Collectors.toList());
	}

	/**
	 * Get the final position
	 */
	private void getFinalPosition() {
		int finalPlacement = 1;
		positionList.get(0).setPosition(finalPlacement);
		for (int positionIndex = 1; positionIndex < positionList.size(); positionIndex++) {
			if (positionList.get(positionIndex).getTime() == positionList.get(positionIndex - 1).getTime()) {
				positionList.get(positionIndex).setPosition(finalPlacement);
			} else {
				positionList.get(positionIndex).setPosition(++finalPlacement);
			}
		}
	}

	/**
	 * calculate the power up
	 * 
	 * @param p
	 */
	private void getPowerUp(Participant p) {
		HorseRank rank = new HorseRank(0, p.getName(), TRACKLENGTH / p.getBaseSpeed());

		this.harryKart.getPowerUps().stream().forEach(loop -> getLanes(p.getLane(), loop).stream().forEach(l -> {
			int loopSpeed = p.getBaseSpeed() + l.getPowerValue();
			if (loopSpeed <= 0) {
				rank.setTime(rank.getTime() + Double.MAX_VALUE);
			} else {
				p.setBaseSpeed(p.getBaseSpeed() + l.getPowerValue());
				rank.setTime(rank.getTime() + (TRACKLENGTH / p.getBaseSpeed()));
			}
		}));
		positionList.add(rank);
	}

	private List<Lane> getLanes(int lane, Loop loop) {
		return loop.getLanes().stream().filter(l -> l.getNumber() == lane).collect(Collectors.toList());
	}
}
