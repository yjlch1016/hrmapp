package org.fkit.hrm.domain;

import org.springframework.format.annotation.DateTimeFormat;

public class Employee implements java.io.Serializable{

	private Integer id;			// id
	private Dept dept;			// 部门
	private Job job;			// 职位
	private String name;		// 名称
	private String cardId;		// 身份证
	private String address;		// 地址
	private String postCode;	// 邮政编码
	private String tel;			// 电话
	private String phone;		// 手机
	private String qqNum;		// qq
	private String email;		// 邮箱
	private Integer sex;		// 性别
	private String party;		// 政治面貌
	/**
	 *  使用@ModelAttribute接收参数时
	 *  form表单中有日期,Spring不知道该如何转换,
	 *  要在实体类的日期属性上加@DateTimeFormat(pattern="yyyy-MM-dd")注解 
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date birthday;	//生日
	private String race;				// 名族
	private String education;			// 学历
	private String speciality;			// 专业
	private String hobby;				// 爱好
	private String remark;				// 备注
	private java.util.Date createDate;	// 建档日期

	// 无参数构造器
	public Employee() {
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
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setCardId(String cardId){
		this.cardId = cardId;
	}
	public String getCardId(){
		return this.cardId;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return this.address;
	}
	public void setPostCode(String postCode){
		this.postCode = postCode;
	}
	public String getPostCode(){
		return this.postCode;
	}
	public void setTel(String tel){
		this.tel = tel;
	}
	public String getTel(){
		return this.tel;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return this.phone;
	}
	public void setQqNum(String qqNum){
		this.qqNum = qqNum;
	}
	public String getQqNum(){
		return this.qqNum;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return this.email;
	}
	public void setSex(Integer sex){
		this.sex = sex;
	}
	public Integer getSex(){
		return this.sex;
	}
	public void setParty(String party){
		this.party = party;
	}
	public String getParty(){
		return this.party;
	}
	public void setBirthday(java.util.Date birthday){
		this.birthday = birthday;
	}
	public java.util.Date getBirthday(){
		return this.birthday;
	}
	public void setRace(String race){
		this.race = race;
	}
	public String getRace(){
		return this.race;
	}
	public void setEducation(String education){
		this.education = education;
	}
	public String getEducation(){
		return this.education;
	}
	public void setSpeciality(String speciality){
		this.speciality = speciality;
	}
	public String getSpeciality(){
		return this.speciality;
	}
	public void setHobby(String hobby){
		this.hobby = hobby;
	}
	public String getHobby(){
		return this.hobby;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getRemark(){
		return this.remark;
	}
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", dept=" + dept + ", job=" + job
				+ ", name=" + name + ", cardId=" + cardId + ", address="
				+ address + ", postCode=" + postCode + ", tel=" + tel
				+ ", phone=" + phone + ", qqNum=" + qqNum + ", email=" + email
				+ ", sex=" + sex + ", party=" + party + ", birthday="
				+ birthday + ", race=" + race + ", education=" + education
				+ ", speciality=" + speciality + 
				", hobby=" + hobby + ", remark=" + remark + ", createDate="
				+ createDate + "]";
	}


}