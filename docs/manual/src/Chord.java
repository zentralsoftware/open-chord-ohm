package de.uniba.wiai.lspi.chord.service;
public interface Chord {
	...
	public Set<Serializable> retrieve(Key key) 
			throws ServiceException;
	public void insert(Key key, Serializable entry)
			throws ServiceException;
	public void remove(Key key, Serializable entry)
			throws ServiceException;
}