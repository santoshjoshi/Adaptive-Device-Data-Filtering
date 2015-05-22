/**
 * 
 */
package com.commons.json.handler;

/**
 * @author santosh joshi
 * 
 *         Subclasses may evolve in future at present only two <li>
 *         {@link TrimDataHandler} used to trim data to some length</li> <li>
 *         {@link AppendDataHandler} used for appending data to some field</li>
 * 
 *         Future handlers can have a requirement of showing date on different
 *         devices using different patterns.
 * 
 */
public interface DeviceDataHandler {

	/**
	 * 
	 * @param object
	 * @param o
	 * @return
	 */
	Object handle(Object object, Object o);
}
