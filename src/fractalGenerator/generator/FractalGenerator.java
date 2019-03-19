package fractalGenerator.generator;

import fractalGenerator.shapes.TriangleDrawer;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public abstract class FractalGenerator implements Runnable
{
	final Pane drawPane;
	Class typ = TriangleDrawer.class;
	int repeats;
	private int startLength;
	double falloff = 0.7;
	private double paddingBottom = 0;
	private boolean isSetPadding = false, isFirstLine = true;
	double deg, degBias;
	Color color = Color.WHITE;

	FractalGenerator(Pane drawPane)
	{
		this.drawPane = drawPane;
	}

	@Override
	public void run()
	{
		double x = drawPane.getWidth() / 2;
		double y;
		if (!isSetPadding)
		{
			y = drawPane.getHeight() - startLength;
		}
		else
		{
			y = (drawPane.getHeight() - paddingBottom) - startLength;
		}
		Platform.runLater(() -> drawPane.getChildren().clear());
		int i = 0;

		if (isFirstLine)
		{
			Line temp = new Line(x, y, x, y + startLength);
			temp.setStroke(Color.WHITE);
			Platform.runLater(() -> drawPane.getChildren().add(temp));
		}

		Platform.runLater(() -> createFractal(startLength * falloff, x, y, i, 0));
	}

	abstract void createFractal(double length, double x, double y, int i, int bias);

	public void setRepeats(int repeats)
	{
		this.repeats = repeats;
	}

	public void setStartLength(int startLength)
	{
		this.startLength = startLength;
	}

	public void setDeg(double deg)
	{
		this.deg = deg;
	}

	public void setFalloff(double falloff)
	{
		this.falloff = falloff;
	}

	public void setPaddingBottom(double paddingBottom)
	{
		this.paddingBottom = paddingBottom;
		isSetPadding = true;
	}

	public void setFirstLine(boolean firstLine)
	{
		isFirstLine = firstLine;
	}

	public void setDegBias(double degBias)
	{
		this.degBias = degBias;
	}

	public void setTyp(Class typ)
	{
		this.typ = typ;
	}
}
