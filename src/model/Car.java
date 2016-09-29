package finalProject.src.model;



public final class Car implements Agent{
	
	private CarAcceptor _currentRoad;
	private double _timeStep;
	private double _frontPosition;
	private String _direction;
	private double _stopDistance;
	private double _brakeDistance;
	private double _velocity;
	private double _carLength;
	
	private java.awt.Color _color;
	 Car(){
		 setCarLength(MP.INSTANCE.getCarLength());
		 _color = new java.awt.Color((int)Math.ceil(Math.random()*255),(int)Math.ceil(Math.random()*255),(int)Math.ceil(Math.random()*255));
		 setTimeStep(MP.INSTANCE.getSimTimeStep());
		 setStopDistance(MP.INSTANCE.getStopDistance());
		 setBrakeDistance(MP.INSTANCE.getBrakedDistance());
	 }	
		
		
		
		
		
		
	

	
	public double getCarLength() {
		return _carLength;
	}
	
	public double getTimeStep(){
		
		   return _timeStep; 
	}
	
	public double getMaxVelocity() {
		return MP.INSTANCE.getMaxVelocity();
	}
	
	public double getVelocity() {
		return _velocity;
	}
	
	public double getBrakeDistance() {
		return _brakeDistance;
	}
	
	public double getStopDistance() {
		return _stopDistance;
	}
	
	public double getbackPosition() {
	    return _frontPosition - getCarLength();
	}
	
	public  String getDirection(){
		return _direction;
	}
	
	public  CarAcceptor getCurrentRoad() {
		return _currentRoad;
	}
	
	public double getFrontPosition(){
        return _frontPosition; 
	
	}
	
	public java.awt.Color getColor() {
	    return _color;
	  }
	
	public double getRoadLength(){
		return getCurrentRoad().getEndPosition();
		
	}
	
	public void setCurrentRoad (CarAcceptor road){
		if(road != null)
			_currentRoad = road;	
		else 
			throw new NullPointerException();
	}
	
	public void setVelocity(double Velocity) {
		if(Velocity < 0.0 || Velocity > MP.INSTANCE.getMaxVelocity())
			throw new IllegalArgumentException();
		else
			_velocity = Velocity;
	}
	
	public void setBrakeDistance(double brakeDistance) {
		if (brakeDistance < MP.INSTANCE.getMinBrakedDistance() || brakeDistance > MP.INSTANCE.getMaxBrakedDistance())
			throw new IllegalArgumentException();
		else
			_brakeDistance = brakeDistance;
	}
	
	public void setStopDistance(double stopDistance) {
		if (stopDistance < MP.INSTANCE.getMinStopDistance() || stopDistance > MP.INSTANCE.getMaxStopDistance())
			throw new IllegalArgumentException();
		else
			_stopDistance = stopDistance;
	}
	
	public void setDirection (String direction) throws NullPointerException{
		if((direction.equals("VERTICAL")) || (direction.equals("HORIZONTAL")))
			_direction = direction;
		else
			throw new IllegalArgumentException();
	}
	
	public void setFrontPosition(Double frontPosition){
		    if(frontPosition < 0.0)
		    	throw new IllegalArgumentException();
		    else 
		    	_frontPosition = frontPosition;
	}     
	
	public void setCarLength(Double carLength){
	    if(carLength > MP.INSTANCE.getMaxCarLength() || carLength < MP.INSTANCE.getMinCarLength())
	    	throw new IllegalArgumentException();
	    else 
	    	_carLength = carLength;
	}     
	
	public void setTimeStep(double timeStep){
		if(timeStep < 0.0)
			throw new IllegalArgumentException();
		else
		   _timeStep = timeStep;
	}
	
	
	
	public double newVelocity (double closetObstacle){
		
		double velocity = (getMaxVelocity()/ (getBrakeDistance() - getStopDistance())) * (closetObstacle-getStopDistance());
		velocity = Math.max(0.0, velocity);
		velocity = Math.min(getMaxVelocity(), velocity);
		while(velocity > closetObstacle)
			velocity = velocity/2;
       
		return velocity;
	}
	
	
	public void run() {
		
		double closetObstacle;
		double newVelocity = 0;
		double nextFrontPosition;
		
		
    		
    		closetObstacle = getCurrentRoad().distanceToObstacle(this, getFrontPosition());
    		
    		if((closetObstacle < getMaxVelocity()) && (closetObstacle > getBrakeDistance())){
    			newVelocity = newVelocity (closetObstacle);
    			setVelocity(newVelocity);
    		}else if (closetObstacle <= getStopDistance()){
    			newVelocity = 0;
    			setVelocity(newVelocity);
    		}else if (closetObstacle > getMaxVelocity()){
    			newVelocity = getMaxVelocity();
    			setVelocity(newVelocity);
    		}
    		nextFrontPosition = getFrontPosition() + getVelocity() * getTimeStep();
		
    		setFrontPosition(nextFrontPosition);
    		
    	
    		getCurrentRoad().accept(this, getFrontPosition());
    	
		//TimeServerLinked.getTimeServer().enqueue(timeStep + TimeServerLinked.getTimeServer().currentTime(),this);
	}









	public void setHRoad(CarAcceptor carGen) {
		throw new IllegalArgumentException();
		
	}


	public void setVRoad(CarAcceptor carGen) {
		throw new IllegalArgumentException();
		
	}


}
