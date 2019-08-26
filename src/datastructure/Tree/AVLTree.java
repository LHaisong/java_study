package datastructure.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;


/**
 * AVL树
 *
 */
public class AVLTree<T extends Comparable<? super T>> {

	/**
	 * AVL树节点的定义
	 */
	private static class AVLNode<T> {
		AVLNode(T theElement) {
			this(theElement, null, null);
		}
		AVLNode(T theElement, AVLNode<T> lt, AVLNode<T> rt) {
			element = theElement;
			left = lt;
			right = rt;
			height = 0;
		}
		T element; // 节点中的数据
		AVLNode<T> left; // 左儿子
		AVLNode<T> right; // 右儿子
		int height; // 节点的高度
	}

	// avl树根
	private AVLNode<T> root;

	public AVLTree() {
		root = null;
	}

	// 在avl树中插入数据，重复数据忽略
	public void insert(T x) {
		root = insert(x, root);
	}

	// 在avl中删除数据,没有实现
	public void remove(T x) {
		System.out.println("Sorry, remove unimplemented");
	}

	// 在avl树中找最小的数据
	public T findMin() throws Exception {
		if (isEmpty())
			throw new Exception("空");
		return findMin(root).element;
	}

	// 在avl树中找最大的数据
	public T findMax() throws Exception {
		if (isEmpty())
			throw new Exception("空");
		return findMax(root).element;
	}

	// 搜索
	public boolean contains(T x) {
		return contains(x, root);
	}

	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	// 排序输出avl树
	public void printTree() {
		if (isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
	}

	/**
	 * AVL树的插入，重点就是这儿了
	 * @param x 要插入的元素
	 * @param t 树的根节点
	 * @return
	 */
	private AVLNode<T> insert(T x, AVLNode<T> t) {
		if (t == null){
			return new AVLNode<T>(x, null, null);
		}

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0) {
			t.left = insert(x, t.left);// 将x插入左子树中

			// 打破平衡
			if (height(t.left) - height(t.right) == 2){
				// LL型（左左型）
				if (x.compareTo(t.left.element) < 0){
					t = rotateWithLeftChild(t);
				}
				// LR型（左右型）
				else{
					t = doubleWithLeftChild(t);
				}
			}

		} else if (compareResult > 0) {
			// 将x插入右子树中
			t.right = insert(x, t.right);

			// 打破平衡
			if (height(t.right) - height(t.left) == 2){
				// RR型（右右型）
				if (x.compareTo(t.right.element) > 0){
					t = rotateWithRightChild(t);
				}
				else{
					// RL型
					t = doubleWithRightChild(t);
				}
			}
		} else{
			; // 重复数据，什么也不做
		}

		t.height = Math.max(height(t.left), height(t.right)) + 1;// 更新高度
		return t;
	}


	// 带左子树旋转,适用于LL型
	private AVLNode<T> rotateWithLeftChild(AVLNode<T> k2) {
		AVLNode<T> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
	}

	// 带右子树旋转，适用于RR型
	private AVLNode<T> rotateWithRightChild(AVLNode<T> k1) {
		AVLNode<T> k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
		k2.height = Math.max(height(k2.right), k1.height) + 1;
		return k2;
	}

	// 双旋转，适用于LR型
	private AVLNode<T> doubleWithLeftChild(AVLNode<T> k3) {
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}

	// 双旋转,适用于RL型
	private AVLNode<T> doubleWithRightChild(AVLNode<T> k1) {
		k1.right = rotateWithLeftChild(k1.right);
		return rotateWithRightChild(k1);
	}



	// 找最小
	private AVLNode<T> findMin(AVLNode<T> t) {
		if (t == null)
			return t;

		while (t.left != null)
			t = t.left;
		return t;
	}

	// 找最大
	private AVLNode<T> findMax(AVLNode<T> t) {
		if (t == null)
			return t;

		while (t.right != null)
			t = t.right;
		return t;
	}

	// 搜索（查找）
	private boolean contains(T x, AVLNode<T> t) {
		while (t != null) {
			int compareResult = x.compareTo(t.element);

			if (compareResult < 0)
				t = t.left;
			else if (compareResult > 0)
				t = t.right;
			else
				return true; // Match
		}

		return false; // No match
	}

	// 前序遍历avl树
	private void printTree(AVLNode<T> t) {
		if (t != null) {
			System.out.print(t.element+" ");
			printTree(t.left);
			printTree(t.right);
		}
	}
	/**
	 * 宽度优先遍历
	 * @param node
	 */
	public void level(AVLNode node) {
		ArrayDeque<AVLNode> queen=new ArrayDeque<>();
		if(node==null) {
			System.out.println("树为空");
		}
		else {
			queen.add(node);
			while(!queen.isEmpty()) {
				AVLNode temp=queen.remove();
				System.out.print(temp.element+" ");
				if(temp.left!=null) {
					queen.add(temp.left);
				}
				if(temp.right!=null) {
					queen.add(temp.right);
				}
			}
		}
	}

	// 求高度
	private int height(AVLNode<T> t) {
		return t == null ? -1 : t.height;
	}

	public static void printMenu(){
		System.out.println("1.insert a element\n"+
				           "2.remove a element\n"+
				           "3.find the max element\n"+
				           "4.find the min element");
	}


	// Test program
	public static void main(String[] args) throws IOException {
		AVLTree<Integer> t = new AVLTree<Integer>();
		//int[]arr={23,25,14,51,35,29,10,39};
		printMenu();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		while(true){
			int opcodeRead=Integer.parseInt(br.readLine());
			if(opcodeRead==1){
				System.out.println("please input the element to insert:");
				String elementRead[]=br.readLine().split(" ");
				for(String s:elementRead){
					if(!s.isEmpty()){
						t.insert(Integer.parseInt(s));
					}
				}
				System.out.println("insert successfully!");
			}
			if (opcodeRead == 2) {
				System.out.print("The AVL tree traversal result is:");
				t.level(t.root);
			}
		}
//		for(int i=0;i<arr.length;i++) {
//			t.insert(arr[i]);
//		}

	}
}