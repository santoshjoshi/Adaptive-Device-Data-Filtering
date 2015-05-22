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
 *         Responsibility: Append some text to a data
 */
public final class AppendDataHandler implements DeviceDataHandler {

	private static final Logger LOG = LoggerFactory
			.getLogger(TrimDataHandler.class);
	
	@Override
	public Object handle(Object value, Object textToAppend) {

		LOG.debug("Appending {} to text {} ", textToAppend, value);
		validate(textToAppend);

		if (StringUtils.hasLength((String) value)) {
			return value + " " + textToAppend;
		}
		return "";
	}

	/**
	 * 
	 * @param trimToSize
	 * @param value
	 */
	private void validate(Object textToappend) {

		if (!StringUtils.hasText((String) textToappend)) {
			LOG.debug("cannot append text {} as it is null or blank",
					textToappend);
			throw new IllegalArgumentException(
					"The textToappend parameter cannot be null or empty.");
		}
	}

}
