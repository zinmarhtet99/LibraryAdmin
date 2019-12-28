package com.example.libraryadmin.model;


import com.google.gson.annotations.SerializedName;

public class Book{

	@SerializedName("image")
	private String image;

	@SerializedName("category_id")
	private int categoryId;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("edition")
	private String edition;

	@SerializedName("publisher")
	private String publisher;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("author_id")
	private int authorId;

	@SerializedName("recommand")
	private String recommand;

	@SerializedName("status")
	private int status;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setCategoryId(int categoryId){
		this.categoryId = categoryId;
	}

	public int getCategoryId(){
		return categoryId;
	}

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public void setEdition(String edition){
		this.edition = edition;
	}

	public String getEdition(){
		return edition;
	}

	public void setPublisher(String publisher){
		this.publisher = publisher;
	}

	public String getPublisher(){
		return publisher;
	}

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setAuthorId(int authorId){
		this.authorId = authorId;
	}

	public int getAuthorId(){
		return authorId;
	}

	public void setRecommand(String recommand){
		this.recommand = recommand;
	}

	public String getRecommand(){
		return recommand;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}