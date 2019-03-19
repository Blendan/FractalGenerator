package fractalGenerator.shapes;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SplitLineDrawer extends ObjectDrawer
{

	public SplitLineDrawer(double x, double y, double length, Color color, double deg, double degBias, int bias, Pane drawPane)
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
	}
}
