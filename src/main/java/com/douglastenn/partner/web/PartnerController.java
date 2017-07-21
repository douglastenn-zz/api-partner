package com.douglastenn.partner.web;

import com.douglastenn.partner.domain.entity.Partner;
import com.douglastenn.partner.domain.entity.PartnerTeam;
import com.douglastenn.partner.exception.PartnerAlreadyExistsException;
import com.douglastenn.partner.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/partner")
public class PartnerController {

    @Autowired
    public PartnerService partnerService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Partner> create(@Valid @RequestBody Partner partner) throws PartnerAlreadyExistsException {
        final Partner saved = this.partnerService.save(partner);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/associate")
    public ResponseEntity<List<PartnerTeam>> associate(@Valid @RequestBody PartnerTeam partnerTeam) {
        final List<PartnerTeam> associated = this.partnerService.associate(partnerTeam);
        return new ResponseEntity<>(associated, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/associate/{id}")
    public ResponseEntity<List<PartnerTeam>> getAssociate(@PathVariable("id") String id) {
        List<PartnerTeam> partnerCampaigns = this.partnerService.findById(id);
        return new ResponseEntity<>(partnerCampaigns, HttpStatus.OK);
    }

}
