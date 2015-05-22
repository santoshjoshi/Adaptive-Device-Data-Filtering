/**
 * 
 */
package com.commons.json.data;

/**
 * @author santosh joshi
 *
 */
public enum Page {

    LISTING("search"),
    DETAIL("detail"),
    
    NO_PAGE(""),
    ALL_PAGES("all");
    
    private String pageName;
    
    Page(String pageName) {
	this.pageName = pageName;
    }
    
     /**
     * @return the pageName
     */
    public String getPageName() {
	return pageName;
    }
}
