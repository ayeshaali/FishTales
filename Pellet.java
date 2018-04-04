import java.awt.*;

public abstract class Pellet implements Tankable{
	protected FishTank tank;
	protected double xPos; 
	protected double yPos; 
	protected double xVel; 
	protected double yVel;
	protected double size;
	protected Color fillColor;
	protected Color outlineColor;
	protected double age;
	protected double maxAge; 

	Pellet(FishTank tank, double yPos, double xPos) {
		this.tank = tank;
		this.size = StdRandom.gaussian(5, 1);
		this.xVel = 0;
		this.yVel = 1;
		this.maxAge= 1000; 
		this.age = 0;
		this.xPos = xPos;
		this.yPos = yPos;	
	}

	public void update() {
		show();
		move();
		age++;
	}
	private void show() {
		StdDraw.setPenColor(this.fillColor);
		StdDraw.filledCircle(this.xPos, this.yPos, this.size);
	}
	private void move() {
		if (this instanceof Bubble) {
			this.yPos+= this.yVel;
		} else {
			if (this.yPos <= 0) {
				this.yPos = 0;
			} else {
				this.yPos -= this.yVel;
			}
		}
	}

	public void changeDirection() {
	}

	public double getDistance(Tankable t) {
		double xDistance = (this.xPos-t.getX())*(this.xPos-t.getX());
		double yDistance = (this.yPos-t.getY())*(this.yPos-t.getY());
		double distance = Math.sqrt(xDistance+ yDistance);
		return distance;
	}

	public boolean hasCollision(Tankable t) {
		if (this instanceof Bubble) {
			if (this.getDistance(t) < ((this.size+t.getSize())/2)) {
				this.tank.remove(this);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public double getX() {
		return xPos;
	} 
	public double getY() {
		return yPos;
	}
	public double getSize() {
		return size;
	}

	abstract public boolean isDead(); 
}