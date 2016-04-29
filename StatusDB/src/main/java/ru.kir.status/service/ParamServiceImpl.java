package ru.kir.status.service;

import ru.kir.status.dao.ParamDao;
import ru.kir.status.domain.Param;

/**
 * Created by Kirill Zhitelev on 07.03.2016.
 */
public class ParamServiceImpl implements ParamService {
    private ParamDao paramDao;

    @Override
    public Param getParam(Integer attrId, String byWhat, String value) {
        return paramDao.getParam(attrId, byWhat, value);
    }

    @Override
    public void updateParam(Integer attrId, String byWhat, String value, String newValue) {
        paramDao.updateParam(attrId, byWhat, value, newValue);
    }

    public void setParamDao(ParamDao paramDao) {
        this.paramDao = paramDao;
    }
}
