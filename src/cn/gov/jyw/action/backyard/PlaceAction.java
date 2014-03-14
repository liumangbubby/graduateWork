package cn.gov.jyw.action.backyard;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import cn.gov.jyw.pojo.place;
import cn.gov.jyw.service.PlaceService;

public class PlaceAction {
	private PlaceService placeService;

	private int placeId;
	private String place;

	public String index() {
		List<place> list = placeService.getPlaceAll();
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("placeList", list);
		return "index";
	}

	public String delete() {
		placeService.deletePlace(placeId);
		return index();
	}
	
	public String insert(){
		placeService.addPlace(place);
		return index();
	}

	// //////////////////////////////////////////////////////////////////
	public PlaceService getPlaceService() {
		return placeService;
	}

	public void setPlaceService(PlaceService placeService) {
		this.placeService = placeService;
	}



	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	

}
