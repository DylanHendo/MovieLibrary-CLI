import java.util.ArrayList;

public class Member {

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String phoneNumber;
    private final String password;
    public ArrayList<String> borrowedMovies;

    private final int CAPACITY = 10;

    public Member(String firstName, String lastName, String address, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.borrowedMovies = new ArrayList<>(CAPACITY);   // each member can only hold 10 unique movies
    }

    /**
     * Borrow a movie DVD from the community library, given the title of the movie DVD
     * @param title Title of movie to be borrowed
     */
    public void borrowMovie(String title) {
        if (borrowedMovies.size() < CAPACITY) {
            borrowedMovies.add(title);
            System.out.println("\nYou borrowed the movie: " + title);
        } else
            System.out.println("\nERROR: You have reached your quota for borrowed movies.");
    }

    /**
     * Return a movie DVD to the community library, given the title of the movie DVD
     * @param title Title of movie to be returned
     */
    public void returnMovie(String title) { borrowedMovies.remove(title); }

    /**
     * Print list of movies currently being borrowed.
     */
    public void listBorrowedMovies() {
        System.out.println("\nYou are currently borrowing: ");
        for (String title : borrowedMovies)
            System.out.println("\t- " + title);
    }


    // ==================== GETTER FUNCTIONS ==============================
    public String getFullName() { return lastName + firstName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getPassword() { return password; }

}
