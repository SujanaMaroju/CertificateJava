package com.javaprojects.certifications.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "users")
public class User {

    private String email;
    private List<EmpCertification> certifications;

    public String getEmail() {
        return email;
    }

    public List<EmpCertification> getCertifications() {
        return certifications;
    }

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCertifications(List<EmpCertification> certifications) {
		this.certifications = certifications;
	}
}
