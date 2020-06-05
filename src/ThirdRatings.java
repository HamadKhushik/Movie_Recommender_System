import java.util.ArrayList;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        // default constructor
        this("data/ratings_short.csv");
    }

    public ThirdRatings(String ratingsFile) {
        FirstRatings firstR = new FirstRatings();
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String k : movies) {
            double avgRating = getAverageById(k, minimalRaters);
            if (avgRating > 0.0) {
                Rating currRating = new Rating(k, avgRating);
                result.add(currRating);
            }
        }
        return result;
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> result = new ArrayList<Rating>();
        for (String k : movies) {
            double avgRating = getAverageById(k, minimalRaters);
            if (avgRating > 0.0) {
                Rating currRating = new Rating(k, avgRating);
                result.add(currRating);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SecondRatings sr = new SecondRatings();
        ArrayList<Rating> test = sr.getAverageRatings(3);
        System.out.println(test);
//        double testId = sr.getAverageById("0068646", 3);
//        System.out.println(testId);
        //System.out.println(sr.getId("The Godfather"));
    }

}

