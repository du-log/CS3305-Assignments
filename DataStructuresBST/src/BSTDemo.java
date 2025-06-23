// Name: Brandon Dulog
// Class: CS 3305/Section 03
// Term: Spring 2025
// Instructor: Umama Tasnim
// Assignment: 6

/*
Decided that rather than always making new single line comments with //, I would just do a block comment.

I assume that we will not have to use a loop, so I will just make a preset array of elements, insert each element into
the tree, then run methods a few times. I added two extra methods: one that just populates the BST; another that prints
a message based on the boolean from the search method. The populate method uses the required search method to check if
we have duplicates, but otherwise lets the required insert method do its thing.

The BinarySearchTree class contains a TreeNode root for the root of the tree. It also has the required methods (insert,
delete, search, inorderTraversal, height), however I am running them recursively so the majority of the work will be
done in their respective Recursion methods. I have a private method minimumValueNode as a helper for when we want
to delete a node that happens to be a parent.

The insert method will call insertRecursion. insertRecursion will take in the designated key from insert as well as
a TreeNode, in this case it will be root since we have to start from there. The base case is if the current node is null,
then we add a new node with our designated key to that current node and then end with a return. Otherwise, we compare
the given key with the current node's key, recursing with node.left if key < node.key or node.right if key > node.key.
If the key equals node.key / is a duplicate, then we will just end the method by returning that same node.

The delete method will call deleteRecursion. The base case is if the current node is null, then we end since we cannot
find a node with the given key integer. Otherwise, we will recurse in the same fashion as the insertRecursion method.
When we find the node with the same key as the given key, then we check if that node is a parent by seeing if it's left
or right nodes are null. Depending on if it has child nodes, it will either return a node or continue. Regardless, we
then call the minimumValueNode helper on the node.right because we need the replacement to still be within BST rules.
From node.right, the helper method will check if a node.left exists, and then keeps traversing until there is none left
at which point the node.left it stopped at becomes the replacement. However, if the node.right does not have a left
child, then that will be the replacement node. Regardless, we delete the old key by overwriting with the replacement
node's key, then delete the replacement node by recursing it. If the node we were trying to delete is a leaf node, then
the key will end up empty/null effectively removing it from the tree.

The inorderTraversal method calls inorderRecursion where if a node exists, it will recurse to its left child node. This
continues until it hits a null. Then, it prints out the key of the last existing node. After that, it switches to the
right child node and repeats the process until that finishes.
Essentially, if we have:
     50
    /  \
  30    70
 /  \
20  25
it starts at 50, then goes to 30, then 20. 20 has no left child, so it prints out 20, then goes to the right child.
20 also does not have a right child, so it finishes that loop. It goes back and prints 30, then moves to that right
child which is 25. This continues until all the node keys in the tree have been printed and traversed.

The search method calls searchRecursion where it takes in the root node and the designated key. The base cases are if
the node is null, return false, or if the node's key is the same as the designated key, return true. We take a boolean
result, then recurse through the current node's left and right children. It checks the base cases until all the nodes
have been searched, then returns the final result to the original method call.

The height method calls heightRecursion which returns 0 if the given node is null. Otherwise, it returns 1 + the maximum
value after recursing through the given node's left and right child nodes, if they exist. Each existing node from the
child adds one to the count, and the final height is based on which side returns the largest value... obviously.

All the recursion methods take in root as their first node since we have to start from the root anyway.
The delete method also takes in a boolean that is set during the recursion and prints out the result based on if the
flag is true or false. If true, it calls inorderTraversal to show the new tree after deleting the requested node.
*/

public class BSTDemo {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        int[] values = {60, 50, 13, 45, 76, 53, 54, 70, 65, 23, 40, 57, 34, 86, 76, 45, 80, 54};

        populateDemo(values, bst);
        System.out.println("Height of BST: " + bst.height() + "\n");

        System.out.print("Inorder Traversal: ");
        bst.inorderTraversal();
        System.out.println();

