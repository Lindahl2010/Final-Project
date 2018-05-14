package application;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SampleController {

    @FXML
    private TableView<TeamName> teamTable;

    	@FXML
    	private TableColumn<TeamName, String> cityCol;

    	@FXML
    	private TableColumn<TeamName, String> teamCol;

    @FXML
    private TextField searchBox;

    @FXML
    private Button infoBtn;
    
    @FXML
    private Button namesBtn;

    @FXML
    private TableView<PlayerName> nameTable;

    	@FXML
    	private TableColumn<PlayerName, String> initialCol;

    	@FXML
    	private TableColumn<PlayerName, String> fnameCol;

    	@FXML
    	private TableColumn<PlayerName, String> lnameCol;


    @FXML
    private TableView<PlayerInfo> infoTable;

    	@FXML
    	private TableColumn<PlayerInfo, String> playerNumCol;

    	@FXML
    	private TableColumn<PlayerInfo, String> shootsCol;
    
    	@FXML
    	private TableColumn<PlayerInfo, String> heightCol;

    	@FXML
    	private TableColumn<PlayerInfo, String> weightCol;
    
    	@FXML
    	private TableColumn<PlayerInfo, String> bornCol;

    	@FXML
    	private TableColumn<PlayerInfo, String> birthplaceCol;

    @FXML
    private ImageView nhlBackground;

    @FXML
    private Button showTeamBtn;
    
    @FXML
    private Label teamName;
    
    @FXML 
    private Label shotCount;
    
    @FXML 
    void showTeams(ActionEvent event) throws Exception {
    	
    	Document teamPage = Jsoup.connect("https://www.nhl.com/info/teams#info_index").get();
    	ObservableList<TeamName> team = Main.getTeamNames(teamPage);
    	displayTeams(team);
    	
    }
    
    void displayTeams(ObservableList<TeamName> list) {
        
    	cityCol.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
    	teamCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    	
    	teamTable.setItems(list);
    }
    
    /**
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void showPlayerNames(ActionEvent event) throws Exception{
    	
    	Document doc = Main.searchTeam(searchBox.getText());
    	ObservableList<PlayerName> names = Main.getPlayerNames(doc);
    	displayPlayerNames(names);
    	
    }
    
    /**
     * Assigns the value of the names in the player name table in the GUI.
     * @param list
     */
    void displayPlayerNames(ObservableList<PlayerName> list) {
    	
    	initialCol.setCellValueFactory(cellData -> cellData.getValue().initialProperty());
    	fnameCol.setCellValueFactory(cellData -> cellData.getValue().fnameProperty());
    	lnameCol.setCellValueFactory(cellData -> cellData.getValue().lnameProperty());
    	
    	nameTable.setItems(list);
    }
    
    /**
     * Displays all of the player information when the user clicks the display info button in the GUI.
     * @param event
     * @throws Exception
     */
    @FXML
    void showPlayerInfo(ActionEvent event) throws Exception{
    	
    	Document doc = Main.searchTeam(searchBox.getText());
    	ObservableList<PlayerInfo> info = Main.getPlayerInfo(doc);
    	displayPlayerInfo(info);
    	
    }
    
    /**
     * Assigns the value to be displayed in the player information table on the GUI.
     * @param list
     */
    void displayPlayerInfo(ObservableList<PlayerInfo> list) {
    	
    	playerNumCol.setCellValueFactory(cellData -> cellData.getValue().playerNumProperty());
    	shootsCol.setCellValueFactory(cellData -> cellData.getValue().shootsProperty());
    	heightCol.setCellValueFactory(cellData -> cellData.getValue().heightProperty());
    	weightCol.setCellValueFactory(cellData -> cellData.getValue().weightProperty());
    	bornCol.setCellValueFactory(cellData -> cellData.getValue().bornProperty());
    	birthplaceCol.setCellValueFactory(cellData -> cellData.getValue().birthplaceProperty());
    	
    	infoTable.setItems(list);
    }  
    
}
