package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.CategoryRequest;
import com.cybersoft.cozastore.payload.response.CategoryResponse;

import java.util.List;

public interface CategoryServiceImp {
    List<CategoryResponse> getAllCategory();

    boolean insertCategory(CategoryRequest categoryRequest);
}
