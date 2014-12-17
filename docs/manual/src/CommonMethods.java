public interface ... {
	public URL getURL();
	public void setURL(URL nodeURL) 
		throws IllegalStateException;
	public ID getID();
	public void setID(ID nodeID) 
		throws IllegalStateException;
	public void create() 
		throws ServiceException;
	public void create(URL localURL) 
		throws ServiceException;
	public void create(URL localURL, ID localID)
		throws ServiceException;
	public void join(URL bootstrapURL) 
		throws ServiceException;
	public void join(URL localURL, URL bootstrapURL)
		throws ServiceException;
	public void join(URL localURL, ID localID, URL bootstrapURL)
		throws ServiceException;
	public void leave() 
		throws ServiceException;
}