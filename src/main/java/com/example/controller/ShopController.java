package com.example.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import com.example.domain.Shop;
import com.example.response.CurrentAddress;
import com.example.response.ExistingShopResp;
import com.example.response.PreviousAddress;
import com.example.response.Response;
import com.example.service.GeolocationService;
import com.example.service.ShopService;
import com.example.utils.Constants;

/**
 * @author Dheeraj
 *
 */
@RestController
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private GeolocationService geolocationService;
	
	@RequestMapping(value="/shop", method=RequestMethod.POST)
	public Response addShop(@RequestBody Shop shop) throws Exception {
		shop = geolocationService.getLatLong(shop);
		Shop prevShop;
		try {
			prevShop = shopService.getPreviousShopData(shop);
		} catch(Exception e) {
			return new Response(Constants.ERROR, "Error in getting Geolocation Data", null);
		}
        ExistingShopResp existingShopResp = new ExistingShopResp();
        CurrentAddress currentAddress = new CurrentAddress();
        try {
        	currentAddress.setShop(shopService.saveShopData(shop));
        } catch(Exception e) {
			return new Response(Constants.ERROR, "Error in saving Shop data", null);
		}
        existingShopResp.setCurrentAddress(currentAddress);
        
        PreviousAddress previousAddress = new PreviousAddress();
        previousAddress.setShop(prevShop);
        existingShopResp.setPreviousAddress(previousAddress);
        
        if(null==prevShop) {
        	return new Response(Constants.SUCCESS, "New shop added", null);
        }
        return new Response(Constants.SUCCESS, "", existingShopResp);
	}
	
	@RequestMapping(value="/nearestShop", method=RequestMethod.POST)
	public Response getNearestShop(@RequestBody Shop shop) {
		try {
			Shop shopResp = shopService.getNearestShopData(shop);
			if(shopResp==null)
				return new Response(Constants.WARNING, "No Shop is present in System", null);
			else
				return new Response(Constants.SUCCESS, "", shopResp);
		} catch(Exception e) {
			return new Response(Constants.ERROR, "Unable to find Nearest location", null);
		}
		 
	}
	
}
