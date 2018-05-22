package application;
	
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	

	
	@Override
	public void start(Stage primaryStage) throws Exception{	

		Parent parent = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		
		Scene scene = new Scene(parent);
		
		primaryStage.setTitle("NHL.com Team Scraper");
		primaryStage.getIcons().add(new Image("NHL-Logo.png"));	
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) throws IOException{
		launch(args);
	}
	
	/**
	 * This method connects to the NHL website and searches for the team specified by the user.
	 * @param team
	 * @return teamURL URL of the team selected by the user through their input into the program.
	 * @throws Exception
	 */
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
	
	public static void createNamesDB(ObservableList<PlayerName> playerList) {
		
		try {
			String DB_URL = "jdbc:mysql://db4free.net:3306/nhlteams";
			String USER = "lindahl2010";
			String PASSWORD = "$JymFM8u";
			
			Connection myConn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
//			Statement drop_stmt = myConn.createStatement();
//			String drop = "DROP TABLE TeamTable";
//			drop_stmt.executeUpdate(drop);
			
			Statement stmt = myConn.createStatement();
			String create = "CREATE TABLE TeamTable" + 
					" (num INTEGER not NULL, " +
					" Initial VARCHAR(255), " + 
					" FirstName VARCHAR(255), " +
					" LastName VARCHAR(255), " + 
					" PRIMARY KEY ( num ))";
			
			stmt.executeUpdate(create);
			
			for(int num = 0; num < playerList.size(); num++) {
				PlayerName initial = playerList.get(num);
				PlayerName fname = playerList.get(num);
				PlayerName lname = playerList.get(num);
				String insert = "INSERT INTO TeamTable" + 
						"VALUES ('"+num+"', '"+initial+"', '"+fname+"', '"+lname+"')";
				stmt.executeUpdate(insert);
			}
		}catch(SQLException i) {
			i.printStackTrace();
		}	
		
	}
	
	/**
	 * 
	 * @param playerList
	 */
	public static void createPlayerDB(ArrayList<String> playerList) {
		
		try {
			String DB_URL = "jdbc:mysql://db4free.net:3306/nhlteams";
			String USER = "lindahl2010";
			String PASSWORD = "$JymFM8u";
			
			Connection myConn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			Statement drop_stmt = myConn.createStatement();
			String drop = "DROP TABLE TeamTable";
			drop_stmt.executeUpdate(drop);
			
			Statement stmt = myConn.createStatement();
			String create = "CREATE TABLE TeamTable" + 
					" (num INTEGER not NULL, " +
					" ";
			
		}catch(SQLException i) {
			i.printStackTrace();
		}
		
		
		
	}

	/**
	 * This method searches through the team page on the NHL website and creates a list of all the teams.
	 * @param teamPage
	 * @return teamName Returns a list of the team names off of the NHL team page
	 * @throws Exception
	 */
	public static ObservableList<TeamName> getTeamNames(Document teamPage) throws Exception{
		
		ObservableList<TeamName> teamName = FXCollections.observableArrayList();
		String city = null, name = null;
		
		Elements teams = teamPage.select("a.team-city");
		for(Element team : teams) {
			if(team.classNames() != null) {
				city = team.child(0).text();
				name = team.child(1).text();
			}
			teamName.add(new TeamName(city, name));
		}
		
		return teamName;
		
	}
	
	/**
	 * This method is used to pull all of the names of the players from the team that the user specifies.
	 * @param doc
	 * @return playerNames Returns a list of the player names on the specified team.
	 */
	public static ObservableList<PlayerName> getPlayerNames(Document doc){
		
		ObservableList<PlayerName> playerNames = FXCollections.observableArrayList();
		
		Elements names = doc.select("div.name-col__list");
		for(Element name : names) {
			
			String initial = name.child(0).text();
			String fname = name.child(1).text();
			String lname = name.child(2).text();
			
			playerNames.add(new PlayerName(initial, fname, lname));
		}
		
		createNamesDB(playerNames);

		return playerNames;
		
	}
	
	/**
	 * This method is used to grab all of the information about each player and create an object instance for each player.
	 * @param doc
	 * @return playerInfo Returns all of the player info associated with each player from the specified team.
	 */
	public static ObservableList<PlayerInfo> getPlayerInfo(Document doc){
		
		ObservableList<PlayerInfo> playerInfo = FXCollections.observableArrayList();
		long right = 0;
		long left = 0;
		
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
			
			//Streams API  
			right = playerInfo
				.stream()
				.filter(s -> s.getShoots().startsWith("R"))
				.count();
			
			left = playerInfo
				.stream()
				.filter(s -> s.getShoots().startsWith("L"))
				.count();
			 
			playerInfo.add(new PlayerInfo(playerNum, position, shoots, height, weight, born, birthplace, right, left));
			
		}
		
		return playerInfo;
		
	}
}
