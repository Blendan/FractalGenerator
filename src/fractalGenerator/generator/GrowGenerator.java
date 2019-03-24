package fractalGenerator.generator;

import fractalGenerator.shapes.DrawerFactory;
import fractalGenerator.shapes.ObjectDrawer;
import javafx.geometry.Point2D;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public class GrowGenerator extends FractalGenerator
{

	GrowGenerator(Pane field, ScrollPane scrollDraw)
	{
		super(field, scrollDraw);
	}

	@Override
	void createFractal(double length, double x, double y, int i, int bias)
	{
		ObjectDrawer temp =  new DrawerFactory(x,y,length,color,deg,degBias,bias,drawPane).getDrawer(typ);

		if (i < repeats)
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
}
