package com.medassets.report.client.flex.selection.view;

import com.google.gwt.user.cellview.client.CellTable;

public interface CellTableResource extends CellTable.Resources 
{ 
   public interface CellTableStyle extends CellTable.Style {}; 
   @Source({"CellTable.css"}) 
   CellTableStyle cellTableStyle(); 
}; 