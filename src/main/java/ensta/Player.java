package ensta;
import java.io.Serializable;
import java.util.List;

public class Player implements Serializable {
    /* **
     * Attributs
     */
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    protected boolean lose;

    private static final long serialVersionUID = 1234L;

    /* **
     * Constructeur
     */
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    /* **
     * Méthodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coodrinates.
     */
    public void putShips() {
        boolean done = false;
        int i = 0;

        do {
            AbstractShip s = ships[i];
            String msg = String.format("placer %d : %s(%d)", i + 1, s.getName(), s.getSize());
            System.out.println(msg);
            InputHelper.ShipInput res = InputHelper.readShipInput();
            res.x += 1;  // hotfix
            res.y += 1;

            // Set ship orientation
            switch(res.orientation) {
                case "e":
                    s.setOrientation(Orientation.EAST);
                    break;
                case "w":
                    s.setOrientation(Orientation.WEST);
                    break;
                case "n":
                    s.setOrientation(Orientation.NORTH);
                    break;
                case "s":
                    s.setOrientation(Orientation.SOUTH);
                    break;
            }

            // Put ship at given position
            try {
                this.board.putShip(s, res.x, res.y);
                // Show board
                this.board.print();
                ++i;
            } catch(Exception e) {
                System.out.println("Impossible! Essayez autre chose: ");
            }
            
            done = i == 5;

        } while (!done);
    }

    public Hit sendHit(int[] coords) {
        boolean done = false;
        Hit hit = null;

        do {
            System.out.println("où frapper?");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            int x = hitInput.x + 1;
            int y = hitInput.y + 1;
            try {
                hit = this.opponentBoard.sendHit(x, y);
                done = true;
            } catch(Exception e) {
                System.out.println("C'est en dehors de la grille! Essayez autre chose: ");
            }
            coords[0] = x;
            coords[1] = y;
        } while (!done);

        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}
