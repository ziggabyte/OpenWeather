package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.WeatherBean;
import model.WeatherGetter;

/**
 * Servlet implementation class OWServlet
 */
@WebServlet("/OWServlet")
public class OWServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OWServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String city = request.getParameter("city");
		String country = request.getParameter("country");
				
		checkExistingCookies(request, response, city, country);
		request = addWeatherToRequest(request, city, country);
		forwardRequest(request, response);	
	}
	
	private HttpServletRequest addWeatherToRequest(HttpServletRequest request, String city, String country) throws IOException {
		WeatherBean weatherBean = new WeatherBean(city, country);
		WeatherGetter.getWeather(weatherBean);
		request.setAttribute("weatherBean", weatherBean);
		return request;
	}
	
	private void forwardRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}
	
	private void checkExistingCookies(HttpServletRequest request, HttpServletResponse response, String city, String country) {
		Cookie[] cookies = request.getCookies();
		
		System.out.println(cookies);
		
		if (cookies != null) {
			if (cookies.length > 1) {
				if (!cookies[1].getValue().contains(city + ":" + country)) {
					addToCookie(cookies[1],  response, city + ":" + country + "-");
				}
			} else {
				newCookie(response, "City&Country", city + ":" + country + "-");
			}
		}
		
		
	}
	
	private void newCookie(HttpServletResponse response, String key, String value) {
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(600);
		response.addCookie(cookie);
	}
	
	private void addToCookie(Cookie cookie, HttpServletResponse response, String value ) {
		cookie.setValue(cookie.getValue() + value);
		cookie.setMaxAge(600);
		response.addCookie(cookie);
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
