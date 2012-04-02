// GGKinect.java

/*
This software is an add-on to the JGameGrid package.
It is Open Source Free Software, so you may
- run the code for any purpose
- study how the code works and adapt it to your needs
- integrate all or parts of the code in your own programs
- redistribute copies of the code
- improve the code and release your improvements to the public
However the use of the code is entirely your responsibility.

Author: Aegidius Pluess, www.aplu.ch
 */
package ch.aplu.ggkinect;

import ch.aplu.jgamegrid.*;
import ch.aplu.jaw.*;
import ch.aplu.kinect.*;
import java.awt.*;

/**
 * Class to handle Microsoft's Kinect device using KinectJLib, a Java
 * wrapper for the native Kinect JDK. Only Windows 7 and higher is supported.
 * The native library KinectHander.dll (for 32-bit JVM) or KinectHandler64.dll
 * (for 64-bit JVM) must reside in the system's path. All needed library files
 * can be downloaded from www.aplu.ch/kinect.
 */
public class GGKinect extends Kinect
{
  /**
   * Enumeration for the style of the native video window.
   */
  public enum DecorationStyle
  {
    /**
     *  Shows a standard window with a title bar containing the usual system menu.
     * The window is not resizable.
     */
    STANDARD,
    /**
     * Shows a window with standard frame but no system menu.
     * The window is not resizable.
     */
    NOMENU,
    /**
     * Shows a window with no border.
     * The window is not resizable.
     */
    UNDECORATED,
    /**
     * Hides the video window.
     */
    HIDDEN
  };
  //
  private static final long[] decorationFlag =
  {
    // with title bar, system menu, not resizable
    NativeHandler.WS_SYSMENU | NativeHandler.WS_MAXIMIZEBOX
    | NativeHandler.WS_MINIMIZEBOX | NativeHandler.WS_VISIBLE,
    // with title bar, no system menu, not resizable
    NativeHandler.WS_BORDER | NativeHandler.WS_VISIBLE,
    // no title bar, not resizable
    NativeHandler.WS_POPUP | NativeHandler.WS_THICKFRAME
    | NativeHandler.WS_VISIBLE,
    // hidden
    0
  };

  /** 
   * Creates a abstraction of the Kinect device. If not hidden, shows a native
   * window with the current video image in real-time using DirectX performance
   * boost. The color video image is captured in 640 x 480 pixel resolution.
   * The microphone array is disabled.
   * @param dllPath the path to the native library DLL. Provide either the full 
   * path (Drive:/Directory/Filename) or the DLL name without extension. 
   * In the latter case, the DLL is searched in the Windows system path.
   * @param title the title of in the native window title bar
   * @param ulx the native window upper left x-coordinate (in pixels)
   * @param uly the native window upper left y-coordinate (in pixels)
   * @param width the width of the native window
   * @param height the height of the window
   * @param style one of the enumerations in DecorationStyle
   */
  public GGKinect(String dllPath, String title, int ulx, int uly,
    int width, int height, DecorationStyle style)
  {
    super(dllPath, title, ulx, uly, width, height, decorationFlag[style.ordinal()], 0);
  }

  /** 
   * Creates a abstraction of the Kinect device. If not hidden, shows a native
   * window with the current video image in real-time using DirectX performance
   * boost. The color video image is captured in 640 x 480 pixel resolution.
   * The audio capture uses the following format: 22050 Hz sampling rate, 8 bit,
   * mono, signed, little endian. Sound samples are captured in a audio buffer of
   * given size and analysed to deliver the sound level by polling or event notification.
   * @param dllPath the path to the native library DLL. Provide either the full 
   * path (Drive:/Directory/Filename) or the DLL name without extension. 
   * In the latter case, the DLL is searched in the Windows system path
   * @param title the title of in the native window title bar
   * @param ulx the native window upper left x-coordinate (in pixels)
   * @param uly the native window upper left y-coordinate (in pixels)
   * @param width the width of the native window
   * @param height the height of the window
   * @param style one of the enumarations in DecorationStyle
   * @param audioBufferSize if greater than zero, the microphone array
   * is enabled for sound level detection
   */
  public GGKinect(String dllPath, String title, int ulx, int uly,
    int width, int height, DecorationStyle style, int audioBufferSize)
  {
    super(dllPath, title, ulx, uly, width, height,
      decorationFlag[style.ordinal()], audioBufferSize);
  }

