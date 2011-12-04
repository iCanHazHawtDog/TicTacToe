package edu.luc.tictactoe.businesslogic.implementation;
/**
 * 
 * @author Subhash Pant
 * Using pair instead of Map class
 */
public class Pair<K, V> implements IPair<K, V>{
	private final K key;
	private final V value;
	private final int hash;
	
	public Pair(K key, V value){
		this.key = key;
		this.value = value;
		this.hash = key.hashCode() ^ value.hashCode();
	}

	/* (non-Javadoc)
	 * @see edu.luc.tictactoe.businesslogic.implementation.IPair#getKey()
	 */
	@Override
	public K getKey() {
		return key;
	}

	/* (non-Javadoc)
	 * @see edu.luc.tictactoe.businesslogic.implementation.IPair#getValue()
	 */
	@Override
	public V getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see edu.luc.tictactoe.businesslogic.implementation.IPair#getHash()
	 */
	@Override
	public int getHash() {
		return hash;
	}
	
	
	/* (non-Javadoc)
	 * @see edu.luc.tictactoe.businesslogic.implementation.IPair#equals(java.lang.Object)
	 */
	@Override
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

	
	
	/* (non-Javadoc)
	 * @see edu.luc.tictactoe.businesslogic.implementation.IPair#toString()
	 */
	@Override
	public String toString()
	{
		return key.toString() + " " + value.toString();
	}
	
}
