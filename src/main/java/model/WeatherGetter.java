package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class WeatherGetter {

	public static void getWeather(WeatherBean wBean) throws IOException{
		Document doc = convertStringToXMLDocument(getApiResponse(wBean));

		wBean.setClouds(getXMLAttribute(doc, "clouds", "name"));
		wBean.setTemperature(getXMLAttribute(doc, "temperature", "value"));
		wBean.setLocalTime(getXMLAttribute(doc, "lastupdate", "value"));
	}
	
	private static URL createURL(WeatherBean wBean) throws MalformedURLException {
		String URLtoSend = "http://api.openweathermap.org/data/2.5/weather?q=" 
				+ wBean.getCity() + ","
				+ wBean.getCountry() + "&APPID=b0b73b21e2a3f1dc048905b0174645a6&mode=xml";

		return new URL(URLtoSend);
	}
	
	private static HttpURLConnection getHttpConnection(WeatherBean wBean) throws IOException {
		HttpURLConnection linec = (HttpURLConnection) createURL(wBean).openConnection();
		linec.setDoInput(true);
		linec.setDoOutput(true);
		linec.setRequestMethod("GET");
		
		return linec;
	}

	private static String getApiResponse(WeatherBean wBean) throws IOException {
		HttpURLConnection linec = getHttpConnection(wBean);
		BufferedReader in = new BufferedReader(new InputStreamReader(linec.getInputStream()));

		String inputLine;
		String ApiResponse = "";

		while ((inputLine = in.readLine()) != null) {
			ApiResponse += inputLine;
		}
				
		in.close();
		return ApiResponse;
	}

	private static Document convertStringToXMLDocument(String xmlString) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			doc.getDocumentElement().normalize();
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String getXMLAttribute(Document doc, String tagName, String attributeName) {
		NodeList nodeListTagName = doc.getElementsByTagName(tagName);
		String attribute = null;	
		for (int i = 0; i < nodeListTagName.getLength(); i++) {
			if (nodeListTagName.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nodeListTagName.item(i);
				attribute = eElement.getAttribute(attributeName);
			}
		}
		return attribute;
	}

}
