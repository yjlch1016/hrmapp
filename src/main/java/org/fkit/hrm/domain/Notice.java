package org.fkit.hrm.domain;

public class Notice implements java.io.Serializable{

	private Integer id;		// 编号
	private String title;   // 标题
	private String content; // 内容
	private java.util.Date createDate;  // 发布日期
	private User user;		// 发布人

	// 无参数构造器
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}

	// setter和getter方法
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return this.title;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return this.content;
	}
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", content=" + content
				+ ", createDate=" + createDate + ", user=" + user + "]";
	}


}