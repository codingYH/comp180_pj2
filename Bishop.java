import java.util.*;

public class Bishop extends Piece {
    public Bishop(Color c) {
        super(c);
    }

    public String toString() {
        if(this.color().equals(Color.WHITE)) {
            return "wb";
        }else {
            return "bb";
        }
    }

    public List<String> moves(Board b, String loc) {
        char c = loc.charAt(0);
        int r = Character.getNumericValue(loc.charAt(1));
        List<String> to = new LinkedList<>();
        to.addAll(moveByDirection(b, c, r, "upRight"));
        to.addAll(moveByDirection(b, c, r, "downRight"));
        to.addAll(moveByDirection(b, c, r, "upLeft"));
        to.addAll(moveByDirection(b, c, r, "downLeft"));
        return to;
    }

}