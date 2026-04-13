package com.example.todolist.entity;

import java.time.LocalDate;

public class Todo {
    private Long id;
    private String descrizione;
    private StatoTask stato;
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