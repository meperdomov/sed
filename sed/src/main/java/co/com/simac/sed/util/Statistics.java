package co.com.simac.sed.util;

import java.util.List;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class Statistics {

	public static double correlationPearson(final double[] xArray,
			final double[] yArray) throws IllegalArgumentException {
		SimpleRegression regression = new SimpleRegression();
		if (xArray.length != yArray.length) {
			throw new DimensionMismatchException(xArray.length, yArray.length);
		} else if (xArray.length < 2) {
			return -100;

		} else {
			for (int i = 0; i < xArray.length; i++) {
				regression.addData(xArray[i], yArray[i]);
			}
			return regression.getR();
		}
	}

	public static double standardDeviation(List<Float> values) {

		DescriptiveStatistics stats = new DescriptiveStatistics();
		for (Float f : values) {
			stats.addValue(f);
		}

		return stats.getStandardDeviation();
	}

	public static double getMean(List<Float> values) {

		DescriptiveStatistics stats = new DescriptiveStatistics();
		for (Float f : values) {
			stats.addValue(f);
		}

		return stats.getMean();
	}
	
	public static SimpleRegression createLinearRegression(final double[] x,
			final double[] y) {
		SimpleRegression regression = new SimpleRegression();
		for (int i = 0; i < x.length; i++) {
			regression.addData(x[i], y[i]);
		}
		return regression;
	}

}
