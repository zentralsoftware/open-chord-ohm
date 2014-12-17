package de.uniba.wiai.lspi.chord.service;
public interface ChordFuture {
	public Throwable getThrowable();
	public boolean isDone() throws ServiceException;
	public void waitForBeingDone() throws ServiceException,
			InterruptedException;
}