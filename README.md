# Binary-Search-Tree-Visualization

Binary Search Tree (BST) Visualization
This Java Swing application provides a visual representation of a Binary Search Tree (BST) and allows users to interactively add and delete nodes. It also displays the in-order, pre-order, and post-order traversals, as well as the height of the BST.

Features

Node Addition: Add new integer nodes to the BST.
Node Deletion: Delete existing nodes from the BST.
Visual Representation: See the BST structure dynamically update as nodes are added or deleted.
Traversal Display: View the in-order, pre-order, and post-order traversals of the current BST.
Height Display: Observe the current height of the BST.
User-Friendly Interface: Simple and intuitive Swing-based GUI.

Prerequisites

Java Development Kit (JDK) 8 or higher
How to Use
Adding a Node:
Enter an integer value into the text field labeled "Add/Delete".
Click the "Add" button or press 'A' (or 'a') or Enter on your keyboard.
If the value already exists in the BST, a message will appear.

Deleting a Node:

Enter an integer value into the text field labeled "Add/Delete".
Click the "Delete" button or press 'D' (or 'd') on your keyboard.
If the value is not found in the BST, a message will appear.

Viewing BST Information:

The "Inorder", "Preorder", and "Postorder" labels will display the respective traversals of the BST.
The "BST Height" label will show the current height of the BST.

Code Structure

BSTVisualization.java: The main class that sets up the Swing GUI and contains the logic for BST operations (add, delete, traversals, height calculation) and visualization.
Node private static class: Represents a node in the BST, holding the data (as a JLabel for visualization), left and right child references, and Points for drawing connections.
Points private static class: A helper class to store coordinates for drawing lines between parent and child nodes.
Height private static class: A helper class used in the calculateHeight method to return the height of the current node's subtree and its left and right children.
paint(Graphics g): Overridden method responsible for drawing the connections between nodes.
initialize(): Sets up all the Swing components and their layouts.
actionPerformed(ActionEvent evt): Handles button click events for Add and Delete.
keyTyped(KeyEvent evt): Handles keyboard input for adding and deleting nodes.
add(int info): Implements the logic for inserting a new node into the BST and updates its visual position.
delete(int data): Implements the logic for deleting a node from the BST and re-arranges the tree visually.
setInfo(): Updates the display labels for in-order, pre-order, post-order traversals and BST height.
getWidth(Node node): Calculates the appropriate width for a node's display label based on its content.
inorder(Node root): Performs an in-order traversal of the BST and returns the result as a string.
preorder(Node root): Performs a pre-order traversal of the BST and returns the result as a string.
postorder(Node root): Performs a post-order traversal of the BST and returns the result as a string.
calculateHeight(Node root): Recursively calculates the height of the BST.
reArrangeNode(Node node, Node pre, int X): Recursively re-positions nodes after an insertion or deletion to maintain a visually coherent tree structure.
main(String arg[]): The entry point of the application, which creates a BSTVisualization object and adds some initial nodes.

Acknowledgments

This project was developed to visualize Binary Search Tree operations.
