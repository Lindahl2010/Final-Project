package application;

import org.jsoup.nodes.Node;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayerInfo {

	private final SimpleStringProperty playerNum;
	private final SimpleStringProperty position;
	private final SimpleStringProperty shoots;
	private final SimpleStringProperty height;
	private final SimpleStringProperty weight;
	private final SimpleStringProperty born;
	private final SimpleStringProperty birthplace;
	private final SimpleLongProperty right;
	private final SimpleLongProperty left;
	
	public PlayerInfo(String playerNum, String position, String shoots, String height, String weight, String born, String birthplace, Long right, Long left) {
		
		this.playerNum = new SimpleStringProperty(playerNum);
		this.position = new SimpleStringProperty(position);
		this.shoots = new SimpleStringProperty(shoots);
		this.height = new SimpleStringProperty(height);
		this.weight = new SimpleStringProperty(weight);
		this.born = new SimpleStringProperty(born);
		this.birthplace = new SimpleStringProperty(birthplace);
		this.right = new SimpleLongProperty(right);
		this.left = new SimpleLongProperty(left);
		
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
	
	public Long getRight() {
		return right.get();
	}
	
	public SimpleLongProperty rightProperty() {
		return right;
	}
	
	public Long getLeft() {
		return left.get();
	}
	
	public SimpleLongProperty leftProperty() {
		return left;
	}
	
}
