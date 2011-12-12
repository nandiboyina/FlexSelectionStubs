package com.medassets.report.client.flex.selection.presenter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.medassets.report.client.service.FlexService;
import com.medassets.report.client.service.FlexServiceAsync;
import com.medassets.report.shared.FlexGlobalSettingsParamItemDTO;
import com.medassets.report.shared.FlexSelectionParamItemDTO;
import com.medassets.report.shared.ParamAvailableItemValue;
import com.medassets.report.shared.ReportItemDTO;

public class SelectTabPresenter {

	private final FlexServiceAsync selectTabService = GWT.create(FlexService.class);
	
	private ReportItemDTO report;
	
	

	public interface MyView {
		Widget asWidget();
		
		void manipulateViewControls();	
		
		ListBox leftSideParametersListBox_eventBox();
		
		void setParametersListViewMapObj(SortedMap<String, FlexSelectionParamItemDTO> parametersListViewMap);
		
		ListDataProvider<ParamAvailableItemValue> getLeftSideParamCodesTableDataProviderObj();

		void setSelectedParameterKeyNameObj(String selectedParameterKeyName);

		Map<String, List<ParamAvailableItemValue>> getRightSideInclusionCodeObjectsListMapObj();

		Button getReturnBtn();
	}
	
	private MyView view = null;

	private SortedMap<String, FlexSelectionParamItemDTO> parametersListViewMap;
	
	private String selectedParameterKeyName;
	
	public SelectTabPresenter() {
	}

	public SelectTabPresenter(final MyView view, ReportItemDTO report) {
		this.view = view;
		this.report = report;
	}

	public void reveal() {
		addComponentEventsforServiceResults();
		
		initialize();
	}
	
	private void initialize() {
		parametersListViewMap = new TreeMap<String, FlexSelectionParamItemDTO>();
		getView().setParametersListViewMapObj(parametersListViewMap);
		
		boolean isNotSavedInstance = (report.getReportInstanceId() == null);
		if (isNotSavedInstance) {
			buildReportTemplate();
		} else {
			buildReportInstance();
		}
	}

	private void buildReportInstance() {
		selectTabService.fetchSavedSelections(report.getReportTemplateId(), report.getReportInstanceId(), new AsyncCallback<List<FlexSelectionParamItemDTO>>() {
			
			@Override
			public void onSuccess(List<FlexSelectionParamItemDTO> result) {
				buildLeftSideParametersListView(result);
				
				for (FlexSelectionParamItemDTO paramObj : result) {
					if (paramObj.getPopulated()) {
						selectedParameterKeyName = paramObj.getKeyName();; 
						getView().setSelectedParameterKeyNameObj(selectedParameterKeyName);
						getView().manipulateViewControls();
						fetchPrameterCodeList();
					}
				}
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log(caught.getMessage(), caught);
//                new MAWindowAlert("Error", caught.getMessage(), null, "Ok").center();
			}
		});
	}

	private void buildReportTemplate() {
		selectTabService.getSelectTabAvailableParamList(report.getReportTemplateId(), new AsyncCallback<List<FlexSelectionParamItemDTO>>() {

            public void onFailure(Throwable caught) {
                GWT.log(caught.getMessage(), caught);
//                new MAWindowAlert("Error", caught.getMessage(), null, "Ok").center();
            }

            public void onSuccess(List<FlexSelectionParamItemDTO> result) {
            	buildLeftSideParametersListView(result);
            }
        });
	}
	
