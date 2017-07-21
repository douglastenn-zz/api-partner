package com.douglastenn.partner.service.impl;

import com.douglastenn.partner.domain.PartnerRepository;
import com.douglastenn.partner.domain.PartnerTeamRepository;
import com.douglastenn.partner.domain.entity.Partner;
import com.douglastenn.partner.domain.entity.PartnerTeam;
import com.douglastenn.partner.exception.PartnerAlreadyExistsException;
import com.douglastenn.partner.exception.PartnerNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class PartnerServiceImplTest {

    @Mock
    private PartnerRepository partnerRepository;

    @Mock
    private PartnerTeamRepository partnerTeamRepository;

    @InjectMocks
    private PartnerServiceImpl partnerService;

    @Captor
    private ArgumentCaptor<Partner> partnerCaptor;

    @Captor
    private ArgumentCaptor<String> partnerTeamStringCaptor;

    @Test
    public void save_withNoExistsPartner() throws Exception {
        // given
        final Partner partner = new Partner(
            "Douglas Tenório",
            "douglas.develop@gmail.com",
            LocalDate.of(1991, 9, 07),
            10L
        );

        final Partner returnedPartner = new Partner(
            "101020",
            "Douglas Tenório",
            "douglas.develop@gmail.com",
            LocalDate.of(1991, 9, 07),
            10L
        );

        // when
        when(this.partnerRepository.findByEmail(partner.email)).thenReturn(null);

        when(partnerRepository.save(partner)).thenReturn(returnedPartner);

        when(partnerRepository.save(this.partnerCaptor.capture())).thenReturn(returnedPartner);

        this.partnerService.save(partner);

        // then

        final Partner partnerValues = this.partnerCaptor.getValue();

        assertThat(partnerValues).isEqualTo(partner);

        Mockito.verify(this.partnerRepository, times(1)).save(partner);
    }

    @Test(expected= PartnerAlreadyExistsException.class)
    public void save_withExistsPartner() throws Exception {
        // given
        final Partner partner = new Partner(
            "Douglas Tenório",
            "douglas.develop@gmail.com",
            LocalDate.of(1991, 9, 07),
            10L
        );

        // when
        when(this.partnerRepository.findByEmail(partner.email)).thenReturn(partner);

        when(this.partnerRepository.save(partner)).thenThrow(new PartnerAlreadyExistsException());

        when(this.partnerRepository.save(this.partnerCaptor.capture())).thenThrow(new PartnerAlreadyExistsException());

        this.partnerService.save(partner);

        // then
        final Partner partnerValues = this.partnerCaptor.getValue();

        assertThat(partnerValues).isEqualTo(partner);

        Mockito.verify(this.partnerRepository, times(1)).save(partner);
    }

    @Test
    public void findById_withExistsId() throws Exception {
        // given
        final String partnerId = "10";

        final List<PartnerTeam> partnerTeamList = Arrays.asList(
            new PartnerTeam(
                "10",
                11l,
                "4894984"
            ),
            new PartnerTeam(
                "10",
                11l,
                "4874984"
            )
        );

        // when
        when(this.partnerTeamRepository.findByPartnerId(partnerId))
                .thenReturn(partnerTeamList);

        when(this.partnerService.findById(partnerId))
                .thenReturn(partnerTeamList);

        when(this.partnerService.findById(this.partnerTeamStringCaptor.capture()))
                .thenReturn(partnerTeamList);

        this.partnerService.findById(partnerId);

        //then
        final String partnerTeamsCaptured = this.partnerTeamStringCaptor.getValue();

        assertThat(partnerTeamsCaptured).isEqualTo(partnerId);

        Mockito.verify(this.partnerTeamRepository, times(1))
                .findByPartnerId(partnerId);
    }

    @Test
    public void findById_withNoExistsId() throws Exception {
        // given
        final String partnerId = "";

        final List<PartnerTeam> partnerTeamList = Arrays.asList();

        // when
        when(this.partnerTeamRepository.findByPartnerId(partnerId))
                .thenReturn(partnerTeamList);

        when(this.partnerService.findById(partnerId))
                .thenReturn(partnerTeamList);

        when(this.partnerService.findById(this.partnerTeamStringCaptor.capture()))
                .thenReturn(partnerTeamList);

        this.partnerService.findById(partnerId);

        //then
        final String partnerTeamsCaptured = this.partnerTeamStringCaptor.getValue();

        assertThat(partnerTeamsCaptured).isEqualTo(partnerId);

        Mockito.verify(this.partnerTeamRepository, times(1))
                .findByPartnerId(partnerId);
    }

    @Test
    public void associate_withExistsPartner() throws Exception {
        // given
        PartnerTeam partnerTeam = new PartnerTeam();
        partnerTeam.setPartnerId("10");

        final Partner partner = new Partner(
            "Douglas Tenório",
            "douglas.develop@gmail.com",
            LocalDate.of(1991, 9, 07),
            10L
        );

        // when
        when(this.partnerRepository.findById(partnerTeam.partnerId))
                .thenReturn(partner);

        this.partnerService.associate(partnerTeam);

        // then
        Mockito.verify(this.partnerRepository, times(1))
                .findById(partnerTeam.partnerId);
    }

    @Test(expected= PartnerNotFoundException.class)
    public void associate_withNoExistsPartner() throws Exception {
        // given
        PartnerTeam partnerTeam = new PartnerTeam();
        partnerTeam.setPartnerId("10");

        Partner partner = new Partner(
            "Douglas Tenório",
            "douglas.develop@gmail.com",
            LocalDate.of(1991, 9, 07),
            10L
        );

        // when
        when(this.partnerRepository.findById(partnerTeam.partnerId))
                .thenThrow(new PartnerNotFoundException());
        this.partnerService.associate(partnerTeam);

        // then
        Mockito.verify(this.partnerRepository, times(1))
                .findById(partnerTeam.partnerId);
    }

}