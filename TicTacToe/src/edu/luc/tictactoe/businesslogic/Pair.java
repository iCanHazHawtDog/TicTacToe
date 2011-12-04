package edu.luc.tictactoe.businesslogic;
/**
 * 
 * @author Subhash Pant
 * Using pair instead of Map class
 */
public class Pair<K, V>{
	private final K key;
	private final V value;
	private final int hash;
	
	public Pair(K key, V value){
		this.key = key;
		this.value = value;
		this.hash = key.hashCode() ^ value.hashCode();
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public int getHash() {
		return hash;
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object object)
	{
		if (this == object){
			return true;
		}
		if ( object == null || !(getClass().isInstance( object )) )
	    {
	      return false;
	    } 
		Pair<K, V> other = getClass().cast( object );
	    return (key == null? other.key == null : key.equals( other.key ))
	     && (value == null? other.value == null : value.equals( other.value )); 
	}

	
	
	public String toString()
	{
		return key.toString() + " " + value.toString();
	}
	
}
