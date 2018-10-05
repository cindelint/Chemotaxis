//declare bacteria variables here
Bacteria[] bob;
int xPos = 200, yPos = 200;
String[] message = new String[] {"debra", "bob", "clyde", "blinky", "bonnie", "bert" ,"bobert", "richard", "jenjamin", "betty", "betsy", "bennifer"};
boolean ownSword = false;
float sX, sY;

void setup() {
  size(400,400);

  bob = new Bacteria[75];
  for (int i=0; i<bob.length; i++) {
    bob[i] = new Bacteria();
  }

  textAlign(CENTER, CENTER);
  rectMode(CENTER);
  sX = (float) (Math.random() * width);
  sY = (float) (Math.random() * height);
}

void draw() {
  background(197, 212, 221);
  for (int i=0; i<bob.length; i++) {
    bob[i].show();
    bob[i].move();
    if (abs(bob[i].myX - xPos) < 3 && abs(bob[i].myY - yPos) < 3 && bob[i].exist) {
      //background(204, 104, 104);
      fill(0);
      textSize(100);
      if (!ownSword) {
        text("rip", 200, 200);
      } else {
        text("k i l l", 200, 200);
        if (key == ' ') {
          bob[i].exist = false;
        }
      }
    }
  }
  person();
  sword();
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
  void move() {
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

  }
  void show() {
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


void person() {
  if (keyPressed) {
    if (keyCode == LEFT && xPos>0) {
      xPos-=5;
    } else if (keyCode == RIGHT && xPos<width) {
      xPos+=5;
    }
    if (keyCode == UP && yPos>0) {
      yPos-=5;
    } else if (keyCode == DOWN && yPos<height) {
      yPos+=5;
    }
  }
  stroke(0);
  fill(255,0,0);
  ellipse(xPos, yPos, 20, 20);
  stroke(0);
  ellipse(xPos-4, yPos-2, 1, 1);
  ellipse(xPos+4, yPos-2, 1, 1);
  if (ownSword) {
    fill(150);
    stroke(70);
    rect(xPos-8, yPos-1, 4, 13);
    line(xPos-8, yPos+6, xPos-8, yPos-3);
    fill(50);
    rect(xPos-8, yPos+7, 6, 2);
    rect(xPos-8, yPos+10, 3, 5);
  }
}

void sword() {
  if (!ownSword) {
    fill(0);
    if (abs(sX - xPos) < 20 && abs(sY - yPos) < 20) {
      fill(100);
      if (keyPressed && key == ' ') {
        ownSword = true;
      }
    }
    noStroke();
    rect(sX, sY, 20, 20);
  }
}
