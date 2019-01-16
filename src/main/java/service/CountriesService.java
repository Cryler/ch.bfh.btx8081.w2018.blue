package service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CountriesService {

	 public static List<String> getAllCountries() {
		 String[] countries = new String[Locale.getISOCountries().length];
		    String[] countryCodes = Locale.getISOCountries();
		    for (int i = 0; i < countryCodes.length; i++) {
		        Locale obj = new Locale("", countryCodes[i]);
		        countries[i] = obj.getDisplayCountry();
		    }
		     List<String> nationality = Arrays.asList(countries);
		     return nationality;
	 }
	 
	 public static List<String>getAllLanguges(){
		 String[] countries = new String[Locale.getISOCountries().length];
		    String[] countryCodes = Locale.getISOLanguages();
		    for (int i = 0; i < countryCodes.length; i++) {
		        Locale obj = new Locale("", countryCodes[i]);
		        countries[i] = obj.getDisplayLanguage();
		    }
		     List<String> languages = Arrays.asList(countryCodes);
		     return languages;
	 }
	
}
