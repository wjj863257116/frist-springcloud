package boot.dao.dao.Mapper;



import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface testDateMapper {

    @Select("select * from infomation ")
    public  List<Map<String,Object>> selectAll();

}
