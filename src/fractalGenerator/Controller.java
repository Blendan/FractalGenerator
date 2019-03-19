package fractalGenerator;

import fractalGenerator.generator.CopyGenerator;
import fractalGenerator.generator.FractalGenerator;
import fractalGenerator.generator.GeneratorFactory;
import fractalGenerator.options.ControllerOptions;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
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
	private Button btnOptions;

	@FXML
	private Button btnGenerate;

	@FXML
	private Button btnSave;

	@FXML
	private Pane drawPane;

	private Stage stage;

	private int repeats = 4, multiplier = 1, length = 100, deg = 45, biasDeg = 45, height, width, bottom = 0;
	private double falloff = 0.7;
	private boolean isFirstLine = true;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		btnGenerate.setOnAction((e) -> startGenerating());
		btnSave.setOnAction(event -> saveImage());

		btnOptions.setOnAction(event -> startOptions());


		drawPane.heightProperty().addListener((e) -> this.setScale());
		drawPane.widthProperty().addListener((e) -> this.setScale());
		setScale();
	}

	private void setScale()
	{
		width = (int) Math.round(drawPane.getWidth());
		height = (int) Math.round(drawPane.getHeight());
	}

	private void startGenerating()
	{
		try
		{
			FractalGenerator fractalGenerator = new GeneratorFactory(drawPane).getGenerator(CopyGenerator.class);
			fractalGenerator.setRepeats(getRepeats());
			fractalGenerator.setDeg(getDeg());
			fractalGenerator.setStartLength(getLength());
			fractalGenerator.setFalloff(falloff);
			fractalGenerator.setFirstLine(isFirstLine);
			fractalGenerator.setDegBias(biasDeg);

			fractalGenerator.setPaddingBottom(bottom);

			new Thread(fractalGenerator).start();
		}
		catch (Exception e)
		{
			System.out.println("input error");
		}

	}

	private void saveImage()
	{
		try
		{
			SnapshotParameters snapshotParameters = new SnapshotParameters();
			snapshotParameters.setFill(Color.BLACK);
			snapshotParameters.setTransform(Transform.scale(multiplier, multiplier));
			WritableImage writableImage = new WritableImage((int) Math.rint(multiplier * drawPane.getWidth()), (int) Math.rint(multiplier * drawPane.getHeight()));

			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));
			File selectedFile = fileChooser.showSaveDialog(stage);

			try
			{
				ImageIO.write(SwingFXUtils.fromFXImage(drawPane.snapshot(snapshotParameters, writableImage), null), "png", selectedFile);
			}
			catch (IOException e)
			{
				System.out.println("no image selected");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void startOptions()
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("options/GuiOptions.fxml"));

		Parent root = null;
		try
		{
			root = fxmlLoader.load();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		Stage stage = new Stage();

		assert root != null;
		stage.setScene(new Scene(root));
		stage.setTitle("Options");
		stage.show();

		ControllerOptions temp = fxmlLoader.getController();
		temp.setController(this);
	}

	public void setWindowScale(int height, int width)
	{
		stage.setHeight(height + 57 + 40);
		stage.setWidth(width + 14);
	}

	void setStage(Stage stage)
	{
		this.stage = stage;
	}

	public int getMultiplier()
	{
		return multiplier;
	}

	public void setMultiplier(int multiplier)
	{
		this.multiplier = multiplier;
	}

	public int getRepeats()
	{
		return repeats;
	}

	public void setRepeats(int repeats)
	{
		this.repeats = repeats;
	}

	public int getLength()
	{
		return length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public int getDeg()
	{
		return deg;
	}

	public void setDeg(int deg)
	{
		this.deg = deg;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public double getFalloff()
	{
		return falloff;
	}

	public void setFalloff(double falloff)
	{
		this.falloff = falloff;
	}

	public int getBottom()
	{
		return bottom;
	}

	public void setBottom(int bottom)
	{
		this.bottom = bottom;
	}

	public int getBiasDeg()
	{
		return biasDeg;
	}

	public void setBiasDeg(int biasDeg)
	{
		this.biasDeg = biasDeg;
	}

	public boolean isFirstLine()
	{
		return isFirstLine;
	}

	public void setFirstLine(boolean firstLine)
	{
		isFirstLine = firstLine;
	}
}
