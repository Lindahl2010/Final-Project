package application;
	
import java.io.IOException;
import javafx.application.Application;
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
		
		primaryStage.setTitle("Team Roster");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	

}
