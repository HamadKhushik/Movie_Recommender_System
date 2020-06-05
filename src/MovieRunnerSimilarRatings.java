import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Hammad on 15/05/2020.
 * @project Project_Recommender_System
 */
public class MovieRunnerSimilarRatings {

    public void printAverageRatings() {
        FourthRatings fourthR = new FourthRatings();
        RaterDatabase.initialize("ratings_short.csv");
        System.out.println("read data for  " + RaterDatabase.size() + " Raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        ArrayList<Rating> result = fourthR.getAverageRatings(1);
        System.out.println("found " + result.size() + " movies");
        Collections.sort(result);
        for (Rating k : result) {
            double rating = k.getValue();
            String title = MovieDatabase.getTitle(k.getItem());
            System.out.println(rating + "  " + title);
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        FourthRatings fourthR = new FourthRatings();
        RaterDatabase.initialize("ratings_short.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        YearAfterFilter yaF = new YearAfterFilter(1980);
        GenreFilter gF = new GenreFilter("Romance");
        AllFilters aF = new AllFilters();
        aF.addFilter(yaF);
        aF.addFilter(gF);
        ArrayList<Rating> result = fourthR.getAverageRatingsByFilter(1, aF);
        System.out.println(result.size() + " movies matched");
        Collections.sort(result);
        for (Rating k : result) {
            double rating = k.getValue();
            String title = MovieDatabase.getTitle(k.getItem());
            String genre = MovieDatabase.getGenres(k.getItem());
            int year = MovieDatabase.getYear(k.getItem());
            System.out.println(rating + " " + year + " " + title);
            System.out.println("    " + genre);
        }
    }

    private void initialize() {
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
    }

    public void printSimilarRatings() {
        FourthRatings fr = new FourthRatings();
        initialize();
        ArrayList<Rating> result = fr.getSimilarRatings("71", 20, 5);
        System.out.println("The size of returned Array is : " + result.size());
        for (Rating k : result) {
            Movie m = MovieDatabase.getMovie(k.getItem());
            System.out.println("Movie : " + m.getTitle() + ", Rating : " + k.getValue());
        }
    }

    public void printSimilarRatingsByGenre() {
        FourthRatings fr = new FourthRatings();
        initialize();
        GenreFilter gF = new GenreFilter("Mystery");
        ArrayList<Rating> result = fr.getSimilarRatingsByFilter("964", 20, 5, gF);
        System.out.println("The size of returned Array is : " + result.size());
        for (Rating k : result) {
            Movie m = MovieDatabase.getMovie(k.getItem());
            System.out.println("Movie : " + m.getTitle() + ", Rating : " + k.getValue());
            System.out.println("Genre : " + m.getGenres());
        }
    }

    public void printSimilarRatingsByDirector() {
        FourthRatings fr = new FourthRatings();
        initialize();
        DirectorsFilter dF = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> result = fr.getSimilarRatingsByFilter("120", 10, 2, dF);
        System.out.println("The size of returned Array is : " + result.size());
        for (Rating k : result) {
            Movie m = MovieDatabase.getMovie(k.getItem());
            System.out.println("Movie : " + m.getTitle() + ", Rating : " + k.getValue());
            System.out.println("Directors : " + m.getDirector());
        }
    }

    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings fr = new FourthRatings();
        initialize();
        GenreFilter gF = new GenreFilter("Drama");
        MinutesFilter mF = new MinutesFilter(80, 160);
        AllFilters aF = new AllFilters();
        aF.addFilter(gF);
        aF.addFilter(mF);
        ArrayList<Rating> result = fr.getSimilarRatingsByFilter("168", 10, 3, aF);
        System.out.println("The size of returned Array is : " + result.size());
        for (Rating k : result) {
            Movie m = MovieDatabase.getMovie(k.getItem());
            System.out.println("Movie: " + m.getTitle() + ", Minutes: " + m.getMinutes() + ", Rating: " + k.getValue());
            System.out.println("Genres : " + m.getGenres());
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings fr = new FourthRatings();
        initialize();
        YearAfterFilter aaF = new YearAfterFilter(1975);
        MinutesFilter mF = new MinutesFilter(70, 200);
        AllFilters aF = new AllFilters();
        aF.addFilter(aaF);
        aF.addFilter(mF);
        ArrayList<Rating> result = fr.getSimilarRatingsByFilter("314", 10, 5, aF);
        System.out.println("The size of returned Array is : " + result.size());
        for (Rating k : result) {
            Movie m = MovieDatabase.getMovie(k.getItem());
            System.out.println("Movie: " + m.getTitle() + ", Year: " + m.getYear() + ", Minutes: " + m.getMinutes()
                    + ", Rating: " + k.getValue());
        }
    }

    public static void main(String[] args) {
        MovieRunnerSimilarRatings mrsr = new MovieRunnerSimilarRatings();
        mrsr.printSimilarRatingsByYearAfterAndMinutes();
    }

}
