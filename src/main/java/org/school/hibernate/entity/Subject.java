package org.school.hibernate.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "subject")
public class Subject {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type = "pg-uuid")
	private UUID id;

	@Column(name = "subj_name")
	private String subjectName;

	@Column(name = "class")
	private int classId;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "subjects")
	private Set<Teacher> teachers = new HashSet<Teacher>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
	private Set<Grades> grades = new HashSet<Grades>();

	@OneToOne
	@JoinColumn(name = "cabinet_id", unique = true, nullable = false)
	private Cabinet cabinet;

	public Cabinet getCabinet() {
		return cabinet;
	}

	public void setCabinet(Cabinet cabinet) {
		this.cabinet = cabinet;
	}

	public Set<Grades> getGrades() {
		return grades;
	}

	public void setGrades(Set<Grades> grades) {
		this.grades = grades;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public Set<Teacher> getTeachers() {
		System.out.println("! getTeachers");
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	public UUID getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cabinet == null) ? 0 : cabinet.hashCode());
		result = prime * result + classId;
		result = prime * result + ((grades == null) ? 0 : grades.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((subjectName == null) ? 0 : subjectName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		if (cabinet == null) {
			if (other.cabinet != null)
				return false;
		} else if (!cabinet.equals(other.cabinet))
			return false;
		if (classId != other.classId)
			return false;
		if (grades == null) {
			if (other.grades != null)
				return false;
		} else if (!grades.equals(other.grades))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (subjectName == null) {
			if (other.subjectName != null)
				return false;
		} else if (!subjectName.equals(other.subjectName))
			return false;
		if (teachers == null) {
			if (other.teachers != null)
				return false;
		} else if (!teachers.equals(other.teachers))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", subjectName=" + subjectName
				+ ", classId=" + classId + "]";
	}

}
