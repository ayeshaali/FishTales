public interface Tankable {
	public void update();
	public void changeDirection();
	public boolean hasCollision(Tankable t);
	public boolean isDead();
	public double getX(); 
	public double getY();
	public double getSize();
}