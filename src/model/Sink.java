package finalProject.src.model;

import java.util.ArrayList;
import java.util.List;

public class Sink extends Road{
    
	private List<Car> _cars = new ArrayList<Car>();

	Sink(double endpoint){super(endpoint);};
	

	public void accept(Car c, double frontPosition) {
		if (c == null) { throw new IllegalArgumentException();}
		_cars.remove(c);
	}
	
	public double distanceToObstacle(Car c, double fromPosition) {
		return Double.POSITIVE_INFINITY;
	}

	public CarAcceptor getnextRoad(Car c) {
		throw new NullPointerException();
	}

	public double getEndPosition() {
		throw new NullPointerException();
	}
}
