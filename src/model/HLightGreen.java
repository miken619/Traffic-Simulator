package finalProject.src.model;

import java.awt.Color;

public final class HLightGreen implements LightController{
    
	final private static String DIRECTION= "VERTICAL";
	private double _endPosition;
	private double _duration;
	
	HLightGreen(double endPosition) {
		
		 _endPosition = endPosition;
		 _duration = MP.INSTANCE.getGreenLight();
		 
		 if (endPosition < 0.0){
				throw new IllegalArgumentException();
		 }else if(_duration > MP.INSTANCE.getMaxGreenLight() || _duration < MP.INSTANCE.getMinGreenLight()){
			    throw new IllegalArgumentException();
		 }
	}
	
	public LightController nextLight(){
		return ModelFactory.newHYellow();
	}
	
	public double getduration (){
		return _duration;
	}
	
	public double getEndPosition(){
		return _endPosition;
	}
	
	public boolean direction(){
		return true;
	}
	
	public Color getState() {
		return Color.GREEN;
	}
	
	public boolean carType(String test){
	       if(test.equals(DIRECTION)) 
	       		return true;
	       else
	    	   return false;
	}        
}
