package cn.gov.jyw.action.backyard;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import cn.gov.jyw.service.JoinStateService;

public class JoinStateAction {
	private JoinStateService joinStateService;
	private String[] joinStateId;
	private String state;

	public String index() {
		List list = joinStateService.getAll();
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("joList", list);
		return "success";
	}

	public String delete() {
		if(joinStateId==null||joinStateId.length==0){
			return "error";
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < joinStateId.length; i++) {
			list.add(Integer.parseInt(joinStateId[i]));
		}
		joinStateService.deleteState(list);
		return index();
	}

	public String insert() {
		if(state==null||state.length()==0){
			return "error";
		}
		joinStateService.addState(state);
		state = null;
		return index();
	}

	public JoinStateService getJoinStateService() {
		return joinStateService;
	}

	public void setJoinStateService(JoinStateService joinStateService) {
		this.joinStateService = joinStateService;
	}

	public String[] getJoinStateId() {
		return joinStateId;
	}

	public void setJoinStateId(String[] joinStateId) {
		this.joinStateId = joinStateId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
