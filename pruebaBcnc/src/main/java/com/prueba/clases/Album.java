package com.prueba.clases;

public class Album {

	private Integer userId;
	private Integer id;
	private String title;

	public Album(Integer userId, Integer id, String title) {
		super();
		this.userId = userId;
		this.id = id;
		this.title = title;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Album [userId=" + userId + ", id=" + id + ", title=" + title + "]";
	}

}
