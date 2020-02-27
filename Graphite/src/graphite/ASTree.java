package graphite;

public class ASTree {
	private Node root;
	public static void print(Node p) {
		if(p.left == null && p.right == null) {
			System.out.print(p.value.type+" "+p.value.text+"\n");
		}
		if(p.left != null)print(p.left);
		if(p.right != null)print(p.right);
	}
	
}
