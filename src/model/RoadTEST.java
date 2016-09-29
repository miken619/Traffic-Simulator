package finalProject.src.model;


import junit.framework.Assert;
import junit.framework.TestCase;

public class RoadTEST extends TestCase {
	Road h; 
	Road v; 
	Road testH;
	Road testV;
	Car c;
	
	
	public RoadTEST(String name) {
	    super(name);
	  }
	
	public void testVHRoads(){
		h = new HorizontalRoad(100.0);
		v = new VerticalRoad (50.0);
		testH = new HorizontalRoad(75.0);
		testV = new VerticalRoad (20.0);
        c = new Car();
		
		Assert.assertEquals(100.0, h.getEndPosition());
		Assert.assertEquals(50.0, v.getEndPosition());
		
		v.setnextVRoad(testV);
		h.setnextHRoad(testH);
		Assert.assertSame(testV,v.getnextRoad(c));
		Assert.assertSame(testH, h.getnextRoad(c));
		
		
		try{h = new HorizontalRoad(-1) ;Assert.fail();}catch(IllegalArgumentException e){};
		try{v = new VerticalRoad (-1) ;Assert.fail();}catch(IllegalArgumentException e){};
		
		try{h.setnextHRoad(v); ;Assert.fail();}catch(IllegalArgumentException e){};
		try{v.setnextVRoad(h); ;Assert.fail();}catch(IllegalArgumentException e){};
		
		try{h.setnextHRoad(null); ;Assert.fail();}catch(NullPointerException e){};
		try{v.setnextVRoad(null); ;Assert.fail();}catch(NullPointerException e){};
		
		try{h.getnextRoad(null); ;Assert.fail();}catch(NullPointerException e){};
		try{v.getnextRoad(null); ;Assert.fail();}catch(NullPointerException e){};
		
		
	}
	
	public void testRoad(){
		h = new HorizontalRoad(100.0);
		v = new VerticalRoad (50.0);
        c = new Car();
	
		try{h.accept(c,-1) ;Assert.fail();}catch(IllegalArgumentException e){};
		try{v.accept(c,-1) ;Assert.fail();}catch(IllegalArgumentException e){};
		
		try{h.accept(null, 1) ;Assert.fail();}catch(IllegalArgumentException e){};
		try{v.accept(null, 1) ;Assert.fail();}catch(IllegalArgumentException e){};
		
		try{h.distanceToCarBack(c,-1) ;Assert.fail();}catch(IllegalArgumentException e){};
		try{v.distanceToCarBack(c,-1) ;Assert.fail();}catch(IllegalArgumentException e){};
		
		try{h.distanceToCarBack(null, 1) ;Assert.fail();}catch(IllegalArgumentException e){};
		try{v.distanceToCarBack(null, 1) ;Assert.fail();}catch(IllegalArgumentException e){};
		
		try{h.distanceToObstacle(c,-1) ;Assert.fail();}catch(IllegalArgumentException e){};
		try{v.distanceToObstacle(c,-1) ;Assert.fail();}catch(IllegalArgumentException e){};
		
		try{h.distanceToObstacle(null, 1) ;Assert.fail();}catch(IllegalArgumentException e){};
		try{v.distanceToObstacle(null, 1) ;Assert.fail();}catch(IllegalArgumentException e){};
		
		Assert.assertEquals(false,v.hasLight());
		Assert.assertEquals(false, h.hasLight());
		
		try{h.setnextVRoad(v); ;Assert.fail();}catch(NullPointerException e){};
		try{h.getLight(); ;Assert.fail();}catch(NullPointerException e){};
		try{h.getCurrentRoad(); ;Assert.fail();}catch(NullPointerException e){};
		
		try{v.setnextHRoad(h); ;Assert.fail();}catch(NullPointerException e){};
		try{v.getLight(); ;Assert.fail();}catch(NullPointerException e){};
		try{v.getCurrentRoad(); ;Assert.fail();}catch(NullPointerException e){};
		
	}
	
	

}
