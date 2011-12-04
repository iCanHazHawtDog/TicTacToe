package edu.luc.tictactoe.businesslogic.implementation;

public interface IPair<K, V> {

	public abstract K getKey();

	public abstract V getValue();

	public abstract int getHash();

	@SuppressWarnings("unchecked")
	public abstract boolean equals(Object object);

	public abstract String toString();

}