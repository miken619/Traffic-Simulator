package finalProject.src.model;

import java.awt.Color;
import java.util.Random;

public final class Light extends Road implements Agent {
	
	CarAcceptor _nextHRoad;
	CarAcceptor _nextVRoad;
	LightController _state;
    Random coinToss = new Random();
    int random = coinToss.nextInt(2);
    double timeStep = MP.INSTANCE.getSimTimeStep();

	
	Light (double endpoint) {
		super(endpoint);
		if(random > 0.5){
			_state = ModelFactory.newHGreen();
		}else{
			_state = ModelFactory.newVGreen();
		}
	}
	
	public void setnextHRoad(CarAcceptor nextRoad){
		if((nextRoad instanceof VerticalRoad) && !(nextRoad instanceof Light))
			throw new IllegalArgumentException();
		else if(nextRoad == null)
			throw new NullPointerException();
		_nextHRoad = nextRoad; 	
	}
	
	public void setnextVRoad(CarAcceptor nextRoad){
		if((nextRoad instanceof HorizontalRoad) && !(nextRoad instanceof Light))
			throw new IllegalArgumentException();
		else if(nextRoad == null)
			throw new NullPointerException();
		_nextVRoad = nextRoad; 	
	}
		
	public CarAcceptor getnextRoad(Car c){
		
		if(c == null)
			throw new NullPointerException();
		else if(!(c instanceof Car))
			throw new IllegalArgumentException();
		
		
		if(c.getDirection().equals("VERTICAL"))
			return _nextVRoad; 
		else
			return _nextHRoad;
	}
	
	public double getEndPosition(){
		return _state.getEndPosition();
	}
	
	public Color getState() {
	    return _state.getState();
	}
	
    public LightController getLight(){
    	return _state;
    }
    
    public boolean hasLight(){
    	return true;
    }
	
    public boolean direction(){
		return _state.direction();
	}

	public void run() {
		_state = _state.nextLight();
		 TimeServerLinked.getTimeServer().enqueue(_state.getduration() + TimeServerLinked.getTimeServer().currentTime(),this);
	}

	public void setHRoad(CarAcceptor carGen) {
		throw new NullPointerException();
	}

	public void setVRoad(CarAcceptor carGen) {
		throw new NullPointerException();
	}

	public void setCurrentRoad(CarAcceptor currentRoad) {
		throw new NullPointerException();
	}

	public void setDirection(String direction) {
		throw new NullPointerException();
	}

}
