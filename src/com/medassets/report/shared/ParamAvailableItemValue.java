package com.medassets.report.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by IntelliJ IDEA.
 * User: pgoyal
 * Date: Sep 26, 2011
 * Time: 1:30:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ParamAvailableItemValue implements IsSerializable {

    private String code;
    private String description;
    private int index;


    public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        if (description == null) {
            return code;
        } else {
            return code + " " + description;
        }
    }
}
