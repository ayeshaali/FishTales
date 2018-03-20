public class Whale extends Fish{
	Whale(FishTank tank, String name) {
		super(tank, name);
		this.maxSpeed = 1;
		this.maxAge = 30000;
		this.maxSize = 100; 
		this.size = StdRandom.gaussian(this.maxSize/4, 1);
		this.xVel = StdRandom.gaussian(this.maxSpeed, 1);
		this.yVel = 0;
		this.fillColor = StdDraw.GRAY;
		this.outlineColor = StdDraw.BLACK;
	}
	boolean tryToEat(Tankable t) {
		if (this.isDead() == false) {
			if (t instanceof Food) {
				this.size += t.getSize()*0.5;
				this.tank.remove(t);
				return true;
			} else if (t instanceof Poison) {
				this.size -= t.getSize()*0.5;
				this.tank.remove(t);
				return true;
			} else if (t instanceof Goldfish) {
				this.size += t.getSize()*0.5;
				this.tank.remove(t);
				return true;
			} else if (t instanceof Piranha) {
				this.size += t.getSize()*0.5;
				this.tank.remove(t);
				return true;
			} else if (t instanceof Whale) {
				this.xVel *=-1; this.yVel *= -1;
				return false;
			} else {
				return false;
			}
		} else {
			return false;
		}
	} 

	void move() {
		if (this.isDead() == false) {
			if (this.xPos> tank.getLength() || this.xPos < 0) {
				xVel *= -(StdRandom.uniform(0.5,1.2));;
			}
			this.xPos += this.xVel;
		} else {
			this.xVel = 0;
			this.yVel = 2;
			if (this.yPos < tank.getWidth()) {
				this.yPos += this.yVel;
			}
		}
	}
}