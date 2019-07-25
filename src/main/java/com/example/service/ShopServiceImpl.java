package com.example.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Shop;
import com.example.repository.ShopRepository;

@Service
public class ShopServiceImpl implements ShopService{
	
	@Autowired
	private ShopRepository shopRepository;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public Shop saveShopData(Shop shop) {
		return shopRepository.save(shop);
		
	}

	public Shop getPreviousShopData(Shop shop) {
		Query query = entityManager.createQuery("SELECT s from Shop s where s.shopId=(Select max(s1.shopId) from Shop s1) and LOWER(s.shopName) LIKE :shopName");
		query.setParameter("shopName", shop.getShopName().toLowerCase());
		try {
			return (Shop) query.getSingleResult();
		} catch(Exception e) {
			return null;
		}
	}

	public Shop getNearestShopData(Shop shop) {
		/*Generally use this*/
//		Query query = entityManager.createNativeQuery("SELECT * FROM SHOP WHERE shop_id = (SELECT shop_id from (SELECT shop_id, SQRT(" + 
//				"POWER(69.1 * (latitude-?1), 2)+" + 
//				"POWER(69.1 * (?2-longitude) * COS(latitude / 57.3), 2)) AS distance FROM SHOP ORDER BY distance LIMIT 0 , 1))");
//		query.setParameter(1, shop.getLatitude());
//		query.setParameter(2, shop.getLongitude());
//		return (Shop) query.getSingleResult();

		
		/*Due to issue in compatibility between H2 and database, various calculations are not supported so we go for collections approach*/
		List<Shop> shopList = shopRepository.findAll();
		if(shopList.size()<=0) {
			return null;
		}
			Map<Long, String> map = new HashMap<Long, String>();
			
			for (Shop shopParam : shopList) {
				double distance = Math.sqrt(Math.pow(69.1*(Double.valueOf(shopParam.getLatitude())-Double.valueOf(shop.getLatitude())), 2)+
						Math.pow(69.1*(Double.valueOf(shop.getLongitude())-Double.valueOf(shopParam.getLongitude()))*Math.cos(Double.valueOf(shopParam.getLatitude())/57.3), 2));
				map.put(shopParam.getShopId(), distance+"");
			}
	        Set<Entry<Long, String>> set = map.entrySet();
	        List<Entry<Long, String>> list = new ArrayList<Entry<Long, String>>(set);
	        Collections.sort( list, new Comparator<Map.Entry<Long, String>>()
	        {
	            public int compare( Map.Entry<Long, String> o1, Map.Entry<Long, String> o2 )
	            {
	                return (int) (Double.valueOf(o1.getValue())-Double.valueOf(o2.getValue()));
	            }
	        } );
	        Long key = list.get(0).getKey();
			Shop shopResp = shopRepository.findOne(key);
			return shopResp;

	}
	
	

}
