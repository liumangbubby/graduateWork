package cn.gov.jyw.action.enroll;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.gov.jyw.action.enroll.vo.TbUserVO;
import cn.gov.jyw.pojo.stu_enroll;
import cn.gov.jyw.pojo.tb_user;
import cn.gov.jyw.service.StuEnrollService;
import cn.gov.jyw.service.TbUserService;

public class UserRegisterAction extends ActionSupport implements ModelDriven<TbUserVO>{
	private TbUserService userService;
	private StuEnrollService enrollService;
	private TbUserVO user;
	public TbUserService getUserService() {
		return userService;
	}
	public void setUserService(TbUserService userService) {
		this.userService = userService;
	}
	public StuEnrollService getEnrollService() {
		return enrollService;
	}
	public void setEnrollService(StuEnrollService enrollService) {
		this.enrollService = enrollService;
	}
	public TbUserVO getUser() {
		return user;
	}
	public void setUser(TbUserVO user) {
		this.user = user;
	}
	
	/////////////////////////////////////////
	public String createUser(){
		tb_user tbuser = new tb_user();
		tbuser.setUserName(user.getUsername());
		tbuser.setPaseWord(user.getPassword());
		stu_enroll enroll = enrollService.selectStuEnrollByPid(user.getPid(), null, null);
		if(enroll != null){
			tbuser.setEnrollId(enrollService.selectStuEnrollByPid(user.getPid(), null, null).getId());
		}
		userService.addUser(tbuser);
		return "success";
	}
	@Override
	public TbUserVO getModel() {
		// TODO Auto-generated method stub
		return new TbUserVO();
	}
}
