package com.laman.biz.original.domain.service;

import com.laman.biz.original.app.dto.OriginalDto;
import com.laman.biz.original.domain.component.QueryComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OriginalDomainService {

    private final QueryComponent queryComponent;

    @Autowired
    public OriginalDomainService(QueryComponent queryComponent) {
        this.queryComponent = queryComponent;
    }

    private static Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<OriginalDto> result = new ArrayList<>();
        while (rs.next()) {
            OriginalDto dto = new OriginalDto();
            dto.setId(rs.getLong("id"));
            dto.setBusiCode(rs.getString("busiCode"));
            dto.setUnionName(rs.getString("unionName"));
            dto.setUnionAddress(rs.getString("unionAddress"));
            result.add(dto);
        }
        return result;
    }

    public List findAllOriginal() throws Exception {

        return queryComponent.getInstance().query("select * from dbo.original", (RowMapper) OriginalDomainService::mapRow);
    }
}
