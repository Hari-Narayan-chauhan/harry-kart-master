package se.atg.service.harrykart.java.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import se.atg.service.harrykart.java.dto.HorseRank;

public class JsonResponseSerializer extends JsonSerializer<HorseRank>{

	@Override
	public void serialize(HorseRank value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("Position", value.getPosition());
		gen.writeStringField("Horse Name", value.getHorseName());
		gen.writeEndObject();
	    }
		
	}

