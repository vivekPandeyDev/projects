package com.webservices.formatter;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.webservices.exception.UnAvailableException;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

	private static final long serialVersionUID = -6684458715395228415L;

	protected LocalDateDeserializer() {
		super(LocalDate.class);
	}

	@Override
	public LocalDate deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		try {
			return LocalDate.parse(parser.readValueAs(String.class));
		} catch (Exception e) {
			throw new UnAvailableException(HttpStatus.BAD_REQUEST, "date format should be yyyy-mm-dd");
		}
	}

}
