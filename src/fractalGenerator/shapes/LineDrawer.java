package fractalGenerator.shapes;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

public class LineDrawer extends ObjectDrawer
{

	LineDrawer(double x, double y, double length, Color color, double deg, double degBias, int bias, Pane drawPane)
	{
		super(x, y, length, color, deg, degBias, bias, drawPane);
	}

	@Override
	void draw()
	{
		Line temp = new Line(x, y, x, y - length);
		temp.getTransforms().add(new Rotate((makePositive(degBias) * bias) + deg, x, y, 0, Rotate.Z_AXIS));
		temp.setStroke(color);
		Platform.runLater(()->drawPane.getChildren().add(temp));

		addPoint(temp.localToScene(temp.getStartX(), temp.getStartY()));
		addPoint(temp.localToScene(temp.getEndX(), temp.getEndY()));
	}
}
