package fractalGenerator;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

public class FractalGenerator implements Runnable
{


	private final Pane drawPane;
	private int repeats, startLength;
	private double falloff = 0.7;
	private double paddingBottom = 0;
	private boolean isSetPadding = false;
	private double deg;

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
			y = drawPane.getHeight() - paddingBottom;
		}
		Platform.runLater(() -> drawPane.getChildren().clear());
		int i = 0;
		Line temp = new Line(x, y, x, y + startLength);
		temp.setStroke(Color.WHITE);
		Platform.runLater(() -> drawPane.getChildren().add(temp));

		Platform.runLater(() -> createFractal(startLength * falloff, x, y, i, 0));
	}

	private void createFractal(double length, double x, double y, int i, int degBias)
	{
		Line temp = new Line(x, y, x, y - length);
		temp.getTransforms().add(new Rotate(deg * degBias + deg, x, y, 0, Rotate.Z_AXIS));
		temp.setStroke(Color.WHITE);
		drawPane.getChildren().add(temp);


		Line temp2 = new Line(x, y, x, y - length);
		temp2.getTransforms().add(new Rotate(deg * degBias - deg, x, y, 0, Rotate.Z_AXIS));
		temp2.setStroke(Color.WHITE);
		drawPane.getChildren().add(temp2);

		if (i < repeats)
		{
			i++;
			Point2D transformedEnd = temp.localToScene(temp.getEndX(), temp.getEndY());
			System.out.println(transformedEnd.getY() + "|"+ temp.getEndY()+ "|"+ temp.getStartY());
			createFractal(length * falloff, transformedEnd.getX(), transformedEnd.getY(), i, degBias + 1);

			transformedEnd = temp2.localToScene(temp2.getEndX(), temp2.getEndY());
			createFractal(length * falloff, transformedEnd.getX(), transformedEnd.getY(), i, degBias - 1);
		}
		else
		{
			System.out.println("---------------");
		}

	}

	void setRepeats(int repeats)
	{
		this.repeats = repeats;
	}

	void setStartLength(int startLength)
	{
		this.startLength = startLength;
	}

	void setDeg(double deg)
	{
		this.deg = deg;
	}

	void setFalloff(double falloff)
	{
		this.falloff = falloff;
	}

	void setPaddingBottom(double paddingBottom)
	{
		this.paddingBottom = paddingBottom;
		isSetPadding = true;
	}

	public void setSetPadding(boolean setPadding)
	{
		isSetPadding = setPadding;
	}
}
