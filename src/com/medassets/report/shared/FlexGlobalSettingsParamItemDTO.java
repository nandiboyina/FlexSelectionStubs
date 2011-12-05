package com.medassets.report.shared;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @author hbala Transfer Object representing a Single Drop Down List for the
 *         General Tab 
 *         
 * The Main Attributes backing the UI are : 
 * promptName : The Label to use for the Drop Down List listOfValues : The
 * Values to show for the Drop Down list (Use each Value when iterating thru the
 * Map, not the Key) defautlSelection : The Default Selection for this Drop Down
 * list (This will contain the value of the Key for the above listOfValues Map)
 * 
 * Also the UI will set the selected Value for the Drop Down List in field currentValue 
 */

public class FlexGlobalSettingsParamItemDTO implements IsSerializable {

	private String labelName; 
	private Map<String, String> listOfValues = new LinkedHashMap<String, String>();

	private String lookup;
	private String parameterName;
	private String currentValue;	
	private String fieldName;
	private String fieldType;
	private String displayLabelName;
	private Long flags;
	private Long masterSettingObjectId;
	private String setTo;
	private Long helpId;
	private Long orderIndex;
	private int wizStyle;

    public enum WizStyleEnum {
		TEXT, CHK, CALENDAR, LIST, CBO, RADIO_BUTTON
	}

	public Long getMasterSettingObjectId() {
		return masterSettingObjectId;
	}

	public void setMasterSettingObjectId(Long masterSettingObjectId) {
		this.masterSettingObjectId = masterSettingObjectId;
	}

	public FlexGlobalSettingsParamItemDTO() {
		super();
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public Map<String, String> getListOfValues() {
		return listOfValues;
	}

	public void setListOfValues(Map<String, String> listOfValues) {
		this.listOfValues = listOfValues;
	}

	public String getLookup() {
		return lookup;
	}

	public void setLookup(String lookup) {
		this.lookup = lookup;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public Long getFlags() {
		return flags;
	}

	public void setFlags(Long flags) {
		this.flags = flags;
	}

	public String getSetTo() {
		return setTo;
	}

	public void setSetTo(String setTo) {
		this.setTo = setTo;
	}

	public Long getHelpId() {
		return helpId;
	}

	public void setHelpId(Long helpId) {
		this.helpId = helpId;
	}

	public Long getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Long orderIndex) {
		this.orderIndex = orderIndex;
	}

	public int getWizStyle() {
		return wizStyle;
	}

	public void setWizStyle(int wizStyle) {
		this.wizStyle = wizStyle;
	}

    public String getDisplayLabelName() {
        return displayLabelName;
    }

    public void setDisplayLabelName(String displayLabelName) {
        this.displayLabelName = displayLabelName;
    }

    public String toString() {
        return "labelName: " + labelName +
            "displayLabelName: " + displayLabelName +
            "\nlookup: " + lookup +
            "\nparameterName: " + parameterName +
            "\ncurrentValue: " + currentValue +
            "\nfieldName: " + fieldName +
            "\nfieldType: " + fieldType +
            "\nflags: " + flags +
            "\nmasterSettingObjectId: " + masterSettingObjectId +
            "\nsetTo: " + setTo +
            "\nhelpId: " + helpId +
            "\norderIndex: " + orderIndex +
            "\nwizStyle: " + wizStyle +
            "\nvalues: " + listOfValues;
    }
}
