import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Hammad on 15/05/2020.
 * @project Project_Recommender_System
 */
public class FourthRatings {

    private double getAverageById(String id, int minimalRaters) {
        int numRated = 0;
        double sumRating = 0;
        for (Rater k : RaterDatabase.getRaters()) {
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

    private double dotProduct(Rater me, Rater r) {
        ArrayList<String> meS = me.getItemsRated();
        double result = 0;
        for (String k : meS) {
            if (r.hasRating(k)) {
                result += (r.getRating(k) - 5) * (me.getRating(k) - 5);
            }
        }
        return result;
    }

//    private double dotProduct(Rater me, Rater r) {
//        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
//        RaterDatabase.initialize("ratings_quiz4.csv");
//
//        String mID = me.getID();
//        String rID = r.getID();
//        Rater mIDR = RaterDatabase.getRater(mID);
//        Rater rIDR = RaterDatabase.getRater(rID);
//
//        double dotP = 0.0;
//
//        for (String k : movies) {
//            //System.out.println(k);
//            if (mIDR.hasRating(k) && rIDR.hasRating(k)) {
//                System.out.println(dotP);
//                dotP += (r.getRating(k) - 5) * (me.getRating(k) - 5);
//            }
//        }
//        return dotP;
//    }

    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r : RaterDatabase.getRaters()) {
            if (!r.getID().equals(id)) {
                double dotP = dotProduct(me, r);
                if (dotP > 0) {
                    list.add(new Rating(r.getID(), dotP));
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {

        return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new AllFilters());
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter f) {
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<Rating> ret = new ArrayList<Rating>();
        for (String movieID : MovieDatabase.filterBy(f)) {
            int currRaters = 0;
            int currRating = 0;
            if (list.size() < numSimilarRaters) {
                numSimilarRaters = list.size();
            }
            for (int k = 0; k < numSimilarRaters; k++) {
                Rater r = RaterDatabase.getRater(list.get(k).getItem());  // get Rater from Rater database
                if (r.hasRating(movieID)) {
                    currRaters += 1;
                    currRating += r.getRating(movieID) * list.get(k).getValue();  // weighted average = rating * dotP
                }
            }
            if (currRaters >= minimalRaters) {
                ret.add(new Rating(movieID, currRating / currRaters));
            }
        }
        ret.sort(Rating::compareTo);
        Collections.reverse(ret);
        return ret;
    }

    public ArrayList<Rating> getSimilarRatingsScaleOf10(String id, int numSimilarRaters, int minimalRaters, Filter f) {
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<Rating> ret = new ArrayList<Rating>();
        for (String movieID : MovieDatabase.filterBy(f)) {
            int currSimilaR = 0;
            int currRaters = 0;
            int currRating = 0;
            if (list.size() < numSimilarRaters) {
                numSimilarRaters = list.size();
            }
            for (int k = 0; k < numSimilarRaters; k++) {
                Rater r = RaterDatabase.getRater(list.get(k).getItem());  // get Rater from Rater database
                if (r.hasRating(movieID)) {
                    currRaters += 1;
                    currSimilaR += list.get(k).getValue();
                    currRating += r.getRating(movieID) * list.get(k).getValue();  // weighted average = rating * dotP
                }
            }
            if (currRaters >= minimalRaters) {
                ret.add(new Rating(movieID, currRating / currSimilaR));
            }
        }
        ret.sort(Rating::compareTo);
        Collections.reverse(ret);
        return ret;
    }

    /*public static void main(String[] args) {
        RaterDatabase.initialize("ratings_quiz4.csv");
        Rater k = RaterDatabase.getRater("15");
        Rater l = RaterDatabase.getRater("20");
//        Rater k = new EfficientRater("15");
//        Rater l = new EfficientRater("20");
        FourthRatings fr = new FourthRatings();
        double d = fr.dotProduct(k, l);
        System.out.println(d);
    }*/
}
