package fractalGenerator.generator;

import fractalGenerator.shapes.DrawerFactory;
import fractalGenerator.shapes.ObjectDrawer;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class CopyGenerator extends FractalGenerator
{
	private int firstStep = 1;
	private Point2D zero, left, right;
	private ArrayList<CopySave> save;

	CopyGenerator(Pane drawPane)
	{
		super(drawPane);
	}

	@Override
	void createFractal(double length, double x, double y, int i, int bias)
	{
		ObjectDrawer temp = new DrawerFactory(x, y, length, color, deg, degBias, bias, drawPane).getDrawer(typ);

		if (i == 0)
		{
			zero = temp.getPoint(0);
		}

		if (save == null)
		{
			save = new ArrayList<>();
		}

		save.add(new CopySave(temp.getListPoints(), length, deg, degBias, bias, x - zero.getX(), y - zero.getY()));

		if (firstStep == i && i < repeats)
		{
			if (i == makePositive(bias) && bias > 0)
			{
				right = temp.getPoint(1);
				int finalI1 = i;
				Platform.runLater(() -> createCopyFractal(temp.getPoint(1).getX(), temp.getPoint(1).getY(), finalI1, bias + 1));
			}
			else if (i == makePositive(bias) && bias < 0 && temp.getListPoints().size() >= 3)
			{
				left = temp.getPoint(2);

				int finalI2 = i;
				Platform.runLater(() -> createCopyFractal(temp.getPoint(2).getX(), temp.getPoint(2).getY(), finalI2, bias - 1));
			}
		}
		else if (i < repeats)
		{

			i++;
			int finalI = i;
			Platform.runLater(() -> createFractal(length * falloff, temp.getPoint(1).getX(), temp.getPoint(1).getY(), finalI, bias + 1));

			if (temp.getListPoints().size() >= 3)
			{
				Platform.runLater(() -> createFractal(length * falloff, temp.getPoint(2).getX(), temp.getPoint(2).getY(), finalI, bias - 1));
			}
		}
	}

	private void createCopyFractal(double x, double y, int i, int bias)
	{
		if (i < repeats)
		{
			for (CopySave value : getCopy())
			{
				ObjectDrawer temp = new DrawerFactory(x + value.getX(), y + value.getY(), value.getLength(), color, value.getDeg(), value.getDegBias(), bias + value.getBias(), drawPane).getDrawer(typ);
				save.add(new CopySave(temp.getListPoints(), value.getLength(), value.getDeg(), value.getDegBias(), bias + value.getBias(), x + value.getX() - zero.getX(), y + value.getY() - zero.getY()));
			}
			i++;

			if (bias < 0)
			{
				left = new Point2D((left.getX() - zero.getX()) * 2 + zero.getX(), (left.getY() - zero.getY()) * 2 + zero.getY());
				int finalI = i;
				Platform.runLater(() -> createCopyFractal(left.getX(), left.getY(), finalI, bias - 1));
			}
			else
			{
				right = new Point2D((right.getX() - zero.getX()) * 2 + zero.getX(), (right.getY() - zero.getY()) * 2 + zero.getY());
				int finalI1 = i;
				Platform.runLater(() -> createCopyFractal(right.getX(), right.getY(), finalI1, bias + 1));
			}
		}
	}

	private ArrayList<CopySave> getCopy()
	{
		return new ArrayList<>(save);
	}

	private int makePositive(int bias)
	{
		if (bias < 0)
		{
			return bias * -1;
		}
		return bias;
	}
}
