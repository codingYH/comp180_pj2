import java.util.*;

public class King extends Piece {
    public King(Color c) {
        super(c);
    }

    public String toString() {
        return this.color()+"k";
    }

    //board's rows 1-8 and the columns a-h
    //eg. a3
    public List<String> moves(Board b, String loc) {
        char c = loc.charAt(0);
        int r = Character.getNumericValue(loc.charAt(1));
        //check loc is valid
        if(checkLocValid(c,r)){
            List<String> toLoc = new LinkedList<>();
            for(int i = -1; i <= 1; i++){
                for(int j = -1; j <= 1; j++ ){
                    // exclude now loc position
                    if(!(i == 0) && (j == 0)){
                        StringBuffer sb = new StringBuffer();
                        // move to legally position on Board
                        if(checkLocValid((char) (c + i), r + j)) {
                            sb.append((char) (c + i));
                            sb.append(r + j);
                            toLoc.add(sb.toString());
                        }
                    }
                }
            }
            return toLoc;
        }else{
            throw new NoSuchElementException("King move exception: wrong start loc: " + loc);
        }
    }
}