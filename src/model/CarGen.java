package finalProject.src.model;

public final class CarGen implements Agent{
	
	CarAcceptor _currentVRoad;
	CarAcceptor _currentHRoad;
	private double _frontPosition;
	private double carGen;
	
	CarGen(){
		_frontPosition = 0;
	}
	
	public  CarAcceptor getCurrentRoad() {
		if (_currentHRoad == null)
		   return _currentVRoad;
		else
			return _currentHRoad;
	}
	
	public void setHRoad (CarAcceptor road) {
		_currentHRoad = road;
	}
	
	public void setVRoad (CarAcceptor road) {
		_currentVRoad = road;
	}
	
	public double getFrontPosition(){
        return _frontPosition; 
	}
	
	public String getDirection(){
		if (_currentHRoad == null)
			return "VERTICAL";
		else
			return "HORIZONTAL";
	
	}

	public void run() {
		
		Agent newCar = ModelFactory.makeCar();
		newCar.setCurrentRoad(getCurrentRoad());
		newCar.setDirection(getDirection());
		newCar.run();
		
		carGen = MP.INSTANCE.getCarEntryRate();
		if (carGen < MP.INSTANCE.getMinCarEntryRate() || carGen > MP.INSTANCE.getMaxCarEntryRate()){
			   throw new IllegalArgumentException();
		 }
		TimeServerLinked.getTimeServer().enqueue(carGen  +TimeServerLinked.getTimeServer().currentTime(),this);
	}


	public void setCurrentRoad(CarAcceptor currentRoad) {
		throw new NullPointerException();
		
	}

	public void setDirection(String direction) {
		throw new NullPointerException();
		
	}

}
