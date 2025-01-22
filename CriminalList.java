package com.example.criminal3;

import com.example.criminal3.repositories.CriminalRepository;

import java.util.ArrayList;
import java.util.List;

public class CriminalList {
    private String graphReg;
    private String graphDone;
    private String subject;
   private List<Criminal> criminalsReg;
    private List<Criminal> criminalsDone;
    private int size=0;
    public CriminalList(String subject, List<Criminal> criminals){
        criminalsReg = CriminalProcessor.processReg(criminals);
        criminalsDone = CriminalProcessor.processDone(criminals);
        this.subject=subject;
    }

    public int getSize(){
        return size;
    }
    public List<Criminal> getCriminalsReg(){
        return criminalsReg;
    }

    public List<Criminal> getCriminalsDone() {
        return criminalsDone;
    }

    public String  getSubject(){
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    public void setGraphReg(String a){
        graphReg =a;
    }

    public void setGraphDone(String graphDone) {
        this.graphDone = graphDone;
    }
    public String getGraphReg(){
        return graphReg;
    }

    public String getGraphDone() {
        return graphDone;
    }
}
