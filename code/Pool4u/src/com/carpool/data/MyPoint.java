/**
 * 
 */
package com.carpool.data;

import java.io.Serializable;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;

/**
 * @author Deepak
 *
 */
public class MyPoint extends Point implements Serializable {

	public MyPoint(Coordinate coordinate, PrecisionModel precisionModel,
			int SRID) {
		super(coordinate, precisionModel, SRID);
		// TODO Auto-generated constructor stub
	}

	
}
