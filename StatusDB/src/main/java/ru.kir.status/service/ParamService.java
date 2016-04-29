package ru.kir.status.service;

import ru.kir.status.domain.Param;

/**
 * Created by Kirill Zhitelev on 07.03.2016.
 */
public interface ParamService {
    Param getParam(Integer attrId, String byWhat, String value);

    /**
     *
     * @param attrId
     * @param byWhat
     * @param value
     * @param newValue
     */
    void updateParam(Integer attrId, String byWhat, String value, String newValue);
}
