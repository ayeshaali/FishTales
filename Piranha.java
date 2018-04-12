import java.awt.*;

public class Piranha extends Fish{
	Piranha(FishTank tank, String name) {
		super(tank, name);
		this.maxSpeed = StdRandom.gaussian(2, .2*2);
		this.maxAge =  StdRandom.gaussian(10000, 10000*0.2);
		this.maxSize = StdRandom.gaussian(50, .2*50); 
		this.size = StdRandom.uniform(13,19);
		this.xVel = StdRandom.uniform((float)this.maxSpeed-1, (float)this.maxSpeed);
		this.yVel = StdRandom.uniform((float)this.maxSpeed-1, (float)this.maxSpeed);
		this.fillColor = new Color (StdRandom.uniform(50)+205, 0, 0);
		this.outlineColor = StdDraw.GREEN;
	}
	
	boolean tryToEat(Tankable t) {
		if (this.isDead() == false) {
			if (t instanceof Food) {
				this.size += t.getSize()*0.05;
				return true;
			} else if (t instanceof Poison) {
				this.size -= t.getSize()*0.05;
				return true;
			} else if (t instanceof Speed) {
				this.xVel *= 1.2;
				this.yVel *= 1.2;
				if (this.xVel > this.maxSpeed) {
					this.xVel = this.maxSpeed;
				} else if (this.yVel > this.maxSpeed) {
					this.yVel = this.maxSpeed;
				}
				return true;
			} else if (t.isDead()) {
				this.size += t.getSize()*0.05;
				return true;
			} else if (t instanceof Goldfish) {
				this.size += t.getSize()*0.05;
				return true;
			} else if (t instanceof Piranha) {
				if (t.getSize() < this.size) {
					this.size += t.getSize()*0.05;
					return true;
				} 
			} else if (t instanceof Whale) {
				if (t.getSize() < this.size) {
					this.size += t.getSize()*0.05;
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
			// System.out.println(xVel);
			xPos+= xVel;
			yPos+=yVel;
			this.followGF();
		} else {
			this.deadMovement();
		}
	}

	void followGF() {
		int count = 0;
		double distance = Double.POSITIVE_INFINITY;
		for (int i = 0; i < this.tank.getSize(); i++) {
			if (this.tank.getItem(i) instanceof Goldfish && (this.tank.getItem(i) instanceof Toroidal == false)) {
				if (this.getDistance(tank.getItem(i)) < distance) {
					distance = this.getDistance(tank.getItem(i));
					count = i;
				}
			}
		}
		if (count == 0 && ((this.tank.getItem(0) instanceof Goldfish) == false)) {
			this.xVel = this.xVel;
			this.yVel = this.yVel;
		} else {
			double run = tank.getItem(count).getX() - this.xPos;
			double rise = tank.getItem(count).getY() - this.yPos;
			this.xVel = run/(distance/2);
			this.yVel = rise/(distance/2);
		}
	}
}