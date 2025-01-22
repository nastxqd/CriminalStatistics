package com.example.criminal3;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@org.springframework.stereotype.Controller
public class Controller {
    private final CriminalService criminalService;

    @Autowired
    public Controller(CriminalService criminalService) {
        this.criminalService = criminalService;
    }

    @GetMapping("/criminals")
    public String showCriminals(Model model) throws IOException {
        List<Criminal> criminalList = criminalService.getAll();
        Map<String, List<Criminal>> groupedCriminals = new HashMap<>();
        for(Criminal criminal : criminalList){
            String subject = criminal.subject;
            if (!groupedCriminals.containsKey(subject)){
                groupedCriminals.put(subject, new ArrayList<>());
            }
            groupedCriminals.get(subject).add(criminal);
        }
        String[] regions = criminalService.getAllSubjects();
        String[] sortedRegions = Arrays.stream(regions)
                .sorted(Comparator.comparing(s -> s.substring(0, 1).toLowerCase(Locale.ROOT)))
                .toArray(String[]::new);
        regions=sortedRegions; // сортировка регионов по алфавиту
        List<CriminalList> regionWithCrimesRegAndDone = new ArrayList<>();
        for (int i = 0; i < regions.length; i++) {
            CriminalList c = new CriminalList(regions[i], groupedCriminals.get(regions[i]));
            c.setGraphReg(showGraph(c.getCriminalsReg(), c.getSubject()));
            c.setGraphDone(showGraph(c.getCriminalsDone(), c.getSubject()));
            regionWithCrimesRegAndDone.add(c);
        }
        model.addAttribute("regionWithCrimesRegAndDone", regionWithCrimesRegAndDone);
        model.addAttribute("regions", regions);
        return "criminal";
    }
    public String showGraph(List<Criminal> criminals, String name) throws IOException {
        List<String> labels = Arrays.asList("Убийство", "Умышленное причинение тяжкого вреда здоровью", "Изнасилование", "Кража", "Грабёж", "Разбой", "Вымогательство", "Хулиганство");
        List<Double> values = new ArrayList<>();
        for (int i = 0; i < criminals.size(); i++) {
            values.add(criminals.get(i).getAmount());
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//поток вывода, который накапливает данные в виде массива байтов
        ChartUtils.writeChartAsPNG(baos, SimpleGraphGenerator.generatePieChart(labels, values, name), 600, 400);
        //imageBytes-  бинарное представление сгенерированного изображения
        byte[] imageBytes = baos.toByteArray(); // возвращает массив байтов, содержащий все данные изображения в формате PNG, записанные в поток baos
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        String str = "data:image/png;base64," + base64Image;
        return str;
    }
}
