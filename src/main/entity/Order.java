package main.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Table(name = "orders", schema = "public", catalog = "OCRV")
@TypeDef(
        name = "status",
        typeClass = PostgreSQLEnumType.class
)
public class Order {
    private long id;
    private String description;
    private Status status;

    public Order() {
    }

    public Order(long id) {
        this.id = id;
    }

    public Order(String description, Status status) {
        this.description = description;
        this.status = status;
    }

    public Order(long id, String description, Status status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "auto_id_orders", sequenceName = "auto_id_orders", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_id_orders")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "status")
    @Type(type = "status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
