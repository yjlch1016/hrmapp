package org.fkit.hrm.controller;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.fkit.hrm.domain.Document;
import org.fkit.hrm.domain.User;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.common.HrmConstants;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Description: 处理上传下载文件请求控制器
 */

@Controller
public class DocumentController {

	/**
	 * 自动注入UserService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	
	/**
	 * 处理/login请求
	 * */
	@RequestMapping(value="/document/selectDocument")
	 public String selectDocument(
			 Model model,Integer pageIndex,
			 @ModelAttribute  Document document){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息     */
		List<Document> documents = hrmService.findDocument(document, pageModel);
		model.addAttribute("documents", documents);
		model.addAttribute("pageModel", pageModel);
		return "document/document";
		
	}
	
	/**
	 * 处理添加请求
	 * @param String flag 标记， 1表示跳转到上传页面，2表示执行上传操作
	 * @param Notice notice  要添加的公告对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/document/addDocument")
	 public ModelAndView addDocument(
			 String flag,
			 @ModelAttribute Document document,
			 ModelAndView mv,
			 HttpSession session)throws Exception{
		if(flag.equals("1")){
			mv.setViewName("document/showAddDocument");
		}else{
			// 上传文件路径
			String path = session.getServletContext().getRealPath(
	                "/upload/");
			System.out.println(path);
			// 上传文件名
			String fileName = document.getFile().getOriginalFilename();
			 // 将上传文件保存到一个目标文件当中
			document.getFile().transferTo(new File(path+File.separator+ fileName));
			
			// 插入数据库
			// 设置fileName
			document.setFileName(fileName);
			// 设置关联的User对象
			User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
			document.setUser(user);
			// 插入数据库
			hrmService.addDocument(document);
			// 返回
			mv.setViewName("redirect:/document/selectDocument");
		}
		// 返回
		return mv;
	}
	
	/**
	 * 处理删除文档请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/document/removeDocument")
	 public ModelAndView removeDocument(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除文档
			hrmService.removeDocumentById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/document/selectDocument");
		// 返回ModelAndView
		return mv;
	}
	
	/**
	 * 处理修改文档请求
	 * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
	 * @param Document document 要修改文档的对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/document/updateDocument")
	 public ModelAndView updateDocument(
			 String flag,
			 @ModelAttribute Document document,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 根据id查询文档
			Document target = hrmService.findDocumentById(document.getId());
			// 设置Model数据
			mv.addObject("document", target);
			// 设置跳转到修改页面
			mv.setViewName("document/showUpdateDocument");
		}else{
			// 执行修改操作
			hrmService.modifyDocument(document);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/document/selectDocument");
		}
		// 返回
		return mv;
	}
	
	/**
	 * 处理文档下载请求
	 * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
	 * @param Document document 要修改文档的对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/document/downLoad")
	 public ResponseEntity<byte[]>  downLoad(
			 Integer id,
			 HttpSession session) throws Exception{
		// 根据id查询文档
		Document target = hrmService.findDocumentById(id);
		String fileName = target.getFileName();
		// 上传文件路径
		String path = session.getServletContext().getRealPath(
                "/upload/");
		// 获得要下载文件的File对象
		File file = new File(path+File.separator+ fileName);
		// 创建springframework的HttpHeaders对象
		HttpHeaders headers = new HttpHeaders();  
        // 下载显示的文件名，解决中文名称乱码问题  
        String downloadFielName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        // 通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFielName); 
        // application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
     // 201 HttpStatus.CREATED
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                headers, HttpStatus.CREATED); 
	}


}
