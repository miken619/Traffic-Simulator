package finalProject.src.model;

public class ModelFactory {

	public static Agent newCar(){
		return new CarGen();
	}
	
	public static Agent makeCar(){
		return new Car();
	}
	
	public static CarAcceptor newVRoad(){
		return new VerticalRoad(MP.INSTANCE.getRoadLength());
	}
	
	public static CarAcceptor newHRoad(){
		return new HorizontalRoad(MP.INSTANCE.getRoadLength());
	}
	
	public static CarAcceptor newLight(){
		return new Light(MP.INSTANCE.getIntersectionLength());
	}
	
	public static LightController newHGreen(){
		return new HLightGreen(MP.INSTANCE.getIntersectionLength());
	}
	
	public static LightController newVGreen(){
		return new VLightGreen(MP.INSTANCE.getIntersectionLength());
	}
	
	public static LightController newHYellow(){
		return new HLightYellow(MP.INSTANCE.getIntersectionLength());
	}
	
	public static LightController newVYellow(){
		return new VLightYellow(MP.INSTANCE.getIntersectionLength());
	}
}
