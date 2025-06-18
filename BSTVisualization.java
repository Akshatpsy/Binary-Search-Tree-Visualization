import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BSTVisualization extends JFrame implements ActionListener, KeyListener {

	private Node root;


	private JPanel topPanel, treePanel, infoPanel;
	private JPanel topLeftPanel, topRightPanel;
	private JButton btnAdd, btnDelete;
	private JTextField tf;
	private int X = 300, Y = 75;
	private Graphics2D g2;
	private Rectangle size;
	private JLabel labelInorder, labelPreorder, labelPostorder, labelHeight;
	private JLabel ansInorder, ansPreorder, ansPostorder, ansHeight;
	private FontMetrics fontMatrix;

	
	private static class Node {
		static int TEXT_WIDTH = 40;
		static int TEXT_HEIGHT = 40;

		JLabel data;
		Node left;
		Node right;
		Points p;

		Node(int info) {
			data = new JLabel(info + "", SwingConstants.CENTER);
			data.setFont(new Font("Arial", Font.BOLD, 20));
			data.setBorder(BorderFactory.createLineBorder(Color.black));
			data.setOpaque(true);
			data.setBackground(Color.green);
			p = null;
		}
	}

	//Points structure
	private static class Points {
		int x1 = 0, x2 = 0, y2 = 0, y1 = 0;

		Points(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.x2 = x2;
			this.y2 = y2;
			this.y1 = y1;
		}

		public String toString() {
			return "x1 = " + x1 + ", y1 = " + y1 + ", x2 = " + x2 + ", y2 = " + y2;
		}
	}

	
	private static class Height {
		int root, left, right;

		Height() {
			this.root = 0;
			this.left = 0;
			this.right = 0;
		}

		Height(int left, int right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			return Integer.toString(this.root);
		}
	}

	public void paint(Graphics g) {
		super.paintComponents(g);

		g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3.0f));

		Stack<Node> s = new Stack<>();
		Node curr = root;
		Points pts;
		int offset = topPanel.getBounds().height;
		while (!s.isEmpty() || curr != null) {
			while (curr != null) {
				s.push(curr);
				curr = curr.left;
			}
			if (!s.isEmpty())
				curr = s.pop();
			pts = curr.p;
			g2.drawLine(pts.x1 + 7, pts.y1 + 30 + offset, pts.x2 + 3, pts.y2 + 10 + offset);
			curr = curr.right;
		}

		

	}

	public BSTVisualization() {
	
		initialize();
	}

	private void initialize() {

		
		setSize(1200, 700); 

		size = getBounds();
		X = size.width / 2;

		topPanel = new JPanel(new BorderLayout());
		Rectangle top = topPanel.getBounds();

		topLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		topPanel.add(topLeftPanel, BorderLayout.WEST);

		topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		topPanel.add(topRightPanel, BorderLayout.EAST);

		treePanel = new JPanel(null);
		treePanel.setPreferredSize(new Dimension(size.width, size.height - 300));

		infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.setPreferredSize(new Dimension(size.width, 200));

		
		labelHeight = new JLabel("BST Height : ");
		labelHeight.setFont(new Font("Calibri", Font.BOLD, 20));
		topLeftPanel.add(labelHeight);

		
		ansHeight = new JLabel("0");
		ansHeight.setFont(new Font("Calibri", Font.BOLD, 20));
		ansHeight.setPreferredSize(new Dimension(50, 30));
		topLeftPanel.add(ansHeight);

		
		tf = new JTextField("");
		tf.setFont(new Font("Arial", Font.BOLD, 20));
		tf.setPreferredSize(new Dimension(150, 30));
		tf.addKeyListener(this);
		topRightPanel.add(tf);

		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Arial", Font.BOLD, 20));
		
		btnAdd.addActionListener(this);
		topRightPanel.add(btnAdd);

		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Arial", Font.BOLD, 20));
		
		btnDelete.addActionListener(this);
		topRightPanel.add(btnDelete);

		
		labelInorder = new JLabel("Inorder :");
		labelInorder.setFont(new Font("Times New Roman", Font.BOLD, 20));
		infoPanel.add(labelInorder);

		infoPanel.add(Box.createRigidArea(new Dimension(7, 5)));

		
		ansInorder = new JLabel("BST is empty.");
		ansInorder.setFont(new Font("Arial", Font.PLAIN, 18));
		infoPanel.add(ansInorder);

		infoPanel.add(Box.createRigidArea(new Dimension(7, 15)));

		
		labelPreorder = new JLabel("Preorder :");
		labelPreorder.setFont(new Font("Times New Roman", Font.BOLD, 20));
		infoPanel.add(labelPreorder);

		infoPanel.add(Box.createRigidArea(new Dimension(7, 5)));

		
		ansPreorder = new JLabel("BST is empty.");
		ansPreorder.setFont(new Font("Arial", Font.PLAIN, 18));
		infoPanel.add(ansPreorder);

		infoPanel.add(Box.createRigidArea(new Dimension(7, 15)));

		
		labelPostorder = new JLabel("Postorder :");
		labelPostorder.setFont(new Font("Times New Roman", Font.BOLD, 20));
		infoPanel.add(labelPostorder);

		
		ansPostorder = new JLabel("BST is empty.");
		ansPostorder.setFont(new Font("Arial", Font.PLAIN, 18));
		infoPanel.add(ansPostorder);

		tf.requestFocusInWindow();

		add(topPanel, BorderLayout.NORTH);
		add(treePanel, BorderLayout.CENTER);
		add(infoPanel, BorderLayout.SOUTH);

		setTitle("Binary Search Tree Visualization"); 
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (tf.isEnabled()) {
			try {
				int data = Integer.parseInt(tf.getText());
				if (evt.getSource() == btnAdd) {
					add(data);
				} else {
					delete(data);
				}
				tf.setText("");
				tf.requestFocusInWindow();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Please Enter Integer.");
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent evt) {
		char c = evt.getKeyChar();
		if (!tf.isEnabled()) {
			return;
		} else if (c == 'a' || c == 'A' || c == '\n') {
			try {
				String data = tf.getText();
				evt.consume(); 
				if (!data.isEmpty()) {
					add(Integer.parseInt(data));
				} else {
					throw new Exception();
				}
				tf.requestFocusInWindow();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Please Enter Integer.");
			}
			tf.setText("");
		} else if (c == 'd' || c == 'D') {
			try {
				String data = tf.getText();
				evt.consume(); 
				if (!data.isEmpty()) {
					delete(Integer.parseInt(data));
				}
				tf.requestFocusInWindow();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Please Enter Integer.");
			}
			tf.setText("");
		} else if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z')
			evt.consume();
	}

	@Override
	public void keyPressed(KeyEvent evt) {
	}

	@Override
	public void keyReleased(KeyEvent evt) {
	}

	
	public void add(int info) {
		Node newNode = new Node(info);
		int width = getWidth(newNode);

		if (root == null) {
			root = newNode;
			newNode.data.setBounds(treePanel.getBounds().width / 2, 10, width, 40);
			newNode.p = new Points(0, 0, 0, 0);
		} else {
			Node curr = root, pre = root;
			int currData;
			X = treePanel.getBounds().width / 2;
			while (curr != null) {
				pre = curr;
				currData = Integer.parseInt(curr.data.getText());
				if (info == currData) {
					JOptionPane.showMessageDialog(null, info + " is already exist.");
					return;
				} else if (currData > info) {
					curr = curr.left;
				} else {
					curr = curr.right;
				}
				X /= 2;
			}

			currData = Integer.parseInt(pre.data.getText());
			int x = pre.data.getX();
			int y = pre.data.getY();
			Dimension preDimension = pre.data.getSize();
			Dimension currDimension = new Dimension(width, Node.TEXT_HEIGHT);

			if (currData > info) {
				pre.left = newNode;
				newNode.data.setBounds(x - X, y + Y, width, 40);
				
				newNode.p = new Points(x, y + preDimension.height / 2, x - X + currDimension.width / 2, y + Y + currDimension.height / 2);
			} else {
				pre.right = newNode;
				newNode.data.setBounds(x + X, y + Y, width, 40);
				
				newNode.p = new Points(x + preDimension.width, y + preDimension.height / 2, x + X + currDimension.width / 2, y + Y + currDimension.height / 2);
			}
		}

		
		setInfo();

		
		treePanel.add(newNode.data);
		treePanel.validate();
		treePanel.repaint();

		validate();
		repaint();
	}

	
	public void delete(int data) {
		if (root == null) {
			JOptionPane.showMessageDialog(null, "BST is empty.");
		} else {
			Node curr = root, pre = root;

			while (curr != null) {
				int info = Integer.parseInt(curr.data.getText());
				if (info == data) {
					break;
				} else if (info > data) {
					pre = curr;
					curr = curr.left;
				} else {
					pre = curr;
					curr = curr.right;
				}
			}

			if (curr == null) {
				JOptionPane.showMessageDialog(null, data + " is not available.");
				return;
			} else if (curr.left == null || curr.right == null) { 

				treePanel.remove(curr.data);
				treePanel.validate();
				treePanel.repaint();

				validate();
				repaint();

				if (curr != root) {
					Node address = curr.left != null ? curr.left : curr.right;
					
					int preData = Integer.parseInt(pre.data.getText());
					int currData = Integer.parseInt(curr.data.getText());
					if (currData > preData) {
						pre.right = address;
					} else {
						pre.left = address;
					}
				} else {
					if (curr.left != null) {
						root = curr.left;
					} else {
						root = curr.right;
					}
				}

			} else { 

				treePanel.remove(curr.data);
				treePanel.validate();
				treePanel.repaint();

				validate();
				repaint();

				
				 
				Node nextRoot = null, preRoot = curr;
				Height height = calculateHeight(curr);

				
				if (height.left > height.right) {
					nextRoot = curr.left;
					while (nextRoot.right != null) {
						preRoot = nextRoot;
						nextRoot = nextRoot.right;
					}

					if (preRoot != curr) {
						preRoot.right = nextRoot.left;
					} else {
						preRoot.left = nextRoot.left;
					}
				} else { 
					nextRoot = curr.right;
					while (nextRoot.left != null) {
						preRoot = nextRoot;
						nextRoot = nextRoot.left;
					}

					if (preRoot != curr) {
						preRoot.left = nextRoot.right;
					} else {
						preRoot.right = nextRoot.right;
					}
				}

				curr.data = nextRoot.data;
			}
			reArrangeNode(root, root, treePanel.getBounds().width / 2);
		}

		
		setInfo();
	}

	
	private void setInfo() {
		Height height = calculateHeight(root);

		if (height.root == 0) {
			ansInorder.setText("BST is empty.");
			ansPostorder.setText("BST is empty.");
			ansPreorder.setText("BST is empty.");
		} else {
			ansInorder.setText(inorder(root));
			ansPostorder.setText(postorder(root));
			ansPreorder.setText(preorder(root));
		}

		ansHeight.setText(height.root + "");
	}

	private int getWidth(Node node) {
		fontMatrix = getFontMetrics(node.data.getFont());
		int width = fontMatrix.stringWidth(node.data.getText());
		return width < Node.TEXT_WIDTH ? Node.TEXT_WIDTH : (width + 5);
	}

	//Inorder logic
	private String inorder(Node root) {
		if (root == null)
			return "";

		return inorder(root.left) + root.data.getText() + " " + inorder(root.right);
	}

	//Preorder logic
	public String preorder(Node root) {
		if (root == null)
			return "";

		return root.data.getText() + " " + preorder(root.left) + preorder(root.right);
	}

	//Postorder logic
	public String postorder(Node root) {
		if (root == null)
			return "";

		return postorder(root.left) + postorder(root.right) + root.data.getText() + " ";
	}

	
	private Height calculateHeight(Node root) {
		if (root == null) {
			return new Height();
		}
		Height leftChild = calculateHeight(root.left);
		Height rightChild = calculateHeight(root.right);
		Height current = new Height(leftChild.root, rightChild.root);
		current.root = 1 + Math.max(leftChild.root, rightChild.root);
		return current;
	}

	
	private void reArrangeNode(Node node, Node pre, int X) {
		if (node == null)
			return;

		int width = getWidth(node);

		if (root == node) {
			node.data.setBounds(X, 10, width, Node.TEXT_HEIGHT);
		} else {
			int x = pre.data.getX();
			int y = pre.data.getY();
			Dimension preDimension = pre.data.getSize();
			Dimension currDimension = new Dimension(width, Node.TEXT_HEIGHT);

			int preData = Integer.parseInt(pre.data.getText());
			int nodeData = Integer.parseInt(node.data.getText());
			if (nodeData < preData) {
				node.data.setBounds(x - X, y + Y, width, Node.TEXT_HEIGHT);
				node.p = new Points(x, y + preDimension.height / 2, x - X + currDimension.width / 2, y + Y + currDimension.height / 2);
			} else {
				node.data.setBounds(x + X, y + Y, width, Node.TEXT_HEIGHT);
				
				node.p = new Points(x + preDimension.width, y + preDimension.height / 2, x + X + currDimension.width / 2, y + Y + currDimension.height / 2);
			}
		}

		reArrangeNode(node.left, node, X / 2);
		reArrangeNode(node.right, node, X / 2);
	}

	public static void main(String arg[]) {
		BSTVisualization bst = new BSTVisualization();

		bst.add(600);
		bst.add(1200);
		bst.add(1350);
		bst.add(2200);
		bst.add(2750);
		bst.add(1000);
		bst.add(3200);
		bst.add(4340);
	}
}