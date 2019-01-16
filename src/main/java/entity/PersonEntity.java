package entity;

import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import service.LocalDateConverter;


/**
 * The Class PersonEntity.  A Class that represents a simple {@code Person} Function.
 * The Data is stored in the table "person" in the DB.
 * 
 * @author gehry1
 * 
 */
@Entity
@Table(name="person")

public abstract class PersonEntity {
	
		/** The id. */
		@Id
		@GeneratedValue
		private int id;
		
		/** The last name. */
		private String lastName;
		
		/** The first name. */
		private String firstName;
		
		/** The gender. */
		private String gender;
		
		/** The address. */
		private String address;
		
		/** The city. */
		private String city;
		
		/** The nationality. */
		private String nationality;
		
		/** The language. */
		private String language;
		
		/** The phone number. */
		private String phoneNumber;
		
		/** The email. */
		private String email;
		
		/** The birthdate. */
		//converts the localdate for the DB
		@Convert(converter = LocalDateConverter.class)
		private LocalDate birthdate;
		

		
		
		/**
		 * Instantiates a new person entity.
		 */
		public PersonEntity() {
			super();			
		}
		
		/**
		 * Gets the pk.
		 *
		 * @return the pk
		 */
		public int getPK() {
			return this.id;
		}
		
		/**
		 * Gets the first name.
		 *
		 * @return the first name
		 */
		public String getFirstName() {
			return this.firstName;
		}
		
		/**
		 * Gets the last name.
		 *
		 * @return the last name
		 */
		public String getLastName() {
			return this.lastName;
		}
		
		/**
		 * Gets the birthdate.
		 *
		 * @return the birthdate
		 */
		public LocalDate getBirthdate() {
			return this.birthdate;
		}
		
		/**
		 * Gets the email.
		 *
		 * @return the email
		 */
		public String getEmail() {
			return this.email;
			
		}
		
		/**
		 * Sets the email.
		 *
		 * @param email the new email
		 */
		public void setEmail(String email) {
			this.email = email;
			
		}
		
		/**
		 * Sets the address.
		 *
		 * @param address the new address
		 */
		public void setAddress(String address) {
			this.address = address;
		}
		
		/**
		 * Gets the address.
		 *
		 * @return the address
		 */
		public String getAddress() {
			return this.address;
		}
		
		/**
		 * Sets the first name.
		 *
		 * @param aFirstName the new first name
		 */
		public void setFirstName(String aFirstName) {
			this.firstName = aFirstName;
		}
		
		/**
		 * Sets the last name.
		 *
		 * @param aLastName the new last name
		 */
		public void setLastName(String aLastName) {
			this.lastName = aLastName;
		}
		
		/**
		 * Sets the birthdate.
		 *
		 * @param localDate the new birthdate
		 */
		public void setBirthdate(LocalDate localDate) {
			this.birthdate = localDate;
		}


		/**
		 * Gets the gender.
		 *
		 * @return the gender
		 */
		public String getGender() {
			return gender;
		}


		/**
		 * Sets the gender.
		 *
		 * @param gender the new gender
		 */
		public void setGender(String gender) {
			this.gender = gender;
		}


		/**
		 * Gets the city.
		 *
		 * @return the city
		 */
		public String getCity() {
			return city;
		}


		/**
		 * Sets the city.
		 *
		 * @param city the new city
		 */
		public void setCity(String city) {
			this.city = city;
		}


		/**
		 * Gets the nationality.
		 *
		 * @return the nationality
		 */
		public String getNationality() {
			return nationality;
		}


		/**
		 * Sets the nationality.
		 *
		 * @param nationality the new nationality
		 */
		public void setNationality(String nationality) {
			this.nationality = nationality;
		}


		/**
		 * Gets the language.
		 *
		 * @return the language
		 */
		public String getLanguage() {
			return language;
		}


		/**
		 * Sets the language.
		 *
		 * @param language the new language
		 */
		public void setLanguage(String language) {
			this.language = language;
		}


		/**
		 * Gets the phone number.
		 *
		 * @return the phone number
		 */
		public String getPhoneNumber() {
			return phoneNumber;
		}


		/**
		 * Sets the phone number.
		 *
		 * @param phoneNumber the new phone number
		 */
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return this.lastName + " "+ this.firstName;
		}

}

