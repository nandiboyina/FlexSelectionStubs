package com.medassets.report.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.medassets.report.shared.FlexGlobalSettingsParamItemDTO;
import com.medassets.report.shared.FlexSelectionParamItemDTO;
import com.medassets.report.shared.ParamAvailableItemValue;
import com.medassets.report.shared.ReportItemDTO;

public interface FlexServiceAsync {

	void getSelectTabAvailableParamList(Long reportTemplateId, AsyncCallback<List<FlexSelectionParamItemDTO>> callback);

	void getSelectTabParamCodeList(FlexSelectionParamItemDTO selectedParamDTO, List<FlexGlobalSettingsParamItemDTO> globalSettingsTabDtoList,
			String[] statusMsg, AsyncCallback<List<ParamAvailableItemValue>> callback);

	void fetchSavedSelections(Long reportTemplateId, Long reportInstanceId, AsyncCallback<List<FlexSelectionParamItemDTO>> callback);

}
