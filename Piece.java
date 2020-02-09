import java.util.*;

abstract public class Piece {
    private Color color;
    protected Piece(Color c){
        color = c;
    }
    //check the loc is a valid position at Board
    //board's rows 1-8 and the columns a-h
    //eg. a3
    protected boolean checkLocValid(char col,int row){
        //col: a-h
        if((col < 'a')||(col > 'h')){
            return false;
        }else if((row < 1) ||( row > 8)){
            //row: 1-8
            return false;
        }else {
            return true;
        }
    }
    private static HashMap<Character, PieceFactory> pieceMakeMap = new HashMap<Character, PieceFactory>();
    //store information about the piece (probably in a map)
    // so that the Piece.createPiece method can look in that map
    // to find out how to create such a piece.
    public static void registerPiece(PieceFactory pf) {
        //TODO
        pieceMakeMap.put(pf.symbol(), pf);
    }

    public static Piece createPiece(String name) {
           Color c;
           char color = name.charAt(0);
           if (color == 'b') {
               c = Color.BLACK;
           } else if (color == 'w') {
               c = Color.WHITE;
           } else {
               throw new NoSuchElementException("createPiece exception: wrong piece color input: " + color);
           }
           char type = name.charAt(1);
           if (pieceMakeMap.get(type) == null) {
               throw new NoSuchElementException("createPiece exception: wrong piece type input: " + type);
           } else {
               return pieceMakeMap.get(type).create(c);
           }
    }

    // You should write code here and just inherit it in
    // subclasses. For this to work, you should know
    // that subclasses can access superclass fields.
    public Color color(){
        return color;
    }

    abstract public String toString();

    abstract public List<String> moves(Board b, String loc);

    protected List<String> moveByDirection(Board b, char c, int r, String direction){
        if(checkLocValid(c,r)){
            List<String> toLoc = new LinkedList<>();
            for(int i = 0; i < 8; i++){
                switch (direction){
                    case "right" : { c = (char) (c + 1); break; }
                    case "left" : { c = (char) (c - 1); break; }
                    case "up" : { r = r + 1; break; }
                    case "down" : { r = r - 1 ; break; }
                    case "upRight" : { c = (char) (c + 1); r = r + 1 ; break;}
                    case "downRight" : { c = (char) (c + 1); r = r - 1 ; break; }
                    case "upLeft" : { c = (char) (c - 1); r = r + 1 ; break; }
                    case "downLeft" : { c = (char) (c - 1); r = r - 1 ; break; }
                    default : throw new NoSuchElementException("queen move exception: wrong direction!");
                }
                // valid on Board
                if (checkLocValid(c, r)){
                    StringBuffer sb = new StringBuffer();
                    sb.append(c);
                    sb.append(r);
                    //is vacant, can move
                    if (b.getPiece(sb.toString()) == null){
                        toLoc.add(sb.toString());
                    }else if(b.getPiece(sb.toString()) != null){
                        // not vacant and is friendly piece, can't move forward
                        if(b.getPiece(sb.toString()).color().equals(this.color())){
                            break;
                        }else {
                            //is opponent piece, can capture
                            toLoc.add(sb.toString());
                            break;
                        }
                    }
                }
            }
            return toLoc;
        }else{
            throw new NoSuchElementException("move exception: wrong start loc: " + Character.toString(c) + r);
        }
    }
}