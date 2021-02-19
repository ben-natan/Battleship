// package ensta;


public class AbstractShip {
    private String name;
    private Character label;
    private int size;
    private Orientation orientation;
    private int strikeCount;

    public AbstractShip(String name, Character label, int size, Orientation orientation) {
        this.name = name;
        this.label = label;
        this.size = size;
        this.orientation = orientation;
        this.strikeCount = 0;
    }

    //Setters:
    public void setName(String name) {
        this.name = name;
    }

    public void setLabel(Character label) {
        this.label = label;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    //Getters:
    public String getName() {
        return this.name;
    }

    public Character getLabel() {
        return this.label;
    }

    public int getSize() {
        return this.size;
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    public int getStrikeCount() {
        return this.strikeCount;
    }

    // Autres
    public void addStrike() {
        this.strikeCount +=1;
    }

    public boolean isSunk() {
        return (this.strikeCount == this.size);
    }


}
