package com.wilson404.blog.domain;

import com.wilson404.blog.common.ResponseCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 博文
 */
@Entity
@Table(name = "T_BLOG_POST")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private User author;
    @ManyToMany
    private List<Term> termList;
    private String title;
    @Lob()
    private String content;
    @CreatedDate
    @Column(updatable = false)
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;
    private Byte status = (byte) ResponseCode.SUCCESS.getCode();

    public BlogPost() {
    }

    public BlogPost(User author, List<Term> termList, String title, String content, Date createDate, Date updateDate, Byte status) {
        this.author = author;
        this.termList = termList;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Term> getTermList() {
        return termList;
    }

    public void setTermList(List<Term> termList) {
        this.termList = termList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
