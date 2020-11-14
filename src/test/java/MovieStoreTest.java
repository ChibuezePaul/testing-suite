import model.Movie;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;


public class MovieStoreTest {
	
	private final MovieStore movieStore = new MovieStore ();
	
	private final Movie three_idiot = new Movie ( "Three Idiot" , "amitab", 1999 );
	private final Movie fast_five = new Movie ( "FAST Five", "don" , 2000 );
	private final Movie fast_and_furious = new Movie ( "Fast And Furious" , "don" , 2001 );
	private final Movie life_itself = new Movie ( "Life Itself" , "don" , 2005 );
	
	@Before
	public void setUp () throws Exception {
		movieStore.addMovie ( three_idiot );
		movieStore.addMovie ( fast_and_furious );
		movieStore.addMovie ( fast_five );
		movieStore.addMovie ( life_itself );
	}
	
	@Test
	public void returns_No_Result_When_No_Title_Partially_Match_Search () throws Exception {
		
		List< Movie > movies = movieStore.findByPartialTitle ( "abc" );
		
		assertThat ( movies.size () , is ( 0 ) );
	}
	
	@Test
	public void finds_Movies_When_Title_Partially_Match_Search_Case_Insensitive () throws Exception {
		
		List< Movie > movies = movieStore.findByPartialTitle ( "ast" );
		
		assertThat ( movies.size () , is ( 2 ) );
		assertThat ( movies , containsInAnyOrder ( fast_and_furious , fast_five ) );
	}
	
	@Test
	public void finds_Movies_When_Director_Exactly_Matches () throws Exception {
		
		List< Movie > movies = movieStore.findByDirector ( "don" );
		
		assertThat ( movies.size () , is ( 3 ) );
		assertThat ( movies , containsInAnyOrder ( fast_and_furious , fast_five, life_itself ) );
	}
	
	@Test
	public void finds_Movies_When_Release_Year_Is_Between_Two_Values() throws Exception {
		
		List< Movie > movies = movieStore.findByReleaseYear ( 1999, 2002 );
		
		assertThat ( movies.size () , is ( 2 ) );
		assertThat ( movies , containsInAnyOrder ( fast_and_furious , fast_five ) );
	}
}