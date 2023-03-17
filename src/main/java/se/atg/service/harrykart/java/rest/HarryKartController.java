package se.atg.service.harrykart.java.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import se.atg.service.harrykart.java.dto.HarryKart;
import se.atg.service.harrykart.java.dto.HorseRank;
import se.atg.service.harrykart.java.exception.HarryKartException;
import se.atg.service.harrykart.java.service.HarryKartService;
import se.atg.service.harrykart.java.service.impl.HarryXmlReader;
import se.atg.service.harrykart.java.util.Utility;

@RestController
@RequestMapping("/java/api")
public class HarryKartController {
	
	@Autowired
	private HarryKartService harryKartService;


	@PostMapping(path = "/play", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String playHarryKart(@RequestBody String xml) throws HarryKartException, SAXException {
    	
    	HarryXmlReader xmlReader = new HarryXmlReader();
    	//parse xml data
    	HarryKart harryKartObj = xmlReader.getXmlData(xml);
    	//get horse rank
    	List<HorseRank> horseRank = harryKartService.getHorseRank(harryKartObj);
    	// parse into json
        return Utility.serializeToJson(horseRank);
                
    }
	
	
}
