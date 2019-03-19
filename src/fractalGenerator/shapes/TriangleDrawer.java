package fractalGenerator.shapes;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

public class TriangleDrawer extends ObjectDrawer
{

	public TriangleDrawer(double x, double y, double length, Color color, double deg, double degBias, int bias, Pane drawPane)
	{
		super(x, y, length, color, deg, degBias, bias, drawPane);
	}

	@Override
	void draw()
	{
		LineDrawer tempLine = new LineDrawer(x,y,length,color,deg,degBias,bias,drawPane);
		addPoint(tempLine.getListPoints());

		tempLine = new LineDrawer(x,y,length,color,-deg,degBias,bias,drawPane);
		addPoint(tempLine.getListPoints());

		Line temp = new Line(getPoint(1).getX(), getPoint(1).getY(), getPoint(2).getX(), getPoint(2).getY());
		temp.setStroke(color);
		drawPane.getChildren().add(temp);
	}
}
