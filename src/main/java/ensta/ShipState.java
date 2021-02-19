// package ensta;

public class ShipState {
    private AbstractShip ship;
    private boolean struck;

    public ShipState(AbstractShip ship, boolean struck) {
        this.ship = ship;
        this.struck = struck;
    }

    public void addStrike() {
        if (!this.struck) {
            this.struck = true;
            this.ship.addStrike();
        }
    }

    public boolean isStruck() {
        return this.struck;
    }

    public String toString() {
        if (this.isStruck()) {
            //en rouge:
            return this.ship.getLabel().toString();
        } else {
            return this.ship.getLabel().toString();
        }
    }

    public boolean isSunk() {
        return this.ship.isSunk();
    }

    public AbstractShip getShip() {
        return this.ship;
    }

}
