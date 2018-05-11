package application;
	
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	public static void main(String[] args) throws IOException{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{	

		Parent parent = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		
		Scene scene = new Scene(parent);
		
		primaryStage.setTitle("NHL.com Team Scraper");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static Document searchTeam(String team) throws Exception{
		
		Document teamURL = null;
		Document doc = Jsoup.connect("https://www.nhl.com/info/teams#info_index").get();
		
		Elements links = doc.select("a.team-city");
		for(Element link : links) {
			if(link.attr("href").contains(team)) {
				teamURL = Jsoup.connect("https://www.nhl.com/" + team + "/roster").get();
			}
		}
		
		return teamURL;
	}
	
	public static String imageURL(Document doc) {
		
		return doc.getElementsByClass("a.megamenu-club-logobar__logo").select("img").attr("src");
		
	}

	public static ObservableList<TeamName> getTeamNames(Document doc){
		
		ObservableList<TeamName> teamName = FXCollections.observableArrayList();
		String city = null, name = null;
		Elements teams = doc.select("a.team-city");
		for(Element team : teams) {
			if(team.classNames() != null) {
				city = team.child(0).text();
				name = team.child(1).text();
			}
			teamName.add(new TeamName(city, name));
		}
		
		return teamName;
		
	}
	
	public static ObservableList<PlayerName> getPlayerNames(Document doc){
		
		ObservableList<PlayerName> playerNames = FXCollections.observableArrayList();
		
		Elements names = doc.select("div.name-col__list");
		for(Element name : names) {
			
			String initial = name.child(0).text();
			String fname = name.child(1).text();
			String lname = name.child(2).text();
			
			playerNames.add(new PlayerName(initial, fname, lname));
		}
		
		return playerNames;
		
	}
	
	public static ObservableList<PlayerInfo> getPlayerInfo(Document doc){
		
		ObservableList<PlayerInfo> playerInfo = FXCollections.observableArrayList();
		
		Elements data = doc.select("td.number-col.fixed-width-font");
		for(Element set : data) {
			Element div = set.parent();
			
			String playerNum = div.child(0).text();
			String position = div.child(1).text();
			String shoots = div.child(2).text();
			String height = div.child(3).child(0).text();
			String weight = div.child(4).text();
			String born = div.child(5).child(0).text();
			String birthplace = div.child(6).text();
			
			playerInfo.add(new PlayerInfo(playerNum, position, shoots, height, weight, born, birthplace));
		}
		
		return playerInfo;
		
	}
	
	
}
