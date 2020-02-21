import java.io.*;
import java.nio.file.FileVisitResult;
import java.util.NoSuchElementException;

public class Chess {
	//read layout file, add pieces
	public static void layOut(String l){
		//first clear Board
		Board.theBoard().clear();
		try {
			File file = new File(l);
			BufferedReader r = new BufferedReader(new FileReader(file));
			String rs;
			while((rs = r.readLine()) != null){
				if(rs.isEmpty()||rs.charAt(0)=='#'){
					continue;
				}else{
					String[] sa = rs.trim().split("=");
					String loc = sa[0];
					//may not place two pieces in the same location
						Piece p = Piece.createPiece(sa[1]);
						Board.theBoard().addPiece(p, loc);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new NoSuchElementException("File not find");
		} catch (IOException e) {
			e.printStackTrace();
			throw new NoSuchElementException("File not find");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//read moves file, move pieces
	public static void playMoves(String m){
		try {
			File file = new File(m);
			BufferedReader r = new BufferedReader(new FileReader(file));
			String rs;
			while((rs = r.readLine()) != null){
				if(rs.isEmpty()||rs.charAt(0)=='#'){
					continue;
				}else{
					String[] sa = rs.trim().split("-");
					String from = sa[0];
					String to = sa[1];
					Board.theBoard().movePiece(from, to);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new NoSuchElementException("File not find");
		} catch (IOException e) {
			e.printStackTrace();
			throw new NoSuchElementException("File not find");
		}
	}
    public static void main(String[] args){
	if (args.length != 2) {
	    System.out.println("Usage: java Chess layout moves");
	}
	Piece.registerPiece(new KingFactory());
	Piece.registerPiece(new QueenFactory());
	Piece.registerPiece(new KnightFactory());
	Piece.registerPiece(new BishopFactory());
	Piece.registerPiece(new RookFactory());
	Piece.registerPiece(new PawnFactory());
	Board.theBoard().registerListener(new Logger());
	// args[0] is the layout file name
	// args[1] is the moves file name
	// Put your code to read the layout file and moves files
	// here.
	layOut(args[0]);
	System.out.println("Initial board:");
	Board.theBoard().iterate(new BoardPrinter());
	playMoves(args[1]);

	// Leave the following code at the end of the simulation:
	System.out.println("Final board:");
	Board.theBoard().iterate(new BoardPrinter());
    }
}