  /**
   * Draws the given skeleton joints as colored circle of given size and color.
   * @param gg the GameGrid reference where the skeletal joints are drawn 
   * @param joints the joints to draw
   * @param radius the radius of the filled circles (in pixels)
   * @param color the color of the filled circles
   */
  public void drawJoints(GameGrid gg, Point[] joints, int radius, Color color)
  {
    GGBackground bg = gg.getBg();
    Color oldColor = bg.getPaintColor();
    bg.setPaintColor(color);
    for (int i = 0; i < joints.length; i++)
      gg.getBg().fillCircle(joints[i], radius);
    bg.setPaintColor(oldColor);
  }

  /**
   * Draws a connection of the given skeleton joints as colored lines.
   * @param gg the GameGrid reference where the skeletal segments are drawn 
   * @param joints the Point array of all joints of the skeleton (the joints are not shown)
   * @param lineWidth the line width (in pixels)
   * @param color the color of the lines
   * @param connectedJoints the SkeletonJoints to be connect by lines
   */
  public void drawSegments(GameGrid gg, Point[] joints,
    int lineWidth, Color color, SkeletonJoint... connectedJoints)
  {
    GGBackground bg = gg.getBg();
    int oldWidth = bg.getLineWidth();
    Color oldColor = bg.getPaintColor();
    bg.setLineWidth(lineWidth);
    bg.setPaintColor(color);
    for (int i = 0; i < connectedJoints.length - 1; i++)
    {
      Point start = joints[connectedJoints[i].ordinal()];
      Point end = joints[connectedJoints[i + 1].ordinal()];
      gg.getBg().drawLine(start, end);
    }
    bg.setLineWidth(oldWidth);
    bg.setPaintColor(oldColor);
  }

  /**
   * Draws all corresponding segments of the give skeleton joints as colored lines.
   * @param gg the GameGrid reference where the skeleton is drawn
   * @param joints the Point array of all joints of the skeleton (the joints are not shown)
   * @param lineWidth the line width (in pixels)
   * @param color the color of the lines
   */
  public void drawSkeleton(GameGrid gg, Point[] joints, int lineWidth, Color color)
  {
    drawSegments(gg, joints, lineWidth, color, SkeletonJoint.HIP_CENTER, SkeletonJoint.SPINE, SkeletonJoint.SHOULDER_CENTER, SkeletonJoint.HEAD);
    drawSegments(gg, joints, lineWidth, color, SkeletonJoint.SHOULDER_CENTER, SkeletonJoint.SHOULDER_LEFT, SkeletonJoint.ELBOW_LEFT,
      SkeletonJoint.WRIST_LEFT, SkeletonJoint.HAND_LEFT);
    drawSegments(gg, joints, lineWidth, color, SkeletonJoint.SHOULDER_CENTER, SkeletonJoint.SHOULDER_LEFT, SkeletonJoint.ELBOW_LEFT,
      SkeletonJoint.WRIST_LEFT, SkeletonJoint.HAND_LEFT);
    drawSegments(gg, joints, lineWidth, color, SkeletonJoint.SHOULDER_CENTER, SkeletonJoint.SHOULDER_RIGHT, SkeletonJoint.ELBOW_RIGHT,
      SkeletonJoint.WRIST_RIGHT, SkeletonJoint.HAND_RIGHT);
    drawSegments(gg, joints, lineWidth, color, SkeletonJoint.HIP_CENTER, SkeletonJoint.HIP_LEFT, SkeletonJoint.KNEE_LEFT, SkeletonJoint.ANKLE_LEFT,
      SkeletonJoint.FOOT_LEFT);
    drawSegments(gg, joints, lineWidth, color, SkeletonJoint.HIP_CENTER, SkeletonJoint.HIP_RIGHT, SkeletonJoint.KNEE_RIGHT, SkeletonJoint.ANKLE_RIGHT,
      SkeletonJoint.FOOT_RIGHT);
  }
}
