package service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;




/**
 * The Class CountriesService is a static methode to get all countries and languages for the newPatientView.
 * 
 * @author gehry1
 */
public class CountriesService {

	 /**
 	 * Gets the all countries for the newPatientView combobox country.
 	 *
 	 * @return the all countries
 	 */
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
	 
	 /**
 	 * Gets the all languges for the newPatienView combobox languages.
 	 *
 	 * @return the all languges
 	 */
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
