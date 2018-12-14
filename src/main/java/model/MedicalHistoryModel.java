package model;

public class MedicalHistoryModel {
	private String reportTitle;
	private String anamneseDesc;
	private String diagnosisDesc;
	private String procedureDesc;

	public MedicalHistoryModel(String reportTitle, String anamneseDesc, String diagnosisDesc, String procedureDesc) {
		this.reportTitle = reportTitle;
		this.anamneseDesc = anamneseDesc;
		this.diagnosisDesc = diagnosisDesc;
		this.procedureDesc = procedureDesc;
	}

	public String getReportTitle() {
		return this.reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String getAnamneseDesc() {
		return this.anamneseDesc;
	}

	public void setAnamneseDesc(String anamneseDesc) {
		this.anamneseDesc = anamneseDesc;
	}

	public String getDiagnosisDesc() {
		return this.diagnosisDesc;
	}
	
	public void setDiagnosisDesc(String diagnosisDesc) {
		this.diagnosisDesc = diagnosisDesc;
	}

	public String getProcedureDesc() {
		return this.procedureDesc;
	}
	
	public void setProcedureDesc(String procedureDesc) {
		this.procedureDesc = procedureDesc;
	}

}
