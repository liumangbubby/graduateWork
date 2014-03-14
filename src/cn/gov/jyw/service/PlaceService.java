package cn.gov.jyw.service;

import java.util.List;

import cn.gov.jyw.dao.PlaceDAO;
import cn.gov.jyw.pojo.place;

public class PlaceService {
	private PlaceDAO placeDAO;

	public List<place> getPlaceAll() {
		return placeDAO.getPlaceAll();
	}

	public void addPlace(String place) {
		placeDAO.insertPlace(place);
	}

	public void deletePlace(int id) {
		placeDAO.deletePlace(id);
	}

	// ///////////////////////////////////////////////////

	public PlaceDAO getPlaceDAO() {
		return placeDAO;
	}

	public void setPlaceDAO(PlaceDAO placeDAO) {
		this.placeDAO = placeDAO;
	}

}
