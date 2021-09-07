

-------------------------------------------------------------------------------------------------

Outline 
--------
This is the DIY version of Netflix and Amazon Movie Recommendation Engines. A search filter has also been added to get the desired subset of movies. IMDB movie database has been used for this project. This engine uses weighted averages to provide a more personal/tailored recommendations to the user.


--------------------------------------------------------------------------------------------------

Description
-------------------

//In this program, a movie has been represented with the following attributes:
//
//1. id - a String variable representing the IMDB ID of the movie
//2. title - a String variable for the movie’s title
//3. year - an integer representing the year
//4. genres - one String of one or more genres separated by commas
//5. director - one String of one or more directors of the movie separated by commas
//6. country - one String of one or more countries the film was made in, separated by commas
//7. minutes - an integer for the length of the movie
//8. poster - a String that is a link to an image of the movie poster if one exists, or “N/A” if no poster exists

//And raters have been represented by the following attributes:
//1. myID - a unique String ID for this rater
//2. myRatings - an ArrayList of Ratings

This program prompts the user to rate some of the movies and recommends movies based on those ratings as is done on Netflix and Amazon. The idea is to find other users in the database who rated the movies in a similar fashion. By analysing those similar raters(who rated the movies similarly), more personalised/tailored recommendations are given

Following functionalities have been added to the program
1. Gets average rating of the movie by id (if the movie has been rated by minimal raters)
2. Movies can be filtered based on movie's year of release, Genre, minutes(how long the movie is) and director of the movie
3. Movies are recommended based on user's ratings by using weighted averages.
4. HTML and CSS styling has been used to display the results


Final/complete Code is in the following files:
1. FourthRatings
2. RecommendationRunner
3. AllFilters

Please find the interactive link to the program below

https://www.dukelearntoprogram.com//capstone/recommender.php?id=KM8q29Q0ELXTOv


Note : This is the capstone project for the Duke University Java Programming and Software Engineering Fundamentals Specialization


