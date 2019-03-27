package fractalGenerator.generator;

import fractalGenerator.shapes.DrawerFactory;
import fractalGenerator.shapes.ObjectDrawer;
import javafx.geometry.Point2D;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class CopyGenerator extends FractalGenerator
{
	@SuppressWarnings("FieldCanBeLocal")
	private int firstStep = 1;
	private Point2D zero, left, right;
	private ArrayList<CopySave> save;
	private boolean isMorThanOne = false;

	CopyGenerator(Pane field, ScrollPane scrollDraw)
	{
		super(field, scrollDraw);
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
				if (temp.getListPoints().size() >= 3)
				{
					isMorThanOne = true;
				}
				right = temp.getPoint(1);
				readyCopyFractal(false, i + 1);
			}
			else if (i == makePositive(bias) && bias < 0 && temp.getListPoints().size() >= 3)
			{
				isMorThanOne = true;
				left = temp.getPoint(2);

				readyCopyFractal(true, i + 1);
			}
		}
		else if (i < repeats)
		{
			i++;
			createFractal(length * falloff, temp.getPoint(1).getX(), temp.getPoint(1).getY(), i, bias + 1);

			if (temp.getListPoints().size() >= 3)
			{
				createFractal(length * falloff, temp.getPoint(2).getX(), temp.getPoint(2).getY(), i, bias - 1);
			}
		}
	}

	private boolean isReadyRight = false, isReadyLeft = false;

	private void readyCopyFractal(boolean isLeft, int i)
	{
		if (isLeft)
		{
			isReadyLeft = true;
		}
		else
		{
			isReadyRight = true;
		}

		if (isReadyLeft && isReadyRight || !isMorThanOne && isReadyRight)
		{
			isReadyLeft = false;
			isReadyRight = false;

			ArrayList<CopySave> temp = getCopy();

			if (isMorThanOne)
			{

				createCopyFractal(left.getX(), left.getY(), i + 1, -i - 1, temp);
			}

			createCopyFractal(right.getX(), right.getY(), i + 1, i + 1, temp);
		}

	}

	private void createCopyFractal(double x, double y, int i, int bias, ArrayList<CopySave> tempList)
	{
		if (i < repeats)
		{
			for (CopySave value : tempList)
			{
				ObjectDrawer temp = new DrawerFactory(x + value.getX(), y + value.getY(), value.getLength(), color, value.getDeg(), value.getDegBias(), bias + value.getBias(), drawPane).getDrawer(typ);
				save.add(new CopySave(temp.getListPoints(), value.getLength(), value.getDeg(), value.getDegBias(), bias + value.getBias(), x + value.getX() - zero.getX(), y + value.getY() - zero.getY()));
			}

			if (i == makePositive(bias) && bias < 0 && left != null)
			{
				left = new Point2D((left.getX() - zero.getX()) * 2 + zero.getX(), (left.getY() - zero.getY()) * 2 + zero.getY());
				readyCopyFractal(true, i);
			}
			else if (i == makePositive(bias) && bias > 0)
			{
				right = new Point2D((right.getX() - zero.getX()) * 2 + zero.getX(), (right.getY() - zero.getY()) * 2 + zero.getY());
				readyCopyFractal(false, i);
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
