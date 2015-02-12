package UserInterface;

import java.io.IOException;

import WeatherData.WeatherData;
import WeatherData.WeatherXMLParser;

import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.Image;
import com.sun.lwuit.Label;
import com.sun.lwuit.layouts.CoordinateLayout;
import com.sun.lwuit.util.Resources;

public class WeatherScreen extends Form{

	public WeatherScreen() {
		System.out.println("****Inside WeatherScreen*******");
		setLayout(new CoordinateLayout(200, 200));
		setBackGround();
		DisplayWeather();
	}
	
	private void setBackGround() {
		try {
			System.out.println("****Inside SetBackGround*******");
			Image backgroundImage = Image.createImage("/res/NewYork.png");
			setBgImage(backgroundImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void DisplayWeather() {
		WeatherXMLParser weatherXMLParser = new WeatherXMLParser();
		WeatherData weatherData = new WeatherData();
		try {
			weatherData = weatherXMLParser.getWeatherData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DisplayTeamprature(weatherData.teamprature_in_celcius);
		DisplayConditions(weatherData.condition);
		DisplayDate(weatherData.Date);
		DisplayCity(weatherData.City);
		DisplayCountry(weatherData.Country);
	}
	
	
	private void DisplayTeamprature(String teamprature1) {
		Label teamprature = new Label(teamprature1 + " C");
		teamprature.setX(20);
		teamprature.setY(30);
		teamprature.getStyle().setBgTransparency(0);
		teamprature.getStyle().setFgColor(0xFFFFFF);
		SetWeatherFont(teamprature);
		addComponent(teamprature);
	}
	
	private void DisplayConditions(String condition) {
		
		Label condition_label = new Label(condition);
		condition_label.setX(20);
		condition_label.setY(60);
		condition_label.getStyle().setBgTransparency(0);
		condition_label.getStyle().setFgColor(0xFFFFFF);
		SetWeatherFont(condition_label);
		addComponent(condition_label);
	}
	
	private void DisplayDate(String Date) {
		
		Label Date_label = new Label(Date);
		Date_label.setX(30);
		Date_label.setY(150);
		Date_label.getStyle().setBgTransparency(0);
		Date_label.getStyle().setFgColor(0xFFFFFF);
		SetWeatherFont(Date_label);
		addComponent(Date_label);
	}
	
	private void DisplayCity(String City) {
		
		Label City_label = new Label(City);
		City_label.setX(120);
		City_label.setY(20);
		City_label.getStyle().setBgTransparency(0);
		City_label.getStyle().setFgColor(0xFFFFFF);
		SetWeatherFont(City_label);
		addComponent(City_label);
	}
	private void DisplayCountry(String Country) {
		
		Label Country_label = new Label(Country);
		Country_label.setX(120);
		Country_label.setY(40);
		Country_label.getStyle().setBgTransparency(0);
		Country_label.getStyle().setFgColor(0xFFFFFF);
		SetWeatherFont(Country_label);
		addComponent(Country_label);
	}
	
	private void SetWeatherFont(Label weatherlabel) {
		
		try {
			Resources resources = Resources.open("/res/WeatherFont.res");
			Font teamprature_font = resources.getFont("TeampratureFont");
			weatherlabel.getStyle().setFont(teamprature_font);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}

