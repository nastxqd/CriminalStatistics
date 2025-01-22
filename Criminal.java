package com.example.criminal3;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "statistics")
@AllArgsConstructor
@NoArgsConstructor
public class Criminal {
    @Id
    @Column(name="id")
    private int id;
    @Column( name = "subject")
    public String subject;

    @Column(name = "point")
    private String point;

    @Column(name = "factor")
    private String factor;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "status")
    private String status;

    public Criminal(String subject){
        this.subject=subject;
    }
    public String getSubject(){
        return subject;
    }

    public Double getAmount(){ return amount;}

    public String getStatus() {
        return status;
    }

}
