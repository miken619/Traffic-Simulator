package finalProject.src.model;

import java.awt.Color;

public final class VLightYellow implements LightController{

	final private static String DIRECTION= "HORIZONTAL";
	private double _endPosition;
	private double _duration;
	
	VLightYellow(double endPosition) {
		 _endPosition = endPosition;
		 _duration = MP.INSTANCE.getYellowLight(); 
		 
		 if (endPosition < 0.0){
				throw new IllegalArgumentException();
		 }else if(_duration > MP.INSTANCE.getMaxYellowLight() || _duration < MP.INSTANCE.getMinYellowLight()){
			    throw new IllegalArgumentException();
		 }
	}
	
	public LightController nextLight(){
		return ModelFactory.newHGreen();
	}
	
	public double getduration (){
		return _duration;
	}
	
	public boolean direction(){
		return false;
	}
	
	public double getEndPosition(){
		return _endPosition;	
	}
	
	public Color getState() {
		return Color.YELLOW;
	}
	
	public boolean carType(String test){
	       if(test.equals(DIRECTION)) 
	       		return true;
	       else
	    	   return false;
	}        
}
