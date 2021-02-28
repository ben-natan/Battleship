package ensta;



public class Destroyer extends AbstractShip { 
    private static final long serialVersionUID = 1L;
    Destroyer() {
        super("Destroyer", 'D', 2, Orientation.EAST);
    }

    Destroyer(Orientation orientation) {
        super("Destroyer", 'D', 2, orientation);
    } 
}
