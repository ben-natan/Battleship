package ensta;



public class Battleship extends AbstractShip {
    private static final long serialVersionUID = 1L;
    Battleship() {
        super("Battleship", 'B', 4, Orientation.EAST);
    }

    Battleship(Orientation orientation) {
        super("Battleship", 'B', 4, orientation);
    }  
}
