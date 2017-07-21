package com.douglastenn.partner.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Campaign {
    @Id
    public String id;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    public LocalDate startDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    public LocalDate endDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    public LocalDate updatedAt;

    public Long teamId;

    public Campaign() {
        // nothing
    }

    public Campaign(String id, LocalDate startDate, LocalDate endDate, LocalDate updatedAt, Long teamId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.updatedAt = updatedAt;
        this.teamId = teamId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Campaign campaign = (Campaign) o;

        if (id != null ? !id.equals(campaign.id) : campaign.id != null) return false;
        if (startDate != null ? !startDate.equals(campaign.startDate) : campaign.startDate != null) return false;
        if (endDate != null ? !endDate.equals(campaign.endDate) : campaign.endDate != null) return false;
        if (updatedAt != null ? !updatedAt.equals(campaign.updatedAt) : campaign.updatedAt != null) return false;
        return teamId != null ? teamId.equals(campaign.teamId) : campaign.teamId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        return result;
    }
}
