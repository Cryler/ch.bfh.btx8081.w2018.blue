package model;

public class MedicalHistoryModel {
	private String reportTitle;
	private String diagnosisTitle;
	private String diagnosisDesc;
	private String procedureTitle;
	private String procedureDesc;

	public MedicalHistoryModel(String reportTitle, String diagnosisTitle, String diagnosisDesc, String procedureTitle,
			String procedureDesc) {
		this.reportTitle = reportTitle;
		this.diagnosisTitle = diagnosisTitle;
		this.diagnosisDesc = diagnosisDesc;
		this.procedureTitle = procedureTitle;
		this.procedureDesc = procedureTitle;
	}
	
	public String getReportTitle() {
		return this.reportTitle;
	}
	
	public String getDiagnosisTitle () {
		return this.diagnosisTitle;
	}
	
	public String getDiagnosisDesc () {
		return this.diagnosisDesc;
	}
	
	public String getProcedureTitle() {
		return this.procedureTitle;
	}
	
	public String getProcedureDesc() {
		return this.procedureDesc;
	}
	
}
