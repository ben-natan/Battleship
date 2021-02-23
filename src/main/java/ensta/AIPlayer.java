// package ensta;
import java.io.Serializable;
import java.util.List;

public class AIPlayer extends Player {
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
