package com.wilson404.blog.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 博文分组
 */
@Entity
@Table(name = "T_TERM")
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String slug;
    private Integer level;
    private Integer sort;
    @ManyToOne
    private Term fatherTerm;

    public Term() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Term getFatherTerm() {
        return fatherTerm;
    }

    public void setFatherTerm(Term fatherTerm) {
        this.fatherTerm = fatherTerm;
    }

    public Term(String name, String slug, Integer level, Integer sort, Term fatherTerm) {
        this.name = name;
        this.slug = slug;
        this.level = level;
        this.sort = sort;
        this.fatherTerm = fatherTerm;
    }
}
