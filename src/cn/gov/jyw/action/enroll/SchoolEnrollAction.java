package cn.gov.jyw.action.enroll;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import cn.gov.jyw.pojo.stu_enroll;
import cn.gov.jyw.service.SchoolEnrollService;

public class SchoolEnrollAction implements ServletContextAware {
	private ServletContext context;
	private File upload; // 文件
	private String uploadContentType; // 文件的内容类型

	private String uploadFileName; // 上传文件名
	private SchoolEnrollService schoolEnrollService;

	public SchoolEnrollService getSchoolEnrollService() {
		return schoolEnrollService;
	}

	public void setSchoolEnrollService(SchoolEnrollService schoolEnrollService) {
		this.schoolEnrollService = schoolEnrollService;
	}

	public String execute() throws Exception {
		String targetDirectory = context.getRealPath("/upload");
		String targetFileName = new Date().getTime() + ".xls";
		System.out.println(targetDirectory);
		File target = new File(targetDirectory, targetFileName);
		FileUtils.copyFile(upload, target);
		setUploadFileName(target.getPath());// 保存文件的存放路径
		// System.out.println(uploadFileName);

		boolean isError = schoolEnrollService.addEnroll(uploadFileName);
		if (isError) {
			ValueStack stack = ActionContext.getContext().getValueStack();
			stack.set("fileName", targetFileName);
			// 跳转到下载画面
			return "download";
		}

		return "success";
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;

	}

}
