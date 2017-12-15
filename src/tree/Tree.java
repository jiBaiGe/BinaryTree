package tree;

import java.util.Stack;

//By ye���ο����Լ���linkedlist����д��

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
		
		//�õݹ�ķ�ʽ������ӡ����������
		public void Traverse() {
			System.out.print("ǰ��������ݹ飩: ");
			preOrderTraverse(root);
			System.out.println(Tree);Tree = "";
			System.out.print("����������ݹ飩: ");
			inOrderTraverse(root);
			System.out.println(Tree);Tree = "";
			System.out.print("����������ݹ飩: ");
			postOrderTraverse(root);
			System.out.println(Tree);Tree = "";
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
		
		//���������Լ�д�ĺ�������̫����
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
