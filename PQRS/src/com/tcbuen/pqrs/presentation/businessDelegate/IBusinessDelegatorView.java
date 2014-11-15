package com.tcbuen.pqrs.presentation.businessDelegate;

import com.tcbuen.pqrs.modelo.AnexosPqr;
import com.tcbuen.pqrs.modelo.AnexosRespuesta;
import com.tcbuen.pqrs.modelo.AnexosSolicitante;
import com.tcbuen.pqrs.modelo.AnxsXArea;
import com.tcbuen.pqrs.modelo.AnxsXTpSol;
import com.tcbuen.pqrs.modelo.AreasInvolucradas;
import com.tcbuen.pqrs.modelo.InfoSolicitante;
import com.tcbuen.pqrs.modelo.MotReclSelect;
import com.tcbuen.pqrs.modelo.MotReclXTpSol;
import com.tcbuen.pqrs.modelo.MotSolSelect;
import com.tcbuen.pqrs.modelo.MotSolXTpSol;
import com.tcbuen.pqrs.modelo.MotivoReclamacion;
import com.tcbuen.pqrs.modelo.MotivoSolicitud;
import com.tcbuen.pqrs.modelo.ParametrosPqr;
import com.tcbuen.pqrs.modelo.RespuestaSol;
import com.tcbuen.pqrs.modelo.Roles;
import com.tcbuen.pqrs.modelo.SolicitudAsignadaArea;
import com.tcbuen.pqrs.modelo.SolicitudPqr;
import com.tcbuen.pqrs.modelo.TipoDocumento;
import com.tcbuen.pqrs.modelo.TipoEstadoPqr;
import com.tcbuen.pqrs.modelo.TipoSolicitudPqr;
import com.tcbuen.pqrs.modelo.UsuariosInternos;
import com.tcbuen.pqrs.modelo.control.AnexosPqrLogic;
import com.tcbuen.pqrs.modelo.control.AnexosRespuestaLogic;
import com.tcbuen.pqrs.modelo.control.AnexosSolicitanteLogic;
import com.tcbuen.pqrs.modelo.control.AnxsXAreaLogic;
import com.tcbuen.pqrs.modelo.control.AnxsXTpSolLogic;
import com.tcbuen.pqrs.modelo.control.AreasInvolucradasLogic;
import com.tcbuen.pqrs.modelo.control.IAnexosPqrLogic;
import com.tcbuen.pqrs.modelo.control.IAnexosRespuestaLogic;
import com.tcbuen.pqrs.modelo.control.IAnexosSolicitanteLogic;
import com.tcbuen.pqrs.modelo.control.IAnxsXAreaLogic;
import com.tcbuen.pqrs.modelo.control.IAnxsXTpSolLogic;
import com.tcbuen.pqrs.modelo.control.IAreasInvolucradasLogic;
import com.tcbuen.pqrs.modelo.control.IInfoSolicitanteLogic;
import com.tcbuen.pqrs.modelo.control.IMotReclSelectLogic;
import com.tcbuen.pqrs.modelo.control.IMotReclXTpSolLogic;
import com.tcbuen.pqrs.modelo.control.IMotSolSelectLogic;
import com.tcbuen.pqrs.modelo.control.IMotSolXTpSolLogic;
import com.tcbuen.pqrs.modelo.control.IMotivoReclamacionLogic;
import com.tcbuen.pqrs.modelo.control.IMotivoSolicitudLogic;
import com.tcbuen.pqrs.modelo.control.IParametrosPqrLogic;
import com.tcbuen.pqrs.modelo.control.IRespuestaSolLogic;
import com.tcbuen.pqrs.modelo.control.IRolesLogic;
import com.tcbuen.pqrs.modelo.control.ISolicitudAsignadaAreaLogic;
import com.tcbuen.pqrs.modelo.control.ISolicitudPqrLogic;
import com.tcbuen.pqrs.modelo.control.ITipoDocumentoLogic;
import com.tcbuen.pqrs.modelo.control.ITipoEstadoPqrLogic;
import com.tcbuen.pqrs.modelo.control.ITipoSolicitudPqrLogic;
import com.tcbuen.pqrs.modelo.control.IUsuariosInternosLogic;
import com.tcbuen.pqrs.modelo.control.InfoSolicitanteLogic;
import com.tcbuen.pqrs.modelo.control.MotReclSelectLogic;
import com.tcbuen.pqrs.modelo.control.MotReclXTpSolLogic;
import com.tcbuen.pqrs.modelo.control.MotSolSelectLogic;
import com.tcbuen.pqrs.modelo.control.MotSolXTpSolLogic;
import com.tcbuen.pqrs.modelo.control.MotivoReclamacionLogic;
import com.tcbuen.pqrs.modelo.control.MotivoSolicitudLogic;
import com.tcbuen.pqrs.modelo.control.ParametrosPqrLogic;
import com.tcbuen.pqrs.modelo.control.RespuestaSolLogic;
import com.tcbuen.pqrs.modelo.control.RolesLogic;
import com.tcbuen.pqrs.modelo.control.SolicitudAsignadaAreaLogic;
import com.tcbuen.pqrs.modelo.control.SolicitudPqrLogic;
import com.tcbuen.pqrs.modelo.control.TipoDocumentoLogic;
import com.tcbuen.pqrs.modelo.control.TipoEstadoPqrLogic;
import com.tcbuen.pqrs.modelo.control.TipoSolicitudPqrLogic;
import com.tcbuen.pqrs.modelo.control.UsuariosInternosLogic;
import com.tcbuen.pqrs.modelo.dto.AnexosPqrDTO;
import com.tcbuen.pqrs.modelo.dto.AnexosRespuestaDTO;
import com.tcbuen.pqrs.modelo.dto.AnexosSolicitanteDTO;
import com.tcbuen.pqrs.modelo.dto.AnxsXAreaDTO;
import com.tcbuen.pqrs.modelo.dto.AnxsXTpSolDTO;
import com.tcbuen.pqrs.modelo.dto.AreasInvolucradasDTO;
import com.tcbuen.pqrs.modelo.dto.EstadisticasDTO;
import com.tcbuen.pqrs.modelo.dto.InfoSolicitanteDTO;
import com.tcbuen.pqrs.modelo.dto.MotReclSelectDTO;
import com.tcbuen.pqrs.modelo.dto.MotReclXTpSolDTO;
import com.tcbuen.pqrs.modelo.dto.MotSolSelectDTO;
import com.tcbuen.pqrs.modelo.dto.MotSolXTpSolDTO;
import com.tcbuen.pqrs.modelo.dto.MotivoReclamacionDTO;
import com.tcbuen.pqrs.modelo.dto.MotivoSolicitudDTO;
import com.tcbuen.pqrs.modelo.dto.ParametrosPqrDTO;
import com.tcbuen.pqrs.modelo.dto.RespuestaSolDTO;
import com.tcbuen.pqrs.modelo.dto.RolesDTO;
import com.tcbuen.pqrs.modelo.dto.SolicitudAsignadaAreaDTO;
import com.tcbuen.pqrs.modelo.dto.SolicitudDTO;
import com.tcbuen.pqrs.modelo.dto.SolicitudPqrDTO;
import com.tcbuen.pqrs.modelo.dto.TipoDocumentoDTO;
import com.tcbuen.pqrs.modelo.dto.TipoEstadoPqrDTO;
import com.tcbuen.pqrs.modelo.dto.TipoSolicitudPqrDTO;
import com.tcbuen.pqrs.modelo.dto.UsuariosInternosDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface IBusinessDelegatorView {
    public List<AnexosPqr> getAnexosPqr() throws Exception;

    public void saveAnexosPqr(AnexosPqr entity) throws Exception;

    public void deleteAnexosPqr(AnexosPqr entity) throws Exception;

    public void updateAnexosPqr(AnexosPqr entity) throws Exception;

    public AnexosPqr getAnexosPqr(Long idAnexoPqr) throws Exception;

    public List<AnexosPqr> findByCriteriaInAnexosPqr(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<AnexosPqr> findPageAnexosPqr(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberAnexosPqr() throws Exception;

    public List<AnexosPqrDTO> getDataAnexosPqr() throws Exception;

    public List<AnexosRespuesta> getAnexosRespuesta() throws Exception;

    public void saveAnexosRespuesta(AnexosRespuesta entity)
        throws Exception;

    public void deleteAnexosRespuesta(AnexosRespuesta entity)
        throws Exception;

    public void updateAnexosRespuesta(AnexosRespuesta entity)
        throws Exception;

    public AnexosRespuesta getAnexosRespuesta(Long idAnxResp)
        throws Exception;

    public List<AnexosRespuesta> findByCriteriaInAnexosRespuesta(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<AnexosRespuesta> findPageAnexosRespuesta(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberAnexosRespuesta() throws Exception;

    public List<AnexosRespuestaDTO> getDataAnexosRespuesta()
        throws Exception;

    public List<AnexosSolicitante> getAnexosSolicitante()
        throws Exception;

    public void saveAnexosSolicitante(AnexosSolicitante entity)
        throws Exception;

    public void deleteAnexosSolicitante(AnexosSolicitante entity)
        throws Exception;

    public void updateAnexosSolicitante(AnexosSolicitante entity)
        throws Exception;

    public AnexosSolicitante getAnexosSolicitante(Long idAnxSolicitante)
        throws Exception;

    public List<AnexosSolicitante> findByCriteriaInAnexosSolicitante(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<AnexosSolicitante> findPageAnexosSolicitante(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberAnexosSolicitante() throws Exception;

    public List<AnexosSolicitanteDTO> getDataAnexosSolicitante()
        throws Exception;

    public List<AnxsXArea> getAnxsXArea() throws Exception;

    public void saveAnxsXArea(AnxsXArea entity) throws Exception;

    public void deleteAnxsXArea(AnxsXArea entity) throws Exception;

    public void updateAnxsXArea(AnxsXArea entity) throws Exception;

    public AnxsXArea getAnxsXArea(Long idAnxXArea) throws Exception;

    public List<AnxsXArea> findByCriteriaInAnxsXArea(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<AnxsXArea> findPageAnxsXArea(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberAnxsXArea() throws Exception;

    public List<AnxsXAreaDTO> getDataAnxsXArea() throws Exception;

    public List<AnxsXTpSol> getAnxsXTpSol() throws Exception;

    public void saveAnxsXTpSol(AnxsXTpSol entity) throws Exception;

    public void deleteAnxsXTpSol(AnxsXTpSol entity) throws Exception;

    public void updateAnxsXTpSol(AnxsXTpSol entity) throws Exception;

    public AnxsXTpSol getAnxsXTpSol(Long idAnexoXTpSol)
        throws Exception;

    public List<AnxsXTpSol> findByCriteriaInAnxsXTpSol(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<AnxsXTpSol> findPageAnxsXTpSol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberAnxsXTpSol() throws Exception;

    public List<AnxsXTpSolDTO> getDataAnxsXTpSol() throws Exception;

    public List<AreasInvolucradas> getAreasInvolucradas()
        throws Exception;
    
    public List<AnexosPqr> consultarAnexos() throws Exception;
    
    public List<AnexosPqr> consultarAnxsXTipoPqr(TipoSolicitudPqr tipoSolicitudPqr)
        	throws Exception;
    
    public List<AnexosPqr> consultarAnxsNoTipoPqr (TipoSolicitudPqr tipoSolicitudPqr)
        	throws Exception;
    
	public List<AnexosPqr> consultarAnxsXArea(AreasInvolucradas areasInvolucradas)
			throws Exception;
	
	public List<AnexosPqr> consultarAnxsNoArea(AreasInvolucradas areasInvolucradas)
			throws Exception;
	
	public List<AreasInvolucradas> consultarTodasAreaXAnxs() throws Exception;
	
	public List<AreasInvolucradas> consultarNoAreaXAnxs() throws Exception;

    public void saveAreasInvolucradas(AreasInvolucradas entity)
        throws Exception;

    public void deleteAreasInvolucradas(AreasInvolucradas entity)
        throws Exception;

    public void updateAreasInvolucradas(AreasInvolucradas entity)
        throws Exception;

    public AreasInvolucradas getAreasInvolucradas(Long idAreaInvolucrada)
        throws Exception;

    public List<AreasInvolucradas> findByCriteriaInAreasInvolucradas(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<AreasInvolucradas> findPageAreasInvolucradas(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberAreasInvolucradas() throws Exception;

    public List<AreasInvolucradasDTO> getDataAreasInvolucradas()
        throws Exception;

    public List<InfoSolicitante> getInfoSolicitante() throws Exception;

    public void saveInfoSolicitante(InfoSolicitante entity)
        throws Exception;

    public void deleteInfoSolicitante(InfoSolicitante entity)
        throws Exception;

    public void updateInfoSolicitante(InfoSolicitante entity)
        throws Exception;

    public InfoSolicitante getInfoSolicitante(Long idInfoSolicitante)
        throws Exception;

    public List<InfoSolicitante> findByCriteriaInInfoSolicitante(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<InfoSolicitante> findPageInfoSolicitante(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberInfoSolicitante() throws Exception;

    public List<InfoSolicitanteDTO> getDataInfoSolicitante()
        throws Exception;

    public List<MotivoReclamacion> getMotivoReclamacion()
        throws Exception;
    
    public List<MotivoReclamacion> consultarMotivosReclamacion() throws Exception;
    
    public List<MotivoReclamacion> consultarMotReclXTipoPqr (TipoSolicitudPqr tipoSolicitudPqr)
        	throws Exception;
    
    public List<MotivoReclamacion> consultarMotReclNoTipoPqr (TipoSolicitudPqr tipoSolicitudPqr)
        	throws Exception;

    public void saveMotivoReclamacion(MotivoReclamacion entity)
        throws Exception;

    public void deleteMotivoReclamacion(MotivoReclamacion entity)
        throws Exception;

    public void updateMotivoReclamacion(MotivoReclamacion entity)
        throws Exception;

    public MotivoReclamacion getMotivoReclamacion(Long idMotRecl)
        throws Exception;

    public List<MotivoReclamacion> findByCriteriaInMotivoReclamacion(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<MotivoReclamacion> findPageMotivoReclamacion(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberMotivoReclamacion() throws Exception;

    public List<MotivoReclamacionDTO> getDataMotivoReclamacion()
        throws Exception;

    public List<MotivoSolicitud> getMotivoSolicitud() throws Exception;
    
    public List<MotivoSolicitud> consultarMotivosSolicitud() throws Exception;
    
    public List<MotivoSolicitud> consultarMotSolXTipoPqr (TipoSolicitudPqr tipoSolicitudPqr)
        	throws Exception;
    
    public List<MotivoSolicitud> consultarMotSolNoTipoPqr (TipoSolicitudPqr tipoSolicitudPqr)
        	throws Exception;

    public void saveMotivoSolicitud(MotivoSolicitud entity)
        throws Exception;

    public void deleteMotivoSolicitud(MotivoSolicitud entity)
        throws Exception;

    public void updateMotivoSolicitud(MotivoSolicitud entity)
        throws Exception;

    public MotivoSolicitud getMotivoSolicitud(Long idMotSol)
        throws Exception;

    public List<MotivoSolicitud> findByCriteriaInMotivoSolicitud(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<MotivoSolicitud> findPageMotivoSolicitud(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberMotivoSolicitud() throws Exception;

    public List<MotivoSolicitudDTO> getDataMotivoSolicitud()
        throws Exception;

    public List<MotReclSelect> getMotReclSelect() throws Exception;

    public void saveMotReclSelect(MotReclSelect entity)
        throws Exception;

    public void deleteMotReclSelect(MotReclSelect entity)
        throws Exception;

    public void updateMotReclSelect(MotReclSelect entity)
        throws Exception;

    public MotReclSelect getMotReclSelect(Long idMotReclSelect)
        throws Exception;

    public List<MotReclSelect> findByCriteriaInMotReclSelect(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<MotReclSelect> findPageMotReclSelect(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberMotReclSelect() throws Exception;

    public List<MotReclSelectDTO> getDataMotReclSelect()
        throws Exception;

    public List<MotReclXTpSol> getMotReclXTpSol() throws Exception;
    
    public void saveMotReclXTpSol(MotReclXTpSol entity)
        throws Exception;

    public void deleteMotReclXTpSol(MotReclXTpSol entity)
        throws Exception;

    public void updateMotReclXTpSol(MotReclXTpSol entity)
        throws Exception;

    public MotReclXTpSol getMotReclXTpSol(Long idMotReclXTpSol)
        throws Exception;

    public List<MotReclXTpSol> findByCriteriaInMotReclXTpSol(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<MotReclXTpSol> findPageMotReclXTpSol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberMotReclXTpSol() throws Exception;

    public List<MotReclXTpSolDTO> getDataMotReclXTpSol()
        throws Exception;

    public List<MotSolSelect> getMotSolSelect() throws Exception;

    public void saveMotSolSelect(MotSolSelect entity) throws Exception;

    public void deleteMotSolSelect(MotSolSelect entity)
        throws Exception;

    public void updateMotSolSelect(MotSolSelect entity)
        throws Exception;

    public MotSolSelect getMotSolSelect(Long idMotSolSelected)
        throws Exception;

    public List<MotSolSelect> findByCriteriaInMotSolSelect(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<MotSolSelect> findPageMotSolSelect(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberMotSolSelect() throws Exception;

    public List<MotSolSelectDTO> getDataMotSolSelect()
        throws Exception;

    public List<MotSolXTpSol> getMotSolXTpSol() throws Exception;

    public void saveMotSolXTpSol(MotSolXTpSol entity) throws Exception;

    public void deleteMotSolXTpSol(MotSolXTpSol entity)
        throws Exception;

    public void updateMotSolXTpSol(MotSolXTpSol entity)
        throws Exception;

    public MotSolXTpSol getMotSolXTpSol(Long idMotSolXTpSol)
        throws Exception;

    public List<MotSolXTpSol> findByCriteriaInMotSolXTpSol(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<MotSolXTpSol> findPageMotSolXTpSol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberMotSolXTpSol() throws Exception;

    public List<MotSolXTpSolDTO> getDataMotSolXTpSol()
        throws Exception;

    public List<ParametrosPqr> getParametrosPqr() throws Exception;

    public void saveParametrosPqr(ParametrosPqr entity)
        throws Exception;

    public void deleteParametrosPqr(ParametrosPqr entity)
        throws Exception;

    public void updateParametrosPqr(ParametrosPqr entity)
        throws Exception;

    public ParametrosPqr getParametrosPqr(Long idParam)
        throws Exception;

    public List<ParametrosPqr> findByCriteriaInParametrosPqr(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<ParametrosPqr> findPageParametrosPqr(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberParametrosPqr() throws Exception;

    public List<ParametrosPqrDTO> getDataParametrosPqr()
        throws Exception;

    public List<RespuestaSol> getRespuestaSol() throws Exception;

    public void saveRespuestaSol(RespuestaSol entity) throws Exception;

    public void deleteRespuestaSol(RespuestaSol entity)
        throws Exception;

    public void updateRespuestaSol(RespuestaSol entity)
        throws Exception;
    
    public List<RespuestaSol> consultarRespuestasSolicitud(Long idSolPqr) 
    	throws Exception;
    
    public RespuestaSol getRespuestaSol(Long idRespSol)
        throws Exception;

    public List<RespuestaSol> findByCriteriaInRespuestaSol(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<RespuestaSol> findPageRespuestaSol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberRespuestaSol() throws Exception;

    public List<RespuestaSolDTO> getDataRespuestaSol()
        throws Exception;

    public List<Roles> getRoles() throws Exception;

    public void saveRoles(Roles entity) throws Exception;

    public void deleteRoles(Roles entity) throws Exception;

    public void updateRoles(Roles entity) throws Exception;

    public Roles getRoles(Long idRol) throws Exception;

    public List<Roles> findByCriteriaInRoles(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Roles> findPageRoles(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberRoles() throws Exception;

    public List<RolesDTO> getDataRoles() throws Exception;

    public List<SolicitudAsignadaArea> getSolicitudAsignadaArea()
        throws Exception;

    public void saveSolicitudAsignadaArea(SolicitudAsignadaArea entity)
        throws Exception;

    public void deleteSolicitudAsignadaArea(SolicitudAsignadaArea entity)
        throws Exception;

    public void updateSolicitudAsignadaArea(SolicitudAsignadaArea entity)
        throws Exception;

    public SolicitudAsignadaArea getSolicitudAsignadaArea(Long idSolAsigArea)
        throws Exception;

    public List<SolicitudAsignadaArea> findByCriteriaInSolicitudAsignadaArea(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<SolicitudAsignadaArea> findPageSolicitudAsignadaArea(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberSolicitudAsignadaArea()
        throws Exception;

    public List<SolicitudAsignadaAreaDTO> getDataSolicitudAsignadaArea()
        throws Exception;

    public List<SolicitudPqr> getSolicitudPqr() throws Exception;
    
   

    public void saveSolicitudPqr(SolicitudPqr entity) throws Exception;
    
    public void saveSolicitud(InfoSolicitante infoSol, SolicitudPqr solicitudPqr, 
    		MotSolSelect motSolSelect, MotReclSelect motReclSelect, 
    		SolicitudAsignadaArea solicitudAsignadaArea,
    		List<AnexosSolicitante> anexosSolicitantes) throws Exception;
    
    public void saveRespuestaSolicitud(SolicitudAsignadaArea solicitudAsignadaArea, 
    		RespuestaSol respuestaSol, List<AnexosRespuesta> anexosRespuestas) throws Exception;
    
    public List<SolicitudDTO> consultarAsignacion(AreasInvolucradas area) throws Exception;
    
    public List<SolicitudPqr> consultarSolicitudes(Long idAreaInvolucrada) throws Exception;

    public void deleteSolicitudPqr(SolicitudPqr entity)
        throws Exception;

    public void updateSolicitudPqr(SolicitudPqr entity)
        throws Exception;

    public SolicitudPqr getSolicitudPqr(Long idSolPqr)
        throws Exception;

    public List<SolicitudPqr> findByCriteriaInSolicitudPqr(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SolicitudPqr> findPageSolicitudPqr(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSolicitudPqr() throws Exception;

    public List<SolicitudPqrDTO> getDataSolicitudPqr()
        throws Exception;

    public List<TipoDocumento> getTipoDocumento() throws Exception;

    public void saveTipoDocumento(TipoDocumento entity)
        throws Exception;

    public void deleteTipoDocumento(TipoDocumento entity)
        throws Exception;

    public void updateTipoDocumento(TipoDocumento entity)
        throws Exception;

    public TipoDocumento getTipoDocumento(Long idTpDoc)
        throws Exception;

    public List<TipoDocumento> findByCriteriaInTipoDocumento(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<TipoDocumento> findPageTipoDocumento(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipoDocumento() throws Exception;

    public List<TipoDocumentoDTO> getDataTipoDocumento()
        throws Exception;

    public List<TipoEstadoPqr> getTipoEstadoPqr() throws Exception;

    public void saveTipoEstadoPqr(TipoEstadoPqr entity)
        throws Exception;

    public void deleteTipoEstadoPqr(TipoEstadoPqr entity)
        throws Exception;

    public void updateTipoEstadoPqr(TipoEstadoPqr entity)
        throws Exception;

    public TipoEstadoPqr getTipoEstadoPqr(Long idTpEstPqr)
        throws Exception;

    public List<TipoEstadoPqr> findByCriteriaInTipoEstadoPqr(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<TipoEstadoPqr> findPageTipoEstadoPqr(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipoEstadoPqr() throws Exception;

    public List<TipoEstadoPqrDTO> getDataTipoEstadoPqr()
        throws Exception;

    public List<TipoSolicitudPqr> getTipoSolicitudPqr()
        throws Exception;

    public void saveTipoSolicitudPqr(TipoSolicitudPqr entity)
        throws Exception;

    public void deleteTipoSolicitudPqr(TipoSolicitudPqr entity)
        throws Exception;

    public void updateTipoSolicitudPqr(TipoSolicitudPqr entity)
        throws Exception;

    public TipoSolicitudPqr getTipoSolicitudPqr(Long idTpSolPqr)
        throws Exception;
    
    public void save_mot_recl_mot_sol_anxs_x_tipo(TipoSolicitudPqr tipoSol,
    		List<MotivoReclamacion> motivosReclamacionTargetCopia,
    		List<MotivoReclamacion> motivosReclamacionTarget,
    		List<MotivoSolicitud> motivosSolicitudTargetCopia,
			List<MotivoSolicitud> motivosSolicitudTarget,
			List<AnexosPqr> anexosPqrTargetCopia,
			List<AnexosPqr> anexosPqrTarget, String esObligatorioSeleccionado) throws Exception;
    
    public void save_anxs_x_area(AreasInvolucradas areasInvolucradas,
			List<AnexosPqr> anexosPqrTargetCopia,
			List<AnexosPqr> anexosPqrTarget, String esObligatorioSeleccionado) throws Exception;

    public List<TipoSolicitudPqr> findByCriteriaInTipoSolicitudPqr(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<TipoSolicitudPqr> findPageTipoSolicitudPqr(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberTipoSolicitudPqr() throws Exception;

    public List<TipoSolicitudPqrDTO> getDataTipoSolicitudPqr()
        throws Exception;

    public List<UsuariosInternos> getUsuariosInternos()
        throws Exception;

    public void saveUsuariosInternos(UsuariosInternos entity)
        throws Exception;

    public void deleteUsuariosInternos(UsuariosInternos entity)
        throws Exception;

    public void updateUsuariosInternos(UsuariosInternos entity)
        throws Exception;

    public UsuariosInternos getUsuariosInternos(Long idUsuInterno)
        throws Exception;

    public List<UsuariosInternos> findByCriteriaInUsuariosInternos(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<UsuariosInternos> findPageUsuariosInternos(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberUsuariosInternos() throws Exception;

    public List<UsuariosInternosDTO> getDataUsuariosInternos()
        throws Exception;
    
    public UsuariosInternos consultarLoginContrasena(String login, String contrasena) throws Exception;
    
    public List<EstadisticasDTO> consultarSolicitudPorEstado(String estado) throws Exception;
    
    public List<EstadisticasDTO> consultarSolicitudMotivoReclamacion(Long idMotivoReclamacion) throws Exception ;
    
    public List<EstadisticasDTO> consultarSolicitudNumeroRadicacion(String numeroRadicacion) throws Exception;
    
    public List<EstadisticasDTO> consultarSolicitudPorFecha(String fechaInicio, String fechaFin) throws Exception;
    
    public List<EstadisticasDTO> consultarSolicitudMotivoReclamacionEstado(Long idMotivoReclamacion, String estado) throws Exception;
    
    public List<EstadisticasDTO> consultarSolicitudPorEstadoYFechas(String estado, String fechaInicio, String fechaFin) throws Exception;
    
    public List<EstadisticasDTO> consultarSolicitudPorReclamacionYFechas(Long idMotivoReclamacion, String fechaInicio, String fechaFin) throws Exception;
    
    public List<EstadisticasDTO> consultarSolicitudPorReclamacionEstadoYFechas(Long idMotivoReclamacion, String estado, String fechaInicio, String fechaFin) throws Exception;
}