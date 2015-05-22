/**
 * 
 */
package com.commons.json.data;

/**
 * @author santosh joshi
 *
 */
public enum Device {
 
    ALL("all"),
    MOBILE("mobile"),
    TABLET("tablet"),
    DESKTOP("normal");
    
    private String deviceName;
    
    Device(String deviceName) {
	this.deviceName = deviceName;
    }
    
    /**
     * @return the deviceName
     */
    public String getDeviceName() {
	return deviceName;
    }

    /**
     * @param deviceName2
     * @return
     */
    public static Device getDevice(String deviceName) {
	
	for(Device device : values()){
	    if(device.getDeviceName().equalsIgnoreCase(deviceName)){
		return device;
	    }
	}
	throw new IllegalArgumentException("Unknown device");
    }
}
