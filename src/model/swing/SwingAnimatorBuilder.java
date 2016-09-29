package finalProject.src.model.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


import finalProject.src.model.AnimatorBuilder;
import finalProject.src.model.Car;


import finalProject.src.model.Light;
import finalProject.src.model.MP;
import finalProject.src.model.Road;
import finalProject.src.util.Animator;
import finalProject.src.util.SwingAnimator;
import finalProject.src.util.SwingAnimatorPainter;

/** 
 * A class for building Animators.
 */
public class SwingAnimatorBuilder implements AnimatorBuilder {
  MyPainter _painter;
  public SwingAnimatorBuilder() {
    _painter = new MyPainter();
  }
  public Animator getAnimator() {
    if (_painter == null) { throw new IllegalStateException(); }
    Animator returnValue = new SwingAnimator(_painter, "Traffic Simulator",
                                             VP.displayWidth, VP.displayHeight, VP.displayDelay);
    _painter = null;
    return returnValue;
  }
  private static double skipInit = VP.gap;
  private static double skipRoad = VP.gap + MP.INSTANCE.getNormalRoadLength();
  private static double skipCar = VP.gap + VP.elementWidth;
  private static double skipRoadCar = skipRoad + skipCar;
  
  public void addLight(Light d, int i, int j) {
    double x = skipInit + skipRoad + j*skipRoadCar;
    double y = skipInit + skipRoad + i*skipRoadCar;
    Translator t = new TranslatorWE(x, y, MP.INSTANCE.getNormalIntersectionLength(), VP.elementWidth, VP.scaleFactor);
    _painter.addLight(d,t);
  }
  public void addHorizontalRoad(Road l, int i, int j, boolean eastToWest) {
    double x = skipInit + j*skipRoadCar;
    double y = skipInit + skipRoad + i*skipRoadCar;
    Translator t = eastToWest ? new TranslatorEW(x, y, MP.INSTANCE.getNormalRoadLength(), VP.elementWidth, VP.scaleFactor)
                              : new TranslatorWE(x, y, MP.INSTANCE.getNormalRoadLength(), VP.elementWidth, VP.scaleFactor);
    _painter.addRoad(l,t);
  }
  public void addVerticalRoad(Road l, int i, int j, boolean southToNorth) {
    double x = skipInit + skipRoad + j*skipRoadCar;
    double y = skipInit + i*skipRoadCar;
    Translator t = southToNorth ? new TranslatorSN(x, y, MP.INSTANCE.getNormalRoadLength(), VP.elementWidth, VP.scaleFactor)
                                : new TranslatorNS(x, y, MP.INSTANCE.getNormalRoadLength(), VP.elementWidth, VP.scaleFactor);
    _painter.addRoad(l,t);
  }


  /** Class for drawing the Model. */
  private static class MyPainter implements SwingAnimatorPainter {

    /** Pair of a model element <code>x</code> and a translator <code>t</code>. */
    private static class Element<T> {
      T x;
      Translator t;
      Element(T x, Translator t) {
        this.x = x;
        this.t = t;
      }
    }
    
    private List<Element<Road>> _roadElements;
    private List<Element<Light>> _lightElements;
    
    
    MyPainter() {
      _roadElements = new ArrayList<Element<Road>>();
      _lightElements = new ArrayList<Element<Light>>();
    }    
    void addLight(Light x, Translator t) {
      _lightElements.add(new Element<Light>(x,t));
    }
    void addRoad(Road x, Translator t) {
      _roadElements.add(new Element<Road>(x,t));
    }
    
    
    
