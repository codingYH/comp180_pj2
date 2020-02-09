import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Board {
    private static Board instance = new Board();

    //col:a-h == 0-7 row:1-8 == 0-7
    private Piece[][] pieces = new Piece[8][8];
    private List<BoardListener> listeners = new LinkedList<BoardListener>();

    private Board() {
    }
    
    public static Board theBoard() {
	return instance;
    }

    // Returns piece at given loc or null if no such piece
    // exists
    public Piece getPiece(String loc) {
        int col = getCol(loc);
        int row = getRow(loc);
        return pieces[col][row];
    }

    public void addPiece(Piece p, String loc) {
        int col = getCol(loc);
        int row = getRow(loc);
        if(pieces[col][row] == null){
            pieces[col][row] = p;
        }else throw new NoSuchElementException("Board addPiece exception: position is already occupied!");
    }

    public void movePiece(String from, String to) {
        int fcol = getCol(from);
        int frow = getRow(from);
        int tcol = getCol(to);
        int trow = getRow(to);
        //check if there is a piece in from position
        if(pieces[fcol][frow] != null){
            //check if move valid
            List<String> toList = pieces[fcol][frow].moves(instance, from);
            if(toList.contains(to)){
                for(BoardListener l : listeners){
                    //broadcast onMove
                    l.onMove(from, to, pieces[fcol][frow]);
                }
                //to position already has a piece, capture
                if(pieces[tcol][trow] != null){
                    //broadcast onCapture
                    for(BoardListener l : listeners){
                        l.onCapture(pieces[fcol][frow], pieces[tcol][trow]);
                    }
                }
                //from capture to
                pieces[tcol][trow] = pieces[fcol][frow];
                //from is vacant
                pieces[fcol][frow] = null;
            }else{
                throw new NoSuchElementException("board movePiece exception: invalid move: " + from + " to " + to );
            }
        }else {
            throw new NoSuchElementException("board movePiece exception: There is no piece at: " + from);
        }
    }

    public void clear() {
        //reassign, garbage collection
        pieces = new Piece[8][8];
    }

    public void registerListener(BoardListener bl) {
        listeners.add(bl);
    }

    public void removeListener(BoardListener bl) {
        listeners.remove(bl);
    }

    public void removeAllListeners() {
        listeners = new LinkedList<BoardListener>();
    }

    public void iterate(BoardExternalIterator bi) {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                bi.visit(toStringLoc(j, i), pieces[j][i]);
            }
        }
    }

    private int getRow (String loc){
        int r = Character.getNumericValue(loc.charAt(1)) - 1;
        if((r >= 0) && (r <= 7)) {
            return Character.getNumericValue(loc.charAt(1)) - 1;
        }else {
            throw new NoSuchElementException("Board exception: row wrong" + loc);
        }
    }

    private int getCol (String loc) {
        char c = loc.charAt(0);
        if ((c >= 'a') && (c <= 'h')) {
            return c - 'a';
        } else {
            throw new NoSuchElementException("Board exception: loc col wrong" + loc);
        }
    }
    private String toStringLoc(int col, int row){
        char c = (char) (col + 'a');
        StringBuffer sb = new StringBuffer();
        sb.append(c);
        sb.append(row + 1);
        return  sb.toString();
    }
}