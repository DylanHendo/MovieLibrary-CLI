import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    MovieCollection movieCollection = new MovieCollection();    // MovieCollection object to store all movies (BST)
    MemberCollection memberCollection = new MemberCollection(); // MemberCollection object to store all members (array)
    BST.Node[] BSTArray = new BST.Node[0];    // store binary search tree in array for top 10 most frequently borrowed

    /**
     * Main program, calls mainMenu(), which in turn calls the other 2 menus and all the controls/actions
     * @param args N/A
     */
    public static void main(String[] args) {
        Main program = new Main();
        program.mainMenu();
    }

    /**
     * Display main menu GUI in command line. Allows users to enter
     * the staff menu, member menu or exit the application.
     */
    public void mainMenu() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to the Community Library");
            System.out.println("===========Main menu============");
            System.out.println("1. Staff Menu");
            System.out.println("2. Member Menu");
            System.out.println("0. Exit");
            System.out.println("================================");
            System.out.print("Please make a selection (1-2, or 0 to exit): ");

            String option = in.nextLine();

            switch (option) {
                case "0":
                    return;
                case "1":
                    staffMenu();
                    break;
                case "2":
                    memberMenu();
                    break;
                default:
                    System.out.println("\nIncorrect option, please enter 0, 1 or 2!");
                    break;
            }
        }
    }


    /**
     * Display staff menu GUI in command line to handle staff controls,
     * such as adding/removing DVD's and creating/searching members.
     * @throws InputMismatchException Handles error if user enters string instead of integer for nextInt()
     */
    public void staffMenu() throws InputMismatchException {

        Scanner staffIn = new Scanner(System.in);
        System.out.print("Enter username: "); String username = staffIn.nextLine(); // staff username
        System.out.print("Enter password: "); String password = staffIn.nextLine(); // staff password

        boolean loggedIn = false;   // initially not logged in
        if ((username.equals("staff")) && (password.equals("today123")))
            loggedIn = true;
        else
            System.out.println("\nERROR: Incorrect username or password");

        // only run if correct login
        while (loggedIn) {
            System.out.println("\n===========Staff menu============");
            System.out.println("1. Add a new movie DVD");
            System.out.println("2. Remove a movie DVD");
            System.out.println("3. Register a new Member");
            System.out.println("4. Find a registered member's phone number");
            System.out.println("0. Return to main menu");
            System.out.println("=================================");
            System.out.print("Please make a selection (1-4, or 0 to return to main menu): ");

            String staffOption = staffIn.nextLine();    // wait for next integer input

            switch (staffOption) {
                case "0":
                    loggedIn=false;   // exit loop
                    break;
                case "1":
                    Scanner newMovie = new Scanner(System.in);
                    try {
                        System.out.print("Title: "); String title = newMovie.nextLine();

                        // if movie already exists in movieCollection, increase copies, else, add new movie
                        if (movieCollection.contains(movieCollection.binarySearchTree.root, title)) {
                            Scanner copiesIn = new Scanner(System.in);
                            System.out.print("How many copies would you like to add?: ");
                            int copies = copiesIn.nextInt();

                            // search movieCollection for Movie where Movie.title == title,
                            // increase copies on that Movie object
                            Movie repeatMovie = movieCollection.findMovie(movieCollection.binarySearchTree.root, title);
                            repeatMovie.copiesAvailable += copies;

                            System.out.println();
                            System.out.println(title + " now has an extra " + copies + " copies!");
                        } else {
                            System.out.print("Starring: "); String starring = newMovie.nextLine();
                            System.out.print("Director: "); String director = newMovie.nextLine();
                            System.out.print("Duration (minutes): "); int duration = newMovie.nextInt();
                            newMovie.nextLine();    // nextInt() doesn't have new line called

                            // Show list of GENRE's for user to choose from, default is other
                            Scanner genreIn = new Scanner(System.in);  // scanner object
                            System.out.println("Genre: ");
                            System.out.println("\t1. Drama");
                            System.out.println("\t2. Adventure");
                            System.out.println("\t3. Family");
                            System.out.println("\t4. Action");
                            System.out.println("\t5. SCI-FI");
                            System.out.println("\t6. Comedy");
                            System.out.println("\t7. Animated");
                            System.out.println("\t8. Thriller");
                            System.out.println("\t9. Other");
                            System.out.print("Please choose a genre (1-9): ");
                            int genreOption = genreIn.nextInt();

                            Movie.Genre genre;  // create Genre instance to hold specified Genre
                            switch (genreOption) {
                                case 1:
                                    genre = Movie.Genre.DRAMA;
                                    break;
                                case 2:
                                    genre = Movie.Genre.ADVENTURE;
                                    break;
                                case 3:
                                    genre = Movie.Genre.FAMILY;
                                    break;
                                case 4:
                                    genre = Movie.Genre.ACTION;
                                    break;
                                case 5:
                                    genre = Movie.Genre.SCI_FI;
                                    break;
                                case 6:
                                    genre = Movie.Genre.COMEDY;
                                    break;
                                case 7:
                                    genre = Movie.Genre.ANIMATED;
                                    break;
                                case 8:
                                    genre = Movie.Genre.THRILLER;
                                    break;
                                case 9:
                                default:
                                    genre = Movie.Genre.OTHER;
                                    break;

                            }

                            // Show list of CLASSIFICATIONS's for user to choose from, default is MA15+
                            Scanner classificationIn = new Scanner(System.in);
                            System.out.println("Classification: ");
                            System.out.println("\t1. General (G)");
                            System.out.println("\t2. Parental Guidance (PG)");
                            System.out.println("\t3. Mature (M)");
                            System.out.println("\t4. Mature Accompanied (MA15+)");
                            System.out.print("Please make a selection (1-4): ");
                            int classificationOption = classificationIn.nextInt();

                            Movie.Classification classification;
                            switch (classificationOption) {
                                case 1:
                                    classification = Movie.Classification.GENERAL;
                                    break;
                                case 2:
                                    classification = Movie.Classification.PARENTAL_GUIDANCE;
                                    break;
                                case 3:
                                    classification = Movie.Classification.MATURE;
                                    break;
                                case 4:
                                default:
                                    classification = Movie.Classification.MATURE_ACCOMPANIED;
                                    break;
                            }

                            System.out.print("Release date (year): "); int releaseDate = newMovie.nextInt();
                            System.out.print("Copies available: "); int copiesAvailable = newMovie.nextInt();

                            // add new movie object
                            movieCollection.addMovie(new Movie(title, starring, director, duration, genre,
                                    classification, releaseDate, copiesAvailable));

                            // find Node object of Movie with title: title
                            BST.Node toAdd = movieCollection.findNode(movieCollection.binarySearchTree.root, title);

                            // add node of new movie object to arr to be sorted for top 10 frequently borrowed movies
                            BSTArray = movieCollection.binarySearchTree.addNodeToArray(BSTArray, toAdd);

                            System.out.println("\nYou added a DVD!");
                        }

                    // error handling, in case user enters string instead of int for nextInt()
                    } catch (InputMismatchException ime) {
                        System.out.println("\nERROR: Movie was not added, please enter an integer.");
                        break;
                    }

                    break;
                case "2":
                    Scanner deleteMovie = new Scanner(System.in);
                    System.out.print("Movie title: "); String movieTitle = deleteMovie.nextLine();

                    // see if movieCollection contains a DVD with title==movieTitle
                    if (movieCollection.contains(movieCollection.binarySearchTree.root, movieTitle)) {

                        // if user deletes movie, delete it from array for top 10 borrows
                        BST.Node toDelete = movieCollection.findNode(movieCollection.binarySearchTree.root, movieTitle);
                        BSTArray = movieCollection.binarySearchTree.deleteNodeFromArray(BSTArray, toDelete);

                        movieCollection.removeMovie(movieTitle);    // remove from movieCollection
                        System.out.println("\nYou removed " + movieTitle + " from the system!");

                    } else
                        System.out.println("\nERROR: There is no movie called " + movieTitle +
                                " currently in the system.");

                    break;
                case "3":
                    Scanner newMember = new Scanner(System.in);
                    System.out.print("First name: "); String firstName = newMember.nextLine();
                    System.out.print("Last name: "); String lastName = newMember.nextLine();

                     if (memberCollection.contains(lastName + firstName))
                         System.out.println("\nERROR: User already exists!");
                     else {
                         System.out.print("Address: "); String address = newMember.nextLine();
                         System.out.print("Phone number: "); String phoneNumb = newMember.nextLine();
                         System.out.print("Password (4-digit): "); String memberPassword = newMember.nextLine();

                         if (memberPassword.length() != 4) {
                             System.out.println("\nERROR: Password must be a 4 digit number!");
                             break;
                         }

                         // if password contains letters, exit
                         try {
                             Integer.parseInt(memberPassword);
                         } catch(NumberFormatException e) {
                             System.out.println("\nERROR: Password must be a 4 digit number!");
                             break;
                         }

                         // if member array hasn't reach capacity, add member
                         if (memberCollection.getNumMembers() < memberCollection.getCAPACITY()) {
                             memberCollection.add(new Member(firstName, lastName, address, phoneNumb, memberPassword));
                             System.out.println("\nYou registered a member!");
                         } else {
                             System.out.println("\nERROR: The member collection is full!");
                         }
                     }

                    break;
                case "4":
                    Scanner getNumber = new Scanner(System.in);
                    System.out.print("First name: "); String first = getNumber.nextLine();
                    System.out.print("Last name: "); String last = getNumber.nextLine();
                    System.out.println();
                    System.out.print(first + "'s phone number is: ");
                    memberCollection.findPhoneNum(first, last);
                    break;
                default:
                    System.out.println("\nIncorrect option, please enter 0,1,2,3 or 4!");
                    break;
            }
        }
    }


    /**
     * Display member menu GUI in command line to handle member controls,
     * such as borrowing/returning DVD's and showing DVD information.
     * @throws NullPointerException Handles error if user tries to borrow movie when library empty
     */
    public void memberMenu() throws NullPointerException {

        Scanner memberIn = new Scanner(System.in);  // scanner object
        System.out.print("Enter username: "); String username = memberIn.nextLine(); // username=LastnameFirstName
        System.out.print("Enter password: "); String password = memberIn.nextLine(); // password=4-digit num

        Member thisMember = null;   // member account to perform actions on
        boolean loggedIn = false;   // initially not logged in

        // search memberCollection for user that matches userName and password
        if ((memberCollection.contains(username)) && (memberCollection.findPassword(username).equals(password))) {
            loggedIn = true;
            thisMember = memberCollection.findMember(username); // assign member to logged in user
        } else
            System.out.println("\nERROR: Incorrect username or password!");

        while (loggedIn) {
            System.out.println("\n===========Member menu============");
            System.out.println("1. Display all movies");
            System.out.println("2. Borrow a movie DVD");
            System.out.println("3. Return a movie DVD");
            System.out.println("4. List current borrowed movie DVDs");
            System.out.println("5. Display top 10 most popular movies");
            System.out.println("0. Return to main menu");
            System.out.println("=================================");
            System.out.print("Please make a selection (1-5, or 0 to return to main menu): ");

            String memberOption = memberIn.nextLine();

            switch (memberOption) {
                case "0":
                    loggedIn=false;
                    break;
                case "1":
                    System.out.println();
                    movieCollection.displayAllMovies(movieCollection.binarySearchTree.root);
                    break;
                case "2":
                    Scanner borrow = new Scanner(System.in);
                    System.out.print("Movie title: "); String movieTitle = borrow.nextLine();

                    // get Movie object that has title=movieTitle
                    Movie toBorrow = movieCollection.findMovie(movieCollection.binarySearchTree.root, movieTitle);

                    // try to add movie, catch error if no movies in movie library (null error)
                    try {
                        if (toBorrow.copiesAvailable >= 1)  {
                            if (thisMember.borrowedMovies.contains(movieTitle))
                                System.out.println("\nERROR: You are already borrowing " + movieTitle);
                            else {
                                thisMember.borrowMovie(movieTitle);
                                toBorrow.copiesAvailable--;
                                toBorrow.timesBorrowed++;
                            }
                        } else
                            System.out.println("\nERROR: There are currently no copies available for: " + movieTitle);
                    } catch (NullPointerException err) {
                        System.out.println("\nERROR: There is no movie called " + movieTitle +
                                " currently in the system.");
                    }
                    break;
                case "3":
                    Scanner returning = new Scanner(System.in);
                    System.out.print("Movie title: "); String title = returning.nextLine();

                    // get Movie object that has title=movieTitle
                    Movie toReturn = movieCollection.findMovie(movieCollection.binarySearchTree.root, title);

                    // catch when movie is deleted from library while still being borrowed by member
                    try {
                        // if member has movie in borrowed movies, remove it, increment copies available of movie
                        if (thisMember.borrowedMovies.contains(title)) {
                            thisMember.returnMovie(title);
                            toReturn.copiesAvailable++;
                            System.out.println("\nYou returned the movie: " + title);
                        } else {
                            System.out.println("\nERROR: You are not currently borrowing " + title);
                        }
                    } catch (NullPointerException err) {
                        thisMember.returnMovie(title);
                        System.out.println("\nYou returned the movie: " + title);
                        System.out.println("\nWARNING: the staff have removed " + title + " from the DVD library!");
                    }
                    break;
                case "4":
                    thisMember.listBorrowedMovies();
                    break;
                case "5":
                    movieCollection.mostFrequentMovies(BSTArray);  // perform sorting on array of nodes
                    break;
                default:
                    System.out.println("Incorrect option, please enter 0,1,2,3,4 or 5");
                    break;
            }
        }
    }
}
