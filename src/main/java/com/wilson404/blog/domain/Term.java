package com.wilson404.blog.domain;

import javax.persistence.*;
import java.util.List;

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
    @OneToMany(fetch = FetchType.LAZY)
    private List<Term> chlidTerm;

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

    public List<Term> getChlidTerm() {
        return chlidTerm;
    }

    public void setChlidTerm(List<Term> chlidTerm) {
        this.chlidTerm = chlidTerm;
    }

    public Term(String name, String slug, Integer level, Integer sort, List<Term> chlidTerm) {
        this.name = name;
        this.slug = slug;
        this.level = level;
        this.sort = sort;
        this.chlidTerm = chlidTerm;
    }

    public Term() {
    }
}
