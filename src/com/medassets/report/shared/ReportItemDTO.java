package com.medassets.report.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ReportItemDTO implements IsSerializable {

    private String name;
    private String reportType;
    private String createdCode;
    private String modifiedCode;
    private String description;
    private String subTitle;
    private String category;
    private String folderName;
    private String templateName;

    private Long reportInstanceId;
	private Long reportTemplateId;
    private Long folderId;
    private Long createdDate;
    private Long modifiedDate;

    private String published;

    public static enum FIELDS {
        NAME,
        REPORT_TYPE,
        CREATED_CODE,
        CREATED_DATE,
        MODIFIED_CODE,
        MODIFIED_DATE,
        DESCRIPTION,
        CATEGORY,
        FOLDER_NAME,
        REPORT_INSTANCE_ID,
        REPORT_TEMPLATE_ID,
        TEMPLATE_NAME;
    }

    public ReportItemDTO() {
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getCreatedCode() {
		return createdCode;
	}

	public void setCreatedCode(String createdCode) {
		this.createdCode = createdCode;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedCode() {
		return modifiedCode;
	}

	public void setModifiedCode(String modifiedCode) {
		this.modifiedCode = modifiedCode;
	}

	public Long getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Long modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public Long getReportInstanceId() {
		return reportInstanceId;
	}

	public void setReportInstanceId(Long reportInstanceId) {
		this.reportInstanceId = reportInstanceId;
	}

	public Long getReportTemplateId() {
		return reportTemplateId;
	}

	public void setReportTemplateId(Long reportTemplateId) {
		this.reportTemplateId = reportTemplateId;
	}

	public Object getValue(FIELDS field) {
		switch (field) {
		case NAME:
			return getName();
		case REPORT_TYPE:
			return getReportType();
		case CREATED_CODE:
			return getCreatedCode();
		case CREATED_DATE:
			return getCreatedDate();
		case MODIFIED_CODE:
			return getModifiedCode();
		case MODIFIED_DATE:
			return getModifiedDate();
		case DESCRIPTION:
			return getDescription();
		case CATEGORY:
			return getCategory();
		case FOLDER_NAME:
			return getFolderName();
		case REPORT_INSTANCE_ID:
			return getReportInstanceId();
		case REPORT_TEMPLATE_ID:
			return getReportTemplateId();
		case TEMPLATE_NAME:
			return getTemplateName();
		default:
			return null;
		}
	}

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public boolean stringMatch(String text) {
		String lc = text == null ? "" : text.toLowerCase().trim();
        return getValue(FIELDS.NAME) != null && getValue(FIELDS.NAME).toString().toLowerCase().contains(lc);
    }

}
