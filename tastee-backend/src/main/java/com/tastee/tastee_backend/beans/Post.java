package com.tastee.tastee_backend.beans;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    // Currently we are storing the whole user object in the post, which is not ideal. We should only store the user id and then fetch the user when needed. But for simplicity we will keep it like this for now.
    @ManyToOne
    @JoinColumn(name = "authorId", referencedColumnName = "id", nullable = false)
    private Users author;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PositiveOrZero
    private int likes;

    private String imgUrl;
    private double calories;
    private String description;

    // @ElementCollection
    // @CollectionTable(name = "post_ingredients", joinColumns = @JoinColumn(name = "post_id"))
    // @Column(name = "ingredient")
    // private List<String> ingredients = new ArrayList<>();

    public void setAuthorId(Users author) {
        this.author = author;
    }
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + ", author=" + author + ", createdAt=" + createdAt + ", likes="
                + likes + ", imgUrl=" + imgUrl + ", calories=" + calories + ", description=" + description + "]";
    }
    

    
}
