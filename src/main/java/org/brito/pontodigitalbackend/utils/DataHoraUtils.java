package org.brito.pontodigitalbackend.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataHoraUtils {

    public static String buscaDataHoraAgora(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
        return now.format(formatter);
    }
}
