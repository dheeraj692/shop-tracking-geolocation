package com.example.service;

import com.example.domain.Shop;

public interface GeolocationService {
	Shop getLatLong(Shop shop) throws Exception;
}
