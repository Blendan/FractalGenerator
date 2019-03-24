package fractalGenerator.generator;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public class GeneratorFactory
{
	private final Pane feld;
	private final ScrollPane scrollDraw;

	public GeneratorFactory(Pane field, ScrollPane scrollDraw)
	{
		this.feld = field;
		this.scrollDraw = scrollDraw;
	}

	public FractalGenerator getGenerator(Class typ)
	{
		if(typ == GrowGenerator.class)
		{
			return new GrowGenerator(feld,scrollDraw);
		}
		else if(typ == CreateGenerator.class)
		{
			return new CreateGenerator(feld,scrollDraw);
		}
		else if(typ == CopyGenerator.class)
		{
			return new CopyGenerator(feld,scrollDraw);
		}

		return null;
	}
}
