/*
 * investovator, Stock Market Gaming framework
 * Copyright (C) 2013  investovator
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.investovator.analysis.technical.indicators.timeseries.utils;

import org.investovator.analysis.exceptions.AnalysisException;
import org.investovator.analysis.technical.utils.ResultsSetImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author rajith
 * @version ${Revision}
 */
public class TimeSeriesResultSet extends ResultsSetImpl{

    private HashMap<TimeSeriesGraph, LinkedHashMap<Date, Double>> graphData;

    public TimeSeriesResultSet(String stockId) {
        super(stockId);
        graphData = new HashMap<>();
    }

    public void setGraph(TimeSeriesGraph graphName, Date[] dates, double [] values, int begin, int length)
            throws AnalysisException {
        if(dates.length == values.length){
            LinkedHashMap <Date, Double> points = new LinkedHashMap<>();
            for(int i = 0; i < length; i++){
                points.put(dates[i + begin], values[i]);
            }
            graphData.put(graphName, points);
        } else {
            throw new AnalysisException("Date and value array length mismatch");
        }
    }

    public HashMap<TimeSeriesGraph, LinkedHashMap<Date, Double>> getGraphs(){
        return graphData;
    }

    public HashMap<Date, Double> getGraph(TimeSeriesGraph graphName){
        return graphData.get(graphName);
    }

    public boolean containsGraph(TimeSeriesGraph graphName){
       return graphData.containsKey(graphName);
    }
}
