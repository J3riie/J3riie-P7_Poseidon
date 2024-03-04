package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.CurvePoint;

@SpringBootTest
public class CurvePointServiceTest {

    @Autowired
    private CurvePointService curvePointService;

    @Test
    public void curvePointTest() {
        CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);

        // Save
        curvePoint = curvePointService.save(curvePoint);
        assertThat(curvePoint.getId()).isNotNull();
        assertThat(curvePoint.getCurveId()).isEqualTo(10);

        // Update
        curvePoint.setCurveId(20);
        curvePoint = curvePointService.save(curvePoint);
        assertThat(curvePoint.getCurveId()).isEqualTo(20);

        // Find
        final List<CurvePoint> listResult = curvePointService.getAllCurves();
        assertThat(listResult).isNotEmpty();

        // Delete
        final Integer id = curvePoint.getId();
        final CurvePoint curvePointListBeforeDelete = curvePointService.getCurveById(id);
        curvePointService.delete(curvePoint);
        assertThat(curvePointListBeforeDelete).isNotNull();
        assertThrows(IllegalArgumentException.class, () -> curvePointService.getCurveById(id));
    }

}
