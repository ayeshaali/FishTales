import java.util.*; 

public class FishTank{
	private double width;
	private double length;
	private double ammoniaCount;
	private ArrayList <Tankable> myStuff; 

	FishTank (double length, double width) {
		this.length = length;
		this.width = width;
		this.ammoniaCount = 0;
		myStuff = new <Tankable> ArrayList();
		StdDraw.setCanvasSize((int)this.length, (int)this.width);
		StdDraw.setXscale(0, this.length);
		StdDraw.setYscale(0, this.width);
	}

	public void update(){
		show();
		int count = 0;
		boolean result= false; 
		boolean result1= false;
		for (int x = 0; x < myStuff.size(); x++) {
			myStuff.get(x).update();
			if (myStuff.get(x) instanceof Pellet && myStuff.get(x).isDead()) {
				count++;
			} else if (myStuff.get(x) instanceof Fish && myStuff.get(x).isDead() == false) {
				count++;
			}
			for (int y = 0; y < myStuff.size(); y++) {			
				if (myStuff.size()> 1 && y != x) {
					result = myStuff.get(x).hasCollision(myStuff.get(y));
					if (result == false) {
						result1 = myStuff.get(y).hasCollision(myStuff.get(x));
					}
				}

				if (result || result1) {
					x = 0; y=0;
					result= false;
					result1= false;
				}
			}
		}
		if (count*2 < 255){
			this.ammoniaCount = count*2;
		}
	}
	public void cleanTheTank(){
		for (int x = myStuff.size()-1; x>=0; x--) {
			if (myStuff.get(x).isDead()) {
				myStuff.remove(x);
			}
		}
	}
	public void tapTheTank(){
		for (int x = myStuff.size()-1; x>=0; x--) {
			myStuff.get(x).changeDirection();
		}
	}
	private void show(){
		StdDraw.setPenColor(0, (int) this.ammoniaCount, 255 - (int) this.ammoniaCount);
		StdDraw.filledRectangle(this.length/2, this.width/2, this.length/2, this.width/2);
	}
	public void add(Tankable t){
		this.myStuff.add(t);
	}
	
	public void remove(Tankable t){
		this.myStuff.remove(t);
	}

	public double getSize(){
		return this.myStuff.size();
	}

	public Tankable getItem(int i){
		return this.myStuff.get(i);
	}

	public int getIndex(Tankable t){
		return this.myStuff.indexOf(t);
	}

	public double getLength() {
		return this.length;
	}
	public double getWidth() {
		return this.width;
	}
	public double getAmmonia() {
		return ammoniaCount;
	}
}