package amri.shodiq.authorization.model;

/**
 *
 * @author amri.s
 */
public class MedicalRecord {
    public long medicalRecordNumber;
    public long patientId;

    public MedicalRecord() {
    }

    public MedicalRecord(long medicalRecordNumber, long patientId) {
        this.medicalRecordNumber = medicalRecordNumber;
        this.patientId = patientId;
    }
    
    
}
