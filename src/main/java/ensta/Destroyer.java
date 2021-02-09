package ensta;

public class Destroyer extends AbstractShip{ 
    Destroyer() {
        super("Destroyer", 'D', 2, Orientation.EAST);
    }

    Destroyer(Orientation orientation) {
        super("Destroyer", 'D', 2, orientation);
    } 
}
