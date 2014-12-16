package com.adv.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class PdfReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map model, Document document,
			PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// List<String> labels = (List<String>) model.get("labels");
		List<Object> data = (List<Object>) model.get("data");

		BeanInfo beanInfo = Introspector.getBeanInfo(data.get(0).getClass());
		Table table = new Table(beanInfo.getPropertyDescriptors().length);
		for (PropertyDescriptor propertyDesc : beanInfo
				.getPropertyDescriptors()) {
			table.addCell(propertyDesc.getName());
		}

		for (Object obj : data) {
			for (PropertyDescriptor propertyDesc : beanInfo
					.getPropertyDescriptors()) {
				Object value = propertyDesc.getReadMethod().invoke(obj);
				table.addCell(
						value==null?"":value.toString());
			}
		}
		document.add(table);
	}
}