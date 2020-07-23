
public class MovieCollection {

    BST binarySearchTree = new BST();    // new binary search tree to store movies

    /**
     * Inserts new movie into movieCollection
     * @param movie Movie object to add to movieCollection
     */
    public void addMovie(Movie movie) { binarySearchTree.insert(new BST.Node(movie)); }

    /**
     * Function that calls BST.delete to remove movie with title "movieTitle" from movieCollection
     * @param movieTitle Title of remove to remove from movieCollection
     */
    public void removeMovie(String movieTitle) { binarySearchTree.delete(movieTitle); }

    /**
     * Performs inorder traversal of BST to display all movies in alphabetical order
     * @param node Root node to begin from
     */
    public void displayAllMovies(BST.Node node) {
        if (node != null) {
            displayAllMovies(node.left);
            System.out.println("Title: " + node.value.title);
            System.out.println("Starring: " + node.value.getStarring());
            System.out.println("Director: " + node.value.getDirector());
            System.out.println("Duration: " + node.value.getDuration() + " minutes");
            System.out.println("Genre: " + node.value.getGenre());
            System.out.println("Classification: " + node.value.getClassification());
            System.out.println("Release date (year): " + node.value.getReleaseDate());
            System.out.println("Copies available: " + node.value.getCopiesAvailable());
            System.out.println("Times borrowed: " + node.value.getTimesBorrowed());
            System.out.println();
            displayAllMovies(node.right);
        }
    }

    /**
     * Recursive function that searches for movie title in BST, from given Node object
     * @param node Root Node object of BST
     * @param title Title of movie that is being searched from
     * @return True if title is in BST, false otherwise
     */
    public boolean contains(BST.Node node, String title) {
        if (node == null) return false;
        if (node.value.title.equals(title)) return true;

        boolean res1 = contains(node.left, title);  // check left subtree
        if (res1) return true;  // node found, no need to look further

        return contains(node.right, title); // not in left, therefore check right subtree
    }

    /**
     * Searches movieCollection for Movie where Movie.title == movieTitle,
     * @param node Root node to begin search from
     * @param movieTitle Title of movie being searched for
     * @return Movie object that has the title movieTitle
     */
    public Movie findMovie(BST.Node node, String movieTitle) throws NullPointerException {
        if (node == null) return null;
        if (node.value.title.equals(movieTitle)) return node.value;

        // search for movie in left tree
        Movie movie = findMovie(node.left, movieTitle);
        try {
            if (movie.title.equals(movieTitle)) return movie;
        } catch (NullPointerException ignored) { }

        return findMovie(node.right, movieTitle);   // search for movie in right tree
    }

    /**
     * Searches BST for Node where Node.Movie.title == movieTitle,
     * @param node Root node to begin search from
     * @param movieTitle Title of movie being searched for
     * @return Node object that has the title movieTitle
     */
    public BST.Node findNode(BST.Node node, String movieTitle) throws NullPointerException {
        if (node == null) return null;
        if (node.value.title.equals(movieTitle)) return node;

        BST.Node leftNode = findNode(node.left, movieTitle);    // search for node in left tree
        try {
            if (leftNode.value.title.equals(movieTitle)) return leftNode;
        } catch (NullPointerException ignored) { }

        return findNode(node.right, movieTitle);    // search for node in right tree
    }

    /**
     * Combines two sorted sub-arrays to form one sorted (sub)array.
     * @param arr Array containing every Node from binary search tree
     * @param left Leftmost index of array
     * @param middle Middle index of array
     * @param right Rightmost index of array
     */
    public void merge(BST.Node[] arr, int left, int middle, int right) {
        int lenLeft = middle - left + 1;   // size of left sub array
        int lenRight = right - middle;      // size of right sub array

        // temp arrays
        BST.Node[] leftArray = new BST.Node[lenLeft];
        BST.Node[] rightArray = new BST.Node[lenRight];

        // copy data to temp arrays
        if (lenLeft >= 0) System.arraycopy(arr, left, leftArray, 0, lenLeft);
        if (lenRight >= 0) System.arraycopy(arr, middle + 1, rightArray, 0, lenRight);

        // mer temp arrays
        int leftIndex = 0;
        int rightIndex = 0;
        int index = left;

        while ((leftIndex < lenLeft) && (rightIndex < lenRight)) {
            // sort in decreasing order (increasing order uses <=)
            if (leftArray[leftIndex].value.timesBorrowed >= rightArray[rightIndex].value.timesBorrowed) {
                arr[index] = leftArray[leftIndex];
                leftIndex++;
            } else {
                arr[index] = rightArray[rightIndex];
                rightIndex++;
            }
            index++;
        }

        // copy remaining elements of leftArray
        while (leftIndex < lenLeft) {
            arr[index] = leftArray[leftIndex];
            leftIndex++;
            index++;
        }

        // copy remaining elements of rightArray
        while (rightIndex < lenRight) {
            arr[index] = rightArray[rightIndex];
            rightIndex++;
            index++;
        }
    }


    /**
     * Perform merge sort on array of Nodes in relation to the amount of times the Node.Movie has been borrowed by
     * recursively breaking down an (sub)array into two smaller sub-arrays.
     * @param arr Array containing every Node from binary search tree
     * @param left Leftmost index of array
     * @param right Rightmost index of array
     */
    public void mergeSort(BST.Node[] arr, int left, int right) {
        if (left < right) {
            int middle = (left+right) / 2;

            // Sort first and second halves
            mergeSort(arr, left, middle);
            mergeSort(arr, middle+1, right);

            // Merge the sorted halves
            merge(arr, left, middle, right);
        }
    }

    /**
     * Display top 10 most frequently borrowed movies in descending order
     * @param arr Array containing every Node from binary search tree
     */
    public void mostFrequentMovies(BST.Node[] arr) {
        mergeSort(arr, 0, arr.length-1);
        System.out.println("\nTop 10 most frequently borrowed movies: ");

        int i = 1;  // count for order
        for (BST.Node node : arr) {
            if (i <= 10) {
                System.out.println(i + ". " + node.value.title + ": " + node.value.timesBorrowed);
                i++;
            }
        }
    }
}
