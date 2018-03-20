public class Toroidal extends Goldfish{
	Toroidal(FishTank tank, String name) {
		super(tank, name);
		this.maxSpeed = 3;
		this.maxAge = 20000;
		this.maxSize = 30; 
		
		this.age = 0;
		this.size = StdRandom.gaussian(this.maxSize/4, 1);
		this.xVel = StdRandom.gaussian(this.maxSpeed, 1);
		this.yVel = StdRandom.gaussian(this.maxSpeed, 1);
		this.xPos = StdRandom.uniform((int)tank.getLength());
		this.yPos = StdRandom.uniform((int)tank.getWidth());
		this.fillColor = StdDraw.ORANGE;
		this.outlineColor = StdDraw.YELLOW;
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
			this.xVel = 0;
			this.yVel = Math.abs(this.yVel);
			if (this.yPos < tank.getWidth()) {
				this.yPos += this.yVel;
			}
		}
	}
}