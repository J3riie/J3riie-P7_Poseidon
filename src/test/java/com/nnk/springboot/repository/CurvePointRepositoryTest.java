package com.nnk.springboot.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@DataJpaTest
public class CurvePointRepositoryTest {

    @Autowired
    private CurvePointRepository curvePointRepository;

    @Test
    public void curvePointTest() {
        CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);

        // Save
        curvePoint = curvePointRepository.save(curvePoint);
        assertThat(curvePoint.getId()).isNotNull();
        assertThat(curvePoint.getCurveId()).isEqualTo(10);

        // Update
        curvePoint.setCurveId(20);
        curvePoint = curvePointRepository.save(curvePoint);
        assertThat(curvePoint.getCurveId()).isEqualTo(20);

        // Find
        final List<CurvePoint> listResult = curvePointRepository.findAll();
        assertThat(listResult).isNotEmpty();

        // Delete
        final Integer id = curvePoint.getId();
        final Optional<CurvePoint> curvePointListBeforeDelete = curvePointRepository.findById(id);
        curvePointRepository.delete(curvePoint);
        final Optional<CurvePoint> curvePointListAfterDelete = curvePointRepository.findById(id);
        assertThat(curvePointListBeforeDelete).isPresent();
        assertThat(curvePointListAfterDelete).isEmpty();
    }

}
