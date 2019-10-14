package com.wildanokt.trasify.model;

public class Trash {

    private Double anorganik;
    private Double organik;
    private Double logam;
    private Double lat;
    private Double lng;
    private String lokasi;
    private String subLokasi;

    public Trash() {
    }

    public Trash(Double anorganik, Double organik, Double logam, Double lat, Double lng, String lokasi, String subLokasi) {
        this.anorganik = anorganik;
        this.organik = organik;
        this.logam = logam;
        this.lat = lat;
        this.lng = lng;
        this.lokasi = lokasi;
        this.subLokasi = subLokasi;
    }

    public Double getAnorganik() {
        return anorganik;
    }

    public Double getOrganik() {
        return organik;
    }

    public Double getLogam() {
        return logam;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getSubLokasi() {
        return subLokasi;
    }
}
