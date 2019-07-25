package com.example.service;

import com.example.domain.Shop;

public interface ShopService {
	Shop saveShopData(Shop shop);
	Shop getPreviousShopData(Shop shop);
	Shop getNearestShopData(Shop shop);
}
