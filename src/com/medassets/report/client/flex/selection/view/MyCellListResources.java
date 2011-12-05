package com.medassets.report.client.flex.selection.view;

import com.google.gwt.user.cellview.client.CellList.Style;
import com.google.gwt.user.cellview.client.CellList;
interface MyCellListResources extends CellList.Resources {
    @Source({"CellList.css"})
    @Override
    public Style cellListStyle();
  }