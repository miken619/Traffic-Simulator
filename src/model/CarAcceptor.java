package finalProject.src.model;

public interface CarAcceptor {
	
	public CarAcceptor getnextRoad(Car c);
		
	public void setnextHRoad(CarAcceptor end);

	public void setnextVRoad(CarAcceptor end);

	public double getEndPosition();

	public LightController getLight();

	public boolean hasLight();
	
	public double distanceToObstacle(Car newCar, double frontPosition);

	public void accept(Car car, double frontPosition);


}
