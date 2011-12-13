package com.medassets.report.client.flex.selection.view;



import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AndDialog extends Composite implements HasText {

	@UiField
	DialogBox mainPanel;

	@UiField
	VerticalPanel verticalPanel;
	@UiField
	HorizontalPanel hpOne;
	@UiField
	HorizontalPanel hpTwo;
	@UiField
	HorizontalPanel hpFour;
	@UiField
	Label title;
	@UiField
	Button okButton;
	@UiField
	Button cancelButton;

	@UiField
	RadioButton r1;

	@UiField
	RadioButton r2;
	@UiField
	RadioButton r3;
	@UiField
	RadioButton r4;
	@UiField Label label1;
	
	@UiField TextBox textBox1;
	
	String a;
	private static AndDialogUiBinder uiBinder = GWT
			.create(AndDialogUiBinder.class);

	interface AndDialogUiBinder extends UiBinder<Widget, AndDialog> {
	}

	public AndDialog() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public AndDialog(String text) {

		Widget local = uiBinder.createAndBindUi(this);
		a = "All" + text;
		label1.setText(text);
		textBox1.setText("AND" + a + "List");
		initWidget(local);
	}

	String logic;

	@Override
	public String getText() {

		return logic;
	}

	@Override
	public void setText(String text) {

		logic = text;
	}

	/*
	 * @UiHandler("okButton") void handleClick(ClickEvent e) { SelectTabView
	 * sel=new SelectTabView();
	 * sel.rightSideInclusionsTableDataProvider.getList().addAll( new
	 * ArrayList<String>(sel.rightSideInclusionsStringMap .values()));
	 * 
	 * mainPanel.hide();
	 * 
	 * }
	 */

	@UiHandler("cancelButton")
	void handleClick1(ClickEvent e) {
		mainPanel.hide();
	}


	@UiHandler("r1")
	
	
	void handleClick2(ClickEvent e) {

		

		label1.setText(a);

		setText("AND");
		textBox1.setText(getText() + a + "List");

		mainPanel.center();

	}

	@UiHandler("r2")
	void handleClick3(ClickEvent e) {
		

		label1.setText(a);

		setText("OR");
		textBox1.setText(getText() + a + "List");

		mainPanel.center();
	

	}

	@UiHandler("r3")
	void handleClick4(ClickEvent e) {
		
		label1.setText(a);

		setText("AND NOT");
		textBox1.setText(getText() + a + "List");


		mainPanel.center();
		
	}

	@UiHandler("r4")
	void handleClick5(ClickEvent e) {
	
		label1.setText(a);

		setText("OR NOT");
		textBox1.setText(getText() + a + "List");

		mainPanel.center();
		
	}

}
