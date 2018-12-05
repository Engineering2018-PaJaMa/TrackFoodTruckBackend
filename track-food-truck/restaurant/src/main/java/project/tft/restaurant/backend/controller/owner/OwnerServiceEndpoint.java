package project.tft.restaurant.backend.controller.owner;

import static project.tft.restaurant.backend.Constants.FOOD_TRUCK_OWNER_PATH;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Paweł Szopa on 05/12/2018
 */
@Slf4j
@RestController
@RequestMapping(value = FOOD_TRUCK_OWNER_PATH)
public class OwnerServiceEndpoint implements OwnerService
{
	@Autowired
	private OwnerServiceImpl ownerService;

	@PutMapping
	@Override
	public Document registerOwner(@RequestBody final Document owner)
	{
		log.info("Creating owner {} in database.", owner);
		return ownerService.createOwner(owner);
	}

	@PostMapping
	@Override
	public Document loginOwner(@RequestBody final Document owner)
	{
		log.info("Logging owner {} in database.", owner);
		return ownerService.getOwner(owner);
	}
}
