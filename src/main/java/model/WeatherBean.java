package model;

public class WeatherBean {
	private String city;
	private String country;
	private String cityWeather;
	private String clouds;
	
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

	public String getCityWeather() {
		return cityWeather;
	}

	public void setCityWeather(String cityWeather) {
		this.cityWeather = cityWeather;
	}

	public String getClouds() {
		return clouds;
	}

	public void setClouds(String clouds) {
		this.clouds = clouds;
	}
}
