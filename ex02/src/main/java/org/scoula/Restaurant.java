package org.scoula;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class Restaurant {
    @Autowired // 의존성 주입
    private Chef chef;
}