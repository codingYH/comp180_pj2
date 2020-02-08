import java.util.*;

public class Rook extends Piece {
    public Rook(Color c) {
        super(c);
    }
    public String toString() {
        if(this.color().equals(Color.WHITE)) {
            return "wr";
        }else {
            return "br";
        }
    }

    public List<String> moves(Board b, String loc) {
        char c = loc.charAt(0);
        int r = Character.getNumericValue(loc.charAt(1));
        List<String> to = new LinkedList<>();
        to.addAll(moveByDirection(b, c, r, "right"));
        to.addAll(moveByDirection(b, c, r, "left"));
        to.addAll(moveByDirection(b, c, r, "up"));
        to.addAll(moveByDirection(b, c, r, "down"));
        return to;
    }
}