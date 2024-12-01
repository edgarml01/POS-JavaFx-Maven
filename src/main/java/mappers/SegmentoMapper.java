package mappers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

import java.util.List;
import models.Segmento;
/**
 *
 * @author egarm
 */
public interface SegmentoMapper {
  List<Segmento> getAllSegmentos();
    Segmento getSegmentoById();
    Segmento insertSegmento(Segmento segmento);
    void updateSegmento(Segmento segmento);
    void deleteSegmento(int id);
}
