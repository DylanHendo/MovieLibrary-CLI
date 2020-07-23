/**
 * Binary Search Tree implementations
 */
public class BST {

    public static class Node {

        Movie value;
        Node left, right;

        public Node(Movie value) {
            this.value = value;
            this.right = null;
            this.left = null;
        }

        /**
         * Utility function to recursively traverses BST, searching for min value in each left sub tree.
         * @param node Root Node object of BST
         * @return Node object, contains min value in left subtree
         */
        private Node minValue(Node node) {
            if (node.left == null)
                return node;
            else {
                return minValue(node.left);
            }
        }

        /**
         * Recursively traverses tree, starting from root, searching for the correct location to add a node at.
         * @param node Root Node object of BST
         */
        private void addNode(Node node) {
            // if node to be added is alphabetically less then root
            if (node.value.title.compareToIgnoreCase(value.title) < 0) {
                if (left == null)
                    left = node;
                else
                    left.addNode(node);
            } else if (node.value.title.compareToIgnoreCase(value.title) > 0) {
                if (right == null)
                    right = node;
                else
                    right.addNode(node);
            }
        }

        /**
         * Recursively traverses BST, searching for node to be deleted, based on movie title
         * @param node Root Node object of BST
         * @param movieTitle Title of movie that is to be deleted from BST
         * @return Node object
         */
        private Node deleteNode(Node node, String movieTitle) {
            if (node == null) return null;

            if (node.value.title.compareToIgnoreCase(movieTitle) > 0) {
                node.left = deleteNode(node.left, movieTitle);
            } else if (node.value.title.compareToIgnoreCase(movieTitle) < 0) {
                node.right = deleteNode(node.right, movieTitle);
            } else {
                // if node has 2 children
                if ((node.right != null) && (node.left != null)) {
                    Node minRight = minValue(node.right);   // find minimum value i right sub tree
                    node.value = minRight.value;            // set current value to min value
                    node.right = deleteNode(node.right, minRight.value.title);
                // if node only has left child
                } else if (node.left != null)
                    node = node.left;
                // if node has only right child
                else if (node.right != null)
                    node = node.right;
                // node has no children
                else
                    node = null;
            }
            return node;
        }
    }


    Node root; // root node (first movie added)

    public BST() {
        this.root = null;
    }

    /**
     * Function called by MovieCollection that inserts newNode into correct position
     * @param newNode New Node to be inserted into Binary Search Tree
     */
    public void insert(Node newNode) {
        if (root == null)
            root = newNode;
        else {
            root.addNode(newNode);
        }
    }

    /**
     * Calls deleteNode to recursively delete Node from BST, based on the title provided
     * @param title Movie title of Node that is to be deleted from BST
     */
    public void delete(String title) {
        root = root.deleteNode(root, title);
    }

    /**
     * Function to dynamically increase size of array, and add the element 'node' to the array
     * @param node Node object to add to array
     * @return Array with length + 1 and containing the node
     */
    public Node[] addNodeToArray(Node[] arr, Node node) {
        int len = arr.length;
        BST.Node[] tempArray = new BST.Node[len + 1];
        System.arraycopy(arr, 0, tempArray, 0, arr.length); // copy array from arr to tempArray
        arr = tempArray;
        arr[len] = node;    // assign node to correct position
        return arr;
    }

    /**
     * Function to remove the element 'node' from the array and decrease array size by 1
     * @param node Node object to remove from array
     * @return New array, without node
     */
    public Node[] deleteNodeFromArray(Node[] arr, Node node) {
        // loop through array, looking for node
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].value.title.equals(node.value.title)) {
                Node[] copy = new Node[arr.length-1];
                System.arraycopy(arr, 0, copy, 0, i);
                System.arraycopy(arr, i+1, copy, i, arr.length-i-1);

                return copy;
            }
        }

        return arr;
    }
}
