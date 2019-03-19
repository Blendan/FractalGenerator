package fractalGenerator.generator;

import fractalGenerator.shapes.DrawerFactory;
import fractalGenerator.shapes.ObjectDrawer;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class CopyGenerator extends FractalGenerator
{
	private int nextNegative = 1, nextPositive = 1;
	CopyGenerator(Pane drawPane)
	{
		super(drawPane);
	}

	@Override
	void createFractal(double length, double x, double y, int i, int bias)
	{
		ObjectDrawer temp =  new DrawerFactory(x,y,length,color,deg,degBias,bias,drawPane).getDrawer(typ);

		if(nextPositive == i && i < repeats)
		{
			if(i==makePositive(bias)&&bias>0)
			{
				i ++;
				nextPositive *= 2;

				Point2D transformedEnd = temp.getPoint(1);
				createFractal(length * falloff, transformedEnd.getX(), transformedEnd.getY(), i, bias + 1);
			}
		}
		else if(nextNegative == i && i < repeats)
		{
			if(i==makePositive(bias)&&bias<0)
			{
				i ++;
				nextNegative *= 2;

				if(temp.getListPoints().size()>=3)
				{
					Point2D transformedEnd = temp.getPoint(2);
					createFractal(length * falloff, transformedEnd.getX(), transformedEnd.getY(), i, bias - 1);
				}
			}
		}
		else if (i < repeats)
		{
			i++;
			Point2D transformedEnd = temp.getPoint(1);
			createFractal(length * falloff, transformedEnd.getX(), transformedEnd.getY(), i, bias + 1);

			if(temp.getListPoints().size()>=3)
			{
				transformedEnd = temp.getPoint(2);
				createFractal(length * falloff, transformedEnd.getX(), transformedEnd.getY(), i, bias - 1);
			}
		}
	}

	private int makePositive(int bias)
	{
		if(bias<0)
		{
			return bias*-1;
		}
		return bias;
	}
}
