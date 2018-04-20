package boot.controller;

import boot.service.ProTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/provide")
public class ProTestController {

    @Autowired
    private ProTestService  ptService;

    @RequestMapping(value = "/test",method = RequestMethod.POST,produces ={"application/json;charset=UTF-8"})
    public Object pvTest(@RequestBody Map<String,Object> req) throws Exception{

        return ptService.proTests(req);
    }

}
