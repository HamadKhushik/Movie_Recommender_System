import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {

    public void printAverageRatings() {
        ThirdRatings thirdR = new ThirdRatings();
        System.out.println("read data for  " + thirdR.getRaterSize() + " movies");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        ArrayList<Rating> result = thirdR.getAverageRatings(1);
        System.out.println("found " + result.size() + " movies");
        Collections.sort(result);
        for (Rating k : result) {
            double rating = k.getValue();
            String title = MovieDatabase.getTitle(k.getItem());
            System.out.println(rating + "  " + title);
        }
    }

    public void printAverageRatingsByYear() {
        ThirdRatings thirdR = new ThirdRatings();
        System.out.println("read data for " + thirdR.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        YearAfterFilter yaF = new YearAfterFilter(2000);
        ArrayList<Rating> result = thirdR.getAverageRatingsByFilter(1, yaF);
        System.out.println("found " + result.size() + " movies");
        Collections.sort(result);
        for (Rating k : result) {
            double rating = k.getValue();
            String title = MovieDatabase.getTitle(k.getItem());
            int year = MovieDatabase.getYear(k.getItem());
            System.out.println(rating + " " + year + " " + title);
        }
    }

    public void printAverageRatingsByGenre() {
        ThirdRatings thirdR = new ThirdRatings();
        System.out.println("read data for " + thirdR.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        GenreFilter gF = new GenreFilter("Crime");
        ArrayList<Rating> result = thirdR.getAverageRatingsByFilter(1, gF);
        System.out.println("found " + result.size() + " movies");
        Collections.sort(result);
        for (Rating k : result) {
            double rating = k.getValue();
            String title = MovieDatabase.getTitle(k.getItem());
            String genre = MovieDatabase.getGenres(k.getItem());
            System.out.println(rating + " " + title);
            System.out.println("    " + genre);
        }
    }

    public void printAverageRatingsByMinutes() {
        ThirdRatings thirdR = new ThirdRatings();
        System.out.println("read data for " + thirdR.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        MinutesFilter mF = new MinutesFilter(110, 170);
        ArrayList<Rating> result = thirdR.getAverageRatingsByFilter(1, mF);
        System.out.println("found " + result.size() + " movies");
        Collections.sort(result);
        for (Rating k : result) {
            double rating = k.getValue();
            String title = MovieDatabase.getTitle(k.getItem());
            int minutes = MovieDatabase.getMinutes(k.getItem());
            System.out.println(rating + "  Time " + minutes + " " + title);
        }
    }

    public void printAverageRatingsByDirectors() {
        ThirdRatings thirdR = new ThirdRatings();
        System.out.println("read data for " + thirdR.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        DirectorsFilter dF = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        ArrayList<Rating> result = thirdR.getAverageRatingsByFilter(1, dF);
        System.out.println("found " + result.size() + " movies");
        Collections.sort(result);
        for (Rating k : result) {
            double rating = k.getValue();
            String title = MovieDatabase.getTitle(k.getItem());
            String directors = MovieDatabase.getDirector(k.getItem());
            System.out.println(rating + " " + title);
            System.out.println("    " + directors);
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings thirdR = new ThirdRatings();
        System.out.println("read data for " + thirdR.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        YearAfterFilter yaF = new YearAfterFilter(1980);
        GenreFilter gF = new GenreFilter("Romance");
        AllFilters aF = new AllFilters();
        aF.addFilter(yaF);
        aF.addFilter(gF);
        ArrayList<Rating> result = thirdR.getAverageRatingsByFilter(1, aF);
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

    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings thirdR = new ThirdRatings();
        System.out.println("read data for " + thirdR.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        DirectorsFilter dF = new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola");
        MinutesFilter mF = new MinutesFilter(30, 170);
        AllFilters aF = new AllFilters();
        aF.addFilter(dF);
        aF.addFilter(mF);
        ArrayList<Rating> result = thirdR.getAverageRatingsByFilter(1, aF);
        System.out.println(result.size() + " movies matched");
        Collections.sort(result);
        for (Rating k : result) {
            double rating = k.getValue();
            String title = MovieDatabase.getTitle(k.getItem());
            String directors = MovieDatabase.getDirector(k.getItem());
            int time = MovieDatabase.getMinutes(k.getItem());
            System.out.println(rating + " Time: " + time + " " + title);
            System.out.println("    " + directors);
        }
    }

    public static void main(String[] args) {
        MovieRunnerWithFilters mrwf = new MovieRunnerWithFilters();
        mrwf.printAverageRatings();
    }
}
