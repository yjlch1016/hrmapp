package org.fkit.hrm.controller;

import java.util.List;
import org.fkit.hrm.domain.Dept;
import org.fkit.hrm.domain.Employee;
import org.fkit.hrm.domain.Job;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Description: 处理员工请求控制器
 */
@Controller
public class EmployeeController {
	/**
	 * 自动注入hrmService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
			
	/**
	 * 处理查询请求
	 * @param pageIndex 请求的是第几页
	 * @param String job_id 职位编号
	 * @param String dept_id 部门编号
	 * @param employee 模糊查询参数
	 * @param Model model
	 * */
	@RequestMapping(value="/employee/selectEmployee")
	 public String selectEmployee(Integer pageIndex,
			 Integer job_id,Integer dept_id,
			 @ModelAttribute Employee employee,
			 Model model){
		// 模糊查询时判断是否有关联对象传递，如果有，创建并封装关联对象
		this.genericAssociation(job_id, dept_id, employee);
		// 创建分页对象
		PageModel pageModel = new PageModel();
		// 如果参数pageIndex不为null，设置pageIndex，即显示第几页
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		// 查询职位信息，用于模糊查询
		List<Job> jobs = hrmService.findAllJob();
		// 查询部门信息 ，用于模糊查询
		List<Dept> depts = hrmService.findAllDept();
		// 查询员工信息    
		List<Employee> employees = hrmService.findEmployee(employee,pageModel);
		// 设置Model数据
		model.addAttribute("employees", employees);
		model.addAttribute("jobs", jobs);
		model.addAttribute("depts", depts);
		model.addAttribute("pageModel", pageModel);
		// 返回员工页面
		return "employee/employee";
		
	}
	
	/**
	 * 处理添加员工请求
	 * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
	 * @param String job_id 职位编号
	 * @param String dept_id 部门编号
	 * @param Employee employee 接收添加参数
	 * @param ModelAndView mv 
	 * */
	@RequestMapping(value="/employee/addEmployee")
	 public ModelAndView addEmployee(
			 String flag,
			 Integer job_id,Integer dept_id,
			 @ModelAttribute Employee employee,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 查询职位信息
			List<Job> jobs = hrmService.findAllJob();
			// 查询部门信息 
			List<Dept> depts = hrmService.findAllDept();
			// 设置Model数据
			mv.addObject("jobs", jobs);
			mv.addObject("depts", depts);
			// 返回添加员工页面
			mv.setViewName("employee/showAddEmployee");
		}else{
			// 判断是否有关联对象传递，如果有，创建关联对象
			this.genericAssociation(job_id, dept_id, employee);
			// 添加操作
			hrmService.addEmployee(employee);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/employee/selectEmployee");
		}
		// 返回
		return mv;
		
	}
	
	/**
	 * 处理删除员工请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/employee/removeEmployee")
	 public ModelAndView removeEmployee(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除员工
			hrmService.removeEmployeeById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
//		mv.setView(new RedirectView("/hrmapp/employee/selectEmployee"));
//		mv.setViewName("forward:/employee/selectEmployee");
		mv.setViewName("redirect:/employee/selectEmployee");
		// 返回ModelAndView
		return mv;
	}
	
	/**
	 * 处理修改员工请求
	 * @param String flag 标记，1表示跳转到修改页面，2表示执行修改操作
	 * @param String job_id 职位编号
	 * @param String dept_id 部门编号
	 * @param Employee employee  要修改员工的对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/employee/updateEmployee")
	 public ModelAndView updateEmployee(
			 String flag,
			 Integer job_id,Integer dept_id,
			 @ModelAttribute Employee employee,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 根据id查询员工
			Employee target = hrmService.findEmployeeById(employee.getId());
			// 需要查询职位信息 
			List<Job> jobs = hrmService.findAllJob();
			// 需要查询部门信息 
			List<Dept> depts = hrmService.findAllDept();
			// 设置Model数据
			mv.addObject("jobs", jobs);
			mv.addObject("depts", depts);
			mv.addObject("employee", target);
			// 返回修改员工页面
			mv.setViewName("employee/showUpdateEmployee");
		}else{
			// 创建并封装关联对象
			this.genericAssociation(job_id, dept_id, employee);
			System.out.println("updateEmployee -->> " + employee);
			// 执行修改操作
			hrmService.modifyEmployee(employee);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/employee/selectEmployee");
		}
		// 返回
		return mv;
	}
	
	/**
	 * 由于部门和职位在Employee中是对象关联映射，
	 * 所以不能直接接收参数，需要创建Job对象和Dept对象
	 * */
	private void genericAssociation(Integer job_id,
			Integer dept_id,Employee employee){
		if(job_id != null){
			Job job = new Job();
			job.setId(job_id);
			employee.setJob(job);
		}
		if(dept_id != null){
			Dept dept = new Dept();
			dept.setId(dept_id);
			employee.setDept(dept);
		}
	}


}
