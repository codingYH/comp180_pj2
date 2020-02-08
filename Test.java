import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    // Run "java -ea Test" to run with assertions enabled (If you run
    // with assertions disabled, the default, then assert statements
    // will not execute!)

    public static void test1() {
	Board b = Board.theBoard();
	Piece.registerPiece(new PawnFactory());
	Piece p = Piece.createPiece("bp");
	b.addPiece(p, "a3");
	assert b.getPiece("a3") == p;
    }

    public static void testKingMove(){
    	Board.theBoard().clear();
		Piece.registerPiece(new KingFactory());
		Piece.registerPiece(new QueenFactory());
		Piece.registerPiece(new KnightFactory());
		Piece.registerPiece(new BishopFactory());
		Piece.registerPiece(new RookFactory());
		Piece.registerPiece(new PawnFactory());
		Board.theBoard().registerListener(new Logger());
		Piece bk = Piece.createPiece("bk");
		Board.theBoard().addPiece(bk, "d5");
		Board.theBoard().addPiece(Piece.createPiece("bp"), "d6");
		Board.theBoard().addPiece(Piece.createPiece("wp"), "d4");
		Board.theBoard().addPiece(Piece.createPiece("wn"), "c5");
		Board.theBoard().addPiece(Piece.createPiece("wp"), "c6");
		Board.theBoard().addPiece(Piece.createPiece("bp"), "c4");

		List<String> toList = bk.moves(Board.theBoard(), "d5" );
		List<String> cList = Arrays.asList("d4", "d6", "c5","e5","c4","c6","e4","e6");
		for(String loc : cList){
			assert toList.contains(loc);
		}
		System.out.println("Final board:");
		Board.theBoard().iterate(new BoardPrinter());
	}
    
    public static void main(String[] args) {
    	test1();
		testKingMove();
    }

}