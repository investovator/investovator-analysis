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

import org.investovator.analysis.technical.utils.ParamsImpl;

import java.util.Date;

/**
 * @author rajith
 * @version ${Revision}
 */
public class TimeSeriesParams extends ParamsImpl {

    private Date startDate;
    private Date endDate;
    private int periodAverage;

    public TimeSeriesParams (String stockId, Date startDate, Date endDate){
        super(stockId);
        this.startDate = startDate;
        this.endDate = endDate;
        this.periodAverage = 30;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPeriodAverage() {
        return periodAverage;
    }

    public void setPeriodAverage(int periodAverage) {
        this.periodAverage = periodAverage;
    }
}
