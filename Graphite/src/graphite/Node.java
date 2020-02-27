package graphite;

public class Node {
	public Node left;
	public Node right;
	public Token value;
	public Node(Token value) {
		this.value = value;
	}
	public Node() {
		super();
	}
	public static void print(Node p) {
		if(p == null) return;

		System.out.print(p.value.text+"\n");
		print(p.left);
		print(p.right);
	}
}
