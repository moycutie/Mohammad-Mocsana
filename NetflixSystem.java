import java.util.ArrayList;
import java.util.List;

class Movie {
    private String title;
    private String genre;

    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void watchMovie(Movie movie, int duration) {
        // Simulate watching a movie
        System.out.println(name + " watched " + movie.getTitle() + " for " + duration + " minutes.");
    }
}

class RecommendationSystem {
    private List<Movie> movies = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<String> recommend(User user) {
        List<String> recommendations = new ArrayList<>();
        String preferredGenre = "Sci-Fi"; // Assuming preferred genre is Sci-Fi
        for (Movie movie : movies) {
            if (movie.getGenre().equals(preferredGenre)) {
                recommendations.add(movie.getTitle());
            }
        }
        return recommendations;
    }
}

public class NetflixSystem {
    public static void main(String[] args) {
        // Create movies
        Movie movie1 = new Movie("Inception", "Sci-Fi");
        Movie movie2 = new Movie("The Dark Knight", "Action");
        Movie movie3 = new Movie("Interstellar", "Sci-Fi");
        Movie movie4 = new Movie("The Matrix", "Sci-Fi");

        // Create a recommendation system
        RecommendationSystem system = new RecommendationSystem();
        system.addMovie(movie1);
        system.addMovie(movie2);
        system.addMovie(movie3);
        system.addMovie(movie4);

        // Create a user
        User user = new User("Alice");
        system.addUser(user);

        // Simulate watching movies
        user.watchMovie(movie1, 120);
        user.watchMovie(movie3, 150);

        // Get recommendations
        List<String> recommendations = system.recommend(user);
        System.out.println("Recommendations for Alice: " + recommendations);
    }
}