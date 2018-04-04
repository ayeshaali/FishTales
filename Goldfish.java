import java.awt.*;

public class Goldfish extends Fish{
	Goldfish(FishTank tank, String name) {
		super(tank, name);
		this.maxSpeed = 3;
		this.maxAge = 20000;
		this.maxSize = 30; 
		this.size = StdRandom.gaussian(this.maxSize/3, 1);
		this.xVel = StdRandom.gaussian(this.maxSpeed/2, 1);
		this.yVel = StdRandom.gaussian(this.maxSpeed/2, 1);
		this.fillColor = new Color (StdRandom.uniform(50)+205, StdRandom.uniform(100)+150, StdRandom.uniform(100));
		this.outlineColor = StdDraw.YELLOW;
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
			} else if (t instanceof Goldfish || t instanceof Toroidal) {
				//tank.add(new Bubble(tank, this.xPos, this.yPos-5));
				this.changeDirection();
				t.changeDirection();
				this.xPos += this.xVel;
				this.yPos += this.yVel;
				boolean result1 = this.trytoBreed();
				if (result1) {System.out.println("bred");}
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	boolean trytoBreed() {
		double chance = StdRandom.uniform(21);
		if (chance == 5) {
			System.out.println(chance);
			Goldfish kid = new Goldfish(tank, "Dory");
			tank.add(kid);
			return true;
		} else {
			return false;
		}
	}
	
	void move() {
		if (this.isDead() == false) {
			this.bounce();
			this.xPos += this.xVel;
			this.yPos += this.yVel;
		} else {
			this.deadMovement();
		}
	}
}