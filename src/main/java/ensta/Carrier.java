// package ensta;

public class Carrier extends AbstractShip {
    Carrier() {
        super("Carrier", 'C', 5, Orientation.EAST);
    }

    Carrier(Orientation orientation) {
        super("Carrier", 'C', 5, orientation);
    } 
}
