package com.tcbuen.pqrs.modelo;
// Generated 20-ago-2014 23:05:43 by Hibernate Tools 4.0.0



/**
 * MotReclSelect generated by hbm2java
 */
public class MotReclSelect  implements java.io.Serializable {


     private Long idMotReclSelect;
     private MotivoReclamacion motivoReclamacion;
     private SolicitudPqr solicitudPqr;

    public MotReclSelect() {
    }

    public MotReclSelect(Long idMotReclSelect, MotivoReclamacion motivoReclamacion, SolicitudPqr solicitudPqr) {
       this.idMotReclSelect = idMotReclSelect;
       this.motivoReclamacion = motivoReclamacion;
       this.solicitudPqr = solicitudPqr;
    }
   
    public Long getIdMotReclSelect() {
        return this.idMotReclSelect;
    }
    
    public void setIdMotReclSelect(Long idMotReclSelect) {
        this.idMotReclSelect = idMotReclSelect;
    }
    public MotivoReclamacion getMotivoReclamacion() {
        return this.motivoReclamacion;
    }
    
    public void setMotivoReclamacion(MotivoReclamacion motivoReclamacion) {
        this.motivoReclamacion = motivoReclamacion;
    }
    public SolicitudPqr getSolicitudPqr() {
        return this.solicitudPqr;
    }
    
    public void setSolicitudPqr(SolicitudPqr solicitudPqr) {
        this.solicitudPqr = solicitudPqr;
    }




}


