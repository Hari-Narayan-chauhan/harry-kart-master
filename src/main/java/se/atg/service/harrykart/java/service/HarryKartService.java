package se.atg.service.harrykart.java.service;

import java.util.List;

import se.atg.service.harrykart.java.dto.HarryKart;
import se.atg.service.harrykart.java.dto.HorseRank;

public interface HarryKartService {
	
	List<HorseRank> getHorseRank(HarryKart harryKart);

}
