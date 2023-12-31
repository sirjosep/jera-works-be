package com.lawencon.candidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_candidate_document")
public class CandidateDocument extends BaseEntity {

	@Column(name = "document_code", length = 5, nullable = false)
	private String documentCode;

	@OneToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;

	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;

	@Column(name = "document_type_id")
	private String documentType;

	public String getDocumentCode() {
		return documentCode;
	}

	public void setDocumentCode(String documentCode) {
		this.documentCode = documentCode;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

}
