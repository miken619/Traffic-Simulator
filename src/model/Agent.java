package finalProject.src.model;

public interface Agent {
	
	public void run ();

	public void setHRoad(CarAcceptor carGen);

	public void setVRoad(CarAcceptor carGen);

	public void setCurrentRoad(CarAcceptor currentRoad);

	public void setDirection(String direction);

	public CarAcceptor getCurrentRoad();

}
