/*
 * Created on Aug 30, 2003 by Ravi Mohan
 *  
 */
package aima.logic.common;

public class Token {
	private String text;

	private int type;

	public Token(int type, String text) {
		this.type = type;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public int getType() {
		return type;
	}

	public boolean equals(Object o) {
		Token other = (Token) o;
		return ((other.type == type) && (other.text.equals(text)));
	}

	public String toString() {
		return "[ " + type + " " + text + " ]";
	}

}