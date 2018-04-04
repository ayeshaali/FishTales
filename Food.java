public class Food extends Pellet{
	Food(FishTank tank, double yPos, double xPos) {
		super(tank, yPos, xPos);
		this.fillColor = StdDraw.GREEN;
		this.outlineColor= StdDraw.GREEN;
	}
	public boolean isDead() {
		if (this.age> this.maxAge && this.yPos ==0) {
			return true;
		} else {
			return false;
		}
	}
}