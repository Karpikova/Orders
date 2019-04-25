package main.entity;

import javax.persistence.*;

@Entity
@Table(name = "comment", schema = "public", catalog = "OCRV")
public class Comment {

    private long id;
    private Order order;
    private String description;

    public Comment(Order order, String description) {
        this.order = order;
        this.description = description;
    }

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "auto_id_comments", sequenceName = "auto_id_comments", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_id_comments")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "id_order")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
