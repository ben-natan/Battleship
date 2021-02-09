// package ensta;

public class Board {
    private String name;
    private Character[][] ships;
    private Boolean[][] strikes;

    public Board(String name) {
        this.name = name;
        this.ships = new Character[10][10];
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                this.ships[i][j] = 'W'; // water
            }
        }
        this.strikes = new Boolean[10][10];
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                this.strikes[i][j] = false;
            }
        }
    }

    public Board(String name, int size) {
        this.name = name;
        this.ships = new Character[size][size];
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                this.ships[i][j] = 'W'; // water
            }
        }
        this.strikes = new Boolean[size][size];
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                this.strikes[i][j] = false; 
            }
        }
    }

    // Getters:
    public String getName() {
        return this.name;
    }

    public Character[][] getShips() {
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

    public void setShips(Character[][] ships) {
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
        System.out.println("Navires: ");
        String firstLine="   ";
        for (int i=0;i<this.getSize();i++) {
            char ascii = (char)(i+65);
            firstLine += Character.toString(ascii) + "  ";
        }
        System.out.println(firstLine);
        for (int i=0;i<this.getSize();i++) {
            String str="";
            str += String.valueOf(i+1) + " ";
            for (int j=0; j<this.ships[0].length;j++) {
                if (this.ships[i][j] == 'W') {
                    str+= " . ";
                } else {
                    str+= " X ";
                }
            }
            System.out.println(str);
        }

        System.out.println("\nFrappes: ");
        firstLine="   ";
        for (int i=0;i<this.getSize();i++) {
            char ascii = (char)(i+65);
            firstLine += Character.toString(ascii) + "  ";
        }
        System.out.println(firstLine);
        for (int i=0;i<this.getSize();i++) {
            String str="";
            str += String.valueOf(i+1) + " ";
            for (int j=0; j<this.getSize();j++) {
                if (this.strikes[i][j]) {
                    str+= " X  ";
                } else {
                    str+= " . ";
                }
            }
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        Board a = new Board("bord", 10);
        a.print();
    }
}
