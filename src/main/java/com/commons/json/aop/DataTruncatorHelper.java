/**
 * 
 */
package com.commons.json.aop;

import java.io.FileReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.hibernate.validator.internal.util.ReflectionHelper;
import org.hibernate.validator.internal.util.TypeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.commons.json.annotation.IgnoreData;
import com.commons.json.annotation.IgnoreSourceData;
import com.commons.json.annotation.ModifyData;
import com.commons.json.annotation.ModifySourceData;
import com.commons.json.data.Device;
import com.commons.json.data.Page;
import com.commons.json.handler.DeviceDataHandler;
import com.commons.json.handler.TrimDataHandler;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

/**
 * @author santosh joshi
 * 
 */
public class DataTruncatorHelper {

    private static final Logger LOG = LoggerFactory.getLogger(TrimDataHandler.class);
    
    public static void main(String[] args) throws Exception {
	Gson gson = new Gson();
	JsonReader reader = new JsonReader( new FileReader(getFileName()));
	ResponseDTO sr = gson.fromJson(reader, ResponseDTO.class);
	
	JsonReader reader2 = new JsonReader( new FileReader(getFileName()));
	JsonReader reader3 = new JsonReader( new FileReader(getFileName()));
	JsonReader reader4 = new JsonReader( new FileReader(getFileName()));
	JsonReader reader5 = new JsonReader( new FileReader(getFileName()));
	JsonReader reader6 = new JsonReader( new FileReader(getFileName()));
	
	LOG.debug("Before {} ",  gson.toJson(sr));
	
	Gson gson1 = new Gson();
	getDataTruncatedFields(sr, Device.MOBILE, Page.LISTING);
	LOG.debug("MOBILE - LISTING {}  ",  gson1.toJson(sr));
	
	Gson gson6 = new Gson();
	ResponseDTO sr1 = gson6.fromJson(reader6, ResponseDTO.class);
	getDataTruncatedFields(sr1, Device.TABLET, Page.LISTING);
	LOG.debug("TABLET - LISTING {}  ",  gson6.toJson(sr1));
	
	Gson gson2 = new Gson();
	ResponseDTO sr2 = gson2.fromJson(reader2, ResponseDTO.class);
	getDataTruncatedFields(sr2, Device.DESKTOP, Page.LISTING);
	LOG.debug("NORMAL - LISTING {}  ",  gson2.toJson(sr2));
	
	Gson gson3 = new Gson();
	ResponseDTO sr3 = gson3.fromJson(reader3, ResponseDTO.class);
	getDataTruncatedFields(sr3, Device.MOBILE, Page.DETAIL);
	LOG.debug("MOBILE - DETAIL {}  ",  gson3.toJson(sr3));
	
	Gson gson4 = new Gson();	
	ResponseDTO sr4 = gson4.fromJson(reader4, ResponseDTO.class);
	getDataTruncatedFields(sr4, Device.TABLET, Page.DETAIL);
	LOG.debug("TABLET - DETAIL {}  ",  gson4.toJson(sr4));
	
	 Gson gson5 = new Gson();
	ResponseDTO sr5 = gson5.fromJson(reader5, ResponseDTO.class);
	getDataTruncatedFields(sr5, Device.DESKTOP, Page.DETAIL);
	LOG.debug("NORMAL - DETAIL {}  ",  gson5.toJson(sr5)); 
    }

    private static String getFileName() {
	return "C:\\Users\\santoshjo\\Downloads\\Spring3-AOP-AspectJ-Examples\\Spring3Example\\src\\main\\java\\com\\mmt\\hotels\\web\\Response.json";
    }

