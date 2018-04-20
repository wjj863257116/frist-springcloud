package boot.admin.contorller;



import boot.dao.dao.Mapper.testDateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/frist")
public class FristsbContorller {

private Logger logger=LoggerFactory.getLogger(getClass());

    @Autowired
   private testDateMapper ttMapper;


    @RequestMapping("/test")
    public String test(Model model){
       List<Map<String,Object>> listMap=ttMapper.selectAll();
        logger.info("-----list size:"+listMap.size());
       model.addAttribute("sddate",listMap);
        return "htmlShow";
    }

}
