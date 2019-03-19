package fractalGenerator.generator;

import javafx.scene.layout.Pane;

public class GeneratorFactory
{
	private final Pane feld;

	public GeneratorFactory(Pane feld)
	{
		this.feld = feld;
	}

	public FractalGenerator getGenerator(Class typ)
	{
		if(typ == GrowGenerator.class)
		{
			return new GrowGenerator(feld);
		}
		else if(typ == CopyGenerator.class)
		{
			return new CopyGenerator(feld);
		}

		return null;
	}
}
