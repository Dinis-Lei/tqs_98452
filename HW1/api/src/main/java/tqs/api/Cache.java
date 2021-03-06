package tqs.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Cache {
    
    private final long timeToLive;
    private final HashMap<String, CachedData> cacheMap;
	private final int capacity;
	private int hit;
	private int miss;
	private Logger logger = Logger.getLogger("apilogger");

    
    public Cache(long ttl, final long timerInterval, int maxItems) {
		this.timeToLive = ttl * 1000;
		this.capacity = maxItems;
		cacheMap = new HashMap<>();
 
		if (timeToLive > 0 && timerInterval > 0) {
 
			Thread t = new Thread( () ->  {
					while (true) {
						try {
							
							// Thread: A thread is a thread of execution in a program.
							// The Java Virtual Machine allows an application to have multiple threads of execution running concurrently.
							Thread.sleep(timerInterval * 100);
						} catch (InterruptedException ex) {
							logger.warning(ex.toString());
							Thread.currentThread().interrupt();
							break;
						}
						cleanup();
					}
				}
			);
 
			// setDaemon(): Marks this thread as either a daemon thread or a user thread.
			// The Java Virtual Machine exits when the only threads running are all daemon threads.
			// This method must be invoked before the thread is started.
			t.setDaemon(true);
			
			// start(): Causes this thread to begin execution; the Java Virtual Machine calls the run method of this thread.
			// The result is that two threads are running concurrently:
			// the current thread (which returns from the call to the start method) and the other thread (which executes its run method).
			t.start();
		}
	}

    public void put(String key, CovidData value) {
		synchronized (cacheMap) {
			if (this.size() < capacity){
				// put(): Puts a key-value mapping into this map.
				cacheMap.put(key, new CachedData(value));
			}
			else{
				Comparator<Map.Entry<String, CachedData>> comp1 =  (v1, v2) -> v1.getValue().hits - v2.getValue().hits;
				Comparator<Map.Entry<String, CachedData>> comp2 =  
					(v1, v2) -> v1.getValue().lastAccessed.intValue() - v2.getValue().lastAccessed.intValue();
				List<Map.Entry<String, CachedData>> a =
				cacheMap.entrySet().stream().sorted(comp1.thenComparing(comp2)).collect(Collectors.toList());
				logger.info(String.format("Cache: full capacity, removing %s and adding %s", a.get(0).getKey(), key));
				cacheMap.remove(a.get(0).getKey());
				cacheMap.put(key, new CachedData(value));
			}
			
		}
	}
 
	public CovidData get(String key) {
		synchronized (cacheMap) { 

			CachedData c;
			c = cacheMap.get(key);

			if (c == null){
				miss++;
				logger.info(String.format("Cache: failed to fetch %s", key));
				return null;
			}	
			else {
				hit++;
				c.lastAccessed = System.currentTimeMillis();
				logger.info(String.format("Cache: successfully fetched %s", key));
				return c.getData();
			}
		}
	}
 
	public void remove(String key) {
		synchronized (cacheMap) {
			cacheMap.remove(key);
		}
	}
 
	public int size() {
		synchronized (cacheMap) {
			return cacheMap.size();
		}
	}

    public void cleanup() {
		long now = System.currentTimeMillis();
		ArrayList<String> deleteKey = new ArrayList<>();
 
		synchronized (cacheMap) {
			cacheMap.forEach(
                (key, value) -> {
                    if (now > (timeToLive + value.lastAccessed)){
                        deleteKey.add(key);
                    }
                }
            );
		}
 
		for (String key : deleteKey) {
			synchronized (cacheMap) {
				
				// remove(): Removes the specified mapping from this map.
				cacheMap.remove(key);
			}
 
			// yield(): A hint to the scheduler that the current thread is willing to
			// yield its current use of a processor.
			// The scheduler is free to ignore this hint.
			Thread.yield();
		}
	}

	public double getRatio(){
		if (hit + miss == 0){
			return 0;
		}
		return 1.0*hit/(hit+miss);
	}

    public int getHit() {
		return hit;
	}

	public int getMiss() {
		return miss;
	}

	protected class CachedData {

        private Long lastAccessed = System.currentTimeMillis();
		private CovidData value;
		private int hits = 0;
 
		protected CachedData(CovidData value) {
			this.value = value;
		}

		public CovidData getData(){
			this.hits++;
			return value;
		}
    }
}
