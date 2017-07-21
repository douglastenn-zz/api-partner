package com.douglastenn.partner.domain;

import com.douglastenn.partner.domain.entity.PartnerTeam;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface PartnerTeamRepository extends MongoRepository<PartnerTeam, String> {
    PartnerTeam findByCampaignIdEqualsAndPartnerIdEquals(String id, String s);

    List<PartnerTeam> findByPartnerId(String partnerId);
}
