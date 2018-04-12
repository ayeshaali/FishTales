public class Toroidal extends Goldfish{
	Toroidal(FishTank tank, String name) {
		super(tank, name);
	}
	
	void move() {
		if (this.isDead() == false) {
			if (this.xPos> tank.getLength()) {
				xPos = 0;
			} else if (this.yPos> tank.getWidth() || this.yPos < 0) {
				yPos = 0;
			}
			this.xPos += this.xVel;
			this.yPos += this.yVel;
		} else {
			this.deadMovement();
		}
	}
}