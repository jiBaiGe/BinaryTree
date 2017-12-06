package tree;

import java.util.Stack;

//By ye

   class TreeNode {//  Definition for a binary tree node.
     int val;
      TreeNode left;
      TreeNode right;
      
      TreeNode(int x) { 
    	  val = x;
    	  }
      
      public void print() {
    	  int x = val;
    	  System.out.print(x);
      }
      
  }

public class Tree {
	
		TreeNode root ;
		
		public Tree(){   // 构造一棵树
			this.root = null;
			
		}
		
		public void insertRoot(int x) {
			TreeNode node = new TreeNode(x);
			root = node;
		}
		
		public void insert(int x) {
			if(root == null) {return;}
			TreeNode node = new TreeNode(x);
			TreeNode current = root;
			TreeNode previous = null;
			while(true) {
				if (x < current.val) {
					previous = current;
					current = current.left;
					if (current == null) {
						previous.left = node;
						break;
					}
				}else if(x > current.val) {
					previous = current;
					current = current.right;
					if (current == null) {
						previous.right = node;
						break;
					}
				}else break;
			}
		}
		
		public void findx(int x) {
			TreeNode current = root;
			while(true) {
			if (x < current.val) {
				current = current.left;
				if ( x == current.val) {
					current.print();
					break;
				}
			}else if(x > current.val) {
				current = current.right;
				if (x == current.val) {
					current.print();
					break;
				}
			}else current.print(); //x is root.val itself
		 }
		}
		
		public void Traverse() {
			System.out.print("前序遍历（递归）: ");
			preOrderTraverse(root);
			System.out.println();
			System.out.print("中序遍历（递归）: ");
			inOrderTraverse(root);
			System.out.println();
			System.out.print("后序遍历（递归）: ");
			postOrderTraverse(root);
			System.out.println();
		}
		
		public void TraverserByStack() {
			System.out.print("前序遍历：");
		}
		
		//用递归的方式遍历整个二叉树
		public void preOrderTraverse(TreeNode node) {
			
			if(node != null) {
				node.print();
				preOrderTraverse(node.left);
				preOrderTraverse(node.right);
			}
			
		}
		
		public void inOrderTraverse(TreeNode node) {
				
				if(node != null) {
					inOrderTraverse(node.left);
					node.print();
					inOrderTraverse(node.right);
				}
				
			}
		
		public void postOrderTraverse(TreeNode node) {
			
			if(node != null) {		
				postOrderTraverse(node.left);
				postOrderTraverse(node.right);
				node.print();
			}
			
		}
		
		public void inOrderTraverseByStack() {
			Stack<TreeNode> stack = new Stack<TreeNode>();
			TreeNode current = root;
			while ( current != null || !stack.isEmpty() ) {
				while (current != null) {
				stack.push(current);
				current = current.left;
				}
				if(!stack.isEmpty()) {
					current = stack.pop();
					current.print();
					current = current.right;
				}
			}
		}
		
		public void postOrderTraverseByStack() {
			TreeNode current = root;
			TreeNode previous = null;
			Stack<TreeNode> stack = new Stack<TreeNode>();
			while (current != null || !stack.isEmpty()) {
		     while(current!=null ) {
		    	if(previous!=null) { if(current.val == previous.val) {
		    		previous = stack.pop();
					stack.push(previous);
		    		break;}}
				while(current != null) {
					stack.push(current);
					previous = current;
					current = current.left;
				}
				
				current = previous.right;
				}
				if(!stack.isEmpty()) {
					current = stack.pop();
					current.print();
					if(stack.isEmpty()) {break;}
					current = stack.pop();
					stack.push(current);
					current = current.right;
				}
					
			}
		}

		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Tree myTree = new Tree();
			myTree.insertRoot(5);
			myTree.insert(3);
			myTree.insert(8);
			myTree.insert(2);
			myTree.insert(4);
			myTree.insert(7);
			myTree.insert(9);
//			myTree.insert(2);
			
			myTree.Traverse();
			myTree.postOrderTraverseByStack();
			
			
	}

}
