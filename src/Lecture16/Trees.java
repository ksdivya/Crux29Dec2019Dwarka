package Lecture16;

import java.util.Scanner;

public class Trees {
	private class Node {
		int data;
		Node left;
		Node right;

		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	private Node root;
	int size;

	Trees() {
		Scanner s = new Scanner(System.in);
		this.root = this.takeInput(s, null, false);
	}

	private Node takeInput(Scanner s, Node parent, boolean isLeftOrRight) {
		if (parent == null) {
			System.out.println("Enter the data for the root node");
		} else {
			if (isLeftOrRight) {
				System.out.println("Enter the data for the left child of " + parent.data);
			} else {
				System.out.println("Enter the data for the rightd child of " + parent.data);
			}
		}

		int data = s.nextInt();
		Node node = new Node(data, null, null);
		this.size++;

		boolean choice = false;
		System.out.println("Do you have left child of " + node.data);
		choice = s.nextBoolean();
		if (choice) {
			node.left = this.takeInput(s, node, true);
		}

		System.out.println("Do you have right child of " + node.data);
		choice = s.nextBoolean();
		if (choice) {
			node.right = this.takeInput(s, node, false);
		}

		return node;
	}

	public void display() {
		this.display(this.root);
	}

	private void display(Node node) {
		if (node.left != null) {
			System.out.print(node.left.data + "=>");
		} else {
			System.out.print("END=>");
		}

		System.out.print(node.data);

		if (node.right != null) {
			System.out.print("<=" + node.right.data);
		} else {
			System.out.print("<=END");
		}

		System.out.println();

		if (node.left != null) {
			this.display(node.left);
		}

		if (node.right != null) {
			this.display(node.right);
		}

	}

	public int size2() {
		return this.size2(this.root);
	}

	private int size2(Node node) {
		if (node == null) {
			return 0;
		}

		int lsize = this.size2(node.left);
		int rsize = this.size2(node.right);
		int mysize = lsize + rsize + 1;
		return mysize;
	}

	public int max() {
		return this.max(this.root);
	}

	private int max(Node node) {
		if (node == null) {
			return Integer.MIN_VALUE;
		}

		int lmax = this.max(node.left);
		int rmax = this.max(node.right);
		int mymax = Math.max(node.data, Math.max(lmax, rmax));
		return mymax;
	}

	public int min() {
		return this.min(this.root);
	}

	private int min(Node node) {
		if (node == null) {
			return Integer.MAX_VALUE;
		}

		int lmin = this.min(node.left);
		int rmin = this.min(node.right);
		int mymin = Math.min(node.data, Math.min(lmin, rmin));
		return mymin;
	}

	public int height() {
		return this.height(this.root);
	}

	private int height(Node node) {
		if (node == null) {
			return -1;
		}

		int lheight = this.height(node.left);
		int rheight = this.height(node.right);

		int myheight = Math.max(lheight, rheight) + 1;
		return myheight;
	}

	public boolean find(int data) {
		return this.find(this.root, data);
	}

	private boolean find(Node node, int data) {
		if (node == null) {
			return false;
		}

		if (node.data == data) {
			return true;
		}

		boolean find_left = this.find(node.left, data);
		if (find_left) {
			return true;
		}

		boolean find_right = this.find(node.right, data);
		if (find_right) {
			return true;
		}

		return false;
	}

	public void mirror() {
		this.mirror(this.root);

	}

	private void mirror(Node node) {
		if (node == null) {
			return;
		}
		Node temp = node.left;
		node.left = node.right;
		node.right = temp;
		this.mirror(node.left);
		this.mirror(node.right);
	}
	
	public int diameter() {
		return this.diameter(this.root);
	}

	private int diameter(Node node) {
		if (node == null) {
			return 0;
		}
		// diametert passes through root
		int f1 = this.height(node.left) + this.height(node.right) + 2;

		int f2 = this.diameter(node.left);

		int f3 = this.diameter(node.right);

		int myans = Math.max(f1, Math.max(f2, f3));
		return myans;
	}
	
	
	public boolean isBst() {
		return this.isBst(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean isBst(Node node, int min, int max) {
		if (node == null) {
			return true;
		}
		if (node.data < min || node.data > max) {
			return false;
		} else if (!this.isBst(node.left, min, node.data)) {
			return false;
		} else if (!this.isBst(node.right, node.data, max)) {
			return false;
		} else {
			return true;
		}
	}


}
