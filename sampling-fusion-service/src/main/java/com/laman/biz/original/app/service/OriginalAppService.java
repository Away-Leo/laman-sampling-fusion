package com.laman.biz.original.app.service;

import com.laman.biz.original.app.dto.OriginalDto;
import com.laman.biz.original.domain.service.OriginalDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OriginalAppService {

    private final OriginalDomainService originalDomainService;

    @Autowired
    public OriginalAppService(OriginalDomainService originalDomainService) {
        this.originalDomainService= originalDomainService;
    }

    public List<OriginalDto> findALLOriginal() throws Exception{
        return this.originalDomainService.findAllOriginal();
    }
}
