package com.tcbuen.pqrs.modelo;
// Generated 20-ago-2014 23:05:43 by Hibernate Tools 4.0.0



/**
 * MotReclXTpSol generated by hbm2java
 */
public class MotReclXTpSol  implements java.io.Serializable {


     private Long idMotReclXTpSol;
     private TipoSolicitudPqr tipoSolicitudPqr;
     private MotivoReclamacion motivoReclamacion;

    public MotReclXTpSol() {
    }

    public MotReclXTpSol(Long idMotReclXTpSol, TipoSolicitudPqr tipoSolicitudPqr, MotivoReclamacion motivoReclamacion) {
       this.idMotReclXTpSol = idMotReclXTpSol;
       this.tipoSolicitudPqr = tipoSolicitudPqr;
       this.motivoReclamacion = motivoReclamacion;
    }
   
    public Long getIdMotReclXTpSol() {
        return this.idMotReclXTpSol;
    }
    
    public void setIdMotReclXTpSol(Long idMotReclXTpSol) {
        this.idMotReclXTpSol = idMotReclXTpSol;
    }
    public TipoSolicitudPqr getTipoSolicitudPqr() {
        return this.tipoSolicitudPqr;
    }
    
    public void setTipoSolicitudPqr(TipoSolicitudPqr tipoSolicitudPqr) {
        this.tipoSolicitudPqr = tipoSolicitudPqr;
    }
    public MotivoReclamacion getMotivoReclamacion() {
        return this.motivoReclamacion;
    }
    
    public void setMotivoReclamacion(MotivoReclamacion motivoReclamacion) {
        this.motivoReclamacion = motivoReclamacion;
    }




}


