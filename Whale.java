public class Whale extends Fish{
	Whale(FishTank tank, String name) {
		super(tank, name);
		this.maxSpeed = 1;
		this.maxAge = 30000;
		this.maxSize = 100; 
		this.size = StdRandom.gaussian(this.maxSize/3, 1);
		this.xVel = StdRandom.gaussian(this.maxSpeed, 1);
		this.yVel = 0;
		this.fillColor = StdDraw.GRAY;
		this.outlineColor = StdDraw.BLACK;
	}
	boolean tryToEat(Tankable t) {
		if (this.isDead() == false) {
			if (t instanceof Food) {
				this.size += t.getSize()*0.05;
				this.tank.remove(t);
				return true;
			} else if (t instanceof Poison) {
				this.size -= t.getSize()*0.05;
				this.tank.remove(t);
				return true;
			} else if (t instanceof Goldfish) {
				this.size += t.getSize()*0.05;
				this.tank.remove(t);
				return true;
			} else if (t instanceof Piranha) {
				if (t.getSize() > this.size) {
					return false; 
				} else{ 
					this.size += t.getSize()*0.05;
					this.tank.remove(t);
					return true;
				}
			} else if (t instanceof Whale) {
				// tank.add(new Bubble(tank, this.xPos, this.yPos-5));
				this.changeDirection();
				t.changeDirection();
				this.xPos += this.xVel;
				this.yPos += this.yVel;
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	} 

	void move() {
		if (this.isDead() == false) {
			this.bounce();
			this.xPos += this.xVel;
		} else {
			this.deadMovement();
		}
	}
}