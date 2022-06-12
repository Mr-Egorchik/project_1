package ru.isys.practice.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.isys.practice.dto.PersonDto;

@FeignClient(name = "service-registry", path = "/PERSON_SERVICE")
public interface VaccinationApi {
    @GetMapping(value = "/persons/verify")
    PersonDto verify(@RequestParam("name") String name, @RequestParam("passport") String passport);
}
