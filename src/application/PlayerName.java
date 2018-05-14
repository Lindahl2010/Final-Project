package application;

import javafx.beans.property.SimpleStringProperty;

public class PlayerName {

	private final SimpleStringProperty initial;
	private final SimpleStringProperty fname;
	private final SimpleStringProperty lname;
	
	public PlayerName(String initial, String fname, String lname) {
		
		this.initial = new SimpleStringProperty(initial);
		this.fname = new SimpleStringProperty(fname);
		this.lname = new SimpleStringProperty(lname);
	
	}

	public String getInitial() {
		return initial.get();
	}
	
	public SimpleStringProperty initialProperty() {
		return initial;
	}
	
	public String getFname() {
		return fname.get();
	}
	
	public SimpleStringProperty fnameProperty() {
		return fname;
	}
	
	public String getLname() {
		return lname.get();
	}
	
	public SimpleStringProperty lnameProperty() {
		return lname;
	}
}
