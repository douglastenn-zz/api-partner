package com.douglastenn.partner.service;

import com.douglastenn.partner.domain.entity.Partner;
import com.douglastenn.partner.domain.entity.PartnerTeam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PartnerService {

    Partner save(Partner partner);

    List<PartnerTeam> associate(PartnerTeam partnerTeam);

    List<PartnerTeam> findById(String id);
}
