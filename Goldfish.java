import java.awt.*;

public class Goldfish extends Fish{
	Goldfish(FishTank tank, String name) {
		super(tank, name);
		this.maxSpeed = StdRandom.gaussian(3, .2*3);
		this.maxAge =  StdRandom.gaussian(20000, 20000*0.2);
		this.maxSize = StdRandom.gaussian(30, .2*30); 
		this.size = StdRandom.uniform(10,16);
		this.xVel = StdRandom.uniform((float)this.maxSpeed-2, (float)this.maxSpeed);
		this.yVel = StdRandom.uniform((float)this.maxSpeed-2, (float)this.maxSpeed);
		this.fillColor = new Color (StdRandom.uniform(50)+205, StdRandom.uniform(100)+150, StdRandom.uniform(100));
		this.outlineColor = StdDraw.YELLOW;
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
			} else if (t instanceof Goldfish || t instanceof Toroidal) {
				//tank.add(new Bubble(tank, this.xPos, this.yPos-5));
				this.changeDirection();
				t.changeDirection();
				this.xPos += this.xVel;
				this.yPos += this.yVel;
				boolean result1 = this.trytoBreed();
				if (result1) {System.out.println("bred");}
				return false;
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