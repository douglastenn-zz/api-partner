package com.douglastenn.partner.domain;

import com.douglastenn.partner.domain.entity.Partner;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PartnerRepository extends MongoRepository<Partner, String> {

    Partner findByEmail(String email);

    Partner findById(String id);
}
