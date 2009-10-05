/*
 * Created on Sep 18, 2004
 *
 */
package aima.logic.fol.parsing.ast;

import aima.logic.fol.parsing.FOLVisitor;

/**
 * @author Ravi Mohan
 *
 */
public class ConnectedSentence extends ParanthizedSentence{

	private Sentence first, second;
	private String connector;

	public ConnectedSentence (
		String connector,
		Sentence first,
		Sentence second) {
		super();
		this.first = first;
		this.second = second;
		this.connector = connector;

	}

	public String getConnector() {
		return connector;
	}

	public Sentence getFirst() {
		return first;
	}

	public Sentence getSecond() {
		return second;
	}
	public void setFirst(Sentence first) {
		this.first = first;
	}

	public void setSecond(Sentence second) {
		this.second = second;
	}
	public boolean equals(Object o) {
		try {
			ConnectedSentence cs = (ConnectedSentence) o;
			return (
				(cs.getConnector().equals(getConnector()))
					&& (cs.getFirst().equals(getFirst()))
					&& (cs.getSecond().equals(getSecond())));
		} catch (ClassCastException e) {

			return false;
		}
	}
	public String toString(){
		return " ("+first.toString()+" "+connector+" "+second.toString()+" )";
	}

	public Object accept(FOLVisitor v,Object arg) {
		
		return v.visitConnectedSentence(this,arg);
	}
	public FOLNode copy(){
		return new ConnectedSentence(connector,(Sentence)first.copy(),(Sentence)second.copy());
	}


}
