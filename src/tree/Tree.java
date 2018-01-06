package tree;

import java.util.LinkedList;
import java.util.Stack;

/*By ye���ο����Լ���linkedlist�����Լ��̲�д��
 * ������1����������
 *     2.������ڵ�    : void insertRoot(int x); 
 *     3.����ڵ� : void insertRoot(int x);
 *     4.�����ڵ�: void findx();
 *     5.���ֱ�����ʽ(ǰ�����򣬺���)�ĵݹ�ʵ�֡�
 *     6�����ֱ�����ʽ(ǰ�����򣬺���)�ķǵݹ�ʵ��(stack)��
 *     7.�������Ĳ�α���(����Linkedlistʵ�ֶ���) : void levelOrderTraverse();
 *     8.�ж�2�ö������Ƿ���ͬ :  boolean isSameTree(TreeNode p, TreeNode q);
 *     9.��������߶� : int maxDepth(TreeNode node);
 *     10.���������Ķ�����������任ΪԴ�������ľ���public void Mirror(TreeNode root);
 *     11.�������ö�����A��B���ж�B�ǲ���A���ӽṹ����ps������Լ��������������һ�������ӽṹ :HasSubtree(TreeNode root1,TreeNode root2);
 */

   class TreeNode {//  Definition for a binary tree node.
     int val;
      TreeNode left;
      TreeNode right;
      
      TreeNode(int x) { 
    	  val = x;
    	  }
      
      //��ȡ�ڵ�ֵ�ĺ���
      public String print() {
    	  int x = val;
    	  return x+"";
      }
      
  
  }

   public class Tree {
	
		TreeNode root ;
		
		private String Tree="";   //ȫ�ֱ����ķ�ʽ���Լ������е�ɵ���������뵽���ܹ��ڵݹ麯����洢�ڵ㷵��ֵ�����������Ľڵ�����ȫ����ӡ�ķ���
		
		public Tree(){   // ����һ����
			this.root = null;
			
		}
		
		public void insertRoot(int x) { //�ֶ�������ڵ㣬�ο������Լ�д��linkedlist��ʵ�ַ�����
			TreeNode node = new TreeNode(x);
			root = node;
		}
		
		public void insert(int x) {   //�������������������
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
		
		//�Ͳ���ķ�������
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
		
		public void disTree(String s) {//�ܽ���������ֵ��ӡ�����ķ������Լ���ģ��о��ܴ�
			Tree = Tree + s;
		}
		
		public void printTree() {
			System.out.println(Tree);Tree = "";
		}
		
		//�õݹ�ķ�ʽ������ӡ����������
		public void Traverse() {
			System.out.print("ǰ��������ݹ飩: ");
			preOrderTraverse(root);
			printTree();
			System.out.print("����������ݹ飩: ");
			inOrderTraverse(root);
			printTree();
			System.out.print("����������ݹ飩: ");
			postOrderTraverse(root);
			printTree();
		}
		
		//���÷ǵݹ�ķ�ʽ����
		public void TraverserByStack() {
			System.out.print("ǰ�������");
			preOrderTraverseByStack();
			System.out.print("���������");
			inOrderTraverseByStack();
			System.out.print("���������");
			postOrderTraverseByStack();;
			
		}
		
		//�õݹ�ķ�ʽ����������������3�ַ�ʽ
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
		
		
		
		//����Stack��ʽ��ɱ������ο��ڻ�������������
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
		
		//���������Լ�д�ĺ�������һ�γ��ԣ�˼·��������������û�����Ͼ���
		public void postOrderTraverseByStack() {
			String s = "";
			TreeNode current = root;
			TreeNode previous = null;
			Stack<TreeNode> stack = new Stack<TreeNode>();
			while (current != null || !stack.isEmpty()) {
		     while(current!=null ) {
		    	if(previous!=null) {
		    		
		    		//�ж�����Ҫ���ʵĽڵ��Ƿ��Ѿ����ʹ���������ʹ������ڷ��ʣ�break����break֮ǰ����
		    		//previous����Ϊstack����Ԫ�ء�
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
		
		//�������Ĳ�α���������Linkedkistʵ�ֶ��С�
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
			
			System.out.println("���������"+s);
		}
		
		//�ж�2���������Ƿ�Ϊ��ȫ��ͬ���������������������õݹ鷽ʽ���ο���Leetcode��com����
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

		//��������߶ȣ�˼·�������ĺ����������ݹ飩���޲ο���
	     public int maxDepth(TreeNode node) {
	        if(node == null) {
	        	return 0;
	        }
	        	int rightdepth = maxDepth(node.left);
	        	int leftdepth = maxDepth(node.right);
	        	return (rightdepth > leftdepth)?(rightdepth+1):(leftdepth+1);
	       }
	     
	     //���������Ķ�����������任ΪԴ�������ľ���
	     public void Mirror(TreeNode root) {	    	 
	         Stack<TreeNode> stack = new Stack<TreeNode>();
	         TreeNode temp = null;
	         TreeNode current = root;
	         //����ջ��������������
	         while (current != null || !stack.isEmpty() ) {
	        	 while (current != null) {
	        		 //�����ǰ�ڵ����Ҷ��ӽԲ�Ϊnull���򽻻��������ڵ㡣
	        		  if(current.left != null && current.right != null) { 
		        			 temp = current.left;
		        			 current.left = current.right;
		        			 current.right = temp;
		        		 }else  		//���������û���Ҷ���
		        		   if(current.left == null && current.right != null) {
		        			  current.left = current.right;
		        			  current.right = null;
		        		  }else			//�����Ҷ���û�������
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
	     
	     //�������ö�����A��B���ж�B�ǲ���A���ӽṹ����ps������Լ��������������һ�������ӽṹ)
	     //�����뵽��˼·������A����ֱ��������ĳһ����B����ȵĽڵ㣬��ʼÿ��AB��һ�������ֱ��B��������
	     //���߽�AB���ֱ��������ֱ�Ӵ����ַ������ж��Ƿ�Ϊ���ַ�����
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
			System.out.println("�������Ƿ���ͬ? :"+myTree.isSameTree(myTree.root, yourTree.root));
			
			System.out.println("�������߶�Ϊ "+myTree.maxDepth(myTree.root));
			myTree.levelOrderTraversal();
			myTree.Mirror(myTree.root);
			myTree.levelOrderTraversal();
			
	}

}
