/**
 * 
 */
package com.commons.json.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.commons.json.data.Device;
import com.commons.json.data.Operation;
import com.commons.json.data.Page;

/**
 * @author santosh joshi
 * 
 */
@Target({FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
public @interface ModifySourceData {

    /**
     *  What operation to perform on feild
     * @return
     */
    Operation operation();

    /**
     * Which device, i mean which we need to perform operation
     * @return
     */
    Device device();

     /**
      * 
      */
    Page page() default Page.NO_PAGE;
    
    /**
     * What should be done 
     * for example : if trim : then what value
     * 
     *  The handling should be done inside the handler
     *  
     * @return
     */
    String dataValue();
}
