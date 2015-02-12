package WeatherData;


public class WeatherData {	
	
	public String City, Country;
	public String Date;
	public String condition;
	public String teamprature_in_celcius, teamprature_in_farenheit;

	public void displayWeatherData() {
		
		System.out.println("Date" + Date);
		System.out.println("teamprature_in_celcius" + teamprature_in_celcius);
		System.out.println("condition" + condition);
		System.out.println("City" + City);
		System.out.println("Country" + Country);
		
	}
}


