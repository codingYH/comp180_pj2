import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
	public void testKing() throws IOException {
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
		List<String> cList = Arrays.asList("d4", "c5","e5","c6","e4","e6");
		//size equal
		Assert.assertEquals(toList.size(), cList.size());
		for(String loc : cList){
			//contain all elements
			Assert.assertEquals(true, toList.contains(loc));
		}
		Board.theBoard().movePiece("d5", "d4");
	}

	@Test
	public void testQueen() throws IOException {
		Board.theBoard().clear();
		Piece.registerPiece(new KingFactory());
		Piece.registerPiece(new QueenFactory());
		Piece.registerPiece(new KnightFactory());
		Piece.registerPiece(new BishopFactory());
		Piece.registerPiece(new RookFactory());
		Piece.registerPiece(new PawnFactory());
		Board.theBoard().registerListener(new Logger());
		Piece bk = Piece.createPiece("bq");
		Board.theBoard().addPiece(bk, "d5");
		Board.theBoard().addPiece(Piece.createPiece("bp"), "d6");
		Board.theBoard().addPiece(Piece.createPiece("wn"), "c5");
		Board.theBoard().addPiece(Piece.createPiece("wp"), "b7");
		Board.theBoard().addPiece(Piece.createPiece("bn"), "f3");

		List<String> toList = bk.moves(Board.theBoard(), "d5" );
		List<String> cList = Arrays.asList("b7", "c6","e6","f7","g8","c5","e5","f5","g5","h5","c4","b3","a2","d4","d3","d2","d1","e4");
		//size equal
		Assert.assertEquals(cList.size(), toList.size());
		for(String loc : toList){
			//contain all elements
			if(!cList.contains(loc)){
				System.out.println(loc);
			}
			Assert.assertEquals(true, cList.contains(loc));
		}
		Board.theBoard().movePiece("d5", "b7");
		Board.theBoard().movePiece("b7", "b8");
	}

	@Test
	public void testKnight() throws IOException {
		Board.theBoard().clear();
		Piece.registerPiece(new KingFactory());
		Piece.registerPiece(new QueenFactory());
		Piece.registerPiece(new KnightFactory());
		Piece.registerPiece(new BishopFactory());
		Piece.registerPiece(new RookFactory());
		Piece.registerPiece(new PawnFactory());
		Board.theBoard().registerListener(new Logger());
		Piece bk = Piece.createPiece("bn");
		Board.theBoard().addPiece(bk, "d5");
		Board.theBoard().addPiece(Piece.createPiece("wk"), "c7");
		Board.theBoard().addPiece(Piece.createPiece("bk"), "e7");
		Board.theBoard().addPiece(Piece.createPiece("br"), "d6");
		Board.theBoard().addPiece(Piece.createPiece("wk"), "c3");
		Board.theBoard().addPiece(Piece.createPiece("bk"), "e3");
		Board.theBoard().addPiece(Piece.createPiece("wr"), "d4");
		Board.theBoard().addPiece(Piece.createPiece("wr"), "e8");

		List<String> toList = bk.moves(Board.theBoard(), "d5" );
		List<String> cList = Arrays.asList("c7", "b6", "b4","f6","f4","c3");
		//size equal
		Assert.assertEquals(cList.size(), toList.size());
		for(String loc : toList){
			//contain all elements
			if(!cList.contains(loc)){
				System.out.println(loc);
			}
			Assert.assertEquals(true, cList.contains(loc));
		}
		Board.theBoard().movePiece("d5", "c7");
		Board.theBoard().movePiece("c7", "e8");
	}
	@Test
	public void testPawn() throws IOException {
		Board.theBoard().clear();
		Piece.registerPiece(new KingFactory());
		Piece.registerPiece(new QueenFactory());
		Piece.registerPiece(new KnightFactory());
		Piece.registerPiece(new BishopFactory());
		Piece.registerPiece(new RookFactory());
		Piece.registerPiece(new PawnFactory());
		Board.theBoard().registerListener(new Logger());
		Piece bp = Piece.createPiece("bp");
		Piece wp = Piece.createPiece("wp");
		Board.theBoard().addPiece(bp, "d7");
		Board.theBoard().addPiece(Piece.createPiece("bk"), "c6");
		Board.theBoard().addPiece(Piece.createPiece("wk"), "e6");
		Board.theBoard().addPiece(Piece.createPiece("bk"), "d4");
		Board.theBoard().addPiece(Piece.createPiece("bn"), "c3");
		Board.theBoard().addPiece(Piece.createPiece("wp"), "d2");
		Board.theBoard().addPiece(Piece.createPiece("wk"), "e3");
		Board.theBoard().addPiece(Piece.createPiece("wk"), "c8");

		List<String> toList = bp.moves(Board.theBoard(), "d7" );
		List<String> cList = Arrays.asList("d6","d5","e6");
		//size equal
		Assert.assertEquals(cList.size(), toList.size());
		for(String loc : toList){
			//contain all elements
			if(!cList.contains(loc)){
				System.out.println("bp: "+ loc);
			}
			Assert.assertEquals(true, cList.contains(loc));
		}

		List<String> toList2 = wp.moves(Board.theBoard(), "d2" );
		List<String> cList2 = Arrays.asList("c3","d3");
		//size equal
		Assert.assertEquals(cList2.size(), toList2.size());
		for(String loc2 : toList2){
			//contain all elements
			if(!cList2.contains(loc2)){
				System.out.println("wp: " + loc2);
			}
			Assert.assertEquals(true, cList2.contains(loc2));
		}
		Board.theBoard().movePiece("d7", "e6");
		Board.theBoard().movePiece("e6", "e4");
	}

}
