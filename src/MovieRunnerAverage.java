import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {

    public void printAverageRatings() {
        SecondRatings secondR = new SecondRatings();
        System.out.println("Total Number of Movies : " + secondR.getMovieSize());
        System.out.println("Total number of Raters : " + secondR.getRaterSize());
        ArrayList<Rating> result = secondR.getAverageRatings(3);
        Collections.sort(result);
        for (Rating k : result) {
            double rating = k.getValue();
            String title = secondR.getTitle(k.getItem());
            System.out.println(rating + "  " + title);
        }
    }

    public void getAverageRatingOneMovie() {
        SecondRatings sr = new SecondRatings();
        String id = sr.getId("The Godfather");
        ArrayList<Rating> result = sr.getAverageRatings(0);
        for (Rating k : result) {
            if (k.getItem().equals(id)) {
                System.out.println("The Godfather : id = " + id + ", rating = " + k.getValue());
            }
        }
    }

    public static void main(String[] args) {
        MovieRunnerAverage mva = new MovieRunnerAverage();
        mva.getAverageRatingOneMovie();
    }
}

