import java.awt.*;

public class Goldfish extends Fish{
	Goldfish(FishTank tank, String name) {
		super(tank, name);
		this.maxSpeed = 3;
		this.maxAge = 20000;
		this.maxSize = 30; 
		this.size = StdRandom.gaussian(this.maxSize/4, 1);
		this.xVel = StdRandom.gaussian(this.maxSpeed, 1);
		this.yVel = StdRandom.gaussian(this.maxSpeed, 1);
		this.fillColor = new Color (StdRandom.uniform(50)+205, StdRandom.uniform(100)+150, StdRandom.uniform(100));
		this.outlineColor = StdDraw.YELLOW;
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
				this.trytoBreed();
				this.xVel *=-1; this.yVel *= -1;
				return false;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	boolean trytoBreed() {
		double chance = StdRandom.uniform(0.0, 1.0);
		System.out.println(chance);
		if (chance < 0.1 && chance >0.05) {
			tank.add(new Goldfish(tank, "Dory"));
			return true;
		} else {
			return false;
		}
	}
	
	void move() {
		if (this.isDead() == false) {
			if (this.xPos> tank.getLength() || this.xPos < 0) {
				xVel *= -(StdRandom.uniform(0.5,1.25));
			} else if (this.yPos> tank.getWidth() || this.yPos < 0) {
				yVel *= -(StdRandom.uniform(0.5,1.25));;
			}
			this.xPos += this.xVel;
			this.yPos += this.yVel;
		} else {
			this.xVel = 0;
			this.yVel = Math.abs(this.yVel);
			if (this.yPos < tank.getWidth()) {
				this.yPos += this.yVel;
			}
		}
	}
}