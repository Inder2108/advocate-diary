package com.adv.util;

import java.util.Collections;
import java.util.List;

public final class PagingCriteria {
	private final Integer displayStart;
	private final Integer displaySize;
	private final List<SortField> sortFields;
	private final Integer pageNumber;
	private final String searchQuery;
	private String sEcho;
	
	public PagingCriteria(Integer displayStart, Integer displaySize,
			Integer pageNumber, List<SortField> sortFields, String searchQuery,String sEcho) {
		this.displayStart = displayStart;
		this.displaySize = displaySize;
		this.pageNumber = pageNumber;
		this.sortFields = sortFields;
		this.searchQuery = searchQuery;
		this.sEcho = sEcho;
	}

	public Integer getDisplayStart() {
		return displayStart;
	}

	public Integer getDisplaySize() {
		return displaySize;
	}

	public List<SortField> getSortFields() {
		return Collections.unmodifiableList(sortFields);
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	
	

}