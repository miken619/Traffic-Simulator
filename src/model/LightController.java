package finalProject.src.model;

import java.awt.Color;

public interface LightController {
	
	public Color getState();
	
	public LightController nextLight();
	
	public double getduration();
	
	public double getEndPosition();

	public boolean carType(String direction);

	public boolean direction();
	

}
