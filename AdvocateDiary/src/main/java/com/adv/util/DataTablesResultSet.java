package com.adv.util;

import java.util.Collections;
import java.util.List;

public class DataTablesResultSet<T> {
	private final String sEcho;
	private final Long iTotalRecords;
	private final Long iTotalDisplayRecords;
	private final List<T> aaData;

	public DataTablesResultSet(String sEcho, Long iTotalRecords,
			Long iTotalDisplayRecords, List<T> aaData) {
		super();
		this.sEcho = sEcho;
		this.iTotalRecords = iTotalRecords;
		this.iTotalDisplayRecords = iTotalDisplayRecords;
		this.aaData = aaData;
	}

	public String getsEcho() {
		return sEcho;
	}

	public Long getiTotalRecords() {
		return iTotalRecords;
	}

	public Long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public List<T> getAaData() {
		return Collections.unmodifiableList(aaData);
	}
}