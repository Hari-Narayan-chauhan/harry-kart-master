package se.atg.service.harrykart.java.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import se.atg.service.harrykart.java.dto.HorseRank;
import se.atg.service.harrykart.java.rest.HarryKartController;
import se.atg.service.harrykart.java.service.HarryKartService;
import se.atg.service.harrykart.java.service.impl.HarryXmlReader;

@ExtendWith(MockitoExtension.class)
class HarryKartControllerTest {

	@InjectMocks
	HarryKartController controller;

	@Mock
	HarryXmlReader harryXmlReader;

	@Mock
	HarryKartService service;

	private MockMvc mockMvc;
	private static HttpHeaders httpHeaders;
	private static ObjectMapper objectMapper = new ObjectMapper();

	@BeforeAll
	static void init() throws IOException {
		httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_XML);
		httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@BeforeEach
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	private String readFileToString(String filename) {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(filename);
		Objects.requireNonNull(in);
		String xmlString = "";
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			xmlString = br.lines().collect(Collectors.joining(System.lineSeparator()));
		} catch (IOException e) {
			e.printStackTrace();
			return xmlString;
		}
		return xmlString;
	}

	@Test
	void harryKartControllerTest() throws Exception {
		String inputXML = readFileToString("finishedracetest.xml");

		List<HorseRank> expectedRanking = Stream.of(
				new HorseRank(1, "TIMETOBELUCKY", 216.66666666666669), 
				new HorseRank(2, "CARGO DOOR", 226.984126984127),
				new HorseRank(3, "HERCULES BOKO", 239.4230769230769)).collect(Collectors.toList());

		when(service.getHorseRank(any())).thenReturn(expectedRanking);

		mockMvc.perform(MockMvcRequestBuilders.post("/java/api/play").content(inputXML).headers(httpHeaders)).andExpect(status().isOk());
	}

}
