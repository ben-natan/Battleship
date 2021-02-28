package ensta;

public class Submarine extends AbstractShip {
    private static final long serialVersionUID = 1L;
    Submarine() {
        super("Submarine", 'S', 3, Orientation.EAST);
    }

    Submarine(Orientation orientation) {
        super("Submarine", 'S', 3, orientation);
    } 
}
