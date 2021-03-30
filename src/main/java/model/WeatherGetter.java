package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class WeatherGetter {

	public static void getWeather(WeatherBean wBean) throws IOException {

		// Build the API call URL by adding city+country into a URL
		String URLtoSend = "http://api.openweathermap.org/data/2.5/weather?q=" 
				+ wBean.getCity() + ","
				+ wBean.getCountry() + "&APPID=b0b73b21e2a3f1dc048905b0174645a6&mode=xml";

		// Set the URL that will be sent
		URL line_api_url = new URL(URLtoSend);

		// Create a HTTP connection to sent the GET request over
		HttpURLConnection linec = (HttpURLConnection) line_api_url.openConnection();
		linec.setDoInput(true);
		linec.setDoOutput(true);
		linec.setRequestMethod("GET");

		// Make a Buffer to read the response from the API
		BufferedReader in = new BufferedReader(new InputStreamReader(linec.getInputStream()));

		// a String to temp save each line in the response
		String inputLine;

		// a String to save the full response to use later
		String ApiResponse = "";

		// loop through the whole response
		while ((inputLine = in.readLine()) != null) {
			ApiResponse += inputLine;
		}
		
		in.close();

		// Call a method to make a XMLdoc out of the full response
		Document doc = convertStringToXMLDocument(ApiResponse);

		// normalize the XML response
		doc.getDocumentElement().normalize();

		wBean.setClouds(getXMLAttribute(doc, "clouds", "name"));
		wBean.setTemperature(getXMLAttribute(doc, "temperature", "value"));
		wBean.setLocalTime(getXMLAttribute(doc, "lastupdate", "value"));
	}
	
	private static String getXMLAttribute(Document doc, String tagName, String attributeName) {
				NodeList nodeListTagName = doc.getElementsByTagName(tagName);
				String attribute = null;
				
				for (int i = 0; i < nodeListTagName.getLength(); i++) {
					Node node = nodeListTagName.item(i);
					
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) node;
						attribute = eElement.getAttribute(attributeName);
					}
				}
		
		return attribute;
	}

	// Method the makes a XML doc out of a string, if it is in a XML format.
	private static Document convertStringToXMLDocument(String xmlString) {
		// Parser that produces DOM object trees from XML content
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// API to obtain DOM Document instance
		DocumentBuilder builder = null;
		try {
			// Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();

			// Parse the content to Document object
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
