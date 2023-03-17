package se.atg.service.harrykart.java.service.impl;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import se.atg.service.harrykart.java.dto.HarryKart;
import se.atg.service.harrykart.java.exception.HarryKartException;

public class HarryXmlReader {

	private XmlMapper xmlMapper;

	public HarryXmlReader() {
		super();
		this.xmlMapper = new XmlMapper();
	}

	/**
	 * Read xml data
	 * @param xml
	 * @return
	 * @throws HarryKartException
	 * @throws SAXException
	 */
	public HarryKart getXmlData(String xml) throws HarryKartException, SAXException {
		if (!isValidXml(xml)) {
			throw new HarryKartException("Wrong input xml.");
		}
		HarryKart harryKartObj = null;
		try {
			harryKartObj = xmlMapper.readValue(xml, HarryKart.class);
		} catch (IOException e) {
			throw new HarryKartException(e.getMessage());
		}

		return harryKartObj;

	}

	/**
	 * validate xml
	 * @param inputXml
	 * @return
	 * @throws SAXException
	 */
	private boolean isValidXml(String inputXml) throws SAXException {
		try {

			SAXParserFactory.newInstance().newSAXParser().getXMLReader().parse(new InputSource(new StringReader(inputXml)));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
