package boot.dao.service;

import boot.dao.dao.Mapper.testDateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class testservice {

    @Autowired
    private testDateMapper ttMapper;


    public List<Map<String,Object>> getAllDate(){
       List< Map<String,Object>> map=ttMapper.selectAll();
        return map;
    }

}
