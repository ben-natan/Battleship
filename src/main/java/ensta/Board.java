package ensta;
import java.io.Serializable;

public class Board implements IBoard, Serializable {
    private String name;
    private ShipState[][] ships;
    private Boolean[][] strikes;

    private static final long serialVersionUID = 1235L;

    public Board(String name) {
        this.name = name;
        this.ships = new ShipState[10][10]; // On les laisse à NULL
        this.strikes = new Boolean[10][10];
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                this.strikes[i][j] = null;
            }
        }
        
    }

    public Board(String name, int size) {
        this.name = name;
        this.ships = new ShipState[size][size]; // On les laisse à NULL
        this.strikes = new Boolean[size][size];
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                this.strikes[i][j] = null; 
            }
        }
    }

    // Getters:
    public String getName() {
        return this.name;
    }

    public ShipState[][] getShips() {
        return this.ships;
    }

    public Boolean[][] getStrikes() {
        return this.strikes;
    }

    public int getSize() {
        return this.ships.length;
    }

    //Setters:
    public void setName(String name) {
        this.name = name;
    }

    public void setShips(ShipState[][] ships) {
        if (ships.length == this.ships.length && ships[0].length == this.ships[0].length) {
            this.ships = ships;
        } else {
            System.out.println("Error: wrong size");
        }
    }

    public void setStrikes(Boolean[][] strikes) {
        if (strikes.length == this.strikes.length && strikes[0].length == this.strikes[0].length) {
            this.strikes = strikes;
        } else {
            System.out.println("Error: wrong size");
        }
    }

    //Print:
    public void print() {
        System.out.println("");
        System.out.println("Navires: " + "                                 " + "Strikes: ");
        String firstLineShips="  ";
        String firstLineStrikes = "    ";
        String spaceBetween = "       ";
        for (int i=0;i<this.getSize();i++) {
            char ascii = (char)(i+65);
            firstLineShips += (i == this.getSize() - 1) ? Character.toString(ascii) + " " : Character.toString(ascii) + "  ";
            firstLineStrikes += Character.toString(ascii) + "  ";
        }
        System.out.println(firstLineShips + spaceBetween + firstLineStrikes);
        spaceBetween += "   ";
        for (int i=0;i<this.getSize();i++) {
            System.out.print(String.valueOf(i+1));
            for (int j=0; j<this.ships[0].length;j++) {
                if (this.ships[j][i] == null) {
                    System.out.print(" . ");
                } else {

                    System.out.print(" " + this.ships[j][i].getShip().getLabel() + " ");
                }
            }

            System.out.print(spaceBetween);
                
            for (int j=0; j<this.ships[0].length;j++) {
                if (this.strikes[j][i] == null) {
                    System.out.print(" . ");
                } else if (this.strikes[j][i]) {
                    // en rouge
                    System.out.print(ColorUtil.colorize(" X ", ColorUtil.Color.RED));
                } else {
                    System.out.print(ColorUtil.colorize(" X ", ColorUtil.Color.WHITE));
                }
            }

            System.out.println("");
        }
        System.out.println("");
    }

    /**
     * @param ship the ship to put
     * @param x 
     * @param y
     */
    public void putShip(AbstractShip ship, int x, int y) {
        
        int boardSize = this.getSize();
        int shipSize = ship.getSize();
        if (x>boardSize || y>boardSize || x < 1 || y < 1) {
            throw new IllegalArgumentException("Indexes out of board");
        }
        switch (ship.getOrientation()) {
            case EAST : 
                // Vérifier que ça rentre
                if (boardSize - x + 1>= shipSize) {
                    // Vérifier que ça chevauche rien;
                    for (int i=0; i<shipSize; i++) {
                        // this.hasShip(x+i,y);
                        if (this.ships[x-1 + i][y-1] != null) {
                            throw new IllegalArgumentException("Ships overlap");
                        }
                    }
                    // Placer le ship
                    for (int i=0; i<shipSize;i++) {
                        // this.ships[y-1][x-1 + i] = label;
                        this.ships[x-1 + i][y-1] = new ShipState(ship, false);
                    }
                } else {
                    throw new IllegalArgumentException("Not enough space");
                }
                break;
            case WEST : 
                // Vérifier que ça rentre
                if (x  >= shipSize) {
                    // Vérifier que ça chevauche rien;
                    for (int i=0; i<shipSize; i++) {
                        if (this.ships[x-1 - i][y-1] != null) {
                            throw new IllegalArgumentException("Ships overlap");
                        }
                    }
                    // Placer le ship
                    for (int i=0; i<shipSize;i++) {
                        // this.ships[y-1][x-1 - i] = label;
                        this.ships[x-1 - i][y-1] = new ShipState(ship, false);
                    }
                } else {
                    throw new IllegalArgumentException("Not enough space");
                }
                break;
            case NORTH :
                // Vérifier que ça rentre
                if (y  >= shipSize) {
                    // Vérifier que ça chevauche rien;
                    for (int i=0; i<shipSize; i++) {
                        if (this.ships[x-1][y-1 - i] != null) {
                            throw new IllegalArgumentException("Ships overlap");
                        }
                    }
                    // Placer le ship
                    for (int i=0; i<shipSize;i++) {
                        // this.ships[y-1 - i][x-1] = label;
                        this.ships[x-1][y-1 - i] = new ShipState(ship, false);
                    }
                } else {
                    throw new IllegalArgumentException("Not enough space");
                }
                break;
            case SOUTH :
                // Vérifier que ça rentre
                if (boardSize - y + 1 >= shipSize) {
                    // Vérifier que ça chevauche rien;
                    for (int i=0; i<shipSize; i++) {
                        if (this.ships[x-1][y-1 + i] != null) {
                            throw new IllegalArgumentException("Ships overlap");
                        }
                    }
                    // Placer le ship
                    for (int i=0; i<shipSize;i++) {
                        // this.ships[y-1 + i][x-1] = label;
                        this.ships[x-1][y-1 + i] = new ShipState(ship, false);
                    }
                } else {
                    throw new IllegalArgumentException("Not enough space");
                } break;
            default: return; // Error
        }
    }

    /**
     * @param x
     * @param y
     * @return if board(x,y) has ship
     */
    public boolean hasShip(int x, int y) {
        int boardSize = this.getSize();
        if (x > boardSize || y > boardSize || x < 1 || y < 1) {
            throw new IllegalArgumentException("Indexes out of board");
        } else {
            return (this.ships[x-1][y-1] != null);
        }
    }

    /**
     * @param hit true or false
     * @param x
     * @param y
     */
    public void setHit(boolean hit, int x, int y) {
        int boardSize = this.getSize();
        if (x > boardSize || y > boardSize || x < 1 || y < 1) {
            throw new IllegalArgumentException("Indexes out of board");
        } else {
            this.strikes[x-1][y-1] = hit;
        }
    }

    /**
     * @param x
     * @param y
     * @return strikes(x,y)
     */
    public Boolean getHit(int x, int y) {
        int boardSize = this.getSize();
        if (x > boardSize || y > boardSize || x < 1 || y < 1) {
            throw new IllegalArgumentException("Indexes out of board");
        } else {
            return this.strikes[x-1][y-1];
        }
    }

    /**
     * @param x
     * @param y
     * @return hit result 
     */
    public Hit sendHit(int x, int y) {
        if (this.hasShip(x, y)) {
            // this.setHit(true, x, y);
            this.ships[x-1][y-1].addStrike();
            if (this.ships[x-1][y-1].getShip().isSunk()) {
                Hit hit = Hit.fromInt(this.ships[x-1][y-1].getShip().getSize());
                return hit;
            } else {
                Hit hit = Hit.fromInt(-2);
                return hit;
            }
        } else {
            // this.setHit(false, x, y);
            Hit hit = Hit.fromInt(-1);
            return hit;
        }
    }  
}