    public void paint(Graphics g) {
      // This method is called by the swing thread, so may be called
      // at any time during execution...

      // First draw the background elements
    	g.setColor(Color.DARK_GRAY);  
    	 for (Element<Light> e : _lightElements) {
    	
    		 XGraphics.fillRect(g, e.t, -1*(MP.INSTANCE.getNormalRoadLength()+MP.INSTANCE.getNormalIntersectionLength()), -1*(MP.INSTANCE.getNormalRoadLength()+MP.INSTANCE.getNormalIntersectionLength()), 
    				            2*(MP.INSTANCE.getNormalRoadLength()+MP.INSTANCE.getNormalIntersectionLength()*2), 2*(MP.INSTANCE.getNormalRoadLength()+MP.INSTANCE.getNormalIntersectionLength()*2)); 
    	 }
    	
    	
      g.setColor(Color.BLACK);  
      for (Element<Road> e : _roadElements) {
    	 
        XGraphics.fillRect(g, e.t, 0, 0, MP.INSTANCE.getNormalRoadLength()+MP.INSTANCE.getNormalIntersectionLength() , VP.elementWidth*1.3);
        XGraphics.fillRect(g, e.t, 0, 0, MP.INSTANCE.getNormalRoadLength()+MP.INSTANCE.getNormalIntersectionLength() , -VP.elementWidth*.4);
      }
      
      // Then draw the foreground elements
      for (Element<Road> e : _roadElements) {
        // iterate through a copy because e.x.getCars() may change during iteration...
    	  
        for (Car d : e.x.getCars().toArray(new Car[0])) {
        	
        	double normalizedPosition = (MP.INSTANCE.getNormalRoadLength()*(d.getFrontPosition()/e.x.getEndPosition()))-(d.getCarLength()/2);

        	
          g.setColor(d.getColor());
          XGraphics.fillOval(g, e.t, normalizedPosition,0, d.getCarLength(), d.getCarLength() );
        
        }
      }
      
      for (Element<Light> e : _lightElements) {
          
    	  if(e.x.direction()){
    		  g.setColor(e.x.getState());
        
    		  XGraphics.fillRect(g, e.t,VP.elementWidth*1.3,-VP.elementWidth*.4, VP.elementWidth*.5, VP.elementWidth*1.7);
    		  XGraphics.fillRect(g, e.t,-VP.elementWidth*.8, -VP.elementWidth*.4, VP.elementWidth*.5, VP.elementWidth*1.7);
          
    		  g.setColor(Color.RED);
          
    		  XGraphics.fillRect(g, e.t,-VP.elementWidth*.4, VP.elementWidth*1.2, VP.elementWidth*1.7, VP.elementWidth*.5);
    		  XGraphics.fillRect(g, e.t,-VP.elementWidth*.4, -VP.elementWidth*.9, VP.elementWidth*1.7, VP.elementWidth*.5);
          
    	  }else{
    		  
    		  g.setColor(Color.RED);
    	        
    		  XGraphics.fillRect(g, e.t,VP.elementWidth*1.3, -VP.elementWidth*.4, VP.elementWidth*.5, VP.elementWidth*1.7);
    		  XGraphics.fillRect(g, e.t,-VP.elementWidth*.8,-VP.elementWidth*.4, VP.elementWidth*.5, VP.elementWidth*1.7);
    		 
    		  g.setColor(e.x.getState());
          
    		  XGraphics.fillRect(g, e.t,-VP.elementWidth*.4, VP.elementWidth*1.2, VP.elementWidth*1.7, VP.elementWidth*.5);
    		  XGraphics.fillRect(g, e.t,-VP.elementWidth*.4, -VP.elementWidth*.9, VP.elementWidth*1.7, VP.elementWidth*.5);
          
    		  
    		  
    	  }
        
         
          
       
        for (Car d : e.x.getCars().toArray(new Car[0])) {
        	double normalizedPosition = (MP.INSTANCE.getNormalIntersectionLength()*(d.getFrontPosition()/e.x.getEndPosition()))-(d.getCarLength()/2);

        	
        	g.setColor(d.getColor());
            XGraphics.fillOval(g, e.t,normalizedPosition ,0, d.getCarLength(), d.getCarLength());
           
          }
        
      }
      
      
     
    }
  }
}

