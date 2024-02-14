package com.codemage.blogplatform.models;

import com.codemage.blogplatform.utils.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Blog")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private String externalAuthor;

    @Column
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    @Column
    private Integer likes;

    @Column
    private String additionalInformation;

    @Lob
    private byte[] image;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Blog(String title, String content, String additionalInformation, byte[] image) {
        this.title = title;
        this.content = content;
        this.additionalInformation = additionalInformation;
        this.image = image;
    }
}
