/**
 * 
 */
package com.commons.json.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author santosh joshi
 * 
 *   Responsibility: Trim a data to specified length
 */
public final class TrimDataHandler implements DeviceDataHandler {

    private static final Logger LOG = LoggerFactory.getLogger(TrimDataHandler.class);

    @Override
    public Object handle(Object value, Object trim) {

	LOG.debug(" Trim operation on data  {} to length {}  then 0", value, trim);
	validate((String) trim, value);

	int trimToSize = Integer.parseInt((String) trim);

	if (trimToSize < ((String) value).length()) {
	    return ((String) value).substring(0, trimToSize);
	}
	return value;
    }

    /**
     * 
     * @param trimToSize
     * @param value
     */
    private void validate(String trimToSize, Object value) {

	if (Integer.parseInt(trimToSize) <= 0) {
	    LOG.debug(" cannot trim text size is less {}  then 0", trimToSize);
	    throw new IllegalArgumentException("The trimToSize parameter cannot be negative or 0.");
	}
	if (!StringUtils.hasText((String) value)) {
	    LOG.debug(" no text ", value);
	    // throw new IllegalArgumentException("The value parameter cannot be null or empty.");
	}
    }

}
