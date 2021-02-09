// package ensta;

public class Submarine extends AbstractShip {
    Submarine() {
        super("Submarine", 'S', 3, Orientation.EAST);
    }

    Submarine(Orientation orientation) {
        super("Submarine", 'S', 3, orientation);
    } 
}
