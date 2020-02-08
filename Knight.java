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
            HashMap<Character, Integer> m = new HashMap<>();
            m.put((char) (c + 2), r + 1);
            m.put((char) (c + 2), r - 1);
            m.put((char) (c - 2), r + 1);
            m.put((char) (c - 2), r - 1);
            m.put((char) (c + 1), r + 2);
            m.put((char) (c + 1), r - 2);
            m.put((char) (c - 1), r + 2);
            m.put((char) (c - 1), r - 2);
            for(Map.Entry<Character, Integer> entry : m.entrySet()){
                if(checkLocValid(entry.getKey(), entry.getValue())){
                    StringBuffer sb = new StringBuffer();
                    sb.append(entry.getKey());
                    sb.append(entry.getValue());
                    toLoc.add(sb.toString());
                }
            }
            return toLoc;
        }else{
            throw new NoSuchElementException("Knight move exception: wrong start loc: " + loc);
        }
    }
}