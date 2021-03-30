package model;

public class WeatherBean {
	private String city;
	private String country;
	private String temperature;
	private String clouds;
	private String localTime;
	
	public WeatherBean(String city, String country) {
		this.city = city;
		this.country = country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String getCity() {
		return city;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperatureInKelvin) {
		int temperatureInCelsius = (int) Math.floor((Double.valueOf(temperatureInKelvin) - 273.15));
		this.temperature = String.valueOf(temperatureInCelsius);
	}

	public String getClouds() {
		return clouds;
	}

	public void setClouds(String clouds) {
		this.clouds = clouds;
	}

	public String getLocalTime() {
		return localTime;
	}

	public void setLocalTime(String lastUpdated) {
		String localTime = lastUpdated.substring(0, lastUpdated.indexOf("T"));
		this.localTime = localTime;
	}
}