        int searchKey1 = 67;
        int searchKey2 = 70;
        int searchKey3 = 80;
        searchDemo(searchKey1, bst);
        System.out.println();
        searchDemo(searchKey2, bst);
        System.out.println();
        searchDemo(searchKey3, bst);
        System.out.println();

        int deleteKey1 = 13;
        int deleteKey2 = 60;
        int deleteKey3 = 55;
        bst.delete(deleteKey1);
        System.out.println();
        bst.delete(deleteKey2);
        System.out.println();
        bst.delete(deleteKey3);
        System.out.println();

        System.out.println("Height of BST: " + bst.height() + "\n");
    }

    public static void populateDemo(int[] arr, BinarySearchTree tree) {
        int dup = 0;
        System.out.print("Inserting values: ");
        for (int i = 0; i < arr.length; i++) {
            if (tree.search(arr[i])) {
                dup++;
            }
            tree.insert(arr[i]);
            if (i == arr.length - 1) {
                System.out.print(arr[i]);
            } else {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println("\n" + dup + " duplicate values not inserted.\n");
    }

    public static void searchDemo(int key, BinarySearchTree tree) {
        System.out.println("Searching for " + key + ": ");
        if(tree.search(key)) {
            System.out.println(key + " found in BST");
        } else {
            System.out.println(key + " not found in BST");
        }
    }

    private static class TreeNode {
        private int key;
        private TreeNode left, right;

        TreeNode(int key) {
            this.key = key;
            this.left = this.right = null;
        }
    }

    public static class BinarySearchTree {
        private TreeNode root;
        private boolean deleteFlag;

        public void insert(int key) {
            root = insertRecursion(root, key);
        }

        private TreeNode insertRecursion(TreeNode node, int key) {
            if (node == null) {
                node = new TreeNode(key);
                return node;
            }

            if (key < node.key) {
                node.left = insertRecursion(node.left, key);
            } else if (key > node.key) {
                node.right = insertRecursion(node.right, key);
            }

            return node;
        }

        public void delete(int key) {
            deleteFlag = false;
            System.out.println("Attempting to delete " + key + " from BST.");
            root = deleteRecursion(root, key);
            if(deleteFlag) {
                System.out.println("Successfully deleted " + key + " from BST.");
                System.out.print("Inorder Traversal after Deletion: ");
                inorderTraversal();
            } else {
                System.out.println("Could not find " + key + " in BST. Unable to delete.");
            }
        }

        private TreeNode deleteRecursion(TreeNode node, int key) {
            if (node == null) {
                return null;
            }

            if (key < node.key) {
                node.left = deleteRecursion(node.left, key);
            } else if (key > node.key) {
                node.right = deleteRecursion(node.right, key);
            } else {
                deleteFlag = true;
                if (node.left == null) {
                    return node.right;
                }
                if (node.right == null) {
                    return node.left;
                }

                TreeNode replacingNode = minimumValueNode(node.right);
                node.key = replacingNode.key;
                node.right = deleteRecursion(node.right, replacingNode.key);
            }

            return node;
        }

        private TreeNode minimumValueNode(TreeNode node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        public void inorderTraversal() {
            inorderRecursion(root);
            System.out.println();
        }

        private void inorderRecursion(TreeNode node) {
            if (node != null) {
                inorderRecursion(node.left);
                System.out.print(node.key + " ");
                inorderRecursion(node.right);
            }
        }

        public boolean search(int key) {
            return searchRecursion(root, key);
        }

        private boolean searchRecursion(TreeNode node, int key) {
            if (node == null) {
                return false;
            }
            if (node.key == key) {
                return true;
            }

            boolean result;
            if (key < node.key) {
                result = searchRecursion(node.left, key);
            } else {
                result = searchRecursion(node.right, key);
            }

            return result;
        }

        public int height() {
            return heightRecursion(root);
        }

        private int heightRecursion(TreeNode node) {
            if (node == null) {
                return 0;
            }

            return 1 + Math.max(heightRecursion(node.left), heightRecursion(node.right));
        }
    }
}
