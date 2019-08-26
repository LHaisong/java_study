package datastructure.Tree;

import java.util.ArrayDeque;

public class BinarySearchTree {
	public BSTNode root=null;
	class BSTNode {
		public int value;
		public BSTNode left;
		public BSTNode right;
        public BSTNode p;
		public BSTNode(BSTNode node,int val) {
			this.p=node;
            this.value=val;
            this.left=null;
            this.right=null;
		}
	}
		/**
		 * @param val
		 */
		public void createBST(int val) {
			BSTNode insertPos=root;
			BSTNode insertPosParent=null;
			while (insertPos!=null) {
				insertPosParent=insertPos;
				if(val<insertPosParent.value){
                    insertPos=insertPosParent.left;
				}else if(val>insertPosParent.value){
					insertPos=insertPosParent.right;
				}
			}
            BSTNode insertPoint=new BSTNode(insertPosParent,val);
			if(insertPosParent==null){
				root=insertPoint;
			}
			else if(val<insertPosParent.value){
				insertPosParent.left=insertPoint;
			}
			else {
				insertPosParent.right=insertPoint;
			}
		}

	/**
	 * 查找节点
	 * @param val
	 * @param node
	 */
		public String find(BSTNode node,int val){
			BSTNode currentNode=node;
			while(currentNode.value!=val) {
				/**
				 * 如果当前的值大于要查的值，则该数位置在当前节点的左子树一边
				 */
				if (currentNode.value > val) {
					currentNode = currentNode.left;
					if (currentNode != null) {
						find(currentNode, val);
					} else {
						return "no such integer";
					}
				}
				/**
				 * 如果当前的值小于要查的值，则该数位置在当前节点的右子树一边
				 */
				if (currentNode.value < val) {
					currentNode = currentNode.right;
					if (currentNode != null) {
						find(currentNode, val);
					} else {
						return "no such integer";
					}
				}
			}
			return "ok";
		}

	/**
	 * 删除节点
	 * @param val
	 */
		public boolean delete(BSTNode node,int val){
			BSTNode currentNode=null;
			if(node!=null) {
				/**
				 * 删除的值在左子树
				 */
				if (node.value > val) {
					delete(node.left, val);
				}
				/**
				 * 删除的值在右子树
				 */
				if (node.value < val) {
					delete(node.right, val);
				}
				else {
					if (node.left != null && node.right != null) {
						currentNode = node.right;
						while (currentNode.left!=null){
							currentNode=currentNode.left;
							node.value = currentNode.value;
						}
						return true;
					}
					else if(node.left!=null&&node.right==null){
						node.value=node.left.value;
						node.left=null;
					}
					else if(node.right!=null&&node.left==null){
						node.value=node.right.value;
						node.right=null;
					}
					else {
						node=null;
					}
					return true;
				}
			}
			return false;
		}

	/**
	 * 插入节点
	 * @param val
	 */
		public boolean insert(int val){
            createBST(val);
            return true;
		}
	/**
	 * 二叉树的广度优先遍历
	 * @param root
	 * 辅助数据结构queen
	 */
	public void LevelOrder(BSTNode root) {
		ArrayDeque<BinarySearchTree.BSTNode> queen = new ArrayDeque<>();
		if (root == null) {
			System.out.println("Tree is empty!");
		}
		//加入根节点
		queen.add(root);
		while (!queen.isEmpty()) {
			BinarySearchTree.BSTNode node=queen.remove();
			System.out.print(node.value+" ");
			if (node.left != null) {
				queen.add(node.left);
			}
			if (node.right != null) {
				queen.add(node.right);
			}
		}
	}
	public static void main(String[]args){
		int[]arr={53,78,65,17,87,9,81,45,23};
		BinarySearchTree bst=new BinarySearchTree();
		for(int i=0;i<arr.length;i++){
			bst.createBST(arr[i]);
		}
		bst.LevelOrder(bst.root);
		System.out.println();
		String findStr=bst.find(bst.root,25);
		System.out.println(findStr);
		if(bst.delete(bst.root,65)){
			System.out.println("删除成功");
		}else {
			System.out.println("不存在该值");
		}
		bst.LevelOrder(bst.root);
		System.out.println();
	}
}
