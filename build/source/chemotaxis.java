import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class chemotaxis extends PApplet {

//declare bacteria variables here
Bacteria[] bob;
public void setup() {
  

  bob = new Bacteria[50];
  for (int i=0; i<bob.length; i++) {
    bob[i] = new Bacteria();
  }
  //initialize bacteria variables here
}

public void draw() {
  background(200);
  for (int i=0; i<bob.length; i++) {
    bob[i].show();
    bob[i].move();
  }
  //move and show the bacteria
}

class Bacteria {
  //lots of java!
  float myX, myY;
  Bacteria() {
    myX = (float) Math.random() * 300;
    myY = (float) Math.random() * 300;
  }
  public void move() {
    if (mouseX > myX) {
      myX += (float) (Math.random() * 7) - 2;
    } else {
      myX += (float) (Math.random() * 7) - 4;
    }
    if (mouseY > myY) {
      myY += (float) (Math.random() * 7) - 2;
    } else {
      myY += (float) (Math.random() * 7) - 4;
    }

  }
  public void show() {
    fill(100);
    ellipse(myX, myY, 20, 20);
  }
}
  public void settings() {  size(300,300); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "chemotaxis" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
