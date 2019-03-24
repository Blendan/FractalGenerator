package fractalGenerator.shapes;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public abstract class ObjectDrawer
{

	protected final Pane drawPane;
	private ArrayList<Point2D> listPoints;
	protected double x,y;
	protected double length, deg, degBias;
	protected Color color;
	protected int bias;

	ObjectDrawer(double x, double y, double length, Color color, double deg, double degBias, int bias, Pane drawPane)
	{
		this.x = x;
		this.y = y;
		this.length = length;
		this.deg = deg;
		this.degBias = degBias;
		this.color = color;
		this.drawPane = drawPane;
		this.listPoints = new ArrayList<>();
		this.bias = bias;
		draw();
	}

	abstract void draw();

	public Point2D getPoint(int index)
	{
		if(listPoints!=null)
		{
			return listPoints.get(index);
		}
		return null;
	}

	double makePositive(double number)
	{
		if(number<0)
		{
			return number*-1;
		}
		else
		{
			return number;
		}
	}

	void addPoint(Point2D point)
	{
		boolean found = false;
		for (Point2D value: listPoints)
		{
			if(value.getX()==point.getX()&&value.getY()==point.getY())
			{
				found = true;
				break;
			}
		}

		if(!found)
		{
			listPoints.add(point);
		}
	}

	void addPoint(ArrayList<Point2D> list)
	{
		for (Point2D point : list)
		{
			boolean found = false;
			for (Point2D value : listPoints)
			{
				if (value.getX() == point.getX() && value.getY() == point.getY())
				{
					found = true;
					break;
				}
			}

			if (!found)
			{
				listPoints.add(point);
			}
		}
	}

	public ArrayList<Point2D> getListPoints()
	{
		return listPoints;
	}
}
