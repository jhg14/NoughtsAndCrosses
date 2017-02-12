/**
 * Created by JHGWhite on 11/01/2017.
 */
public class Cell {

    private Symbol contents;

    public Cell() {
        contents = Symbol.EMPTY;
    }

    public Cell(Symbol contents) {
        this.contents = contents;
    }

    // Set the contents of a cell, only if empty
    public void setContents(Symbol contents) {
        if (this.contents == Symbol.EMPTY)
            this.contents = contents;
    }

    public Symbol getContents() {
        return contents;
    }

    public Cell copy() {
        Cell cell = new Cell();
        cell.setContents(contents);
        return cell;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        return contents == cell.contents;

    }

    @Override
    public int hashCode() {
        return contents.hashCode();
    }
}

enum Symbol {

    X, O, EMPTY;

    public Symbol getOpponent() {
        if (this == Symbol.X) {
            return Symbol.O;
        } else {
            return Symbol.X;
        }
    }

}