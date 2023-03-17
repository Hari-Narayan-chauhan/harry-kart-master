package se.atg.service.harrykart.java.util;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import se.atg.service.harrykart.java.dto.HorseRank;

public class Utility {

	private Utility() {
		throw new IllegalStateException("Utility class");
	}

	public static String serializeToJson(List<HorseRank> ranking) {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule sm = new SimpleModule();
		sm.addSerializer(HorseRank.class, new JsonResponseSerializer());
		mapper.registerModule(sm);
		String jsonRanking = "[]";
		try {
			jsonRanking = mapper.writeValueAsString(ranking);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{\"json-processing-error\": " + e.getMessage() + " }";
		}
		return "{\"ranking\": " + jsonRanking + " }";
	}

}
