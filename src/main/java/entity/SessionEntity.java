package entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "session")
public class SessionEntity {
	@Id
	private String sessionId;
	private String description;
	private Date date;
	private int craving;

	
	public SessionEntity(String sessionId, String description, Date date, int craving) {
		this.sessionId = sessionId;
		this.description = description;
		this.date = date;
		this.craving = craving;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getCraving() {
		return craving;
	}

	public void setCraving(int craving) {
		this.craving = craving;
	}
}
