package org.fkit.hrm.controller;

import java.util.List;
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
 * @Description: 处理职位请求控制器
 */

@Controller
public class JobController {

	/**
	 * 自动注入UserService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/**
	 * 处理/login请求
	 * */
	@RequestMapping(value="/job/selectJob")
	 public String selectJob(Model model,Integer pageIndex,
			 @ModelAttribute Job job){
		System.out.println("selectJob -->> " + job);
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息     */
		List<Job> jobs = hrmService.findJob(job, pageModel);
		model.addAttribute("jobs", jobs);
		model.addAttribute("pageModel", pageModel);
		return "job/job";
		
	}
	
	/**
	 * 处理删除职位请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/job/removeJob")
	 public ModelAndView removeJob(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除职位
			hrmService.removeJobById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/job/selectJob");
		// 返回ModelAndView
		return mv;
	}
	
	/**
	 * 处理添加请求
	 * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
	 * @param Job  job  要添加的职位对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/job/addJob")
	 public ModelAndView addJob(
			 String flag,
			 @ModelAttribute Job job,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 设置跳转到添加页面
			mv.setViewName("job/showAddJob");
		}else{
			// 执行添加操作
			hrmService.addJob(job);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/job/selectJob");
		}
		// 返回
		return mv;
	}
	
	
	/**
	 * 处理修改职位请求
	 * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
	 * @param Job job 要修改部门的对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/job/updateJob")
	 public ModelAndView updateDpet(
			 String flag,
			 @ModelAttribute Job job,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 根据id查询部门
			Job target = hrmService.findJobById(job.getId());
			// 设置Model数据
			mv.addObject("job", target);
			// 设置跳转到修改页面
			mv.setViewName("job/showUpdateJob");
		}else{
			// 执行修改操作
			hrmService.modifyJob(job);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/job/selectJob");
		}
		// 返回
		return mv;
	}


}
