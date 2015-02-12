package WeatherData;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;


public class WeatherXMLParser {	
	
	static String url = "http://weather.yahooapis.com/forecastrss?w=2295420";
		
	static public  WeatherData weatherdata;
	
	public WeatherXMLParser() {
	
		weatherdata = new WeatherData();
	}
	
	public static WeatherData getWeatherData() throws Exception {
		try {
			InputStream inputStream = getConnection();
			KXmlParser xmlParser = createXmlParser(inputStream);
			parseWeatherXML(xmlParser);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		weatherdata.displayWeatherData();
		return weatherdata;
	}
	
	private static InputStream getConnection() throws IOException {
		HttpConnection httpconnection = (HttpConnection) Connector.open(url); 	
		InputStream inputstream = httpconnection.openInputStream();
		return inputstream;
	}
	
	private static  KXmlParser createXmlParser(InputStream inputstream) throws XmlPullParserException {
	   	Reader reader = new InputStreamReader(inputstream);
    	KXmlParser xmlparser = new KXmlParser();
    	xmlparser.setInput(reader);
    	return xmlparser;
	}
	
    private static  void parseWeatherXML(KXmlParser xmlparser) throws XmlPullParserException, IOException {
       	
    	System.out.println("****Inside Parser*******");
    	skipFisrtNode_RSS(xmlparser);
    	skipSecondNode_CHANNEL(xmlparser);
    	skipThirdNode_TITLE(xmlparser);

    	while (xmlparser.getEventType() != XmlPullParser.END_DOCUMENT) {
            String name = xmlparser.getName();
            if(name.equals("yweather:location")) {
            		parseWeatherLocation(xmlparser);
            }
            if (name.equals("item")) {
	            	parseWeatherItemTag(xmlparser);
            }	
            else {
                xmlparser.skipSubTree();
            }
            xmlparser.nextTag();
        }
  	   	   	
    }
	private static void skipFisrtNode_RSS(KXmlParser xmlparser) throws XmlPullParserException, IOException {
        xmlparser.nextTag();
        xmlparser.require(XmlPullParser.START_TAG, null, "rss");
	}
	
	private static void skipSecondNode_CHANNEL(KXmlParser xmlparser) throws XmlPullParserException, IOException {
        xmlparser.nextTag();
        xmlparser.require(XmlPullParser.START_TAG, null, "channel");
	}
	
	private static void skipThirdNode_TITLE(KXmlParser xmlparser) throws XmlPullParserException, IOException {
        xmlparser.nextTag();
        xmlparser.require(XmlPullParser.START_TAG, null, "title");
	}
	
    private static void parseWeatherLocation(KXmlParser xmlparser) throws XmlPullParserException, IOException {
    	
    	if(xmlparser.getName().equals("yweather:location")) {
      		weatherdata.City = xmlparser.getAttributeValue(0);
    		weatherdata.Country = xmlparser.getAttributeValue(2);
    	}
    }

	private static void parseWeatherItemTag(KXmlParser xmlparser) throws XmlPullParserException, IOException {
		
		while (!xmlparser.getName().equals("description") )  {
       		xmlparser.nextTag();
    		if(xmlparser.getName().equals("yweather:condition")) {
    				
  					weatherdata.condition = xmlparser.getAttributeValue(0);
        			weatherdata.teamprature_in_farenheit = xmlparser.getAttributeValue(2);
        			UpdateTeampratureInCelcius(weatherdata.teamprature_in_farenheit);
        			weatherdata.Date = xmlparser.getAttributeValue(3);
        			xmlparser.nextText();
        		}
    			else {
    			xmlparser.nextText();
    			}
    	}
		
	}

	private static void UpdateTeampratureInCelcius(String teamprature) {
		
		int teamprature_in_fareheit = Integer.parseInt(teamprature);
						
		weatherdata.teamprature_in_celcius = "" + (int)((teamprature_in_fareheit - 32 )*5/9);
	}

}