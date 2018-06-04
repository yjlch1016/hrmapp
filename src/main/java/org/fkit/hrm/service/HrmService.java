package org.fkit.hrm.service;

import java.util.List;

import org.fkit.hrm.domain.Dept;
import org.fkit.hrm.domain.Document;
import org.fkit.hrm.domain.Employee;
import org.fkit.hrm.domain.Job;
import org.fkit.hrm.domain.Notice;
import org.fkit.hrm.domain.User;
import org.fkit.hrm.util.tag.PageModel;

/**   
 * @Description: 人事管理服务层接口
 */
public interface HrmService {


	/**
	 * 用户登录
	 * @param  loginname
	 * @param  password
	 * @return User对象
	 * */
	User login(String loginname, String password);
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return 用户对象
	 * */
	User findUserById(Integer id);
	
	/**
	 * 获得所有用户
	 * @return User对象的List集合
	 * */
	List<User> findUser(User user, PageModel pageModel);
	
	/**
	 * 根据id删除用户
	 * @param id
	 * */
	void removeUserById(Integer id);
	
	/**
	 * 修改用户
	 * @param User 用户对象
	 * */
	void modifyUser(User user);
	
	/**
	 * 添加用户
	 * @param User 用户对象
	 * */
	void addUser(User user);
	
	/**
	 * 获得所有员工
	 * @param employee 查询条件
	 * @param pageModel 分页对象
	 * @return Dept对象的List集合
	 * */
	List<Employee> findEmployee(Employee employee, PageModel pageModel);
	
	/**
	 * 根据id删除员工
	 * @param id
	 * */
	void removeEmployeeById(Integer id);
	
	/**
	 * 根据id查询员工
	 * @param id
	 * @return 员工对象
	 * */
	Employee findEmployeeById(Integer id);
	
	/**
	 * 添加员工
	 * @param employee 员工对象
	 * */
	void addEmployee(Employee employee);
	
	/**
	 * 修改员工
	 * @param employee 员工对象
	 * */
	void modifyEmployee(Employee employee);
	
	/**
	 * 获得所有部门，分页查询
	 * @return Dept对象的List集合
	 * */
	List<Dept> findDept(Dept dept, PageModel pageModel);
	
	/**
	 * 获得所有部门
	 * @return Dept对象的List集合
	 * */
	List<Dept> findAllDept();
	
	/**
	 * 根据id删除部门
	 * @param id
	 * */
	public void removeDeptById(Integer id);
	
	/**
	 * 添加部门
	 * @param dept 部门对象
	 * */
	void addDept(Dept dept);
	
	/**
	 * 根据id查询部门
	 * @param id
	 * @return 部门对象
	 * */
	Dept findDeptById(Integer id);
	
	/**
	 * 修改部门
	 * @param dept 部门对象
	 * */
	void modifyDept(Dept dept);
	
	/**
	 * 获得所有职位
	 * @return Job对象的List集合
	 * */
	List<Job> findAllJob();
	
	List<Job> findJob(Job job, PageModel pageModel);
	
	/**
	 * 根据id删除职位
	 * @param id
	 * */
	public void removeJobById(Integer id);
	
	/**
	 * 添加职位
	 * @param Job 部门对象
	 * */
	void addJob(Job job);
	
	/**
	 * 根据id查询职位
	 * @param id
	 * @return 职位对象
	 * */
	Job findJobById(Integer id);
	
	/**
	 * 修改职位
	 * @param dept 部门对象
	 * */
	void modifyJob(Job job);
	
	
	/**
	 * 获得所有公告
	 * @return Notice对象的List集合
	 * */
	List<Notice> findNotice(Notice notice, PageModel pageModel);
	
	/**
	 * 根据id查询公告
	 * @param id
	 * @return 公告对象
	 * */
	Notice findNoticeById(Integer id);
	
	/**
	 * 根据id删除公告
	 * @param id
	 * */
	public void removeNoticeById(Integer id);
	
	/**
	 * 添加公告
	 * @param Notice 公告对象
	 * */
	void addNotice(Notice notice);
	
	/**
	 * 修改公告
	 * @param Notice 公告对象
	 * */
	void modifyNotice(Notice notice);
	
	/**
	 * 获得所有文档
	 * @return Document对象的List集合
	 * */
	List<Document> findDocument(Document document, PageModel pageModel);
	
	/**
	 * 添加文档
	 * @param Document 文件对象
	 * */
	void addDocument(Document document);
	
	/**
	 * 根据id查询文档
	 * @param id
	 * @return 文档对象
	 * */
	Document findDocumentById(Integer id);
	
	/**
	 * 根据id删除文档
	 * @param id
	 * */
	public void removeDocumentById(Integer id);
	
	/**
	 * 修改文档
	 * @param Document 公告对象
	 * */
	void modifyDocument(Document document);
	
	
}
