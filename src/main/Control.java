package finalProject.src.main;

import finalProject.src.model.MP;
import finalProject.src.model.Model;
import finalProject.src.model.swing.SwingAnimatorBuilder;
import finalProject.src.ui.UI;
import finalProject.src.ui.UIError;
import finalProject.src.ui.UIForm;
import finalProject.src.ui.UIFormBuilder;
import finalProject.src.ui.UIFormTest;
import finalProject.src.ui.UIMenu;
import finalProject.src.ui.UIMenuAction;
import finalProject.src.ui.UIMenuBuilder;

class Control {
	private static final int EXITED = 0;
	private static final int EXIT = 1;
	private static final int START = 2;
	private static final int CHANGEMP = 3;
	private static final int NUMSTATES = 5;
	private UIMenu[] _menus;
	private int _state;

	
	private UIFormTest _numberTest;


	
	private UI _ui;

	Control(UI ui) {

		_ui = ui;

		_menus = new UIMenu[NUMSTATES];
		_state = START;
		addSTART(START);
		changeMP(CHANGEMP);
		addEXIT(EXIT);
		
		_numberTest = new UIFormTest() {
	        public boolean run(String input) {
	          try {
	            Integer.parseInt(input);
	            return true;
	          } catch (NumberFormatException e) {
	            return false;
	          }
	        }
	      };

	}
	
	
      
	
	void run() {
		try {
			while (_state != EXITED) {
				_ui.processMenu(_menus[_state]);
			}
		} catch (UIError e) {
			_ui.displayError("UI closed");
		}
	}

	private void addSTART(int stateNum) {
		UIMenuBuilder m = new UIMenuBuilder();

		m.add("Default", new UIMenuAction() {
			public void run() {
				_ui.displayError("doh!");
			}
		});
		m.add("Run Simulation", new UIMenuAction() {
			public void run() {
				Model model = new Model (new SwingAnimatorBuilder(),MP.INSTANCE.getRow(),MP.INSTANCE.getColumn());
				model.run(MP.INSTANCE.getSimRunTime());
				model.dispose();
				
				
			}
		});
		m.add("Change simulation", new UIMenuAction() {
			public void run() {
				_state = CHANGEMP;

			}
		});

		m.add("Exit", new UIMenuAction() {
			public void run() {
				_state = EXIT;
			}
		});

		_menus[stateNum] = m.toUIMenu("Mike's Traffic Simulation. \n\n");
	}

