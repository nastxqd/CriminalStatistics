package com.example.criminal3;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CriminalProcessor {
    public static   List<Criminal> processReg(List<Criminal> criminals) {
        Map<String, List<Criminal>> groupedSalaries = criminals.stream()
                .collect(Collectors.groupingBy(Criminal::getStatus)); //коллектор группирует элементы потока по ключу "статус"
        List<Criminal> regcriminals = groupedSalaries.get("зарегистрирован");
        return regcriminals;
    }
    public static   List<Criminal> processDone(List<Criminal> criminals) {
        Map<String, List<Criminal>> groupedSalaries = criminals.stream()
                .collect(Collectors.groupingBy(Criminal::getStatus));

        List<Criminal> regcriminals = groupedSalaries.get("расследован");
        return regcriminals;
    }
}
