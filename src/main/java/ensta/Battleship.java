// package ensta;

public class Battleship extends AbstractShip {
    Battleship() {
        super("Battleship", 'B', 4, Orientation.EAST);
    }

    Battleship(Orientation orientation) {
        super("Battleship", 'B', 4, orientation);
    }  
}
