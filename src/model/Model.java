package finalProject.src.model;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;




import finalProject.src.util.Animator;

/**
 * An example to model for a simple visualization.
 * The model contains roads organized in a matrix.
 * See {@link #Model(AnimatorBuilder, int, int)}.
 */
public final class Model extends Observable {
 
  private Animator _animator;
  private boolean _disposed;
  private double _time;

  /** Creates a model to be visualized using the <code>builder</code>.
   *  If the builder is null, no visualization is performed.
   *  The number of <code>rows</code> and <code>columns</code>
   *  indicate the number of {@link Light}s, organized as a 2D
   *  matrix.  These are sempted and surrounded by horizontal and
   *  vertical {@link Road}s.  For example, calling the constructor with 1
   *  row and 2 columns generates a model of the form:
   *  <pre>
   *     |  |
   *   --@--@--
   *     |  |
   *  </pre>
   *  where <code>@</code> is a {@link Light}, <code>|</code> is a
   *  vertical {@link Road} and <code>--</code> is a horizontal {@link Road}.
   *  Each road has one {@link Car}.
   *
   *  <p>
   *  The {@link AnimatorBuilder} is used to set up an {@link
   *  Animator}.
   *  {@link AnimatorBuilder#getAnimator()} is registered as
   *  an observer of this model.
   *  <p>
   */
  public Model(AnimatorBuilder builder, int rows, int columns) {
    if (rows < 0 || columns < 0 || (rows == 0 && columns == 0)) {
      throw new IllegalArgumentException();
    }
    if (builder == null) {
      builder = new NullAnimatorBuilder();
    }
   
    TimeServerLinked.setTimeServer();
    setup(builder, rows, columns);
    _animator = builder.getAnimator();
    super.addObserver(_animator);
  }

  /**
   * Run the simulation for <code>duration</code> model seconds.
   */
  public void run(double duration) {
    if (_disposed)
      throw new IllegalStateException();
    for (int i=0; i<duration; i++) {
    	
      _time+=MP.INSTANCE.getSimTimeStep();
      
      TimeServerLinked.getTimeServer().run(_time);
      super.setChanged();
      super.notifyObservers();
    }
  }

  /**
   * Throw away this model.
   */
  public void dispose() {
    _animator.dispose();
    _disposed = true;
  }

  /**
   * Construct the model, establishing correspondences with the visualizer.
   */
  private void setup(AnimatorBuilder builder, int rows, int columns) {
    
    CarAcceptor[][] intersections = new Light[rows][columns];
 
    // Add Lights
    for (int i=0; i<rows; i++) {
      for (int j=0; j<columns; j++) {
        intersections[i][j] = ModelFactory.newLight();
        builder.addLight((Light)intersections[i][j], i, j);
        TimeServerLinked.getTimeServer().enqueue(0,(Agent)intersections[i][j]);
      }
    }

    // Add Horizontal Roads
    boolean eastToWest = false;
    for (int i=0; i<rows; i++) {
    	 List<CarAcceptor> roads = new ArrayList<CarAcceptor>();
    	 int c = 0;
    	 CarAcceptor first =null;
    	 CarAcceptor end = null;
    	 CarAcceptor carGen = null;
      for (int j=0; j<=columns; j++) {
    	CarAcceptor l = ModelFactory.newHRoad();
        builder.addHorizontalRoad((Road)l, i, j, eastToWest);
        roads.add(l);
        if(j<columns){
            roads.add(intersections[i][j]);
        }
      }
      
      if(eastToWest){
    	  Collections.reverse(roads);
      }
      
      for(CarAcceptor a: roads){
    	  if(c==0){
    		  first = a;
    		  carGen = a;
    		  c++;
    	  }else{
    		  end = a;
    		  first.setnextHRoad(end);
    		  first = end;
    	  }
      }
      
      Agent car = new CarGen();
      car.setHRoad(carGen);
      CarAcceptor sink = new Sink(Double.POSITIVE_INFINITY);
      end.setnextHRoad(sink);
      TimeServerLinked.getTimeServer().enqueue(0,car);
      
      if(MP.INSTANCE.getTrafficePattern().equals("alternating"))
    	  eastToWest = !eastToWest;
   
    }

    // Add Vertical Roads
    boolean southToNorth = false;
    for (int j=0; j<columns; j++) {
    	List<CarAcceptor> roads = new ArrayList<CarAcceptor>();
   	 	int c = 0;
   	 	CarAcceptor first =null;
   	 	CarAcceptor end = null;
   	 	CarAcceptor carGen = null;
      for (int i=0; i<=rows; i++) {
    	CarAcceptor l = ModelFactory.newVRoad();
        builder.addVerticalRoad((Road)l, i, j, southToNorth);
        roads.add(l);
        if(i < rows){
            roads.add(intersections[i][j]);
        }
      }
      
      if(southToNorth){
    	  Collections.reverse(roads);
      }
      
      for(CarAcceptor a: roads){
    	  if(c==0){
    		  first = a;
    		  carGen = a;
    		  c++;
    	  }else{
    		  end = a;
    		  first.setnextVRoad(end);
    		  first = end;
    	  } 
      }

      Agent car = new CarGen();
      car.setVRoad(carGen);
      CarAcceptor sink = new Sink(Double.POSITIVE_INFINITY);
      end.setnextVRoad(sink);
      TimeServerLinked.getTimeServer().enqueue(0,car);
      
      if(MP.INSTANCE.getTrafficePattern().equals("alternating"))
    	  southToNorth = !southToNorth;
    }
  }
}
