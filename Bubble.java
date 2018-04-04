public class Bubble extends Pellet{
	Bubble(FishTank tank, double yPos, double xPos) {
		super(tank, yPos, xPos);
		this.fillColor = StdDraw.GRAY;
		this.outlineColor= StdDraw.BLACK;
	}
	public boolean isDead() {
		if (this.yPos >= tank.getWidth()) {
			return true;
		} else {
			return false;
		}
	}
}