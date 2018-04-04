public class Poison extends Pellet{
	Poison(FishTank tank, double yPos, double xPos) {
		super(tank, yPos, xPos);
		this.fillColor = StdDraw.RED;
		this.outlineColor= StdDraw.RED;
	}
	public boolean isDead() {
		if (this.age> this.maxAge && this.yPos ==0) {
			return true;
		} else {
			return false;
		}
	}
}