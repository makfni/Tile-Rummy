package core;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TileTest {
	
	//Tile Rank Testing
	@Test
	public void tileRankFiveEqualTileRankFive_True() {
		Tile t1 = new Tile(5, 'R');
		Tile t2 = new Tile(5, 'R');
		assertTrue(t1.getRank() == t2.getRank());
	}
	@Test
	public void tileRankFiveEqualTileRankFour_False() {
		Tile t1 = new Tile(5, 'R');
		Tile t2 = new Tile(4, 'R');
		assertFalse(t1.getRank() == t2.getRank());
	}
	@Test
	public void tileRankSevenExactlyOneUpTileRankSix_True() {
		Tile t1 = new Tile(7, 'R');
		Tile t2 = new Tile(6, 'R');
		assertTrue( (t1.getRank() - t2.getRank()) == 1 );
	}
	@Test
	public void tileRankSevenExactlyOneUpTileRankFive_False() {
		Tile t1 = new Tile(7, 'R');
		Tile t2 = new Tile(5, 'R');
		assertFalse( (t1.getRank() - t2.getRank()) == 1 );
	}
	@Test
	public void tileRankEightExactlyOneDownTileRankNine_True() {
		Tile t1 = new Tile(8, 'R');
		Tile t2 = new Tile(9, 'R');
		assertTrue( (t1.getRank() - t2.getRank()) == -1 );
	}
	@Test
	public void tileRankEightExactlyOneDownTileRankTen_False() {
		Tile t1 = new Tile(8, 'R');
		Tile t2 = new Tile(10, 'R');
		assertFalse( (t1.getRank() - t2.getRank()) == -1 );
	}
	@Test
	public void tileRankTwoLessThanTileRankTen_True() {
		Tile t1 = new Tile(2, 'R');
		Tile t2 = new Tile(10, 'R');
		assertTrue(t1.getRank() < t2.getRank());
	}
	@Test
	public void tileRankTwoLessThanTileRankOne_False() {
		Tile t1 = new Tile(2, 'R');
		Tile t2 = new Tile(1, 'R');
		assertFalse(t1.getRank() < t2.getRank());
	}
	@Test
	public void tileRankTwelveGreaterThanTileRankFive_True() {
		Tile t1 = new Tile(12, 'R');
		Tile t2 = new Tile(5, 'R');
		assertTrue(t1.getRank() > t2.getRank());
	}
	@Test
	public void tileRankTwelveGreaterThanTileRankThirteen_False() {
		Tile t1 = new Tile(12, 'R');
		Tile t2 = new Tile(13, 'R');
		assertFalse(t1.getRank() > t2.getRank());
	}
	
	//Tile Color Testing
	@Test
	public void tileColorRedSameAsTileColorRed_True() {
		Tile t1 = new Tile(12, 'R');
		Tile t2 = new Tile(13, 'R');
		assertTrue(t1.getColor() == t2.getColor());
	}
	@Test
	public void tileColorRedSameAsTileColorBlue_False() {
		Tile t1 = new Tile(12, 'R');
		Tile t2 = new Tile(13, 'B');
		assertFalse(t1.getColor() == t2.getColor());
	}
	
	//Print Tile
	@Test
	public void printTile() {
		Tile t1 = new Tile(12, 'R');
		t1.displayTile();
	}
}