import java.util.*;

public class Pawn extends Piece {
    public Pawn(Color c) {
        super(c);
    }

    public String toString() {
        if (this.color().equals(Color.WHITE)) {
            return "wp";
        } else {
            return "bp";
        }
    }

    //move one space vertically forward,
    // only toward the opponent's side of the board,
    // i.e., a black pawn can move toward row 1,
    // and a white pawn can move toward row 8.
    public List<String> moves(Board b, String loc) {
        char c = loc.charAt(0);
        int r = Character.getNumericValue(loc.charAt(1));
        if (checkLocValid(c, r)) {
            List<String> toLoc = new LinkedList<>();
            // i is int row change, j is home row
            int i, j;
            if (this.color() == Color.BLACK) {
                i = -1;
                j = 7;
            } else if (this.color() == Color.WHITE) {
                i = 1;
                j = 2;
            } else {
                throw new NoSuchElementException("pawn move exception: color type wrong");
            }
            if (checkLocValid(c, r + i)
                    && (b.getPiece(Character.toString(c) + Integer.toString(r + i)) == null)) {
                toLoc.add(Character.toString(c) + (r + i));
                if (checkLocValid(c, r + 2 * i)
                        && (b.getPiece(Character.toString(c) + Integer.toString(r + 2 * i)) == null)
                        && (r == j)) {
                    toLoc.add(Character.toString(c) + (r + 2 * i));
                }
            }
            //capture possibility
            toLoc.addAll(pawnCapture(b, c, r, this.color()));
            return toLoc;
        } else {
            throw new NoSuchElementException("pawn move exception: wrong start loc: " + loc);
        }
    }

    private List<String> pawnCapture(Board b, char c, int r, Color color) {
        List<String> toLoc = new LinkedList<>();
        HashMap<Character, List<Integer>> m = new HashMap<>();
        //diagonal
        m.put((char) (c + 1), Arrays.asList(r + 1, r - 1));
        m.put((char) (c - 1), Arrays.asList(r + 1, r - 1));
        for (Map.Entry<Character, List<Integer>> entry : m.entrySet()) {
            char col = entry.getKey();
            for (Integer row : entry.getValue()) {
                // capture if is opponent one
                if (checkLocValid(col, row)
                        && (b.getPiece(col + Integer.toString(row)) != null)
                        && (b.getPiece(col + Integer.toString(row)).color() != color)) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(col);
                    sb.append(row);
                    toLoc.add(sb.toString());
                }
            }
        }
            return toLoc;
    }
}