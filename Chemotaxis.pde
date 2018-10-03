//declare bacteria variables here
Bacteria[] bob;
void setup() {
  size(300,300);

  bob = new Bacteria[50];
  for (int i=0; i<bob.length; i++) {
    bob[i] = new Bacteria();
  }
  //initialize bacteria variables here
}

void draw() {
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
  void move() {
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
  void show() {
    fill(100);
    ellipse(myX, myY, 20, 20);
  }
}
