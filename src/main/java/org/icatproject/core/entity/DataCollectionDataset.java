package org.icatproject.core.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Comment("Represents a many-to-many relationship between a DataCollection and its datasets.")
@SuppressWarnings("serial")
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "DATACOLLECTION_ID", "DATASET_ID" }) })
public class DataCollectionDataset extends EntityBaseBean implements Serializable {

	@JoinColumn(name = "DATACOLLECTION_ID", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private DataCollection dataCollection;

	@JoinColumn(name = "DATASET_ID", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Dataset dataset;

	public DataCollection getDataCollection() {
		return dataCollection;
	}

	public Dataset getDataset() {
		return dataset;
	}

	public void setDataCollection(DataCollection dataCollection) {
		this.dataCollection = dataCollection;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}

}
