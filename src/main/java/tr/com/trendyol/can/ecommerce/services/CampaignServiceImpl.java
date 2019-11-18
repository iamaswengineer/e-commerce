package tr.com.trendyol.can.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.trendyol.can.ecommerce.controllers.dtos.CampaignDTO;
import tr.com.trendyol.can.ecommerce.entities.Campaign;
import tr.com.trendyol.can.ecommerce.entities.enums.DiscountStrategy;
import tr.com.trendyol.can.ecommerce.repositories.CampaignRepository;

@Service
public class CampaignServiceImpl implements CampaignService {

    private CampaignRepository campaignRepository;

    @Autowired
    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Campaign saveOne(CampaignDTO campaignDTO) {
        Campaign campaign = new Campaign();
        campaign.setAmount(campaignDTO.getAmount());
        campaign.setDiscountStrategy(campaignDTO.getDiscountStrategy() == 1 ? DiscountStrategy.AMOUNT.getValue() : DiscountStrategy.RATE.getValue());
        return campaignRepository.save(campaign);
    }

}
