/**
 * Created by JHGWhite on 11/01/2017.
 */
public class Cell {

    private Symbol contents;

    public Cell() {
        contents = Symbol.EMPTY;
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