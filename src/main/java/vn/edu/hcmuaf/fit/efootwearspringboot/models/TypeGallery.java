package vn.edu.hcmuaf.fit.efootwearspringboot.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.EntityState;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "type_galleries")
public class TypeGallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "type_code")
    private String typeCode;
    @Column(name = "type_name")
    private String typeName;
    @Column(name = "create_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private ZonedDateTime createAt;

    @Column(name = "update_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @UpdateTimestamp
    private ZonedDateTime updateAt;

    @Column(name = "state", length = 10)
    @Enumerated(value = EnumType.STRING)
    private EntityState state;

    @OneToMany(mappedBy = "typeGallery")
    private List<Gallery> galleries;

    @OneToMany(mappedBy = "typeGallery")
    private List<ProductImage> images;
}
