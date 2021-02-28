package ensta;



public class Carrier extends AbstractShip {
    private static final long serialVersionUID = 1L;
    Carrier() {
        super("Carrier", 'C', 5, Orientation.EAST);
    }

    Carrier(Orientation orientation) {
        super("Carrier", 'C', 5, orientation);
    } 
}
