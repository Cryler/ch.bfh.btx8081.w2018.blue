package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Observable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name="person")
@NamedQuery(name="person.findAll", query="SELECT p FROM Person p")
public abstract class PersonEntity {
	
		@Id
		@GeneratedValue
		private int id;
		
		private String lastName;
		private String firstName;
		private LocalDate birthdate;
		private String gender;
		private String address;
		private String city;
		private String nationality;
		private String language;
		private String phoneNumber;
		private String email;
		
//		@Embedded
//		private Address address;
		
		
		public PersonEntity() {
			super();
			
		}
		
		
		public String getFirstName() {
			return this.firstName;
		}
		
		public String getLastName() {
			return this.lastName;
		}
		
		public LocalDate getBirthdate() {
			return this.birthdate;
		}
		
		public String getEmail() {
			return this.email;
			
		}
		
		public void setEmail(String email) {
			this.email = email;
			
		}
		
		public String getAddress() {
			return this.address;
		}
		
		public void setFirstName(String aFirstName) {
			this.firstName = aFirstName;
		}
		
		public void setLastName(String aLastName) {
			this.lastName = aLastName;
		}
		
		public void setBirthdate(LocalDate localDate) {
			this.birthdate = localDate;
		}


		public String getGender() {
			return gender;
		}


		public void setGender(String gender) {
			this.gender = gender;
		}


		public String getCity() {
			return city;
		}


		public void setCity(String city) {
			this.city = city;
		}


		public String getNationality() {
			return nationality;
		}


		public void setNationality(String nationality) {
			this.nationality = nationality;
		}


		public String getLanguage() {
			return language;
		}


		public void setLanguage(String language) {
			this.language = language;
		}


		public String getPhoneNumber() {
			return phoneNumber;
		}


		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

}

