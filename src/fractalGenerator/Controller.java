package fractalGenerator;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Transform;
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
	public TextField inputResMultiplier;
	public Label outResX;
	public Label outResY;

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

		InvalidationListener scaleFeld = (e) -> new Thread(() -> Platform.runLater(this::setScale)).start();

		drawPane.heightProperty().addListener(scaleFeld);
		drawPane.widthProperty().addListener(scaleFeld);
		setScale();
	}

	private void setScale()
	{
		outResX.setText(drawPane.getWidth() + "");
		outResY.setText(drawPane.getHeight() + "");
	}

	private void startGenerating()
	{
		try
		{
			fractalGenerator.setRepeats(Integer.parseInt(inputRepeats.getText()));
			fractalGenerator.setDeg(Integer.parseInt(inputDeg.getText()));
			fractalGenerator.setStartLength(Integer.parseInt(inputHeight.getText()));
			fractalGenerator.setFalloff(Double.parseDouble(inputFalloff.getText()));

			if (!inputMargin.getText().equals(""))
			{
				fractalGenerator.setPaddingBottom(Double.parseDouble(inputMargin.getText()));
			}

			new Thread(fractalGenerator).start();
		} catch (Exception e)
		{
			System.out.println("input error");
		}

	}

	private void saveImage()
	{
		try
		{
			int multiplier;
			if(!inputResMultiplier.getText().equals(""))
			{
				multiplier = Integer.parseInt(inputResMultiplier.getText());
			}
			else
			{
				multiplier = 1;
			}

			SnapshotParameters snapshotParameters = new SnapshotParameters();
			snapshotParameters.setFill(Color.BLACK);
			snapshotParameters.setTransform(Transform.scale(multiplier, multiplier));
			WritableImage writableImage = new WritableImage((int)Math.rint(multiplier*drawPane.getWidth()), (int)Math.rint(multiplier*drawPane.getHeight()));

			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));
			File selectedFile = fileChooser.showSaveDialog(stage);

			try
			{
				ImageIO.write(SwingFXUtils.fromFXImage(drawPane.snapshot(snapshotParameters, writableImage),null), "png", selectedFile);
			} catch (IOException e)
			{
				System.out.println("no image selected");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	void setStage(Stage stage)
	{
		this.stage = stage;
	}
}
