package se.atg.service.harrykart.java.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.xml.sax.SAXException;

import se.atg.service.harrykart.java.dto.HarryKart;
import se.atg.service.harrykart.java.dto.HorseRank;
import se.atg.service.harrykart.java.exception.HarryKartException;
import se.atg.service.harrykart.java.service.impl.HarryKartServiceImpl;
import se.atg.service.harrykart.java.service.impl.HarryXmlReader;
import se.atg.service.harrykart.java.util.Utility;

@ExtendWith(MockitoExtension.class)
class HarryKartServiceTest {

	@Mock
	HarryXmlReader harryXmlReader;

	@Mock
	HarryKartService service;
	
	@BeforeEach
	public void setUp() throws Exception {
		service = new HarryKartServiceImpl();
		harryXmlReader = new HarryXmlReader();
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
	void getHorseRankWithFinishedLane() throws HarryKartException, SAXException {
		String inputXML = readFileToString("finishedracetest.xml");
		HarryKart hk = harryXmlReader.getXmlData(inputXML);
		List<HorseRank> actualRanking = service.getHorseRank(hk);
		List<HorseRank> expectedRanking = Stream.of(
					new HorseRank(1, "TIMETOBELUCKY", 0.0), 
					new HorseRank(2, "CARGO DOOR", 0.0),
					new HorseRank(3, "HERCULES BOKO", 0.0)).collect(Collectors.toList());
		String resultJson = Utility.serializeToJson(actualRanking);
		String expectedJson = Utility.serializeToJson(expectedRanking);
		assertEquals(resultJson, expectedJson);
	}
	
	  /**
     * All participants finish at the same time
     * @throws HarryKartException
	 * @throws SAXException 
     */
    @Test
    void allWayTieTest() throws HarryKartException, SAXException {
        String inputXML = readFileToString("tietest.xml");
        HarryKart hk = harryXmlReader.getXmlData(inputXML);
        List<HorseRank> actualRanking = service.getHorseRank(hk);
        actualRanking.forEach(rank -> assertEquals(rank.getPosition(),1));
    }

   
    /**
     * When the base power is less than 1 on a loop, the participant hasn't completed the lap and is out of the race
     * @throws HarryKartException
     * @throws SAXException 
     */
    @Test
    void zeroAndNegativePowerTest() throws HarryKartException, SAXException {
        String inputXML = readFileToString("zeronegativepower.xml");
        HarryKart hk = harryXmlReader.getXmlData(inputXML);
        List<HorseRank> actualRanking = service.getHorseRank(hk);
        List<HorseRank> expectedRanking = Stream.of(
				        new HorseRank(1,"WAIKIKI SILVIO", 0.0),
				        new HorseRank(2,"HERCULES BOKO", 0.0)).collect(Collectors.toList());
        String resultJson = Utility.serializeToJson(actualRanking);
		String expectedJson = Utility.serializeToJson(expectedRanking);
        assertEquals(resultJson, expectedJson);
    }
	
	
}
