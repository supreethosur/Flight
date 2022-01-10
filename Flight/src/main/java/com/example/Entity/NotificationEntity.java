package com.example.Entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NotificationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer messageId;
	private String subject;
	private String messagebody;
	private LocalDateTime timeOfEvent;
	private Integer userid;
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessagebody() {
		return messagebody;
	}
	public void setMessagebody(String messagebody) {
		this.messagebody = messagebody;
	}
	public LocalDateTime getTimeOfEvent() {
		return timeOfEvent;
	}
	public void setTimeOfEvent(LocalDateTime timeOfEvent) {
		this.timeOfEvent = timeOfEvent;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	
	
	
}
