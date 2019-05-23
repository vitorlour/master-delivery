/**
 * 
 */
package br.com.masterdelivery.utils;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

/**
 * @author vitorlour
 *
 */
@Component
public class GoogleMapsServices {
	
	private static final String PT_BR = "pt-BR";


	public GeocodingResult[] getGeoCodingResult(String str) throws ApiException, InterruptedException, IOException {
		return GeocodingApi.geocode(getGeoApiContext(), str)
						   .language(PT_BR)
						   .await();
	}
	
	public long getDuration(LatLng origin, String destination) throws ApiException, InterruptedException, IOException {
		DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(getGeoApiContext()); 
	    DistanceMatrix result = req.origins(origin)
	               					.destinations(destination)
	               					.mode(TravelMode.DRIVING)
	               					.language(PT_BR)
	               					.await();
	    
	    return result.rows[0].elements[0].duration.inSeconds;
	}
	
	public static GeoApiContext getGeoApiContext() {
		return new GeoApiContext
				  .Builder()
				  .apiKey(Constantes.GOOGLE_MAPS_KEY_API)
				  .build();
		
	}

}