	private void changeMP(int stateNum) {
		UIMenuBuilder m = new UIMenuBuilder();
		
		m.add("Default", new UIMenuAction() {
			public void run() {
				_ui.displayError("doh!");
			}
		});

		m.add("Show current values", new UIMenuAction() {
			public void run() {
				showMP();
				
			}
		});
		m.add("Simulation time step", new UIMenuAction() {
			public void run() {
				
				 UIFormBuilder f = new UIFormBuilder();
		         f.add("Enter a new time step", _numberTest);
		         String[] result2 = _ui.processForm(f.toUIForm(""));
		         MP.INSTANCE.setSimTimeStep(Double.parseDouble(result2[0]));

			}
		});
		m.add("Simulation run time", new UIMenuAction() {
			public void run() {
				
				UIFormBuilder f = new UIFormBuilder();
		         f.add("Enter a new Simulation run time", _numberTest);
		         String[] result2 = _ui.processForm(f.toUIForm(""));
		         MP.INSTANCE.setSimRunTime(Double.parseDouble(result2[0]));

			}
		});

		m.add("Grid size", new UIMenuAction() {
			public void run() {
				
				UIFormBuilder f = new UIFormBuilder();
		         f.add("Enter a new number of rows", _numberTest);
		         f.add("Enter a new number of columns", _numberTest);
		         String[] result2 = _ui.processForm(f.toUIForm(""));
		         MP.INSTANCE.setRow(Integer.parseInt(result2[0]));
		         MP.INSTANCE.setColumn(Integer.parseInt(result2[1]));

			}
		});

		m.add("Traffic pattern", new UIMenuAction() {
			public void run() {
				
				UIFormBuilder f = new UIFormBuilder();
		         f.add("Change traffic pattens \n" +
		         		"1. Yes \n" +
		         		"2. No", _numberTest);
		         
		         String[] result2 = _ui.processForm(f.toUIForm(""));
		         if(Integer.parseInt(result2[0])==1)
		        	 MP.INSTANCE.setTrafficePattern();
		        

				
			}
		});
		m.add("Car entry rate", new UIMenuAction() {
			public void run() {
				
				UIFormBuilder f = new UIFormBuilder();
		         f.add("Enter a new minimum entry rate", _numberTest);
		         f.add("Enter a new maximum entry rate", _numberTest);
		         String[] result2 = _ui.processForm(f.toUIForm(""));
		         MP.INSTANCE.setMinCarEntryRate(Double.parseDouble(result2[0]));
		         MP.INSTANCE.setMaxCarEntryRate(Double.parseDouble(result2[1]));

			}
		});
		m.add("Road segment length", new UIMenuAction() {
			public void run() {
				
				 UIFormBuilder f = new UIFormBuilder();
		         f.add("Enter a new minimum segment length", _numberTest);
		         f.add("Enter a new maximum segment length", _numberTest);
		         String[] result2 = _ui.processForm(f.toUIForm(""));
		         MP.INSTANCE.setMinRoadLength(Double.parseDouble(result2[0]));
		         MP.INSTANCE.setMaxRoadLength(Double.parseDouble(result2[1]));

			}
		});

		m.add("Intersection length", new UIMenuAction() {
			public void run() {
				
				 UIFormBuilder f = new UIFormBuilder();
		         f.add("Enter a new minimum intersection length", _numberTest);
		         f.add("Enter a new maximum intersection length", _numberTest);
		         String[] result2 = _ui.processForm(f.toUIForm(""));
		         MP.INSTANCE.setMinIntersectionLength(Double.parseDouble(result2[0]));
		         MP.INSTANCE.setMaxIntersectionLength(Double.parseDouble(result2[1]));

			}
		});

		m.add("Car length", new UIMenuAction() {
			public void run() {
				
				 UIFormBuilder f = new UIFormBuilder();
		         f.add("Enter a new minimum car length", _numberTest);
		         f.add("Enter a new maximum car length", _numberTest);
		         String[] result2 = _ui.processForm(f.toUIForm(""));
		         MP.INSTANCE.setMinCarLength(Double.parseDouble(result2[0]));
		         MP.INSTANCE.setMaxCarLength(Double.parseDouble(result2[1]));
			
			}
		});
		m.add("Car maximum velocity", new UIMenuAction() {
			public void run() {
				
				UIFormBuilder f = new UIFormBuilder();
		         f.add("Enter a new minimum car velocity", _numberTest);
		         f.add("Enter a new maximum car velocity", _numberTest);
		         String[] result2 = _ui.processForm(f.toUIForm(""));
		         MP.INSTANCE.setMinVelocity(Double.parseDouble(result2[0]));
		         MP.INSTANCE.setMaxVelocity(Double.parseDouble(result2[1]));

			}
		});
		m.add("Car stop distance", new UIMenuAction() {
			public void run() {
				
				UIFormBuilder f = new UIFormBuilder();
		         f.add("Enter a new minimum car stop distance", _numberTest);
		         f.add("Enter a new maximum car stop distance", _numberTest);
		         String[] result2 = _ui.processForm(f.toUIForm(""));
		         MP.INSTANCE.setMinStopDistance(Double.parseDouble(result2[0]));
		         MP.INSTANCE.setMaxStopDistance(Double.parseDouble(result2[1]));
				

			}
		});
		
		m.add("Car brake distance", new UIMenuAction() {
			public void run() {
				
				UIFormBuilder f = new UIFormBuilder();
		         f.add("Enter a new minimum car stop distance", _numberTest);
		         f.add("Enter a new maximum car stop distance", _numberTest);
		         String[] result2 = _ui.processForm(f.toUIForm(""));
		         MP.INSTANCE.setMinBrakedDistance(Double.parseDouble(result2[0]));
		         MP.INSTANCE.setMaxBrakedDistance(Double.parseDouble(result2[1]));
				

			}
		});

		m.add("Traffic light green time", new UIMenuAction() {
			public void run() {
				
				UIFormBuilder f = new UIFormBuilder();
		         f.add("Enter a new minimum traffic light green time", _numberTest);
		         f.add("Enter a new maximum traffic light green time", _numberTest);
		         String[] result2 = _ui.processForm(f.toUIForm(""));
		         MP.INSTANCE.setMinGreenLight(Double.parseDouble(result2[0]));
		         MP.INSTANCE.setMaxGreenLight(Double.parseDouble(result2[1]));

			}
		});

		m.add("Traffic light yellow time", new UIMenuAction() {
			public void run() {
				
				UIFormBuilder f = new UIFormBuilder();
		         f.add("Enter a new minimum traffic light yellow time", _numberTest);
		         f.add("Enter a new maximum traffic light yellow time", _numberTest);
		         String[] result2 = _ui.processForm(f.toUIForm(""));
		         MP.INSTANCE.setMinYellowLight(Double.parseDouble(result2[0]));
		         MP.INSTANCE.setMaxYellowLight(Double.parseDouble(result2[1]));


			}
		});
		m.add("Reset simulation and return to the main menu",
				new UIMenuAction() {
					public void run() {
						_state = START;

					}
				});
		
		_menus[stateNum] = m.toUIMenu("What would you like to change. \n\n");

	}

