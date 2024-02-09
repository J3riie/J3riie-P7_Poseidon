package com.nnk.springboot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
public class CurvePointService {

    private final CurvePointRepository repository;

    public CurvePointService(CurvePointRepository curvePointRepository) {
        this.repository = curvePointRepository;
    }

    public List<CurvePoint> getAllCurves() {
        return repository.findAll();
    }

    public void save(CurvePoint curvePoint) {
        repository.save(curvePoint);

    }

    public CurvePoint getCurveById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curve Id:" + id));
    }

    public void update(Integer id, CurvePoint curvePoint) {
        curvePoint.setId(id);
        repository.save(curvePoint);
    }

    public void delete(CurvePoint curvePoint) {
        repository.delete(curvePoint);
    }

}
