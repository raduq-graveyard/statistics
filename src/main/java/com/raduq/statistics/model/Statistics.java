package com.raduq.statistics.model;

import java.util.DoubleSummaryStatistics;

/**
 * Represents a Statistics object that will be returned via rest
 */
public class Statistics {

	private Double sum;
	private Double avg;
	private Double max;
	private Double min;
	private Long count;

	public Statistics(DoubleSummaryStatistics summary) {
		this.sum = summary.getSum();
		this.avg = summary.getAverage();
		this.min = summary.getMin();
		this.max = summary.getMax();
		this.count = summary.getCount();
	}

	public Double getSum() {
		return sum;
	}

	public Double getAvg() {
		return avg;
	}

	public Double getMax() {
		return max;
	}

	public Double getMin() {
		return min;
	}

	public Long getCount() {
		return count;
	}

}
