package amri.shodiq.authorization.model;

import java.util.Date;

/**
 *
 * @author amri.s
 */
public class Patient {
    public static final int MALE = 1;
    public static final int FEMALE = 0;
    
    public long patientId;
    public String nationalIdNumber;
    public String realName;
    public Date dateOfBirth;
    public int gender;
    public String motherName;
    public String fatherName;
    public String responsiblePerson;

    public Patient() {
    }

    public Patient(long patientId, String nationalIdNumber, String realName, Date dateOfBirth, int gender, String motherName, String fatherName, String responsiblePerson) {
        this.patientId = patientId;
        this.nationalIdNumber = nationalIdNumber;
        this.realName = realName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.responsiblePerson = responsiblePerson;
    }
    
    
}
