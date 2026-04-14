package com.example.todolist.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La descrizione non può essere vuota")
    @Size(min = 3, max = 100, message = "La descrizione deve essere tra 3 e 100 caratteri")
    private String descrizione;

    @Enumerated(EnumType.STRING)
    private StatoTask stato;

    @Min(value = 1, message = "La priorità minima è 1")
    @Max(value = 3, message = "La priorità massima è 3")
    private int priorita;

    private LocalDate dataScadenza;

    public Todo() {}

    public Todo(Long id, String descrizione, StatoTask stato, int priorita, LocalDate dataScadenza) {
        this.id = id;
        this.descrizione = descrizione;
        this.stato = stato;
        this.priorita = priorita;
        this.dataScadenza = dataScadenza;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public StatoTask getStato() { return stato; }
    public void setStato(StatoTask stato) { this.stato = stato; }

    public int getPriorita() { return priorita; }
    public void setPriorita(int priorita) { this.priorita = priorita; }

    public LocalDate getDataScadenza() { return dataScadenza; }
    public void setDataScadenza(LocalDate dataScadenza) { this.dataScadenza = dataScadenza; }
}