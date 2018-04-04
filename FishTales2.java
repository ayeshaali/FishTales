import java.awt.Color;

public class FishTales2 {
	public static void main (String args[]) {
		boolean x = true;
		FishTank myTank = new FishTank(1000, 500);
		StdDraw.enableDoubleBuffering();
		while (x = true) {
			if (StdDraw.isKeyPressed(71)) {
				while(StdDraw.isKeyPressed(71)) {
					
				}
				System.out.println("New Goldfish");
				myTank.add(new Goldfish(myTank, "Dory"));
			} else if (StdDraw.isKeyPressed(84)) {
				while(StdDraw.isKeyPressed(84)) {
					
				}
				System.out.println("New Toroidal");
				myTank.add(new Toroidal(myTank, "Dory"));
			} else if (StdDraw.isKeyPressed(80)) {
				while(StdDraw.isKeyPressed(80)) {
					
				}
				System.out.println("New Piranha");
				myTank.add(new Piranha(myTank, "Dory"));
			} else if (StdDraw.isKeyPressed(87)) {
				while(StdDraw.isKeyPressed(87)) {
					
				}
				System.out.println("New Whale");
				myTank.add(new Whale(myTank, "Dory"));
			} else if (StdDraw.isKeyPressed(70)) {
				System.out.println("Food");
				while(StdDraw.isKeyPressed(70)) {
					
				}
				for (int i = 0; i < 6; i ++) {
					myTank.add(new Food(myTank, myTank.getWidth(), StdRandom.uniform((int)myTank.getLength())));
					if (i%5 == 0) {
						myTank.add(new Poison(myTank, myTank.getWidth(), StdRandom.uniform((int)myTank.getLength())));
					}
				}
			} else if (StdDraw.isKeyPressed(67)) {
				System.out.println("Clean the Tank");
				myTank.cleanTheTank();
			} else if (StdDraw.mousePressed()) {
				myTank.add(new Bubble(myTank, StdDraw.mouseY(), StdDraw.mouseX()));
				myTank.tapTheTank();
				System.out.println("Tap the Tank");
			}
			
			myTank.update();
			StdDraw.show();
			StdDraw.pause(1000/30);
		}
	}
}