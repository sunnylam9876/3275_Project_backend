package com.example.Project_3275_backend.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "articles")
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long articleId;
	
	@Column(name = "title")
	private String title;
	
	@Lob // Use @Lob annotation to specify TEXT data type
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
//	@Column(name = "content")
//	private String content;
	
//	@ManyToOne
//    @JoinColumn(name = "userId", referencedColumnName = "userId")
//    private User user;
	@Column(name = "userId")
	private long userId;
	
	@Column(name = "noOfView")
	private int noOfView;
	
	@Column(name = "noOfLike")
	private int noOfLike;

	
	// Constructors	
	public Article() {
		
	}

	public Article(String title, String content, long userId) {
		this.title = title;
		this.content = content;
		this.userId = userId;
	}

	// Getters and Setters
	public long getarticleId() {
		return articleId;
	}

	public void setarticleId(long articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getNoOfView() {
		return noOfView;
	}

	public void setNoOfView(int noOfView) {
		this.noOfView = noOfView;
	}

	public int getNoOfLike() {
		return noOfLike;
	}

	public void setNoOfLike(int noOfLike) {
		this.noOfLike = noOfLike;
	}
	
	
	
//	public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
	
	
	
}

//title, content, category, numberOfView, numberOfLike, createdAt