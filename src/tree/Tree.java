package tree;

import java.util.LinkedList;
import java.util.Stack;

/*By ye，参考我自己的linkedlist代码以及教材写成
 * 包含：1。创建树。
 *     2.插入根节点    : void insertRoot(int x); 
 *     3.插入节点 : void insertRoot(int x);
 *     4.搜索节点: void findx();
 *     5.三种遍历方式(前序，中序，后续)的递归实现。
 *     6。三种遍历方式(前序，中序，后续)的非递归实现(stack)。
 *     7.二叉树的层次遍历(利用Linkedlist实现队列) : void levelOrderTraverse();
 *     8.判断2棵二叉树是否相同 :  boolean isSameTree(TreeNode p, TreeNode q);
 *     9.求熟的最大高度 : int maxDepth(TreeNode node);
 *     10.操作给定的二叉树，将其变换为源二叉树的镜像。public void Mirror(TreeNode root);
 *     11.输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构 :HasSubtree(TreeNode root1,TreeNode root2);
 */

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
		
		public void printTree() {
			System.out.println(Tree);Tree = "";
		}
		
		//用递归的方式遍历打印二叉搜索树
		public void Traverse() {
			System.out.print("前序遍历（递归）: ");
			preOrderTraverse(root);
			printTree();
			System.out.print("中序遍历（递归）: ");
			inOrderTraverse(root);
			printTree();
			System.out.print("后序遍历（递归）: ");
			postOrderTraverse(root);
			printTree();
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
		
		//理解中序后自己写的后续，第一次尝试，思路不够清晰，代码没有网上精简
		public void postOrderTraverseByStack() {
			String s = "";
			TreeNode current = root;
			TreeNode previous = null;
			Stack<TreeNode> stack = new Stack<TreeNode>();
			while (current != null || !stack.isEmpty()) {
		     while(current!=null ) {
		    	if(previous!=null) {
		    		
		    		//判断现在要访问的节点是否已经访问过，如果访问过，则不在访问，break，在break之前，将
		    		//previous设置为stack顶端元素。
		    		if(current.val == previous.val) {  
		    		previous = stack.peek();				
		    		break;}
		    		}
		    	
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
					current = stack.peek();
					current = current.right;
				}
					
			 }
			System.out.println(s);
		  }
		
		//二叉树的层次遍历，利用Linkedkist实现队列。
		public void levelOrderTraversal() {
			String s = "";
			TreeNode current = root;
			LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
			if(current == null) {
				return;
			}
			queue.offer(current);
			while( !queue.isEmpty() ) {
				current = queue.poll();
				s  += current.val;
				if(current.left != null) {
					queue.offer(current.left);
				}
				if(current.right != null) {
					queue.offer(current.right);
				}
			}
			
			System.out.println("层序遍历："+s);
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

		//求熟的最大高度，思路来自树的后续遍历（递归），无参考。
	     public int maxDepth(TreeNode node) {
	        if(node == null) {
	        	return 0;
	        }
	        	int rightdepth = maxDepth(node.left);
	        	int leftdepth = maxDepth(node.right);
	        	return (rightdepth > leftdepth)?(rightdepth+1):(leftdepth+1);
	       }
	     
	     //操作给定的二叉树，将其变换为源二叉树的镜像
	     public void Mirror(TreeNode root) {	    	 
	         Stack<TreeNode> stack = new Stack<TreeNode>();
	         TreeNode temp = null;
	         TreeNode current = root;
	         //利用栈遍历整个二叉树
	         while (current != null || !stack.isEmpty() ) {
	        	 while (current != null) {
	        		 //如果当前节点左右儿子皆不为null，则交换这两个节点。
	        		  if(current.left != null && current.right != null) { 
		        			 temp = current.left;
		        			 current.left = current.right;
		        			 current.right = temp;
		        		 }else  		//若有左儿子没有右儿子
		        		   if(current.left == null && current.right != null) {
		        			  current.left = current.right;
		        			  current.right = null;
		        		  }else			//若有右儿子没有左儿子
			        		  if(current.left != null && current.right == null) {
			        			  current.right = current.left;
			        			  current.left = null;
			        		  }
		        	 stack.push(current);
		        	 current = current.left;
	        	 }
		        	 if (!stack.isEmpty()) {
		        		 current = stack.pop();
		        		 current = current.right;
		        	 }
	         }
	     }
	     
	     //输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构)
	     //首先想到的思路，遍历A树，直到遍历到某一个和B树相等的节点，开始每轮AB树一起遍历，直到B树结束。
	     //或者将AB树分别遍历，并直接存入字符串，判断是否为子字符串；
	     public boolean HasSubtree(TreeNode root1,TreeNode root2) {
	    	 Stack<TreeNode> stack1 = new Stack<TreeNode>();
	    	 Stack<TreeNode> stack2 = new Stack<TreeNode>();
	    	 boolean match = false;
	    	 TreeNode current1 = root1;
	    	 TreeNode current2 = root2;
	    	 while (root1 !=null  || !stack1.isEmpty()) {
	    		 while (root1 != null) {
	    			 stack1.push(current1);
	    			 if(current1.val == current2.val) {match = true;}
	    			 if(match && current2 != null) {
	    				 
	    				 stack2.push(current2);
	    				 current2 = current2.left;
	    			 }
	    			 current1 = current1.left;
	    		 }
	    		 if(!stack1.isEmpty()) {
	    			 current1 = stack1.pop();
	    			 if(match &&!stack2.isEmpty()) {
	    				 current2 = stack2.pop();
	    				 if(current1.val != current2.val) {return false;}
	    				 current2 = current2.right;
	    			 }
	    			 current1 = current1.right;
	    			 
	    		 }
	    	 }
	    	 
	         return true;
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
			System.out.println("两棵树是否相同? :"+myTree.isSameTree(myTree.root, yourTree.root));
			
			System.out.println("树的最大高度为 "+myTree.maxDepth(myTree.root));
			myTree.levelOrderTraversal();
			myTree.Mirror(myTree.root);
			myTree.levelOrderTraversal();
			
	}

}
