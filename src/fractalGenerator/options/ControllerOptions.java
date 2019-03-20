package fractalGenerator.options;

import fractalGenerator.Controller;
import fractalGenerator.generator.CopyGenerator;
import fractalGenerator.generator.CreateGenerator;
import fractalGenerator.generator.GrowGenerator;
import fractalGenerator.shapes.LineDrawer;
import fractalGenerator.shapes.SplitLineDrawer;
import fractalGenerator.shapes.TriangleDrawer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerOptions implements Initializable
{
	@FXML
	private Button btnSave;
	@FXML
	private Button btnSaveAndClose;
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
	@FXML
	private ComboBox<DisplayClasses> boxShape;

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

			controller.setTypGenerator(boxGenerator.getSelectionModel().getSelectedItem().getTyp());
			controller.setTypLine(boxShape.getSelectionModel().getSelectedItem().getTyp());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setController(Controller controller, Stage stage)
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
		boxGenerator.getItems().add(new DisplayClasses(CreateGenerator.class,"Generate (!! Resource Heavy !!)"));
		boxGenerator.getItems().add(new DisplayClasses(CopyGenerator.class,"Copy (!! Resource Heavy !! WORK IN PROGRESS)"));


		int i = 0;
		for (DisplayClasses value : boxGenerator.getItems())
		{
			if(value.getTyp()==controller.getTypGenerator())
			{
				boxGenerator.getSelectionModel().select(i);
			}
			i ++ ;
		}

		boxShape.getItems().add(new DisplayClasses(LineDrawer.class,"Line"));
		boxShape.getItems().add(new DisplayClasses(SplitLineDrawer.class,"SplitLine"));
		boxShape.getItems().add(new DisplayClasses(TriangleDrawer.class,"Triangle"));

		i = 0;
		for (DisplayClasses value : boxShape.getItems())
		{
			if(value.getTyp()==controller.getTypLine())
			{
				boxShape.getSelectionModel().select(i);
			}
			i ++ ;
		}

		btnSaveAndClose.setOnAction((e)->{
			save();
			stage.close();
		});
	}


}
