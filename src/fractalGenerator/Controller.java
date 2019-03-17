package fractalGenerator;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
	@FXML
	private Button btnGenerate;

	@FXML
	private Button btnSave;

	@FXML
	private Pane drawPane;

	@FXML
	private TextField inputRepeats;

	@FXML
	private TextField inputDeg;

	@FXML
	private TextField inputHeight;

	@FXML
	private TextField inputFalloff;

	@FXML
	private TextField inputMargin;

	private FractalGenerator fractalGenerator;
	private Stage stage;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		fractalGenerator = new FractalGenerator(drawPane);
		btnGenerate.setOnAction((e) -> startGenerating());
		btnSave.setOnAction(event -> saveImage());
	}

	private void startGenerating()
	{
		try
		{
			fractalGenerator.setRepeats(Integer.parseInt(inputRepeats.getText()));
			fractalGenerator.setDeg(Integer.parseInt(inputDeg.getText()));
			fractalGenerator.setStartLength(Integer.parseInt(inputHeight.getText()));
			fractalGenerator.setFalloff(Double.parseDouble(inputFalloff.getText()));

			if(!inputMargin.getText().equals(""))
			{
				fractalGenerator.setPaddingBottom(Double.parseDouble(inputMargin.getText()));
			}

			new Thread(fractalGenerator).start();
		}
		catch (Exception e)
		{
			System.out.println("input error");
		}

	}

	private void saveImage()
	{
		SnapshotParameters snapshotParameters = new SnapshotParameters();
		snapshotParameters.setFill(Color.BLACK);
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));
		File selectedFile = fileChooser.showSaveDialog(stage);

		try
		{
			ImageIO.write(SwingFXUtils.fromFXImage(drawPane.snapshot(snapshotParameters,null), null), "png", selectedFile);
		}
		catch (IOException e)
		{
			System.out.println("no image selected");
		}
	}

	void setStage(Stage stage)
	{
		this.stage = stage;
	}
}
