package finalProject.src.model.swing;

import finalProject.src.model.MP;


/**
 * Static class for visualization parameters.
 */
class VP {
  private VP() {}
  /** Width of model elements, in meters */
  static double elementWidth =MP.INSTANCE.getMaxCarLength();
  /** Gap between model elements, in meters */
  static double gap = 1;
  /** Width of the displayed graphics window, in pixels */
  static int displayWidth = 1500;
  /** Height of the displayed graphics window, in pixels */
  static int displayHeight = 1500;
  /** Delay introduced into each graphics update, in milliseconds */
  static int displayDelay = 50;
  /** Scalefactor relating model space to pixels, in pixels/meter */
  static double scaleFactor = .8;
}
