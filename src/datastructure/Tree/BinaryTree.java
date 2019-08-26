package datastructure.Tree;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTree {
	public Node root=null;
	//
	public int size;
	//
	public int[]data;
	/**
	 * 结点类
	 */
	class Node {
		//
		public char value;
		//
		public Node left;
		//
		public Node right;

		//
		public Node(char value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}
	/**
	 * 建立已广义表的形式存储的二叉树
	 * 遍历二叉树
	 * @param exp
	 * @return
	 */
	public Node createTree(String exp) {
	Node[] nodes = new Node[3];
	Node b, p = null;
	int top = -1, k = 0, j = 0;
	char[] exps = exp.toCharArray();
	char data = exps[j];
	b = null;
	while (j < exps.length - 1) {
		switch (data) {
			case '(':
				top++;
				nodes[top] = p;
				k = 1;
				break;
			case ')':
				top--;
				break;
			case ',':
				k = 2;
				break;
			default:
				p = new Node(data);
				if (root == null) {
					root = p;
				} else {
					switch (k) {
						case 1:
							nodes[top].left=p;
							break;
						case 2:
							nodes[top].right=p;
							break;
					}
				}
		}
		j++;
		data = exps[j];
	}
	return root;
}

	/**
	 *前序遍历
	 */
	 public void preOrder(Node node){
	 	if(node!=null){
	 		System.out.print(node.value+" ");
	 		preOrder(node.left);
	 		preOrder(node.right);
		}
	 }
	/**
	 * 非递归前序遍历
	 */
	public List<Character> preorderTraversal(Node node){
		List<Character>resultList=new ArrayList<>();
		Stack<Node>nodeStack=new Stack<>();
		if(node==null){
			return resultList;
		}
		nodeStack.push(node);
		while (!nodeStack.empty()){
			Node tempNode=nodeStack.pop();
			if(tempNode!=null){
				resultList.add(tempNode.value);
				nodeStack.push(tempNode.right);
				nodeStack.push(tempNode.left);
			}
		}
		return resultList;
	}
	/**
	 *中序遍历
	 */
	public void inOrder(Node node){
         if(node!=null){
         	inOrder(node.left);
         	System.out.print(node.value+" ");
         	inOrder(node.right);
		 }
	}

	/**
	 * 非递归中序遍历
	 * 逐步将根节点的左子节点入栈，然后处理右子节点
	 * @param node
	 * @return
	 */
	public List<Character> inorderTraversal(Node node){
		List<Character>resultList=new ArrayList<>();
		Node p=node;
		Stack<Node>nodeStack=new Stack<>();
		if(p==null){
			return resultList;
		}
		if(p==null||!nodeStack.empty()){
			if (p!=null){
				nodeStack.push(p);
				p=p.left;
			}
			p=nodeStack.pop();
			resultList.add(p.value);
			p=p.right;
		}
		return resultList;
	}
	/**
	 * 后序遍历
	 */
	public void postOrder(Node node){
          if(node!=null){
          	postOrder(node.left);
          	postOrder(node.right);
          	System.out.print(node.value);
		  }
	}

	/**
	 * 二叉树的广度优先遍历
	 * @param root
	 * 辅助数据结构queen
	 */
	public void LevelOrder(Node root) {
		ArrayDeque<Node> queen = new ArrayDeque<>();
		if (root == null) {
			System.out.println("Tree is empty!");
		}
		//加入根节点
		queen.add(root);
		while (!queen.isEmpty()) {
			Node node=queen.remove();
			System.out.print(node.value);
			if (node.left != null) {
				queen.add(node.left);
			}
			if (node.right != null) {
				queen.add(node.right);
			}
		}
	}
	/***
	 * main函数
	 * @param args
	 */
	public static void main(String[]args){
		BinaryTree bTree = new BinaryTree();
		List<Character>preresultList=new ArrayList<>();
		List<Character>inresultList=new ArrayList<>();
		List<Character>postresultList=new ArrayList<>();
		//int[] a = {1,2,3,4,5,6,7,8,9};
		String str="a(b(c(d,e),),f(g,h(i,)))";
		//bTree.createBinaryTree(str);
		bTree.createTree(str);
		System.out.println("-----------递归前序遍历------------");
		bTree.preOrder(bTree.root);
		System.out.println("\n"+"--------非递归前序遍历----------");
		preresultList=bTree.preorderTraversal(bTree.root);
		for(char c:preresultList){
			System.out.print(c+" ");
		}
		System.out.println("\n-----------递归中序遍历-----------");
		bTree.inOrder(bTree.root);
		System.out.println("\n---------非递归中序遍历----------");
		inresultList=bTree.inorderTraversal(bTree.root);
		for(char c:inresultList){
			System.out.print(c+" ");
		}
		System.out.print("后序遍历：");
		bTree.postOrder(bTree.root);
		System.out.println();
		System.out.print("广度优先遍历：");
		bTree.LevelOrder(bTree.root);
	}
}