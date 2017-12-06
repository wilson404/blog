package com.wilson404.blog.service.impl;

import com.wilson404.blog.domain.TermRepository;
import com.wilson404.blog.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TermServiceImpl implements TermService {
    @Autowired
    private TermRepository termRepository;

}
