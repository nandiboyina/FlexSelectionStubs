package com.medassets.report.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.medassets.report.shared.FlexGlobalSettingsParamItemDTO;
import com.medassets.report.shared.FlexSelectionParamItemDTO;
import com.medassets.report.shared.ParamAvailableItemValue;

@RemoteServiceRelativePath("flexService")
public interface FlexService extends RemoteService {

	/**
	 * Retrieve the Flex Select Tab Available List
	 * 
	 * @param reportTemplateId
	 * @return List<FlexSelectTabParamDTO>
	 */
	List<FlexSelectionParamItemDTO> getSelectTabAvailableParamList(
			Long reportTemplateId);

	/**
     * Return parameter codes list to the flex selection tab.
     * 
     * @param reportTemplateId Long
     * @return List<FlexSelectTabParamDTO>
     */
	public List<ParamAvailableItemValue> getSelectTabParamCodeList(
			FlexSelectionParamItemDTO selectedParamDTO,
			List<FlexGlobalSettingsParamItemDTO> globalSettingsTabDtoList,
			String[] statusMsg);
	
	/**
	 * Return saved selections to the flex selection tab
	 * @param reportTemplateId Long
	 * @param reportInstanceId Long
	 * @return List<FlexSelectionParamItemDTO>
	 */
	public List<FlexSelectionParamItemDTO> fetchSavedSelections(Long reportTemplateId, Long reportInstanceId);
}
