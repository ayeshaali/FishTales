import java.awt.*;

public abstract class Fish implements Tankable{
	protected FishTank tank;
	protected String name;
	protected double xPos; 
	protected double yPos; 
	protected double xVel; 
	protected double yVel;
	protected double maxSpeed;
	protected double size;
	protected double maxSize;
	protected Color fillColor;
	protected Color outlineColor;
	protected double age;
	protected double maxAge; 

	Fish(FishTank tank, String name) {
		this.tank = tank;
		this.name = name;
		this.age = 0;
		this.xPos = StdRandom.uniform((int)tank.getLength());
		this.yPos = StdRandom.uniform((int)tank.getWidth());
	}

	public void update() {
		move();
		show();
		age += tank.getAmmonia()/3;
	}
	private void show() {
		StdDraw.setPenColor(this.fillColor);
		StdDraw.filledCircle(this.xPos, this.yPos, Math.abs(this.size));
		if(xVel>0 && yVel>=0){
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledCircle(this.xPos+this.size/4,this.yPos+this.size/4,this.size/5);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledCircle(this.xPos+this.size/4,this.yPos+this.size/4,this.size/7);
		} else if(xVel<0 && yVel>=0){
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledCircle(this.xPos-this.size/4,this.yPos+this.size/4,this.size/5);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledCircle(this.xPos-this.size/4,this.yPos+this.size/4,this.size/7);
		} else if(xVel<0 && yVel<=0){
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledCircle(this.xPos-this.size/4,this.yPos-this.size/4,this.size/5);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledCircle(this.xPos-this.size/4,this.yPos-this.size/4,this.size/7);
		} else if(xVel>0 && yVel<=0){
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledCircle(this.xPos+this.size/4,this.yPos-this.size/4,this.size/5);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledCircle(this.xPos+this.size/4,this.yPos-this.size/4,this.size/7);
		} else if(xVel == 0 && yVel>=0){
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledCircle(this.xPos,this.yPos-this.size/4,this.size/5);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledCircle(this.xPos,this.yPos-this.size/4,this.size/7);
		}
	}

	public double getDistance(Tankable t) {
		double xDistance = (this.xPos-t.getX())*(this.xPos-t.getX());
		double yDistance = (this.yPos-t.getY())*(this.yPos-t.getY());
		double distance = Math.sqrt(xDistance+ yDistance);
		return distance;
	}

	public boolean hasCollision(Tankable t) {
		if (this.getDistance(t) < ((this.size+t.getSize())/2) ) {
			boolean result = this.tryToEat(t); 
			return result;
		} else {
			return false;
		}
	}

	public boolean isDead() {
		if (this.age > this.maxAge || this.size > this.maxSize) {
			return true;
		} else {
			return false;
		}
	}

	public void changeDirection() {
		this.xVel *=-1;
		this.yVel *=-1;
	}

	public void bounce() {
		if (xPos+size> tank.getLength() || xPos-size < 0) {
			xVel *= -(StdRandom.uniform(0.7,1.25));
		} else if (yPos+size> tank.getWidth() || yPos-size < 0) {
			yVel *= -(StdRandom.uniform(0.7,1.25));;
		}
	}

	public void deadMovement() {
		this.xVel = 0;
		this.yVel = Math.abs(this.yVel);
		if (this instanceof Whale) {
			yVel = 1;
		}

		if (this.yPos < tank.getWidth()) {
			this.yPos += this.yVel;
		}
	}

	public double getX() {
		return this.xPos;
	} 
	public double getY() {
		return this.yPos;
	}
	public double getSize() {
		return this.size;
	}

	public FishTank getTank() {
		return this.tank;
	}

	abstract void move();
	abstract boolean tryToEat(Tankable t);
}