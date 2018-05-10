package application;

import org.jsoup.nodes.Node;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player {

	private final SimpleStringProperty initial;
	private final SimpleStringProperty fname;
	private final SimpleStringProperty lname;
	private final SimpleStringProperty playerNum;
	private final SimpleStringProperty position;
	private final SimpleStringProperty shoots;
	private final SimpleStringProperty height;
	private final SimpleStringProperty weight;
	private final SimpleStringProperty born;
	private final SimpleStringProperty birthplace;
	
	public Player(String initial, String fname, String lname, String playerNum, String position, String shoots, String height, String weight, String born, String birthplace) {
		
		this.initial = new SimpleStringProperty(initial);
		this.fname = new SimpleStringProperty(fname);
		this.lname = new SimpleStringProperty(lname);
		this.playerNum = new SimpleStringProperty(playerNum);
		this.position = new SimpleStringProperty(position);
		this.shoots = new SimpleStringProperty(shoots);
		this.height = new SimpleStringProperty(height);
		this.weight = new SimpleStringProperty(weight);
		this.born = new SimpleStringProperty(born);
		this.birthplace = new SimpleStringProperty(birthplace);
		
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
	
	public String getPlayerNum() {
		return playerNum.get();
	}
	
	public SimpleStringProperty playerNumProperty() {
		return playerNum;
	}
	
	public String getPosition() {
		return position.get();
	}
	
	public SimpleStringProperty positionProperty() {
		return position;
	}
	
	public String getShoots() {
		return shoots.get();
	}
	
	public SimpleStringProperty shootsProperty() {
		return shoots;
	}
	
	public String getHeight() {
		return height.get();
	}
	
	public SimpleStringProperty heightProperty() {
		return height;
	}
	
	public String getWeight() {
		return weight.get();
	}
	
	public SimpleStringProperty weightProperty() {
		return weight;
	}
	
	public String getBorn() {
		return born.get();
	}
	
	public SimpleStringProperty bornProperty() {
		return born;
	}
	
	public String getBirthplace() {
		return birthplace.get();
	}
	
	public SimpleStringProperty birthplaceProperty() {
		return birthplace;
	}
}
