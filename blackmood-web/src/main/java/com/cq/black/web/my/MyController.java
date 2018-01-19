package com.cq.black.web.my;

import com.cq.black.service.person.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my")
public class MyController {
    Logger log= LoggerFactory.getLogger(MyController.class);
    @Autowired
    PersonService personService;
    @RequestMapping("year")
    public String year() {
        log.error("hahah");
        log.info("2323");
        log.debug("456");
        personService.insertPerson();
        return "my/year";
    }
}
