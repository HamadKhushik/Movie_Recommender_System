import java.util.ArrayList;
import java.util.Random;

/**
 * @author Hammad on 31/05/2020.
 * @project Project_Recommender_System
 */
public class RecommendationRunner implements Recommender {

    @Override
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> list = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<String> ret = new ArrayList<String>();
        for (int k = 0; k < 10; k++) {
            Random rand = new Random();
            ret.add(list.get(rand.nextInt(list.size())));
        }
        return ret;
    }

    @Override
    public void printRecommendationsFor(String webRaterID) {
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");

        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> result = fr.getSimilarRatingsScaleOf10(webRaterID, 20, 2, new AllFilters());
        int rMovies = 0;

        if (result.size() < 20) {
            rMovies = result.size();
        } else {
            rMovies = 20;
        }
        System.out.println("Number of Recomended Movies: " + rMovies);

        if (result.size() == 0) {
            System.out.println("No Recommendations for Given Ratings");
        } else {
            System.out.println("<html> <h3 class = \"header\"> Recommended Movies </h3>");
            System.out.println("<table>");
            System.out.println("<tr><th class = \"Theader\"> Movie </th>");
            System.out.println("<th> Year </th>");
            System.out.println("<th> Rating </th>");
            System.out.println("<th> Genre </th>");
            System.out.println("</tr>");
            String beg = "https://www.imdb.com/title/tt";
            String end = "/?ref_=fn_al_tt_1";
            for (int k = 0; k < 20; k++) {
                Rating r = result.get(k);
                Movie m = MovieDatabase.getMovie(r.getItem());
                System.out.println(" <tr><td> <a href = " + beg + m.getID() + end + " target = \"blank\"> "
                        + m.getTitle() + "</a> </td>");
                System.out.println(" <td> " + m.getYear() + " </td>");
                System.out.println(" <td> " + r.getValue() + " </td>");
                System.out.println(" <td> " + m.getGenres() + " </td>");
                System.out.println(" </tr>");

                if ((k + 1) == result.size()) {
                    break;
                }
            }
            System.out.println("<style>.header{color:#1a1818;text-align:center; font-size: 30px;}");
            System.out.println("table { border-collapse: collapse; width: 70%; margin-left:auto; margin-right:auto;}");
            System.out.println("th {text-align: left; background-color: #822f78;" +
                    "color: white;}");
            System.out.println("tr:nth-child(even) {background-color: #9a9c11;}");
            System.out.println("table, th, td {border: 2px solid black;}");
            System.out.println("th, td {padding: 6px}");
            System.out.println("th {font-size: 20px}");
            System.out.println("td {font-size: 15px}");

            System.out.println("</table>");
            System.out.println("</html>");
        }
    }

    /*public static void main(String[] args) {
        RecommendationRunner rr = new RecommendationRunner();
        *//*ArrayList<String> result = rr.getItemsToRate();
        System.out.println(result.size());
        for (String k : result) {
            System.out.println(k);
        }*//*
        rr.printRecommendationsFor("11");
    }*/
}
