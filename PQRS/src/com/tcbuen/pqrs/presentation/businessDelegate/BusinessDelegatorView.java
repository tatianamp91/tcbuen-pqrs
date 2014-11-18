package com.tcbuen.pqrs.presentation.businessDelegate;

import com.tcbuen.pqrs.mail.IMail;
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
import com.tcbuen.pqrs.modelo.control.ISolicitudLogic;
import com.tcbuen.pqrs.modelo.control.ISolicitudPqrLogic;
import com.tcbuen.pqrs.modelo.control.ITipoDocumentoLogic;
import com.tcbuen.pqrs.modelo.control.ITipoEstadoPqrLogic;
import com.tcbuen.pqrs.modelo.control.ITipoSolicitudPqrLogic;
import com.tcbuen.pqrs.modelo.control.IUsuariosInternosLogic;
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
import com.tcbuen.pqrs.presentation.businessDelegate.IBusinessDelegatorView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* Use a Business Delegate to reduce coupling between presentation-tier clients and business services.
* The Business Delegate hides the underlying implementation details of the business service, such as lookup and access details of the EJB architecture.
*
* The Business Delegate acts as a client-side business abstraction; it provides an abstraction for, and thus hides,
* the implementation of the business services. Using a Business Delegate reduces the coupling between presentation-tier clients and
* the system's business services. Depending on the implementation strategy, the Business Delegate may shield clients from possible
* volatility in the implementation of the business service API. Potentially, this reduces the number of changes that must be made to the
* presentation-tier client code when the business service API or its underlying implementation changes.
*
* However, interface methods in the Business Delegate may still require modification if the underlying business service API changes.
* Admittedly, though, it is more likely that changes will be made to the business service rather than to the Business Delegate.
*
* Often, developers are skeptical when a design goal such as abstracting the business layer causes additional upfront work in return
* for future gains. However, using this pattern or its strategies results in only a small amount of additional upfront work and provides
* considerable benefits. The main benefit is hiding the details of the underlying service. For example, the client can become transparent
* to naming and lookup services. The Business Delegate also handles the exceptions from the business services, such as java.rmi.Remote
* exceptions, Java Messages Service (JMS) exceptions and so on. The Business Delegate may intercept such service level exceptions and
* generate application level exceptions instead. Application level exceptions are easier to handle by the clients, and may be user friendly.
* The Business Delegate may also transparently perform any retry or recovery operations necessary in the event of a service failure without
* exposing the client to the problem until it is determined that the problem is not resolvable. These gains present a compelling reason to
* use the pattern.
*
* Another benefit is that the delegate may cache results and references to remote business services. Caching can significantly improve performance,
* because it limits unnecessary and potentially costly round trips over the network.
*
* A Business Delegate uses a component called the Lookup Service. The Lookup Service is responsible for hiding the underlying implementation
* details of the business service lookup code. The Lookup Service may be written as part of the Delegate, but we recommend that it be
* implemented as a separate component, as outlined in the Service Locator pattern (See "Service Locator" on page 368.)
*
* When the Business Delegate is used with a Session Facade, typically there is a one-to-one relationship between the two.
* This one-to-one relationship exists because logic that might have been encapsulated in a Business Delegate relating to its interaction
* with multiple business services (creating a one-to-many relationship) will often be factored back into a Session Facade.
*
* Finally, it should be noted that this pattern could be used to reduce coupling between other tiers, not simply the presentation and the
* business tiers.
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
@Scope("singleton")
@Service("BusinessDelegatorView")
public class BusinessDelegatorView implements IBusinessDelegatorView {
	@Autowired
	private IMail mail; 	
    @Autowired
    private IAnexosPqrLogic anexosPqrLogic;
    @Autowired
    private IAnexosRespuestaLogic anexosRespuestaLogic;
    @Autowired
    private IAnexosSolicitanteLogic anexosSolicitanteLogic;
    @Autowired
    private IAnxsXAreaLogic anxsXAreaLogic;
    @Autowired
    private IAnxsXTpSolLogic anxsXTpSolLogic;
    @Autowired
    private IAreasInvolucradasLogic areasInvolucradasLogic;
    @Autowired
    private IInfoSolicitanteLogic infoSolicitanteLogic;
    @Autowired
    private IMotivoReclamacionLogic motivoReclamacionLogic;
    @Autowired
    private IMotivoSolicitudLogic motivoSolicitudLogic;
    @Autowired
    private IMotReclSelectLogic motReclSelectLogic;
    @Autowired
    private IMotReclXTpSolLogic motReclXTpSolLogic;
    @Autowired
    private IMotSolSelectLogic motSolSelectLogic;
    @Autowired
    private IMotSolXTpSolLogic motSolXTpSolLogic;
    @Autowired
    private IParametrosPqrLogic parametrosPqrLogic;
    @Autowired
    private IRespuestaSolLogic respuestaSolLogic;
    @Autowired
    private IRolesLogic rolesLogic;
    @Autowired
    private ISolicitudAsignadaAreaLogic solicitudAsignadaAreaLogic;
    @Autowired
    private ISolicitudPqrLogic solicitudPqrLogic;
    @Autowired
    private ITipoDocumentoLogic tipoDocumentoLogic;
    @Autowired
    private ITipoEstadoPqrLogic tipoEstadoPqrLogic;
    @Autowired
    private ITipoSolicitudPqrLogic tipoSolicitudPqrLogic;
    @Autowired
    private IUsuariosInternosLogic usuariosInternosLogic;
    @Autowired
    private ISolicitudLogic solicitudLogic;
    
    public void send(String destino,String asunto, String mensaje){
    	mail.send(destino, asunto, mensaje);
    }

    public List<AnexosPqr> getAnexosPqr() throws Exception {
        return anexosPqrLogic.getAnexosPqr();
    }

    public void saveAnexosPqr(AnexosPqr entity) throws Exception {
        anexosPqrLogic.saveAnexosPqr(entity);
    }

    public void deleteAnexosPqr(AnexosPqr entity) throws Exception {
        anexosPqrLogic.deleteAnexosPqr(entity);
    }

    public void updateAnexosPqr(AnexosPqr entity) throws Exception {
        anexosPqrLogic.updateAnexosPqr(entity);
    }

    public AnexosPqr getAnexosPqr(Long idAnexoPqr) throws Exception {
        AnexosPqr anexosPqr = null;

        try {
            anexosPqr = anexosPqrLogic.getAnexosPqr(idAnexoPqr);
        } catch (Exception e) {
            throw e;
        }

        return anexosPqr;
    }

