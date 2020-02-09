import java.lang.reflect.Array;
import java.util.*;

public class Knight extends Piece {
    public Knight(Color c) {
        super(c);
        }

    public String toString() {
        if(this.color().equals(Color.WHITE)) {
            return "wn";
        }else {
            return "bn";
        }
    }

    public List<String> moves(Board b, String loc) {
        char c = loc.charAt(0);
        int r = Character.getNumericValue(loc.charAt(1));
        if(checkLocValid(c,r)) {
            List<String> toLoc = new LinkedList<>();
            HashMap<Character, List<Integer>> m = new HashMap<>();
            m.put((char) (c + 2),  Arrays.asList(r - 1, r + 1));
            m.put((char) (c - 2),  Arrays.asList(r - 1, r + 1));
            m.put((char) (c + 1),  Arrays.asList(r - 2, r + 2));
            m.put((char) (c - 1),  Arrays.asList(r - 2, r + 2));
            for(Map.Entry<Character, List<Integer>> entry : m.entrySet()) {
                char col = entry.getKey();
                for (Integer l : entry.getValue()) {
                    int row = l;
                    if (checkLocValid(col, row)) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(col);
                        sb.append(row);
                        if (b.getPiece(sb.toString()) == null) {
                            toLoc.add(sb.toString());
                        } else {
                            //not vacant, friendly piece, can't move
                            if (b.getPiece(sb.toString()).color().equals(this.color())) {
                                continue;
                            } else {
                                //not vacant, opponent piece, capture
                                toLoc.add(sb.toString());
                            }
                        }
                    }
                }
            }
            return toLoc;
        }else{
            throw new NoSuchElementException("Knight move exception: wrong start loc: " + loc);
        }
    }
}