    @SuppressWarnings("rawtypes")
    private static void getDataTruncatedFields(Object object, Device currentDevice, Page currentPage) throws Exception {

	Class<?> clazz = object.getClass();
	LOG.debug("Class - {}  ",  clazz.getName());
	for (Field field : clazz.getDeclaredFields()) {
	    LOG.debug("    == {}  ",   field.getName());

	    if (field.isAnnotationPresent(IgnoreData.class)) {
		LOG.debug("       =Ignore= :{}  ",  field.getName());
		if (shoudIgnoreCurrentPageAndDevice(currentDevice, currentPage, field)) {
		    field.setAccessible(true);
		    field.set(object, null);
		    continue;
		}
	    }

	    // Should be on very leaf level
	    if (field.isAnnotationPresent(ModifyData.class)) {
		modifySource(object, currentDevice, field, currentPage );
		continue;
	    }

	    /**
	     * is ARRAY or COLLECTION OR MAP
	     */
	    if (ReflectionHelper.isList(field.getType()) || TypeHelper.isArray(field.getType())
		    || ReflectionHelper.isMap(field.getType())) {

		field.setAccessible(true);
		Object obj = field.get(object);
		if (obj != null) {
		    if (ReflectionHelper.isMap(field.getType())) {
			Map m = (Map) obj;
			if (m != null && m.size() > 0) {
			    for (Iterator iterator = m.values().iterator(); iterator.hasNext();) {
				Object mo = iterator.next();
				if (mo != null) {
				    if (PRIMITIVE_TO_WRAPPER_TYPES.contains(mo.getClass())) {
					modifySource(object, currentDevice, field, currentPage);
				    } else {
					getDataTruncatedFields(mo, currentDevice, currentPage);
				    }
				}
			    }
			}

		    } else {

			Type type = obj.getClass();
			LOG.debug(type + "--{}  ",  object);
			if (ReflectionHelper.isIterable(type)) {
			    Iterator<?> iter = ((Iterable<?>) obj).iterator();
			    Object o;
			    while (iter.hasNext()) {
				o = iter.next();
				if (o != null) {
				    if (PRIMITIVE_TO_WRAPPER_TYPES.contains(o.getClass())) {
					modifySource(object, currentDevice, field, currentPage);
				    } else {
					getDataTruncatedFields(o, currentDevice, currentPage);
				    }
				}
			    }
			} else if (TypeHelper.isArray(type)) {
			    Object[] ooo = (Object[]) obj;
			    for (Object object2 : ooo) {
				if (object2 != null) {
				    if (PRIMITIVE_TO_WRAPPER_TYPES.contains(object2.getClass())) {
					modifySource(object, currentDevice, field, currentPage);
				    } else {
					getDataTruncatedFields(object2, currentDevice, currentPage);
				    }
				}
			    }
			}
		    }
		}
		continue;
	    }

	    // LOG.debug(field.getName());
	    if (PRIMITIVE_TO_WRAPPER_TYPES.contains(field.getType())) {
		modifySource(object, currentDevice, field, currentPage);
		continue;
	    }

	    // Dont do anything for enums
	    if (field.getType().isEnum()) {
		continue;
	    }
	    /**
	     * IS CUSTOM CLASS
	     */
	    if (!field.getType().isPrimitive()) {
		field.setAccessible(true);
		Object o = field.get(object);
		if (o != null) {
		    getDataTruncatedFields(o, currentDevice, currentPage);
		}
	    }
	}
    }

    private static boolean shoudIgnoreCurrentPageAndDevice(Device currentDevice, Page currentPage, Field field) {
	IgnoreData ignoreDataAnnotation = field.getAnnotation(IgnoreData.class);
	IgnoreSourceData[] ignoreSourceDataArray = ignoreDataAnnotation.value();

	boolean ignoreDevicesAndPage = false;
	for (IgnoreSourceData ignoreSourceData : ignoreSourceDataArray) {

	    Device device = ignoreSourceData.devices();
	    Page page = ignoreSourceData.page();

	    if (device.equals(Device.ALL) && page.equals(Page.ALL_PAGES)) {
		ignoreDevicesAndPage = true;
		break;
	    } else if (device.equals(Device.ALL) && page.equals(currentPage)) {
		ignoreDevicesAndPage = true;
		break;
	    } else if (device.equals(currentDevice) && page.equals(Page.ALL_PAGES)) {
		ignoreDevicesAndPage = true;
		break;
	    } else if (device.equals(currentDevice) && page.equals(currentPage)) {
		ignoreDevicesAndPage = true;
		break;
	    } else {
		// why???, because we are left with no condition
	    }
	}
	return ignoreDevicesAndPage;
    }

    private static void modifySource(Object object, Device currentDevice, Field field, Page currentPage) throws IllegalAccessException {

	//Device device = Device.getDevice(deviceName);
	Annotation[] annotations = field.getDeclaredAnnotations();
	if (annotations != null && annotations.length > 0) {
	    field.setAccessible(true);
	    for (Annotation annotation : annotations) {
		if (annotation.annotationType().equals(ModifyData.class)) {
		    ModifyData dd = (ModifyData) annotation;

		    Annotation[] annotations2 = dd.value();
		    for (Annotation annotation2 : annotations2) {
			if (annotation2.annotationType().equals(ModifySourceData.class)) {
			    ModifySourceData mb = (ModifySourceData) annotation2;
		
			    if ((mb.device().equals(Device.ALL) && mb.page().equals(Page.ALL_PAGES)) 
				    || ( mb.device().equals(Device.ALL) && currentPage.equals(mb.page()))
				    || ( currentDevice.equals(mb.device()) && mb.page().equals(Page.ALL_PAGES))
				    || ( currentDevice.equals(mb.device()) && currentPage.equals(mb.page()))
				    ) {
				LOG.debug(mb.dataValue());
				// LOG.debug(mb.device());
				// LOG.debug();
				DeviceDataHandler dataHandler = mb.operation().getDataHandler();
				Object value = field.get(object);
				if (value != null) {
				    LOG.debug("Old value is --> {}  ",  value);
				    value = dataHandler.handle(value, mb.dataValue());
				    LOG.debug("New  value is --> {}  ",  value);
				    field.set(object, value);
				}
			    }
			}
		    }
		}
	    }
	}
    }

    private static final Set<Class<?>> PRIMITIVE_TO_WRAPPER_TYPES;

    static {
	Set<Class<?>> temp = new HashSet<Class<?>>(9);

	temp.add(Boolean.class);
	temp.add(String.class);
	temp.add(Character.class);
	temp.add(Double.class);
	temp.add(Float.class);
	temp.add(Long.class);
	temp.add(Integer.class);
	temp.add(Short.class);
	temp.add(Byte.class);
	temp.add(Void.TYPE);
	temp.add(Date.class);

	PRIMITIVE_TO_WRAPPER_TYPES = Collections.unmodifiableSet(temp);
    }

}
