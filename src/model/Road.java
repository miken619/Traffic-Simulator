package finalProject.src.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Road implements CarAcceptor {

	CarAcceptor _nextRoad;
	private List<Car> _cars = new ArrayList<Car>();
	double timeStep = MP.INSTANCE.getSimTimeStep();

	Road(double end) {}

	public void accept(Car c, double frontPosition) {
		if (c == null || frontPosition < 0.0) {
			throw new IllegalArgumentException();
		}
		_cars.remove(c);
		if (frontPosition > getEndPosition()) {
			getnextRoad(c).accept(c, frontPosition -getEndPosition());
		} else {
			c.setCurrentRoad(this);
			if (getnextRoad(c).hasLight()) {
				if (getnextRoad(c).getLight().carType(c.getDirection())
						&& (frontPosition > (getEndPosition() - 22)))
					c.setFrontPosition(getEndPosition() - 22);
			} else {
				c.setFrontPosition(frontPosition);
			}
			_cars.add(c);
			TimeServerLinked.getTimeServer().enqueue(
					timeStep + TimeServerLinked.getTimeServer().currentTime(),
					c);
		}
	}

	public double distanceToCarBack(Car a, double fromPosition){
		if (a == null || fromPosition < 0.0) {
			throw new IllegalArgumentException();
		}
		double carBackPosition = Double.POSITIVE_INFINITY;
		for (Car c : _cars)
			if (!c.equals(a)) {
				if (c.getbackPosition() >= fromPosition
						&& c.getbackPosition() < carBackPosition)
					carBackPosition = c.getbackPosition();
			}
		return carBackPosition;
	}

	public double distanceToObstacle(Car c, double fromPosition) {
		if (c == null || fromPosition < 0.0) {
			throw new IllegalArgumentException();
		}
		double obstaclePosition = this.distanceToCarBack(c, fromPosition);
		if (obstaclePosition == Double.POSITIVE_INFINITY) {

			obstaclePosition = getEndPosition()
					+ getnextRoad(c).distanceToObstacle(c, 0);
		}
		return obstaclePosition - fromPosition;
	}

	public List<Car> getCars() {
		return _cars;
	}
	
	public boolean hasLight() {
		return false;
	}
	
	
	
	
	
	public void setnextVRoad(CarAcceptor end) {
		throw new NullPointerException();
	}

	public LightController getLight() {
		throw new NullPointerException();
	}

	public CarAcceptor getCurrentRoad() {
		throw new NullPointerException();
	}

	public void setnextHRoad(CarAcceptor end) {
		throw new NullPointerException();
	}
}
