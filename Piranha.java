public class Piranha extends Fish{
	Piranha(FishTank tank, String name) {
		super(tank, name);
		this.maxSpeed = 2;
		this.maxAge = 10000;
		this.maxSize = 50; 
		this.size = StdRandom.gaussian(this.maxSize/4, 1);
		this.xVel = StdRandom.gaussian(this.maxSpeed, 1);
		this.yVel = StdRandom.gaussian(this.maxSpeed, 1);
		this.fillColor = StdDraw.RED;
		this.outlineColor = StdDraw.GREEN;
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
			} else if (t.isDead()) {
				this.size += t.getSize()*0.5;
				this.tank.remove(t);
				return true;
			} else if (t instanceof Goldfish) {
				this.size += t.getSize()*0.5;
				this.tank.remove(t);
				return true;
			} else if (t instanceof Piranha) {
				if (t.getSize() < this.size) {
					this.size += t.getSize()*0.5;
					this.tank.remove(t);
					return true;
				} 
			} else if (t instanceof Whale) {
				if (t.getSize() < this.size) {
					this.size += t.getSize()*0.5;
					this.tank.remove(t);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}

		return false;
	}

	void move() {
		if (this.isDead() == false) {
			if (this.xPos> tank.getLength() || this.xPos < 0) {
				xVel *= -(StdRandom.uniform(0.5,1.3));
			} else if (this.yPos> tank.getWidth() || this.yPos < 0) {
				yVel *= -(StdRandom.uniform(0.5,1.3));
			}
			this.followGF();
		} else {
			this.xVel = 0;
			this.yVel = Math.abs(this.yVel);
			if (this.yPos < tank.getWidth()) {
				this.yPos += this.yVel;
			}
		}
	}

	void followGF() {
		int count = 0;
		double distance = Double.POSITIVE_INFINITY;
		for (int i = 0; i < this.tank.getSize(); i++) {
			if (this.tank.getItem(i) instanceof Goldfish) {
				if (this.getDistance(tank.getItem(i)) < distance) {
					distance = this.getDistance(tank.getItem(i));
					count = i;
				}
			}
		}
		if (count == 0 && (this.tank.getItem(0) instanceof Goldfish) == false) {
			this.xPos += this.xVel;
			this.yPos += this.yVel;
		} else {
			double run = tank.getItem(count).getX() - this.xPos;
			double rise = tank.getItem(count).getY() - this.yPos;
			this.xPos += run/(Math.abs(run)/2);
			this.yPos += rise/(Math.abs(run)/2);
		}
	}
}