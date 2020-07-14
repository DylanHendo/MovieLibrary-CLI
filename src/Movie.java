public class Movie {

    public String title;
    private final String starring;
    private final String director;
    private final int duration;         // minutes
    public enum Genre {
        DRAMA,
        ADVENTURE,
        FAMILY,
        ACTION,
        SCI_FI,
        COMEDY,
        ANIMATED,
        THRILLER,
        OTHER
    }
    public Genre genre;    // instance of enum class

    public enum Classification {
        GENERAL,
        PARENTAL_GUIDANCE,
        MATURE,
        MATURE_ACCOMPANIED
    }
    public Classification classification;  // instance of enum class

    private final int releaseDate;    // year
    public int copiesAvailable;
    public int timesBorrowed;


    public Movie(String title, String starring, String director, int duration, Genre genre,
                 Classification classification, int releaseDate, int copiesAvailable) {
        this.title = title;
        this.starring = starring;
        this.director = director;
        this.duration = duration;
        this.genre = genre;
        this.classification = classification;
        this.releaseDate = releaseDate;
        this.copiesAvailable = copiesAvailable;
        this.timesBorrowed = 0;
    }


    // ====================== GETTER FUNCTIONS ==============================

    public String getStarring() { return this.starring; }

    public String getDirector() { return this.director; }

    public int getDuration() { return this.duration; }

    public Genre getGenre() { return this.genre; }

    public Classification getClassification() { return this.classification; }

    public int getReleaseDate() { return this.releaseDate; }

    public int getCopiesAvailable() { return this.copiesAvailable; }

    public int getTimesBorrowed() { return  this.timesBorrowed; }


}
