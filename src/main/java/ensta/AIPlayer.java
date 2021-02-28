package ensta;
import java.util.List;

public class AIPlayer extends Player {
    private static final long serialVersionUID = 1L;
    /* **
     * Attribut
     */
    private BattleShipsAI ai;

    /* **
     * Constructeur
     */
    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }

    public void putShips() {
        ai.putShips(this.ships);
    }

    public Hit sendHit(int[] coords) {
        return ai.sendHit(coords);
    }
}
