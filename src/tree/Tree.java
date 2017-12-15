package tree;

import java.util.Stack;

//By ye，参考我自己的linkedlist代码写成

   class TreeNode {//  Definition for a binary tree node.
     int val;
      TreeNode left;
      TreeNode right;
      
      TreeNode(int x) { 
    	  val = x;
    	  }
      
      //获取节点值的函数
      public String print() {
    	  int x = val;
    	  return x+"";
      }
      
  
  }

public class Tree {
	
		TreeNode root ;
		
		private String Tree="";   //全局变量的方式我自己觉得有点傻，这是我想到的能够在递归函数外存储节点返回值并将整颗树的节点整体全部打印的方法
		
		public Tree(){   // 构造一棵树
			this.root = null;
			
		}
		
		public void insertRoot(int x) { //手动插入根节点，参考了我自己写的linkedlist的实现方法。
			TreeNode node = new TreeNode(x);
			root = node;
		}
		
		public void insert(int x) {   //二叉搜索树的有序插入
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
		
		//和插入的方法类似
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
		
		public void disTree(String s) {//能将整棵树的值打印出来的方法，自己想的，感觉很蠢
			Tree = Tree + s;
		}
		
		//用递归的方式遍历打印二叉搜索树
		public void Traverse() {
			System.out.print("前序遍历（递归）: ");
			preOrderTraverse(root);
			System.out.println(Tree);Tree = "";
			System.out.print("中序遍历（递归）: ");
			inOrderTraverse(root);
			System.out.println(Tree);Tree = "";
			System.out.print("后序遍历（递归）: ");
			postOrderTraverse(root);
			System.out.println(Tree);Tree = "";
		}
		
		//采用非递归的方式遍历
		public void TraverserByStack() {
			System.out.print("前序遍历：");
			preOrderTraverseByStack();
			System.out.print("中序遍历：");
			inOrderTraverseByStack();
			System.out.print("后序遍历：");
			postOrderTraverseByStack();;
			
		}
		
		//用递归的方式遍历整个二叉树的3种方式
		public void preOrderTraverse(TreeNode node) {
			
			if(node != null) {
				disTree(node.print());;
				preOrderTraverse(node.left);
				preOrderTraverse(node.right);
			}
			
		}
		
		public void inOrderTraverse(TreeNode node) {
				
				if(node != null) {
					inOrderTraverse(node.left);
					disTree(node.print());;
					inOrderTraverse(node.right);
				}
				
			}
		
		public void postOrderTraverse(TreeNode node) {
			
			if(node != null) {		
				postOrderTraverse(node.left);
				postOrderTraverse(node.right);
				disTree(node.print());;
			}
			
		}
		
		
		
		//采用Stack方式完成遍历，参考于互联网热心网友
		public void preOrderTraverseByStack() {
			String s = "";
			Stack<TreeNode> stack = new Stack<TreeNode>();
			TreeNode current = root;
			while ( current != null || !stack.isEmpty()) {
				while (current != null) {
				stack.push(current);
				s += current.print();
				current = current.left;				
				}
			
			if (!stack.isEmpty()) {
				current = stack.pop();
				current = current.right;
			}
			}
			System.out.println(s);
		}
		
		public void inOrderTraverseByStack() {
			String s ="";
			Stack<TreeNode> stack = new Stack<TreeNode>();
			TreeNode current = root;
			while ( current != null || !stack.isEmpty() ) {
				while (current != null) {
				stack.push(current);
				current = current.left;
				}
				if(!stack.isEmpty()) {
					current = stack.pop();
					s = s + current.print();
					
					current = current.right;
				}
			}
			System.out.println(s);
		}
		
		//理解中序后自己写的后续，不太容易
		public void postOrderTraverseByStack() {
			String s = "";
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
					s = s + current.print();
					if(stack.isEmpty()) {break;}
					current = stack.pop();
					stack.push(current);
					current = current.right;
				}
					
			}
			System.out.println(s);
		}
		
		//判断2个二叉树是否为完全相同的两个二叉搜索树，采用递归方式，参考于Leetcode。com网友
		  public boolean isSameTree(TreeNode p, TreeNode q) {
		       if (p == null && q == null){
		           return true;
		       }
		       if (p == null && q != null){
		           return false;
		       }
		        if(p != null && q == null){
		            return false;
		        }
		        if(p.val != q.val){
		            return false;
		        }
		        
		        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
		            
		           
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
//			myTree.inOrderTraverseByStack();
			Tree yourTree = new Tree();
			yourTree.insertRoot(5);
			yourTree.insert(3);
			yourTree.insert(8);
			yourTree.insert(2);
			yourTree.insert(4);
			yourTree.insert(7);
			yourTree.insert(9);
			yourTree.insert(6);
			yourTree.TraverserByStack();
			System.out.println(myTree.isSameTree(myTree.root, yourTree.root));
			
			
	}

}
