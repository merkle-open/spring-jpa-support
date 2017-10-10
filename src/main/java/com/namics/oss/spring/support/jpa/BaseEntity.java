/*
 * Copyright 2000-2015 Namics AG. All rights reserved.
 */

package com.namics.oss.spring.support.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Base entity, you must provide concrete class name as generic, to provide flexible chained accessors.
 * e.G. <code>class Entity extends BaseEntity&lt;Entity,Long&gt;</code>
 *
 * @author aschaefer, Namics AG
 * @since 31.03.2015
 */
@MappedSuperclass
public class BaseEntity<ENTITY extends BaseEntity, PK extends Serializable> implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private PK id;

	@Version
	private int versionId;

	private LocalDateTime creationDate;

	private LocalDateTime lastModifiedDate;

	/**
	 * Sets the update and creation time stamps.
	 */
	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
		lastModifiedDate = LocalDateTime.now();
		if (creationDate == null) {
			creationDate = LocalDateTime.now();
		}
	}


	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public PK getId() {
		return id;
	}

	public void setId(final PK id) {
		this.id = id;
	}

	/**
	 * Must be {@link Transient} in order to ensure that no JPA provider complains because of a missing setter.
	 */
	@Transient
	public boolean isNew() {
		return null == getId();
	}

	@Override
	public String toString() {
		return toStringBase();
	}

	/**
	 * Use this base string and add custom fields as you like.
	 *
	 * @return Entity{id=x, versionId=y, creationDate=z, lastModifiedDate=a
	 */
	public String toStringBase() {
		return this.getClass().getName() + "{" +
		       "id=" + id +
		       ", versionId=" + versionId +
		       ", creationDate=" + creationDate +
		       ", lastModifiedDate=" + lastModifiedDate;
	}

	@Override
	public boolean equals(Object obj) {

		if (null == obj) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		if (!getClass().equals(obj.getClass())) {
			return false;
		}

		BaseEntity<?, ?> that = (BaseEntity<?, ?>) obj;

		return null == this.getId() ? false : this.getId().equals(that.getId());
	}


	@Override
	public int hashCode() {

		int hashCode = 17;

		hashCode += null == getId() ? 0 : getId().hashCode() * 31;

		return hashCode;
	}

	public ENTITY id(PK id) {
		setId(id);
		return self();
	}

	public ENTITY versionId(int versionId) {
		setVersionId(versionId);
		return self();
	}

	public ENTITY creationDate(LocalDateTime creationDate) {
		setCreationDate(creationDate);
		return self();
	}

	public ENTITY lastModifiedDate(LocalDateTime lastModifiedDate) {
		setLastModifiedDate(lastModifiedDate);
		return self();
	}

	public ENTITY self() {
		return (ENTITY) this;
	}
}
