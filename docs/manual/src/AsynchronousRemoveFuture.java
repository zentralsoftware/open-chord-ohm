...
AsynChord aChord = ...
//join or create a DHT
...
String data = "Just an Example."; 
StringKey myKey = new StringKey(data); 
ChordFuture future = aChord.removeAsync(myKey, data); 
...
//do other things while operation performed.
...
try {
	boolean finished = future.isDone(); 
	while (!finished) {
		try {
			future.waitForBeingDone(); 
			finished = true; 
		} catch (InterruptedException e){
			finished = false;
		}
	}
} catch (ServiceException e) {
	//handle exception
	...
}
...