public class Speed extends Pellet{
	Speed(FishTank tank, double yPos, double xPos) {
		super(tank, yPos, xPos);
		this.fillColor = StdDraw.MAGENTA;
		this.outlineColor= StdDraw.BLUE;
	}
	public boolean isDead() {
		if (this.age> this.maxAge && this.yPos ==0) {
			return true;
		} else {
			return false;
		}
	}
}