	private void showMP() {
		StringBuilder build = new StringBuilder();
		
		build.append("Simulation time step (seconds)                         [" + MP.INSTANCE.getSimTimeStep() + "]\n");
		build.append("Simulation run time (seconds)                           [" + MP.INSTANCE.getSimRunTime() + "]\n");
		build.append("Grid size (number of roads)                                [min=" + MP.INSTANCE.getRow() + ", max=" + MP.INSTANCE.getColumn() + "]\n");
		build.append("Traffic pattern                                                        [" + MP.INSTANCE.getTrafficePattern() + "]\n");
		build.append("Car entry rate (seconds/car)                              [min=" + MP.INSTANCE.getMinCarEntryRate() + ", max=" + MP.INSTANCE.getMaxCarEntryRate() + "]\n");
		build.append("Road segment length (meters)                          [min=" + MP.INSTANCE.getMinRoadLength() + ", max=" + MP.INSTANCE.getMaxRoadLength() + "]\n");
		build.append("Intersection length (meters)                               [min=" + MP.INSTANCE.getMinIntersectionLength() + ", max=" + MP.INSTANCE.getMaxIntersectionLength() + "]\n");
		build.append("Car length (meters)                                               [min=" + MP.INSTANCE.getMinCarLength() + ", max=" + MP.INSTANCE.getMaxCarLength() + "]\n");
		build.append("Car maximum velocity (meters/second)         [min=" + MP.INSTANCE.getMinVelocity() + ", max=" + MP.INSTANCE.getMaxVelocity() + "]\n");
		build.append("Car stop distance (meters)                                 [min=" + MP.INSTANCE.getMinStopDistance() + ", max=" + MP.INSTANCE.getMaxStopDistance() + "]\n");
		build.append("Car brake distance (meters)                              [min=" + MP.INSTANCE.getMinBrakedDistance() + ", max=" + MP.INSTANCE.getMaxBrakedDistance() + "]\n");
		build.append("Traffic light green time (seconds)                     [min=" + MP.INSTANCE.getMinGreenLight()  + ", max=" + MP.INSTANCE.getMaxGreenLight()  + "]\n");
		build.append("Traffic light yellow time (seconds)                    [min=" + MP.INSTANCE.getMinYellowLight()  + ", max=" + MP.INSTANCE.getMaxYellowLight()  + "]\n");
		
		
		_ui.displayMessage(build.toString());
		
	}

	
	
	
	
	private void addEXIT(int stateNum) {
		UIMenuBuilder m = new UIMenuBuilder();

		m.add("Default", new UIMenuAction() {
			public void run() {
			}
		});
		m.add("Yes", new UIMenuAction() {
			public void run() {
				_state = EXITED;
			}
		});
		m.add("No", new UIMenuAction() {
			public void run() {
				_state = START;
			}
		});

		_menus[stateNum] = m.toUIMenu("Are you sure you want to exit?");
	}
}
