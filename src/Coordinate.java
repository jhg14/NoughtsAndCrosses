/**
 * Created by JHGWhite on 14/01/2017.
 */
public class Coordinate {

    public final int i;
    public final int j;

    public Coordinate(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (i != that.i) return false;
        return j == that.j;

    }

    @Override
    public int hashCode() {
        int result = i;
        result = 31 * result + j;
        return result;
    }
}
