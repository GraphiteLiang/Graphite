package graphite;

public class Token {
	public String text;
	public Type type;
	
	public Token(Type type) {
		this.text = "";
		this.type = type;
	}
	public Token(Type type, char text) {
		this.text = ""+text;
		this.type = type;
	}
	public Token() {
		this.text = "";
		this.type = Type.UnKnown;
	}

}
