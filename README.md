

-------------------------------------------------------------------------------------------------

Outline 
--------
- This is the DIY version of Netflix and Amazon Movie Recommendation Engines. 
- IMDB movie database API has been used. 
- This engine uses weighted averages to provide the user with more personal/tailored recommendations. Program asks the user to rate some movies and then recommends a list of movies based on other users in the database who similarly rated the movies.
- A search filter has also been added to get the desired subset of movies.

--------------------------------------------------------------------------------------------------

Description
-------------------
This program prompts the user to rate some of the movies and recommends movies based on those ratings, as on Netflix and Amazon. The idea is to find other users in the database who similarly rated the movies. By analysing those similar users(who rated the movies similarly), more personalised recommendations are given

The following functionalities have been added to the program
1. Gets an average rating of the movie by id (if the movie has been rated by at least a minimum number of users)
2. Movies can be filtered based on the movie's year of release, Genre, minutes(how long the movie is), and director of the movie
3. Movies are recommended based on users’ ratings by using weighted averages.
4. HTML and CSS styling have been used to display the results

The following pictures show the output of the program
![](images/SubmitRatings.PNG)
![](images/Recommendations.PNG)


Please find the interactive link to the program below

https://www.dukelearntoprogram.com//capstone/recommender.php?id=KM8q29Q0ELXTOv

The final/complete Code is in the following files:
1. FourthRatings
2. RecommendationRunner
3. AllFilters

Note: This is the capstone project for the Duke University Java Programming and Software Engineering Fundamentals Specialization