	public void buildLeftSideParametersListView(List<FlexSelectionParamItemDTO> result) {
		TreeMap<String, String> availListSortedMap = new TreeMap<String, String>();
		for (FlexSelectionParamItemDTO paramObj : result) {
			if (paramObj != null && paramObj.getDescription() != null
					&& paramObj.getDescription().trim().length() != 0) {
				parametersListViewMap.put(paramObj.getKeyName(), paramObj);
				availListSortedMap.put(paramObj.getDescription(), paramObj.getKeyName());
			}
		}

		Iterator<String> iterator = availListSortedMap.keySet().iterator();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			getView().leftSideParametersListBox_eventBox().addItem((String) key, availListSortedMap.get(key));
		}
	}

	private void addComponentEventsforServiceResults() {
		getView().leftSideParametersListBox_eventBox().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				GWT.log("SelectTabPresenter.addComponentEventsforServiceResults().leftSideParametersListBox_eventBox fired - Begin");
				int selectedIndex = getView().leftSideParametersListBox_eventBox().getSelectedIndex();
								selectedParameterKeyName = getView().leftSideParametersListBox_eventBox().getValue(selectedIndex);
				getView().manipulateViewControls();
				fetchPrameterCodeList();
				GWT.log("SelectTabPresenter.addComponentEventsforServiceResults().leftSideParametersListBox_eventBox fired - End");
			}
		});
	}

	/**
	 * @return the view
	 */
	public Widget getWidget() {
		return view.asWidget();
	}
	
	public MyView getView() {
		return view;
	}

	public void fetchPrameterCodeList() {
		GWT.log("SelectTabPresenter.fetchParameterCodeList() - Begin");
		FlexSelectionParamItemDTO selctedParameter = parametersListViewMap.get(selectedParameterKeyName);
		// TODO REFACTORING WILL BE THERE WEHN INTEGRATING SELECTION TAB WITH GLOBAL SETTINGS TAB
		List<FlexGlobalSettingsParamItemDTO> globalSettingsTabDtoList = null;
		String[] statusMsg = new String[] { " " };
		selectTabService.getSelectTabParamCodeList(selctedParameter, globalSettingsTabDtoList, statusMsg , new AsyncCallback<List<ParamAvailableItemValue>>() {
			
			@Override
			public void onSuccess(List<ParamAvailableItemValue> result) {
				populateListCodes(result);
				getView().getReturnBtn().setEnabled(true);
				GWT.log("SelectTabPresenter.fetchParameterCodeList() - End");
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log(caught.getMessage(), caught);
//                new MAWindowAlert("Error", caught.getMessage(), null, "Ok").center();
			}
		});
	}
	
	/**
	 * Prepares display data with the different codes as rows into the left panel to the user.
	 * It sets all the list data into dataProvider object of the CellTable
	 * 
	 * @param paramCodeList List<ParamAvailableItemValue>
	 */
	protected void populateListCodes(List<ParamAvailableItemValue> paramCodeList) {
		// Add the data to the data provider, which automatically pushes it to the
	    // widget.
	    List<ParamAvailableItemValue> list = getView().getLeftSideParamCodesTableDataProviderObj().getList();
	   int i=0;
		for (ParamAvailableItemValue paramCodeObject : paramCodeList) {
			boolean includedAlready = isCodeIncludedAlready(paramCodeObject);
			paramCodeObject.setIndex(i);
			i++;
			// it disables to add duplicate code into the left panel list
			if (!includedAlready) {
				list.add(paramCodeObject);
			}
			
			
			
		}
	  
	   
	}
	
	/**
	 * Verify the code is already included in right panel or not. if so, it does not require
	 * to populate in the left panel.
	 * 
	 * @param paramCodeObject ParamAvailableItemValue
	 * @return boolean
	 */
	private boolean isCodeIncludedAlready(ParamAvailableItemValue paramCodeObject) {
		for (Entry<String, List<ParamAvailableItemValue>> includedMap : getView().getRightSideInclusionCodeObjectsListMapObj().entrySet()) {
			String paramKeyName = includedMap.getKey();
			List<ParamAvailableItemValue> paramObjList = includedMap.getValue();
			
			if (paramKeyName.equals(selectedParameterKeyName)) {
				for (ParamAvailableItemValue paramCodeObj: paramObjList) {
					if (paramCodeObj.getCode().equals(paramCodeObject.getCode())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
}
