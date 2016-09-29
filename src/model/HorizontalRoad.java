package finalProject.src.model;




public final class HorizontalRoad extends Road{
	
	CarAcceptor _nextRoad;
	double _endpoint;
	
	HorizontalRoad(double endpoint){
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

	public void setnextHRoad(CarAcceptor nextRoad) {
		if((nextRoad instanceof VerticalRoad) && !(nextRoad instanceof Light))
			throw new IllegalArgumentException();
		else if(nextRoad == null)
			throw new NullPointerException();
		_nextRoad = nextRoad; 
		
	}

	public double getEndPosition(){
		return _endpoint;
		
	}
	
}



	

	

	

	
	
	
	
	


