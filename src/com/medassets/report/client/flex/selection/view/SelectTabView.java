package com.medassets.report.client.flex.selection.view;






import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import com.google.gwt.cell.client.AbstractSafeHtmlCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.medassets.report.client.flex.selection.presenter.SelectTabPresenter;
import com.medassets.report.shared.FlexSelectionParamItemDTO;
import com.medassets.report.shared.ParamAvailableItemValue;

public class SelectTabView implements SelectTabPresenter.MyView {

	private static String ORDER = "ASC";
	private final Widget widget;
	CellTableResource resource = GWT.create(CellTableResource.class);
	//CellTableResource rightResource = (Resource)GWT.create(RightCellTableResource.class);
	public interface Binder extends UiBinder<Widget, SelectTabView> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	


	@UiField
	ListBox leftSideParametersListBox;

	@UiField
	AbsolutePanel leftSideParameterCodesListPanel;

	@UiField(provided = true)
	CellTable<ParamAvailableItemValue> leftSideParameterCodesTable = new CellTable<ParamAvailableItemValue>(
			10, resource);

	@UiField
	Button returnBtn;

	@UiField
	Button rightMoveBtn;

	@UiField
	Button leftMoveBtn;

	@UiField
	Button andOrBtn;

	@UiField
	Button matchBtn;

	@UiField(provided = true)
	CellTable<String> rightSideInclusionsListTable = new CellTable<String>(10, (Resources)GWT.create(RightCellTableResource.class));

	@UiField
	Label parameterLbl;

	@UiField
	Button searchBtn;

	private TextColumn<ParamAvailableItemValue> codeColumn;
	
	// Create a data provider.
	private ListDataProvider<ParamAvailableItemValue> leftSideParamCodesTableDataProvider;

	//private SingleSelectionModel<ParamAvailableItemValue> leftSideParamCodesRowSelectionModel;
	private MultiSelectionModel<ParamAvailableItemValue> leftSideParamCodesRowSelectionModel;

	private StringBuffer INCLUSION_STRING_PREPARATION = new StringBuffer(
			"AND  ");

	private Map<String, String> rightSideInclusionsStringMap = new HashMap<String, String>();

	private Map<String, List<ParamAvailableItemValue>> rightSideInclusionCodeObjectsListMap = new HashMap<String, List<ParamAvailableItemValue>>();

	private SingleSelectionModel<String> rightSideInclusionsRowSelectionModel;

	private ListDataProvider<String> rightSideInclusionsTableDataProvider;

	private SortedMap<String, FlexSelectionParamItemDTO> parametersListViewMap;

	private String selectedParameterKeyName;
	String selectedParamCodeRec ="";

	@UiConstructor
	public SelectTabView() {
		super();
		widget = binder.createAndBindUi(this);
	}

	public Widget asWidget() {
		buildWidget();
		return widget;
	}

	public void buildWidget() {
		buildLeftSideListCodesTable();

		buildRightPanelSelectedCellTable();

		buildEvents();

		disableButton();
	}

	private void buildRightPanelSelectedCellTable() {
		TextColumn<String> selectedStringColumn = new TextColumn<String>() {
			@Override
			public String getValue(String item) {
				return item;
			}
		};
		rightSideInclusionsListTable.setStyleName("RightCellTable");

		rightSideInclusionsListTable.addColumn(selectedStringColumn);
	

		rightSideInclusionsTableDataProvider = new ListDataProvider<String>();
		// Connect the table to the data provider.
		rightSideInclusionsTableDataProvider
				.addDataDisplay(rightSideInclusionsListTable);

		// Add a selection model to handle user selection.
		rightSideInclusionsRowSelectionModel = new SingleSelectionModel<String>();
		rightSideInclusionsListTable
				.setSelectionModel(rightSideInclusionsRowSelectionModel);
	}

