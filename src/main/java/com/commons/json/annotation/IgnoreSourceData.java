/**
 * 
 */
package com.commons.json.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.commons.json.data.Device;
import com.commons.json.data.Page;

/**
 * 
 * @author santosh joshi
 *
 */
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreSourceData {

   /**
    * Device on which we don't want to send
    * @return
    */
    Device devices();
    /**
     * pages on which this needs to be ignored 
     * 
     * @return
     */
    Page page();

}

