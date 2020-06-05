import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.HashMap;

public class FirstRatings {

    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource data = new FileResource(filename);
        CSVParser parser = data.getCSVParser();

        for (CSVRecord record : parser) {
            Movie movieDesc = new Movie(record.get("id"), record.get("title"), record.get("year"),
                    record.get("genre"), record.get("director"), record.get("country"),
                    record.get("poster"), Integer.parseInt(record.get("minutes")));
            movies.add(movieDesc);
        }
        return movies;
    }

    public void testLoadMovies() {
        ArrayList<Movie> test = loadMovies("data/ratedmoviesfull.csv");
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        int comedy = 0;
        int length = 0;
        int maxValue = 0;
        String maxKey = null;

        for (Movie k : test) {
            if (k.getGenres().contains("Comedy")) {
                comedy += 1;
            }
            if (k.getMinutes() > 150) {
                length += 1;
            }

            // building map for the director with most movies
            // and keeping track of director with most movies
            String[] moviesByDirector = k.getDirector().split(",");
            for (String s : moviesByDirector) {

                if (map.containsKey(s)) {
                    ArrayList<String> value = map.get(s);
                    value.add(k.getTitle());
                    map.put(s, value);
                    if (maxValue < value.size()) {
                        maxValue = value.size();
                        maxKey = s;
                    }
                }

                if (!map.containsKey(s)) {
                    ArrayList<String> value = new ArrayList<String>();
                    value.add(k.getTitle());
                    map.put(s, value);
                    if (maxValue == 0) {
                        maxValue = value.size();
                        maxKey = s;
                    }
                }
            }
        }

        System.out.println("The number of comedy movies is : " + comedy);
        System.out.println("The number of movies more than 150 mins are : " + length);
        System.out.println("The number of movies are : " + test.size());
        System.out.println("The director with most movies is : " + maxKey);
        System.out.println("The maximum number of movies by one director are : " + maxValue);
        System.out.println("The movies by " + maxKey + " are :" + map.get(maxKey));
    }

    public ArrayList<Rater> loadRaters(String filename) {
        FileResource data = new FileResource(filename);
        CSVParser parser = data.getCSVParser();
        HashMap<String, Rater> mapR = buildMap(parser);
        ArrayList<Rater> ratingsData = new ArrayList<Rater>();

        for (String k : mapR.keySet()) {
            ratingsData.add(mapR.get(k));
        }
        return ratingsData;
    }

    // Helper function to build map for Rater objects to be used with loadRaters()
    private HashMap<String, Rater> buildMap(CSVParser parse) {
        HashMap<String, Rater> map = new HashMap<String, Rater>();

        for (CSVRecord record : parse) {
            if (!map.containsKey(record.get("rater_id"))) {
                Rater rated = new PlainRater(record.get("rater_id"));
                rated.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                map.put(record.get("rater_id"), rated);

            } else {
                Rater rated = map.get(record.get("rater_id"));
                rated.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                map.put(record.get("rater_id"), rated);
            }
        }
        return map;
    }

    public void testLoadRaters(String raterId, String movieId) {
        ArrayList<Rater> test = loadRaters("data/ratings.csv");
        ArrayList<Rater> maxRatings = new ArrayList<Rater>();
        int max = 0;  // max number of ratings by any one rater
        int movieRating = 0;  // max ratings for one particular movie
        ArrayList<String> movies = new ArrayList<String>(); // to determine how many different movies have been
        // rated by all these raters
        System.out.println("Total number of rater is : " + test.size());

        for (Rater k : test) {
            if (k.getID().equals(raterId)) {
                System.out.println("Rater Id : " + k.getID() + " has = " + k.numRatings() + " ratings");
            }

            // to find the maximum number of ratings by any rater.
            // Determine how many raters have this maximum number of ratings and who those raters are
            if (k.numRatings() > max) {
                maxRatings.clear();
                max = k.numRatings();
                maxRatings.add(k);

            } else if (k.numRatings() == max) {
                maxRatings.add(k);
            }

            //  to find the number of ratings a particular movie has.
            if (k.hasRating(movieId)) {
                movieRating += 1;
            }

            ArrayList<String> list = k.getItemsRated();
            for (String j : list) {
                if (!movies.contains(j)) {
                    movies.add(j);
                }
            }
        }
        /*for (Rater k : test) {
            System.out.println("Rater's id : " + k.getID() + " & number of Ratings is : " + k.numRatings());
            ArrayList<String> ratedItems = k.getItemsRated();
            for (String j : ratedItems) {
                System.out.println("rating for movie id : " + j + " is : " + k.getRating(j));
            }
        }*/
        System.out.println("Max ratings by any rater are : " + max);
        System.out.println("The number of raters with max ratings are : " + maxRatings.size());
        System.out.println("Raters with most movies are :");
        for (Rater k : maxRatings) {
            System.out.println(k.getID());
        }
        System.out.println("Total number of ratings for movie : " + movieId + " are : " + movieRating);
        System.out.println("The number of rated movies is : " + movies.size());
    }

    public static void main(String[] args) {
        FirstRatings fr = new FirstRatings();
        //fr.testLoadMovies();
        double start = System.nanoTime();
        fr.testLoadRaters("193", "1798709");
        double end = System.nanoTime();
        System.out.println("Time taken = " + (end - start) / 1000000000);

    }
}

