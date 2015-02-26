package io.searchbox.core.search.aggregation;

import com.google.gson.JsonObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import static io.searchbox.core.search.aggregation.AggregationField.STD_DEVIATION;
import static io.searchbox.core.search.aggregation.AggregationField.SUM_OF_SQUARES;
import static io.searchbox.core.search.aggregation.AggregationField.VARIANCE;

/**
 * @author cfstout
 */
public class ExtendedStatsAggregation extends StatsAggregation<ExtendedStatsAggregation> {

    private Double sumOfSquares;
    private Double variance;
    private Double stdDeviation;

    public <T extends Aggregation> ExtendedStatsAggregation(String name, JsonObject extendedStatsAggregation) {
        super(name, extendedStatsAggregation);
        this.sumOfSquares = !extendedStatsAggregation.has(String.valueOf(SUM_OF_SQUARES)) || extendedStatsAggregation.get(String.valueOf(SUM_OF_SQUARES)).isJsonNull() ?
                null : extendedStatsAggregation.get(String.valueOf(SUM_OF_SQUARES)).getAsDouble();
        this.variance = !extendedStatsAggregation.has(String.valueOf(VARIANCE)) || extendedStatsAggregation.get(String.valueOf(VARIANCE)).isJsonNull() ?
                null : extendedStatsAggregation.get(String.valueOf(VARIANCE)).getAsDouble();
        this.stdDeviation = !extendedStatsAggregation.has(String.valueOf(STD_DEVIATION)) || extendedStatsAggregation.get(String.valueOf(STD_DEVIATION)).isJsonNull() ?
                null : extendedStatsAggregation.get(String.valueOf(STD_DEVIATION)).getAsDouble();
    }

    /**
     * @return Sum of Squares for the aggregated data if found, null otherwise
     */
    public Double getSumOfSquares() {
        return sumOfSquares;
    }

    /**
     * @return Variance of the aggregated data if found, null otherwise
     */
    public Double getVariance() {
        return variance;
    }

    /**
     * @return Standard deviation of the aggregated data if found, null otherwise
     */
    public Double getStdDeviation() {
        return stdDeviation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        ExtendedStatsAggregation rhs = (ExtendedStatsAggregation) o;
        return new EqualsBuilder()
                .append(getStdDeviation(), rhs.getStdDeviation())
                .append(getSumOfSquares(), rhs.getSumOfSquares())
                .append(getVariance(), rhs.getVariance())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(getSumOfSquares())
                .append(getVariance())
                .append(getStdDeviation())
                .toHashCode();
    }
}
