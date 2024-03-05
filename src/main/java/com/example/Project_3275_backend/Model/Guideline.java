package com.example.Project_3275_backend.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "giudelines")
public class Guideline {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long guidelineId;
	
	@Column(name = "content")
	private String content;

	public Guideline() {
		
	}
	
	public Guideline(long guidelineId, String content) {
		super();
		this.guidelineId = guidelineId;
		this.content = content;
	}

	public long getGuidelineId() {
		return guidelineId;
	}

	public void setGuidelineId(long guidelineId) {
		this.guidelineId = guidelineId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
