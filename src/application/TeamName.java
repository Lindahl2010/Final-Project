package application;

import javafx.beans.property.SimpleStringProperty;

public class TeamName {

	private final SimpleStringProperty city;
	private final SimpleStringProperty name;
	
	public TeamName(String city, String name) {
		this.city = new SimpleStringProperty(city);
		this.name = new SimpleStringProperty(name);
	}
	
	public String getCity() {
		return city.get();
	}
	
	public SimpleStringProperty cityProperty() {
		return city;
	}
	
	public String getName() {
		return name.get();
	}
	
	public SimpleStringProperty nameProperty() {
		return name;
	}
}
