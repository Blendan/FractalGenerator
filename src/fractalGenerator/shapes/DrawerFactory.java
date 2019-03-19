package fractalGenerator.shapes;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class DrawerFactory
{
	protected final Pane drawPane;
	protected double x,y;
	protected double length, deg, degBias;
	protected Color color;
	protected int bias;

	public DrawerFactory(double x, double y, double length, Color color, double deg, double degBias, int bias, Pane drawPane)
	{
		this.x = x;
		this.y = y;
		this.length = length;
		this.deg = deg;
		this.degBias = degBias;
		this.color = color;
		this.drawPane = drawPane;
		this.bias = bias;
	}

	public ObjectDrawer getDrawer(Class typ)
	{
		if(typ == SplitLineDrawer.class)
		{
			return new SplitLineDrawer(x,y,length,color,deg,degBias,bias,drawPane);
		}
		else if(typ == TriangleDrawer.class)
		{
			return new TriangleDrawer(x,y,length,color,deg,degBias,bias,drawPane);
		}
		else if(typ == LineDrawer.class)
		{
			return new LineDrawer(x,y,length,color,deg,degBias,bias,drawPane);
		}
		return null;
	}
}
