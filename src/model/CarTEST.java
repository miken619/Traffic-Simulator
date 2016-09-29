package finalProject.src.model;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CarTEST extends TestCase{
	Car car = new Car ();
	CarAcceptor h = new HorizontalRoad(100);
	CarAcceptor v = new HorizontalRoad(100);
	
	public CarTEST(String name) {
	    super(name);
	  }
	public void testSetGetMethod(){
		
		car.setVelocity(MP.INSTANCE.getMaxVelocity());
		Assert.assertEquals( MP.INSTANCE.getMaxVelocity(), car.getVelocity() );
		car.setVelocity(MP.INSTANCE.getMinVelocity());
		Assert.assertEquals(MP.INSTANCE.getMinVelocity(), car.getVelocity() );
		
		car.setBrakeDistance(MP.INSTANCE.getMinBrakedDistance());
		Assert.assertEquals( MP.INSTANCE.getMinBrakedDistance(), car.getBrakeDistance() );
		car.setBrakeDistance( MP.INSTANCE.getMaxBrakedDistance());
		Assert.assertEquals(  MP.INSTANCE.getMaxBrakedDistance(), car.getBrakeDistance() );
		
		car.setStopDistance(MP.INSTANCE.getMinStopDistance());
		Assert.assertEquals( MP.INSTANCE.getMinStopDistance(), car.getStopDistance() );
		car.setStopDistance( MP.INSTANCE.getMaxStopDistance());
		Assert.assertEquals(  MP.INSTANCE.getMaxStopDistance(), car.getStopDistance() );
		
		car.setDirection ("VERTICAL");
		Assert.assertEquals( "VERTICAL", car.getDirection() );
		car.setDirection("HORIZONTAL");
		Assert.assertEquals( "HORIZONTAL", car.getDirection() );
		
		car.setFrontPosition(MP.INSTANCE.getMinStopDistance());
		Assert.assertEquals( MP.INSTANCE.getMinStopDistance(), car.getFrontPosition() );
		car.setFrontPosition( MP.INSTANCE.getMaxStopDistance());
		Assert.assertEquals(  MP.INSTANCE.getMaxStopDistance(), car.getFrontPosition() );
		
		car.setCarLength(MP.INSTANCE.getMaxCarLength());
		Assert.assertEquals( MP.INSTANCE.getMaxCarLength(), car.getCarLength() );
		car.setCarLength( MP.INSTANCE.getMinCarLength());
		Assert.assertEquals(MP.INSTANCE.getMinCarLength(), car.getCarLength() );
		
		car.setTimeStep(MP.INSTANCE.getMaxCarLength());
		Assert.assertEquals( MP.INSTANCE.getMaxCarLength(), car.getTimeStep() );
		car.setTimeStep(MP.INSTANCE.getMinCarLength());
		Assert.assertEquals( MP.INSTANCE.getMinCarLength(), car.getTimeStep() );
		
		car.setFrontPosition(20.0);
		car.setCarLength(10.0);
		
		Assert.assertEquals( 20.0-10.0, car.getbackPosition() );
		
		car.setCurrentRoad (h);
		Assert.assertSame( h, car.getCurrentRoad() );
		car.setCurrentRoad (v);
		Assert.assertSame( v, car.getCurrentRoad() );
	}
	
	public void testException(){
		
		
		try{car.setVelocity(MP.INSTANCE.getMaxVelocity()+1);Assert.fail();}catch(IllegalArgumentException e){};
		
		try{car.setVelocity(-1);Assert.fail();}catch(IllegalArgumentException e){};
		
		try{car.setBrakeDistance(MP.INSTANCE.getMaxBrakedDistance()+1);Assert.fail();}catch(IllegalArgumentException e){};  

		try{car.setBrakeDistance( MP.INSTANCE.getMinBrakedDistance()-1);Assert.fail();}catch(IllegalArgumentException e){};
		
		try{car.setStopDistance(MP.INSTANCE.getMaxStopDistance()+1);Assert.fail();}catch(IllegalArgumentException e){};
		
		try{car.setStopDistance(MP.INSTANCE.getMinStopDistance()-1);Assert.fail();}catch(IllegalArgumentException e){};
		
		try{car.setDirection("column");Assert.fail();}catch(IllegalArgumentException e){};
		
		try{car.setDirection("straight");Assert.fail();}catch(IllegalArgumentException e){};
		
		try{car.setDirection(null);Assert.fail();}catch(NullPointerException e){};
		
		try{car.setFrontPosition(-1.0);Assert.fail();}catch(IllegalArgumentException e){};
		
		try{car.setCarLength(MP.INSTANCE.getMaxCarLength()+1);Assert.fail();}catch(IllegalArgumentException e){};
		
		try{car.setCarLength(MP.INSTANCE.getMinCarLength()-1);Assert.fail();}catch(IllegalArgumentException e){};
		
		try{car.setTimeStep(-1);Assert.fail();}catch(IllegalArgumentException e){};
		
		try{car.setHRoad(h);Assert.fail();}catch(IllegalArgumentException e){};
		
		try{car.setVRoad(v);Assert.fail();}catch(IllegalArgumentException e){};
		
		try{car.setCurrentRoad(null);Assert.fail();}catch(NullPointerException e){};
		
	}

}
