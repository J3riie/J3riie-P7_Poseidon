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

    public CurvePoint save(CurvePoint curvePoint) {
        return repository.save(curvePoint);
    }

    public CurvePoint getCurveById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curve Id:" + id));
    }

    public CurvePoint update(Integer id, CurvePoint curvePoint) {
        curvePoint.setId(id);
        return repository.save(curvePoint);
    }

    public void delete(CurvePoint curvePoint) {
        repository.delete(curvePoint);
    }

}
