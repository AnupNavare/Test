package com.example.caronline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Cardata {

	private long id;

	private int yearOfManufacture;
	private int mileage;
	private int price;
	private String carModel;
	private String location;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getYearOfManufacture() {
		return yearOfManufacture;
	}

	public void setYearOfManufacture(int yearOfManufacture) {
		this.yearOfManufacture = yearOfManufacture;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Cardata [yearOfManufacture=" + yearOfManufacture + ", mileage="
				+ mileage + ", price=" + price + ", carModel=" + carModel
				+ ", location=" + location + "]";
	}

	public ArrayList<Cardata> sortCarByPrice(ArrayList<Cardata> car) {
		// TODO Auto-generated method stub

		Collections.sort(car, new Comparator<Cardata>() {
			@Override
			public int compare(Cardata lhs, Cardata rhs) {
				// TODO Auto-generated method stub
				String lhsPrice = "" + lhs.getPrice();
				String rhsPrice = "" + rhs.getPrice();
				return lhsPrice.compareTo(rhsPrice);
			}
		});
		return car;
	}

	public ArrayList<Cardata> sortCarByName(ArrayList<Cardata> car) {
		// TODO Auto-generated method stub
		Collections.sort(car, new Comparator<Cardata>() {
			@Override
			public int compare(Cardata lhs, Cardata rhs) {
				// TODO Auto-generated method stub
				String lhsCarName = lhs.getCarModel();
				String rhsCarName = rhs.getCarModel();
				return lhsCarName.compareTo(rhsCarName);
			}
		});
		return car;
	}

}
