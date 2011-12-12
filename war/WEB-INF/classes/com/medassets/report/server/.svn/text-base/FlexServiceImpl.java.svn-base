package com.medassets.report.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.medassets.report.client.service.FlexService;
import com.medassets.report.shared.FlexGlobalSettingsParamItemDTO;
import com.medassets.report.shared.FlexSelectionParamItemDTO;
import com.medassets.report.shared.ParamAvailableItemValue;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class FlexServiceImpl extends RemoteServiceServlet implements
		FlexService {

	/**
	 * Return available parameters list to the flex selection tab.
	 * 
	 * @param reportTemplateId
	 *            Long
	 * @return List<FlexSelectTabParamDTO>
	 */
	@Override
	public List<FlexSelectionParamItemDTO> getSelectTabAvailableParamList(
			Long reportTemplateId) {
		List<FlexSelectionParamItemDTO> paramList = new ArrayList<FlexSelectionParamItemDTO>();
		mockSelectTabAvailableParameters(paramList);
		return paramList;

	}

	private void mockSelectTabAvailableParameters(
			List<FlexSelectionParamItemDTO> flexParamDTOList) {
		for (int i = 0; i < 100; i++) {
			FlexSelectionParamItemDTO paramObj = new FlexSelectionParamItemDTO();
			paramObj.setKeyName("APC Classification Scheme" + i);
			paramObj.setDescription("APC Classification Scheme" + i);
			paramObj.setLookup("APC Classification Scheme" + i);

			String expectedAlias = "AND APC Classification Scheme " + "\n"
					+ "<Does Not Exist>" + "\n" + "APC102007" + "\n"
					+ "APC2009" + "\n" + "APC2010LONG" + "\n" + "APC01" + "\n"
					+ "APC2007" + "\n" + "APC2011";
			paramObj.setAliasFormula(expectedAlias);
			if (i == 9) {
				paramObj.setPopulated(true);
			}

			paramObj.setPopulated(false);
			flexParamDTOList.add(paramObj);
		}
	}

	private void mockSelectTabParamCodeList(
			List<ParamAvailableItemValue> paramList) {
		for (int i = 0; i < 1000; i++) {
			ParamAvailableItemValue paramObj = new ParamAvailableItemValue();
			paramObj.setCode(""+i);
			paramObj.setDescription("Admission Source" + i);
			paramList.add(paramObj);
		}
	}
	
	/**
	 * Return saved selections to the flex selection tab
	 * @param reportTemplateId Long
	 * @param reportInstanceId Long
	 * @return List<FlexSelectionParamItemDTO>
	 */
	public List<FlexSelectionParamItemDTO> fetchSavedSelections(Long reportTemplateId, Long reportInstanceId) {
		List<FlexSelectionParamItemDTO> savedSelectionsSharedDtoList = new ArrayList<FlexSelectionParamItemDTO>();
		mockSelectTabAvailableParameters(savedSelectionsSharedDtoList);
		return savedSelectionsSharedDtoList;
	}

	@Override
	public List<ParamAvailableItemValue> getSelectTabParamCodeList(
			FlexSelectionParamItemDTO selectedParamDTO,
			List<FlexGlobalSettingsParamItemDTO> globalSettingsTabDtoList,
			String[] statusMsg) {
		List<ParamAvailableItemValue> paramList = new ArrayList<ParamAvailableItemValue>();

		mockSelectTabParamCodeList(paramList);

		return paramList;
	}
}
