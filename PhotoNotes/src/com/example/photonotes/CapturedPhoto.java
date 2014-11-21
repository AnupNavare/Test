package com.example.photonotes;

public class CapturedPhoto {

	private int _id;
	private String caption;
	private String photoPath;

	public CapturedPhoto() {
	}

	public CapturedPhoto(String caption, String path) {
		this.caption = caption;
		photoPath = path;
	}

	public CapturedPhoto(int _id, String caption, String path) {
		// TODO Auto-generated constructor stub
		this._id = _id;
		this.caption = caption;
		photoPath = path;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

}
