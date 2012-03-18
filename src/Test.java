
public class Test {

	public static void main(String[] args) {
		Labyrinth lab = new Labyrinth(5,5);
		lab.printMap();
		System.out.println("-----");
		lab.getRandomCell().setValue("X");
		lab.printMap();
		
	}
}