    public List<AnexosPqr> findByCriteriaInAnexosPqr(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return anexosPqrLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<AnexosPqr> findPageAnexosPqr(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return anexosPqrLogic.findPageAnexosPqr(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberAnexosPqr() throws Exception {
        return anexosPqrLogic.findTotalNumberAnexosPqr();
    }

    public List<AnexosPqrDTO> getDataAnexosPqr() throws Exception {
        return anexosPqrLogic.getDataAnexosPqr();
    }

    public List<AnexosRespuesta> getAnexosRespuesta() throws Exception {
        return anexosRespuestaLogic.getAnexosRespuesta();
    }

    public void saveAnexosRespuesta(AnexosRespuesta entity)
        throws Exception {
        anexosRespuestaLogic.saveAnexosRespuesta(entity);
    }

    public void deleteAnexosRespuesta(AnexosRespuesta entity)
        throws Exception {
        anexosRespuestaLogic.deleteAnexosRespuesta(entity);
    }

    public void updateAnexosRespuesta(AnexosRespuesta entity)
        throws Exception {
        anexosRespuestaLogic.updateAnexosRespuesta(entity);
    }
    
    public List<AnexosRespuesta> consultarAnexosRespuesta(Long idRespSol) 
    	throws Exception {
    	return anexosRespuestaLogic.consultarAnexosRespuesta(idRespSol);
    }

    public AnexosRespuesta getAnexosRespuesta(Long idAnxResp)
        throws Exception {
        AnexosRespuesta anexosRespuesta = null;

        try {
            anexosRespuesta = anexosRespuestaLogic.getAnexosRespuesta(idAnxResp);
        } catch (Exception e) {
            throw e;
        }

        return anexosRespuesta;
    }

    public List<AnexosRespuesta> findByCriteriaInAnexosRespuesta(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return anexosRespuestaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<AnexosRespuesta> findPageAnexosRespuesta(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return anexosRespuestaLogic.findPageAnexosRespuesta(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberAnexosRespuesta() throws Exception {
        return anexosRespuestaLogic.findTotalNumberAnexosRespuesta();
    }

    public List<AnexosRespuestaDTO> getDataAnexosRespuesta()
        throws Exception {
        return anexosRespuestaLogic.getDataAnexosRespuesta();
    }

    public List<AnexosSolicitante> getAnexosSolicitante()
        throws Exception {
        return anexosSolicitanteLogic.getAnexosSolicitante();
    }

    public void saveAnexosSolicitante(AnexosSolicitante entity)
        throws Exception {
        anexosSolicitanteLogic.saveAnexosSolicitante(entity);
    }

    public void deleteAnexosSolicitante(AnexosSolicitante entity)
        throws Exception {
        anexosSolicitanteLogic.deleteAnexosSolicitante(entity);
    }

    public void updateAnexosSolicitante(AnexosSolicitante entity)
        throws Exception {
        anexosSolicitanteLogic.updateAnexosSolicitante(entity);
    }

    public AnexosSolicitante getAnexosSolicitante(Long idAnxSolicitante)
        throws Exception {
        AnexosSolicitante anexosSolicitante = null;

        try {
            anexosSolicitante = anexosSolicitanteLogic.getAnexosSolicitante(idAnxSolicitante);
        } catch (Exception e) {
            throw e;
        }

        return anexosSolicitante;
    }

    public List<AnexosSolicitante> findByCriteriaInAnexosSolicitante(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return anexosSolicitanteLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<AnexosSolicitante> findPageAnexosSolicitante(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return anexosSolicitanteLogic.findPageAnexosSolicitante(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberAnexosSolicitante() throws Exception {
        return anexosSolicitanteLogic.findTotalNumberAnexosSolicitante();
    }

    public List<AnexosSolicitanteDTO> getDataAnexosSolicitante()
        throws Exception {
        return anexosSolicitanteLogic.getDataAnexosSolicitante();
    }

    public List<AnxsXArea> getAnxsXArea() throws Exception {
        return anxsXAreaLogic.getAnxsXArea();
    }

    public void saveAnxsXArea(AnxsXArea entity) throws Exception {
        anxsXAreaLogic.saveAnxsXArea(entity);
    }

    public void deleteAnxsXArea(AnxsXArea entity) throws Exception {
        anxsXAreaLogic.deleteAnxsXArea(entity);
    }

    public void updateAnxsXArea(AnxsXArea entity) throws Exception {
        anxsXAreaLogic.updateAnxsXArea(entity);
    }

    public AnxsXArea getAnxsXArea(Long idAnxXArea) throws Exception {
        AnxsXArea anxsXArea = null;

        try {
            anxsXArea = anxsXAreaLogic.getAnxsXArea(idAnxXArea);
        } catch (Exception e) {
            throw e;
        }

        return anxsXArea;
    }

    public List<AnxsXArea> findByCriteriaInAnxsXArea(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return anxsXAreaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<AnxsXArea> findPageAnxsXArea(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return anxsXAreaLogic.findPageAnxsXArea(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberAnxsXArea() throws Exception {
        return anxsXAreaLogic.findTotalNumberAnxsXArea();
    }

    public List<AnxsXAreaDTO> getDataAnxsXArea() throws Exception {
        return anxsXAreaLogic.getDataAnxsXArea();
    }

    public List<AnxsXTpSol> getAnxsXTpSol() throws Exception {
        return anxsXTpSolLogic.getAnxsXTpSol();
    }

    public void saveAnxsXTpSol(AnxsXTpSol entity) throws Exception {
        anxsXTpSolLogic.saveAnxsXTpSol(entity);
    }

    public void deleteAnxsXTpSol(AnxsXTpSol entity) throws Exception {
        anxsXTpSolLogic.deleteAnxsXTpSol(entity);
    }

    public void updateAnxsXTpSol(AnxsXTpSol entity) throws Exception {
        anxsXTpSolLogic.updateAnxsXTpSol(entity);
    }

    public AnxsXTpSol getAnxsXTpSol(Long idAnexoXTpSol)
        throws Exception {
        AnxsXTpSol anxsXTpSol = null;

        try {
            anxsXTpSol = anxsXTpSolLogic.getAnxsXTpSol(idAnexoXTpSol);
        } catch (Exception e) {
            throw e;
        }

        return anxsXTpSol;
    }

    public List<AnxsXTpSol> findByCriteriaInAnxsXTpSol(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return anxsXTpSolLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<AnxsXTpSol> findPageAnxsXTpSol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return anxsXTpSolLogic.findPageAnxsXTpSol(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberAnxsXTpSol() throws Exception {
        return anxsXTpSolLogic.findTotalNumberAnxsXTpSol();
    }

    public List<AnxsXTpSolDTO> getDataAnxsXTpSol() throws Exception {
        return anxsXTpSolLogic.getDataAnxsXTpSol();
    }

    public List<AreasInvolucradas> getAreasInvolucradas()
        throws Exception {
        return areasInvolucradasLogic.getAreasInvolucradas();
    }
    
    public List<AnexosPqr> consultarAnexos() throws Exception {
        List<AnexosPqr> anexosPqr = null;
        try{
        	anexosPqr = anexosPqrLogic.consultarAnexos();		
        }catch(Exception e){
        	throw e;
        }
        return anexosPqr;
    }
    
    public List<AnexosPqr> consultarAnxsXTipoPqr(TipoSolicitudPqr tipoSolicitudPqr)
        throws Exception{
        List<AnexosPqr> anexosPqr = null;
        try{
        	anexosPqr = anexosPqrLogic.consultarAnxsXTipoPqr(tipoSolicitudPqr); 		
        }catch(Exception e){
        	throw e;
        }
        return anexosPqr;
    }
    
    public List<AnexosPqr> consultarAnxsNoTipoPqr(TipoSolicitudPqr tipoSolicitudPqr)
    	throws Exception{
        List<AnexosPqr> anexosPqr = null;
        try{
        	anexosPqr = anexosPqrLogic.consultarAnxsNoTipoPqr(tipoSolicitudPqr); 		
        }catch(Exception e){
        	throw e;
        }
        	return anexosPqr;
    }
    
    public List<AnexosPqr> consultarAnxsXArea(AreasInvolucradas areasInvolucradas)
            throws Exception{
            List<AnexosPqr> anexosPqr = null;
            try{
            	anexosPqr = anexosPqrLogic.consultarAnxsXArea(areasInvolucradas); 		
            }catch(Exception e){
            	throw e;
            }
            return anexosPqr;
    }
    
    public List<AnexosPqr> consultarAnxsNoArea(AreasInvolucradas areasInvolucradas)
        	throws Exception{
            List<AnexosPqr> anexosPqr = null;
            try{
            	anexosPqr = anexosPqrLogic.consultarAnxsNoArea(areasInvolucradas); 		
            }catch(Exception e){
            	throw e;
            }
            return anexosPqr;
    }
    
    public List<AreasInvolucradas> consultarTodasAreaXAnxs() throws Exception{
            List<AreasInvolucradas> areasInvolucradas = null;
            try{
            	areasInvolucradas = areasInvolucradasLogic.consultarTodasAreaXAnxs();
            }catch(Exception e){
            	throw e;
            }
            return areasInvolucradas;
    }
    
    public List<AreasInvolucradas> consultarNoAreaXAnxs() throws Exception{
        List<AreasInvolucradas> areasInvolucradas = null;
        try{
        	areasInvolucradas = areasInvolucradasLogic.consultarNoAreaXAnxs();
        }catch(Exception e){
        	throw e;
        }
        return areasInvolucradas;
    }

    public void saveAreasInvolucradas(AreasInvolucradas entity)
        throws Exception {
        areasInvolucradasLogic.saveAreasInvolucradas(entity);
    }

    public void deleteAreasInvolucradas(AreasInvolucradas entity)
        throws Exception {
        areasInvolucradasLogic.deleteAreasInvolucradas(entity);
    }

    public void updateAreasInvolucradas(AreasInvolucradas entity)
        throws Exception {
        areasInvolucradasLogic.updateAreasInvolucradas(entity);
    }

    public AreasInvolucradas getAreasInvolucradas(Long idAreaInvolucrada)
        throws Exception {
        AreasInvolucradas areasInvolucradas = null;

        try {
            areasInvolucradas = areasInvolucradasLogic.getAreasInvolucradas(idAreaInvolucrada);
        } catch (Exception e) {
            throw e;
        }

        return areasInvolucradas;
    }

    public List<AreasInvolucradas> findByCriteriaInAreasInvolucradas(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return areasInvolucradasLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<AreasInvolucradas> findPageAreasInvolucradas(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return areasInvolucradasLogic.findPageAreasInvolucradas(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberAreasInvolucradas() throws Exception {
        return areasInvolucradasLogic.findTotalNumberAreasInvolucradas();
    }

    public List<AreasInvolucradasDTO> getDataAreasInvolucradas()
        throws Exception {
        return areasInvolucradasLogic.getDataAreasInvolucradas();
    }

    public List<InfoSolicitante> getInfoSolicitante() throws Exception {
        return infoSolicitanteLogic.getInfoSolicitante();
    }

    public void saveInfoSolicitante(InfoSolicitante entity)
        throws Exception {
        infoSolicitanteLogic.saveInfoSolicitante(entity);
    }

    public void deleteInfoSolicitante(InfoSolicitante entity)
        throws Exception {
        infoSolicitanteLogic.deleteInfoSolicitante(entity);
    }

    public void updateInfoSolicitante(InfoSolicitante entity)
        throws Exception {
        infoSolicitanteLogic.updateInfoSolicitante(entity);
    }
    
    public InfoSolicitante getInfoSolicitante(Long idInfoSolicitante)
        throws Exception {
        InfoSolicitante infoSolicitante = null;

        try {
            infoSolicitante = infoSolicitanteLogic.getInfoSolicitante(idInfoSolicitante);
        } catch (Exception e) {
            throw e;
        }

        return infoSolicitante;
    }

    public List<InfoSolicitante> findByCriteriaInInfoSolicitante(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return infoSolicitanteLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<InfoSolicitante> findPageInfoSolicitante(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return infoSolicitanteLogic.findPageInfoSolicitante(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberInfoSolicitante() throws Exception {
        return infoSolicitanteLogic.findTotalNumberInfoSolicitante();
    }

    public List<InfoSolicitanteDTO> getDataInfoSolicitante()
        throws Exception {
        return infoSolicitanteLogic.getDataInfoSolicitante();
    }

    public List<MotivoReclamacion> getMotivoReclamacion()
        throws Exception {
        return motivoReclamacionLogic.getMotivoReclamacion();
    }
    
    public List<MotivoReclamacion> consultarMotivosReclamacion() throws Exception {
    	List<MotivoReclamacion> motivoReclamacion = null;
    	try{
    		motivoReclamacion = motivoReclamacionLogic.consultarMotivosReclamacion(); 		
    	}catch(Exception e){
    		throw e;
    	}
    	return motivoReclamacion;
    }
    
    public List<MotivoReclamacion> consultarMotReclXTipoPqr (TipoSolicitudPqr tipoSolicitudPqr)
        throws Exception{
    	List<MotivoReclamacion> motivoReclamacion = null;
    	try{
    		motivoReclamacion = motivoReclamacionLogic.consultarMotReclXTipoPqr(tipoSolicitudPqr); 		
    	}catch(Exception e){
    		throw e;
    	}
    	return motivoReclamacion;
    }
    
    public List<MotivoReclamacion> consultarMotReclNoTipoPqr (TipoSolicitudPqr tipoSolicitudPqr)
        	throws Exception{
    	List<MotivoReclamacion> motivoReclamacion = null;
    	try{
    		motivoReclamacion = motivoReclamacionLogic.consultarMotReclNoTipoPqr(tipoSolicitudPqr); 		
    	}catch(Exception e){
    		throw e;
    	}
    	return motivoReclamacion;
    }

    public void saveMotivoReclamacion(MotivoReclamacion entity)
        throws Exception {
        motivoReclamacionLogic.saveMotivoReclamacion(entity);
    }

    public void deleteMotivoReclamacion(MotivoReclamacion entity)
        throws Exception {
        motivoReclamacionLogic.deleteMotivoReclamacion(entity);
    }

    public void updateMotivoReclamacion(MotivoReclamacion entity)
        throws Exception {
        motivoReclamacionLogic.updateMotivoReclamacion(entity);
    }

    public MotivoReclamacion getMotivoReclamacion(Long idMotRecl)
        throws Exception {
        MotivoReclamacion motivoReclamacion = null;

        try {
            motivoReclamacion = motivoReclamacionLogic.getMotivoReclamacion(idMotRecl);
        } catch (Exception e) {
            throw e;
        }

        return motivoReclamacion;
    }       	

    public List<MotivoReclamacion> findByCriteriaInMotivoReclamacion(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return motivoReclamacionLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<MotivoReclamacion> findPageMotivoReclamacion(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return motivoReclamacionLogic.findPageMotivoReclamacion(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberMotivoReclamacion() throws Exception {
        return motivoReclamacionLogic.findTotalNumberMotivoReclamacion();
    }

    public List<MotivoReclamacionDTO> getDataMotivoReclamacion()
        throws Exception {
        return motivoReclamacionLogic.getDataMotivoReclamacion();
    }

    public List<MotivoSolicitud> getMotivoSolicitud() throws Exception {
        return motivoSolicitudLogic.getMotivoSolicitud();
    }
    
    public List<MotivoSolicitud> consultarMotivosSolicitud() throws Exception{
    	List<MotivoSolicitud> motivoSolicitud = null;
    	try{
    		motivoSolicitud = motivoSolicitudLogic.consultarMotivosSolicitud();		
    	}catch(Exception e){
    		throw e;
    	}
    	return motivoSolicitud;
    }
    
    public List<MotivoSolicitud> consultarMotSolXTipoPqr (TipoSolicitudPqr tipoSolicitudPqr)
            throws Exception{
        	List<MotivoSolicitud> motivoSolicitud = null;
        	try{
        		motivoSolicitud = motivoSolicitudLogic.consultarMotSolXTipoPqr(tipoSolicitudPqr); 		
        	}catch(Exception e){
        		throw e;
        	}
        	return motivoSolicitud;
    }
        
    public List<MotivoSolicitud> consultarMotSolNoTipoPqr (TipoSolicitudPqr tipoSolicitudPqr)
            	throws Exception{
        	List<MotivoSolicitud> motivoSolicitud = null;
        	try{
        		motivoSolicitud = motivoSolicitudLogic.consultarMotSolNoTipoPqr(tipoSolicitudPqr); 		
        	}catch(Exception e){
        		throw e;
        	}
        	return motivoSolicitud;
    }

    public void saveMotivoSolicitud(MotivoSolicitud entity)
        throws Exception {
        motivoSolicitudLogic.saveMotivoSolicitud(entity);
    }

    public void deleteMotivoSolicitud(MotivoSolicitud entity)
        throws Exception {
        motivoSolicitudLogic.deleteMotivoSolicitud(entity);
    }

    public void updateMotivoSolicitud(MotivoSolicitud entity)
        throws Exception {
        motivoSolicitudLogic.updateMotivoSolicitud(entity);
    }

    public MotivoSolicitud getMotivoSolicitud(Long idMotSol)
        throws Exception {
        MotivoSolicitud motivoSolicitud = null;

        try {
            motivoSolicitud = motivoSolicitudLogic.getMotivoSolicitud(idMotSol);
        } catch (Exception e) {
            throw e;
        }

        return motivoSolicitud;
    }

    public List<MotivoSolicitud> findByCriteriaInMotivoSolicitud(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return motivoSolicitudLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<MotivoSolicitud> findPageMotivoSolicitud(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return motivoSolicitudLogic.findPageMotivoSolicitud(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberMotivoSolicitud() throws Exception {
        return motivoSolicitudLogic.findTotalNumberMotivoSolicitud();
    }

    public List<MotivoSolicitudDTO> getDataMotivoSolicitud()
        throws Exception {
        return motivoSolicitudLogic.getDataMotivoSolicitud();
    }

    public List<MotReclSelect> getMotReclSelect() throws Exception {
        return motReclSelectLogic.getMotReclSelect();
    }

    public void saveMotReclSelect(MotReclSelect entity)
        throws Exception {
        motReclSelectLogic.saveMotReclSelect(entity);
    }

    public void deleteMotReclSelect(MotReclSelect entity)
        throws Exception {
        motReclSelectLogic.deleteMotReclSelect(entity);
    }

    public void updateMotReclSelect(MotReclSelect entity)
        throws Exception {
        motReclSelectLogic.updateMotReclSelect(entity);
    }

    public MotReclSelect getMotReclSelect(Long idMotReclSelect)
        throws Exception {
        MotReclSelect motReclSelect = null;

        try {
            motReclSelect = motReclSelectLogic.getMotReclSelect(idMotReclSelect);
        } catch (Exception e) {
            throw e;
        }

        return motReclSelect;
    }

    public List<MotReclSelect> findByCriteriaInMotReclSelect(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return motReclSelectLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<MotReclSelect> findPageMotReclSelect(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return motReclSelectLogic.findPageMotReclSelect(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberMotReclSelect() throws Exception {
        return motReclSelectLogic.findTotalNumberMotReclSelect();
    }

    public List<MotReclSelectDTO> getDataMotReclSelect()
        throws Exception {
        return motReclSelectLogic.getDataMotReclSelect();
    }

    public List<MotReclXTpSol> getMotReclXTpSol() throws Exception {
        return motReclXTpSolLogic.getMotReclXTpSol();
    }
    
    public void saveMotReclXTpSol(MotReclXTpSol entity)
        throws Exception {
        motReclXTpSolLogic.saveMotReclXTpSol(entity);
    }

    public void deleteMotReclXTpSol(MotReclXTpSol entity)
        throws Exception {
        motReclXTpSolLogic.deleteMotReclXTpSol(entity);
    }

    public void updateMotReclXTpSol(MotReclXTpSol entity)
        throws Exception {
        motReclXTpSolLogic.updateMotReclXTpSol(entity);
    }

    public MotReclXTpSol getMotReclXTpSol(Long idMotReclXTpSol)
        throws Exception {
        MotReclXTpSol motReclXTpSol = null;

        try {
            motReclXTpSol = motReclXTpSolLogic.getMotReclXTpSol(idMotReclXTpSol);
        } catch (Exception e) {
            throw e;
        }

        return motReclXTpSol;
    }

    public List<MotReclXTpSol> findByCriteriaInMotReclXTpSol(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return motReclXTpSolLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<MotReclXTpSol> findPageMotReclXTpSol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return motReclXTpSolLogic.findPageMotReclXTpSol(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberMotReclXTpSol() throws Exception {
        return motReclXTpSolLogic.findTotalNumberMotReclXTpSol();
    }

    public List<MotReclXTpSolDTO> getDataMotReclXTpSol()
        throws Exception {
        return motReclXTpSolLogic.getDataMotReclXTpSol();
    }

    public List<MotSolSelect> getMotSolSelect() throws Exception {
        return motSolSelectLogic.getMotSolSelect();
    }

    public void saveMotSolSelect(MotSolSelect entity) throws Exception {
        motSolSelectLogic.saveMotSolSelect(entity);
    }

    public void deleteMotSolSelect(MotSolSelect entity)
        throws Exception {
        motSolSelectLogic.deleteMotSolSelect(entity);
    }

    public void updateMotSolSelect(MotSolSelect entity)
        throws Exception {
        motSolSelectLogic.updateMotSolSelect(entity);
    }

    public MotSolSelect getMotSolSelect(Long idMotSolSelected)
        throws Exception {
        MotSolSelect motSolSelect = null;

        try {
            motSolSelect = motSolSelectLogic.getMotSolSelect(idMotSolSelected);
        } catch (Exception e) {
            throw e;
        }

        return motSolSelect;
    }

    public List<MotSolSelect> findByCriteriaInMotSolSelect(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return motSolSelectLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<MotSolSelect> findPageMotSolSelect(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return motSolSelectLogic.findPageMotSolSelect(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberMotSolSelect() throws Exception {
        return motSolSelectLogic.findTotalNumberMotSolSelect();
    }

    public List<MotSolSelectDTO> getDataMotSolSelect()
        throws Exception {
        return motSolSelectLogic.getDataMotSolSelect();
    }

    public List<MotSolXTpSol> getMotSolXTpSol() throws Exception {
        return motSolXTpSolLogic.getMotSolXTpSol();
    }

    public void saveMotSolXTpSol(MotSolXTpSol entity) throws Exception {
        motSolXTpSolLogic.saveMotSolXTpSol(entity);
    }

    public void deleteMotSolXTpSol(MotSolXTpSol entity)
        throws Exception {
        motSolXTpSolLogic.deleteMotSolXTpSol(entity);
    }

    public void updateMotSolXTpSol(MotSolXTpSol entity)
        throws Exception {
        motSolXTpSolLogic.updateMotSolXTpSol(entity);
    }

    public MotSolXTpSol getMotSolXTpSol(Long idMotSolXTpSol)
        throws Exception {
        MotSolXTpSol motSolXTpSol = null;

        try {
            motSolXTpSol = motSolXTpSolLogic.getMotSolXTpSol(idMotSolXTpSol);
        } catch (Exception e) {
            throw e;
        }

        return motSolXTpSol;
    }

    public List<MotSolXTpSol> findByCriteriaInMotSolXTpSol(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return motSolXTpSolLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<MotSolXTpSol> findPageMotSolXTpSol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return motSolXTpSolLogic.findPageMotSolXTpSol(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberMotSolXTpSol() throws Exception {
        return motSolXTpSolLogic.findTotalNumberMotSolXTpSol();
    }

    public List<MotSolXTpSolDTO> getDataMotSolXTpSol()
        throws Exception {
        return motSolXTpSolLogic.getDataMotSolXTpSol();
    }

    public List<ParametrosPqr> getParametrosPqr() throws Exception {
        return parametrosPqrLogic.getParametrosPqr();
    }

    public void saveParametrosPqr(ParametrosPqr entity)
        throws Exception {
        parametrosPqrLogic.saveParametrosPqr(entity);
    }

    public void deleteParametrosPqr(ParametrosPqr entity)
        throws Exception {
        parametrosPqrLogic.deleteParametrosPqr(entity);
    }

    public void updateParametrosPqr(ParametrosPqr entity)
        throws Exception {
        parametrosPqrLogic.updateParametrosPqr(entity);
    }

    public ParametrosPqr getParametrosPqr(Long idParam)
        throws Exception {
        ParametrosPqr parametrosPqr = null;

        try {
            parametrosPqr = parametrosPqrLogic.getParametrosPqr(idParam);
        } catch (Exception e) {
            throw e;
        }

        return parametrosPqr;
    }

    public List<ParametrosPqr> findByCriteriaInParametrosPqr(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return parametrosPqrLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<ParametrosPqr> findPageParametrosPqr(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return parametrosPqrLogic.findPageParametrosPqr(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberParametrosPqr() throws Exception {
        return parametrosPqrLogic.findTotalNumberParametrosPqr();
    }

    public List<ParametrosPqrDTO> getDataParametrosPqr()
        throws Exception {
        return parametrosPqrLogic.getDataParametrosPqr();
    }

    public List<RespuestaSol> getRespuestaSol() throws Exception {
        return respuestaSolLogic.getRespuestaSol();
    }

    public void saveRespuestaSol(RespuestaSol entity) throws Exception {
        respuestaSolLogic.saveRespuestaSol(entity);
    }

    public void deleteRespuestaSol(RespuestaSol entity)
        throws Exception {
        respuestaSolLogic.deleteRespuestaSol(entity);
    }

    public void updateRespuestaSol(RespuestaSol entity)
        throws Exception {
        respuestaSolLogic.updateRespuestaSol(entity);
    }
    
    public List<RespuestaSol> consultarRespuestasSolicitud(Long idSolPqr) 
    	throws Exception {
    	return respuestaSolLogic.consultarRespuestasSolicitud(idSolPqr);
    }
    
    public RespuestaSol getRespuestaSol(Long idRespSol)
        throws Exception {
        RespuestaSol respuestaSol = null;

        try {
            respuestaSol = respuestaSolLogic.getRespuestaSol(idRespSol);
        } catch (Exception e) {
            throw e;
        }

        return respuestaSol;
    }

    public List<RespuestaSol> findByCriteriaInRespuestaSol(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return respuestaSolLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<RespuestaSol> findPageRespuestaSol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return respuestaSolLogic.findPageRespuestaSol(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberRespuestaSol() throws Exception {
        return respuestaSolLogic.findTotalNumberRespuestaSol();
    }

    public List<RespuestaSolDTO> getDataRespuestaSol()
        throws Exception {
        return respuestaSolLogic.getDataRespuestaSol();
    }

    public List<Roles> getRoles() throws Exception {
        return rolesLogic.getRoles();
    }

    public void saveRoles(Roles entity) throws Exception {
        rolesLogic.saveRoles(entity);
    }

    public void deleteRoles(Roles entity) throws Exception {
        rolesLogic.deleteRoles(entity);
    }

    public void updateRoles(Roles entity) throws Exception {
        rolesLogic.updateRoles(entity);
    }

    public Roles getRoles(Long idRol) throws Exception {
        Roles roles = null;

        try {
            roles = rolesLogic.getRoles(idRol);
        } catch (Exception e) {
            throw e;
        }

        return roles;
    }

    public List<Roles> findByCriteriaInRoles(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return rolesLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Roles> findPageRoles(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return rolesLogic.findPageRoles(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberRoles() throws Exception {
        return rolesLogic.findTotalNumberRoles();
    }

    public List<RolesDTO> getDataRoles() throws Exception {
        return rolesLogic.getDataRoles();
    }

    public List<SolicitudAsignadaArea> getSolicitudAsignadaArea()
        throws Exception {
        return solicitudAsignadaAreaLogic.getSolicitudAsignadaArea();
    }

    public void saveSolicitudAsignadaArea(SolicitudAsignadaArea entity)
        throws Exception {
        solicitudAsignadaAreaLogic.saveSolicitudAsignadaArea(entity);
    }

    public void deleteSolicitudAsignadaArea(SolicitudAsignadaArea entity)
        throws Exception {
        solicitudAsignadaAreaLogic.deleteSolicitudAsignadaArea(entity);
    }

    public void updateSolicitudAsignadaArea(SolicitudAsignadaArea entity)
        throws Exception {
        solicitudAsignadaAreaLogic.updateSolicitudAsignadaArea(entity);
    }

    public SolicitudAsignadaArea getSolicitudAsignadaArea(Long idSolAsigArea)
        throws Exception {
        SolicitudAsignadaArea solicitudAsignadaArea = null;

        try {
            solicitudAsignadaArea = solicitudAsignadaAreaLogic.getSolicitudAsignadaArea(idSolAsigArea);
        } catch (Exception e) {
            throw e;
        }

        return solicitudAsignadaArea;
    }

    public List<SolicitudAsignadaArea> findByCriteriaInSolicitudAsignadaArea(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return solicitudAsignadaAreaLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<SolicitudAsignadaArea> findPageSolicitudAsignadaArea(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return solicitudAsignadaAreaLogic.findPageSolicitudAsignadaArea(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSolicitudAsignadaArea()
        throws Exception {
        return solicitudAsignadaAreaLogic.findTotalNumberSolicitudAsignadaArea();
    }

    public List<SolicitudAsignadaAreaDTO> getDataSolicitudAsignadaArea()
        throws Exception {
        return solicitudAsignadaAreaLogic.getDataSolicitudAsignadaArea();
    }

    public List<SolicitudPqr> getSolicitudPqr() throws Exception {
        return solicitudPqrLogic.getSolicitudPqr();
    }

    public void saveSolicitudPqr(SolicitudPqr entity) throws Exception {
        solicitudPqrLogic.saveSolicitudPqr(entity);
    }
    
    public void saveSolicitud(InfoSolicitante infoSol, SolicitudPqr solicitudPqr, 
    		MotSolSelect motSolSelect, MotReclSelect motReclSelect, 
    		SolicitudAsignadaArea solicitudAsignadaArea,
    		List<AnexosSolicitante> anexosSolicitantes) throws Exception{
    	solicitudLogic.saveSolicitudPqr(infoSol, solicitudPqr, motSolSelect, 
    		motReclSelect, solicitudAsignadaArea, anexosSolicitantes);
    }
    
    public void saveRespuestaSolicitud(SolicitudAsignadaArea solicitudAsignadaArea, 
    		RespuestaSol respuestaSol, List<AnexosRespuesta> anexosRespuestas) throws Exception {
    	solicitudLogic.saveRespuestaSolicitud(solicitudAsignadaArea, respuestaSol, anexosRespuestas);
    }
    
    public List<SolicitudDTO> consultarAsignacion(AreasInvolucradas area) throws Exception{
    	return solicitudLogic.consultarAsignacion(area);
    }
    
    public List<SolicitudPqr> consultarSolicitudes(Long idAreaInvolucrada) throws Exception {
    	return solicitudLogic.consultarSolicitudes(idAreaInvolucrada);
    }
    
    public void deleteSolicitudPqr(SolicitudPqr entity)
        throws Exception {
        solicitudPqrLogic.deleteSolicitudPqr(entity);
    }

    public void updateSolicitudPqr(SolicitudPqr entity)
        throws Exception {
        solicitudPqrLogic.updateSolicitudPqr(entity);
    }

    public SolicitudPqr getSolicitudPqr(Long idSolPqr)
        throws Exception {
        SolicitudPqr solicitudPqr = null;

        try {
            solicitudPqr = solicitudPqrLogic.getSolicitudPqr(idSolPqr);
        } catch (Exception e) {
            throw e;
        }

        return solicitudPqr;
    }
    
    public List<EstadisticasDTO> consultarSolicitudPorEstado(String estado) throws Exception{
    	List<EstadisticasDTO> solicitudPqr =  null;
    	try {
			solicitudPqr = solicitudPqrLogic.consultarSolicitudPorEstado(estado);
		} catch (Exception e) {
			throw e;
		}
    	return solicitudPqr;
    }
    
    public List<EstadisticasDTO> consultarSolicitudNumeroRadicacion(String numeroRadicacion) throws Exception{
    	List<EstadisticasDTO> solicitudPqr = null;
    	try {
			solicitudPqr = solicitudPqrLogic.consultarSolicitudNumeroRadicacion(numeroRadicacion);
		} catch (Exception e) {
			throw e;
		}
    	return solicitudPqr;
    }
    
    public List<EstadisticasDTO> consultarSolicitudMotivoReclamacion(Long idMotivoReclamacion) throws Exception {
    	List<EstadisticasDTO> solicitudPqr = null;
    	try {
			solicitudPqr = solicitudPqrLogic.consultarSolicitudMotivoReclamacion(idMotivoReclamacion);
		} catch (Exception e) {
			throw e;
		}
    	return solicitudPqr;
	}
    
    public List<EstadisticasDTO> consultarSolicitudPorFecha(String fechaInicio, String fechaFin) throws Exception {
    	List<EstadisticasDTO> solicitudPqr = null;
    	try {
			solicitudPqr = solicitudPqrLogic.consultarSolicitudPorFecha(fechaInicio, fechaFin);
		} catch (Exception e) {
			throw e;
		}
    	return solicitudPqr;
	}
    
    public List<EstadisticasDTO> consultarSolicitudMotivoReclamacionEstado(Long idMotivoReclamacion, String estado) throws Exception{
    	List<EstadisticasDTO> solicitudPqr = null;
    	try {
			solicitudPqr = solicitudPqrLogic.consultarSolicitudMotivoReclamacionEstado(idMotivoReclamacion, estado);
		} catch (Exception e) {
			throw e;
		}
    	return solicitudPqr;
	}
    
    public List<EstadisticasDTO> consultarSolicitudPorEstadoYFechas(String estado, String fechaInicio, String fechaFin) throws Exception{
    	List<EstadisticasDTO> solicitudPqr = null;
    	try {
			solicitudPqr = solicitudPqrLogic.consultarSolicitudPorEstadoYFechas(estado, fechaInicio, fechaFin);
		} catch (Exception e) {
			throw e;
		}
    	return solicitudPqr;
	}
    
    public List<EstadisticasDTO> consultarSolicitudPorReclamacionYFechas(Long idMotivoReclamacion, String fechaInicio, String fechaFin) throws Exception{
    	List<EstadisticasDTO> solicitudPqr = null;
    	try {
			solicitudPqr = solicitudPqrLogic.consultarSolicitudPorReclamacionYFechas(idMotivoReclamacion, fechaInicio, fechaFin);
		} catch (Exception e) {
			throw e;
		}
    	return solicitudPqr;
	}
    
    public List<EstadisticasDTO> consultarSolicitudPorReclamacionEstadoYFechas(Long idMotivoReclamacion, String estado, String fechaInicio, String fechaFin) throws Exception{
    	List<EstadisticasDTO> solicitudPqr = null;
    	try {
			solicitudPqr = solicitudPqrLogic.consultarSolicitudPorReclamacionEstadoYFechas(idMotivoReclamacion, estado, fechaInicio, fechaFin);
		} catch (Exception e) {
			throw e;
		}
    	return solicitudPqr;
	}

    public List<SolicitudPqr> findByCriteriaInSolicitudPqr(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return solicitudPqrLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SolicitudPqr> findPageSolicitudPqr(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return solicitudPqrLogic.findPageSolicitudPqr(sortColumnName,
            sortAscending, startRow, maxResults);
    }
    
    

    public Long findTotalNumberSolicitudPqr() throws Exception {
        return solicitudPqrLogic.findTotalNumberSolicitudPqr();
    }

    public List<SolicitudPqrDTO> getDataSolicitudPqr()
        throws Exception {
        return solicitudPqrLogic.getDataSolicitudPqr();
    }

    public List<TipoDocumento> getTipoDocumento() throws Exception {
        return tipoDocumentoLogic.getTipoDocumento();
    }

    public void saveTipoDocumento(TipoDocumento entity)
        throws Exception {
        tipoDocumentoLogic.saveTipoDocumento(entity);
    }

    public void deleteTipoDocumento(TipoDocumento entity)
        throws Exception {
        tipoDocumentoLogic.deleteTipoDocumento(entity);
    }

    public void updateTipoDocumento(TipoDocumento entity)
        throws Exception {
        tipoDocumentoLogic.updateTipoDocumento(entity);
    }

    public TipoDocumento getTipoDocumento(Long idTpDoc)
        throws Exception {
        TipoDocumento tipoDocumento = null;

        try {
            tipoDocumento = tipoDocumentoLogic.getTipoDocumento(idTpDoc);
        } catch (Exception e) {
            throw e;
        }

        return tipoDocumento;
    }

    public List<TipoDocumento> findByCriteriaInTipoDocumento(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return tipoDocumentoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<TipoDocumento> findPageTipoDocumento(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return tipoDocumentoLogic.findPageTipoDocumento(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberTipoDocumento() throws Exception {
        return tipoDocumentoLogic.findTotalNumberTipoDocumento();
    }

    public List<TipoDocumentoDTO> getDataTipoDocumento()
        throws Exception {
        return tipoDocumentoLogic.getDataTipoDocumento();
    }

    public List<TipoEstadoPqr> getTipoEstadoPqr() throws Exception {
        return tipoEstadoPqrLogic.getTipoEstadoPqr();
    }

    public void saveTipoEstadoPqr(TipoEstadoPqr entity)
        throws Exception {
        tipoEstadoPqrLogic.saveTipoEstadoPqr(entity);
    }

    public void deleteTipoEstadoPqr(TipoEstadoPqr entity)
        throws Exception {
        tipoEstadoPqrLogic.deleteTipoEstadoPqr(entity);
    }

    public void updateTipoEstadoPqr(TipoEstadoPqr entity)
        throws Exception {
        tipoEstadoPqrLogic.updateTipoEstadoPqr(entity);
    }

    public TipoEstadoPqr getTipoEstadoPqr(Long idTpEstPqr)
        throws Exception {
        TipoEstadoPqr tipoEstadoPqr = null;

        try {
            tipoEstadoPqr = tipoEstadoPqrLogic.getTipoEstadoPqr(idTpEstPqr);
        } catch (Exception e) {
            throw e;
        }

        return tipoEstadoPqr;
    }

    public List<TipoEstadoPqr> findByCriteriaInTipoEstadoPqr(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return tipoEstadoPqrLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<TipoEstadoPqr> findPageTipoEstadoPqr(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return tipoEstadoPqrLogic.findPageTipoEstadoPqr(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberTipoEstadoPqr() throws Exception {
        return tipoEstadoPqrLogic.findTotalNumberTipoEstadoPqr();
    }

    public List<TipoEstadoPqrDTO> getDataTipoEstadoPqr()
        throws Exception {
        return tipoEstadoPqrLogic.getDataTipoEstadoPqr();
    }

    public List<TipoSolicitudPqr> getTipoSolicitudPqr()
        throws Exception {
        return tipoSolicitudPqrLogic.getTipoSolicitudPqr();
    }
    
    public void save_mot_recl_mot_sol_anxs_x_tipo(TipoSolicitudPqr tipoSol,
    		List<MotivoReclamacion> motivosReclamacionTargetCopia,
    		List<MotivoReclamacion> motivosReclamacionTarget,
    		List<MotivoSolicitud> motivosSolicitudTargetCopia,
			List<MotivoSolicitud> motivosSolicitudTarget,
			List<AnexosPqr> anexosPqrTargetCopia,
			List<AnexosPqr> anexosPqrTarget, String esObligatorioSeleccionado) 
            throws Exception {
            tipoSolicitudPqrLogic.save_mot_recl_mot_sol_anxs_x_tipo(tipoSol,
            		motivosReclamacionTargetCopia, motivosReclamacionTarget, motivosSolicitudTargetCopia,
        			motivosSolicitudTarget, anexosPqrTargetCopia, anexosPqrTarget, esObligatorioSeleccionado);
    }
    
    public void save_anxs_x_area(AreasInvolucradas areasInvolucradas,
			List<AnexosPqr> anexosPqrTargetCopia,
			List<AnexosPqr> anexosPqrTarget, String esObligatorioSeleccionado) throws Exception{
        	anxsXAreaLogic.save_anxs_x_area(areasInvolucradas,
        			anexosPqrTargetCopia, anexosPqrTarget, esObligatorioSeleccionado);
    }

    public void saveTipoSolicitudPqr(TipoSolicitudPqr entity)
        throws Exception {
        tipoSolicitudPqrLogic.saveTipoSolicitudPqr(entity);
    }

    public void deleteTipoSolicitudPqr(TipoSolicitudPqr entity)
        throws Exception {
        tipoSolicitudPqrLogic.deleteTipoSolicitudPqr(entity);
    }

    public void updateTipoSolicitudPqr(TipoSolicitudPqr entity)
        throws Exception {
        tipoSolicitudPqrLogic.updateTipoSolicitudPqr(entity);
    }

    public TipoSolicitudPqr getTipoSolicitudPqr(Long idTpSolPqr)
        throws Exception {
        TipoSolicitudPqr tipoSolicitudPqr = null;

        try {
            tipoSolicitudPqr = tipoSolicitudPqrLogic.getTipoSolicitudPqr(idTpSolPqr);
        } catch (Exception e) {
            throw e;
        }

        return tipoSolicitudPqr;
    }

    public List<TipoSolicitudPqr> findByCriteriaInTipoSolicitudPqr(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return tipoSolicitudPqrLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<TipoSolicitudPqr> findPageTipoSolicitudPqr(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return tipoSolicitudPqrLogic.findPageTipoSolicitudPqr(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberTipoSolicitudPqr() throws Exception {
        return tipoSolicitudPqrLogic.findTotalNumberTipoSolicitudPqr();
    }

    public List<TipoSolicitudPqrDTO> getDataTipoSolicitudPqr()
        throws Exception {
        return tipoSolicitudPqrLogic.getDataTipoSolicitudPqr();
    }

    public List<UsuariosInternos> getUsuariosInternos()
        throws Exception {
        return usuariosInternosLogic.getUsuariosInternos();
    }

    public void saveUsuariosInternos(UsuariosInternos entity)
        throws Exception {
        usuariosInternosLogic.saveUsuariosInternos(entity);
    }

    public void deleteUsuariosInternos(UsuariosInternos entity)
        throws Exception {
        usuariosInternosLogic.deleteUsuariosInternos(entity);
    }

    public void updateUsuariosInternos(UsuariosInternos entity)
        throws Exception {
        usuariosInternosLogic.updateUsuariosInternos(entity);
    }

    public UsuariosInternos getUsuariosInternos(Long idUsuInterno)
        throws Exception {
        UsuariosInternos usuariosInternos = null;

        try {
            usuariosInternos = usuariosInternosLogic.getUsuariosInternos(idUsuInterno);
        } catch (Exception e) {
            throw e;
        }

        return usuariosInternos;
    }

    public List<UsuariosInternos> findByCriteriaInUsuariosInternos(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return usuariosInternosLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<UsuariosInternos> findPageUsuariosInternos(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return usuariosInternosLogic.findPageUsuariosInternos(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberUsuariosInternos() throws Exception {
        return usuariosInternosLogic.findTotalNumberUsuariosInternos();
    }

    public List<UsuariosInternosDTO> getDataUsuariosInternos()
        throws Exception {
        return usuariosInternosLogic.getDataUsuariosInternos();
    }
    
    public UsuariosInternos consultarLoginContrasena(String login, String contrasena) throws Exception {
    	return usuariosInternosLogic.consultarLoginContrasena(login, contrasena);
    }
}