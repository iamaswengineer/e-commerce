package tr.com.trendyol.can.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.trendyol.can.ecommerce.controllers.dtos.CampaignDTO;
import tr.com.trendyol.can.ecommerce.entities.Campaign;
import tr.com.trendyol.can.ecommerce.entities.enums.DiscountStrategy;
import tr.com.trendyol.can.ecommerce.services.CampaignService;

@RestController
@RequestMapping("campaign")
public class CampaignRestController {

    private CampaignService campaignService;

    @Autowired
    public CampaignRestController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping(value="/saveOne")
    public ResponseEntity saveOne(@RequestBody CampaignDTO campaignDTO){
        Campaign saved = campaignService.saveOne(campaignDTO);
        return new ResponseEntity<>(saved.getId(), HttpStatus.CREATED);
    }

}
