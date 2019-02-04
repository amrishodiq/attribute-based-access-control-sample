package amri.shodiq.authorization.model;

import java.util.Date;

/**
 *
 * @author amri.s
 */
public class MedicalRecordItem {
    public long medicalRecordItemId;
    public long medicalRecordNumber;
    public String doctor;
    public Date visitDate;
    public String symptoms;
    public String diagnoses;
    public String disease;
    public String treatment;
    public String prescriptions;
    public boolean healed;

    public MedicalRecordItem() {
    }

    public MedicalRecordItem(long medicalRecordItemId, long medicalRecordNumber, String doctor, Date visitDate, String symptoms, String diagnoses, String disease, String treatment, String prescriptions, boolean healed) {
        this.medicalRecordItemId = medicalRecordItemId;
        this.medicalRecordNumber = medicalRecordNumber;
        this.doctor = doctor;
        this.visitDate = visitDate;
        this.symptoms = symptoms;
        this.diagnoses = diagnoses;
        this.disease = disease;
        this.treatment = treatment;
        this.prescriptions = prescriptions;
        this.healed = healed;
    }
    
    
}
