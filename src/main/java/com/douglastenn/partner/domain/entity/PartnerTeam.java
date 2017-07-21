package com.douglastenn.partner.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PartnerTeam {

    @Id
    public String id;

    public String partnerId;

    public Long teamId;

    public String campaignId;

    public PartnerTeam() {
        // empty
    }

    public PartnerTeam(String id, String partnerId, Long teamId, String campaignId) {
        this.id = id;
        this.partnerId = partnerId;
        this.teamId = teamId;
        this.campaignId = campaignId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartnerTeam that = (PartnerTeam) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (partnerId != null ? !partnerId.equals(that.partnerId) : that.partnerId != null) return false;
        if (teamId != null ? !teamId.equals(that.teamId) : that.teamId != null) return false;
        return campaignId != null ? campaignId.equals(that.campaignId) : that.campaignId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (partnerId != null ? partnerId.hashCode() : 0);
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        result = 31 * result + (campaignId != null ? campaignId.hashCode() : 0);
        return result;
    }
}
