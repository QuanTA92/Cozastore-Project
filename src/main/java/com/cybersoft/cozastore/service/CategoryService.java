package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.CategoryEntity;
import com.cybersoft.cozastore.payload.response.CartResponse;
import com.cybersoft.cozastore.payload.response.CategoryResponse;
import com.cybersoft.cozastore.repository.CategoryRepository;
import com.cybersoft.cozastore.service.imp.CategoryServiceImp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    private Gson gson = new Gson();

//    @Cacheable("allCategory")
    @Override
    public List<CategoryResponse> getAllCategory() {

        List<CategoryResponse> listResponse = new ArrayList<>();

        if(redisTemplate.hasKey("listCategory") != null){
            System.out.println("Kiem tra category redis");

            String dataRedis = redisTemplate.opsForValue().get("listCategory").toString();
            Type listType = new TypeToken<ArrayList<CategoryResponse>>() {}.getType();
            listResponse = gson.fromJson(dataRedis,listType);

        }else {

            System.out.println("Kiem tra category database no cache");

            // Không có key thì tiến hành truy vấn cơ sở dữ liệu
            List<CategoryEntity> list = categoryRepository.findAll();

            for (CategoryEntity item : list){

                CategoryResponse categoryResponse = new CategoryResponse();
                categoryResponse.setId(item.getId());
                categoryResponse.setName(item.getName());

                listResponse.add(categoryResponse);
            }

//          Custom type là dành cho parse JSon (biến json thành 1 list đối tượng)

            // Muốn biến một list đối tượng thành kiểu String
            String dataJon = gson.toJson(listResponse);

            redisTemplate.opsForValue().set("listCategory", dataJon);

        }

        return listResponse;
    }
}
