public class Piranha extends Fish{
	Piranha(FishTank tank, String name) {
		super(tank, name);
		this.maxSpeed = 2;
		this.maxAge = 10000;
		this.maxSize = 50; 
		this.size = StdRandom.gaussian(this.maxSize/3, 1);
		this.xVel = StdRandom.gaussian(this.maxSpeed/2, 1);
		this.yVel = StdRandom.gaussian(this.maxSpeed/2, 1);
		this.fillColor = StdDraw.RED;
		this.outlineColor = StdDraw.GREEN;
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
			} else if (t.isDead()) {
				this.size += t.getSize()*0.05;
				this.tank.remove(t);
				return true;
			} else if (t instanceof Goldfish) {
				this.size += t.getSize()*0.05;
				this.tank.remove(t);
				return true;
			} else if (t instanceof Piranha) {
				if (t.getSize() < this.size) {
					this.size += t.getSize()*0.05;
					this.tank.remove(t);
					return true;
				} 
			} else if (t instanceof Whale) {
				if (t.getSize() < this.size) {
					this.size += t.getSize()*0.05;
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
			this.bounce();
			this.followGF();
			xPos+= xVel;
			yPos+=yVel;
		} else {
			this.deadMovement();
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
			
		} else {
			double run = tank.getItem(count).getX() - this.xPos;
			double rise = tank.getItem(count).getY() - this.yPos;
			this.xVel = run/(distance/2);
			this.yVel = rise/(distance/2);
		}
	}
}