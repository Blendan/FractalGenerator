package fractalGenerator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("mainGui.fxml"));
		Parent root = (Parent) loader.load();
		primaryStage.setTitle("Fractal Generator");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		Controller controller = loader.getController();
		controller.setStage(primaryStage);
	}


	public static void main(String[] args)
	{
		launch(args);
	}
}
