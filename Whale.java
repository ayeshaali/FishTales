import java.awt.*;

public class Whale extends Fish{
	public static int colorpicker;
	Whale(FishTank tank, String name) {
		super(tank, name);
		this.maxSpeed = StdRandom.gaussian(1, .2*1);
		this.maxAge =  StdRandom.gaussian(30000, 30000*0.2);
		this.maxSize = StdRandom.gaussian(100, .2*100); 
		this.size = StdRandom.uniform(22,27);
		this.xVel = StdRandom.uniform(0.2, (float)this.maxSpeed);
		this.yVel = StdRandom.uniform(0.2, (float)this.maxSpeed);
		colorpicker = StdRandom.uniform(100);
		this.fillColor = new Color (colorpicker+155, colorpicker+155, colorpicker+155);
		this.outlineColor = StdDraw.BLACK;
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
			} else if (t instanceof Goldfish) {
				this.size += t.getSize()*0.05;
				return true;
			} else if (t instanceof Piranha) {
				if (t.getSize() > this.size) {
					return false; 
				} else{ 
					this.size += t.getSize()*0.05;
					return true;
				}
			} else if (t instanceof Whale) {
				// tank.add(new Bubble(tank, this.xPos, this.yPos-5));
				this.changeDirection();
				t.changeDirection();
				this.xPos += this.xVel;
				this.yPos += this.yVel;
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
			this.bounce();
			this.xPos += this.xVel;
		} else {
			this.deadMovement();
		}
	}
}