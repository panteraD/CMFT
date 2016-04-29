package ru.kir.status.dao;

import ru.kir.status.domain.Param;

/**
 * Created by Kirill Zhitelev on 07.03.2016.
 */
public interface ParamDao {
    Param getParam(Integer attrId, String byWhat, String value);
    void updateParam(Integer attrId, String byWhat, String value, String newValue);
}
