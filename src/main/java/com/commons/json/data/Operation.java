/**
 * 
 */
package com.commons.json.data;

import com.commons.json.handler.AppendDataHandler;
import com.commons.json.handler.DeviceDataHandler;
import com.commons.json.handler.TrimDataHandler;

/**
 * @author santosh joshi
 * 
 *  This might evolve in future depending on the requirement,
 *  add more operations and their handlers
 *
 */
public enum Operation {
	
    TRIM("trim", new TrimDataHandler()),
    APPEND("append", new AppendDataHandler());
    
    String operation ;
    DeviceDataHandler dataHandler ;
    
    Operation(String operation, DeviceDataHandler dataHandler) {
	
	this.operation = operation;
	this.dataHandler = dataHandler;
    }
    
    /**
     * @return the dataHandler
     */
    public DeviceDataHandler getDataHandler() {
	return this.dataHandler;
    }
}
