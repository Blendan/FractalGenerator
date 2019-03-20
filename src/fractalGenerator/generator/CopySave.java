package fractalGenerator.generator;

import javafx.geometry.Point2D;

import java.util.ArrayList;

class CopySave
{
	private ArrayList<Point2D> listPoints;
	private double length;
	private double deg, degBias;
	private int bias;
	private double x,y;

	CopySave(ArrayList<Point2D> listPoints, double length, double deg, double degBias, int bias, double x, double y)
	{
		this.listPoints = listPoints;
		this.length = length;
		this.deg = deg;
		this.degBias = degBias;
		this.bias = bias;
		this.x = x;
		this.y = y;
	}

	public double getDegBias()
	{
		return degBias;
	}

	public int getBias()
	{
		return bias;
	}

	public ArrayList<Point2D> getListPoints()
	{
		return listPoints;
	}

	public double getLength()
	{
		return length;
	}

	public double getDeg()
	{
		return deg;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}
}
