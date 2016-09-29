package finalProject.src.model;


public final class VerticalRoad extends Road{
	
    CarAcceptor _nextRoad;
	double _endpoint;
	
    VerticalRoad(double endpoint){
    	super(endpoint); 
    	_endpoint=endpoint;
    	if(endpoint < 0)
			throw new IllegalArgumentException();
    }
		
	public CarAcceptor getnextRoad(Car c){
		if(c == null)
			throw new NullPointerException();
		else if(!(c instanceof Car))
			throw new IllegalArgumentException();
		return _nextRoad; 
	}
	
	public void setnextVRoad(CarAcceptor nextRoad) {
		if((nextRoad instanceof HorizontalRoad) && !(nextRoad instanceof Light))
			throw new IllegalArgumentException();
		else if(nextRoad == null)
			throw new NullPointerException();
		_nextRoad = nextRoad; 
	}
	
	public double getEndPosition(){
		return _endpoint;
	}
}
