package com.javaprojects.certifications.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class EmpCertification {
    private String cid;
    private String certificationName;
    private int status;
    private String cost;
    private String type;
    private String level;
    private String experience;
    private String prerequisite;
    private String stateDate;
    private String endDate;

    public Object getCid() {
        return cid;
    }
    public int getStatus() {
        return status;
    }

    public String getStateDate() {
        return stateDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setStateDate(String stateDate) {
        this.stateDate = stateDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
	public String getCertificationName() {
		return certificationName;
	}
	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getPrerequisite() {
		return prerequisite;
	}
	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
}
