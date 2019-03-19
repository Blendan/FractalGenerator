package fractalGenerator.options;

public class DisplayClasses
{
	private final Class typ;
	private final String display;

	public DisplayClasses(Class typ, String display)
	{
		this.typ = typ;
		this.display = display;
	}

	public Class getTyp()
	{
		return typ;
	}

	@Override
	public String toString()
	{
		return display;
	}
}
