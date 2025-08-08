package com.SpringExaminationSystem.model.entity.exam;

import java.io.Serializable;
import java.util.Objects;

import com.SpringExaminationSystem.model.entity.BaseEntity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SubjectMajor")
public class SubjectMajor {
    @EmbeddedId
    private SubjectMajorId id;

    @ManyToOne
    @MapsId("majorId")
    @JoinColumn(name = "majorId")
    private Major major;

    @ManyToOne
    @MapsId("subId")
    @JoinColumn(name = "subId")
    private Subject subject;

}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
class SubjectMajorId implements Serializable {
    private int majorId;
    private int subId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubjectMajorId)) {
            return false;
        }
        SubjectMajorId that = (SubjectMajorId) o;
        return majorId == that.majorId && subId == that.subId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(majorId, subId);
    }
}