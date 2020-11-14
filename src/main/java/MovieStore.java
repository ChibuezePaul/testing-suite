import model.Movie;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieStore {
	private List<Movie> movies = new LinkedList<> ();
	
	public void addMovie ( Movie movie ) {
		movies.add ( movie );
	}
	
	public List< Movie > findByPartialTitle ( String partialTitle ) {
		return findMovie ( movie -> movie.title ().toLowerCase ().contains ( partialTitle.toLowerCase () ) );
	}
	
	public List< Movie > findByDirector ( String director ) {
		return findMovie ( movie -> movie.director ().equals ( director) );
	}
	
	public List< Movie > findByReleaseYear ( int from , int to ) {
		return findMovie ( movie -> movie.releaseYear () > from &&  movie.releaseYear () < to );
	}
	
	private List< Movie > findMovie ( Predicate predicate ) {
		return movies.stream ()
			  .filter ( predicate :: matches )
			  .collect ( Collectors.toList () );
	}
	
	private interface Predicate {
		boolean matches(Movie movie);
	}
}