	private void disableButton() {
		rightMoveBtn.setEnabled(false);
		leftMoveBtn.setEnabled(false);
		andOrBtn.setEnabled(false);
		matchBtn.setEnabled(false);
		returnBtn.setEnabled(false);
		searchBtn.setEnabled(false);
	}

	private void buildEvents() {
		returnBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				leftSideParametersListBox.setVisible(true);
				leftSideParameterCodesListPanel.setVisible(false);
				leftSideParamCodesTableDataProvider.getList().clear();
				INCLUSION_STRING_PREPARATION.setLength(0);
				// selectedParamCodeRec = " ";
				INCLUSION_STRING_PREPARATION.append("AND ");
			   
			
			
				returnBtn.setEnabled(false);
				selectedParameterKeyName = null;
			}
		});

		leftSideParamCodesRowSelectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						rightMoveBtn.setEnabled(true);
						leftMoveBtn.setEnabled(false);
						andOrBtn.setEnabled(true);
						matchBtn.setEnabled(true);
					}
				});

		rightMoveBtn.addClickHandler(new ClickHandler() {

			@Override
			/**
			 * Move 
			 */
			public void onClick(ClickEvent event) {
				
				selectedParamCodeRec = " ";
				rightSideInclusionsTableDataProvider.getList().clear();
				/*ParamAvailableItemValue selectedParamCodeObj = leftSideParamCodesRowSelectionModel
						.getSelectedObject();*/
				ParamAvailableItemValue tempCodeDescription = new ParamAvailableItemValue();
				List<ParamAvailableItemValue> includeList = new ArrayList<ParamAvailableItemValue>();
				
				
				Set<ParamAvailableItemValue> singleRowSet = new HashSet<ParamAvailableItemValue>();
				List<ParamAvailableItemValue> selectedParamObjList = new ArrayList<ParamAvailableItemValue>();
				Set<ParamAvailableItemValue> selectedParamCodeObjSet = leftSideParamCodesRowSelectionModel.getSelectedSet();
				
			
				List<ParamAvailableItemValue> rightsideSelectionList = new ArrayList<ParamAvailableItemValue>(selectedParamCodeObjSet);
				if(rightSideInclusionCodeObjectsListMap!= null && rightSideInclusionCodeObjectsListMap.get(selectedParameterKeyName)!=null)
				{
					rightsideSelectionList.addAll(rightSideInclusionCodeObjectsListMap.get(selectedParameterKeyName));
				}
				
				getSortedList(rightsideSelectionList);
				for(int i=0 ;i<rightsideSelectionList.size() ;i++)
				{
                	ParamAvailableItemValue selectedParamCodeObj = rightsideSelectionList.get(i);
                	singleRowSet.add(selectedParamCodeObj);
                
    				selectedParamObjList.add(selectedParamCodeObj);
    				
    				
        			if(tempCodeDescription.getCode() != null)
        			{
        				if(tempCodeDescription.getIndex()+1== selectedParamCodeObj.getIndex())
        				{
        					singleRowSet.remove(selectedParamCodeObj);
        					singleRowSet.remove(tempCodeDescription);
        					includeSingleSet(singleRowSet);
        					singleRowSet.removeAll(singleRowSet);
        					if(!includeList.contains(tempCodeDescription))
        					{
        						includeList.add(tempCodeDescription);
        					}
        					includeList.add(selectedParamCodeObj);
        				
        				 }
        				else
        				{
        					includerightside(includeList);
        					singleRowSet.add(selectedParamCodeObj);
        					
        				}
        				if(i== rightsideSelectionList.size()-1)
        				{
        					includerightside(includeList);
        					includeSingleSet(singleRowSet);
        				}
        			}
        			tempCodeDescription = selectedParamCodeObj;
        			leftSideParamCodesTableDataProvider.getList().remove(selectedParamCodeObj);
                	if(rightsideSelectionList.size()==1)
                	{
                		includeSingleSet(singleRowSet);
                	}
				}
  			
				/*selectedParamCodeRec = "\n "
						+ selectedParamCodeObj.getCode() + "  "
						+ selectedParamCodeObj.getDescription();
				List<ParamAvailableItemValue> selectedParamObjList = new ArrayList<ParamAvailableItemValue>();
				selectedParamObjList.add(selectedParamCodeObj);
				//Window.alert("selectedparam"+selectedParamCodeObj);
				if (!rightSideInclusionsStringMap.containsKey(selectedParameterKeyName)) 
				{
					rightSideInclusionsStringMap.put(selectedParameterKeyName,INCLUSION_STRING_PREPARATION.toString()+ selectedParamCodeRec.toString());
					rightSideInclusionCodeObjectsListMap.put(selectedParameterKeyName, selectedParamObjList);
				} 
				else 
				{
					String mergedParamString = rightSideInclusionsStringMap.get(selectedParameterKeyName)+ selectedParamCodeRec.toString();
					rightSideInclusionsStringMap.put(selectedParameterKeyName,mergedParamString);
					rightSideInclusionCodeObjectsListMap.get(selectedParameterKeyName).add(selectedParamCodeObj);
				}

				leftSideParamCodesTableDataProvider.getList().remove(selectedParamCodeObj);*/
				//Window.alert("  "+INCLUSION_STRING_PREPARATION.toString()+" test "+ selectedParamCodeRec);
				rightSideInclusionsStringMap.put(selectedParameterKeyName,INCLUSION_STRING_PREPARATION.toString()+ selectedParamCodeRec);
				rightSideInclusionCodeObjectsListMap.put(selectedParameterKeyName, selectedParamObjList);
				
			
				rightSideInclusionsTableDataProvider.getList().clear();
				
				rightSideInclusionsTableDataProvider.getList().addAll(new ArrayList<String>(rightSideInclusionsStringMap.values()));
				
				
				rightMoveBtn.setEnabled(false);
			
			
			}
		});

		rightSideInclusionsRowSelectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						leftMoveBtn.setEnabled(true);
						rightMoveBtn.setEnabled(false);
					}
				});

		leftMoveBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String rightPanelSelectedRow = rightSideInclusionsRowSelectionModel
						.getSelectedObject();
				
				for (String paramItemStringKey : rightSideInclusionsStringMap
						.keySet()) {
			
					if (rightSideInclusionsStringMap.get(paramItemStringKey)
							.equals(rightPanelSelectedRow)) {
						if (paramItemStringKey.equals(selectedParameterKeyName)) {
							List<ParamAvailableItemValue> paramItemList = rightSideInclusionCodeObjectsListMap
									.get(paramItemStringKey);
							leftSideParamCodesTableDataProvider.getList()
									.addAll(paramItemList);
							Collections.sort(
									leftSideParamCodesTableDataProvider
											.getList(),
									new Comparator<ParamAvailableItemValue>() {

										public int compare(
												ParamAvailableItemValue o1,
												ParamAvailableItemValue o2) {
											if (ORDER.equals("ASC")) {
												return compare1(o1, o2);
											} else {
												return compare1(o2, o1);
											}
										}
									}

							);
							leftSideParamCodesTableDataProvider.refresh();
							//break;
						}
						rightSideInclusionCodeObjectsListMap
								.remove(paramItemStringKey);
						
					}
					
				}
				
				rightSideInclusionsStringMap.values().remove(
						rightPanelSelectedRow);
				
				rightSideInclusionsTableDataProvider.getList().clear();
				rightSideInclusionsTableDataProvider.getList().addAll(
						new ArrayList<String>(rightSideInclusionsStringMap
								.values()));

				leftSideParameterCodesTable.getColumnSortList()
						.push(codeColumn);
				leftMoveBtn.setEnabled(false);
			}
		});

		
	
		}
	

	private void prepareSelectedString(String pSelected) {
		INCLUSION_STRING_PREPARATION.append(pSelected);
	}

	
	private void includerightside(List<ParamAvailableItemValue> includeList)
	{

		if(includeList.size()>0)
		{
			
			selectedParamCodeRec =selectedParamCodeRec+"\n between includes ("+includeList.size()+") \n "+includeList.get(0).getCode()+"  "+includeList.get(0).getDescription()+
		" \n"+includeList.get(includeList.size()-1).getCode()+" "+includeList.get(includeList.size()-1).getDescription();
			
		includeList.removeAll(includeList);
		
		}
	}
	
	private void includeSingleSet(Set<ParamAvailableItemValue> singleSet)
	{
		if(singleSet.size()>0){
			List<ParamAvailableItemValue> templist = new ArrayList<ParamAvailableItemValue>(singleSet);
			getSortedList(templist);
			for(int i=0 ;i<templist.size() ;i++)
			{
				
				selectedParamCodeRec = selectedParamCodeRec+"\n "+templist.get(i).getCode()+" "+templist.get(i).getDescription();
				
			}
			
		}
	}
	
	private void getSortedList(List<ParamAvailableItemValue> list)
	{
		Collections.sort(list , new Comparator<ParamAvailableItemValue>()
				{
					public int compare(ParamAvailableItemValue obj1, ParamAvailableItemValue obj2) {
						Integer name = ((ParamAvailableItemValue) obj1).getIndex();
						Integer name1 = ((ParamAvailableItemValue) obj2).getIndex();
						return name.compareTo(name1);
						}
				});
	}
	/**
	 * 
	 */
	private void buildLeftSideListCodesTable() {

		leftSideParametersListBox.setVisibleItemCount(32000);
	
		// Create code column.
		codeColumn = new TextColumn<ParamAvailableItemValue>() {
			@Override
			public String getValue(ParamAvailableItemValue item) {
				return item.getCode();
			}
		};

		// Create description column.
		TextColumn<ParamAvailableItemValue> nameColumn = new TextColumn<ParamAvailableItemValue>() {
			@Override
			public String getValue(ParamAvailableItemValue item) {
				return item.getDescription();
			}
		};

		// Make the name column sortable.
		codeColumn.setSortable(true);
		nameColumn.setSortable(true);

		leftSideParameterCodesTable.setHeight("10PX");
		leftSideParameterCodesTable.setStyleName("CellTable");

		leftSideParameterCodesTable.addColumn(codeColumn, "Code");
		leftSideParameterCodesTable.addColumn(nameColumn, "Name");
		

		leftSideParamCodesTableDataProvider = new ListDataProvider<ParamAvailableItemValue>();

		// Connect the table to the data provider.
		leftSideParamCodesTableDataProvider
				.addDataDisplay(leftSideParameterCodesTable);
		
		

		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<ParamAvailableItemValue> codeColumnSortHandler = new ListHandler<ParamAvailableItemValue>(
				leftSideParamCodesTableDataProvider.getList());
		codeColumnSortHandler.setComparator(codeColumn,
				new Comparator<ParamAvailableItemValue>() {
					public int compare(ParamAvailableItemValue o1,
							ParamAvailableItemValue o2) {

						return compare1(o1, o2);
					}
					/*
					 * if (o1 == o2) { return 0; }
					 * 
					 * // Compare the name columns. if (o1 != null) { return (o2
					 * != null) ? o1.getCode().compareTo(o2.getCode()) : 1; }
					 * return -1; }
					 */
				});

		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<ParamAvailableItemValue> nameColumnSortHandler = new ListHandler<ParamAvailableItemValue>(
				leftSideParamCodesTableDataProvider.getList());
		nameColumnSortHandler.setComparator(nameColumn,
				new Comparator<ParamAvailableItemValue>() {
					public int compare(ParamAvailableItemValue o1,
							ParamAvailableItemValue o2) {

						return compare1(o1, o2);
					}
					/*
					 * if (o1 == o2) { return 0; }
					 * 
					 * // Compare the name columns. if (o1 != null) { return (o2
					 * != null) ?
					 * o1.getDescription().compareTo(o2.getDescription()) : 1; }
					 * return -1; }
					 */
				});

		leftSideParameterCodesTable.addColumnSortHandler(codeColumnSortHandler);
		leftSideParameterCodesTable.addColumnSortHandler(nameColumnSortHandler);
		leftSideParameterCodesTable
				.addColumnSortHandler(new ColumnSortEvent.Handler() {

					public void onColumnSort(ColumnSortEvent event) {
						if (event.isSortAscending())
							ORDER = "ASC";
						else
							ORDER = "DSC";

						// Window.alert("Order"+event.isSortAscending());
					}
				});

		// We know that the data is sorted alphabetically by default.
		leftSideParameterCodesTable.getColumnSortList().push(codeColumn);
		leftSideParameterCodesTable.getColumnSortList().push(nameColumn);

		// Add a selection model to handle user selection.
		leftSideParamCodesRowSelectionModel = new MultiSelectionModel<ParamAvailableItemValue>();
		leftSideParameterCodesTable.setSelectionModel(leftSideParamCodesRowSelectionModel);

		leftSideParameterCodesListPanel.setVisible(false);
	}

	/**
	 * Verify the code is already included in right panel or not. if so, it does
	 * not require to populate in the left panel.
	 * 
	 * @param paramCodeObject
	 *            ParamAvailableItemValue
	 * @return boolean
	 */
	private boolean isCodeIncludedAlready(ParamAvailableItemValue paramCodeObject) {
		for (Entry<String, List<ParamAvailableItemValue>> includedMap : rightSideInclusionCodeObjectsListMap
				.entrySet()) {
			String paramKeyName = includedMap.getKey();
			List<ParamAvailableItemValue> paramObjList = includedMap.getValue();

			if (paramKeyName.equals(selectedParameterKeyName)) {
				for (ParamAvailableItemValue paramCodeObj : paramObjList) {
					if (paramCodeObj.getCode()
							.equals(paramCodeObject.getCode())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public ListBox leftSideParametersListBox_eventBox() {
		return leftSideParametersListBox;
	}

	@Override
	public void manipulateViewControls() {
		int selectedIndex = leftSideParametersListBox.getSelectedIndex();

		if (selectedIndex > -1) {
			selectedParameterKeyName = leftSideParametersListBox
					.getValue(selectedIndex);
		}
		parameterLbl.setText(selectedParameterKeyName);
		prepareSelectedString(parametersListViewMap.get(
				selectedParameterKeyName).getDescription());

		leftSideParametersListBox.setVisible(false);
		leftSideParameterCodesListPanel.setVisible(true);
		returnBtn.setEnabled(true);
	}

	@Override
	public void setParametersListViewMapObj(
			SortedMap<String, FlexSelectionParamItemDTO> parametersListViewMap) {
		this.parametersListViewMap = parametersListViewMap;
	}

	@Override
	public ListDataProvider<ParamAvailableItemValue> getLeftSideParamCodesTableDataProviderObj() {
		return leftSideParamCodesTableDataProvider;
	}

	@Override
	public void setSelectedParameterKeyNameObj(String selectedParameterKeyName) {
		this.selectedParameterKeyName = selectedParameterKeyName;
	}

	@Override
	public Map<String, List<ParamAvailableItemValue>> getRightSideInclusionCodeObjectsListMapObj() {
		return rightSideInclusionCodeObjectsListMap;
	}

	@Override
	public Button getReturnBtn() {
		return returnBtn;
	}

	public int compare1(ParamAvailableItemValue o1, ParamAvailableItemValue o2) {
		String s1 = null;
		String s2 = null;

		s1 = (String) o1.toString();
		s2 = (String) o2.toString();

		int thisMarker = 0;
		int thisNumericChunk = 0;
		String thisChunk = new String();
		int thatMarker = 0;
		int thatNumericChunk = 0;
		String thatChunk = new String();

		while ((thisMarker < s1.length()) && (thatMarker < s2.length())) {
			char thisCh = s1.charAt(thisMarker);
			char thatCh = s2.charAt(thatMarker);

			thisChunk = "";
			thatChunk = "";

			while ((thisMarker < s1.length()) && inChunk(thisCh, thisChunk)) {
				thisChunk = thisChunk + thisCh;
				thisMarker++;
				if (thisMarker < s1.length()) {
					thisCh = s1.charAt(thisMarker);
				}
			}

			while ((thatMarker < s2.length()) && inChunk(thatCh, thatChunk)) {
				thatChunk = thatChunk + thatCh;
				thatMarker++;
				if (thatMarker < s2.length()) {
					thatCh = s2.charAt(thatMarker);
				}
			}

			int thisChunkType = isIn(thisChunk.charAt(0), numbers) ? 1 : 0;
			int thatChunkType = isIn(thatChunk.charAt(0), numbers) ? 1 : 0;

			// If both chunks contain numeric characters, sort them
			// numerically
			int result = 0;

			if ((thisChunkType == 1) && (thatChunkType == 1)) {
				thisNumericChunk = Integer.parseInt(thisChunk);
				thatNumericChunk = Integer.parseInt(thatChunk);
				if (thisNumericChunk < thatNumericChunk) {
					result = -1;
				}
				if (thisNumericChunk > thatNumericChunk) {
					result = 1;
				}
			} else {
				result = thisChunk.compareTo(thatChunk);
			}

			if (result != 0) {
				return result;
			}
		}

		return 0;
	}

	char[] numbers = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

	private boolean isIn(char ch, char[] chars) {
		for (int i = 0; i < chars.length; i++) {
			if (ch == chars[i]) {
				return true;
			}
		}
		return false;
	}

	private boolean inChunk(char ch, String s) {
		if (s.length() == 0) {
			return true;
		}

		char s0 = s.charAt(0);
		int chunkType = 0;
		// 0 = alphabetic, 1 = numeric

		if (isIn(s0, numbers)) {
			chunkType = 1;
		}

		if ((chunkType == 0) && (isIn(ch, numbers))) {
			return false;
		}
		if ((chunkType == 1) && (!isIn(ch, numbers))) {
			return false;
		}

		return true;
	}

	class TextCell extends AbstractSafeHtmlCell<String> {

		/**
		 * Constructs a TextCell that uses a {@link SimpleSafeHtmlRenderer} to
		 * render its text.
		 */
		public TextCell() {
			super(SimpleSafeHtmlRenderer.getInstance());
		}

		/**
		 * Constructs a TextCell that uses the provided {@link SafeHtmlRenderer}
		 * to render its text.
		 * 
		 * @param renderer
		 *            a {@link SafeHtmlRenderer SafeHtmlRenderer<String>}
		 *            instance
		 */
		public TextCell(SafeHtmlRenderer<String> renderer) {
			super(renderer);
		}

		@Override
		public void render(Context context, SafeHtml value, SafeHtmlBuilder sb) {
			if (value != null) {
				String str = value.asString();
				if (str.contains("\n")) {
					String temp[] = str.split("\n");
					for (int i = 0; i < temp.length; i++) {
						SafeHtml value1 = SafeHtmlUtils
								.fromSafeConstant(temp[i]);
						sb.append(value1);
						sb.appendHtmlConstant("<br/>");
					}
				} else {
					sb.append(value);
				}
			}
		}

	}

	public abstract class TextColumn<T> extends Column<T, String> {

		/**
		 * Construct a new TextColumn.
		 */
		public TextColumn() {
			super(new TextCell());
		}
	}

	
	
}
