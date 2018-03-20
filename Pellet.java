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
		this.size = 5;
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
			this.yPos -= this.yVel;
		}
	}

	public void changeDirection() {

	}

	public boolean hasCollision(Tankable t) {
		return false;
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