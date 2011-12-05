package com.medassets.report.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.medassets.report.client.flex.selection.presenter.SelectTabPresenter;
import com.medassets.report.client.flex.selection.view.SelectTabView;
import com.medassets.report.shared.ReportItemDTO;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FlexSelectionStubs implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		ReportItemDTO report = new ReportItemDTO();
		report.setReportTemplateId(Long.valueOf(113));
		SelectTabPresenter selectTabPresenter = new SelectTabPresenter(new SelectTabView(), report);
		selectTabPresenter.reveal();
		RootPanel.get().clear();
		RootPanel.get().add(selectTabPresenter.getWidget());
	}
}
