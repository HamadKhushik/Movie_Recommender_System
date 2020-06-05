import java.util.ArrayList;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {
        // default constructor
        this("data/ratedmovies_short.csv", "data/ratings_short.csv");
    }

    public SecondRatings(String movieFile, String ratingsFile) {
        FirstRatings firstR = new FirstRatings();
        myMovies = firstR.loadMovies(movieFile);
        myRaters = firstR.loadRaters(ratingsFile);
    }

    private double getAverageById(String id, int minimalRaters) {
        int numRated = 0;
        double sumRating = 0;
        for (Rater k : myRaters) {
            if (k.getRating(id) >= 0) {
                sumRating += k.getRating(id);
                numRated += 1;
            }
        }
        if (!(numRated >= minimalRaters)) {
            return 0.0;
        }
        return sumRating / numRated;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> result = new ArrayList<Rating>();
        for (Movie k : myMovies) {
            String id = k.getID();
            double avgRating = getAverageById(id, minimalRaters);
            if (avgRating > 0.0) {
                Rating currRating = new Rating(id, avgRating);
                result.add(currRating);
            }
        }
        return result;
    }

    public String getTitle(String id) {
        for (Movie k : myMovies) {
            if (k.getID().equals(id)) {
                return k.getTitle();
            }
        }
        return "ID not found";
    }

    public String getId(String title) {
        for (Movie k : myMovies) {
            if (k.getTitle().equals(title)) {
                return k.getID();
            }
        }
        return "NO SUCH TITLE";
    }

    public int getMovieSize() {
        return myMovies.size();
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    public static void main(String[] args) {
        SecondRatings sr = new SecondRatings();
//        ArrayList<Rating> test = sr.getAverageRatings(3);
//        System.out.println(test);
//        double testId = sr.getAverageById("0068646", 3);
//        System.out.println(testId);
        System.out.println(sr.getId("The Godfather"));
    }

}

