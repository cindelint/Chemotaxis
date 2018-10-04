//declare bacteria variables here
Bacteria[] bob;
int xPos = 250, yPos = 250;
String[] message = new String[] {"your apush hw", "college", "be a well rounded person", "college", "avoid being obsessed with college", "more college", "feeling inadequate", "parents", "some homework", "that one project due tomorrow that you havent started"};


void setup() {
  size(500,500);

  bob = new Bacteria[75];
  for (int i=0; i<bob.length; i++) {
    bob[i] = new Bacteria();
  }

  textAlign(CENTER, CENTER);
}

void draw() {
  background(200);
  for (int i=0; i<bob.length; i++) {
    bob[i].show();
    bob[i].move();
    if (abs(bob[i].myX - xPos) < 3 && abs(bob[i].myY - yPos) < 3) {
      background(255,0,0);
      fill(0);
      textSize(50);
      text("rip", 250, 250);
    }
  }

  person();
}

class Bacteria {
  //lots of java!
  float myX, myY;
  int myMessage;
  Bacteria() {
    myX = (float) Math.random() * width;
    myY = (float) Math.random() * height;
    myMessage = (int) (Math.random() * message.length);
  }
  void move() {
    if (xPos > myX) {
      myX += (float) (Math.random() * 7) - 2;
    } else {
      myX += (float) (Math.random() * 7) - 4;
    }
    if (yPos > myY) {
      myY += (float) (Math.random() * 7) - 2;
    } else {
      myY += (float) (Math.random() * 7) - 4;
    }

  }
  void show() {
    fill(100);
    ellipse(myX, myY, 15,15);
    fill(0);
    textSize(9);
    text(message[myMessage], myX, myY);
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
  fill(255,0,0);
  ellipse(xPos, yPos, 20, 20);
  stroke(0);
  ellipse(xPos-4, yPos-2, 1, 1);
  ellipse(xPos+4, yPos-2, 1, 1);
}
