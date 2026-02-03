package org.openmrs.module.orderexpansion.api.model;

import javax.persistence.PrePersist;

import org.openmrs.Concept;
import org.openmrs.OrderFrequency;
import org.openmrs.ServiceOrder;
import org.openmrs.module.orderexpansion.api.enums.RadiologyOrderStatus;

public class RadiologyOrder extends ServiceOrder {
	
	private static final long serialVersionUID = 1L;
	
	private Concept bodySite;
	
	private Concept modality;
	
	private Concept specimenSource;
	
	private String studyUuid;
	
	private Laterality laterality;
	
	private String clinicalHistory;
	
	private Integer numberOfRepeats;
	
	private RadiologyOrderStatus radiologyStatus;
	
	private OrderFrequency frequency;
	
	private Concept location;
	
	private RadiologyOrder relatedRadiologyOrder;
	
	public RadiologyOrder() {
	}
	
	@PrePersist
	public void setDefaults() {
		if (this.radiologyStatus == null) {
			this.radiologyStatus = RadiologyOrderStatus.PENDING;
		}
	}
	
	@Override
	public RadiologyOrder copy() {
		RadiologyOrder newOrder = new RadiologyOrder();
		super.copyHelper(newOrder);
		return newOrder;
	}
	
	@Override
	public RadiologyOrder cloneForDiscontinuing() {
		RadiologyOrder newOrder = new RadiologyOrder();
		super.cloneForDiscontinuingHelper(newOrder);
		return newOrder;
	}
	
	@Override
	public RadiologyOrder cloneForRevision() {
		RadiologyOrder newOrder = new RadiologyOrder();
		super.cloneForRevisionHelper(newOrder);
		return newOrder;
	}
	
	public Concept getBodySite() {
		return bodySite;
	}
	
	public void setBodySite(Concept bodySite) {
		this.bodySite = bodySite;
	}
	
	public Concept getModality() {
		return modality;
	}
	
	public void setModality(Concept modality) {
		this.modality = modality;
	}
	
	public Concept getSpecimenSource() {
		return specimenSource;
	}
	
	public void setSpecimenSource(Concept specimenSource) {
		this.specimenSource = specimenSource;
	}
	
	public String getStudyUuid() {
		return studyUuid;
	}
	
	public void setStudyUuid(String studyUuid) {
		this.studyUuid = studyUuid;
	}
	
	public Laterality getLaterality() {
		return laterality;
	}
	
	public void setLaterality(Laterality laterality) {
		this.laterality = laterality;
	}
	
	public String getClinicalHistory() {
		return clinicalHistory;
	}
	
	public void setClinicalHistory(String clinicalHistory) {
		this.clinicalHistory = clinicalHistory;
	}
	
	public Integer getNumberOfRepeats() {
		return numberOfRepeats;
	}
	
	public void setNumberOfRepeats(Integer numberOfRepeats) {
		this.numberOfRepeats = numberOfRepeats;
	}
	
	public RadiologyOrderStatus getRadiologyStatus() {
		return radiologyStatus;
	}
	
	public void setRadiologyStatus(RadiologyOrderStatus radiologyStatus) {
		this.radiologyStatus = radiologyStatus;
	}
	
	public OrderFrequency getFrequency() {
		return frequency;
	}
	
	public void setFrequency(OrderFrequency frequency) {
		this.frequency = frequency;
	}
	
	public Concept getLocation() {
		return location;
	}
	
	public void setLocation(Concept location) {
		this.location = location;
	}
	
	public RadiologyOrder getRelatedRadiologyOrder() {
		return relatedRadiologyOrder;
	}
	
	public void setRelatedRadiologyOrder(RadiologyOrder relatedRadiologyOrder) {
		this.relatedRadiologyOrder = relatedRadiologyOrder;
	}
	
}
