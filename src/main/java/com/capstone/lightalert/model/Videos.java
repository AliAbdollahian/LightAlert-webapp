package com.capstone.lightalert.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "videos")
public class Videos {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_id", referencedColumnName = "system_id")
    private Users user;

    @Column(name = "video_url", nullable = false)
    private String videoURL;

    @Column(name = "upload_date", nullable = false)
    private Date uploadDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getSystemId() {
        return user != null ? user.getSystemId() : null;
    }

    public void setSystemId(String systemId) {
        if (user == null) {
            user = new Users();
        }
        user.setSystemId(systemId);
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public void setUser(Users user) {
        this.user = user;
    }

}
