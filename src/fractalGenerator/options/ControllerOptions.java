package fractalGenerator.options;

import fractalGenerator.Controller;
import fractalGenerator.generator.CopyGenerator;
import fractalGenerator.generator.GrowGenerator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerOptions implements Initializable
{
	@FXML
	private Button btnSave;
	@FXML
	private TextField inputRepeats;
	@FXML
	private TextField inputLength;
	@FXML
	private TextField inputFalloffLength;
	@FXML
	private TextField inputDeg;
	@FXML
	private TextField inputDegBias;
	@FXML
	private TextField inputBottom;
	@FXML
	private CheckBox checkIsStartLine;
	@FXML
	private TextField inputWidth;
	@FXML
	private TextField inputHeight;
	@FXML
	private TextField inputMultiplier;
	@FXML
	private ComboBox<DisplayClasses> boxGenerator;

	private Controller controller;


	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		btnSave.setOnAction(e -> save());
	}

	private void save()
	{
		try
		{
			controller.setWindowScale(Integer.parseInt(inputHeight.getText()), Integer.parseInt(inputWidth.getText()));
			controller.setFalloff(Double.parseDouble(inputFalloffLength.getText()));
			controller.setDeg(Integer.parseInt(inputDeg.getText()));
			controller.setBiasDeg(Integer.parseInt(inputDegBias.getText()));
			controller.setBottom(Integer.parseInt(inputBottom.getText()));
			controller.setLength(Integer.parseInt(inputLength.getText()));
			controller.setMultiplier(Integer.parseInt(inputMultiplier.getText()));
			controller.setFirstLine(checkIsStartLine.isSelected());
			controller.setRepeats(Integer.parseInt(inputRepeats.getText()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setController(Controller controller)
	{
		this.controller = controller;

		inputBottom.setText(controller.getBottom() + "");
		inputDeg.setText(controller.getDeg() + "");
		inputDegBias.setText(controller.getBiasDeg() + "");
		inputFalloffLength.setText(controller.getFalloff() + "");
		inputLength.setText(controller.getLength() + "");
		inputMultiplier.setText(controller.getMultiplier() + "");
		inputRepeats.setText(controller.getRepeats() + "");
		inputWidth.setText(controller.getWidth() + "");
		inputHeight.setText(controller.getHeight() + "");
		checkIsStartLine.setSelected(controller.isFirstLine());

		boxGenerator.getItems().add(new DisplayClasses(GrowGenerator.class,"Grow"));
		boxGenerator.getItems().add(new DisplayClasses(CopyGenerator.class,"Copy"));

	}


}
