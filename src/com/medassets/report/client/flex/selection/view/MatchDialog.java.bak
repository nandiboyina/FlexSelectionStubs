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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;

public class MatchDialog extends Composite implements HasText {

	private static MatchDialogUiBinder uiBinder = GWT
			.create(MatchDialogUiBinder.class);
	@UiField
	ListBox matchList;
	@UiField
	Button buttonOk;
	@UiField
	Button buttonCancel;
	@UiField
	DialogBox dialog;
	@UiField
	Label source;
	private Widget widget;

	interface MatchDialogUiBinder extends UiBinder<Widget, MatchDialog> {
	}

	public MatchDialog() {
		super();
		widget = uiBinder.createAndBindUi(this);
		initWidget(uiBinder.createAndBindUi(this));

	}

	public MatchDialog(String firstName) {
		Widget local = uiBinder.createAndBindUi(this);
		source.setText(firstName);

		initWidget(local);

	}

	public Widget asWidget() {

		return widget;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub

	}

	@UiHandler("buttonOk")
	void handleClick(ClickEvent e) {
		dialog.hide();

	}

	@UiHandler("buttonCancel")
	void handleClick1(ClickEvent e) {
		dialog.hide();
	}

}
