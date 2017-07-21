package com.douglastenn.partner.service.impl;

import com.douglastenn.partner.domain.PartnerRepository;
import com.douglastenn.partner.domain.PartnerTeamRepository;
import com.douglastenn.partner.domain.entity.Campaign;
import com.douglastenn.partner.domain.entity.Partner;
import com.douglastenn.partner.domain.entity.PartnerTeam;
import com.douglastenn.partner.exception.PartnerAlreadyExistsException;
import com.douglastenn.partner.exception.PartnerNotFoundException;
import com.douglastenn.partner.service.PartnerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

@Component
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    public PartnerRepository partnerRepository;

    @Autowired
    public PartnerTeamRepository partnerTeamRepository;

    private static String API_CAMPAIGN_ENDPOINT = "http://localhost:8080/api/campaign";

    public Partner save(Partner requestedPartner) throws PartnerAlreadyExistsException {
        final Partner partner = this.partnerRepository.findByEmail(requestedPartner.email);
        if(partner != null) {
            throw new PartnerAlreadyExistsException();
        }
        return this.partnerRepository.save(requestedPartner);
    }

    public List<PartnerTeam> findById(String partnerId) {
        return this.partnerTeamRepository.findByPartnerId(partnerId);
    }

    public List<PartnerTeam> associate(PartnerTeam requestedPartnerTeam) throws PartnerNotFoundException {
        final Partner partner = this.partnerRepository.findById(requestedPartnerTeam.partnerId);
        if(partner == null) {
            throw new PartnerNotFoundException();
        }
        return this.saveAssociateCampaigns(partner);
    }

    private List<PartnerTeam> saveAssociateCampaigns(Partner partner) {
        List<PartnerTeam> listPartnerTeam = new ArrayList();

        for(Campaign campaign : this.getCampaignsByPartnerTeamId(partner.teamId)) {
            PartnerTeam currentCampaign = this.partnerTeamRepository
                    .findByCampaignIdEqualsAndPartnerIdEquals(campaign.id, partner.id);
            PartnerTeam partnerTeam = (currentCampaign != null) ? currentCampaign : new PartnerTeam();
            partnerTeam.setCampaignId(campaign.id);
            partnerTeam.setPartnerId(partner.id);
            partnerTeam.setTeamId(partner.teamId);

            listPartnerTeam.add(partnerTeam);
            this.partnerTeamRepository.save(partnerTeam);
        }
        return listPartnerTeam;
    }

    private List<Campaign> getCampaignsByPartnerTeamId(Long teamId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Campaign[]> activeCampaigns = restTemplate.getForEntity(API_CAMPAIGN_ENDPOINT, Campaign[].class);

        return Arrays.stream(activeCampaigns.getBody())
                .filter(c -> c.getTeamId() == teamId)
                .collect(toList());
    }
}
