package finalProject.src.model;


import java.util.Random;

public enum MP {
	
	INSTANCE;
	private MP(){}
	
	Random r = new Random();
	
	
	private  double maxVelocity = 30.0;
	private  double minVelocity = 10.0;
	
	
	public  double getMaxVelocity() {
		return maxVelocity;
	}

	public  void setMaxVelocity(double Velocity) {
		maxVelocity = Velocity;
	}

	public  double getMinVelocity() {
		return minVelocity;
	}

	public  void setMinVelocity(double Velocity) {
		minVelocity = Velocity;
	}

	
	
	
	
	

	
	
	private  double maxStopDistance = 5.0;
	private  double minStopDistance = 3.0;
	private  double stopDistance;
	
	public  double getMaxStopDistance() {
		return maxStopDistance;
	}

	public  void setMaxStopDistance(double Distance) {
		maxStopDistance = Distance;
	}

	public  double getMinStopDistance() {
		return minStopDistance;
	}

	public  void setMinStopDistance(double Distance) {
		minStopDistance = Distance;
	}

	public  double getStopDistance() {
		return stopDistance = getMinStopDistance() + (getMaxStopDistance()  -getMinStopDistance()) * r.nextDouble() ;
	}


    
	
	
	
	private  double maxBrakedDistance = 10.0;
	private  double minBrakedDistance = 9.0;
	private  double brakeDistance;
	
	public  double getMaxBrakedDistance() {
		return maxBrakedDistance;
	}

	public  void setMaxBrakedDistance(double BrakedDistance) {
		maxBrakedDistance = BrakedDistance;
	}
	
	public  double getMinBrakedDistance() {
		return minBrakedDistance;
	}

	public  void setMinBrakedDistance(double BrakedDistance) {
		minBrakedDistance = BrakedDistance;
	}

	
	public  double getBrakedDistance() {
		return brakeDistance = getMinBrakedDistance() + ( getMaxBrakedDistance()  - getMinBrakedDistance()) * r.nextDouble();
	}
	
	
	
	
	
	


	private  double maxCarLength = 10.0;
	private  double minCarLength = 5.0;
	private  double Carlength;
	
	
	public  double getMaxCarLength() {
		return maxCarLength;
	}

	public  void setMaxCarLength(double Length) {
		maxCarLength = Length;
	}

	public  double getMinCarLength() {
		return minCarLength;
	}

	public  void setMinCarLength(double Length) {
		minCarLength = Length;
	}

	public  double getCarLength() {
		return Carlength = getMinCarLength() + ( getMaxCarLength() - getMinCarLength()) * r.nextDouble();
	}
	

	
	
	
	
	

	private  double maxGreenLight = 180.0;
	private  double minGreenLight = 30.0;
	private  double greenLight ;
	

	public  double getMaxGreenLight() {
		return maxGreenLight;
	}

	public  void setMaxGreenLight(double GreenLight) {
		maxGreenLight = GreenLight;
	}

	public  double getMinGreenLight() {
		return minGreenLight;
	}

	public  void setMinGreenLight(double GreenLight) {
		minGreenLight = GreenLight;
	}
	
	public  double getGreenLight() {
		return greenLight  = getMinGreenLight() + ( getMaxGreenLight() - getMinGreenLight()) * r.nextDouble() ;
	}
	
	
	



	private  double maxYellowLight = 5.0;
	private  double minYellowLight = 4.0;
	private  double yellowLight;
	
	
	public  double getMaxYellowLight() {
		return maxYellowLight;
	}

	public  void setMaxYellowLight(double YellowLight) {
		maxYellowLight = YellowLight;
	}

	public  double getMinYellowLight() {
		return minYellowLight;
	}

	public  void setMinYellowLight(double YellowLight) {
		minYellowLight = YellowLight;
	}
	
	public  double getYellowLight() {
		return yellowLight = getMinYellowLight() + ( getMaxYellowLight()  - getMinYellowLight()) * r.nextDouble();
	}

	
	


	private  double maxIntersectionLength = 15.0; 
	private  double minIntersectionLength = 10.0; 
	private  double intersectionNLength = ( getMaxIntersectionLength() + getMinIntersectionLength()) * .55; 
	private  double intersectionLength; 
	
	public  double getMaxIntersectionLength() {
		return maxIntersectionLength;
	}

	public  void setMaxIntersectionLength(double IntersectionLength) {
		maxIntersectionLength = IntersectionLength;
	}

	public  double getMinIntersectionLength() {
		return minIntersectionLength;
	}

	public  void setMinIntersectionLength(double IntersectionLength) {
		minIntersectionLength = IntersectionLength;
	}
	
	public  double getNormalIntersectionLength() {
		return intersectionNLength;
	}

	public  double getIntersectionLength() {
		return intersectionLength = getMinIntersectionLength()+ ( getMaxIntersectionLength()- getMinIntersectionLength()) * r.nextDouble();
	}

	
	

	private  double maxCarEntryRate = 25.0;
	private  double minCarEntryRate = 2.0;
	private  double carEntryRate;
	
	
	public  double getMaxCarEntryRate() {
		return maxCarEntryRate;
	}

	public  void setMaxCarEntryRate(double CarEntryRate) {
		maxCarEntryRate = CarEntryRate;
	}

	public  double getMinCarEntryRate() {
		return minCarEntryRate;
	}

	public  void setMinCarEntryRate(double CarEntryRate) {
		minCarEntryRate = CarEntryRate;
	}
	
	public  double getCarEntryRate() {
		return carEntryRate = getMinCarEntryRate()  + ( getMaxCarEntryRate() -  getMinCarEntryRate() ) * r.nextDouble();
	}



	private  String trafficPattern = "alternating";
	
	
	public  String getTrafficePattern() {
		return trafficPattern;
	}

	public  void setTrafficePattern() {
		
		if(trafficPattern.equals("alternating")){
			trafficPattern = "same";
		}else
			trafficPattern = "alternating";
	}

	


	private  int row = 2;
	private  int column = 3;

	public  int getRow() {
		return row;
	}

	public  void setRow(int NewRow) {
		row = NewRow;
	}

	public  int getColumn() {
		return column;
	}

	public  void setColumn(int NewColumn) {
		column = NewColumn;
	}
	
	



	private  double simRunTime = 1000.0;
	
	
	public  double getSimRunTime() {
		return simRunTime;
	}

	public  void setSimRunTime(double RunTime) {
		simRunTime = RunTime;
	}



	private  double simTimeStep = 0.01;


	public  double getSimTimeStep() {
		return simTimeStep;
	}

	public  void setSimTimeStep(double TimeStep) {
		simTimeStep = TimeStep;
	}
	
	
	private  double maxRoadLength = 500.0;
	private  double minRoadLength = 200.0;
	private  double RoadLength;
	private  double RoadNLength =( getMaxRoadLength() +  getMinRoadLength() ) * .55;
	public  double getMaxRoadLength() {
		return maxRoadLength;
	}

	public  void setMaxRoadLength(double RoadLength) {
		maxRoadLength = RoadLength;
	}

	public  double getMinRoadLength() {
		return minRoadLength;
	}

	public  void setMinRoadLength(double RoadLength) {
		minRoadLength = RoadLength;
	}
	
	
	public  double getNormalRoadLength() {
		return RoadNLength ;
	}
	
	public  double getRoadLength() {
		return RoadLength = getMinRoadLength()  + ( getMaxRoadLength() -  getMinRoadLength() ) * r.nextDouble();
	}
	
	
	

}
