package fr.andromeda.sport.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RowData {

    private static final Logger logger = LoggerFactory.getLogger(RowData.class);

    public Long id;
    public int cadence_spm;
    public int stroke_count;
    public int power_w;
    public int calories_kcal;
    public int heart_rate_bpm;
    public double elapsed_time_s;



}
