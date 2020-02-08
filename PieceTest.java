import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class PieceTest {

	// Run "java -ea Test" to run with assertions enabled (If you run
	// with assertions disabled, the default, then assert statements
	// will not execute!)

	@Test
	public void testAddPiece() {
		Board b = Board.theBoard();
		Piece.registerPiece(new PawnFactory());
		Piece p = Piece.createPiece("bp");
		b.addPiece(p, "a3");
		Assert.assertEquals(p, b.getPiece("a3"));
	}

	@Test
	public void testLayOut() throws IOException {
		Piece.registerPiece(new KingFactory());
		Piece.registerPiece(new QueenFactory());
		Piece.registerPiece(new KnightFactory());
		Piece.registerPiece(new BishopFactory());
		Piece.registerPiece(new RookFactory());
		Piece.registerPiece(new PawnFactory());
		Board.theBoard().registerListener(new Logger());
		Chess.layOut("layout1");

	}
}
