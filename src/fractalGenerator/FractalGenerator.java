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
	private int repets, startLengt;
	private double falloff = 0.7;
	private double deg;

	FractalGenerator(Pane drawPane)
	{
		this.drawPane = drawPane;
	}

	@Override
	public void run()
	{
		System.out.println("---------------------\n\n");
		double x = drawPane.getWidth() / 2;
		double y = drawPane.getHeight() - startLengt;
		Platform.runLater(() -> drawPane.getChildren().clear());
		int i = 0;
		Line temp = new Line(x, y, x, y + startLengt);
		temp.setStroke(Color.WHITE);
		Platform.runLater(() -> drawPane.getChildren().add(temp));

		Platform.runLater(() -> createFractal(startLengt * falloff, x, y, i, 0));
	}

	private void createFractal(double lengt, double x, double y, int i, int degBias)
	{
		Line temp = new Line(x, y, x, y - lengt);
		temp.getTransforms().add(new Rotate(deg * degBias + deg, x, y, 0, Rotate.Z_AXIS));
		temp.setStroke(Color.WHITE);
		drawPane.getChildren().add(temp);


		Line temp2 = new Line(x, y, x, y - lengt);
		temp2.getTransforms().add(new Rotate(deg * degBias - deg, x, y, 0, Rotate.Z_AXIS));

		temp2.setStroke(Color.WHITE);
		drawPane.getChildren().add(temp2);

		if (i < repets)
		{
			i++;
			Point2D transformedEnd = temp.localToScene(temp.getEndX(), temp.getEndY());
			createFractal(lengt * falloff, transformedEnd.getX(), transformedEnd.getY(), i, degBias + 1);

			transformedEnd = temp2.localToScene(temp2.getEndX(), temp2.getEndY());
			createFractal(lengt * falloff, transformedEnd.getX(), transformedEnd.getY(), i, degBias - 1);
		}

	}

	void setRepets(int repets)
	{
		this.repets = repets;
	}

	void setStartLengt(int startLengt)
	{
		this.startLengt = startLengt;
	}

	void setDeg(double deg)
	{
		this.deg = deg;
	}

	void setFalloff(double falloff)
	{
		this.falloff = falloff;
	}
}
