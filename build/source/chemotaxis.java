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
int xPos = 200, yPos = 200;
String[] message = new String[] {"debra", "bob", "clyde", "blinky", "bonnie", "bert" ,"bobert", "richard", "jenjamin", "betty", "betsy", "bennifer"};
boolean ownSword = false;
float sX, sY;
String[] killCount;
int kc = 0; //kill count
int kcWidth = 0;
boolean beingKilled;

public void setup() {
  
  bob = new Bacteria[70];
  for (int i=0; i<bob.length; i++) {
    bob[i] = new Bacteria();
  }
  textAlign(CENTER, CENTER);
  rectMode(CENTER);
  sX = (float) (Math.random() * width);
  sY = (float) (Math.random() * height);
  killCount = new String[bob.length];
}

public void draw() {
  background(197, 212, 221);
  for (int i=0; i<bob.length; i++) {
    bob[i].show();
    bob[i].move();
    beingKilled = false;
    if (abs(bob[i].myX - xPos) < 5 && abs(bob[i].myY - yPos) < 5 && bob[i].exist) {
      //background(204, 104, 104);
      fill(0);
      textSize(100);
      if (!ownSword) {
        text("rip", 200, 200);
        beingKilled = true;
      } else {
        text("k i l l", 200, 200);
        if (key == ' ') {
          bob[i].exist = false;
          killCount[kc] = message[bob[i].myMessage];
          kc++;
        }
      }
    }
  }
  person();
  sword();
  textSize(11);
  text("kill record: ", 50, 30);
  line(20,37,75,37);
  for (int i=0; i<kc; i++) {
    if (i<25) {
      text(killCount[i], 50, 50+i*14);
    } else if (i<50) {
      text(killCount[i], 100, 50+(i-25)*14);
    } else {
      text(killCount[i], 150, 50+(i-50)*14);
    }
  }
}

class Bacteria {
  float myX, myY;
  int myMessage;
  int myColor;
  boolean exist;
  Bacteria() {
    myX = (float) Math.random() * width;
    myY = (float) Math.random() * height;
    myMessage = (int) (Math.random() * message.length);
    myColor = color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
    exist = true;
  }
  public void move() {
    if (!ownSword) {
      if (xPos > myX) {
        myX += (Math.random() * 7) - 2;
      } else {
        myX += (Math.random() * 7) - 4;
      }
      if (yPos > myY) {
        myY += (Math.random() * 7) - 2;
      } else {
        myY += (Math.random() * 7) - 4;
      }
    } else {
      if (xPos > myX) {
        myX += (Math.random() * 7) - 4;
      } else {
        myX += (Math.random() * 7) - 2;
      }
      if (yPos > myY) {
        myY += (Math.random() * 7) - 4;
      } else {
        myY += (Math.random() * 7) - 2;
      }
    }
    if (myX<=20) {
      myX += Math.random() * 5;
    } else if (myX>=width-20) {
      myX -= Math.random() * 5;
    } else if (myY<=20) {
      myY += Math.random() * 5;
    } else if (myY>=height-20) {
      myY -= Math.random() * 5;
    }
  }
  public void show() {
    if (exist) {
      stroke(0);
      fill(myColor);
      ellipse((float) myX, (float) myY, 15,15);
      fill(0);
      textSize(10);
      text(message[myMessage], myX, myY);
    }
  }
}

boolean movUp, movDown, movLeft, movRight;

public void keyReleased() {
  setMove(keyCode, false);
}

public void keyPressed() {
  setMove(keyCode, true);
}

public boolean setMove(int k, boolean b) {
  switch (k) {
  case UP:
    return movUp = b;
  case DOWN:
    return movDown = b;
  case LEFT:
    return movLeft = b;
  case RIGHT:
    return movRight = b;
  default:
    return b;
  }
}

public void person() {
  if (movLeft && xPos>0) {
    xPos-=5;
  } else if (movRight && xPos<width) {
    xPos+=5;
  }
  if (movUp && yPos>0) {
    yPos-=5;
  } else if (movDown && yPos<height) {
    yPos+=5;
  }
  stroke(0);
  fill(255,0,0);
  ellipse(xPos, yPos, 20, 20);
  stroke(0);
  ellipse(xPos-4, yPos-2, 1, 1);
  ellipse(xPos+4, yPos-2, 1, 1);
  if (ownSword) {
    swordArt(xPos-8, yPos);
  }
}

public void swordArt(float x, float y) {
  fill(150);
  stroke(70);
  rect(x, y-1, 4, 13);
  line(x, y+6, x, y-3);
  fill(50);
  rect(x, y+7, 6, 2);
  rect(x, y+10, 3, 5);
}

public void sword() {
  if (!ownSword) {
    fill(150);
    if (abs(sX - xPos) < 20 && abs(sY - yPos) < 20) {
      fill(180);
      if (keyPressed && key == ' ' && !beingKilled) {
        ownSword = true;
      }
    }
    noStroke();
    rect(sX, sY, 20, 20);
    pushMatrix();
    translate(sX, sY-1);
    rotate(PI/5);
    swordArt(0,0);
    popMatrix();
  }
}
  public void settings() {  size(400,400); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "chemotaxis" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
