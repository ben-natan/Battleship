package ensta;


public class TestGame {
    
    public static void main(String[] args) {
        Board board = new Board("Board", 10);
        Destroyer D1 = new Destroyer();
        Submarine S1 = new Submarine();
        Submarine S2 = new Submarine();
        Battleship B1 = new Battleship();
        Carrier C1 = new Carrier();
        AbstractShip ships[] = new AbstractShip[5];
        ships[0] = D1;
        ships[1] = S1;
        ships[2] = S2;
        ships[3] = B1;
        ships[4] = C1;
        BattleShipsAI ai = new BattleShipsAI(board, board);
        ai.putShips(ships);
        int compteur = 0;
        board.print();
        while (compteur < 5) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace(); 
            }
            int coords[] = new int[2];
            Hit res = ai.sendHit(coords);
            if (res.getValue() > 0) {
                compteur += 1;
            }
            System.out.println("Coordonnées du hit: " + coords[0] + ", " + coords[1]);
            System.out.println("Résultat: " + res.toString());
            board.print();
        }
    }
}
