package ensta;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {

    /* ***
     * Constante
     */
    public static final File SAVE_FILE = new File("savegame.dat");

    /* ***
     * Attributs
     */
    private Player player1;
    private Player player2;
    private Scanner sin;
    static String flushString;

    /* ***
     * Constructeurs
     */
    public Game() {}

    public Game init() {
        flushString = "\r\n".repeat(50);
        if (!loadSave()) {
            sin = new Scanner(System.in);
            System.out.println("Nom du joueur 1: ");
            String player1Name = sin.nextLine();
            System.out.println("Nom du joueur 2: ");
            String player2Name = sin.nextLine();


            Board b1, b2;
            b1 = new Board(player1Name, 10);
            b2 = new Board(player2Name, 10);


            List<AbstractShip> player1_ships = createDefaultShips();
            this.player1 = new Player(b1, b2, player1_ships);

            List<AbstractShip> player2_ships = createDefaultShips();
            this.player2 = new Player(b2, b1, player2_ships);

            player1.putShips();
            sleep(3000);
            clearScreen();

            player2.putShips();
            sleep(3000);
            clearScreen();
        }
        return this;
    }

    /* ***
     * Méthodes
     */
    public void run() {
        int[] coords = new int[2];
        Board b1 = player1.board;
        Board b2 = player2.board;
        Hit hit;

        // main loop
        boolean done;
        do {
            System.out.print("Au tour de: ");
            System.out.println(ColorUtil.colorize(b1.getName(), ColorUtil.Color.YELLOW));

            b1.print();

            hit = player1.sendHit(coords);

            boolean strike = hit != Hit.MISS; 
            b1.setHit(strike, coords[0], coords[1]);

            done = updateScore();
            b1.print();
            System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

            save();

            if (!strike) {
                sleep(3000);
                clearScreen();
            }
            
            

            if (!done && !strike) {
                do {

                    System.out.print("Au tour de: ");
                    System.out.println(ColorUtil.colorize(b2.getName(), ColorUtil.Color.PURPLE));
                    b2.print();

                    hit = player2.sendHit(coords);

                    strike = hit != Hit.MISS;
                    b2.setHit(strike, coords[0], coords[1]);

                    
                    done = updateScore();
                    b2.print();

                    System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));

                    if (!done) {
                        save();
                    }

                    if (!strike) {
                        sleep(3000);
                        clearScreen();
                    }
                } while(strike && !done);
            }

        } while (!done);

        SAVE_FILE.delete();
        System.out.println(String.format("joueur %d gagne", player1.lose ? 2 : 1));
        sin.close();
    }


    private void save() {
        try {
            
            if (!SAVE_FILE.exists()) {
                SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
            }

            FileOutputStream fos = new FileOutputStream(SAVE_FILE);
            ObjectOutputStream oos =  new ObjectOutputStream(fos) ;
                    
            oos.writeObject(this.player1);
            oos.writeObject(this.player2);

            oos.close(); 
            fos.close(); 
              
            System.out.println("Partie sauvegardée!"); 
            System.out.println("");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean loadSave() {
        if (SAVE_FILE.exists()) {
            try {
                FileInputStream fis = new FileInputStream(SAVE_FILE); 
                ObjectInputStream ois = new ObjectInputStream(fis); 
                
                this.player1 = (Player)ois.readObject(); 
                this.player2 = (Player)ois.readObject();

                ois.close(); 
                fis.close(); 
            
                System.out.println("Partie existante chargée!"); 
                System.out.println(""); 
                return true;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean updateScore() {
        for (Player player : new Player[]{player1, player2}) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips()) {
                if (ship.isSunk()) {
                    destroyed++;
                }
            }

            player.destroyedCount = destroyed;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
            }
        }
        return false;
    }

    private String makeHitMessage(boolean incoming, int[] coords, Hit hit) {
        String msg;
        ColorUtil.Color color = ColorUtil.Color.RESET;
        switch (hit) {
            case MISS:
                msg = hit.toString();
                color = ColorUtil.Color.CYAN;
                break;
            case STRIKE:
                msg = hit.toString();
                color = ColorUtil.Color.RED;
                break;
            default:
                msg = hit.toString() + " coulé";
                color = ColorUtil.Color.RED;
        }
        msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>",
                ((char) ('A' + coords[0] - 1)),
                (coords[1]), msg);
        return ColorUtil.colorize(msg, color);
    }

    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new Battleship(), new Carrier()});
    }

    /**
     * clears screen to hide the board
     */
    public static void clearScreen() {  
        System.out.println(flushString); 
    }

    /**
     * sleeps for n milliseconds
     * @param n
     */
    public static void sleep(int n) {
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace(); 
        }
    }

    public static void main(String args[]) {
        new Game().init().run();
    }
}
