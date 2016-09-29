package finalProject.src.model;

import junit.framework.Assert;
import junit.framework.TestCase;

public final class LightTEST extends TestCase  {
	Road h; 
	Road v;
	Road l;
	Road testH;
	Road testV;
	Car c;
	
	public LightTEST(String name){
		super(name);
	}
	
	public void testSetGet(){
		l = new Light(100.0);
		h = new HorizontalRoad(100.0);
		v = new VerticalRoad (50.0);
		c = new Car();
	    
		
	
		try{l.setnextHRoad(v); ;Assert.fail();}catch(IllegalArgumentException e){};
		try{l.setnextVRoad(h); ;Assert.fail();}catch(IllegalArgumentException e){};
		
		try{l.setnextHRoad(null); ;Assert.fail();}catch(NullPointerException e){};
		try{l.setnextVRoad(null); ;Assert.fail();}catch(NullPointerException e){};
		
		c.setDirection("VERTICAL");
		l.setnextVRoad(v);
		l.setnextHRoad(h);
		Assert.assertSame(v,l.getnextRoad(c));
		c.setDirection("HORIZONTAL");
		Assert.assertSame(h, l.getnextRoad(c));
		
		
		try{((Agent)l).setHRoad(h); ;Assert.fail();}catch(NullPointerException e){};
		try{((Agent)l).setCurrentRoad(h); ;Assert.fail();}catch(NullPointerException e){};
		try{((Agent)l).setDirection("HORIZONTAL"); ;Assert.fail();}catch(NullPointerException e){};
		
		try{((Agent)l).setVRoad(v); ;Assert.fail();}catch(NullPointerException e){};
		try{((Agent)l).setCurrentRoad(v); ;Assert.fail();}catch(NullPointerException e){};
		try{((Agent)l).setDirection("VERTICAL"); ;Assert.fail();}catch(NullPointerException e){};

		
	}
	
	public void testLightState(){
		
		HLightYellow hY;
		HLightGreen hG;
		VLightGreen vG;
		VLightYellow vY;
		
		try{hY = new HLightYellow(-1.0); Assert.fail();}catch(IllegalArgumentException e){};
		try{hG = new HLightGreen(-1.0); Assert.fail();}catch(IllegalArgumentException e){};
		try{vG = new VLightGreen(-1.0); Assert.fail();}catch(IllegalArgumentException e){};
		try{vY = new VLightYellow(-1.0); Assert.fail();}catch(IllegalArgumentException e){};
		
		hY = new HLightYellow(10.0);
		hG = new HLightGreen(10.0);
		vG = new VLightGreen(10.0);
		vY = new VLightYellow(10.0);
		
		Assert.assertEquals(true,vG.carType("HORIZONTAL"));
		Assert.assertEquals(true,vY.carType("HORIZONTAL"));	
		Assert.assertEquals(false,vG.carType("VERTICAL"));
		Assert.assertEquals(false,vY.carType("VERTICAL"));
		
		
		Assert.assertEquals(false,hG.carType("HORIZONTAL"));
		Assert.assertEquals(false,hY.carType("HORIZONTAL"));	
		Assert.assertEquals(true,hG.carType("VERTICAL"));
		Assert.assertEquals(true,hY.carType("VERTICAL"));
		
	}

}
