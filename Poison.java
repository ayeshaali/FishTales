public class Poison extends Pellet{
	Poison(FishTank tank, double yPos, double xPos) {
		super(tank, yPos, xPos);
		this.fillColor = StdDraw.RED;
		this.outlineColor= StdDraw.RED;
	}
	public boolean isDead() {
		boolean result = false;
		return result;
	}
}