package com.javaprojects.certifications.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
public class Certification {
    private ObjectId id;
    private String cid;
    private String certificationName;
    private String cost;
    private String type;
    private String level;
    private String experience;
    private String prerequisite;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
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
}