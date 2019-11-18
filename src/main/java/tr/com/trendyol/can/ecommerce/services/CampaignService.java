package tr.com.trendyol.can.ecommerce.services;

import tr.com.trendyol.can.ecommerce.controllers.dtos.CampaignDTO;
import tr.com.trendyol.can.ecommerce.entities.Campaign;

public interface CampaignService {

    Campaign saveOne(CampaignDTO campaignDTO);
}
