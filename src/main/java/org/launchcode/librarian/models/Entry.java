package org.launchcode.librarian.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "entry")
public class Entry extends AbstractEntity {

	private String title;
	private String creator;
	private String genre;
	private String body;
	private User author;
	private Date created;
	private Date modified;
	
	public Entry() {}
	
	public Entry(String title, String creator, String genre, String body, User author) {
		
		super();
		
		this.title = title;
		this.creator = creator;
		this.genre = genre;
		this.body = body;
		this.author = author;
		this.created = new Date();
		this.updated();
		
		author.addEntry(this);
	}
	
	
	@NotNull
    @Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		this.updated();
	}
	
	@NotNull
	@Column(name = "creator")
	public String getCreator() {
		return creator;
	}
	
	public void setCreator(String creator) {
		this.creator = creator;
		this.updated();
	}
	
	@NotNull
	@Column(name = "genre")
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
		this.updated();
	}
	
	@NotNull
    @Column(name = "body")
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
		this.updated();
	}
	
	@ManyToOne
	public User getAuthor() {
		return author;
	}
	
	@SuppressWarnings("unused")
	private void setAuthor(User author) {
		this.author = author;
	}
	
	@NotNull
	@OrderColumn
	@Column(name = "created")
	public Date getCreated() {
		return created;
	}
	
	@SuppressWarnings("unused")
	private void setCreated(Date created) {
		this.created = created;
	}
	
	@NotNull
	@Column(name = "modified")  // you could do nullable = false which would in lieu of the above @NotNull annotation (see Persistence video21:00)
	public Date getModified() {
		return modified;
	}
	
	@SuppressWarnings("unused")
	private void setModified(Date modified) {
		this.modified = modified;
	}
	
	private void updated() {
		this.modified = new Date();
	}
	
}