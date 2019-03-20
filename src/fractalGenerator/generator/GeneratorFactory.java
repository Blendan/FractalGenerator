package fractalGenerator.generator;

import javafx.scene.layout.Pane;

public class GeneratorFactory
{
	private final Pane feld;

	public GeneratorFactory(Pane field)
	{
		this.feld = field;
	}

	public FractalGenerator getGenerator(Class typ)
	{
		if(typ == GrowGenerator.class)
		{
			return new GrowGenerator(feld);
		}
		else if(typ == CreateGenerator.class)
		{
			return new CreateGenerator(feld);
		}
		else if(typ == CopyGenerator.class)
		{
			return new CopyGenerator(feld);
		}

		return null;
	}
}
