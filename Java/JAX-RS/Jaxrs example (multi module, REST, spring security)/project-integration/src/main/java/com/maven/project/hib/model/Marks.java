package com.maven.project.hib.model;

public class Marks implements java.io.Serializable {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = -5799532254316416057L;

	// Properties--------------------------------------------------
	private long userId;
	private Double mathsScore;
	private Double scienceScore;
	private Double literatureScore;
	private Double percentage;

	// Constructors--------------------------------------------------
	public Marks() {
	}

	public Marks(long userId) {
		this.userId = userId;
	}

	public Marks(long userId, Double mathsScore, Double scienceScore, Double literatureScore, Double percentage) {
		this.userId = userId;
		this.mathsScore = mathsScore;
		this.scienceScore = scienceScore;
		this.literatureScore = literatureScore;
		this.percentage = percentage;
	}

	// Getters & Setters-------------------------------------------------------
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Double getMathsScore() {
		return this.mathsScore;
	}

	public void setMathsScore(Double mathsScore) {
		this.mathsScore = mathsScore;
	}

	public Double getScienceScore() {
		return this.scienceScore;
	}

	public void setScienceScore(Double scienceScore) {
		this.scienceScore = scienceScore;
	}

	public Double getLiteratureScore() {
		return this.literatureScore;
	}

	public void setLiteratureScore(Double literatureScore) {
		this.literatureScore = literatureScore;
	}

	public Double getPercentage() {
		return this.percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

}
