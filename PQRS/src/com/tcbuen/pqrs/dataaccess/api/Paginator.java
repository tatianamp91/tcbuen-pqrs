package com.tcbuen.pqrs.dataaccess.api;

import java.io.Serializable;


/**
 *
 * @author <a href="mailto:dgomez@vortexbird.com">Diego A Gomez</a>
 * @project zathuracode
 * @class Paginator
 * @date Nov 01, 2013
 *
 */
public class Paginator implements Serializable {
    private static final long serialVersionUID = 1L;
    private int firstResult;
    private int maxResults;

    // a separated comma order properties: +=ASC, -=DESC, for example: +id,-processName
    private String sort;

    public Paginator(int firstResult, int maxResults, String sort) {
        super();
        this.firstResult = firstResult;
        this.maxResults = maxResults;
        this.sort = sort;
    }

    public Paginator(int firstResult, int maxResults) {
        this.firstResult = firstResult;
        this.maxResults = maxResults;
    }

    public static Paginator createPaginator() {
        return new Paginator(-1, -1);
    }

    public int getFirstResult() {
        return firstResult;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public String getSort() {
        return sort;
    }

    public static Paginator getDefault() {
        return new Paginator(0, 1000);
    }
}
