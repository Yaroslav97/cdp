package com.epam.cdp.storage;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface Mapper<T, ID extends Serializable> {

    Map<ID, T> getModel(List list);

}
